package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>RestRequestHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class RestRequestHelper {
    
    public static RestRequestWrapper getRestRequestWrapper(HttpServletRequest request) {
        RestRequestWrapper requestWrapper = null;
        if (request instanceof RestRequestWrapper) {
            requestWrapper = (RestRequestWrapper) request;
        } else if (request instanceof StandardMultipartHttpServletRequest) {
            if (((StandardMultipartHttpServletRequest) request).getRequest() instanceof RestRequestWrapper) {
                requestWrapper = (RestRequestWrapper) ((StandardMultipartHttpServletRequest) request).getRequest();
            }
        }
        if (requestWrapper == null) {
            requestWrapper = new RestRequestWrapper(request);
        }
        return requestWrapper;
    }
}
