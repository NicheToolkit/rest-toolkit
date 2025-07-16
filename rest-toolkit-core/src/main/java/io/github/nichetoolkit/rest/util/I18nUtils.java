package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.helper.I18nHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * <code>I18nUtils</code>
 * <p>The 18 n utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class I18nUtils {

    /**
     * <code>message</code>
     * <p>The message method.</p>
     * @param source {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The message return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String message(String source) {
        try {
            return I18nHelper.message(source);
        } catch (ResourceNotFoundException exception) {
            log.error("It is failed during source to parse as i18n message! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return source;
        }
    }

    /**
     * <code>message</code>
     * <p>The message method.</p>
     * @param source  {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The message return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String message(String source, String message) {
        return I18nHelper.message(source, message);
    }

    /**
     * <code>message</code>
     * <p>The message method.</p>
     * @param source {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param args   {@link java.lang.Object} <p>The args parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.String} <p>The message return object is <code>String</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     */
    public static String message(String source, Object... args) throws ResourceNotFoundException {
        try {
            return I18nHelper.message(source, args);
        } catch (ResourceNotFoundException exception) {
            log.error("It is failed during source to parse as i18n message with args! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return source;
        }
    }

    /**
     * <code>message</code>
     * <p>The message method.</p>
     * @param source  {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param args    {@link java.lang.Object} <p>The args parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.String} <p>The message return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static String message(String source, String message, Object... args) {
        return I18nHelper.message(source, message, args);
    }

}
