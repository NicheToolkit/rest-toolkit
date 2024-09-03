package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.error.network.HttpConfigError;
import io.github.nichetoolkit.rest.http.HttpThreadFactory;
import io.github.nichetoolkit.rest.http.RestTemplates;
import io.github.nichetoolkit.rest.http.config.HttpClientType;
import io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor;
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
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.net.ssl.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <code>RestHttpAutoConfigure</code>
 * <p>The type rest http auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see java.lang.SuppressWarnings
 * @see org.springframework.context.annotation.ComponentScan
 * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@ConditionalOnProperty(value = "nichetoolkit.rest.http.enabled", havingValue = "true", matchIfMissing = true)
public class RestHttpAutoConfigure {

    /**
     * <code>interceptProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestInterceptProperties} <p>the <code>interceptProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestInterceptProperties
     */
    private final RestInterceptProperties interceptProperties;
    /**
     * <code>httpInterceptor</code>
     * {@link io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor} <p>the <code>httpInterceptor</code> field.</p>
     * @see io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor
     */
    private final DefaultClientHttpInterceptor httpInterceptor;
    /**
     * <code>httpProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestHttpProperties} <p>the <code>httpProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestHttpProperties
     */
    private final RestHttpProperties httpProperties;

    /**
     * <code>RestHttpAutoConfigure</code>
     * Instantiates a new rest http auto configure.
     * @param httpProperties      {@link io.github.nichetoolkit.rest.configure.RestHttpProperties} <p>the http properties parameter is <code>RestHttpProperties</code> type.</p>
     * @param httpInterceptor     {@link io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor} <p>the http interceptor parameter is <code>DefaultClientHttpInterceptor</code> type.</p>
     * @param interceptProperties {@link io.github.nichetoolkit.rest.configure.RestInterceptProperties} <p>the intercept properties parameter is <code>RestInterceptProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestHttpProperties
     * @see io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor
     * @see io.github.nichetoolkit.rest.configure.RestInterceptProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RestHttpAutoConfigure(RestHttpProperties httpProperties, DefaultClientHttpInterceptor httpInterceptor, RestInterceptProperties interceptProperties) {
        log.debug("================= http-auto-config initiated ！ ===================");
        this.httpProperties = httpProperties;
        this.httpInterceptor = httpInterceptor;
        this.interceptProperties = interceptProperties;
    }

    /**
     * <code>restTemplates</code>
     * <p>the templates method.</p>
     * @param restTemplate {@link org.springframework.web.client.RestTemplate} <p>the rest template parameter is <code>RestTemplate</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.http.RestTemplates} <p>the templates return object is <code>RestTemplates</code> type.</p>
     * @see org.springframework.web.client.RestTemplate
     * @see io.github.nichetoolkit.rest.http.RestTemplates
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
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

    /**
     * <code>DefaultRestTemplateAutoConfigure</code>
     * <p>The type default rest template auto configure class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see org.springframework.context.annotation.Configuration
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     * @since Jdk1.8
     */
    @Configuration
    @ConditionalOnProperty(value = "nichetoolkit.rest.http.http-type", havingValue = "default", matchIfMissing = true)
    public class DefaultRestTemplateAutoConfigure {
        /**
         * <code>DefaultRestTemplateAutoConfigure</code>
         * Instantiates a new default rest template auto configure.
         */
        public DefaultRestTemplateAutoConfigure() {
            log.debug("================= default-rest-template-auto-config initiated ！ ===================");
        }

        /**
         * <code>restTemplate</code>
         * <p>the template method.</p>
         * @param simpleClientHttpRequestFactory {@link org.springframework.http.client.SimpleClientHttpRequestFactory} <p>the simple client http request factory parameter is <code>SimpleClientHttpRequestFactory</code> type.</p>
         * @return {@link org.springframework.web.client.RestTemplate} <p>the template return object is <code>RestTemplate</code> type.</p>
         * @see org.springframework.http.client.SimpleClientHttpRequestFactory
         * @see org.springframework.web.client.RestTemplate
         * @see org.springframework.context.annotation.Bean
         */
        @Bean(name = HttpClientType.DEFAULT_BEAN)
        public RestTemplate restTemplate(SimpleClientHttpRequestFactory simpleClientHttpRequestFactory) {
            return createRestTemplate(simpleClientHttpRequestFactory);
        }

