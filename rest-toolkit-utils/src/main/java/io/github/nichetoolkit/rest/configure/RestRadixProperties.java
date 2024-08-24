package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>RestRadixProperties</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.radix")
public class RestRadixProperties {
    private static final String DEFAULT_DIGITS = "qwe8as2dzx9c7p5ik3mjufr4vyltn6bgh";
    private static final char DEFAULT_SUPPLY = 'o';

    private boolean enabled;
    private String digits = DEFAULT_DIGITS;
    private String supply = String.valueOf(DEFAULT_SUPPLY);
    private Integer minLength = 6;

    public RestRadixProperties() {
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
