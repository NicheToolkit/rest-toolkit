package io.github.nichetoolkit.rest.error.network;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.RestStatus;
import org.springframework.http.ResponseEntity;

/**
 * <p>HttpException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class HttpResultFailException extends HttpErrorException {

    public HttpResultFailException() {
        super(RestErrorStatus.HTTP_RESULT_FAIL);
    }

    public HttpResultFailException(RestStatus status) {
        super(status);
    }

    public HttpResultFailException(String message) {
        super(RestErrorStatus.HTTP_RESULT_FAIL,message);
    }

    public HttpResultFailException(Integer status, String message) {
        super(status,message);
    }

    public HttpResultFailException(Integer status,String resource, String message) {
        super(status,resource,message);
    }

    public HttpResultFailException(Integer status, String message, Throwable cause) {
        super(status,message,cause);
    }

    public HttpResultFailException(Integer status, String resource, String message, Throwable cause) {
        super(status,resource,message,cause);
    }

    public HttpResultFailException(RestResult restResult) {
        super(restResult.getStatus(),restResult.getMessage(),restResult.getError());
    }

    public HttpResultFailException(ResponseEntity responseEntity) {
        super(responseEntity.getStatusCodeValue(),responseEntity.toString());
    }

    public HttpResultFailException(String resource,RestResult restResult) {
        super(restResult.getStatus(),resource,restResult.getMessage(),restResult.getError());
    }

    public HttpResultFailException(String resource, ResponseEntity responseEntity) {
        super(responseEntity.getStatusCodeValue(),resource,responseEntity.toString());
    }

    @Override
    public HttpResultFailException get() {
        return new HttpResultFailException();
    }
}
