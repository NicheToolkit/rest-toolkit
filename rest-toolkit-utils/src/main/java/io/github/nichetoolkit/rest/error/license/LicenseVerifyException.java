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

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"cause", "stackTrace", "localizedMessage", "suppressed"})
public class LicenseVerifyException extends LicenseContentException implements RestStatus, Supplier<LicenseVerifyException>, Serializable {
    private RestError error;
    private Integer status;

    public LicenseVerifyException() {
        super(LicenseErrorStatus.LICENSE_VERIFY_ERROR.getMessage());
        this.error = RestError.error(LicenseErrorStatus.LICENSE_VERIFY_ERROR);
        this.status = LicenseErrorStatus.LICENSE_VERIFY_ERROR.getStatus();
    }

    public LicenseVerifyException(Supplier<RestStatus> supplier) {
        super(supplier.get().getMessage());
        this.error = RestError.parser(supplier.get());
        this.status = supplier.get().getStatus();
    }

    public LicenseVerifyException(String message) {
        super(message);
        this.error = RestError.error(LicenseErrorStatus.LICENSE_VERIFY_ERROR);
        this.status = LicenseErrorStatus.LICENSE_VERIFY_ERROR.getStatus();
    }

    public LicenseVerifyException(Integer status) {
        super(LicenseErrorStatus.LICENSE_VERIFY_ERROR.getMessage());
        this.error = RestError.error(LicenseErrorStatus.LICENSE_VERIFY_ERROR);
        this.status = status;
    }

    public LicenseVerifyException(RestStatus status) {
        super(status.getMessage());
        this.error = RestError.parser(status);
        this.status = status.getStatus();
    }

    public LicenseVerifyException(RestError error) {
        super(error.getMessage());
        this.error = error;
        this.status = error.getStatus();
    }


    public LicenseVerifyException(Integer status, String message) {
        super(message);
        this.error = RestError.error(status, message);
        this.status = status;
    }


    public LicenseVerifyException(Integer status, RestStatus restStatus) {
        super(restStatus.getMessage());
        this.error = RestError.parser(status, restStatus);
        this.status = status;
    }

    public LicenseVerifyException(Integer status, RestError error) {
        super(error.getMessage());
        this.error = error;
        this.status = status;
    }


    public LicenseVerifyException(String message, RestStatus status) {
        super(message);
        this.error = RestError.parser(message, status);
        this.status = status.getStatus();
    }

    public LicenseVerifyException(String message, RestError error) {
        super(message);
        this.error = error;
        this.status = error.getStatus();
    }

    public LicenseVerifyException(RestStatus status, RestError error) {
        super(error.getMessage());
        this.status = status.getStatus();
        this.error = error;
    }

    public LicenseVerifyException(Integer status, String message, RestError error) {
        super(message);
        this.status = status;
        this.error = error;
    }

    public LicenseVerifyException(RestStatus status, String resource) {
        this(status, RestError.error(resource, status));
    }

    public LicenseVerifyException(RestStatus status, String resource, String filed) {
        this(status, RestError.error(resource, filed, status));
    }

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
