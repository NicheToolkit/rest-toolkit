package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>ParamMissingException</code>
 * <p>The type param missing exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class ParamMissingException extends RestErrorException {

    /**
     * <code>ParamMissingException</code>
     * Instantiates a new param missing exception.
     */
    public ParamMissingException() {
        super(RestErrorStatus.PARAM_MISSING);
    }

    /**
     * <code>ParamMissingException</code>
     * Instantiates a new param missing exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ParamMissingException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ParamMissingException</code>
     * Instantiates a new param missing exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ParamMissingException(String message) {
        super(RestErrorStatus.PARAM_MISSING, RestError.error(RestErrorStatus.PARAM_MISSING, message));
    }

    /**
     * <code>ParamMissingException</code>
     * Instantiates a new param missing exception.
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ParamMissingException(String field, String message) {
        super(RestErrorStatus.PARAM_MISSING, RestError.error(field, RestErrorStatus.PARAM_MISSING, message));
    }

    /**
     * <code>ParamMissingException</code>
     * Instantiates a new param missing exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ParamMissingException(String resource, String field, String message) {
        super(RestErrorStatus.PARAM_MISSING, RestError.error(resource, field, RestErrorStatus.PARAM_MISSING, message));
    }

    @Override
    public ParamMissingException get() {
        return new ParamMissingException();
    }
}
