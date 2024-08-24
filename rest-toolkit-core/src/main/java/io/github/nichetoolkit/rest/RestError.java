package io.github.nichetoolkit.rest;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.function.Supplier;

/**
 * <p>RestError</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("SameNameButDifferent")
public class RestError extends DefaultError implements RestStatus, Supplier<RestError> {
    private String name;
    private Integer status;

    public RestError() {
        super(RestErrorStatus.UNKNOWN_ERROR.getMessage());
        this.name = RestErrorStatus.UNKNOWN_ERROR.name();
        this.status = RestErrorStatus.UNKNOWN_ERROR.getStatus();
    }

    public RestError(Supplier<RestStatus> supplier) {
        super(supplier.get().getMessage());
        this.name = supplier.get().name();
        this.status = supplier.get().getStatus();
    }

    public RestError(RestStatus status) {
        super(status.getMessage());
        this.name = status.name();
        this.status = status.getStatus();
    }

    public RestError(String message) {
        super(message);
        this.name = RestErrorStatus.UNKNOWN_ERROR.name();
        this.status = RestErrorStatus.UNKNOWN_ERROR.getStatus();
    }

    public RestError(Integer status) {
        super(RestErrorStatus.UNKNOWN_ERROR.getMessage());
        this.name = RestErrorStatus.UNKNOWN_ERROR.name();
        this.status = status;
    }

    public RestError(Throwable cause) {
        super(cause.getMessage(), cause);
        this.name = cause.getClass().getName();
        this.status = RestErrorStatus.UNKNOWN_ERROR.getStatus();
    }

    public RestError(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public RestError(Integer status, RestStatus restStatus) {
        super(restStatus.getMessage());
        this.name = restStatus.name();
        this.status = status;
    }

    public RestError(String message, RestStatus status) {
        super(message);
        this.name = status.name();
        this.status = status.getStatus();
    }

    public RestError(Integer status, String message, String name, Integer domain) {
        super(message, domain);
        this.name = name;
        this.status = status;
    }

    public RestError(Integer status, String message, String name) {
        super(message);
        this.name = name;
        this.status = status;
    }

    public RestError(String message, Throwable cause) {
        super(message, cause);
    }

    public RestError(RestStatus status, Throwable cause) {
        super(status.getMessage(), cause);
        this.name = status.name();
        this.status = status.getStatus();
    }

    public RestError(RestStatus status, String message, Throwable cause) {
        super(message, cause);
        this.name = status.name();
        this.status = status.getStatus();
    }

    public RestError(Integer status, String message, String name, Throwable cause) {
        super(message, cause);
        this.name = name;
        this.status = status;
    }

    public RestError(Integer status, String message, String name, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.name = name;
        this.status = status;
    }

    public RestError(Integer status, String message, String name, Integer domain, Throwable cause) {
        super(message, domain, cause);
        this.name = name;
        this.status = status;
    }

    public RestError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String name, Integer domain, Integer status) {
        super(message, cause, enableSuppression, writableStackTrace, name, domain);
        this.status = status;
    }

    public RestError(Integer status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public RestError(Integer status, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }

    public RestError(RestError.Builder builder) {
        super(builder);
        this.name = builder.name;
        this.status = builder.status;
    }

    public static RestError parser(RestStatus status) {
        if (status instanceof RestError) {
            return (RestError) status;
        } else {
            return RestError.error(status);
        }
    }

    public static RestError parser(String message, RestStatus status) {
        if (status instanceof RestError) {
            return (RestError) status;
        } else {
            return RestError.error(message, status);
        }
    }

    public static RestError parser(Integer status, RestStatus restStatus) {
        if (restStatus instanceof RestError) {
            return (RestError) restStatus;
        } else {
            return RestError.error(status, restStatus);
        }
    }

    public static RestError parser(Integer status, String message, RestStatus restStatus) {
        if (restStatus instanceof RestError) {
            return (RestError) restStatus;
        } else {
            return RestError.error(status, message);
        }
    }


    public static RestError parser(Throwable cause) {
        if (cause instanceof RestError) {
            return (RestError) cause;
        } else if (cause instanceof RestException) {
            return RestError.error((RestStatus) cause);
        } else {
            return RestError.error();
        }
    }

    public static RestError parser(String message, Throwable cause) {
        if (cause instanceof RestError) {
            return (RestError) cause;
        } else if (cause instanceof RestException) {
            return RestError.error((RestStatus) cause);
        } else {
            return RestError.error(message);
        }
    }

    public static RestError parser(Integer status, Throwable cause) {
        if (cause instanceof RestError) {
            return (RestError) cause;
        } else if (cause instanceof RestException) {
            return RestError.error((RestStatus) cause);
        } else {
            return RestError.error(status, cause.getMessage());
        }
    }

    public static RestError parser(Integer status, String message, Throwable cause) {
        if (cause instanceof RestError) {
            return (RestError) cause;
        } else if (cause instanceof RestException) {
            return RestError.error((RestStatus) cause);
        } else {
            return RestError.error(status, message);
        }
    }

    public static RestError error() {
        return (new RestError.Builder(RestErrorStatus.UNKNOWN_ERROR)).add(new RestErrorIssue(RestErrorStatus.UNKNOWN_ERROR)).build();
    }

    public static RestError error(Throwable cause) {
        return (new RestError.Builder(RestErrorStatus.UNKNOWN_ERROR)).message(cause).add(new RestErrorIssue(RestErrorStatus.UNKNOWN_ERROR)).cause(cause).build();
    }

    public static RestError error(String field) {
        return (new Builder(RestErrorStatus.UNKNOWN_ERROR)).resource(field).add(new RestErrorIssue(field, RestErrorStatus.UNKNOWN_ERROR)).build();
    }

    public static RestError error(String field, Throwable cause) {
        return (new RestError.Builder(RestErrorStatus.UNKNOWN_ERROR)).resource(field).message(cause).add(new RestErrorIssue(field, RestErrorStatus.UNKNOWN_ERROR)).cause(cause).build();
    }

    public static RestError error(String field, String error) {
        return (new RestError.Builder(RestErrorStatus.UNKNOWN_ERROR)).resource(field).message(error).add(new RestErrorIssue(field, RestErrorStatus.UNKNOWN_ERROR, error)).build();
    }

    public static RestError error(String field, String error, Throwable cause) {
        return (new RestError.Builder(RestErrorStatus.UNKNOWN_ERROR)).resource(field).message(error).add(new RestErrorIssue(field, RestErrorStatus.UNKNOWN_ERROR, error)).cause(cause).build();
    }

    public static RestError error(String field, Object value, String error) {
        return (new RestError.Builder(RestErrorStatus.UNKNOWN_ERROR)).resource(field).message(error).add(new RestErrorIssue(field, value, RestErrorStatus.UNKNOWN_ERROR, error)).build();
    }

    public static RestError error(String field, Object value, String error, Throwable cause) {
        return (new RestError.Builder(RestErrorStatus.UNKNOWN_ERROR)).resource(field).message(error).add(new RestErrorIssue(field, value, RestErrorStatus.UNKNOWN_ERROR, error)).cause(cause).build();
    }

    public static RestError error(RestStatus status) {
        return (new RestError.Builder(status)).add(new RestErrorIssue(status)).build();
    }

    public static RestError error(RestStatus status, Throwable cause) {
        return (new RestError.Builder(status)).message(cause).add(new RestErrorIssue(status)).cause(cause).build();
    }

    public static RestError error(String field, RestStatus status) {
        return (new RestError.Builder(status)).resource(field).add(new RestErrorIssue(field, status)).build();
    }

    public static RestError error(String field, RestStatus status, Throwable cause) {
        return (new RestError.Builder(status)).resource(field).message(cause).add(new RestErrorIssue(field, status)).cause(cause).build();
    }

    public static RestError error(Integer status, String error) {
        return (new Builder()).status(status).message(error).add(new RestErrorIssue(status, error)).build();
    }

    public static RestError error(Integer status, String error, Throwable cause) {
        return (new Builder()).status(status).message(error).add(new RestErrorIssue(status, error)).cause(cause).build();
    }


    public static RestError error(Integer status, RestStatus restStatus) {
        return (new Builder(restStatus)).status(status).add(new RestErrorIssue(status, restStatus)).build();
    }

    public static RestError error(Integer status, RestStatus restStatus, Throwable cause) {
        return (new Builder(restStatus)).status(status).message(cause).add(new RestErrorIssue(status, restStatus)).cause(cause).build();
    }

    public static RestError error(RestStatus status, String error) {
        return (new RestError.Builder(status)).message(error).add(new RestErrorIssue(status, error)).build();
    }

    public static RestError error(RestStatus status, String error, Throwable cause) {
        return (new RestError.Builder(status)).message(error).add(new RestErrorIssue(status, error)).cause(cause).build();
    }

    public static RestError error(String field, Integer status, String error) {
        return (new RestError.Builder()).status(status).resource(field).message(error).add(new RestErrorIssue(field, status, error)).build();
    }

    public static RestError error(String field, Integer status, String error, Throwable cause) {
        return (new RestError.Builder()).status(status).resource(field).message(error).add(new RestErrorIssue(field, status, error)).cause(cause).build();
    }

    public static RestError error(String field, Object value, Integer status, String error) {
        return (new RestError.Builder()).status(status).resource(field).message(error).add(new RestErrorIssue(field, value, status, error)).build();
    }

    public static RestError error(String field, Object value, Integer status, String error, Throwable cause) {
        return (new RestError.Builder()).status(status).resource(field).message(error).add(new RestErrorIssue(field, value, status, error)).cause(cause).build();
    }

    public static RestError error(String field, RestStatus status, String error) {
        return (new RestError.Builder(status)).resource(field).message(error).add(new RestErrorIssue(field, status, error)).build();
    }

    public static RestError error(String field, RestStatus status, String error, Throwable cause) {
        return (new RestError.Builder(status)).resource(field).message(error).add(new RestErrorIssue(field, status, error)).cause(cause).build();
    }

    public static RestError error(String field, RestStatus restStatus, Integer status, String error) {
        return (new Builder(restStatus)).status(status).message(error).resource(field).add(new RestErrorIssue(field, status, error)).build();
    }

    public static RestError error(String field, RestStatus restStatus, Integer status, String error, Throwable cause) {
        return (new Builder(restStatus)).status(status).message(error).resource(field).add(new RestErrorIssue(field, status, error)).cause(cause).build();
    }

    public static RestError error(String field, Object value, RestStatus status) {
        return (new RestError.Builder(status)).resource(field).add(new RestErrorIssue(field, value, status)).build();
    }

    public static RestError error(String field, Object value, RestStatus status, Throwable cause) {
        return (new RestError.Builder(status)).resource(field).message(cause).add(new RestErrorIssue(field, value, status)).cause(cause).build();
    }

    public static RestError error(String field, Object value, RestStatus status, String error) {
        return (new RestError.Builder(status)).resource(field).message(error).add(new RestErrorIssue(field, value, status, error)).build();
    }

    public static RestError error(String field, Object value, RestStatus status, String error, Throwable cause) {
        return (new RestError.Builder(status)).resource(field).message(error).add(new RestErrorIssue(field, value, status, error)).cause(cause).build();
    }

    public static RestError error(String field, Object value, RestStatus restStatus, Integer status, String error) {
        return (new Builder(restStatus)).status(status).resource(field).message(error).add(new RestErrorIssue(field, value, status, error)).build();
    }

    public static RestError error(String field, Object value, RestStatus restStatus, Integer status, String error, Throwable cause) {
        return (new Builder(restStatus)).status(status).resource(field).message(error).add(new RestErrorIssue(field, value, status, error)).cause(cause).build();
    }

    public static RestError error(String resource, String field, RestStatus restStatus) {
        return (new RestError.Builder(restStatus)).resource(resource).add(new RestErrorIssue(field, restStatus)).build();
    }

    public static RestError error(String resource, String field, RestStatus restStatus, Throwable cause) {
        return (new RestError.Builder(restStatus)).resource(resource).message(cause).add(new RestErrorIssue(field, restStatus)).cause(cause).build();
    }

    public static RestError error(String resource, String field, RestStatus restStatus, String error) {
        return (new RestError.Builder(restStatus)).resource(resource).message(error).add(new RestErrorIssue(field, restStatus, error)).build();
    }

    public static RestError error(String resource, String field, RestStatus restStatus, String error, Throwable cause) {
        return (new RestError.Builder(restStatus)).resource(resource).message(error).add(new RestErrorIssue(field, restStatus, error)).cause(cause).build();
    }

    public static RestError error(String resource, String field, Object value, RestStatus restStatus) {
        return (new RestError.Builder(restStatus)).resource(resource).add(new RestErrorIssue(field, value, restStatus)).build();
    }

    public static RestError error(String resource, String field, Object value, RestStatus restStatus, Throwable cause) {
        return (new Builder(restStatus)).resource(resource).message(cause).add(new RestErrorIssue(field, value, restStatus)).cause(cause).build();
    }

    public static RestError error(String resource, String field, Object value, RestStatus restStatus, String error) {
        return (new RestError.Builder(restStatus)).resource(resource).message(error).add(new RestErrorIssue(field, value, restStatus, error)).build();
    }

    public static RestError error(String resource, String field, Object value, RestStatus restStatus, String error, Throwable cause) {
        return (new RestError.Builder(restStatus)).resource(resource).message(error).add(new RestErrorIssue(field, value, restStatus, error)).cause(cause).build();
    }

    public static RestError error(String resource, String field, RestStatus restStatus, Integer status, String error) {
        return (new RestError.Builder(restStatus)).status(status).resource(resource).message(error).add(new RestErrorIssue(field, status, error)).build();
    }

    public static RestError error(String resource, String field, RestStatus restStatus, Integer status, String error, Throwable cause) {
        return (new RestError.Builder(restStatus)).status(status).resource(resource).message(error).add(new RestErrorIssue(field, status, error)).cause(cause).build();
    }

    public static RestError error(String resource, String field, Object value, RestStatus restStatus, Integer status, String error) {
        return (new RestError.Builder(restStatus)).status(status).resource(resource).message(error).add(new RestErrorIssue(field, value, status, error)).build();
    }

    public static RestError error(String resource, String field, Object value, RestStatus restStatus, Integer status, String error, Throwable cause) {
        return (new RestError.Builder(restStatus)).status(status).resource(resource).message(error).add(new RestErrorIssue(field, value, status, error)).cause(cause).build();
    }

    @Override
    public String name() {
        return this.getName();
    }

    @Override
    public RestError get() {
        return new RestError();
    }


    public static class Builder extends DefaultError.Builder {
        protected String name;
        protected Integer status;

        public Builder() {
            super();
        }

        private Builder(RestStatus status) {
            super(status);
            this.name = status.name();
            this.status = status.getStatus();
        }

        public RestError.Builder name(String name) {
            this.name = name;
            return this;
        }

        public RestError.Builder name(RestStatus status) {
            this.name = status.name();
            return this;
        }

        public RestError.Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public RestError.Builder status(RestStatus status) {
            this.status = status.getStatus();
            return this;
        }

        @Override
        public RestError.Builder message(String message) {
            super.message(message);
            return this;
        }

        @Override
        public RestError.Builder message(RestStatus status) {
            super.message(status.getMessage());
            return this;
        }

        @Override
        public RestError.Builder message(Throwable cause) {
            super.message(cause);
            return this;
        }

        @Override
        public RestError.Builder debug(String debug) {
            super.debug(debug);
            return this;
        }

        @Override
        public RestError.Builder resource(String resource) {
            super.resource(resource);
            return this;
        }

        @Override
        public RestError.Builder add(RestErrorIssue issue) {
            super.add(issue);
            return this;
        }

        @Override
        public RestError.Builder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        @Override
        public RestError build() {
            return new RestError(this);
        }
    }
}
