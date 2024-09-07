package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.identity.worker.IdentityWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * <code>IdentityWorkerTest</code>
 * <p>The type identity worker test class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.test.context.SpringBootTest
 * @since Jdk1.8
 */
@Slf4j
@SpringBootTest
public class IdentityWorkerTest {

    /**
     * <code>test</code>
     * <p>the method.</p>
     * @see org.junit.jupiter.api.Test
     */
    @Test
    void test() {
        Set<String> identitySet = new HashSet<>();

        int index = 0;
        while (index<1000000) {
            String identity = IdentityUtils.generateString();
//                log.info("[{}]: {}",index,identity);
            identitySet.add(identity);
            index++;
        }
        System.out.println("size: " + identitySet.size());
        Assertions.assertEquals(identitySet.size(),1000000);

    }
}
