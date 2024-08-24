package io.github.nichetoolkit.rest;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * <p>RestErrorIssue</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RestErrorIssue extends DefaultErrorIssue implements RestStatus {

    protected Integer status;

    public RestErrorIssue() {
    }

    public RestErrorIssue(RestErrorIssue.Builder builder) {
        super(builder);
        this.status = builder.status;
    }

    public RestErrorIssue(RestStatus status) {
        super(status.name(),status.getMessage());
        this.status = status.getStatus();
    }

    public RestErrorIssue(RestStatus status, String message) {
        super(message);
        this.status = status.getStatus();
    }

    public RestErrorIssue(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public RestErrorIssue(Integer status, RestStatus restStatus) {
        super(restStatus.getMessage());
        this.status = status;
    }

    public RestErrorIssue(String field, RestStatus status) {
        super(field, status.getMessage());
        this.status = status.getStatus();
    }

    public RestErrorIssue(String field, RestStatus status, String message) {
        super(field, message);
        this.status = status.getStatus();
    }

    public RestErrorIssue(String field, Integer status,  String message) {
        super(field, message);
        this.status = status;
    }

    public RestErrorIssue(String field, Object value, Integer status, String message) {
        super(field, value, message);
        this.status = status;
    }

    public RestErrorIssue(String field, Object value, RestStatus status) {
        super(field, value, status.getMessage());
        this.status = status.getStatus();
    }

    public RestErrorIssue(String field, Object value, RestStatus status, String message) {
        super(field, value, message);
        this.status = status.getStatus();
    }

    public static class Builder extends DefaultErrorIssue.Builder {
        protected Integer status;

        public Builder() {
        }

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
