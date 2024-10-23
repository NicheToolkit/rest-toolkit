package io.github.nichetoolkit.rest.interceptor;

import io.github.nichetoolkit.rest.util.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <code>RestRequestInterceptHolder</code>
 * <p>The rest request intercept holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class RestRequestInterceptHolder {

    /**
     * <code>getRequestParam</code>
     * <p>The get request param getter method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @return {@link java.lang.String} <p>The get request param return object is <code>String</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see java.lang.String
     */
    public static String getRequestParam(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> requestMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            requestMap.put(entry.getKey(), entry.getValue()[0]);
        }
        return JsonUtils.parseJson(requestMap);
    }

    /**
     * <code>getRequestBody</code>
     * <p>The get request body getter method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @return {@link java.lang.String} <p>The get request body return object is <code>String</code> type.</p>
     * @throws IOException {@link java.io.IOException} <p>The io exception is <code>IOException</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see java.lang.String
     * @see java.io.IOException
     */
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();
        String string;
        while ((string = bufferedReader.readLine()) != null) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
}
