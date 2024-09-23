package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.stream.RestStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;


@Slf4j
@SpringBootTest
class RestStreamTest {


    @Test
    void test() throws RestException {
        Integer[] testArray = {1,5,3,2,3,4};
        RestOptional<Integer> anyFirst = RestStream.stream(testArray).findAny(value -> value > 2);
        anyFirst.ifNullPresent(value -> System.out.println(">: " + value));
    }
}