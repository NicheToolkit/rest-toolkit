package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.error.network.HttpConfigError;
import io.github.nichetoolkit.rest.http.HttpThreadFactory;
import io.github.nichetoolkit.rest.http.RestTemplates;
import io.github.nichetoolkit.rest.http.config.HttpClientType;
import io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor;
import io.github.nichetoolkit.rest.type.CharsetType;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.ConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.RouteInfo;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.DefaultClientTlsStrategy;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicHeaderElementIterator;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
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
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * <code>RestHttpAutoConfigure</code>
 * <p>The rest http auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see java.lang.SuppressWarnings
 * @see org.springframework.boot.context.properties.EnableConfigurationProperties
 * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 * @since Jdk17
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@EnableConfigurationProperties({RestInterceptProperties.class, RestHttpProperties.class})
@ConditionalOnProperty(value = "nichetoolkit.rest.http.enabled", havingValue = "true")
public class RestHttpAutoConfigure {

    /**
     * <code>interceptProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestInterceptProperties} <p>The <code>interceptProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestInterceptProperties
     */
    private final RestInterceptProperties interceptProperties;

    /**
     * <code>httpProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestHttpProperties} <p>The <code>httpProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestHttpProperties
     */
    private final RestHttpProperties httpProperties;

    /**
     * <code>RestHttpAutoConfigure</code>
     * <p>Instantiates a new rest http auto configure.</p>
     * @param httpProperties      {@link io.github.nichetoolkit.rest.configure.RestHttpProperties} <p>The http properties parameter is <code>RestHttpProperties</code> type.</p>
     * @param interceptProperties {@link io.github.nichetoolkit.rest.configure.RestInterceptProperties} <p>The intercept properties parameter is <code>RestInterceptProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestHttpProperties
     * @see io.github.nichetoolkit.rest.configure.RestInterceptProperties
     */
    public RestHttpAutoConfigure(RestHttpProperties httpProperties, RestInterceptProperties interceptProperties) {
        log.debug("The auto configuration for [rest-http] initiated");
        this.httpProperties = httpProperties;
        this.interceptProperties = interceptProperties;
    }

    /**
     * <code>defaultClientHttpInterceptor</code>
     * <p>The default client http interceptor method.</p>
     * @return {@link io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor} <p>The default client http interceptor return object is <code>DefaultClientHttpInterceptor</code> type.</p>
     * @see io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(DefaultClientHttpInterceptor.class)
    public DefaultClientHttpInterceptor defaultClientHttpInterceptor() {
        return new DefaultClientHttpInterceptor(this.interceptProperties);
    }


    /**
     * <code>restTemplates</code>
     * <p>The rest templates method.</p>
     * @param restTemplate {@link org.springframework.web.client.RestTemplate} <p>The rest template parameter is <code>RestTemplate</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.http.RestTemplates} <p>The rest templates return object is <code>RestTemplates</code> type.</p>
     * @see org.springframework.web.client.RestTemplate
     * @see io.github.nichetoolkit.rest.http.RestTemplates
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(RestTemplates.class)
    public RestTemplates restTemplates(RestTemplate restTemplate) {
        log.debug("The http       properties: {}", JsonUtils.parseJson(httpProperties));
        HttpClientType httpType = httpProperties.getHttpType();
        if (GeneralUtils.isNotEmpty(httpType)) {
            restTemplate = BeanUtils.beanOfType(httpType.getValue(), RestTemplate.class);
        }
        return new RestTemplates(restTemplate);
    }

    /**
     * <code>DefaultClientTemplateAutoConfigure</code>
     * <p>The default client template auto configure class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see org.springframework.context.annotation.Configuration
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     * @since Jdk17
     */
    @Configuration
    @ConditionalOnProperty(value = "nichetoolkit.rest.http.http-type", havingValue = "default_client")
    public class DefaultClientTemplateAutoConfigure {
        /**
         * <code>DefaultClientTemplateAutoConfigure</code>
         * <p>Instantiates a new default client template auto configure.</p>
         */
        public DefaultClientTemplateAutoConfigure() {
            log.debug("The auto configuration for [defaultClient] rest template initiated");
        }

