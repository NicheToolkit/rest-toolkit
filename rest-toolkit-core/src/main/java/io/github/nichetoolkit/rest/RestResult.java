package io.github.nichetoolkit.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.ResponseEntity;

import java.util.Date;

/**
 * <p>RestResult</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings({"UnnecessaryParentheses","unused","SameNameButDifferent"})
public class RestResult<T> extends DefaultResult<T,RestResult<T>> {
    @JsonIgnore
    public static final String STATUS_NAME = "status";
    @JsonIgnore
    public static final String MESSAGE_NAME = "message";
    @JsonIgnore
    public static final String DATA_NAME = "data";

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date time;

    public RestResult() {
    }

    public RestResult(RestStatus status) {
        super(status.getStatus(),status.getMessage());
        this.time = new Date();
    }


    public RestResult(Integer status, String message) {
        super(status,message);
        this.time = new Date();
    }

    public RestResult(RestResult.Builder<T> builder) {
        super(builder.status, builder.message, builder.data);
        this.time = builder.time;
    }

    public RestResult(Integer status, String message, T data) {
        super(status,message,data);
        this.time = new Date();
    }

    public static <T> RestResult success() {
        return (new RestResult.Builder<T>()).restStatus(RestErrorStatus.SUCCESS).build();
    }

    public static <T> RestResult<T> success(T data) {
        return (new RestResult.Builder<T>()).restStatus(RestErrorStatus.SUCCESS).data(data).build();
    }

    public static <T> RestResult success(String message) {
        return (new RestResult.Builder<T>()).status(RestErrorStatus.SUCCESS).message(message).build();
    }

    public static <T> RestResult<T> success(String message, T data) {
        return (new RestResult.Builder<T>()).status(RestErrorStatus.SUCCESS).message(message).data(data).build();
    }

    public static <T> RestResult success(RestStatus status) {
        return (new RestResult.Builder<T>()).restStatus(status).build();
    }

    public static <T> RestResult<T> success(RestStatus status, T data) {
        return (new RestResult.Builder<T>()).restStatus(status).data(data).build();
    }

    public static <T> RestResult mistake(String message) {
        return (new RestResult.Builder<T>()).status(RestErrorStatus.MISTAKE).message(message).build();
    }

    public static <T> RestResult<T> mistake(String message, T data) {
        return (new RestResult.Builder<T>()).status(RestErrorStatus.MISTAKE).message(message).data(data).build();
    }

    public static <T> RestResult<T> mistake(Integer status, String message, T data) {
        return (new RestResult.Builder<T>()).status(status).message(message).data(data).build();
    }

    public static <T> RestResult mistake(Integer status,Throwable cause) {
        return (new RestResult.Builder<T>()).status(status).message(cause.getMessage()).cause(cause).build();
    }

    public static <T> RestResult mistake(Integer status, String message) {
        return (new RestResult.Builder<T>()).status(status).message(message).build();
    }

    public static <T> RestResult mistake(RestStatus status) {
        return (new RestResult.Builder<T>()).restStatus(status).build();
    }

    public static <T> RestResult mistake(RestStatus status,Throwable cause) {
        return (new RestResult.Builder<T>()).restStatus(status).cause(cause).build();
    }

    public static <T> RestResult<T> mistake(RestStatus status, T data) {
        return (new RestResult.Builder<T>()).restStatus(status).data(data).build();
    }

    public static <T> RestResult.Builder<T> defaultBuilder() {
        return new RestResult.Builder<>();
    }

    public static <T> ResponseEntity<RestResult> ok() {
        return ResponseEntity.ok((new RestResult.Builder<T>()).restStatus(RestErrorStatus.SUCCESS).build());
    }

    public static <T> ResponseEntity<RestResult<T>> ok(T data) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).restStatus(RestErrorStatus.SUCCESS).data(data).build());
    }

    public static <T> ResponseEntity<RestResult> ok(String message) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).status(RestErrorStatus.SUCCESS).message(message).build());
    }

    public static <T> ResponseEntity<RestResult<T>> ok(String message, T data) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).status(RestErrorStatus.SUCCESS).message(message).data(data).build());
    }

    public static <T> ResponseEntity<RestResult> ok(RestStatus status) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).restStatus(status).build());
    }

    public static <T> ResponseEntity<RestResult<T>> ok(RestStatus status, T data) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).restStatus(status).data(data).build());
    }

    public static <T> ResponseEntity<RestResult> error(String message) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).status(RestErrorStatus.MISTAKE).message(message).build());
    }

    public static <T> ResponseEntity<RestResult<T>> error(String message, T data) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).status(RestErrorStatus.MISTAKE).message(message).data(data).build());
    }

    public static <T> ResponseEntity<RestResult<T>> error(Integer status, String message, T data) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).status(status).message(message).data(data).build());
    }

    public static <T> ResponseEntity<RestResult> error(Integer status, String message) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).status(status).message(message).build());
    }

    public static <T> ResponseEntity<RestResult> error(RestStatus status) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).restStatus(status).build());
    }

    public static <T> ResponseEntity<RestResult<T>> error(RestStatus status, T data) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).restStatus(status).data(data).build());
    }

    public static class Builder<T> extends DefaultResult.Builder<T,RestResult<T>> {
        protected Date time;

        public Builder() {
            this.time = new Date();
        }

        @Override
        public RestResult.Builder<T> restStatus(RestStatus status) {
            this.status = status.getStatus();
            this.message = status.getMessage();
            return this;
        }

        @Override
        public RestResult.Builder<T> status(Integer status) {
            super.status(status);
            return this;
        }

        @Override
        public RestResult.Builder<T> status(RestStatus status) {
            this.status = status.getStatus();
            return this;
        }

        @Override
        public RestResult.Builder<T> message(String message) {
            super.message(message);
            return this;
        }

        @Override
        public RestResult.Builder<T> message(RestStatus status) {
            this.message = status.getMessage();
            return this;
        }

        @Override
        public RestResult.Builder<T> data(T data) {
            super.data(data);
            return this;
        }

        @Override
        public RestResult.Builder<T> cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        public RestResult.Builder<T> time(Date time) {
            this.time = time;
            return this;
        }

        public RestResult.Builder<T> time(Long time) {
            this.time = new Date(time);
            return this;
        }

        @Override
        public RestResult<T> build() {
            return new RestResult<>(this);
        }
    }
}
