package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.XmlErrorException;

/**
 * <code>XmlMarshalException</code>
 * <p>The type xml marshal exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.XmlErrorException
 * @since Jdk1.8
 */
public class XmlMarshalException extends XmlErrorException {
    /**
     * <code>XmlMarshalException</code>
     * <p>Instantiates a new xml marshal exception.</p>
     */
    public XmlMarshalException() {
        super(RestErrorStatus.XML_MARSHAL_ERROR);
    }

    /**
     * <code>XmlMarshalException</code>
     * <p>Instantiates a new xml marshal exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public XmlMarshalException(RestStatus status) {
        super(status);
    }

    /**
     * <code>XmlMarshalException</code>
     * <p>Instantiates a new xml marshal exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlMarshalException(String message) {
        super(RestErrorStatus.XML_MARSHAL_ERROR, message);
    }

    /**
     * <code>XmlMarshalException</code>
     * <p>Instantiates a new xml marshal exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlMarshalException(String resource, String message) {
        super(RestErrorStatus.XML_MARSHAL_ERROR, resource, message);
    }

    @Override
    public XmlMarshalException get() {
        return new XmlMarshalException();
    }
}
