package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.HttpRequestWrapper;
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
     * @return {@link io.github.nichetoolkit.rest.HttpRequestWrapper} <p>the rest request wrapper return object is <code>HttpRequestWrapper</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see io.github.nichetoolkit.rest.HttpRequestWrapper
     */
    public static HttpRequestWrapper getRestRequestWrapper(HttpServletRequest request) {
        HttpRequestWrapper requestWrapper = null;
        if (request instanceof HttpRequestWrapper) {
            requestWrapper = (HttpRequestWrapper) request;
        } else if (request instanceof StandardMultipartHttpServletRequest) {
            if (((StandardMultipartHttpServletRequest) request).getRequest() instanceof HttpRequestWrapper) {
                requestWrapper = (HttpRequestWrapper) ((StandardMultipartHttpServletRequest) request).getRequest();
            }
        }
        if (requestWrapper == null) {
            requestWrapper = new HttpRequestWrapper(request);
        }
        return requestWrapper;
    }
}
