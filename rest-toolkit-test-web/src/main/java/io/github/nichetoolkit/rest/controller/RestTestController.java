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
import io.github.nichetoolkit.rest.worker.rsa.RsaWorker;
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
 * @see Slf4j
 * @see RestNotelog
 * @see RestController
 * @see SuppressWarnings
 * @see RequestMapping
 * @since Jdk1.8
 */
@Slf4j
@RestNotelog
@RestController
@SuppressWarnings("SameNameButDifferent")
@RequestMapping("/v1.0.0/rest")
public class RestTestController {

    /**
     * <code>radixWorker</code>
     * <p>the <code>radixWorker</code> field.</p>
     * @see Autowired
     */
    @Autowired
    private RadixWorker radixWorker;

    /**
     * <code>shaWorker</code>
     * <p>the <code>shaWorker</code> field.</p>
     * @see Autowired
     */
    @Autowired
    private ShaWorker shaWorker;

    /**
     * <code>jwtWorker</code>
     * <p>the <code>jwtWorker</code> field.</p>
     * @see Autowired
     */
    @Autowired
    private JwtWorker jwtWorker;

    /**
     * <code>rsaWorker</code>
     * <p>the <code>rsaWorker</code> field.</p>
     * @see Autowired
     */
    @Autowired
    private RsaWorker rsaWorker;

    /**
     * <code>test</code>
     * <p>the method.</p>
     * @return ResponseEntity <p>the return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException RestException <p>the rest exception is <code>RestException</code> type.</p>
     * @see GetMapping
     * @see RestException
     */
    @GetMapping("/test")
    public ResponseEntity<RestResult> test() throws RestException {
        Object test = null;
        test.toString();
        return RestResult.ok("11111");
    }

    /**
     * <code>generalIdentity</code>
     * <p>the identity method.</p>
     * @return ResponseEntity <p>the identity return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException RestException <p>the rest exception is <code>RestException</code> type.</p>
     * @see GetMapping
     * @see RestException
     */
    @GetMapping("/identity")
    public ResponseEntity<RestResult<String>> generalIdentity() throws RestException {
//        Long identity = IdentityUtils.generateLong();
        String identity = IdentityUtils.generateString();
        return RestResult.ok(RestErrorStatus.SUCCESS, identity);
    }

    /**
     * <code>generalUuid</code>
     * <p>the uuid method.</p>
     * @return ResponseEntity <p>the uuid return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException RestException <p>the rest exception is <code>RestException</code> type.</p>
     * @see GetMapping
     * @see RestException
     */
    @GetMapping("/uuid")
    public ResponseEntity<RestResult<String>> generalUuid() throws RestException {
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String uuid = GeneralUtils.uuid();
        return RestResult.ok(RestErrorStatus.SUCCESS, uuid);
    }

    /**
     * <code>generalImage</code>
     * <p>the image method.</p>
     * @param response HttpServletResponse <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @throws RestException RestException <p>the rest exception is <code>RestException</code> type.</p>
     * @see GetMapping
     * @see RestException
     */
    @GetMapping("/image")
    public void generalImage(HttpServletResponse response) throws RestException {
        ImageVerify imageVerify = ImageUtils.randoms();
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try {
            imageVerify.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <code>generalRadix</code>
     * <p>the radix method.</p>
     * @return ResponseEntity <p>the radix return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException RestException <p>the rest exception is <code>RestException</code> type.</p>
     * @see GetMapping
     * @see RestException
     */
    @GetMapping("/radix")
    public ResponseEntity<RestResult<Map<String, Object>>> generalRadix() throws RestException {
        Long subject = IdentityUtils.generateLong();
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
     * <p>the sha method.</p>
     * @return ResponseEntity <p>the sha return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException RestException <p>the rest exception is <code>RestException</code> type.</p>
     * @see GetMapping
     * @see RestException
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
     * <p>the jwt method.</p>
     * @return ResponseEntity <p>the jwt return object is <code>ResponseEntity</code> type.</p>
     * @throws RestException RestException <p>the rest exception is <code>RestException</code> type.</p>
     * @see GetMapping
     * @see RestException
     */
    @GetMapping("/jwt")
    public ResponseEntity<RestResult<Map<String, Object>>> generalJwt() throws RestException {
        String uniqueId = GeneralUtils.uuid();
        Long subject = IdentityUtils.generateLong();
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
