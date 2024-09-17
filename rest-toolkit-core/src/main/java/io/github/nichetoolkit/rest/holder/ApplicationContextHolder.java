package io.github.nichetoolkit.rest.holder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <code>ApplicationContextHolder</code>
 * <p>The type application context holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.context.ApplicationContextAware
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.core.annotation.Order
 * @see org.springframework.stereotype.Component
 * @since Jdk1.8
 */
@Slf4j
@Order(1)
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    /**
     * <code>APPLICATION_CONTEXT</code>
     * {@link org.springframework.context.ApplicationContext} <p>the constant <code>APPLICATION_CONTEXT</code> field.</p>
     * @see org.springframework.context.ApplicationContext
     */
    private static ApplicationContext APPLICATION_CONTEXT;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        log.debug("the application context holder initiated");
        APPLICATION_CONTEXT = applicationContext;
    }

    /**
     * <code>initApplicationContext</code>
     * <p>the application context method.</p>
     * @param applicationContext {@link org.springframework.context.ApplicationContext} <p>the application context parameter is <code>ApplicationContext</code> type.</p>
     * @see org.springframework.context.ApplicationContext
     * @see org.springframework.lang.NonNull
     */
    public static void initApplicationContext(@NonNull ApplicationContext applicationContext) {
        APPLICATION_CONTEXT = applicationContext;
    }

    /**
     * <code>getApplicationContext</code>
     * <p>the application context getter method.</p>
     * @return {@link org.springframework.context.ApplicationContext} <p>the application context return object is <code>ApplicationContext</code> type.</p>
     * @see org.springframework.context.ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    /**
     * <code>getBean</code>
     * <p>the bean getter method.</p>
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Object} <p>the bean return object is <code>Object</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>the beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.beans.BeansException
     */
    public static Object getBean(String name) throws BeansException {
        if (null != APPLICATION_CONTEXT) {
            return APPLICATION_CONTEXT.getBean(name);
        }
        return null;
    }

    /**
     * <code>getBean</code>
     * <p>the bean getter method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the bean return object is <code>T</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>the beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.Class
     * @see org.springframework.beans.BeansException
     */
    public static <T> T getBean(Class<T> clazz) throws BeansException {
        if (null != APPLICATION_CONTEXT) {
            return APPLICATION_CONTEXT.getBean(clazz);
        }
        return null;
    }

    /**
     * <code>getBean</code>
     * <p>the bean getter method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param name  {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the bean return object is <code>T</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>the beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see org.springframework.beans.BeansException
     */
    public static <T> T getBean(String name, Class<T> clazz) throws BeansException {
        if (null != APPLICATION_CONTEXT) {
            return APPLICATION_CONTEXT.getBean(name, clazz);
        }
        return null;
    }

    /**
     * <code>getBeans</code>
     * <p>the beans getter method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the beans return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T> List<T> getBeans(Class<T> clazz) {
        return getBeans(APPLICATION_CONTEXT, clazz);
    }


    /**
     * <code>getBeans</code>
     * <p>the beans getter method.</p>
     * @param <T>                {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param applicationContext {@link org.springframework.context.ApplicationContext} <p>the application context parameter is <code>ApplicationContext</code> type.</p>
     * @param clazz              {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the beans return object is <code>List</code> type.</p>
     * @see org.springframework.context.ApplicationContext
     * @see java.lang.Class
     * @see java.util.List
     */
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
