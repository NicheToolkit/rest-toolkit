package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.XmlErrorException;

/**
 * <p>XmlReadException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class XmlReadException extends XmlErrorException {
    public XmlReadException() {
        super(RestErrorStatus.XML_READ_ERROR);
    }

    public XmlReadException(RestStatus status) {
        super(status);
    }

    public XmlReadException(String message) {
        super(RestErrorStatus.XML_READ_ERROR, message);
    }

    public XmlReadException(String resource, String message) {
        super(RestErrorStatus.XML_READ_ERROR, resource, message);
    }

    @Override
    public XmlReadException get() {
        return new XmlReadException();
    }
}
