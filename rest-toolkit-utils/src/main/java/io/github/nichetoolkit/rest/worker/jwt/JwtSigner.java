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
 * <p>RestSigner</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface JwtSigner extends RestAlgorithm<Algorithm> {

    default Signer signer() {
        throw new InterfaceLackError();
    }

    default Signer signer(byte[] secret) {
        throw new InterfaceLackError();
    }

    default Signer signer(String secret) {
        throw new InterfaceLackError();
    }

    default Signer signer(byte[] secret, String kid) {
        throw new InterfaceLackError();
    }

    default Signer signer(String secret, String kid) {
        throw new InterfaceLackError();
    }

    default Signer signer(String secret, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    default Signer signer(byte[] secret, String kid, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    default Signer signer(String secret, String kid, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    default Signer signer(PrivateKey privateKey) {
        throw new InterfaceLackError();
    }

    default Signer signer(PrivateKey privateKey, String kid) {
        throw new InterfaceLackError();
    }

    default Signer signer(PrivateKey privateKey, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    default Signer signer(PrivateKey privateKey, String kid, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    default Verifier verifier() {
        throw new InterfaceLackError();
    }

    default Verifier verifier(String secret) {
        throw new InterfaceLackError();
    }

    default Verifier verifier(PublicKey publicKey) {
        throw new InterfaceLackError();
    }

    default Verifier verifier(Path path) {
        throw new InterfaceLackError();
    }

    default Verifier verifier(byte[] bytes) {
        throw new InterfaceLackError();
    }

    default Verifier verifier(String secret, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    default Verifier verifier(PublicKey publicKey, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    default Verifier verifier(Path path, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }

    default Verifier verifier(byte[] bytes, CryptoProvider cryptoProvider) {
        throw new InterfaceLackError();
    }
}
