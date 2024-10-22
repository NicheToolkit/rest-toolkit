package io.github.nichetoolkit.rest.controller;

import io.fusionauth.jwt.domain.JWT;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.image.ImageUtils;
import io.github.nichetoolkit.rest.image.ImageVerify;
import io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.RadixWorker;
import io.github.nichetoolkit.rest.worker.jwt.JwtWorker;
import io.github.nichetoolkit.rest.worker.sha.ShaWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * <code>RestTestController</code>
 * <p>The type rest test controller class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog
 * @see org.springframework.web.bind.annotation.RestController
 * @see java.lang.SuppressWarnings
 * @see org.springframework.web.bind.annotation.RequestMapping
 * @since Jdk1.8
 */
@Slf4j
@RestNotelog
@RestController
@SuppressWarnings("SameNameButDifferent")
@RequestMapping("/v1.1.0/rest")
public class RestTestController {

    /**
     * <code>radixWorker</code>
     * {@link io.github.nichetoolkit.rest.worker.RadixWorker} <p>The <code>radixWorker</code> field.</p>
     * @see io.github.nichetoolkit.rest.worker.RadixWorker
     */
    private final RadixWorker radixWorker;

    /**
     * <code>shaWorker</code>
     * {@link io.github.nichetoolkit.rest.worker.sha.ShaWorker} <p>The <code>shaWorker</code> field.</p>
     * @see io.github.nichetoolkit.rest.worker.sha.ShaWorker
     */
    private final ShaWorker shaWorker;

    /**
     * <code>jwtWorker</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtWorker} <p>The <code>jwtWorker</code> field.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtWorker
     */
    private final JwtWorker jwtWorker;

    /**
     * <code>RestTestController</code>
     * <p>Instantiates a new rest test controller.</p>
     * @param radixWorker {@link io.github.nichetoolkit.rest.worker.RadixWorker} <p>The radix worker parameter is <code>RadixWorker</code> type.</p>
     * @param shaWorker   {@link io.github.nichetoolkit.rest.worker.sha.ShaWorker} <p>The sha worker parameter is <code>ShaWorker</code> type.</p>
     * @param jwtWorker   {@link io.github.nichetoolkit.rest.worker.jwt.JwtWorker} <p>The jwt worker parameter is <code>JwtWorker</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.RadixWorker
     * @see io.github.nichetoolkit.rest.worker.sha.ShaWorker
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtWorker
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RestTestController(RadixWorker radixWorker, ShaWorker shaWorker, JwtWorker jwtWorker) {
        this.radixWorker = radixWorker;
        this.shaWorker = shaWorker;
        this.jwtWorker = jwtWorker;
    }

    /**
     * <code>generalIdentity</code>
     * <p>The identity method.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The identity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.web.bind.annotation.GetMapping
     * @see io.github.nichetoolkit.rest.RestException
     */
    @GetMapping("/identity")
    public ResponseEntity<RestResult<String>> generalIdentity() throws RestException {
        String identity = IdentityUtils.valueOfString();
        return RestResult.ok(RestErrorStatus.SUCCESS, identity);
    }

    /**
     * <code>generalUuid</code>
     * <p>The uuid method.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The uuid return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.web.bind.annotation.GetMapping
     * @see io.github.nichetoolkit.rest.RestException
     */
    @GetMapping("/uuid")
    public ResponseEntity<RestResult<String>> generalUuid() throws RestException {
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String uuid = GeneralUtils.uuid();
        return RestResult.ok(RestErrorStatus.SUCCESS, uuid);
    }

    /**
     * <code>generalImage</code>
     * <p>The image method.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     * @see org.springframework.web.bind.annotation.GetMapping
     * @see io.github.nichetoolkit.rest.RestException
     */
    @GetMapping("/image")
    public void generalImage(HttpServletResponse response) throws RestException {
        ImageVerify imageVerify = ImageUtils.randoms();
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try {
            imageVerify.write(response.getOutputStream());
        } catch (IOException exception) {
            GeneralUtils.printStackTrace(log,exception,true);
        }
    }

    /**
     * <code>generalRadix</code>
     * <p>The radix method.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The radix return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.web.bind.annotation.GetMapping
     * @see io.github.nichetoolkit.rest.RestException
     */
    @GetMapping("/radix")
    public ResponseEntity<RestResult<Map<String, Object>>> generalRadix() throws RestException {
        Long subject = IdentityUtils.valueOfLong();
        String encryptSubject = radixWorker.encrypt(subject);
        Long decryptSubject = radixWorker.decrypt(encryptSubject);
        Map<String, Object> result = new HashMap<>(3);
        result.put("subject", subject);
        result.put("encryptSubject", encryptSubject);
        result.put("decryptSubject", decryptSubject);
        return RestResult.ok(RestErrorStatus.SUCCESS, result);
    }

    /**
     * <code>generalSha</code>
     * <p>The sha method.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The sha return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.web.bind.annotation.GetMapping
     * @see io.github.nichetoolkit.rest.RestException
     */
    @GetMapping("/sha")
    public ResponseEntity<RestResult<Map<String, Object>>> generalSha() throws RestException {
        String password = GeneralUtils.uuid();
//        String encryptPassword = Md5Worker.encrypts(password);
        String encryptPassword = shaWorker.encrypt(password);
        Map<String, Object> result = new HashMap<>(2);
        result.put("password", password);
        result.put("encryptPassword", encryptPassword);
        return RestResult.ok(RestErrorStatus.SUCCESS, result);
    }

    /**
     * <code>generalJwt</code>
     * <p>The jwt method.</p>
     * @return {@link org.springframework.http.ResponseEntity} <p>The jwt return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.web.bind.annotation.GetMapping
     * @see io.github.nichetoolkit.rest.RestException
     */
    @GetMapping("/jwt")
    public ResponseEntity<RestResult<Map<String, Object>>> generalJwt() throws RestException {
        String uniqueId = GeneralUtils.uuid();
        Long subject = IdentityUtils.valueOfLong();
//        String encryptSubject = RadixWorker.encrypts(subject);
        String encryptSubject = radixWorker.encrypt(subject);
//        Long decryptSubject = RadixWorker.decrypts(encryptSubject);
        Long decryptSubject = radixWorker.decrypt(encryptSubject);
        String token = jwtWorker.generate(uniqueId, encryptSubject);
//        String token = JwtWorker.token(uniqueId,encryptSubject);
        JWT jwt = jwtWorker.parser(token);
        Map<String, Object> result = new HashMap<>(6);
        result.put("uniqueId", uniqueId);
        result.put("subject", subject);
        result.put("encryptSubject", encryptSubject);
        result.put("decryptSubject", decryptSubject);
        result.put("token", token);
        result.put("jwt", jwt);
        return RestResult.ok(result);
    }

}
