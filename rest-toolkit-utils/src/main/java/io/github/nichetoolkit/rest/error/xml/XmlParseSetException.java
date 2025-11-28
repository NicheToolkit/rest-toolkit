package io.github.nichetoolkit.rest.error.xml;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>XmlParseSetException</code>
 * <p>The xml parse set exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.xml.XmlParseErrorException
 * @since Jdk17
 */
public class XmlParseSetException extends XmlParseErrorException {
    /**
     * <code>XmlParseSetException</code>
     * <p>Instantiates a new xml parse set exception.</p>
     */
    public XmlParseSetException() {
        super(RestErrorStatus.XML_PARSE_SET);
    }

    /**
     * <code>XmlParseSetException</code>
     * <p>Instantiates a new xml parse set exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public XmlParseSetException(RestStatus status) {
        super(status);
    }

    /**
     * <code>XmlParseSetException</code>
     * <p>Instantiates a new xml parse set exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseSetException(String message) {
        super(RestErrorStatus.XML_PARSE_SET, message);
    }

    /**
     * <code>XmlParseSetException</code>
     * <p>Instantiates a new xml parse set exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseSetException(String resource, String message) {
        super(RestErrorStatus.XML_PARSE_SET, resource, message);
    }

    /**
     * <code>XmlParseSetException</code>
     * <p>Instantiates a new xml parse set exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseSetException(String resource, String field, String message) {
        super(RestErrorStatus.XML_PARSE_SET, resource, field, message);
    }

    /**
     * <code>XmlParseSetException</code>
     * <p>Instantiates a new xml parse set exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public XmlParseSetException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.XML_PARSE_SET, resource, field, value, message);
    }

    @Override
    public XmlParseSetException get() {
        return new XmlParseSetException();
    }
}
