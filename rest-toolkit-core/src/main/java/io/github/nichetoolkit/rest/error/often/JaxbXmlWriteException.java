package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.JaxbXmlErrorException;

/**
 * <code>JaxbXmlWriteException</code>
 * <p>The jaxb xml write exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.JaxbXmlErrorException
 * @since Jdk1.8
 */
public class JaxbXmlWriteException extends JaxbXmlErrorException {
    /**
     * <code>JaxbXmlWriteException</code>
     * <p>Instantiates a new jaxb xml write exception.</p>
     */
    public JaxbXmlWriteException() {
        super(RestErrorStatus.JAXB_XML_WRITE_ERROR);
    }

    /**
     * <code>JaxbXmlWriteException</code>
     * <p>Instantiates a new jaxb xml write exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JaxbXmlWriteException(RestStatus status) {
        super(status);
    }

    /**
     * <code>JaxbXmlWriteException</code>
     * <p>Instantiates a new jaxb xml write exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JaxbXmlWriteException(String message) {
        super(RestErrorStatus.JAXB_XML_WRITE_ERROR, message);
    }

    /**
     * <code>JaxbXmlWriteException</code>
     * <p>Instantiates a new jaxb xml write exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JaxbXmlWriteException(String resource, String message) {
        super(RestErrorStatus.JAXB_XML_WRITE_ERROR, resource, message);
    }

    @Override
    public JaxbXmlWriteException get() {
        return new JaxbXmlWriteException();
    }
}
