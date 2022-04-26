package io.github.nichetoolkit.rest.interceptor;


import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StreamUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * <p>RestRequestWrapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class RestRequestWrapper extends HttpServletRequestWrapper implements Closeable {

    private static final String REQUEST_ID_KEY = "_REQUEST_ID_KEY_";

    private static final String HANDLER_METHOD_KEY = "HANDLER_METHOD_KEY";


    private byte[] cacheBody;

    private BufferedReader reader;

    private ServletInputStream inputStream;

    private String paramsJson;

    private Map<String, Object> paramsMap;

    private List<HandlerMethod> handlerMethods;

    public RestRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    private void cacheBody(ServletInputStream inputStream) throws IOException{
        if (GeneralUtils.isEmpty(this.cacheBody)) {
            this.cacheBody = StreamUtils.copyToByteArray(inputStream);
            this.inputStream = new RequestCachingInputStream(cacheBody);
        }
    }

    public byte[] getCacheBody() {
        return cacheBody;
    }

    public List<HandlerMethod> getHandlerMethods() {
        return handlerMethods;
    }

    @Override
    public BufferedReader getReader()throws IOException {
        if (reader == null) {
            reader = new BufferedReader(new InputStreamReader(inputStream, getCharacterEncoding()));
        }
        return reader;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (inputStream != null) {
            return inputStream;
        }
        cacheBody(getRequest().getInputStream());
        return this.inputStream;
    }

    @Override
    public void close() throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public String getRequestURI(boolean isNoContext) {
        String url = super.getRequestURI();
        if (isNoContext) {
            return url.replaceAll(getContextPath(), "");
        }
        return url;
    }

    public String getParamsJson() throws IOException {
        if (this.paramsJson != null) {
            return this.paramsJson;
        }
        this.paramsJson = JsonUtils.parseJson(getParamsMap());
        return this.paramsJson;
    }

    public Map<String, Object> getParamsMap() throws IOException {
        if (this.paramsMap != null) {
            return this.paramsMap;
        }
        cacheBody(getRequest().getInputStream());
        cacheMap();
        return Collections.unmodifiableMap(this.paramsMap);
    }

    public String getRequestId() {
        Object requestId = getAttribute(REQUEST_ID_KEY);
        if (requestId == null) {
            requestId = GeneralUtils.uuid();
        }
        return requestId.toString();
    }

    public void setRequestId(String requestId) {
        setAttribute(REQUEST_ID_KEY, requestId);
    }

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

    @Nullable
    @SuppressWarnings(value = "unchecked")
    public <A extends Annotation> A getMethodAnnotation(Class<A> annotationType) {
        List<HandlerMethod> handlerMethodList = (List<HandlerMethod>) getAttribute(HANDLER_METHOD_KEY);
        if (handlerMethodList == null) {
            return null;
        }
        for(HandlerMethod handlerMethod : handlerMethodList) {
            A annotation = handlerMethod.getMethodAnnotation(annotationType);
            if (annotation != null) {
                return annotation;
            }
        }
        return  null;
    }

    private boolean isSupportedJson(String value) {
        if (GeneralUtils.isEmpty(value)) {
            return false;
        }
        value = value.trim();
        /** 大括号或者中括号开头的都算json字符串 */
        if (value.startsWith("{") || value.startsWith("[")) {
            return true;
        }
        return false;
    }

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
            /** url中的请求参数不能覆盖请求体中的请求参数 */
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

    private static class RequestCachingInputStream extends ServletInputStream {

        private final ByteArrayInputStream inputStream;

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
}
