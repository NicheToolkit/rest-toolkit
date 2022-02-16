package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>DataTransformException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataTransformException extends RestErrorException {
    public DataTransformException() {
        super(RestErrorStatus.DATA_TRANSFORM_FAILED);
    }

    public DataTransformException(RestErrorStatus status) {
        super(status);
    }

    public DataTransformException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public DataTransformException(String resource) {
        super(RestErrorStatus.DATA_TRANSFORM_FAILED, RestError.error(resource, RestErrorStatus.DATA_TRANSFORM_FAILED));
    }

    public DataTransformException(String resource, String message) {
        super(RestErrorStatus.DATA_TRANSFORM_FAILED, RestError.error(resource, RestErrorStatus.DATA_TRANSFORM_FAILED, message));
    }

    public DataTransformException(String resource, Object value, String message) {
        super(RestErrorStatus.DATA_TRANSFORM_FAILED, RestError.error(resource, value, RestErrorStatus.DATA_TRANSFORM_FAILED, message));
    }

    @Override
    public DataTransformException get() {
        return new DataTransformException();
    }
}