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
 * <p>ShaWorker</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class ShaWorker {

    private static final String SIGN_KEY = "sign";

    private static final String PASSWORD_KEY = "password";

    private RestShaProperties shaProperties;

    private static ShaWorker INSTANCE = null;

    public static ShaWorker getInstance() {
        return INSTANCE;
    }

    @Autowired
    public ShaWorker(RestShaProperties shaProperties) {
        this.shaProperties = shaProperties;
    }

    @PostConstruct
    public void shaWorkerInit() {
        log.debug("sha properties: {}", JsonUtils.parseJson(shaProperties));
        INSTANCE = this;
    }

    private static String shaEncrypt(String source) {
        return encrypt(source,INSTANCE.shaProperties.getAlgorithm());
    }

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

    public String encrypt(String source) {
        return encrypts(source, this.shaProperties.getSecret());
    }

    public static String encrypts(String source) {
        return encrypts(source,INSTANCE.shaProperties.getSecret());
    }

    public static String encrypts(String source, String secret) {
        Map<String, Object> paramMap = new HashMap<>();
        String target = shaEncrypt(source);
        paramMap.put(PASSWORD_KEY, target);
        return encrypts(paramMap, secret);
    }

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
