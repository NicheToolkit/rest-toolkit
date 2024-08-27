package io.github.nichetoolkit.rest.userlog;

import io.github.nichetoolkit.rest.RestResult;
import lombok.Data;

/**
 * <code>RestResponse</code>
 * <p>The type rest response class.</p>
 * @param <T> {@link io.github.nichetoolkit.rest.userlog.RestResponse} <p>the generic parameter is <code>RestResponse</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @since Jdk1.8
 */
@Data
public class RestResponse<T extends RestResponse<T>> {
    /**
     * <code>time</code>
     * {@link java.lang.Long} <p>the <code>time</code> field.</p>
     * @see java.lang.Long
     */
    protected Long time;
    /**
     * <code>startTime</code>
     * {@link java.lang.Long} <p>the <code>startTime</code> field.</p>
     * @see java.lang.Long
     */
    protected Long startTime;
    /**
     * <code>endTime</code>
     * {@link java.lang.Long} <p>the <code>endTime</code> field.</p>
     * @see java.lang.Long
     */
    protected Long endTime;
    /**
     * <code>costTime</code>
     * {@link java.lang.Long} <p>the <code>costTime</code> field.</p>
     * @see java.lang.Long
     */
    protected Long costTime;
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
     * <code>error</code>
     * {@link java.lang.String} <p>the <code>error</code> field.</p>
     * @see java.lang.String
     */
    protected String error;
    /**
     * <code>method</code>
     * {@link java.lang.String} <p>the <code>method</code> field.</p>
     * @see java.lang.String
     */
    protected String method;
    /**
     * <code>mediaType</code>
     * {@link java.lang.String} <p>the <code>mediaType</code> field.</p>
     * @see java.lang.String
     */
    protected String mediaType;
    /**
     * <code>result</code>
     * {@link java.lang.String} <p>the <code>result</code> field.</p>
     * @see java.lang.String
     */
    protected String result;
    /**
     * <code>resultString</code>
     * {@link java.lang.String} <p>the <code>resultString</code> field.</p>
     * @see java.lang.String
     */
    protected String resultString;
    /**
     * <code>restResult</code>
     * {@link io.github.nichetoolkit.rest.RestResult} <p>the <code>restResult</code> field.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     */
    protected RestResult restResult;
    /**
     * <code>data</code>
     * {@link java.lang.String} <p>the <code>data</code> field.</p>
     * @see java.lang.String
     */
    protected String data;

    /**
     * <code>RestResponse</code>
     * Instantiates a new rest response.
     */
    public RestResponse() {
    }

