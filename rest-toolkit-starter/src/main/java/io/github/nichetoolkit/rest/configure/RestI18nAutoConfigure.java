package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.RestI18nBasename;
import io.github.nichetoolkit.rest.constant.RestConstants;
import io.github.nichetoolkit.rest.holder.MessageSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.*;

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
     * <code>sessionLocaleResolver</code>
     * <p>The session locale resolver method.</p>
     * @return {@link org.springframework.web.servlet.LocaleResolver} <p>The session locale resolver return object is <code>LocaleResolver</code> type.</p>
     * @see org.springframework.web.servlet.LocaleResolver
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     */
    @Bean
    @ConditionalOnProperty(value = "nichetoolkit.rest.i18n.session-resolver-enabled", havingValue = "true")
    public LocaleResolver sessionLocaleResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(this.i18nProperties.getLocale().getValue());
        return sessionLocaleResolver;
    }

    /**
     * <code>cookieLocaleResolver</code>
     * <p>The cookie locale resolver method.</p>
     * @return {@link org.springframework.web.servlet.LocaleResolver} <p>The cookie locale resolver return object is <code>LocaleResolver</code> type.</p>
     * @see org.springframework.web.servlet.LocaleResolver
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     */
    @Bean
    @ConditionalOnProperty(value = "nichetoolkit.rest.i18n.cookie-resolver-enabled", havingValue = "true")
    public LocaleResolver cookieLocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(this.i18nProperties.getLocale().getValue());
        return cookieLocaleResolver;
    }

    /**
     * <code>localeChangeInterceptor</code>
     * <p>The locale change interceptor method.</p>
     * @return {@link org.springframework.web.servlet.i18n.LocaleChangeInterceptor} <p>The locale change interceptor return object is <code>LocaleChangeInterceptor</code> type.</p>
     * @see org.springframework.web.servlet.i18n.LocaleChangeInterceptor
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     */
    @Bean
    @ConditionalOnProperty(value = "nichetoolkit.rest.i18n.interceptor-enabled", havingValue = "true")
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName(this.i18nProperties.getParamName());
        return localeChangeInterceptor;
    }


    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        if (this.i18nProperties.getInterceptorEnabled()) {
            LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
            localeChangeInterceptor.setParamName(this.i18nProperties.getParamName());
            registry.addInterceptor(localeChangeInterceptor);
        }
    }

    /**
     * <code>restI18nBasename</code>
     * <p>The rest i 18 n basename method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestI18nBasename} <p>The rest i 18 n basename return object is <code>RestI18nBasename</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestI18nBasename
     * @see org.springframework.context.annotation.Bean
     */
    @Bean
    public RestI18nBasename restI18nBasename() {
        return () -> Collections.singleton(RestConstants.REST_I18N);
    }

    /**
     * <code>messageSource</code>
     * <p>The message source method.</p>
     * @param i18nBasenameList {@link java.util.List} <p>The 18 n basename list parameter is <code>List</code> type.</p>
     * @return {@link org.springframework.context.support.ResourceBundleMessageSource} <p>The message source return object is <code>ResourceBundleMessageSource</code> type.</p>
     * @see java.util.List
     * @see org.springframework.context.support.ResourceBundleMessageSource
     * @see org.springframework.context.annotation.Bean
     */
    @Bean
    public ResourceBundleMessageSource messageSource(List<RestI18nBasename> i18nBasenameList) {
        Locale.setDefault(this.i18nProperties.getLocale().getValue());
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        String[] basename = this.i18nProperties.getBasename();
        Set<String> basenameSet = new HashSet<>(Arrays.asList(basename));
        i18nBasenameList.forEach(i18nBasename -> {
            basenameSet.addAll(i18nBasename.getBaseNames());
        });
        source.setBasenames(basenameSet.toArray(new String[0]));
        source.setUseCodeAsDefaultMessage(false);
        source.setDefaultEncoding(this.i18nProperties.getCharset().getKey());
        MessageSourceHolder.refreshMessageSource(source);
        return source;
    }
}
