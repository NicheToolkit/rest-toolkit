package io.github.nichetoolkit.rest.parsing;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.configure.RestParsingProperties;
import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
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
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <code>RequestParsingArgumentResolver</code>
 * <p>The request parsing argument resolver class.</p>
 * @see  org.springframework.web.method.support.HandlerMethodArgumentResolver
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public abstract class RequestParsingArgumentResolver implements HandlerMethodArgumentResolver {
    /**
     * <code>multipartResolver</code>
     * {@link org.springframework.web.multipart.support.StandardServletMultipartResolver} <p>The <code>multipartResolver</code> field.</p>
     * @see  org.springframework.web.multipart.support.StandardServletMultipartResolver
     */
    private final StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();

    /**
     * <code>parsingProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestParsingProperties} <p>The <code>parsingProperties</code> field.</p>
     * @see  io.github.nichetoolkit.rest.configure.RestParsingProperties
     */
    private final RestParsingProperties parsingProperties;

    /**
     * <code>RequestParsingArgumentResolver</code>
     * <p>Instantiates a new request parsing argument resolver.</p>
     * @param parsingProperties {@link io.github.nichetoolkit.rest.configure.RestParsingProperties} <p>The parsing properties parameter is <code>RestParsingProperties</code> type.</p>
     * @see  io.github.nichetoolkit.rest.configure.RestParsingProperties
     */
    public RequestParsingArgumentResolver(RestParsingProperties parsingProperties) {
        this.parsingProperties = parsingProperties;
    }

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestParsing.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        RequestParsing requestParsing = parameter.getParameterAnnotation(RequestParsing.class);
        Assert.state(requestParsing != null, "No RequestParsing annotation");
        RestHttpRequest request = RestHttpRequest.getHttpRequest((HttpServletRequest) webRequest.getNativeRequest());
        Class<?> parsingType = requestParsing.type() != Object.class ? requestParsing.type() : parameter.getParameterType();
        List<RestParsingFieldPack> parsingFields = parsingFields(parsingType);
        Object parsing;
        if (multipartResolver.isMultipart(request) && parsingProperties.getFormData()) {
            MultipartHttpServletRequest multipartRequest = multipartResolver.resolveMultipart(request);
            Map<String, Object> parameterMap = parsingParameterMap(parsingFields, multipartRequest.getParameterMap());
            parsing = beforeParsingParameter(parameterMap, parsingType);
            if (parsingProperties.getFormFile()) {
                Iterator<String> fileNames = multipartRequest.getFileNames();
                while (fileNames.hasNext()) {
                    String fileName = fileNames.next();
                    if (parsingProperties.getMultiFile()) {
                        List<MultipartFile> files = multipartRequest.getFiles(fileName);
                        if (GeneralUtils.isNotEmpty(files)) {
                            parsingMultiFileValue(parsingType, parsing, fileName, files);
                        }
                    } else {
                        MultipartFile multipartFile = multipartRequest.getFile(fileName);
                        if (GeneralUtils.isNotEmpty(multipartFile)) {
                            parsingSingleFileValue(parsingType, parsing, fileName, multipartFile);
                        }
                    }
                }
            }
            multipartResolver.cleanupMultipart(multipartRequest);
        } else {
            Map<String, Object> parameterMap = parsingParameterMap(parsingFields, request.getParameterMap());
            parsing = beforeParsingParameter(parameterMap, parsingType);
        }
        parsing = afterParsingArgument(parsing, parsingType);
        return parsing;
    }


    /**
     * <code>afterParsingArgument</code>
     * <p>The after parsing argument method.</p>
     * @param parsing {@link java.lang.Object} <p>The parsing parameter is <code>Object</code> type.</p>
     * @param parsingType {@link java.lang.Class} <p>The parsing type parameter is <code>Class</code> type.</p>
     * @see  java.lang.Object
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.RestException
     * @see  java.lang.InstantiationException
     * @see  java.lang.IllegalAccessException
     * @return  {@link java.lang.Object} <p>The after parsing argument return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @throws InstantiationException {@link java.lang.InstantiationException} <p>The instantiation exception is <code>InstantiationException</code> type.</p>
     * @throws IllegalAccessException {@link java.lang.IllegalAccessException} <p>The illegal access exception is <code>IllegalAccessException</code> type.</p>
     */
    protected Object afterParsingArgument(Object parsing, Class<?> parsingType) throws RestException, InstantiationException, IllegalAccessException {
        return Optional.ofNullable(parsing).orElse(parsingType.newInstance());
    }

    /**
     * <code>beforeParsingParameter</code>
     * <p>The before parsing parameter method.</p>
     * @param parameters {@link java.util.Map} <p>The parameters parameter is <code>Map</code> type.</p>
     * @param parsingType {@link java.lang.Class} <p>The parsing type parameter is <code>Class</code> type.</p>
     * @see  java.util.Map
     * @see  java.lang.Class
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.lang.Object} <p>The before parsing parameter return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    protected Object beforeParsingParameter(Map<String, Object> parameters, Class<?> parsingType) throws RestException {
        return JsonUtils.parseConvert(parameters, parsingType);
    }

    /**
     * <code>resolveSingleFile</code>
     * <p>The resolve single file method.</p>
     * @param fileName {@link java.lang.String} <p>The file name parameter is <code>String</code> type.</p>
     * @param fieldType {@link java.lang.Class} <p>The field type parameter is <code>Class</code> type.</p>
     * @param file {@link org.springframework.web.multipart.MultipartFile} <p>The file parameter is <code>MultipartFile</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  java.lang.Object
     * @see  java.lang.Exception
     * @return  {@link java.lang.Object} <p>The resolve single file return object is <code>Object</code> type.</p>
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    protected Object resolveSingleFile(String fileName, Class<?> fieldType, MultipartFile file) throws Exception {
        return null;
    }

    /**
     * <code>resolveListFile</code>
     * <p>The resolve list file method.</p>
     * @param fileName {@link java.lang.String} <p>The file name parameter is <code>String</code> type.</p>
     * @param fieldType {@link java.lang.Class} <p>The field type parameter is <code>Class</code> type.</p>
     * @param files {@link java.util.List} <p>The files parameter is <code>List</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  java.util.List
     * @see  java.lang.Object
     * @see  java.lang.Exception
     * @return  {@link java.lang.Object} <p>The resolve list file return object is <code>Object</code> type.</p>
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    protected Object resolveListFile(String fileName, Class<?> fieldType, List<MultipartFile> files) throws Exception {
        return null;
    }

    /**
     * <code>resolveArrayFile</code>
     * <p>The resolve array file method.</p>
     * @param fileName {@link java.lang.String} <p>The file name parameter is <code>String</code> type.</p>
     * @param fieldType {@link java.lang.Class} <p>The field type parameter is <code>Class</code> type.</p>
     * @param files {@link org.springframework.web.multipart.MultipartFile} <p>The files parameter is <code>MultipartFile</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  java.lang.Object
     * @see  java.lang.Exception
     * @return  {@link java.lang.Object} <p>The resolve array file return object is <code>Object</code> type.</p>
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    protected Object resolveArrayFile(String fileName, Class<?> fieldType, MultipartFile[] files) throws Exception {
        return null;
    }

    /**
     * <code>parsingSingleFileValue</code>
     * <p>The parsing single file value method.</p>
     * @param parsingType {@link java.lang.Class} <p>The parsing type parameter is <code>Class</code> type.</p>
     * @param parsing {@link java.lang.Object} <p>The parsing parameter is <code>Object</code> type.</p>
     * @param fileName {@link java.lang.String} <p>The file name parameter is <code>String</code> type.</p>
     * @param file {@link org.springframework.web.multipart.MultipartFile} <p>The file parameter is <code>MultipartFile</code> type.</p>
     * @see  java.lang.Class
     * @see  java.lang.Object
     * @see  java.lang.String
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  java.lang.Exception
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    private void parsingSingleFileValue(Class<?> parsingType, Object parsing, String fileName, MultipartFile file) throws Exception {
        Field declaredField;
        try {
            declaredField = parsingType.getDeclaredField(fileName);
        } catch (NoSuchFieldException ignored) {
            return;
        }
        parsingSingleFileValue(fileName, declaredField, parsing, file);
    }

    /**
     * <code>parsingMultiFileValue</code>
     * <p>The parsing multi file value method.</p>
     * @param parsingType {@link java.lang.Class} <p>The parsing type parameter is <code>Class</code> type.</p>
     * @param parsing {@link java.lang.Object} <p>The parsing parameter is <code>Object</code> type.</p>
     * @param fileName {@link java.lang.String} <p>The file name parameter is <code>String</code> type.</p>
     * @param files {@link java.util.List} <p>The files parameter is <code>List</code> type.</p>
     * @see  java.lang.Class
     * @see  java.lang.Object
     * @see  java.lang.String
     * @see  java.util.List
     * @see  java.lang.Exception
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    private void parsingMultiFileValue(Class<?> parsingType, Object parsing, String fileName, List<MultipartFile> files) throws Exception {
        Field declaredField;
        try {
            declaredField = parsingType.getDeclaredField(fileName);
        } catch (NoSuchFieldException ignored) {
            return;
        }
        Type genericType = declaredField.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            parsingParameterizedTypeValue(parameterizedType, fileName, declaredField, parsing, files);

        } else if (genericType instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) genericType;
            parsingGenericArrayTypeValue(genericArrayType, fileName, declaredField, parsing, files);
        } else {
            MultipartFile multipartFile = files.get(0);
            if (GeneralUtils.isNotEmpty(multipartFile)) {
                parsingSingleFileValue(fileName, declaredField, parsing, multipartFile);
            }
        }
    }

    /**
     * <code>parsingParameterizedTypeValue</code>
     * <p>The parsing parameterized type value method.</p>
     * @param parameterizedType {@link java.lang.reflect.ParameterizedType} <p>The parameterized type parameter is <code>ParameterizedType</code> type.</p>
     * @param fileName {@link java.lang.String} <p>The file name parameter is <code>String</code> type.</p>
     * @param declaredField {@link java.lang.reflect.Field} <p>The declared field parameter is <code>Field</code> type.</p>
     * @param parsing {@link java.lang.Object} <p>The parsing parameter is <code>Object</code> type.</p>
     * @param files {@link java.util.List} <p>The files parameter is <code>List</code> type.</p>
     * @see  java.lang.reflect.ParameterizedType
     * @see  java.lang.String
     * @see  java.lang.reflect.Field
     * @see  java.lang.Object
     * @see  java.util.List
     * @see  java.lang.Exception
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    private void parsingParameterizedTypeValue(ParameterizedType parameterizedType, String fileName, Field declaredField, Object parsing, List<MultipartFile> files) throws Exception {
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
                Class<?> fieldType = ((Class<?>) actualRawType);
                parsingListFileValue(fileName, fieldType, declaredField, parsing, files);
            }
        }
    }

    /**
     * <code>parsingGenericArrayTypeValue</code>
     * <p>The parsing generic array type value method.</p>
     * @param genericArrayType {@link java.lang.reflect.GenericArrayType} <p>The generic array type parameter is <code>GenericArrayType</code> type.</p>
     * @param fileName {@link java.lang.String} <p>The file name parameter is <code>String</code> type.</p>
     * @param declaredField {@link java.lang.reflect.Field} <p>The declared field parameter is <code>Field</code> type.</p>
     * @param parsing {@link java.lang.Object} <p>The parsing parameter is <code>Object</code> type.</p>
     * @param files {@link java.util.List} <p>The files parameter is <code>List</code> type.</p>
     * @see  java.lang.reflect.GenericArrayType
     * @see  java.lang.String
     * @see  java.lang.reflect.Field
     * @see  java.lang.Object
     * @see  java.util.List
     * @see  java.lang.Exception
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    private void parsingGenericArrayTypeValue(GenericArrayType genericArrayType, String fileName, Field declaredField, Object parsing, List<MultipartFile> files) throws Exception {
        Type genericComponentType = genericArrayType.getGenericComponentType();
        if (genericComponentType instanceof ParameterizedType) {
            ParameterizedType arrayParameterizedType = (ParameterizedType) genericComponentType;
            Type arrayRawType = arrayParameterizedType.getRawType();
            if (!(arrayRawType instanceof Class)) {
                return;
            }
            Class<?> fieldType = ((Class<?>) arrayRawType);
            MultipartFile[] arrayFile = files.toArray(new MultipartFile[0]);
            parsingArrayFileValue(fileName, fieldType, declaredField, parsing, arrayFile);

        }
    }

    /**
     * <code>parsingSingleFileValue</code>
     * <p>The parsing single file value method.</p>
     * @param fileName {@link java.lang.String} <p>The file name parameter is <code>String</code> type.</p>
     * @param declaredField {@link java.lang.reflect.Field} <p>The declared field parameter is <code>Field</code> type.</p>
     * @param parsing {@link java.lang.Object} <p>The parsing parameter is <code>Object</code> type.</p>
     * @param file {@link org.springframework.web.multipart.MultipartFile} <p>The file parameter is <code>MultipartFile</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.reflect.Field
     * @see  java.lang.Object
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  java.lang.Exception
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    private void parsingSingleFileValue(String fileName, Field declaredField, Object parsing, MultipartFile file) throws Exception {
        Class<?> fieldType = declaredField.getType();
        JsonParsingMultipartFile parsingMultipartFile = AnnotationUtils.getAnnotation(declaredField,JsonParsingMultipartFile.class);
        if (GeneralUtils.isNotEmpty(parsingMultipartFile)) {
            Object resolveFile = resolveSingleFile(fileName, fieldType, file);
            if (GeneralUtils.isNotEmpty(resolveFile)) {
                declaredField.setAccessible(true);
                declaredField.set(parsing, resolveFile);
                return;
            }
        }
        if (fieldType == MultipartFile.class) {
            declaredField.setAccessible(true);
            declaredField.set(parsing, file);
        }
    }

    /**
     * <code>parsingListFileValue</code>
     * <p>The parsing list file value method.</p>
     * @param fileName {@link java.lang.String} <p>The file name parameter is <code>String</code> type.</p>
     * @param fieldType {@link java.lang.Class} <p>The field type parameter is <code>Class</code> type.</p>
     * @param declaredField {@link java.lang.reflect.Field} <p>The declared field parameter is <code>Field</code> type.</p>
     * @param parsing {@link java.lang.Object} <p>The parsing parameter is <code>Object</code> type.</p>
     * @param files {@link java.util.List} <p>The files parameter is <code>List</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  java.lang.reflect.Field
     * @see  java.lang.Object
     * @see  java.util.List
     * @see  java.lang.Exception
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    private void parsingListFileValue(String fileName, Class<?> fieldType, Field declaredField, Object parsing, List<MultipartFile> files) throws Exception {
        JsonParsingMultipartFile parsingMultipartFile = AnnotationUtils.getAnnotation(declaredField,JsonParsingMultipartFile.class);
        if (GeneralUtils.isNotEmpty(parsingMultipartFile)) {
            Object resolveFile = resolveListFile(fileName, fieldType, files);
            if (GeneralUtils.isNotEmpty(resolveFile)) {
                declaredField.setAccessible(true);
                declaredField.set(parsing, resolveFile);
                return;
            }
        }
        if (fieldType == MultipartFile.class) {
            declaredField.setAccessible(true);
            declaredField.set(parsing, files);
        }
    }

    /**
     * <code>parsingArrayFileValue</code>
     * <p>The parsing array file value method.</p>
     * @param fileName {@link java.lang.String} <p>The file name parameter is <code>String</code> type.</p>
     * @param fieldType {@link java.lang.Class} <p>The field type parameter is <code>Class</code> type.</p>
     * @param declaredField {@link java.lang.reflect.Field} <p>The declared field parameter is <code>Field</code> type.</p>
     * @param parsing {@link java.lang.Object} <p>The parsing parameter is <code>Object</code> type.</p>
     * @param files {@link org.springframework.web.multipart.MultipartFile} <p>The files parameter is <code>MultipartFile</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  java.lang.reflect.Field
     * @see  java.lang.Object
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  java.lang.Exception
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    private void parsingArrayFileValue(String fileName, Class<?> fieldType, Field declaredField, Object parsing, MultipartFile[] files) throws Exception {
        JsonParsingMultipartFile parsingMultipartFile = AnnotationUtils.getAnnotation(declaredField,JsonParsingMultipartFile.class);
        if (GeneralUtils.isNotEmpty(parsingMultipartFile)) {
            Object resolveFile = resolveArrayFile(fileName, fieldType, files);
            if (GeneralUtils.isNotEmpty(resolveFile)) {
                declaredField.setAccessible(true);
                declaredField.set(parsing, resolveFile);
                return;
            }
        }
        if (fieldType == MultipartFile.class) {
            declaredField.setAccessible(true);
            declaredField.set(parsing, files);
        }
    }

    /**
     * <code>parsingNestedFields</code>
     * <p>The parsing nested fields method.</p>
     * @param parentField {@link java.lang.reflect.Field} <p>The parent field parameter is <code>Field</code> type.</p>
     * @param parent {@link io.github.nichetoolkit.rest.parsing.RestParsingFieldPack} <p>The parent parameter is <code>RestParsingFieldPack</code> type.</p>
     * @param parsingFields {@link java.util.List} <p>The parsing fields parameter is <code>List</code> type.</p>
     * @param parsingType {@link java.lang.Class} <p>The parsing type parameter is <code>Class</code> type.</p>
     * @param prefix {@link java.lang.String} <p>The prefix parameter is <code>String</code> type.</p>
     * @see  java.lang.reflect.Field
     * @see  io.github.nichetoolkit.rest.parsing.RestParsingFieldPack
     * @see  java.util.List
     * @see  java.lang.Class
     * @see  java.lang.String
     */
    private void parsingNestedFields(Field parentField, RestParsingFieldPack parent, List<RestParsingFieldPack> parsingFields, Class<?> parsingType, String prefix) {
        List<String> ignoredFields = new ArrayList<>();
        List<String> multiFields = new ArrayList<>();
        JsonParsingNestedIgnoredFields nestIgnoredFields = AnnotationUtils.getAnnotation(parentField, JsonParsingNestedIgnoredFields.class);
        if (GeneralUtils.isNotEmpty(nestIgnoredFields) && GeneralUtils.isNotEmpty(nestIgnoredFields.fields())) {
            ignoredFields.addAll(Arrays.asList(nestIgnoredFields.fields()));
        }
        JsonParsingNestedMultiFields nestMultiFields = AnnotationUtils.getAnnotation(parentField,JsonParsingNestedMultiFields.class);
        if (GeneralUtils.isNotEmpty(nestMultiFields) && GeneralUtils.isNotEmpty(nestMultiFields.fields())) {
            multiFields.addAll(Arrays.asList(nestMultiFields.fields()));
        }

        parsingIgnoredFields(ignoredFields, multiFields, parsingType);
        JsonParsingUnderline jsonParsingUnderline = AnnotationUtils.getAnnotation(parsingType,JsonParsingUnderline.class);
        boolean parsingUnderline = GeneralUtils.isNotEmpty(jsonParsingUnderline);
        parsingFields(parsingFields, parsingType, ignoredFields, multiFields, parsingUnderline, parent, prefix);
    }

    /**
     * <code>parsingFields</code>
     * <p>The parsing fields method.</p>
     * @param parsingFields {@link java.util.List} <p>The parsing fields parameter is <code>List</code> type.</p>
     * @param parsingType {@link java.lang.Class} <p>The parsing type parameter is <code>Class</code> type.</p>
     * @param ignoredFields {@link java.util.List} <p>The ignored fields parameter is <code>List</code> type.</p>
     * @param multiFields {@link java.util.List} <p>The multi fields parameter is <code>List</code> type.</p>
     * @param parsingUnderline boolean <p>The parsing underline parameter is <code>boolean</code> type.</p>
     * @param parent {@link io.github.nichetoolkit.rest.parsing.RestParsingFieldPack} <p>The parent parameter is <code>RestParsingFieldPack</code> type.</p>
     * @param prefix {@link java.lang.String} <p>The prefix parameter is <code>String</code> type.</p>
     * @see  java.util.List
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.parsing.RestParsingFieldPack
     * @see  java.lang.String
     */
    private void parsingFields(List<RestParsingFieldPack> parsingFields, Class<?> parsingType, List<String> ignoredFields, List<String> multiFields, boolean parsingUnderline, RestParsingFieldPack parent, String prefix) {
        Field[] declaredFields = parsingType.getDeclaredFields();
        if (GeneralUtils.isNotEmpty(declaredFields)) {
            for (Field field : declaredFields) {
                String fieldName = field.getName();
                RestParsingFieldPack.RestParsingFieldPackBuilder fieldBuilder = RestParsingFieldPack.builder()
                        .declaringType(field.getDeclaringClass()).name(fieldName).field(field);

                if (parsingUnderline || GeneralUtils.isNotEmpty(AnnotationUtils.getAnnotation(field,JsonParsingUnderline.class))) {
                    fieldName = GeneralUtils.camelToLine(fieldName);
                    fieldBuilder.name(fieldName);
                    fieldBuilder.underline(true);
                }
                JsonParsingField jsonParsingField = AnnotationUtils.getAnnotation(field,JsonParsingField.class);
                if (GeneralUtils.isNotEmpty(jsonParsingField) && GeneralUtils.isNotEmpty(jsonParsingField.name())) {
                    fieldName = jsonParsingField.name();
                    fieldBuilder.name(jsonParsingField.name());
                }
                if (GeneralUtils.isNotEmpty(parent)) {
                    fieldBuilder.parent(parent).nestedField(true);
                    String nestedPrefix = RestOptional.ofEmptyable(prefix).orEmpty(parent.getName());
                    fieldBuilder.nestedName(nestedPrefix + "." + fieldName);
                }
                if (ignoredFields.contains(fieldName) || ignoredFields.contains(field.getName()) || GeneralUtils.isNotEmpty(field.getAnnotation(JsonParsingIgnored.class))) {
                    fieldBuilder.ignored(true);
                }
                if (multiFields.contains(fieldName) || multiFields.contains(field.getName()) || GeneralUtils.isNotEmpty(field.getAnnotation(JsonParsingMultiField.class))) {
                    fieldBuilder.multiple(true);
                }
                JsonParsingNestedField jsonParsingNestField = AnnotationUtils.getAnnotation(field,JsonParsingNestedField.class);
                RestParsingFieldPack parsingField = null;
                if (GeneralUtils.isNotEmpty(jsonParsingNestField)) {
                    fieldBuilder.nested(true);
                    parsingField = fieldBuilder.build();
                    Class<?> nestType = jsonParsingNestField.type() != null ? jsonParsingNestField.type() : RestGenericTypes.resolveFieldType(field);
                    prefix = GeneralUtils.isNotEmpty(jsonParsingNestField.prefix()) ? jsonParsingNestField.prefix() : fieldName;
                    parsingNestedFields(field, parsingField, parsingFields, nestType, prefix);
                }
                parsingFields.add(Optional.ofNullable(parsingField).orElse(fieldBuilder.build()));
            }
        }
        ignoredFields.clear();
        multiFields.clear();
        Class<?> superclass = parsingType.getSuperclass();
        if (GeneralUtils.isNotEmpty(superclass)) {
            parsingIgnoredFields(ignoredFields, multiFields, superclass);
            JsonParsingUnderline jsonParsingUnderline = AnnotationUtils.getAnnotation(superclass,JsonParsingUnderline.class);
            parsingUnderline = GeneralUtils.isNotEmpty(jsonParsingUnderline);
            parsingFields(parsingFields, superclass, ignoredFields, multiFields, parsingUnderline, null, null);
        }
    }

    /**
     * <code>parsingIgnoredFields</code>
     * <p>The parsing ignored fields method.</p>
     * @param ignoredFields {@link java.util.List} <p>The ignored fields parameter is <code>List</code> type.</p>
     * @param multiFields {@link java.util.List} <p>The multi fields parameter is <code>List</code> type.</p>
     * @param parsingType {@link java.lang.Class} <p>The parsing type parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.lang.Class
     */
    private void parsingIgnoredFields(List<String> ignoredFields, List<String> multiFields, Class<?> parsingType) {
        RestParsingProperties.ParsingIgnored ignored = parsingProperties.getIgnored();
        List<String> fields = ignored.getFields();
        if (GeneralUtils.isNotEmpty(fields)) {
            ignoredFields.addAll(fields);
        }
        JsonParsingIgnoredFields parsingIgnoredFields = AnnotationUtils.getAnnotation(parsingType,JsonParsingIgnoredFields.class);
        if (GeneralUtils.isNotEmpty(parsingIgnoredFields) && GeneralUtils.isNotEmpty(parsingIgnoredFields.fields())) {
            ignoredFields.addAll(Arrays.asList(parsingIgnoredFields.fields()));
        }
        JsonParsingMultiFields parsingMultiFields = AnnotationUtils.getAnnotation(parsingType,JsonParsingMultiFields.class);
        if (GeneralUtils.isNotEmpty(parsingMultiFields) && GeneralUtils.isNotEmpty(parsingMultiFields.fields())) {
            multiFields.addAll(Arrays.asList(parsingMultiFields.fields()));
        }
    }

    /**
     * <code>parsingFields</code>
     * <p>The parsing fields method.</p>
     * @param parsingType {@link java.lang.Class} <p>The parsing type parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The parsing fields return object is <code>List</code> type.</p>
     */
    private List<RestParsingFieldPack> parsingFields(Class<?> parsingType) {
        List<RestParsingFieldPack> parsingFields = new ArrayList<>();
        List<String> ignoredFields = new ArrayList<>();
        List<String> multiFields = new ArrayList<>();
        parsingIgnoredFields(ignoredFields, multiFields, parsingType);
        JsonParsingUnderline jsonParsingUnderline = AnnotationUtils.getAnnotation(parsingType,JsonParsingUnderline.class);
        boolean parsingUnderline = GeneralUtils.isNotEmpty(jsonParsingUnderline);
        parsingFields(parsingFields, parsingType, ignoredFields, multiFields, parsingUnderline, null, null);
        return parsingFields;
    }

    /**
     * <code>parsingParameterMap</code>
     * <p>The parsing parameter map method.</p>
     * @param parsingFields {@link java.util.List} <p>The parsing fields parameter is <code>List</code> type.</p>
     * @param parameterMap {@link java.util.Map} <p>The parameter map parameter is <code>Map</code> type.</p>
     * @see  java.util.List
     * @see  java.util.Map
     * @return  {@link java.util.Map} <p>The parsing parameter map return object is <code>Map</code> type.</p>
     */
    private Map<String, Object> parsingParameterMap(List<RestParsingFieldPack> parsingFields, Map<String, String[]> parameterMap) {
        List<RestParsingFieldPack> nestedFieldList = new ArrayList<>();
        List<RestParsingFieldPack> parsingFieldList = new ArrayList<>();
        parsingFields.forEach(parsingField -> {
            if (parsingField.isNestedField()) {
                nestedFieldList.add(parsingField);
            } else {
                parsingFieldList.add(parsingField);
            }
        });
        Map<String, RestParsingFieldPack> nestedFieldMap = nestedFieldList.stream().collect(Collectors.toMap(RestParsingFieldPack::getNestedName, Function.identity(), (oldValue, newValue) -> newValue));
        Map<String, RestParsingFieldPack> parsingFieldMap = parsingFieldList.stream().collect(Collectors.toMap(RestParsingFieldPack::getName, Function.identity(), (oldValue, newValue) -> newValue));
        Map<String, Object> parameters = new LinkedHashMap<>(parameterMap.size());
        List<String> ignoredValues = parsingProperties.getIgnored().getValues();
        parameterMap.forEach((key, value) -> {
            boolean isNestedKey = key.contains(".");
            RestParsingFieldPack fieldPack;
            if (isNestedKey) {
                fieldPack = nestedFieldMap.get(key);
            } else {
                fieldPack = parsingFieldMap.get(key);
            }
            if (GeneralUtils.isNotEmpty(fieldPack)) {
                if (fieldPack.isMultiple() && GeneralUtils.isNotEmpty(value)) {
                    List<String> values = new ArrayList<>();
                    Arrays.stream(value).forEach(fieldValue -> {
                        if (fieldValue.contains(",")) {
                            String[] split = fieldValue.split(",");
                            List<String> splitValues = Arrays.stream(split).filter(splitValue -> !ignoredValues.contains(splitValue)).collect(Collectors.toList());
                            values.addAll(splitValues);
                        } else if (!ignoredValues.contains(fieldValue)) {
                            values.add(fieldValue);
                        }
                    });
                    parameters.put(key, values);
                } else if (GeneralUtils.isNotEmpty(value) && !ignoredValues.contains(value[0])) {
                    parameters.put(key, value[0]);
                }
            }

        });
        return parameters;
    }



}
