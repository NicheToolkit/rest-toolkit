package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.holder.MessageSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * <code>RestI18nAutoConfigure</code>
 * <p>The rest i 18 n auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see java.lang.SuppressWarnings
 * @see org.springframework.context.annotation.ComponentScan
 * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@ConditionalOnProperty(value = "nichetoolkit.rest.i18n.enabled", havingValue = "true")
public class RestI18nAutoConfigure implements WebMvcConfigurer {

    /**
     * <code>i18nProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestI18nProperties} <p>The <code>i18nProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestI18nProperties
     */
    private final RestI18nProperties i18nProperties;

    /**
     * <code>RestI18nAutoConfigure</code>
     * <p>Instantiates a new rest i 18 n auto configure.</p>
     * @param i18nProperties {@link io.github.nichetoolkit.rest.configure.RestI18nProperties} <p>The 18 n properties parameter is <code>RestI18nProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestI18nProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RestI18nAutoConfigure(RestI18nProperties i18nProperties) {
        this.i18nProperties = i18nProperties;
        log.debug("The auto configuration for [rest-i18n] initiated");
    }

    /**
     * <code>localeResolver</code>
     * <p>The locale resolver method.</p>
     * @return {@link org.springframework.web.servlet.LocaleResolver} <p>The locale resolver return object is <code>LocaleResolver</code> type.</p>
     * @see org.springframework.web.servlet.LocaleResolver
     * @see org.springframework.context.annotation.Bean
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(this.i18nProperties.getLocale().getValue());
        return sessionLocaleResolver;
    }

    /**
     * <code>localeChangeInterceptor</code>
     * <p>The locale change interceptor method.</p>
     * @return {@link org.springframework.web.servlet.i18n.LocaleChangeInterceptor} <p>The locale change interceptor return object is <code>LocaleChangeInterceptor</code> type.</p>
     * @see org.springframework.web.servlet.i18n.LocaleChangeInterceptor
     * @see org.springframework.context.annotation.Bean
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName(this.i18nProperties.getParamName());
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }


    /**
     * <code>messageSource</code>
     * <p>The message source method.</p>
     * @return {@link org.springframework.context.support.ResourceBundleMessageSource} <p>The message source return object is <code>ResourceBundleMessageSource</code> type.</p>
     * @see org.springframework.context.support.ResourceBundleMessageSource
     * @see org.springframework.context.annotation.Bean
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        Locale.setDefault(this.i18nProperties.getLocale().getValue());
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames(this.i18nProperties.getBasename());
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding(this.i18nProperties.getCharset().getKey());
        MessageSourceHolder.refreshMessageSource(source);
        return source;
    }
}
