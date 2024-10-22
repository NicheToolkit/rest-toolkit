package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.configure.RestRsaProperties;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@Slf4j
@Setter
public class TestIntend implements RestIntend<TestIntend> {

    @Resource
    private RestRsaProperties rsaProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("[TestIntend] > afterPropertiesSet: {}", JsonUtils.parseJson(rsaProperties));
    }
}
