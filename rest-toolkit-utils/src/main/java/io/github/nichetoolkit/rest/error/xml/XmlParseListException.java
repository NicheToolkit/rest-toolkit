package io.github.nichetoolkit.rest.error.xml;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>XmlParseListException</code>
 * <p>The xml parse list exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.xml.XmlParseErrorException
 * @since Jdk17
 */
public class XmlParseListException extends XmlParseErrorException {
    /**
     * <code>XmlParseListException</code>
     * <p>Instantiates a new xml parse list exception.</p>
     */
    public XmlParseListException() {
        super(RestErrorStatus.XML_PARSE_LIST);
    }

    /**
     * <code>XmlParseListException</code>
     * <p>Instantiates a new xml parse list exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public XmlParseListException(RestStatus status) {
        super(status);
    }

    /**
     * <code>XmlParseListException</code>
     * <p>Instantiates a new xml parse list exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseListException(String message) {
        super(RestErrorStatus.XML_PARSE_LIST, message);
    }

    /**
     * <code>XmlParseListException</code>
     * <p>Instantiates a new xml parse list exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseListException(String resource, String message) {
        super(RestErrorStatus.XML_PARSE_LIST, resource, message);
    }

    /**
     * <code>XmlParseListException</code>
     * <p>Instantiates a new xml parse list exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseListException(String resource, String field, String message) {
        super(RestErrorStatus.XML_PARSE_LIST, resource, field, message);
    }

    /**
     * <code>XmlParseListException</code>
     * <p>Instantiates a new xml parse list exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public XmlParseListException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.XML_PARSE_LIST, resource, field, value, message);
    }

    @Override
    public XmlParseListException get() {
        return new XmlParseListException();
    }
}
