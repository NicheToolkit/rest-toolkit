package io.github.nichetoolkit.rest.handler;

import io.github.nichetoolkit.rest.*;
import io.github.nichetoolkit.rest.configure.RestExceptionProperties;
import io.github.nichetoolkit.rest.fitter.RestFulfilledFitter;
import io.github.nichetoolkit.rest.holder.ApplicationContextHolder;
import io.github.nichetoolkit.rest.holder.BeanDefinitionRegistryHolder;
import io.github.nichetoolkit.rest.holder.ListableBeanFactoryHolder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.I18nUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * <code>RestControllerHandler</code>
 * <p>The rest controller handler class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
 * @see org.springframework.beans.factory.InitializingBean
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.web.bind.annotation.CrossOrigin
 * @see org.springframework.web.bind.annotation.RestControllerAdvice
 * @since Jdk17
 */
@Slf4j
@CrossOrigin
@RestControllerAdvice
public class RestControllerHandler implements ResponseBodyAdvice<Object>, InitializingBean {

    /**
     * <code>IS_HAS_INIT_OF_FULFILLED_FITTER</code>
     * <p>The <code>IS_HAS_INIT_OF_FULFILLED_FITTER</code> field.</p>
     */
    static boolean IS_HAS_INIT_OF_FULFILLED_FITTER = false;
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
     * @see org.jspecify.annotations.Nullable
     */
    @Nullable
    private List<RestExceptionAdvice> exceptionAdvices;
    /**
     * <code>responseAdvices</code>
     * {@link java.util.List} <p>The <code>responseAdvices</code> field.</p>
     * @see java.util.List
     * @see org.jspecify.annotations.Nullable
     */
    @Nullable
    private List<RestResponseAdvice> responseAdvices;

