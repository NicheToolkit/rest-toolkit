package io.github.nichetoolkit.rest.logback;

import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>DefaultLogKeyGenerator</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class DefaultLogKeyGenerator extends RestLogKeyGenerator {

    public DefaultLogKeyGenerator(RestLogbackProperties logbackProperties) {
        super(logbackProperties);
    }

    @Override
    public String accessToken(RestRequestWrapper requestWrapper) {
        return null;
    }
}
