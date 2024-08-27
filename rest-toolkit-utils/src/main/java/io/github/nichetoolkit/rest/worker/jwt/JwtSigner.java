package io.github.nichetoolkit.rest.worker.jwt;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.Algorithm;
import io.fusionauth.security.CryptoProvider;
import io.github.nichetoolkit.rest.RestAlgorithm;
import io.github.nichetoolkit.rest.error.lack.InterfaceLackError;

import java.nio.file.Path;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * <code>JwtSigner</code>
 * <p>The type jwt signer interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestAlgorithm
 * @since Jdk1.8
 */
public interface JwtSigner extends RestAlgorithm<Algorithm> {

    /**
     * <code>signer</code>
     * <p>the method.</p>
     * @return {@link io.fusionauth.jwt.Signer} <p>the return object is <code>Signer</code> type.</p>
     * @see io.fusionauth.jwt.Signer
     */
    default Signer signer() {
        throw new InterfaceLackError();
    }

    /**
     * <code>signer</code>
     * <p>the method.</p>
     * @param secret {@link byte} <p>the secret parameter is <code>byte</code> type.</p>
     * @return {@link io.fusionauth.jwt.Signer} <p>the return object is <code>Signer</code> type.</p>
     * @see io.fusionauth.jwt.Signer
     */
    default Signer signer(byte[] secret) {
        throw new InterfaceLackError();
    }

    /**
     * <code>signer</code>
     * <p>the method.</p>
     * @param secret {@link java.lang.String} <p>the secret parameter is <code>String</code> type.</p>
     * @return {@link io.fusionauth.jwt.Signer} <p>the return object is <code>Signer</code> type.</p>
     * @see java.lang.String
     * @see io.fusionauth.jwt.Signer
     */
    default Signer signer(String secret) {
        throw new InterfaceLackError();
    }

    /**
     * <code>signer</code>
     * <p>the method.</p>
     * @param secret {@link byte} <p>the secret parameter is <code>byte</code> type.</p>
     * @param kid    {@link java.lang.String} <p>the kid parameter is <code>String</code> type.</p>
     * @return {@link io.fusionauth.jwt.Signer} <p>the return object is <code>Signer</code> type.</p>
     * @see java.lang.String
     * @see io.fusionauth.jwt.Signer
     */
    default Signer signer(byte[] secret, String kid) {
        throw new InterfaceLackError();
    }

    /**
     * <code>signer</code>
     * <p>the method.</p>
     * @param secret {@link java.lang.String} <p>the secret parameter is <code>String</code> type.</p>
     * @param kid    {@link java.lang.String} <p>the kid parameter is <code>String</code> type.</p>
     * @return {@link io.fusionauth.jwt.Signer} <p>the return object is <code>Signer</code> type.</p>
     * @see java.lang.String
     * @see io.fusionauth.jwt.Signer
     */
    default Signer signer(String secret, String kid) {
        throw new InterfaceLackError();
    }

