package io.github.nichetoolkit.rest.userlog;

import io.github.nichetoolkit.rest.RestResult;

/**
 * <p>RestResponse</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class RestResponse<T extends RestResponse<T>> {
    protected Long time;
    protected Long startTime;
    protected Long endTime;
    protected Long costTime;
    protected Integer status;
    protected String message;
    protected String error;
    protected String method;
    protected String mediaType;
    protected String result;
    protected String resultString;
    protected RestResult restResult;
    protected String data;

    public RestResponse() {
    }

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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public RestResult getRestResult() {
        return restResult;
    }

    public void setRestResult(RestResult restResult) {
        this.restResult = restResult;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static class Builder<T extends RestResponse<T>> {
        protected Long time;
        protected Long startTime;
        protected Long endTime;
        protected Long costTime;
        protected Integer status;
        protected String message;
        protected String error;
        protected String method;
        protected String mediaType;
        protected String result;
        protected String resultString;
        protected RestResult restResult;
        protected String data;

        public Builder() {
        }

        public RestResponse.Builder<T> time() {
            this.time = System.currentTimeMillis();
            return this;
        }

        public RestResponse.Builder<T> time(Long time) {
            this.time = time;
            return this;
        }

        public RestResponse.Builder<T> startTime(Long startTime) {
            this.startTime = startTime;
            return this;
        }

        public RestResponse.Builder<T> endTime(Long endTime) {
            this.endTime = endTime;
            return this;
        }

        public RestResponse.Builder<T> costTime(Long costTime) {
            this.costTime = costTime;
            return this;
        }


        public RestResponse.Builder<T> status(Integer status) {
            this.status = status;
            return this;
        }

        public RestResponse.Builder<T> error(String error) {
            this.error = error;
            return this;
        }

        public RestResponse.Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public RestResponse.Builder<T> mediaType(String mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        public RestResponse.Builder<T> method(String method) {
            this.method = method;
            return this;
        }

        public RestResponse.Builder<T> result(String result) {
            this.result = result;
            return this;
        }

        public RestResponse.Builder<T> resultString(String resultString) {
            this.resultString = resultString;
            return this;
        }

        public RestResponse.Builder<T> restResult(RestResult restResult) {
            this.restResult = restResult;
            return this;
        }


        public RestResponse.Builder<T> data(String data) {
            this.data = data;
            return this;
        }

        public RestResponse<T> build() {
            return new RestResponse<>(this);
        }
    }
}