        /**
         * <code>restTemplate</code>
         * <p>The rest template method.</p>
         * @param clientHttpInterceptor {@link io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor} <p>The client http interceptor parameter is <code>DefaultClientHttpInterceptor</code> type.</p>
         * @return {@link org.springframework.web.client.RestTemplate} <p>The rest template return object is <code>RestTemplate</code> type.</p>
         * @see io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor
         * @see org.springframework.web.client.RestTemplate
         * @see org.springframework.context.annotation.Bean
         */
        @Bean(name = HttpClientType.DEFAULT_TEMPLATE)
        public RestTemplate restTemplate(DefaultClientHttpInterceptor clientHttpInterceptor) {
            return createRestTemplate(null,clientHttpInterceptor);
        }
    }

    /**
     * <code>ReactorClientTemplateAutoConfigure</code>
     * <p>The reactor client template auto configure class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see org.springframework.context.annotation.Configuration
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     * @since Jdk17
     */
    @Configuration
    @ConditionalOnProperty(value = "nichetoolkit.rest.http.http-type", havingValue = "reactor_client")
    public class ReactorClientTemplateAutoConfigure {
        /**
         * <code>ReactorClientTemplateAutoConfigure</code>
         * <p>Instantiates a new reactor client template auto configure.</p>
         */
        public ReactorClientTemplateAutoConfigure() {
            log.debug("The auto configuration for [reactorClient] rest template initiated");
        }

        /**
         * <code>restTemplate</code>
         * <p>The rest template method.</p>
         * @param reactorClientRequestFactory {@link org.springframework.http.client.ReactorClientHttpRequestFactory} <p>The reactor client request factory parameter is <code>ReactorClientHttpRequestFactory</code> type.</p>
         * @param clientHttpInterceptor       {@link io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor} <p>The client http interceptor parameter is <code>DefaultClientHttpInterceptor</code> type.</p>
         * @return {@link org.springframework.web.client.RestTemplate} <p>The rest template return object is <code>RestTemplate</code> type.</p>
         * @see org.springframework.http.client.ReactorClientHttpRequestFactory
         * @see io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor
         * @see org.springframework.web.client.RestTemplate
         * @see org.springframework.context.annotation.Bean
         */
        @Bean(name = HttpClientType.REACTOR_TEMPLATE)
        public RestTemplate restTemplate(ReactorClientHttpRequestFactory reactorClientRequestFactory, DefaultClientHttpInterceptor clientHttpInterceptor) {
            return createRestTemplate(reactorClientRequestFactory, clientHttpInterceptor);
        }

        /**
         * <code>reactorClientRequestFactory</code>
         * <p>The reactor client request factory method.</p>
         * @return {@link org.springframework.http.client.ReactorClientHttpRequestFactory} <p>The reactor client request factory return object is <code>ReactorClientHttpRequestFactory</code> type.</p>
         * @see org.springframework.http.client.ReactorClientHttpRequestFactory
         * @see org.springframework.context.annotation.Bean
         */
        @Bean(name = "reactorClientRequestFactory")
        public ReactorClientHttpRequestFactory reactorClientRequestFactory() {
            ReactorClientHttpRequestFactory reactorClientHttpRequestFactory = new ReactorClientHttpRequestFactory();
            reactorClientHttpRequestFactory.setConnectTimeout(httpProperties.getConnectTimeout());
            /* 数据读取超时时间，即SocketTimeout */
            reactorClientHttpRequestFactory.setReadTimeout(httpProperties.getReadTimeout());
            ThreadPoolTaskScheduler taskExecutor = new ThreadPoolTaskScheduler();
            taskExecutor.setPoolSize(httpProperties.getMaxCoreSize());
            taskExecutor.setThreadFactory(new HttpThreadFactory("http-thread-pool"));
            reactorClientHttpRequestFactory.setExecutor(taskExecutor);
            return reactorClientHttpRequestFactory;
        }
    }


    /**
     * <code>JdkClientTemplateAutoConfigure</code>
     * <p>The jdk client template auto configure class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see org.springframework.context.annotation.Configuration
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     * @since Jdk17
     */
    @Configuration
    @ConditionalOnProperty(value = "nichetoolkit.rest.http.http-type", havingValue = "jdk_client")
    public class JdkClientTemplateAutoConfigure {
        /**
         * <code>JdkClientTemplateAutoConfigure</code>
         * <p>Instantiates a new jdk client template auto configure.</p>
         */
        public JdkClientTemplateAutoConfigure() {
            log.debug("The auto configuration for [jdkClient] rest template initiated");
        }

