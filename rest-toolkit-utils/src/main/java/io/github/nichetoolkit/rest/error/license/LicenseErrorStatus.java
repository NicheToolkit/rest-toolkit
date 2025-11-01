package io.github.nichetoolkit.rest.error.license;

import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.util.I18nUtils;
import lombok.Getter;

/**
 * <code>LicenseErrorStatus</code>
 * <p>The license error status enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see lombok.Getter
 * @since Jdk1.8
 */
@Getter
public enum LicenseErrorStatus implements RestStatus {
    /**
     * <code>LICENSE_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_ERROR</code> field.</p>
     */
    LICENSE_ERROR(11200, "It has encountered a license related error"),
    /**
     * <code>LICENSE_VERIFY_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_VERIFY_ERROR</code> field.</p>
     */
    LICENSE_VERIFY_ERROR(11201, "It has encountered a license verify error"),
    /**
     * <code>LICENSE_CREATE_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_CREATE_ERROR</code> field.</p>
     */
    LICENSE_CREATE_ERROR(11202, "It has encountered a license create error"),

    /**
     * <code>LICENSE_CREATE_SUCCESS</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_CREATE_SUCCESS</code> field.</p>
     */
    LICENSE_CREATE_SUCCESS(11203, "The license created is successful"),
    /**
     * <code>LICENSE_CREATE_FAILURE</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_CREATE_FAILURE</code> field.</p>
     */
    LICENSE_CREATE_FAILURE(11204, "The license created is failed"),
    /**
     * <code>LICENSE_INSTALL_SUCCESS</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_INSTALL_SUCCESS</code> field.</p>
     */
    LICENSE_INSTALL_SUCCESS(11205, "The license installed is successful"),
    /**
     * <code>LICENSE_INSTALL_FAILURE</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_INSTALL_FAILURE</code> field.</p>
     */
    LICENSE_INSTALL_FAILURE(11206, "The license installed is failed"),
    /**
     * <code>LICENSE_VERIFY_SUCCESS</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_VERIFY_SUCCESS</code> field.</p>
     */
    LICENSE_VERIFY_SUCCESS(11207, "The license verified is valid"),
    /**
     * <code>LICENSE_VERIFY_FAILURE</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_VERIFY_FAILURE</code> field.</p>
     */
    LICENSE_VERIFY_FAILURE(11208, "The license verified is invalid"),

    /**
     * <code>LICENSE_NOT_INSTALLED_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_NOT_INSTALLED_ERROR</code> field.</p>
     */
    LICENSE_NOT_INSTALLED_ERROR(11210, "The server does not installed license"),
    /**
     * <code>LICENSE_NOT_AFTER_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_NOT_AFTER_ERROR</code> field.</p>
     */
    LICENSE_NOT_AFTER_ERROR(11211, "The license expiration time cannot be earlier than the current time"),
    /**
     * <code>LICENSE_NOT_BEFORE_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_NOT_BEFORE_ERROR</code> field.</p>
     */
    LICENSE_NOT_BEFORE_ERROR(11212, "The license effective time cannot be later than the expiration time"),
    /**
     * <code>LICENSE_CONSUMER_TYPE_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_CONSUMER_TYPE_ERROR</code> field.</p>
     */
    LICENSE_CONSUMER_TYPE_ERROR(11213, "The consumer type of license cannot be empty"),
    /**
     * <code>LICENSE_ENCODING_UNSUPPORTED</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_ENCODING_UNSUPPORTED</code> field.</p>
     */
    LICENSE_ENCODING_UNSUPPORTED(11214, "The encoding of license is unsupported"),

    /**
     * <code>LICENSE_SERVER_UNSUPPORTED</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_SERVER_UNSUPPORTED</code> field.</p>
     */
    LICENSE_SERVER_UNSUPPORTED(11221, "The type of server is unsupported"),
    /**
     * <code>LICENSE_SERVER_INFO_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_SERVER_INFO_ERROR</code> field.</p>
     */
    LICENSE_SERVER_INFO_ERROR(11222, "It is failed to obtain server hardware information"),
    /**
     * <code>LICENSE_SERVER_CPU_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_SERVER_CPU_ERROR</code> field.</p>
     */
    LICENSE_SERVER_CPU_ERROR(11223, "It is failed to obtain server cpu serial information"),
    /**
     * <code>LICENSE_SERVER_BOARD_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_SERVER_BOARD_ERROR</code> field.</p>
     */
    LICENSE_SERVER_BOARD_ERROR(11224, "It is failed to obtain server board serial information"),

    /**
     * <code>LICENSE_EXPIRED_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_EXPIRED_ERROR</code> field.</p>
     */
    LICENSE_EXPIRED_ERROR(11230, "The system license has expired, and the current time has exceeded the license validity date"),
    /**
     * <code>LICENSE_IP_OVERSTEP_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_IP_OVERSTEP_ERROR</code> field.</p>
     */
    LICENSE_IP_OVERSTEP_ERROR(11231, "The system license is invalid, as the current server's IP is not within the authorized scope"),
    /**
     * <code>LICENSE_MAC_OVERSTEP_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_MAC_OVERSTEP_ERROR</code> field.</p>
     */
    LICENSE_MAC_OVERSTEP_ERROR(11232, "The system license is invalid, as the Mac address of the current server is not within the authorized scope"),
    /**
     * <code>LICENSE_BOARD_SERIAL_OVERSTEP_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_BOARD_SERIAL_OVERSTEP_ERROR</code> field.</p>
     */
    LICENSE_BOARD_SERIAL_OVERSTEP_ERROR(11233, "The system license is invalid, as the current server's motherboard serial number is not within the authorized scope"),
    /**
     * <code>LICENSE_CPU_SERIAL_OVERSTEP_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The <code>LICENSE_CPU_SERIAL_OVERSTEP_ERROR</code> field.</p>
     */
    LICENSE_CPU_SERIAL_OVERSTEP_ERROR(11234, "The system license is invalid, as the CPU serial number of the current server is not within the authorized scope"),


    ;

    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
     * @see java.lang.Integer
     */
    private final Integer status;
    /**
     * <code>message</code>
     * {@link java.lang.String} <p>The <code>message</code> field.</p>
     * @see java.lang.String
     */
    private final String message;

    /**
     * <code>LicenseErrorStatus</code>
     * <p>Instantiates a new license error status.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    LicenseErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return I18nUtils.message(name(), this.message);
    }
}
