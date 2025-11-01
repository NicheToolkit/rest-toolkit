package io.github.nichetoolkit.rest.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * <code>LicenseExtraParam</code>
 * <p>The license extra param class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see RestServerInfo
 * @see lombok.Getter
 * @see lombok.Setter
 * @see lombok.experimental.SuperBuilder
 * @see lombok.NoArgsConstructor
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseExtraParam extends LicenseExtraInfo {
    /**
     * <code>ipCheck</code>
     * <p>The <code>ipCheck</code> field.</p>
     */
    private boolean ipCheck;
    /**
     * <code>macCheck</code>
     * <p>The <code>macCheck</code> field.</p>
     */
    private boolean macCheck;
    /**
     * <code>cpuCheck</code>
     * <p>The <code>cpuCheck</code> field.</p>
     */
    private boolean cpuCheck;
    /**
     * <code>boardCheck</code>
     * <p>The <code>boardCheck</code> field.</p>
     */
    private boolean boardCheck;
}
