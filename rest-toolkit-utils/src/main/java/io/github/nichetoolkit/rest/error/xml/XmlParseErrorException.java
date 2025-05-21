package io.github.nichetoolkit.rest.error.xml;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ParseErrorException;

/**
 * <code>XmlParseErrorException</code>
 * <p>The xml parse error exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ParseErrorException
 * @since Jdk1.8
 */
public class XmlParseErrorException extends ParseErrorException {

    /**
     * <code>XmlParseErrorException</code>
     * <p>Instantiates a new xml parse error exception.</p>
     */
    public XmlParseErrorException() {
        super(RestErrorStatus.XML_PARSE_ERROR);
    }

    /**
     * <code>XmlParseErrorException</code>
     * <p>Instantiates a new xml parse error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>The status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public XmlParseErrorException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>XmlParseErrorException</code>
     * <p>Instantiates a new xml parse error exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseErrorException(String error) {
        super(RestErrorStatus.XML_PARSE_ERROR, RestError.error(RestErrorStatus.XML_PARSE_ERROR, error));
    }

    /**
     * <code>XmlParseErrorException</code>
     * <p>Instantiates a new xml parse error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public XmlParseErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>XmlParseErrorException</code>
     * <p>Instantiates a new xml parse error exception.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public XmlParseErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    /**
     * <code>XmlParseErrorException</code>
     * <p>Instantiates a new xml parse error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public XmlParseErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    /**
     * <code>XmlParseErrorException</code>
     * <p>Instantiates a new xml parse error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public XmlParseErrorException(RestStatus status, String resource, String field, String message) {
        super(status, RestError.error(resource, field, status, message));
    }

    /**
     * <code>XmlParseErrorException</code>
     * <p>Instantiates a new xml parse error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Object
     */
    public XmlParseErrorException(RestStatus status, String resource, String field, Object value, String message) {
        super(status, RestError.error(resource, field, value, status, message));
    }

    @Override
    public XmlParseErrorException get() {
        return new XmlParseErrorException();
    }
}
