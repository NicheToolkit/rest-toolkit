package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.util.Collection;

/**
 * <code>RestAccessTokenAdvice</code>
 * <p>The type rest access token advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestAccessTokenAdvice extends RestAccessToken {

    @Override
   default String accessToken(RestHttpRequest httpRequest) {
        return doAccessTokenHandle(httpRequest);
    }

    /**
     * <code>doAccessTokenHandle</code>
     * <p>the access token handle method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the http request parameter is <code>RestHttpRequest</code> type.</p>
     * @return {@link java.lang.String} <p>the access token handle return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.String
     */
    String doAccessTokenHandle(RestHttpRequest httpRequest);

    /**
     * <code>doAttributeHandle</code>
     * <p>the attribute handle method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the http request parameter is <code>RestHttpRequest</code> type.</p>
     * @param attribute   {@link java.lang.String} <p>the attribute parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the attribute handle return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.String
     */
    default String doAttributeHandle(RestHttpRequest httpRequest, String attribute) {
        if (GeneralUtils.isEmpty(attribute)) {
            return null;
        }
        String attributeValue = httpRequest.getParameter(attribute);
        if (GeneralUtils.isEmpty(attribute)) {
            attributeValue = httpRequest.getHeader(attribute);
        }
        return attributeValue;
    }

    /**
     * <code>doAttributesHandle</code>
     * <p>the attributes handle method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the http request parameter is <code>RestHttpRequest</code> type.</p>
     * @param attributes  {@link java.util.Collection} <p>the attributes parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.String} <p>the attributes handle return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.util.Collection
     * @see java.lang.String
     */
    default String doAttributesHandle(RestHttpRequest httpRequest, Collection<String> attributes) {
        for (String attribute : attributes) {
            String attributeValue = doAttributeHandle(httpRequest, attribute);
            if (GeneralUtils.isNotEmpty(attributeValue)) {
                return attributeValue;
            }
        }
        return null;
    }
}
