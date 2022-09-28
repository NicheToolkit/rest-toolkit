## [Rest-Toolkit](https://github.com/NicheToolkit/rest-toolkit) rest开发工具组

[![GitHub license](https://img.shields.io/badge/license-Apache-blue.svg)](https://github.com/NicheToolkit/rest-toolkit/blob/master/LICENSE)[![Maven Release](https://img.shields.io/maven-central/v/io.github.nichetoolkit/rest-toolkit-starter.svg)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22io.github.nichetoolkit%22%20AND%20a%3A%rest-toolkit-starter%22)
![Tests](https://github.com/NicheToolkit/rest-toolkit/workflows/Tests/badge.svg)

针对日常[Spring Boot](https://spring.io/projects/spring-boot)框架下开发整理抽离的底层基础组件，不建议直接使用，通过其他组件引入使用

-  [Rice-Toolkit](https://github.com/NicheToolkit/rice-toolkit) 

-  [Socket-Toolkit](https://github.com/NicheToolkit/socket-toolkit) 

-  [Jts-Toolkit](https://github.com/NicheToolkit/jts-toolkit) 

-  [File-Toolkit](https://github.com/NicheToolkit/file-toolkit) 

## Release介绍

-  [Rest-Toolkit@1.0.3](https://github.com/NicheToolkit/rest-toolkit/tree/master/release/1.0.3.md)

-  [Rest-Toolkit@1.0.4](https://github.com/NicheToolkit/rest-toolkit/tree/master/release/1.0.4.md)

-  [Rest-Toolkit@1.0.5](https://github.com/NicheToolkit/rest-toolkit/tree/master/release/1.0.5.md)

### v1.0.5 Release

# [rest-toolkit-core](https://github.com/NicheToolkit/rest-toolkit/tree/master/rest-toolkit-core)

1、[RestArithmetic](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-core/src/main/java/io/github/nichetoolkit/rest/RestArithmetic.java)`RestPurview`重命名为`RestArithmetic`,新增`parseArithmetic`静态方法,`deconKey()`、`reachKey()`静态解析方法修复。

2、[DefaultResult](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-core/src/main/java/io/github/nichetoolkit/rest/DefaultResult.java)`DefaultResult.Builder<T,S>`新增`restStatus(RestStatus)`方法用于修复异常信息替换`RestResult`的`message`字段，`success`和`fail`静态方法调整传参。

3、[RestResult](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-core/src/main/java/io/github/nichetoolkit/rest/RestResult.java)`RestResult.Builder<T>`新增`restStatus(RestStatus)`方法，`ok`和`error`静态方法调整传参。静态方法`ok()`返回参数由`ResponseEntity<RestResult<T>>`

4、[LoginErrorException](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-core/src/main/java/io/github/nichetoolkit/rest/error/natives/LoginErrorException.java)新增`LoginErrorException`用户登录相关异常。

5、[RestErrorStatus](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-core/src/main/java/io/github/nichetoolkit/rest/RestErrorStatus.java)新增10320-10323用户登录相关状态码。

6、[FieldRepeatException](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-core/src/main/java/io/github/nichetoolkit/rest/error/often/FieldRepeatException.java)新增`FieldRepeatException`用户异常。

7、[DataDeleteException](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-core/src/main/java/io/github/nichetoolkit/rest/error/data/DataDeleteException.java)新增`DataDeleteException`异常。

8、`RestKey`、`RestValue`、`RestStatus`、`RestField`、`RestArithmetic`等枚举接口静态方法`Map`数据转换方法`Key`重复异常修复。


# [rest-toolkit-utils](https://github.com/NicheToolkit/rest-toolkit/tree/master/rest-toolkit-utils) 

1、[FileUtils](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-utils/src/main/java/io/github/nichetoolkit/rest/util/FileUtils.java)`suffix()`方法修复默认返回空字符串而不是`null`,`filename()`方法同时增加分隔符`.`安全校验。新增`mediaType`类型。

2、[PasswordWorker](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-utils/src/main/java/io/github/nichetoolkit/rest/worker/PasswordWorker.java)新增`PasswordWorker`密码校验器。

3、[Md5Worker](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-utils/src/main/java/io/github/nichetoolkit/rest/worker/Md5Worker.java)新增`PasswordWorker`密码校验器。

4、[Md5Worker](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-utils/src/main/java/io/github/nichetoolkit/rest/worker/Md5Worker.java)新增`PasswordWorker`密码校验器。

5、[GeneralUtils](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-utils/src/main/java/io/github/nichetoolkit/rest/util/GeneralUtils.java)`isNotEmpty`和`isEmpty`方法支持`BigDecimal`和`BigInteger`类型校验，数组对象校验修复。

6、[OptionalHelper](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-utils/src/main/java/io/github/nichetoolkit/rest/helper/OptionalHelper.java)新增`fieldRepeat()`静态校验方法。

7、[JsonHelper](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-utils/src/main/java/io/github/nichetoolkit/rest/helper/JsonHelper.java)新增`parseResult()`静态解析方法，支持将`Json`格式数据解析为`RestResult<String>`对象数据。

8、[DateUtils](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-utils/src/main/java/io/github/nichetoolkit/rest/util/DateUtils.java)新增`getDay`、`getMonth`、`getYear`等日期截取静态方法。


# [rest-toolkit-starter](https://github.com/NicheToolkit/rest-toolkit/tree/master/rest-toolkit-starter)

1、[RestRequestWrapper](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-starter/src/main/java/io/github/nichetoolkit/rest/interceptor/RestRequestWrapper.java)`RestNoteHandlerInterceptor`重命名为`RestRequestWrapper`。

2、[RestRequestHelper](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-starter/src/main/java/io/github/nichetoolkit/rest/worker/Md5Worker.java)`md5Encrypts`修复编码算法`SHA256`为`SHA-256`。

3、[RestContextHolder](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-starter/src/main/java/io/github/nichetoolkit/rest/helper/RestContextHolder.java)新增`RestContextHolder`静态辅助类用于获取`ApplicationContext`上下文。

4、[RestHandlerInterceptor](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-starter/src/main/java/io/github/nichetoolkit/rest/interceptor/RestHandlerInterceptor.java)完善请求日志记录。

4、[RestTemplates](https://github.com/NicheToolkit/rest-toolkit/blob/master/rest-toolkit-starter/src/main/java/io/github/nichetoolkit/rest/http/RestTemplates.java)`RestTemplates`工具类扩展完善。

## Report介绍 

-  [Repository SBOM Report@1.0.5](https://github.com/NicheToolkit/rest-toolkit/tree/master/report/1.0.5.md)

### Components 

6 components have vulnerabilities, which is 11% of all components.

###  Threats
11 threats found. 

Critical: 0% High: 27% Medium: 73% Low: 0% None: 0% Unknown: 0%

| Components                                                   |                                                              | Type | Occurrences | Threat |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ---- | ----------- | ------ |
| [pkg:maven/org.yaml/snakeyaml@1.29](https://ossindex.sonatype.org/component/pkg:maven/org.yaml/snakeyaml@1.29?utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |                                                              | T    | 3           | 1，4   |
|                                                              | [[CVE-2022-25857\] CWE-400: Uncontrolled Resource Consumption ('Resource Exhaustion')](https://ossindex.sonatype.org/vulnerability/CVE-2022-25857?component-type=maven&component-name=org.yaml%2Fsnakeyaml&utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |      |             |        |
|                                                              | [[CVE-2022-38749\] CWE-787: Out-of-bounds Write](https://ossindex.sonatype.org/vulnerability/CVE-2022-38749?component-type=maven&component-name=org.yaml%2Fsnakeyaml&utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |      |             |        |
|                                                              | [[CVE-2022-38751\] CWE-787: Out-of-bounds Write](https://ossindex.sonatype.org/vulnerability/CVE-2022-38751?component-type=maven&component-name=org.yaml%2Fsnakeyaml&utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |      |             |        |
|                                                              | [[CVE-2022-38752\] CWE-787: Out-of-bounds Write](https://ossindex.sonatype.org/vulnerability/CVE-2022-38752?component-type=maven&component-name=org.yaml%2Fsnakeyaml&utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |      |             |        |
|                                                              | [[CVE-2022-38750\] CWE-787: Out-of-bounds Write](https://ossindex.sonatype.org/vulnerability/CVE-2022-38750?component-type=maven&component-name=org.yaml%2Fsnakeyaml&utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |      |             |        |
| [pkg:maven/com.squareup.okhttp3/okhttp@4.8.1](https://ossindex.sonatype.org/component/pkg:maven/com.squareup.okhttp3/okhttp@4.8.1?utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |                                                              | D    | 1           | 1，1   |
|                                                              | [[CVE-2021-0341\] CWE-295: Improper Certificate Validation](https://ossindex.sonatype.org/vulnerability/CVE-2021-0341?component-type=maven&component-name=com.squareup.okhttp3%2Fokhttp&utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |      |             |        |
|                                                              | [[sonatype-2022-4262\] CWE-200: Information Exposure](https://ossindex.sonatype.org/vulnerability/sonatype-2022-4262?component-type=maven&component-name=com.squareup.okhttp3%2Fokhttp&utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |      |             |        |
| [pkg:maven/com.google.guava/guava@30.1.1-jre](https://ossindex.sonatype.org/component/pkg:maven/com.google.guava/guava@30.1.1-jre?utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |                                                              | D，T | 3           | 1      |
|                                                              | [[sonatype-2020-0926\] CWE-379: Creation of Temporary File in Directory with Incorrect Permissions](https://ossindex.sonatype.org/vulnerability/sonatype-2020-0926?component-type=maven&component-name=com.google.guava%2Fguava&utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |      |             |        |
| [pkg:maven/org.springframework/spring-beans@5.3.18](https://ossindex.sonatype.org/component/pkg:maven/org.springframework/spring-beans@5.3.18?utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |                                                              | T    | 3           | 1      |
|                                                              | [[CVE-2022-22970\] CWE-770: Allocation of Resources Without Limits or Throttling](https://ossindex.sonatype.org/vulnerability/CVE-2022-22970?component-type=maven&component-name=org.springframework%2Fspring-beans&utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |      |             |        |
| [pkg:maven/org.springframework/spring-context@5.3.18](https://ossindex.sonatype.org/component/pkg:maven/org.springframework/spring-context@5.3.18?utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |                                                              | T    | 3           | 1      |
|                                                              | [[CVE-2022-22968\] CWE-178: Improper Handling of Case Sensitivity](https://ossindex.sonatype.org/vulnerability/CVE-2022-22968?component-type=maven&component-name=org.springframework%2Fspring-context&utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |      |             |        |
| [pkg:maven/org.springframework/spring-web@5.3.18](https://ossindex.sonatype.org/component/pkg:maven/org.springframework/spring-web@5.3.18?utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |                                                              | T    | 3           | 1      |
|                                                              | [[CVE-2016-1000027\] CWE-502: Deserialization of Untrusted Data](https://ossindex.sonatype.org/vulnerability/CVE-2016-1000027?component-type=maven&component-name=org.springframework%2Fspring-web&utm_source=ossindex-client&utm_medium=integration&utm_content=1.7.0) |      |             |        |

## Maven Central

-  [Maven Central Repository](https://search.maven.org/search?q=g:io.github.nichetoolkit)

## 依赖环境
 > [Spring Boot](https://spring.io/projects/spring-boot) 2.6.6.RELEASE\
 > [Maven](https://maven.apache.org/) 3.6.0+\
 > [JDK](https://www.oracle.com/java/technologies/downloads/#java8) 1.8
 
## rest-toolkit-core
 * Maven (`pom.xml`)
```xml
  <dependency>
    <groupId>io.github.nichetoolkit</groupId>
    <artifactId>rest-toolkit-core</artifactId>
    <version>1.0.5</version>
  </dependency>
```  
 
## rest-toolkit-starter
 * Maven (`pom.xml`)
```xml
  <dependency>
    <groupId>io.github.nichetoolkit</groupId>
    <artifactId>rest-toolkit-starter</artifactId>
    <version>1.0.5</version>
  </dependency>
``` 

## rest-toolkit-utils
 * Maven (`pom.xml`)
```xml
  <dependency>
    <groupId>io.github.nichetoolkit</groupId>
    <artifactId>rest-toolkit-utils</artifactId>
    <version>1.0.5</version>
  </dependency>
```

## 使用方式

参考[rest-toolkit-test-web](https://github.com/NicheToolkit/rest-toolkit/tree/master/rest-toolkit-test-web)模块.
 
## License 

 [Apache License](https://www.apache.org/licenses/LICENSE-2.0)
 
## Dependencies

 [Spring Boot](https://github.com/spring-projects/spring-boot)
 
 [Guava](https://github.com/google/guava)
 
 [Lombok](https://github.com/projectlombok/lombok)
 
 [Okhttp](https://github.com/square/okhttp)
 
 [Httpclient](http://hc.apache.org/httpcomponents-client)
 
 [Fusionauth-jwt](https://github.com/FusionAuth/fusionauth-jwt)
 
 