package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface RestFitter<F> extends InitializingBean {

    @Override
    default void afterPropertiesSet() throws Exception {
        Instance.caching(this.getClass());
    }

    static


    @SuppressWarnings("unchecked")
    default Class<F> fitterClass() {
        return (Class<F>) RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                RestFitter.class.getTypeParameters()[0], this.getClass(), RestFitter.class));
    }


    default boolean supports(Class<?> alertnessType) {
        return alertnessType.isAnnotationPresent(RestAlertness.class);
    }

    @SuppressWarnings({"unchecked","rawtypes"})
    static <F> Class<F> fitterType(Class<? extends RestFitter> declaringFitterType) {
        return (Class<F>) Instance.fitterType(declaringFitterType);
    }

    class Instance {
        static Map<Class<?>, Class<?>> FITTER_CACHES = new ConcurrentHashMap<>();

        @SuppressWarnings("rawtypes")
        private static Class<?> fitterType(Class<? extends RestFitter> declaringFitterType) {
            return FITTER_CACHES.get(declaringFitterType);
        }

        @SuppressWarnings("rawtypes")
        private static void caching(Class<? extends RestFitter> declaringFitterType) {
            Class<?> fitterType = RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                    RestFitter.class.getTypeParameters()[0], declaringFitterType, RestFitter.class));
            if (!FITTER_CACHES.containsKey(declaringFitterType)) {
                FITTER_CACHES.put(declaringFitterType, fitterType);
            }
        }

    }

}
