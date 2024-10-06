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
 * <code>RestResults</code>
 * <p>The type rest results class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings({"TypeParameterUnusedInFormals","SameNameButDifferent","rawtypes"})
public class RestResults {

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, TypeReference<T> typeReference) throws RestException {
        return result(response, null, typeReference, false);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        return result(response, null, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param resource      {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.HttpMethod
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, String resource, TypeReference<T> typeReference, HttpMethod method) throws RestException {
        return result(response, resource, typeReference, isCheckData(method));
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param resource      {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, String resource, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(typeReference);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, Class<T> clazz) throws RestException {
        return result(response, null, clazz, false);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, Class<T> clazz, boolean isCheckData) throws RestException {
        return result(response, null, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Class<T> clazz) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see org.springframework.http.HttpMethod
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, String resource, Class<T> clazz, HttpMethod method) throws RestException {
        return result(response, resource, clazz, isCheckData(method));
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param resource    {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, String resource, Class<T> clazz, boolean isCheckData) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(clazz);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, JavaType javaType) throws RestException {
        return result(response, null, javaType, false);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, JavaType javaType, boolean isCheckData) throws RestException {
        return result(response, null, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, JavaType javaType) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, JavaType javaType) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.HttpMethod
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, String resource, JavaType javaType, HttpMethod method) throws RestException {
        return result(response, resource, javaType, isCheckData(method));
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param resource    {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(String response, String resource, JavaType javaType, boolean isCheckData) throws RestException {
        RestResult<T> restResult = JsonUtils.parseResult(response, javaType);
        checkRestResponse(restResult, resource, false, isCheckData);
        return restResult;
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, TypeReference<T> typeReference) throws RestException {
        return bean(response, null, typeReference);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url);
        return bean(response, resource, typeReference);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers);
        return bean(response, resource, typeReference);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, params);
        return bean(response, resource, typeReference);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body);
        return bean(response, resource, typeReference);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body);
        return bean(response, resource, typeReference);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, params);
        return bean(response, resource, typeReference);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body, params);
        return bean(response, resource, typeReference);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return bean(response, resource, typeReference);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param resource      {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, String resource, TypeReference<T> typeReference) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(typeReference);
        return bean(response, resource, javaType);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, Class<T> clazz) throws RestException {
        return bean(response, null, clazz);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, Class<T> clazz) throws RestException {
        String resource = resource(method, url);
        return bean(response, resource, clazz);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers);
        return bean(response, resource, clazz);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, params);
        return bean(response, resource, clazz);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body);
        return bean(response, resource, clazz);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body);
        return bean(response, resource, clazz);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, params);
        return bean(response, resource, clazz);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body, params);
        return bean(response, resource, clazz);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return bean(response, resource, clazz);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, String resource, Class<T> clazz) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(clazz);
        return bean(response, resource, javaType);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, JavaType javaType) throws RestException {
        return bean(response, null, javaType);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, JavaType javaType) throws RestException {
        String resource = resource(method, url);
        return bean(response, resource, javaType);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers);
        return bean(response, resource, javaType);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, params);
        return bean(response, resource, javaType);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, Object body, JavaType javaType) throws RestException {
        String resource = resource(method, url, body);
        return bean(response, resource, javaType);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, Object body, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body);
        return bean(response, resource, javaType);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, params);
        return bean(response, resource, javaType);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, body, params);
        return bean(response, resource, javaType);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return bean(response, resource, javaType);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link java.lang.String} <p>The response parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(String response, String resource, JavaType javaType) throws RestException {
        RestResult<T> result = result(response, resource, javaType, true);
        return Optional.ofNullable(result).map(RestResult::getData).orElse(null);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, TypeReference<T> typeReference) throws RestException {
        return result(response, null, typeReference, false);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        return result(response, null, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, typeReference, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param resource      {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.HttpMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, String resource, TypeReference<T> typeReference, HttpMethod method) throws RestException {
        return result(response, resource, typeReference, isCheckData(method));
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param resource      {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, String resource, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(typeReference);
        return result(response,resource,javaType,isCheckData);
    }


    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, Class<T> clazz) throws RestException {
        return result(response, null, clazz, false);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, Class<T> clazz, boolean isCheckData) throws RestException {
        return result(response, null, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Class<T> clazz) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, clazz, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see java.lang.String
     * @see java.lang.Class
     * @see org.springframework.http.HttpMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, String resource, Class<T> clazz, HttpMethod method) throws RestException {
        return result(response, resource, clazz, isCheckData(method));
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param resource    {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, String resource, Class<T> clazz, boolean isCheckData) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(clazz);
        return result(response,resource,javaType,isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, JavaType javaType) throws RestException {
        return result(response, null, javaType, false);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, JavaType javaType, boolean isCheckData) throws RestException {
        return result(response, null, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, JavaType javaType) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, JavaType javaType) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, javaType, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.HttpMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, String resource, JavaType javaType, HttpMethod method) throws RestException {
        return result(response, resource, javaType, isCheckData(method));
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link io.github.nichetoolkit.rest.RestResult} <p>The response parameter is <code>RestResult</code> type.</p>
     * @param resource    {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(RestResult response, String resource, JavaType javaType, boolean isCheckData) throws RestException {
        checkRestResponse(response, resource, false, isCheckData);
        String resultJson = JsonUtils.parseJson(response);
        return JsonUtils.parseResult(resultJson, javaType);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, TypeReference<T> typeReference) throws RestException {
        return result(response, null, typeReference, true, false);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        return result(response, null, typeReference, true, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, typeReference, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers       {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, typeReference, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param resource      {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param method        {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.HttpMethod
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, String resource, TypeReference<T> typeReference, HttpMethod method) throws RestException {
        return result(response, resource, typeReference, isCheckBody(method), isCheckData(method));
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response      {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param resource      {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @param isCheckBody   boolean <p>The is check body parameter is <code>boolean</code> type.</p>
     * @param isCheckData   boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, String resource, TypeReference<T> typeReference, boolean isCheckBody, boolean isCheckData) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(typeReference);
        return result(response, resource, javaType, isCheckBody, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, Class<T> clazz) throws RestException {
        return result(response, null, clazz, true, false);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, Class<T> clazz, boolean isCheckData) throws RestException {
        return result(response, null, clazz, true, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Class<T> clazz) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, clazz, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params, Class<T> clazz, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, clazz, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.String
     * @see java.lang.Class
     * @see org.springframework.http.HttpMethod
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, String resource, Class<T> clazz, HttpMethod method) throws RestException {
        return result(response, resource, clazz, isCheckBody(method), isCheckData(method));
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param resource    {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param isCheckBody boolean <p>The is check body parameter is <code>boolean</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, String resource, Class<T> clazz, boolean isCheckBody, boolean isCheckData) throws RestException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(clazz);
        return result(response, resource, javaType, isCheckBody, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response,JavaType javaType) throws RestException {
        return result(response, null, javaType, true, false);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response,JavaType javaType, boolean isCheckData) throws RestException {
        return result(response, null, javaType, true, isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url,JavaType javaType) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers,JavaType javaType) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, MultiValueMap<String, String> params,JavaType javaType) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, MultiValueMap<String, String> params,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, params);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body,JavaType javaType) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body,JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params,JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, params);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params,JavaType javaType) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, body, params);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params,JavaType javaType) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, javaType, method);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method      {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers     {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params,JavaType javaType, boolean isCheckData) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return result(response, resource, javaType, isCheckBody(method), isCheckData);
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.HttpMethod
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, String resource,JavaType javaType, HttpMethod method) throws RestException {
        return result(response, resource, javaType, isCheckBody(method), isCheckData(method));
    }

    /**
     * <code>result</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param resource    {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @param isCheckBody boolean <p>The is check body parameter is <code>boolean</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> result(ResponseEntity<RestResult> response, String resource, JavaType javaType, boolean isCheckBody, boolean isCheckData) throws RestException {
        checkRestResponse(response, resource, isCheckBody, isCheckData);
        String resultJson = JsonUtils.parseJson(response.getBody());
        return JsonUtils.parseResult(resultJson, javaType);
    }


    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response T <p>The response parameter is <code>T</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(T response) throws RestException {
        return bean(response, null, false, false);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response T <p>The response parameter is <code>T</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(T response, HttpMethod method, String url) throws RestException {
        String resource = resource(method, url);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response T <p>The response parameter is <code>T</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(T response, HttpMethod method, String url, HttpHeaders headers) throws RestException {
        String resource = resource(method, url, headers);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response T <p>The response parameter is <code>T</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(T response, HttpMethod method, String url, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, params);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response T <p>The response parameter is <code>T</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(T response, HttpMethod method, String url, Object body) throws RestException {
        String resource = resource(method, url, body);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response T <p>The response parameter is <code>T</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(T response, HttpMethod method, String url, HttpHeaders headers, Object body) throws RestException {
        String resource = resource(method, url, headers, body);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response T <p>The response parameter is <code>T</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(T response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, headers, params);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response T <p>The response parameter is <code>T</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(T response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, body, params);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response T <p>The response parameter is <code>T</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(T response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response T <p>The response parameter is <code>T</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(T response, String resource, HttpMethod method) throws RestException {
        checkRestResponse(response, resource, isCheckBody(method), isCheckData(method));
        return response;
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    T <p>The response parameter is <code>T</code> type.</p>
     * @param resource    {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param isCheckBody boolean <p>The is check body parameter is <code>boolean</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(T response, String resource, boolean isCheckBody, boolean isCheckData) throws RestException {
        checkRestResponse(response, resource, isCheckBody, isCheckData);
        return response;
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(ResponseEntity<T> response) throws RestException {
        return bean(response, null, false, false);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url) throws RestException {
        String resource = resource(method, url);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url, HttpHeaders headers) throws RestException {
        String resource = resource(method, url, headers);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, params);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url, Object body) throws RestException {
        String resource = resource(method, url, body);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url, HttpHeaders headers, Object body) throws RestException {
        String resource = resource(method, url, headers, body);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, headers, params);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url, Object body, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, body, params);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers  {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(ResponseEntity<T> response, HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params) throws RestException {
        String resource = resource(method, url, headers, body, params);
        return bean(response, resource, method);
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param method   {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(ResponseEntity<T> response, String resource, HttpMethod method) throws RestException {
        return bean(response, resource, isCheckBody(method), isCheckData(method));
    }

    /**
     * <code>bean</code>
     * <p>The method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param response    {@link org.springframework.http.ResponseEntity} <p>The response parameter is <code>ResponseEntity</code> type.</p>
     * @param resource    {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param isCheckBody boolean <p>The is check body parameter is <code>boolean</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T bean(ResponseEntity<T> response, String resource, boolean isCheckBody, boolean isCheckData) throws RestException {
        checkRestResponse(response, resource, isCheckBody, isCheckData);
        return response.getBody();
    }

    /**
     * <code>isCheckBody</code>
     * <p>The check body method.</p>
     * @param method {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @return boolean <p>The check body return object is <code>boolean</code> type.</p>
     * @see org.springframework.http.HttpMethod
     */
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

    /**
     * <code>isCheckData</code>
     * <p>The check data method.</p>
     * @param method {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @return boolean <p>The check data return object is <code>boolean</code> type.</p>
     * @see org.springframework.http.HttpMethod
     */
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

    /**
     * <code>checkRestResponse</code>
     * <p>The rest response method.</p>
     * @param response    {@link java.lang.Object} <p>The response parameter is <code>Object</code> type.</p>
     * @param resource    {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param isCheckBody boolean <p>The is check body parameter is <code>boolean</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
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


    /**
     * <code>checkResponse</code>
     * <p>The response method.</p>
     * @param response {@link java.lang.Object} <p>The response parameter is <code>Object</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
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


    /**
     * <code>checkResult</code>
     * <p>The result method.</p>
     * @param restResult {@link io.github.nichetoolkit.rest.RestResult} <p>The rest result parameter is <code>RestResult</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
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


    /**
     * <code>checkEntity</code>
     * <p>The entity method.</p>
     * @param responseEntity {@link org.springframework.http.ResponseEntity} <p>The response entity parameter is <code>ResponseEntity</code> type.</p>
     * @param resource       {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
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

    /**
     * <code>checkResultSuccess</code>
     * <p>The result success method.</p>
     * @param restResult {@link io.github.nichetoolkit.rest.RestResult} <p>The rest result parameter is <code>RestResult</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
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

    /**
     * <code>checkEntitySuccess</code>
     * <p>The entity success method.</p>
     * @param responseEntity {@link org.springframework.http.ResponseEntity} <p>The response entity parameter is <code>ResponseEntity</code> type.</p>
     * @param resource       {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkEntitySuccess(ResponseEntity responseEntity, String resource) throws RestException {
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            if (GeneralUtils.isNotEmpty(resource)) {
                log.error("the response entity is failed! resource: {}, status: {}, message: {}, ", resource, responseEntity.getStatusCodeValue(), responseEntity);
                throw new HttpResultFailException(resource, responseEntity);
            } else {
                log.error("the response entity is failed! status: {}, message: {}, ", responseEntity.getStatusCodeValue(), responseEntity);
                throw new HttpResultFailException(responseEntity);
            }
        }
    }

    /**
     * <code>checkBody</code>
     * <p>The body method.</p>
     * @param responseEntity {@link org.springframework.http.ResponseEntity} <p>The response entity parameter is <code>ResponseEntity</code> type.</p>
     * @param resource       {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param isCheckBody    boolean <p>The is check body parameter is <code>boolean</code> type.</p>
     * @param isCheckData    boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
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

    /**
     * <code>checkData</code>
     * <p>The data method.</p>
     * @param restResult  {@link io.github.nichetoolkit.rest.RestResult} <p>The rest result parameter is <code>RestResult</code> type.</p>
     * @param resource    {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param isCheckData boolean <p>The is check data parameter is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
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

    /**
     * <code>resource</code>
     * <p>The method.</p>
     * @param method {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     */
    public static String resource(HttpMethod method, String url) {
        return resource(method, url, null, null, null);
    }

    /**
     * <code>resource</code>
     * <p>The method.</p>
     * @param method {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     */
    public static String resource(HttpMethod method, String url, MultiValueMap<String, String> params) {
        return resource(method, url, null, null, params);
    }

    /**
     * <code>resource</code>
     * <p>The method.</p>
     * @param method  {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url     {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     */
    public static String resource(HttpMethod method, String url, HttpHeaders headers) {
        return resource(method, url, headers, null, null);
    }

    /**
     * <code>resource</code>
     * <p>The method.</p>
     * @param method  {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url     {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params  {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     */
    public static String resource(HttpMethod method, String url, HttpHeaders headers, MultiValueMap<String, String> params) {
        return resource(method, url, headers, null, params);
    }

    /**
     * <code>resource</code>
     * <p>The method.</p>
     * @param method {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body   {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static String resource(HttpMethod method, String url, Object body) {
        return resource(method, url, null, body, null);
    }


    /**
     * <code>resource</code>
     * <p>The method.</p>
     * @param method  {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url     {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body    {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     */
    public static String resource(HttpMethod method, String url, HttpHeaders headers, Object body) {
        return resource(method, url, headers, body, null);
    }

    /**
     * <code>resource</code>
     * <p>The method.</p>
     * @param method {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body   {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     */
    public static String resource(HttpMethod method, String url, Object body, MultiValueMap<String, String> params) {
        return resource(method, url, null, body, params);
    }

    /**
     * <code>resource</code>
     * <p>The method.</p>
     * @param method  {@link org.springframework.http.HttpMethod} <p>The method parameter is <code>HttpMethod</code> type.</p>
     * @param url     {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param headers {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @param body    {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params  {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see org.springframework.http.HttpMethod
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     */
    public static String resource(HttpMethod method, String url, HttpHeaders headers, Object body, MultiValueMap<String, String> params) {
        StringBuilder resourceBuilder = new StringBuilder();
        if (GeneralUtils.isNotEmpty(method)) {
            resourceBuilder.append(" method: ").append(method.name());
        }
        if (GeneralUtils.isNotEmpty(url)) {
            resourceBuilder.append(" url: ").append(url);
        }
        if (GeneralUtils.isNotEmpty(headers)) {
            resourceBuilder.append(" headers: ").append(headers);
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
