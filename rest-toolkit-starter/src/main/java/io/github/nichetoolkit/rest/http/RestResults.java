package io.github.nichetoolkit.rest.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.error.network.HttpResponseNullException;
import io.github.nichetoolkit.rest.error.network.HttpResultDataNullException;
import io.github.nichetoolkit.rest.error.network.HttpResultFailException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

/**
 * <p>RestResults</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@SuppressWarnings({"TypeParameterUnusedInFormals","SameNameButDifferent"})
public class RestResults {

    public static <T> RestResult<T> result(String response, TypeReference<T> typeReference) throws RestException {
        return result(response, null, typeReference, false);
    }

    public static <T> RestResult<T> result(String response, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        return result(response, null, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(String response, String resource, TypeReference<T> typeReference, HttpMethod method) throws RestException {
        return result(response, resource, typeReference, isCheckData(method));
    }

    public static <T> RestResult<T> result(String response, String resource, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(typeReference);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(String response, Class<T> clazz) throws RestException {
        return result(response, null, clazz, false);
    }

    public static <T> RestResult<T> result(String response, Class<T> clazz, boolean isCheckData) throws RestException {
        return result(response, null, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Class<T> clazz) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(String response, String resource, Class<T> clazz, HttpMethod method) throws RestException {
        return result(response, resource, clazz, isCheckData(method));
    }

    public static <T> RestResult<T> result(String response, String resource, Class<T> clazz, boolean isCheckData) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(clazz);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(String response, JavaType javaType) throws RestException {
        return result(response, null, javaType, false);
    }

    public static <T> RestResult<T> result(String response, JavaType javaType, boolean isCheckData) throws RestException {
        return result(response, null, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, JavaType javaType) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, JavaType javaType) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(String response, String resource, JavaType javaType, HttpMethod method) throws RestException {
        return result(response, resource, javaType, isCheckData(method));
    }

    public static <T> RestResult<T> result(String response, String resource, JavaType javaType, boolean isCheckData) throws RestException {
        RestResult<T> restResult = JsonUtils.parseResult(response, javaType);
        checkRestResponse(restResult, resource, false, isCheckData);
        return restResult;
    }

    public static <T> T bean(String response, TypeReference<T> typeReference) throws RestException {
        return bean(response, null, typeReference);
    }

    public static <T> T bean(String response, HttpMethod method, String url, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url);
        return bean(response, resource, typeReference);
    }

    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers);
        return bean(response, resource, typeReference);
    }

    public static <T> T bean(String response, HttpMethod method, String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, params);
        return bean(response, resource, typeReference);
    }

    public static <T> T bean(String response, HttpMethod method, String url, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body);
        return bean(response, resource, typeReference);
    }

    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body);
        return bean(response, resource, typeReference);
    }

    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, params);
        return bean(response, resource, typeReference);
    }

    public static <T> T bean(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body, params);
        return bean(response, resource, typeReference);
    }

    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return bean(response, resource, typeReference);
    }

    public static <T> T bean(String response, String resource, TypeReference<T> typeReference) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(typeReference);
        return bean(response, resource, javaType);
    }

    public static <T> T bean(String response, Class<T> clazz) throws RestException {
        return bean(response, null, clazz);
    }

    public static <T> T bean(String response, HttpMethod method, String url, Class<T> clazz) throws RestException {
        String resource = resource(method, url);
        return bean(response, resource, clazz);
    }

    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers);
        return bean(response, resource, clazz);
    }

    public static <T> T bean(String response, HttpMethod method, String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, params);
        return bean(response, resource, clazz);
    }

    public static <T> T bean(String response, HttpMethod method, String url, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body);
        return bean(response, resource, clazz);
    }

    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body);
        return bean(response, resource, clazz);
    }

    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, params);
        return bean(response, resource, clazz);
    }

    public static <T> T bean(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body, params);
        return bean(response, resource, clazz);
    }

    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return bean(response, resource, clazz);
    }

    public static <T> T bean(String response, String resource, Class<T> clazz) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(clazz);
        return bean(response, resource, javaType);
    }

    public static <T> T bean(String response, JavaType javaType) throws RestException {
        return bean(response, null, javaType);
    }

    public static <T> T bean(String response, HttpMethod method, String url, JavaType javaType) throws RestException {
        String resource = resource(method, url);
        return bean(response, resource, javaType);
    }

    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers);
        return bean(response, resource, javaType);
    }

    public static <T> T bean(String response, HttpMethod method, String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, params);
        return bean(response, resource, javaType);
    }

    public static <T> T bean(String response, HttpMethod method, String url, Object body, JavaType javaType) throws RestException {
        String resource = resource(method, url, body);
        return bean(response, resource, javaType);
    }

    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, Object body, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body);
        return bean(response, resource, javaType);
    }

    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, params);
        return bean(response, resource, javaType);
    }

    public static <T> T bean(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, body, params);
        return bean(response, resource, javaType);
    }

    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return bean(response, resource, javaType);
    }

    public static <T> T bean(String response, String resource, JavaType javaType) throws RestException {
        RestResult<T> result = result(response, resource, javaType, true);
        return Optional.ofNullable(result).map(RestResult::getData).orElse(null);
    }

    public static <T> RestResult<T> result(RestResult response, TypeReference<T> typeReference) throws RestException {
        return result(response, null, typeReference, false);
    }

    public static <T> RestResult<T> result(RestResult response, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        return result(response, null, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, typeReference, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, String resource, TypeReference<T> typeReference, HttpMethod method) throws RestException {
        return result(response, resource, typeReference, isCheckData(method));
    }

    public static <T> RestResult<T> result(RestResult response, String resource, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(typeReference);
        return result(response,resource,javaType,isCheckData);
    }


    public static <T> RestResult<T> result(RestResult response, Class<T> clazz) throws RestException {
        return result(response, null, clazz, false);
    }

    public static <T> RestResult<T> result(RestResult response, Class<T> clazz, boolean isCheckData) throws RestException {
        return result(response, null, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Class<T> clazz) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, clazz, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, String resource, Class<T> clazz, HttpMethod method) throws RestException {
        return result(response, resource, clazz, isCheckData(method));
    }

    public static <T> RestResult<T> result(RestResult response, String resource, Class<T> clazz, boolean isCheckData) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(clazz);
        return result(response,resource,javaType,isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, JavaType javaType) throws RestException {
        return result(response, null, javaType, false);
    }

    public static <T> RestResult<T> result(RestResult response, JavaType javaType, boolean isCheckData) throws RestException {
        return result(response, null, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, JavaType javaType) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, JavaType javaType) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, javaType, isCheckData);
    }

    public static <T> RestResult<T> result(RestResult response, String resource, JavaType javaType, HttpMethod method) throws RestException {
        return result(response, resource, javaType, isCheckData(method));
    }

    public static <T> RestResult<T> result(RestResult response, String resource, JavaType javaType, boolean isCheckData) throws RestException {
        checkRestResponse(response, resource, false, isCheckData);
        String resultJson = JsonUtils.parseJson(response);
        return JsonUtils.parseResult(resultJson, javaType);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, TypeReference<T> typeReference) throws RestException {
        return result(response, null, typeReference, true, false);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        return result(response, null, typeReference, true, isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, typeReference, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, String resource, TypeReference<T> typeReference, HttpMethod method) throws RestException {
        return result(response, resource, typeReference, isCheckBody(method), isCheckData(method));
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, String resource, TypeReference<T> typeReference, boolean isCheckBody, boolean isCheckData) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(typeReference);
        return result(response, resource, javaType, isCheckBody, isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, Class<T> clazz) throws RestException {
        return result(response, null, clazz, true, false);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, Class<T> clazz, boolean isCheckData) throws RestException {
        return result(response, null, clazz, true, isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Class<T> clazz) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, clazz, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, String resource, Class<T> clazz, HttpMethod method) throws RestException {
        return result(response, resource, clazz, isCheckBody(method), isCheckData(method));
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, String resource, Class<T> clazz, boolean isCheckBody, boolean isCheckData) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(clazz);
        return result(response, resource, javaType, isCheckBody, isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response,JavaType javaType) throws RestException {
        return result(response, null, javaType, true, false);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response,JavaType javaType, boolean isCheckData) throws RestException {
        return result(response, null, javaType, true, isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url,JavaType javaType) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers,JavaType javaType) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, MultiValueMap<String, String> params,JavaType javaType) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, MultiValueMap<String, String> params,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body,JavaType javaType) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body,JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params,JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params,JavaType javaType) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params,JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, javaType, method);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, String resource,JavaType javaType, HttpMethod method) throws RestException {
        return result(response, resource, javaType, isCheckBody(method), isCheckData(method));
    }

    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, String resource, JavaType javaType, boolean isCheckBody, boolean isCheckData) throws RestException {
        checkRestResponse(response, resource, isCheckBody, isCheckData);
        String resultJson = JsonUtils.parseJson(response.getBody());
        return JsonUtils.parseResult(resultJson, javaType);
    }


    public static <T> T bean(T response) throws RestException {
        return bean(response, null, false, false);
    }

    public static <T> T bean(T response, HttpMethod method, String url) throws RestException {
        String resource = resource(method, url);
        return bean(response, resource, method);
    }

    public static <T> T bean(T response, HttpMethod method, String url, HttpHeaders headers) throws RestException {
        String resource = resource(method, url, headers);
        return bean(response, resource, method);
    }

    public static <T> T bean(T response, HttpMethod method, String url, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, params);
        return bean(response, resource, method);
    }

    public static <T> T bean(T response, HttpMethod method, String url, Object body) throws RestException {
        String resource = resource(method, url, body);
        return bean(response, resource, method);
    }

    public static <T> T bean(T response, HttpMethod method, String url, HttpHeaders headers, Object body) throws RestException {
        String resource = resource(method, url, headers, body);
        return bean(response, resource, method);
    }

    public static <T> T bean(T response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, headers, params);
        return bean(response, resource, method);
    }

    public static <T> T bean(T response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, body, params);
        return bean(response, resource, method);
    }

    public static <T> T bean(T response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return bean(response, resource, method);
    }

    public static <T> T bean(T response, String resource, HttpMethod method) throws RestException {
        checkRestResponse(response, resource, isCheckBody(method), isCheckData(method));
        return response;
    }

    public static <T> T bean(T response, String resource, boolean isCheckBody, boolean isCheckData) throws RestException {
        checkRestResponse(response, resource, isCheckBody, isCheckData);
        return response;
    }

    public static <T> T bean(ResponseEntity<T> response) throws RestException {
        return bean(response, null, false, false);
    }

    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url) throws RestException {
        String resource = resource(method, url);
        return bean(response, resource, method);
    }

    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url, HttpHeaders headers) throws RestException {
        String resource = resource(method, url, headers);
        return bean(response, resource, method);
    }

    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, params);
        return bean(response, resource, method);
    }

    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url, Object body) throws RestException {
        String resource = resource(method, url, body);
        return bean(response, resource, method);
    }

    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url, HttpHeaders headers, Object body) throws RestException {
        String resource = resource(method, url, headers, body);
        return bean(response, resource, method);
    }

    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, headers, params);
        return bean(response, resource, method);
    }

    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, body, params);
        return bean(response, resource, method);
    }

    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return bean(response, resource, method);
    }

    public static <T> T bean(ResponseEntity<T> response, String resource, HttpMethod method) throws RestException {
        return bean(response, resource, isCheckBody(method), isCheckData(method));
    }

    public static <T> T bean(ResponseEntity<T> response, String resource, boolean isCheckBody, boolean isCheckData) throws RestException {
        checkRestResponse(response, resource, isCheckBody, isCheckData);
        return response.getBody();
    }

    public static boolean isCheckBody(HttpMethod method) {
        switch (method) {
            case OPTIONS:
            case HEAD:
            case TRACE:
                return false;
            case GET:
            case PUT:
            case POST:
            case PATCH:
            case DELETE:
            default:
                return true;
        }
    }

    public static boolean isCheckData(HttpMethod method) {
        switch (method) {
            case GET:
                return true;
            case OPTIONS:
            case HEAD:
            case TRACE:
            case PUT:
            case POST:
            case PATCH:
            case DELETE:
            default:
                return false;
        }
    }

    public static void checkRestResponse(Object response, String resource, boolean isCheckBody, boolean isCheckData) throws RestException {
        if (GeneralUtils.isNotEmpty(response)) {
            if (response instanceof ResponseEntity) {
                ResponseEntity responseEntity = (ResponseEntity) response;
                checkEntity(responseEntity, resource);
                checkEntitySuccess(responseEntity, resource);
                checkBody(responseEntity, resource, isCheckBody, isCheckData);
            } else if (response instanceof RestResult) {
                RestResult restResult = (RestResult) response;
                checkResult(restResult, resource);
                checkResultSuccess(restResult, resource);
                checkData(restResult, resource, isCheckData);
            }
        } else {
            checkResponse(response, resource);
        }
    }


    public static void checkResponse(Object response, String resource) throws RestException {
        if (GeneralUtils.isEmpty(response)) {
            if (GeneralUtils.isNotEmpty(resource)) {
                log.error("the response is null, resource: {}", resource);
                throw new HttpResponseNullException(resource, "the response is null");
            } else {
                log.error("the response is null");
                throw new HttpResponseNullException("the response is null");
            }
        }
    }


    public static void checkResult(RestResult restResult, String resource) throws RestException {
        if (GeneralUtils.isEmpty(restResult)) {
            if (GeneralUtils.isNotEmpty(resource)) {
                log.error("the response is parsed to io.github.nichetoolkit.rest result fail! resource: {}", resource);
                throw new HttpResponseNullException(resource, "the response is parsed to io.github.nichetoolkit.rest result fail!");
            } else {
                log.error("the response is parsed to io.github.nichetoolkit.rest result fail!");
                throw new HttpResponseNullException("the response is parsed to io.github.nichetoolkit.rest result fail!");
            }
        }
    }


    public static void checkEntity(ResponseEntity responseEntity, String resource) throws RestException {
        if (GeneralUtils.isEmpty(responseEntity)) {
            if (GeneralUtils.isNotEmpty(resource)) {
                log.error("the response is parsed to response entity fail! resource: {}", resource);
                throw new HttpResponseNullException(resource, "the response is parsed to response entity fail!");
            } else {
                log.error("the response is parsed to io.github.nichetoolkit.rest response entity!");
                throw new HttpResponseNullException("the response is parsed to response entity fail!");
            }

        }
    }

    public static void checkResultSuccess(RestResult restResult, String resource) throws RestException {
        if (!restResult.isSuccess()) {
            if (GeneralUtils.isNotEmpty(resource)) {
                log.error("the io.github.nichetoolkit.rest result is failed! resource: {}, status: {}, message: {}, ", resource, restResult.getStatus(), restResult.getMessage());
                throw new HttpResultFailException(resource, restResult);
            } else {
                log.error("the io.github.nichetoolkit.rest result is failed! status: {}, message: {}, ", restResult.getStatus(), restResult.getMessage());
                throw new HttpResultFailException(restResult);
            }
        }
    }

    public static void checkEntitySuccess(ResponseEntity responseEntity, String resource) throws RestException {
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            if (GeneralUtils.isNotEmpty(resource)) {
                log.error("the response entity is failed! resource: {}, status: {}, message: {}, ", resource, responseEntity.getStatusCodeValue(), responseEntity.toString());
                throw new HttpResultFailException(resource, responseEntity);
            } else {
                log.error("the response entity is failed! status: {}, message: {}, ", responseEntity.getStatusCodeValue(), responseEntity.toString());
                throw new HttpResultFailException(responseEntity);
            }
        }
    }

    public static void checkBody(ResponseEntity responseEntity, String resource, boolean isCheckBody, boolean isCheckData) throws RestException {
        if (isCheckBody) {
            if (GeneralUtils.isNotEmpty(responseEntity.getBody())) {
                Object body = responseEntity.getBody();
                String bodyString = body.toString();
                RestResult restResult = JsonUtils.parseBean(bodyString, RestResult.class);
                if (GeneralUtils.isNotEmpty(restResult)) {
                    checkResult(restResult, resource);
                    checkResultSuccess(restResult, resource);
                    checkData(restResult, resource, isCheckData);
                } else if (body instanceof RestResult) {
                    restResult = (RestResult) body;
                    checkResult(restResult, resource);
                    checkResultSuccess(restResult, resource);
                    checkData(restResult, resource, isCheckData);
                }
            } else {
                if (GeneralUtils.isNotEmpty(resource)) {
                    log.error("the response entity body is null! resource: {}", resource);
                    throw new HttpResultDataNullException(resource);
                } else {
                    log.error("the response entity body is null!");
                    throw new HttpResultDataNullException();
                }
            }
        }
    }

    public static void checkData(RestResult restResult, String resource, boolean isCheckData) throws RestException {
        if (isCheckData && GeneralUtils.isEmpty(restResult.getData())) {
            if (GeneralUtils.isNotEmpty(resource)) {
                log.error("the io.github.nichetoolkit.rest result data is null! resource: {}", resource);
                throw new HttpResultDataNullException(resource);
            } else {
                log.error("the io.github.nichetoolkit.rest result data is null!");
                throw new HttpResultDataNullException();
            }
        }
    }

    public static String resource(HttpMethod method, String url) {
        return resource(method, url, null, null, null);
    }

    public static String resource(HttpMethod method, String url, MultiValueMap<String, String> params) {
        return resource(method, url, null, null, params);
    }

    public static String resource(HttpMethod method, String url, HttpHeaders headers) {
        return resource(method, url, headers, null, null);
    }

    public static String resource(HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params) {
        return resource(method, url, headers, null, params);
    }

    public static String resource(HttpMethod method, String url, Object body) {
        return resource(method, url, null, body, null);
    }


    public static String resource(HttpMethod method, String url, HttpHeaders headers, Object body) {
        return resource(method, url, headers, body, null);
    }

    public static String resource(HttpMethod method, String url, Object body, MultiValueMap<String, String> params) {
        return resource(method, url, null, body, params);
    }

    public static String resource(HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params) {
        StringBuilder resourceBuilder = new StringBuilder();
        if (GeneralUtils.isNotEmpty(method)) {
            resourceBuilder.append(" method: ").append(method.name());
        }
        if (GeneralUtils.isNotEmpty(url)) {
            resourceBuilder.append(" url: ").append(url);
        }
        if (GeneralUtils.isNotEmpty(headers)) {
            resourceBuilder.append(" headers: ").append(headers.toString());
        }
        if (GeneralUtils.isNotEmpty(body)) {
            resourceBuilder.append(" body: ").append(JsonUtils.parseJson(body));
        }
        if (GeneralUtils.isNotEmpty(params)) {
            resourceBuilder.append(" params: ").append(JsonUtils.parseJson(params));
        }
        return resourceBuilder.toString();
    }
}
