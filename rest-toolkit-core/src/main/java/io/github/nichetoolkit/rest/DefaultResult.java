package io.github.nichetoolkit.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

/**
 * <code>DefaultResult</code>
 * <p>The type default result class.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <S> {@link io.github.nichetoolkit.rest.DefaultResult} <p>the generic parameter is <code>DefaultResult</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Data
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Data
@SuppressWarnings({"UnnecessaryParentheses","unused"})
class DefaultResult<T,S extends DefaultResult<T,S>> implements Serializable {
    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>the <code>status</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer status;
    /**
     * <code>message</code>
     * {@link java.lang.String} <p>the <code>message</code> field.</p>
     * @see java.lang.String
     */
    private String message;
    /**
     * <code>data</code>
     * <p>the <code>data</code> field.</p>
     * @see com.fasterxml.jackson.annotation.JsonInclude
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    /**
     * <code>error</code>
     * {@link io.github.nichetoolkit.rest.DefaultError} <p>the <code>error</code> field.</p>
     * @see io.github.nichetoolkit.rest.DefaultError
     */
    private DefaultError error;


    /**
     * <code>DefaultResult</code>
     * Instantiates a new default result.
     */
    protected DefaultResult() {
    }

    /**
     * <code>DefaultResult</code>
     * Instantiates a new default result.
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    protected DefaultResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * <code>DefaultResult</code>
     * Instantiates a new default result.
     * @param builder {@link io.github.nichetoolkit.rest.DefaultResult.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rest.DefaultResult.Builder
     */
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

    /**
     * <code>DefaultResult</code>
     * Instantiates a new default result.
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param data    T <p>the data parameter is <code>T</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    protected DefaultResult(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * <code>isSuccess</code>
     * <p>the success method.</p>
     * @return boolean <p>the success return object is <code>boolean</code> type.</p>
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public boolean isSuccess() {
        return Optional.ofNullable(this.status).map(RestErrorStatus.SUCCESS.getStatus()::equals).orElse(false);
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <S> {@link io.github.nichetoolkit.rest.DefaultResult} <p>the generic parameter is <code>DefaultResult</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static abstract class Builder<T,S extends DefaultResult<T,S>> {
        /**
         * <code>status</code>
         * {@link java.lang.Integer} <p>the <code>status</code> field.</p>
         * @see java.lang.Integer
         */
        protected Integer status;
        /**
         * <code>message</code>
         * {@link java.lang.String} <p>the <code>message</code> field.</p>
         * @see java.lang.String
         */
        protected String message;
        /**
         * <code>data</code>
         * <p>the <code>data</code> field.</p>
         */
        protected T data;
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
         * <code>restStatus</code>
         * <p>the status method.</p>
         * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultResult.Builder} <p>the status return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestStatus
         */
        public DefaultResult.Builder<T,S> restStatus(RestStatus status) {
            this.status = status.getStatus();
            this.message = status.getMessage();
            return this;
        }

        /**
         * <code>status</code>
         * <p>the method.</p>
         * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultResult.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public DefaultResult.Builder<T,S> status(Integer status) {
            this.status = status;
            return this;
        }

        /**
         * <code>status</code>
         * <p>the method.</p>
         * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultResult.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestStatus
         */
        public DefaultResult.Builder<T,S> status(RestStatus status) {
            this.status = status.getStatus();
            return this;
        }

        /**
         * <code>message</code>
         * <p>the method.</p>
         * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultResult.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public DefaultResult.Builder<T,S> message(String message) {
            this.message = message;
            return this;
        }

        /**
         * <code>message</code>
         * <p>the method.</p>
         * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultResult.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestStatus
         */
        public DefaultResult.Builder<T,S> message(RestStatus status) {
            this.message = status.getMessage();
            return this;
        }

        /**
         * <code>data</code>
         * <p>the method.</p>
         * @param data T <p>the data parameter is <code>T</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultResult.Builder} <p>the return object is <code>Builder</code> type.</p>
         */
        public DefaultResult.Builder<T,S> data(T data) {
            this.data = data;
            return this;
        }

        /**
         * <code>cause</code>
         * <p>the method.</p>
         * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultResult.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Throwable
         */
        public DefaultResult.Builder<T,S> cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        /**
         * <code>build</code>
         * <p>the method.</p>
         * @return {@link io.github.nichetoolkit.rest.DefaultResult} <p>the return object is <code>DefaultResult</code> type.</p>
         */
        abstract DefaultResult<T,S> build();
    }

}
