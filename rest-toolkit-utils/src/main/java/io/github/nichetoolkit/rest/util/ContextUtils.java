package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.holder.ContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;

import java.util.List;

/**
 * <p>ContextUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class ContextUtils {

    public static Object getBean(String name){
        try {
            return ContextHolder.getBean(name);
        } catch (BeansException ex) {
            log.warn("bean of name is {} no found, error: {}",name,ex.getMessage());
            return null;
        }
    }

    public static <T> T getBean(Class<T> clazz){
        try {
            return ContextHolder.getBean(clazz);
        } catch (BeansException ex) {
            log.warn("bean of type is {} no found, error: {}",clazz.getName(),ex.getMessage());
            return null;
        }
    }

    public static <T> T getBean(String name, Class<T> clazz){
        try {
            return ContextHolder.getBean(name, clazz);
        } catch (BeansException ex) {
            log.warn("bean of type is {} and name is {} no found, error: {}",clazz.getName(),name,ex.getMessage());
            return null;
        }
    }

    public static <T> List<T> getBeans(Class<T> clazz){
        try {
            return ContextHolder.getBeans(clazz);
        } catch (BeansException ex) {
            log.warn("beans of type is {} no found, error: {}",clazz.getName(),ex.getMessage());
            return null;
        }
    }

}
