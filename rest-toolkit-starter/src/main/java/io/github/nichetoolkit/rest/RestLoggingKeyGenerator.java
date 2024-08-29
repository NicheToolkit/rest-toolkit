package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;

/**
 * <code>RestLoggingKeyGenerator</code>
 * <p>The type rest logging key generator class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestLoggingKeyAdvice
 * @see io.github.nichetoolkit.rest.RestRequestAdvice
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public abstract class RestLoggingKeyGenerator implements RestLoggingKeyAdvice, RestRequestAdvice {
    /**
     * <code>logbackProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the <code>logbackProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    protected final RestLogbackProperties logbackProperties;

    /**
     * <code>RestLoggingKeyGenerator</code>
     * Instantiates a new rest logging key generator.
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    public RestLoggingKeyGenerator(RestLogbackProperties logbackProperties) {
        this.logbackProperties = logbackProperties;
    }

    @Override
    public String doLoggingKeyHandle(HttpRequestWrapper requestWrapper) {
        String loggingKey = "";
        int prefixLength = 24;
        String accessToken = doAccessTokenHandle(requestWrapper);
        if (GeneralUtils.isNotEmpty(accessToken)) {
            if (accessToken.length() > prefixLength) {
                loggingKey = accessToken.substring(accessToken.length() - prefixLength);
            }
        } else {
            loggingKey = requestWrapper.getSession().getId();
            if (loggingKey.length() > prefixLength) {
                loggingKey = loggingKey.substring(loggingKey.length() - prefixLength);
            }
        }
        List<String> attributes = logbackProperties.getAttributes();
        String attribute = doAttributesHandle(requestWrapper, attributes);
        if (GeneralUtils.isEmpty(attribute)) {
            attribute = IdentityUtils.generateString();
        }
        loggingKey += attribute;
        loggingKey = loggingKey.replaceAll("[=.]", "");
        return loggingKey;
    }

   abstract public String doAccessTokenHandle(HttpRequestWrapper requestWrapper);

    public String doAttributeHandle(HttpRequestWrapper requestWrapper, String attribute) {
        if (GeneralUtils.isEmpty(attribute)) {
            return null;
        }
        String attributeValue = requestWrapper.getParameter(attribute);
        if (GeneralUtils.isEmpty(attribute)) {
            attributeValue = requestWrapper.getHeader(attribute);
        }
        return attributeValue;
    }

    public String doAttributesHandle(HttpRequestWrapper requestWrapper, Collection<String> attributes) {
        for (String attribute : attributes) {
            String attributeValue = doAttributeHandle(requestWrapper, attribute);
            if (GeneralUtils.isNotEmpty(attributeValue)) {
                return attributeValue;
            }
        }
        return null;
    }
}
