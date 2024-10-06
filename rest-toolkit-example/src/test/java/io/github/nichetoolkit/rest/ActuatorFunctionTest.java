package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.actuator.AnchorActuator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * <code>ActuatorFunctionTest</code>
 * <p>The type actuator function test class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.test.context.SpringBootTest
 * @since Jdk1.8
 */
@Slf4j
@SpringBootTest
class ActuatorFunctionTest {

    /**
     * <code>handle</code>
     * <p>The method.</p>
     * @param before {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>The before parameter is <code>AnchorActuator</code> type.</p>
     * @param after  {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>The after parameter is <code>AnchorActuator</code> type.</p>
     * @param over   {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>The over parameter is <code>AnchorActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    void handle(AnchorActuator before, AnchorActuator after, AnchorActuator over) throws RestException{
        before.actuate();
        log.info("the handle method invoke!");
        after.actuate();
        over.actuate();
    }

    /**
     * <code>test</code>
     * <p>The method.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.junit.jupiter.api.Test
     * @see io.github.nichetoolkit.rest.RestException
     */
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