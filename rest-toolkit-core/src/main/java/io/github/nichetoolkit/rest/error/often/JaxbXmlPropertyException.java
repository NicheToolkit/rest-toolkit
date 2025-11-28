package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.JaxbXmlErrorException;

/**
 * <code>JaxbXmlPropertyException</code>
 * <p>The jaxb xml property exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.JaxbXmlErrorException
 * @since Jdk17
 */
public class JaxbXmlPropertyException extends JaxbXmlErrorException {
    /**
     * <code>JaxbXmlPropertyException</code>
     * <p>Instantiates a new jaxb xml property exception.</p>
     */
    public JaxbXmlPropertyException() {
        super(RestErrorStatus.JAXB_XML_PROPERTY_ERROR);
    }

    /**
     * <code>JaxbXmlPropertyException</code>
     * <p>Instantiates a new jaxb xml property exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JaxbXmlPropertyException(RestStatus status) {
        super(status);
    }

    /**
     * <code>JaxbXmlPropertyException</code>
     * <p>Instantiates a new jaxb xml property exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JaxbXmlPropertyException(String message) {
        super(RestErrorStatus.JAXB_XML_PROPERTY_ERROR, message);
    }

    /**
     * <code>JaxbXmlPropertyException</code>
     * <p>Instantiates a new jaxb xml property exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JaxbXmlPropertyException(String resource, String message) {
        super(RestErrorStatus.JAXB_XML_PROPERTY_ERROR, resource, message);
    }

    @Override
    public JaxbXmlPropertyException get() {
        return new JaxbXmlPropertyException();
    }
}
