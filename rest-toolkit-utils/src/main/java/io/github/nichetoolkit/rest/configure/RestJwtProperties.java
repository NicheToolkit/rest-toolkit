package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.RadixWorker;
import io.github.nichetoolkit.rest.worker.jwt.AlgorithmType;
import io.github.nichetoolkit.rest.worker.jwt.JwtBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * <p>RestJWTProperties</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.jwt")
public class RestJwtProperties {
    private Boolean enabled = false;
    /** 加密算法 */
    private AlgorithmType algorithm = AlgorithmType.HS256;
    /** 加密密钥 */
    private String secretKey;
    /** 密钥kid */
    private String kid;
    /** 发行者 */
    private String issuer;
    /** 接收者 */
    private String[] audiences;
    /** 发行时间默认为 发行延时后多久失效 */
    private Long expireTime = 0L;
    /** 发行延时后多久失效 */
    private ChronoUnit expireUnit = ChronoUnit.MILLIS;
    /** 发行延时时间 */
    private Long issuedDelayTime = 0L;
    /** 发行延时单位 */
    private ChronoUnit issuedDelayUnit = ChronoUnit.MILLIS;
    /** 是否开启nbf */
    private boolean notBeforeEnabled = false;

    public RestJwtProperties() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public AlgorithmType getAlgorithm() {
        if (algorithm == AlgorithmType.NONE) {
            this.algorithm.signer();
        } else {
            if (GeneralUtils.isNotEmpty(this.secretKey)) {
                this.algorithm.verifier(this.secretKey);
                if (GeneralUtils.isNotEmpty(this.kid)) {
                    this.algorithm.signer(this.secretKey, this.kid);
                } else {
                    this.algorithm.signer(this.secretKey);
                }
            } else {
                String secretKey = RadixWorker.encrypts(System.currentTimeMillis());
                this.secretKey = secretKey;
                this.algorithm.signer(secretKey);
                this.algorithm.verifier(secretKey);
            }
        }
        return algorithm;
    }

    public void setAlgorithm(AlgorithmType algorithm) {
        this.algorithm = algorithm;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @SuppressWarnings("MixedMutabilityReturnType")
    public List<String> getAudiences() {
        if (GeneralUtils.isNotEmpty(this.audiences)) {
            return new ArrayList<>(Arrays.asList(this.audiences));
        }
        return Collections.emptyList();
    }

    public void setAudiences(String[] audiences) {
        this.audiences = audiences;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public ChronoUnit getExpireUnit() {
        return expireUnit;
    }

    public void setExpireUnit(ChronoUnit expireUnit) {
        this.expireUnit = expireUnit;
    }

    public Long getIssuedDelayTime() {
        return issuedDelayTime;
    }

    public void setIssuedDelayTime(Long issuedDelayTime) {
        this.issuedDelayTime = issuedDelayTime;
    }

    public ChronoUnit getIssuedDelayUnit() {
        return issuedDelayUnit;
    }

    public void setIssuedDelayUnit(ChronoUnit issuedDelayUnit) {
        this.issuedDelayUnit = issuedDelayUnit;
    }

    public boolean isNotBeforeEnabled() {
        return notBeforeEnabled;
    }

    public void setNotBeforeEnabled(boolean notBeforeEnabled) {
        this.notBeforeEnabled = notBeforeEnabled;
    }

    public JwtBuilder toBuilder() {
        if (this.getEnabled()) {
            JwtBuilder builder = JwtBuilder.builder();
            ZonedDateTime nowDateTime = ZonedDateTime.now(ZoneId.systemDefault());
            if (GeneralUtils.isNotEmpty(this.getIssuer())) {
                builder.issuer(this.getIssuer());
            }
            if (GeneralUtils.isNotEmpty(this.getAudiences())) {
                builder.audience(this.getAudiences());
            }
            ZonedDateTime issuedDateTime = nowDateTime;
            if (GeneralUtils.isNotEmpty(this.getIssuedDelayTime()) && GeneralUtils.isNotEmpty(this.getIssuedDelayUnit())) {
                issuedDateTime = nowDateTime.plus(this.getIssuedDelayTime(), this.getIssuedDelayUnit());
            }
            builder.issuedAt(issuedDateTime);
            if (GeneralUtils.isNotEmpty(this.getExpireTime()) && GeneralUtils.isNotEmpty(this.getExpireUnit())) {
                builder.expiration(issuedDateTime.plus(this.getExpireTime(), this.getExpireUnit()));
            }
            if (this.isNotBeforeEnabled()) {
                builder.notBefore(issuedDateTime);
            }
            return builder;
        } else {
            return JwtBuilder.builder();
        }
    }
}
