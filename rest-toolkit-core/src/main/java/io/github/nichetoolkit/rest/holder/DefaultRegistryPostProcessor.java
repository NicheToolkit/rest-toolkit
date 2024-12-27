package io.github.nichetoolkit.rest.holder;

import io.github.nichetoolkit.rest.RestFitter;
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
 * @see  org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
 * @see  lombok.extern.slf4j.Slf4j
 * @author Cyan (snow22314@outlook.com)
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
        fitterBeanRegistryAndAutowireProperties();
    }

    /**
     * <code>intendBeanRegistryAndAutowireProperties</code>
     * <p>The intend bean registry and autowire properties method.</p>
     * @see  java.lang.SuppressWarnings
     * @see  org.springframework.beans.BeansException
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     */
    @SuppressWarnings("rawtypes")
    private void fitterBeanRegistryAndAutowireProperties() throws BeansException {
        List<RestFitter> fitterList = SpringFactoriesLoader.loadFactories(RestFitter.class, null);
        if (GeneralUtils.isNotEmpty(fitterList)) {
            for (RestFitter<?> fitter : fitterList) {
                Class<? extends RestFitter> beanType = fitter.beanType();
                fitter = BeanDefinitionRegistryHolder.registerRootBeanDefinition(fitter.beanName(), fitter.beanType(), fitter.beanScope());
                ListableBeanFactoryHolder.autowireBeanProperties(fitter);
                fitter.afterAutowirePropertiesSet();
            }
            log.debug("There are {} fitter beans has be initiated.", fitterList.size());
        }
    }
}