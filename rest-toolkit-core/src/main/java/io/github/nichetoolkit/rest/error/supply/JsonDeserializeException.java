package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>JsonDeserializeException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class JsonDeserializeException extends RestErrorException {
    public JsonDeserializeException() {
        super(RestErrorStatus.JSON_DESERIALIZE_ERROR);
    }

    public JsonDeserializeException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public JsonDeserializeException(String message) {
        super(RestErrorStatus.JSON_DESERIALIZE_ERROR, RestError.error(RestErrorStatus.JSON_DESERIALIZE_ERROR, message));
    }

    @Override
    public JsonDeserializeException get() {
        return new JsonDeserializeException();
    }
}
