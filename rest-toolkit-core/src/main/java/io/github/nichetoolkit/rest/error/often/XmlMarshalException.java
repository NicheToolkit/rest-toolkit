package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.XmlErrorException;

/**
 * <p>XmlMarshalException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class XmlMarshalException extends XmlErrorException {
    public XmlMarshalException() {
        super(RestErrorStatus.XML_MARSHAL_ERROR);
    }

    public XmlMarshalException(RestStatus status) {
        super(status);
    }

    public XmlMarshalException(String message) {
        super(RestErrorStatus.XML_MARSHAL_ERROR, message);
    }

    public XmlMarshalException(String resource, String message) {
        super(RestErrorStatus.XML_MARSHAL_ERROR, resource, message);
    }

    @Override
    public XmlMarshalException get() {
        return new XmlMarshalException();
    }
}
