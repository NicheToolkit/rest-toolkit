package io.github.nichetoolkit.rest.configure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.RadixWorker;
import io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm;
import io.github.nichetoolkit.rest.worker.jwt.JwtBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * <code>RestJwtProperties</code>
 * <p>The type rest jwt properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Data
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.jwt")
public class RestJwtProperties {
    /**
     * <code>radixWorker</code>
     * {@link io.github.nichetoolkit.rest.worker.RadixWorker} <p>The <code>radixWorker</code> field.</p>
     * @see io.github.nichetoolkit.rest.worker.RadixWorker
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    private final RadixWorker radixWorker;

    /**
     * <code>enabled</code>
     * <p>The <code>enabled</code> field.</p>
     */
    private boolean enabled;
    /**
     * <code>algorithm</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>The <code>algorithm</code> field.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     */
    private JwtAlgorithm algorithm = JwtAlgorithm.HS256;
    /**
     * <code>secret</code>
     * {@link java.lang.String} <p>The <code>secret</code> field.</p>
     * @see java.lang.String
     */
    private String secret;
    /**
     * <code>kid</code>
     * {@link java.lang.String} <p>The <code>kid</code> field.</p>
     * @see java.lang.String
     */
    private String kid;
    /**
     * <code>issuer</code>
     * {@link java.lang.String} <p>The <code>issuer</code> field.</p>
     * @see java.lang.String
     */
    private String issuer;
    /**
     * <code>audiences</code>
     * {@link java.lang.String} <p>The <code>audiences</code> field.</p>
     * @see java.lang.String
     */
    private String[] audiences;
    /**
     * <code>expireTime</code>
     * {@link java.lang.Long} <p>The <code>expireTime</code> field.</p>
     * @see java.lang.Long
     */
    private Long expireTime = 0L;
    /**
     * <code>expireUnit</code>
     * {@link java.time.temporal.ChronoUnit} <p>The <code>expireUnit</code> field.</p>
     * @see java.time.temporal.ChronoUnit
     */
    private ChronoUnit expireUnit = ChronoUnit.MILLIS;
    /**
     * <code>issuedDelayTime</code>
     * {@link java.lang.Long} <p>The <code>issuedDelayTime</code> field.</p>
     * @see java.lang.Long
     */
    private Long issuedDelayTime = 0L;
    /**
     * <code>issuedDelayUnit</code>
     * {@link java.time.temporal.ChronoUnit} <p>The <code>issuedDelayUnit</code> field.</p>
     * @see java.time.temporal.ChronoUnit
     */
    private ChronoUnit issuedDelayUnit = ChronoUnit.MILLIS;
    /**
     * <code>notBeforeEnabled</code>
     * <p>The <code>notBeforeEnabled</code> field.</p>
     */
    private boolean notBeforeEnabled = false;

    /**
     * <code>RestJwtProperties</code>
     * <p>Instantiates a new rest jwt properties.</p>
     */
    public RestJwtProperties() {
        this.radixWorker = null;
    }

    /**
     * <code>RestJwtProperties</code>
     * <p>Instantiates a new rest jwt properties.</p>
     * @param radixWorker {@link io.github.nichetoolkit.rest.worker.RadixWorker} <p>The radix worker parameter is <code>RadixWorker</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.RadixWorker
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired(required = false)
    public RestJwtProperties(RadixWorker radixWorker) {
        this.radixWorker = radixWorker;
    }

    /**
     * <code>algorithmInit</code>
     * <p>The init method.</p>
     * @see javax.annotation.PostConstruct
     */
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


    /**
     * <code>getAudiences</code>
     * <p>The audiences getter method.</p>
     * @return {@link java.util.List} <p>The audiences return object is <code>List</code> type.</p>
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("MixedMutabilityReturnType")
    public List<String> getAudiences() {
        if (GeneralUtils.isNotEmpty(this.audiences)) {
            return new ArrayList<>(Arrays.asList(this.audiences));
        }
        return Collections.emptyList();
    }

    /**
     * <code>toBuilder</code>
     * <p>The builder method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>The builder return object is <code>JwtBuilder</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtBuilder
     */
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
