package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.RadixWorker;
import io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm;
import io.github.nichetoolkit.rest.worker.jwt.JwtBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * <p>RestJwtProperties</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.jwt")
public class RestJwtProperties {

    private final RadixWorker radixWorker;

    private boolean enabled;
    /** 加密算法 */
    private JwtAlgorithm algorithm = JwtAlgorithm.HS256;
    /** 加密密钥 */
    private String secret;
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

    @Autowired(required = false)
    public RestJwtProperties() {
        this.radixWorker = null;
    }

    @Autowired(required = false)
    public RestJwtProperties(RadixWorker radixWorker) {
        this.radixWorker = radixWorker;
    }

    @PostConstruct
    public void algorithmInit() {
        if (algorithm == JwtAlgorithm.NONE) {
            this.algorithm.signer();
        } else {
            if (GeneralUtils.isNotEmpty(this.secret)) {
                this.algorithm.verifier(this.secret);
                if (GeneralUtils.isNotEmpty(this.kid)) {
                    this.algorithm.signer(this.secret, this.kid);
                } else {
                    this.algorithm.signer(this.secret);
                }
            } else if (GeneralUtils.isNotEmpty(radixWorker)) {
                this.secret = radixWorker.encrypt(System.currentTimeMillis());
                this.algorithm.signer(this.secret);
                this.algorithm.verifier(this.secret);
            }
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public JwtAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(JwtAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
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
        if (this.isEnabled()) {
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
