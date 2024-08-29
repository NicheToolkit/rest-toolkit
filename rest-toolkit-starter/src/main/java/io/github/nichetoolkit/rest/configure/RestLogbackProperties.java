package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <code>RestLogbackProperties</code>
 * <p>The type rest logback properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Data
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.logback")
public class RestLogbackProperties {

    private Boolean enabled = true;

    private String loggingKey ="loggingKey";

    private String[] attributes = {"t"};

    private String requestKey = "requestKey";

    private String headerKey = "X-Request-ID";

    private Integer argumentLength = 1024;

    /**
     * <code>getAttributes</code>
     * <p>the attributes getter method.</p>
     * @return {@link java.util.List} <p>the attributes return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public List<String> getAttributes() {
        if (GeneralUtils.isNotEmpty(this.attributes)) {
            return Arrays.asList(this.attributes);
        }
        return Collections.emptyList();
    }

}
