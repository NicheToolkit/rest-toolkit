package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.rsa.RsaKey;
import io.github.nichetoolkit.rest.worker.rsa.RsaWorker;
import org.apache.commons.codec.binary.Base64;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;


/**
 * <code>RsaWorkerTest</code>
 * <p>The type rsa worker test class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.test.context.SpringBootTest
 * @since Jdk1.8
 */
@Slf4j
@SpringBootTest
class RsaWorkerTest {

    /**
     * <code>rsaWorker</code>
     * {@link io.github.nichetoolkit.rest.worker.rsa.RsaWorker} <p>The <code>rsaWorker</code> field.</p>
     * @see io.github.nichetoolkit.rest.worker.rsa.RsaWorker
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    private RsaWorker rsaWorker;

    /**
     * <code>rsaKey</code>
     * <p>The key method.</p>
     * @see org.junit.jupiter.api.Test
     */
    @Test
    void rsaKey() {
        RsaKey rsaKey = rsaWorker.generate();
        log.info("the publicKey: {}", rsaKey.getPublicKey());
        log.info("the privateKey: {}", rsaKey.getPrivateKey());
    }

    /**
     * <code>test</code>
     * <p>The method.</p>
     * @see org.junit.jupiter.api.Test
     */
    @Test
    void test() {
        /* the uuid as subject  */
        String subject = GeneralUtils.uuid();
        byte[] subjectBytes = subject.getBytes(StandardCharsets.UTF_8);
        /* use Base64 encode subject bytes as default subject  */
        String defaultSubject = Base64.encodeBase64String(subjectBytes);
        log.info("the default subject: {}", defaultSubject);
        /* use rsa worker to encrypt subject  */
        String encryptSubject = rsaWorker.encrypt(defaultSubject);
        log.info("the encrypt subject: {}", encryptSubject);
        /* use Base64 to decode subject  */
        byte[] decodeSubjectBytes = Base64.decodeBase64(encryptSubject);
        /* use rsa worker to decrypt subject  */
        String base64Decrypt = rsaWorker.decrypt(decodeSubjectBytes);
        byte[] decryptBytes = Base64.decodeBase64(base64Decrypt);
        String decryptSubject = new String(decryptBytes);
        log.info("the decrypt subject: {}", decryptSubject);
        Assertions.assertEquals(decryptSubject,defaultSubject);
    }
}