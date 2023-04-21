package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>StreamErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class XmlErrorException extends RestErrorException {

    public XmlErrorException() {
        super(RestErrorStatus.XML_ERROR);
    }

    public XmlErrorException(RestErrorStatus status) {
        super(status);
    }

    public XmlErrorException(String error) {
        super(RestErrorStatus.XML_ERROR, RestError.error(RestErrorStatus.XML_ERROR, error));
    }

    public XmlErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public XmlErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public XmlErrorException(RestStatus status, String error) {
        super(status, RestError.error(status, error));
    }

    public XmlErrorException(String resource, String error) {
        super(RestErrorStatus.XML_ERROR, RestError.error( resource, RestErrorStatus.XML_ERROR, error));
    }

    public XmlErrorException(String resource, RestStatus status) {
        super(status, RestError.error(resource, status));
    }

    public XmlErrorException(String service, RestStatus status, String error) {
        super(status, RestError.error( service, status, error));
    }

    public XmlErrorException(String resource, String service, RestStatus status) {
        super(status, RestError.error(resource, service, status));
    }

    public XmlErrorException(String resource, String service, String error) {
        super(RestErrorStatus.XML_ERROR, RestError.error(resource, service, RestErrorStatus.XML_ERROR, error));
    }

    public XmlErrorException(String resource, String service, RestStatus status, String error) {
        super(status, RestError.error(resource, service, status, error));
    }

    @Override
    public XmlErrorException get() {
        return new XmlErrorException();
    }
}

