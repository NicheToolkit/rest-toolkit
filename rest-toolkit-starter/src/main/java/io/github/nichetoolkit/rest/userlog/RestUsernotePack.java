package io.github.nichetoolkit.rest.userlog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * <code>RestUsernotePack</code>
 * <p>The type rest usernote pack class.</p>
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
public class RestUsernotePack {
    /**
     * <code>notelog</code>
     * {@link java.lang.String} <p>The <code>notelog</code> field.</p>
     * @see java.lang.String
     */
    protected String notelog;
    /**
     * <code>userlog</code>
     * {@link java.lang.String} <p>The <code>userlog</code> field.</p>
     * @see java.lang.String
     */
    protected String userlog;
    /**
     * <code>loggingKey</code>
     * {@link java.lang.String} <p>The <code>loggingKey</code> field.</p>
     * @see java.lang.String
     */
    protected String loggingKey;
    /**
     * <code>loggingValue</code>
     * {@link java.lang.String} <p>The <code>loggingValue</code> field.</p>
     * @see java.lang.String
     */
    protected String loggingValue;
    /**
     * <code>loggingType</code>
     * {@link io.github.nichetoolkit.rest.userlog.LoggingType} <p>The <code>loggingType</code> field.</p>
     * @see io.github.nichetoolkit.rest.userlog.LoggingType
     */
    protected LoggingType loggingType;

    /**
     * <code>RestUsernotePack</code>
     * <p>Instantiates a new rest usernote pack.</p>
     */
    public RestUsernotePack() {
    }

    /**
     * <code>RestUsernotePack</code>
     * <p>Instantiates a new rest usernote pack.</p>
     * @param builder {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
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
         * {@link java.lang.String} <p>The <code>notelog</code> field.</p>
         * @see java.lang.String
         */
        protected String notelog;
        /**
         * <code>userlog</code>
         * {@link java.lang.String} <p>The <code>userlog</code> field.</p>
         * @see java.lang.String
         */
        protected String userlog;
        /**
         * <code>loggingKey</code>
         * {@link java.lang.String} <p>The <code>loggingKey</code> field.</p>
         * @see java.lang.String
         */
        protected String loggingKey;
        /**
         * <code>loggingValue</code>
         * {@link java.lang.String} <p>The <code>loggingValue</code> field.</p>
         * @see java.lang.String
         */
        protected String loggingValue;
        /**
         * <code>loggingType</code>
         * {@link io.github.nichetoolkit.rest.userlog.LoggingType} <p>The <code>loggingType</code> field.</p>
         * @see io.github.nichetoolkit.rest.userlog.LoggingType
         */
        protected LoggingType loggingType;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>notelog</code>
         * <p>The method.</p>
         * @param notelog {@link java.lang.String} <p>The notelog parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernotePack.Builder notelog(String notelog) {
            this.notelog = notelog;
            return this;
        }

        /**
         * <code>userlog</code>
         * <p>The method.</p>
         * @param userlog {@link java.lang.String} <p>The userlog parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernotePack.Builder userlog(String userlog) {
            this.userlog = userlog;
            return this;
        }

        /**
         * <code>loggingKey</code>
         * <p>The key method.</p>
         * @param loggingKey {@link java.lang.String} <p>The logging key parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack.Builder} <p>The key return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernotePack.Builder loggingKey(String loggingKey) {
            this.loggingKey = loggingKey;
            return this;
        }

        /**
         * <code>loggingValue</code>
         * <p>The value method.</p>
         * @param loggingValue {@link java.lang.String} <p>The logging value parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack.Builder} <p>The value return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestUsernotePack.Builder loggingValue(String loggingValue) {
            this.loggingValue = loggingValue;
            return this;
        }

        /**
         * <code>loggingType</code>
         * <p>The type method.</p>
         * @param loggingType {@link io.github.nichetoolkit.rest.userlog.LoggingType} <p>The logging type parameter is <code>LoggingType</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack.Builder} <p>The type return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.userlog.LoggingType
         */
        public RestUsernotePack.Builder loggingType(LoggingType loggingType) {
            this.loggingType = loggingType;
            return this;
        }

        /**
         * <code>build</code>
         * <p>The method.</p>
         * @return {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack} <p>The return object is <code>RestUsernotePack</code> type.</p>
         */
        public RestUsernotePack build() {
            return new RestUsernotePack(this);
        }
    }
}
