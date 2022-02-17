package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>RestRadixProperties</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.radix")
public class RestRadixProperties {

    private static final String DEFAULT_DIGITS = "qwe8as2dzx9c7p5ik3mjufr4vyltn6bgh";

    private static final char DEFAULT_SUPPLY = 'o';

    private Boolean enabled = false;

    private String digits = DEFAULT_DIGITS;
    private String supply = String.valueOf(DEFAULT_SUPPLY);
    private Integer minLength = 6;

    public RestRadixProperties() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getDigits() {
        return digits;
    }

    public void setDigits(String digits) {
        this.digits = digits;
    }

    public String getSupply() {
        return supply;
    }


    public void setSupply(String supply) {
        this.supply = supply;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public char toSupplyChar() {
        if (GeneralUtils.isNotEmpty(this.supply)) {
            return supply.charAt(0);
        }
        return DEFAULT_SUPPLY;
    }

    public char[] toDigitsChar() {
        if (GeneralUtils.isNotEmpty(this.digits)) {
            return digits.toCharArray();
        }
        return DEFAULT_DIGITS.toCharArray();
    }
}
