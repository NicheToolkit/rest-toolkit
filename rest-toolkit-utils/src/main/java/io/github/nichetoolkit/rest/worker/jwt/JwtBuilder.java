package io.github.nichetoolkit.rest.worker.jwt;

import io.fusionauth.jwt.domain.JWT;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>JwtBuilder</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class JwtBuilder {
    public Object audience;
    public ZonedDateTime expiration;
    public ZonedDateTime issuedAt;
    public String issuer;
    public ZonedDateTime notBefore;
    public Map<String, Object> otherClaims = new LinkedHashMap<>();
    public String subject;
    public String uniqueId;

    public JwtBuilder() {
    }

    public JwtBuilder audience(Object audience) {
        this.audience = audience;
        return this;
    }

    public JwtBuilder expiration(ZonedDateTime expiration) {
        this.expiration = expiration;
        return this;
    }
    public JwtBuilder issuedAt(ZonedDateTime issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    public JwtBuilder issuer(String issuer) {
        this.issuer = issuer;
        return this;
    }


    public JwtBuilder notBefore(ZonedDateTime notBefore) {
        this.notBefore = notBefore;
        return this;
    }


    public JwtBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    public JwtBuilder uniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public JwtBuilder addClaim(String name, Object value) {
        this.otherClaims.putIfAbsent(name,value);
        return this;
    }

    public JwtBuilder addClaim(Map<String,Object> claimMap) {
        this.otherClaims.putAll(claimMap);
        return this;
    }

    public JwtBuilder addClaim(Collection<Map.Entry<String,Object>> entries) {
        if (GeneralUtils.isNotEmpty(entries)) {
            entries.forEach(entry -> this.otherClaims.putIfAbsent(entry.getKey(),entry.getValue()));
        }
        return this;
    }

    public JWT toBean() {
        JWT jwt = new JWT();
        BeanUtils.copyNonullProperties(this,jwt);
        return jwt;
    }

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

    public static JwtBuilder builder() {
        return new JwtBuilder();
    }
}
