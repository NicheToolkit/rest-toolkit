# Actuator & Function

the function interface with `@FunctionalInterface` in jdk1.8 is not allow to be throw exception, the actuator interface
with `@FunctionalInterface` is as the function interface but throw rest exception.

|              rest               |       jdk1.8        |
|:-------------------------------:|:-------------------:|
|         AnchorActuator          |                     |
|    BiConsumerActuator<T, U>     |  BiConsumer<T, U>   |
|   BiFunctionActuator<T, U, R>   | BiFunction<T, U, R> |
|    BinaryOperatorActuator<T>    |  BinaryOperator<T>  |
|    BiPredicateActuator<T, U>    |  BiPredicate<T, U>  |
|      ComparatorActuator<T>      |    Comparator<T>    |
|       ConsumerActuator<T>       |     Consumer<T>     |
|      FunctionActuator<T,R>      |   Function<T, R>    |
|      PredicateActuator<T>       |    Predicate<T>     |
|       SupplierActuator<T>       |     Supplier<T>     |
|  MapConsumerActuator<T, U, S>   |                     |
| MapFunctionActuator<T, U, S, R> |                     |
|  MapPredicateActuator<T, U, S>  |                     |

* examples

```java

@Slf4j
@SpringBootTest
class ActuatorFunctionTest {

    void handle(AnchorActuator before, AnchorActuator after, AnchorActuator over) throws RestException {
        before.actuate();
        log.info("the handle method invoke!");
        after.actuate();
        over.actuate();
    }

    @Test
    void test() throws RestException {
        handle(
                /* the before method */
                () -> log.info("the before handle method invoke!"),
                /* the after method */
                () -> log.info("the after handle method invoke!"),
                /* the over method */
                () -> {
                    throw new RestException();
                }
        );
    }
}
```

# Notelog & Userlog

* default annotation

|  annotation   |                  target                  |                         description                          |
| :-----------: | :--------------------------------------: | :----------------------------------------------------------: |
| `RestLogging` | `ElementType.METHOD`、`ElementType.TYPE` | the annotation is used to annotate `controller ` or `method` that need to mark `loggingKey` and `loggingValue`. |
| `RestNotelog` |            `ElementType.TYPE`            | the annotation is used to annotate `controller` that need to mark `notelog`. |
| `RestUserlog` |           `ElementType.METHOD`           | the annotation is used to annotate `controller` that need to mark `userlog`. |

you can use `@RestLogging`、`@RestNotelog` and `@RestUserlog` to obtain infos (`request` 、`response `、`usernote`)
freely .

* `RestUsernoteAdvice` interface implement

```java

@Slf4j
@Service
public class RestUsernoteService implements RestUsernoteAdvice {
    @Override
    public void doUsernoteHandle(@NonNull RestRequestPack requestPack, @NonNull RestResponsePack responsePack, @NonNull RestUsernotePack usernotePack) {
        log.info("the request pack: {}", JsonUtils.parseJson(requestPack));
        log.info("the response pack: {}", JsonUtils.parseJson(responsePack));
        log.info("the usernote pack: {}", JsonUtils.parseJson(usernotePack));
    }
}
```

* annotation usages

```java

@Slf4j
@RestController
@RestNotelog(loggingKey = "logging", notelog = "rest notelog")
@RequestMapping("/rest/logging")
public class RestLoggingController {

    @GetMapping("/test")
    @RestUserlog(loggingValue = "logging test", userlog = "rest userlog")
    public RestResult<?> test() throws RestException {
        return RestResult.success();
    }
}
```

* test request

> GET http://localhost:8080/rest/logging/test

* pack infos

> request pack

```json
{
  "ipAddress": "127.0.0.1",
  "userAgent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0",
  "method": "GET",
  "url": "http://localhost:8080/rest/logging"
}
```

> response pack

```json
{
  "time": 1725401067616,
  "startTime": 1725401067616,
  "endTime": 1725401067619,
  "costTime": 3,
  "status": 200,
  "message": "\"成功\"",
  "method": "test",
  "mediaType": "application/json",
  "result": "{\"status\":200,\"message\":\"成功\",\"time\":\"2024-09-04 06:04:27\"}",
  "resultString": "{\"status\":200,\"message\":\"成功\",\"time\":\"2024-09-04 06:04:27\"}",
  "restResult": {
    "status": 200,
    "message": "\"成功\"",
    "time": "2024-09-04 06:04:27"
  },
  "success": true
}
```

> usernote pack

```json
{
  "notelog": "rest notelog",
  "userlog": "rest userlog",
  "loggingKey": "logging",
  "loggingValue": "logging test",
  "loggingType": "test"
}
```