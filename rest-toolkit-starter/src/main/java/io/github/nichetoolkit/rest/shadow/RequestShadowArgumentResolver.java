package io.github.nichetoolkit.rest.shadow;

import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

@Slf4j
public class RequestShadowArgumentResolver implements HandlerMethodArgumentResolver {

    private final StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestShadow.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        RequestShadow requestShadow = parameter.getParameterAnnotation(RequestShadow.class);
        assert requestShadow != null;
        RestHttpRequest request = RestHttpRequest.getHttpRequest((HttpServletRequest) webRequest.getNativeRequest());
        Class<?> filterType = requestShadow.type() != Object.class ? requestShadow.type() : parameter.getParameterType();
        List<String> multiFields = filterMultiFields(requestShadow, filterType);
        Object filter;
        if (multipartResolver.isMultipart(request) || requestShadow.formData()) {
            MultipartHttpServletRequest multipartRequest = multipartResolver.resolveMultipart(request);
            Map<String, Object> parameterMap = filterParameterMap(multiFields, multipartRequest.getParameterMap());
            String parameterJson = JsonUtils.parseJson(parameterMap);
            filter = JsonUtils.parseBean(parameterJson, filterType);
            if (requestShadow.formFile()) {
                Iterator<String> fileNames = multipartRequest.getFileNames();
                while (fileNames.hasNext()) {
                    String fileName = fileNames.next();
                    if (requestShadow.multiFile()) {
                        List<MultipartFile> files = multipartRequest.getFiles(fileName);
                        if (GeneralUtils.isNotEmpty(files)) {
                            filterOfFilesValue(filterType, filter, fileName, files);
                        }
                    } else {
                        MultipartFile multipartFile = multipartRequest.getFile(fileName);
                        if (GeneralUtils.isNotEmpty(multipartFile)) {
                            filterOfFileValue(filterType, filter, fileName, multipartFile);
                        }
                    }
                }
            }
            multipartResolver.cleanupMultipart(multipartRequest);
        } else {
            Map<String, Object> parameterMap = filterParameterMap(multiFields, request.getParameterMap());
            String parameterJson = JsonUtils.parseJson(parameterMap);
            filter = JsonUtils.parseBean(parameterJson, filterType);
        }
        return Optional.ofNullable(filter).orElse(filterType.newInstance());
    }

    private void filterFieldNames(List<String> fieldNames, Class<?> type) {
        Field[] declaredFields = type.getDeclaredFields();
        if (GeneralUtils.isNotEmpty(declaredFields)) {
            Arrays.stream(declaredFields).forEach(field -> {
                if (GeneralUtils.isNotEmpty(field.getAnnotation(RestMutiField.class))) {
                    fieldNames.add(field.getName());
                }
            });
        }
        Class<?> superclass = type.getSuperclass();
        if (GeneralUtils.isNotEmpty(superclass)) {
            filterFieldNames(fieldNames, superclass);
        }
    }

    private List<String> filterMultiFields(RequestShadow requestShadow, Class<?> filterType) {
        List<String> fieldNames = new ArrayList<>();
        String[] multiFields = requestShadow.multiFields();
        if (GeneralUtils.isNotEmpty(multiFields)) {
            fieldNames.addAll(Arrays.asList(multiFields));
        }
        filterFieldNames(fieldNames,filterType);
        return fieldNames;
    }

    private Map<String, Object> filterParameterMap(List<String> multiFields, Map<String, String[]> parameterMap) {
        Map<String, Object> parameters = new LinkedHashMap<>(parameterMap.size());
        parameterMap.forEach((key, value) -> {
            if (multiFields.contains(key)) {
                List<String> fields = new ArrayList<>();
                Arrays.stream(value).forEach(field -> {
                    if (field.contains(",")) {
                        String[] split = field.split(",");
                        fields.addAll(Arrays.asList(split));
                    } else {
                        fields.add(field);
                    }
                });
                parameters.put(key, fields);
            } else if (GeneralUtils.isNotEmpty(value)) {
                parameters.put(key, value[0]);
            }
        });
        return parameters;
    }

    private void filterOfFileValue(Class<?> filterType, Object filter, String fileName, MultipartFile multipartFile) throws Exception {
        Field declaredField;
        try {
            declaredField = filterType.getDeclaredField(fileName);
        } catch (NoSuchFieldException ignored) {
            return;
        }
        filterOfFileValue(declaredField, filter, multipartFile);
    }

    private void filterOfFilesValue(Class<?> filterType, Object filter, String fileName, List<MultipartFile> files) throws Exception {
        Field declaredField;
        try {
            declaredField = filterType.getDeclaredField(fileName);
        } catch (NoSuchFieldException ignored) {
            return;
        }

        Type genericType = declaredField.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type rawType = parameterizedType.getRawType();
            if (!(rawType instanceof Class)) {
                return;
            }
            Class<?> rawClass = (Class<?>) rawType;
            if (Collection.class.isAssignableFrom(rawClass)) {
                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                Type actualType = typeArguments[0];
                if (actualType instanceof ParameterizedType) {
                    ParameterizedType actualParameterizedType = (ParameterizedType) actualType;
                    Type actualRawType = actualParameterizedType.getRawType();
                    if (!(actualRawType instanceof Class)) {
                        return;
                    }
                    if (MultipartFile.class.isAssignableFrom((Class<?>) actualRawType)) {
                        declaredField.set(filter, files.toArray(new MultipartFile[0]));
                    }
                }
            }

        } else if (genericType instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) genericType;
            Type genericComponentType = genericArrayType.getGenericComponentType();
            if (genericComponentType instanceof ParameterizedType) {
                ParameterizedType arrayParameterizedType = (ParameterizedType) genericComponentType;
                Type arrayRawType = arrayParameterizedType.getRawType();
                if (!(arrayRawType instanceof Class)) {
                    return;
                }
                if (MultipartFile.class.isAssignableFrom((Class<?>) arrayRawType)) {

                    declaredField.set(filter, files);
                }
            }
        } else {
            MultipartFile multipartFile = files.get(0);
            if (GeneralUtils.isNotEmpty(multipartFile)) {
                filterOfFileValue(declaredField, filter, multipartFile);
            }
        }
    }

    private void filterOfFileValue(Field declaredField, Object filter, MultipartFile file) throws Exception {
        Class<?> fieldType = declaredField.getType();
        declaredField.setAccessible(true);
        if (fieldType == MultipartFile.class) {
            declaredField.set(filter, file);
        }
    }

