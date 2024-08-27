package io.github.nichetoolkit.rest.error.network;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>HttpResultDataNullException</code>
 * <p>The type http result data null exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
 * @since Jdk1.8
 */
public class HttpResultDataNullException extends HttpErrorException {

    /**
     * <code>HttpResultDataNullException</code>
     * Instantiates a new http result data null exception.
     */
    public HttpResultDataNullException() {
        super(RestErrorStatus.HTTP_RESULT_DATA_NULL);
    }

    /**
     * <code>HttpResultDataNullException</code>
     * Instantiates a new http result data null exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public HttpResultDataNullException(RestStatus status) {
        super(status);
    }

    /**
     * <code>HttpResultDataNullException</code>
     * Instantiates a new http result data null exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public HttpResultDataNullException(String message) {
        super( RestErrorStatus.HTTP_RESULT_DATA_NULL,message);
    }

    /**
     * <code>HttpResultDataNullException</code>
     * Instantiates a new http result data null exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public HttpResultDataNullException(String resource, String message) {
        super(RestErrorStatus.HTTP_RESULT_DATA_NULL,resource,message);
    }

    @Override
    public HttpResultDataNullException get() {
        return new HttpResultDataNullException();
    }
}
