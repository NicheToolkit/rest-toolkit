package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>FieldInvalidException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ParamInvalidException extends RestErrorException {

    public ParamInvalidException() {
        super(RestErrorStatus.PARAM_INVALID);
    }

    public ParamInvalidException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ParamInvalidException(String message) {
        super(RestErrorStatus.PARAM_INVALID, RestError.error(RestErrorStatus.PARAM_INVALID,message));
    }

    public ParamInvalidException(String message, String field) {
        super(RestErrorStatus.PARAM_INVALID, RestError.error(field, RestErrorStatus.PARAM_INVALID, message));
    }

    public ParamInvalidException(String message, String resource, String field, String value) {
        super(RestErrorStatus.PARAM_INVALID, RestError.error(resource, field, value, RestErrorStatus.PARAM_INVALID,message));
    }

    @Override
    public ParamInvalidException get() {
        return new ParamInvalidException();
    }
}
