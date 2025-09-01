package io.github.nichetoolkit.rest;

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
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@Slf4j
@CrossOrigin
@RestControllerAdvice
public abstract class RestControllerHandler implements ResponseBodyAdvice<Object>, InitializingBean {

    static boolean IS_HAS_INIT_OF_FULFILLED_FITTER = false;
    private final RestExceptionProperties exceptionProperties;
    @Nullable
    private List<RestExceptionAdvice> exceptionAdvices;
    @Nullable
    private List<RestResponseAdvice> responseAdvices;

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

    public List<RestExceptionAdvice> applyExceptionAdvices() {
        return ApplicationContextHolder.beansOfType(RestExceptionAdvice.class);
    }

    public List<RestResponseAdvice> applyResponseAdvices() {
        return ApplicationContextHolder.beansOfType(RestResponseAdvice.class);
    }

    protected List<RestExceptionAdvice> getExceptionAdvices() {
        return this.exceptionAdvices != null && !this.exceptionAdvices.isEmpty() ? this.exceptionAdvices : Collections.emptyList();
    }

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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResult.mistake(RestErrorStatus.UNKNOWN_ERROR, exception));
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
