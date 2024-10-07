package io.github.nichetoolkit.rest.holder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.lang.NonNull;

/**
 * <code>ListableBeanFactoryHolder</code>
 * <p>The type listable bean factory holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class ListableBeanFactoryHolder {

    /**
     * <code>LISTABLE_BEAN_FACTORY</code>
     * {@link org.springframework.beans.factory.config.ConfigurableListableBeanFactory} <p>The constant <code>LISTABLE_BEAN_FACTORY</code> field.</p>
     * @see org.springframework.beans.factory.config.ConfigurableListableBeanFactory
     */
    private static ConfigurableListableBeanFactory LISTABLE_BEAN_FACTORY;

    /**
     * <code>initConfigurableListableBeanFactory</code>
     * <p>The configurable listable bean factory method.</p>
     * @param listableBeanFactory {@link org.springframework.beans.factory.config.ConfigurableListableBeanFactory} <p>The listable bean factory parameter is <code>ConfigurableListableBeanFactory</code> type.</p>
     * @see org.springframework.beans.factory.config.ConfigurableListableBeanFactory
     * @see org.springframework.lang.NonNull
     */
    static void initConfigurableListableBeanFactory(@NonNull ConfigurableListableBeanFactory listableBeanFactory) {
        LISTABLE_BEAN_FACTORY = listableBeanFactory;
        log.debug("The listable bean factory holder has be initiated");
    }

    /**
     * <code>getConfigurableListableBeanFactory</code>
     * <p>The configurable listable bean factory getter method.</p>
     * @return {@link org.springframework.beans.factory.config.ConfigurableListableBeanFactory} <p>The configurable listable bean factory return object is <code>ConfigurableListableBeanFactory</code> type.</p>
     * @see org.springframework.beans.factory.config.ConfigurableListableBeanFactory
     * @see org.springframework.lang.NonNull
     */
    @NonNull
    public static ConfigurableListableBeanFactory getConfigurableListableBeanFactory() {
        return LISTABLE_BEAN_FACTORY;
    }


    /**
     * <code>createBean</code>
     * <p>The bean method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param beanType {@link java.lang.Class} <p>The bean type parameter is <code>Class</code> type.</p>
     * @return T <p>The bean return object is <code>T</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.Class
     * @see org.springframework.beans.BeansException
     */
    public static <T> T createBean(Class<T> beanType) throws BeansException {
        return LISTABLE_BEAN_FACTORY.createBean(beanType);
    }

    /**
     * <code>autowireBean</code>
     * <p>The bean method.</p>
     * @param existingBean {@link java.lang.Object} <p>The existing bean parameter is <code>Object</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.beans.BeansException
     */
    public static void autowireBean(Object existingBean) throws BeansException {
        LISTABLE_BEAN_FACTORY.autowireBean(existingBean);
    }

    /**
     * <code>configureBean</code>
     * <p>The bean method.</p>
     * @param existingBean {@link java.lang.Object} <p>The existing bean parameter is <code>Object</code> type.</p>
     * @param beanName     {@link java.lang.String} <p>The bean name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Object} <p>The bean return object is <code>Object</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.String
     * @see org.springframework.beans.BeansException
     */
    public static Object configureBean(Object existingBean, String beanName) throws BeansException {
        return LISTABLE_BEAN_FACTORY.configureBean(existingBean, beanName);
    }

    /**
     * <code>createBean</code>
     * <p>The bean method.</p>
     * @param beanType        {@link java.lang.Class} <p>The bean type parameter is <code>Class</code> type.</p>
     * @param autowireMode    int <p>The autowire mode parameter is <code>int</code> type.</p>
     * @param dependencyCheck boolean <p>The dependency check parameter is <code>boolean</code> type.</p>
     * @return {@link java.lang.Object} <p>The bean return object is <code>Object</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Object
     * @see org.springframework.beans.BeansException
     */
    public static Object createBean(Class<?> beanType, int autowireMode, boolean dependencyCheck) throws BeansException {
        return LISTABLE_BEAN_FACTORY.createBean(beanType, autowireMode, dependencyCheck);
    }


    /**
     * <code>autowire</code>
     * <p>The method.</p>
     * @param beanType {@link java.lang.Class} <p>The bean type parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.Object} <p>The return object is <code>Object</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Object
     * @see org.springframework.beans.BeansException
     */
    public static Object autowire(Class<?> beanType) throws BeansException {
        return LISTABLE_BEAN_FACTORY.autowire(beanType, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
    }


    /**
     * <code>autowire</code>
     * <p>The method.</p>
     * @param beanType        {@link java.lang.Class} <p>The bean type parameter is <code>Class</code> type.</p>
     * @param autowireMode    int <p>The autowire mode parameter is <code>int</code> type.</p>
     * @param dependencyCheck boolean <p>The dependency check parameter is <code>boolean</code> type.</p>
     * @return {@link java.lang.Object} <p>The return object is <code>Object</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Object
     * @see org.springframework.beans.BeansException
     */
    public static Object autowire(Class<?> beanType, int autowireMode, boolean dependencyCheck) throws BeansException {
        return LISTABLE_BEAN_FACTORY.autowire(beanType, autowireMode, dependencyCheck);
    }

    /**
     * <code>autowireBeanProperties</code>
     * <p>The bean properties method.</p>
     * @param existingBean {@link java.lang.Object} <p>The existing bean parameter is <code>Object</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.beans.BeansException
     */
    public static void autowireBeanProperties(Object existingBean) throws BeansException {
        LISTABLE_BEAN_FACTORY.autowireBeanProperties(existingBean, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
    }

    /**
     * <code>autowireBeanProperties</code>
     * <p>The bean properties method.</p>
     * @param existingBean    {@link java.lang.Object} <p>The existing bean parameter is <code>Object</code> type.</p>
     * @param autowireMode    int <p>The autowire mode parameter is <code>int</code> type.</p>
     * @param dependencyCheck boolean <p>The dependency check parameter is <code>boolean</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.beans.BeansException
     */
    public static void autowireBeanProperties(Object existingBean, int autowireMode, boolean dependencyCheck) throws BeansException {
        LISTABLE_BEAN_FACTORY.autowireBeanProperties(existingBean, autowireMode, dependencyCheck);
    }

    /**
     * <code>applyBeanPropertyValues</code>
     * <p>The bean property values method.</p>
     * @param existingBean {@link java.lang.Object} <p>The existing bean parameter is <code>Object</code> type.</p>
     * @param beanName     {@link java.lang.String} <p>The bean name parameter is <code>String</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.String
     * @see org.springframework.beans.BeansException
     */
    public static void applyBeanPropertyValues(Object existingBean, String beanName) throws BeansException {
        LISTABLE_BEAN_FACTORY.applyBeanPropertyValues(existingBean, beanName);
    }

    /**
     * <code>initializeBean</code>
     * <p>The bean method.</p>
     * @param existingBean {@link java.lang.Object} <p>The existing bean parameter is <code>Object</code> type.</p>
     * @param beanName     {@link java.lang.String} <p>The bean name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Object} <p>The bean return object is <code>Object</code> type.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.String
     * @see org.springframework.beans.BeansException
     */
    public static Object initializeBean(Object existingBean, String beanName) throws BeansException {
        return LISTABLE_BEAN_FACTORY.initializeBean(existingBean, beanName);
    }

    /**
     * <code>destroyBean</code>
     * <p>The bean method.</p>
     * @param existingBean {@link java.lang.Object} <p>The existing bean parameter is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    public static void destroyBean(Object existingBean) {
        LISTABLE_BEAN_FACTORY.destroyBean(existingBean);
    }


}
