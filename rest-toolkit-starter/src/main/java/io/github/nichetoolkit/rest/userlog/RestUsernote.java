package io.github.nichetoolkit.rest.userlog;

import lombok.Data;

/**
 * <p>RestUsernote</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
public class RestUsernote<T extends RestUsernote<T>> {
    protected String notelog;
    protected String userlog;
    protected Integer logKey;
    protected String logValue;
    protected LogType logType;

    public RestUsernote() {
    }

    private RestUsernote(RestUsernote.Builder<T> builder) {
        this.notelog = builder.notelog;
        this.userlog = builder.userlog;
        this.logKey = builder.logKey;
        this.logValue = builder.logValue;
        this.logType = builder.logType;
    }

    public static class Builder<T extends RestUsernote<T>> {
        protected String notelog;
        protected String userlog;
        protected Integer logKey;
        protected String logValue;
        protected LogType logType;

        public Builder() {
        }

        public RestUsernote.Builder<T> notelog(String notelog) {
            this.notelog = notelog;
            return this;
        }

        public RestUsernote.Builder<T> userlog(String userlog) {
            this.userlog = userlog;
            return this;
        }

        public RestUsernote.Builder<T> logKey(Integer logKey) {
            this.logKey = logKey;
            return this;
        }

        public RestUsernote.Builder<T> logValue(String logValue) {
            this.logValue = logValue;
            return this;
        }

        public RestUsernote.Builder<T> logType(LogType logType) {
            this.logType = logType;
            return this;
        }

        public RestUsernote<T> build() {
            return new RestUsernote<>(this);
        }
    }
}
