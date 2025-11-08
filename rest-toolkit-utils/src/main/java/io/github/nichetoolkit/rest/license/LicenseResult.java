package io.github.nichetoolkit.rest.license;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.schlichtherle.license.LicenseContent;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.license.LicenseErrorStatus;
import io.github.nichetoolkit.rest.util.DateUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * <code>LicenseResult</code>
 * <p>The license result class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Getter
 * @see lombok.Setter
 * @see lombok.experimental.SuperBuilder
 * @see lombok.NoArgsConstructor
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseResult implements Serializable {
    /**
     * <code>result</code>
     * {@link java.lang.Boolean} <p>The <code>result</code> field.</p>
     * @see java.lang.Boolean
     */
    /* 检验结果 */
    private Boolean result;
    /**
     * <code>errorCode</code>
     * {@link java.lang.Integer} <p>The <code>errorCode</code> field.</p>
     * @see java.lang.Integer
     */
    /* 检验结果 */
    private Integer errorCode;
    /**
     * <code>message</code>
     * {@link java.lang.String} <p>The <code>message</code> field.</p>
     * @see java.lang.String
     */
    /* 附加信息 */
    private String message;
    /**
     * <code>context</code>
     * {@link io.github.nichetoolkit.rest.license.LicenseContext} <p>The <code>context</code> field.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseContext
     */
    /* 证书内容 */
    private LicenseContext context;
    /**
     * <code>exception</code>
     * {@link java.lang.Exception} <p>The <code>exception</code> field.</p>
     * @see java.lang.Exception
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    /* 检验失败错误 */
    @JsonIgnore
    private Exception exception;
    /**
     * <code>stopServer</code>
     * {@link java.lang.Boolean} <p>The <code>stopServer</code> field.</p>
     * @see java.lang.Boolean
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    private Boolean stopServer= false;

    /**
     * <code>LicenseResult</code>
     * <p>Instantiates a new license result.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param content {@link de.schlichtherle.license.LicenseContent} <p>The content parameter is <code>LicenseContent</code> type.</p>
     * @see java.lang.String
     * @see de.schlichtherle.license.LicenseContent
     */
    public LicenseResult(String message, LicenseContent content) {
        this.result = true;
        this.message = message;
        this.context = LicenseContext.licenseContext(content);
    }

    /**
     * <code>LicenseResult</code>
     * <p>Instantiates a new license result.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param exception {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Exception
     */
    public LicenseResult(String message, Exception exception) {
        this.result = false;
        this.message = message;
        this.errorCode = LicenseErrorStatus.LICENSE_ERROR.getStatus();
        this.exception = exception;
    }

    /**
     * <code>LicenseResult</code>
     * <p>Instantiates a new license result.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param exception  {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Exception
     */
    public LicenseResult(RestStatus restStatus, Exception exception) {
        this.result = false;
        this.errorCode = restStatus.getStatus();
        this.message = restStatus.getMessage();
        this.exception = exception;
    }

    /**
     * <code>LicenseResult</code>
     * <p>Instantiates a new license result.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param exception  {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @param stopServer boolean <p>The stop server parameter is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Exception
     */
    public LicenseResult(RestStatus restStatus, Exception exception, boolean stopServer) {
        this.result = false;
        this.stopServer = stopServer;
        this.errorCode = restStatus.getStatus();
        this.message = restStatus.getMessage();
        this.exception = exception;
    }

    /**
     * <code>success</code>
     * <p>The success method.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param content {@link de.schlichtherle.license.LicenseContent} <p>The content parameter is <code>LicenseContent</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseResult} <p>The success return object is <code>LicenseResult</code> type.</p>
     * @see java.lang.String
     * @see de.schlichtherle.license.LicenseContent
     */
    public static LicenseResult success(String message, LicenseContent content) {
        return new LicenseResult(message, content);
    }

    /**
     * <code>failure</code>
     * <p>The failure method.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param exception {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseResult} <p>The failure return object is <code>LicenseResult</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Exception
     */
    public static LicenseResult failure(String message, Exception exception) {
        return new LicenseResult(message, exception);
    }

    /**
     * <code>success</code>
     * <p>The success method.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param content    {@link de.schlichtherle.license.LicenseContent} <p>The content parameter is <code>LicenseContent</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseResult} <p>The success return object is <code>LicenseResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see de.schlichtherle.license.LicenseContent
     */
    public static LicenseResult success(RestStatus restStatus, LicenseContent content) {
        return new LicenseResult(restStatus.getMessage(), content);
    }

    /**
     * <code>failure</code>
     * <p>The failure method.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param exception  {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseResult} <p>The failure return object is <code>LicenseResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Exception
     */
    public static LicenseResult failure(RestStatus restStatus, Exception exception) {
        return new LicenseResult(restStatus, exception);
    }

    /**
     * <code>failure</code>
     * <p>The failure method.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param exception  {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @param stopServer boolean <p>The stop server parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseResult} <p>The failure return object is <code>LicenseResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Exception
     */
    public static LicenseResult failure(RestStatus restStatus, Exception exception, boolean stopServer) {
        return new LicenseResult(restStatus, exception, stopServer);
    }

    /**
     * <code>getStartTime</code>
     * <p>The get start time getter method.</p>
     * @return {@link java.lang.String} <p>The get start time return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getStartTime() {
        if (GeneralUtils.isNotEmpty(this.context) && GeneralUtils.isNotEmpty(this.context.getIssuedTime())) {
            return DateUtils.formatTime(this.context.getIssuedTime());
        }
        return null;
    }

    /**
     * <code>getEndTime</code>
     * <p>The get end time getter method.</p>
     * @return {@link java.lang.String} <p>The get end time return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getEndTime() {
        if (GeneralUtils.isNotEmpty(this.context) && GeneralUtils.isNotEmpty(this.context.getExpiryTime())) {
            return DateUtils.formatTime(this.context.getExpiryTime());
        }
        return null;
    }

    /**
     * <code>getAvailableDays</code>
     * <p>The get available days getter method.</p>
     * @return {@link java.lang.Long} <p>The get available days return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public Long getAvailableDays() {
        if (GeneralUtils.isNotEmpty(this.context) && GeneralUtils.isNotEmpty(this.context.getIssuedTime()) && GeneralUtils.isNotEmpty(this.context.getExpiryTime())) {
            return DateUtils.offsetDays(this.context.getIssuedTime(), this.context.getExpiryTime());
        }
        return null;
    }


    /**
     * <code>getSurplusDays</code>
     * <p>The get surplus days getter method.</p>
     * @return {@link java.lang.Long} <p>The get surplus days return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public Long getSurplusDays() {
        if (GeneralUtils.isNotEmpty(this.context) && GeneralUtils.isNotEmpty(this.context.getIssuedTime()) && GeneralUtils.isNotEmpty(this.context.getExpiryTime())) {
            return DateUtils.offsetDays(new Date(), this.context.getExpiryTime());
        }
        return null;
    }
}
