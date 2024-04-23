package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.XmlErrorException;

/**
 * <p>XmlWriteException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class XmlWriteException extends XmlErrorException {
    public XmlWriteException() {
        super(RestErrorStatus.XML_WRITE_ERROR);
    }

    public XmlWriteException(RestStatus status) {
        super(status);
    }

    public XmlWriteException(String message) {
        super(RestErrorStatus.XML_WRITE_ERROR, message);
    }

    public XmlWriteException(String resource, String message) {
        super(RestErrorStatus.XML_WRITE_ERROR, resource, message);
    }

    @Override
    public XmlWriteException get() {
        return new XmlWriteException();
    }
}
