package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.holder.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;

import java.util.List;

/**
 * <code>ContextUtils</code>
 * <p>The type context utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class ContextUtils {

    /**
     * <code>getBean</code>
     * <p>the bean method.</p>
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Object} <p>the bean return object is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static Object getBean(String name){
        try {
            return ApplicationContextHolder.getBean(name);
        } catch (BeansException ex) {
            log.warn("bean of name is {} no found, error: {}",name,ex.getMessage());
            return null;
        }
    }

    /**
     * <code>getBean</code>
     * <p>the bean method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the bean return object is <code>T</code> type.</p>
     * @see java.lang.Class
     */
    public static <T> T getBean(Class<T> clazz){
        try {
            return ApplicationContextHolder.getBean(clazz);
        } catch (BeansException ex) {
            log.warn("bean of type is {} no found, error: {}",clazz.getName(),ex.getMessage());
            return null;
        }
    }

    /**
     * <code>getBean</code>
     * <p>the bean method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param name  {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the bean return object is <code>T</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <T> T getBean(String name, Class<T> clazz){
        try {
            return ApplicationContextHolder.getBean(name, clazz);
        } catch (BeansException ex) {
            log.warn("bean of type is {} and name is {} no found, error: {}",clazz.getName(),name,ex.getMessage());
            return null;
        }
    }

    /**
     * <code>getBeans</code>
     * <p>the beans method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the beans return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T> List<T> getBeans(Class<T> clazz){
        try {
            return ApplicationContextHolder.getBeans(clazz);
        } catch (BeansException ex) {
            log.warn("beans of type is {} no found, error: {}",clazz.getName(),ex.getMessage());
            return null;
        }
    }

}
