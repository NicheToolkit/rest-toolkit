package io.github.nichetoolkit.rest.userlog;

import lombok.Data;

/**
 * <code>RestUsernote</code>
 * <p>The type rest usernote class.</p>
 * @param <T> {@link io.github.nichetoolkit.rest.userlog.RestUsernote} <p>the generic parameter is <code>RestUsernote</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @since Jdk1.8
 */
@Data
public class RestUsernote<T extends RestUsernote<T>> {
    /**
     * <code>notelog</code>
     * {@link java.lang.String} <p>the <code>notelog</code> field.</p>
     * @see java.lang.String
     */
    protected String notelog;
    /**
     * <code>userlog</code>
     * {@link java.lang.String} <p>the <code>userlog</code> field.</p>
     * @see java.lang.String
     */
    protected String userlog;
    /**
     * <code>logKey</code>
     * {@link java.lang.Integer} <p>the <code>logKey</code> field.</p>
     * @see java.lang.Integer
     */
    protected Integer logKey;
    /**
     * <code>logValue</code>
     * {@link java.lang.String} <p>the <code>logValue</code> field.</p>
     * @see java.lang.String
     */
    protected String logValue;
    /**
     * <code>logType</code>
     * {@link io.github.nichetoolkit.rest.userlog.LogType} <p>the <code>logType</code> field.</p>
     * @see io.github.nichetoolkit.rest.userlog.LogType
     */
    protected LogType logType;

    /**
     * <code>RestUsernote</code>
     * Instantiates a new rest usernote.
     */
    public RestUsernote() {
    }

    /**
     * <code>RestUsernote</code>
     * Instantiates a new rest usernote.
     * @param builder {@link io.github.nichetoolkit.rest.userlog.RestUsernote.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.RestUsernote.Builder
     */
    private RestUsernote(RestUsernote.Builder<T> builder) {
        this.notelog = builder.notelog;
        this.userlog = builder.userlog;
        this.logKey = builder.logKey;
        this.logValue = builder.logValue;
        this.logType = builder.logType;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @param <T> {@link io.github.nichetoolkit.rest.userlog.RestUsernote} <p>the generic parameter is <code>RestUsernote</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder<T extends RestUsernote<T>> {
        /**
         * <code>notelog</code>
         * {@link java.lang.String} <p>the <code>notelog</code> field.</p>
         * @see java.lang.String
         */
        protected String notelog;
        /**
         * <code>userlog</code>
         * {@link java.lang.String} <p>the <code>userlog</code> field.</p>
         * @see java.lang.String
         */
        protected String userlog;
        /**
         * <code>logKey</code>
         * {@link java.lang.Integer} <p>the <code>logKey</code> field.</p>
         * @see java.lang.Integer
         */
        protected Integer logKey;
        /**
         * <code>logValue</code>
         * {@link java.lang.String} <p>the <code>logValue</code> field.</p>
         * @see java.lang.String
         */
        protected String logValue;
        /**
         * <code>logType</code>
         * {@link io.github.nichetoolkit.rest.userlog.LogType} <p>the <code>logType</code> field.</p>
         * @see io.github.nichetoolkit.rest.userlog.LogType
         */
        protected LogType logType;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
        }

        /**
         * <code>notelog</code>
         * <p>the method.</p>
         * @param notelog {@link java.lang.String} <p>the notelog parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernote.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernote.Builder<T> notelog(String notelog) {
            this.notelog = notelog;
            return this;
        }

        /**
         * <code>userlog</code>
         * <p>the method.</p>
         * @param userlog {@link java.lang.String} <p>the userlog parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernote.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernote.Builder<T> userlog(String userlog) {
            this.userlog = userlog;
            return this;
        }

        /**
         * <code>logKey</code>
         * <p>the key method.</p>
         * @param logKey {@link java.lang.Integer} <p>the log key parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernote.Builder} <p>the key return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public RestUsernote.Builder<T> logKey(Integer logKey) {
            this.logKey = logKey;
            return this;
        }

        /**
         * <code>logValue</code>
         * <p>the value method.</p>
         * @param logValue {@link java.lang.String} <p>the log value parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernote.Builder} <p>the value return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernote.Builder<T> logValue(String logValue) {
            this.logValue = logValue;
            return this;
        }

        /**
         * <code>logType</code>
         * <p>the type method.</p>
         * @param logType {@link io.github.nichetoolkit.rest.userlog.LogType} <p>the log type parameter is <code>LogType</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernote.Builder} <p>the type return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.userlog.LogType
         */
        public RestUsernote.Builder<T> logType(LogType logType) {
            this.logType = logType;
            return this;
        }

        /**
         * <code>build</code>
         * <p>the method.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernote} <p>the return object is <code>RestUsernote</code> type.</p>
         */
        public RestUsernote<T> build() {
            return new RestUsernote<>(this);
        }
    }
}
