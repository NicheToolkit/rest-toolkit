package io.github.nichetoolkit.rest.error.xml;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>XmlParseBeanException</code>
 * <p>The xml parse bean exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.xml.XmlParseErrorException
 * @since Jdk1.8
 */
public class XmlParseBeanException extends XmlParseErrorException {
    /**
     * <code>XmlParseBeanException</code>
     * <p>Instantiates a new xml parse bean exception.</p>
     */
    public XmlParseBeanException() {
        super(RestErrorStatus.XML_PARSE_BEAN);
    }

    /**
     * <code>XmlParseBeanException</code>
     * <p>Instantiates a new xml parse bean exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public XmlParseBeanException(RestStatus status) {
        super(status);
    }

    /**
     * <code>XmlParseBeanException</code>
     * <p>Instantiates a new xml parse bean exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseBeanException(String message) {
        super(RestErrorStatus.XML_PARSE_BEAN, message);
    }

    /**
     * <code>XmlParseBeanException</code>
     * <p>Instantiates a new xml parse bean exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseBeanException(String resource, String message) {
        super(RestErrorStatus.XML_PARSE_BEAN, resource, message);
    }

    /**
     * <code>XmlParseBeanException</code>
     * <p>Instantiates a new xml parse bean exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public XmlParseBeanException(String resource, String field, String message) {
        super(RestErrorStatus.XML_PARSE_BEAN, resource, field, message);
    }

    /**
     * <code>XmlParseBeanException</code>
     * <p>Instantiates a new xml parse bean exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public XmlParseBeanException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.XML_PARSE_BEAN, resource, field, value, message);
    }

    @Override
    public XmlParseBeanException get() {
        return new XmlParseBeanException();
    }
}
