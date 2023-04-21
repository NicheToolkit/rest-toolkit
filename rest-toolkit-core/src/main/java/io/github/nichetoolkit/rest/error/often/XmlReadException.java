package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>XmlReadException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class XmlReadException extends RestErrorException {
    public XmlReadException() {
        super(RestErrorStatus.XML_READ_ERROR);
    }

    public XmlReadException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public XmlReadException(String message) {
        super(RestErrorStatus.XML_READ_ERROR, RestError.error(RestErrorStatus.XML_READ_ERROR, message));
    }

    public XmlReadException(String resource, String message) {
        super(RestErrorStatus.XML_READ_ERROR, RestError.error(resource, RestErrorStatus.XML_READ_ERROR, message));
    }

    @Override
    public XmlReadException get() {
        return new XmlReadException();
    }
}
