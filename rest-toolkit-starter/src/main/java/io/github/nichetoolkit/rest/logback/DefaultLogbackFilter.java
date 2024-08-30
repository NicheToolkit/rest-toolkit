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
public class DefaultLogbackFilter extends OncePerRequestFilter {
    private final RestLogbackProperties logbackProperties;
    private RestLoggingKey loggingKey;

    /**
     * <code>DefaultRequestLogbackFilter</code>
     * Instantiates a new default request logback filter.
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public DefaultLogbackFilter(RestLogbackProperties logbackProperties) {
        this.logbackProperties = logbackProperties;
    }

    /**
     * <code>DefaultRequestLogbackFilter</code>
     * Instantiates a new default request logback filter.
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @param loggingKey        {@link io.github.nichetoolkit.rest.RestLoggingKey} <p>the logging key parameter is <code>RestLoggingKey</code> type.</p>
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
            String logPrefixKey = logbackProperties.getLoggingKey();
            String loggingKey = null;
            if(GeneralUtils.isNotEmpty(this.loggingKey)) {
                loggingKey = this.loggingKey.loggingKey(httpRequest);
            }
            if (GeneralUtils.isEmpty(loggingKey)) {
                loggingKey = httpRequest.getSession().getId();
            }
            if (GeneralUtils.isNotEmpty(loggingKey)) {
                loggingKey = "[".concat(loggingKey).concat("]");
                MDC.put(logPrefixKey, loggingKey);
                request.setAttribute(logPrefixKey, loggingKey);
            }
            String requestId = getRequestId(httpRequest);
            log.info("request id: {}, request uri: {}", requestId, request.getRequestURI());
            MDC.put(logbackProperties.getRequestKey(), requestId);
            try {
                filterChain.doFilter(httpRequest, response);
            } finally {
                MDC.remove(logPrefixKey);
                MDC.clear();
            }
        } else {
            filterChain.doFilter(httpRequest, response);
        }
    }

    private String getRequestId(RestHttpRequest httpRequest) {
        String requestId = httpRequest.getHeader(logbackProperties.getHeaderKey());
        if (GeneralUtils.isEmpty(requestId)) {
            requestId = GeneralUtils.uuid();
        }
        httpRequest.setRequestId(requestId);
        return requestId;
    }
}
