package io.github.nichetoolkit.rest;


import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.StreamUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * <code>RestHttpRequest</code>
 * <p>The type rest http request class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see javax.servlet.http.HttpServletRequestWrapper
 * @see java.io.Closeable
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class RestHttpRequest extends HttpServletRequestWrapper implements Closeable {

    /**
     * <code>REQUEST_ID_KEY</code>
     * {@link java.lang.String} <p>the constant <code>REQUEST_ID_KEY</code> field.</p>
     * @see java.lang.String
     */
    private static final String REQUEST_ID_KEY = "_REQUEST_ID_KEY_";

    /**
     * <code>HANDLER_METHOD_KEY</code>
     * {@link java.lang.String} <p>the constant <code>HANDLER_METHOD_KEY</code> field.</p>
     * @see java.lang.String
     */
    private static final String HANDLER_METHOD_KEY = "HANDLER_METHOD_KEY";

    /**
     * <code>cacheBody</code>
     * <p>the <code>cacheBody</code> field.</p>
     */
    private byte[] cacheBody;

    /**
     * <code>reader</code>
     * {@link java.io.BufferedReader} <p>the <code>reader</code> field.</p>
     * @see java.io.BufferedReader
     */
    private BufferedReader reader;

    /**
     * <code>inputStream</code>
     * {@link javax.servlet.ServletInputStream} <p>the <code>inputStream</code> field.</p>
     * @see javax.servlet.ServletInputStream
     */
    private ServletInputStream inputStream;

    /**
     * <code>paramsJson</code>
     * {@link java.lang.String} <p>the <code>paramsJson</code> field.</p>
     * @see java.lang.String
     */
    private String paramsJson;

    /**
     * <code>paramsMap</code>
     * {@link java.util.Map} <p>the <code>paramsMap</code> field.</p>
     * @see java.util.Map
     */
    private Map<String, Object> paramsMap;

    /**
     * <code>handlerMethods</code>
     * {@link java.util.List} <p>the <code>handlerMethods</code> field.</p>
     * @see java.util.List
     * @see lombok.Getter
     */
    @Getter
    private List<HandlerMethod> handlerMethods;

    /**
     * <code>RestHttpRequest</code>
     * Instantiates a new rest http request.
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>the request parameter is <code>HttpServletRequest</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     */
    public RestHttpRequest(HttpServletRequest request) {
        super(request);
    }

    /**
     * <code>cacheBody</code>
     * <p>the body method.</p>
     * @param request {@link javax.servlet.ServletRequest} <p>the request parameter is <code>ServletRequest</code> type.</p>
     * @see javax.servlet.ServletRequest
     */
    private void cacheBody(ServletRequest request) {
        if (GeneralUtils.isEmpty(this.cacheBody)) {
            try {
                this.cacheBody = StreamUtils.copyToByteArray(request.getInputStream());
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
            this.inputStream = new RequestCachingInputStream(cacheBody);
        }
    }

    /**
     * <code>getCacheBody</code>
     * <p>the cache body method.</p>
     * @return byte <p>the cache body return object is <code>byte</code> type.</p>
     */
    public byte[] getCacheBody() {
        if (cacheBody != null) {
            return cacheBody;
        }
        cacheBody(getRequest());
        return cacheBody;
    }

    @Override
    public BufferedReader getReader()throws IOException {
        if (reader == null) {
            reader = new BufferedReader(new InputStreamReader(inputStream, getCharacterEncoding()));
        }
        return reader;
    }

    @Override
    public ServletInputStream getInputStream() {
        if (inputStream != null) {
            return inputStream;
        }
        cacheBody(getRequest());
        return this.inputStream;
    }

    @Override
    public void close() throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }
    }

    /**
     * <code>getRequestURI</code>
     * <p>the request uri getter method.</p>
     * @param isNoContext boolean <p>the is no context parameter is <code>boolean</code> type.</p>
     * @return {@link java.lang.String} <p>the request uri return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public String getRequestURI(boolean isNoContext) {
        String url = super.getRequestURI();
        if (isNoContext) {
            return url.replaceAll(getContextPath(), "");
        }
        return url;
    }

    /**
     * <code>getParamsJson</code>
     * <p>the params json getter method.</p>
     * @return {@link java.lang.String} <p>the params json return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getParamsJson() {
        if (this.paramsJson != null) {
            return this.paramsJson;
        }
        this.paramsJson = JsonUtils.parseJson(getParamsMap());
        return this.paramsJson;
    }

    /**
     * <code>getParamsMap</code>
     * <p>the params map getter method.</p>
     * @return {@link java.util.Map} <p>the params map return object is <code>Map</code> type.</p>
     * @see java.util.Map
     */
    public Map<String, Object> getParamsMap() {
        if (this.paramsMap != null) {
            return this.paramsMap;
        }
        cacheBody(getRequest());
        cacheMap();
        return Collections.unmodifiableMap(this.paramsMap);
    }

    /**
     * <code>getRequestId</code>
     * <p>the request id getter method.</p>
     * @return {@link java.lang.String} <p>the request id return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getRequestId() {
        Object requestId = getAttribute(REQUEST_ID_KEY);
        if (requestId == null) {
            requestId = GeneralUtils.uuid();
        }
        return requestId.toString();
    }

    /**
     * <code>setRequestId</code>
     * <p>the request id setter method.</p>
     * @param requestId {@link java.lang.String} <p>the request id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setRequestId(String requestId) {
        setAttribute(REQUEST_ID_KEY, requestId);
    }

    /**
     * <code>setHandlerMethods</code>
     * <p>the handler methods setter method.</p>
     * @param handlerMethod {@link org.springframework.web.method.HandlerMethod} <p>the handler method parameter is <code>HandlerMethod</code> type.</p>
     * @see org.springframework.web.method.HandlerMethod
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    public void setHandlerMethods(HandlerMethod handlerMethod) {
        if (handlerMethods == null) {
            handlerMethods = (List<HandlerMethod>) getAttribute(HANDLER_METHOD_KEY);
        }
        if (handlerMethods == null) {
            handlerMethods = new ArrayList<>();
            setAttribute(HANDLER_METHOD_KEY, handlerMethods);
        }
        handlerMethods.add(handlerMethod);
    }

    /**
     * <code>getUrlParams</code>
     * <p>the url params getter method.</p>
     * @return {@link java.util.List} <p>the url params return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<String> getUrlParams() {
        List<String> paramList = new ArrayList<>(5);
        String queryString = getQueryString();
        if (queryString != null) {
            String splitChar = "&";
            String equalChar = "=";
            String[] params = queryString.split(splitChar);
            for (String param : params) {
                paramList.add(param.split(equalChar)[0]);
            }
        }
        return paramList;
    }

    /**
     * <code>getMethodAnnotation</code>
     * <p>the method annotation getter method.</p>
     * @param <A>            {@link java.lang.annotation.Annotation} <p>the generic parameter is <code>Annotation</code> type.</p>
     * @param annotationType {@link java.lang.Class} <p>the annotation type parameter is <code>Class</code> type.</p>
     * @return A <p>the method annotation return object is <code>A</code> type.</p>
     * @see java.lang.annotation.Annotation
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    public <A extends Annotation> A getMethodAnnotation(Class<A> annotationType) {
        Object attribute = getAttribute(HANDLER_METHOD_KEY);
        if (GeneralUtils.isEmpty(attribute)) {
            return null;
        }
        List<HandlerMethod> handlerMethodList = (List<HandlerMethod>) attribute;
        if (GeneralUtils.isEmpty(handlerMethodList)) {
            return null;
        }
        for(HandlerMethod handlerMethod : handlerMethodList) {
            A annotation = AnnotationUtils.getAnnotation(handlerMethod.getMethod(), annotationType);
            if (annotation != null) {
                return annotation;
            }
        }
        return  null;
    }

    /**
     * <code>isSupportedJson</code>
     * <p>the supported json method.</p>
     * @param value {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @return boolean <p>the supported json return object is <code>boolean</code> type.</p>
     * @see java.lang.String
     */
    private boolean isSupportedJson(String value) {
        if (GeneralUtils.isEmpty(value)) {
            return false;
        }
        value = value.trim();
        /* 大括号或者中括号开头的都算json字符串 */
        return value.startsWith("{") || value.startsWith("[");
    }

    /**
     * <code>cacheMap</code>
     * <p>the map method.</p>
     */
    private void cacheMap() {
        if (this.paramsMap != null) {
            return;
        }
        String requestBody = null;
        try {
            requestBody = new String(cacheBody, getCharacterEncoding());
        } catch (UnsupportedEncodingException exception) {
            log.info("the request params parse error, request body: {}, error: {}", getContentType(), exception.getMessage());
        }
        if (isSupportedJson(requestBody)) {
            this.paramsMap = JsonUtils.parseMap(requestBody, String.class, Object.class);
        }
        Map<String, String[]> parameterMap = super.getParameterMap();
        if (this.paramsMap == null) {
            this.paramsMap = new HashMap<>(parameterMap.size());
        }
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            /* url中的请求参数不能覆盖请求体中的请求参数 */
            if (!this.paramsMap.containsKey(entry.getKey())) {
                if (entry.getValue().length == 0) {
                    this.paramsMap.put(entry.getKey(), "");
                } else if (entry.getValue().length == 1) {
                    this.paramsMap.put(entry.getKey(), entry.getValue()[0]);
                } else {
                    this.paramsMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    /**
     * <code>RequestCachingInputStream</code>
     * <p>The type request caching input stream class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see javax.servlet.ServletInputStream
     * @since Jdk1.8
     */
    private static class RequestCachingInputStream extends ServletInputStream {

        /**
         * <code>inputStream</code>
         * {@link java.io.ByteArrayInputStream} <p>the <code>inputStream</code> field.</p>
         * @see java.io.ByteArrayInputStream
         */
        private final ByteArrayInputStream inputStream;

        /**
         * <code>RequestCachingInputStream</code>
         * Instantiates a new request caching input stream.
         * @param bytes byte <p>the bytes parameter is <code>byte</code> type.</p>
         */
        public RequestCachingInputStream(byte[] bytes) {
            inputStream = new ByteArrayInputStream(bytes);
        }

        @Override
        public int read() {
            return inputStream.read();
        }

        @Override
        public int read(@NonNull byte[] bytes) throws IOException {
            return inputStream.read(bytes);
        }

        @Override
        public int read(@NonNull byte[] bytes, int off, int len) {
            return inputStream.read(bytes, off, len);
        }

        @Override
        public boolean isFinished() {
            return inputStream.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readlistener) {
        }

    }

    /**
     * <code>getHttpRequest</code>
     * <p>the http request getter method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>the request parameter is <code>HttpServletRequest</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>the http request return object is <code>RestHttpRequest</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     */
    public static RestHttpRequest getHttpRequest(HttpServletRequest request) {
        RestHttpRequest httpRequest = null;
        if (request instanceof RestHttpRequest) {
            httpRequest = (RestHttpRequest) request;
        } else if (request instanceof StandardMultipartHttpServletRequest) {
            if (((StandardMultipartHttpServletRequest) request).getRequest() instanceof RestHttpRequest) {
                httpRequest = (RestHttpRequest) ((StandardMultipartHttpServletRequest) request).getRequest();
            }
        }
        if (httpRequest == null) {
            httpRequest = new RestHttpRequest(request);
        }
        return httpRequest;
    }
}
