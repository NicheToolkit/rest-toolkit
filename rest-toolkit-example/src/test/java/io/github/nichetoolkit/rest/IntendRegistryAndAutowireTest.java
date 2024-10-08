package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.holder.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class IntendRegistryAndAutowireTest {

    @Test
    void test() throws RestException {
        TestIntend bean = ApplicationContextHolder.beanOfType(TestIntend.class);
        System.out.println(bean.getExceptionProperties());
    }
}