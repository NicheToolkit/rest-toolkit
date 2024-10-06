package io.github.nichetoolkit.rest.error.network;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.RestStatus;
import org.springframework.http.ResponseEntity;

/**
 * <code>HttpResultFailException</code>
 * <p>The type http result fail exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
 * @since Jdk1.8
 */
public class HttpResultFailException extends HttpErrorException {

    /**
     * <code>HttpResultFailException</code>
     * <p>Instantiates a new http result fail exception.</p>
     */
    public HttpResultFailException() {
        super(RestErrorStatus.HTTP_RESULT_FAILED);
    }

    /**
     * <code>HttpResultFailException</code>
     * <p>Instantiates a new http result fail exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public HttpResultFailException(RestStatus status) {
        super(status);
    }

    /**
     * <code>HttpResultFailException</code>
     * <p>Instantiates a new http result fail exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public HttpResultFailException(String message) {
        super(RestErrorStatus.HTTP_RESULT_FAILED, message);
    }

    /**
     * <code>HttpResultFailException</code>
     * <p>Instantiates a new http result fail exception.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public HttpResultFailException(Integer status, String message) {
        super(status, message);
    }

    /**
     * <code>HttpResultFailException</code>
     * <p>Instantiates a new http result fail exception.</p>
     * @param status   {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public HttpResultFailException(Integer status, String resource, String message) {
        super(status, resource, message);
    }

    /**
     * <code>HttpResultFailException</code>
     * <p>Instantiates a new http result fail exception.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public HttpResultFailException(Integer status, String message, Throwable cause) {
        super(status, message, cause);
    }

    /**
     * <code>HttpResultFailException</code>
     * <p>Instantiates a new http result fail exception.</p>
     * @param status   {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public HttpResultFailException(Integer status, String resource, String message, Throwable cause) {
        super(status, resource, message, cause);
    }

    /**
     * <code>HttpResultFailException</code>
     * <p>Instantiates a new http result fail exception.</p>
     * @param restResult {@link io.github.nichetoolkit.rest.RestResult} <p>The rest result parameter is <code>RestResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     */
    public HttpResultFailException(RestResult<?> restResult) {
        super(restResult.getStatus(), restResult.getMessage(), restResult.getError());
    }

    /**
     * <code>HttpResultFailException</code>
     * <p>Instantiates a new http result fail exception.</p>
     * @param responseEntity {@link org.springframework.http.ResponseEntity} <p>The response entity parameter is <code>ResponseEntity</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     */
    public HttpResultFailException(ResponseEntity<?> responseEntity) {
        super(responseEntity.getStatusCodeValue(), responseEntity.toString());
    }

    /**
     * <code>HttpResultFailException</code>
     * <p>Instantiates a new http result fail exception.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param restResult {@link io.github.nichetoolkit.rest.RestResult} <p>The rest result parameter is <code>RestResult</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestResult
     */
    public HttpResultFailException(String resource, RestResult<?> restResult) {
        super(restResult.getStatus(), resource, restResult.getMessage(), restResult.getError());
    }

    /**
     * <code>HttpResultFailException</code>
     * <p>Instantiates a new http result fail exception.</p>
     * @param resource       {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param responseEntity {@link org.springframework.http.ResponseEntity} <p>The response entity parameter is <code>ResponseEntity</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.ResponseEntity
     */
    public HttpResultFailException(String resource, ResponseEntity<?> responseEntity) {
        super(responseEntity.getStatusCodeValue(), resource, responseEntity.toString());
    }

    @Override
    public HttpResultFailException get() {
        return new HttpResultFailException();
    }
}
