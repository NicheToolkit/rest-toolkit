package io.github.nichetoolkit.rest.controller;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog;
import io.github.nichetoolkit.rest.userlog.stereotype.RestUserlog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RestNotelog("rest notelog")
@RequestMapping("/rest")
public class RestLoggingController {

    @GetMapping("/logging")
    @RestUserlog(loggingKey = 999, loggingValue = "logging test", userlog = "rest userlog")
    public RestResult<?> test() throws RestException {
        return RestResult.success();
    }
}
