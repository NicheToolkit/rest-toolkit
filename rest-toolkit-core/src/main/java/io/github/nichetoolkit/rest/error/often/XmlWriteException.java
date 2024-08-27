package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.XmlErrorException;

/**
 * <code>XmlWriteException</code>
 * <p>The type xml write exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.XmlErrorException
 * @since Jdk1.8
 */
public class XmlWriteException extends XmlErrorException {
    /**
     * <code>XmlWriteException</code>
     * Instantiates a new xml write exception.
     */
    public XmlWriteException() {
        super(RestErrorStatus.XML_WRITE_ERROR);
    }

    /**
     * <code>XmlWriteException</code>
     * Instantiates a new xml write exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public XmlWriteException(RestStatus status) {
        super(status);
    }

    /**
     * <code>XmlWriteException</code>
     * Instantiates a new xml write exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlWriteException(String message) {
        super(RestErrorStatus.XML_WRITE_ERROR, message);
    }

    /**
     * <code>XmlWriteException</code>
     * Instantiates a new xml write exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlWriteException(String resource, String message) {
        super(RestErrorStatus.XML_WRITE_ERROR, resource, message);
    }

    @Override
    public XmlWriteException get() {
        return new XmlWriteException();
    }
}
