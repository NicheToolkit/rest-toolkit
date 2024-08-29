package io.github.nichetoolkit.rest;

/**
 * <code>RestLoggingKeyAdvice</code>
 * <p>The type rest logging key advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestLoggingKeyAdvice {

    /**
     * <code>doLoggingKeyHandle</code>
     * <p>the logging key handle method.</p>
     * @param requestWrapper {@link io.github.nichetoolkit.rest.HttpRequestWrapper} <p>the request wrapper parameter is <code>HttpRequestWrapper</code> type.</p>
     * @return {@link java.lang.String} <p>the logging key handle return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.HttpRequestWrapper
     * @see java.lang.String
     */
    String doLoggingKeyHandle(HttpRequestWrapper requestWrapper);
}
