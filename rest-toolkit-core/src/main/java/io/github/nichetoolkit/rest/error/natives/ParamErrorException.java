package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.RestErrorException;

/**
 * <p>ParamException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ParamErrorException extends RestErrorException {

    public ParamErrorException() {
        super(RestErrorStatus.PARAM_ERROR);
    }

    public ParamErrorException(RestErrorStatus status) {
        super(status);
    }

    public ParamErrorException(String error) {
        super(RestErrorStatus.PARAM_ERROR, RestError.error(RestErrorStatus.PARAM_ERROR,error));
    }

    public ParamErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ParamErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public ParamErrorException(RestStatus status, String error) {
        super(status,RestError.error(status, error));
    }

    public ParamErrorException(String param, String error) {
        super(RestErrorStatus.PARAM_ERROR, RestError.error(param, RestErrorStatus.PARAM_ERROR, error));
    }

    public ParamErrorException(String param, Object value, String error) {
        super(RestErrorStatus.PARAM_ERROR, RestError.error(param, value, RestErrorStatus.PARAM_ERROR, error));
    }

    public ParamErrorException(String param, RestStatus status) {
        super(status, RestError.error(param, status));
    }

    public ParamErrorException(String param, Object value, RestStatus status) {
        super(status, RestError.error(param, value, status));
    }

    @Override
    public ParamErrorException get() {
        return new ParamErrorException();
    }

}
