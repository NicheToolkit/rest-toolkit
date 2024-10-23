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
 * <code>RsaWorker</code>
 * <p>The rsa worker class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class RsaWorker {
    /**
     * <code>RSA_ALGORITHM</code>
     * {@link java.lang.String} <p>The constant <code>RSA_ALGORITHM</code> field.</p>
     * @see java.lang.String
     */
    private static final String RSA_ALGORITHM = "RSA";

    /**
     * <code>INSTANCE</code>
     * {@link io.github.nichetoolkit.rest.worker.rsa.RsaWorker} <p>The constant <code>INSTANCE</code> field.</p>
     */
    private static RsaWorker INSTANCE = null;

    /**
     * <code>getInstance</code>
     * <p>The get instance getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.rsa.RsaWorker} <p>The get instance return object is <code>RsaWorker</code> type.</p>
     */
    public static RsaWorker getInstance() {
        return INSTANCE;
    }

    /**
     * <code>rsaProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestRsaProperties} <p>The <code>rsaProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestRsaProperties
     */
    private final RestRsaProperties rsaProperties;

    /**
     * <code>RsaWorker</code>
     * <p>Instantiates a new rsa worker.</p>
     * @param rsaProperties {@link io.github.nichetoolkit.rest.configure.RestRsaProperties} <p>The rsa properties parameter is <code>RestRsaProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestRsaProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
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

    /**
     * <code>rsaWorkerInit</code>
     * <p>The rsa worker init method.</p>
     * @see javax.annotation.PostConstruct
     */
    @PostConstruct
    public void rsaWorkerInit() {
        log.debug("The rsa        properties: {}", JsonUtils.parseJson(this.rsaProperties));
        INSTANCE = this;
    }

    /**
     * <code>generate</code>
     * <p>The generate method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.rsa.RsaKey} <p>The generate return object is <code>RsaKey</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.rsa.RsaKey
     */
    public RsaKey generate() {
        return generates(INSTANCE.rsaProperties.getKeySize());
    }

    /**
     * <code>generate</code>
     * <p>The generate method.</p>
     * @param keySize int <p>The key size parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.rsa.RsaKey} <p>The generate return object is <code>RsaKey</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.rsa.RsaKey
     */
    public RsaKey generate(int keySize) {
        return generates(keySize);
    }

    /**
     * <code>verify</code>
     * <p>The verify method.</p>
     * @param rsaKey {@link io.github.nichetoolkit.rest.worker.rsa.RsaKey} <p>The rsa key parameter is <code>RsaKey</code> type.</p>
     * @return boolean <p>The verify return object is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.rsa.RsaKey
     */
    public boolean verify(RsaKey rsaKey) {
        return verifies(rsaKey);
    }

    /**
     * <code>verify</code>
     * <p>The verify method.</p>
     * @param publicKeySecret  {@link java.lang.String} <p>The public key secret parameter is <code>String</code> type.</p>
     * @param privateKeySecret {@link java.lang.String} <p>The private key secret parameter is <code>String</code> type.</p>
     * @return boolean <p>The verify return object is <code>boolean</code> type.</p>
     * @see java.lang.String
     */
    public boolean verify(String publicKeySecret, String privateKeySecret) {
        return verifies(publicKeySecret, privateKeySecret);
    }

    /**
     * <code>verify</code>
     * <p>The verify method.</p>
     * @param publicKey  {@link java.security.PublicKey} <p>The public key parameter is <code>PublicKey</code> type.</p>
     * @param privateKey {@link java.security.PrivateKey} <p>The private key parameter is <code>PrivateKey</code> type.</p>
     * @return boolean <p>The verify return object is <code>boolean</code> type.</p>
     * @see java.security.PublicKey
     * @see java.security.PrivateKey
     */
    public boolean verify(PublicKey publicKey, PrivateKey privateKey) {
        return verifies(publicKey, privateKey);
    }

    /**
     * <code>encrypt</code>
     * <p>The encrypt method.</p>
     * @param source {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypt return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String encrypt(String source) {
        return encrypts(source, this.rsaProperties.getPublicKey());
    }

    /**
     * <code>encrypt</code>
     * <p>The encrypt method.</p>
     * @param sourceBytes byte <p>The source bytes parameter is <code>byte</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypt return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String encrypt(byte[] sourceBytes) {
        return encrypts(sourceBytes, this.rsaProperties.getPublicKey());
    }

    /**
     * <code>encrypt</code>
     * <p>The encrypt method.</p>
     * @param source    {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param publicKey {@link java.lang.String} <p>The public key parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypt return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String encrypt(String source, String publicKey) {
        return encrypts(source, publicKey);
    }


    /**
     * <code>encrypt</code>
     * <p>The encrypt method.</p>
     * @param sourceBytes byte <p>The source bytes parameter is <code>byte</code> type.</p>
     * @param publicKey   {@link java.lang.String} <p>The public key parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypt return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String encrypt(byte[] sourceBytes, String publicKey) {
        return encrypts(sourceBytes, publicKey);
    }

    /**
     * <code>decrypt</code>
     * <p>The decrypt method.</p>
     * @param source {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypt return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String decrypt(String source) {
        return decrypts(source, INSTANCE.rsaProperties.getPrivateKey());
    }

    /**
     * <code>decrypt</code>
     * <p>The decrypt method.</p>
     * @param sourceBytes byte <p>The source bytes parameter is <code>byte</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypt return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String decrypt(byte[] sourceBytes) {
        return decrypts(sourceBytes, INSTANCE.rsaProperties.getPrivateKey());
    }

    /**
     * <code>decrypt</code>
     * <p>The decrypt method.</p>
     * @param source     {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param privateKey {@link java.lang.String} <p>The private key parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypt return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String decrypt(String source, String privateKey) {
        return decrypts(source, privateKey);
    }

    /**
     * <code>decrypt</code>
     * <p>The decrypt method.</p>
     * @param sourceBytes byte <p>The source bytes parameter is <code>byte</code> type.</p>
     * @param privateKey  {@link java.lang.String} <p>The private key parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypt return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String decrypt(byte[] sourceBytes, String privateKey) {
        return decrypts(sourceBytes, privateKey);
    }

    /**
     * <code>verifies</code>
     * <p>The verifies method.</p>
     * @param rsaKey {@link io.github.nichetoolkit.rest.worker.rsa.RsaKey} <p>The rsa key parameter is <code>RsaKey</code> type.</p>
     * @return boolean <p>The verifies return object is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.rsa.RsaKey
     */
    public static boolean verifies(RsaKey rsaKey) {
        return verifies(rsaKey.getPublicKey(), rsaKey.getPrivateKey());
    }

    /**
     * <code>verifies</code>
     * <p>The verifies method.</p>
     * @param publicKeySecret  {@link java.lang.String} <p>The public key secret parameter is <code>String</code> type.</p>
     * @param privateKeySecret {@link java.lang.String} <p>The private key secret parameter is <code>String</code> type.</p>
     * @return boolean <p>The verifies return object is <code>boolean</code> type.</p>
     * @see java.lang.String
     */
    public static boolean verifies(String publicKeySecret, String privateKeySecret) {
        PublicKey publicKey = publicKey(publicKeySecret);
        PrivateKey privateKey = privateKey(privateKeySecret);
        return verifies(publicKey, privateKey);
    }

    /**
     * <code>verifies</code>
     * <p>The verifies method.</p>
     * @param publicKey  {@link java.security.PublicKey} <p>The public key parameter is <code>PublicKey</code> type.</p>
     * @param privateKey {@link java.security.PrivateKey} <p>The private key parameter is <code>PrivateKey</code> type.</p>
     * @return boolean <p>The verifies return object is <code>boolean</code> type.</p>
     * @see java.security.PublicKey
     * @see java.security.PrivateKey
     */
    public static boolean verifies(PublicKey publicKey, PrivateKey privateKey) {
        if (GeneralUtils.isEmpty(publicKey) || GeneralUtils.isEmpty(privateKey)) {
            return false;
        }
        /* 必须把私钥转成BC库里的RSA私钥对象，才是PKCS1标准的私钥形式，此时才能依据私钥获取一些理论算法中提及的各种密钥参数 */
        byte[] privateKeyBytes = privateKey(privateKey);
        /*
         * 这里getInstance只接受PKCS1的私钥转换成的byte数组，否则会报错
         * org.bouncycastle.asn1.DLSequence cannot be cast to org.bouncycastle.asn1.ASN1Integer
         * 所以需要先转换成PKCS1的私钥信息，才能输入进来 这里输入asn1Primitive也可以
         */
        RSAPrivateKey rsaPrivateKey = RSAPrivateKey.getInstance(privateKeyBytes);
        /* 从私钥中获取公钥的指数 */
        BigInteger privateExponent = rsaPrivateKey.getPublicExponent();
        if (privateExponent == null) {
            return false;
        }
        /* 从私钥中获取密钥对共用的模数 */
        BigInteger privateKeyModulus = rsaPrivateKey.getModulus();
        if (privateKeyModulus == null) {
            return false;
        }
        KeyFactory keyFactory = keyFactory(RSA_ALGORITHM);
        /* 把公钥加载成RSA公钥spec对象，以此获取公钥的指数 */
        try {
            RSAPublicKeySpec rsaPublicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
            /* 依据公钥获取公钥的指数 */
            BigInteger publicKeyExponent = rsaPublicKeySpec.getPublicExponent();
            /* 依据公钥获取密钥对共用的模数 */
            BigInteger publicKeyModulus = rsaPublicKeySpec.getModulus();
            /* 如果 利用私钥推算出的公钥的参数 和 公钥自身的参数 二者是一致的，说明这个公钥和这个私钥是匹配的，是一对的 */
            return privateExponent.equals(publicKeyExponent) && privateKeyModulus.equals(publicKeyModulus);
        } catch (InvalidKeySpecException exception) {
            log.error("the verify key is error !, error: {}", exception.getMessage());
        }
        return false;

    }


    /**
     * <code>privateKey</code>
     * <p>The private key method.</p>
     * @param privateKey {@link java.security.PrivateKey} <p>The private key parameter is <code>PrivateKey</code> type.</p>
     * @return byte <p>The private key return object is <code>byte</code> type.</p>
     * @see java.security.PrivateKey
     */
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

    /**
     * <code>generates</code>
     * <p>The generates method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.rsa.RsaKey} <p>The generates return object is <code>RsaKey</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.rsa.RsaKey
     */
    public static RsaKey generates() {
        return generates(INSTANCE.rsaProperties.getKeySize());
    }

    /**
     * <code>generates</code>
     * <p>The generates method.</p>
     * @param keySize int <p>The key size parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.rsa.RsaKey} <p>The generates return object is <code>RsaKey</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.rsa.RsaKey
     */
    public static RsaKey generates(int keySize) {
        RsaKey rsaKey = new RsaKey();
        try {
            /* 为RSA算法创建一个KeyPairGenerator对象（KeyPairGenerator，密钥对生成器，用于生成公钥和私钥对） */
            KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            /* 初始化KeyPairGenerator对象,密钥长度 */
            pairGenerator.initialize(keySize);
            /* 生成密匙对 */
            KeyPair keyPair = pairGenerator.generateKeyPair();
            Key publicKeySecret = keyPair.getPublic();
            /* publicKey经过二次加密后的字符串 */
            byte[] publicKeyEncoded = publicKeySecret.getEncoded();
            String publicKey = Base64.encodeBase64URLSafeString(publicKeyEncoded);
            rsaKey.setPublicKey(publicKey);
            Key privateKeySecret = keyPair.getPrivate();
            /* privateKey经过二次加密后的字符串 */
            byte[] privateKeyEncoded = privateKeySecret.getEncoded();
            String privateKey = Base64.encodeBase64URLSafeString(privateKeyEncoded);
            rsaKey.setPrivateKey(privateKey);
        } catch (NoSuchAlgorithmException exception) {
            rsaKey = null;
            log.error("the generate algorithm of rsa is error !, error: {}", exception.getMessage());
        }
        return rsaKey;
    }

    /**
     * <code>encrypts</code>
     * <p>The encrypts method.</p>
     * @param source {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String encrypts(String source) {
        String publicKey = INSTANCE.rsaProperties.getPublicKey();
        return encrypts(source, publicKey);
    }

    /**
     * <code>encrypts</code>
     * <p>The encrypts method.</p>
     * @param sourceBytes byte <p>The source bytes parameter is <code>byte</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String encrypts(byte[] sourceBytes) {
        String publicKey = INSTANCE.rsaProperties.getPublicKey();
        return encrypts(sourceBytes, publicKey);
    }

    /**
     * <code>decrypts</code>
     * <p>The decrypts method.</p>
     * @param source {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String decrypts(String source) {
        String privateKey = INSTANCE.rsaProperties.getPrivateKey();
        return decrypts(source, privateKey);
    }

    /**
     * <code>decrypts</code>
     * <p>The decrypts method.</p>
     * @param sourceBytes byte <p>The source bytes parameter is <code>byte</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String decrypts(byte[] sourceBytes) {
        String privateKey = INSTANCE.rsaProperties.getPrivateKey();
        return decrypts(sourceBytes, privateKey);
    }

    /**
     * <code>decrypts</code>
     * <p>The decrypts method.</p>
     * @param source           {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param privateKeySecret {@link java.lang.String} <p>The private key secret parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String decrypts(String source, String privateKeySecret) {
        PrivateKey privateKey = privateKey(privateKeySecret);
        return decrypts(source, privateKey, INSTANCE.rsaProperties.getKeySize() / 8);
    }

    /**
     * <code>decrypts</code>
     * <p>The decrypts method.</p>
     * @param sourceBytes      byte <p>The source bytes parameter is <code>byte</code> type.</p>
     * @param privateKeySecret {@link java.lang.String} <p>The private key secret parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String decrypts(byte[] sourceBytes, String privateKeySecret) {
        PrivateKey privateKey = privateKey(privateKeySecret);
        return decrypts(sourceBytes, privateKey, INSTANCE.rsaProperties.getKeySize() / 8);
    }

    /**
     * <code>decrypts</code>
     * <p>The decrypts method.</p>
     * @param source           {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param privateKeySecret {@link java.lang.String} <p>The private key secret parameter is <code>String</code> type.</p>
     * @param segmentSize      int <p>The segment size parameter is <code>int</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String decrypts(String source, String privateKeySecret, int segmentSize) {
        PrivateKey privateKey = privateKey(privateKeySecret);
        return decrypts(source, privateKey, segmentSize);
    }

    /**
     * <code>decrypts</code>
     * <p>The decrypts method.</p>
     * @param sourceBytes      byte <p>The source bytes parameter is <code>byte</code> type.</p>
     * @param privateKeySecret {@link java.lang.String} <p>The private key secret parameter is <code>String</code> type.</p>
     * @param segmentSize      int <p>The segment size parameter is <code>int</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String decrypts(byte[] sourceBytes, String privateKeySecret, int segmentSize) {
        PrivateKey privateKey = privateKey(privateKeySecret);
        return decrypts(sourceBytes, privateKey, segmentSize);
    }

    /**
     * <code>keyFactory</code>
     * <p>The key factory method.</p>
     * @param algorithm {@link java.lang.String} <p>The algorithm parameter is <code>String</code> type.</p>
     * @return {@link java.security.KeyFactory} <p>The key factory return object is <code>KeyFactory</code> type.</p>
     * @see java.lang.String
     * @see java.security.KeyFactory
     */
    public static KeyFactory keyFactory(String algorithm) {
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance(algorithm);
        } catch (NoSuchAlgorithmException exception) {
            log.error("the key factory of {} algorithm init  is error !, error: {}", algorithm, exception.getMessage());
        }
        return keyFactory;
    }

    /**
     * <code>privateKey</code>
     * <p>The private key method.</p>
     * @param privateKeySecret {@link java.lang.String} <p>The private key secret parameter is <code>String</code> type.</p>
     * @return {@link java.security.PrivateKey} <p>The private key return object is <code>PrivateKey</code> type.</p>
     * @see java.lang.String
     * @see java.security.PrivateKey
     */
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

    /**
     * <code>decrypts</code>
     * <p>The decrypts method.</p>
     * @param source      {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param privateKey  {@link java.security.PrivateKey} <p>The private key parameter is <code>PrivateKey</code> type.</p>
     * @param segmentSize int <p>The segment size parameter is <code>int</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.security.PrivateKey
     */
    public static String decrypts(String source, PrivateKey privateKey, int segmentSize) {
        byte[] sourceBytes = source.getBytes(StandardCharsets.UTF_8);
        return decrypts(sourceBytes, privateKey, segmentSize);
    }

    /**
     * <code>decrypts</code>
     * <p>The decrypts method.</p>
     * @param sourceBytes byte <p>The source bytes parameter is <code>byte</code> type.</p>
     * @param privateKey  {@link java.security.PrivateKey} <p>The private key parameter is <code>PrivateKey</code> type.</p>
     * @param segmentSize int <p>The segment size parameter is <code>int</code> type.</p>
     * @return {@link java.lang.String} <p>The decrypts return object is <code>String</code> type.</p>
     * @see java.security.PrivateKey
     * @see java.lang.String
     */
    public static String decrypts(byte[] sourceBytes, PrivateKey privateKey, int segmentSize) {
        if (GeneralUtils.isEmpty(privateKey)) {
            return null;
        }
        byte[] targetBytes = null;
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            targetBytes = dofinal(cipher, sourceBytes, segmentSize);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException exception) {
            log.error("the decrypts algorithm of RSA is error !, error: {}", exception.getMessage());
        }
        return Base64.encodeBase64String(targetBytes);
    }

    /**
     * <code>encrypts</code>
     * <p>The encrypts method.</p>
     * @param source          {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param publicKeySecret {@link java.lang.String} <p>The public key secret parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String encrypts(String source, String publicKeySecret) {
        PublicKey publicKey = publicKey(publicKeySecret);
        return encrypts(source, publicKey, INSTANCE.rsaProperties.getKeySize() / 8 - 11);
    }

    /**
     * <code>encrypts</code>
     * <p>The encrypts method.</p>
     * @param sourceBytes     byte <p>The source bytes parameter is <code>byte</code> type.</p>
     * @param publicKeySecret {@link java.lang.String} <p>The public key secret parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String encrypts(byte[] sourceBytes,  String publicKeySecret) {
        PublicKey publicKey = publicKey(publicKeySecret);
        return encrypts(sourceBytes, publicKey, INSTANCE.rsaProperties.getKeySize() / 8 - 11);
    }

    /**
     * <code>encrypts</code>
     * <p>The encrypts method.</p>
     * @param source          {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param publicKeySecret {@link java.lang.String} <p>The public key secret parameter is <code>String</code> type.</p>
     * @param segmentSize     int <p>The segment size parameter is <code>int</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String encrypts(String source, String publicKeySecret, int segmentSize) {
        PublicKey publicKey = publicKey(publicKeySecret);
        return encrypts(source, publicKey, segmentSize);
    }

    /**
     * <code>encrypts</code>
     * <p>The encrypts method.</p>
     * @param sourceBytes     byte <p>The source bytes parameter is <code>byte</code> type.</p>
     * @param publicKeySecret {@link java.lang.String} <p>The public key secret parameter is <code>String</code> type.</p>
     * @param segmentSize     int <p>The segment size parameter is <code>int</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String encrypts(byte[] sourceBytes,  String publicKeySecret, int segmentSize) {
        PublicKey publicKey = publicKey(publicKeySecret);
        return encrypts(sourceBytes, publicKey, segmentSize);
    }

    /**
     * <code>publicKey</code>
     * <p>The public key method.</p>
     * @param publicKeySecret {@link java.lang.String} <p>The public key secret parameter is <code>String</code> type.</p>
     * @return {@link java.security.PublicKey} <p>The public key return object is <code>PublicKey</code> type.</p>
     * @see java.lang.String
     * @see java.security.PublicKey
     */
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


    /**
     * <code>encrypts</code>
     * <p>The encrypts method.</p>
     * @param source      {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param publicKey   {@link java.security.PublicKey} <p>The public key parameter is <code>PublicKey</code> type.</p>
     * @param segmentSize int <p>The segment size parameter is <code>int</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.security.PublicKey
     */
    public static String encrypts(String source, PublicKey publicKey, int segmentSize) {
        byte[] sourceBytes = source.getBytes(StandardCharsets.UTF_8);
        return encrypts(sourceBytes,publicKey,segmentSize);
    }

    /**
     * <code>encrypts</code>
     * <p>The encrypts method.</p>
     * @param sourceBytes byte <p>The source bytes parameter is <code>byte</code> type.</p>
     * @param publicKey   {@link java.security.PublicKey} <p>The public key parameter is <code>PublicKey</code> type.</p>
     * @param segmentSize int <p>The segment size parameter is <code>int</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
     * @see java.security.PublicKey
     * @see java.lang.String
     */
    public static String encrypts(byte[] sourceBytes, PublicKey publicKey, int segmentSize) {
        if (GeneralUtils.isEmpty(publicKey)) {
            return null;
        }
        byte[] targetBytes = null;
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            targetBytes = dofinal(cipher, sourceBytes, segmentSize);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException exception) {
            log.error("the encrypts algorithm of RSA is error !, error: {}", exception.getMessage());
        }
        return Base64.encodeBase64String(targetBytes);
    }

    /**
     * <code>dofinal</code>
     * <p>The dofinal method.</p>
     * @param cipher      {@link javax.crypto.Cipher} <p>The cipher parameter is <code>Cipher</code> type.</p>
     * @param source      {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param segmentSize int <p>The segment size parameter is <code>int</code> type.</p>
     * @return byte <p>The dofinal return object is <code>byte</code> type.</p>
     * @see javax.crypto.Cipher
     * @see java.lang.String
     */
    private static byte[] dofinal(Cipher cipher, String source, int segmentSize) {
        byte[] sourceBytes = source.getBytes(StandardCharsets.UTF_8);
        return dofinal(cipher, sourceBytes, segmentSize);
    }

    /**
     * <code>dofinal</code>
     * <p>The dofinal method.</p>
     * @param cipher      {@link javax.crypto.Cipher} <p>The cipher parameter is <code>Cipher</code> type.</p>
     * @param sourceBytes byte <p>The source bytes parameter is <code>byte</code> type.</p>
     * @param segmentSize int <p>The segment size parameter is <code>int</code> type.</p>
     * @return byte <p>The dofinal return object is <code>byte</code> type.</p>
     * @see javax.crypto.Cipher
     */
    private static byte[] dofinal(Cipher cipher, byte[] sourceBytes, int segmentSize) {
        byte[] targetBytes = null;
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

    /**
     * <code>segmentDofinal</code>
     * <p>The segment dofinal method.</p>
     * @param cipher      {@link javax.crypto.Cipher} <p>The cipher parameter is <code>Cipher</code> type.</p>
     * @param sourceBytes byte <p>The source bytes parameter is <code>byte</code> type.</p>
     * @param segmentSize int <p>The segment size parameter is <code>int</code> type.</p>
     * @return byte <p>The segment dofinal return object is <code>byte</code> type.</p>
     * @see javax.crypto.Cipher
     */
    private static byte[] segmentDofinal(Cipher cipher, byte[] sourceBytes, int segmentSize) {
        int length = sourceBytes.length;
        int offSet = 0;
        byte[] bytesCache;
        int segment = 0;
        /* 对数据分段解密 */
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
