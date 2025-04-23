package io.github.nichetoolkit.rest.holder;

import io.github.nichetoolkit.rest.fitter.RestUnfulfilledFitter;
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

    /**
     * <code>IS_HAS_INIT_OF_UNFULFILLED_FITTER</code>
     * <p>The constant <code>IS_HAS_INIT_OF_UNFULFILLED_FITTER</code> field.</p>
     */
    private static boolean IS_HAS_INIT_OF_UNFULFILLED_FITTER = false;

    @Override
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        BeanDefinitionRegistryHolder.initBeanDefinitionRegistry(beanDefinitionRegistry);
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory listableBeanFactory) throws BeansException {
        ListableBeanFactoryHolder.initConfigurableListableBeanFactory(listableBeanFactory);
        initOfUnfulfilledFitter();
    }

    /**
     * <code>initOfUnfulfilledFitter</code>
     * <p>The init of unfulfilled fitter method.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see org.springframework.beans.BeansException
     */
    @SuppressWarnings("rawtypes")
    private void initOfUnfulfilledFitter() throws BeansException {
        if (IS_HAS_INIT_OF_UNFULFILLED_FITTER) {
            return;
        }
        IS_HAS_INIT_OF_UNFULFILLED_FITTER = true;
        List<RestUnfulfilledFitter> unfulfilledFitters = ApplicationContextHolder.beansOfType(RestUnfulfilledFitter.class);
        if (GeneralUtils.isNotEmpty(unfulfilledFitters)) {
            return;
        }
        unfulfilledFitters = SpringFactoriesLoader.loadFactories(RestUnfulfilledFitter.class, null);
        if (GeneralUtils.isEmpty(unfulfilledFitters)) {
            return;
        }
        for (RestUnfulfilledFitter<?> unfulfilledFitter : unfulfilledFitters) {
            unfulfilledFitter = BeanDefinitionRegistryHolder.registerRootBeanDefinition(unfulfilledFitter.beanName(), unfulfilledFitter.beanType(), unfulfilledFitter.beanScope());
            ListableBeanFactoryHolder.autowireBeanProperties(unfulfilledFitter);
            unfulfilledFitter.afterAutowirePropertiesSet();
        }
        log.debug("There are {} unfulfilled fitter beans has be initiated.", unfulfilledFitters.size());
    }
}