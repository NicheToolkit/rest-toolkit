package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>IdentityProperties</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.ip-address")
public class RestIpAddressProperties {

    private Boolean enabled = false;

    private String[] ignoredIpAddresses;

    public RestIpAddressProperties() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getIgnoredIpAddresses() {
        if (GeneralUtils.isNotEmpty(this.ignoredIpAddresses)) {
            return new ArrayList<>(Arrays.asList(this.ignoredIpAddresses));
        }
        return Collections.emptyList();
    }

    public void setIgnoredIpAddresses(String[] ignoredIpAddresses) {
        this.ignoredIpAddresses = ignoredIpAddresses;
    }
}
