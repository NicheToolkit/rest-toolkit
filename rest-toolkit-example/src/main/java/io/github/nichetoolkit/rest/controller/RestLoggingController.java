package io.github.nichetoolkit.rest.controller;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.userlog.LoggingType;
import io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog;
import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <code>RestLoggingController</code>
 * <p>The type rest logging controller class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.web.bind.annotation.RestController
 * @see io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog
 * @see org.springframework.web.bind.annotation.RequestMapping
 * @since Jdk1.8
 */
@Slf4j
@RestController
@RestNotelog(loggingKey = "logging", notelog = "rest notelog")
@RequestMapping("/rest/logging")
public class RestLoggingController {

    /**
     * <code>test</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.GetMapping
     * @see io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog
     * @see io.github.nichetoolkit.rest.RestException
     */
    @GetMapping("/test")
    @RestUserlog(loggingValue = "logging test", userlog = "rest userlog")
    public RestResult<?> test() throws RestException {
        return RestResult.success();
    }
}
