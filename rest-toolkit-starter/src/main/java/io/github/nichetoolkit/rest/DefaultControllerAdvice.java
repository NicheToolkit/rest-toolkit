package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.configure.RestExceptionProperties;
import io.github.nichetoolkit.rest.holder.ContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;

/**
 * <code>DefaultControllerAdvice</code>
 * <p>The type default controller advice class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
 * @see org.springframework.context.ApplicationContextAware
 * @see org.springframework.beans.factory.InitializingBean
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.core.annotation.Order
 * @see org.springframework.web.bind.annotation.CrossOrigin
 * @see org.springframework.web.bind.annotation.RestControllerAdvice
 * @since Jdk1.8
 */
@Slf4j
@Order(0)
@CrossOrigin
@RestControllerAdvice
public class DefaultControllerAdvice implements ResponseBodyAdvice<Object>, ApplicationContextAware, InitializingBean {
    private final RestExceptionProperties exceptionProperties;

    @Nullable
    private ApplicationContext applicationContext;
    @Nullable
    private List<RestExceptionAdvice> exceptionAdvices;
    @Nullable
    private List<RestResponseAdvice> responseAdvices;

    /**
     * <code>DefaultControllerAdvice</code>
     * Instantiates a new default controller advice.
     * @param exceptionProperties {@link io.github.nichetoolkit.rest.configure.RestExceptionProperties} <p>the exception properties parameter is <code>RestExceptionProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestExceptionProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public DefaultControllerAdvice(RestExceptionProperties exceptionProperties) {
        this.exceptionProperties = exceptionProperties;
    }

    @Override
    public boolean supports(@NonNull MethodParameter params, @NonNull Class clazz) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter params, @NonNull MediaType mediaType, @NonNull Class clazz, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (this.responseAdvices != null && !this.responseAdvices.isEmpty()) {
            for (RestResponseAdvice advice : this.responseAdvices) {
                boolean supports = advice.supports(params, clazz);
                if (supports) {
                    advice.doResponseBodyHandle(body, params, mediaType, clazz, request, response);
                }
            }
        }
        return body;
    }

    @Override
    public void afterPropertiesSet() {
        if (this.exceptionAdvices == null) {
            Assert.notNull(this.applicationContext, "No ApplicationContext");
            this.exceptionAdvices = ContextHolder.getBeans(this.applicationContext, RestExceptionAdvice.class);
        }
        if (this.responseAdvices == null) {
            Assert.notNull(this.applicationContext, "No ApplicationContext");
            this.responseAdvices = ContextHolder.getBeans(this.applicationContext, RestResponseAdvice.class);
        }
    }

    /**
     * <code>getExceptionAdvices</code>
     * <p>the exception advices getter method.</p>
     * @return {@link java.util.List} <p>the exception advices return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<RestExceptionAdvice> getExceptionAdvices() {
        return this.exceptionAdvices != null && !this.exceptionAdvices.isEmpty() ? this.exceptionAdvices : Collections.emptyList();
    }


    /**
     * <code>getResponseAdvices</code>
     * <p>the response advices getter method.</p>
     * @return {@link java.util.List} <p>the response advices return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<RestResponseAdvice> getResponseAdvices() {
        return this.responseAdvices != null && !this.responseAdvices.isEmpty() ? this.responseAdvices : Collections.emptyList();
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        ContextHolder.initApplicationContext(applicationContext);
    }

    /**
     * <code>exceptionHandle</code>
     * <p>the handle method.</p>
     * @param exception {@link java.lang.Exception} <p>the exception parameter is <code>Exception</code> type.</p>
     * @param request   {@link javax.servlet.http.HttpServletRequest} <p>the request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>the handle return object is <code>ResponseEntity</code> type.</p>
     * @see java.lang.Exception
     * @see javax.servlet.http.HttpServletRequest
     * @see javax.servlet.http.HttpServletResponse
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.web.bind.annotation.ResponseBody
     * @see org.springframework.web.bind.annotation.ExceptionHandler
     */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> exceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        preExceptionHandle(exception, request, response);
        if (exception instanceof RestException) {
            RestException restException = (RestException) exception;
            doRestExceptionHandle(restException, request, response);
            boolean restExceptionEnabled = exceptionProperties.getConsoleLog().getRestExceptionEnabled();
            if (restExceptionEnabled) {
                printStackTrace(exception);
            }
            return ResponseEntity.ok(restException.buildResult());
        } else {
            doExceptionHandle(exception, request, response);
            boolean commonExceptionEnabled = exceptionProperties.getConsoleLog().getCommonExceptionEnabled();
            if (commonExceptionEnabled) {
                printStackTrace(exception);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResult.mistake(RestErrorStatus.UNKNOWN_ERROR, exception));
        }
    }

    private void printStackTrace(Exception exception) {
        try (StringWriter stringWriter = new StringWriter();
             PrintWriter printWriter = new PrintWriter(stringWriter)) {
            exception.printStackTrace(printWriter);
            String stackTrace = stringWriter.toString();
            StackTraceElement stackTraceElement = exception.getStackTrace()[0];
            Integer line = stackTraceElement.getLineNumber();
            String resource = stackTraceElement.getClassName();
            String errorClass = exception.getClass().getName();
            log.error("{} [{}] {}: {} \n{}", resource, line, errorClass, exception.getMessage(), stackTrace);
        } catch (IOException ignored) {
        }
    }

    private void preExceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        if (this.exceptionAdvices != null && !this.exceptionAdvices.isEmpty()) {
            for (RestExceptionAdvice advice : this.exceptionAdvices) {
                advice.preExceptionHandle(exception, request, response);
            }
        }
    }

    private void doRestExceptionHandle(RestException restException, HttpServletRequest request, HttpServletResponse response) {
        if (this.exceptionAdvices != null && !this.exceptionAdvices.isEmpty()) {
            for (RestExceptionAdvice advice : this.exceptionAdvices) {
                advice.doRestExceptionHandle(restException, request, response);
            }
        }
    }

    private void doExceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        if (this.exceptionAdvices != null && !this.exceptionAdvices.isEmpty()) {
            for (RestExceptionAdvice advice : this.exceptionAdvices) {
                advice.doExceptionHandle(exception, request, response);
            }
        }
    }

}
