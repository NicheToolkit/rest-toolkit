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

@Slf4j
public abstract class LicenseServerInfo {

    private static class ServerContainer {
        private static List<String> ipAddress = null;
        private static List<String> macAddress = null;
        private static String cpuSerial = null;
        private static String boardSerial = null;
    }

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

    public static LicenseServerInfo getServerInfo() throws LicenseVerifyException {
        try {
            return serverInfo(null);
        } catch (LicenseErrorException exception) {
            throw new LicenseVerifyException(exception);
        }
    }

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

    public List<String> getIpAddress() throws RestException {
        List<InetAddress> inetAddresses = getLocalInetAddress();
        if (GeneralUtils.isNotEmpty(inetAddresses)) {
            return inetAddresses.stream().map(InetAddress::getHostAddress).distinct().map(String::toLowerCase).collect(Collectors.toList());
        }
        return null;
    }

    public List<String> getMacAddress() throws RestException {
        List<InetAddress> inetAddresses = getLocalInetAddress();
        if (GeneralUtils.isNotEmpty(inetAddresses)) {
            return inetAddresses.stream().map(this::getMacByInetAddress).distinct().collect(Collectors.toList());
        }
        return null;
    }

    protected abstract String getCpuSerial() throws LicenseErrorException;

    protected abstract String getBoardSerial() throws LicenseErrorException;

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
