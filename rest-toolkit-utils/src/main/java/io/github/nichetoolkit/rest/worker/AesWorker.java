package io.github.nichetoolkit.rest.worker;

import io.github.nichetoolkit.rest.configure.RestAesProperties;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
public class AesWorker {

    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private static final String ALGORITHM = "AES";

    private final RestAesProperties aesProperties;

    private static AesWorker INSTANCE = null;

    public static AesWorker getInstance() {
        return INSTANCE;
    }

    @Autowired
    public AesWorker(RestAesProperties aesProperties) {
        this.aesProperties = aesProperties;
    }

    @PostConstruct
    public void radixWorkerInit() {
        log.debug("The aes      properties: {}", JsonUtils.parseJson(aesProperties));
        INSTANCE = this;
    }

    public String encrypt(String source) {
        return encrypts(source, aesProperties);
    }

    public String decrypt(String target) {
        return decrypts(target, aesProperties);
    }

    private static synchronized String encrypts(String source, RestAesProperties properties) {
        return encrypts(source, properties.getSecretKey(), properties.getSecretIv());
    }

    public static synchronized String encrypts(String source) {
        return encrypts(source, INSTANCE.aesProperties.getSecretKey(), INSTANCE.aesProperties.getSecretIv());
    }

    public static synchronized String encrypts(String source, String secretIv) {
        return encrypts(source, INSTANCE.aesProperties.getSecretKey(), secretIv);
    }

    public static synchronized String encrypts(String source, String secretKey, String secretIv) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(secretIv.getBytes());
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encryptedBytes = cipher.doFinal(source.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                 | InvalidAlgorithmParameterException | BadPaddingException | InvalidKeyException exception) {
            log.error("the encrypts algorithm of aes is error !, error: {}", exception.getMessage());
        }
        return null;
    }

    private static synchronized String decrypts(String target, RestAesProperties properties) {
        return decrypts(target, properties.getSecretKey(), properties.getSecretIv());
    }

    public static synchronized String decrypts(String target) {
        return decrypts(target, INSTANCE.aesProperties.getSecretKey(), INSTANCE.aesProperties.getSecretIv());
    }

    public static synchronized String decrypts(String target, String secretIv) {
        return decrypts(target, INSTANCE.aesProperties.getSecretKey(), secretIv);
    }

    public static synchronized String decrypts(String target, String secretKey, String secretIv) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(secretIv.getBytes());
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] decodedBytes = Base64.getDecoder().decode(target);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                 | InvalidAlgorithmParameterException | BadPaddingException | InvalidKeyException exception) {
            log.error("the decrypts algorithm of aes is error !, error: {}", exception.getMessage());
        }
        return null;
    }
}
