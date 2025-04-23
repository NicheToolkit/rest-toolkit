package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.configure.RestRadixProperties;
import io.github.nichetoolkit.rest.configure.RestRsaProperties;
import io.github.nichetoolkit.rest.configure.RestShaProperties;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <code>TestFitter</code>
 * <p>The test fitter class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestFulfilledFitter
 * @see lombok.extern.slf4j.Slf4j
 * @see lombok.Setter
 * @since Jdk1.8
 */
@Slf4j
@Setter
public class TestFitter implements RestFulfilledFitter<TestFitter> {
    /**
     * <code>radixProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestRadixProperties} <p>The <code>radixProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestRadixProperties
     * @see javax.annotation.Resource
     */
    @Resource
    private RestRadixProperties radixProperties;
    /**
     * <code>rsaProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestRsaProperties} <p>The <code>rsaProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestRsaProperties
     * @see javax.annotation.Resource
     */
    @Resource
    private RestRsaProperties rsaProperties;

    @Override
    public void afterAutowirePropertiesSet() {
        log.info("[TestFitter] > afterPropertiesSet: {}", JsonUtils.parseJson(rsaProperties));
    }
}
