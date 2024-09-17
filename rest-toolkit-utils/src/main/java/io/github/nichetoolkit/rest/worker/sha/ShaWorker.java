package io.github.nichetoolkit.rest.worker.sha;

import io.github.nichetoolkit.rest.configure.RestShaProperties;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <code>ShaWorker</code>
 * <p>The type sha worker class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class ShaWorker {

    /**
     * <code>SIGN_KEY</code>
     * {@link java.lang.String} <p>the constant <code>SIGN_KEY</code> field.</p>
     * @see java.lang.String
     */
    private static final String SIGN_KEY = "sign";

    /**
     * <code>PASSWORD_KEY</code>
     * {@link java.lang.String} <p>the constant <code>PASSWORD_KEY</code> field.</p>
     * @see java.lang.String
     */
    private static final String PASSWORD_KEY = "password";

    /**
     * <code>shaProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestShaProperties} <p>the <code>shaProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestShaProperties
     */
    private final RestShaProperties shaProperties;

    /**
     * <code>INSTANCE</code>
     * {@link io.github.nichetoolkit.rest.worker.sha.ShaWorker} <p>the constant <code>INSTANCE</code> field.</p>
     */
    private static ShaWorker INSTANCE = null;

    /**
     * <code>getInstance</code>
     * <p>the instance getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.sha.ShaWorker} <p>the instance return object is <code>ShaWorker</code> type.</p>
     */
    public static ShaWorker getInstance() {
        return INSTANCE;
    }

    /**
     * <code>ShaWorker</code>
     * Instantiates a new sha worker.
     * @param shaProperties {@link io.github.nichetoolkit.rest.configure.RestShaProperties} <p>the sha properties parameter is <code>RestShaProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestShaProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public ShaWorker(RestShaProperties shaProperties) {
        this.shaProperties = shaProperties;
    }

    /**
     * <code>shaWorkerInit</code>
     * <p>the worker init method.</p>
     * @see javax.annotation.PostConstruct
     */
    @PostConstruct
    public void shaWorkerInit() {
        log.debug("sha        properties: {}", JsonUtils.parseJson(shaProperties));
        INSTANCE = this;
    }

    /**
     * <code>shaEncrypt</code>
     * <p>the encrypt method.</p>
     * @param source {@link java.lang.String} <p>the source parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the encrypt return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    private static String shaEncrypt(String source) {
        return encrypt(source,INSTANCE.shaProperties.getAlgorithm());
    }

    /**
     * <code>encrypt</code>
     * <p>the method.</p>
     * @param source    {@link java.lang.String} <p>the source parameter is <code>String</code> type.</p>
     * @param algorithm {@link io.github.nichetoolkit.rest.worker.sha.ShaAlgorithm} <p>the algorithm parameter is <code>ShaAlgorithm</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.worker.sha.ShaAlgorithm
     */
    public static String encrypt(String source, ShaAlgorithm algorithm) {
        StringBuilder hexBuilder = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm.getAlgorithm());
            byte[] bytes = messageDigest.digest(source.getBytes(StandardCharsets.UTF_8));
            for (byte byt : bytes) {
                hexBuilder.append(Integer.toHexString((byt & 0xFF) | 0x100), 1, 3);
            }
        } catch (NoSuchAlgorithmException exception) {
            log.error("the encrypts algorithm of {} is error !, error: {}", algorithm, exception.getMessage());
        }
        return hexBuilder.toString().toUpperCase();
    }

    /**
     * <code>encrypt</code>
     * <p>the method.</p>
     * @param source {@link java.lang.String} <p>the source parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String encrypt(String source) {
        return encrypts(source, this.shaProperties.getSecret());
    }

    /**
     * <code>encrypts</code>
     * <p>the method.</p>
     * @param source {@link java.lang.String} <p>the source parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String encrypts(String source) {
        return encrypts(source,INSTANCE.shaProperties.getSecret());
    }

    /**
     * <code>encrypts</code>
     * <p>the method.</p>
     * @param source {@link java.lang.String} <p>the source parameter is <code>String</code> type.</p>
     * @param secret {@link java.lang.String} <p>the secret parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String encrypts(String source, String secret) {
        Map<String, Object> paramMap = new HashMap<>();
        String target = shaEncrypt(source);
        paramMap.put(PASSWORD_KEY, target);
        return encrypts(paramMap, secret);
    }

    /**
     * <code>encrypts</code>
     * <p>the method.</p>
     * @param source {@link java.util.Map} <p>the source parameter is <code>Map</code> type.</p>
     * @param secret {@link java.lang.String} <p>the secret parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.util.Map
     * @see java.lang.String
     */
    public static String encrypts(final Map<String, Object> source, String secret) {
        Set<String> keySet = source.keySet();
        String[] keyArray = keySet.toArray(new String[0]);
        Arrays.sort(keyArray);
        StringBuilder keyBuilder = new StringBuilder();
        for (String key : keyArray) {
            if (SIGN_KEY.equals(key)) {
                continue;
            }
            if("v".equals(key)){
                continue;
            }
            if (source.get(key) != null && source.get(key).toString().trim().length() > 0) {
                keyBuilder.append(key).append("=").append(source.get(key).toString().trim()).append("&");
            }
        }
        keyBuilder.append("key=").append(secret);
        return shaEncrypt(keyBuilder.toString()).toUpperCase();
    }
}
