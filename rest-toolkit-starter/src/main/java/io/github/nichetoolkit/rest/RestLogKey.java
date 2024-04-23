package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;

/**
 * <p>RestLogKey</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestLogKey {

    /**
     * 生成日志key
     * @param requestWrapper {@link RestRequestWrapper}
     * @return String
     */
    String logKey(RestRequestWrapper requestWrapper);
}
