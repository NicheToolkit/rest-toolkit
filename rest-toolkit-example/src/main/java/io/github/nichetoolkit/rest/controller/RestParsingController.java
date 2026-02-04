package io.github.nichetoolkit.rest.controller;

import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.controller.pack.ParsingTestPack;
import io.github.nichetoolkit.rest.parsing.RequestParsing;
import io.github.nichetoolkit.rest.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/parsing")
public class RestParsingController {

    @PostMapping(value = "/test")
    public RestResult<?> test(@RequestParsing ParsingTestPack testPack) {
        log.info("test: {}", JacksonUtils.parseJson(testPack));
        return RestResult.success(testPack);
    }

}
