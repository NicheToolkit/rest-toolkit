package io.github.nichetoolkit.rest;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>DefaultErrorIssue</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
class DefaultErrorIssue implements Serializable {
    private String field;
    private Object value;
    private String issue;

    protected DefaultErrorIssue() {
    }

    protected DefaultErrorIssue(DefaultErrorIssue.Builder builder) {
        this.field = builder.field;
        this.value = builder.value;
        this.issue = builder.issue;
    }

    protected DefaultErrorIssue(String issue) {
        this.issue = issue;
    }

    protected DefaultErrorIssue(String field, String issue) {
        this.field = field;
        this.issue = issue;
    }

    protected DefaultErrorIssue(String field, Object value, String issue) {
        this.field = field;
        this.value = value;
        this.issue = issue;
    }

    public static abstract class Builder {
        protected String field;
        protected Object value;
        protected String issue;

        public Builder() {
        }

        public DefaultErrorIssue.Builder field(String field) {
            this.field = field;
            return this;
        }

        public DefaultErrorIssue.Builder value(Object value) {
            this.value = value;
            return this;
        }

        public DefaultErrorIssue.Builder issue(String issue) {
            this.issue = issue;
            return this;
        }

        abstract DefaultErrorIssue build();
    }
}
