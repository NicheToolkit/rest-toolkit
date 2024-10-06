package io.github.nichetoolkit.rest.interceptor;

import io.github.nichetoolkit.rest.*;
import io.github.nichetoolkit.rest.configure.RestInterceptProperties;
import io.github.nichetoolkit.rest.constant.RestConstants;
import io.github.nichetoolkit.rest.DefaultControllerAdvice;
import io.github.nichetoolkit.rest.userlog.*;
import io.github.nichetoolkit.rest.userlog.stereotype.RestLogging;
import io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog;
import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import io.github.nichetoolkit.rest.util.*;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
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
 * <code>DefaultLoggingInterceptor</code>
 * <p>The type default logging interceptor class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.servlet.AsyncHandlerInterceptor
 * @see io.github.nichetoolkit.rest.RestResponseAdvice
 * @see io.github.nichetoolkit.rest.RestExceptionAdvice
 * @see javax.servlet.Filter
 * @see lombok.extern.slf4j.Slf4j
 * @see javax.servlet.annotation.WebFilter
 * @see org.springframework.stereotype.Component
 * @see java.lang.SuppressWarnings
 * @see org.springframework.core.annotation.Order
 * @since Jdk1.8
 */
@Slf4j
@WebFilter
@Component
@SuppressWarnings("SameNameButDifferent")
@Order(1000)
public class DefaultLoggingInterceptor implements AsyncHandlerInterceptor, RestResponseAdvice, RestExceptionAdvice, Filter {
    /**
     * <code>START_TIME_HOLDER</code>
     * {@link java.lang.ThreadLocal} <p>the constant <code>START_TIME_HOLDER</code> field.</p>
     * @see java.lang.ThreadLocal
     */
    protected static final ThreadLocal<Long> START_TIME_HOLDER = new ThreadLocal<>();
    /**
     * <code>EXCEPTION_HOLDER</code>
     * {@link java.lang.ThreadLocal} <p>the constant <code>EXCEPTION_HOLDER</code> field.</p>
     * @see java.lang.ThreadLocal
     */
    protected static final ThreadLocal<Exception> EXCEPTION_HOLDER = new ThreadLocal<>();
    /**
     * <code>REST_RESPONSE_HOLDER</code>
     * {@link java.lang.ThreadLocal} <p>the constant <code>REST_RESPONSE_HOLDER</code> field.</p>
     * @see java.lang.ThreadLocal
     */
    protected static final ThreadLocal<RestResponsePack> REST_RESPONSE_HOLDER = new ThreadLocal<>();

    /**
     * <code>interceptProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestInterceptProperties} <p>the <code>interceptProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestInterceptProperties
     */
    private final RestInterceptProperties interceptProperties;

