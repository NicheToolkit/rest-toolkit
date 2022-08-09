package io.github.nichetoolkit.rest.userlog;

/**
 * <p>RestUsernote</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
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

    public String getNotelog() {
        return notelog;
    }

    public void setNotelog(String notelog) {
        this.notelog = notelog;
    }

    public String getUserlog() {
        return userlog;
    }

    public void setUserlog(String userlog) {
        this.userlog = userlog;
    }

    public Integer getLogKey() {
        return logKey;
    }

    public void setLogKey(Integer logKey) {
        this.logKey = logKey;
    }

    public String getLogValue() {
        return logValue;
    }

    public void setLogValue(String logValue) {
        this.logValue = logValue;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
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
