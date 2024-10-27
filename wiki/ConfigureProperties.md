# exception&error console log configuration

* prefix

>
> nichetoolkit.rest.error
>

* values

|                 value                  |   type    | defaultValue |                  description                   |
|:--------------------------------------:|:---------:|:------------:|:----------------------------------------------:|
|  `console-log.rest-exception-enabled`  | `Boolean` |   `false`    |  the switch of rest exception in console log.  |
| `console-log.common-exception-enabled` | `Boolean` |    `true`    | the switch of common exception in console log. |

* properties

```properties
nichetoolkit.rest.error.console-log.rest-exception-enabled=false
nichetoolkit.rest.error.console-log.common-exception-enabled=true
```

# jwt worker configuration

* prefix

>
> nichetoolkit.rest.jwt
>

* values

|        value         |      type      | defaultValue |             description              |
|:--------------------:|:--------------:|:------------:|:------------------------------------:|
|      `enabled`       |   `Boolean`    |   `false`    |      the switch of jwt worker.       |
|     `algorithm`      | `JwtAlgorithm` |   `hs256`    |  the algorithm type of jwt worker.   |
|       `secret`       |    `String`    |              |    the secret key of jwt worker.     |
|        `kid`         |    `String`    |              |        the kid of jwt worker.        |
|       `issuer`       |    `String`    |              |      the issuer of jwt worker.       |
|     `audiences`      |   `String[]`   |              |     the audiences of jwt worker.     |
|    `expire-time`     |     `Long`     |     `0`      |    the expire time of jwt worker.    |
|    `expire-unit`     |  `ChronoUnit`  |   `millis`   |    the expire-unit of jwt worker.    |
| `issued-delay-time`  |     `Long`     |     `0`      | the issued delay time of jwt worker. |
| `issued-delay-unit`  |  `ChronoUnit`  |   `millis`   | the issued delay unit of jwt worker. |
| `not-before-enabled` |   `Boolean`    |   `false`    | the not before switch of jwt worker. |

* properties

```properties
nichetoolkit.rest.jwt.enabled=true
nichetoolkit.rest.jwt.algorithm=hs256
nichetoolkit.rest.jwt.secret=4AFa1sTe45FAst2^aPHV7sVG
nichetoolkit.rest.jwt.kid=
nichetoolkit.rest.jwt.issuer=io.github.nichetoolkit
nichetoolkit.rest.jwt.audiences=
nichetoolkit.rest.jwt.expire-time=0
nichetoolkit.rest.jwt.expire-unit=millis
nichetoolkit.rest.jwt.issued-delay-time=0
nichetoolkit.rest.jwt.issued-delay-unit=millis
nichetoolkit.rest.jwt.not-before-enabled=false
```

* examples

```java

@Slf4j
@SpringBootTest
class JwtWorkerTest {

    @Autowired
    private JwtWorker jwtWorker;

    @Test
    void test() {
        /* the uuid as unique id  */
        String uniqueId = GeneralUtils.uuid();
        log.info("the unique id: {}", uniqueId);
        /* the generate string as subject  */
        String subject = IdentityUtils.generateString();
        log.info("the subject: {}", subject);
        /* use jwt worker to generate token  */
        String token = jwtWorker.generate(uniqueId, subject);
        log.info("the token: {}", token);
        /* use jwt worker to parse token  */
        JWT jwt = jwtWorker.parser(token);
        log.info("the jwt: {}", JsonUtils.parseJson(jwt));
    }
}
```

# radix worker configuration

* prefix

>
> nichetoolkit.rest.radix
>

* values

|    value     |   type    |            defaultValue             |              description              |
|:------------:|:---------:|:-----------------------------------:|:-------------------------------------:|
|  `enabled`   | `Boolean` |               `false`               |      the switch of radix worker.      |
|   `digits`   | `String`  | `qwe8as2dzx9c7p5ik3mjufr4vyltn6bgh` | the digits character of radix worker. |
|   `supply`   | `String`  |                 `o`                 | the supply character of radix worker. |
| `min-length` | `Integer` |                 `6`                 |    the min length of radix worker.    |

* properties

```properties
nichetoolkit.rest.radix.enabled=true
nichetoolkit.rest.radix.digits=qwe8as2dzx9c7p5ik3mjufr4vyltn6bgh
nichetoolkit.rest.radix.supply=o
nichetoolkit.rest.radix.min-length=6
```

* examples

