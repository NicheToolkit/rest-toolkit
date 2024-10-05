package io.github.nichetoolkit.rest.defaults;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.NonNull;

public class DefaultContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        ApplicationContextHolder.initApplicationContext(applicationContext);
        applicationContext.addBeanFactoryPostProcessor(new DefaultRegistryPostProcessor());
    }
}