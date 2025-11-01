package io.github.nichetoolkit.rest.license;

import io.github.nichetoolkit.rest.error.license.LicenseErrorException;
import io.github.nichetoolkit.rest.error.license.LicenseErrorStatus;
import io.github.nichetoolkit.rest.error.license.LicenseVerifyException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <code>LicenseServerInfo</code>
 * <p>The license server info class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.license.RestServerInfo
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public abstract class LicenseServerInfo implements RestServerInfo {

    /**
     * <code>ServerContainer</code>
     * <p>The server container class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    private static class ServerContainer {
        /**
         * <code>ipAddress</code>
         * {@link java.util.List} <p>The <code>ipAddress</code> field.</p>
         * @see java.util.List
         */
        private static List<String> ipAddress = null;
        /**
         * <code>macAddress</code>
         * {@link java.util.List} <p>The <code>macAddress</code> field.</p>
         * @see java.util.List
         */
        private static List<String> macAddress = null;
        /**
         * <code>cpuSerial</code>
         * {@link java.lang.String} <p>The constant <code>cpuSerial</code> field.</p>
         * @see java.lang.String
         */
        private static String cpuSerial = null;
        /**
         * <code>boardSerial</code>
         * {@link java.lang.String} <p>The constant <code>boardSerial</code> field.</p>
         * @see java.lang.String
         */
        private static String boardSerial = null;
    }

    /**
     * <code>setupServerContainer</code>
     * <p>The setup server container setter method.</p>
     * @throws LicenseErrorException {@link io.github.nichetoolkit.rest.error.license.LicenseErrorException} <p>The license error exception is <code>LicenseErrorException</code> type.</p>
     * @see io.github.nichetoolkit.rest.error.license.LicenseErrorException
     */
    private void setupServerContainer() throws LicenseErrorException {
        if (GeneralUtils.isEmpty(ServerContainer.ipAddress)) {
            ServerContainer.ipAddress = this.getIpAddress();
        }
        if (GeneralUtils.isEmpty(ServerContainer.macAddress)) {
            ServerContainer.macAddress = this.getMacAddress();
        }
        if (GeneralUtils.isEmpty(ServerContainer.cpuSerial)) {
            ServerContainer.cpuSerial = this.localCpuSerial();
        }
        if (GeneralUtils.isEmpty(ServerContainer.boardSerial)) {
            ServerContainer.boardSerial = this.localBoardSerial();
        }
    }

    /**
     * <code>serverInfo</code>
     * <p>The server info method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.RestServerInfo} <p>The server info return object is <code>RestServerInfo</code> type.</p>
     * @throws LicenseErrorException {@link io.github.nichetoolkit.rest.error.license.LicenseErrorException} <p>The license error exception is <code>LicenseErrorException</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.RestServerInfo
     * @see io.github.nichetoolkit.rest.error.license.LicenseErrorException
     */
    public static RestServerInfo serverInfo() throws LicenseErrorException {
        return extraInfo(null);
    }

    /**
     * <code>extraInfo</code>
     * <p>The extra info method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.RestServerInfo} <p>The extra info return object is <code>RestServerInfo</code> type.</p>
     * @throws LicenseVerifyException {@link io.github.nichetoolkit.rest.error.license.LicenseVerifyException} <p>The license verify exception is <code>LicenseVerifyException</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.RestServerInfo
     * @see io.github.nichetoolkit.rest.error.license.LicenseVerifyException
     */
    public static RestServerInfo extraInfo() throws LicenseVerifyException {
        try {
            return extraInfo(null);
        } catch (LicenseErrorException exception) {
            throw new LicenseVerifyException(exception);
        }
    }

    /**
     * <code>extraInfo</code>
     * <p>The extra info method.</p>
     * @param osName {@link java.lang.String} <p>The os name parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.RestServerInfo} <p>The extra info return object is <code>RestServerInfo</code> type.</p>
     * @throws LicenseErrorException {@link io.github.nichetoolkit.rest.error.license.LicenseErrorException} <p>The license error exception is <code>LicenseErrorException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.license.RestServerInfo
     * @see io.github.nichetoolkit.rest.error.license.LicenseErrorException
     */
    public static RestServerInfo extraInfo(String osName) throws LicenseErrorException {
        if (GeneralUtils.isEmpty(osName)) {
            osName = System.getProperty("os.name").toLowerCase();
        }
        RestServerInfo serverInfo;
        if (osName.startsWith("windows")) {
            serverInfo = new WindowsServerInfo().localExtraInfo();
        } else if (osName.startsWith("linux")) {
            serverInfo = new LinuxServerInfo().localExtraInfo();
        } else {
            throw new LicenseErrorException(LicenseErrorStatus.LICENSE_SERVER_UNSUPPORTED, "system", "system", osName);
        }
        return serverInfo;
    }

    /**
     * <code>localExtraInfo</code>
     * <p>The local extra info method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseExtraInfo} <p>The local extra info return object is <code>LicenseExtraInfo</code> type.</p>
     * @throws LicenseErrorException {@link io.github.nichetoolkit.rest.error.license.LicenseErrorException} <p>The license error exception is <code>LicenseErrorException</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseExtraInfo
     * @see io.github.nichetoolkit.rest.error.license.LicenseErrorException
     */
    public LicenseExtraInfo localExtraInfo() throws LicenseErrorException {
        LicenseExtraInfo extraInfo;
        try {
            setupServerContainer();
            extraInfo = new LicenseExtraInfo();
            extraInfo.setIpAddress(ServerContainer.ipAddress);
            extraInfo.setMacAddress(ServerContainer.macAddress);
            extraInfo.setCpuSerial(ServerContainer.cpuSerial);
            extraInfo.setBoardSerial(ServerContainer.boardSerial);
        } catch (Exception exception) {
            log.error("It is failed to obtain server hardware information, error: {}", exception.getMessage(), exception);
            throw new LicenseErrorException(LicenseErrorStatus.LICENSE_SERVER_INFO_ERROR);
        }
        return extraInfo;
    }

    @Override
    public List<String> getIpAddress() {
        List<InetAddress> inetAddresses = localInetAddress();
        if (GeneralUtils.isNotEmpty(inetAddresses)) {
            return inetAddresses.stream().map(InetAddress::getHostAddress).distinct().map(String::toLowerCase).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<String> getMacAddress() {
        List<InetAddress> inetAddresses = localInetAddress();
        if (GeneralUtils.isNotEmpty(inetAddresses)) {
            return inetAddresses.stream().map(this::localMacAddress).distinct().collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public String getCpuSerial() throws LicenseVerifyException {
        try {
            return localCpuSerial();
        } catch (LicenseErrorException exception) {
            throw new LicenseVerifyException(exception);
        }
    }

    @Override
    public String getBoardSerial() throws LicenseVerifyException {
        try {
            return localBoardSerial();
        } catch (LicenseErrorException exception) {
            throw new LicenseVerifyException(exception);
        }
    }

    /**
     * <code>localCpuSerial</code>
     * <p>The local cpu serial method.</p>
     * @return {@link java.lang.String} <p>The local cpu serial return object is <code>String</code> type.</p>
     * @throws LicenseErrorException {@link io.github.nichetoolkit.rest.error.license.LicenseErrorException} <p>The license error exception is <code>LicenseErrorException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.license.LicenseErrorException
     */
    public abstract String localCpuSerial() throws LicenseErrorException;

    /**
     * <code>localBoardSerial</code>
     * <p>The local board serial method.</p>
     * @return {@link java.lang.String} <p>The local board serial return object is <code>String</code> type.</p>
     * @throws LicenseErrorException {@link io.github.nichetoolkit.rest.error.license.LicenseErrorException} <p>The license error exception is <code>LicenseErrorException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.license.LicenseErrorException
     */
    public abstract String localBoardSerial() throws LicenseErrorException;

    /**
     * <code>localInetAddress</code>
     * <p>The local inet address method.</p>
     * @return {@link java.util.List} <p>The local inet address return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    private List<InetAddress> localInetAddress() {
        List<InetAddress> inetAddresses = new ArrayList<>(4);
        Enumeration<NetworkInterface> networkInterfaces;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException exception) {
            log.error("It is failed to obtain server network interfaces, error: {}", exception.getMessage(), exception);
            return Collections.emptyList();
        }
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            // 在所有的接口下再遍历IP
            for (Enumeration<InetAddress> addresses = networkInterface.getInetAddresses(); addresses.hasMoreElements(); ) {
                InetAddress address = addresses.nextElement();
                //排除LoopbackAddress、SiteLocalAddress、LinkLocalAddress、MulticastAddress类型的IP地址
                if (!address.isLoopbackAddress() /*&& !inetAddr.isSiteLocalAddress()*/
                        && !address.isLinkLocalAddress() && !address.isMulticastAddress()) {
                    inetAddresses.add(address);
                }
            }
        }
        return inetAddresses;
    }

    /**
     * <code>localMacAddress</code>
     * <p>The local mac address method.</p>
     * @param inetAddress {@link java.net.InetAddress} <p>The inet address parameter is <code>InetAddress</code> type.</p>
     * @return {@link java.lang.String} <p>The local mac address return object is <code>String</code> type.</p>
     * @see java.net.InetAddress
     * @see java.lang.String
     */
    private String localMacAddress(InetAddress inetAddress) {
        byte[] hardwareAddress;
        try {
            hardwareAddress = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
        } catch (SocketException exception) {
            log.error("It is failed to obtain server hardware address, error: {}", exception.getMessage(), exception);
            return null;
        }
        StringBuilder macBuilder = new StringBuilder();
        for (int i = 0; i < hardwareAddress.length; i++) {
            if (i != 0) {
                macBuilder.append("-");
            }
            /* 将十六进制byte转化为字符串 */
            String mac = Integer.toHexString(hardwareAddress[i] & 0xff);
            if (mac.length() == 1) {
                macBuilder.append("0").append(mac);
            } else {
                macBuilder.append(mac);
            }
        }
        return macBuilder.toString().toUpperCase();

    }


}
