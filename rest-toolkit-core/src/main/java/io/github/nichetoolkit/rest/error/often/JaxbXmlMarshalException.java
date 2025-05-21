package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.JaxbXmlErrorException;

/**
 * <code>JaxbXmlMarshalException</code>
 * <p>The jaxb xml marshal exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.JaxbXmlErrorException
 * @since Jdk1.8
 */
public class JaxbXmlMarshalException extends JaxbXmlErrorException {
    /**
     * <code>JaxbXmlMarshalException</code>
     * <p>Instantiates a new jaxb xml marshal exception.</p>
     */
    public JaxbXmlMarshalException() {
        super(RestErrorStatus.JAXB_XML_MARSHAL_ERROR);
    }

    /**
     * <code>JaxbXmlMarshalException</code>
     * <p>Instantiates a new jaxb xml marshal exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JaxbXmlMarshalException(RestStatus status) {
        super(status);
    }

    /**
     * <code>JaxbXmlMarshalException</code>
     * <p>Instantiates a new jaxb xml marshal exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JaxbXmlMarshalException(String message) {
        super(RestErrorStatus.JAXB_XML_MARSHAL_ERROR, message);
    }

    /**
     * <code>JaxbXmlMarshalException</code>
     * <p>Instantiates a new jaxb xml marshal exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JaxbXmlMarshalException(String resource, String message) {
        super(RestErrorStatus.JAXB_XML_MARSHAL_ERROR, resource, message);
    }

    @Override
    public JaxbXmlMarshalException get() {
        return new JaxbXmlMarshalException();
    }
}
