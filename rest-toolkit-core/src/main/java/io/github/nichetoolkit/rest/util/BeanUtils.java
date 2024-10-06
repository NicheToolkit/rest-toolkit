package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.holder.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * <code>BeanUtils</code>
 * <p>The type bean utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class BeanUtils {

    /**
     * <code>beanOfName</code>
     * <p>The of name method.</p>
     * @param beanName {@link java.lang.String} <p>The bean name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Object} <p>The of name return object is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static Object beanOfName(String beanName) {
        try {
            return ApplicationContextHolder.beanOfName(beanName);
        } catch (BeansException exception) {
            log.warn("bean for named {} is no found, error: {}", beanName, exception.getMessage());
            return null;
        }
    }

    /**
     * <code>beanOfType</code>
     * <p>The of type method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param beanType {@link java.lang.Class} <p>The bean type parameter is <code>Class</code> type.</p>
     * @return T <p>The of type return object is <code>T</code> type.</p>
     * @see java.lang.Class
     */
    public static <T> T beanOfType(Class<T> beanType) {
        try {
            return ApplicationContextHolder.beanOfType(beanType);
        } catch (BeansException exception) {
            log.warn("bean of {} is no found, error: {}", beanType.getName(), exception.getMessage());
            return null;
        }
    }

    /**
     * <code>beanOfType</code>
     * <p>The of type method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param beanName {@link java.lang.String} <p>The bean name parameter is <code>String</code> type.</p>
     * @param beanType {@link java.lang.Class} <p>The bean type parameter is <code>Class</code> type.</p>
     * @return T <p>The of type return object is <code>T</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <T> T beanOfType(String beanName, Class<T> beanType) {
        try {
            return ApplicationContextHolder.beanOfType(beanName, beanType);
        } catch (BeansException exception) {
            log.warn("bean of {} for named {} is no found, error: {}", beanType.getName(), beanName, exception.getMessage());
            return null;
        }
    }

    /**
     * <code>beansOfType</code>
     * <p>The of type method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param beanType {@link java.lang.Class} <p>The bean type parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The of type return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T> List<T> beansOfType(Class<T> beanType) {
        try {
            return ApplicationContextHolder.beansOfType(beanType);
        } catch (BeansException ex) {
            log.warn("beans of {} is no found, error: {}", beanType.getName(), ex.getMessage());
            return null;
        }
    }

    /**
     * <code>beanMapOfType</code>
     * <p>The map of type method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param beanType {@link java.lang.Class} <p>The bean type parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The map of type return object is <code>Map</code> type.</p>
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <T> Map<String, T> beanMapOfType(Class<T> beanType) {
        try {
            return ApplicationContextHolder.beanMapOfType(beanType);
        } catch (BeansException ex) {
            log.warn("bean map of {} is no found, error: {}", beanType.getName(), ex.getMessage());
            return null;
        }
    }

    /**
     * <code>beansOfAnnotation</code>
     * <p>The of annotation method.</p>
     * @param annotationType {@link java.lang.Class} <p>The annotation type parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The of annotation return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    public static List<Object> beansOfAnnotation(Class<? extends Annotation> annotationType) {
        try {
            return ApplicationContextHolder.beansOfAnnotation(annotationType);
        } catch (BeansException ex) {
            log.warn("beans with {} type is no found, error: {}", annotationType.getName(), ex.getMessage());
            return null;
        }
    }

    /**
     * <code>beanMapOfAnnotation</code>
     * <p>The map of annotation method.</p>
     * @param annotationType {@link java.lang.Class} <p>The annotation type parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The map of annotation return object is <code>Map</code> type.</p>
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static Map<String, Object> beanMapOfAnnotation(Class<? extends Annotation> annotationType) {
        try {
            return ApplicationContextHolder.beanMapOfAnnotation(annotationType);
        } catch (BeansException ex) {
            log.warn("bean map with {} type is no found, error: {}", annotationType.getName(), ex.getMessage());
            return null;
        }
    }

    /**
     * <code>copyNullProperties</code>
     * <p>The null properties method.</p>
     * @param <S>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param source S <p>The source parameter is <code>S</code> type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @return T <p>The null properties return object is <code>T</code> type.</p>
     */
    public static <S,T> T copyNullProperties(S source, T target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * <code>ignoreProperties</code>
     * <p>The properties method.</p>
     * @param source           {@link java.lang.Object} <p>The source parameter is <code>Object</code> type.</p>
     * @param ignoreProperties {@link java.lang.String} <p>The ignore properties parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The properties return object is <code>String</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.String
     */
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
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] ignorePropertiesNames = new String[emptyNames.size()];
        emptyNames.toArray(ignorePropertiesNames);
        return ignorePropertiesNames;
    }

    /**
     * <code>copyNonnullProperties</code>
     * <p>The nonnull properties method.</p>
     * @param <S>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param source S <p>The source parameter is <code>S</code> type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @return T <p>The nonnull properties return object is <code>T</code> type.</p>
     */
    public static <S,T> T copyNonnullProperties(S source, T target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target,ignoreProperties(source));
        return target;
    }

    /**
     * <code>copyNonnullProperties</code>
     * <p>The nonnull properties method.</p>
     * @param <S>              {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>              {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param source           S <p>The source parameter is <code>S</code> type.</p>
     * @param target           T <p>The target parameter is <code>T</code> type.</p>
     * @param ignoreProperties {@link java.lang.String} <p>The ignore properties parameter is <code>String</code> type.</p>
     * @return T <p>The nonnull properties return object is <code>T</code> type.</p>
     * @see java.lang.String
     */
    public static <S,T> T copyNonnullProperties(S source, T target, String... ignoreProperties) {
        org.springframework.beans.BeanUtils.copyProperties(source, target,ignoreProperties(source, ignoreProperties));
        return target;
    }

    /**
     * <code>copyProperties</code>
     * <p>The properties method.</p>
     * @param <S>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>      B <p>The generic parameter is <code>B</code> type.</p>
     * @param <B>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param source   S <p>The source parameter is <code>S</code> type.</p>
     * @param target   T <p>The target parameter is <code>T</code> type.</p>
     * @param editable {@link java.lang.Class} <p>The editable parameter is <code>Class</code> type.</p>
     * @return T <p>The properties return object is <code>T</code> type.</p>
     * @see java.lang.Class
     */
    public static <S,T extends B,B> T copyProperties(S source, T target, Class<B> editable) {
        org.springframework.beans.BeanUtils.copyProperties(source, target, editable);
        return target;
    }

    /**
     * <code>copyProperties</code>
     * <p>The properties method.</p>
     * @param <S>              {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>              {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param source           S <p>The source parameter is <code>S</code> type.</p>
     * @param target           T <p>The target parameter is <code>T</code> type.</p>
     * @param ignoreProperties {@link java.lang.String} <p>The ignore properties parameter is <code>String</code> type.</p>
     * @return T <p>The properties return object is <code>T</code> type.</p>
     * @see java.lang.String
     */
    public static <S,T> T copyProperties(S source, T target, String... ignoreProperties) {
        org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties);
        return target;
    }

}
