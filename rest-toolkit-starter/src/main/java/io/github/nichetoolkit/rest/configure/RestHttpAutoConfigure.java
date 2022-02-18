package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.error.network.HttpConfigError;
import io.github.nichetoolkit.rest.http.HttpThreadFactory;
import io.github.nichetoolkit.rest.http.RestTemplates;
import io.github.nichetoolkit.rest.http.config.HttpClientType;
import io.github.nichetoolkit.rest.interceptor.RestHttpInterceptor;
import io.github.nichetoolkit.rest.util.ContextUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>RestTemplateConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Configuration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@ConditionalOnProperty(value = "nichetoolkit.rest.http.enabled", havingValue = "true")
public class RestHttpAutoConfigure {

    private RestInterceptProperties interceptProperties;
    private RestHttpInterceptor httpInterceptor;
    private RestHttpProperties httpProperties;

    @Autowired
    public RestHttpAutoConfigure(RestHttpProperties httpProperties,RestHttpInterceptor httpInterceptor,RestInterceptProperties interceptProperties) {
        log.debug("================= http-auto-config initiated ！ ===================");
        this.httpProperties = httpProperties;
        this.httpInterceptor = httpInterceptor;
        this.interceptProperties = interceptProperties;
    }

    @Bean
    @ConditionalOnMissingBean(RestTemplates.class)
    public RestTemplates restTemplates(RestTemplate restTemplate) {
        log.debug("http properties: {}", JsonUtils.parseJson(httpProperties));
        HttpClientType httpType = httpProperties.getHttpType();
        if (GeneralUtils.isNotEmpty(httpType)) {
            restTemplate = ContextUtils.getBean(httpType.getValue(), RestTemplate.class);
        }
        return new RestTemplates(restTemplate);
    }


    @Configuration
    @ConditionalOnProperty(value = "nichetoolkit.rest.http.http-type", havingValue = "default")
    public class DefaultRestTemplateAutoConfigure {

        public DefaultRestTemplateAutoConfigure() {
            log.debug("================= default-rest-template-auto-config initiated ！ ===================");
        }

        @Bean(name = HttpClientType.DEFAULT_BEAN)
        public RestTemplate restTemplate(SimpleClientHttpRequestFactory simpleClientHttpRequestFactory) {
            return createRestTemplate(simpleClientHttpRequestFactory);
        }

        @Bean(name = "simpleClientHttpRequestFactory")
        public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory() {
            SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            simpleClientHttpRequestFactory.setConnectTimeout(httpProperties.getConnectTimeout());
            /** 数据读取超时时间，即SocketTimeout */
            simpleClientHttpRequestFactory.setReadTimeout(httpProperties.getReadTimeout());
            /** 从连接池获取请求连接的超时时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的 */
            Proxy proxy = httpProperties.getProxy().toProxy();
            if (GeneralUtils.isNotEmpty(proxy)) {
                simpleClientHttpRequestFactory.setProxy(proxy);
            }
            ThreadPoolTaskScheduler taskExecutor = new ThreadPoolTaskScheduler();
            taskExecutor.setPoolSize(httpProperties.getMaxCoreSize());
            taskExecutor.setThreadFactory(new HttpThreadFactory("http-thread-pool"));
            simpleClientHttpRequestFactory.setTaskExecutor(taskExecutor);
            return simpleClientHttpRequestFactory;
        }
    }


    @Configuration
    @ConditionalOnProperty(value = "nichetoolkit.rest.http.http-type", havingValue = "ok_http_client")
    public class OkHttpRestTemplateAutoConfigure {
        public OkHttpRestTemplateAutoConfigure() {
            log.debug("================= okhttp3-rest-template-auto-config initiated ！ ===================");
        }

        @Bean(name = HttpClientType.OKHTTP3_BEAN)
        public RestTemplate okHttp3Template(OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory) {
            return createRestTemplate(okHttp3ClientHttpRequestFactory);
        }

        @Bean(name = "okHttp3ClientHttpRequestFactory")
        public OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory(OkHttpClient okHttpClient) {
            OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory = new OkHttp3ClientHttpRequestFactory(okHttpClient);
            okHttp3ClientHttpRequestFactory.setConnectTimeout(httpProperties.getConnectTimeout());
            /** 数据读取超时时间，即SocketTimeout */
            okHttp3ClientHttpRequestFactory.setReadTimeout(httpProperties.getReadTimeout());
            /** 从连接池获取请求连接的超时时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的 */
            okHttp3ClientHttpRequestFactory.setWriteTimeout(httpProperties.getRequestTimeout());
            return okHttp3ClientHttpRequestFactory;
        }

