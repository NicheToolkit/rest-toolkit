package io.github.nichetoolkit.rest.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * <code>LicenseExtraParam</code>
 * <p>The license extra param class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
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
public class LicenseExtraParam implements Serializable {
    /**
     * <code>ipCheck</code>
     * <p>The <code>ipCheck</code> field.</p>
     */
    private boolean ipCheck;
    /**
     * <code>ipAddress</code>
     * {@link java.util.List} <p>The <code>ipAddress</code> field.</p>
     * @see java.util.List
     */
    private List<String> ipAddress;
    /**
     * <code>macCheck</code>
     * <p>The <code>macCheck</code> field.</p>
     */
    private boolean macCheck;
    /**
     * <code>macAddress</code>
     * {@link java.util.List} <p>The <code>macAddress</code> field.</p>
     * @see java.util.List
     */
    private List<String> macAddress;
    /**
     * <code>cpuCheck</code>
     * <p>The <code>cpuCheck</code> field.</p>
     */
    private boolean cpuCheck;
    /**
     * <code>cpuSerial</code>
     * {@link java.lang.String} <p>The <code>cpuSerial</code> field.</p>
     * @see java.lang.String
     */
    private String cpuSerial;
    /**
     * <code>boardCheck</code>
     * <p>The <code>boardCheck</code> field.</p>
     */
    private boolean boardCheck;
    /**
     * <code>boardSerial</code>
     * {@link java.lang.String} <p>The <code>boardSerial</code> field.</p>
     * @see java.lang.String
     */
    private String boardSerial;
    /**
     * <code>registerCheck</code>
     * <p>The <code>registerCheck</code> field.</p>
     */
    private boolean registerCheck;
    /**
     * <code>registerSize</code>
     * {@link java.lang.Long} <p>The <code>registerSize</code> field.</p>
     * @see java.lang.Long
     */
    private Long registerSize;
}
