package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.holder.MessageSourceHolder;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * <code>I18nHelper</code>
 * <p>The 18 n helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class I18nHelper {

    /**
     * <code>message</code>
     * <p>The message method.</p>
     * @param source {@link java.lang.String} <p>The source parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The message return object is <code>String</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     */
    public static String message(String source) throws ResourceNotFoundException {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return MessageSourceHolder.messageSource().getMessage(source, null, null, locale);
        } catch (NoSuchMessageException exception) {
            throw new ResourceNotFoundException(source, exception.getMessage());
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
        Locale locale = LocaleContextHolder.getLocale();
        return MessageSourceHolder.messageSource().getMessage(source, null, message, locale);

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
            Locale locale = LocaleContextHolder.getLocale();
            return MessageSourceHolder.messageSource().getMessage(source, args,null, locale);
        } catch (NoSuchMessageException exception) {
            throw new ResourceNotFoundException(source, exception.getMessage());
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
        Locale locale = LocaleContextHolder.getLocale();
        return MessageSourceHolder.messageSource().getMessage(source, args, message, locale);
    }


}
