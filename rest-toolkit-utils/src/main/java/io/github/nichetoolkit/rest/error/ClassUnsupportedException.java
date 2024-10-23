package io.github.nichetoolkit.rest.error;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ClassErrorException;

/**
 * <code>ClassUnsupportedException</code>
 * <p>The class unsupported exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ClassErrorException
 * @since Jdk1.8
 */
public class ClassUnsupportedException extends ClassErrorException {
    /**
     * <code>ClassUnsupportedException</code>
     * <p>Instantiates a new class unsupported exception.</p>
     */
    public ClassUnsupportedException() {
        super(RestErrorStatus.CLASS_TYPE_UNSUPPORTED);
    }

    /**
     * <code>ClassUnsupportedException</code>
     * <p>Instantiates a new class unsupported exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ClassUnsupportedException(String message) {
        super(RestErrorStatus.CLASS_TYPE_UNSUPPORTED,message);
    }


    /**
     * <code>ClassUnsupportedException</code>
     * <p>Instantiates a new class unsupported exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ClassUnsupportedException(String resource, String message) {
        super(RestErrorStatus.CLASS_TYPE_UNSUPPORTED, resource, message);
    }

    /**
     * <code>ClassUnsupportedException</code>
     * <p>Instantiates a new class unsupported exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ClassUnsupportedException(String resource, String field, String message) {
        super(RestErrorStatus.CLASS_TYPE_UNSUPPORTED, resource, field, message);
    }

    /**
     * <code>ClassUnsupportedException</code>
     * <p>Instantiates a new class unsupported exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public ClassUnsupportedException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.CLASS_TYPE_UNSUPPORTED, resource,field, value, message);
    }

    @Override
    public ClassUnsupportedException get() {
        return new ClassUnsupportedException();
    }
}
