package io.github.nichetoolkit.rest.logback;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import io.github.nichetoolkit.rest.holder.ApplicationContextHolder;
import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.util.CommonUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

/**
 * <code>DefaultMessageConverter</code>
 * <p>The default message converter class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see ch.qos.logback.classic.pattern.MessageConverter
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class DefaultMessageConverter extends MessageConverter {
    /**
     * <code>logbackProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>The <code>logbackProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    protected RestLogbackProperties logbackProperties;

    /**
     * <code>DefaultMessageConverter</code>
     * <p>Instantiates a new default message converter.</p>
     */
    public DefaultMessageConverter() {
    }

    /**
     * <code>DefaultMessageConverter</code>
     * <p>Instantiates a new default message converter.</p>
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>The logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    public DefaultMessageConverter(RestLogbackProperties logbackProperties) {
        this.logbackProperties = logbackProperties;
    }

    @Override
    public String convert(ILoggingEvent event) {
        Object[] argumentArray = event.getArgumentArray();
        if (GeneralUtils.isNotEmpty(argumentArray)) {
            Object[] formats = argumentsFormat(argumentArray);
            String message = event.getMessage();
            return MessageFormatter.arrayFormat(message, formats).getMessage();
        }
        return super.convert(event);
    }

    /**
     * <code>argumentsFormat</code>
     * <p>The arguments format method.</p>
     * @param arguments {@link java.lang.Object} <p>The arguments parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Object} <p>The arguments format return object is <code>Object</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.lang.NonNull
     */
    protected Object[] argumentsFormat(@NonNull Object[] arguments) {
        Object[] formats = new Object[arguments.length];
        for (int index = 0; index < arguments.length; index++) {
            if (arguments[index] != null) {
                formats[index] = argumentJson(arguments[index]);
            } else {
                formats[index] = "";
            }
        }
        return formats;
    }

    /**
     * <code>argumentJson</code>
     * <p>The argument json method.</p>
     * @param argument {@link java.lang.Object} <p>The argument parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Object} <p>The argument json return object is <code>Object</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.lang.NonNull
     */
    protected Object argumentJson(@NonNull Object argument) {
        if (BeanUtils.isSimpleProperty(argument.getClass())) {
            return argument;
        } else {
            String toString = argument.toString();
            if (toString != null && !toString.contains("@")) {
                argument = toString;
            }
        }
        Integer argumentLength = 1024;
        if (GeneralUtils.isEmpty(this.logbackProperties) && ApplicationContextHolder.isActiveContext()) {
            this.logbackProperties = ApplicationContextHolder.beanOfType(RestLogbackProperties.class);
        }
        if (GeneralUtils.isNotEmpty(this.logbackProperties)) {
            argumentLength = this.logbackProperties.getArgumentLength();
        }
        if (argument instanceof String) {
            if (argument.toString().length() < argumentLength) {
                return argument;
            } else {
                return CommonUtils.substring(((String) argument), argumentLength);
            }
        }
        String argumentJson = JsonUtils.parseJson(argument);
        if (GeneralUtils.isNotEmpty(argumentJson)) {
            if (argumentJson.length() < argumentLength) {
                return argumentJson;
            } else {
                return CommonUtils.substring(argumentJson, argumentLength);
            }
        }
        return argument;
    }
}
