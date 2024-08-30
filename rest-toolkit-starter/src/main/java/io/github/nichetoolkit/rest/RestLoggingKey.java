package io.github.nichetoolkit.rest;

/**
 * <code>RestLoggingKey</code>
 * <p>The type rest logging key interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestLoggingKey {
    /**
     * <code>loggingKey</code>
     * <p>the key method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the http request parameter is <code>RestHttpRequest</code> type.</p>
     * @return {@link java.lang.String} <p>the key return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.String
     */
    String loggingKey(RestHttpRequest httpRequest);
}
