package io.github.nichetoolkit.rest.license;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
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
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public abstract class LicenseServerInfo {

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
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    private void setupServerContainer() throws RestException {
        RestOptional.ofEmptyable(ServerContainer.ipAddress).isEmpty(() -> {
            ServerContainer.ipAddress = this.getIpAddress();
        });
        RestOptional.ofEmptyable(ServerContainer.macAddress).isEmpty(() -> {
            ServerContainer.macAddress = this.getMacAddress();
        });
        RestOptional.ofEmptyable(ServerContainer.cpuSerial).isEmpty(() -> {
            ServerContainer.cpuSerial = this.getCpuSerial();
        });
        RestOptional.ofEmptyable(ServerContainer.boardSerial).isEmpty(() -> {
            ServerContainer.boardSerial = this.getBoardSerial();
        });
    }

    /**
     * <code>getServerInfo</code>
     * <p>The get server info getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseServerInfo} <p>The get server info return object is <code>LicenseServerInfo</code> type.</p>
     * @throws LicenseVerifyException {@link io.github.nichetoolkit.rest.error.license.LicenseVerifyException} <p>The license verify exception is <code>LicenseVerifyException</code> type.</p>
     * @see io.github.nichetoolkit.rest.error.license.LicenseVerifyException
     */
    public static LicenseServerInfo getServerInfo() throws LicenseVerifyException {
        try {
            return serverInfo(null);
        } catch (LicenseErrorException exception) {
            throw new LicenseVerifyException(exception);
        }
    }

    /**
     * <code>serverInfo</code>
     * <p>The server info method.</p>
     * @param osName {@link java.lang.String} <p>The os name parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseServerInfo} <p>The server info return object is <code>LicenseServerInfo</code> type.</p>
     * @throws LicenseErrorException {@link io.github.nichetoolkit.rest.error.license.LicenseErrorException} <p>The license error exception is <code>LicenseErrorException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.license.LicenseErrorException
     */
    public static LicenseServerInfo serverInfo(String osName) throws LicenseErrorException {
        if (GeneralUtils.isEmpty(osName)) {
            osName = System.getProperty("os.name").toLowerCase();
        }
        LicenseServerInfo serverInfo;
        if (osName.startsWith("windows")) {
            serverInfo = new WindowsServerInfo();
        } else if (osName.startsWith("linux")) {
            serverInfo = new LinuxServerInfo();
        } else {
            throw new LicenseErrorException(LicenseErrorStatus.LICENSE_SERVER_UNSUPPORTED,"system","system",osName);
        }
        return serverInfo;
    }

    /**
     * <code>getExtraParam</code>
     * <p>The get extra param getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseExtraParam} <p>The get extra param return object is <code>LicenseExtraParam</code> type.</p>
     * @throws LicenseVerifyException {@link io.github.nichetoolkit.rest.error.license.LicenseVerifyException} <p>The license verify exception is <code>LicenseVerifyException</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseExtraParam
     * @see io.github.nichetoolkit.rest.error.license.LicenseVerifyException
     */
    public LicenseExtraParam getExtraParam() throws LicenseVerifyException {
        LicenseExtraParam extraParam;
        try {
            setupServerContainer();
            extraParam = new LicenseExtraParam();
            extraParam.setIpAddress(ServerContainer.ipAddress);
            extraParam.setMacAddress(ServerContainer.macAddress);
            extraParam.setCpuSerial(ServerContainer.cpuSerial);
            extraParam.setBoardSerial(ServerContainer.boardSerial);
        } catch (RestException exception) {
            log.error("It is failed to obtain server hardware information, error: {}", exception.getMessage(), exception);
            throw new LicenseVerifyException(LicenseErrorStatus.LICENSE_SERVER_INFO_ERROR);
        }
        return extraParam;
    }

    /**
     * <code>getIpAddress</code>
     * <p>The get ip address getter method.</p>
     * @return {@link java.util.List} <p>The get ip address return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public List<String> getIpAddress() throws RestException {
        List<InetAddress> inetAddresses = getLocalInetAddress();
        if (GeneralUtils.isNotEmpty(inetAddresses)) {
            return inetAddresses.stream().map(InetAddress::getHostAddress).distinct().map(String::toLowerCase).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * <code>getMacAddress</code>
     * <p>The get mac address getter method.</p>
     * @return {@link java.util.List} <p>The get mac address return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    public List<String> getMacAddress() throws RestException {
        List<InetAddress> inetAddresses = getLocalInetAddress();
        if (GeneralUtils.isNotEmpty(inetAddresses)) {
            return inetAddresses.stream().map(this::getMacByInetAddress).distinct().collect(Collectors.toList());
        }
        return null;
    }

    /**
     * <code>getCpuSerial</code>
     * <p>The get cpu serial getter method.</p>
     * @return {@link java.lang.String} <p>The get cpu serial return object is <code>String</code> type.</p>
     * @throws LicenseErrorException {@link io.github.nichetoolkit.rest.error.license.LicenseErrorException} <p>The license error exception is <code>LicenseErrorException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.license.LicenseErrorException
     */
    protected abstract String getCpuSerial() throws LicenseErrorException;

    /**
     * <code>getBoardSerial</code>
     * <p>The get board serial getter method.</p>
     * @return {@link java.lang.String} <p>The get board serial return object is <code>String</code> type.</p>
     * @throws LicenseErrorException {@link io.github.nichetoolkit.rest.error.license.LicenseErrorException} <p>The license error exception is <code>LicenseErrorException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.license.LicenseErrorException
     */
    protected abstract String getBoardSerial() throws LicenseErrorException;

    /**
     * <code>getLocalInetAddress</code>
     * <p>The get local inet address getter method.</p>
     * @return {@link java.util.List} <p>The get local inet address return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
    private List<InetAddress> getLocalInetAddress() throws RestException {
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
     * <code>getMacByInetAddress</code>
     * <p>The get mac by inet address getter method.</p>
     * @param inetAddress {@link java.net.InetAddress} <p>The inet address parameter is <code>InetAddress</code> type.</p>
     * @return {@link java.lang.String} <p>The get mac by inet address return object is <code>String</code> type.</p>
     * @see java.net.InetAddress
     * @see java.lang.String
     */
    private String getMacByInetAddress(InetAddress inetAddress) {
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
