package io.github.nichetoolkit.rest;


import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.LoggerUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * <code>RestIntend</code>
 * <p>The type rest intend interface.</p>
 * @param <B> {@link io.github.nichetoolkit.rest.RestIntend} <p>The generic parameter is <code>RestIntend</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.beans.factory.InitializingBean
 * @since Jdk1.8
 */
public interface RestIntend<B extends RestIntend<B>> extends InitializingBean {


    @Override
    default void afterPropertiesSet() throws Exception {
        LoggerUtils.debug("The intend bean of [{}] type for named '{}' has be initiated.", beanType().getName());
    }

    default String scope() {
        return BeanDefinition.SCOPE_SINGLETON;
    }

    /**
     * <code>beanName</code>
     * <p>The name method.</p>
     * @return {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    default String beanName() {
        return beanName(getClass());
    }

    /**
     * <code>beanType</code>
     * <p>The type method.</p>
     * @return {@link java.lang.Class} <p>The type return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     */
    default Class<B> beanType() {
        return beanType(getClass());
    }

    /**
     * <code>beanName</code>
     * <p>The name method.</p>
     * @param intendType {@link java.lang.Class} <p>The intend type parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.String
     */
    static String beanName(Class<?> intendType) {
        return GeneralUtils.camelCase(intendType.getSimpleName());
    }


    /**
     * <code>beanType</code>
     * <p>The type method.</p>
     * @param <B>        {@link io.github.nichetoolkit.rest.RestIntend} <p>The generic parameter is <code>RestIntend</code> type.</p>
     * @param intendType {@link java.lang.Class} <p>The intend type parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.Class} <p>The type return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    static <B extends RestIntend<B>> Class<B> beanType(Class<?> intendType) {
        return (Class<B>) RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                RestIntend.class.getTypeParameters()[0], intendType, RestIntend.class));
    }

}
