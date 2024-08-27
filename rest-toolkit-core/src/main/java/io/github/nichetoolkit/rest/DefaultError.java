package io.github.nichetoolkit.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <code>DefaultError</code>
 * <p>The type default error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.Error
 * @see java.io.Serializable
 * @see lombok.Data
 * @see lombok.EqualsAndHashCode
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"cause","stackTrace","localizedMessage","suppressed"})
class DefaultError extends Error implements Serializable {
    /**
     * <code>domain</code>
     * {@link java.lang.Integer} <p>the <code>domain</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer domain;
    /**
     * <code>line</code>
     * {@link java.lang.Integer} <p>the <code>line</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer line;
    /**
     * <code>resource</code>
     * {@link java.lang.String} <p>the <code>resource</code> field.</p>
     * @see java.lang.String
     */
    private String resource;
    /**
     * <code>debug</code>
     * {@link java.lang.String} <p>the <code>debug</code> field.</p>
     * @see java.lang.String
     */
    private String debug;
    /**
     * <code>issues</code>
     * {@link java.util.List} <p>the <code>issues</code> field.</p>
     * @see java.util.List
     */
    private List<RestErrorIssue> issues;

    /**
     * <code>DefaultError</code>
     * Instantiates a new default error.
     */
    protected DefaultError() {
    }

    /**
     * <code>DefaultError</code>
     * Instantiates a new default error.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    protected DefaultError(String message) {
        super(message);
    }

    /**
     * <code>DefaultError</code>
     * Instantiates a new default error.
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
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
     * Instantiates a new default error.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param domain  {@link java.lang.Integer} <p>the domain parameter is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     */
    protected DefaultError(String message, Integer domain) {
        super(message);
        this.domain = domain;
    }


    /**
     * <code>DefaultError</code>
     * Instantiates a new default error.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
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
     * Instantiates a new default error.
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  {@link boolean} <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace {@link boolean} <p>the writable stack trace parameter is <code>boolean</code> type.</p>
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
     * Instantiates a new default error.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param domain  {@link java.lang.Integer} <p>the domain parameter is <code>Integer</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    protected DefaultError(String message, Integer domain,Throwable cause) {
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
     * Instantiates a new default error.
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  {@link boolean} <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace {@link boolean} <p>the writable stack trace parameter is <code>boolean</code> type.</p>
     * @param name               {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param domain             {@link java.lang.Integer} <p>the domain parameter is <code>Integer</code> type.</p>
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
     * Instantiates a new default error.
     * @param builder {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rest.DefaultError.Builder
     */
    protected DefaultError(Builder builder) {
        super(builder.message,builder.cause);
        if (this.getCause() != null) {
            StackTraceElement stackTraceElement = this.getCause().getStackTrace()[0];
            this.line = stackTraceElement.getLineNumber();
            this.debug = builder.debug == null ? this.getCause().getClass().getName() : builder.debug;
            this.resource = builder.resource == null ?  stackTraceElement.getClassName() : builder.resource;
        }  else {
            this.debug = builder.debug;
            this.resource = builder.resource ;
            this.issues = builder.issues;
        }
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static abstract class Builder {
        /**
         * <code>message</code>
         * {@link java.lang.String} <p>the <code>message</code> field.</p>
         * @see java.lang.String
         */
        protected String message;
        /**
         * <code>debug</code>
         * {@link java.lang.String} <p>the <code>debug</code> field.</p>
         * @see java.lang.String
         */
        protected String debug;
        /**
         * <code>resource</code>
         * {@link java.lang.String} <p>the <code>resource</code> field.</p>
         * @see java.lang.String
         */
        protected String resource;
        /**
         * <code>issues</code>
         * {@link java.util.List} <p>the <code>issues</code> field.</p>
         * @see java.util.List
         */
        protected List<RestErrorIssue> issues;
        /**
         * <code>cause</code>
         * {@link java.lang.Throwable} <p>the <code>cause</code> field.</p>
         * @see java.lang.Throwable
         */
        protected Throwable cause;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestStatus
         */
        public Builder(RestStatus status) {
            this.message = status.getMessage();
        }

        /**
         * <code>message</code>
         * <p>the method.</p>
         * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>the return object is <code>Builder</code> type.</p>
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
         * <p>the method.</p>
         * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestStatus
         */
        public DefaultError.Builder message(RestStatus status) {
            this.message = status.getMessage();
            return this;
        }

        /**
         * <code>message</code>
         * <p>the method.</p>
         * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Throwable
         */
        public DefaultError.Builder message(Throwable cause) {
            this.message = cause.getMessage();
            return this;
        }

        /**
         * <code>debug</code>
         * <p>the method.</p>
         * @param debug {@link java.lang.String} <p>the debug parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public DefaultError.Builder debug(String debug) {
            this.debug = debug;
            return this;
        }

        /**
         * <code>resource</code>
         * <p>the method.</p>
         * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public DefaultError.Builder resource(String resource) {
            this.resource = resource;
            return this;
        }

        /**
         * <code>add</code>
         * <p>the method.</p>
         * @param issue {@link io.github.nichetoolkit.rest.RestErrorIssue} <p>the issue parameter is <code>RestErrorIssue</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestErrorIssue
         */
        public DefaultError.Builder add(RestErrorIssue issue) {
            this.issues = Optional.ofNullable(this.issues).orElseGet(ArrayList::new);
            this.issues.add(issue);
            return this;
        }

        /**
         * <code>cause</code>
         * <p>the method.</p>
         * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Throwable
         */
        public DefaultError.Builder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        /**
         * <code>build</code>
         * <p>the method.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultError} <p>the return object is <code>DefaultError</code> type.</p>
         */
        abstract DefaultError build();
    }

}
