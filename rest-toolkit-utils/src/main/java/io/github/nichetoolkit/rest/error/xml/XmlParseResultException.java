package io.github.nichetoolkit.rest.error.xml;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>XmlParseResultException</code>
 * <p>The xml parse result exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.xml.XmlParseErrorException
 * @since Jdk1.8
 */
public class XmlParseResultException extends XmlParseErrorException {
    /**
     * <code>XmlParseResultException</code>
     * <p>Instantiates a new xml parse result exception.</p>
     */
    public XmlParseResultException() {
        super(RestErrorStatus.XML_PARSE_RESULT);
    }

    /**
     * <code>XmlParseResultException</code>
     * <p>Instantiates a new xml parse result exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public XmlParseResultException(RestStatus status) {
        super(status);
    }

    /**
     * <code>XmlParseResultException</code>
     * <p>Instantiates a new xml parse result exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseResultException(String message) {
        super(RestErrorStatus.XML_PARSE_RESULT, message);
    }

    /**
     * <code>XmlParseResultException</code>
     * <p>Instantiates a new xml parse result exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseResultException(String resource, String message) {
        super(RestErrorStatus.XML_PARSE_RESULT, resource, message);
    }

    /**
     * <code>XmlParseResultException</code>
     * <p>Instantiates a new xml parse result exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseResultException(String resource, String field, String message) {
        super(RestErrorStatus.XML_PARSE_RESULT, resource, field, message);
    }

    /**
     * <code>XmlParseResultException</code>
     * <p>Instantiates a new xml parse result exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public XmlParseResultException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.XML_PARSE_RESULT, resource, field, value, message);
    }

    @Override
    public XmlParseResultException get() {
        return new XmlParseResultException();
    }
}
