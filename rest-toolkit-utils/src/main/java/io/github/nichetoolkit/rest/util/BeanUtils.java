package io.github.nichetoolkit.rest.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>BeanUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class BeanUtils {

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
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] ignorePropertiesNames = new String[emptyNames.size()];
        emptyNames.toArray(ignorePropertiesNames);
        return ignorePropertiesNames;
    }

    public static <S,T> T copyNonullProperties(S source, T target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target,ignoreProperties(source));
        return target;
    }

    public static <S,T> T copyNonullProperties(S source, T target, String... ignoreProperties) {
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
