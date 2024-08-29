package io.github.nichetoolkit.rest.logback;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.util.CommonUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * <code>DefaultMessageConverter</code>
 * <p>The type default message converter class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see ch.qos.logback.classic.pattern.MessageConverter
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Component
 * @since Jdk1.8
 */
@Slf4j
@Component
public class DefaultMessageConverter extends MessageConverter {

    /**
     * <code>logbackProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the <code>logbackProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    protected final RestLogbackProperties logbackProperties;

    /**
     * <code>DefaultMessageConverter</code>
     * Instantiates a new default message converter.
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
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
     * <p>the format method.</p>
     * @param arguments {@link java.lang.Object} <p>the arguments parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Object} <p>the format return object is <code>Object</code> type.</p>
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
     * <p>the json method.</p>
     * @param argument {@link java.lang.Object} <p>the argument parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Object} <p>the json return object is <code>Object</code> type.</p>
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
        if (argument instanceof String) {
            if (argument.toString().length() < logbackProperties.getArgumentLength()){
                return argument;
            } else {
                return CommonUtils.substring(((String) argument), logbackProperties.getArgumentLength());
            }
        }
        String argumentJson = JsonUtils.parseJson(argument);
        if (GeneralUtils.isNotEmpty(argumentJson)) {
            if (argumentJson.length() < logbackProperties.getArgumentLength()) {
                return argumentJson;
            } else {
                return CommonUtils.substring(argumentJson, logbackProperties.getArgumentLength());
            }
        }
        return argument;
    }
}
