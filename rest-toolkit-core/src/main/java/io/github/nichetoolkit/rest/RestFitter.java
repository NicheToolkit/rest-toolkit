package io.github.nichetoolkit.rest;


import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.LoggerUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;

public interface RestFitter<F extends RestFitter<F>> extends RestOrder, InitializingBean {


    @Override
    default void afterPropertiesSet() throws Exception {
        LoggerUtils.debug("The fitter bean of [{}] type for named '{}' has be initiated.", beanType().getName(), beanName());
    }

    default void afterAutowirePropertiesSet() {
    }

    default String beanScope() {
        return BeanDefinition.SCOPE_SINGLETON;
    }

    default String beanName() {
        return beanName(getClass());
    }

    default Class<F> beanType() {
        return beanType(getClass());
    }

    static String beanName(Class<?> intendType) {
        return GeneralUtils.camelCase(intendType.getSimpleName());
    }


    @SuppressWarnings(value = "unchecked")
    static <F extends RestFitter<F>> Class<F> beanType(Class<?> intendType) {
        return (Class<F>) RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                RestFitter.class.getTypeParameters()[0], intendType, RestFitter.class));
    }

}
