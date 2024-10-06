package io.github.nichetoolkit.rest;

import lombok.Data;

import java.io.Serializable;

/**
 * <code>DefaultErrorIssue</code>
 * <p>The type default error issue class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Data
 * @since Jdk1.8
 */
@Data
class DefaultErrorIssue implements Serializable {
    /**
     * <code>field</code>
     * {@link java.lang.String} <p>The <code>field</code> field.</p>
     * @see java.lang.String
     */
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
     */
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
     * @param builder {@link io.github.nichetoolkit.rest.DefaultErrorIssue.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rest.DefaultErrorIssue.Builder
     */
    protected DefaultErrorIssue(DefaultErrorIssue.Builder builder) {
        this.field = builder.field;
        this.value = builder.value;
        this.issue = builder.issue;
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
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static abstract class Builder {
        /**
         * <code>field</code>
         * {@link java.lang.String} <p>The <code>field</code> field.</p>
         * @see java.lang.String
         */
        protected String field;
        /**
         * <code>value</code>
         * {@link java.lang.Object} <p>The <code>value</code> field.</p>
         * @see java.lang.Object
         */
        protected Object value;
        /**
         * <code>issue</code>
         * {@link java.lang.String} <p>The <code>issue</code> field.</p>
         * @see java.lang.String
         */
        protected String issue;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>field</code>
         * <p>The method.</p>
         * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultErrorIssue.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public DefaultErrorIssue.Builder field(String field) {
            this.field = field;
            return this;
        }

        /**
         * <code>value</code>
         * <p>The method.</p>
         * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultErrorIssue.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.Object
         */
        public DefaultErrorIssue.Builder value(Object value) {
            this.value = value;
            return this;
        }

        /**
         * <code>issue</code>
         * <p>The method.</p>
         * @param issue {@link java.lang.String} <p>The issue parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultErrorIssue.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public DefaultErrorIssue.Builder issue(String issue) {
            this.issue = issue;
            return this;
        }

        /**
         * <code>build</code>
         * <p>The method.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultErrorIssue} <p>The return object is <code>DefaultErrorIssue</code> type.</p>
         */
        abstract DefaultErrorIssue build();
    }
}
