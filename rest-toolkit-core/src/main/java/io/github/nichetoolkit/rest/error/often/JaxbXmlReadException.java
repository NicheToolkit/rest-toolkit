package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.JaxbXmlErrorException;

/**
 * <code>JaxbXmlReadException</code>
 * <p>The jaxb xml read exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.JaxbXmlErrorException
 * @since Jdk17
 */
public class JaxbXmlReadException extends JaxbXmlErrorException {
    /**
     * <code>JaxbXmlReadException</code>
     * <p>Instantiates a new jaxb xml read exception.</p>
     */
    public JaxbXmlReadException() {
        super(RestErrorStatus.JAXB_XML_READ_ERROR);
    }

    /**
     * <code>JaxbXmlReadException</code>
     * <p>Instantiates a new jaxb xml read exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JaxbXmlReadException(RestStatus status) {
        super(status);
    }

    /**
     * <code>JaxbXmlReadException</code>
     * <p>Instantiates a new jaxb xml read exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JaxbXmlReadException(String message) {
        super(RestErrorStatus.JAXB_XML_READ_ERROR, message);
    }

    /**
     * <code>JaxbXmlReadException</code>
     * <p>Instantiates a new jaxb xml read exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JaxbXmlReadException(String resource, String message) {
        super(RestErrorStatus.JAXB_XML_READ_ERROR, resource, message);
    }

    @Override
    public JaxbXmlReadException get() {
        return new JaxbXmlReadException();
    }
}
