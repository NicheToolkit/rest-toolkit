package io.github.nichetoolkit.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * <code>RestExceptionResolver</code>
 * <p>The rest exception resolver interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestExceptionResolver {

    /**
     * <code>restExceptionResult</code>
     * <p>The rest exception result method.</p>
     * @param exception {@link io.github.nichetoolkit.rest.RestException} <p>The exception parameter is <code>RestException</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.http.ResponseEntity
     * @return  {@link org.springframework.http.ResponseEntity} <p>The rest exception result return object is <code>ResponseEntity</code> type.</p>
     */
    default ResponseEntity<Object> restExceptionResult(RestException exception, String message) {
        return ResponseEntity.ok(exception.buildResult(message));
    }

    /**
     * <code>restExceptionResult</code>
     * <p>The rest exception result method.</p>
     * @param exception {@link io.github.nichetoolkit.rest.RestException} <p>The exception parameter is <code>RestException</code> type.</p>
     * @return  {@link org.springframework.http.ResponseEntity} <p>The rest exception result return object is <code>ResponseEntity</code> type.</p>
     * @see  org.springframework.http.ResponseEntity
     */
    default ResponseEntity<Object> restExceptionResult(RestException exception) {
        return ResponseEntity.ok(exception.buildResult());
    }

    /**
     * <code>bindExceptionResult</code>
     * <p>The bind exception result method.</p>
     * @param exception {@link org.springframework.validation.BindException} <p>The exception parameter is <code>BindException</code> type.</p>
     * @see  org.springframework.validation.BindException
     * @see  org.springframework.http.ResponseEntity
     * @return  {@link org.springframework.http.ResponseEntity} <p>The bind exception result return object is <code>ResponseEntity</code> type.</p>
     */
    default ResponseEntity<Object> bindExceptionResult(BindException exception) {
        return ResponseEntity.ok(RestError.error(RestErrorStatus.BIND_ERROR,exception).buildResult());
    }

    /**
     * <code>unrecognizedRestStatusResult</code>
     * <p>The unrecognized rest status result method.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestStatus
     * @see  org.springframework.http.ResponseEntity
     * @return  {@link org.springframework.http.ResponseEntity} <p>The unrecognized rest status result return object is <code>ResponseEntity</code> type.</p>
     */
    default ResponseEntity<Object> unrecognizedRestStatusResult(RestStatus restStatus) {
        return ResponseEntity.ok(restStatus.buildResult());
    }

    /**
     * <code>unrecognizedErrorsResult</code>
     * <p>The unrecognized errors result method.</p>
     * @param errors {@link org.springframework.validation.Errors} <p>The errors parameter is <code>Errors</code> type.</p>
     * @see  org.springframework.validation.Errors
     * @see  org.springframework.http.ResponseEntity
     * @return  {@link org.springframework.http.ResponseEntity} <p>The unrecognized errors result return object is <code>ResponseEntity</code> type.</p>
     */
    default ResponseEntity<Object> unrecognizedErrorsResult(Errors errors) {
        return ResponseEntity.ok(RestError.error(RestErrorStatus.BIND_ERROR,errors).buildResult());
    }

    /**
     * <code>unrecognizedExceptionResult</code>
     * <p>The unrecognized exception result method.</p>
     * @param exception {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @see  java.lang.Exception
     * @see  org.springframework.http.ResponseEntity
     * @return  {@link org.springframework.http.ResponseEntity} <p>The unrecognized exception result return object is <code>ResponseEntity</code> type.</p>
     */
    default ResponseEntity<Object> unrecognizedExceptionResult(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResult.mistake(RestErrorStatus.UNKNOWN_ERROR, exception));
    }

}
