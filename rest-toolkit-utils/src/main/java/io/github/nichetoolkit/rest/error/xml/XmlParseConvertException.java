package io.github.nichetoolkit.rest.error.xml;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.json.JsonParseErrorException;

/**
 * <code>XmlParseConvertException</code>
 * <p>The xml parse convert exception class.</p>
 * @see  io.github.nichetoolkit.rest.error.json.JsonParseErrorException
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class XmlParseConvertException extends JsonParseErrorException {
    /**
     * <code>XmlParseConvertException</code>
     * <p>Instantiates a new xml parse convert exception.</p>
     */
    public XmlParseConvertException() {
        super(RestErrorStatus.XML_PARSE_CONVERT);
    }

    /**
     * <code>XmlParseConvertException</code>
     * <p>Instantiates a new xml parse convert exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestStatus
     */
    public XmlParseConvertException(RestStatus status) {
        super(status);
    }

    /**
     * <code>XmlParseConvertException</code>
     * <p>Instantiates a new xml parse convert exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public XmlParseConvertException(String message) {
        super(RestErrorStatus.XML_PARSE_CONVERT, message);
    }

    /**
     * <code>XmlParseConvertException</code>
     * <p>Instantiates a new xml parse convert exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public XmlParseConvertException(String resource, String message) {
        super(RestErrorStatus.XML_PARSE_CONVERT, resource, message);
    }

    /**
     * <code>XmlParseConvertException</code>
     * <p>Instantiates a new xml parse convert exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public XmlParseConvertException(String resource, String field, String message) {
        super(RestErrorStatus.XML_PARSE_CONVERT, resource, field, message);
    }

    /**
     * <code>XmlParseConvertException</code>
     * <p>Instantiates a new xml parse convert exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     */
    public XmlParseConvertException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.XML_PARSE_CONVERT, resource, field, value, message);
    }

    @Override
    public XmlParseConvertException get() {
        return new XmlParseConvertException();
    }
}
