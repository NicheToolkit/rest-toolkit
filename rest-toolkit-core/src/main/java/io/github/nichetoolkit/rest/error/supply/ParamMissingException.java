package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>ParamMissingException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ParamMissingException extends RestErrorException {

    public ParamMissingException() {
        super(RestErrorStatus.PARAM_MISSING);
    }

    public ParamMissingException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ParamMissingException(String message) {
        super(RestErrorStatus.PARAM_MISSING, RestError.error(RestErrorStatus.PARAM_MISSING,message));
    }

    public ParamMissingException(String message,String field) {
        super(RestErrorStatus.PARAM_MISSING, RestError.error(field, RestErrorStatus.PARAM_MISSING,message));
    }

    public ParamMissingException(String message, String resource, String field) {
        super(RestErrorStatus.PARAM_MISSING, RestError.error(resource, field, RestErrorStatus.PARAM_MISSING,message));
    }

    @Override
    public ParamMissingException get() {
        return new ParamMissingException();
    }
}
