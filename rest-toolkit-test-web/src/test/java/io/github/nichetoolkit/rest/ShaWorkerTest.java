package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.sha.ShaWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * <code>ShaWorkerTest</code>
 * <p>The type sha worker test class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.test.context.SpringBootTest
 * @since Jdk1.8
 */
@Slf4j
@SpringBootTest
class ShaWorkerTest{

    /**
     * <code>shaWorker</code>
     * {@link io.github.nichetoolkit.rest.worker.sha.ShaWorker} <p>the <code>shaWorker</code> field.</p>
     * @see io.github.nichetoolkit.rest.worker.sha.ShaWorker
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    private ShaWorker shaWorker;

    /**
     * <code>test</code>
     * <p>the method.</p>
     * @see org.junit.jupiter.api.Test
     */
    @Test
    void test() {
        /* the uuid as default password  */
        String password = GeneralUtils.uuid();
        log.info("the default password: {}", password);
        /* use sha worker to encrypt password  */
        String encrypt = shaWorker.encrypt(password);
        log.info("the encrypt password: {}", encrypt);
        Assertions.assertNotEquals(password,encrypt);
    }
}