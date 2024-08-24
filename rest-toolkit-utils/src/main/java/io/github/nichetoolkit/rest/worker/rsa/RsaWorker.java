package io.github.nichetoolkit.rest.worker.rsa;

import io.github.nichetoolkit.rest.configure.RestRsaProperties;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
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

    private final RestRsaProperties rsaProperties;

    @Autowired
    public RsaWorker(RestRsaProperties rsaProperties) {
        this.rsaProperties = rsaProperties;
        RsaKey rsaKey = this.rsaProperties.toRsaKey();
        if (this.rsaProperties.isAutoVerify() && GeneralUtils.isNotEmpty(rsaKey)) {
            boolean verify = verify(rsaKey);
            if (!verify) {
                log.warn("the rsa key check mismatch, rsa key : \n {}", JsonUtils.parseJson(rsaKey));
            }
        }
    }

    @PostConstruct
    public void rsaWorkerInit() {
        log.debug("rsa properties: {}", JsonUtils.parseJson(this.rsaProperties));
        INSTANCE = this;
    }

    public RsaKey generate() {
        return generates(INSTANCE.rsaProperties.getKeySize());
    }

    public RsaKey generate(int keySize) {
        return generates(keySize);
    }

    public boolean verify(RsaKey rsaKey) {
        return verifies(rsaKey);
    }

    public boolean verify(String publicKeySecret, String privateKeySecret) {
        return verifies(publicKeySecret,privateKeySecret);
    }

    public boolean verify(PublicKey publicKey, PrivateKey privateKey) {
        return verifies(publicKey,privateKey);
    }

    public String encrypt(String source) {
        return encrypts(source, this.rsaProperties.getPublicKey());
    }

    public String encrypt(String source, String publicKey) {
        return encrypts(source, publicKey);
    }

    public String decrypt(String source) {
        return decrypts(source, INSTANCE.rsaProperties.getPrivateKey());
    }

    public String decrypt(String source, String privateKey) {
        return decrypts(source, privateKey);
    }

    public static boolean verifies(RsaKey rsaKey) {
        return verifies(rsaKey.getPublicKey(),rsaKey.getPrivateKey());
    }

    public static boolean verifies(String publicKeySecret, String privateKeySecret) {
        PublicKey publicKey = publicKey(publicKeySecret);
        PrivateKey privateKey = privateKey(privateKeySecret);
        return verifies(publicKey,privateKey);
    }

    public static boolean verifies(PublicKey publicKey, PrivateKey privateKey) {
        if (GeneralUtils.isEmpty(publicKey) || GeneralUtils.isEmpty(privateKey)) {
            return false;
        }
        /** 必须把私钥转成BC库里的RSA私钥对象，才是PKCS1标准的私钥形式，此时才能依据私钥获取一些理论算法中提及的各种密钥参数 */
        byte[] privateKeyBytes = privateKey(privateKey);
        /**
         * 这里getInstance只接受PKCS1的私钥转换成的byte数组，否则会报错
         * org.bouncycastle.asn1.DLSequence cannot be cast to org.bouncycastle.asn1.ASN1Integer
         * 所以需要先转换成PKCS1的私钥信息，才能输入进来 这里输入asn1Primitive也可以
         */
        RSAPrivateKey rsaPrivateKey = RSAPrivateKey.getInstance(privateKeyBytes);
        log.info("the rsa private key version: {}", rsaPrivateKey.getVersion());
        /** 从私钥中获取公钥的指数 */
        BigInteger privateExponent = rsaPrivateKey.getPublicExponent();
        if (privateExponent == null) {
            return false;
        }
        /** 从私钥中获取密钥对共用的模数 */
        BigInteger privateKeyModulus = rsaPrivateKey.getModulus();
        if (privateKeyModulus == null) {
            return false;
        }
        KeyFactory keyFactory = keyFactory(RSA_ALGORITHM);
        /** 把公钥加载成RSA公钥spec对象，以此获取公钥的指数 */
        try {
            RSAPublicKeySpec rsaPublicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
            /** 依据公钥获取公钥的指数 */
            BigInteger publicKeyExponent = rsaPublicKeySpec.getPublicExponent();
            /** 依据公钥获取密钥对共用的模数 */
            BigInteger publicKeyModulus = rsaPublicKeySpec.getModulus();
            /** 如果 利用私钥推算出的公钥的参数 和 公钥自身的参数 二者是一致的，说明这个公钥和这个私钥是匹配的，是一对的 */
            return privateExponent.equals(publicKeyExponent) && privateKeyModulus.equals(publicKeyModulus);
        } catch (InvalidKeySpecException exception) {
            log.error("the verify key is error !, error: {}", exception.getMessage());
        }
        return false;

    }


    public static byte[] privateKey(PrivateKey privateKey) {
        byte[] bytes = null;
        try {
            PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(privateKey.getEncoded());
            ASN1Encodable asn1Encodable = privateKeyInfo.parsePrivateKey();
            ASN1Primitive asn1Primitive = asn1Encodable.toASN1Primitive();
            bytes = asn1Primitive.getEncoded();
        } catch (IOException exception) {
            log.error("the private key to encode with 'ASN1' is error !, error: {}", exception.getMessage());
        }
        return bytes;
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

    public static String encrypts(String source) {
        String publicKey = INSTANCE.rsaProperties.getPublicKey();
        return encrypts(source, publicKey);
    }

    public static String decrypts(String source) {
        String privateKey = INSTANCE.rsaProperties.getPrivateKey();
        return decrypts(source, privateKey);
    }

    public static String decrypts(String source, String privateKeySecret) {
        PrivateKey privateKey = privateKey(privateKeySecret);
        return decrypts(source, privateKey, INSTANCE.rsaProperties.getKeySize() / 8);
    }

    public static String decrypts(String source, String privateKeySecret, int segmentSize) {
        PrivateKey privateKey = privateKey(privateKeySecret);
        return decrypts(source, privateKey, segmentSize);
    }

    public static KeyFactory keyFactory(String algorithm) {
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance(algorithm);
        } catch (NoSuchAlgorithmException exception) {
            log.error("the key factory of {} algorithm init  is error !, error: {}", algorithm, exception.getMessage());
        }
        return keyFactory;
    }

    public static PrivateKey privateKey(String privateKeySecret) {
        byte[] decodeBase64 = Base64.decodeBase64(privateKeySecret);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decodeBase64);
        PrivateKey privateKey = null;
        try {
            KeyFactory keyFactory = keyFactory(RSA_ALGORITHM);
            privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (InvalidKeySpecException exception) {
            log.error("the privateKey init is error !, error: {}", exception.getMessage());
        }
        return privateKey;
    }

    public static String decrypts(String source, PrivateKey privateKey, int segmentSize) {
        if (GeneralUtils.isEmpty(privateKey)) {
            return null;
        }
        byte[] targetBytes = null;
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            targetBytes = dofinal(cipher, source, segmentSize);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException exception) {
            log.error("the decrypts algorithm of RSA is error !, error: {}", exception.getMessage());
        }
        return Base64.encodeBase64String(targetBytes);
    }

    public static String encrypts(String source, String publicKeySecret) {
        PublicKey publicKey = publicKey(publicKeySecret);
        return encrypts(source, publicKey, INSTANCE.rsaProperties.getKeySize() / 8 - 11);
    }

    public static String encrypts(String source, String publicKeySecret, int segmentSize) {
        PublicKey publicKey = publicKey(publicKeySecret);
        return encrypts(source, publicKey, segmentSize);
    }

    public static PublicKey publicKey(String publicKeySecret) {
        byte[] decodeBase64 = Base64.decodeBase64(publicKeySecret);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decodeBase64);
        PublicKey publicKey = null;
        try {
            KeyFactory keyFactory = keyFactory(RSA_ALGORITHM);
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (InvalidKeySpecException exception) {
            log.error("the publicKey init is error !, error: {}", exception.getMessage());
        }
        return publicKey;
    }

    public static String encrypts(String source, PublicKey publicKey, int segmentSize) {
        if (GeneralUtils.isEmpty(publicKey)) {
            return null;
        }
        byte[] targetBytes = null;
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            targetBytes = dofinal(cipher, source, segmentSize);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException exception) {
            log.error("the encrypts algorithm of RSA is error !, error: {}", exception.getMessage());
        }
        return Base64.encodeBase64String(targetBytes);
    }

    private static byte[] dofinal(Cipher cipher, String source, int segmentSize) {
        byte[] targetBytes = null;
        byte[] sourceBytes = source.getBytes(StandardCharsets.UTF_8);
        try {
            if (segmentSize > 0) {
                targetBytes = segmentDofinal(cipher, sourceBytes, segmentSize);
            } else {
                targetBytes = cipher.doFinal(sourceBytes);
            }
        } catch (IllegalBlockSizeException | BadPaddingException exception) {
            log.error("the do final of RSA algorithm is error !, error: {}", exception.getMessage());
        }
        return targetBytes;
    }

    private static byte[] segmentDofinal(Cipher cipher, byte[] sourceBytes, int segmentSize) {
        int length = sourceBytes.length;
        int offSet = 0;
        byte[] bytesCache;
        int segment = 0;
        /** 对数据分段解密 */
        byte[] bytes = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            while (length - offSet > 0) {
                if (length - offSet > segmentSize) {
                    bytesCache = cipher.doFinal(sourceBytes, offSet, segmentSize);
                } else {
                    bytesCache = cipher.doFinal(sourceBytes, offSet, length - offSet);
                }
                outputStream.write(bytesCache, 0, bytesCache.length);
                segment++;
                offSet = segment * segmentSize;
            }
            bytes = outputStream.toByteArray();
        } catch (IOException | BadPaddingException | IllegalBlockSizeException exception) {
            log.error("the segment do final of RSA algorithm is error !, error: {}", exception.getMessage());
        }
        return bytes;
    }
}
