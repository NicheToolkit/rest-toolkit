package io.github.nichetoolkit.rest.interceptor;

import io.github.nichetoolkit.rest.util.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>RestInterceptHolder</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class RestInterceptHolder {

    public static String getRequestParam(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> requestMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            requestMap.put(entry.getKey(), entry.getValue()[0]);
        }
        return JsonUtils.parseJson(requestMap);
    }

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
