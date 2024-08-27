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
     * {@link java.lang.Object} <p>the <code>audience</code> field.</p>
     * @see java.lang.Object
     */
    public Object audience;
    /**
     * <code>expiration</code>
     * {@link java.time.ZonedDateTime} <p>the <code>expiration</code> field.</p>
     * @see java.time.ZonedDateTime
     */
    public ZonedDateTime expiration;
    /**
     * <code>issuedAt</code>
     * {@link java.time.ZonedDateTime} <p>the <code>issuedAt</code> field.</p>
     * @see java.time.ZonedDateTime
     */
    public ZonedDateTime issuedAt;
    /**
     * <code>issuer</code>
     * {@link java.lang.String} <p>the <code>issuer</code> field.</p>
     * @see java.lang.String
     */
    public String issuer;
    /**
     * <code>notBefore</code>
     * {@link java.time.ZonedDateTime} <p>the <code>notBefore</code> field.</p>
     * @see java.time.ZonedDateTime
     */
    public ZonedDateTime notBefore;
    /**
     * <code>otherClaims</code>
     * {@link java.util.Map} <p>the <code>otherClaims</code> field.</p>
     * @see java.util.Map
     */
    public Map<String, Object> otherClaims = new LinkedHashMap<>();
    /**
     * <code>subject</code>
     * {@link java.lang.String} <p>the <code>subject</code> field.</p>
     * @see java.lang.String
     */
    public String subject;
    /**
     * <code>uniqueId</code>
     * {@link java.lang.String} <p>the <code>uniqueId</code> field.</p>
     * @see java.lang.String
     */
    public String uniqueId;

    /**
     * <code>JwtBuilder</code>
     * Instantiates a new jwt builder.
     */
    public JwtBuilder() {
    }

    /**
     * <code>audience</code>
     * <p>the method.</p>
     * @param audience {@link java.lang.Object} <p>the audience parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the return object is <code>JwtBuilder</code> type.</p>
     * @see java.lang.Object
     */
    public JwtBuilder audience(Object audience) {
        this.audience = audience;
        return this;
    }

    /**
     * <code>expiration</code>
     * <p>the method.</p>
     * @param expiration {@link java.time.ZonedDateTime} <p>the expiration parameter is <code>ZonedDateTime</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the return object is <code>JwtBuilder</code> type.</p>
     * @see java.time.ZonedDateTime
     */
    public JwtBuilder expiration(ZonedDateTime expiration) {
        this.expiration = expiration;
        return this;
    }

    /**
     * <code>issuedAt</code>
     * <p>the at method.</p>
     * @param issuedAt {@link java.time.ZonedDateTime} <p>the issued at parameter is <code>ZonedDateTime</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the at return object is <code>JwtBuilder</code> type.</p>
     * @see java.time.ZonedDateTime
     */
    public JwtBuilder issuedAt(ZonedDateTime issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    /**
     * <code>issuer</code>
     * <p>the method.</p>
     * @param issuer {@link java.lang.String} <p>the issuer parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the return object is <code>JwtBuilder</code> type.</p>
     * @see java.lang.String
     */
    public JwtBuilder issuer(String issuer) {
        this.issuer = issuer;
        return this;
    }


    /**
     * <code>notBefore</code>
     * <p>the before method.</p>
     * @param notBefore {@link java.time.ZonedDateTime} <p>the not before parameter is <code>ZonedDateTime</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the before return object is <code>JwtBuilder</code> type.</p>
     * @see java.time.ZonedDateTime
     */
    public JwtBuilder notBefore(ZonedDateTime notBefore) {
        this.notBefore = notBefore;
        return this;
    }


    /**
     * <code>subject</code>
     * <p>the method.</p>
     * @param subject {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the return object is <code>JwtBuilder</code> type.</p>
     * @see java.lang.String
     */
    public JwtBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * <code>uniqueId</code>
     * <p>the id method.</p>
     * @param uniqueId {@link java.lang.String} <p>the unique id parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the id return object is <code>JwtBuilder</code> type.</p>
     * @see java.lang.String
     */
    public JwtBuilder uniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    /**
     * <code>addClaim</code>
     * <p>the claim method.</p>
     * @param name  {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the claim return object is <code>JwtBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public JwtBuilder addClaim(String name, Object value) {
        this.otherClaims.putIfAbsent(name,value);
        return this;
    }

    /**
     * <code>addClaim</code>
     * <p>the claim method.</p>
     * @param claimMap {@link java.util.Map} <p>the claim map parameter is <code>Map</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the claim return object is <code>JwtBuilder</code> type.</p>
     * @see java.util.Map
     */
    public JwtBuilder addClaim(Map<String,Object> claimMap) {
        this.otherClaims.putAll(claimMap);
        return this;
    }

    /**
     * <code>addClaim</code>
     * <p>the claim method.</p>
     * @param entries {@link java.util.Collection} <p>the entries parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the claim return object is <code>JwtBuilder</code> type.</p>
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
     * <p>the bean method.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>the bean return object is <code>JWT</code> type.</p>
     * @see io.fusionauth.jwt.domain.JWT
     */
    public JWT toBean() {
        JWT jwt = new JWT();
        BeanUtils.copyNonullProperties(this,jwt);
        return jwt;
    }

    /**
     * <code>build</code>
     * <p>the method.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>the return object is <code>JWT</code> type.</p>
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
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the return object is <code>JwtBuilder</code> type.</p>
     */
    public static JwtBuilder builder() {
        return new JwtBuilder();
    }
}
