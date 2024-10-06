package io.github.nichetoolkit.rest.advance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.lang.NonNull;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <code>ApplicationContextHolder</code>
 * <p>The type application context holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class ApplicationContextHolder {
    /**
     * <code>APPLICATION_CONTEXT</code>
     * {@link org.springframework.context.ConfigurableApplicationContext} <p>The constant <code>APPLICATION_CONTEXT</code> field.</p>
     * @see org.springframework.context.ConfigurableApplicationContext
     */
    private static ConfigurableApplicationContext APPLICATION_CONTEXT;

    /**
     * <code>initApplicationContext</code>
     * <p>The application context method.</p>
     * @param applicationContext {@link org.springframework.context.ConfigurableApplicationContext} <p>The application context parameter is <code>ConfigurableApplicationContext</code> type.</p>
     * @see org.springframework.context.ConfigurableApplicationContext
     * @see org.springframework.lang.NonNull
     */
    static void initApplicationContext(@NonNull ConfigurableApplicationContext applicationContext) {
        APPLICATION_CONTEXT = applicationContext;
        log.debug("The application context holder has be initiated");
    }

    /**
     * <code>isActiveContext</code>
     * <p>The active context method.</p>
     * @return boolean <p>The active context return object is <code>boolean</code> type.</p>
     */
    public static boolean isActiveContext() {
        return APPLICATION_CONTEXT != null && APPLICATION_CONTEXT.isActive();
    }

    /**
     * <code>getApplicationContext</code>
     * <p>The application context getter method.</p>
     * @return {@link org.springframework.context.ApplicationContext} <p>The application context return object is <code>ApplicationContext</code> type.</p>
     * @see org.springframework.context.ApplicationContext
     * @see org.springframework.lang.NonNull
     */
    @NonNull
    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    /**
     * <code>beanFactory</code>
     * <p>The factory method.</p>
     * @return {@link org.springframework.beans.factory.config.ConfigurableListableBeanFactory} <p>The factory return object is <code>ConfigurableListableBeanFactory</code> type.</p>
     * @see org.springframework.beans.factory.config.ConfigurableListableBeanFactory
     */
    public static ConfigurableListableBeanFactory beanFactory() {
        return APPLICATION_CONTEXT.getBeanFactory();
    }

    /**
     * <code>refreshOfContext</code>
     * <p>The of context method.</p>
     */
    public static void refreshOfContext() {
        APPLICATION_CONTEXT.refresh();
    }

    /**
     * <code>getEnvironment</code>
     * <p>The environment getter method.</p>
     * @return {@link org.springframework.core.env.ConfigurableEnvironment} <p>The environment return object is <code>ConfigurableEnvironment</code> type.</p>
     * @see org.springframework.core.env.ConfigurableEnvironment
     */
    public static ConfigurableEnvironment getEnvironment() {
        return APPLICATION_CONTEXT.getEnvironment();
    }

    /**
     * <code>addBeanFactoryPostProcessor</code>
     * <p>The bean factory post processor method.</p>
     * @param postProcessor {@link org.springframework.beans.factory.config.BeanFactoryPostProcessor} <p>The post processor parameter is <code>BeanFactoryPostProcessor</code> type.</p>
     * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor
     */
    public static void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor) {
        APPLICATION_CONTEXT.addBeanFactoryPostProcessor(postProcessor);
    }

    /**
     * <code>addApplicationListener</code>
     * <p>The application listener method.</p>
     * @param listener {@link org.springframework.context.ApplicationListener} <p>The listener parameter is <code>ApplicationListener</code> type.</p>
     * @see org.springframework.context.ApplicationListener
     */
    public static void addApplicationListener(ApplicationListener<?> listener) {
        APPLICATION_CONTEXT.addApplicationListener(listener);
    }

    /**
     * <code>addProtocolResolver</code>
     * <p>The protocol resolver method.</p>
     * @param resolver {@link org.springframework.core.io.ProtocolResolver} <p>The resolver parameter is <code>ProtocolResolver</code> type.</p>
     * @see org.springframework.core.io.ProtocolResolver
     */
    public static void addProtocolResolver(ProtocolResolver resolver) {
        APPLICATION_CONTEXT.addProtocolResolver(resolver);
    }

    /**
     * <code>beanOfName</code>
     * <p>The of name method.</p>
     * @param beanName {@link java.lang.String} <p>The bean name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Object} <p>The of name return object is <code>Object</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.beans.BeansException
     */
    public static Object beanOfName(String beanName) throws BeansException {
        return APPLICATION_CONTEXT.getBean(beanName);
    }

    /**
     * <code>beanOfType</code>
     * <p>The of type method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param beanType {@link java.lang.Class} <p>The bean type parameter is <code>Class</code> type.</p>
     * @return T <p>The of type return object is <code>T</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.Class
     * @see org.springframework.beans.BeansException
     */
    public static <T> T beanOfType(Class<T> beanType) throws BeansException {
        return APPLICATION_CONTEXT.getBean(beanType);
    }

    /**
     * <code>beanOfType</code>
     * <p>The of type method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param name     {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param beanType {@link java.lang.Class} <p>The bean type parameter is <code>Class</code> type.</p>
     * @return T <p>The of type return object is <code>T</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see org.springframework.beans.BeansException
     */
    public static <T> T beanOfType(String name, Class<T> beanType) throws BeansException {
        return APPLICATION_CONTEXT.getBean(name, beanType);
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
        return beansOfType(beanType, true, false);
    }

    /**
     * <code>beansOfType</code>
     * <p>The of type method.</p>
     * @param <T>                  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param beanType             {@link java.lang.Class} <p>The bean type parameter is <code>Class</code> type.</p>
     * @param includeNonSingletons boolean <p>The include non singletons parameter is <code>boolean</code> type.</p>
     * @param allowEagerInit       boolean <p>The allow eager init parameter is <code>boolean</code> type.</p>
     * @return {@link java.util.List} <p>The of type return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T> List<T> beansOfType(Class<T> beanType, boolean includeNonSingletons, boolean allowEagerInit) {
        Map<String, T> beansOfType = beanMapOfType(beanType, includeNonSingletons, allowEagerInit);
        if (!beansOfType.isEmpty()) {
            List<T> beans = new ArrayList<>(beansOfType.values());
            AnnotationAwareOrderComparator.sort(beans);
            return Collections.unmodifiableList(beans);
        }
        return Collections.emptyList();
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
        return beanMapOfType(beanType, true, false);
    }

    /**
     * <code>beanMapOfType</code>
     * <p>The map of type method.</p>
     * @param <T>                  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param beanType             {@link java.lang.Class} <p>The bean type parameter is <code>Class</code> type.</p>
     * @param includeNonSingletons boolean <p>The include non singletons parameter is <code>boolean</code> type.</p>
     * @param allowEagerInit       boolean <p>The allow eager init parameter is <code>boolean</code> type.</p>
     * @return {@link java.util.Map} <p>The map of type return object is <code>Map</code> type.</p>
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <T> Map<String, T> beanMapOfType(Class<T> beanType, boolean includeNonSingletons, boolean allowEagerInit) {
        return APPLICATION_CONTEXT.getBeansOfType(beanType, includeNonSingletons, allowEagerInit);
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
        return APPLICATION_CONTEXT.getBeansWithAnnotation(annotationType);
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
        Map<String, Object> beansOfType = beanMapOfAnnotation(annotationType);
        if (!beansOfType.isEmpty()) {
            List<Object> beans = new ArrayList<>(beansOfType.values());
            AnnotationAwareOrderComparator.sort(beans);
            return Collections.unmodifiableList(beans);
        }
        return Collections.emptyList();
    }


}
