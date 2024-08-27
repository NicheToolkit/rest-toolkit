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
 * <code>JwtWorker</code>
 * <p>The type jwt worker class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Data
@Slf4j
public class JwtWorker {

    /**
     * <code>jwtBuilder</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the <code>jwtBuilder</code> field.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtBuilder
     */
    private JwtBuilder jwtBuilder;
    /**
     * <code>signer</code>
     * {@link io.fusionauth.jwt.Signer} <p>the <code>signer</code> field.</p>
     * @see io.fusionauth.jwt.Signer
     */
    private Signer signer;
    /**
     * <code>algorithm</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the <code>algorithm</code> field.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     */
    private JwtAlgorithm algorithm;
    /**
     * <code>verifier</code>
     * {@link io.fusionauth.jwt.Verifier} <p>the <code>verifier</code> field.</p>
     * @see io.fusionauth.jwt.Verifier
     */
    private Verifier verifier;

    /**
     * <code>INSTANCE</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtWorker} <p>the constant <code>INSTANCE</code> field.</p>
     */
    private static JwtWorker INSTANCE = null;

    /**
     * <code>getInstance</code>
     * <p>the instance getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtWorker} <p>the instance return object is <code>JwtWorker</code> type.</p>
     */
    public static JwtWorker getInstance() {
        return INSTANCE;
    }

    /**
     * <code>jwtProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestJwtProperties} <p>the <code>jwtProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestJwtProperties
     */
    private final RestJwtProperties jwtProperties;

    /**
     * <code>JwtWorker</code>
     * Instantiates a new jwt worker.
     * @param jwtProperties {@link io.github.nichetoolkit.rest.configure.RestJwtProperties} <p>the jwt properties parameter is <code>RestJwtProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestJwtProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public JwtWorker(RestJwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.jwtBuilder = jwtProperties.toBuilder();
        this.algorithm = jwtProperties.getAlgorithm();
        this.signer = jwtProperties.getAlgorithm().getSigner();
        this.verifier = jwtProperties.getAlgorithm().getVerifier();
    }

    /**
     * <code>jwtWorkerInit</code>
     * <p>the worker init method.</p>
     * @see javax.annotation.PostConstruct
     */
    @PostConstruct
    public void jwtWorkerInit() {
        log.debug("jwt properties: {}", JsonUtils.parseJson(this.jwtProperties));
        INSTANCE = this;
    }


    /**
     * <code>generate</code>
     * <p>the method.</p>
     * @param subject {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String generate(String subject)  {
        this.jwtBuilder.subject(subject);
        return token(this.jwtBuilder,this.signer);
    }

    /**
     * <code>generate</code>
     * <p>the method.</p>
     * @param uniqueId {@link java.lang.String} <p>the unique id parameter is <code>String</code> type.</p>
     * @param subject  {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String generate(String uniqueId,String subject)  {
        this.jwtBuilder.uniqueId(uniqueId).subject(subject);
        return token(this.jwtBuilder,this.signer);
    }

    /**
     * <code>generate</code>
     * <p>the method.</p>
     * @param subject   {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @param claimsMap {@link java.util.Map} <p>the claims map parameter is <code>Map</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.util.Map
     */
    public String generate(String subject, Map<String, Object> claimsMap)  {
        this.jwtBuilder.subject(subject).addClaim(claimsMap);
        return token(this.jwtBuilder,this.signer);
    }

    /**
     * <code>generate</code>
     * <p>the method.</p>
     * @param uniqueId  {@link java.lang.String} <p>the unique id parameter is <code>String</code> type.</p>
     * @param subject   {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @param claimsMap {@link java.util.Map} <p>the claims map parameter is <code>Map</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.util.Map
     */
    public String generate(String uniqueId, String subject, Map<String, Object> claimsMap)  {
        this.jwtBuilder.uniqueId(uniqueId).subject(subject).addClaim(claimsMap);
        return token(this.jwtBuilder,this.signer);
    }

    /**
     * <code>parser</code>
     * <p>the method.</p>
     * @param token {@link java.lang.String} <p>the token parameter is <code>String</code> type.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>the return object is <code>JWT</code> type.</p>
     * @see java.lang.String
     * @see io.fusionauth.jwt.domain.JWT
     */
    public JWT parser(String token)  {
        return parse(token,this.verifier);
    }

