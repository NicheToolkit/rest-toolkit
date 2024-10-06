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
     * <p>Instantiates a new xml write exception.</p>
     */
    public XmlWriteException() {
        super(RestErrorStatus.XML_WRITE_ERROR);
    }

    /**
     * <code>XmlWriteException</code>
     * <p>Instantiates a new xml write exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public XmlWriteException(RestStatus status) {
        super(status);
    }

    /**
     * <code>XmlWriteException</code>
     * <p>Instantiates a new xml write exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlWriteException(String message) {
        super(RestErrorStatus.XML_WRITE_ERROR, message);
    }

    /**
     * <code>XmlWriteException</code>
     * <p>Instantiates a new xml write exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
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
