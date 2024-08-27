package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>JsonDeserializeException</code>
 * <p>The type json deserialize exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class JsonDeserializeException extends RestErrorException {
    /**
     * <code>JsonDeserializeException</code>
     * Instantiates a new json deserialize exception.
     */
    public JsonDeserializeException() {
        super(RestErrorStatus.JSON_DESERIALIZE_ERROR);
    }

    /**
     * <code>JsonDeserializeException</code>
     * Instantiates a new json deserialize exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JsonDeserializeException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>JsonDeserializeException</code>
     * Instantiates a new json deserialize exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonDeserializeException(String message) {
        super(RestErrorStatus.JSON_DESERIALIZE_ERROR, RestError.error(RestErrorStatus.JSON_DESERIALIZE_ERROR, message));
    }

    @Override
    public JsonDeserializeException get() {
        return new JsonDeserializeException();
    }
}
