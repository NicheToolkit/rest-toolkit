package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>XmlDeserializeException</code>
 * <p>The xml deserialize exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk17
 */
public class XmlDeserializeException extends RestErrorException {
    /**
     * <code>XmlDeserializeException</code>
     * <p>Instantiates a new xml deserialize exception.</p>
     */
    public XmlDeserializeException() {
        super(RestErrorStatus.XML_DESERIALIZE_ERROR);
    }

    /**
     * <code>XmlDeserializeException</code>
     * <p>Instantiates a new xml deserialize exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public XmlDeserializeException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>XmlDeserializeException</code>
     * <p>Instantiates a new xml deserialize exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlDeserializeException(String message) {
        super(RestErrorStatus.XML_DESERIALIZE_ERROR, RestError.error(RestErrorStatus.XML_DESERIALIZE_ERROR, message));
    }

    @Override
    public XmlDeserializeException get() {
        return new XmlDeserializeException();
    }
}