```java

@Slf4j
@SpringBootTest
class RadixWorkerTest {

    @Autowired
    private RadixWorker radixWorker;

    @Test
    void test() {
        /* the generate long value as default subject  */
        Long defaultSubject = IdentityUtils.generateLong();
        log.info("the default subject: {}", defaultSubject);
        /* use radix worker to encrypt subject  */
        String encryptSubject = radixWorker.encrypt(defaultSubject);
        log.info("the encrypt subject: {}", encryptSubject);
        /* use radix worker to decrypt subject  */
        Long decryptSubject = radixWorker.decrypt(encryptSubject);
        log.info("the decrypt subject: {}", decryptSubject);
        Assertions.assertEquals(decryptSubject, defaultSubject);
    }
}
```

# rsa worker configuration

* prefix

>
> nichetoolkit.rest.rsa
>

* values

|     value     |   type    | defaultValue |              description              |
|:-------------:|:---------:|:------------:|:-------------------------------------:|
|   `enabled`   | `Boolean` |   `false`    |       the switch of rsa worker.       |
|  `key-size`   | `Integer` |    `2048`    |      the key size of rsa worker.      |
| `public-key`  | `String`  |              |     the public key of rsa worker.     |
| `private-key` | `String`  |              |    the private key of rsa worker.     |
| `auto-verify` | `Boolean` |    `true`    | the auto verify switch of rsa worker. |

* properties

```properties
nichetoolkit.rest.rsa.enabled=true
nichetoolkit.rest.rsa.key-size=1024
nichetoolkit.rest.rsa.public-key=MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdN-Jpwju_3YwRHEi0i6OYjF3nhY4zsqSJZ36eJ0wlTXbnyFSYnqLdQ9Hb2rSNiJRW-SQo5j5gd5714ceWqPvKCEqHIembx9q7COUo-jQb9_X-040B4lmFkQ5OvEc11zkMYdgxmCpUjsj6x5gVi9RBuB4kSaZXxJgXrzLyneAkQQIDAQAB
nichetoolkit.rest.rsa.private-key=MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ034mnCO7_djBEcSLSLo5iMXeeFjjOypIlnfp4nTCVNdufIVJieot1D0dvatI2IlFb5JCjmPmB3nvXhx5ao-8oISoch6ZvH2rsI5Sj6NBv39f7TjQHiWYWRDk68RzXXOQxh2DGYKlSOyPrHmBWL1EG4HiRJplfEmBevMvKd4CRBAgMBAAECgYBAY7f2Kpe__-Oyyku4rgwlmS-Id_mCkOK0Z8_CxEN6b5yIV1LWYvo-9tzHV25lHQ_sAwPdQFdBuRxAheTxfmoIb5IOUO6D_Km6BrloDIB37B5smxdrsS-1xCiengA4GK6ND2v8Zv2UnQ0J95EfHWVhOh63gPpw4hC_5Bb33UNgAQJBAPfb0B2KwEhIqUS_Cpf1QMNpefup5GRAygvxqansrtKehmxhvSAfRisHRkeg1eO4x0vEXfz-awvbRAPkIWpDjoECQQCiYeW7iuwQQmn3UHLtJZsnhGU9e2BTnLIi3GROHjq_EWcpewjibSBGujVUIUPZKg4qipxnSRy9JcKss6Pd8TXBAkAd6lP2HNcUYmC7wWsf6CQG8eYdL-Y3C3LXejWGIuD-3s9OqYVQKTYo31nmL01hjN3REURe0r5M0gwlhBVcZAcBAkAwS7WsRfFYFKi3qfHEnr66ziHeW2eW9q_0tmAO6-bFFUP9jl5iGdrTrmZWE16-pEd6V6eVAKXPi_-Opg9C348BAkAIApHVGuXj0KY2jyTQpDJzbCqhXKWUKgYQ6LbgzWf5vn_1BSS5Wx4ltQrqe3uhusx8TuoZo7jys00H4Xow1VaR
nichetoolkit.rest.rsa.auto-verify=true
```

* examples

```java

@Slf4j
@SpringBootTest
class RsaWorkerTest {

    @Autowired
    private RsaWorker rsaWorker;

    @Test
    void rsaKey() {
        RsaKey rsaKey = rsaWorker.generate();
        log.info("the publicKey: {}", rsaKey.getPublicKey());
        log.info("the privateKey: {}", rsaKey.getPrivateKey());
    }

    @Test
    void test() {
        /* the uuid as subject  */
        String subject = GeneralUtils.uuid();
        byte[] subjectBytes = subject.getBytes(StandardCharsets.UTF_8);
        /* use Base64 encode subject bytes as default subject  */
        String defaultSubject = Base64.encodeBase64String(subjectBytes);
        log.info("the default subject: {}", defaultSubject);
        /* use rsa worker to encrypt default subject  */
        String encryptSubject = rsaWorker.encrypt(defaultSubject);
        log.info("the encrypt subject: {}", encryptSubject);
        /* use Base64 to decode subject  */
        byte[] decodeSubjectBytes = Base64.decodeBase64(encryptSubject);
        /* use rsa worker to decrypt subject  */
        String base64Decrypt = rsaWorker.decrypt(decodeSubjectBytes);
        /* use Base64 to decode subject  */
        byte[] decryptBytes = Base64.decodeBase64(base64Decrypt);
        String decryptSubject = new String(decryptBytes);
        log.info("the decrypt subject: {}", decryptSubject);
        Assertions.assertEquals(decryptSubject, defaultSubject);
    }
}
```

