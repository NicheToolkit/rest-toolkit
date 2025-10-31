package io.github.nichetoolkit.rest.error.license;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestStatus;

public class LicenseExpiredException extends LicenseErrorException {
    public LicenseExpiredException() {
        super(LicenseErrorStatus.LICENSE_EXPIRED_ERROR);
    }

    public LicenseExpiredException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public LicenseExpiredException(String message) {
        super(LicenseErrorStatus.LICENSE_EXPIRED_ERROR, RestError.error(LicenseErrorStatus.LICENSE_EXPIRED_ERROR, message));
    }

    public LicenseExpiredException(String resource, String message) {
        super(LicenseErrorStatus.LICENSE_EXPIRED_ERROR, RestError.error(resource, LicenseErrorStatus.LICENSE_EXPIRED_ERROR, message));
    }

    @Override
    public LicenseExpiredException get() {
        return new LicenseExpiredException();
    }
}
