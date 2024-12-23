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
 * <p>The sha worker class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class ShaWorker {

    /**
     * <code>SIGN_KEY</code>
     * {@link java.lang.String} <p>The constant <code>SIGN_KEY</code> field.</p>
     * @see java.lang.String
     */
    private static final String SIGN_KEY = "sign";

    /**
     * <code>PASSWORD_KEY</code>
     * {@link java.lang.String} <p>The constant <code>PASSWORD_KEY</code> field.</p>
     * @see java.lang.String
     */
    private static final String PASSWORD_KEY = "password";

    /**
     * <code>shaProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestShaProperties} <p>The <code>shaProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestShaProperties
     */
    private final RestShaProperties shaProperties;

    /**
     * <code>INSTANCE</code>
     * {@link io.github.nichetoolkit.rest.worker.sha.ShaWorker} <p>The constant <code>INSTANCE</code> field.</p>
     */
    private static ShaWorker INSTANCE = null;

    /**
     * <code>getInstance</code>
     * <p>The get instance getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.sha.ShaWorker} <p>The get instance return object is <code>ShaWorker</code> type.</p>
     */
    public static ShaWorker getInstance() {
        return INSTANCE;
    }

    /**
     * <code>ShaWorker</code>
     * <p>Instantiates a new sha worker.</p>
     * @param shaProperties {@link io.github.nichetoolkit.rest.configure.RestShaProperties} <p>The sha properties parameter is <code>RestShaProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestShaProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public ShaWorker(RestShaProperties shaProperties) {
        this.shaProperties = shaProperties;
    }

    /**
     * <code>shaWorkerInit</code>
     * <p>The sha worker init method.</p>
     * @see javax.annotation.PostConstruct
     */
    @PostConstruct
    public void shaWorkerInit() {
        log.debug("The sha        properties: {}", JsonUtils.parseJson(shaProperties));
        INSTANCE = this;
    }

    /**
     * <code>shaEncrypt</code>
     * <p>The sha encrypt method.</p>
     * @param source {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The sha encrypt return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    private static String shaEncrypt(String source) {
        return encrypt(source,INSTANCE.shaProperties.getAlgorithm());
    }

    /**
     * <code>encrypt</code>
     * <p>The encrypt method.</p>
     * @param source    {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param algorithm {@link io.github.nichetoolkit.rest.worker.sha.ShaAlgorithm} <p>The algorithm parameter is <code>ShaAlgorithm</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypt return object is <code>String</code> type.</p>
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
     * <p>The encrypt method.</p>
     * @param source {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypt return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String encrypt(String source) {
        return encrypts(source, this.shaProperties.getSecret());
    }

    /**
     * <code>encrypts</code>
     * <p>The encrypts method.</p>
     * @param source {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String encrypts(String source) {
        return encrypts(source,INSTANCE.shaProperties.getSecret());
    }

    /**
     * <code>encrypts</code>
     * <p>The encrypts method.</p>
     * @param source {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param secret {@link java.lang.String} <p>The secret parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
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
     * <p>The encrypts method.</p>
     * @param source {@link java.util.Map} <p>The source parameter is <code>Map</code> type.</p>
     * @param secret {@link java.lang.String} <p>The secret parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The encrypts return object is <code>String</code> type.</p>
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
            if (source.get(key) != null && !source.get(key).toString().trim().isEmpty()) {
                keyBuilder.append(key).append("=").append(source.get(key).toString().trim()).append("&");
            }
        }
        keyBuilder.append("key=").append(secret);
        return shaEncrypt(keyBuilder.toString()).toUpperCase();
    }
}
