package io.github.nichetoolkit.rest.error;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ClassErrorException;

/**
 * <code>ClassUnknownException</code>
 * <p>The type class unknown exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ClassErrorException
 * @since Jdk1.8
 */
public class ClassUnknownException extends ClassErrorException {
    /**
     * <code>ClassUnknownException</code>
     * <p>Instantiates a new class unknown exception.</p>
     */
    public ClassUnknownException() {
        super(RestErrorStatus.CLASS_TYPE_UNKNOWN);
    }

    /**
     * <code>ClassUnknownException</code>
     * <p>Instantiates a new class unknown exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ClassUnknownException(String message) {
        super(RestErrorStatus.CLASS_TYPE_UNKNOWN,message);
    }

    /**
     * <code>ClassUnknownException</code>
     * <p>Instantiates a new class unknown exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ClassUnknownException(String resource, String message) {
        super(RestErrorStatus.CLASS_TYPE_UNKNOWN, resource, message);
    }

    /**
     * <code>ClassUnknownException</code>
     * <p>Instantiates a new class unknown exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ClassUnknownException(String resource, String field, String message) {
        super(RestErrorStatus.CLASS_TYPE_UNKNOWN, resource, field, message);
    }

    /**
     * <code>ClassUnknownException</code>
     * <p>Instantiates a new class unknown exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public ClassUnknownException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.CLASS_TYPE_UNKNOWN, resource,field, value, message);
    }

    @Override
    public ClassUnknownException get() {
        return new ClassUnknownException();
    }
}
