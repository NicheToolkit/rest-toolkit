package io.github.nichetoolkit.rest.error.network;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>HttpException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class HttpResultDataNullException extends HttpErrorException {

    public HttpResultDataNullException() {
        super(RestErrorStatus.HTTP_RESULT_DATA_NULL);
    }

    public HttpResultDataNullException(RestStatus status) {
        super(status);
    }

    public HttpResultDataNullException(String message) {
        super(message, RestErrorStatus.HTTP_RESULT_DATA_NULL);
    }

    public HttpResultDataNullException(String resource, String message) {
        super(resource,message,RestErrorStatus.HTTP_RESULT_DATA_NULL);
    }

    @Override
    public HttpResultDataNullException get() {
        return new HttpResultDataNullException();
    }
}
