package io.github.nichetoolkit.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <code>DefaultError</code>
 * <p>The default error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.Error
 * @see java.io.Serializable
 * @see lombok.Getter
 * @see lombok.Setter
 * @see lombok.EqualsAndHashCode
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"cause", "stackTrace", "localizedMessage", "suppressed"})
class DefaultError extends Error implements Serializable {
    /**
     * <code>domain</code>
     * {@link java.lang.Integer} <p>The <code>domain</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer domain;
    /**
     * <code>line</code>
     * {@link java.lang.Integer} <p>The <code>line</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer line;
    /**
     * <code>resource</code>
     * {@link java.lang.String} <p>The <code>resource</code> field.</p>
     * @see java.lang.String
     */
    private String resource;
    /**
     * <code>debug</code>
     * {@link java.lang.String} <p>The <code>debug</code> field.</p>
     * @see java.lang.String
     */
    private String debug;
    /**
     * <code>issues</code>
     * {@link java.util.List} <p>The <code>issues</code> field.</p>
     * @see java.util.List
     */
    private List<RestErrorIssue> issues;

    /**
     * <code>DefaultError</code>
     * <p>Instantiates a new default error.</p>
     */
    protected DefaultError() {
    }

    /**
     * <code>DefaultError</code>
     * <p>Instantiates a new default error.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    protected DefaultError(String message) {
        super(message);
    }

    /**
     * <code>DefaultError</code>
     * <p>Instantiates a new default error.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    protected DefaultError(Throwable cause) {
        super(cause);
        if (this.getCause() != null) {
            StackTraceElement stackTraceElement = this.getCause().getStackTrace()[0];
            this.line = stackTraceElement.getLineNumber();
            this.resource = stackTraceElement.getClassName();
            this.debug = this.getCause().getClass().getName();
        }
    }

    /**
     * <code>DefaultError</code>
     * <p>Instantiates a new default error.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param domain  {@link java.lang.Integer} <p>The domain parameter is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     */
    protected DefaultError(String message, Integer domain) {
        super(message);
        this.domain = domain;
    }

