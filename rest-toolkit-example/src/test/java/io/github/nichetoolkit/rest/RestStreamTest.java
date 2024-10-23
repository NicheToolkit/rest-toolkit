package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.stream.RestStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * <code>RestStreamTest</code>
 * <p>The rest stream test class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.test.context.SpringBootTest
 * @since Jdk1.8
 */
@Slf4j
@SpringBootTest
class RestStreamTest {


    /**
     * <code>test</code>
     * <p>The test method.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.junit.jupiter.api.Test
     * @see io.github.nichetoolkit.rest.RestException
     */
    @Test
    void test() throws RestException {
        Integer[] testArray = {1,5,3,2,3,4};
        RestOptional<Integer> anyFirst = RestStream.stream(testArray).findAny(value -> value > 2);
        anyFirst.ifNullPresent(value -> System.out.println(">: " + value));
    }
}