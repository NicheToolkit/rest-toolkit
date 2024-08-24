package io.github.nichetoolkit.rest.logback;

import io.github.nichetoolkit.rest.RestLogKey;
import io.github.nichetoolkit.rest.configure.RestLogbackProperties;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.interceptor.RestRequestWrapper;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>RestLogKeyGenerator</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RestLogKeyGenerator implements RestLogKey {

    protected RestLogbackProperties logbackProperties;

    public RestLogKeyGenerator(RestLogbackProperties logbackProperties) {
        this.logbackProperties = logbackProperties;
    }

    @Override
    public String logKey(RestRequestWrapper requestWrapper) {
        String logKey = "";
        int prefixLength = 24;
        String token = accessToken(requestWrapper);
        if (StringUtils.isNotEmpty(token)) {
            if (token.length() > prefixLength) {
                logKey = token.substring(token.length() - prefixLength);
            }
        } else {
            logKey = requestWrapper.getSession().getId();
            if (logKey.length() > prefixLength) {
                logKey = logKey.substring(logKey.length() - prefixLength);
            }
        }
        List<String> attributes = logbackProperties.getAttributes();
        String attribute = attributes(requestWrapper, attributes);
        if (StringUtils.isEmpty(attribute)) {
            attribute = IdentityUtils.generateString();
        }
        logKey += attribute;
        logKey = logKey.replaceAll("[=.]", "");
        return logKey;
    }

    abstract public String accessToken(RestRequestWrapper requestWrapper);

    protected String attribute(RestRequestWrapper requestWrapper, String attribute) {
        if (GeneralUtils.isEmpty(attribute)) {
            return null;
        }
        String attributeValue = requestWrapper.getParameter(attribute);
        if (StringUtils.isEmpty(attribute)) {
            attributeValue = requestWrapper.getHeader(attribute);
        }
        return attributeValue;
    }

    protected String attributes(RestRequestWrapper requestWrapper, List<String> attributes) {
        for (String attribute : attributes) {
            String attributeValue = attribute(requestWrapper, attribute);
            if (GeneralUtils.isNotEmpty(attributeValue)) {
                return attributeValue;
            }
        }
        return null;
    }
}
