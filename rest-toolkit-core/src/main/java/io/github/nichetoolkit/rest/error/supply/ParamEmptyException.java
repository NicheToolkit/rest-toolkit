package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ParamErrorException;

/**
 * <code>ParamEmptyException</code>
 * <p>The param empty exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ParamErrorException
 * @since Jdk1.8
 */
public class ParamEmptyException extends ParamErrorException {

    /**
     * <code>ParamEmptyException</code>
     * <p>Instantiates a new param empty exception.</p>
     */
    public ParamEmptyException() {
        super(RestErrorStatus.PARAM_EMPTY);
    }

    /**
     * <code>ParamEmptyException</code>
     * <p>Instantiates a new param empty exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ParamEmptyException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ParamEmptyException</code>
     * <p>Instantiates a new param empty exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ParamEmptyException(String message) {
        super(RestErrorStatus.PARAM_EMPTY, RestError.error(RestErrorStatus.PARAM_EMPTY,message));
    }

    /**
     * <code>ParamEmptyException</code>
     * <p>Instantiates a new param empty exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ParamEmptyException(String field, String message) {
        super(RestErrorStatus.PARAM_EMPTY, RestError.error(field, RestErrorStatus.PARAM_EMPTY, message));
    }

    /**
     * <code>ParamEmptyException</code>
     * <p>Instantiates a new param empty exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ParamEmptyException(String resource, String field, String message) {
        super(RestErrorStatus.PARAM_EMPTY, RestError.error(resource, field, RestErrorStatus.PARAM_EMPTY, message));
    }

    @Override
    public ParamEmptyException get() {
        return new ParamEmptyException();
    }
}