        /**
         * <code>simpleClientHttpRequestFactory</code>
         * <p>the client http request factory method.</p>
         * @return {@link org.springframework.http.client.SimpleClientHttpRequestFactory} <p>the client http request factory return object is <code>SimpleClientHttpRequestFactory</code> type.</p>
         * @see org.springframework.http.client.SimpleClientHttpRequestFactory
         * @see org.springframework.context.annotation.Bean
         */
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


    /**
     * <code>OkHttpRestTemplateAutoConfigure</code>
     * <p>The type ok http rest template auto configure class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see org.springframework.context.annotation.Configuration
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnClass
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     * @since Jdk1.8
     */
    @Configuration
    @ConditionalOnClass({RestTemplate.class,OkHttpClient.class})
    @ConditionalOnProperty(value = "nichetoolkit.rest.http.http-type", havingValue = "ok_http_client", matchIfMissing = true)
    public class OkHttpRestTemplateAutoConfigure {
        /**
         * <code>OkHttpRestTemplateAutoConfigure</code>
         * Instantiates a new ok http rest template auto configure.
         */
        public OkHttpRestTemplateAutoConfigure() {
            log.debug("================= okhttp3-rest-template-auto-config initiated ！ ===================");
        }

        /**
         * <code>okHttpTemplate</code>
         * <p>the http template method.</p>
         * @param okHttp3ClientHttpRequestFactory {@link org.springframework.http.client.OkHttp3ClientHttpRequestFactory} <p>the ok http 3 client http request factory parameter is <code>OkHttp3ClientHttpRequestFactory</code> type.</p>
         * @return {@link org.springframework.web.client.RestTemplate} <p>the http template return object is <code>RestTemplate</code> type.</p>
         * @see org.springframework.http.client.OkHttp3ClientHttpRequestFactory
         * @see org.springframework.web.client.RestTemplate
         * @see org.springframework.context.annotation.Bean
         */
        @Bean(name = HttpClientType.OKHTTP3_BEAN)
        public RestTemplate okHttpTemplate(OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory) {
            return createRestTemplate(okHttp3ClientHttpRequestFactory);
        }

        /**
         * <code>okHttp3ClientHttpRequestFactory</code>
         * <p>the http 3 client http request factory method.</p>
         * @param okHttpClient {@link okhttp3.OkHttpClient} <p>the ok http client parameter is <code>OkHttpClient</code> type.</p>
         * @return {@link org.springframework.http.client.OkHttp3ClientHttpRequestFactory} <p>the http 3 client http request factory return object is <code>OkHttp3ClientHttpRequestFactory</code> type.</p>
         * @see okhttp3.OkHttpClient
         * @see org.springframework.http.client.OkHttp3ClientHttpRequestFactory
         * @see org.springframework.context.annotation.Bean
         */
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

