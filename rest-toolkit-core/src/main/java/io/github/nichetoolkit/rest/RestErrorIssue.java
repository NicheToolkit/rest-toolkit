package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * <code>RestErrorIssue</code>
 * <p>The rest error issue class.</p>
 * @see  io.github.nichetoolkit.rest.DefaultErrorIssue
 * @see  io.github.nichetoolkit.rest.RestStatus
 * @see  lombok.Getter
 * @see  lombok.Setter
 * @see  lombok.experimental.SuperBuilder
 * @see  lombok.EqualsAndHashCode
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RestErrorIssue extends DefaultErrorIssue implements RestStatus {

    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
     * @see  java.lang.Integer
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
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorIssue(RestStatus status) {
        super(status.name(), status.getMessage());
        this.status = status.getStatus();
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestStatus
     * @see  java.lang.String
     */
    public RestErrorIssue(RestStatus status, String message) {
        super(message);
        this.status = status.getStatus();
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestStatus
     * @see  java.lang.Throwable
     */
    public RestErrorIssue(RestStatus status, Throwable cause) {
        super(GeneralUtils.causeMessage(cause.getMessage(),1024));
        this.status = status.getStatus();
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.Integer
     * @see  java.lang.String
     */
    public RestErrorIssue(Integer status, String message) {
        super(message);
        this.status = status;
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @see  java.lang.Integer
     * @see  io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorIssue(Integer status, RestStatus restStatus) {
        super(restStatus.getMessage());
        this.status = status;
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see  java.lang.Integer
     * @see  java.lang.Throwable
     */
    public RestErrorIssue(Integer status, Throwable cause) {
        super(cause.getMessage());
        this.status = status;
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorIssue(String field, RestStatus status) {
        super(field, status.getMessage());
        this.status = status.getStatus();
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorIssue(String field, RestStatus status, String message) {
        super(field, message);
        this.status = status.getStatus();
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestStatus
     * @see  java.lang.Throwable
     */
    public RestErrorIssue(String field, RestStatus status, Throwable cause) {
        super(field, GeneralUtils.causeMessage(cause.getMessage(),1024));
        this.status = status.getStatus();
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Integer
     */
    public RestErrorIssue(String field, Integer status, String message) {
        super(field, message);
        this.status = status;
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Integer
     * @see  java.lang.Throwable
     */
    public RestErrorIssue(String field, Integer status, Throwable cause) {
        super(field, cause.getMessage());
        this.status = status;
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Integer
     */
    public RestErrorIssue(String field, Object value, Integer status, String message) {
        super(field, value, message);
        this.status = status;
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  java.lang.Integer
     * @see  java.lang.Throwable
     */
    public RestErrorIssue(String field, Object value, Integer status, Throwable cause) {
        super(field, value, cause.getMessage());
        this.status = status;
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorIssue(String field, Object value, RestStatus status) {
        super(field, value, status.getMessage());
        this.status = status.getStatus();
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorIssue(String field, Object value, RestStatus status, String message) {
        super(field, value, message);
        this.status = status.getStatus();
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param field {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestStatus
     * @see  java.lang.Throwable
     */
    public RestErrorIssue(String field, Object value, RestStatus status, Throwable cause) {
        super(field, value, cause.getMessage());
        this.status = status.getStatus();
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param fieldError {@link org.springframework.validation.FieldError} <p>The field error parameter is <code>FieldError</code> type.</p>
     * @see  java.lang.Integer
     * @see  org.springframework.validation.FieldError
     */
    public RestErrorIssue(Integer status, FieldError fieldError) {
        super(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
        this.status = status;
    }

    /**
     * <code>RestErrorIssue</code>
     * <p>Instantiates a new rest error issue.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param fieldError {@link org.springframework.validation.FieldError} <p>The field error parameter is <code>FieldError</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestStatus
     * @see  org.springframework.validation.FieldError
     */
    public RestErrorIssue(RestStatus status, FieldError fieldError) {
        super(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
        this.status = status.getStatus();
    }

    @Override
    public String name() {
        return Optional.ofNullable(this.getField()).orElse("io.github.nichetoolkit.rest error issue");
    }

    @Override
    public Integer getKey() {
        return RestStatus.super.getKey();
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
