package io.github.nichetoolkit.rest.holder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>ContextHolder</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ContextHolder {

    private static ApplicationContext APPLICATION_CONTEXT;

    public static void initApplicationContext(ApplicationContext applicationContext) {
        APPLICATION_CONTEXT = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    public static Object getBean(String name) throws BeansException {
        if (null != APPLICATION_CONTEXT) {
            return APPLICATION_CONTEXT.getBean(name);
        }
        return null;
    }

    public static <T> T getBean(Class<T> clazz) throws BeansException {
        if (null != APPLICATION_CONTEXT) {
            return APPLICATION_CONTEXT.getBean(clazz);
        }
        return null;
    }

    public static <T> T getBean(String name, Class<T> clazz) throws BeansException {
        if (null != APPLICATION_CONTEXT) {
            return APPLICATION_CONTEXT.getBean(name, clazz);
        }
        return null;
    }

    public static <T> List<T> getBeans(Class<T> clazz) {
        return getBeans(APPLICATION_CONTEXT,clazz);
    }


    public static <T> List<T> getBeans(ApplicationContext applicationContext, Class<T> clazz) {
        Map<String, T> beans = BeanFactoryUtils.beansOfTypeIncludingAncestors(APPLICATION_CONTEXT, clazz, true, false);
        if (!beans.isEmpty()) {
            List<T> handlers = new ArrayList<>(beans.values());
            AnnotationAwareOrderComparator.sort(handlers);
            return Collections.unmodifiableList(handlers);
        }
        return Collections.emptyList();
    }
}