        /**
         * <code>okHttpClient</code>
         * <p>the http client method.</p>
         * @param x509TrustManager {@link javax.net.ssl.X509TrustManager} <p>the x 509 trust manager parameter is <code>X509TrustManager</code> type.</p>
         * @return {@link okhttp3.OkHttpClient} <p>the http client return object is <code>OkHttpClient</code> type.</p>
         * @throws HttpConfigError {@link io.github.nichetoolkit.rest.error.network.HttpConfigError} <p>the http config error is <code>HttpConfigError</code> type.</p>
         * @see javax.net.ssl.X509TrustManager
         * @see okhttp3.OkHttpClient
         * @see org.springframework.context.annotation.Bean
         * @see io.github.nichetoolkit.rest.error.network.HttpConfigError
         */
        @Bean(name = "okHttpClient")
        public OkHttpClient okHttpClient(X509TrustManager x509TrustManager) throws HttpConfigError {
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient().newBuilder();
            ConnectionPool connectionPool = new ConnectionPool(httpProperties.getMaxIdleSize(), httpProperties.getKeepAliveTime(), TimeUnit.MILLISECONDS);
            okHttpClientBuilder.connectionPool(connectionPool);
            try {
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
                okHttpClientBuilder.sslSocketFactory(sslContext.getSocketFactory(),x509TrustManager);
                okHttpClientBuilder.hostnameVerifier((hostname, session) -> true);
                okHttpClientBuilder.retryOnConnectionFailure(true);
                okHttpClientBuilder.connectTimeout(httpProperties.getConnectTimeout(),TimeUnit.MILLISECONDS);
                okHttpClientBuilder.readTimeout(httpProperties.getReadTimeout(),TimeUnit.MILLISECONDS);
                okHttpClientBuilder.writeTimeout(httpProperties.getRequestTimeout(),TimeUnit.MILLISECONDS);
                ProxySelector proxySelector = ProxySelector.getDefault();
                okHttpClientBuilder.proxySelector(proxySelector);
                return okHttpClientBuilder.build();
            } catch (KeyManagementException | NoSuchAlgorithmException exception) {
                log.error("the http connection pool initiated with error, error: {}", exception.getMessage());
                throw new HttpConfigError("the http connection pool initiated with error, error: " + exception.getMessage(), exception);
            }
        }

