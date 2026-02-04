package io.github.nichetoolkit.rest.parsing;

import io.github.nichetoolkit.rest.configure.RestParsingProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * <code>DefaultRequestParsingArgumentResolver</code>
 * <p>The default request parsing argument resolver class.</p>
 * @see  io.github.nichetoolkit.rest.parsing.RequestParsingArgumentResolver
 * @see  lombok.extern.slf4j.Slf4j
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
public class DefaultRequestParsingArgumentResolver extends RequestParsingArgumentResolver {

    /**
     * <code>DefaultRequestParsingArgumentResolver</code>
     * <p>Instantiates a new default request parsing argument resolver.</p>
     * @param parsingProperties {@link io.github.nichetoolkit.rest.configure.RestParsingProperties} <p>The parsing properties parameter is <code>RestParsingProperties</code> type.</p>
     * @see  io.github.nichetoolkit.rest.configure.RestParsingProperties
     */
    public DefaultRequestParsingArgumentResolver(RestParsingProperties parsingProperties) {
        super(parsingProperties);
    }

}