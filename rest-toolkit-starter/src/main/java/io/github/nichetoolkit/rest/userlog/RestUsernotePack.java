package io.github.nichetoolkit.rest.userlog;

import lombok.Data;

/**
 * <code>RestUsernotePack</code>
 * <p>The type rest usernote pack class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @since Jdk1.8
 */
@Data
public class RestUsernotePack {
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
     * <code>loggingKey</code>
     * {@link java.lang.Integer} <p>the <code>loggingKey</code> field.</p>
     * @see java.lang.Integer
     */
    protected Integer loggingKey;
    /**
     * <code>loggingValue</code>
     * {@link java.lang.String} <p>the <code>loggingValue</code> field.</p>
     * @see java.lang.String
     */
    protected String loggingValue;
    /**
     * <code>loggingType</code>
     * {@link io.github.nichetoolkit.rest.userlog.LoggingType} <p>the <code>loggingType</code> field.</p>
     * @see io.github.nichetoolkit.rest.userlog.LoggingType
     */
    protected LoggingType loggingType;

    /**
     * <code>RestUsernotePack</code>
     * Instantiates a new rest usernote pack.
     */
    public RestUsernotePack() {
    }

    /**
     * <code>RestUsernotePack</code>
     * Instantiates a new rest usernote pack.
     * @param builder {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.RestUsernotePack.Builder
     */
    private RestUsernotePack(Builder builder) {
        this.notelog = builder.notelog;
        this.userlog = builder.userlog;
        this.loggingKey = builder.loggingKey;
        this.loggingValue = builder.loggingValue;
        this.loggingType = builder.loggingType;
    }

    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static class Builder {
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
         * <code>loggingKey</code>
         * {@link java.lang.Integer} <p>the <code>loggingKey</code> field.</p>
         * @see java.lang.Integer
         */
        protected Integer loggingKey;
        /**
         * <code>loggingValue</code>
         * {@link java.lang.String} <p>the <code>loggingValue</code> field.</p>
         * @see java.lang.String
         */
        protected String loggingValue;
        /**
         * <code>loggingType</code>
         * {@link io.github.nichetoolkit.rest.userlog.LoggingType} <p>the <code>loggingType</code> field.</p>
         * @see io.github.nichetoolkit.rest.userlog.LoggingType
         */
        protected LoggingType loggingType;

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
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernotePack.Builder notelog(String notelog) {
            this.notelog = notelog;
            return this;
        }

        /**
         * <code>userlog</code>
         * <p>the method.</p>
         * @param userlog {@link java.lang.String} <p>the userlog parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernotePack.Builder userlog(String userlog) {
            this.userlog = userlog;
            return this;
        }

        /**
         * <code>loggingKey</code>
         * <p>the key method.</p>
         * @param loggingKey {@link java.lang.Integer} <p>the logging key parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack.Builder} <p>the key return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public RestUsernotePack.Builder loggingKey(Integer loggingKey) {
            this.loggingKey = loggingKey;
            return this;
        }

        /**
         * <code>loggingValue</code>
         * <p>the value method.</p>
         * @param loggingValue {@link java.lang.String} <p>the logging value parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack.Builder} <p>the value return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernotePack.Builder loggingValue(String loggingValue) {
            this.loggingValue = loggingValue;
            return this;
        }

        /**
         * <code>loggingType</code>
         * <p>the type method.</p>
         * @param loggingType {@link io.github.nichetoolkit.rest.userlog.LoggingType} <p>the logging type parameter is <code>LoggingType</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack.Builder} <p>the type return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.userlog.LoggingType
         */
        public RestUsernotePack.Builder loggingType(LoggingType loggingType) {
            this.loggingType = loggingType;
            return this;
        }

        /**
         * <code>build</code>
         * <p>the method.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack} <p>the return object is <code>RestUsernotePack</code> type.</p>
         */
        public RestUsernotePack build() {
            return new RestUsernotePack(this);
        }
    }
}
