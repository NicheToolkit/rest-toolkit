package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.configure.RestExceptionProperties;
import io.github.nichetoolkit.rest.worker.rsa.RsaWorker;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * <code>TestIntend</code>
 * <p>The type test intend class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestIntend
 * @see lombok.extern.slf4j.Slf4j
 * @see lombok.Setter
 * @since Jdk1.8
 */
@Slf4j
@Setter
public class TestIntend implements RestIntend<TestIntend> {

    /**
     * <code>exceptionProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestExceptionProperties} <p>The <code>exceptionProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestExceptionProperties
     * @see javax.annotation.Resource
     */
    @Resource
    private RestExceptionProperties exceptionProperties;

    /**
     * <code>rsaWorker</code>
     * {@link io.github.nichetoolkit.rest.worker.rsa.RsaWorker} <p>The <code>rsaWorker</code> field.</p>
     * @see io.github.nichetoolkit.rest.worker.rsa.RsaWorker
     * @see javax.annotation.Resource
     */
    @Resource
    private RsaWorker rsaWorker;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("[TestIntend] > afterPropertiesSet");
    }
}
