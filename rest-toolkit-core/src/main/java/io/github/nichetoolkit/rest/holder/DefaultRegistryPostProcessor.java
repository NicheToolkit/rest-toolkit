package io.github.nichetoolkit.rest.holder;

import io.github.nichetoolkit.rest.RestIntend;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.LoggerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <code>DefaultRegistryPostProcessor</code>
 * <p>The type default registry post processor class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class DefaultRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    @SuppressWarnings("rawtypes")
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        BeanDefinitionRegistryHolder.initBeanDefinitionRegistry(beanDefinitionRegistry);
        List<RestIntend> intendList = SpringFactoriesLoader.loadFactories(RestIntend.class, null);
        if (GeneralUtils.isNotEmpty(intendList)) {
            for (RestIntend<?> intend : intendList) {
                String beanName = intend.beanName();
                Class<?> beanType = intend.beanType();
                BeanDefinitionRegistryHolder.registerBeanDefinition(beanName, beanType);
            }
            log.debug("There are {} intend beans has be initiated.", intendList.size());
        }
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory listableBeanFactory) throws BeansException {
        ListableBeanFactoryHolder.initConfigurableListableBeanFactory(listableBeanFactory);
    }
}