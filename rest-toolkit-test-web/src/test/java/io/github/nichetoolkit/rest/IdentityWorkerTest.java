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

@Slf4j
@SpringBootTest
public class IdentityWorkerTest {

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
