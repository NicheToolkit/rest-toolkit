package io.github.nichetoolkit.rest.defaults;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.lang.NonNull;

/**
 * <code>ListableBeanFactoryHolder</code>
 * <p>The type listable bean factory holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
public class ListableBeanFactoryHolder {

    /**
     * <code>LISTABLE_BEAN_FACTORY</code>
     * {@link org.springframework.beans.factory.config.ConfigurableListableBeanFactory} <p>the constant <code>LISTABLE_BEAN_FACTORY</code> field.</p>
     * @see org.springframework.beans.factory.config.ConfigurableListableBeanFactory
     */
    private static ConfigurableListableBeanFactory LISTABLE_BEAN_FACTORY;

    /**
     * <code>initConfigurableListableBeanFactory</code>
     * <p>the configurable listable bean factory method.</p>
     * @param listableBeanFactory {@link org.springframework.beans.factory.config.ConfigurableListableBeanFactory} <p>the listable bean factory parameter is <code>ConfigurableListableBeanFactory</code> type.</p>
     * @see org.springframework.beans.factory.config.ConfigurableListableBeanFactory
     * @see org.springframework.lang.NonNull
     */
    static void initConfigurableListableBeanFactory(@NonNull ConfigurableListableBeanFactory listableBeanFactory) {
        LISTABLE_BEAN_FACTORY = listableBeanFactory;
        log.debug("the configurable listable bean factory holder has be initiated!");
    }

    /**
     * <code>getConfigurableListableBeanFactory</code>
     * <p>the configurable listable bean factory getter method.</p>
     * @return {@link org.springframework.beans.factory.config.ConfigurableListableBeanFactory} <p>the configurable listable bean factory return object is <code>ConfigurableListableBeanFactory</code> type.</p>
     * @see org.springframework.beans.factory.config.ConfigurableListableBeanFactory
     * @see org.springframework.lang.NonNull
     */
    @NonNull
    public static ConfigurableListableBeanFactory getConfigurableListableBeanFactory() {
        return LISTABLE_BEAN_FACTORY;
    }


}
