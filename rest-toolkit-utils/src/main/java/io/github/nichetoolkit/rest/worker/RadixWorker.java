package io.github.nichetoolkit.rest.worker;

import io.github.nichetoolkit.rest.configure.RestRadixProperties;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;

/**
 * <code>RadixWorker</code>
 * <p>The type radix worker class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class RadixWorker {

    /**
     * <code>radixProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestRadixProperties} <p>the <code>radixProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestRadixProperties
     */
    private final RestRadixProperties radixProperties;

    /**
     * <code>INSTANCE</code>
     * {@link io.github.nichetoolkit.rest.worker.RadixWorker} <p>the constant <code>INSTANCE</code> field.</p>
     */
    private static RadixWorker INSTANCE = null;

    /**
     * <code>getInstance</code>
     * <p>the instance getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.RadixWorker} <p>the instance return object is <code>RadixWorker</code> type.</p>
     */
    public static RadixWorker getInstance() {
        return INSTANCE;
    }

    /**
     * <code>RadixWorker</code>
     * Instantiates a new radix worker.
     * @param radixProperties {@link io.github.nichetoolkit.rest.configure.RestRadixProperties} <p>the radix properties parameter is <code>RestRadixProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestRadixProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RadixWorker(RestRadixProperties radixProperties) {
        this.radixProperties = radixProperties;
    }

    /**
     * <code>radixWorkerInit</code>
     * <p>the worker init method.</p>
     * @see javax.annotation.PostConstruct
     */
    @PostConstruct
    public void radixWorkerInit() {
        log.debug("The radix      properties: {}", JsonUtils.parseJson(radixProperties));
        INSTANCE = this;
    }

    /**
     * <code>encrypt</code>
     * <p>the method.</p>
     * @param source {@link java.lang.Long} <p>the source parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.String
     */
    public String encrypt(Long source) {
        return encrypts(source,radixProperties);
    }

    /**
     * <code>decrypt</code>
     * <p>the method.</p>
     * @param target {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Long} <p>the return object is <code>Long</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Long
     */
    public Long decrypt(String target) {
        return decrypts(target,radixProperties);
    }

    /**
     * <code>encrypts</code>
     * <p>the method.</p>
     * @param source     {@link java.lang.Long} <p>the source parameter is <code>Long</code> type.</p>
     * @param properties {@link io.github.nichetoolkit.rest.configure.RestRadixProperties} <p>the properties parameter is <code>RestRadixProperties</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.Long
     * @see io.github.nichetoolkit.rest.configure.RestRadixProperties
     * @see java.lang.String
     */
    private static synchronized String encrypts(Long source, RestRadixProperties properties) {
        return encrypts(source,properties.toDigitsChar(),properties.toSupplyChar(),properties.getMinLength());
    }

    /**
     * <code>encrypts</code>
     * <p>the method.</p>
     * @param source {@link java.lang.Long} <p>the source parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.String
     */
    public static synchronized String encrypts(Long source) {
        return encrypts(source,INSTANCE.radixProperties);
    }

    /**
     * <code>encrypts</code>
     * <p>the method.</p>
     * @param source {@link java.lang.Long} <p>the source parameter is <code>Long</code> type.</p>
     * @param digits char <p>the digits parameter is <code>char</code> type.</p>
     * @param supply char <p>the supply parameter is <code>char</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.String
     */
    public static synchronized String encrypts(Long source, char[] digits, char supply) {
        return encrypts(source,digits,supply,INSTANCE.radixProperties.getMinLength());
    }

    /**
     * <code>encrypts</code>
     * <p>the method.</p>
     * @param source    {@link java.lang.Long} <p>the source parameter is <code>Long</code> type.</p>
     * @param digits    char <p>the digits parameter is <code>char</code> type.</p>
     * @param supply    char <p>the supply parameter is <code>char</code> type.</p>
     * @param minLength int <p>the min length parameter is <code>int</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.String
     */
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

    /**
     * <code>decrypts</code>
     * <p>the method.</p>
     * @param target     {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param properties {@link io.github.nichetoolkit.rest.configure.RestRadixProperties} <p>the properties parameter is <code>RestRadixProperties</code> type.</p>
     * @return {@link java.lang.Long} <p>the return object is <code>Long</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.configure.RestRadixProperties
     * @see java.lang.Long
     */
    private static synchronized Long decrypts(String target, RestRadixProperties properties) {
        return decrypts(target,properties.toDigitsChar(),properties.toSupplyChar());
    }

    /**
     * <code>decrypts</code>
     * <p>the method.</p>
     * @param target {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Long} <p>the return object is <code>Long</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Long
     */
    public static synchronized Long decrypts(String target) {
        return decrypts(target,INSTANCE.radixProperties);
    }

    /**
     * <code>decrypts</code>
     * <p>the method.</p>
     * @param target {@link java.lang.String} <p>the target parameter is <code>String</code> type.</p>
     * @param digits char <p>the digits parameter is <code>char</code> type.</p>
     * @param supply char <p>the supply parameter is <code>char</code> type.</p>
     * @return {@link java.lang.Long} <p>the return object is <code>Long</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Long
     */
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
