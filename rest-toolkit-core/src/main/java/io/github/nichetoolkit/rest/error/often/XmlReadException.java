package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.XmlErrorException;

/**
 * <code>XmlReadException</code>
 * <p>The xml read exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.XmlErrorException
 * @since Jdk1.8
 */
public class XmlReadException extends XmlErrorException {
    /**
     * <code>XmlReadException</code>
     * <p>Instantiates a new xml read exception.</p>
     */
    public XmlReadException() {
        super(RestErrorStatus.XML_READ_ERROR);
    }

    /**
     * <code>XmlReadException</code>
     * <p>Instantiates a new xml read exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public XmlReadException(RestStatus status) {
        super(status);
    }

    /**
     * <code>XmlReadException</code>
     * <p>Instantiates a new xml read exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlReadException(String message) {
        super(RestErrorStatus.XML_READ_ERROR, message);
    }

    /**
     * <code>XmlReadException</code>
     * <p>Instantiates a new xml read exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlReadException(String resource, String message) {
        super(RestErrorStatus.XML_READ_ERROR, resource, message);
    }

    @Override
    public XmlReadException get() {
        return new XmlReadException();
    }
}
