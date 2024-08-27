package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;

/**
 * <code>RestLogKey</code>
 * <p>The type rest log key interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestLogKey {

    /**
     * <code>logKey</code>
     * <p>the key method.</p>
     * @param requestWrapper {@link io.github.nichetoolkit.rest.interceptor.RestRequestWrapper} <p>the request wrapper parameter is <code>RestRequestWrapper</code> type.</p>
     * @return {@link java.lang.String} <p>the key return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.interceptor.RestRequestWrapper
     * @see java.lang.String
     */
    String logKey(RestRequestWrapper requestWrapper);
}
