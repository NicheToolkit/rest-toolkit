package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.configure.RestExceptionProperties;
import io.github.nichetoolkit.rest.holder.ApplicationContextHolder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * <code>DefaultControllerAdvice</code>
 * <p>The default controller advice class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
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
public class DefaultControllerAdvice implements ResponseBodyAdvice<Object>, InitializingBean {
    /**
     * <code>exceptionProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestExceptionProperties} <p>The <code>exceptionProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestExceptionProperties
     */
    private final RestExceptionProperties exceptionProperties;
    /**
     * <code>exceptionAdvices</code>
     * {@link java.util.List} <p>The <code>exceptionAdvices</code> field.</p>
     * @see java.util.List
     * @see org.springframework.lang.Nullable
     */
    @Nullable
    private List<RestExceptionAdvice> exceptionAdvices;
    /**
     * <code>responseAdvices</code>
     * {@link java.util.List} <p>The <code>responseAdvices</code> field.</p>
     * @see java.util.List
     * @see org.springframework.lang.Nullable
     */
    @Nullable
    private List<RestResponseAdvice> responseAdvices;

    /**
     * <code>DefaultControllerAdvice</code>
     * <p>Instantiates a new default controller advice.</p>
     * @param exceptionProperties {@link io.github.nichetoolkit.rest.configure.RestExceptionProperties} <p>The exception properties parameter is <code>RestExceptionProperties</code> type.</p>
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
            this.exceptionAdvices = ApplicationContextHolder.beansOfType(RestExceptionAdvice.class);
        }
        if (this.responseAdvices == null) {
            this.responseAdvices = ApplicationContextHolder.beansOfType(RestResponseAdvice.class);
        }
    }

    /**
     * <code>getExceptionAdvices</code>
     * <p>The get exception advices getter method.</p>
     * @return {@link java.util.List} <p>The get exception advices return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<RestExceptionAdvice> getExceptionAdvices() {
        return this.exceptionAdvices != null && !this.exceptionAdvices.isEmpty() ? this.exceptionAdvices : Collections.emptyList();
    }


    /**
     * <code>getResponseAdvices</code>
     * <p>The get response advices getter method.</p>
     * @return {@link java.util.List} <p>The get response advices return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<RestResponseAdvice> getResponseAdvices() {
        return this.responseAdvices != null && !this.responseAdvices.isEmpty() ? this.responseAdvices : Collections.emptyList();
    }

    /**
     * <code>exceptionHandle</code>
     * <p>The exception handle method.</p>
     * @param exception {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @param request   {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The exception handle return object is <code>ResponseEntity</code> type.</p>
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
                GeneralUtils.printStackTrace(log,exception,true);
            }
            return ResponseEntity.ok(restException.buildResult());
        } else {
            doExceptionHandle(exception, request, response);
            boolean commonExceptionEnabled = exceptionProperties.getConsoleLog().getCommonExceptionEnabled();
            if (commonExceptionEnabled) {
                GeneralUtils.printStackTrace(log,exception,true);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResult.mistake(RestErrorStatus.UNKNOWN_ERROR, exception));
        }
    }

    /**
     * <code>preExceptionHandle</code>
     * <p>The pre exception handle method.</p>
     * @param exception {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @param request   {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.Exception
     * @see javax.servlet.http.HttpServletRequest
     * @see javax.servlet.http.HttpServletResponse
     */
    private void preExceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        if (this.exceptionAdvices != null && !this.exceptionAdvices.isEmpty()) {
            for (RestExceptionAdvice advice : this.exceptionAdvices) {
                advice.preExceptionHandle(exception, request, response);
            }
        }
    }

    /**
     * <code>doRestExceptionHandle</code>
     * <p>The do rest exception handle method.</p>
     * @param restException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception parameter is <code>RestException</code> type.</p>
     * @param request       {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response      {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see javax.servlet.http.HttpServletRequest
     * @see javax.servlet.http.HttpServletResponse
     */
    private void doRestExceptionHandle(RestException restException, HttpServletRequest request, HttpServletResponse response) {
        if (this.exceptionAdvices != null && !this.exceptionAdvices.isEmpty()) {
            for (RestExceptionAdvice advice : this.exceptionAdvices) {
                advice.doRestExceptionHandle(restException, request, response);
            }
        }
    }

    /**
     * <code>doExceptionHandle</code>
     * <p>The do exception handle method.</p>
     * @param exception {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @param request   {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.Exception
     * @see javax.servlet.http.HttpServletRequest
     * @see javax.servlet.http.HttpServletResponse
     */
    private void doExceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        if (this.exceptionAdvices != null && !this.exceptionAdvices.isEmpty()) {
            for (RestExceptionAdvice advice : this.exceptionAdvices) {
                advice.doExceptionHandle(exception, request, response);
            }
        }
    }

}
