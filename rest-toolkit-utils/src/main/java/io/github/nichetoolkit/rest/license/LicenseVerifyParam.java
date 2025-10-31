package io.github.nichetoolkit.rest.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseVerifyParam implements Serializable {
    /* 证书subject */
    private String subject;
    /* 公钥别称 */
    private String publicAlias;
    /* 访问公钥库的密码 */
    private String storePass;
    /* 证书生成路径 */
    private String licensePath;
    /* 密钥库存储路径 */
    private String publicKeysStorePath;
}
