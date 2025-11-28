package io.github.nichetoolkit.rest.userlog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * <code>RestRequestPack</code>
 * <p>The rest request pack class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Getter
 * @see lombok.Setter
 * @see lombok.experimental.SuperBuilder
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@Getter
@Setter
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestRequestPack {
    /**
     * <code>headers</code>
     * {@link java.lang.String} <p>The <code>headers</code> field.</p>
     * @see java.lang.String
     */
    protected String headers;
    /**
     * <code>ipAddress</code>
     * {@link java.lang.String} <p>The <code>ipAddress</code> field.</p>
     * @see java.lang.String
     */
    protected String ipAddress;
    /**
     * <code>userAgent</code>
     * {@link java.lang.String} <p>The <code>userAgent</code> field.</p>
     * @see java.lang.String
     */
    protected String userAgent;
    /**
     * <code>method</code>
     * {@link java.lang.String} <p>The <code>method</code> field.</p>
     * @see java.lang.String
     */
    protected String method;
    /**
     * <code>url</code>
     * {@link java.lang.String} <p>The <code>url</code> field.</p>
     * @see java.lang.String
     */
    protected String url;
    /**
     * <code>params</code>
     * {@link java.lang.String} <p>The <code>params</code> field.</p>
     * @see java.lang.String
     */
    protected String params;
    /**
     * <code>body</code>
     * {@link java.lang.String} <p>The <code>body</code> field.</p>
     * @see java.lang.String
     */
    protected String body;
    /**
     * <code>bodyString</code>
     * {@link java.lang.String} <p>The <code>bodyString</code> field.</p>
     * @see java.lang.String
     */
    protected String bodyString;
}
