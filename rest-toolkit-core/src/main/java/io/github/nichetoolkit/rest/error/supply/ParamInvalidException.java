package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>ParamInvalidException</code>
 * <p>The type param invalid exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class ParamInvalidException extends RestErrorException {

    /**
     * <code>ParamInvalidException</code>
     * <p>Instantiates a new param invalid exception.</p>
     */
    public ParamInvalidException() {
        super(RestErrorStatus.PARAM_INVALID);
    }

    /**
     * <code>ParamInvalidException</code>
     * <p>Instantiates a new param invalid exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ParamInvalidException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ParamInvalidException</code>
     * <p>Instantiates a new param invalid exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ParamInvalidException(String message) {
        super(RestErrorStatus.PARAM_INVALID, RestError.error(RestErrorStatus.PARAM_INVALID,message));
    }

    /**
     * <code>ParamInvalidException</code>
     * <p>Instantiates a new param invalid exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ParamInvalidException( String field, String message) {
        super(RestErrorStatus.PARAM_INVALID, RestError.error(field, RestErrorStatus.PARAM_INVALID, message));
    }

    /**
     * <code>ParamInvalidException</code>
     * <p>Instantiates a new param invalid exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ParamInvalidException(String resource, String field, String message) {
        super(RestErrorStatus.PARAM_INVALID, RestError.error(resource, field, RestErrorStatus.PARAM_INVALID, message));
    }

    @Override
    public ParamInvalidException get() {
        return new ParamInvalidException();
    }
}