        @Bean(name = "okHttpClient")
        public OkHttpClient okHttpClient(X509TrustManager x509TrustManager) throws HttpConfigError {
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient().newBuilder();
            ConnectionPool connectionPool = new ConnectionPool(httpProperties.getMaxIdleSize(), httpProperties.getKeepAliveTime(), TimeUnit.MILLISECONDS);
            okHttpClientBuilder.connectionPool(connectionPool);
            try {
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1) -> true).build();
                sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
                okHttpClientBuilder.sslSocketFactory(sslContext.getSocketFactory(),x509TrustManager);
                okHttpClientBuilder.hostnameVerifier(NoopHostnameVerifier.INSTANCE);
                okHttpClientBuilder.retryOnConnectionFailure(true);
                okHttpClientBuilder.connectTimeout(httpProperties.getConnectTimeout(),TimeUnit.MILLISECONDS);
                okHttpClientBuilder.readTimeout(httpProperties.getReadTimeout(),TimeUnit.MILLISECONDS);
                okHttpClientBuilder.writeTimeout(httpProperties.getRequestTimeout(),TimeUnit.MILLISECONDS);
                ProxySelector proxySelector = ProxySelector.getDefault();
                okHttpClientBuilder.proxySelector(proxySelector);
                return okHttpClientBuilder.build();
            } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException exception) {
                log.error("the http connection pool initiated with error, error: {}", exception.getMessage());
                throw new HttpConfigError("the http connection pool initiated with error, error: " + exception.getMessage(), exception);
            }
        }
    }


    @Configuration
    @ConditionalOnProperty(value = "nichetoolkit.rest.http.http-type", havingValue = "http_client")
    public class HttpClientRestTemplateAutoConfigure {
        public HttpClientRestTemplateAutoConfigure() {
            log.debug("================= http-client-rest-template-auto-config initiated ！ ===================");
        }

        @Bean(name = HttpClientType.HTTPCLIENT_BEAN)
        public RestTemplate httpTemplate(HttpComponentsClientHttpRequestFactory HttpComponentsClientHttpRequestFactory) {
            return createRestTemplate(HttpComponentsClientHttpRequestFactory);
        }

        @Bean(name = "httpComponentsClientHttpRequestFactory")
        public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(HttpClient httpClient) {
            HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            httpComponentsClientHttpRequestFactory.setConnectTimeout(httpProperties.getConnectTimeout());
            /** 数据读取超时时间，即SocketTimeout */
            httpComponentsClientHttpRequestFactory.setReadTimeout(httpProperties.getReadTimeout());
            /** 从连接池获取请求连接的超时时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的 */
            httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(httpProperties.getRequestTimeout());
            return httpComponentsClientHttpRequestFactory;
        }

        @Bean(name = "httpClient")
        public HttpClient httpClient(X509TrustManager x509TrustManager) throws HttpConfigError {
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            Proxy proxy = httpProperties.getProxy().toProxy();
            if (GeneralUtils.isNotEmpty(proxy)) {
                InetSocketAddress address = (InetSocketAddress) proxy.address();
                HttpHost host = new HttpHost(address.getHostName(), address.getPort(), proxy.type().name().toLowerCase());
                httpClientBuilder.setProxy(host);
            }
            try {
                /** 设置信任ssl访问 */
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1) -> true).build();
                sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
                httpClientBuilder.setSSLContext(sslContext);

                HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
                SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
                Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                        /**  注册http和https请求 */
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", sslConnectionSocketFactory).build();

                /** 使用Httpclient连接池的方式配置(推荐)，同时支持netty，okHttp以及其他http框架 */
                PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
                /** 最大连接数 */
                poolingHttpClientConnectionManager.setMaxTotal(httpProperties.getMaxCoreSize());
                /** 同路由并发数 */
                poolingHttpClientConnectionManager.setDefaultMaxPerRoute(httpProperties.getMaxIdleSize());
                /** 配置连接池 */
                httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
                /** 重试次数 */
                httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(httpProperties.getRetryTimes(), true));
                /** 设置默认请求头 */
                httpClientBuilder.setDefaultHeaders(getDefaultHeaders());
                /** 设置长连接保持策略 */
                httpClientBuilder.setKeepAliveStrategy(connectionKeepAliveStrategy());
                return httpClientBuilder.build();
            } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException exception) {
                log.error("the http connection pool initiated with error, error: {}", exception.getMessage());
                throw new HttpConfigError("the http connection pool initiated with error, error: " + exception.getMessage(), exception);
            }
        }
    }

    @Bean
    @ConditionalOnMissingBean(X509TrustManager.class)
    public X509TrustManager x509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy(){
        return (response, context) -> {
            HeaderElementIterator iterator = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (iterator.hasNext()) {
                HeaderElement headerElement = iterator.nextElement();
                log.debug("HeaderElement: {}", JsonUtils.parseJson(headerElement));
                String param = headerElement.getName();
                String value = headerElement.getValue();
                if (value != null && "timeout".equalsIgnoreCase(param)) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch(NumberFormatException exception) {
                        log.error("Parsing long connection expiration time exception!", exception);
                    }
                }
            }
            HttpHost target = (HttpHost) context.getAttribute(HttpClientContext.HTTP_TARGET_HOST);
            /** 如果请求目标地址,单独配置了长连接保持时间,使用该配置 */
            Optional<Map.Entry<String, Integer>> any = Optional.ofNullable(httpProperties.getKeepAliveHosts()).orElseGet(HashMap::new)
                    .entrySet().stream().filter(entry -> entry.getKey().equalsIgnoreCase(target.getHostName())).findAny();
            /** 否则使用默认长连接保持时间 */
            return any.map(en -> en.getValue() * 1000L).orElse(httpProperties.getKeepAliveTime());
        };
    }


    private List<Header> getDefaultHeaders() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));
        return headers;
    }


    private RestTemplate createRestTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        if (interceptProperties.getEnabled()) {
            restTemplate.getInterceptors().add(httpInterceptor);
        }
        modifyDefaultCharset(restTemplate);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return restTemplate;
    }


    private void modifyDefaultCharset(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (StringHttpMessageConverter.class == item.getClass()) {
                converterTarget = item;
                break;
            }
        }
        if (null != converterTarget) {
            converterList.remove(converterTarget);
        }
        Charset defaultCharset = httpProperties.getCharset();
        converterList.add(1, new StringHttpMessageConverter(defaultCharset));
    }

}
