package io.github.nichetoolkit.rest.worker.rsa;

import io.github.nichetoolkit.rest.configure.RestRsaProperties;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * <p>SecurityWorker</p>
 *
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class RsaWorker {
    private static final String RSA_ALGORITHM = "RSA";

    private static RsaWorker INSTANCE = null;

    public static RsaWorker getInstance() {
        return INSTANCE;
    }

    private RestRsaProperties rsaProperties;

    @Autowired
    public RsaWorker(RestRsaProperties rsaProperties) {
        this.rsaProperties = rsaProperties;
    }

    @PostConstruct
    public void rsaWorkerInit() {
        log.debug("rsa properties: {}", JsonUtils.parseJson(this.rsaProperties));
        INSTANCE = this;
    }

    public RsaKey generate() {
        return generates(INSTANCE.rsaProperties.getKeySize());
    }

    public static RsaKey generates() {
        return generates(INSTANCE.rsaProperties.getKeySize());
    }

    public static RsaKey generates(int keySize) {
        RsaKey rsaKey = new RsaKey();
        try {
            /** 为RSA算法创建一个KeyPairGenerator对象（KeyPairGenerator，密钥对生成器，用于生成公钥和私钥对） */
            KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            /** 初始化KeyPairGenerator对象,密钥长度 */
            pairGenerator.initialize(keySize);
            /** 生成密匙对 */
            KeyPair keyPair = pairGenerator.generateKeyPair();
            Key publicKeySecret = keyPair.getPublic();
            /** publicKey经过二次加密后的字符串 */
            byte[] publicKeyEncoded = publicKeySecret.getEncoded();
            String publicKey = Base64.encodeBase64URLSafeString(publicKeyEncoded);
            rsaKey.setPrivateKey(publicKey);
            Key privateKeySecret = keyPair.getPrivate();
            /** privateKey经过二次加密后的字符串 */
            byte[] privateKeyEncoded = privateKeySecret.getEncoded();
            String privateKey = Base64.encodeBase64URLSafeString(privateKeyEncoded);
            rsaKey.setPrivateKey(privateKey);
        } catch (NoSuchAlgorithmException exception) {
            rsaKey = null;
            log.error("the generate algorithm of rsa is error !, error: {}", exception.getMessage());
        }
        return rsaKey;
    }

    public String encrypt(String source) {
        return encrypts(source, this.rsaProperties.getPublicKey());
    }

    public String decrypt(String source) {
        return encrypts(source, INSTANCE.rsaProperties.getPrivateKey());
    }

    public static String encrypts(String source) {
        String publicKey = INSTANCE.rsaProperties.getPublicKey();
        return encrypts(source, publicKey);
    }

    public static String decrypts(String source) {
        String privateKey = INSTANCE.rsaProperties.getPrivateKey();
        return encrypts(source, privateKey);
    }

    public static String decrypts(String source, String privateKey) {
        byte[] decoded = Base64.decodeBase64(privateKey);

        byte[] bytes = null;
        try {
            RSAPrivateKey privateKeySecret = (RSAPrivateKey) KeyFactory.getInstance(RSA_ALGORITHM)
                    .generatePrivate(new PKCS8EncodedKeySpec(decoded));
            log.debug("privateKey: {}", privateKey);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKeySecret);
            bytes = cipher.doFinal(source.getBytes(StandardCharsets.UTF_8));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | InvalidKeyException exception) {
            log.error("the decrypts algorithm of RSA is error !, error: {}", exception.getMessage());
        }
        return Base64.encodeBase64String(bytes);
    }

    public static String encrypts(String source, String publicKey) {
        byte[] decoded = Base64.decodeBase64(publicKey);
        byte[] bytes = null;
        try {
            RSAPublicKey publicKeySecret = (RSAPublicKey) KeyFactory.getInstance(RSA_ALGORITHM)
                    .generatePublic(new X509EncodedKeySpec(decoded));
            log.debug("publicKey: {}", publicKey);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKeySecret);
            bytes = cipher.doFinal(source.getBytes(StandardCharsets.UTF_8));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException |
                NoSuchPaddingException exception) {
            log.error("the encrypts algorithm of RSA is error !, error: {}", exception.getMessage());
        }
        return Base64.encodeBase64String(bytes);
    }
}
