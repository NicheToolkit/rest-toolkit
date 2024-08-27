package io.github.nichetoolkit.rest.identity.error;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>IdentityException</code>
 * <p>The type identity exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestException
 * @since Jdk1.8
 */
public class IdentityException extends RestException {
    /**
     * <code>IdentityException</code>
     * Instantiates a new identity exception.
     */
    public IdentityException() {
        super(RestErrorStatus.UNKNOWN_ERROR);
    }

    /**
     * <code>IdentityException</code>
     * Instantiates a new identity exception.
     * @param supplier {@link java.util.function.Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public IdentityException(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>IdentityException</code>
     * Instantiates a new identity exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IdentityException(String message) {
        super(message);
    }

    /**
     * <code>IdentityException</code>
     * Instantiates a new identity exception.
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public IdentityException(Integer status) {
        super(status);
    }

    /**
     * <code>IdentityException</code>
     * Instantiates a new identity exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public IdentityException(RestStatus status) {
        super(status);
    }

    /**
     * <code>IdentityException</code>
     * Instantiates a new identity exception.
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public IdentityException(Integer status, String message) {
        super(status, message);
    }

    /**
     * <code>IdentityException</code>
     * Instantiates a new identity exception.
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public IdentityException(Integer status, RestStatus restStatus) {
        super(status, restStatus);
    }

    /**
     * <code>IdentityException</code>
     * Instantiates a new identity exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public IdentityException(String message, RestStatus status) {
        super(message, status);
    }

    @Override
    public IdentityException get() {
        return new IdentityException();
    }
}
