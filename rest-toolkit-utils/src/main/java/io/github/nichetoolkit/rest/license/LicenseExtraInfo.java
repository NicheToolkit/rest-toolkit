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
 * <code>LicenseExtraInfo</code>
 * <p>The license extra info class.</p>
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
public class LicenseExtraInfo implements Serializable {
    /**
     * <code>ipAddress</code>
     * {@link java.util.List} <p>The <code>ipAddress</code> field.</p>
     * @see java.util.List
     */
    private List<String> ipAddress;
    /**
     * <code>macAddress</code>
     * {@link java.util.List} <p>The <code>macAddress</code> field.</p>
     * @see java.util.List
     */
    private List<String> macAddress;
    /**
     * <code>cpuSerial</code>
     * {@link java.lang.String} <p>The <code>cpuSerial</code> field.</p>
     * @see java.lang.String
     */
    private String cpuSerial;
    /**
     * <code>boardSerial</code>
     * {@link java.lang.String} <p>The <code>boardSerial</code> field.</p>
     * @see java.lang.String
     */
    private String boardSerial;
}
