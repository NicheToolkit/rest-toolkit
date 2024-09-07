package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.actuator.SupplierActuator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Supplier;

@Slf4j
@SpringBootTest
public class RestStreamTest {
    @Test
    void test() {

        Supplier<String> supplier = () -> "<>";
        SupplierActuator<String> supplier1 = (SupplierActuator<String>) supplier;

    }
}
