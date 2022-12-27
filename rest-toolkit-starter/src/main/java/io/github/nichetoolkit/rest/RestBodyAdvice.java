package io.github.nichetoolkit.rest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

/**
 * <p>RestBodyAdvice</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestBodyAdvice {

    default boolean supports(MethodParameter params, Class clazz) {
        return true;
    }

    default void doRestBodyHandle(Object resultBody, MethodParameter params, MediaType mediaType, Class clazz, ServerHttpRequest request, ServerHttpResponse response)  {
    }

}
