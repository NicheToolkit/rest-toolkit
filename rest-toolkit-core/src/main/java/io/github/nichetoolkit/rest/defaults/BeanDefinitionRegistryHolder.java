package io.github.nichetoolkit.rest.defaults;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.lang.NonNull;

@Slf4j
public class BeanDefinitionRegistryHolder {

    private static BeanDefinitionRegistry BEAN_DEFINITION_REGISTRY;

    static void initBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry beanDefinitionRegistry) {
        BEAN_DEFINITION_REGISTRY = beanDefinitionRegistry;
        log.debug("the bean definition registry holder has be initiated!");
    }

    @NonNull
    public static BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return BEAN_DEFINITION_REGISTRY;
    }


}
