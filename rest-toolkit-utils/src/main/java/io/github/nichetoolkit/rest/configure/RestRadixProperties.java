package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <code>RestRadixProperties</code>
 * <p>The rest radix properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Getter
 * @see lombok.Setter
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.radix")
public class RestRadixProperties {
    /**
     * <code>DEFAULT_DIGITS</code>
     * {@link java.lang.String} <p>The constant <code>DEFAULT_DIGITS</code> field.</p>
     * @see java.lang.String
     */
    private static final String DEFAULT_DIGITS = "qwe8as2dzx9c7p5ik3mjufr4vyltn6bgh";
    /**
     * <code>DEFAULT_SUPPLY</code>
     * <p>The constant <code>DEFAULT_SUPPLY</code> field.</p>
     */
    private static final char DEFAULT_SUPPLY = 'o';

    /**
     * <code>enabled</code>
     * <p>The <code>enabled</code> field.</p>
     */
    private boolean enabled;
    /**
     * <code>digits</code>
     * {@link java.lang.String} <p>The <code>digits</code> field.</p>
     * @see java.lang.String
     */
    private String digits = DEFAULT_DIGITS;
    /**
     * <code>supply</code>
     * {@link java.lang.String} <p>The <code>supply</code> field.</p>
     * @see java.lang.String
     */
    private String supply = String.valueOf(DEFAULT_SUPPLY);
    /**
     * <code>minLength</code>
     * {@link java.lang.Integer} <p>The <code>minLength</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer minLength = 6;

    /**
     * <code>RestRadixProperties</code>
     * <p>Instantiates a new rest radix properties.</p>
     */
    public RestRadixProperties() {
    }

    /**
     * <code>toSupplyChar</code>
     * <p>The to supply char method.</p>
     * @return char <p>The to supply char return object is <code>char</code> type.</p>
     */
    public char toSupplyChar() {
        if (GeneralUtils.isNotEmpty(this.supply)) {
            return supply.charAt(0);
        }
        return DEFAULT_SUPPLY;
    }

    /**
     * <code>toDigitsChar</code>
     * <p>The to digits char method.</p>
     * @return char <p>The to digits char return object is <code>char</code> type.</p>
     */
    public char[] toDigitsChar() {
        if (GeneralUtils.isNotEmpty(this.digits)) {
            return digits.toCharArray();
        }
        return DEFAULT_DIGITS.toCharArray();
    }
}
