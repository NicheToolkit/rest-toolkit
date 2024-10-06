package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <code>RestLoggingKeyGenerator</code>
 * <p>The type rest logging key generator class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestLoggingKeyAdvice
 * @see io.github.nichetoolkit.rest.RestAccessValueAdvice
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public abstract class RestLoggingKeyGenerator implements RestLoggingKeyAdvice, RestAccessValueAdvice {
    /**
     * <code>logbackProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>The <code>logbackProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    protected final RestLogbackProperties logbackProperties;

    /**
     * <code>RestLoggingKeyGenerator</code>
     * <p>Instantiates a new rest logging key generator.</p>
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>The logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    public RestLoggingKeyGenerator(RestLogbackProperties logbackProperties) {
        this.logbackProperties = logbackProperties;
    }

    @Override
    public String doLoggingKeyHandle(RestHttpRequest httpRequest) {
        String loggingKey = "";
        int prefixLength = logbackProperties.getPrefixLength();
        String accessToken = doAccessTokenHandle(httpRequest);
        if (GeneralUtils.isNotEmpty(accessToken)) {
            if (accessToken.length() > prefixLength) {
                loggingKey = accessToken.substring(accessToken.length() - prefixLength);
            }
        }
        String accessAuth = doAccessAuthHandle(httpRequest);
        if (GeneralUtils.isEmpty(loggingKey) && GeneralUtils.isNotEmpty(accessAuth)) {
            if (accessAuth.length() > prefixLength) {
                loggingKey = accessAuth.substring(accessAuth.length() - prefixLength);
            }
        }
        String sessionId = httpRequest.getSession().getId();
        if (GeneralUtils.isEmpty(loggingKey) && GeneralUtils.isNotEmpty(sessionId)) {
            if (sessionId.length() > prefixLength) {
                loggingKey = sessionId.substring(sessionId.length() - prefixLength);
            }
        }
        List<String> attributes = logbackProperties.getAttributes();
        String attribute = doAttributesHandle(httpRequest, attributes);
        if (GeneralUtils.isEmpty(attribute)) {
            attribute = IdentityUtils.generateString();
        }
        loggingKey += "-".concat(attribute);
        loggingKey = loggingKey.replaceAll("[=.]", "");
        return loggingKey;
    }

    abstract public String doAccessTokenHandle(RestHttpRequest httpRequest);

    abstract public String doAccessAuthHandle(RestHttpRequest httpRequest);
}
