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
 * <p>DefaultRequestLogbackFilter</p>
 * 此过滤器需要 尽量放在最前面执行 但是必须放在 OrderedCharacterEncodingFilter 之后
 * {@link org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter}
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE + 100)
public class DefaultRequestLogbackFilter extends OncePerRequestFilter {
    @Autowired
    private RestLogbackProperties logbackProperties;
    @Autowired(required = false)
    private RestLogKey restLogKey;

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

    private String getRequestId(RestRequestWrapper requestWrapper) {
        String requestId = requestWrapper.getHeader(logbackProperties.getHeaderKey());
        if (GeneralUtils.isEmpty(requestId)) {
            requestId = GeneralUtils.uuid();
        }
        requestWrapper.setRequestId(requestId);
        return requestId;
    }
}