        /**
         * <code>restTemplate</code>
         * <p>The rest template method.</p>
         * @param jdkClientRequestFactory {@link org.springframework.http.client.JdkClientHttpRequestFactory} <p>The jdk client request factory parameter is <code>JdkClientHttpRequestFactory</code> type.</p>
         * @param clientHttpInterceptor   {@link io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor} <p>The client http interceptor parameter is <code>DefaultClientHttpInterceptor</code> type.</p>
         * @return {@link org.springframework.web.client.RestTemplate} <p>The rest template return object is <code>RestTemplate</code> type.</p>
         * @see org.springframework.http.client.JdkClientHttpRequestFactory
         * @see io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor
         * @see org.springframework.web.client.RestTemplate
         * @see org.springframework.context.annotation.Bean
         */
        @Bean(name = HttpClientType.JDK_TEMPLATE)
        public RestTemplate restTemplate(JdkClientHttpRequestFactory jdkClientRequestFactory, DefaultClientHttpInterceptor clientHttpInterceptor) {
            return createRestTemplate(jdkClientRequestFactory, clientHttpInterceptor);
        }

        /**
         * <code>jdkClientRequestFactory</code>
         * <p>The jdk client request factory method.</p>
         * @return {@link org.springframework.http.client.JdkClientHttpRequestFactory} <p>The jdk client request factory return object is <code>JdkClientHttpRequestFactory</code> type.</p>
         * @see org.springframework.http.client.JdkClientHttpRequestFactory
         * @see org.springframework.context.annotation.Bean
         */
        @Bean(name = "jdkClientRequestFactory")
        public JdkClientHttpRequestFactory jdkClientRequestFactory() {
            JdkClientHttpRequestFactory jdkClientHttpRequestFactory = new JdkClientHttpRequestFactory();
            jdkClientHttpRequestFactory.enableCompression(true);
            /* 数据读取超时时间，即SocketTimeout */
            jdkClientHttpRequestFactory.setReadTimeout(httpProperties.getReadTimeout());
            return jdkClientHttpRequestFactory;
        }
    }

    /**
     * <code>JettyClientTemplateAutoConfigure</code>
     * <p>The jetty client template auto configure class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see org.springframework.context.annotation.Configuration
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     * @since Jdk17
     */
    @Configuration
    @ConditionalOnProperty(value = "nichetoolkit.rest.http.http-type", havingValue = "jetty_client")
    public class JettyClientTemplateAutoConfigure {
        /**
         * <code>JettyClientTemplateAutoConfigure</code>
         * <p>Instantiates a new jetty client template auto configure.</p>
         */
        public JettyClientTemplateAutoConfigure() {
            log.debug("The auto configuration for [jettyClient] rest template initiated");
        }

        /**
         * <code>restTemplate</code>
         * <p>The rest template method.</p>
         * @param jettyClientRequestFactory {@link org.springframework.http.client.JettyClientHttpRequestFactory} <p>The jetty client request factory parameter is <code>JettyClientHttpRequestFactory</code> type.</p>
         * @param clientHttpInterceptor     {@link io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor} <p>The client http interceptor parameter is <code>DefaultClientHttpInterceptor</code> type.</p>
         * @return {@link org.springframework.web.client.RestTemplate} <p>The rest template return object is <code>RestTemplate</code> type.</p>
         * @see org.springframework.http.client.JettyClientHttpRequestFactory
         * @see io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor
         * @see org.springframework.web.client.RestTemplate
         * @see org.springframework.context.annotation.Bean
         */
        @Bean(name = HttpClientType.JETTY_TEMPLATE)
        public RestTemplate restTemplate(JettyClientHttpRequestFactory jettyClientRequestFactory, DefaultClientHttpInterceptor clientHttpInterceptor) {
            return createRestTemplate(jettyClientRequestFactory, clientHttpInterceptor);
        }

        /**
         * <code>jettyClientRequestFactory</code>
         * <p>The jetty client request factory method.</p>
         * @return {@link org.springframework.http.client.JettyClientHttpRequestFactory} <p>The jetty client request factory return object is <code>JettyClientHttpRequestFactory</code> type.</p>
         * @see org.springframework.http.client.JettyClientHttpRequestFactory
         * @see org.springframework.context.annotation.Bean
         */
        @Bean(name = "jettyClientRequestFactory")
        public JettyClientHttpRequestFactory jettyClientRequestFactory() {
            JettyClientHttpRequestFactory jettyClientHttpRequestFactory = new JettyClientHttpRequestFactory();
            jettyClientHttpRequestFactory.setConnectTimeout(httpProperties.getConnectTimeout());
            /* 数据读取超时时间，即SocketTimeout */
            jettyClientHttpRequestFactory.setReadTimeout(httpProperties.getReadTimeout());
            return jettyClientHttpRequestFactory;
        }
    }

