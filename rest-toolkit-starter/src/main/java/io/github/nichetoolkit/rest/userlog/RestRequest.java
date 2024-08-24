package io.github.nichetoolkit.rest.userlog;

import lombok.Data;

/**
 * <p>RestRequest</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
public class RestRequest<T extends RestRequest<T>> {
    protected String headers;
    protected String ipAddress;
    protected String userAgent;
    protected String method;
    protected String url;
    protected String params;
    protected String body;
    protected String bodyString;

    public RestRequest() {
    }

    private RestRequest(RestRequest.Builder<T> builder) {
        this.ipAddress = builder.ipAddress;
        this.userAgent = builder.userAgent;
        this.method = builder.method;
        this.url = builder.url;
        this.params = builder.params;
        this.body = builder.body;
        this.bodyString = builder.bodyString;
    }

    public static class Builder<T extends RestRequest<T>> {
        protected String headers;
        protected String ipAddress;
        protected String userAgent;
        protected String method;
        protected String url;
        protected String params;
        protected String body;
        protected String bodyString;

        public Builder() {
        }

        public RestRequest.Builder<T> headers(String headers) {
            this.headers = headers;
            return this;
        }

        public RestRequest.Builder<T> ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public RestRequest.Builder<T> userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public RestRequest.Builder<T> method(String method) {
            this.method = method;
            return this;
        }

        public RestRequest.Builder<T> url(String url) {
            this.url = url;
            return this;
        }

        public RestRequest.Builder<T> params(String params) {
            this.params = params;
            return this;
        }

        public RestRequest.Builder<T> body(String body) {
            this.body = body;
            return this;
        }

        public RestRequest.Builder<T> bodyString(String bodyString) {
            this.bodyString = bodyString;
            return this;
        }

        public RestRequest<T> build() {
            return new RestRequest<>(this);
        }
    }
}
