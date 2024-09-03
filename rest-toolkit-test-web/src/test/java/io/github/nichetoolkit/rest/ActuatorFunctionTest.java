package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.actuator.AnchorActuator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class ActuatorFunctionTest {

    void handle(AnchorActuator before, AnchorActuator after, AnchorActuator over) throws RestException{
        before.actuate();
        log.info("the handle method invoke!");
        after.actuate();
        over.actuate();
    }

    @Test
    void test() throws RestException {
        handle(
                /* the before method */
                () -> {
                    log.info("the before handle method invoke!");
                },
                /* the after method */
                () -> {
                    log.info("the after handle method invoke!");
                },
                /* the over method */
                () -> {
                    throw new RestException();
                }
        );
    }
}