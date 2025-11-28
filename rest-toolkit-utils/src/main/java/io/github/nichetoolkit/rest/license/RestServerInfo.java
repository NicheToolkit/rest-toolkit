package io.github.nichetoolkit.rest.license;

import io.github.nichetoolkit.rest.error.license.LicenseVerifyException;

import java.io.Serializable;
import java.util.List;


/**
 * <code>RestServerInfo</code>
 * <p>The rest server info interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @since Jdk17
 */
public interface RestServerInfo extends Serializable {

    /**
     * <code>getIpAddress</code>
     * <p>The get ip address getter method.</p>
     * @return {@link java.util.List} <p>The get ip address return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    List<String> getIpAddress();

    /**
     * <code>getMacAddress</code>
     * <p>The get mac address getter method.</p>
     * @return {@link java.util.List} <p>The get mac address return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    List<String> getMacAddress();

    /**
     * <code>getCpuSerial</code>
     * <p>The get cpu serial getter method.</p>
     * @return {@link java.lang.String} <p>The get cpu serial return object is <code>String</code> type.</p>
     * @throws LicenseVerifyException {@link io.github.nichetoolkit.rest.error.license.LicenseVerifyException} <p>The license verify exception is <code>LicenseVerifyException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.license.LicenseVerifyException
     */
    String getCpuSerial() throws LicenseVerifyException;

    /**
     * <code>getBoardSerial</code>
     * <p>The get board serial getter method.</p>
     * @return {@link java.lang.String} <p>The get board serial return object is <code>String</code> type.</p>
     * @throws LicenseVerifyException {@link io.github.nichetoolkit.rest.error.license.LicenseVerifyException} <p>The license verify exception is <code>LicenseVerifyException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.license.LicenseVerifyException
     */
    String getBoardSerial() throws LicenseVerifyException;
}