    /**
     * <code>SimpleClientTemplateAutoConfigure</code>
     * <p>The simple client template auto configure class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see org.springframework.context.annotation.Configuration
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     * @since Jdk17
     */
    @Configuration
    @ConditionalOnProperty(value = "nichetoolkit.rest.http.http-type", havingValue = "simple_client")
    public class SimpleClientTemplateAutoConfigure {
        /**
         * <code>SimpleClientTemplateAutoConfigure</code>
         * <p>Instantiates a new simple client template auto configure.</p>
         */
        public SimpleClientTemplateAutoConfigure() {
            log.debug("The auto configuration for [simpleClient] rest template initiated");
        }

        /**
         * <code>restTemplate</code>
         * <p>The rest template method.</p>
         * @param simpleClientRequestFactory {@link org.springframework.http.client.SimpleClientHttpRequestFactory} <p>The simple client request factory parameter is <code>SimpleClientHttpRequestFactory</code> type.</p>
         * @param clientHttpInterceptor      {@link io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor} <p>The client http interceptor parameter is <code>DefaultClientHttpInterceptor</code> type.</p>
         * @return {@link org.springframework.web.client.RestTemplate} <p>The rest template return object is <code>RestTemplate</code> type.</p>
         * @see org.springframework.http.client.SimpleClientHttpRequestFactory
         * @see io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor
         * @see org.springframework.web.client.RestTemplate
         * @see org.springframework.context.annotation.Bean
         */
        @Bean(name = HttpClientType.SIMPLE_TEMPLATE)
        public RestTemplate restTemplate(SimpleClientHttpRequestFactory simpleClientRequestFactory, DefaultClientHttpInterceptor clientHttpInterceptor) {
            return createRestTemplate(simpleClientRequestFactory, clientHttpInterceptor);
        }

        /**
         * <code>simpleClientRequestFactory</code>
         * <p>The simple client request factory method.</p>
         * @return {@link org.springframework.http.client.SimpleClientHttpRequestFactory} <p>The simple client request factory return object is <code>SimpleClientHttpRequestFactory</code> type.</p>
         * @see org.springframework.http.client.SimpleClientHttpRequestFactory
         * @see org.springframework.context.annotation.Bean
         */
        @Bean(name = "simpleClientRequestFactory")
        public SimpleClientHttpRequestFactory simpleClientRequestFactory() {
            SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            simpleClientHttpRequestFactory.setConnectTimeout(httpProperties.getConnectTimeout());
            /* 数据读取超时时间，即SocketTimeout */
            simpleClientHttpRequestFactory.setReadTimeout(httpProperties.getReadTimeout());
            /* 从连接池获取请求连接的超时时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的 */
            Proxy proxy = httpProperties.getProxy().toProxy();
            if (GeneralUtils.isNotEmpty(proxy)) {
                simpleClientHttpRequestFactory.setProxy(proxy);
            }
            return simpleClientHttpRequestFactory;
        }
    }

    /**
     * <code>Http5ClientTemplateAutoConfigure</code>
     * <p>The http 5 client template auto configure class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see org.springframework.context.annotation.Configuration
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnClass
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     * @since Jdk17
     */
    @Configuration
    @ConditionalOnClass({RestTemplate.class, HttpClient.class})
    @ConditionalOnProperty(value = "nichetoolkit.rest.http.http-type", havingValue = "http_client")
    public class Http5ClientTemplateAutoConfigure {
        /**
         * <code>Http5ClientTemplateAutoConfigure</code>
         * <p>Instantiates a new http 5 client template auto configure.</p>
         */
        public Http5ClientTemplateAutoConfigure() {
            log.debug("the auto configuration for [http5Client] rest template initiated!");
        }

