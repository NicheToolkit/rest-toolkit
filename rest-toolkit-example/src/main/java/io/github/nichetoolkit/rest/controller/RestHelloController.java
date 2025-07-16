package io.github.nichetoolkit.rest.controller;

import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.util.I18nUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.1.0/rest")
public class RestHelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public RestResult<?> hello() {
        return RestResult.success(I18nUtils.message("HELLO_MESSAGE"));
    }
}