        /**
         * <code>x509TrustManager</code>
         * <p>the 509 trust manager method.</p>
         * @return {@link javax.net.ssl.X509TrustManager} <p>the 509 trust manager return object is <code>X509TrustManager</code> type.</p>
         * @see javax.net.ssl.X509TrustManager
         * @see org.springframework.context.annotation.Bean
         * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
         */
        @Bean
        @ConditionalOnMissingBean(X509TrustManager.class)
        public X509TrustManager x509TrustManager() {
            return new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
                }
                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
                }
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
        }
    }


    /**
     * <code>HttpClientRestTemplateAutoConfigure</code>
     * <p>The type http client rest template auto configure class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see org.springframework.context.annotation.Configuration
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnClass
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     * @since Jdk1.8
     */
    @Configuration
    @ConditionalOnClass({RestTemplate.class,HttpClient.class})
    @ConditionalOnProperty(value = "nichetoolkit.rest.http.http-type", havingValue = "http_client", matchIfMissing = true)
    public class HttpClientRestTemplateAutoConfigure {
        /**
         * <code>HttpClientRestTemplateAutoConfigure</code>
         * Instantiates a new http client rest template auto configure.
         */
        public HttpClientRestTemplateAutoConfigure() {
            log.debug("================= http-client-rest-template-auto-config initiated ！ ===================");
        }

        /**
         * <code>httpTemplate</code>
         * <p>the template method.</p>
         * @param httpComponentsClientHttpRequestFactory {@link org.springframework.http.client.HttpComponentsClientHttpRequestFactory} <p>the http components client http request factory parameter is <code>HttpComponentsClientHttpRequestFactory</code> type.</p>
         * @return {@link org.springframework.web.client.RestTemplate} <p>the template return object is <code>RestTemplate</code> type.</p>
         * @see org.springframework.http.client.HttpComponentsClientHttpRequestFactory
         * @see org.springframework.web.client.RestTemplate
         * @see org.springframework.context.annotation.Bean
         */
        @Bean(name = HttpClientType.HTTPCLIENT_BEAN)
        public RestTemplate httpTemplate(HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory) {
            return createRestTemplate(httpComponentsClientHttpRequestFactory);
        }

        /**
         * <code>httpComponentsClientHttpRequestFactory</code>
         * <p>the components client http request factory method.</p>
         * @param httpClient {@link org.apache.http.client.HttpClient} <p>the http client parameter is <code>HttpClient</code> type.</p>
         * @return {@link org.springframework.http.client.HttpComponentsClientHttpRequestFactory} <p>the components client http request factory return object is <code>HttpComponentsClientHttpRequestFactory</code> type.</p>
         * @see org.apache.http.client.HttpClient
         * @see org.springframework.http.client.HttpComponentsClientHttpRequestFactory
         * @see org.springframework.context.annotation.Bean
         */
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

        /**
         * <code>httpClient</code>
         * <p>the client method.</p>
         * @param x509TrustManager {@link javax.net.ssl.X509TrustManager} <p>the x 509 trust manager parameter is <code>X509TrustManager</code> type.</p>
         * @return {@link org.apache.http.client.HttpClient} <p>the client return object is <code>HttpClient</code> type.</p>
         * @throws HttpConfigError {@link io.github.nichetoolkit.rest.error.network.HttpConfigError} <p>the http config error is <code>HttpConfigError</code> type.</p>
         * @see javax.net.ssl.X509TrustManager
         * @see org.apache.http.client.HttpClient
         * @see org.springframework.context.annotation.Bean
         * @see io.github.nichetoolkit.rest.error.network.HttpConfigError
         */
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

        /**
         * <code>x509TrustManager</code>
         * <p>the 509 trust manager method.</p>
         * @return {@link javax.net.ssl.X509TrustManager} <p>the 509 trust manager return object is <code>X509TrustManager</code> type.</p>
         * @see javax.net.ssl.X509TrustManager
         * @see org.springframework.context.annotation.Bean
         * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
         */
        @Bean
        @ConditionalOnMissingBean(X509TrustManager.class)
        public X509TrustManager x509TrustManager() {
            return new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
                }
                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
                }
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
        }

        /**
         * <code>connectionKeepAliveStrategy</code>
         * <p>the keep alive strategy method.</p>
         * @return {@link org.apache.http.conn.ConnectionKeepAliveStrategy} <p>the keep alive strategy return object is <code>ConnectionKeepAliveStrategy</code> type.</p>
         * @see org.apache.http.conn.ConnectionKeepAliveStrategy
         */
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
    }

    /**
     * <code>getDefaultHeaders</code>
     * <p>the default headers getter method.</p>
     * @return {@link java.util.List} <p>the default headers return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    private List<Header> getDefaultHeaders() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));
        return headers;
    }

    /**
     * <code>createRestTemplate</code>
     * <p>the rest template method.</p>
     * @param factory {@link org.springframework.http.client.ClientHttpRequestFactory} <p>the factory parameter is <code>ClientHttpRequestFactory</code> type.</p>
     * @return {@link org.springframework.web.client.RestTemplate} <p>the rest template return object is <code>RestTemplate</code> type.</p>
     * @see org.springframework.http.client.ClientHttpRequestFactory
     * @see org.springframework.web.client.RestTemplate
     */
    private RestTemplate createRestTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(factory));
        if (interceptProperties.getEnabled()) {
            restTemplate.getInterceptors().add(httpInterceptor);
        }
        modifyDefaultCharset(restTemplate);
        DefaultUriBuilderFactory uriFactory = new DefaultUriBuilderFactory();
        uriFactory.setEncodingMode(httpProperties.getEncodingMode());
        restTemplate.setUriTemplateHandler(uriFactory);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return restTemplate;
    }


    /**
     * <code>modifyDefaultCharset</code>
     * <p>the default charset method.</p>
     * @param restTemplate {@link org.springframework.web.client.RestTemplate} <p>the rest template parameter is <code>RestTemplate</code> type.</p>
     * @see org.springframework.web.client.RestTemplate
     */
    private void modifyDefaultCharset(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> messageConverter : messageConverters) {
            if (StringHttpMessageConverter.class == messageConverter.getClass()) {
                converterTarget = messageConverter;
                break;
            }
        }
        if (null != converterTarget) {
            messageConverters.remove(converterTarget);
        }
        Charset defaultCharset = httpProperties.getCharset();
        messageConverters.add(1, new StringHttpMessageConverter(defaultCharset));
    }

}
