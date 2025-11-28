package io.github.nichetoolkit.rest.license;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * <code>LicenseCreateParam</code>
 * <p>The license create param class.</p>
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
public class LicenseCreateParam implements Serializable {
    /**
     * <code>subject</code>
     * {@link java.lang.String} <p>The <code>subject</code> field.</p>
     * @see java.lang.String
     */
    /* 证书subject */
    private String subject;
    /**
     * <code>privateAlias</code>
     * {@link java.lang.String} <p>The <code>privateAlias</code> field.</p>
     * @see java.lang.String
     */
    /* 密钥别称 */
    private String privateAlias;
    /**
     * <code>keyPass</code>
     * {@link java.lang.String} <p>The <code>keyPass</code> field.</p>
     * @see java.lang.String
     */
    /* 密钥密码（需要妥善保管） */
    private String keyPass;
    /**
     * <code>storePass</code>
     * {@link java.lang.String} <p>The <code>storePass</code> field.</p>
     * @see java.lang.String
     */
    /* 访问秘钥库的密码 */
    private String storePass;
    /**
     * <code>licensePath</code>
     * {@link java.lang.String} <p>The <code>licensePath</code> field.</p>
     * @see java.lang.String
     */
    /* 证书生成路径 */
    private String licensePath;
    /**
     * <code>privateKeysStorePath</code>
     * {@link java.lang.String} <p>The <code>privateKeysStorePath</code> field.</p>
     * @see java.lang.String
     */
    /* 密钥库存储路径 */
    private String privateKeysStorePath;
    /**
     * <code>issuedTime</code>
     * {@link java.util.Date} <p>The <code>issuedTime</code> field.</p>
     * @see java.util.Date
     * @see com.fasterxml.jackson.annotation.JsonFormat
     * @see lombok.Builder.Default
     */
    /* 证书生效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Builder.Default
    private Date issuedTime = new Date();
    /**
     * <code>expiryTime</code>
     * {@link java.util.Date} <p>The <code>expiryTime</code> field.</p>
     * @see java.util.Date
     * @see com.fasterxml.jackson.annotation.JsonFormat
     */
    /* 证书失效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expiryTime;
    /**
     * <code>consumerType</code>
     * {@link java.lang.String} <p>The <code>consumerType</code> field.</p>
     * @see java.lang.String
     * @see lombok.Builder.Default
     */
    /* 用户类型 */
    @Builder.Default
    private String consumerType = "user";
    /**
     * <code>consumerSize</code>
     * {@link java.lang.Integer} <p>The <code>consumerSize</code> field.</p>
     * @see java.lang.Integer
     * @see lombok.Builder.Default
     */
    /* 用户数量 */
    @Builder.Default
    private Integer consumerSize = 1;
    /**
     * <code>description</code>
     * {@link java.lang.String} <p>The <code>description</code> field.</p>
     * @see java.lang.String
     * @see lombok.Builder.Default
     */
    /* 描述信息 */
    @Builder.Default
    private String description = "";
    /**
     * <code>extraInfo</code>
     * {@link io.github.nichetoolkit.rest.license.LicenseExtraParam} <p>The <code>extraInfo</code> field.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseExtraParam
     */
    /* 额外的服务器硬件校验信息 */
    private LicenseExtraParam extraInfo;
    /**
     * <code>licenseUrl</code>
     * {@link java.lang.String} <p>The <code>licenseUrl</code> field.</p>
     * @see java.lang.String
     */
    /* 证书下载地址 */
    private String licenseUrl;

}