    /**
     * <code>DefaultError</code>
     * <p>Instantiates a new default error.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    protected DefaultError(String message, Throwable cause) {
        super(message, cause);
        if (this.getCause() != null) {
            StackTraceElement stackTraceElement = this.getCause().getStackTrace()[0];
            this.line = stackTraceElement.getLineNumber();
            this.resource = stackTraceElement.getClassName();
            this.debug = this.getCause().getClass().getName();
        }
    }

    /**
     * <code>DefaultError</code>
     * <p>Instantiates a new default error.</p>
     * @param message            {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    protected DefaultError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        if (this.getCause() != null) {
            StackTraceElement stackTraceElement = this.getCause().getStackTrace()[0];
            this.line = stackTraceElement.getLineNumber();
            this.resource = stackTraceElement.getClassName();
            this.debug = this.getCause().getClass().getName();
        }
    }

    /**
     * <code>DefaultError</code>
     * <p>Instantiates a new default error.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param domain  {@link java.lang.Integer} <p>The domain parameter is <code>Integer</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    protected DefaultError(String message, Integer domain, Throwable cause) {
        super(message, cause);
        this.domain = domain;
        if (this.getCause() != null) {
            StackTraceElement stackTraceElement = this.getCause().getStackTrace()[0];
            this.line = stackTraceElement.getLineNumber();
            this.resource = stackTraceElement.getClassName();
            this.debug = this.getCause().getClass().getName();
        }
    }

    /**
     * <code>DefaultError</code>
     * <p>Instantiates a new default error.</p>
     * @param message            {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
     * @param name               {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param domain             {@link java.lang.Integer} <p>The domain parameter is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Integer
     */
    protected DefaultError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String name, Integer domain) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.domain = domain;
        if (this.getCause() != null) {
            StackTraceElement stackTraceElement = this.getCause().getStackTrace()[0];
            this.line = stackTraceElement.getLineNumber();
            this.resource = stackTraceElement.getClassName();
            this.debug = this.getCause().getClass().getName();
        }
    }

    /**
     * <code>DefaultError</code>
     * <p>Instantiates a new default error.</p>
     * @param builder {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rest.DefaultError.Builder
     */
    protected DefaultError(Builder builder) {
        super(builder.message, builder.cause);
        if (this.getCause() != null) {
            StackTraceElement stackTraceElement = this.getCause().getStackTrace()[0];
            this.line = stackTraceElement.getLineNumber();
            this.debug = builder.debug == null ? this.getCause().getClass().getName() : builder.debug;
            this.resource = builder.resource == null ? stackTraceElement.getClassName() : builder.resource;
        } else {
            this.debug = builder.debug;
            this.resource = builder.resource;
            this.issues = builder.issues;
        }
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static abstract class Builder {
        /**
         * <code>message</code>
         * {@link java.lang.String} <p>The <code>message</code> field.</p>
         * @see java.lang.String
         */
        protected String message;
        /**
         * <code>debug</code>
         * {@link java.lang.String} <p>The <code>debug</code> field.</p>
         * @see java.lang.String
         */
        protected String debug;
        /**
         * <code>resource</code>
         * {@link java.lang.String} <p>The <code>resource</code> field.</p>
         * @see java.lang.String
         */
        protected String resource;
        /**
         * <code>issues</code>
         * {@link java.util.List} <p>The <code>issues</code> field.</p>
         * @see java.util.List
         */
        protected List<RestErrorIssue> issues;
        /**
         * <code>cause</code>
         * {@link java.lang.Throwable} <p>The <code>cause</code> field.</p>
         * @see java.lang.Throwable
         */
        protected Throwable cause;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestStatus
         */
        public Builder(RestStatus status) {
            this.message = status.getMessage();
        }

        /**
         * <code>message</code>
         * <p>The message method.</p>
         * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>The message return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public DefaultError.Builder message(String message) {
            if (message != null && !message.isEmpty()) {
                this.message = message;
            }
            return this;
        }

        /**
         * <code>message</code>
         * <p>The message method.</p>
         * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>The message return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestStatus
         */
        public DefaultError.Builder message(RestStatus status) {
            this.message = status.getMessage();
            return this;
        }

        /**
         * <code>message</code>
         * <p>The message method.</p>
         * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>The message return object is <code>Builder</code> type.</p>
         * @see java.lang.Throwable
         */
        public DefaultError.Builder message(Throwable cause) {
            this.message = cause.getMessage();
            return this;
        }

        /**
         * <code>debug</code>
         * <p>The debug method.</p>
         * @param debug {@link java.lang.String} <p>The debug parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>The debug return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public DefaultError.Builder debug(String debug) {
            this.debug = debug;
            return this;
        }

        /**
         * <code>resource</code>
         * <p>The resource method.</p>
         * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>The resource return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public DefaultError.Builder resource(String resource) {
            this.resource = resource;
            return this;
        }

        /**
         * <code>add</code>
         * <p>The add method.</p>
         * @param issue {@link io.github.nichetoolkit.rest.RestErrorIssue} <p>The issue parameter is <code>RestErrorIssue</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>The add return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestErrorIssue
         */
        public DefaultError.Builder add(RestErrorIssue issue) {
            this.issues = Optional.ofNullable(this.issues).orElseGet(ArrayList::new);
            this.issues.add(issue);
            return this;
        }

        /**
         * <code>cause</code>
         * <p>The cause method.</p>
         * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>The cause return object is <code>Builder</code> type.</p>
         * @see java.lang.Throwable
         */
        public DefaultError.Builder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        /**
         * <code>build</code>
         * <p>The build method.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError} <p>The build return object is <code>DefaultError</code> type.</p>
         */
        abstract DefaultError build();
    }

}
