package io.github.nichetoolkit.rest;

import java.util.Collection;

/**
 * <code>RestRequestAdvice</code>
 * <p>The type rest request advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestRequestAdvice {

    /**
     * <code>doAccessTokenHandle</code>
     * <p>the access token handle method.</p>
     * @param requestWrapper {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the request wrapper parameter is <code>RestHttpRequest</code> type.</p>
     * @return {@link java.lang.String} <p>the access token handle return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.String
     */
    String doAccessTokenHandle(RestHttpRequest requestWrapper);

    /**
     * <code>doAttributeHandle</code>
     * <p>the attribute handle method.</p>
     * @param requestWrapper {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the request wrapper parameter is <code>RestHttpRequest</code> type.</p>
     * @param attribute      {@link java.lang.String} <p>the attribute parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the attribute handle return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.String
     */
    String doAttributeHandle(RestHttpRequest requestWrapper, String attribute);

    /**
     * <code>doAttributesHandle</code>
     * <p>the attributes handle method.</p>
     * @param requestWrapper {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the request wrapper parameter is <code>RestHttpRequest</code> type.</p>
     * @param attributes     {@link java.util.Collection} <p>the attributes parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.String} <p>the attributes handle return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.util.Collection
     * @see java.lang.String
     */
    String doAttributesHandle(RestHttpRequest requestWrapper, Collection<String> attributes);
}
