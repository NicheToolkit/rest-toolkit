package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

public class JaxbXmlErrorException extends RestErrorException {

    public JaxbXmlErrorException() {
        super(RestErrorStatus.JAXB_XML_ERROR);
    }

    public JaxbXmlErrorException(RestErrorStatus status) {
        super(status);
    }

    public JaxbXmlErrorException(String error) {
        super(RestErrorStatus.JAXB_XML_ERROR, RestError.error(RestErrorStatus.JAXB_XML_ERROR, error));
    }

    public JaxbXmlErrorException(String error, Throwable cause) {
        super(RestErrorStatus.JAXB_XML_ERROR, RestError.error(RestErrorStatus.JAXB_XML_ERROR, error, cause), cause);
    }

    public JaxbXmlErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public JaxbXmlErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public JaxbXmlErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public JaxbXmlErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public JaxbXmlErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public JaxbXmlErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public JaxbXmlErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public JaxbXmlErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public JaxbXmlErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public JaxbXmlErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public JaxbXmlErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public JaxbXmlErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public JaxbXmlErrorException(String resource, String error) {
        super(RestErrorStatus.JAXB_XML_ERROR, RestError.error(resource, RestErrorStatus.JAXB_XML_ERROR, error));
    }

    public JaxbXmlErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.JAXB_XML_ERROR, RestError.error(resource, RestErrorStatus.JAXB_XML_ERROR, error, cause), cause);
    }

    public JaxbXmlErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.JAXB_XML_ERROR, RestError.error(resource, filed, RestErrorStatus.JAXB_XML_ERROR, error));
    }

    public JaxbXmlErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.JAXB_XML_ERROR, RestError.error(resource, filed, RestErrorStatus.JAXB_XML_ERROR, error, cause), cause);
    }

    public JaxbXmlErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.JAXB_XML_ERROR, RestError.error(resource, filed, value, RestErrorStatus.JAXB_XML_ERROR, error));
    }

    public JaxbXmlErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.JAXB_XML_ERROR, RestError.error(resource, filed, value, RestErrorStatus.JAXB_XML_ERROR, error, cause));
    }

    @Override
    public JaxbXmlErrorException get() {
        return new JaxbXmlErrorException();
    }
}

