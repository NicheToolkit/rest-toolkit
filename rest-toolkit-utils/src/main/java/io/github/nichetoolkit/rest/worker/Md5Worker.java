package io.github.nichetoolkit.rest.worker;

import io.github.nichetoolkit.rest.configure.RestMd5Properties;
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
 * <p>MD5Worker</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class Md5Worker {

    private static final String SIGN_KEY = "sign";

    private static final String PASSWORD_KEY = "password";

    private RestMd5Properties md5CipherProperties;

    private static Md5Worker INSTANCE = null;

    public static Md5Worker getInstance() {
        return INSTANCE;
    }

    @Autowired
    public Md5Worker(RestMd5Properties md5CipherProperties) {
        this.md5CipherProperties = md5CipherProperties;
    }

    @PostConstruct
    public void md5WorkerInit() {
        log.debug("md5 properties: {}", JsonUtils.parseJson(md5CipherProperties));
        INSTANCE = this;
    }

    public String encrypt(String source) {
        return encrypts(source, this.md5CipherProperties.getCipher());
    }

    private static String md5Encrypts(String source) {
        StringBuilder hexBuilder = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = messageDigest.digest(source.getBytes(StandardCharsets.UTF_8));
            for (byte byt : bytes) {
                hexBuilder.append(Integer.toHexString((byt & 0xFF) | 0x100), 1, 3);
            }
        } catch (NoSuchAlgorithmException exception) {
            log.error("the algorithm of md5 not be found!, error: {}",exception.getMessage());
        }
        return hexBuilder.toString().toUpperCase();
    }

    public static String encrypts(final Map<String, Object> source, String cipher) {
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
        keyBuilder.append("key=").append(cipher);
        return md5Encrypts(keyBuilder.toString()).toUpperCase();
    }

    public static String encrypts(String source) {
        return encrypts(source,INSTANCE.md5CipherProperties.getCipher());
    }

    public static String encrypts(String source, String cipher) {
        Map<String, Object> paramMap = new HashMap<>();
        String target = md5Encrypts(source);
        paramMap.put(PASSWORD_KEY, target);
        return encrypts(paramMap, cipher);
    }
}
