package io.github.nichetoolkit.rest.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.constant.RestConstants;
import io.github.nichetoolkit.rest.error.network.HttpErrorException;
import io.github.nichetoolkit.rest.error.network.HttpResultDataNullException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
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
 * <code>RestTemplates</code>
 * <p>The rest templates class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings({"SameNameButDifferent", "TypeParameterUnusedInFormals","rawtypes"})
public class RestTemplates {

    /**
     * <code>restTemplate</code>
     * {@link org.springframework.web.client.RestTemplate} <p>The <code>restTemplate</code> field.</p>
     * @see org.springframework.web.client.RestTemplate
     */
    private final RestTemplate restTemplate;

    /**
     * <code>INSTANCE</code>
     * {@link io.github.nichetoolkit.rest.http.RestTemplates} <p>The constant <code>INSTANCE</code> field.</p>
     */
    private static RestTemplates INSTANCE;

    /**
     * <code>RestTemplates</code>
     * <p>Instantiates a new rest templates.</p>
     * @param restTemplate {@link org.springframework.web.client.RestTemplate} <p>The rest template parameter is <code>RestTemplate</code> type.</p>
     * @see org.springframework.web.client.RestTemplate
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RestTemplates(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * <code>restTemplatesInit</code>
     * <p>The rest templates init method.</p>
     * @see javax.annotation.PostConstruct
     */
    @PostConstruct
    public void restTemplatesInit() {
        INSTANCE = this;
    }

