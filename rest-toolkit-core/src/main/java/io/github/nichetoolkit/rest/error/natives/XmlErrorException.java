package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>XmlErrorException</p>
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

    public XmlErrorException(String error, Throwable cause) {
        super(RestErrorStatus.XML_ERROR, RestError.error(RestErrorStatus.XML_ERROR, error, cause), cause);
    }

    public XmlErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public XmlErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public XmlErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public XmlErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public XmlErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public XmlErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public XmlErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public XmlErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public XmlErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public XmlErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public XmlErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public XmlErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public XmlErrorException(String resource, String error) {
        super(RestErrorStatus.XML_ERROR, RestError.error(resource, RestErrorStatus.XML_ERROR, error));
    }

    public XmlErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.XML_ERROR, RestError.error(resource, RestErrorStatus.XML_ERROR, error, cause), cause);
    }

    public XmlErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.XML_ERROR, RestError.error(resource, filed, RestErrorStatus.XML_ERROR, error));
    }

    public XmlErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.XML_ERROR, RestError.error(resource, filed, RestErrorStatus.XML_ERROR, error, cause), cause);
    }

    public XmlErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.XML_ERROR, RestError.error(resource, filed, value, RestErrorStatus.XML_ERROR, error));
    }

    public XmlErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.XML_ERROR, RestError.error(resource, filed, value, RestErrorStatus.XML_ERROR, error, cause));
    }

    @Override
    public XmlErrorException get() {
        return new XmlErrorException();
    }
}

