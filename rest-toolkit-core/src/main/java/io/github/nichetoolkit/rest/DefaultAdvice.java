package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.configure.RestExceptionProperties;
import io.github.nichetoolkit.rest.holder.ContextHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>DefaultAdvice</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Order(0)
@CrossOrigin
@ControllerAdvice
@RestControllerAdvice
public class DefaultAdvice implements ResponseBodyAdvice<Object>, ApplicationContextAware, InitializingBean {
    private final RestExceptionProperties exceptionProperties;

    @Nullable
    private ApplicationContext applicationContext;
    @Nullable
    private List<RestExceptionAdvice> restExceptionAdvices;
    @Nullable
    private List<RestBodyAdvice> restBodyAdvices;

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
                if (advice.supports(params,clazz)) {
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

    public List<RestExceptionAdvice> getRestExceptionAdvices() {
        return this.restExceptionAdvices != null && !this.restExceptionAdvices.isEmpty() ? this.restExceptionAdvices : Collections.emptyList();
    }


    public List<RestBodyAdvice> getRestBodyAdvices() {
        return this.restBodyAdvices != null && !this.restBodyAdvices.isEmpty() ? this.restBodyAdvices : Collections.emptyList();
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        ContextHolder.initApplicationContext(applicationContext);
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> exceptionHandle(Exception exception,HttpServletRequest request, HttpServletResponse response) {
        preExceptionHandle(exception,request,response);
        if (exception instanceof DefaultException) {
            DefaultException defaultException = (DefaultException) exception;
            doDefaultExceptionHandle(defaultException,request,response);
            boolean restExceptionEnabled = exceptionProperties.getConsoleLog().getRestExceptionEnabled();
            if (restExceptionEnabled) {
                exception.printStackTrace();
            }
            return ResponseEntity.ok(defaultException.buildResult());
        } else {
            doExceptionHandle(exception,request,response);
            boolean commonExceptionEnabled = exceptionProperties.getConsoleLog().getCommonExceptionEnabled();
            if (commonExceptionEnabled) {
                exception.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DefaultResult.fail(RestErrorStatus.UNKNOWN_ERROR,exception));
        }
    }

    private void preExceptionHandle(Exception exception,HttpServletRequest request, HttpServletResponse response)  {
        if (this.restExceptionAdvices != null && !this.restExceptionAdvices.isEmpty()) {
            for (RestExceptionAdvice advice : this.restExceptionAdvices) {
                advice.preExceptionHandle(exception, request, response);
            }
        }
    }

    private void doDefaultExceptionHandle(DefaultException defaultException, HttpServletRequest request, HttpServletResponse response)  {
        RestException restException = (RestException) defaultException;
        if (this.restExceptionAdvices != null && !this.restExceptionAdvices.isEmpty()) {
            for (RestExceptionAdvice advice : this.restExceptionAdvices) {
                advice.doRestExceptionHandle(restException, request, response);
            }
        }
    }

    private void doExceptionHandle(Exception exception,HttpServletRequest request, HttpServletResponse response)  {
        if (this.restExceptionAdvices != null && !this.restExceptionAdvices.isEmpty()) {
            for (RestExceptionAdvice advice : this.restExceptionAdvices) {
                advice.doExceptionHandle(exception, request, response);
            }
        }
    }

}
