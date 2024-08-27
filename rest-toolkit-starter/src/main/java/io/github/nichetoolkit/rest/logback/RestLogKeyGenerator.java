package io.github.nichetoolkit.rest.logback;

import io.github.nichetoolkit.rest.RestLogKey;
import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.util.List;

/**
 * <code>RestLogKeyGenerator</code>
 * <p>The type rest log key generator class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestLogKey
 * @since Jdk1.8
 */
public abstract class RestLogKeyGenerator implements RestLogKey {

    /**
     * <code>logbackProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the <code>logbackProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    protected RestLogbackProperties logbackProperties;

    /**
     * <code>RestLogKeyGenerator</code>
     * Instantiates a new rest log key generator.
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    public RestLogKeyGenerator(RestLogbackProperties logbackProperties) {
        this.logbackProperties = logbackProperties;
    }

    @Override
    public String logKey(RestRequestWrapper requestWrapper) {
        String logKey = "";
        int prefixLength = 24;
        String token = accessToken(requestWrapper);
        if (GeneralUtils.isNotEmpty(token)) {
            if (token.length() > prefixLength) {
                logKey = token.substring(token.length() - prefixLength);
            }
        } else {
            logKey = requestWrapper.getSession().getId();
            if (logKey.length() > prefixLength) {
                logKey = logKey.substring(logKey.length() - prefixLength);
            }
        }
        List<String> attributes = logbackProperties.getAttributes();
        String attribute = attributes(requestWrapper, attributes);
        if (GeneralUtils.isEmpty(attribute)) {
            attribute = IdentityUtils.generateString();
        }
        logKey += attribute;
        logKey = logKey.replaceAll("[=.]", "");
        return logKey;
    }

    /**
     * <code>accessToken</code>
     * <p>the token method.</p>
     * @param requestWrapper {@link io.github.nichetoolkit.rest.interceptor.RestRequestWrapper} <p>the request wrapper parameter is <code>RestRequestWrapper</code> type.</p>
     * @return {@link java.lang.String} <p>the token return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.interceptor.RestRequestWrapper
     * @see java.lang.String
     */
    abstract public String accessToken(RestRequestWrapper requestWrapper);

    /**
     * <code>attribute</code>
     * <p>the method.</p>
     * @param requestWrapper {@link io.github.nichetoolkit.rest.interceptor.RestRequestWrapper} <p>the request wrapper parameter is <code>RestRequestWrapper</code> type.</p>
     * @param attribute      {@link java.lang.String} <p>the attribute parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.interceptor.RestRequestWrapper
     * @see java.lang.String
     */
    protected String attribute(RestRequestWrapper requestWrapper, String attribute) {
        if (GeneralUtils.isEmpty(attribute)) {
            return null;
        }
        String attributeValue = requestWrapper.getParameter(attribute);
        if (GeneralUtils.isEmpty(attribute)) {
            attributeValue = requestWrapper.getHeader(attribute);
        }
        return attributeValue;
    }

    /**
     * <code>attributes</code>
     * <p>the method.</p>
     * @param requestWrapper {@link io.github.nichetoolkit.rest.interceptor.RestRequestWrapper} <p>the request wrapper parameter is <code>RestRequestWrapper</code> type.</p>
     * @param attributes     {@link java.util.List} <p>the attributes parameter is <code>List</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.interceptor.RestRequestWrapper
     * @see java.util.List
     * @see java.lang.String
     */
    protected String attributes(RestRequestWrapper requestWrapper, List<String> attributes) {
        for (String attribute : attributes) {
            String attributeValue = attribute(requestWrapper, attribute);
            if (GeneralUtils.isNotEmpty(attributeValue)) {
                return attributeValue;
            }
        }
        return null;
    }
}
