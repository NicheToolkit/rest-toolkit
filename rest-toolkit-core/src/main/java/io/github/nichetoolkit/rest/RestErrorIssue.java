package io.github.nichetoolkit.rest;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * <code>RestErrorIssue</code>
 * <p>The rest error issue class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.DefaultErrorIssue
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see lombok.Getter
 * @see lombok.Setter
 * @see lombok.EqualsAndHashCode
 * @since Jdk1.8
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RestErrorIssue extends DefaultErrorIssue implements RestStatus {

    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
     * @see java.lang.Integer
     */
    protected Integer status;

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     */
    public RestErrorIssue() {
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param builder {@link io.github.nichetoolkit.rest.RestErrorIssue.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorIssue.Builder
     */
    public RestErrorIssue(RestErrorIssue.Builder builder) {
        super(builder);
        this.status = builder.status;
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorIssue(RestStatus status) {
        super(status.name(),status.getMessage());
        this.status = status.getStatus();
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public RestErrorIssue(RestStatus status, String message) {
        super(message);
        this.status = status.getStatus();
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public RestErrorIssue(Integer status, String message) {
        super(message);
        this.status = status;
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param status     {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorIssue(Integer status, RestStatus restStatus) {
        super(restStatus.getMessage());
        this.status = status;
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field  {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorIssue(String field, RestStatus status) {
        super(field, status.getMessage());
        this.status = status.getStatus();
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorIssue(String field, RestStatus status, String message) {
        super(field, message);
        this.status = status.getStatus();
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     */
    public RestErrorIssue(String field, Integer status,  String message) {
        super(field, message);
        this.status = status;
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value   {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Integer
     */
    public RestErrorIssue(String field, Object value, Integer status, String message) {
        super(field, value, message);
        this.status = status;
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field  {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value  {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorIssue(String field, Object value, RestStatus status) {
        super(field, value, status.getMessage());
        this.status = status.getStatus();
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value   {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorIssue(String field, Object value, RestStatus status, String message) {
        super(field, value, message);
        this.status = status.getStatus();
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.DefaultErrorIssue.Builder
     * @since Jdk1.8
     */
    public static class Builder extends DefaultErrorIssue.Builder {
        /**
         * <code>status</code>
         * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
         * @see java.lang.Integer
         */
        protected Integer status;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>status</code>
         * <p>The status method.</p>
         * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.RestErrorIssue.Builder} <p>The status return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public RestErrorIssue.Builder status(Integer status) {
            this.status = status;
            return this;
        }

        @Override
        public RestErrorIssue.Builder field(String field) {
            this.field = field;
            return this;
        }

        @Override
        public RestErrorIssue.Builder value(Object value) {
            this.value = value;
            return this;
        }

        @Override
        public RestErrorIssue.Builder issue(String issue) {
            this.issue = issue;
            return this;
        }

        @Override
        public RestErrorIssue build() {
            return new RestErrorIssue(this);
        }
    }

    @Override
    public String name() {
        return Optional.ofNullable(this.getField()).orElse("io.github.nichetoolkit.rest error issue");
    }

    @Override
    public String getMessage() {
        return this.getIssue();
    }

    @Override
    public Map<Integer, String> entry() {
        return Collections.singletonMap(this.getStatus(), getIssue());
    }
}