//    private Object parseFromRequestParams(HttpServletRequest request, Class<?> targetClass) throws Exception {
//
//        Object obj = targetClass.newInstance();
//        Map<String, String[]> parameterMap = request.getParameterMap();
//
//        for (Field field : getAllFields(targetClass)) {
//            field.setAccessible(true);
//            String fieldName = field.getName();
//
//            // 处理嵌套对象（如：address.city）
//            if (field.getType().isMemberClass()) {
//                setNestedFieldValue(obj, field, parameterMap, fieldName);
//                continue;
//            }
//
//            String[] values = parameterMap.get(fieldName);
//            if (values != null && values.length > 0) {
//                setFieldValue(obj, field, values[0]);
//            }
//        }
//
//        return obj;
//    }
//
//
//    private void setNestedFieldValue(Object parentObj, Field field, Map<String, String[]> parameterMap, String prefix) throws Exception {
//        Class<?> fieldType = field.getType();
//        Object nestedObj = fieldType.newInstance();
//
//        for (Field nestedField : getAllFields(fieldType)) {
//            nestedField.setAccessible(true);
//            String nestedFieldName = nestedField.getName();
//            String paramName = prefix + "." + nestedFieldName;
//
//            String[] values = parameterMap.get(paramName);
//            if (values != null && values.length > 0) {
//                setFieldValue(nestedObj, nestedField, values[0]);
//            }
//        }
//
//        field.set(parentObj, nestedObj);
//    }
//
//
//    private void setFileValue(Object pojo, String fileName, MultipartFile file) throws Exception {
//        Class<?> clazz = pojo.getClass();
//        Field field;
//        try {
//            field = clazz.getDeclaredField(fileName);
//        } catch (NoSuchFieldException e) {
//            return; // 如果没有对应的字段，则忽略该文件
//        }
//
//        field.setAccessible(true);
//        Class<?> fieldType = field.getType();
//
//        if (fieldType == MultipartFile.class) {
//            field.set(pojo, file);
//        }
//        // 这里可以支持其他类型，比如byte[]等，根据需要转换
//    }
//
//    private void setFieldValue(Object filter, Map<String, String[]> parameterMap) throws Exception {
//        Class<?> clazz = filter.getClass();
//        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
//            String paramName = entry.getKey();
//            String[] values = entry.getValue();
//            // 使用反射找到对应的字段
//            Field field;
//            try {
//                field = clazz.getDeclaredField(paramName);
//            } catch (NoSuchFieldException e) {
//                // 如果没有找到字段，可能是嵌套对象，这里我们简化处理，只处理一级字段
//                continue;
//            }
//
//            field.setAccessible(true);
//            Class<?> fieldType = field.getType();
//
//            // 这里只处理String和基本类型，如果是其他类型需要更复杂的转换
//            if (fieldType == String.class) {
//                field.set(filter, values[0]);
//            } else if (fieldType == Integer.class || fieldType == int.class) {
//                field.set(filter, Integer.parseInt(values[0]));
//            } else if (fieldType == Long.class || fieldType == long.class) {
//                field.set(filter, Long.parseLong(values[0]));
//            } else if (fieldType == Double.class || fieldType == double.class) {
//                field.set(filter, Double.parseDouble(values[0]));
//            } else if (fieldType == Float.class || fieldType == float.class) {
//                field.set(filter, Float.parseFloat(values[0]));
//            } else if (fieldType == Boolean.class || fieldType == boolean.class) {
//                field.set(filter, Boolean.parseBoolean(values[0]));
//            }
//            // 其他类型可以继续扩展
//        }
//    }
//
//    private void setFieldValue(Object obj, Field field, String value) throws Exception {
//        Class<?> fieldType = field.getType();
//        if (fieldType == String.class) {
//            field.set(obj, value);
//        } else if (fieldType == Integer.class || fieldType == int.class) {
//            field.set(obj, Integer.parseInt(value));
//        } else if (fieldType == Long.class || fieldType == long.class) {
//            field.set(obj, Long.parseLong(value));
//        } else if (fieldType == Double.class || fieldType == double.class) {
//            field.set(obj, Double.parseDouble(value));
//        } else if (fieldType == Float.class || fieldType == float.class) {
//            field.set(obj, Float.parseFloat(value));
//        } else if (fieldType == Boolean.class || fieldType == boolean.class) {
//            field.set(obj, Boolean.parseBoolean(value));
//        } else if (fieldType == Date.class) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            field.set(obj, sdf.parse(value));
//        }
//    }
//
//    private List<Field> getAllFields(Class<?> clazz) {
//        List<Field> fields = new ArrayList<>();
//        while (clazz != null) {
//            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
//            clazz = clazz.getSuperclass();
//        }
//        return fields;
//    }
}