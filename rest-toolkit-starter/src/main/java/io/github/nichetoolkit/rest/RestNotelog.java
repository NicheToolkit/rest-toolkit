package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.log.LogType;

/**
 * <p>RestNotelog</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class RestNotelog<T extends RestNotelog<T>> {
    protected String title;
    protected String message;
    protected Integer key;
    protected String value;
    protected LogType logType;

    public RestNotelog() {
    }

    private RestNotelog(RestNotelog.Builder<T> builder) {
        this.title = builder.title;
        this.message = builder.message;
        this.key = builder.key;
        this.value = builder.value;
        this.logType = builder.logType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public static class Builder<T extends RestNotelog<T>> {
        protected String title;
        protected String message;
        protected Integer key;
        protected String value;
        protected LogType logType;

        public Builder() {
        }

        public RestNotelog.Builder<T> title(String title) {
            this.title = title;
            return this;
        }

        public RestNotelog.Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public RestNotelog.Builder<T> key(Integer key) {
            this.key = key;
            return this;
        }

        public RestNotelog.Builder<T> value(String value) {
            this.value = value;
            return this;
        }

        public RestNotelog.Builder<T> logType(LogType logType) {
            this.logType = logType;
            return this;
        }

        public RestNotelog<T> build() {
            return new RestNotelog<>(this);
        }
    }
}
