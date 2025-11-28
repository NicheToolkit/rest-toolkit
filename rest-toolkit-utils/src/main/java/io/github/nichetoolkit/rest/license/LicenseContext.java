package io.github.nichetoolkit.rest.license;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.schlichtherle.license.LicenseContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jspecify.annotations.NonNull;

import java.io.Serializable;
import java.util.Date;

/**
 * <code>LicenseContext</code>
 * <p>The license context class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Getter
 * @see lombok.Setter
 * @see lombok.experimental.SuperBuilder
 * @see lombok.NoArgsConstructor
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseContext implements Serializable {
    /**
     * <code>subject</code>
     * {@link java.lang.String} <p>The <code>subject</code> field.</p>
     * @see java.lang.String
     */
    private String subject;
    /**
     * <code>issuedTime</code>
     * {@link java.util.Date} <p>The <code>issuedTime</code> field.</p>
     * @see java.util.Date
     * @see com.fasterxml.jackson.annotation.JsonFormat
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date issuedTime;
    /**
     * <code>expiryTime</code>
     * {@link java.util.Date} <p>The <code>expiryTime</code> field.</p>
     * @see java.util.Date
     * @see com.fasterxml.jackson.annotation.JsonFormat
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expiryTime;
    /**
     * <code>consumerType</code>
     * {@link java.lang.String} <p>The <code>consumerType</code> field.</p>
     * @see java.lang.String
     */
    private String consumerType;
    /**
     * <code>consumerSize</code>
     * {@link java.lang.Integer} <p>The <code>consumerSize</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer consumerSize;
    /**
     * <code>description</code>
     * {@link java.lang.String} <p>The <code>description</code> field.</p>
     * @see java.lang.String
     */
    private String description;
    /**
     * <code>extra</code>
     * {@link java.lang.Object} <p>The <code>extra</code> field.</p>
     * @see java.lang.Object
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    private Object extra;

    /**
     * <code>LicenseContext</code>
     * <p>Instantiates a new license context.</p>
     * @param content {@link de.schlichtherle.license.LicenseContent} <p>The content parameter is <code>LicenseContent</code> type.</p>
     * @see de.schlichtherle.license.LicenseContent
     * @see org.jspecify.annotations.NonNull
     */
    public LicenseContext(@NonNull LicenseContent content) {
        this.subject = content.getSubject();
        this.issuedTime = content.getIssued();
        this.expiryTime = content.getNotAfter();
        this.consumerType = content.getConsumerType();
        this.consumerSize = content.getConsumerAmount();
        this.description = content.getInfo();
        this.extra = content.getExtra();
    }

    /**
     * <code>licenseContext</code>
     * <p>The license context method.</p>
     * @param content {@link de.schlichtherle.license.LicenseContent} <p>The content parameter is <code>LicenseContent</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseContext} <p>The license context return object is <code>LicenseContext</code> type.</p>
     * @see de.schlichtherle.license.LicenseContent
     * @see org.jspecify.annotations.NonNull
     */
    public static LicenseContext licenseContext(@NonNull LicenseContent content) {
        return new LicenseContext(content);
    }
}
