package io.github.nichetoolkit.rest.holder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;

/**
 * <code>MessageSourceHolder</code>
 * <p>The message source holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class MessageSourceHolder {
    /**
     * <code>MESSAGE_SOURCE</code>
     * {@link org.springframework.context.MessageSource} <p>The constant <code>MESSAGE_SOURCE</code> field.</p>
     * @see org.springframework.context.MessageSource
     */
    private static MessageSource MESSAGE_SOURCE;

    /**
     * <code>initMessageSource</code>
     * <p>The init message source method.</p>
     * @param messageSource {@link org.springframework.context.MessageSource} <p>The message source parameter is <code>MessageSource</code> type.</p>
     * @see org.springframework.context.MessageSource
     * @see org.springframework.lang.Nullable
     */
    static void initMessageSource(@Nullable MessageSource messageSource) {
        MESSAGE_SOURCE = messageSource;
        log.debug("The message source context holder has be initiated");
    }

    /**
     * <code>messageSource</code>
     * <p>The message source method.</p>
     * @return {@link org.springframework.context.MessageSource} <p>The message source return object is <code>MessageSource</code> type.</p>
     * @see org.springframework.context.MessageSource
     */
    public static MessageSource messageSource() {
        return MESSAGE_SOURCE;
    }

}
