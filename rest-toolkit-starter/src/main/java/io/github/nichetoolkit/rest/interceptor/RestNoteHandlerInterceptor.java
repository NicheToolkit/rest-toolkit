package io.github.nichetoolkit.rest.interceptor;

import io.github.nichetoolkit.rest.*;
import io.github.nichetoolkit.rest.configure.RestInterceptProperties;
import io.github.nichetoolkit.rest.constant.RestConstants;
import io.github.nichetoolkit.rest.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <p>RestHandlerInterceptor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@WebFilter
@Component
public class RestNoteHandlerInterceptor implements AsyncHandlerInterceptor, RestBodyAdvice, RestExceptionAdvice, Filter {
    protected final ThreadLocal<Long> START_TIME_HOLDER = new ThreadLocal<>();
    protected final ThreadLocal<Exception> EXCEPTION_HOLDER = new ThreadLocal<>();
    protected final ThreadLocal<RestResponse> REST_RESPONSE_HOLDER = new ThreadLocal<>();
    private final RestInterceptProperties interceptProperties;

    @Autowired
    public RestNoteHandlerInterceptor(RestInterceptProperties interceptProperties) {
        this.interceptProperties = interceptProperties;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public boolean supports(MethodParameter params, Class clazz) {
        if (GeneralUtils.isEmpty(params)) {
            return false;
        }
        Class<?> declaringClass = params.getDeclaringClass();
        if (declaringClass == DefaultAdvice.class) {
            return true;
        }
        RestNote classAnnotation = declaringClass.getAnnotation(RestNote.class);
        RestNote methodAnnotation = params.getMethodAnnotation(RestNote.class);
        return GeneralUtils.isNotEmpty(classAnnotation) || GeneralUtils.isNotEmpty(methodAnnotation);
    }

    @Override
    public void doRestBodyHandle(Object resultBody, MethodParameter params, MediaType mediaType, Class clazz, ServerHttpRequest request, ServerHttpResponse response) {
        RestResponse restResponse = new RestResponse();
        restResponse.setMediaType(mediaType.toString());
        if (mediaType.includes(MediaType.APPLICATION_JSON)) {
            String resultJson = JsonUtils.parseJson(resultBody);
            String result = CommonUtils.substring(resultJson, interceptProperties.getResultLength());
            restResponse.setResult(result);
            Method method = params.getMethod();
            if (GeneralUtils.isNotEmpty(method)) {
                restResponse.setMethod(method.getName());
            }
        }
        if (!mediaType.includes(MediaType.APPLICATION_JSON)) {
            Method method = params.getMethod();
            if (GeneralUtils.isNotEmpty(method)) {
                restResponse.setMethod(method.getName());
            }
        }
        REST_RESPONSE_HOLDER.set(restResponse);
    }

    @Override
    public void preExceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        if (GeneralUtils.isNotEmpty(exception)) {
            EXCEPTION_HOLDER.set(exception);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Long start = System.currentTimeMillis();
        START_TIME_HOLDER.set(start);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> controllerClass = handlerMethod.getBean().getClass();
            RestNote controllerAnnotation = controllerClass.getAnnotation(RestNote.class);
            Method method = handlerMethod.getMethod();
            RestNote methodAnnotation = method.getAnnotation(RestNote.class);
            if (GeneralUtils.isEmpty(methodAnnotation) && GeneralUtils.isEmpty(controllerAnnotation)) {
                return;
            }
            RestResponse restResponse = REST_RESPONSE_HOLDER.get();
            RestRequest restRequest = applyInterceptRest(request, response, exception, restResponse);
            applyInterceptRequestLog(restRequest, restResponse);
            applyInterceptService(restRequest, restResponse);
        }
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RestNoteRequestWrapper requestWrapper = null;
        if (servletRequest instanceof HttpServletRequest) {
            String contentType = servletRequest.getContentType();
            if (GeneralUtils.isNotEmpty(contentType) && contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
                requestWrapper = new RestNoteRequestWrapper((HttpServletRequest) servletRequest);
            }
        }
        if (null == requestWrapper) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(requestWrapper, servletResponse);
            requestWrapper.close();
        }
    }

    public RestRequest applyInterceptRest(HttpServletRequest request, HttpServletResponse response, @Nullable Throwable throwable, RestResponse restResponse) {
        applyRestResponseTime(response, throwable, restResponse);
        RestRequest.Builder requestBuilder = new RestRequest.Builder();
        Map<String, String> headerMap = applyRestRequestHeader(request);
        RestRequest restRequest = requestBuilder.headers(JsonUtils.parseJson(headerMap))
                .ipAddress(IpAddressUtils.baseIpAddress(request))
                .userAgent(request.getHeader(RestConstants.USER_AGENT_HEADER)).method(request.getMethod())
                .url(request.getRequestURL().toString()).build();
        applyRestResponseError(response, throwable, restResponse);
        applyRestRequestBody(request, restRequest);
        String params = RestInterceptHolder.getRequestParam(request);
        restRequest.setParams(params);
        return restRequest;
    }



    public void applyRestResponseTime(HttpServletResponse response, Throwable throwable, RestResponse restResponse) {
        Long startTime = START_TIME_HOLDER.get();
        Long endTime = System.currentTimeMillis();
        Long costTime = endTime - startTime;
        if (GeneralUtils.isNotEmpty(restResponse)) {
            restResponse.setTime(startTime);
            restResponse.setStartTime(startTime);
            restResponse.setEndTime(endTime);
            restResponse.setCostTime(costTime);
            restResponse.setStatus(response.getStatus());
            restResponse.setMessage(Optional.ofNullable(throwable).map(Throwable::getMessage).orElse(RestConstants.OK_MESSAGE));
        }
    }

    public void applyRestResponseError(HttpServletResponse response, Throwable throwable, RestResponse restResponse) {
        if (!RestErrorStatus.SUCCESS.getStatus().equals(response.getStatus())) {
            Exception exception = EXCEPTION_HOLDER.get();
            if (GeneralUtils.isNotEmpty(restResponse)) {
                String messageContent;
                String errorContent;
                if (GeneralUtils.isEmpty(throwable) && GeneralUtils.isEmpty(exception)) {
                    messageContent = RestConstants.UNKNOWN_ERROR;
                    errorContent = RestConstants.UNKNOWN_ERROR;
                } else if (GeneralUtils.isNotEmpty(throwable)) {
                    messageContent = throwable.getMessage();
                    errorContent = throwable.toString();
                } else {
                    messageContent = exception.getMessage();
                    errorContent = exception.toString();
                }
                String message = CommonUtils.substring(messageContent, interceptProperties.getMessageLength());
                String error = CommonUtils.substring(errorContent, interceptProperties.getErrorLength());
                restResponse.setMessage(message);
                restResponse.setError(error);
            }
        }
    }

    public Map<String,String> applyRestRequestHeader(HttpServletRequest request) {
        Map<String,String> headerMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            headerMap.put(element,request.getHeader(element));
        }
        return headerMap;
    }

    public void applyRestRequestBody(HttpServletRequest request, RestRequest restRequest) {
        String contentType = request.getContentType();
        if (StringUtils.hasText(contentType) && contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
            if (request instanceof RestNoteRequestWrapper) {
                RestNoteRequestWrapper requestWrapper = (RestNoteRequestWrapper) request;
                String bodyString = new String(requestWrapper.getBody(), StandardCharsets.UTF_8);
                String body = CommonUtils.substring(bodyString, interceptProperties.getBodyLength());
                restRequest.setBody(body);
            } else {
                restRequest.setBody("the request of content type without 'application/json' is ignored.");
                log.debug("the request is not 'RestRequestWrapper' type!");
            }
        }
    }

    public void applyInterceptService(RestRequest request, RestResponse restResponse) throws RestException {
        RestNoteService interceptService = ContextUtils.getBean(RestNoteService.class);
        if (GeneralUtils.isNotEmpty(interceptService) && interceptProperties.getBeanEnabled()) {
            interceptService.handler(request, restResponse);
        }
    }

    public void applyInterceptRequestLog(RestRequest request, RestResponse response) {
        if (interceptProperties.getLogEnabled()) {
            if (GeneralUtils.isNotEmpty(request) || GeneralUtils.isNotEmpty(response)){
                log.info(">>>>>>>>>>>>>> intercept log begin <<<<<<<<<<<<<<");
            }
            if (GeneralUtils.isNotEmpty(request)) {
                log.info("request     ip address : {}", request.getIpAddress());
                log.info("request     user agent : {}", request.getUserAgent());
                log.info("request         method : {}", request.getMethod());
                log.info("request            url : {}", request.getUrl());
                if (GeneralUtils.isNotEmpty(request.getParams())) {
                    log.info("request         params : {}", request.getParams());
                }
                if (GeneralUtils.isNotEmpty(request.getBody())) {
                    log.info("request           body : {}", request.getBody());
                }
            }
            if (GeneralUtils.isNotEmpty(response)) {
                log.info("response          time : {}", DateUtils.formatTime(response.getTime()));
                log.info("response    start time : {}", response.getStartTime());
                log.info("response      end time : {}", response.getEndTime());
                log.info("response     cost time : {}", response.getCostTime());
                log.info("response        status : {}", response.getStatus());
                log.info("response       message : {}", response.getMessage());
                if (GeneralUtils.isNotEmpty(response.getError())) {
                    log.info("response         error : {}", response.getError());
                }
                log.info("response        method : {}", response.getMethod());
                log.info("response    media type : {}", response.getMediaType());
                if (GeneralUtils.isNotEmpty(response.getResult())) {
                    log.info("response        result : {}", response.getResult());
                }
            }
            if (GeneralUtils.isNotEmpty(request) || GeneralUtils.isNotEmpty(response)){
                log.info(">>>>>>>>>>>>>>> intercept log end <<<<<<<<<<<<<<<");
            }

        }
    }
}