    /**
     * <code>DefaultLoggingInterceptor</code>
     * Instantiates a new default logging interceptor.
     * @param interceptProperties {@link io.github.nichetoolkit.rest.configure.RestInterceptProperties} <p>the intercept properties parameter is <code>RestInterceptProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestInterceptProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public DefaultLoggingInterceptor(RestInterceptProperties interceptProperties) {
        this.interceptProperties = interceptProperties;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public boolean supports(MethodParameter params, Class<?> clazz) {
        if (GeneralUtils.isEmpty(params)) {
            return false;
        }
        Class<?> declaringClass = params.getDeclaringClass();
        if (declaringClass == DefaultControllerAdvice.class) {
            return true;
        }
        if (!interceptProperties.getUserlogEnabled() && !interceptProperties.getLoggingEnabled()) {
            return false;
        }
        RestLogging classRestLogAnnotation = declaringClass.getAnnotation(RestLogging.class);
        RestNotelog notelogAnnotation = declaringClass.getAnnotation(RestNotelog.class);
        RestLogging methodRestLogAnnotation = params.getMethodAnnotation(RestLogging.class);
        RestUserlog userlogAnnotation = params.getMethodAnnotation(RestUserlog.class);
        return GeneralUtils.isNotEmpty(classRestLogAnnotation) || GeneralUtils.isNotEmpty(notelogAnnotation)
                || GeneralUtils.isNotEmpty(methodRestLogAnnotation) || GeneralUtils.isNotEmpty(userlogAnnotation);
    }

    @Override
    public void doResponseBodyHandle(Object resultBody, MethodParameter params, MediaType mediaType, Class<?> clazz, ServerHttpRequest request, ServerHttpResponse response) {
        RestResponsePack restResponse = new RestResponsePack();
        restResponse.setMediaType(mediaType.toString());
        if (mediaType.includes(MediaType.APPLICATION_JSON)) {
            String result = JsonUtils.parseJson(resultBody);
            restResponse.setResult(result);
            RestResult<String> restResult = JsonUtils.parseResult(result);
            if (GeneralUtils.isNotEmpty(restResult)) {
                restResponse.setData(restResult.getData());
                restResponse.setRestResult(new RestResult<>(restResult.getStatus(), restResult.getMessage()));
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
    public boolean preHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler) {
        Long start = System.currentTimeMillis();
        START_TIME_HOLDER.set(start);
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        RestHttpRequest httpRequest = RestHttpRequest.getHttpRequest(request);
        httpRequest.setHandlerMethods((HandlerMethod) handler);
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler, Exception exception) {
        if ((!interceptProperties.getUserlogEnabled() && !interceptProperties.getLoggingEnabled()) || !(handler instanceof HandlerMethod)) {
            return;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RestLogging beanRestLogAnnotation = handlerMethod.getBeanType().getAnnotation(RestLogging.class);
        RestNotelog notelogAnnotation = handlerMethod.getBeanType().getAnnotation(RestNotelog.class);
        RestLogging methodRestLog = handlerMethod.getMethodAnnotation(RestLogging.class);
        RestUserlog userlogAnnotation = handlerMethod.getMethodAnnotation(RestUserlog.class);
        if (GeneralUtils.isEmpty(beanRestLogAnnotation) && GeneralUtils.isEmpty(notelogAnnotation)
                && GeneralUtils.isEmpty(methodRestLog) && GeneralUtils.isEmpty(userlogAnnotation)) {
            return;
        }
        RestUsernotePack usernote = null;
        if (GeneralUtils.isNotEmpty(notelogAnnotation) && (GeneralUtils.isNotEmpty(notelogAnnotation.notelog())
                || GeneralUtils.isNotEmpty(notelogAnnotation.value()))) {
            String loggingKey = notelogAnnotation.loggingKey();
            String notelog = notelogAnnotation.notelog();
            if (GeneralUtils.isEmpty(notelogAnnotation.notelog())) {
                notelog = notelogAnnotation.value();
            }
            usernote = new RestUsernotePack();
            usernote.setNotelog(notelog);
            usernote.setLoggingKey(loggingKey);
        }
        if (GeneralUtils.isNotEmpty(userlogAnnotation)) {
            if (GeneralUtils.isEmpty(usernote)) {
                usernote = new RestUsernotePack();
            }
            if (GeneralUtils.isNotEmpty(userlogAnnotation.notelog())) {
                usernote.setNotelog(userlogAnnotation.notelog());
            }
            String userlog = userlogAnnotation.userlog();
            if (GeneralUtils.isEmpty(notelogAnnotation.notelog())) {
                userlog = userlogAnnotation.value();
            }
            usernote.setUserlog(userlog);
            if (GeneralUtils.isNotEmpty(userlogAnnotation.loggingKey())) {
                usernote.setLoggingKey(userlogAnnotation.loggingKey());
            }
            usernote.setLoggingValue(userlogAnnotation.loggingValue());
            LoggingType loggingType = userlogAnnotation.loggingType();
            usernote.setLoggingType(loggingType);
            if (GeneralUtils.isNotEmpty(loggingType)) {
                if (GeneralUtils.isEmpty(usernote.getLoggingKey())) {
                    usernote.setLoggingKey(loggingType.getKey());
                }
                if (GeneralUtils.isEmpty(usernote.getLoggingValue())) {
                    usernote.setLoggingValue(loggingType.getValue());
                }
                if (GeneralUtils.isEmpty(usernote.getUserlog())) {
                    usernote.setUserlog(loggingType.getValue());
                }
            }
        } else {
            usernote = null;
        }
        RestResponsePack responsePack = REST_RESPONSE_HOLDER.get();
        RestRequestPack requestPack = applyInterceptRequest(request, response, exception, responsePack);
        applyInterceptLogging(requestPack, responsePack, usernote);
        applyInterceptAdvice(requestPack, responsePack, usernote);
    }

    @Override
    public void afterConcurrentHandlingStarted(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RestHttpRequest httpRequest = null;
        if (servletRequest instanceof HttpServletRequest) {
            String contentType = servletRequest.getContentType();
            if (GeneralUtils.isNotEmpty(contentType) && contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
                httpRequest = new RestHttpRequest((HttpServletRequest) servletRequest);
            }
        }
        if (null == httpRequest) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(httpRequest, servletResponse);
            httpRequest.close();
        }
    }

    /**
     * <code>applyInterceptRequest</code>
     * <p>the intercept request method.</p>
     * @param request      {@link javax.servlet.http.HttpServletRequest} <p>the request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response     {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @param throwable    {@link java.lang.Throwable} <p>the throwable parameter is <code>Throwable</code> type.</p>
     * @param restResponse {@link io.github.nichetoolkit.rest.userlog.RestResponsePack} <p>the rest response parameter is <code>RestResponsePack</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.userlog.RestRequestPack} <p>the intercept request return object is <code>RestRequestPack</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see javax.servlet.http.HttpServletResponse
     * @see java.lang.Throwable
     * @see org.springframework.lang.Nullable
     * @see io.github.nichetoolkit.rest.userlog.RestResponsePack
     * @see io.github.nichetoolkit.rest.userlog.RestRequestPack
     */
    public RestRequestPack applyInterceptRequest(HttpServletRequest request, HttpServletResponse response, @Nullable Throwable throwable, RestResponsePack restResponse) {
        applyResponseTime(response, throwable, restResponse);
        RestRequestPack.Builder requestBuilder = new RestRequestPack.Builder();
        Map<String, String> headerMap = applyRequestHeader(request);
        RestRequestPack restRequest = requestBuilder.headers(JsonUtils.parseJson(headerMap))
                .ipAddress(IpAddressUtils.baseIpAddress(request))
                .userAgent(request.getHeader(RestConstants.USER_AGENT_HEADER)).method(request.getMethod())
                .url(request.getRequestURL().toString()).build();
        applyResponseError(response, throwable, restResponse);
        applyRequestBody(request, restRequest);
        String params = RestRequestInterceptHolder.getRequestParam(request);
        restRequest.setParams(params);
        return restRequest;
    }


