package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <p>DataTransformException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataTransformException extends DataErrorException {
    public DataTransformException() {
        super(RestErrorStatus.DATA_TRANSFORM_FAILED);
    }

    public DataTransformException(RestErrorStatus status) {
        super(status);
    }

    public DataTransformException(RestStatus status) {
        super(status);
    }

    public DataTransformException(String message) {
        super(RestErrorStatus.DATA_TRANSFORM_FAILED, message);
    }

    public DataTransformException(String resource, String message) {
        super(RestErrorStatus.DATA_TRANSFORM_FAILED, resource, message);
    }

    public DataTransformException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_TRANSFORM_FAILED, resource, field, value, message);
    }

    @Override
    public DataTransformException get() {
        return new DataTransformException();
    }
}