    /**
     * <code>signer</code>
     * <p>the method.</p>
     * @param secret         {@link java.lang.String} <p>the secret parameter is <code>String</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link io.fusionauth.jwt.Signer} <p>the return object is <code>Signer</code> type.</p>
     * @see java.lang.String
     * @see io.fusionauth.security.CryptoProvider
     * @see io.fusionauth.jwt.Signer
     */
    default Signer signer(String secret, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    /**
     * <code>signer</code>
     * <p>the method.</p>
     * @param secret         {@link byte} <p>the secret parameter is <code>byte</code> type.</p>
     * @param kid            {@link java.lang.String} <p>the kid parameter is <code>String</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link io.fusionauth.jwt.Signer} <p>the return object is <code>Signer</code> type.</p>
     * @see java.lang.String
     * @see io.fusionauth.security.CryptoProvider
     * @see io.fusionauth.jwt.Signer
     */
    default Signer signer(byte[] secret, String kid, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    /**
     * <code>signer</code>
     * <p>the method.</p>
     * @param secret         {@link java.lang.String} <p>the secret parameter is <code>String</code> type.</p>
     * @param kid            {@link java.lang.String} <p>the kid parameter is <code>String</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link io.fusionauth.jwt.Signer} <p>the return object is <code>Signer</code> type.</p>
     * @see java.lang.String
     * @see io.fusionauth.security.CryptoProvider
     * @see io.fusionauth.jwt.Signer
     */
    default Signer signer(String secret, String kid, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    /**
     * <code>signer</code>
     * <p>the method.</p>
     * @param privateKey {@link java.security.PrivateKey} <p>the private key parameter is <code>PrivateKey</code> type.</p>
     * @return {@link io.fusionauth.jwt.Signer} <p>the return object is <code>Signer</code> type.</p>
     * @see java.security.PrivateKey
     * @see io.fusionauth.jwt.Signer
     */
    default Signer signer(PrivateKey privateKey) {
        throw new InterfaceLackError();
    }

    /**
     * <code>signer</code>
     * <p>the method.</p>
     * @param privateKey {@link java.security.PrivateKey} <p>the private key parameter is <code>PrivateKey</code> type.</p>
     * @param kid        {@link java.lang.String} <p>the kid parameter is <code>String</code> type.</p>
     * @return {@link io.fusionauth.jwt.Signer} <p>the return object is <code>Signer</code> type.</p>
     * @see java.security.PrivateKey
     * @see java.lang.String
     * @see io.fusionauth.jwt.Signer
     */
    default Signer signer(PrivateKey privateKey, String kid) {
        throw new InterfaceLackError();
    }

    /**
     * <code>signer</code>
     * <p>the method.</p>
     * @param privateKey     {@link java.security.PrivateKey} <p>the private key parameter is <code>PrivateKey</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link io.fusionauth.jwt.Signer} <p>the return object is <code>Signer</code> type.</p>
     * @see java.security.PrivateKey
     * @see io.fusionauth.security.CryptoProvider
     * @see io.fusionauth.jwt.Signer
     */
    default Signer signer(PrivateKey privateKey, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    /**
     * <code>signer</code>
     * <p>the method.</p>
     * @param privateKey     {@link java.security.PrivateKey} <p>the private key parameter is <code>PrivateKey</code> type.</p>
     * @param kid            {@link java.lang.String} <p>the kid parameter is <code>String</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link io.fusionauth.jwt.Signer} <p>the return object is <code>Signer</code> type.</p>
     * @see java.security.PrivateKey
     * @see java.lang.String
     * @see io.fusionauth.security.CryptoProvider
     * @see io.fusionauth.jwt.Signer
     */
    default Signer signer(PrivateKey privateKey, String kid, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    /**
     * <code>verifier</code>
     * <p>the method.</p>
     * @return {@link io.fusionauth.jwt.Verifier} <p>the return object is <code>Verifier</code> type.</p>
     * @see io.fusionauth.jwt.Verifier
     */
    default Verifier verifier() {
        throw new InterfaceLackError();
    }

    /**
     * <code>verifier</code>
     * <p>the method.</p>
     * @param secret {@link java.lang.String} <p>the secret parameter is <code>String</code> type.</p>
     * @return {@link io.fusionauth.jwt.Verifier} <p>the return object is <code>Verifier</code> type.</p>
     * @see java.lang.String
     * @see io.fusionauth.jwt.Verifier
     */
    default Verifier verifier(String secret) {
        throw new InterfaceLackError();
    }

    /**
     * <code>verifier</code>
     * <p>the method.</p>
     * @param publicKey {@link java.security.PublicKey} <p>the public key parameter is <code>PublicKey</code> type.</p>
     * @return {@link io.fusionauth.jwt.Verifier} <p>the return object is <code>Verifier</code> type.</p>
     * @see java.security.PublicKey
     * @see io.fusionauth.jwt.Verifier
     */
    default Verifier verifier(PublicKey publicKey) {
        throw new InterfaceLackError();
    }

    /**
     * <code>verifier</code>
     * <p>the method.</p>
     * @param path {@link java.nio.file.Path} <p>the path parameter is <code>Path</code> type.</p>
     * @return {@link io.fusionauth.jwt.Verifier} <p>the return object is <code>Verifier</code> type.</p>
     * @see java.nio.file.Path
     * @see io.fusionauth.jwt.Verifier
     */
    default Verifier verifier(Path path) {
        throw new InterfaceLackError();
    }

    /**
     * <code>verifier</code>
     * <p>the method.</p>
     * @param bytes {@link byte} <p>the bytes parameter is <code>byte</code> type.</p>
     * @return {@link io.fusionauth.jwt.Verifier} <p>the return object is <code>Verifier</code> type.</p>
     * @see io.fusionauth.jwt.Verifier
     */
    default Verifier verifier(byte[] bytes) {
        throw new InterfaceLackError();
    }

    /**
     * <code>verifier</code>
     * <p>the method.</p>
     * @param secret         {@link java.lang.String} <p>the secret parameter is <code>String</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link io.fusionauth.jwt.Verifier} <p>the return object is <code>Verifier</code> type.</p>
     * @see java.lang.String
     * @see io.fusionauth.security.CryptoProvider
     * @see io.fusionauth.jwt.Verifier
     */
    default Verifier verifier(String secret, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    /**
     * <code>verifier</code>
     * <p>the method.</p>
     * @param publicKey      {@link java.security.PublicKey} <p>the public key parameter is <code>PublicKey</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link io.fusionauth.jwt.Verifier} <p>the return object is <code>Verifier</code> type.</p>
     * @see java.security.PublicKey
     * @see io.fusionauth.security.CryptoProvider
     * @see io.fusionauth.jwt.Verifier
     */
    default Verifier verifier(PublicKey publicKey, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    /**
     * <code>verifier</code>
     * <p>the method.</p>
     * @param path           {@link java.nio.file.Path} <p>the path parameter is <code>Path</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link io.fusionauth.jwt.Verifier} <p>the return object is <code>Verifier</code> type.</p>
     * @see java.nio.file.Path
     * @see io.fusionauth.security.CryptoProvider
     * @see io.fusionauth.jwt.Verifier
     */
    default Verifier verifier(Path path, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    /**
     * <code>verifier</code>
     * <p>the method.</p>
     * @param bytes          {@link byte} <p>the bytes parameter is <code>byte</code> type.</p>
     * @param cryptoProvider {@link io.fusionauth.security.CryptoProvider} <p>the crypto provider parameter is <code>CryptoProvider</code> type.</p>
     * @return {@link io.fusionauth.jwt.Verifier} <p>the return object is <code>Verifier</code> type.</p>
     * @see io.fusionauth.security.CryptoProvider
     * @see io.fusionauth.jwt.Verifier
     */
    default Verifier verifier(byte[] bytes, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }
}