    /**
     * <code>RestControllerHandler</code>
     * <p>Instantiates a new rest controller handler.</p>
     * @param exceptionProperties {@link io.github.nichetoolkit.rest.configure.RestExceptionProperties} <p>The exception properties parameter is <code>RestExceptionProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestExceptionProperties
     */
    public RestControllerHandler(RestExceptionProperties exceptionProperties) {
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

    /**
     * <code>applyExceptionAdvices</code>
     * <p>The apply exception advices method.</p>
     * @return {@link java.util.List} <p>The apply exception advices return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<RestExceptionAdvice> applyExceptionAdvices() {
        return ApplicationContextHolder.beansOfType(RestExceptionAdvice.class);
    }

    /**
     * <code>applyResponseAdvices</code>
     * <p>The apply response advices method.</p>
     * @return {@link java.util.List} <p>The apply response advices return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<RestResponseAdvice> applyResponseAdvices() {
        return ApplicationContextHolder.beansOfType(RestResponseAdvice.class);
    }

    /**
     * <code>getExceptionAdvices</code>
     * <p>The get exception advices getter method.</p>
     * @return {@link java.util.List} <p>The get exception advices return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    protected List<RestExceptionAdvice> getExceptionAdvices() {
        return this.exceptionAdvices != null && !this.exceptionAdvices.isEmpty() ? this.exceptionAdvices : Collections.emptyList();
    }

    /**
     * <code>getResponseAdvices</code>
     * <p>The get response advices getter method.</p>
     * @return {@link java.util.List} <p>The get response advices return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    protected List<RestResponseAdvice> getResponseAdvices() {
        return this.responseAdvices != null && !this.responseAdvices.isEmpty() ? this.responseAdvices : Collections.emptyList();
    }

    @Override
    public void afterPropertiesSet() {
        if (this.exceptionAdvices == null) {
            this.exceptionAdvices = applyExceptionAdvices();
        }
        if (this.responseAdvices == null) {
            this.responseAdvices = applyResponseAdvices();
        }
        log.debug("The exception      properties: {}", JsonUtils.parseJson(exceptionProperties));
        initOfFulfilledFitter();
    }

    /**
     * <code>initOfFulfilledFitter</code>
     * <p>The init of fulfilled fitter method.</p>
     * @throws BeansException {@link org.springframework.beans.BeansException} <p>The beans exception is <code>BeansException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see org.springframework.beans.BeansException
     */
    @SuppressWarnings("rawtypes")
    private void initOfFulfilledFitter() throws BeansException {
        if (IS_HAS_INIT_OF_FULFILLED_FITTER) {
            return;
        }
        IS_HAS_INIT_OF_FULFILLED_FITTER = true;
        List<RestFulfilledFitter> fulfilledFitters = ApplicationContextHolder.beansOfType(RestFulfilledFitter.class);
        if (GeneralUtils.isNotEmpty(fulfilledFitters)) {
            return;
        }
        fulfilledFitters = SpringFactoriesLoader.loadFactories(RestFulfilledFitter.class, null);
        if (GeneralUtils.isEmpty(fulfilledFitters)) {
            return;
        }
        fulfilledFitters.sort(RestOrder::compareTo);
        for (RestFulfilledFitter<?> fulfilledFitter : fulfilledFitters) {
            fulfilledFitter = BeanDefinitionRegistryHolder.registerRootBeanDefinition(fulfilledFitter.beanName(), fulfilledFitter.beanType(), fulfilledFitter.beanScope());
            ListableBeanFactoryHolder.autowireBeanProperties(fulfilledFitter);
            fulfilledFitter.afterAutowirePropertiesSet();
        }
        log.debug("There are {} fulfilled fitter beans has be initiated.", fulfilledFitters.size());
    }

    /**
     * <code>exceptionHandle</code>
     * <p>The exception handle method.</p>
     * @param exception {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @param request   {@link jakarta.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The exception handle return object is <code>ResponseEntity</code> type.</p>
     * @see java.lang.Exception
     * @see jakarta.servlet.http.HttpServletRequest
     * @see jakarta.servlet.http.HttpServletResponse
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
            Boolean transformEnabled = exceptionProperties.getMessageI18n().getTransformEnabled();
            if (transformEnabled) {
                String messagePrefix = exceptionProperties.getMessageI18n().getMessagePrefix();
                String message = restException.getMessage();
                if (message.startsWith(messagePrefix)) {
                    message = message.substring(messagePrefix.length());
                    String i18nMessage = I18nUtils.message(message);
                    return ResponseEntity.ok(restException.buildResult(i18nMessage));
                } else {
                    return ResponseEntity.ok(restException.buildResult());
                }
            } else {
                return ResponseEntity.ok(restException.buildResult());
            }
        } else {
            doExceptionHandle(exception, request, response);
            boolean commonExceptionEnabled = exceptionProperties.getConsoleLog().getCommonExceptionEnabled();
            if (commonExceptionEnabled) {
                GeneralUtils.printStackTrace(log,exception,true);
            }
            Throwable cause = exception.getCause();
            if (cause instanceof RestStatus) {
                RestStatus restStatus = (RestStatus) cause;
                return ResponseEntity.ok(restStatus.buildResult());
            }  else if (exception instanceof RestStatus) {
                RestStatus restStatus = (RestStatus) exception;
                return ResponseEntity.ok(restStatus.buildResult());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResult.mistake(RestErrorStatus.UNKNOWN_ERROR, exception));
            }
        }
    }

    /**
     * <code>preExceptionHandle</code>
     * <p>The pre exception handle method.</p>
     * @param exception {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @param request   {@link jakarta.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.Exception
     * @see jakarta.servlet.http.HttpServletRequest
     * @see jakarta.servlet.http.HttpServletResponse
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
     * @param request       {@link jakarta.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response      {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see jakarta.servlet.http.HttpServletRequest
     * @see jakarta.servlet.http.HttpServletResponse
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
     * @param request   {@link jakarta.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.Exception
     * @see jakarta.servlet.http.HttpServletRequest
     * @see jakarta.servlet.http.HttpServletResponse
     */
    private void doExceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        if (this.exceptionAdvices != null && !this.exceptionAdvices.isEmpty()) {
            for (RestExceptionAdvice advice : this.exceptionAdvices) {
                advice.doExceptionHandle(exception, request, response);
            }
        }
    }

}
