package io.github.nichetoolkit.rest.worker;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * <p>NRadixWorker 自定义进制转换加密</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
public class NRadixWorker {

    private Character[] digits;

    private Character supplyDigit;

    private Integer minLength;

    public Character[] getDigits() {
        return digits;
    }

    public void setDigits(Character[] digits) {
        this.digits = digits;
    }

    public Character getSupplyDigit() {
        return supplyDigit;
    }

    public void setSupplyDigit(Character supplyDigit) {
        this.supplyDigit = supplyDigit;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    @Autowired
    public NRadixWorker() {
        this.digits = UtilConstants.N_RADIX_DIGITS;
        this.supplyDigit = UtilConstants.N_RADIX_SUPPLY_DIGIT;
        this.minLength = UtilConstants.N_RADIX_MIN_LENGTH;
    }

    public NRadixWorker(Character[] digits, Character supplyDigit) {
        this.digits = digits;
        this.supplyDigit = supplyDigit;
        this.minLength = UtilConstants.N_RADIX_MIN_LENGTH;
    }

    public NRadixWorker(Character[] digits, Character supplyDigit, Integer minLength) {
        this.digits = digits;
        this.supplyDigit = supplyDigit;
        this.minLength = minLength;
    }

    public String encrypt(Long source) {
        return encrypts(source,this.digits,this.supplyDigit,this.minLength);
    }

    public Long decrypt(String target) {
        return decrypts(target,this.digits,this.supplyDigit);
    }

    public static synchronized String encrypts(Long source) {
        return encrypts(source,UtilConstants.N_RADIX_DIGITS,UtilConstants.N_RADIX_SUPPLY_DIGIT,UtilConstants.N_RADIX_MIN_LENGTH);
    }

    public static synchronized String encrypts(Long source, Character[] digitArray, Character supply) {
        return encrypts(source,digitArray,supply,UtilConstants.N_RADIX_MIN_LENGTH);
    }

    public static synchronized String encrypts(Long source, Character[] digits, Character supplyDigit, Integer minLength) {
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
            targetBuilder.append(supplyDigit);
            SecureRandom secureRandom = new SecureRandom();
            for (int i = 1; i < minLength - target.length(); i++) {
                targetBuilder.append(digits[secureRandom.nextInt(digitsLength)]);
            }
            target += targetBuilder.toString();
        }
        return target;
    }

    public static synchronized Long decrypts(String target) {
        return decrypts(target,UtilConstants.N_RADIX_DIGITS,UtilConstants.N_RADIX_SUPPLY_DIGIT);
    }

    public static synchronized Long decrypts(String target, Character[] digits, Character supplyDigit) {
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
            if (chars[i] == supplyDigit) {
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
