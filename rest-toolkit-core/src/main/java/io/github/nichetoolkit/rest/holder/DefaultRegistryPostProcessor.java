package io.github.nichetoolkit.rest.holder;

import io.github.nichetoolkit.rest.RestIntend;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <code>DefaultRegistryPostProcessor</code>
 * <p>The default registry post processor class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class DefaultRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        BeanDefinitionRegistryHolder.initBeanDefinitionRegistry(beanDefinitionRegistry);
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory listableBeanFactory) throws BeansException {
        ListableBeanFactoryHolder.initConfigurableListableBeanFactory(listableBeanFactory);
        intendBeanRegistryAndAutowireProperties();
    }

    /**
     * <code>intendBeanRegistryAndAutowireProperties</code>
     * <p>The intend bean registry and autowire properties method.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see org.springframework.beans.BeansException
     */
    @SuppressWarnings("rawtypes")
    private void intendBeanRegistryAndAutowireProperties() throws BeansException {
        List<RestIntend> intendList = SpringFactoriesLoader.loadFactories(RestIntend.class, null);
        if (GeneralUtils.isNotEmpty(intendList)) {
            for (RestIntend<?> intend : intendList) {
                Class<? extends RestIntend> beanType = intend.beanType();
                intend = BeanDefinitionRegistryHolder.registerRootBeanDefinition(intend.beanName(), intend.beanType(), intend.beanScope());
                ListableBeanFactoryHolder.autowireBeanProperties(intend);
                intend.afterAutowirePropertiesSet();
            }
            log.debug("There are {} intend beans has be initiated.", intendList.size());
        }
    }
}