    /**
     * <code>userAgent</code>
     * <p>The user agent method.</p>
     * @return {@link org.springframework.http.HttpHeaders} <p>The user agent return object is <code>HttpHeaders</code> type.</p>
     * @see org.springframework.http.HttpHeaders
     */
    public static HttpHeaders userAgent() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(RestConstants.USER_AGENT_HEADER, RestConstants.USER_AGENT_VALUE);
        return httpHeaders;
    }


    /**
     * <code>emptyMap</code>
     * <p>The empty map method.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link org.springframework.util.MultiValueMap} <p>The empty map return object is <code>MultiValueMap</code> type.</p>
     * @see org.springframework.util.MultiValueMap
     */
    public static <K, V> MultiValueMap<K, V> emptyMap() {
        return new LinkedMultiValueMap<>(0);
    }

    /**
     * <code>singletonMap</code>
     * <p>The singleton map method.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param key   K <p>The key parameter is <code>K</code> type.</p>
     * @param value V <p>The value parameter is <code>V</code> type.</p>
     * @return {@link org.springframework.util.MultiValueMap} <p>The singleton map return object is <code>MultiValueMap</code> type.</p>
     * @see org.springframework.util.MultiValueMap
     */
    public static <K, V> MultiValueMap<K, V> singletonMap(K key, V value) {
        LinkedMultiValueMap<K, V> singletonMap = new LinkedMultiValueMap<>(1);
        singletonMap.add(key, value);
        return singletonMap;
    }

    /**
     * <code>add</code>
     * <p>The add method.</p>
     * @param <K>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param paramsMap {@link java.util.Map} <p>The params map parameter is <code>Map</code> type.</p>
     * @return {@link org.springframework.util.MultiValueMap} <p>The add return object is <code>MultiValueMap</code> type.</p>
     * @see java.util.Map
     * @see org.springframework.util.MultiValueMap
     */
    public static <K, V> MultiValueMap<K, V> add(Map<K, V> paramsMap) {
        if (GeneralUtils.isEmpty(paramsMap)) {
            return emptyMap();
        }
        LinkedMultiValueMap<K, V> singletonMap = new LinkedMultiValueMap<>(paramsMap.size());
        paramsMap.forEach(singletonMap::add);
        return singletonMap;
    }

    /**
     * <code>put</code>
     * <p>The put method.</p>
     * @param <K>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param paramsMap {@link java.util.Map} <p>The params map parameter is <code>Map</code> type.</p>
     * @return {@link org.springframework.util.MultiValueMap} <p>The put return object is <code>MultiValueMap</code> type.</p>
     * @see java.util.Map
     * @see org.springframework.util.MultiValueMap
     */
    public static <K, V> MultiValueMap<K, V> put(Map<K, List<V>> paramsMap) {
        if (GeneralUtils.isEmpty(paramsMap)) {
            return emptyMap();
        }
        LinkedMultiValueMap<K, V> singletonMap = new LinkedMultiValueMap<>(paramsMap.size());
        singletonMap.putAll(paramsMap);
        return singletonMap;
    }

    /**
     * <code>singletonListMap</code>
     * <p>The singleton list map method.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param key   K <p>The key parameter is <code>K</code> type.</p>
     * @param value V <p>The value parameter is <code>V</code> type.</p>
     * @return {@link org.springframework.util.MultiValueMap} <p>The singleton list map return object is <code>MultiValueMap</code> type.</p>
     * @see org.springframework.util.MultiValueMap
     */
    public static <K, V> MultiValueMap<K, List<V>> singletonListMap(K key, V value) {
        LinkedMultiValueMap<K, List<V>> singletonMap = new LinkedMultiValueMap<>(1);
        singletonMap.add(key, Collections.singletonList(value));
        return singletonMap;
    }

    /**
     * <code>merge</code>
     * <p>The merge method.</p>
     * @param <K>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param paramsMap {@link org.springframework.util.MultiValueMap} <p>The params map parameter is <code>MultiValueMap</code> type.</p>
     * @param key       K <p>The key parameter is <code>K</code> type.</p>
     * @param value     V <p>The value parameter is <code>V</code> type.</p>
     * @return {@link org.springframework.util.MultiValueMap} <p>The merge return object is <code>MultiValueMap</code> type.</p>
     * @see org.springframework.util.MultiValueMap
     */
    public static <K, V> MultiValueMap<K, V> merge(MultiValueMap<K, V> paramsMap, K key, V value) {
        if (GeneralUtils.isEmpty(paramsMap)) {
            return emptyMap();
        }
        LinkedMultiValueMap<K, V> singletonMap = new LinkedMultiValueMap<>();
        singletonMap.putAll(paramsMap);
        if (GeneralUtils.isNotEmpty(value)) {
            singletonMap.put(key, Collections.singletonList(value));
        }
        return singletonMap;
    }

    /**
     * <code>mergeList</code>
     * <p>The merge list method.</p>
     * @param <K>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param paramsMap {@link org.springframework.util.MultiValueMap} <p>The params map parameter is <code>MultiValueMap</code> type.</p>
     * @param key       K <p>The key parameter is <code>K</code> type.</p>
     * @param value     V <p>The value parameter is <code>V</code> type.</p>
     * @return {@link org.springframework.util.MultiValueMap} <p>The merge list return object is <code>MultiValueMap</code> type.</p>
     * @see org.springframework.util.MultiValueMap
     */
    public static <K, V> MultiValueMap<K, List<V>> mergeList(MultiValueMap<K, V> paramsMap, K key, V value) {
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

    /**
     * <code>merge</code>
     * <p>The merge method.</p>
     * @param <K>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param firstMap  {@link org.springframework.util.MultiValueMap} <p>The first map parameter is <code>MultiValueMap</code> type.</p>
     * @param secondMap {@link org.springframework.util.MultiValueMap} <p>The second map parameter is <code>MultiValueMap</code> type.</p>
     * @return {@link org.springframework.util.MultiValueMap} <p>The merge return object is <code>MultiValueMap</code> type.</p>
     * @see org.springframework.util.MultiValueMap
     */
    public static <K, V> MultiValueMap<K, V> merge(MultiValueMap<K, V> firstMap, MultiValueMap<K, V> secondMap) {
        if (GeneralUtils.isEmpty(firstMap) && GeneralUtils.isEmpty(secondMap)) {
            return emptyMap();
        }
        LinkedMultiValueMap<K, V> singletonMap = new LinkedMultiValueMap<>();
        if (GeneralUtils.isNotEmpty(firstMap)) {
            singletonMap.putAll(firstMap);
        }
        if (GeneralUtils.isNotEmpty(secondMap)) {
            singletonMap.putAll(secondMap);
        }
        return singletonMap;
    }

    /**
     * <code>mergeList</code>
     * <p>The merge list method.</p>
     * @param <K>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param firstMap  {@link org.springframework.util.MultiValueMap} <p>The first map parameter is <code>MultiValueMap</code> type.</p>
     * @param secondMap {@link org.springframework.util.MultiValueMap} <p>The second map parameter is <code>MultiValueMap</code> type.</p>
     * @return {@link org.springframework.util.MultiValueMap} <p>The merge list return object is <code>MultiValueMap</code> type.</p>
     * @see org.springframework.util.MultiValueMap
     */
    public static <K, V> MultiValueMap<K, List<V>> mergeList(MultiValueMap<K, List<V>> firstMap, MultiValueMap<K, List<V>> secondMap) {
        if (GeneralUtils.isEmpty(firstMap) && GeneralUtils.isEmpty(secondMap)) {
            return emptyMap();
        }
        LinkedMultiValueMap<K, List<V>> singletonMap = new LinkedMultiValueMap<>();
        if (GeneralUtils.isNotEmpty(firstMap)) {
            singletonMap.putAll(firstMap);
        }
        if (GeneralUtils.isNotEmpty(secondMap)) {
            singletonMap.putAll(secondMap);
        }
        return singletonMap;
    }

    /**
     * <code>mergeAll</code>
     * <p>The merge all method.</p>
     * @param <K>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param firstMap  {@link org.springframework.util.MultiValueMap} <p>The first map parameter is <code>MultiValueMap</code> type.</p>
     * @param secondMap {@link org.springframework.util.MultiValueMap} <p>The second map parameter is <code>MultiValueMap</code> type.</p>
     * @return {@link org.springframework.util.MultiValueMap} <p>The merge all return object is <code>MultiValueMap</code> type.</p>
     * @see org.springframework.util.MultiValueMap
     */
    public static <K, V> MultiValueMap<K, List<V>> mergeAll(MultiValueMap<K, List<V>> firstMap, MultiValueMap<K, V> secondMap) {
        if (GeneralUtils.isEmpty(firstMap) && GeneralUtils.isEmpty(secondMap)) {
            return emptyMap();
        }
        LinkedMultiValueMap<K, List<V>> singletonMap = new LinkedMultiValueMap<>();
        if (GeneralUtils.isNotEmpty(firstMap)) {
            singletonMap.putAll(firstMap);
        }
        if (GeneralUtils.isNotEmpty(secondMap)) {
            secondMap.forEach(singletonMap::add);
        }
        return singletonMap;
    }

    /**
     * <code>httpEntity</code>
     * <p>The http entity method.</p>
     * @param mediaType {@link org.springframework.http.MediaType} <p>The media type parameter is <code>MediaType</code> type.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The http entity return object is <code>HttpEntity</code> type.</p>
     * @see org.springframework.http.MediaType
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity<?> httpEntity(MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return new HttpEntity<>(null, headers);
    }

    /**
     * <code>httpEntity</code>
     * <p>The http entity method.</p>
     * @param mediaType {@link org.springframework.http.MediaType} <p>The media type parameter is <code>MediaType</code> type.</p>
     * @param body      {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The http entity return object is <code>HttpEntity</code> type.</p>
     * @see org.springframework.http.MediaType
     * @see java.lang.Object
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity<?> httpEntity(MediaType mediaType, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return new HttpEntity<>(body, headers);
    }

    /**
     * <code>httpEntity</code>
     * <p>The http entity method.</p>
     * @param mediaType {@link org.springframework.http.MediaType} <p>The media type parameter is <code>MediaType</code> type.</p>
     * @param headers   {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The http entity return object is <code>HttpEntity</code> type.</p>
     * @see org.springframework.http.MediaType
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity<?> httpEntity(MediaType mediaType, HttpHeaders headers) {
        headers.setContentType(mediaType);
        return new HttpEntity<>(null, headers);
    }

    /**
     * <code>httpEntity</code>
     * <p>The http entity method.</p>
     * @param mediaType {@link org.springframework.http.MediaType} <p>The media type parameter is <code>MediaType</code> type.</p>
     * @param body      {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param headers   {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The http entity return object is <code>HttpEntity</code> type.</p>
     * @see org.springframework.http.MediaType
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity<?> httpEntity(MediaType mediaType, Object body, HttpHeaders headers) {
        headers.setContentType(mediaType);
        return new HttpEntity<>(body, headers);
    }

    /**
     * <code>formDataHttpEntity</code>
     * <p>The form data http entity method.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The form data http entity return object is <code>HttpEntity</code> type.</p>
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity<?> formDataHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return new HttpEntity<>(null, headers);
    }

    /**
     * <code>formDataHttpEntity</code>
     * <p>The form data http entity method.</p>
     * @param body {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The form data http entity return object is <code>HttpEntity</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity<?> formDataHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return new HttpEntity<>(body, headers);
    }

    /**
     * <code>formDataHttpEntity</code>
     * <p>The form data http entity method.</p>
     * @param headers {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The form data http entity return object is <code>HttpEntity</code> type.</p>
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity<?> formDataHttpEntity(HttpHeaders headers) {
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return new HttpEntity<>(null, headers);
    }

    /**
     * <code>formDataHttpEntity</code>
     * <p>The form data http entity method.</p>
     * @param body    {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param headers {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The form data http entity return object is <code>HttpEntity</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity<?> formDataHttpEntity(Object body, HttpHeaders headers) {
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return new HttpEntity<>(body, headers);
    }

    /**
     * <code>jsonHttpEntity</code>
     * <p>The json http entity method.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The json http entity return object is <code>HttpEntity</code> type.</p>
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity<?> jsonHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(null, headers);
    }

    /**
     * <code>jsonHttpEntity</code>
     * <p>The json http entity method.</p>
     * @param body {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The json http entity return object is <code>HttpEntity</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity jsonHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

    /**
     * <code>jsonHttpEntity</code>
     * <p>The json http entity method.</p>
     * @param headers {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The json http entity return object is <code>HttpEntity</code> type.</p>
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity jsonHttpEntity(HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(null, headers);
    }

    /**
     * <code>jsonHttpEntity</code>
     * <p>The json http entity method.</p>
     * @param body    {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param headers {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The json http entity return object is <code>HttpEntity</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity jsonHttpEntity(Object body, HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

    /**
     * <code>formHttpEntity</code>
     * <p>The form http entity method.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The form http entity return object is <code>HttpEntity</code> type.</p>
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity formHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(null, headers);
    }

    /**
     * <code>formHttpEntity</code>
     * <p>The form http entity method.</p>
     * @param body {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The form http entity return object is <code>HttpEntity</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity formHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(body, headers);
    }

    /**
     * <code>formHttpEntity</code>
     * <p>The form http entity method.</p>
     * @param headers {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The form http entity return object is <code>HttpEntity</code> type.</p>
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity formHttpEntity(HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(null, headers);
    }

    /**
     * <code>formHttpEntity</code>
     * <p>The form http entity method.</p>
     * @param body    {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param headers {@link org.springframework.http.HttpHeaders} <p>The headers parameter is <code>HttpHeaders</code> type.</p>
     * @return {@link org.springframework.http.HttpEntity} <p>The form http entity return object is <code>HttpEntity</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.http.HttpEntity
     */
    public static HttpEntity formHttpEntity(Object body, HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(body, headers);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(), typeReference);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(), javaType);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(), clazz);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(), params, typeReference);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(), params, javaType);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(), params, clazz);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(body), typeReference);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, Object body, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(body), javaType);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body  {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, Object body, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(body), clazz);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(body), params, typeReference);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(body), params, javaType);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body   {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(body), params, clazz);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(httpHeaders), typeReference);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(httpHeaders), javaType);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(httpHeaders), clazz);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(httpHeaders), params, typeReference);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(httpHeaders), params, javaType);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(httpHeaders), params, clazz);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(body, httpHeaders), typeReference);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(body, httpHeaders), javaType);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(body, httpHeaders), clazz);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, formHttpEntity(body, httpHeaders), params, typeReference);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, formHttpEntity(body, httpHeaders), params, javaType);
    }

    /**
     * <code>formObject</code>
     * <p>The form object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The form object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T formObject(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, formHttpEntity(body, httpHeaders), params, clazz);
    }


    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(), typeReference);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(), javaType);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(), clazz);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(), params, typeReference);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(), params, javaType);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(), params, clazz);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(body), typeReference);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, Object body, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(body), javaType);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body  {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, Object body, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(body), clazz);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(body), params, typeReference);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(body), params, javaType);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body   {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(body), params, clazz);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(httpHeaders), typeReference);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(httpHeaders), javaType);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(httpHeaders), clazz);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(httpHeaders), params, typeReference);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(httpHeaders), params, javaType);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(httpHeaders), params, clazz);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(body, httpHeaders), typeReference);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(body, httpHeaders), javaType);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(body, httpHeaders), clazz);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, formHttpEntity(body, httpHeaders), params, typeReference);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, formHttpEntity(body, httpHeaders), params, javaType);
    }

    /**
     * <code>formObjectResult</code>
     * <p>The form object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formObjectResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, formHttpEntity(body, httpHeaders), params, clazz);
    }


    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(), typeReference);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(), javaType);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(), clazz);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(), params, typeReference);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(), params, javaType);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(), params, clazz);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(body), typeReference);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, Object body, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(body), javaType);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body  {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, Object body, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(body), clazz);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(body), params, typeReference);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(body), params, javaType);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body   {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(body), params, clazz);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(httpHeaders), typeReference);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(httpHeaders), javaType);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(httpHeaders), clazz);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(httpHeaders), params, typeReference);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(httpHeaders), params, javaType);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(httpHeaders), params, clazz);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(body, httpHeaders), typeReference);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(body, httpHeaders), javaType);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(body, httpHeaders), clazz);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObject(url, jsonHttpEntity(body, httpHeaders), params, typeReference);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObject(url, jsonHttpEntity(body, httpHeaders), params, javaType);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObject(url, jsonHttpEntity(body, httpHeaders), params, clazz);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity    {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, HttpEntity httpEntity, TypeReference<T> typeReference) throws RestException {
        String response = postString(url, httpEntity);
        return JsonUtils.parseBean(response, typeReference);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param javaType   {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, HttpEntity httpEntity, JavaType javaType) throws RestException {
        String response = postString(url, httpEntity);
        return JsonUtils.parseBean(response, javaType);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, HttpEntity httpEntity, Class<T> clazz) throws RestException {
        String response = postString(url, httpEntity);
        return JsonUtils.parseBean(response, clazz);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity    {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String response = postString(url, httpEntity, params);
        return JsonUtils.parseBean(response, typeReference);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType   {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String response = postString(url, httpEntity, params);
        return JsonUtils.parseBean(response, javaType);
    }

    /**
     * <code>postObject</code>
     * <p>The post object method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The post object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T postObject(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String response = postString(url, httpEntity, params);
        return JsonUtils.parseBean(response, clazz);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(), typeReference);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(), javaType);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(), clazz);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(), params, typeReference);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(), params, javaType);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(), params, clazz);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body), typeReference);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, Object body, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body), javaType);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body  {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, Object body, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body), clazz);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body), params, typeReference);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body), params, javaType);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body   {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body), params, clazz);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(httpHeaders), typeReference);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(httpHeaders), javaType);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(httpHeaders), clazz);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(httpHeaders), params, typeReference);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(httpHeaders), params, javaType);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(httpHeaders), params, clazz);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body, httpHeaders), typeReference);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body, httpHeaders), javaType);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body, httpHeaders), clazz);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body, httpHeaders), params, typeReference);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body, httpHeaders), params, javaType);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postObjectResult(url, jsonHttpEntity(body, httpHeaders), params, clazz);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity    {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, HttpEntity httpEntity, TypeReference<T> typeReference) throws RestException {
        String response = postString(url, httpEntity);
        return RestResults.result(response, HttpMethod.POST, url, typeReference);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param javaType   {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, HttpEntity httpEntity, JavaType javaType) throws RestException {
        String response = postString(url, httpEntity);
        return RestResults.result(response, HttpMethod.POST, url, javaType);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, HttpEntity httpEntity, Class<T> clazz) throws RestException {
        String response = postString(url, httpEntity);
        return RestResults.result(response, HttpMethod.POST, url, clazz);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity    {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String response = postString(url, httpEntity, params);
        return RestResults.result(response, HttpMethod.POST, url, typeReference);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType   {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String response = postString(url, httpEntity, params);
        return RestResults.result(response, HttpMethod.POST, url, javaType);
    }

    /**
     * <code>postObjectResult</code>
     * <p>The post object result method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post object result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postObjectResult(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String response = postString(url, httpEntity, params);
        return RestResults.result(response, HttpMethod.POST, url, clazz);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(), typeReference);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(), javaType);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(), clazz);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(), params, typeReference);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(), params, javaType);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(), params, clazz);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(body), typeReference);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, Object body, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(body), javaType);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body  {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, Object body, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(body), clazz);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(body), params, typeReference);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(body), params, javaType);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body   {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(body), params, clazz);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(httpHeaders), typeReference);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(httpHeaders), javaType);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(httpHeaders), clazz);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(httpHeaders), params, typeReference);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(httpHeaders), params, javaType);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(httpHeaders), params, clazz);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(body, httpHeaders), typeReference);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(body, httpHeaders), javaType);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(body, httpHeaders), clazz);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, formHttpEntity(body, httpHeaders), params, typeReference);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, formHttpEntity(body, httpHeaders), params, javaType);
    }

    /**
     * <code>formEntity</code>
     * <p>The form entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The form entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> formEntity(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, formHttpEntity(body, httpHeaders), params, clazz);
    }


    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(), typeReference);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(), javaType);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(), clazz);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(), params, typeReference);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(), params, javaType);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(), params, clazz);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(body), typeReference);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, Object body, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(body), javaType);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body  {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, Object body, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(body), clazz);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(body), params, typeReference);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(body), params, javaType);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body   {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(body), params, clazz);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(httpHeaders), typeReference);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(httpHeaders), javaType);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(httpHeaders), clazz);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(httpHeaders), params, typeReference);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(httpHeaders), params, javaType);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(httpHeaders), params, clazz);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(body, httpHeaders), typeReference);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(body, httpHeaders), javaType);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(body, httpHeaders), clazz);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, formHttpEntity(body, httpHeaders), params, typeReference);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, formHttpEntity(body, httpHeaders), params, javaType);
    }

    /**
     * <code>formEntityResult</code>
     * <p>The form entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The form entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> formEntityResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, formHttpEntity(body, httpHeaders), params, clazz);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(), typeReference);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(), javaType);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(), clazz);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(), params, typeReference);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(), params, javaType);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(), params, clazz);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(body), typeReference);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, Object body, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(body), javaType);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body  {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, Object body, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(body), clazz);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(body), params, typeReference);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(body), params, javaType);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body   {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(body), params, clazz);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(httpHeaders), typeReference);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(httpHeaders), javaType);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(httpHeaders), clazz);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(httpHeaders), params, typeReference);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(httpHeaders), params, javaType);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(httpHeaders), params, clazz);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(body, httpHeaders), typeReference);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(body, httpHeaders), javaType);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(body, httpHeaders), clazz);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntity(url, jsonHttpEntity(body, httpHeaders), params, typeReference);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntity(url, jsonHttpEntity(body, httpHeaders), params, javaType);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntity(url, jsonHttpEntity(body, httpHeaders), params, clazz);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity    {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> postEntity(String url, HttpEntity httpEntity, TypeReference<T> typeReference) throws RestException {
        return (ResponseEntity<T>) postEntityObject(url, httpEntity, TypeFactory.rawClass(TypeFactory.defaultInstance().constructType(typeReference)));
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param javaType   {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> postEntity(String url, HttpEntity httpEntity, JavaType javaType) throws RestException {
        return (ResponseEntity<T>) postEntityObject(url, httpEntity, TypeFactory.rawClass(javaType));
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, HttpEntity<?> httpEntity, Class<T> clazz) throws RestException {
        return postEntityObject(url, httpEntity, clazz);
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity    {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> postEntity(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return (ResponseEntity<T>) postEntityObject(url, httpEntity, params, TypeFactory.rawClass(TypeFactory.defaultInstance().constructType(typeReference)));
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType   {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> postEntity(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return (ResponseEntity<T>) postEntityObject(url, httpEntity, params, TypeFactory.rawClass(javaType));
    }

    /**
     * <code>postEntity</code>
     * <p>The post entity method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> postEntity(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityObject(url, httpEntity, params, clazz);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(), typeReference);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(), javaType);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(), clazz);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(), params, typeReference);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(), params, javaType);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(), params, clazz);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, Object body, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body), typeReference);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, Object body, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body), javaType);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body  {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, Object body, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body), clazz);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, Object body, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body), params, typeReference);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body     {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, Object body, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body), params, javaType);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body   {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, Object body, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body), params, clazz);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(httpHeaders), typeReference);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(httpHeaders), javaType);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(httpHeaders), clazz);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(httpHeaders), params, typeReference);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(httpHeaders), params, javaType);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(httpHeaders), params, clazz);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, Object body, HttpHeaders httpHeaders, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body, httpHeaders), typeReference);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, Object body, HttpHeaders httpHeaders, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body, httpHeaders), javaType);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body, httpHeaders), clazz);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body          {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders   {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body, httpHeaders), params, typeReference);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType    {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body, httpHeaders), params, javaType);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url         {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param body        {@link java.lang.Object} <p>The body parameter is <code>Object</code> type.</p>
     * @param httpHeaders {@link org.springframework.http.HttpHeaders} <p>The http headers parameter is <code>HttpHeaders</code> type.</p>
     * @param params      {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see org.springframework.http.HttpHeaders
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, Object body, HttpHeaders httpHeaders, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return postEntityResult(url, jsonHttpEntity(body, httpHeaders), params, clazz);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity    {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, HttpEntity httpEntity, TypeReference<T> typeReference) throws RestException {
        ResponseEntity<RestResult> response = postEntityResult(url, httpEntity);
        return RestResults.result(response, HttpMethod.POST, url, typeReference);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param javaType   {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, HttpEntity httpEntity, JavaType javaType) throws RestException {
        ResponseEntity<RestResult> response = postEntityResult(url, httpEntity);
        return RestResults.result(response, HttpMethod.POST, url, javaType);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, HttpEntity httpEntity, Class<T> clazz) throws RestException {
        ResponseEntity<RestResult> response = postEntityResult(url, httpEntity);
        return RestResults.result(response, HttpMethod.POST, url, clazz);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity    {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        ResponseEntity<RestResult> response = postEntityResult(url, httpEntity, params);
        return RestResults.result(response, HttpMethod.POST, url, typeReference);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType   {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        ResponseEntity<RestResult> response = postEntityResult(url, httpEntity, params);
        return RestResults.result(response, HttpMethod.POST, url, javaType);
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The post entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> postEntityResult(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        ResponseEntity<RestResult> response = postEntityResult(url, httpEntity, params);
        return RestResults.result(response, HttpMethod.POST, url, clazz);
    }


    /**
     * <code>postString</code>
     * <p>The post string method.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @return {@link java.lang.String} <p>The post string return object is <code>String</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static String postString(String url, HttpEntity httpEntity) throws HttpErrorException {
        try {
            return INSTANCE.restTemplate.postForObject(url, httpEntity, String.class);
        } catch (RestClientException exception) {
            log.error("the request of restTemplate 'postForObject' for 'postString' method with url has error: {}", exception.getMessage());
            throw new HttpErrorException("postForObject", exception.getMessage(), exception);
        }
    }

    /**
     * <code>postString</code>
     * <p>The post string method.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return {@link java.lang.String} <p>The post string return object is <code>String</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static String postString(String url, HttpEntity httpEntity, MultiValueMap<String, String> params) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.postForObject(builder.toUriString(), httpEntity, String.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'postForObject' for 'postString' method with params has error: {}", exception.getMessage());
            throw new HttpErrorException("postForObject", exception.getMessage(), exception);
        }
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity result return object is <code>ResponseEntity</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static ResponseEntity<RestResult> postEntityResult(String url, HttpEntity httpEntity) throws HttpErrorException {
        try {
            return INSTANCE.restTemplate.postForEntity(url, httpEntity, RestResult.class);
        } catch (RestClientException exception) {
            log.error("the post entity result request with restTemplate 'postForEntity' for 'postEntityResult' method with url has error: {}", exception.getMessage());
            throw new HttpErrorException("postForEntity", exception.getMessage(), exception);
        }
    }

    /**
     * <code>postEntityResult</code>
     * <p>The post entity result method.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity result return object is <code>ResponseEntity</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static ResponseEntity<RestResult> postEntityResult(String url, HttpEntity httpEntity, MultiValueMap<String, String> params) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.postForEntity(builder.toUriString(), httpEntity, RestResult.class);
        } catch (RestClientException exception) {
            log.error("the request of restTemplate 'postForEntity' for 'postEntityResult' method with params has error: {}", exception.getMessage());
            throw new HttpErrorException("postForEntity", exception.getMessage(), exception);
        }
    }

    /**
     * <code>postEntityObject</code>
     * <p>The post entity object method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity object return object is <code>ResponseEntity</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static <T> ResponseEntity<T> postEntityObject(String url, HttpEntity httpEntity, Class<T> clazz) throws HttpErrorException {
        try {
            return INSTANCE.restTemplate.postForEntity(url, httpEntity, clazz);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'postForEntity' for 'postEntityObject' method with class type has error: {}", exception.getMessage());
            throw new HttpErrorException("postForEntity", exception.getMessage(), exception);
        }
    }

    /**
     * <code>postEntityObject</code>
     * <p>The post entity object method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The post entity object return object is <code>ResponseEntity</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static <T> ResponseEntity<T> postEntityObject(String url, HttpEntity httpEntity, MultiValueMap<String, String> params, Class<T> clazz) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.postForEntity(builder.toUriString(), httpEntity, clazz);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'postForEntity' for 'postEntityObject' method has error: {}", exception.getMessage());
            throw new HttpErrorException("postForEntity", exception.getMessage(), exception);
        }
    }

    /**
     * <code>getResult</code>
     * <p>The get result getter method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The get result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> getResult(String url, TypeReference<T> typeReference) throws RestException {
        String response = getString(url);
        return RestResults.result(response, HttpMethod.GET, url, typeReference);
    }

    /**
     * <code>getResult</code>
     * <p>The get result getter method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The get result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> getResult(String url, JavaType javaType) throws RestException {
        String response = getString(url);
        return RestResults.result(response, HttpMethod.GET, url, javaType);
    }

    /**
     * <code>getResult</code>
     * <p>The get result getter method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The get result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> getResult(String url, Class<T> clazz) throws RestException {
        String response = getString(url);
        return RestResults.result(response, HttpMethod.GET, url, clazz);
    }

    /**
     * <code>getResult</code>
     * <p>The get result getter method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The get result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> getResult(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String response = getString(url, params);
        return RestResults.result(response, HttpMethod.GET, url, typeReference);
    }

    /**
     * <code>getResult</code>
     * <p>The get result getter method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The get result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> getResult(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String response = getString(url, params);
        return RestResults.result(response, HttpMethod.GET, url, javaType);
    }

    /**
     * <code>getResult</code>
     * <p>The get result getter method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The get result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> getResult(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String response = getString(url, params);
        return RestResults.result(response, HttpMethod.GET, url, clazz);
    }

    /**
     * <code>getObject</code>
     * <p>The get object getter method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The get object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T getObject(String url, TypeReference<T> typeReference) throws RestException {
        String response = getString(url);
        return JsonUtils.parseBean(response, typeReference);
    }

    /**
     * <code>getObject</code>
     * <p>The get object getter method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The get object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T getObject(String url, JavaType javaType) throws RestException {
        String response = getString(url);
        return JsonUtils.parseBean(response, javaType);
    }

    /**
     * <code>getObject</code>
     * <p>The get object getter method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The get object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T getObject(String url, Class<T> clazz) throws RestException {
        String response = getString(url);
        return JsonUtils.parseBean(response, clazz);
    }

    /**
     * <code>getObject</code>
     * <p>The get object getter method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The get object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T getObject(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String response = getString(url, params);
        return JsonUtils.parseBean(response, typeReference);
    }

    /**
     * <code>getObject</code>
     * <p>The get object getter method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The get object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T getObject(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String response = getString(url, params);
        return JsonUtils.parseBean(response, javaType);
    }

    /**
     * <code>getObject</code>
     * <p>The get object getter method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The get object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T getObject(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String response = getString(url, params);
        return JsonUtils.parseBean(response, clazz);
    }

    /**
     * <code>getObjectResult</code>
     * <p>The get object result getter method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The get object result return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T getObjectResult(String url, TypeReference<T> typeReference) throws RestException {
        String response = getString(url);
        return RestResults.bean(response, HttpMethod.GET, url, typeReference);
    }

    /**
     * <code>getObjectResult</code>
     * <p>The get object result getter method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The get object result return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T getObjectResult(String url, JavaType javaType) throws RestException {
        String response = getString(url);
        return RestResults.bean(response, HttpMethod.GET, url, javaType);
    }

    /**
     * <code>getObjectResult</code>
     * <p>The get object result getter method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The get object result return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T getObjectResult(String url, Class<T> clazz) throws RestException {
        String response = getString(url);
        return RestResults.bean(response, HttpMethod.GET, url, clazz);
    }

    /**
     * <code>getObjectResult</code>
     * <p>The get object result getter method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The get object result return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T getObjectResult(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        String response = getString(url, params);
        return RestResults.bean(response, HttpMethod.GET, url, params, typeReference);
    }

    /**
     * <code>getObjectResult</code>
     * <p>The get object result getter method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The get object result return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T getObjectResult(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        String response = getString(url, params);
        return RestResults.bean(response, HttpMethod.GET, url, params, javaType);
    }

    /**
     * <code>getObjectResult</code>
     * <p>The get object result getter method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The get object result return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T getObjectResult(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        String response = getString(url, params);
        return RestResults.bean(response, HttpMethod.GET, url, params, clazz);
    }

    /**
     * <code>getEntity</code>
     * <p>The get entity getter method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The get entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> getEntity(String url, TypeReference<T> typeReference) throws RestException {
        return (ResponseEntity<T>) getEntityObject(url, TypeFactory.rawClass(TypeFactory.defaultInstance().constructType(typeReference)));
    }

    /**
     * <code>getEntity</code>
     * <p>The get entity getter method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The get entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> getEntity(String url, JavaType javaType) throws RestException {
        return (ResponseEntity<T>) getEntityObject(url, TypeFactory.rawClass(javaType));
    }

    /**
     * <code>getEntity</code>
     * <p>The get entity getter method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The get entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> getEntity(String url, Class<T> clazz) throws RestException {
        return getEntityObject(url, clazz);
    }

    /**
     * <code>getEntity</code>
     * <p>The get entity getter method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The get entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> getEntity(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return (ResponseEntity<T>) getEntityObject(url, params, TypeFactory.rawClass(TypeFactory.defaultInstance().constructType(typeReference)));
    }

    /**
     * <code>getEntity</code>
     * <p>The get entity getter method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The get entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> getEntity(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return (ResponseEntity<T>) getEntityObject(url, params, TypeFactory.rawClass(javaType));
    }

    /**
     * <code>getEntity</code>
     * <p>The get entity getter method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The get entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> getEntity(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return getEntityObject(url, params, clazz);
    }


    /**
     * <code>getEntityResult</code>
     * <p>The get entity result getter method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The get entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> getEntityResult(String url, TypeReference<T> typeReference) throws RestException {
        ResponseEntity<RestResult> response = getEntityResult(url);
        return RestResults.result(response, HttpMethod.GET, url, typeReference);
    }

    /**
     * <code>getEntityResult</code>
     * <p>The get entity result getter method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The get entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> getEntityResult(String url, JavaType javaType) throws RestException {
        ResponseEntity<RestResult> response = getEntityResult(url);
        return RestResults.result(response, HttpMethod.GET, url, javaType);
    }

    /**
     * <code>getEntityResult</code>
     * <p>The get entity result getter method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The get entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> getEntityResult(String url, Class<T> clazz) throws RestException {
        ResponseEntity<RestResult> response = getEntityResult(url);
        return RestResults.result(response, HttpMethod.GET, url, clazz);
    }

    /**
     * <code>getEntityResult</code>
     * <p>The get entity result getter method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The get entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> getEntityResult(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        ResponseEntity<RestResult> response = getEntityResult(url, params);
        return RestResults.result(response, HttpMethod.GET, url, typeReference);
    }

    /**
     * <code>getEntityResult</code>
     * <p>The get entity result getter method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url      {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params   {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The get entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> getEntityResult(String url, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        ResponseEntity<RestResult> response = getEntityResult(url, params);
        return RestResults.result(response, HttpMethod.GET, url, javaType);
    }

    /**
     * <code>getEntityResult</code>
     * <p>The get entity result getter method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The get entity result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> getEntityResult(String url, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        ResponseEntity<RestResult> response = getEntityResult(url, params);
        return RestResults.result(response, HttpMethod.GET, url, clazz);
    }

    /**
     * <code>getString</code>
     * <p>The get string getter method.</p>
     * @param url {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The get string return object is <code>String</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static String getString(String url) throws HttpErrorException {
        try {
            return INSTANCE.restTemplate.getForObject(url, String.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'getForObject' for 'getString' method with url has error: {}", exception.getMessage());
            throw new HttpErrorException("getForObject", exception.getMessage(), exception);
        }
    }

    /**
     * <code>getString</code>
     * <p>The get string getter method.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return {@link java.lang.String} <p>The get string return object is <code>String</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static String getString(String url, MultiValueMap<String, String> params) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.getForObject(builder.toUriString(), String.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'getForObject' for 'getString' method has error: {}", exception.getMessage());
            throw new HttpErrorException("getForObject", exception.getMessage(), exception);
        }
    }


    /**
     * <code>getEntityObject</code>
     * <p>The get entity object getter method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url   {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The get entity object return object is <code>ResponseEntity</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static <T> ResponseEntity<T> getEntityObject(String url, Class<T> clazz) throws HttpErrorException {
        try {
            return INSTANCE.restTemplate.getForEntity(url, clazz);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'getForEntity' for 'getEntityObject' method has error: {}", exception.getMessage());
            throw new HttpErrorException("getForEntity", exception.getMessage(), exception);
        }
    }

    /**
     * <code>getEntityObject</code>
     * <p>The get entity object getter method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The get entity object return object is <code>ResponseEntity</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static <T> ResponseEntity<T> getEntityObject(String url, MultiValueMap<String, String> params, Class<T> clazz) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.getForEntity(builder.toUriString(), clazz);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'getForEntity' method has error: {}", exception.getMessage());
            throw new HttpErrorException("getForEntity", exception.getMessage(), exception);
        }
    }

    /**
     * <code>getEntityResult</code>
     * <p>The get entity result getter method.</p>
     * @param url {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The get entity result return object is <code>ResponseEntity</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static ResponseEntity<RestResult> getEntityResult(String url) throws HttpErrorException {
        try {
            return INSTANCE.restTemplate.getForEntity(url, RestResult.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'getForEntity' for 'getEntityResult' method with params has error: {}", exception.getMessage());
            throw new HttpErrorException("getEntityResult", exception.getMessage(), exception);
        }
    }

    /**
     * <code>getEntityResult</code>
     * <p>The get entity result getter method.</p>
     * @param url    {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param params {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The get entity result return object is <code>ResponseEntity</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.util.MultiValueMap
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static ResponseEntity<RestResult> getEntityResult(String url, MultiValueMap<String, String> params) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.getForEntity(builder.toUriString(), RestResult.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'getForEntity' for 'getEntityResult' method with url and params has error: {}", exception.getMessage());
            throw new HttpErrorException("getEntityResult", exception.getMessage(), exception);
        }
    }

    /**
     * <code>exchangeObject</code>
     * <p>The exchange object method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpMethod    {@link org.springframework.http.HttpMethod} <p>The http method parameter is <code>HttpMethod</code> type.</p>
     * @param httpEntity    {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The exchange object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> T exchangeObject(String url, HttpMethod httpMethod, HttpEntity<?> httpEntity, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        ResponseEntity<T> response = (ResponseEntity<T>) exchangeEntityObject(url, httpMethod, httpEntity, params, TypeFactory.rawClass(TypeFactory.defaultInstance().constructType(typeReference)));
        OptionalUtils.ofFalse(GeneralUtils.isNotEmpty(response) && GeneralUtils.isNotEmpty(response.getBody()),"the response entity body is null! ",log, HttpResultDataNullException::new);
        return response.getBody();
    }

    /**
     * <code>exchangeObject</code>
     * <p>The exchange object method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpMethod {@link org.springframework.http.HttpMethod} <p>The http method parameter is <code>HttpMethod</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType   {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The exchange object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> T exchangeObject(String url, HttpMethod httpMethod, HttpEntity httpEntity, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        ResponseEntity<T> response = (ResponseEntity<T>) exchangeEntityObject(url, httpMethod, httpEntity, params, TypeFactory.rawClass(javaType));
        OptionalUtils.ofFalse(GeneralUtils.isNotEmpty(response) && GeneralUtils.isNotEmpty(response.getBody()),"the response entity body is null! ", log,HttpResultDataNullException::new);
        return response.getBody();
    }

    /**
     * <code>exchangeObject</code>
     * <p>The exchange object method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpMethod {@link org.springframework.http.HttpMethod} <p>The http method parameter is <code>HttpMethod</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The exchange object return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> T exchangeObject(String url, HttpMethod httpMethod, HttpEntity httpEntity, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        ResponseEntity<T> response = exchangeEntityObject(url, httpMethod, httpEntity, params, clazz);
        OptionalUtils.ofFalse(GeneralUtils.isNotEmpty(response) && GeneralUtils.isNotEmpty(response.getBody()),"the response entity body is null! ", log,HttpResultDataNullException::new);
        return response.getBody();
    }

    /**
     * <code>exchangeString</code>
     * <p>The exchange string method.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpMethod {@link org.springframework.http.HttpMethod} <p>The http method parameter is <code>HttpMethod</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return {@link java.lang.String} <p>The exchange string return object is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static String exchangeString(String url, HttpMethod httpMethod, HttpEntity httpEntity, MultiValueMap<String, String> params) throws RestException {
        ResponseEntity<String> response = exchangeEntityString(url, httpMethod,httpEntity,params);
        OptionalUtils.ofFalse(GeneralUtils.isNotEmpty(response) && GeneralUtils.isNotEmpty(response.getBody()),"the response entity body is null! ",log, HttpResultDataNullException::new);
        return response.getBody();
    }

    /**
     * <code>exchangeEntity</code>
     * <p>The exchange entity method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpMethod    {@link org.springframework.http.HttpMethod} <p>The http method parameter is <code>HttpMethod</code> type.</p>
     * @param httpEntity    {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The exchange entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> exchangeEntity(String url, HttpMethod httpMethod, HttpEntity httpEntity, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        return (ResponseEntity<T>) exchangeEntityObject(url, httpMethod, httpEntity, params, TypeFactory.rawClass(TypeFactory.defaultInstance().constructType(typeReference)));
    }

    /**
     * <code>exchangeEntity</code>
     * <p>The exchange entity method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpMethod {@link org.springframework.http.HttpMethod} <p>The http method parameter is <code>HttpMethod</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType   {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The exchange entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see org.springframework.http.ResponseEntity
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> ResponseEntity<T> exchangeEntity(String url, HttpMethod httpMethod, HttpEntity httpEntity, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        return (ResponseEntity<T>) exchangeEntityObject(url, httpMethod, httpEntity, params, TypeFactory.rawClass(javaType));
    }

    /**
     * <code>exchangeEntity</code>
     * <p>The exchange entity method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpMethod {@link org.springframework.http.HttpMethod} <p>The http method parameter is <code>HttpMethod</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The exchange entity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> ResponseEntity<T> exchangeEntity(String url, HttpMethod httpMethod, HttpEntity httpEntity, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        return exchangeEntityObject(url, httpMethod, httpEntity, params, clazz);
    }

    /**
     * <code>exchangeResult</code>
     * <p>The exchange result method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url           {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpMethod    {@link org.springframework.http.HttpMethod} <p>The http method parameter is <code>HttpMethod</code> type.</p>
     * @param httpEntity    {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params        {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The exchange result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> exchangeResult(String url, HttpMethod httpMethod, HttpEntity httpEntity, MultiValueMap<String, String> params, TypeReference<T> typeReference) throws RestException {
        ResponseEntity<RestResult> response = exchangeEntityResult(url, httpMethod, httpEntity,params);
        return RestResults.result(response, httpMethod, url, typeReference);
    }

    /**
     * <code>exchangeResult</code>
     * <p>The exchange result method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpMethod {@link org.springframework.http.HttpMethod} <p>The http method parameter is <code>HttpMethod</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param javaType   {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The exchange result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> exchangeResult(String url, HttpMethod httpMethod, HttpEntity httpEntity, MultiValueMap<String, String> params, JavaType javaType) throws RestException {
        ResponseEntity<RestResult> response = exchangeEntityResult(url, httpMethod,httpEntity,params);
        return RestResults.result(response, httpMethod, url, javaType);
    }

    /**
     * <code>exchangeResult</code>
     * <p>The exchange result method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpMethod {@link org.springframework.http.HttpMethod} <p>The http method parameter is <code>HttpMethod</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The exchange result return object is <code>RestResult</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestResult<T> exchangeResult(String url, HttpMethod httpMethod, HttpEntity httpEntity, MultiValueMap<String, String> params, Class<T> clazz) throws RestException {
        ResponseEntity<RestResult> response = exchangeEntityResult(url, httpMethod,httpEntity,params);
        return RestResults.result(response, httpMethod, url, clazz);
    }

    /**
     * <code>exchangeEntityString</code>
     * <p>The exchange entity string method.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpMethod {@link org.springframework.http.HttpMethod} <p>The http method parameter is <code>HttpMethod</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The exchange entity string return object is <code>ResponseEntity</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static ResponseEntity<String> exchangeEntityString(String url, HttpMethod httpMethod, HttpEntity httpEntity, MultiValueMap<String, String> params) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.exchange(builder.toUriString(), httpMethod, httpEntity, String.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'exchange' for 'exchangeEntityString' method with params has error: {}", exception.getMessage());
            throw new HttpErrorException("exchangeEntityString", exception.getMessage(), exception);
        }
    }

    /**
     * <code>exchangeEntityResult</code>
     * <p>The exchange entity result method.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpMethod {@link org.springframework.http.HttpMethod} <p>The http method parameter is <code>HttpMethod</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The exchange entity result return object is <code>ResponseEntity</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static ResponseEntity<RestResult> exchangeEntityResult(String url, HttpMethod httpMethod, HttpEntity httpEntity, MultiValueMap<String, String> params) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.exchange(builder.toUriString(), httpMethod, httpEntity, RestResult.class);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'exchange' for 'exchangeEntityObject' method with params has error: {}", exception.getMessage());
            throw new HttpErrorException("exchangeEntityResult", exception.getMessage(), exception);
        }
    }

    /**
     * <code>exchangeEntityObject</code>
     * <p>The exchange entity object method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param url        {@link java.lang.String} <p>The url parameter is <code>String</code> type.</p>
     * @param httpMethod {@link org.springframework.http.HttpMethod} <p>The http method parameter is <code>HttpMethod</code> type.</p>
     * @param httpEntity {@link org.springframework.http.HttpEntity} <p>The http entity parameter is <code>HttpEntity</code> type.</p>
     * @param params     {@link org.springframework.util.MultiValueMap} <p>The params parameter is <code>MultiValueMap</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The exchange entity object return object is <code>ResponseEntity</code> type.</p>
     * @throws HttpErrorException {@link io.github.nichetoolkit.rest.error.network.HttpErrorException} <p>The http error exception is <code>HttpErrorException</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.HttpMethod
     * @see org.springframework.http.HttpEntity
     * @see org.springframework.util.MultiValueMap
     * @see java.lang.Class
     * @see org.springframework.http.ResponseEntity
     * @see io.github.nichetoolkit.rest.error.network.HttpErrorException
     */
    private static <T> ResponseEntity<T> exchangeEntityObject(String url, HttpMethod httpMethod, HttpEntity httpEntity, MultiValueMap<String, String> params, Class<T> clazz) throws HttpErrorException {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
            return INSTANCE.restTemplate.exchange(builder.toUriString(), httpMethod, httpEntity, clazz);
        } catch (RestClientException exception) {
            log.error("the request with restTemplate 'exchange' for 'exchangeEntityObject' method with class type has error: {}", exception.getMessage());
            throw new HttpErrorException("exchangeEntityObject", exception.getMessage(), exception);
        }
    }

}
