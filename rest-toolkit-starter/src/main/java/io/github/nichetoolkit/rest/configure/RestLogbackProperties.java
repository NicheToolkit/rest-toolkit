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

    /**
     * <code>enabled</code>
     * {@link java.lang.Boolean} <p>the <code>enabled</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean enabled = true;

    /**
     * <code>loggingKey</code>
     * {@link java.lang.String} <p>the <code>loggingKey</code> field.</p>
     * @see java.lang.String
     */
    private String loggingKey ="loggingKey";

    /**
     * <code>attributes</code>
     * {@link java.lang.String} <p>the <code>attributes</code> field.</p>
     * @see java.lang.String
     */
    private String[] attributes = {"t"};

    /**
     * <code>requestKey</code>
     * {@link java.lang.String} <p>the <code>requestKey</code> field.</p>
     * @see java.lang.String
     */
    private String requestKey = "requestKey";

    /**
     * <code>requestHeader</code>
     * {@link java.lang.String} <p>the <code>requestHeader</code> field.</p>
     * @see java.lang.String
     */
    private String requestHeader = "X-Request-ID";

    /**
     * <code>argumentLength</code>
     * {@link java.lang.Integer} <p>the <code>argumentLength</code> field.</p>
     * @see java.lang.Integer
     */
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
