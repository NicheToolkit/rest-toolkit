package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.type.CharsetType;
import io.github.nichetoolkit.rest.type.LocaleType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <code>RestI18nProperties</code>
 * <p>The rest i 18 n properties class.</p>
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
@ConfigurationProperties(prefix = "nichetoolkit.rest.i18n")
public class RestI18nProperties {
    /**
     * <code>enabled</code>
     * {@link java.lang.Boolean} <p>The <code>enabled</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean enabled = false;

    /**
     * <code>interceptEnabled</code>
     * {@link java.lang.Boolean} <p>The <code>interceptEnabled</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean interceptorEnabled = false;

    /**
     * <code>interceptEnabled</code>
     * {@link java.lang.Boolean} <p>The <code>interceptEnabled</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean sessionResolverEnabled = false;

    /**
     * <code>paramName</code>
     * {@link java.lang.String} <p>The <code>paramName</code> field.</p>
     * @see java.lang.String
     */
    private String paramName = "language";

    /**
     * <code>basename</code>
     * {@link java.lang.String} <p>The <code>basename</code> field.</p>
     * @see java.lang.String
     */
    private String[] basename = {"i18n/messages"};

    /**
     * <code>locale</code>
     * {@link io.github.nichetoolkit.rest.type.LocaleType} <p>The <code>locale</code> field.</p>
     * @see io.github.nichetoolkit.rest.type.LocaleType
     */
    private LocaleType locale = LocaleType.CHINA;
    /**
     * <code>charset</code>
     * {@link io.github.nichetoolkit.rest.type.CharsetType} <p>The <code>charset</code> field.</p>
     * @see io.github.nichetoolkit.rest.type.CharsetType
     */
    private CharsetType charset = CharsetType.UTF_8;

}
