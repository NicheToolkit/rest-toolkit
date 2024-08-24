package io.github.nichetoolkit.rest.worker.jwt;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.security.CryptoProvider;
import io.github.nichetoolkit.rest.configure.RestJwtProperties;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * <p>JwtWorker</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@Slf4j
public class JwtWorker {

    private JwtBuilder jwtBuilder;
    private Signer signer;
    private JwtAlgorithm algorithm;
    private Verifier verifier;

    private static JwtWorker INSTANCE = null;

    public static JwtWorker getInstance() {
        return INSTANCE;
    }

    private final RestJwtProperties jwtProperties;

    @Autowired
    public JwtWorker(RestJwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.jwtBuilder = jwtProperties.toBuilder();
        this.algorithm = jwtProperties.getAlgorithm();
        this.signer = jwtProperties.getAlgorithm().getSigner();
        this.verifier = jwtProperties.getAlgorithm().getVerifier();
    }

    @PostConstruct
    public void jwtWorkerInit() {
        log.debug("jwt properties: {}", JsonUtils.parseJson(this.jwtProperties));
        INSTANCE = this;
    }


    public String generate(String subject)  {
        this.jwtBuilder.subject(subject);
        return token(this.jwtBuilder,this.signer);
    }

    public String generate(String uniqueId,String subject)  {
        this.jwtBuilder.uniqueId(uniqueId).subject(subject);
        return token(this.jwtBuilder,this.signer);
    }

    public String generate(String subject, Map<String, Object> claimsMap)  {
        this.jwtBuilder.subject(subject).addClaim(claimsMap);
        return token(this.jwtBuilder,this.signer);
    }

    public String generate(String uniqueId, String subject, Map<String, Object> claimsMap)  {
        this.jwtBuilder.uniqueId(uniqueId).subject(subject).addClaim(claimsMap);
        return token(this.jwtBuilder,this.signer);
    }

    public JWT parser(String token)  {
        return parse(token,this.verifier);
    }

    public static JwtBuilder builder(String subject) {
        return JwtBuilder.builder().uniqueId(UUID.randomUUID().toString())
                .issuedAt(ZonedDateTime.now()).subject(subject);
    }

    public static JwtBuilder builder(String uniqueId, String subject) {
        return JwtBuilder.builder().uniqueId(uniqueId)
                .issuedAt(ZonedDateTime.now()).subject(subject);
    }

    public static JwtBuilder builder(String uniqueId, String subject, Map<String, Object> claimsMap) {
        return JwtBuilder.builder().addClaim(claimsMap).uniqueId(uniqueId)
                .issuedAt(ZonedDateTime.now()).subject(subject);
    }

    public static JwtBuilder builder(String subject, Map<String, Object> claimsMap) {
        return JwtBuilder.builder().addClaim(claimsMap).uniqueId(UUID.randomUUID().toString())
                .issuedAt(ZonedDateTime.now()).subject(subject);
    }

    public static String token(String subject)  {
        JwtBuilder jwtBuilder = builder(subject);
        return token(jwtBuilder,INSTANCE.signer);
    }

    public static String token(String uniqueId,String subject)  {
        JwtBuilder jwtBuilder = builder(uniqueId,subject);
        return token(jwtBuilder,INSTANCE.signer);
    }

    public static String token(String subject, Map<String, Object> claimsMap)  {
        JwtBuilder jwtBuilder = builder(subject, claimsMap);
        return token(jwtBuilder,INSTANCE.signer);
    }