    /**
     * <code>builder</code>
     * <p>the method.</p>
     * @param subject {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the return object is <code>JwtBuilder</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtBuilder
     */
    public static JwtBuilder builder(String subject) {
        return JwtBuilder.builder().uniqueId(UUID.randomUUID().toString())
                .issuedAt(ZonedDateTime.now()).subject(subject);
    }

    /**
     * <code>builder</code>
     * <p>the method.</p>
     * @param uniqueId {@link java.lang.String} <p>the unique id parameter is <code>String</code> type.</p>
     * @param subject  {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the return object is <code>JwtBuilder</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtBuilder
     */
    public static JwtBuilder builder(String uniqueId, String subject) {
        return JwtBuilder.builder().uniqueId(uniqueId)
                .issuedAt(ZonedDateTime.now()).subject(subject);
    }

    /**
     * <code>builder</code>
     * <p>the method.</p>
     * @param uniqueId  {@link java.lang.String} <p>the unique id parameter is <code>String</code> type.</p>
     * @param subject   {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @param claimsMap {@link java.util.Map} <p>the claims map parameter is <code>Map</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the return object is <code>JwtBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtBuilder
     */
    public static JwtBuilder builder(String uniqueId, String subject, Map<String, Object> claimsMap) {
        return JwtBuilder.builder().addClaim(claimsMap).uniqueId(uniqueId)
                .issuedAt(ZonedDateTime.now()).subject(subject);
    }

