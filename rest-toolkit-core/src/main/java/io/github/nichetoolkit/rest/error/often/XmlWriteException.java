package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>XmlWriteException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class XmlWriteException extends RestErrorException {
    public XmlWriteException() {
        super(RestErrorStatus.XML_WRITE_ERROR);
    }

    public XmlWriteException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public XmlWriteException(String message) {
        super(RestErrorStatus.XML_WRITE_ERROR, RestError.error(RestErrorStatus.XML_WRITE_ERROR, message));
    }

    public XmlWriteException(String resource, String message) {
        super(RestErrorStatus.XML_WRITE_ERROR, RestError.error(resource, RestErrorStatus.XML_WRITE_ERROR, message));
    }

    @Override
    public XmlWriteException get() {
        return new XmlWriteException();
    }
}
