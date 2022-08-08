package io.github.nichetoolkit.rest.interceptor;

import io.github.nichetoolkit.rest.*;
import io.github.nichetoolkit.rest.configure.RestInterceptProperties;
import io.github.nichetoolkit.rest.constant.RestConstants;
import io.github.nichetoolkit.rest.helper.RestRequestHelper;
import io.github.nichetoolkit.rest.RestNote;
import io.github.nichetoolkit.rest.log.LogType;
import io.github.nichetoolkit.rest.log.RestLogMessage;
import io.github.nichetoolkit.rest.log.RestLogTitle;
import io.github.nichetoolkit.rest.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
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
@SuppressWarnings("SameNameButDifferent")
@Order(1000)
public class RestHandlerInterceptor implements AsyncHandlerInterceptor, RestBodyAdvice, RestExceptionAdvice, Filter {
    protected static final ThreadLocal<Long> START_TIME_HOLDER = new ThreadLocal<>();
    protected static final ThreadLocal<Exception> EXCEPTION_HOLDER = new ThreadLocal<>();
    protected static final ThreadLocal<RestResponse> REST_RESPONSE_HOLDER = new ThreadLocal<>();

    @Autowired
    private RestInterceptProperties interceptProperties;

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
            String result = JsonUtils.parseJson(resultBody);
            restResponse.setResult(result);
            RestResult<String> restResult = JsonUtils.parseResult(result);
            if (GeneralUtils.isNotEmpty(restResult)) {
                restResponse.setData(restResult.getData());
                restResponse.setRestResult(new RestResult(restResult.getStatus(),restResult.getMessage()));
            }
            String resultString = CommonUtils.substring(result, interceptProperties.getResultLength());
            restResponse.setResultString(resultString);
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
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        RestRequestWrapper requestWrapper = RestRequestHelper.getRestRequestWrapper(request);
        requestWrapper.setHandlerMethods((HandlerMethod) handler);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> controllerClass = handlerMethod.getBean().getClass();
            RestNote controllerAnnotation = controllerClass.getAnnotation(RestNote.class);
            RestNote methodAnnotation = handlerMethod.getMethodAnnotation(RestNote.class);
            if (GeneralUtils.isEmpty(methodAnnotation) && GeneralUtils.isEmpty(controllerAnnotation)) {
                return;
            }
            RestNotelog restLog = null;
            RestLogTitle logTitleAnnotation = controllerClass.getAnnotation(RestLogTitle.class);
            if (GeneralUtils.isNotEmpty(logTitleAnnotation) && GeneralUtils.isNotEmpty(logTitleAnnotation.value())) {
                restLog = new RestNotelog();
                restLog.setTitle(logTitleAnnotation.value());
                restLog.setKey(logTitleAnnotation.key());
            }
            RestLogMessage logMessageAnnotation = handlerMethod.getMethodAnnotation(RestLogMessage.class);
            if (GeneralUtils.isNotEmpty(logMessageAnnotation)) {
                if (GeneralUtils.isEmpty(restLog)) {
                    restLog = new RestNotelog();
                }
                if (GeneralUtils.isNotEmpty(logMessageAnnotation.title())) {
                    restLog.setTitle(logMessageAnnotation.title());
                }
                restLog.setMessage(logMessageAnnotation.message());
                if (GeneralUtils.isNotEmpty(logMessageAnnotation.key())) {
                    restLog.setKey(logMessageAnnotation.key());
                }
                restLog.setValue(logMessageAnnotation.value());
                LogType logType = logMessageAnnotation.logType();
                restLog.setLogType(logType);
                if (GeneralUtils.isNotEmpty(logType)) {
                    if (GeneralUtils.isEmpty(restLog.getKey())) {
                        restLog.setKey(logType.getKey());
                    }
                    if (GeneralUtils.isEmpty(restLog.getValue())) {
                        restLog.setValue(logType.getValue());
                    }
                    if (GeneralUtils.isEmpty(restLog.getMessage())) {
                        restLog.setMessage(logType.getField());
                    }
                }
            }
            RestResponse restResponse = REST_RESPONSE_HOLDER.get();
            RestRequest restRequest = applyInterceptRest(request, response, exception, restResponse);
            applyInterceptRequestLog(restRequest, restResponse,restLog);
            applyInterceptService(restRequest, restResponse,restLog);
        }
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RestRequestWrapper requestWrapper = null;
        if (servletRequest instanceof HttpServletRequest) {
            String contentType = servletRequest.getContentType();
            if (GeneralUtils.isNotEmpty(contentType) && contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
                requestWrapper = new RestRequestWrapper((HttpServletRequest) servletRequest);
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
            int status = response.getStatus();

            if (status != HttpStatus.OK.value()) {
                restResponse.setStatus(status);
                restResponse.setMessage(Optional.ofNullable(throwable).map(Throwable::getMessage).orElse(RestConstants.OK_MESSAGE));
            } else {
                RestResult restResult = restResponse.getRestResult();
                if (GeneralUtils.isNotEmpty(restResult)) {
                    restResponse.setStatus(restResult.getStatus());
                    restResponse.setMessage(restResult.getMessage());
                } else {
                    restResponse.setMessage(Optional.ofNullable(throwable).map(Throwable::getMessage).orElse(RestConstants.OK_MESSAGE));
                }
            }
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

    public Map<String, String> applyRestRequestHeader(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            headerMap.put(element, request.getHeader(element));
        }
        return headerMap;
    }

    public void applyRestRequestBody(HttpServletRequest request, RestRequest restRequest) {
        String contentType = request.getContentType();
        if (StringUtils.hasText(contentType) && contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
            if (request instanceof RestRequestWrapper) {
                RestRequestWrapper requestWrapper = (RestRequestWrapper) request;
                String body = new String(requestWrapper.getCacheBody(), StandardCharsets.UTF_8);
                restRequest.setBody(body);
                String bodyString = CommonUtils.substring(body, interceptProperties.getBodyLength());
                restRequest.setBodyString(bodyString);
            } else {
                restRequest.setBody("the request of content type without 'application/json' is ignored.");
                log.debug("the request is not 'RestRequestWrapper' type!");
            }
        }
    }

    public void applyInterceptService(RestRequest request, RestResponse restResponse, RestNotelog restLog) throws RestException {
        RestNoteService interceptService = ContextUtils.getBean(RestNoteService.class);
        if (GeneralUtils.isNotEmpty(interceptService) && interceptProperties.getBeanEnabled()) {
            interceptService.handler(request, restResponse,restLog);
        }
    }

    public void applyInterceptRequestLog(RestRequest request, RestResponse response, RestNotelog restLog) {
        if (interceptProperties.getLogEnabled()) {
            if (GeneralUtils.isNotEmpty(request) || GeneralUtils.isNotEmpty(response) || GeneralUtils.isNotEmpty(restLog)) {
                log.info(">>>>>>>>>>>>>> intercept log begin <<<<<<<<<<<<<<");
            }
            if (GeneralUtils.isNotEmpty(restLog)) {
                log.info("log              title : {}", restLog.getTitle());
                log.info("log            message : {}", restLog.getMessage());
                log.info("log                key : {}", restLog.getKey());
                log.info("log              value : {}", restLog.getValue());
                log.info("log            logType : {}", restLog.getLogType().toString());
            }
            if (GeneralUtils.isNotEmpty(request)) {
                log.info("request     ip address : {}", request.getIpAddress());
                log.info("request     user agent : {}", request.getUserAgent());
                log.info("request         method : {}", request.getMethod());
                log.info("request            url : {}", request.getUrl());
                if (GeneralUtils.isNotEmpty(request.getParams())) {
                    log.info("request         params : {}", request.getParams());
                }
                if (GeneralUtils.isNotEmpty(request.getBodyString())) {
                    log.info("request           body : {}", request.getBodyString());
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
                if (GeneralUtils.isNotEmpty(response.getResultString())) {
                    log.info("response        result : {}", response.getResultString());
                }
            }
            if (GeneralUtils.isNotEmpty(request) || GeneralUtils.isNotEmpty(response) || GeneralUtils.isNotEmpty(restLog)) {
                log.info(">>>>>>>>>>>>>>> intercept log end <<<<<<<<<<<<<<<");
            }

        }
    }
}