    /**
     * <code>builder</code>
     * <p>the method.</p>
     * @param subject   {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @param claimsMap {@link java.util.Map} <p>the claims map parameter is <code>Map</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the return object is <code>JwtBuilder</code> type.</p>
     * @see java.lang.String
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtBuilder
     */
    public static JwtBuilder builder(String subject, Map<String, Object> claimsMap) {
        return JwtBuilder.builder().addClaim(claimsMap).uniqueId(UUID.randomUUID().toString())
                .issuedAt(ZonedDateTime.now()).subject(subject);
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param subject {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String token(String subject)  {
        JwtBuilder jwtBuilder = builder(subject);
        return token(jwtBuilder,INSTANCE.signer);
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param uniqueId {@link java.lang.String} <p>the unique id parameter is <code>String</code> type.</p>
     * @param subject  {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String token(String uniqueId,String subject)  {
        JwtBuilder jwtBuilder = builder(uniqueId,subject);
        return token(jwtBuilder,INSTANCE.signer);
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param subject   {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @param claimsMap {@link java.util.Map} <p>the claims map parameter is <code>Map</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.util.Map
     */
    public static String token(String subject, Map<String, Object> claimsMap)  {
        JwtBuilder jwtBuilder = builder(subject, claimsMap);
        return token(jwtBuilder,INSTANCE.signer);
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param algorithm      {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param privateKey     {@link java.security.PrivateKey} <p>the private key parameter is <code>PrivateKey</code> type.</p>
     * @param kid            {@link java.lang.String} <p>the kid parameter is <code>String</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @param subject        {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @param claimsMap      {@link java.util.Map} <p>the claims map parameter is <code>Map</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.security.PrivateKey
     * @see java.lang.String
     * @see io.fusionauth.security.CryptoProvider
     * @see java.util.Map
     */
    public static String token(JwtAlgorithm algorithm, PrivateKey privateKey, String kid, CryptoProvider cryptoProvider, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(privateKey,kid,cryptoProvider));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param algorithm      {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param privateKey     {@link java.security.PrivateKey} <p>the private key parameter is <code>PrivateKey</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @param subject        {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @param claimsMap      {@link java.util.Map} <p>the claims map parameter is <code>Map</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.security.PrivateKey
     * @see io.fusionauth.security.CryptoProvider
     * @see java.lang.String
     * @see java.util.Map
     */
    public static String token(JwtAlgorithm algorithm, PrivateKey privateKey, CryptoProvider cryptoProvider, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(privateKey,cryptoProvider));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param algorithm  {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param privateKey {@link java.security.PrivateKey} <p>the private key parameter is <code>PrivateKey</code> type.</p>
     * @param kid        {@link java.lang.String} <p>the kid parameter is <code>String</code> type.</p>
     * @param subject    {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @param claimsMap  {@link java.util.Map} <p>the claims map parameter is <code>Map</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.security.PrivateKey
     * @see java.lang.String
     * @see java.util.Map
     */
    public static String token(JwtAlgorithm algorithm, PrivateKey privateKey, String kid, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(privateKey,kid));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param algorithm  {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param privateKey {@link java.security.PrivateKey} <p>the private key parameter is <code>PrivateKey</code> type.</p>
     * @param subject    {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @param claimsMap  {@link java.util.Map} <p>the claims map parameter is <code>Map</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.security.PrivateKey
     * @see java.lang.String
     * @see java.util.Map
     */
    public static String token(JwtAlgorithm algorithm, PrivateKey privateKey, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(privateKey));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param algorithm {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param secretKey {@link java.lang.String} <p>the secret key parameter is <code>String</code> type.</p>
     * @param subject   {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @param claimsMap {@link java.util.Map} <p>the claims map parameter is <code>Map</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.lang.String
     * @see java.util.Map
     */
    public static String token(JwtAlgorithm algorithm, String secretKey, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(secretKey));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param algorithm {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param secretKey {@link java.lang.String} <p>the secret key parameter is <code>String</code> type.</p>
     * @param kid       {@link java.lang.String} <p>the kid parameter is <code>String</code> type.</p>
     * @param subject   {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @param claimsMap {@link java.util.Map} <p>the claims map parameter is <code>Map</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.lang.String
     * @see java.util.Map
     */
    public static String token(JwtAlgorithm algorithm, String secretKey, String kid, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(secretKey,kid));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param algorithm      {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param secretKey      {@link java.lang.String} <p>the secret key parameter is <code>String</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @param subject        {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @param claimsMap      {@link java.util.Map} <p>the claims map parameter is <code>Map</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.lang.String
     * @see io.fusionauth.security.CryptoProvider
     * @see java.util.Map
     */
    public static String token(JwtAlgorithm algorithm, String secretKey, CryptoProvider cryptoProvider, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(secretKey,cryptoProvider));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param algorithm      {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param secretKey      {@link java.lang.String} <p>the secret key parameter is <code>String</code> type.</p>
     * @param kid            {@link java.lang.String} <p>the kid parameter is <code>String</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @param subject        {@link java.lang.String} <p>the subject parameter is <code>String</code> type.</p>
     * @param claimsMap      {@link java.util.Map} <p>the claims map parameter is <code>Map</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.lang.String
     * @see io.fusionauth.security.CryptoProvider
     * @see java.util.Map
     */
    public static String token(JwtAlgorithm algorithm, String secretKey, String kid, CryptoProvider cryptoProvider, String subject, Map<String, Object> claimsMap) {
        return token(builder(subject,claimsMap),algorithm.signer(secretKey,kid,cryptoProvider));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param jwtBuilder {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the jwt builder parameter is <code>JwtBuilder</code> type.</p>
     * @param algorithm  {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param privateKey {@link java.security.PrivateKey} <p>the private key parameter is <code>PrivateKey</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtBuilder
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.security.PrivateKey
     * @see java.lang.String
     */
    public static String token(JwtBuilder jwtBuilder, JwtAlgorithm algorithm, PrivateKey privateKey) {
        return JWT.getEncoder().encode(jwtBuilder.build(), algorithm.signer(privateKey));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param jwtBuilder {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the jwt builder parameter is <code>JwtBuilder</code> type.</p>
     * @param algorithm  {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param privateKey {@link java.security.PrivateKey} <p>the private key parameter is <code>PrivateKey</code> type.</p>
     * @param kid        {@link java.lang.String} <p>the kid parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtBuilder
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.security.PrivateKey
     * @see java.lang.String
     */
    public static String token(JwtBuilder jwtBuilder, JwtAlgorithm algorithm, PrivateKey privateKey, String kid) {
        return JWT.getEncoder().encode(jwtBuilder.build(), algorithm.signer(privateKey,kid));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param jwtBuilder     {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the jwt builder parameter is <code>JwtBuilder</code> type.</p>
     * @param algorithm      {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param privateKey     {@link java.security.PrivateKey} <p>the private key parameter is <code>PrivateKey</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtBuilder
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.security.PrivateKey
     * @see io.fusionauth.security.CryptoProvider
     * @see java.lang.String
     */
    public static String token(JwtBuilder jwtBuilder, JwtAlgorithm algorithm, PrivateKey privateKey, CryptoProvider cryptoProvider) {
        return JWT.getEncoder().encode(jwtBuilder.build(), algorithm.signer(privateKey, cryptoProvider));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param jwtBuilder     {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the jwt builder parameter is <code>JwtBuilder</code> type.</p>
     * @param algorithm      {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param privateKey     {@link java.security.PrivateKey} <p>the private key parameter is <code>PrivateKey</code> type.</p>
     * @param kid            {@link java.lang.String} <p>the kid parameter is <code>String</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtBuilder
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.security.PrivateKey
     * @see java.lang.String
     * @see io.fusionauth.security.CryptoProvider
     */
    public static String token(JwtBuilder jwtBuilder, JwtAlgorithm algorithm, PrivateKey privateKey, String kid, CryptoProvider cryptoProvider) {
        return JWT.getEncoder().encode(jwtBuilder.build(), algorithm.signer(privateKey,kid, cryptoProvider));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param jwtBuilder     {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the jwt builder parameter is <code>JwtBuilder</code> type.</p>
     * @param algorithm      {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param secretKey      {@link java.lang.String} <p>the secret key parameter is <code>String</code> type.</p>
     * @param kid            {@link java.lang.String} <p>the kid parameter is <code>String</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtBuilder
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.lang.String
     * @see io.fusionauth.security.CryptoProvider
     */
    public static String token(JwtBuilder jwtBuilder, JwtAlgorithm algorithm, String secretKey, String kid, CryptoProvider cryptoProvider) {
        return JWT.getEncoder().encode(jwtBuilder.build(), algorithm.signer(secretKey,kid, cryptoProvider));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param jwtBuilder {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the jwt builder parameter is <code>JwtBuilder</code> type.</p>
     * @param algorithm  {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param secretKey  {@link java.lang.String} <p>the secret key parameter is <code>String</code> type.</p>
     * @param kid        {@link java.lang.String} <p>the kid parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtBuilder
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.lang.String
     */
    public static String token(JwtBuilder jwtBuilder, JwtAlgorithm algorithm, String secretKey, String kid) {
        return JWT.getEncoder().encode(jwtBuilder.build(), algorithm.signer(secretKey,kid));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param jwtBuilder {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the jwt builder parameter is <code>JwtBuilder</code> type.</p>
     * @param algorithm  {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param secretKey  {@link java.lang.String} <p>the secret key parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtBuilder
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.lang.String
     */
    public static String token(JwtBuilder jwtBuilder, JwtAlgorithm algorithm, String secretKey) {
        return JWT.getEncoder().encode(jwtBuilder.build(), algorithm.signer(secretKey));
    }

    /**
     * <code>token</code>
     * <p>the method.</p>
     * @param jwtBuilder {@link io.github.nichetoolkit.rest.worker.jwt.JwtBuilder} <p>the jwt builder parameter is <code>JwtBuilder</code> type.</p>
     * @param signer     {@link io.fusionauth.jwt.Signer} <p>the signer parameter is <code>Signer</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtBuilder
     * @see io.fusionauth.jwt.Signer
     * @see java.lang.String
     */
    public static String token(JwtBuilder jwtBuilder, Signer signer) {
        return JWT.getEncoder().encode(jwtBuilder.build(), signer);
    }

    /**
     * <code>parse</code>
     * <p>the method.</p>
     * @param token {@link java.lang.String} <p>the token parameter is <code>String</code> type.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>the return object is <code>JWT</code> type.</p>
     * @see java.lang.String
     * @see io.fusionauth.jwt.domain.JWT
     */
    public static JWT parse(String token) {
        return parse(token,INSTANCE.verifier);
    }

    /**
     * <code>parse</code>
     * <p>the method.</p>
     * @param token     {@link java.lang.String} <p>the token parameter is <code>String</code> type.</p>
     * @param algorithm {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param bytes     {@link byte} <p>the bytes parameter is <code>byte</code> type.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>the return object is <code>JWT</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see io.fusionauth.jwt.domain.JWT
     */
    public static JWT parse(String token, JwtAlgorithm algorithm, byte[] bytes) {
        return JWT.getDecoder().decode(token, algorithm.verifier(bytes));
    }

    /**
     * <code>parse</code>
     * <p>the method.</p>
     * @param token     {@link java.lang.String} <p>the token parameter is <code>String</code> type.</p>
     * @param algorithm {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param path      {@link java.nio.file.Path} <p>the path parameter is <code>Path</code> type.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>the return object is <code>JWT</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.nio.file.Path
     * @see io.fusionauth.jwt.domain.JWT
     */
    public static JWT parse(String token, JwtAlgorithm algorithm, Path path) {
        return JWT.getDecoder().decode(token, algorithm.verifier(path));
    }

    /**
     * <code>parse</code>
     * <p>the method.</p>
     * @param token     {@link java.lang.String} <p>the token parameter is <code>String</code> type.</p>
     * @param algorithm {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param publicKey {@link java.security.PublicKey} <p>the public key parameter is <code>PublicKey</code> type.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>the return object is <code>JWT</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.security.PublicKey
     * @see io.fusionauth.jwt.domain.JWT
     */
    public static JWT parse(String token, JwtAlgorithm algorithm, PublicKey publicKey) {
        return parse(token, algorithm.verifier(publicKey));
    }

    /**
     * <code>parse</code>
     * <p>the method.</p>
     * @param token     {@link java.lang.String} <p>the token parameter is <code>String</code> type.</p>
     * @param algorithm {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param secretKey {@link java.lang.String} <p>the secret key parameter is <code>String</code> type.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>the return object is <code>JWT</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see io.fusionauth.jwt.domain.JWT
     */
    public static JWT parse(String token, JwtAlgorithm algorithm, String secretKey) {
        return parse(token, algorithm.verifier(secretKey));
    }

    /**
     * <code>parse</code>
     * <p>the method.</p>
     * @param token          {@link java.lang.String} <p>the token parameter is <code>String</code> type.</p>
     * @param algorithm      {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param secret         {@link java.lang.String} <p>the secret parameter is <code>String</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>the return object is <code>JWT</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see io.fusionauth.security.CryptoProvider
     * @see io.fusionauth.jwt.domain.JWT
     */
    public static JWT parse(String token, JwtAlgorithm algorithm, String secret, CryptoProvider cryptoProvider) {
        return JWT.getDecoder().decode(token, algorithm.verifier(secret, cryptoProvider));
    }

    /**
     * <code>parse</code>
     * <p>the method.</p>
     * @param token          {@link java.lang.String} <p>the token parameter is <code>String</code> type.</p>
     * @param algorithm      {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param publicKey      {@link java.security.PublicKey} <p>the public key parameter is <code>PublicKey</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>the return object is <code>JWT</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.security.PublicKey
     * @see io.fusionauth.security.CryptoProvider
     * @see io.fusionauth.jwt.domain.JWT
     */
    public static JWT parse(String token, JwtAlgorithm algorithm, PublicKey publicKey, CryptoProvider cryptoProvider) {
        return JWT.getDecoder().decode(token, algorithm.verifier(publicKey, cryptoProvider));
    }

    /**
     * <code>parse</code>
     * <p>the method.</p>
     * @param token          {@link java.lang.String} <p>the token parameter is <code>String</code> type.</p>
     * @param algorithm      {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param path           {@link java.nio.file.Path} <p>the path parameter is <code>Path</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>the return object is <code>JWT</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see java.nio.file.Path
     * @see io.fusionauth.security.CryptoProvider
     * @see io.fusionauth.jwt.domain.JWT
     */
    public static JWT parse(String token, JwtAlgorithm algorithm, Path path, CryptoProvider cryptoProvider) {
        return JWT.getDecoder().decode(token, algorithm.verifier(path, cryptoProvider));
    }

    /**
     * <code>parse</code>
     * <p>the method.</p>
     * @param token          {@link java.lang.String} <p>the token parameter is <code>String</code> type.</p>
     * @param algorithm      {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the algorithm parameter is <code>JwtAlgorithm</code> type.</p>
     * @param bytes          {@link byte} <p>the bytes parameter is <code>byte</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>the return object is <code>JWT</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm
     * @see io.fusionauth.security.CryptoProvider
     * @see io.fusionauth.jwt.domain.JWT
     */
    public static JWT parse(String token, JwtAlgorithm algorithm, byte[] bytes, CryptoProvider cryptoProvider) {
        return JWT.getDecoder().decode(token, algorithm.verifier(bytes, cryptoProvider));
    }

    /**
     * <code>parse</code>
     * <p>the method.</p>
     * @param token    {@link java.lang.String} <p>the token parameter is <code>String</code> type.</p>
     * @param verifier {@link io.fusionauth.jwt.Verifier} <p>the verifier parameter is <code>Verifier</code> type.</p>
     * @return {@link io.fusionauth.jwt.domain.JWT} <p>the return object is <code>JWT</code> type.</p>
     * @see java.lang.String
     * @see io.fusionauth.jwt.Verifier
     * @see io.fusionauth.jwt.domain.JWT
     */
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
