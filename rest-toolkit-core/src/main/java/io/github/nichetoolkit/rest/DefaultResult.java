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
public class DefaultResult<T,S extends DefaultResult<T,S>> implements Serializable {
    private Integer status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    private DefaultError error;


    public DefaultResult() {
    }

    public DefaultResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public DefaultResult(DefaultResult.Builder<T,S> builder) {
        if (builder.cause != null) {
            this.error = new DefaultError(builder.cause);
            this.message = builder.message == null ? builder.cause.getMessage() == null ? this.error.getLocalizedMessage() : builder.cause.getMessage() : builder.message;
        } else {
            this.message = builder.message;
        }
        this.status = builder.status;
        this.data = builder.data;
    }

    public DefaultResult(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return Optional.ofNullable(this.status).map(RestErrorStatus.SUCCESS.getStatus()::equals).orElse(false);
    }

    public static <T,S extends DefaultResult<T,S>> DefaultResult<T,S> success() {
        return (new DefaultResult.Builder<T,S>()).status(RestErrorStatus.SUCCESS.getStatus()).build();
    }

    public static <T,S extends DefaultResult<T,S>> DefaultResult<T,S> success(T data) {
        return (new DefaultResult.Builder<T,S>()).status(RestErrorStatus.SUCCESS.getStatus()).data(data).build();
    }

    public static <T,S extends DefaultResult<T,S>> DefaultResult<T,S> success(String message) {
        return (new DefaultResult.Builder<T,S>()).status(RestErrorStatus.SUCCESS.getStatus()).message(message).build();
    }

    public static <T,S extends DefaultResult<T,S>> DefaultResult<T,S> success(String message, T data) {
        return (new DefaultResult.Builder<T,S>()).status(RestErrorStatus.SUCCESS.getStatus()).message(message).data(data).build();
    }

    public static <T,S extends DefaultResult<T,S>> DefaultResult<T,S> success(RestStatus status) {
        return (new DefaultResult.Builder<T,S>()).status(status.getStatus()).message(status.getMessage()).build();
    }

    public static <T,S extends DefaultResult<T,S>> DefaultResult<T,S> success(RestStatus status, T data) {
        return (new DefaultResult.Builder<T,S>()).status(status.getStatus()).message(status.getMessage()).data(data).build();
    }

    public static <T,S extends DefaultResult<T,S>> DefaultResult<T,S> fail(String message) {
        return (new DefaultResult.Builder<T,S>()).status(RestErrorStatus.FAILED.getStatus()).message(message).build();
    }

    public static <T,S extends DefaultResult<T,S>> DefaultResult<T,S> fail(String message, T data) {
        return (new DefaultResult.Builder<T,S>()).status(RestErrorStatus.FAILED.getStatus()).message(message).data(data).build();
    }

    public static <T,S extends DefaultResult<T,S>> DefaultResult<T,S> fail(Integer status, String message, T data) {
        return (new DefaultResult.Builder<T,S>()).status(status).message(message).data(data).build();
    }

    public static <T,S extends DefaultResult<T,S>> DefaultResult<T,S> fail(Integer status,Throwable cause) {
        return (new DefaultResult.Builder<T,S>()).status(status).message(cause.getMessage()).cause(cause).build();
    }

    public static <T,S extends DefaultResult<T,S>> DefaultResult<T,S> fail(Integer status, String message) {
        return (new DefaultResult.Builder<T,S>()).status(status).message(message).build();
    }

    public static <T,S extends DefaultResult<T,S>> DefaultResult<T,S> fail(RestStatus status) {
        return (new DefaultResult.Builder<T,S>()).status(status.getStatus()).message(status.getMessage()).build();
    }


    public static <T,S extends DefaultResult<T,S>> DefaultResult<T,S> fail(RestStatus status,Throwable cause) {
        return (new Builder<T, S>()).status(status.getStatus()).message(status.getMessage()).cause(cause).build();
    }

    public static <T,S extends DefaultResult<T,S>> DefaultResult<T,S> fail(RestStatus status, T data) {
        return (new DefaultResult.Builder<T,S>()).status(status.getStatus()).message(status.getMessage()).data(data).build();
    }

    public static <T,S extends DefaultResult<T,S>> DefaultResult.Builder<T,S> defaultBuilder() {
        return new DefaultResult.Builder<>();
    }

    public static class Builder<T,S extends DefaultResult<T,S>> {
        protected Integer status;
        protected String message;
        protected T data;
        private Throwable cause;

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

        public DefaultResult<T,S> build() {
            return new DefaultResult<>(this);
        }
    }

}
