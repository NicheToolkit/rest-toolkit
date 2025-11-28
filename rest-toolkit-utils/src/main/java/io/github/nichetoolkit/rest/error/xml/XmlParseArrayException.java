package io.github.nichetoolkit.rest.error.xml;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>XmlParseArrayException</code>
 * <p>The xml parse array exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.xml.XmlParseErrorException
 * @since Jdk17
 */
public class XmlParseArrayException extends XmlParseErrorException {
    /**
     * <code>XmlParseArrayException</code>
     * <p>Instantiates a new xml parse array exception.</p>
     */
    public XmlParseArrayException() {
        super(RestErrorStatus.XML_PARSE_ARRAY);
    }

    /**
     * <code>XmlParseArrayException</code>
     * <p>Instantiates a new xml parse array exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public XmlParseArrayException(RestStatus status) {
        super(status);
    }

    /**
     * <code>XmlParseArrayException</code>
     * <p>Instantiates a new xml parse array exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseArrayException(String message) {
        super(RestErrorStatus.XML_PARSE_ARRAY, message);
    }

    /**
     * <code>XmlParseArrayException</code>
     * <p>Instantiates a new xml parse array exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseArrayException(String resource, String message) {
        super(RestErrorStatus.XML_PARSE_ARRAY, resource, message);
    }

    /**
     * <code>XmlParseArrayException</code>
     * <p>Instantiates a new xml parse array exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseArrayException(String resource, String field, String message) {
        super(RestErrorStatus.XML_PARSE_ARRAY, resource, field, message);
    }

    /**
     * <code>XmlParseArrayException</code>
     * <p>Instantiates a new xml parse array exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public XmlParseArrayException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.XML_PARSE_ARRAY, resource, field, value, message);
    }

    @Override
    public XmlParseArrayException get() {
        return new XmlParseArrayException();
    }
}
