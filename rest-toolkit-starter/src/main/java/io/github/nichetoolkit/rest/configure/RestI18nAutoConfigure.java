package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.RestI18n;
import io.github.nichetoolkit.rest.resource.RestI18nResources;
import io.github.nichetoolkit.rest.constant.RestConstants;
import io.github.nichetoolkit.rest.holder.MessageSourceHolder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.jspecify.annotations.NonNull;
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
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @see org.springframework.boot.context.properties.EnableConfigurationProperties
 * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 * @see org.springframework.boot.autoconfigure.ImportAutoConfiguration
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
@EnableConfigurationProperties(RestI18nProperties.class)
@ConditionalOnProperty(value = "nichetoolkit.rest.i18n.enabled", havingValue = "true")
@ImportAutoConfiguration({RestI18nAutoConfigure.I18nMessageSourceAutoConfigure.class})
public class RestI18nAutoConfigure {

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
     */
    public RestI18nAutoConfigure(RestI18nProperties i18nProperties) {
        this.i18nProperties = i18nProperties;
        log.debug("The auto configuration for [rest-i18n] initiated");
    }

    /**
     * <code>restI18nResource</code>
     * <p>The rest i 18 n resource method.</p>
     * @return {@link io.github.nichetoolkit.rest.resource.RestI18nResources} <p>The rest i 18 n resource return object is <code>RestI18nResources</code> type.</p>
     * @see io.github.nichetoolkit.rest.resource.RestI18nResources
     * @see org.springframework.context.annotation.Bean
     */
    @Bean
    public RestI18nResources restI18nResource() {
        return RestI18nResources.of(RestConstants.REST_I18N);
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
     * <code>I18nMessageSourceAutoConfigure</code>
     * <p>The 18 n message source auto configure class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see org.springframework.context.annotation.Configuration
     * @since Jdk1.8
     */
    @Configuration
    public class I18nMessageSourceAutoConfigure {

        /**
         * <code>i18nResources</code>
         * {@link java.util.List} <p>The <code>i18nResources</code> field.</p>
         * @see java.util.List
         */
        private final List<RestI18n> i18nResources;

        /**
         * <code>I18nMessageSourceAutoConfigure</code>
         * <p>Instantiates a new 18 n message source auto configure.</p>
         * @param i18nResources {@link java.util.List} <p>The 18 n resources parameter is <code>List</code> type.</p>
         * @see java.util.List
         */
        public I18nMessageSourceAutoConfigure(List<RestI18n> i18nResources) {
            this.i18nResources = i18nResources;
            log.debug("The auto configuration for [i18n-message] initiated");
        }


        /**
         * <code>messageSource</code>
         * <p>The message source method.</p>
         * @return {@link org.springframework.context.MessageSource} <p>The message source return object is <code>MessageSource</code> type.</p>
         * @see org.springframework.context.MessageSource
         * @see org.springframework.context.annotation.Bean
         */
        @Bean
        public MessageSource messageSource() {
            Locale.setDefault(i18nProperties.getLocale().getValue());
            ResourceBundleMessageSource source = new ResourceBundleMessageSource();
            String[] basenameArray = i18nProperties.getBasename();
            Set<String> basenameSet = new HashSet<>();
            if (GeneralUtils.isNotEmpty(basenameArray)) {
                basenameSet.addAll(Arrays.asList(basenameArray));
            }
            i18nResources.forEach(resources -> basenameSet.addAll(resources.getBaseNames()));
            source.setBasenames(basenameSet.toArray(new String[0]));
            source.setUseCodeAsDefaultMessage(false);
            source.setDefaultEncoding(i18nProperties.getCharset().getKey());
            MessageSourceHolder.refreshMessageSource(source);
            return source;
        }
    }

    /**
     * <code>I18nLocaleChangeAutoConfigure</code>
     * <p>The 18 n locale change auto configure class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer
     * @see org.springframework.context.annotation.Configuration
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     * @since Jdk1.8
     */
    @Configuration
    @ConditionalOnProperty(value = "nichetoolkit.rest.i18n.interceptor-enabled", havingValue = "true")
    public class I18nLocaleChangeAutoConfigure implements WebMvcConfigurer {

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
            localeChangeInterceptor.setParamName(i18nProperties.getParamName());
            return localeChangeInterceptor;
        }

        @Override
        public void addInterceptors(@NonNull InterceptorRegistry registry) {
            registry.addInterceptor(localeChangeInterceptor());
        }
    }

}
