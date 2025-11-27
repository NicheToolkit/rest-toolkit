package io.github.nichetoolkit.rest.worker;

import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.configure.RestAesProperties;
import io.github.nichetoolkit.rest.error.lack.ConfigureLackError;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

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

/**
 * <code>AesWorker</code>
 * <p>The aes worker class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class AesWorker {

    /**
     * <code>TRANSFORMATION</code>
     * {@link java.lang.String} <p>The constant <code>TRANSFORMATION</code> field.</p>
     * @see java.lang.String
     */
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    /**
     * <code>ALGORITHM</code>
     * {@link java.lang.String} <p>The constant <code>ALGORITHM</code> field.</p>
     * @see java.lang.String
     */
    private static final String ALGORITHM = "AES";

    /**
     * <code>aesProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestAesProperties} <p>The <code>aesProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestAesProperties
     */
    private final RestAesProperties aesProperties;

    /**
     * <code>INSTANCE</code>
     * {@link io.github.nichetoolkit.rest.worker.AesWorker} <p>The constant <code>INSTANCE</code> field.</p>
     */
    private static AesWorker INSTANCE = null;

    /**
     * <code>AesWorker</code>
     * <p>Instantiates a new aes worker.</p>
     * @param aesProperties {@link io.github.nichetoolkit.rest.configure.RestAesProperties} <p>The aes properties parameter is <code>RestAesProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestAesProperties
     */
    public AesWorker(RestAesProperties aesProperties) {
        this.aesProperties = aesProperties;
    }

    /**
     * <code>radixWorkerInit</code>
     * <p>The radix worker init method.</p>
     * @see javax.annotation.PostConstruct
     */
    @PostConstruct
    public void radixWorkerInit() {
        log.debug("The aes      properties: {}", JsonUtils.parseJson(aesProperties));
        INSTANCE = this;
    }

    /**
     * <code>instance</code>
     * <p>The instance method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.AesWorker} <p>The instance return object is <code>AesWorker</code> type.</p>
     */
    public static AesWorker instance() {
        return RestOptional.ofNullable(INSTANCE).orNullThrow(ConfigureLackError::new);
    }

    /**
     * <code>encrypt</code>
     * <p>The encrypt method.</p>
     * @param source {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypt return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String encrypt(String source) {
        return encrypts(source, aesProperties);
    }

    /**
     * <code>decrypt</code>
     * <p>The decrypt method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypt return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String decrypt(String target) {
        return decrypts(target, aesProperties);
    }

    /**
     * <code>encrypts</code>
     * <p>The encrypts method.</p>
     * @param source     {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param properties {@link io.github.nichetoolkit.rest.configure.RestAesProperties} <p>The properties parameter is <code>RestAesProperties</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.configure.RestAesProperties
     */
    private static synchronized String encrypts(String source, RestAesProperties properties) {
        return encrypts(source, properties.getSecretKey(), properties.getSecretIv());
    }

    /**
     * <code>encrypts</code>
     * <p>The encrypts method.</p>
     * @param source {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static synchronized String encrypts(String source) {
        return encrypts(source, instance().aesProperties.getSecretKey(), instance().aesProperties.getSecretIv());
    }

    /**
     * <code>encrypts</code>
     * <p>The encrypts method.</p>
     * @param source   {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param secretIv {@link java.lang.String} <p>The secret iv parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static synchronized String encrypts(String source, String secretIv) {
        return encrypts(source, instance().aesProperties.getSecretKey(), secretIv);
    }

    /**
     * <code>encrypts</code>
     * <p>The encrypts method.</p>
     * @param source    {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param secretKey {@link java.lang.String} <p>The secret key parameter is <code>String</code> type.</p>
     * @param secretIv  {@link java.lang.String} <p>The secret iv parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
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

    /**
     * <code>decrypts</code>
     * <p>The decrypts method.</p>
     * @param target     {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param properties {@link io.github.nichetoolkit.rest.configure.RestAesProperties} <p>The properties parameter is <code>RestAesProperties</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.configure.RestAesProperties
     */
    private static synchronized String decrypts(String target, RestAesProperties properties) {
        return decrypts(target, properties.getSecretKey(), properties.getSecretIv());
    }

    /**
     * <code>decrypts</code>
     * <p>The decrypts method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static synchronized String decrypts(String target) {
        return decrypts(target, instance().aesProperties.getSecretKey(), instance().aesProperties.getSecretIv());
    }

    /**
     * <code>decrypts</code>
     * <p>The decrypts method.</p>
     * @param target   {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param secretIv {@link java.lang.String} <p>The secret iv parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static synchronized String decrypts(String target, String secretIv) {
        return decrypts(target, instance().aesProperties.getSecretKey(), secretIv);
    }

    /**
     * <code>decrypts</code>
     * <p>The decrypts method.</p>
     * @param target    {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @param secretKey {@link java.lang.String} <p>The secret key parameter is <code>String</code> type.</p>
     * @param secretIv  {@link java.lang.String} <p>The secret iv parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
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
