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
 * <code>DefaultAdvice</code>
 * <p>The type default advice class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
 * @see org.springframework.context.ApplicationContextAware
 * @see org.springframework.beans.factory.InitializingBean
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.core.annotation.Order
 * @see org.springframework.web.bind.annotation.CrossOrigin
 * @see org.springframework.web.bind.annotation.ControllerAdvice
 * @see org.springframework.web.bind.annotation.RestControllerAdvice
 * @since Jdk1.8
 */
@Slf4j
@Order(0)
@CrossOrigin
@ControllerAdvice
@RestControllerAdvice
public class DefaultAdvice implements ResponseBodyAdvice<Object>, ApplicationContextAware, InitializingBean {
    /**
     * <code>exceptionProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestExceptionProperties} <p>the <code>exceptionProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestExceptionProperties
     */
    private final RestExceptionProperties exceptionProperties;

    /**
     * <code>applicationContext</code>
     * {@link org.springframework.context.ApplicationContext} <p>the <code>applicationContext</code> field.</p>
     * @see org.springframework.context.ApplicationContext
     * @see org.springframework.lang.Nullable
     */
    @Nullable
    private ApplicationContext applicationContext;
    /**
     * <code>restExceptionAdvices</code>
     * {@link java.util.List} <p>the <code>restExceptionAdvices</code> field.</p>
     * @see java.util.List
     * @see org.springframework.lang.Nullable
     */
    @Nullable
    private List<RestExceptionAdvice> restExceptionAdvices;
    /**
     * <code>restBodyAdvices</code>
     * {@link java.util.List} <p>the <code>restBodyAdvices</code> field.</p>
     * @see java.util.List
     * @see org.springframework.lang.Nullable
     */
    @Nullable
    private List<RestBodyAdvice> restBodyAdvices;

