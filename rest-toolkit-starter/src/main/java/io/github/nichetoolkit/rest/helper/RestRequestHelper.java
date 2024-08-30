package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.RestHttpRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <code>RestRequestHelper</code>
 * <p>The type rest request helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class RestRequestHelper {

    /**
     * <code>getRestRequestWrapper</code>
     * <p>the rest request wrapper getter method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>the request parameter is <code>HttpServletRequest</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the rest request wrapper return object is <code>RestHttpRequest</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     */
    public static RestHttpRequest getRestRequestWrapper(HttpServletRequest request) {
        RestHttpRequest requestWrapper = null;
        if (request instanceof RestHttpRequest) {
            requestWrapper = (RestHttpRequest) request;
        } else if (request instanceof StandardMultipartHttpServletRequest) {
            if (((StandardMultipartHttpServletRequest) request).getRequest() instanceof RestHttpRequest) {
                requestWrapper = (RestHttpRequest) ((StandardMultipartHttpServletRequest) request).getRequest();
            }
        }
        if (requestWrapper == null) {
            requestWrapper = new RestHttpRequest(request);
        }
        return requestWrapper;
    }
}