    /**
     * <code>applyResponseTime</code>
     * <p>the response time method.</p>
     * @param response     {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @param throwable    {@link java.lang.Throwable} <p>the throwable parameter is <code>Throwable</code> type.</p>
     * @param restResponse {@link io.github.nichetoolkit.rest.userlog.RestResponsePack} <p>the rest response parameter is <code>RestResponsePack</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.userlog.RestResponsePack
     */
    public void applyResponseTime(HttpServletResponse response, Throwable throwable, RestResponsePack restResponse) {
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
                RestResult<?> restResult = restResponse.getRestResult();
                if (GeneralUtils.isNotEmpty(restResult)) {
                    restResponse.setStatus(restResult.getStatus());
                    restResponse.setMessage(restResult.getMessage());
                } else {
                    restResponse.setMessage(Optional.ofNullable(throwable).map(Throwable::getMessage).orElse(RestConstants.OK_MESSAGE));
                }
            }
        }
    }

    /**
     * <code>applyResponseError</code>
     * <p>the response error method.</p>
     * @param response     {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @param throwable    {@link java.lang.Throwable} <p>the throwable parameter is <code>Throwable</code> type.</p>
     * @param restResponse {@link io.github.nichetoolkit.rest.userlog.RestResponsePack} <p>the rest response parameter is <code>RestResponsePack</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.userlog.RestResponsePack
     */
    public void applyResponseError(HttpServletResponse response, Throwable throwable, RestResponsePack restResponse) {
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

    /**
     * <code>applyRequestHeader</code>
     * <p>the request header method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>the request parameter is <code>HttpServletRequest</code> type.</p>
     * @return {@link java.util.Map} <p>the request header return object is <code>Map</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see java.util.Map
     */
    public Map<String, String> applyRequestHeader(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            headerMap.put(element, request.getHeader(element));
        }
        return headerMap;
    }

    /**
     * <code>applyRequestBody</code>
     * <p>the request body method.</p>
     * @param request     {@link javax.servlet.http.HttpServletRequest} <p>the request parameter is <code>HttpServletRequest</code> type.</p>
     * @param restRequest {@link io.github.nichetoolkit.rest.userlog.RestRequestPack} <p>the rest request parameter is <code>RestRequestPack</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see io.github.nichetoolkit.rest.userlog.RestRequestPack
     */
    public void applyRequestBody(HttpServletRequest request, RestRequestPack restRequest) {
        String contentType = request.getContentType();
        if (StringUtils.hasText(contentType) && contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
            if (request instanceof RestHttpRequest) {
                RestHttpRequest httpRequest = (RestHttpRequest) request;
                String body = new String(httpRequest.getCacheBody(), StandardCharsets.UTF_8);
                restRequest.setBody(body);
                Integer bodyLength = interceptProperties.getBodyLength();
                String bodyString;
                if (GeneralUtils.isNotEmpty(bodyLength)) {
                    bodyString = CommonUtils.substring(body, bodyLength);
                } else {
                    bodyString = body;
                }
                restRequest.setBodyString(bodyString);
            } else {
                restRequest.setBody("the request of content type without 'application/json' is ignored.");
                log.warn("the request is not 'RestHttpRequest' type!");
            }
        }
    }

    /**
     * <code>applyInterceptAdvice</code>
     * <p>the intercept advice method.</p>
     * @param requestPack  {@link io.github.nichetoolkit.rest.userlog.RestRequestPack} <p>the request pack parameter is <code>RestRequestPack</code> type.</p>
     * @param responsePack {@link io.github.nichetoolkit.rest.userlog.RestResponsePack} <p>the response pack parameter is <code>RestResponsePack</code> type.</p>
     * @param usernotePack {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack} <p>the usernote pack parameter is <code>RestUsernotePack</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.RestRequestPack
     * @see io.github.nichetoolkit.rest.userlog.RestResponsePack
     * @see io.github.nichetoolkit.rest.userlog.RestUsernotePack
     */
    public void applyInterceptAdvice(RestRequestPack requestPack, RestResponsePack responsePack, RestUsernotePack usernotePack) {
        RestUsernoteAdvice usernoteAdvice = BeanUtils.beanOfType(RestUsernoteAdvice.class);
        if (GeneralUtils.isNotEmpty(usernotePack) && GeneralUtils.isNotEmpty(usernoteAdvice) && interceptProperties.getUserlogEnabled()) {
            usernoteAdvice.doUsernoteHandle(requestPack, responsePack, usernotePack);
        }
    }

    /**
     * <code>applyInterceptLogging</code>
     * <p>the intercept logging method.</p>
     * @param requestPack  {@link io.github.nichetoolkit.rest.userlog.RestRequestPack} <p>the request pack parameter is <code>RestRequestPack</code> type.</p>
     * @param responsePack {@link io.github.nichetoolkit.rest.userlog.RestResponsePack} <p>the response pack parameter is <code>RestResponsePack</code> type.</p>
     * @param usernotePack {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack} <p>the usernote pack parameter is <code>RestUsernotePack</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.RestRequestPack
     * @see io.github.nichetoolkit.rest.userlog.RestResponsePack
     * @see io.github.nichetoolkit.rest.userlog.RestUsernotePack
     */
    public void applyInterceptLogging(RestRequestPack requestPack, RestResponsePack responsePack, RestUsernotePack usernotePack) {
        if (interceptProperties.getLoggingEnabled()) {
            if (GeneralUtils.isNotEmpty(requestPack) || GeneralUtils.isNotEmpty(responsePack) || GeneralUtils.isNotEmpty(usernotePack)) {
                log.info(">>>>>>>>>>>>>> intercept logging begin <<<<<<<<<<<<<<");
            }
            if (GeneralUtils.isNotEmpty(usernotePack)) {
                log.info("logging       notelog : {}", usernotePack.getNotelog());
                log.info("logging       userlog : {}", usernotePack.getUserlog());
                log.info("logging           key : {}", usernotePack.getLoggingKey());
                log.info("logging         value : {}", usernotePack.getLoggingValue());
                log.info("logging          type : {}", usernotePack.getLoggingType().name());
            }
            if (GeneralUtils.isNotEmpty(requestPack)) {
                log.info("request     ip address : {}", requestPack.getIpAddress());
                log.info("request     user agent : {}", requestPack.getUserAgent());
                log.info("request         method : {}", requestPack.getMethod());
                log.info("request            url : {}", requestPack.getUrl());
                if (GeneralUtils.isNotEmpty(requestPack.getParams())) {
                    log.info("request         params : {}", requestPack.getParams());
                }
                if (GeneralUtils.isNotEmpty(requestPack.getBodyString())) {
                    log.info("request           body : {}", requestPack.getBodyString());
                }
            }
            if (GeneralUtils.isNotEmpty(responsePack)) {
                log.info("response          time : {}", DateUtils.formatTime(responsePack.getTime()));
                log.info("response    start time : {}", responsePack.getStartTime());
                log.info("response      end time : {}", responsePack.getEndTime());
                log.info("response     cost time : {}", responsePack.getCostTime());
                log.info("response        status : {}", responsePack.getStatus());
                log.info("response       message : {}", responsePack.getMessage());
                if (GeneralUtils.isNotEmpty(responsePack.getError())) {
                    log.info("response         error : {}", responsePack.getError());
                }
                log.info("response        method : {}", responsePack.getMethod());
                log.info("response    media type : {}", responsePack.getMediaType());
                if (GeneralUtils.isNotEmpty(responsePack.getResultString())) {
                    log.info("response        result : {}", responsePack.getResultString());
                }
            }
            if (GeneralUtils.isNotEmpty(requestPack) || GeneralUtils.isNotEmpty(responsePack) || GeneralUtils.isNotEmpty(usernotePack)) {
                log.info(">>>>>>>>>>>>>>> intercept logging end <<<<<<<<<<<<<<<");
            }

        }
    }
}