        /**
         * <code>httpTemplate</code>
         * <p>The http template method.</p>
         * @param httpClientRequestFactory {@link org.springframework.http.client.HttpComponentsClientHttpRequestFactory} <p>The http client request factory parameter is <code>HttpComponentsClientHttpRequestFactory</code> type.</p>
         * @param clientHttpInterceptor    {@link io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor} <p>The client http interceptor parameter is <code>DefaultClientHttpInterceptor</code> type.</p>
         * @return {@link org.springframework.web.client.RestTemplate} <p>The http template return object is <code>RestTemplate</code> type.</p>
         * @see org.springframework.http.client.HttpComponentsClientHttpRequestFactory
         * @see io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor
         * @see org.springframework.web.client.RestTemplate
         * @see org.springframework.context.annotation.Bean
         */
        @Bean(name = HttpClientType.HTTP_TEMPLATE)
        public RestTemplate httpTemplate(HttpComponentsClientHttpRequestFactory httpClientRequestFactory, DefaultClientHttpInterceptor clientHttpInterceptor) {
            return createRestTemplate(httpClientRequestFactory, clientHttpInterceptor);
        }

        /**
         * <code>httpClientRequestFactory</code>
         * <p>The http client request factory method.</p>
         * @param httpClient {@link org.apache.hc.client5.http.classic.HttpClient} <p>The http client parameter is <code>HttpClient</code> type.</p>
         * @return {@link org.springframework.http.client.HttpComponentsClientHttpRequestFactory} <p>The http client request factory return object is <code>HttpComponentsClientHttpRequestFactory</code> type.</p>
         * @see org.apache.hc.client5.http.classic.HttpClient
         * @see org.springframework.http.client.HttpComponentsClientHttpRequestFactory
         * @see org.springframework.context.annotation.Bean
         */
        @Bean(name = "httpClientRequestFactory")
        public HttpComponentsClientHttpRequestFactory httpClientRequestFactory(HttpClient httpClient) {
            HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(httpProperties.getConnectTimeout());
            /* 数据读取超时时间，即SocketTimeout */
            httpComponentsClientHttpRequestFactory.setReadTimeout(httpProperties.getReadTimeout());
            /* 从连接池获取请求连接的超时时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的 */
            httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(httpProperties.getRequestTimeout());
            return httpComponentsClientHttpRequestFactory;
        }

