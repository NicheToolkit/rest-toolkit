package io.github.nichetoolkit.rest.worker.jwt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.UnsecuredSigner;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.Algorithm;
import io.fusionauth.jwt.ec.ECSigner;
import io.fusionauth.jwt.ec.ECVerifier;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import io.fusionauth.jwt.rsa.RSAPSSSigner;
import io.fusionauth.jwt.rsa.RSAPSSVerifier;
import io.fusionauth.jwt.rsa.RSASigner;
import io.fusionauth.jwt.rsa.RSAVerifier;
import io.fusionauth.security.CryptoProvider;
import io.github.nichetoolkit.rest.RestValue;

import java.nio.file.Path;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Optional;

/**
 * <code>JwtAlgorithm</code>
 * <p>The type jwt algorithm enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.worker.jwt.JwtSigner
 * @since Jdk1.8
 */
public enum JwtAlgorithm implements JwtSigner {

    /**
     * <code>NONE</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the <code>NONE</code> field.</p>
     */
    NONE(0, "none", Algorithm.none) {
        @Override
        public final Signer signer() {
            UnsecuredSigner signer = new UnsecuredSigner();
            setSigner(signer);
            return signer;
        }
    },

    /**
     * <code>ES256</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the <code>ES256</code> field.</p>
     */
    ES256(1, "ES256", Algorithm.ES256) {
        @Override
        public final Signer signer(String secret) {
            ECSigner signer = ECSigner.newSHA256Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid) {
            ECSigner signer = ECSigner.newSHA256Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, CryptoProvider cryptoProvider) {
            ECSigner signer = ECSigner.newSHA256Signer(secret, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid, CryptoProvider cryptoProvider) {
            ECSigner signer = ECSigner.newSHA256Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey) {
            ECSigner signer = ECSigner.newSHA256Signer(privateKey);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid) {
            ECSigner signer = ECSigner.newSHA256Signer(privateKey, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, CryptoProvider cryptoProvider) {
            ECSigner signer = ECSigner.newSHA256Signer(privateKey, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid, CryptoProvider cryptoProvider) {
            ECSigner signer = ECSigner.newSHA256Signer(privateKey, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Verifier verifier(String secret) {
            ECVerifier verifier = ECVerifier.newVerifier(secret);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey) {
            ECVerifier verifier = ECVerifier.newVerifier(publicKey);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path) {
            ECVerifier verifier = ECVerifier.newVerifier(path);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes) {
            ECVerifier verifier = ECVerifier.newVerifier(bytes);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(String secret, CryptoProvider cryptoProvider) {
            ECVerifier verifier = ECVerifier.newVerifier(secret, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey, CryptoProvider cryptoProvider) {
            ECVerifier verifier = ECVerifier.newVerifier(publicKey, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path, CryptoProvider cryptoProvider) {
            ECVerifier verifier = ECVerifier.newVerifier(path, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes, CryptoProvider cryptoProvider) {
            ECVerifier verifier = ECVerifier.newVerifier(bytes, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }
    },
    /**
     * <code>ES384</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the <code>ES384</code> field.</p>
     */
    ES384(2, "ES384", Algorithm.ES384) {
        @Override
        public final Signer signer(String secret) {
            ECSigner signer = ECSigner.newSHA384Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid) {
            ECSigner signer = ECSigner.newSHA384Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, CryptoProvider cryptoProvider) {
            ECSigner signer = ECSigner.newSHA384Signer(secret, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid, CryptoProvider cryptoProvider) {
            ECSigner signer = ECSigner.newSHA384Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey) {
            ECSigner signer = ECSigner.newSHA384Signer(privateKey);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid) {
            ECSigner signer = ECSigner.newSHA384Signer(privateKey, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, CryptoProvider cryptoProvider) {
            ECSigner signer = ECSigner.newSHA384Signer(privateKey, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid, CryptoProvider cryptoProvider) {
            ECSigner signer = ECSigner.newSHA384Signer(privateKey, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Verifier verifier(String secret) {
            ECVerifier verifier = ECVerifier.newVerifier(secret);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey) {
            ECVerifier verifier = ECVerifier.newVerifier(publicKey);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path) {
            ECVerifier verifier = ECVerifier.newVerifier(path);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes) {
            ECVerifier verifier = ECVerifier.newVerifier(bytes);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(String secret, CryptoProvider cryptoProvider) {
            ECVerifier verifier = ECVerifier.newVerifier(secret, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey, CryptoProvider cryptoProvider) {
            ECVerifier verifier = ECVerifier.newVerifier(publicKey, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path, CryptoProvider cryptoProvider) {
            ECVerifier verifier = ECVerifier.newVerifier(path, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes, CryptoProvider cryptoProvider) {
            ECVerifier verifier = ECVerifier.newVerifier(bytes, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }
    },
    /**
     * <code>ES512</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the <code>ES512</code> field.</p>
     */
    ES512(3, "ES512", Algorithm.ES512) {
        @Override
        public final Signer signer(String secret) {
            ECSigner signer = ECSigner.newSHA512Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid) {
            ECSigner signer = ECSigner.newSHA512Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, CryptoProvider cryptoProvider) {
            ECSigner signer = ECSigner.newSHA512Signer(secret, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid, CryptoProvider cryptoProvider) {
            ECSigner signer = ECSigner.newSHA512Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey) {
            ECSigner signer = ECSigner.newSHA512Signer(privateKey);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid) {
            ECSigner signer = ECSigner.newSHA512Signer(privateKey, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, CryptoProvider cryptoProvider) {
            ECSigner signer = ECSigner.newSHA512Signer(privateKey, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid, CryptoProvider cryptoProvider) {
            ECSigner signer = ECSigner.newSHA512Signer(privateKey, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Verifier verifier(String secret) {
            ECVerifier verifier = ECVerifier.newVerifier(secret);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey) {
            ECVerifier verifier = ECVerifier.newVerifier(publicKey);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path) {
            ECVerifier verifier = ECVerifier.newVerifier(path);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes) {
            ECVerifier verifier = ECVerifier.newVerifier(bytes);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(String secret, CryptoProvider cryptoProvider) {
            ECVerifier verifier = ECVerifier.newVerifier(secret, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey, CryptoProvider cryptoProvider) {
            ECVerifier verifier = ECVerifier.newVerifier(publicKey, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path, CryptoProvider cryptoProvider) {
            ECVerifier verifier = ECVerifier.newVerifier(path, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes, CryptoProvider cryptoProvider) {
            ECVerifier verifier = ECVerifier.newVerifier(bytes, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }
    },
    /**
     * <code>HS256</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the <code>HS256</code> field.</p>
     */
    HS256(4, "HS256", Algorithm.HS256) {
        @Override
        public final Signer signer(byte[] secret) {
            HMACSigner signer = HMACSigner.newSHA256Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret) {
            HMACSigner signer = HMACSigner.newSHA256Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(byte[] secret, String kid) {
            HMACSigner signer = HMACSigner.newSHA256Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid) {
            HMACSigner signer = HMACSigner.newSHA256Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, CryptoProvider cryptoProvider) {
            HMACSigner signer = HMACSigner.newSHA256Signer(secret, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(byte[] secret, String kid, CryptoProvider cryptoProvider) {
            HMACSigner signer = HMACSigner.newSHA256Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid, CryptoProvider cryptoProvider) {
            HMACSigner signer = HMACSigner.newSHA256Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Verifier verifier(String secret) {
            HMACVerifier verifier = HMACVerifier.newVerifier(secret);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path) {
            HMACVerifier verifier = HMACVerifier.newVerifier(path);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes) {
            HMACVerifier verifier = HMACVerifier.newVerifier(bytes);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(String secret, CryptoProvider cryptoProvider) {
            HMACVerifier verifier = HMACVerifier.newVerifier(secret, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path, CryptoProvider cryptoProvider) {
            HMACVerifier verifier = HMACVerifier.newVerifier(path, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes, CryptoProvider cryptoProvider) {
            HMACVerifier verifier = HMACVerifier.newVerifier(bytes, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }
    },
    /**
     * <code>HS384</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the <code>HS384</code> field.</p>
     */
    HS384(5, "HS384", Algorithm.HS384) {
        @Override
        public final Signer signer(byte[] secret) {
            HMACSigner signer = HMACSigner.newSHA384Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret) {
            HMACSigner signer = HMACSigner.newSHA384Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(byte[] secret, String kid) {
            HMACSigner signer = HMACSigner.newSHA384Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid) {
            HMACSigner signer = HMACSigner.newSHA384Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, CryptoProvider cryptoProvider) {
            HMACSigner signer = HMACSigner.newSHA384Signer(secret, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(byte[] secret, String kid, CryptoProvider cryptoProvider) {
            HMACSigner signer = HMACSigner.newSHA384Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid, CryptoProvider cryptoProvider) {
            HMACSigner signer = HMACSigner.newSHA384Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Verifier verifier(String secret) {
            HMACVerifier verifier = HMACVerifier.newVerifier(secret);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path) {
            HMACVerifier verifier = HMACVerifier.newVerifier(path);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes) {
            HMACVerifier verifier = HMACVerifier.newVerifier(bytes);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(String secret, CryptoProvider cryptoProvider) {
            HMACVerifier verifier = HMACVerifier.newVerifier(secret, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path, CryptoProvider cryptoProvider) {
            HMACVerifier verifier = HMACVerifier.newVerifier(path, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes, CryptoProvider cryptoProvider) {
            HMACVerifier verifier = HMACVerifier.newVerifier(bytes, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }
    },
    /**
     * <code>HS512</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the <code>HS512</code> field.</p>
     */
    HS512(6, "HS512", Algorithm.HS512) {
        @Override
        public final Signer signer(byte[] secret) {
            HMACSigner signer = HMACSigner.newSHA512Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret) {
            HMACSigner signer = HMACSigner.newSHA512Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(byte[] secret, String kid) {
            HMACSigner signer = HMACSigner.newSHA512Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid) {
            HMACSigner signer = HMACSigner.newSHA512Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, CryptoProvider cryptoProvider) {
            HMACSigner signer = HMACSigner.newSHA512Signer(secret, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(byte[] secret, String kid, CryptoProvider cryptoProvider) {
            HMACSigner signer = HMACSigner.newSHA512Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid, CryptoProvider cryptoProvider) {
            HMACSigner signer = HMACSigner.newSHA512Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Verifier verifier(String secret) {
            HMACVerifier verifier = HMACVerifier.newVerifier(secret);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path) {
            HMACVerifier verifier = HMACVerifier.newVerifier(path);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes) {
            HMACVerifier verifier = HMACVerifier.newVerifier(bytes);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(String secret, CryptoProvider cryptoProvider) {
            HMACVerifier verifier = HMACVerifier.newVerifier(secret, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path, CryptoProvider cryptoProvider) {
            HMACVerifier verifier = HMACVerifier.newVerifier(path, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes, CryptoProvider cryptoProvider) {
            HMACVerifier verifier = HMACVerifier.newVerifier(bytes, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }
    },
    /**
     * <code>PS256</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the <code>PS256</code> field.</p>
     */
    PS256(7, "PS256", Algorithm.PS256) {
        @Override
        public final Signer signer(String secret) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA256Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA256Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, CryptoProvider cryptoProvider) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA256Signer(secret, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid, CryptoProvider cryptoProvider) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA256Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA256Signer(privateKey);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA256Signer(privateKey, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, CryptoProvider cryptoProvider) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA256Signer(privateKey, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid, CryptoProvider cryptoProvider) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA256Signer(privateKey, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Verifier verifier(String secret) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(secret);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(publicKey);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(path);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(bytes);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(String secret, CryptoProvider cryptoProvider) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(secret, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey, CryptoProvider cryptoProvider) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(publicKey, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path, CryptoProvider cryptoProvider) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(path, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

    },
    /**
     * <code>PS384</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the <code>PS384</code> field.</p>
     */
    PS384(8, "PS384", Algorithm.PS384) {
        @Override
        public final Signer signer(String secret) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA384Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA384Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, CryptoProvider cryptoProvider) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA384Signer(secret, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid, CryptoProvider cryptoProvider) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA384Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA384Signer(privateKey);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA384Signer(privateKey, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, CryptoProvider cryptoProvider) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA384Signer(privateKey, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid, CryptoProvider cryptoProvider) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA384Signer(privateKey, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Verifier verifier(String secret) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(secret);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(publicKey);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(path);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(bytes);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(String secret, CryptoProvider cryptoProvider) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(secret, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey, CryptoProvider cryptoProvider) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(publicKey, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path, CryptoProvider cryptoProvider) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(path, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }
    },
    /**
     * <code>PS512</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the <code>PS512</code> field.</p>
     */
    PS512(9, "PS512", Algorithm.PS512) {
        @Override
        public final Signer signer(String secret) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA512Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA512Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, CryptoProvider cryptoProvider) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA512Signer(secret, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid, CryptoProvider cryptoProvider) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA512Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA512Signer(privateKey);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA512Signer(privateKey, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, CryptoProvider cryptoProvider) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA512Signer(privateKey, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid, CryptoProvider cryptoProvider) {
            RSAPSSSigner signer = RSAPSSSigner.newSHA512Signer(privateKey, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Verifier verifier(String secret) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(secret);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(publicKey);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(path);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(bytes);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(String secret, CryptoProvider cryptoProvider) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(secret, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey, CryptoProvider cryptoProvider) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(publicKey, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path, CryptoProvider cryptoProvider) {
            RSAPSSVerifier verifier = RSAPSSVerifier.newVerifier(path, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }
    },
    /**
     * <code>RS256</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the <code>RS256</code> field.</p>
     */
    RS256(10, "RS256", Algorithm.RS256) {
        @Override
        public final Signer signer(String secret) {
            RSASigner signer = RSASigner.newSHA256Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid) {
            RSASigner signer = RSASigner.newSHA256Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, CryptoProvider cryptoProvider) {
            RSASigner signer = RSASigner.newSHA256Signer(secret, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid, CryptoProvider cryptoProvider) {
            RSASigner signer = RSASigner.newSHA256Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey) {
            RSASigner signer = RSASigner.newSHA256Signer(privateKey);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid) {
            RSASigner signer = RSASigner.newSHA256Signer(privateKey, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, CryptoProvider cryptoProvider) {
            RSASigner signer = RSASigner.newSHA256Signer(privateKey, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid, CryptoProvider cryptoProvider) {
            RSASigner signer = RSASigner.newSHA256Signer(privateKey, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Verifier verifier(String secret) {
            RSAVerifier verifier = RSAVerifier.newVerifier(secret);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey) {
            RSAVerifier verifier = RSAVerifier.newVerifier(publicKey);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path) {
            RSAVerifier verifier = RSAVerifier.newVerifier(path);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes) {
            RSAVerifier verifier = RSAVerifier.newVerifier(bytes);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(String secret, CryptoProvider cryptoProvider) {
            RSAVerifier verifier = RSAVerifier.newVerifier(secret, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey, CryptoProvider cryptoProvider) {
            RSAVerifier verifier = RSAVerifier.newVerifier(publicKey, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path, CryptoProvider cryptoProvider) {
            RSAVerifier verifier = RSAVerifier.newVerifier(path, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }
    },
    /**
     * <code>RS384</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the <code>RS384</code> field.</p>
     */
    RS384(11, "RS384", Algorithm.RS384) {
        @Override
        public final Signer signer(String secret) {
            RSASigner signer = RSASigner.newSHA384Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid) {
            RSASigner signer = RSASigner.newSHA384Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, CryptoProvider cryptoProvider) {
            RSASigner signer = RSASigner.newSHA384Signer(secret, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid, CryptoProvider cryptoProvider) {
            RSASigner signer = RSASigner.newSHA384Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey) {
            RSASigner signer = RSASigner.newSHA384Signer(privateKey);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid) {
            RSASigner signer = RSASigner.newSHA384Signer(privateKey, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, CryptoProvider cryptoProvider) {
            RSASigner signer = RSASigner.newSHA384Signer(privateKey, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid, CryptoProvider cryptoProvider) {
            RSASigner signer = RSASigner.newSHA384Signer(privateKey, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Verifier verifier(String secret) {
            RSAVerifier verifier = RSAVerifier.newVerifier(secret);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey) {
            RSAVerifier verifier = RSAVerifier.newVerifier(publicKey);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path) {
            RSAVerifier verifier = RSAVerifier.newVerifier(path);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes) {
            RSAVerifier verifier = RSAVerifier.newVerifier(bytes);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(String secret, CryptoProvider cryptoProvider) {
            RSAVerifier verifier = RSAVerifier.newVerifier(secret, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey, CryptoProvider cryptoProvider) {
            RSAVerifier verifier = RSAVerifier.newVerifier(publicKey, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path, CryptoProvider cryptoProvider) {
            RSAVerifier verifier = RSAVerifier.newVerifier(path, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }
    },
    /**
     * <code>RS512</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the <code>RS512</code> field.</p>
     */
    RS512(12, "RS512", Algorithm.RS512) {
        @Override
        public final Signer signer(String secret) {
            RSASigner signer = RSASigner.newSHA512Signer(secret);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid) {
            RSASigner signer = RSASigner.newSHA512Signer(secret, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, CryptoProvider cryptoProvider) {
            RSASigner signer = RSASigner.newSHA512Signer(secret, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(String secret, String kid, CryptoProvider cryptoProvider) {
            RSASigner signer = RSASigner.newSHA512Signer(secret, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey) {
            RSASigner signer = RSASigner.newSHA512Signer(privateKey);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid) {
            RSASigner signer = RSASigner.newSHA512Signer(privateKey, kid);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, CryptoProvider cryptoProvider) {
            RSASigner signer = RSASigner.newSHA512Signer(privateKey, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Signer signer(PrivateKey privateKey, String kid, CryptoProvider cryptoProvider) {
            RSASigner signer = RSASigner.newSHA512Signer(privateKey, kid, cryptoProvider);
            setSigner(signer);
            return signer;
        }

        @Override
        public final Verifier verifier(String secret) {
            RSAVerifier verifier = RSAVerifier.newVerifier(secret);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey) {
            RSAVerifier verifier = RSAVerifier.newVerifier(publicKey);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path) {
            RSAVerifier verifier = RSAVerifier.newVerifier(path);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(byte[] bytes) {
            RSAVerifier verifier = RSAVerifier.newVerifier(bytes);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(String secret, CryptoProvider cryptoProvider) {
            RSAVerifier verifier = RSAVerifier.newVerifier(secret, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(PublicKey publicKey, CryptoProvider cryptoProvider) {
            RSAVerifier verifier = RSAVerifier.newVerifier(publicKey, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }

        @Override
        public final Verifier verifier(Path path, CryptoProvider cryptoProvider) {
            RSAVerifier verifier = RSAVerifier.newVerifier(path, cryptoProvider);
            setVerifier(verifier);
            return verifier;
        }
    },
    ;

    /**
     * <code>key</code>
     * {@link java.lang.Integer} <p>the <code>key</code> field.</p>
     * @see java.lang.Integer
     */
    private final Integer key;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>the <code>value</code> field.</p>
     * @see java.lang.String
     */
    private final String value;
    /**
     * <code>algorithm</code>
     * {@link io.fusionauth.jwt.domain.Algorithm} <p>the <code>algorithm</code> field.</p>
     * @see io.fusionauth.jwt.domain.Algorithm
     */
    private final Algorithm algorithm;
    /**
     * <code>signer</code>
     * {@link io.fusionauth.jwt.Signer} <p>the <code>signer</code> field.</p>
     * @see io.fusionauth.jwt.Signer
     */
    private Signer signer;
    /**
     * <code>verifier</code>
     * {@link io.fusionauth.jwt.Verifier} <p>the <code>verifier</code> field.</p>
     * @see io.fusionauth.jwt.Verifier
     */
    private Verifier verifier;

    /**
     * <code>JwtAlgorithm</code>
     * Instantiates a new jwt algorithm.
     * @param key       {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @param value     {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @param algorithm {@link io.fusionauth.jwt.domain.Algorithm} <p>the algorithm parameter is <code>Algorithm</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.fusionauth.jwt.domain.Algorithm
     */
    JwtAlgorithm(Integer key, String value, Algorithm algorithm) {
        this.key = key;
        this.value = value;
        this.algorithm = algorithm;
    }

    @Override
    public Integer getKey() {
        return this.key;
    }

    @JsonValue
    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public Algorithm getAlgorithm() {
        return this.algorithm;
    }

    /**
     * <code>setSigner</code>
     * <p>the signer setter method.</p>
     * @param signer {@link io.fusionauth.jwt.Signer} <p>the signer parameter is <code>Signer</code> type.</p>
     * @see io.fusionauth.jwt.Signer
     */
    protected void setSigner(Signer signer) {
        this.signer = signer;
    }

    /**
     * <code>getSigner</code>
     * <p>the signer getter method.</p>
     * @return {@link io.fusionauth.jwt.Signer} <p>the signer return object is <code>Signer</code> type.</p>
     * @see io.fusionauth.jwt.Signer
     */
    public final Signer getSigner() {
        return signer;
    }

    /**
     * <code>getVerifier</code>
     * <p>the verifier getter method.</p>
     * @return {@link io.fusionauth.jwt.Verifier} <p>the verifier return object is <code>Verifier</code> type.</p>
     * @see io.fusionauth.jwt.Verifier
     */
    public final Verifier getVerifier() {
        return verifier;
    }

    /**
     * <code>setVerifier</code>
     * <p>the verifier setter method.</p>
     * @param verifier {@link io.fusionauth.jwt.Verifier} <p>the verifier parameter is <code>Verifier</code> type.</p>
     * @see io.fusionauth.jwt.Verifier
     */
    protected void setVerifier(Verifier verifier) {
        this.verifier = verifier;
    }

    /**
     * <code>parseKey</code>
     * <p>the key method.</p>
     * @param key {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the key return object is <code>JwtAlgorithm</code> type.</p>
     * @see java.lang.Integer
     */
    public static JwtAlgorithm parseKey(Integer key) {
        JwtAlgorithm sortTypeEnum = RestValue.parseKey(JwtAlgorithm.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(JwtAlgorithm.HS256);
    }

    /**
     * <code>parseValue</code>
     * <p>the value method.</p>
     * @param value {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtAlgorithm} <p>the value return object is <code>JwtAlgorithm</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static JwtAlgorithm parseValue(String value) {
        JwtAlgorithm sortTypeEnum = RestValue.parseValue(JwtAlgorithm.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(JwtAlgorithm.HS256);
    }


}
