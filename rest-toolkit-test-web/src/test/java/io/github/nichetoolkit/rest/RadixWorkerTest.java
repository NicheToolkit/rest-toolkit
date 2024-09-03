package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.worker.RadixWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * <code>RadixWorkerTest</code>
 * <p>The type radix worker test class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see Slf4j
 * @see SpringBootTest
 * @since Jdk1.8
 */
@Slf4j
@SpringBootTest
class RadixWorkerTest {

    /**
     * <code>radixWorker</code>
     * <p>the <code>radixWorker</code> field.</p>
     * @see Autowired
     */
    @Autowired
    private RadixWorker radixWorker;

    /**
     * <code>test</code>
     * <p>the method.</p>
     */
    @Test
    void test() {
        /* the generate long value as default subject  */
        Long defaultSubject = IdentityUtils.generateLong();
        log.info("the default subject: {}", defaultSubject);
        /* use radix worker to encrypt subject  */
        String encryptSubject = radixWorker.encrypt(defaultSubject);
        log.info("the encrypt subject: {}", encryptSubject);
        /* use radix worker to decrypt subject  */
        Long decryptSubject = radixWorker.decrypt(encryptSubject);
        log.info("the decrypt subject: {}", decryptSubject);
        Assertions.assertEquals(decryptSubject,defaultSubject);
    }
}