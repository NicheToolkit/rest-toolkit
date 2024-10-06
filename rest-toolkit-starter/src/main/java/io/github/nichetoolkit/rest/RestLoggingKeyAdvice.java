package io.github.nichetoolkit.rest;

/**
 * <code>RestLoggingKeyAdvice</code>
 * <p>The type rest logging key advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestLoggingKeyAdvice extends RestLoggingKey {

    @Override
    default String loggingKey(RestHttpRequest httpRequest) {
        return doLoggingKeyHandle(httpRequest);
    }

    /**
     * <code>doLoggingKeyHandle</code>
     * <p>The logging key handle method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The http request parameter is <code>RestHttpRequest</code> type.</p>
     * @return {@link java.lang.String} <p>The logging key handle return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.String
     */
    String doLoggingKeyHandle(RestHttpRequest httpRequest);
}
