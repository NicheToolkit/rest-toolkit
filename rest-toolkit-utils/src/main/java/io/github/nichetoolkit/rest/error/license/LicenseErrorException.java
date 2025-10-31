package io.github.nichetoolkit.rest.error.license;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

public class LicenseErrorException extends RestErrorException {

    public LicenseErrorException() {
        super(LicenseErrorStatus.LICENSE_ERROR);
    }

    public LicenseErrorException(LicenseErrorStatus status) {
        super(status);
    }

    public LicenseErrorException(String error) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(LicenseErrorStatus.LICENSE_ERROR, error));
    }

    public LicenseErrorException(String error, Throwable cause) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(LicenseErrorStatus.LICENSE_ERROR, error, cause), cause);
    }

    public LicenseErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public LicenseErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public LicenseErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public LicenseErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }


    public LicenseErrorException(RestStatus status, String resource) {
        super(status, RestError.error(resource, status));
    }

    public LicenseErrorException(RestStatus status, String resource, Throwable cause) {
        super(status, RestError.error(resource, status, cause), cause);
    }

    public LicenseErrorException(RestStatus status, String resource, String filed) {
        super(status, RestError.error(resource, filed, status));
    }

    public LicenseErrorException(RestStatus status, String resource, String filed, Throwable cause) {
        super(status, RestError.error(resource, filed, status, cause), cause);
    }

    public LicenseErrorException(RestStatus status, String resource, String filed, Object value) {
        super(status, RestError.error(resource, filed, value, status));
    }

    public LicenseErrorException(RestStatus status, String resource, String filed, Object value, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, cause), cause);
    }

    public LicenseErrorException(String resource, String error) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(resource, LicenseErrorStatus.LICENSE_ERROR, error));
    }

    public LicenseErrorException(String resource, String error, Throwable cause) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(resource, LicenseErrorStatus.LICENSE_ERROR, error, cause), cause);
    }

    public LicenseErrorException(String resource, String filed, String error) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(resource, filed, LicenseErrorStatus.LICENSE_ERROR, error));
    }

    public LicenseErrorException(String resource, String filed, String error, Throwable cause) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(resource, filed, LicenseErrorStatus.LICENSE_ERROR, error, cause), cause);
    }

    public LicenseErrorException(String resource, String filed, Object value, String error) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(resource, filed, value, LicenseErrorStatus.LICENSE_ERROR, error));
    }

    public LicenseErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(resource, filed, value, LicenseErrorStatus.LICENSE_ERROR, error, cause));
    }

    @Override
    public LicenseErrorException get() {
        return new LicenseErrorException();
    }
}