    /**
     * <code>DefaultAdvice</code>
     * Instantiates a new default advice.
     * @param exceptionProperties {@link io.github.nichetoolkit.rest.configure.RestExceptionProperties} <p>the exception properties parameter is <code>RestExceptionProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestExceptionProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public DefaultAdvice(RestExceptionProperties exceptionProperties) {
        this.exceptionProperties = exceptionProperties;
    }

    @Override
    public boolean supports( @NonNull MethodParameter params, @NonNull Class clazz) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter params,  @NonNull MediaType mediaType, @NonNull Class clazz, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (this.restBodyAdvices != null && !this.restBodyAdvices.isEmpty()) {
            for (RestBodyAdvice advice : this.restBodyAdvices) {
                boolean supports = advice.supports(params, clazz);
                if (supports) {
                    advice.doRestBodyHandle(body, params,mediaType,clazz,request, response);
                }
            }
        }
        return body;
    }

    @Override
    public void afterPropertiesSet() {
        if (this.restExceptionAdvices == null) {
            Assert.notNull(this.applicationContext, "No ApplicationContext");
            this.restExceptionAdvices = ContextHolder.getBeans(this.applicationContext, RestExceptionAdvice.class);
        }
        if (this.restBodyAdvices == null) {
            Assert.notNull(this.applicationContext, "No ApplicationContext");
            this.restBodyAdvices = ContextHolder.getBeans(this.applicationContext,RestBodyAdvice.class);
        }
    }

    /**
     * <code>getRestExceptionAdvices</code>
     * <p>the rest exception advices getter method.</p>
     * @return {@link java.util.List} <p>the rest exception advices return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<RestExceptionAdvice> getRestExceptionAdvices() {
        return this.restExceptionAdvices != null && !this.restExceptionAdvices.isEmpty() ? this.restExceptionAdvices : Collections.emptyList();
    }


    /**
     * <code>getRestBodyAdvices</code>
     * <p>the rest body advices getter method.</p>
     * @return {@link java.util.List} <p>the rest body advices return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<RestBodyAdvice> getRestBodyAdvices() {
        return this.restBodyAdvices != null && !this.restBodyAdvices.isEmpty() ? this.restBodyAdvices : Collections.emptyList();
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
        preExceptionHandle(exception,request,response);
        if (exception instanceof DefaultException) {
            DefaultException defaultException = (DefaultException) exception;
            doDefaultExceptionHandle(defaultException,request,response);
            boolean restExceptionEnabled = exceptionProperties.getConsoleLog().getRestExceptionEnabled();
            if (restExceptionEnabled) {
                printStackTrace(exception);
            }
            return ResponseEntity.ok(defaultException.buildResult());
        } else {
            doExceptionHandle(exception,request,response);
            boolean commonExceptionEnabled = exceptionProperties.getConsoleLog().getCommonExceptionEnabled();
            if (commonExceptionEnabled) {
                printStackTrace(exception);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResult.mistake(RestErrorStatus.UNKNOWN_ERROR,exception));
        }
    }

    /**
     * <code>printStackTrace</code>
     * <p>the stack trace method.</p>
     * @param exception {@link java.lang.Exception} <p>the exception parameter is <code>Exception</code> type.</p>
     * @see java.lang.Exception
     */
    private void printStackTrace(Exception exception) {
        try(StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter)) {
            exception.printStackTrace(printWriter);
            String stackTrace = stringWriter.toString();
            StackTraceElement stackTraceElement = exception.getStackTrace()[0];
            Integer line = stackTraceElement.getLineNumber();
            String resource = stackTraceElement.getClassName();
            String errorClass = exception.getClass().getName();
            log.error("{} [{}] {}: {} \n{}", resource,line, errorClass, exception.getMessage(), stackTrace);
        } catch (IOException ignored) {}
    }

    /**
     * <code>preExceptionHandle</code>
     * <p>the exception handle method.</p>
     * @param exception {@link java.lang.Exception} <p>the exception parameter is <code>Exception</code> type.</p>
     * @param request   {@link javax.servlet.http.HttpServletRequest} <p>the request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.Exception
     * @see javax.servlet.http.HttpServletRequest
     * @see javax.servlet.http.HttpServletResponse
     */
    private void preExceptionHandle(Exception exception,HttpServletRequest request, HttpServletResponse response)  {
        if (this.restExceptionAdvices != null && !this.restExceptionAdvices.isEmpty()) {
            for (RestExceptionAdvice advice : this.restExceptionAdvices) {
                advice.preExceptionHandle(exception, request, response);
            }
        }
    }

    /**
     * <code>doDefaultExceptionHandle</code>
     * <p>the default exception handle method.</p>
     * @param defaultException {@link io.github.nichetoolkit.rest.DefaultException} <p>the default exception parameter is <code>DefaultException</code> type.</p>
     * @param request          {@link javax.servlet.http.HttpServletRequest} <p>the request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response         {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @see io.github.nichetoolkit.rest.DefaultException
     * @see javax.servlet.http.HttpServletRequest
     * @see javax.servlet.http.HttpServletResponse
     */
    private void doDefaultExceptionHandle(DefaultException defaultException, HttpServletRequest request, HttpServletResponse response)  {
        RestException restException = (RestException) defaultException;
        if (this.restExceptionAdvices != null && !this.restExceptionAdvices.isEmpty()) {
            for (RestExceptionAdvice advice : this.restExceptionAdvices) {
                advice.doRestExceptionHandle(restException, request, response);
            }
        }
    }

    /**
     * <code>doExceptionHandle</code>
     * <p>the exception handle method.</p>
     * @param exception {@link java.lang.Exception} <p>the exception parameter is <code>Exception</code> type.</p>
     * @param request   {@link javax.servlet.http.HttpServletRequest} <p>the request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.Exception
     * @see javax.servlet.http.HttpServletRequest
     * @see javax.servlet.http.HttpServletResponse
     */
    private void doExceptionHandle(Exception exception,HttpServletRequest request, HttpServletResponse response)  {
        if (this.restExceptionAdvices != null && !this.restExceptionAdvices.isEmpty()) {
            for (RestExceptionAdvice advice : this.restExceptionAdvices) {
                advice.doExceptionHandle(exception, request, response);
            }
        }
    }

}
