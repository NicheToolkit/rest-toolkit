package io.github.nichetoolkit.rest;

/**
 * <code>RestAccessValue</code>
 * <p>The type rest access value interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestAccessValue {
    /**
     * <code>accessToken</code>
     * <p>The token method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The http request parameter is <code>RestHttpRequest</code> type.</p>
     * @return {@link java.lang.String} <p>The token return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.String
     */
    String accessToken(RestHttpRequest httpRequest);

    /**
     * <code>accessAuth</code>
     * <p>The auth method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The http request parameter is <code>RestHttpRequest</code> type.</p>
     * @return {@link java.lang.String} <p>The auth return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.String
     */
    String accessAuth(RestHttpRequest httpRequest);
}