# sha worker configuration

* prefix

>
> nichetoolkit.rest.sha
>

* values

|    value    |      type      | defaultValue |            description            |
|:-----------:|:--------------:|:------------:|:---------------------------------:|
|  `enabled`  |   `Boolean`    |   `false`    |     the switch of sha worker.     |
|  `secret`   |    `String`    |              |   the secret key of sha worker.   |
| `algorithm` | `ShaAlgorithm` |   `sha256`   | the algorithm type of sha worker. |

* properties

```properties
nichetoolkit.rest.sha.enabled=true
nichetoolkit.rest.sha.secret=4AFa1sTe45FAst2^aPHV7sVG
nichetoolkit.rest.sha.algorithm=sha256
```

* examples

```java

@Slf4j
@SpringBootTest
class ShaWorkerTest {

    @Autowired
    private ShaWorker shaWorker;

    @Test
    void test() {
        /* the uuid as default password  */
        String password = GeneralUtils.uuid();
        log.info("the default password: {}", password);
        /* use sha worker to encrypt password  */
        String encrypt = shaWorker.encrypt(password);
        log.info("the encrypt password: {}", encrypt);
        Assertions.assertNotEquals(password, encrypt);
    }
}
```

# identity worker configuration

* prefix

>
> nichetoolkit.rest.identity
>

* values

|       value        |      type      | defaultValue |               description                |
|:------------------:|:--------------:|:------------:|:----------------------------------------:|
|     `enabled`      |   `Boolean`    |   `false`    |      the switch of identity worker.      |
|       `type`       | `IdentityType` |    `auto`    |  the identity type of identity worker.   |
| `config.worker-id` |     `Long`     |              | the worker id config of identity worker. |
| `config.center-id` |     `Long`     |              | the center id config of identity worker. |
|    `server.url`    |    `String`    |              |    the server url of identity worker.    |
|    `server.api`    |    `String`    |              |    the server api of identity worker.    |

* properties

```properties
nichetoolkit.rest.identity.enabled=true
nichetoolkit.rest.identity.type=config
nichetoolkit.rest.identity.config.worker-id=1
nichetoolkit.rest.identity.config.center-id=2
nichetoolkit.rest.identity.server.url=
nichetoolkit.rest.identity.server.api=
```

# intercept message configuration

* prefix

>
> nichetoolkit.rest.intercept
>

* values

|       value       |   type    | defaultValue |                     description                      |
|:-----------------:|:---------:|:------------:|:----------------------------------------------------:|
|     `enabled`     | `Boolean` |   `false`    |        the switch of intercept configuration.        |
| `logging-enabled` | `Boolean` |   `false`    |    the logging switch of intercept configuration.    |
| `userlog-enabled` | `Boolean` |  ``false``   |    the userlog switch of intercept configuration.    |
|  `bean-enabled`   | `Boolean` |   `false`    |     the bean switch of intercept configuration.      |
|   `body-length`   | `Integer` |    `1024`    | the response body length of intercept configuration. |
|  `error-length`   | `Integer` |    `1024`    |     the error length of intercept configuration.     |
| `message-length`  | `Integer` |    `1024`    |    the message length of intercept configuration.    |
|  `result-length`  | `Integer` |    `1024`    |    the result length of intercept configuration.     |

* properties

```properties
nichetoolkit.rest.intercept.enabled=true
nichetoolkit.rest.intercept.logging-enabled=true
nichetoolkit.rest.intercept.userlog-enabled=true
nichetoolkit.rest.intercept.bean-enabled=true
nichetoolkit.rest.intercept.body-length=1024
nichetoolkit.rest.intercept.error-length=1024
nichetoolkit.rest.intercept.message-length=1024
nichetoolkit.rest.intercept.result-length=1024
```

# http client configuration

* prefix

>
> nichetoolkit.rest.http
>

* values

