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
 * <code>RestResult</code>
 * <p>The type rest result class.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.DefaultResult
 * @see lombok.Data
 * @see lombok.EqualsAndHashCode
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings({"UnnecessaryParentheses","unused","SameNameButDifferent"})
public class RestResult<T> extends DefaultResult<T,RestResult<T>> {
    /**
     * <code>STATUS_NAME</code>
     * {@link java.lang.String} <p>the constant <code>STATUS_NAME</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public static final String STATUS_NAME = "status";
    /**
     * <code>MESSAGE_NAME</code>
     * {@link java.lang.String} <p>the constant <code>MESSAGE_NAME</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public static final String MESSAGE_NAME = "message";
    /**
     * <code>DATA_NAME</code>
     * {@link java.lang.String} <p>the constant <code>DATA_NAME</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public static final String DATA_NAME = "data";

    /**
     * <code>time</code>
     * {@link java.util.Date} <p>the <code>time</code> field.</p>
     * @see java.util.Date
     * @see com.fasterxml.jackson.annotation.JsonFormat
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date time;

    /**
     * <code>RestResult</code>
     * Instantiates a new rest result.
     */
    public RestResult() {
    }

    /**
     * <code>RestResult</code>
     * Instantiates a new rest result.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestResult(RestStatus status) {
        super(status.getStatus(),status.getMessage());
        this.time = new Date();
    }


    /**
     * <code>RestResult</code>
     * Instantiates a new rest result.
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public RestResult(Integer status, String message) {
        super(status,message);
        this.time = new Date();
    }

    /**
     * <code>RestResult</code>
     * Instantiates a new rest result.
     * @param builder {@link io.github.nichetoolkit.rest.RestResult.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult.Builder
     */
    public RestResult(RestResult.Builder<T> builder) {
        super(builder.status, builder.message, builder.data);
        this.time = builder.time;
    }

    /**
     * <code>RestResult</code>
     * Instantiates a new rest result.
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param data    {@link T} <p>the data parameter is <code>T</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public RestResult(Integer status, String message, T data) {
        super(status,message,data);
        this.time = new Date();
    }

    /**
     * <code>success</code>
     * <p>the method.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     */
    public static <T> RestResult success() {
        return (new RestResult.Builder<T>()).restStatus(RestErrorStatus.SUCCESS).build();
    }

    /**
     * <code>success</code>
     * <p>the method.</p>
     * @param <T>  {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param data {@link T} <p>the data parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     */
    public static <T> RestResult<T> success(T data) {
        return (new RestResult.Builder<T>()).restStatus(RestErrorStatus.SUCCESS).data(data).build();
    }

    /**
     * <code>success</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @see java.lang.String
     */
    public static <T> RestResult success(String message) {
        return (new RestResult.Builder<T>()).status(RestErrorStatus.SUCCESS).message(message).build();
    }

    /**
     * <code>success</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param data    {@link T} <p>the data parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @see java.lang.String
     */
    public static <T> RestResult<T> success(String message, T data) {
        return (new RestResult.Builder<T>()).status(RestErrorStatus.SUCCESS).message(message).data(data).build();
    }

    /**
     * <code>success</code>
     * <p>the method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static <T> RestResult success(RestStatus status) {
        return (new RestResult.Builder<T>()).restStatus(status).build();
    }

    /**
     * <code>success</code>
     * <p>the method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param data   {@link T} <p>the data parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static <T> RestResult<T> success(RestStatus status, T data) {
        return (new RestResult.Builder<T>()).restStatus(status).data(data).build();
    }

    /**
     * <code>mistake</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @see java.lang.String
     */
    public static <T> RestResult mistake(String message) {
        return (new RestResult.Builder<T>()).status(RestErrorStatus.MISTAKE).message(message).build();
    }

    /**
     * <code>mistake</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param data    {@link T} <p>the data parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @see java.lang.String
     */
    public static <T> RestResult<T> mistake(String message, T data) {
        return (new RestResult.Builder<T>()).status(RestErrorStatus.MISTAKE).message(message).data(data).build();
    }

    /**
     * <code>mistake</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param data    {@link T} <p>the data parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public static <T> RestResult<T> mistake(Integer status, String message, T data) {
        return (new RestResult.Builder<T>()).status(status).message(message).data(data).build();
    }

    /**
     * <code>mistake</code>
     * <p>the method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    public static <T> RestResult mistake(Integer status,Throwable cause) {
        return (new RestResult.Builder<T>()).status(status).message(cause.getMessage()).cause(cause).build();
    }

    /**
     * <code>mistake</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public static <T> RestResult mistake(Integer status, String message) {
        return (new RestResult.Builder<T>()).status(status).message(message).build();
    }

    /**
     * <code>mistake</code>
     * <p>the method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static <T> RestResult mistake(RestStatus status) {
        return (new RestResult.Builder<T>()).restStatus(status).build();
    }

    /**
     * <code>mistake</code>
     * <p>the method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static <T> RestResult mistake(RestStatus status,Throwable cause) {
        return (new RestResult.Builder<T>()).restStatus(status).cause(cause).build();
    }

    /**
     * <code>mistake</code>
     * <p>the method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param data   {@link T} <p>the data parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static <T> RestResult<T> mistake(RestStatus status, T data) {
        return (new RestResult.Builder<T>()).restStatus(status).data(data).build();
    }

    /**
     * <code>defaultBuilder</code>
     * <p>the builder method.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult.Builder} <p>the builder return object is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult.Builder
     */
    public static <T> RestResult.Builder<T> defaultBuilder() {
        return new RestResult.Builder<>();
    }

