package io.github.nichetoolkit.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>DefaultError</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"cause","stackTrace","localizedMessage","suppressed"})
class DefaultError extends Error implements Serializable {
    protected Integer domain;
    protected Integer line;
    protected String resource;
    protected String debug;
    protected List<DefaultErrorIssue> issues;

    public DefaultError() {
    }

    public DefaultError(String message) {
        super(message);
    }

    public DefaultError(Throwable cause) {
        super(cause);
        if (this.getCause() != null) {
            StackTraceElement stackTraceElement = this.getCause().getStackTrace()[0];
            this.line = stackTraceElement.getLineNumber();
            this.resource = stackTraceElement.getClassName();
            this.debug = this.getCause().getClass().getName();
        }
    }

    public DefaultError(String message, Integer domain) {
        super(message);
        this.domain = domain;
    }


    public DefaultError(String message, Throwable cause) {
        super(message, cause);
        if (this.getCause() != null) {
            StackTraceElement stackTraceElement = this.getCause().getStackTrace()[0];
            this.line = stackTraceElement.getLineNumber();
            this.resource = stackTraceElement.getClassName();
            this.debug = this.getCause().getClass().getName();
        }
    }

    public DefaultError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        if (this.getCause() != null) {
            StackTraceElement stackTraceElement = this.getCause().getStackTrace()[0];
            this.line = stackTraceElement.getLineNumber();
            this.resource = stackTraceElement.getClassName();
            this.debug = this.getCause().getClass().getName();
        }
    }

    public DefaultError(String message, Integer domain,Throwable cause) {
        super(message, cause);
        this.domain = domain;
        if (this.getCause() != null) {
            StackTraceElement stackTraceElement = this.getCause().getStackTrace()[0];
            this.line = stackTraceElement.getLineNumber();
            this.resource = stackTraceElement.getClassName();
            this.debug = this.getCause().getClass().getName();
        }
    }


    public DefaultError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String name, Integer domain) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.domain = domain;
        if (this.getCause() != null) {
            StackTraceElement stackTraceElement = this.getCause().getStackTrace()[0];
            this.line = stackTraceElement.getLineNumber();
            this.resource = stackTraceElement.getClassName();
            this.debug = this.getCause().getClass().getName();
        }
    }

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

    public static class Builder {
        protected String message;
        protected String debug;
        protected String resource;
        protected List<DefaultErrorIssue> issues;
        protected Throwable cause;

        public Builder() {
        }

        public Builder(RestStatus status) {
            this.message = status.getMessage();
        }

        public DefaultError.Builder message(String message) {
            if (message != null && message.length() > 0) {
                this.message = message;
            }
            return this;
        }

        public DefaultError.Builder message(RestStatus status) {
            this.message = status.getMessage();
            return this;
        }

        public DefaultError.Builder message(Throwable cause) {
            this.message = cause.getMessage();
            return this;
        }

        public DefaultError.Builder debug(String debug) {
            this.debug = debug;
            return this;
        }

        public DefaultError.Builder resource(String resource) {
            this.resource = resource;
            return this;
        }

        public DefaultError.Builder add(DefaultErrorIssue issue) {
            this.issues = Optional.ofNullable(this.issues).orElseGet(ArrayList::new);
            this.issues.add(issue);
            return this;
        }

        public DefaultError.Builder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        public DefaultError build() {
            return new DefaultError(this);
        }
    }

}
