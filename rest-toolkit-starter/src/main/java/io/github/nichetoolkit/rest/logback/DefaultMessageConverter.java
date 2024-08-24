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

import java.nio.charset.StandardCharsets;

/**
 * <p>DefaultMessageConverter</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
public class DefaultMessageConverter extends MessageConverter {

    protected final RestLogbackProperties logbackProperties;

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
