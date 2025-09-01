package io.github.nichetoolkit.rest.controller;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestHelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public RestResult<?> hello() {
        return RestResult.success(RestErrorStatus.HTTP_CONFIG_ERROR);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public RestResult<?> test() throws UnsupportedErrorException {
        throw new UnsupportedErrorException();
    }
}