    /**
     * <code>RestResponse</code>
     * Instantiates a new rest response.
     * @param builder {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.RestResponse.Builder
     */
    private RestResponse(RestResponse.Builder<T> builder) {
        this.time = builder.time;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.costTime = builder.costTime;
        this.status = builder.status;
        this.message = builder.message;
        this.error = builder.error;
        this.method = builder.method;
        this.mediaType = builder.mediaType;
        this.result = builder.result;
        this.resultString = builder.resultString;
        this.restResult = builder.restResult;
        this.data = builder.data;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <T> {@link io.github.nichetoolkit.rest.userlog.RestResponse} <p>the generic parameter is <code>RestResponse</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder<T extends RestResponse<T>> {
        /**
         * <code>time</code>
         * {@link java.lang.Long} <p>the <code>time</code> field.</p>
         * @see java.lang.Long
         */
        protected Long time;
        /**
         * <code>startTime</code>
         * {@link java.lang.Long} <p>the <code>startTime</code> field.</p>
         * @see java.lang.Long
         */
        protected Long startTime;
        /**
         * <code>endTime</code>
         * {@link java.lang.Long} <p>the <code>endTime</code> field.</p>
         * @see java.lang.Long
         */
        protected Long endTime;
        /**
         * <code>costTime</code>
         * {@link java.lang.Long} <p>the <code>costTime</code> field.</p>
         * @see java.lang.Long
         */
        protected Long costTime;
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
         * <code>error</code>
         * {@link java.lang.String} <p>the <code>error</code> field.</p>
         * @see java.lang.String
         */
        protected String error;
        /**
         * <code>method</code>
         * {@link java.lang.String} <p>the <code>method</code> field.</p>
         * @see java.lang.String
         */
        protected String method;
        /**
         * <code>mediaType</code>
         * {@link java.lang.String} <p>the <code>mediaType</code> field.</p>
         * @see java.lang.String
         */
        protected String mediaType;
        /**
         * <code>result</code>
         * {@link java.lang.String} <p>the <code>result</code> field.</p>
         * @see java.lang.String
         */
        protected String result;
        /**
         * <code>resultString</code>
         * {@link java.lang.String} <p>the <code>resultString</code> field.</p>
         * @see java.lang.String
         */
        protected String resultString;
        /**
         * <code>restResult</code>
         * {@link io.github.nichetoolkit.rest.RestResult} <p>the <code>restResult</code> field.</p>
         * @see io.github.nichetoolkit.rest.RestResult
         */
        protected RestResult restResult;
        /**
         * <code>data</code>
         * {@link java.lang.String} <p>the <code>data</code> field.</p>
         * @see java.lang.String
         */
        protected String data;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>time</code>
         * <p>the method.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the return object is <code>Builder</code> type.</p>
         */
        public RestResponse.Builder<T> time() {
            this.time = System.currentTimeMillis();
            return this;
        }

        /**
         * <code>time</code>
         * <p>the method.</p>
         * @param time {@link java.lang.Long} <p>the time parameter is <code>Long</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Long
         */
        public RestResponse.Builder<T> time(Long time) {
            this.time = time;
            return this;
        }

        /**
         * <code>startTime</code>
         * <p>the time method.</p>
         * @param startTime {@link java.lang.Long} <p>the start time parameter is <code>Long</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the time return object is <code>Builder</code> type.</p>
         * @see java.lang.Long
         */
        public RestResponse.Builder<T> startTime(Long startTime) {
            this.startTime = startTime;
            return this;
        }

        /**
         * <code>endTime</code>
         * <p>the time method.</p>
         * @param endTime {@link java.lang.Long} <p>the end time parameter is <code>Long</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the time return object is <code>Builder</code> type.</p>
         * @see java.lang.Long
         */
        public RestResponse.Builder<T> endTime(Long endTime) {
            this.endTime = endTime;
            return this;
        }

        /**
         * <code>costTime</code>
         * <p>the time method.</p>
         * @param costTime {@link java.lang.Long} <p>the cost time parameter is <code>Long</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the time return object is <code>Builder</code> type.</p>
         * @see java.lang.Long
         */
        public RestResponse.Builder<T> costTime(Long costTime) {
            this.costTime = costTime;
            return this;
        }


        /**
         * <code>status</code>
         * <p>the method.</p>
         * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public RestResponse.Builder<T> status(Integer status) {
            this.status = status;
            return this;
        }

        /**
         * <code>error</code>
         * <p>the method.</p>
         * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestResponse.Builder<T> error(String error) {
            this.error = error;
            return this;
        }

        /**
         * <code>message</code>
         * <p>the method.</p>
         * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestResponse.Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        /**
         * <code>mediaType</code>
         * <p>the type method.</p>
         * @param mediaType {@link java.lang.String} <p>the media type parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the type return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestResponse.Builder<T> mediaType(String mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        /**
         * <code>method</code>
         * <p>the method.</p>
         * @param method {@link java.lang.String} <p>the method parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestResponse.Builder<T> method(String method) {
            this.method = method;
            return this;
        }

        /**
         * <code>result</code>
         * <p>the method.</p>
         * @param result {@link java.lang.String} <p>the result parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestResponse.Builder<T> result(String result) {
            this.result = result;
            return this;
        }

        /**
         * <code>resultString</code>
         * <p>the string method.</p>
         * @param resultString {@link java.lang.String} <p>the result string parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the string return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestResponse.Builder<T> resultString(String resultString) {
            this.resultString = resultString;
            return this;
        }

        /**
         * <code>restResult</code>
         * <p>the result method.</p>
         * @param restResult {@link io.github.nichetoolkit.rest.RestResult} <p>the rest result parameter is <code>RestResult</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the result return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestResult
         */
        public RestResponse.Builder<T> restResult(RestResult restResult) {
            this.restResult = restResult;
            return this;
        }


        /**
         * <code>data</code>
         * <p>the method.</p>
         * @param data {@link java.lang.String} <p>the data parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestResponse.Builder<T> data(String data) {
            this.data = data;
            return this;
        }

        /**
         * <code>build</code>
         * <p>the method.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestResponse} <p>the return object is <code>RestResponse</code> type.</p>
         */
        public RestResponse<T> build() {
            return new RestResponse<>(this);
        }
    }
}