    /**
     * <code>ok</code>
     * <p>the method.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>the return object is <code>ResponseEntity</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     */
    public static <T> ResponseEntity<RestResult> ok() {
        return ResponseEntity.ok((new RestResult.Builder<T>()).restStatus(RestErrorStatus.SUCCESS).build());
    }

    /**
     * <code>ok</code>
     * <p>the method.</p>
     * @param <T>  {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param data {@link T} <p>the data parameter is <code>T</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>the return object is <code>ResponseEntity</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     */
    public static <T> ResponseEntity<RestResult<T>> ok(T data) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).restStatus(RestErrorStatus.SUCCESS).data(data).build());
    }

    /**
     * <code>ok</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>the return object is <code>ResponseEntity</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.ResponseEntity
     */
    public static <T> ResponseEntity<RestResult> ok(String message) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).status(RestErrorStatus.SUCCESS).message(message).build());
    }

    /**
     * <code>ok</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param data    {@link T} <p>the data parameter is <code>T</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>the return object is <code>ResponseEntity</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.ResponseEntity
     */
    public static <T> ResponseEntity<RestResult<T>> ok(String message, T data) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).status(RestErrorStatus.SUCCESS).message(message).data(data).build());
    }

    /**
     * <code>ok</code>
     * <p>the method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>the return object is <code>ResponseEntity</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.springframework.http.ResponseEntity
     */
    public static <T> ResponseEntity<RestResult> ok(RestStatus status) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).restStatus(status).build());
    }

    /**
     * <code>ok</code>
     * <p>the method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param data   {@link T} <p>the data parameter is <code>T</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>the return object is <code>ResponseEntity</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.springframework.http.ResponseEntity
     */
    public static <T> ResponseEntity<RestResult<T>> ok(RestStatus status, T data) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).restStatus(status).data(data).build());
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>the return object is <code>ResponseEntity</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.ResponseEntity
     */
    public static <T> ResponseEntity<RestResult> error(String message) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).status(RestErrorStatus.MISTAKE).message(message).build());
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param data    {@link T} <p>the data parameter is <code>T</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>the return object is <code>ResponseEntity</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.ResponseEntity
     */
    public static <T> ResponseEntity<RestResult<T>> error(String message, T data) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).status(RestErrorStatus.MISTAKE).message(message).data(data).build());
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param data    {@link T} <p>the data parameter is <code>T</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>the return object is <code>ResponseEntity</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see org.springframework.http.ResponseEntity
     */
    public static <T> ResponseEntity<RestResult<T>> error(Integer status, String message, T data) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).status(status).message(message).data(data).build());
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>the return object is <code>ResponseEntity</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see org.springframework.http.ResponseEntity
     */
    public static <T> ResponseEntity<RestResult> error(Integer status, String message) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).status(status).message(message).build());
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>the return object is <code>ResponseEntity</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.springframework.http.ResponseEntity
     */
    public static <T> ResponseEntity<RestResult> error(RestStatus status) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).restStatus(status).build());
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param data   {@link T} <p>the data parameter is <code>T</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>the return object is <code>ResponseEntity</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.springframework.http.ResponseEntity
     */
    public static <T> ResponseEntity<RestResult<T>> error(RestStatus status, T data) {
        return ResponseEntity.ok((new RestResult.Builder<T>()).restStatus(status).data(data).build());
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.DefaultResult.Builder
     * @since Jdk1.8
     */
    public static class Builder<T> extends DefaultResult.Builder<T,RestResult<T>> {
        /**
         * <code>time</code>
         * {@link java.util.Date} <p>the <code>time</code> field.</p>
         * @see java.util.Date
         */
        protected Date time;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
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

        /**
         * <code>time</code>
         * <p>the method.</p>
         * @param time {@link java.util.Date} <p>the time parameter is <code>Date</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.RestResult.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.util.Date
         */
        public RestResult.Builder<T> time(Date time) {
            this.time = time;
            return this;
        }

        /**
         * <code>time</code>
         * <p>the method.</p>
         * @param time {@link java.lang.Long} <p>the time parameter is <code>Long</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.RestResult.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Long
         */
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
