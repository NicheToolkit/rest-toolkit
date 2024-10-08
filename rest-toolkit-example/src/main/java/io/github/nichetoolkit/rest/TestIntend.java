package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.configure.RestExceptionProperties;
import io.github.nichetoolkit.rest.worker.rsa.RsaWorker;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@Slf4j
@Data
public class TestIntend implements RestIntend<TestIntend> {

    @Resource
    private RestExceptionProperties exceptionProperties;

    @Resource
    private RsaWorker rsaWorker;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("[TestIntend] > afterPropertiesSet");
    }
}
