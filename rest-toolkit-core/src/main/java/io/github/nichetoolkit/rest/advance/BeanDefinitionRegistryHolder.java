package io.github.nichetoolkit.rest.advance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.lang.NonNull;

/**
 * <code>BeanDefinitionRegistryHolder</code>
 * <p>The type bean definition registry holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class BeanDefinitionRegistryHolder {

    /**
     * <code>BEAN_DEFINITION_REGISTRY</code>
     * {@link org.springframework.beans.factory.support.BeanDefinitionRegistry} <p>The constant <code>BEAN_DEFINITION_REGISTRY</code> field.</p>
     * @see org.springframework.beans.factory.support.BeanDefinitionRegistry
     */
    private static BeanDefinitionRegistry BEAN_DEFINITION_REGISTRY;

    /**
     * <code>initBeanDefinitionRegistry</code>
     * <p>The bean definition registry method.</p>
     * @param beanDefinitionRegistry {@link org.springframework.beans.factory.support.BeanDefinitionRegistry} <p>The bean definition registry parameter is <code>BeanDefinitionRegistry</code> type.</p>
     * @see org.springframework.beans.factory.support.BeanDefinitionRegistry
     * @see org.springframework.lang.NonNull
     */
    static void initBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry beanDefinitionRegistry) {
        BEAN_DEFINITION_REGISTRY = beanDefinitionRegistry;
        log.debug("The bean definition registry holder has be initiated");
    }

    /**
     * <code>getBeanDefinitionRegistry</code>
     * <p>The bean definition registry getter method.</p>
     * @return {@link org.springframework.beans.factory.support.BeanDefinitionRegistry} <p>The bean definition registry return object is <code>BeanDefinitionRegistry</code> type.</p>
     * @see org.springframework.beans.factory.support.BeanDefinitionRegistry
     * @see org.springframework.lang.NonNull
     */
    @NonNull
    public static BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return BEAN_DEFINITION_REGISTRY;
    }


    /**
     * <code>registerBeanDefinition</code>
     * <p>The bean definition method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param beanName {@link java.lang.String} <p>The bean name parameter is <code>String</code> type.</p>
     * @param beanType {@link java.lang.Class} <p>The bean type parameter is <code>Class</code> type.</p>
     * @return T <p>The bean definition return object is <code>T</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <T> T registerBeanDefinition(String beanName, Class<T> beanType) {
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(beanType).getBeanDefinition();
        BEAN_DEFINITION_REGISTRY.registerBeanDefinition(beanName, beanDefinition);
        ApplicationContextHolder.refreshOfContext();
        return ApplicationContextHolder.beanOfType(beanName, beanType);
    }

    /**
     * <code>registerBeanDefinition</code>
     * <p>The bean definition method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param beanType {@link java.lang.Class} <p>The bean type parameter is <code>Class</code> type.</p>
     * @return T <p>The bean definition return object is <code>T</code> type.</p>
     * @see java.lang.Class
     */
    public static <T> T registerBeanDefinition(Class<T> beanType) {
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(beanType).getBeanDefinition();
        String beanName = DefaultBeanNameGenerator.INSTANCE.generateBeanName(beanDefinition, BEAN_DEFINITION_REGISTRY);
        BEAN_DEFINITION_REGISTRY.registerBeanDefinition(beanName, beanDefinition);
        ApplicationContextHolder.refreshOfContext();
        return ApplicationContextHolder.beanOfType(beanName, beanType);
    }


}
