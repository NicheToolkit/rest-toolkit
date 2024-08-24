package io.github.nichetoolkit.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

/**
 * <p>DefaultResult</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@SuppressWarnings({"UnnecessaryParentheses","unused"})
class DefaultResult<T,S extends DefaultResult<T,S>> implements Serializable {
    private Integer status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    private DefaultError error;


    protected DefaultResult() {
    }

    protected DefaultResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    protected DefaultResult(DefaultResult.Builder<T,S> builder) {
        if (builder.cause != null) {
            this.error = new DefaultError(builder.cause);
            this.message = builder.message == null ? builder.cause.getMessage() == null ? this.error.getLocalizedMessage() : builder.cause.getMessage() : builder.message;
        } else {
            this.message = builder.message;
        }
        this.status = builder.status;
        this.data = builder.data;
    }

    protected DefaultResult(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return Optional.ofNullable(this.status).map(RestErrorStatus.SUCCESS.getStatus()::equals).orElse(false);
    }

    public static abstract class Builder<T,S extends DefaultResult<T,S>> {
        protected Integer status;
        protected String message;
        protected T data;
        protected Throwable cause;

        public Builder() {
        }

        public DefaultResult.Builder<T,S> restStatus(RestStatus status) {
            this.status = status.getStatus();
            this.message = status.getMessage();
            return this;
        }

        public DefaultResult.Builder<T,S> status(Integer status) {
            this.status = status;
            return this;
        }

        public DefaultResult.Builder<T,S> status(RestStatus status) {
            this.status = status.getStatus();
            return this;
        }

        public DefaultResult.Builder<T,S> message(String message) {
            this.message = message;
            return this;
        }

        public DefaultResult.Builder<T,S> message(RestStatus status) {
            this.message = status.getMessage();
            return this;
        }

        public DefaultResult.Builder<T,S> data(T data) {
            this.data = data;
            return this;
        }

        public DefaultResult.Builder<T,S> cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        abstract DefaultResult<T,S> build();
    }

}
