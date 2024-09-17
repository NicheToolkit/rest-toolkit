package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.identity.IdentityUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
     * @throws InterruptedException {@link java.lang.InterruptedException} <p>the interrupted exception is <code>InterruptedException</code> type.</p>
     * @see org.junit.jupiter.api.Test
     * @see java.lang.InterruptedException
     */
    @Test
    void test() throws InterruptedException {
        Set<String> identitySet = new HashSet<>();
//        String id = IdentityUtils.commonString();
//        log.info("identity: {}", id);
//

//        int index = 0;
//        while (index<1000000) {
//            String identity = IdentityUtils.commonString();
////                log.info("[{}]: {}",index,identity);
//            identitySet.add(identity);
//            index++;
//        }
//        System.out.println("size: " + identitySet.size());
//        Assertions.assertEquals(identitySet.size(),1000000);

        Thread thread1 = new Thread(() -> {
            int index = 0;
            while (index < 100000) {
                String identity = IdentityUtils.commonString();
//                log.info("[{}]: {}",index,identity);
                identitySet.add(identity);
                index++;
            }
            System.out.println("[Thread1]: " + identitySet.size());
        });
        thread1.start();

        new Thread(() -> System.out.println("[Thread Temp]: " + identitySet.size())).start();

        new Thread(() -> System.out.println("[Thread Temp]: " + identitySet.size())).start();

        Thread thread2 =new Thread(() -> {
            int index = 0;
            while (index < 100000) {
                String identity = IdentityUtils.commonString();
//                log.info("[{}]: {}",index,identity);
                identitySet.add(identity);
                index++;
            }
            System.out.println("[Thread2]: " + identitySet.size());
        });

        thread2.start();

        new Thread(() -> System.out.println("[Thread Temp]: " + identitySet.size())).start();

        new Thread(() -> System.out.println("[Thread Temp]: " + identitySet.size())).start();

        new Thread(() -> System.out.println("[Thread Temp]: " + identitySet.size())).start();

        Thread thread3 = new Thread(() -> {
            int index = 0;
            while (index < 100000) {
                String identity = IdentityUtils.commonString();
//                log.info("[{}]: {}",index,identity);
                identitySet.add(identity);
                index++;
            }
            System.out.println("[Thread3]: " + identitySet.size());
        });

        thread3.start();

        new Thread(() -> System.out.println("[Thread Temp]: " + identitySet.size())).start();

        Thread thread4 = new Thread(() -> {
            int index = 0;
            while (index < 100000) {
                String identity = IdentityUtils.commonString();
//                log.info("[{}]: {}",index,identity);
                identitySet.add(identity);
                index++;
            }
            System.out.println("[Thread4]: " + identitySet.size());
        });

        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        Assertions.assertEquals(identitySet.size(),400000);

    }
}
