package io.github.nichetoolkit.rest.error.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.schlichtherle.license.LicenseContentException;
import io.github.nichetoolkit.rest.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * <code>LicenseVerifyException</code>
 * <p>The license verify exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see de.schlichtherle.license.LicenseContentException
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see java.util.function.Supplier
 * @see java.io.Serializable
 * @see lombok.Getter
 * @see lombok.Setter
 * @see lombok.EqualsAndHashCode
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"cause", "stackTrace", "localizedMessage", "suppressed"})
public class LicenseVerifyException extends LicenseContentException implements RestStatus, Supplier<LicenseVerifyException>, Serializable {
    /**
     * <code>error</code>
     * {@link io.github.nichetoolkit.rest.RestError} <p>The <code>error</code> field.</p>
     * @see io.github.nichetoolkit.rest.RestError
     */
    private RestError error;
    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer status;

    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     */
    public LicenseVerifyException() {
        super(LicenseErrorStatus.LICENSE_VERIFY_ERROR.getMessage());
        this.error = RestError.error(LicenseErrorStatus.LICENSE_VERIFY_ERROR);
        this.status = LicenseErrorStatus.LICENSE_VERIFY_ERROR.getStatus();
    }

    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public LicenseVerifyException(Supplier<RestStatus> supplier) {
        super(supplier.get().getMessage());
        this.error = RestError.parser(supplier.get());
        this.status = supplier.get().getStatus();
    }

    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LicenseVerifyException(String message) {
        super(message);
        this.error = RestError.error(LicenseErrorStatus.LICENSE_VERIFY_ERROR);
        this.status = LicenseErrorStatus.LICENSE_VERIFY_ERROR.getStatus();
    }

    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public LicenseVerifyException(Integer status) {
        super(LicenseErrorStatus.LICENSE_VERIFY_ERROR.getMessage());
        this.error = RestError.error(LicenseErrorStatus.LICENSE_VERIFY_ERROR);
        this.status = status;
    }

    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public LicenseVerifyException(RestStatus status) {
        super(status.getMessage());
        this.error = RestError.parser(status);
        this.status = status.getStatus();
    }

    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param error {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     */
    public LicenseVerifyException(RestError error) {
        super(error.getMessage());
        this.error = error;
        this.status = error.getStatus();
    }


    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public LicenseVerifyException(Integer status, String message) {
        super(message);
        this.error = RestError.error(status, message);
        this.status = status;
    }


    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param status     {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public LicenseVerifyException(Integer status, RestStatus restStatus) {
        super(restStatus.getMessage());
        this.error = RestError.parser(status, restStatus);
        this.status = status;
    }

    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestError
     */
    public LicenseVerifyException(Integer status, RestError error) {
        super(error.getMessage());
        this.error = error;
        this.status = status;
    }


    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public LicenseVerifyException(String message, RestStatus status) {
        super(message);
        this.error = RestError.parser(message, status);
        this.status = status.getStatus();
    }

    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param error   {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestError
     */
    public LicenseVerifyException(String message, RestError error) {
        super(message);
        this.error = error;
        this.status = error.getStatus();
    }

    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     */
    public LicenseVerifyException(RestStatus status, RestError error) {
        super(error.getMessage());
        this.status = status.getStatus();
        this.error = error;
    }

    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param error   {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestError
     */
    public LicenseVerifyException(Integer status, String message, RestError error) {
        super(message);
        this.status = status;
        this.error = error;
    }

    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public LicenseVerifyException(RestStatus status, String resource) {
        this(status, RestError.error(resource, status));
    }

    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public LicenseVerifyException(RestStatus status, String resource, String filed) {
        this(status, RestError.error(resource, filed, status));
    }

    /**
     * <code>LicenseVerifyException</code>
     * <p>Instantiates a new license verify exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Object
     */
    public LicenseVerifyException(RestStatus status, String resource, String filed, Object value) {
        this(status, RestError.error(resource, filed, value, status));
    }



    @Override
    public String toString() {
        String errorType = getClass().getSimpleName();
        Integer status = getStatus();
        String message = getLocalizedMessage();
        return (message != null) ? (errorType + " [" + status + "]: " + message) : errorType;
    }

    @Override
    public String name() {
        return Optional.ofNullable(this.error).map(RestError::getName).orElse(this.getClass().getSimpleName());
    }

    @Override
    public LicenseVerifyException get() {
        return new LicenseVerifyException();
    }
}
