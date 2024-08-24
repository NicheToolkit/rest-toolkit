package io.github.nichetoolkit.rest.interceptor;

import com.google.common.io.ByteStreams;
import io.github.nichetoolkit.rest.configure.RestInterceptProperties;
import io.github.nichetoolkit.rest.util.CommonUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * <p>HttpClientInterceptor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
@SuppressWarnings("SameNameButDifferent")
public class RestHttpInterceptor implements ClientHttpRequestInterceptor {
    @Autowired
    private RestInterceptProperties interceptProperties;

    @Override
    @NonNull
    public ClientHttpResponse intercept(@NonNull HttpRequest httpRequest, @NonNull byte[] bytes, @NonNull ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(httpRequest, bytes);
        String url = httpRequest.getURI().getPath();
        log.info(" HttpRequest -Url:     {}",url);
        HttpMethod method = httpRequest.getMethod();
        log.info(" HttpRequest -Method:  {}", JsonUtils.parseJson(method));
        String query = httpRequest.getURI().getQuery();
        UriComponents uriComponents = UriComponentsBuilder.newInstance().query(query).build();
        MultiValueMap<String, String> queryParams = uriComponents.getQueryParams();
        Map<String, String> params = queryParams.toSingleValueMap();
        log.info(" HttpRequest -Params:  {}", JsonUtils.parseJson(params));
        HttpHeaders requestHeaders = httpRequest.getHeaders();
        log.info(" HttpRequest -Headers: {}",JsonUtils.parseJson(requestHeaders));
        HttpStatus statusCode = response.getStatusCode();
        log.info(" HttpResponse -HttpStatus: {}",JsonUtils.parseJson(statusCode));
        HttpHeaders responseHeaders = response.getHeaders();
        log.info(" HttpResponse -Headers: {}",JsonUtils.parseJson(responseHeaders));
        byte[] body = bytes;
        if (GeneralUtils.isEmpty(bytes)) {
            body = ByteStreams.toByteArray(response.getBody());
        }
        String content = new String(body, StandardCharsets.UTF_8);
        if (GeneralUtils.isNotEmpty(content)) {
            Integer bodyLength = interceptProperties.getBodyLength();
            String bodyString;
            if (GeneralUtils.isNotEmpty(bodyLength)) {
                bodyString = CommonUtils.substring(content, bodyLength);
            } else {
                bodyString = content;
            }
            log.info(" HttpResponse -Body:    {}", bodyString);
        }
        return response;
    }
}
