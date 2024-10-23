package io.github.nichetoolkit.rest.error.network;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>HttpErrorException</code>
 * <p>The http error exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class HttpErrorException extends RestErrorException {
    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     */
    public HttpErrorException() {
        super(RestErrorStatus.HTTP_ERROR);
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public HttpErrorException(Integer status) {
        super(status, RestError.error(status, RestErrorStatus.HTTP_ERROR));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    public HttpErrorException(Integer status, Throwable cause) {
        super(status, RestError.error(status, RestErrorStatus.HTTP_ERROR, cause));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public HttpErrorException(Integer status, String message) {
        super(status, RestError.error(status, message));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status   {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public HttpErrorException(Integer status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public HttpErrorException(Integer status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status   {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public HttpErrorException(Integer status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status     {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public HttpErrorException(Integer status, RestStatus restStatus) {
        super(status, RestError.error(status, restStatus));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status     {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param cause      {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public HttpErrorException(Integer status, RestStatus restStatus, Throwable cause) {
        super(status, RestError.error(status, restStatus, cause), cause);
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>The status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public HttpErrorException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public HttpErrorException(String error) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(RestErrorStatus.HTTP_ERROR, error));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public HttpErrorException(String error, Throwable cause) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(RestErrorStatus.HTTP_ERROR, error, cause), cause);
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public HttpErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public HttpErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     */
    public HttpErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    public HttpErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public HttpErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public HttpErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public HttpErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public HttpErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public HttpErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public HttpErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Object
     */
    public HttpErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Throwable
     */
    public HttpErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public HttpErrorException(String resource, String error) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(resource, RestErrorStatus.HTTP_ERROR, error));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public HttpErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(resource, RestErrorStatus.HTTP_ERROR, error, cause), cause);
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public HttpErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(resource, filed, RestErrorStatus.HTTP_ERROR, error));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public HttpErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(resource, filed, RestErrorStatus.HTTP_ERROR, error, cause), cause);
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public HttpErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(resource, filed, value, RestErrorStatus.HTTP_ERROR, error));
    }

    /**
     * <code>HttpErrorException</code>
     * <p>Instantiates a new http error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Throwable
     */
    public HttpErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(resource, filed, value, RestErrorStatus.HTTP_ERROR, error, cause));
    }

    @Override
    public HttpErrorException get() {
        return new HttpErrorException();
    }
}
