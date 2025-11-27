package io.github.nichetoolkit.rest.logback;

import io.github.nichetoolkit.rest.RestLoggingKey;
import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class DefaultLogbackFilter extends OncePerRequestFilter {
    private final RestLogbackProperties logbackProperties;
    private RestLoggingKey loggingKey;

    public DefaultLogbackFilter(RestLogbackProperties logbackProperties) {
        this.logbackProperties = logbackProperties;
    }

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
