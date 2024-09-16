package io.github.nichetoolkit.rest.userlog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * <code>RestRequestPack</code>
 * <p>The type rest request pack class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @since Jdk1.8
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestRequestPack {
    /**
     * <code>headers</code>
     * {@link java.lang.String} <p>the <code>headers</code> field.</p>
     * @see java.lang.String
     */
    protected String headers;
    /**
     * <code>ipAddress</code>
     * {@link java.lang.String} <p>the <code>ipAddress</code> field.</p>
     * @see java.lang.String
     */
    protected String ipAddress;
    /**
     * <code>userAgent</code>
     * {@link java.lang.String} <p>the <code>userAgent</code> field.</p>
     * @see java.lang.String
     */
    protected String userAgent;
    /**
     * <code>method</code>
     * {@link java.lang.String} <p>the <code>method</code> field.</p>
     * @see java.lang.String
     */
    protected String method;
    /**
     * <code>url</code>
     * {@link java.lang.String} <p>the <code>url</code> field.</p>
     * @see java.lang.String
     */
    protected String url;
    /**
     * <code>params</code>
     * {@link java.lang.String} <p>the <code>params</code> field.</p>
     * @see java.lang.String
     */
    protected String params;
    /**
     * <code>body</code>
     * {@link java.lang.String} <p>the <code>body</code> field.</p>
     * @see java.lang.String
     */
    protected String body;
    /**
     * <code>bodyString</code>
     * {@link java.lang.String} <p>the <code>bodyString</code> field.</p>
     * @see java.lang.String
     */
    protected String bodyString;

    /**
     * <code>RestRequestPack</code>
     * Instantiates a new rest request pack.
     */
    public RestRequestPack() {
    }

    /**
     * <code>RestRequestPack</code>
     * Instantiates a new rest request pack.
     * @param builder {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder
     */
    private RestRequestPack(Builder builder) {
        this.ipAddress = builder.ipAddress;
        this.userAgent = builder.userAgent;
        this.method = builder.method;
        this.url = builder.url;
        this.params = builder.params;
        this.body = builder.body;
        this.bodyString = builder.bodyString;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder {
        /**
         * <code>headers</code>
         * {@link java.lang.String} <p>the <code>headers</code> field.</p>
         * @see java.lang.String
         */
        protected String headers;
        /**
         * <code>ipAddress</code>
         * {@link java.lang.String} <p>the <code>ipAddress</code> field.</p>
         * @see java.lang.String
         */
        protected String ipAddress;
        /**
         * <code>userAgent</code>
         * {@link java.lang.String} <p>the <code>userAgent</code> field.</p>
         * @see java.lang.String
         */
        protected String userAgent;
        /**
         * <code>method</code>
         * {@link java.lang.String} <p>the <code>method</code> field.</p>
         * @see java.lang.String
         */
        protected String method;
        /**
         * <code>url</code>
         * {@link java.lang.String} <p>the <code>url</code> field.</p>
         * @see java.lang.String
         */
        protected String url;
        /**
         * <code>params</code>
         * {@link java.lang.String} <p>the <code>params</code> field.</p>
         * @see java.lang.String
         */
        protected String params;
        /**
         * <code>body</code>
         * {@link java.lang.String} <p>the <code>body</code> field.</p>
         * @see java.lang.String
         */
        protected String body;
        /**
         * <code>bodyString</code>
         * {@link java.lang.String} <p>the <code>bodyString</code> field.</p>
         * @see java.lang.String
         */
        protected String bodyString;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>headers</code>
         * <p>the method.</p>
         * @param headers {@link java.lang.String} <p>the headers parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder headers(String headers) {
            this.headers = headers;
            return this;
        }

        /**
         * <code>ipAddress</code>
         * <p>the address method.</p>
         * @param ipAddress {@link java.lang.String} <p>the ip address parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>the address return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        /**
         * <code>userAgent</code>
         * <p>the agent method.</p>
         * @param userAgent {@link java.lang.String} <p>the user agent parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>the agent return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        /**
         * <code>method</code>
         * <p>the method.</p>
         * @param method {@link java.lang.String} <p>the method parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder method(String method) {
            this.method = method;
            return this;
        }

        /**
         * <code>url</code>
         * <p>the method.</p>
         * @param url {@link java.lang.String} <p>the url parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder url(String url) {
            this.url = url;
            return this;
        }

        /**
         * <code>params</code>
         * <p>the method.</p>
         * @param params {@link java.lang.String} <p>the params parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder params(String params) {
            this.params = params;
            return this;
        }

        /**
         * <code>body</code>
         * <p>the method.</p>
         * @param body {@link java.lang.String} <p>the body parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder body(String body) {
            this.body = body;
            return this;
        }

        /**
         * <code>bodyString</code>
         * <p>the string method.</p>
         * @param bodyString {@link java.lang.String} <p>the body string parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>the string return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder bodyString(String bodyString) {
            this.bodyString = bodyString;
            return this;
        }

        /**
         * <code>build</code>
         * <p>the method.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack} <p>the return object is <code>RestRequestPack</code> type.</p>
         */
        public RestRequestPack build() {
            return new RestRequestPack(this);
        }
    }
}