|       value        |       type       | defaultValue |                      description                       |
|:------------------:|:----------------:|:------------:|:------------------------------------------------------:|
|     `enabled`      |    `Boolean`     |   `false`    |        the switch of http client configuration.        |
| `connect-timeout`  |    `Integer`     |    `2000`    |   the connect timeout of http client configuration.    |
|   `read-timeout`   |    `Integer`     |   `30000`    |     the read timeout of http client configuration.     |
| `request-timeout`  |    `Integer`     |    `200`     |   the request timeout of http client configuration.    |
|  `max-core-size`   |    `Integer`     |     `5`      | the max core thread size of http client configuration. |
|  `max-idle-size`   |    `Integer`     |     `10`     | the max idle thread size of http client configuration. |
|    `http-type`     | `HttpClientType` |  `default`   |      the http type of http client configuration.       |
|     `charset`      |    `Charset`     |   `utf_8`    |   the charset encoding of http client configuration.   |
|  `encoding-mode`   |  `EncodingMode`  |    `none`    |    the encoding mode of http client configuration.     |
|   `retry-times`    |    `Integer`     |     `0`      |     the retry times of http client configuration.      |
| `keep-alive-hosts` |      `Map`       |              |   the keep alive hosts of http client configuration.   |
| `keep-alive-time`  |      `Long`      |   `600000`   |   the keep alive time of http client configuration.    |
|    `proxy.type`    |   `ProxyType`    |   `socks`    |      the proxy type of http client configuration.      |
|  `proxy.hostname`  |     `String`     |              |    the proxy hostname of http client configuration.    |
|    `proxy.port`    |    `Integer`     |              |      the proxy port of http client configuration.      |

* properties

```properties
nichetoolkit.rest.http.enabled=true
nichetoolkit.rest.http.connect-timeout=2000
nichetoolkit.rest.http.read-timeout=30000
nichetoolkit.rest.http.request-timeout=200
nichetoolkit.rest.http.max-core-size=5
nichetoolkit.rest.http.max-idle-size=10
nichetoolkit.rest.http.http-type=default
nichetoolkit.rest.http.charset=utf-8
nichetoolkit.rest.http.encoding-mode=none
nichetoolkit.rest.http.retry-times=0
nichetoolkit.rest.http.keep-alive-time=600000
nichetoolkit.rest.http.proxy.type=socks
nichetoolkit.rest.http.proxy.hostname=
nichetoolkit.rest.http.proxy.port=
```

* http_client usages

- properties

```properties
nichetoolkit.rest.http.enabled=true
nichetoolkit.rest.http.http-type=http_client
```

- pom

```xml

<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
</dependency>
```

* ok_http_client usages

- properties

```properties
nichetoolkit.rest.http.enabled=true
nichetoolkit.rest.http.http-type=ok_http_client
```

- pom

```xml

<dependency>
    <groupId>com.squareup.okhttp3</groupId>
    <artifactId>okhttp</artifactId>
</dependency>
```

# logback configuration

* prefix

>
> nichetoolkit.rest.logback
>

* values

|       value       |    type    |  defaultValue  |                   description                    |
|:-----------------:|:----------:|:--------------:|:------------------------------------------------:|
|     `enabled`     | `Boolean`  |     `true`     |       the switch of logback configuration.       |
|   `logging-key`   |  `String`  |  `loggingKey`  | the logging key param of logback configuration.  |
|   `attributes`    | `String[]` |      `t`       |   the attributes key of logback configuration.   |
|   `request-key`   |  `String`  |  `requestKey`  |    the request key of logback configuration.     |
| `request-header`  |  `String`  | `X-Request-ID` | the request header key of logback configuration. |
| `argument-length` | `Integer`  |     `1024`     |  the argument length of logback configuration.   |
|  `prefix-length`  | `Integer`  |      `12`      |  the argument length of logback configuration.   |

* properties

```properties
nichetoolkit.rest.logback.enabled=true
nichetoolkit.rest.logback.logging-key=loggingKey
nichetoolkit.rest.logback.attributes=t
nichetoolkit.rest.logback.request-key=requestKey
nichetoolkit.rest.logback.request-header=X-Request-ID
nichetoolkit.rest.logback.argument-length=1024
nichetoolkit.rest.logback.prefix-length=12
logging.config=classpath:logback-spring.xml
logging.file.path=G:\\data\\server\\log
```

* logback-spring.xml

 ```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="10 seconds">
    <!--include default logback configuration -->
    <include resource="io/github/nichetoolkit/rest/logback/logback-default.xml"/>
    <!--log retention time -->
    <property name="MAX_FILE_SIZE" value="1GB"/>
    <property name="MAX_HISTORY" value="30"/>

    <springProfile name="default">
        <logger name="io.github.nichetoolkit" level="debug"/>
        <root level="info">
            <appender-ref ref="CONSOLE"/>
            <appender-ref ref="LOGS_FILE"/>
            <appender-ref ref="DEBUG_FILE"/>
            <appender-ref ref="INFO_FILE"/>
            <appender-ref ref="WARN_FILE"/>
            <appender-ref ref="ERROR_FILE"/>
        </root>
    </springProfile>
</configuration>
 ```