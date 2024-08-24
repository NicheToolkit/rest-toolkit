package io.github.nichetoolkit.rest.worker;

import io.github.nichetoolkit.rest.configure.RestRadixProperties;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;

/**
 * <p>RadixWorker 自定义进制转换加密</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class RadixWorker {

    private final RestRadixProperties radixProperties;

    private static RadixWorker INSTANCE = null;

    public static RadixWorker getInstance() {
        return INSTANCE;
    }

    @Autowired
    public RadixWorker(RestRadixProperties radixProperties) {
        this.radixProperties = radixProperties;
    }

    @PostConstruct
    public void radixWorkerInit() {
        log.debug("radix properties: {}", JsonUtils.parseJson(radixProperties));
        INSTANCE = this;
    }

    public String encrypt(Long source) {
        return encrypts(source,radixProperties);
    }

    public Long decrypt(String target) {
        return decrypts(target,radixProperties);
    }

    private static synchronized String encrypts(Long source, RestRadixProperties properties) {
        return encrypts(source,properties.toDigitsChar(),properties.toSupplyChar(),properties.getMinLength());
    }

    public static synchronized String encrypts(Long source) {
        return encrypts(source,INSTANCE.radixProperties);
    }

    public static synchronized String encrypts(Long source, char[] digits, char supply) {
        return encrypts(source,digits,supply,INSTANCE.radixProperties.getMinLength());
    }

    public static synchronized String encrypts(Long source, char[] digits, char supply, int minLength) {
        int digitsLength = digits.length;
        char[] buffer = new char[32];
        int charPos = 32;

        while ((source / digitsLength) > 0) {
            int index = (int) (source % digitsLength);
            buffer[--charPos] = digits[index];
            source /= digitsLength;
        }
        buffer[--charPos] = digits[(int) (source % digitsLength)];
        String target = new String(buffer, charPos, (32 - charPos));
        if (target.length() < minLength) {
            StringBuilder targetBuilder = new StringBuilder();
            targetBuilder.append(supply);
            SecureRandom secureRandom = new SecureRandom();
            for (int i = 1; i < minLength - target.length(); i++) {
                targetBuilder.append(digits[secureRandom.nextInt(digitsLength)]);
            }
            target += targetBuilder.toString();
        }
        return target;
    }

    private static synchronized Long decrypts(String target, RestRadixProperties properties) {
        return decrypts(target,properties.toDigitsChar(),properties.toSupplyChar());
    }

    public static synchronized Long decrypts(String target) {
        return decrypts(target,INSTANCE.radixProperties);
    }

    public static synchronized Long decrypts(String target, char[] digits, char supply) {
        int digitsLength = digits.length;
        char[] chars = target.toCharArray();
        long source = 0L;
        for (int i = 0; i < chars.length; i++) {
            int index = 0;
            for (int j = 0; j < digitsLength; j++) {
                if (chars[i] == digits[j]) {
                    index = j;
                    break;
                }
            }
            if (chars[i] == supply) {
                break;
            }
            if (i > 0) {
                source = source * digitsLength + index;
            } else {
                source = index;
            }
        }
        return source;
    }
    
}
