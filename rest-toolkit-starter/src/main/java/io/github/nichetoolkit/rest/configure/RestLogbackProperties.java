package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>RestLogbackProperties</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.logback")
public class RestLogbackProperties {

    private Boolean enabled = true;

    private String logKey ="logKey";

    private String[] attributes = {"t"};

    private String requestKey = "requestKey";

    private String headerKey = "X-Request-ID";

    private Integer argumentLength = 1024;

    public RestLogbackProperties() {
    }

    public List<String> getAttributes() {
        if (GeneralUtils.isNotEmpty(this.attributes)) {
            return Arrays.asList(this.attributes);
        }
        return Collections.emptyList();
    }

}
