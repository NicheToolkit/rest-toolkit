package io.github.nichetoolkit.rest.controller;

import io.fusionauth.jwt.domain.JWT;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestNote;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.image.ImageVerify;
import io.github.nichetoolkit.rest.image.ImageUtils;
import io.github.nichetoolkit.rest.worker.jwt.JwtWorker;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.Md5Worker;
import io.github.nichetoolkit.rest.worker.RadixWorker;
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
 * <p>RestTestController</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@RestNote
@RestController
@RequestMapping("/v1.0.0/rest")
public class RestTestController {

    @Autowired
    private RadixWorker radixWorker;

    @Autowired
    private Md5Worker md5Worker;

    @Autowired
    private JwtWorker jwtWorker;

    @GetMapping("/identity")
    public ResponseEntity<RestResult<String>> generalIdentity() throws RestException {
//        Long identity = IdentityUtils.generateLong();
        String identity = IdentityUtils.generateString();
        return RestResult.ok(RestErrorStatus.SUCCESS, identity);
    }

    @GetMapping("/uuid")
    public ResponseEntity<RestResult<String>> generalUuid() throws RestException {
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String uuid = GeneralUtils.uuid();
        return RestResult.ok(RestErrorStatus.SUCCESS, uuid);
    }

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

    @GetMapping("/md5")
    public ResponseEntity<RestResult<Map<String, Object>>> generalMd5() throws RestException {
        String password = GeneralUtils.uuid();
//        String encryptPassword = Md5Worker.encrypts(password);
        String encryptPassword = md5Worker.encrypt(password);
        Map<String, Object> result = new HashMap<>(2);
        result.put("password", password);
        result.put("encryptPassword", encryptPassword);
        return RestResult.ok(RestErrorStatus.SUCCESS, result);
    }

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
