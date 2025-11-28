package io.github.nichetoolkit.rest.error.xml;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>XmlParseMapException</code>
 * <p>The xml parse map exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.xml.XmlParseErrorException
 * @since Jdk17
 */
public class XmlParseMapException extends XmlParseErrorException {
    /**
     * <code>XmlParseMapException</code>
     * <p>Instantiates a new xml parse map exception.</p>
     */
    public XmlParseMapException() {
        super(RestErrorStatus.XML_PARSE_MAP);
    }

    /**
     * <code>XmlParseMapException</code>
     * <p>Instantiates a new xml parse map exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public XmlParseMapException(RestStatus status) {
        super(status);
    }

    /**
     * <code>XmlParseMapException</code>
     * <p>Instantiates a new xml parse map exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseMapException(String message) {
        super(RestErrorStatus.XML_PARSE_MAP, message);
    }

    /**
     * <code>XmlParseMapException</code>
     * <p>Instantiates a new xml parse map exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseMapException(String resource, String message) {
        super(RestErrorStatus.XML_PARSE_MAP, resource, message);
    }

    /**
     * <code>XmlParseMapException</code>
     * <p>Instantiates a new xml parse map exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseMapException(String resource, String field, String message) {
        super(RestErrorStatus.XML_PARSE_MAP, resource, field, message);
    }

    /**
     * <code>XmlParseMapException</code>
     * <p>Instantiates a new xml parse map exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public XmlParseMapException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.XML_PARSE_MAP, resource, field, value, message);
    }

    @Override
    public XmlParseMapException get() {
        return new XmlParseMapException();
    }
}
