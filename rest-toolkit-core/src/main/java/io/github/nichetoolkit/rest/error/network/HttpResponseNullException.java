package io.github.nichetoolkit.rest.error.network;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>ResponseNullException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class HttpResponseNullException extends HttpErrorException {

    public HttpResponseNullException() {
        super(RestErrorStatus.HTTP_RESPONSE_NULL);
    }

    public HttpResponseNullException(RestStatus status) {
        super(status);
    }

    public HttpResponseNullException(String message) {
        super(message, RestErrorStatus.HTTP_RESPONSE_NULL);
    }

    public HttpResponseNullException(String resource, String message) {
        super(resource,message,RestErrorStatus.HTTP_RESPONSE_NULL);
    }

    @Override
    public HttpResponseNullException get() {
        return new HttpResponseNullException();
    }
}
