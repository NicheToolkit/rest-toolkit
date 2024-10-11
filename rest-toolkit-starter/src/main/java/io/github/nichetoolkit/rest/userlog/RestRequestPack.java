package io.github.nichetoolkit.rest.userlog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * <code>RestRequestPack</code>
 * <p>The type rest request pack class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Getter
 * @see lombok.Setter
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestRequestPack {
    /**
     * <code>headers</code>
     * {@link java.lang.String} <p>The <code>headers</code> field.</p>
     * @see java.lang.String
     */
    protected String headers;
    /**
     * <code>ipAddress</code>
     * {@link java.lang.String} <p>The <code>ipAddress</code> field.</p>
     * @see java.lang.String
     */
    protected String ipAddress;
    /**
     * <code>userAgent</code>
     * {@link java.lang.String} <p>The <code>userAgent</code> field.</p>
     * @see java.lang.String
     */
    protected String userAgent;
    /**
     * <code>method</code>
     * {@link java.lang.String} <p>The <code>method</code> field.</p>
     * @see java.lang.String
     */
    protected String method;
    /**
     * <code>url</code>
     * {@link java.lang.String} <p>The <code>url</code> field.</p>
     * @see java.lang.String
     */
    protected String url;
    /**
     * <code>params</code>
     * {@link java.lang.String} <p>The <code>params</code> field.</p>
     * @see java.lang.String
     */
    protected String params;
    /**
     * <code>body</code>
     * {@link java.lang.String} <p>The <code>body</code> field.</p>
     * @see java.lang.String
     */
    protected String body;
    /**
     * <code>bodyString</code>
     * {@link java.lang.String} <p>The <code>bodyString</code> field.</p>
     * @see java.lang.String
     */
    protected String bodyString;

    /**
     * <code>RestRequestPack</code>
     * <p>Instantiates a new rest request pack.</p>
     */
    public RestRequestPack() {
    }

    /**
     * <code>RestRequestPack</code>
     * <p>Instantiates a new rest request pack.</p>
     * @param builder {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
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
         * {@link java.lang.String} <p>The <code>headers</code> field.</p>
         * @see java.lang.String
         */
        protected String headers;
        /**
         * <code>ipAddress</code>
         * {@link java.lang.String} <p>The <code>ipAddress</code> field.</p>
         * @see java.lang.String
         */
        protected String ipAddress;
        /**
         * <code>userAgent</code>
         * {@link java.lang.String} <p>The <code>userAgent</code> field.</p>
         * @see java.lang.String
         */
        protected String userAgent;
        /**
         * <code>method</code>
         * {@link java.lang.String} <p>The <code>method</code> field.</p>
         * @see java.lang.String
         */
        protected String method;
        /**
         * <code>url</code>
         * {@link java.lang.String} <p>The <code>url</code> field.</p>
         * @see java.lang.String
         */
        protected String url;
        /**
         * <code>params</code>
         * {@link java.lang.String} <p>The <code>params</code> field.</p>
         * @see java.lang.String
         */
        protected String params;
        /**
         * <code>body</code>
         * {@link java.lang.String} <p>The <code>body</code> field.</p>
         * @see java.lang.String
         */
        protected String body;
        /**
         * <code>bodyString</code>
         * {@link java.lang.String} <p>The <code>bodyString</code> field.</p>
         * @see java.lang.String
         */
        protected String bodyString;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>headers</code>
         * <p>The method.</p>
         * @param headers {@link java.lang.String} <p>The headers parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder headers(String headers) {
            this.headers = headers;
            return this;
        }

        /**
         * <code>ipAddress</code>
         * <p>The address method.</p>
         * @param ipAddress {@link java.lang.String} <p>The ip address parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>The address return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        /**
         * <code>userAgent</code>
         * <p>The agent method.</p>
         * @param userAgent {@link java.lang.String} <p>The user agent parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>The agent return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        /**
         * <code>method</code>
         * <p>The method.</p>
         * @param method {@link java.lang.String} <p>The method parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder method(String method) {
            this.method = method;
            return this;
        }

        /**
         * <code>url</code>
         * <p>The method.</p>
         * @param url {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder url(String url) {
            this.url = url;
            return this;
        }

        /**
         * <code>params</code>
         * <p>The method.</p>
         * @param params {@link java.lang.String} <p>The params parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder params(String params) {
            this.params = params;
            return this;
        }

        /**
         * <code>body</code>
         * <p>The method.</p>
         * @param body {@link java.lang.String} <p>The body parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder body(String body) {
            this.body = body;
            return this;
        }

        /**
         * <code>bodyString</code>
         * <p>The string method.</p>
         * @param bodyString {@link java.lang.String} <p>The body string parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack.Builder} <p>The string return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestRequestPack.Builder bodyString(String bodyString) {
            this.bodyString = bodyString;
            return this;
        }

        /**
         * <code>build</code>
         * <p>The method.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack} <p>The return object is <code>RestRequestPack</code> type.</p>
         */
        public RestRequestPack build() {
            return new RestRequestPack(this);
        }
    }
}
