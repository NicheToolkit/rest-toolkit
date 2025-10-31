package io.github.nichetoolkit.rest.error.license;

import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.util.I18nUtils;
import lombok.Getter;

@Getter
public enum LicenseErrorStatus implements RestStatus {
    LICENSE_ERROR(11200, "It has encountered a license related error"),
    LICENSE_VERIFY_ERROR(11201, "It has encountered a license verify error"),
    LICENSE_CREATE_ERROR(11202, "It has encountered a license create error"),

    LICENSE_NOT_INSTALLED_ERROR(11210, "The server does not installed license"),
    LICENSE_NOT_AFTER_ERROR(11211, "The license expiration time cannot be earlier than the current time"),
    LICENSE_NOT_BEFORE_ERROR(11212, "The license effective time cannot be later than the expiration time"),
    LICENSE_CONSUMER_TYPE_ERROR(11213, "The consumer type of license cannot be empty"),
    LICENSE_ENCODING_UNSUPPORTED(11214, "The encoding of license is unsupported"),

    LICENSE_SERVER_UNSUPPORTED(11221, "The type of server is unsupported"),
    LICENSE_SERVER_INFO_ERROR(11222, "It is failed to obtain server hardware information"),
    LICENSE_SERVER_CPU_ERROR(11223, "It is failed to obtain server cpu serial information"),
    LICENSE_SERVER_BOARD_ERROR(11224, "It is failed to obtain server board serial information"),

    LICENSE_EXPIRED_ERROR(11230, "The system license has expired, and the current time has exceeded the license validity date"),
    LICENSE_IP_OVERSTEP_ERROR(11231, "The system license is invalid, as the current server's IP is not within the authorized scope"),
    LICENSE_MAC_OVERSTEP_ERROR(11232, "The system license is invalid, as the Mac address of the current server is not within the authorized scope"),
    LICENSE_BOARD_SERIAL_OVERSTEP_ERROR(11233, "The system license is invalid, as the current server's motherboard serial number is not within the authorized scope"),
    LICENSE_CPU_SERIAL_OVERSTEP_ERROR(11234, "The system license is invalid, as the CPU serial number of the current server is not within the authorized scope"),


    ;

    private final Integer status;
    private final String message;

    LicenseErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return I18nUtils.message(name(), this.message);
    }
}
