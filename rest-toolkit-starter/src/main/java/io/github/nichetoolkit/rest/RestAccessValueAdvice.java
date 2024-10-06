package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.util.Collection;

/**
 * <code>RestAccessValueAdvice</code>
 * <p>The type rest access value advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestAccessValueAdvice extends RestAccessValue {

    @Override
   default String accessToken(RestHttpRequest httpRequest) {
        return doAccessTokenHandle(httpRequest);
    }


    @Override
    default String accessAuth(RestHttpRequest httpRequest) {
        return doAccessAuthHandle(httpRequest);
    }

    /**
     * <code>doAccessTokenHandle</code>
     * <p>The access token handle method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The http request parameter is <code>RestHttpRequest</code> type.</p>
     * @return {@link java.lang.String} <p>The access token handle return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.String
     */
    String doAccessTokenHandle(RestHttpRequest httpRequest);

    /**
     * <code>doAccessAuthHandle</code>
     * <p>The access auth handle method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The http request parameter is <code>RestHttpRequest</code> type.</p>
     * @return {@link java.lang.String} <p>The access auth handle return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.String
     */
    String doAccessAuthHandle(RestHttpRequest httpRequest);

    /**
     * <code>doAttributeHandle</code>
     * <p>The attribute handle method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The http request parameter is <code>RestHttpRequest</code> type.</p>
     * @param attribute   {@link java.lang.String} <p>The attribute parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The attribute handle return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.String
     */
    default String doAttributeHandle(RestHttpRequest httpRequest, String attribute) {
        if (GeneralUtils.isEmpty(attribute)) {
            return null;
        }
        String attributeValue = httpRequest.getParameter(attribute);
        if (GeneralUtils.isEmpty(attributeValue)) {
            attributeValue = httpRequest.getHeader(attribute);
        }
        return attributeValue;
    }

    /**
     * <code>doAttributesHandle</code>
     * <p>The attributes handle method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The http request parameter is <code>RestHttpRequest</code> type.</p>
     * @param attributes  {@link java.util.Collection} <p>The attributes parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.String} <p>The attributes handle return object is <code>String</code> type.</p>
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