    public static String token(JwtAlgorithm algorithm, PrivateKey privateKey, String kid, CryptoProvider cryptoProvider, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(privateKey,kid,cryptoProvider));
    }

    public static String token(JwtAlgorithm algorithm, PrivateKey privateKey, CryptoProvider cryptoProvider, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(privateKey,cryptoProvider));
    }

    public static String token(JwtAlgorithm algorithm, PrivateKey privateKey, String kid, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(privateKey,kid));
    }

    public static String token(JwtAlgorithm algorithm, PrivateKey privateKey, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(privateKey));
    }

    public static String token(JwtAlgorithm algorithm, String secretKey, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(secretKey));
    }

    public static String token(JwtAlgorithm algorithm, String secretKey, String kid, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(secretKey,kid));
    }

    public static String token(JwtAlgorithm algorithm, String secretKey, CryptoProvider cryptoProvider, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(secretKey,cryptoProvider));
    }

    public static String token(JwtAlgorithm algorithm, String secretKey, String kid, CryptoProvider cryptoProvider, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(secretKey,kid,cryptoProvider));
    }

    public static String token(JwtBuilder jwtBuilder, JwtAlgorithm algorithm, PrivateKey privateKey) {
        return JWT.getEncoder().encode(jwtBuilder.build(), algorithm.signer(privateKey));
    }

    public static String token(JwtBuilder jwtBuilder, JwtAlgorithm algorithm, PrivateKey privateKey, String kid) {
        return JWT.getEncoder().encode(jwtBuilder.build(), algorithm.signer(privateKey,kid));
    }

    public static String token(JwtBuilder jwtBuilder, JwtAlgorithm algorithm, PrivateKey privateKey, CryptoProvider cryptoProvider) {
        return JWT.getEncoder().encode(jwtBuilder.build(), algorithm.signer(privateKey, cryptoProvider));
    }

    public static String token(JwtBuilder jwtBuilder, JwtAlgorithm algorithm, PrivateKey privateKey, String kid, CryptoProvider cryptoProvider) {
        return JWT.getEncoder().encode(jwtBuilder.build(), algorithm.signer(privateKey,kid, cryptoProvider));
    }

    public static String token(JwtBuilder jwtBuilder, JwtAlgorithm algorithm, String secretKey, String kid, CryptoProvider cryptoProvider) {
        return JWT.getEncoder().encode(jwtBuilder.build(), algorithm.signer(secretKey,kid, cryptoProvider));
    }

    public static String token(JwtBuilder jwtBuilder, JwtAlgorithm algorithm, String secretKey, String kid) {
        return JWT.getEncoder().encode(jwtBuilder.build(), algorithm.signer(secretKey,kid));
    }

    public static String token(JwtBuilder jwtBuilder, JwtAlgorithm algorithm, String secretKey) {
        return JWT.getEncoder().encode(jwtBuilder.build(), algorithm.signer(secretKey));
    }

    public static String token(JwtBuilder jwtBuilder, Signer signer) {
        return JWT.getEncoder().encode(jwtBuilder.build(), signer);
    }

    public static JWT parse(String token) {
        return parse(token,INSTANCE.verifier);
    }

    public static JWT parse(String token, JwtAlgorithm algorithm, byte[] bytes) {
        return JWT.getDecoder().decode(token, algorithm.verifier(bytes));
    }

    public static JWT parse(String token, JwtAlgorithm algorithm, Path path) {
        return JWT.getDecoder().decode(token, algorithm.verifier(path));
    }

    public static JWT parse(String token, JwtAlgorithm algorithm, PublicKey publicKey) {
        return parse(token, algorithm.verifier(publicKey));
    }

    public static JWT parse(String token, JwtAlgorithm algorithm, String secretKey) {
        return parse(token, algorithm.verifier(secretKey));
    }

    public static JWT parse(String token, JwtAlgorithm algorithm, String secret, CryptoProvider cryptoProvider) {
        return JWT.getDecoder().decode(token, algorithm.verifier(secret, cryptoProvider));
    }

    public static JWT parse(String token, JwtAlgorithm algorithm, PublicKey publicKey, CryptoProvider cryptoProvider) {
        return JWT.getDecoder().decode(token, algorithm.verifier(publicKey, cryptoProvider));
    }

    public static JWT parse(String token, JwtAlgorithm algorithm, Path path, CryptoProvider cryptoProvider) {
        return JWT.getDecoder().decode(token, algorithm.verifier(path, cryptoProvider));
    }

    public static JWT parse(String token, JwtAlgorithm algorithm, byte[] bytes, CryptoProvider cryptoProvider) {
        return JWT.getDecoder().decode(token, algorithm.verifier(bytes, cryptoProvider));
    }

    public static JWT parse(String token, Verifier verifier) {
        JWT jwt;
        if (GeneralUtils.isEmpty(verifier)) {
            jwt = JWT.getDecoder().decode(token);
        } else {
            jwt = JWT.getDecoder().decode(token, verifier);
        }
        return jwt;
    }


}
