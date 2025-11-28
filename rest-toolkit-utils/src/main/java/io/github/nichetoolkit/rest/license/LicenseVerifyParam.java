package io.github.nichetoolkit.rest.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * <code>LicenseVerifyParam</code>
 * <p>The license verify param class.</p>
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
public class LicenseVerifyParam implements Serializable {
    /**
     * <code>subject</code>
     * {@link java.lang.String} <p>The <code>subject</code> field.</p>
     * @see java.lang.String
     */
    /* 证书subject */
    private String subject;
    /**
     * <code>publicAlias</code>
     * {@link java.lang.String} <p>The <code>publicAlias</code> field.</p>
     * @see java.lang.String
     */
    /* 公钥别称 */
    private String publicAlias;
    /**
     * <code>storePass</code>
     * {@link java.lang.String} <p>The <code>storePass</code> field.</p>
     * @see java.lang.String
     */
    /* 访问公钥库的密码 */
    private String storePass;
    /**
     * <code>licensePath</code>
     * {@link java.lang.String} <p>The <code>licensePath</code> field.</p>
     * @see java.lang.String
     */
    /* 证书生成路径 */
    private String licensePath;
    /**
     * <code>publicKeysStorePath</code>
     * {@link java.lang.String} <p>The <code>publicKeysStorePath</code> field.</p>
     * @see java.lang.String
     */
    /* 密钥库存储路径 */
    private String publicKeysStorePath;
}
