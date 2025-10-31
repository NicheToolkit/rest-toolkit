package io.github.nichetoolkit.rest.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.schlichtherle.license.LicenseContent;
import io.github.nichetoolkit.rest.util.DateUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

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
     * <code>message</code>
     * {@link java.lang.String} <p>The <code>message</code> field.</p>
     * @see java.lang.String
     */
    /* 附加信息 */
    private String message;
    /**
     * <code>content</code>
     * {@link de.schlichtherle.license.LicenseContent} <p>The <code>content</code> field.</p>
     * @see de.schlichtherle.license.LicenseContent
     */
    /* 证书内容 */
    private LicenseContent content;
    /**
     * <code>exception</code>
     * {@link java.lang.Exception} <p>The <code>exception</code> field.</p>
     * @see java.lang.Exception
     */
    /* 检验失败错误 */
    private Exception exception;

    /**
     * <code>LicenseResult</code>
     * <p>Instantiates a new license result.</p>
     * @param content {@link de.schlichtherle.license.LicenseContent} <p>The content parameter is <code>LicenseContent</code> type.</p>
     * @see de.schlichtherle.license.LicenseContent
     */
    public LicenseResult(LicenseContent content) {
        this.result = true;
        this.content = content;
    }

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
        this.content = content;
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
        this.exception = exception;
    }

    /**
     * <code>success</code>
     * <p>The success method.</p>
     * @param content {@link de.schlichtherle.license.LicenseContent} <p>The content parameter is <code>LicenseContent</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseResult} <p>The success return object is <code>LicenseResult</code> type.</p>
     * @see de.schlichtherle.license.LicenseContent
     */
    public static LicenseResult success(LicenseContent content) {
        return new LicenseResult(content);
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
     * <code>error</code>
     * <p>The error method.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param exception {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseResult} <p>The error return object is <code>LicenseResult</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Exception
     */
    public static LicenseResult error(String message, Exception exception) {
        return new LicenseResult(message, exception);
    }

    /**
     * <code>getStartTime</code>
     * <p>The get start time getter method.</p>
     * @return {@link java.lang.String} <p>The get start time return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getStartTime() {
        if (GeneralUtils.isNotEmpty(this.content) && GeneralUtils.isNotEmpty(this.content.getNotBefore())) {
            return DateUtils.formatTime(this.content.getNotBefore());
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
        if (GeneralUtils.isNotEmpty(this.content) && GeneralUtils.isNotEmpty(this.content.getNotAfter())) {
            return DateUtils.formatTime(this.content.getNotAfter());
        }
        return null;
    }

    public Long getSurplusDays() {
        if (GeneralUtils.isNotEmpty(this.content) && GeneralUtils.isNotEmpty(this.content.getNotAfter()) && GeneralUtils.isNotEmpty(this.content.getNotBefore())) {
            return DateUtils.offsetDays(this.content.getNotAfter(), this.content.getNotBefore());
        }
        return null;
    }
}
