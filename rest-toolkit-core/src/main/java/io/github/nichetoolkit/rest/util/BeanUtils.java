package io.github.nichetoolkit.rest.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <code>BeanUtils</code>
 * <p>The type bean utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class BeanUtils {

    /**
     * <code>copyNullProperties</code>
     * <p>the null properties method.</p>
     * @param <S>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param source S <p>the source parameter is <code>S</code> type.</p>
     * @param target T <p>the target parameter is <code>T</code> type.</p>
     * @return T <p>the null properties return object is <code>T</code> type.</p>
     */
    public static <S,T> T copyNullProperties(S source, T target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * <code>ignoreProperties</code>
     * <p>the properties method.</p>
     * @param source           {@link java.lang.Object} <p>the source parameter is <code>Object</code> type.</p>
     * @param ignoreProperties {@link java.lang.String} <p>the ignore properties parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the properties return object is <code>String</code> type.</p>
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
     * <p>the nonnull properties method.</p>
     * @param <S>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param source S <p>the source parameter is <code>S</code> type.</p>
     * @param target T <p>the target parameter is <code>T</code> type.</p>
     * @return T <p>the nonull properties return object is <code>T</code> type.</p>
     */
    public static <S,T> T copyNonnullProperties(S source, T target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target,ignoreProperties(source));
        return target;
    }

    /**
     * <code>copyNonnullProperties</code>
     * <p>the nonnull properties method.</p>
     * @param <S>              {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>              {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param source           S <p>the source parameter is <code>S</code> type.</p>
     * @param target           T <p>the target parameter is <code>T</code> type.</p>
     * @param ignoreProperties {@link java.lang.String} <p>the ignore properties parameter is <code>String</code> type.</p>
     * @return T <p>the nonnull properties return object is <code>T</code> type.</p>
     * @see java.lang.String
     */
    public static <S,T> T copyNonnullProperties(S source, T target, String... ignoreProperties) {
        org.springframework.beans.BeanUtils.copyProperties(source, target,ignoreProperties(source, ignoreProperties));
        return target;
    }

    /**
     * <code>copyProperties</code>
     * <p>the properties method.</p>
     * @param <S>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>      B <p>the generic parameter is <code>B</code> type.</p>
     * @param <B>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param source   S <p>the source parameter is <code>S</code> type.</p>
     * @param target   T <p>the target parameter is <code>T</code> type.</p>
     * @param editable {@link java.lang.Class} <p>the editable parameter is <code>Class</code> type.</p>
     * @return T <p>the properties return object is <code>T</code> type.</p>
     * @see java.lang.Class
     */
    public static <S,T extends B,B> T copyProperties(S source, T target, Class<B> editable) {
        org.springframework.beans.BeanUtils.copyProperties(source, target, editable);
        return target;
    }

    /**
     * <code>copyProperties</code>
     * <p>the properties method.</p>
     * @param <S>              {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>              {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param source           S <p>the source parameter is <code>S</code> type.</p>
     * @param target           T <p>the target parameter is <code>T</code> type.</p>
     * @param ignoreProperties {@link java.lang.String} <p>the ignore properties parameter is <code>String</code> type.</p>
     * @return T <p>the properties return object is <code>T</code> type.</p>
     * @see java.lang.String
     */
    public static <S,T> T copyProperties(S source, T target, String... ignoreProperties) {
        org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties);
        return target;
    }
}
