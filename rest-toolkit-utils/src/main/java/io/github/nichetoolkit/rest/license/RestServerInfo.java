package io.github.nichetoolkit.rest.license;

import io.github.nichetoolkit.rest.error.license.LicenseVerifyException;

import java.io.Serializable;
import java.util.List;


/**
 * <code>LicenseExtraInfo</code>
 * <p>The license extra info interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @since Jdk1.8
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
     * @see java.lang.String
     */
    String getCpuSerial() throws LicenseVerifyException;

    /**
     * <code>getBoardSerial</code>
     * <p>The get board serial getter method.</p>
     * @return {@link java.lang.String} <p>The get board serial return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String getBoardSerial() throws LicenseVerifyException;
}
