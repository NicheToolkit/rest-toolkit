package io.github.nichetoolkit.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * <code>RestTestApplication</code>
 * <p>The type rest test application class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see SpringBootServletInitializer
 * @see SpringBootApplication
 * @see ComponentScan
 * @since Jdk1.8
 */
@SpringBootApplication
@ComponentScan(basePackages = "io.github.nichetoolkit")
public class RestTestApplication extends SpringBootServletInitializer {

    /**
     * <code>main</code>
     * <p>The entry point of application.</p>
     * @param args {@link java.lang.String} <p>the input arguments.</p>
     * @see java.lang.String
     */
    public static void main(String[] args) {
        SpringApplication.run(RestTestApplication.class, args);
    }

    /**
     * <code>configure</code>
     * <p>the method.</p>
     * @param application SpringApplicationBuilder <p>the application parameter is <code>SpringApplicationBuilder</code> type.</p>
     * @return SpringApplicationBuilder <p>the return object is <code>SpringApplicationBuilder</code> type.</p>
     * @see Override
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RestTestApplication.class);
    }
}
