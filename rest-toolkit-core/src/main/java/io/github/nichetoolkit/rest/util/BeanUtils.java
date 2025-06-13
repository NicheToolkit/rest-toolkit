package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.holder.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
public class BeanUtils {

    public static Object beanOfName(String beanName) {
        try {
            return ApplicationContextHolder.beanOfName(beanName);
        } catch (BeansException exception) {
            log.warn("The bean for named '{}' is no found, {}", beanName, exception.getMessage());
            return null;
        }
    }

    public static Object nameOfBean(String beanName) {
        try {
            return ApplicationContextHolder.beanOfName(beanName);
        } catch (BeansException exception) {
            return null;
        }
    }

    public static <T> T beanOfType(Class<T> beanType) {
        try {
            return ApplicationContextHolder.beanOfType(beanType);
        } catch (BeansException exception) {
            log.warn("The bean of [{}] type is no found, {}", beanType.getName(), exception.getMessage());
            return null;
        }
    }

    public static <T> T typeOfBean(Class<T> beanType) {
        try {
            return ApplicationContextHolder.beanOfType(beanType);
        } catch (BeansException exception) {
            return null;
        }
    }

    public static <T> T beanOfType(String beanName, Class<T> beanType) {
        try {
            return ApplicationContextHolder.beanOfType(beanName, beanType);
        } catch (BeansException exception) {
            log.warn("The bean of [{}] type for named '{}' is no found, {}", beanType.getName(), beanName, exception.getMessage());
            return null;
        }
    }

    public static <T> T typeOfBean(String beanName, Class<T> beanType) {
        try {
            return ApplicationContextHolder.beanOfType(beanName, beanType);
        } catch (BeansException exception) {
            return null;
        }
    }

    public static <T> List<T> beansOfType(Class<T> beanType) {
        try {
            return ApplicationContextHolder.beansOfType(beanType);
        } catch (BeansException exception) {
            log.warn("The beans of [{}] type is no found, {}", beanType.getName(), exception.getMessage());
            return null;
        }
    }

    public static <T> List<T> typeOfBeans(Class<T> beanType) {
        try {
            return ApplicationContextHolder.beansOfType(beanType);
        } catch (BeansException exception) {
            return null;
        }
    }

    public static <T> Map<String, T> beanMapOfType(Class<T> beanType) {
        try {
            return ApplicationContextHolder.beanMapOfType(beanType);
        } catch (BeansException exception) {
            log.warn("The bean map of [{}] type is no found, {}", beanType.getName(), exception.getMessage());
            return null;
        }
    }

    public static <T> Map<String, T> typeOfBeanMap(Class<T> beanType) {
        try {
            return ApplicationContextHolder.beanMapOfType(beanType);
        } catch (BeansException exception) {
            return null;
        }
    }

    public static List<Object> beansOfAnnotation(Class<? extends Annotation> annotationType) {
        try {
            return ApplicationContextHolder.beansOfAnnotation(annotationType);
        } catch (BeansException exception) {
            log.warn("The beans with [{}] type is no found, {}", annotationType.getName(), exception.getMessage());
            return null;
        }
    }


    public static List<Object> annotationOfBean(Class<? extends Annotation> annotationType) {
        try {
            return ApplicationContextHolder.beansOfAnnotation(annotationType);
        } catch (BeansException exception) {
            return null;
        }
    }

    public static Map<String, Object> beanMapOfAnnotation(Class<? extends Annotation> annotationType) {
        try {
            return ApplicationContextHolder.beanMapOfAnnotation(annotationType);
        } catch (BeansException exception) {
            log.warn("The bean map with [{}] type is no found, {}", annotationType.getName(), exception.getMessage());
            return null;
        }
    }

    public static Map<String, Object> annotationOfBeanMap(Class<? extends Annotation> annotationType) {
        try {
            return ApplicationContextHolder.beanMapOfAnnotation(annotationType);
        } catch (BeansException exception) {
            return null;
        }
    }

    public static <S,T> T copyNullProperties(S source, T target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
        return target;
    }

    public static String[] ignoreProperties(Object source, String... ignoreProperties) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames;
        if (GeneralUtils.isNotEmpty(ignoreProperties)) {
            emptyNames = new HashSet<>(Arrays.asList(ignoreProperties));
        } else {
            emptyNames = new HashSet<>();
        }
        for (PropertyDescriptor pd : pds) {
            Method readMethod = pd.getReadMethod();
            Method writeMethod = pd.getWriteMethod();
            if (GeneralUtils.isNotEmpty(readMethod) && GeneralUtils.isNotEmpty(writeMethod)) {
                Object srcValue = src.getPropertyValue(pd.getName());
                if (srcValue == null) {
                    emptyNames.add(pd.getName());
                }
            } else {
                emptyNames.add(pd.getName());
            }
        }
        String[] ignorePropertiesNames = new String[emptyNames.size()];
        emptyNames.toArray(ignorePropertiesNames);
        return ignorePropertiesNames;
    }

    public static <S,T> T copyNonnullProperties(S source, T target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target,ignoreProperties(source));
        return target;
    }

    public static <S,T> T copyNonnullProperties(S source, T target, String... ignoreProperties) {
        org.springframework.beans.BeanUtils.copyProperties(source, target,ignoreProperties(source, ignoreProperties));
        return target;
    }

    public static <S,T extends B,B> T copyProperties(S source, T target, Class<B> editable) {
        org.springframework.beans.BeanUtils.copyProperties(source, target, editable);
        return target;
    }

    public static <S,T> T copyProperties(S source, T target, String... ignoreProperties) {
        org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties);
        return target;
    }

}
