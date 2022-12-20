package io.github.nichetoolkit.rest.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.error.network.HttpErrorException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>RestTemplates</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@SuppressWarnings({"SameNameButDifferent", "TypeParameterUnusedInFormals"})
public class RestTemplates {

    private final RestTemplate restTemplate;

    private static RestTemplates INSTANCE;

    @Autowired
    public RestTemplates(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void restTemplatesInit() {
        INSTANCE = this;
    }

    public static <K, V> MultiValueMap<K, V> emptyMap() {
        return new LinkedMultiValueMap<>(0);
    }

    public static <K, V> MultiValueMap<K, V> singletonMap(K key, V value) {
        LinkedMultiValueMap<K, V> singletonMap = new LinkedMultiValueMap<>(1);
        singletonMap.add(key, value);
        return singletonMap;
    }

    public static <K, V> MultiValueMap<K, V> add(Map<K, V> paramsMap) {
        if (GeneralUtils.isEmpty(paramsMap)) {
            return emptyMap();
        }
        LinkedMultiValueMap<K, V> singletonMap = new LinkedMultiValueMap<>(paramsMap.size());
        paramsMap.forEach(singletonMap::add);
        return singletonMap;
    }

    public static <K, V> MultiValueMap<K, V> put(Map<K, List<V>> paramsMap) {
        if (GeneralUtils.isEmpty(paramsMap)) {
            return emptyMap();
        }
        LinkedMultiValueMap<K, V> singletonMap = new LinkedMultiValueMap<>(paramsMap.size());
        paramsMap.forEach(singletonMap::put);
        return singletonMap;
    }

    public static <K, V> MultiValueMap<K, List<V>> singletonListMap(K key, V value) {
        LinkedMultiValueMap<K, List<V>> singletonMap = new LinkedMultiValueMap<>(1);
        singletonMap.add(key, Collections.singletonList(value));
        return singletonMap;
    }

    public static <K, V, O> MultiValueMap<K, List<V>> merge(MultiValueMap<K, V> paramsMap, K key, V value) {
        if (GeneralUtils.isEmpty(paramsMap)) {
            return emptyMap();
        }
        LinkedMultiValueMap<K, List<V>> singletonMap = new LinkedMultiValueMap<>();
        paramsMap.forEach(singletonMap::add);
        if (GeneralUtils.isNotEmpty(value)) {
            singletonMap.add(key, Collections.singletonList(value));
        }
        return singletonMap;
    }

    public static <K, V, O> MultiValueMap<K, List<V>> merge(MultiValueMap<K, V> firstMap, MultiValueMap<K, V> secondMap) {
        if (GeneralUtils.isEmpty(firstMap) && GeneralUtils.isEmpty(secondMap)) {
            return emptyMap();
        }
        LinkedMultiValueMap<K, List<V>> singletonMap = new LinkedMultiValueMap<>();
        if (GeneralUtils.isNotEmpty(firstMap)) {
            firstMap.forEach(singletonMap::add);
        }
        if (GeneralUtils.isNotEmpty(secondMap)) {
            secondMap.forEach(singletonMap::add);
        }
        return singletonMap;
    }

    public static HttpEntity jsonHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(null, headers);
    }

    public static HttpEntity jsonHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

    public static HttpEntity jsonHttpEntity(HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(null, headers);
    }

    public static HttpEntity jsonHttpEntity(Object body, HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

    public static HttpEntity formHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(null, headers);
    }

