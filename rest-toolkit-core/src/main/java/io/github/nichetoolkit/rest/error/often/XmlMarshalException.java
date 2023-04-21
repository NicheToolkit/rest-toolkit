package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>XmlMarshalException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class XmlMarshalException extends RestErrorException {
    public XmlMarshalException() {
        super(RestErrorStatus.XML_MARSHAL_ERROR);
    }

    public XmlMarshalException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public XmlMarshalException(String message) {
        super(RestErrorStatus.XML_MARSHAL_ERROR, RestError.error(RestErrorStatus.XML_MARSHAL_ERROR, message));
    }

    public XmlMarshalException(String resource, String message) {
        super(RestErrorStatus.XML_MARSHAL_ERROR, RestError.error(resource, RestErrorStatus.XML_MARSHAL_ERROR, message));
    }

    @Override
    public XmlMarshalException get() {
        return new XmlMarshalException();
    }
}
