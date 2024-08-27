package io.github.nichetoolkit.rest.logback;

import io.github.nichetoolkit.rest.RestLogKey;
import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <code>DefaultRequestLogbackFilter</code>
 * <p>The type default request logback filter class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.filter.OncePerRequestFilter
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Component
 * @see org.springframework.core.annotation.Order
 * @since Jdk1.8
 */
@Slf4j
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE + 100)
public class DefaultRequestLogbackFilter extends OncePerRequestFilter {
    /**
     * <code>logbackProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the <code>logbackProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    private final RestLogbackProperties logbackProperties;
    /**
     * <code>restLogKey</code>
     * {@link io.github.nichetoolkit.rest.RestLogKey} <p>the <code>restLogKey</code> field.</p>
     * @see io.github.nichetoolkit.rest.RestLogKey
     */
    private RestLogKey restLogKey;

    /**
     * <code>DefaultRequestLogbackFilter</code>
     * Instantiates a new default request logback filter.
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultRequestLogbackFilter(RestLogbackProperties logbackProperties) {
        this.logbackProperties = logbackProperties;
    }

    /**
     * <code>DefaultRequestLogbackFilter</code>
     * Instantiates a new default request logback filter.
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @param restLogKey        {@link io.github.nichetoolkit.rest.RestLogKey} <p>the rest log key parameter is <code>RestLogKey</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     * @see io.github.nichetoolkit.rest.RestLogKey
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultRequestLogbackFilter(RestLogbackProperties logbackProperties, RestLogKey restLogKey) {
        this.logbackProperties = logbackProperties;
        this.restLogKey = restLogKey;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        RestRequestWrapper requestWrapper = new RestRequestWrapper(request);
        if (logbackProperties.getEnabled()) {
            String logPrefixKey = logbackProperties.getLogKey();
            String logKey = null;
            if(GeneralUtils.isNotEmpty(restLogKey)) {
                logKey = restLogKey.logKey(requestWrapper);
            }
            if (GeneralUtils.isEmpty(logKey)) {
                logKey = requestWrapper.getSession().getId();
            }
            if (GeneralUtils.isNotEmpty(logKey)) {
                logKey = "[".concat(logKey).concat("]");
                MDC.put(logPrefixKey, logKey);
                request.setAttribute(logPrefixKey, logKey);
            }
            String requestId = getRequestId(requestWrapper);
            log.info("request id: {}, request uri: {}", requestId, request.getRequestURI());
            MDC.put(logbackProperties.getRequestKey(), requestId);
            try {
                filterChain.doFilter(requestWrapper, response);
            } finally {
                MDC.remove(logPrefixKey);
                MDC.clear();
            }
        } else {
            filterChain.doFilter(requestWrapper, response);
        }
    }

    /**
     * <code>getRequestId</code>
     * <p>the request id getter method.</p>
     * @param requestWrapper {@link io.github.nichetoolkit.rest.interceptor.RestRequestWrapper} <p>the request wrapper parameter is <code>RestRequestWrapper</code> type.</p>
     * @return {@link java.lang.String} <p>the request id return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.interceptor.RestRequestWrapper
     * @see java.lang.String
     */
    private String getRequestId(RestRequestWrapper requestWrapper) {
        String requestId = requestWrapper.getHeader(logbackProperties.getHeaderKey());
        if (GeneralUtils.isEmpty(requestId)) {
            requestId = GeneralUtils.uuid();
        }
        requestWrapper.setRequestId(requestId);
        return requestId;
    }
}
