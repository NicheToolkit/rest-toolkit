package io.github.nichetoolkit.rest.error;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ClassErrorException;

/**
 * <code>ClassUnrenewException</code>
 * <p>The type class unrenew exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ClassErrorException
 * @since Jdk1.8
 */
public class ClassUnrenewException extends ClassErrorException {
    /**
     * <code>ClassUnrenewException</code>
     * Instantiates a new class unrenew exception.
     */
    public ClassUnrenewException() {
        super(RestErrorStatus.CLASS_TYPE_UNRENEW);
    }

    /**
     * <code>ClassUnrenewException</code>
     * Instantiates a new class unrenew exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ClassUnrenewException(String message) {
        super(RestErrorStatus.CLASS_TYPE_UNRENEW,message);
    }

    /**
     * <code>ClassUnrenewException</code>
     * Instantiates a new class unrenew exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ClassUnrenewException(String resource, String message) {
        super(RestErrorStatus.CLASS_TYPE_UNRENEW, resource, message);
    }

    /**
     * <code>ClassUnrenewException</code>
     * Instantiates a new class unrenew exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ClassUnrenewException(String resource, String field, String message) {
        super(RestErrorStatus.CLASS_TYPE_UNRENEW, resource, field, message);
    }

    /**
     * <code>ClassUnrenewException</code>
     * Instantiates a new class unrenew exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public ClassUnrenewException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.CLASS_TYPE_UNRENEW, resource,field, value, message);
    }

    @Override
    public ClassUnrenewException get() {
        return new ClassUnrenewException();
    }
}
