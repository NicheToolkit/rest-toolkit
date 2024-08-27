package io.github.nichetoolkit.rest.error.network;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>HttpResponseNullException</code>
 * <p>The type http response null exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
 * @since Jdk1.8
 */
public class HttpResponseNullException extends HttpErrorException {

    /**
     * <code>HttpResponseNullException</code>
     * Instantiates a new http response null exception.
     */
    public HttpResponseNullException() {
        super(RestErrorStatus.HTTP_RESPONSE_NULL);
    }

    /**
     * <code>HttpResponseNullException</code>
     * Instantiates a new http response null exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public HttpResponseNullException(RestStatus status) {
        super(status);
    }

    /**
     * <code>HttpResponseNullException</code>
     * Instantiates a new http response null exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public HttpResponseNullException(String message) {
        super(RestErrorStatus.HTTP_RESPONSE_NULL, message);
    }

    /**
     * <code>HttpResponseNullException</code>
     * Instantiates a new http response null exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public HttpResponseNullException(String resource, String message) {
        super(RestErrorStatus.HTTP_RESPONSE_NULL, resource, message);
    }

    @Override
    public HttpResponseNullException get() {
        return new HttpResponseNullException();
    }
}
