package io.github.nichetoolkit.rest.userlog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestResult;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * <code>RestResponsePack</code>
 * <p>The rest response pack class.</p>
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
public class RestResponsePack {
    /**
     * <code>time</code>
     * {@link java.lang.Long} <p>The <code>time</code> field.</p>
     * @see java.lang.Long
     */
    protected Long time;
    /**
     * <code>startTime</code>
     * {@link java.lang.Long} <p>The <code>startTime</code> field.</p>
     * @see java.lang.Long
     */
    protected Long startTime;
    /**
     * <code>endTime</code>
     * {@link java.lang.Long} <p>The <code>endTime</code> field.</p>
     * @see java.lang.Long
     */
    protected Long endTime;
    /**
     * <code>costTime</code>
     * {@link java.lang.Long} <p>The <code>costTime</code> field.</p>
     * @see java.lang.Long
     */
    protected Long costTime;
    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
     * @see java.lang.Integer
     */
    protected Integer status;
    /**
     * <code>message</code>
     * {@link java.lang.String} <p>The <code>message</code> field.</p>
     * @see java.lang.String
     */
    protected String message;
    /**
     * <code>error</code>
     * {@link java.lang.String} <p>The <code>error</code> field.</p>
     * @see java.lang.String
     */
    protected String error;
    /**
     * <code>method</code>
     * {@link java.lang.String} <p>The <code>method</code> field.</p>
     * @see java.lang.String
     */
    protected String method;
    /**
     * <code>mediaType</code>
     * {@link java.lang.String} <p>The <code>mediaType</code> field.</p>
     * @see java.lang.String
     */
    protected String mediaType;
    /**
     * <code>result</code>
     * {@link java.lang.String} <p>The <code>result</code> field.</p>
     * @see java.lang.String
     */
    protected String result;
    /**
     * <code>resultString</code>
     * {@link java.lang.String} <p>The <code>resultString</code> field.</p>
     * @see java.lang.String
     */
    protected String resultString;
    /**
     * <code>restResult</code>
     * {@link io.github.nichetoolkit.rest.RestResult} <p>The <code>restResult</code> field.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     */
    protected RestResult<?> restResult;
    /**
     * <code>data</code>
     * {@link java.lang.String} <p>The <code>data</code> field.</p>
     * @see java.lang.String
     */
    protected String data;

    /**
     * <code>RestResponsePack</code>
     * <p>Instantiates a new rest response pack.</p>
     */
    public RestResponsePack() {
    }

    /**
     * <code>isSuccess</code>
     * <p>The is success method.</p>
     * @return boolean <p>The is success return object is <code>boolean</code> type.</p>
     */
    public boolean isSuccess() {
        return RestErrorStatus.SUCCESS.getStatus().equals(this.status);
    }

}
