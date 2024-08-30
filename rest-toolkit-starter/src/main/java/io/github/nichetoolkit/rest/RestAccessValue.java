package io.github.nichetoolkit.rest;

/**
 * <code>RestAccessToken</code>
 * <p>The type rest access token interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestAccessValue {
    /**
     * <code>accessToken</code>
     * <p>the token method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the http request parameter is <code>RestHttpRequest</code> type.</p>
     * @return {@link java.lang.String} <p>the token return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.String
     */
    String accessToken(RestHttpRequest httpRequest);

    /**
     * <code>accessAuth</code>
     * <p>the auth method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the http request parameter is <code>RestHttpRequest</code> type.</p>
     * @return {@link java.lang.String} <p>the auth return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.String
     */
    String accessAuth(RestHttpRequest httpRequest);
}
