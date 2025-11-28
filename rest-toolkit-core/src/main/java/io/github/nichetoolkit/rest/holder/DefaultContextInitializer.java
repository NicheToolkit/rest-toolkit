package io.github.nichetoolkit.rest.holder;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.jspecify.annotations.NonNull;

/**
 * <code>DefaultContextInitializer</code>
 * <p>The default context initializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.context.ApplicationContextInitializer
 * @since Jdk17
 */
public class DefaultContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        ApplicationContextHolder.initApplicationContext(applicationContext);
        MessageSourceHolder.initMessageSource(applicationContext);
        applicationContext.addBeanFactoryPostProcessor(new DefaultRegistryPostProcessor());

    }
}