        /**
         * <code>httpClient</code>
         * <p>The http client method.</p>
         * @param x509TrustManager {@link javax.net.ssl.X509TrustManager} <p>The x 509 trust manager parameter is <code>X509TrustManager</code> type.</p>
         * @return {@link org.apache.hc.client5.http.classic.HttpClient} <p>The http client return object is <code>HttpClient</code> type.</p>
         * @throws HttpConfigError {@link io.github.nichetoolkit.rest.error.network.HttpConfigError} <p>The http config error is <code>HttpConfigError</code> type.</p>
         * @see javax.net.ssl.X509TrustManager
         * @see org.apache.hc.client5.http.classic.HttpClient
         * @see org.springframework.context.annotation.Bean
         * @see io.github.nichetoolkit.rest.error.network.HttpConfigError
         */
        @Bean(name = "httpClient")
        public HttpClient httpClient(X509TrustManager x509TrustManager) throws HttpConfigError {
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            Proxy proxy = httpProperties.getProxy().toProxy();
            if (GeneralUtils.isNotEmpty(proxy)) {
                InetSocketAddress address = (InetSocketAddress) proxy.address();
                HttpHost host = new HttpHost(address.getHostName(), proxy.type().name().toLowerCase(), address.getPort());
                httpClientBuilder.setProxy(host);
            }
            try {
                /* 设置信任ssl访问 */
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1) -> true).build();
                sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
                HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
                DefaultClientTlsStrategy clientTlsStrategy = new DefaultClientTlsStrategy(sslContext, hostnameVerifier);
                /* 使用Httpclient连接池的方式配置(推荐)，同时支持netty，okHttp以及其他http框架 */
                PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                        /* 最大连接数 */
                        .setMaxConnTotal(httpProperties.getMaxCoreSize())
                        /* 同路由并发数 */
                        .setMaxConnPerRoute(httpProperties.getMaxIdleSize())
                        .setTlsSocketStrategy(clientTlsStrategy)
                        .build();
                /* 配置连接池 */
                httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
                /* 重试次数 */
                httpClientBuilder.setRetryStrategy(new DefaultHttpRequestRetryStrategy(httpProperties.getRetryTimes(), TimeValue.ofSeconds(1L)));
                /* 设置默认请求头 */
                httpClientBuilder.setDefaultHeaders(getDefaultHeaders());
                /* 设置长连接保持策略 */
                httpClientBuilder.setKeepAliveStrategy(connectionKeepAliveStrategy());
                return httpClientBuilder.build();
            } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException exception) {
                log.error("the http connection pool initiated with error, error: {}", exception.getMessage());
                throw new HttpConfigError("the http connection pool initiated with error, error: " + exception.getMessage(), exception);
            }
        }

        /**
         * <code>x509TrustManager</code>
         * <p>The x 509 trust manager method.</p>
         * @return {@link javax.net.ssl.X509TrustManager} <p>The x 509 trust manager return object is <code>X509TrustManager</code> type.</p>
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
         * <p>The connection keep alive strategy method.</p>
         * @return {@link org.apache.hc.client5.http.ConnectionKeepAliveStrategy} <p>The connection keep alive strategy return object is <code>ConnectionKeepAliveStrategy</code> type.</p>
         * @see org.apache.hc.client5.http.ConnectionKeepAliveStrategy
         */
        public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
            return (response, context) -> {
                BasicHeaderElementIterator iterator = new BasicHeaderElementIterator(response.headerIterator(HeaderElements.KEEP_ALIVE));
                while (iterator.hasNext()) {
                    HeaderElement headerElement = iterator.next();
                    log.debug("header         element: {}", JsonUtils.parseJson(headerElement));
                    String param = headerElement.getName();
                    String value = headerElement.getValue();
                    if (value != null && "timeout".equalsIgnoreCase(param)) {
                        try {
                            return TimeValue.ofSeconds(Long.parseLong(value));
                        } catch (NumberFormatException exception) {
                            log.error("parsing long connection expiration time exception!", exception);
                        }
                    }
                }
                HttpClientContext clientContext = HttpClientContext.cast(context);
                RouteInfo httpRoute = clientContext.getHttpRoute();
                HttpHost target = httpRoute.getTargetHost();
                /* 如果请求目标地址,单独配置了长连接保持时间,使用该配置 */
                Optional<Map.Entry<String, Integer>> targetAny = Optional.ofNullable(httpProperties.getKeepAliveHosts()).orElseGet(HashMap::new)
                        .entrySet().stream().filter(entry -> entry.getKey().equalsIgnoreCase(target.getHostName())).findAny();
                /* 否则使用默认长连接保持时间 */
                return targetAny.map(entry -> TimeValue.ofSeconds(entry.getValue())).orElse(TimeValue.ofMilliseconds(httpProperties.getKeepAliveTime()));
            };
        }
    }

    /**
     * <code>getDefaultHeaders</code>
     * <p>The get default headers getter method.</p>
     * @return {@link java.util.List} <p>The get default headers return object is <code>List</code> type.</p>
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
     * <p>The create rest template method.</p>
     * @param factory               {@link org.springframework.http.client.ClientHttpRequestFactory} <p>The factory parameter is <code>ClientHttpRequestFactory</code> type.</p>
     * @param clientHttpInterceptor {@link io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor} <p>The client http interceptor parameter is <code>DefaultClientHttpInterceptor</code> type.</p>
     * @return {@link org.springframework.web.client.RestTemplate} <p>The create rest template return object is <code>RestTemplate</code> type.</p>
     * @see org.springframework.http.client.ClientHttpRequestFactory
     * @see io.github.nichetoolkit.rest.interceptor.DefaultClientHttpInterceptor
     * @see org.springframework.web.client.RestTemplate
     */
    private RestTemplate createRestTemplate(ClientHttpRequestFactory factory, DefaultClientHttpInterceptor clientHttpInterceptor) {
        RestTemplate restTemplate;
        if (GeneralUtils.isNotNull(factory)) {
            restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(factory));
        } else {
            restTemplate = new RestTemplate();
        }
        if (interceptProperties.getEnabled()) {
            restTemplate.getInterceptors().add(clientHttpInterceptor);
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
     * <p>The modify default charset method.</p>
     * @param restTemplate {@link org.springframework.web.client.RestTemplate} <p>The rest template parameter is <code>RestTemplate</code> type.</p>
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
        CharsetType charset = httpProperties.getCharset();
        messageConverters.add(1, new StringHttpMessageConverter(charset.getValue()));
    }

}
