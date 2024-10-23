package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.configure.RestRsaProperties;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * <code>TestIntend</code>
 * <p>The test intend class.</p>
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
     * <code>rsaProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestRsaProperties} <p>The <code>rsaProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestRsaProperties
     * @see javax.annotation.Resource
     */
    @Resource
    private RestRsaProperties rsaProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("[TestIntend] > afterPropertiesSet: {}", JsonUtils.parseJson(rsaProperties));
    }
}
