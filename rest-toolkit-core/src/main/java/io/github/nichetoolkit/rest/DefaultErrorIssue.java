package io.github.nichetoolkit.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Objects;

/**
 * <code>DefaultErrorIssue</code>
 * <p>The default error issue class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Setter
 * @see lombok.experimental.SuperBuilder
 * @since Jdk1.8
 */
@Setter
@SuperBuilder
class DefaultErrorIssue implements Serializable {
    /**
     * <code>field</code>
     * {@link java.lang.String} <p>The <code>field</code> field.</p>
     * @see java.lang.String
     * @see lombok.Getter
     */
    @Getter
    private String field;
    /**
     * <code>value</code>
     * {@link java.lang.Object} <p>The <code>value</code> field.</p>
     * @see java.lang.Object
     */
    private Object value;
    /**
     * <code>issue</code>
     * {@link java.lang.String} <p>The <code>issue</code> field.</p>
     * @see java.lang.String
     * @see lombok.Getter
     */
    @Getter
    private String issue;

    /**
     * <code>DefaultErrorIssue</code>
     * <p>Instantiates a new default error issue.</p>
     */
    protected DefaultErrorIssue() {
    }

    /**
     * <code>DefaultErrorIssue</code>
     * <p>Instantiates a new default error issue.</p>
     * @param issue {@link java.lang.String} <p>The issue parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    protected DefaultErrorIssue(String issue) {
        this.issue = issue;
    }

    /**
     * <code>DefaultErrorIssue</code>
     * <p>Instantiates a new default error issue.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param issue {@link java.lang.String} <p>The issue parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    protected DefaultErrorIssue(String field, String issue) {
        this.field = field;
        this.issue = issue;
    }

    /**
     * <code>DefaultErrorIssue</code>
     * <p>Instantiates a new default error issue.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param issue {@link java.lang.String} <p>The issue parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    protected DefaultErrorIssue(String field, Object value, String issue) {
        this.field = field;
        this.value = value;
        this.issue = issue;
    }

    /**
     * <code>getValue</code>
     * <p>The get value getter method.</p>
     * @return {@link java.lang.String} <p>The get value return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getValue() {
        return Objects.toString(value);
    }

    /**
     * <code>getObjectValue</code>
     * <p>The get object value getter method.</p>
     * @return {@link java.lang.Object} <p>The get object value return object is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    public Object getObjectValue() {
        return this.value;
    }
}
