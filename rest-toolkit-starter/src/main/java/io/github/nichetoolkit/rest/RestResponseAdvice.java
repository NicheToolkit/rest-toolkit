package io.github.nichetoolkit.rest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

/**
 * <code>RestResponseAdvice</code>
 * <p>The type rest response advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestResponseAdvice {

    /**
     * <code>supports</code>
     * <p>the method.</p>
     * @param params {@link org.springframework.core.MethodParameter} <p>the params parameter is <code>MethodParameter</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return boolean <p>the return object is <code>boolean</code> type.</p>
     * @see org.springframework.core.MethodParameter
     * @see java.lang.Class
     */
    default boolean supports(MethodParameter params, Class<?> clazz) {
        return true;
    }

    /**
     * <code>doResponseBodyHandle</code>
     * <p>the response body handle method.</p>
     * @param resultBody {@link java.lang.Object} <p>the result body parameter is <code>Object</code> type.</p>
     * @param params     {@link org.springframework.core.MethodParameter} <p>the params parameter is <code>MethodParameter</code> type.</p>
     * @param mediaType  {@link org.springframework.http.MediaType} <p>the media type parameter is <code>MediaType</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param request    {@link org.springframework.http.server.ServerHttpRequest} <p>the request parameter is <code>ServerHttpRequest</code> type.</p>
     * @param response   {@link org.springframework.http.server.ServerHttpResponse} <p>the response parameter is <code>ServerHttpResponse</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.core.MethodParameter
     * @see org.springframework.http.MediaType
     * @see java.lang.Class
     * @see org.springframework.http.server.ServerHttpRequest
     * @see org.springframework.http.server.ServerHttpResponse
     */
    default void doResponseBodyHandle(Object resultBody, MethodParameter params, MediaType mediaType, Class<?> clazz, ServerHttpRequest request, ServerHttpResponse response)  {
    }

}
