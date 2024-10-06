package io.github.nichetoolkit.rest.worker.jwt;

import io.fusionauth.jwt.domain.JWT;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <code>JwtBuilder</code>
 * <p>The type jwt builder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class JwtBuilder {
    /**
     * <code>audience</code>
     * {@link java.lang.Object} <p>The <code>audience</code> field.</p>
     * @see java.lang.Object
     */
    public Object audience;
    /**
     * <code>expiration</code>
     * {@link java.time.ZonedDateTime} <p>The <code>expiration</code> field.</p>
     * @see java.time.ZonedDateTime
     */
    public ZonedDateTime expiration;
    /**
     * <code>issuedAt</code>
     * {@link java.time.ZonedDateTime} <p>The <code>issuedAt</code> field.</p>
     * @see java.time.ZonedDateTime
     */
    public ZonedDateTime issuedAt;
    /**
     * <code>issuer</code>
     * {@link java.lang.String} <p>The <code>issuer</code> field.</p>
     * @see java.lang.String
     */
    public String issuer;
    /**
     * <code>notBefore</code>
     * {@link java.time.ZonedDateTime} <p>The <code>notBefore</code> field.</p>
     * @see java.time.ZonedDateTime
     */
    public ZonedDateTime notBefore;
    /**
     * <code>otherClaims</code>
     * {@link java.util.Map} <p>The <code>otherClaims</code> field.</p>
     * @see java.util.Map
     */
    public Map<String, Object> otherClaims = new LinkedHashMap<>();
    /**
     * <code>subject</code>
     * {@link java.lang.String} <p>The <code>subject</code> field.</p>
     * @see java.lang.String
     */
    public String subject;
    /**
     * <code>uniqueId</code>
     * {@link java.lang.String} <p>The <code>uniqueId</code> field.</p>
     * @see java.lang.String
     */
    public String uniqueId;

    /**
     * <code>JwtBuilder</code>
     * <p>Instantiates a new jwt builder.</p>
     */
    public JwtBuilder() {
    }

    /**
     * <code>audience</code>
     * <p>The method.</p>
     * @param audience {@link java.lang.Object} <p>The audience parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>The return object is <code>JwtBuilder</code> type.</p>
     * @see java.lang.Object
     */
    public JwtBuilder audience(Object audience) {
        this.audience = audience;
        return this;
    }

    /**
     * <code>expiration</code>
     * <p>The method.</p>
     * @param expiration {@link java.time.ZonedDateTime} <p>The expiration parameter is <code>ZonedDateTime</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>The return object is <code>JwtBuilder</code> type.</p>
     * @see java.time.ZonedDateTime
     */
    public JwtBuilder expiration(ZonedDateTime expiration) {
        this.expiration = expiration;
        return this;
    }

    /**
     * <code>issuedAt</code>
     * <p>The at method.</p>
     * @param issuedAt {@link java.time.ZonedDateTime} <p>The issued at parameter is <code>ZonedDateTime</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>The at return object is <code>JwtBuilder</code> type.</p>
     * @see java.time.ZonedDateTime
     */
    public JwtBuilder issuedAt(ZonedDateTime issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    /**
     * <code>issuer</code>
     * <p>The method.</p>
     * @param issuer {@link java.lang.String} <p>The issuer parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>The return object is <code>JwtBuilder</code> type.</p>
     * @see java.lang.String
     */
    public JwtBuilder issuer(String issuer) {
        this.issuer = issuer;
        return this;
    }


    /**
     * <code>notBefore</code>
     * <p>The before method.</p>
     * @param notBefore {@link java.time.ZonedDateTime} <p>The not before parameter is <code>ZonedDateTime</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>The before return object is <code>JwtBuilder</code> type.</p>
     * @see java.time.ZonedDateTime
     */
    public JwtBuilder notBefore(ZonedDateTime notBefore) {
        this.notBefore = notBefore;
        return this;
    }


    /**
     * <code>subject</code>
     * <p>The method.</p>
     * @param subject {@link java.lang.String} <p>The subject parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>The return object is <code>JwtBuilder</code> type.</p>
     * @see java.lang.String
     */
    public JwtBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * <code>uniqueId</code>
     * <p>The id method.</p>
     * @param uniqueId {@link java.lang.String} <p>The unique id parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>The id return object is <code>JwtBuilder</code> type.</p>
     * @see java.lang.String
     */
    public JwtBuilder uniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    /**
     * <code>addClaim</code>
     * <p>The claim method.</p>
     * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>The claim return object is <code>JwtBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public JwtBuilder addClaim(String name, Object value) {
        this.otherClaims.putIfAbsent(name,value);
        return this;
    }

    /**
     * <code>addClaim</code>
     * <p>The claim method.</p>
     * @param claimMap {@link java.util.Map} <p>The claim map parameter is <code>Map</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>The claim return object is <code>JwtBuilder</code> type.</p>
     * @see java.util.Map
     */
    public JwtBuilder addClaim(Map<String,Object> claimMap) {
        this.otherClaims.putAll(claimMap);
        return this;
    }

    /**
     * <code>addClaim</code>
     * <p>The claim method.</p>
     * @param entries {@link java.util.Collection} <p>The entries parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>The claim return object is <code>JwtBuilder</code> type.</p>
     * @see java.util.Collection
     */
    public JwtBuilder addClaim(Collection<Map.Entry<String,Object>> entries) {
        if (GeneralUtils.isNotEmpty(entries)) {
            entries.forEach(entry -> this.otherClaims.putIfAbsent(entry.getKey(),entry.getValue()));
        }
        return this;
    }

    /**
     * <code>toBean</code>
     * <p>The bean method.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>The bean return object is <code>JWT</code> type.</p>
     * @see io.fusionauth.jwt.domain.JWT
     */
    public JWT toBean() {
        JWT jwt = new JWT();
        BeanUtils.copyNonnullProperties(this,jwt);
        return jwt;
    }

    /**
     * <code>build</code>
     * <p>The method.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>The return object is <code>JWT</code> type.</p>
     * @see io.fusionauth.jwt.domain.JWT
     */
    public JWT build() {
        JWT jwt = new JWT().setAudience(this.audience)
                .setExpiration(this.expiration)
                .setIssuedAt(this.issuedAt)
                .setIssuer(this.issuer)
                .setNotBefore(this.notBefore)
                .setSubject(this.subject)
                .setUniqueId(this.uniqueId);
        if (GeneralUtils.isNotEmpty(this.otherClaims)) {
            this.otherClaims.forEach(jwt::addClaim);
        }
        return jwt;
    }

    /**
     * <code>builder</code>
     * <p>The method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>The return object is <code>JwtBuilder</code> type.</p>
     */
    public static JwtBuilder builder() {
        return new JwtBuilder();
    }
}
