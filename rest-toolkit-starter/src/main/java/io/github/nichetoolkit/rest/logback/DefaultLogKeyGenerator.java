package io.github.nichetoolkit.rest.logback;

import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * <code>DefaultLogKeyGenerator</code>
 * <p>The type default log key generator class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.logback.RestLogKeyGenerator
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class DefaultLogKeyGenerator extends RestLogKeyGenerator {

    /**
     * <code>DefaultLogKeyGenerator</code>
     * Instantiates a new default log key generator.
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    public DefaultLogKeyGenerator(RestLogbackProperties logbackProperties) {
        super(logbackProperties);
    }

    @Override
    public String accessToken(RestRequestWrapper requestWrapper) {
        return null;
    }
}
