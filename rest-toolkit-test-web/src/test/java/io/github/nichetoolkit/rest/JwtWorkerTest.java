package io.github.nichetoolkit.rest;

import io.fusionauth.jwt.domain.JWT;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rest.worker.jwt.JwtWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class JwtWorkerTest {

    @Autowired
    private JwtWorker jwtWorker;

    @Test
    void test() {
        /* the uuid as unique id  */
        String uniqueId = GeneralUtils.uuid();
        log.info("the unique id: {}", uniqueId);
        /* the generate string as subject  */
        String subject = IdentityUtils.generateString();
        log.info("the subject: {}", subject);
        /* use jwt worker to generate token  */
        String token = jwtWorker.generate(uniqueId, subject);
        log.info("the token: {}", token);
        /* use jwt worker to parse token  */
        JWT jwt = jwtWorker.parser(token);
        log.info("the jwt: {}", JsonUtils.parseJson(jwt));
    }
}