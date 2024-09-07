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


/**
 * <code>JwtWorkerTest</code>
 * <p>The type jwt worker test class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.test.context.SpringBootTest
 * @since Jdk1.8
 */
@Slf4j
@SpringBootTest
class JwtWorkerTest {

    /**
     * <code>jwtWorker</code>
     * {@link io.github.nichetoolkit.rest.worker.jwt.JwtWorker} <p>the <code>jwtWorker</code> field.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtWorker
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    private JwtWorker jwtWorker;

    /**
     * <code>test</code>
     * <p>the method.</p>
     * @see org.junit.jupiter.api.Test
     */
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