    public static HttpEntity formHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(body, headers);
    }

    public static HttpEntity formHttpEntity(HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(null, headers);
    }

    public static HttpEntity formHttpEntity(Object body, HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(body, headers);
    }

    public static <T> T formObject(String url, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(), typeReference);
    }

    public static <T> T formObject(String url, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(), javaType);
    }

    public static <T> T formObject(String url, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(), clazz);
    }

    public static <T> T formObject(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(), params, typeReference);
    }

    public static <T> T formObject(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(), params, javaType);
    }

    public static <T> T formObject(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(), params, clazz);
    }

    public static <T> T formObject(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(body), typeReference);
    }

    public static <T> T formObject(String url, Object body, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(body), javaType);
    }

    public static <T> T formObject(String url, Object body, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(body), clazz);
    }

    public static <T> T formObject(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(body), params, typeReference);
    }

    public static <T> T formObject(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(body), params, javaType);
    }

    public static <T> T formObject(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(body), params, clazz);
    }

    public static <T> T formObject(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(httpHeaders), typeReference);
    }

    public static <T> T formObject(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(httpHeaders), javaType);
    }

    public static <T> T formObject(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(httpHeaders), clazz);
    }

    public static <T> T formObject(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(httpHeaders), params, typeReference);
    }

    public static <T> T formObject(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(httpHeaders), params, javaType);
    }

    public static <T> T formObject(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(httpHeaders), params, clazz);
    }

    public static <T> T formObject(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(body, httpHeaders), typeReference);
    }

    public static <T> T formObject(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(body, httpHeaders), javaType);
    }

    public static <T> T formObject(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(body, httpHeaders), clazz);
    }

    public static <T> T formObject(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(body, httpHeaders), params, typeReference);
    }

    public static <T> T formObject(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(body, httpHeaders), params, javaType);
    }

    public static <T> T formObject(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(body, httpHeaders), params, clazz);
    }


    public static <T> RestResult<T> formObjectResult(String url, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(), typeReference);
    }

    public static <T> RestResult<T> formObjectResult(String url, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(), javaType);
    }

    public static <T> RestResult<T> formObjectResult(String url, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(), clazz);
    }

    public static <T> RestResult<T> formObjectResult(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(), params, typeReference);
    }

    public static <T> RestResult<T> formObjectResult(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(), params, javaType);
    }

    public static <T> RestResult<T> formObjectResult(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(), params, clazz);
    }

    public static <T> RestResult<T> formObjectResult(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(body), typeReference);
    }

    public static <T> RestResult<T> formObjectResult(String url, Object body, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(body), javaType);
    }

    public static <T> RestResult<T> formObjectResult(String url, Object body, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(body), clazz);
    }

    public static <T> RestResult<T> formObjectResult(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(body), params, typeReference);
    }

    public static <T> RestResult<T> formObjectResult(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(body), params, javaType);
    }

    public static <T> RestResult<T> formObjectResult(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(body), params, clazz);
    }

    public static <T> RestResult<T> formObjectResult(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(httpHeaders), typeReference);
    }

    public static <T> RestResult<T> formObjectResult(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(httpHeaders), javaType);
    }

    public static <T> RestResult<T> formObjectResult(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(httpHeaders), clazz);
    }

    public static <T> RestResult<T> formObjectResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(httpHeaders), params, typeReference);
    }

    public static <T> RestResult<T> formObjectResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(httpHeaders), params, javaType);
    }

    public static <T> RestResult<T> formObjectResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(httpHeaders), params, clazz);
    }

    public static <T> RestResult<T> formObjectResult(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(body, httpHeaders), typeReference);
    }

    public static <T> RestResult<T> formObjectResult(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(body, httpHeaders), javaType);
    }

    public static <T> RestResult<T> formObjectResult(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(body, httpHeaders), clazz);
    }

    public static <T> RestResult<T> formObjectResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(body, httpHeaders), params, typeReference);
    }

    public static <T> RestResult<T> formObjectResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(body, httpHeaders), params, javaType);
    }

    public static <T> RestResult<T> formObjectResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(body, httpHeaders), params, clazz);
    }


    public static <T> T postObject(String url, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(), typeReference);
    }

    public static <T> T postObject(String url, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(), javaType);
    }

    public static <T> T postObject(String url, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(), clazz);
    }

    public static <T> T postObject(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(), params, typeReference);
    }

    public static <T> T postObject(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(), params, javaType);
    }

    public static <T> T postObject(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(), params, clazz);
    }

    public static <T> T postObject(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(body), typeReference);
    }

    public static <T> T postObject(String url, Object body, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(body), javaType);
    }

    public static <T> T postObject(String url, Object body, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(body), clazz);
    }

    public static <T> T postObject(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(body), params, typeReference);
    }

    public static <T> T postObject(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(body), params, javaType);
    }

    public static <T> T postObject(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(body), params, clazz);
    }

    public static <T> T postObject(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(httpHeaders), typeReference);
    }

    public static <T> T postObject(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(httpHeaders), javaType);
    }

    public static <T> T postObject(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(httpHeaders), clazz);
    }

    public static <T> T postObject(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(httpHeaders), params, typeReference);
    }

    public static <T> T postObject(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(httpHeaders), params, javaType);
    }

    public static <T> T postObject(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(httpHeaders), params, clazz);
    }

    public static <T> T postObject(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(body, httpHeaders), typeReference);
    }

    public static <T> T postObject(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(body, httpHeaders), javaType);
    }

    public static <T> T postObject(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(body, httpHeaders), clazz);
    }

    public static <T> T postObject(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(body, httpHeaders), params, typeReference);
    }

    public static <T> T postObject(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(body, httpHeaders), params, javaType);
    }

    public static <T> T postObject(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(body, httpHeaders), params, clazz);
    }

    public static <T> T postObject(String url, HttpEntity httpEntity, TypeReference<T> typeReference) throws RestException {
        String response = postString(url, httpEntity);
        return JsonUtils.parseBean(response, typeReference);
    }

    public static <T> T postObject(String url, HttpEntity httpEntity, JavaType javaType) throws RestException {
        String response = postString(url, httpEntity);
        return JsonUtils.parseBean(response, javaType);
    }

    public static <T> T postObject(String url, HttpEntity httpEntity, Class<T> clazz) throws RestException {
        String response = postString(url, httpEntity);
        return JsonUtils.parseBean(response, clazz);
    }

    public static <T> T postObject(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String response = postString(url, httpEntity, params);
        return JsonUtils.parseBean(response, typeReference);
    }

    public static <T> T postObject(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String response = postString(url, httpEntity, params);
        return JsonUtils.parseBean(response, javaType);
    }

    public static <T> T postObject(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String response = postString(url, httpEntity, params);
        return JsonUtils.parseBean(response, clazz);
    }

    public static <T> RestResult<T> postObjectResult(String url, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(), typeReference);
    }

    public static <T> RestResult<T> postObjectResult(String url, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(), javaType);
    }

    public static <T> RestResult<T> postObjectResult(String url, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(), clazz);
    }

    public static <T> RestResult<T> postObjectResult(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(), params, typeReference);
    }

    public static <T> RestResult<T> postObjectResult(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(), params, javaType);
    }

    public static <T> RestResult<T> postObjectResult(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(), params, clazz);
    }

    public static <T> RestResult<T> postObjectResult(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body), typeReference);
    }

    public static <T> RestResult<T> postObjectResult(String url, Object body, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body), javaType);
    }

    public static <T> RestResult<T> postObjectResult(String url, Object body, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body), clazz);
    }

    public static <T> RestResult<T> postObjectResult(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body), params, typeReference);
    }

    public static <T> RestResult<T> postObjectResult(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body), params, javaType);
    }

    public static <T> RestResult<T> postObjectResult(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body), params, clazz);
    }

    public static <T> RestResult<T> postObjectResult(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(httpHeaders), typeReference);
    }

    public static <T> RestResult<T> postObjectResult(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(httpHeaders), javaType);
    }

    public static <T> RestResult<T> postObjectResult(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(httpHeaders), clazz);
    }

    public static <T> RestResult<T> postObjectResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(httpHeaders), params, typeReference);
    }

    public static <T> RestResult<T> postObjectResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(httpHeaders), params, javaType);
    }

    public static <T> RestResult<T> postObjectResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(httpHeaders), params, clazz);
    }

    public static <T> RestResult<T> postObjectResult(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body, httpHeaders), typeReference);
    }

    public static <T> RestResult<T> postObjectResult(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body, httpHeaders), javaType);
    }

    public static <T> RestResult<T> postObjectResult(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body, httpHeaders), clazz);
    }

    public static <T> RestResult<T> postObjectResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body, httpHeaders), params, typeReference);
    }

    public static <T> RestResult<T> postObjectResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body, httpHeaders), params, javaType);
    }

    public static <T> RestResult<T> postObjectResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body, httpHeaders), params, clazz);
    }

    public static <T> RestResult<T> postObjectResult(String url, HttpEntity httpEntity, TypeReference<T> typeReference) throws RestException {
        String response = postString(url, httpEntity);
        return RestResults.result(response, HttpMethod.POST, url, typeReference);
    }

    public static <T> RestResult<T> postObjectResult(String url, HttpEntity httpEntity, JavaType javaType) throws RestException {
        String response = postString(url, httpEntity);
        return RestResults.result(response, HttpMethod.POST, url, javaType);
    }

    public static <T> RestResult<T> postObjectResult(String url, HttpEntity httpEntity, Class<T> clazz) throws RestException {
        String response = postString(url, httpEntity);
        return RestResults.result(response, HttpMethod.POST, url, clazz);
    }

    public static <T> RestResult<T> postObjectResult(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String response = postString(url, httpEntity, params);
        return RestResults.result(response, HttpMethod.POST, url, typeReference);
    }

    public static <T> RestResult<T> postObjectResult(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String response = postString(url, httpEntity, params);
        return RestResults.result(response, HttpMethod.POST, url, javaType);
    }

    public static <T> RestResult<T> postObjectResult(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String response = postString(url, httpEntity, params);
        return RestResults.result(response, HttpMethod.POST, url, clazz);
    }

    public static <T> ResponseEntity<T> formEntity(String url, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(), typeReference);
    }

    public static <T> ResponseEntity<T> formEntity(String url, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(), javaType);
    }

    public static <T> ResponseEntity<T> formEntity(String url, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(), clazz);
    }

    public static <T> ResponseEntity<T> formEntity(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(), params, typeReference);
    }

    public static <T> ResponseEntity<T> formEntity(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(), params, javaType);
    }

    public static <T> ResponseEntity<T> formEntity(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(), params, clazz);
    }

    public static <T> ResponseEntity<T> formEntity(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(body), typeReference);
    }

    public static <T> ResponseEntity<T> formEntity(String url, Object body, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(body), javaType);
    }

    public static <T> ResponseEntity<T> formEntity(String url, Object body, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(body), clazz);
    }

    public static <T> ResponseEntity<T> formEntity(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(body), params, typeReference);
    }

    public static <T> ResponseEntity<T> formEntity(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(body), params, javaType);
    }

    public static <T> ResponseEntity<T> formEntity(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(body), params, clazz);
    }

    public static <T> ResponseEntity<T> formEntity(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(httpHeaders), typeReference);
    }

    public static <T> ResponseEntity<T> formEntity(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(httpHeaders), javaType);
    }

    public static <T> ResponseEntity<T> formEntity(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(httpHeaders), clazz);
    }

    public static <T> ResponseEntity<T> formEntity(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(httpHeaders), params, typeReference);
    }

    public static <T> ResponseEntity<T> formEntity(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(httpHeaders), params, javaType);
    }

    public static <T> ResponseEntity<T> formEntity(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(httpHeaders), params, clazz);
    }

    public static <T> ResponseEntity<T> formEntity(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(body, httpHeaders), typeReference);
    }

    public static <T> ResponseEntity<T> formEntity(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(body, httpHeaders), javaType);
    }

    public static <T> ResponseEntity<T> formEntity(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(body, httpHeaders), clazz);
    }

    public static <T> ResponseEntity<T> formEntity(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(body, httpHeaders), params, typeReference);
    }

    public static <T> ResponseEntity<T> formEntity(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(body, httpHeaders), params, javaType);
    }

    public static <T> ResponseEntity<T> formEntity(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(body, httpHeaders), params, clazz);
    }


    public static <T> RestResult<T> formEntityResult(String url, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(), typeReference);
    }

    public static <T> RestResult<T> formEntityResult(String url, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(), javaType);
    }

    public static <T> RestResult<T> formEntityResult(String url, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(), clazz);
    }

    public static <T> RestResult<T> formEntityResult(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(), params, typeReference);
    }

    public static <T> RestResult<T> formEntityResult(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(), params, javaType);
    }

    public static <T> RestResult<T> formEntityResult(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(), params, clazz);
    }

    public static <T> RestResult<T> formEntityResult(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(body), typeReference);
    }

    public static <T> RestResult<T> formEntityResult(String url, Object body, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(body), javaType);
    }

    public static <T> RestResult<T> formEntityResult(String url, Object body, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(body), clazz);
    }

    public static <T> RestResult<T> formEntityResult(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(body), params, typeReference);
    }

    public static <T> RestResult<T> formEntityResult(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(body), params, javaType);
    }

    public static <T> RestResult<T> formEntityResult(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(body), params, clazz);
    }

    public static <T> RestResult<T> formEntityResult(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(httpHeaders), typeReference);
    }

    public static <T> RestResult<T> formEntityResult(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(httpHeaders), javaType);
    }

    public static <T> RestResult<T> formEntityResult(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(httpHeaders), clazz);
    }

    public static <T> RestResult<T> formEntityResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(httpHeaders), params, typeReference);
    }

    public static <T> RestResult<T> formEntityResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(httpHeaders), params, javaType);
    }

    public static <T> RestResult<T> formEntityResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(httpHeaders), params, clazz);
    }

    public static <T> RestResult<T> formEntityResult(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(body, httpHeaders), typeReference);
    }

    public static <T> RestResult<T> formEntityResult(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(body, httpHeaders), javaType);
    }

    public static <T> RestResult<T> formEntityResult(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(body, httpHeaders), clazz);
    }

    public static <T> RestResult<T> formEntityResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(body, httpHeaders), params, typeReference);
    }

    public static <T> RestResult<T> formEntityResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(body, httpHeaders), params, javaType);
    }

    public static <T> RestResult<T> formEntityResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(body, httpHeaders), params, clazz);
    }

    public static <T> ResponseEntity<T> postEntity(String url, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(), typeReference);
    }

    public static <T> ResponseEntity<T> postEntity(String url, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(), javaType);
    }

    public static <T> ResponseEntity<T> postEntity(String url, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(), clazz);
    }

    public static <T> ResponseEntity<T> postEntity(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(), params, typeReference);
    }

    public static <T> ResponseEntity<T> postEntity(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(), params, javaType);
    }

    public static <T> ResponseEntity<T> postEntity(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(), params, clazz);
    }

    public static <T> ResponseEntity<T> postEntity(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(body), typeReference);
    }

    public static <T> ResponseEntity<T> postEntity(String url, Object body, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(body), javaType);
    }

    public static <T> ResponseEntity<T> postEntity(String url, Object body, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(body), clazz);
    }

    public static <T> ResponseEntity<T> postEntity(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(body), params, typeReference);
    }

    public static <T> ResponseEntity<T> postEntity(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(body), params, javaType);
    }

    public static <T> ResponseEntity<T> postEntity(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(body), params, clazz);
    }

    public static <T> ResponseEntity<T> postEntity(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(httpHeaders), typeReference);
    }

    public static <T> ResponseEntity<T> postEntity(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(httpHeaders), javaType);
    }

    public static <T> ResponseEntity<T> postEntity(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(httpHeaders), clazz);
    }

    public static <T> ResponseEntity<T> postEntity(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(httpHeaders), params, typeReference);
    }

    public static <T> ResponseEntity<T> postEntity(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(httpHeaders), params, javaType);
    }

    public static <T> ResponseEntity<T> postEntity(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(httpHeaders), params, clazz);
    }

    public static <T> ResponseEntity<T> postEntity(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(body, httpHeaders), typeReference);
    }

    public static <T> ResponseEntity<T> postEntity(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(body, httpHeaders), javaType);
    }

    public static <T> ResponseEntity<T> postEntity(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(body, httpHeaders), clazz);
    }

    public static <T> ResponseEntity<T> postEntity(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(body, httpHeaders), params, typeReference);
    }

    public static <T> ResponseEntity<T> postEntity(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(body, httpHeaders), params, javaType);
    }

    public static <T> ResponseEntity<T> postEntity(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(body, httpHeaders), params, clazz);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> postEntity(String url, HttpEntity httpEntity, TypeReference<T> typeReference) throws RestException {
        return (ResponseEntity<T>) postEntityObject(url, httpEntity, TypeFactory.rawClass(TypeFactory.defaultInstance().constructType(typeReference)));
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> postEntity(String url, HttpEntity httpEntity, JavaType javaType) throws RestException {
        return (ResponseEntity<T>) postEntityObject(url, httpEntity, TypeFactory.rawClass(javaType));
    }

    public static <T> ResponseEntity<T> postEntity(String url, HttpEntity httpEntity, Class<T> clazz) throws RestException {
        return postEntityObject(url, httpEntity, clazz);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> postEntity(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return (ResponseEntity<T>) postEntityObject(url, httpEntity, params, TypeFactory.rawClass(TypeFactory.defaultInstance().constructType(typeReference)));
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> postEntity(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return (ResponseEntity<T>) postEntityObject(url, httpEntity, params, TypeFactory.rawClass(javaType));
    }

    public static <T> ResponseEntity<T> postEntity(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityObject(url, httpEntity, params, clazz);
    }

    public static <T> RestResult<T> postEntityResult(String url, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(), typeReference);
    }

    public static <T> RestResult<T> postEntityResult(String url, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(), javaType);
    }

    public static <T> RestResult<T> postEntityResult(String url, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(), clazz);
    }

    public static <T> RestResult<T> postEntityResult(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(), params, typeReference);
    }

    public static <T> RestResult<T> postEntityResult(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(), params, javaType);
    }

    public static <T> RestResult<T> postEntityResult(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(), params, clazz);
    }

    public static <T> RestResult<T> postEntityResult(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body), typeReference);
    }

    public static <T> RestResult<T> postEntityResult(String url, Object body, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body), javaType);
    }

    public static <T> RestResult<T> postEntityResult(String url, Object body, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body), clazz);
    }

    public static <T> RestResult<T> postEntityResult(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body), params, typeReference);
    }

    public static <T> RestResult<T> postEntityResult(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body), params, javaType);
    }

    public static <T> RestResult<T> postEntityResult(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body), params, clazz);
    }

    public static <T> RestResult<T> postEntityResult(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(httpHeaders), typeReference);
    }

    public static <T> RestResult<T> postEntityResult(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(httpHeaders), javaType);
    }

    public static <T> RestResult<T> postEntityResult(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(httpHeaders), clazz);
    }

    public static <T> RestResult<T> postEntityResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(httpHeaders), params, typeReference);
    }

    public static <T> RestResult<T> postEntityResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(httpHeaders), params, javaType);
    }

    public static <T> RestResult<T> postEntityResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(httpHeaders), params, clazz);
    }

    public static <T> RestResult<T> postEntityResult(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body, httpHeaders), typeReference);
    }

    public static <T> RestResult<T> postEntityResult(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body, httpHeaders), javaType);
    }

    public static <T> RestResult<T> postEntityResult(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body, httpHeaders), clazz);
    }

    public static <T> RestResult<T> postEntityResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body, httpHeaders), params, typeReference);
    }

    public static <T> RestResult<T> postEntityResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body, httpHeaders), params, javaType);
    }

    public static <T> RestResult<T> postEntityResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body, httpHeaders), params, clazz);
    }

    public static <T> RestResult<T> postEntityResult(String url, HttpEntity httpEntity, TypeReference<T> typeReference) throws RestException {
        ResponseEntity<RestResult> response = postEntityResult(url, httpEntity);
        return RestResults.result(response, HttpMethod.POST, url, typeReference);
    }

    public static <T> RestResult<T> postEntityResult(String url, HttpEntity httpEntity, JavaType javaType) throws RestException {
        ResponseEntity<RestResult> response = postEntityResult(url, httpEntity);
        return RestResults.result(response, HttpMethod.POST, url, javaType);
    }

    public static <T> RestResult<T> postEntityResult(String url, HttpEntity httpEntity, Class<T> clazz) throws RestException {
        ResponseEntity<RestResult> response = postEntityResult(url, httpEntity);
        return RestResults.result(response, HttpMethod.POST, url, clazz);
    }

    public static <T> RestResult<T> postEntityResult(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        ResponseEntity<RestResult> response = postEntityResult(url, httpEntity, params);
        return RestResults.result(response, HttpMethod.POST, url, typeReference);
    }

    public static <T> RestResult<T> postEntityResult(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        ResponseEntity<RestResult> response = postEntityResult(url, httpEntity, params);
        return RestResults.result(response, HttpMethod.POST, url, javaType);
    }

    public static <T> RestResult<T> postEntityResult(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        ResponseEntity<RestResult> response = postEntityResult(url, httpEntity, params);
        return RestResults.result(response, HttpMethod.POST, url, clazz);
    }


    private static String postString(String url, HttpEntity httpEntity) throws HttpErrorException {
        try {
            return INSTANCE.restTemplate.postForObject(url, httpEntity, String.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'postForObject' method has error: {}", exception.getMessage());
            throw new HttpErrorException("postForObject", exception.getMessage(), exception);
        }
    }

    private static String postString(String url, HttpEntity httpEntity, MultiValueMap<String, String> params) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.postForObject(builder.toUriString(), httpEntity, String.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'postForObject' method has error: {}", exception.getMessage());
            throw new HttpErrorException("postForObject", exception.getMessage(), exception);
        }
    }

    private static ResponseEntity<RestResult> postEntityResult(String url, HttpEntity httpEntity) throws HttpErrorException {
        try {
            return INSTANCE.restTemplate.postForEntity(url, httpEntity, RestResult.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'postForEntity' method has error: {}", exception.getMessage());
            throw new HttpErrorException("postForEntity", exception.getMessage(), exception);
        }
    }

    private static ResponseEntity<RestResult> postEntityResult(String url, HttpEntity httpEntity, MultiValueMap<String, String> params) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.postForEntity(builder.toUriString(), httpEntity, RestResult.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'postForEntity' method has error: {}", exception.getMessage());
            throw new HttpErrorException("postForEntity", exception.getMessage(), exception);
        }
    }

    private static <T> ResponseEntity<T> postEntityObject(String url, HttpEntity httpEntity, Class<T> clazz) throws HttpErrorException {
        try {
            return INSTANCE.restTemplate.postForEntity(url, httpEntity, clazz);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'postForEntity' method has error: {}", exception.getMessage());
            throw new HttpErrorException("postForEntity", exception.getMessage(), exception);
        }
    }

    private static <T> ResponseEntity<T> postEntityObject(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, Class<T> clazz) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.postForEntity(builder.toUriString(), httpEntity, clazz);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'postForEntity' method has error: {}", exception.getMessage());
            throw new HttpErrorException("postForEntity", exception.getMessage(), exception);
        }
    }

    public static <T> RestResult<T> getResult(String url, TypeReference<T> typeReference) throws RestException {
        String response = getString(url);
        return RestResults.result(response, HttpMethod.GET, url, typeReference);
    }

    public static <T> RestResult<T> getResult(String url, JavaType javaType) throws RestException {
        String response = getString(url);
        return RestResults.result(response, HttpMethod.GET, url, javaType);
    }

    public static <T> RestResult<T> getResult(String url, Class<T> clazz) throws RestException {
        String response = getString(url);
        return RestResults.result(response, HttpMethod.GET, url, clazz);
    }

    public static <T> RestResult<T> getResult(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String response = getString(url, params);
        return RestResults.result(response, HttpMethod.GET, url, typeReference);
    }

    public static <T> RestResult<T> getResult(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String response = getString(url, params);
        return RestResults.result(response, HttpMethod.GET, url, javaType);
    }

    public static <T> RestResult<T> getResult(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String response = getString(url, params);
        return RestResults.result(response, HttpMethod.GET, url, clazz);
    }

    public static <T> T getObject(String url, TypeReference<T> typeReference) throws RestException {
        String response = getString(url);
        return JsonUtils.parseBean(response, typeReference);
    }

    public static <T> T getObject(String url, JavaType javaType) throws RestException {
        String response = getString(url);
        return JsonUtils.parseBean(response, javaType);
    }

    public static <T> T getObject(String url, Class<T> clazz) throws RestException {
        String response = getString(url);
        return JsonUtils.parseBean(response, clazz);
    }

    public static <T> T getObject(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String response = getString(url, params);
        return JsonUtils.parseBean(response, typeReference);
    }

    public static <T> T getObject(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String response = getString(url, params);
        return JsonUtils.parseBean(response, javaType);
    }

    public static <T> T getObject(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String response = getString(url, params);
        return JsonUtils.parseBean(response, clazz);
    }

    public static <T> T getObjectResult(String url, TypeReference<T> typeReference) throws RestException {
        String response = getString(url);
        return RestResults.bean(response, HttpMethod.GET, url, typeReference);
    }

    public static <T> T getObjectResult(String url, JavaType javaType) throws RestException {
        String response = getString(url);
        return RestResults.bean(response, HttpMethod.GET, url, javaType);
    }

    public static <T> T getObjectResult(String url, Class<T> clazz) throws RestException {
        String response = getString(url);
        return RestResults.bean(response, HttpMethod.GET, url, clazz);
    }

    public static <T> T getObjectResult(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String response = getString(url, params);
        return RestResults.bean(response, HttpMethod.GET, url, params, typeReference);
    }

    public static <T> T getObjectResult(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String response = getString(url, params);
        return RestResults.bean(response, HttpMethod.GET, url, params, javaType);
    }

    public static <T> T getObjectResult(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String response = getString(url, params);
        return RestResults.bean(response, HttpMethod.GET, url, params, clazz);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> getEntity(String url, TypeReference<T> typeReference) throws RestException {
        return (ResponseEntity<T>) getEntityObject(url, TypeFactory.rawClass(TypeFactory.defaultInstance().constructType(typeReference)));
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> getEntity(String url, JavaType javaType) throws RestException {
        return (ResponseEntity<T>) getEntityObject(url, TypeFactory.rawClass(javaType));
    }

    public static <T> ResponseEntity<T> getEntity(String url, Class<T> clazz) throws RestException {
        return getEntityObject(url, clazz);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> getEntity(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return (ResponseEntity<T>) getEntityObject(url, params, TypeFactory.rawClass(TypeFactory.defaultInstance().constructType(typeReference)));
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> getEntity(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return (ResponseEntity<T>) getEntityObject(url, params, TypeFactory.rawClass(javaType));
    }

    public static <T> ResponseEntity<T> getEntity(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return getEntityObject(url, params, clazz);
    }

    public static <T> RestResult<T> getEntityResult(String url, TypeReference<T> typeReference) throws RestException {
        ResponseEntity<RestResult> response = getEntityResult(url);
        return RestResults.result(response, HttpMethod.GET, url, typeReference);
    }

    public static <T> RestResult<T> getEntityResult(String url, JavaType javaType) throws RestException {
        ResponseEntity<RestResult> response = getEntityResult(url);
        return RestResults.result(response, HttpMethod.GET, url, javaType);
    }

    public static <T> RestResult<T> getEntityResult(String url, Class<T> clazz) throws RestException {
        ResponseEntity<RestResult> response = getEntityResult(url);
        return RestResults.result(response, HttpMethod.GET, url, clazz);
    }

    public static <T> RestResult<T> getEntityResult(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        ResponseEntity<RestResult> response = getEntityResult(url, params);
        return RestResults.result(response, HttpMethod.GET, url, typeReference);
    }

    public static <T> RestResult<T> getEntityResult(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        ResponseEntity<RestResult> response = getEntityResult(url, params);
        return RestResults.result(response, HttpMethod.GET, url, javaType);
    }

    public static <T> RestResult<T> getEntityResult(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        ResponseEntity<RestResult> response = getEntityResult(url, params);
        return RestResults.result(response, HttpMethod.GET, url, clazz);
    }

    private static String getString(String url) throws HttpErrorException {
        try {
            return INSTANCE.restTemplate.getForObject(url, String.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'getForObject' method has error: {}", exception.getMessage());
            throw new HttpErrorException("getForObject", exception.getMessage(), exception);
        }
    }

    private static String getString(String url, MultiValueMap<String, String> params) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.getForObject(builder.toUriString(), String.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'getForObject' method has error: {}", exception.getMessage());
            throw new HttpErrorException("getForObject", exception.getMessage(), exception);
        }
    }

    private static <T> ResponseEntity<T> getEntityObject(String url, Class<T> clazz) throws HttpErrorException {
        try {
            return INSTANCE.restTemplate.getForEntity(url, clazz);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'getForEntity' method has error: {}", exception.getMessage());
            throw new HttpErrorException("getForEntity", exception.getMessage(), exception);
        }
    }

    private static <T> ResponseEntity<T> getEntityObject(String url, MultiValueMap<String, String> params, Class<T> clazz) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.getForEntity(builder.toUriString(), clazz);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'getForEntity' method has error: {}", exception.getMessage());
            throw new HttpErrorException("getForEntity", exception.getMessage(), exception);
        }
    }

    private static ResponseEntity<RestResult> getEntityResult(String url) throws HttpErrorException {
        try {
            return INSTANCE.restTemplate.getForEntity(url, RestResult.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'getForEntity' method has error: {}", exception.getMessage());
            throw new HttpErrorException("getForEntity", exception.getMessage(), exception);
        }
    }

    private static ResponseEntity<RestResult> getEntityResult(String url, MultiValueMap<String, String> params) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.getForEntity(builder.toUriString(), RestResult.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'getForEntity' method has error: {}", exception.getMessage());
            throw new HttpErrorException("getForEntity", exception.getMessage(), exception);
        }
    }


}
