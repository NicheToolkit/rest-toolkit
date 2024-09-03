package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.sha.ShaWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class ShaWorkerTest{

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
        Assertions.assertNotEquals(password,encrypt);
    }
}