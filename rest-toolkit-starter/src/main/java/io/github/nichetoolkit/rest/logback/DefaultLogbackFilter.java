package io.github.nichetoolkit.rest.logback;

import io.github.nichetoolkit.rest.RestLoggingKey;
import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.RestHttpRequest;
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
 * <code>DefaultLogbackFilter</code>
 * <p>The default logback filter class.</p>
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
public class DefaultLogbackFilter extends OncePerRequestFilter {
    /**
     * <code>logbackProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>The <code>logbackProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    private final RestLogbackProperties logbackProperties;
    /**
     * <code>loggingKey</code>
     * {@link io.github.nichetoolkit.rest.RestLoggingKey} <p>The <code>loggingKey</code> field.</p>
     * @see io.github.nichetoolkit.rest.RestLoggingKey
     */
    private RestLoggingKey loggingKey;

    /**
     * <code>DefaultLogbackFilter</code>
     * <p>Instantiates a new default logback filter.</p>
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>The logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultLogbackFilter(RestLogbackProperties logbackProperties) {
        this.logbackProperties = logbackProperties;
    }

    /**
     * <code>DefaultLogbackFilter</code>
     * <p>Instantiates a new default logback filter.</p>
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>The logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @param loggingKey        {@link io.github.nichetoolkit.rest.RestLoggingKey} <p>The logging key parameter is <code>RestLoggingKey</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     * @see io.github.nichetoolkit.rest.RestLoggingKey
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultLogbackFilter(RestLogbackProperties logbackProperties, RestLoggingKey loggingKey) {
        this.logbackProperties = logbackProperties;
        this.loggingKey = loggingKey;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        RestHttpRequest httpRequest = new RestHttpRequest(request);
        if (logbackProperties.getEnabled()) {
            String loggingKey = logbackProperties.getLoggingKey();
            String loggingKeyValue = null;
            if(GeneralUtils.isNotEmpty(this.loggingKey)) {
                loggingKeyValue = this.loggingKey.loggingKey(httpRequest);
            }
            if (GeneralUtils.isEmpty(loggingKeyValue)) {
                loggingKeyValue = httpRequest.getSession().getId();
            }
            if (GeneralUtils.isNotEmpty(loggingKeyValue)) {
                loggingKeyValue = "[".concat(loggingKeyValue).concat("]");
                MDC.put(loggingKey, loggingKeyValue);
                request.setAttribute(loggingKey, loggingKeyValue);
            }
            String requestKey = logbackProperties.getRequestKey();
            String requestIdValue = getRequestId(httpRequest);
            log.debug("request-id: {}, request-uri: {}", requestIdValue, request.getRequestURI());
            MDC.put(requestKey, requestIdValue);
            try {
                filterChain.doFilter(httpRequest, response);
            } finally {
                MDC.remove(loggingKey);
                MDC.clear();
            }
        } else {
            filterChain.doFilter(httpRequest, response);
        }
    }

    /**
     * <code>getRequestId</code>
     * <p>The get request id getter method.</p>
     * @param httpRequest {@link io.github.nichetoolkit.rest.RestHttpRequest} <p>The http request parameter is <code>RestHttpRequest</code> type.</p>
     * @return {@link java.lang.String} <p>The get request id return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestHttpRequest
     * @see java.lang.String
     */
    private String getRequestId(RestHttpRequest httpRequest) {
        String requestHeader = logbackProperties.getRequestHeader();
        String requestId = httpRequest.getHeader(requestHeader);
        if (GeneralUtils.isEmpty(requestId)) {
            requestId = GeneralUtils.uuid();
        }
        httpRequest.setRequestId(requestId);
        return requestId;
    }
}
