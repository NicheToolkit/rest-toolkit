package io.github.nichetoolkit.rest.worker;

import io.github.nichetoolkit.rest.configure.RestIpAddressProperties;
import io.github.nichetoolkit.rest.constant.UtilConstants;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>IpAddressWorker</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class IpAddressWorker {

    private HttpServletRequest httpServletRequest;

    private RestIpAddressProperties ipAddressProperties;

    private static IpAddressWorker INSTANCE = null;

    public static IpAddressWorker getInstance() {
        return INSTANCE;
    }

    @Autowired(required = false)
    public IpAddressWorker( RestIpAddressProperties ipAddressProperties, HttpServletRequest httpServletRequest) {
        this.ipAddressProperties = ipAddressProperties;
        this.httpServletRequest = httpServletRequest;

    }

    @PostConstruct
    public void ipAddressWorkerInit() {
        log.debug("ipAddress properties: {}", JsonUtils.parseJson(ipAddressProperties));
        INSTANCE = this;
    }

    public String ipAddress() {
        return ipAddress(httpServletRequest,ipAddressProperties.getEnabled(),ipAddressProperties.getIgnoredIpAddresses());
    }

    public static String ipAddress(HttpServletRequest httpServletRequest) {
        return ipAddress(httpServletRequest,false, Collections.emptyList());
    }
    
    public static String ipAddress(HttpServletRequest httpServletRequest, List<String> ignoredIpAddresses) {
        return ipAddress(httpServletRequest,true, ignoredIpAddresses);
    }

    public static String ipAddress(HttpServletRequest httpServletRequest, boolean enabled, List<String> ignoredIpAddresses) {
        String ip = httpServletRequest.getHeader(UtilConstants.X_FORWARDED_FOR_HEADER);
        if (GeneralUtils.isEmpty(ip)) {
            return httpServletRequest.getRemoteAddr();
        }
        List<String> ipAddressList = ipAddress(ip, enabled, ignoredIpAddresses);
        if (ipAddressList.isEmpty()) {
            return ip;
        }
        return ipAddressList.get(ipAddressList.size() - 1);
    }

    public static List<String> ipAddress(String ip) {
        return ipAddress(ip,false, Collections.emptyList());
    }

    public static List<String> ipAddress(String ip, List<String> ignoredIpAddresses) {
        return ipAddress(ip,true,ignoredIpAddresses);
    }

    public static List<String> ipAddress(String ip, boolean enabled, List<String> ignoredIpAddresses) {
        if (GeneralUtils.isEmpty(ip)) {
            return Collections.emptyList();
        }
        if (!ip.contains(UtilConstants.IP_REGEX)) {
            return Collections.singletonList(ip);
        }
        String[] ipAddressArray = ip.split(UtilConstants.IP_REGEX,-1);
        List<String> ipAddressList = new ArrayList<>();
        if (enabled) {
            for(String ipaddress : ipAddressArray) {
                if (!ignoredIpAddresses.contains(ipaddress)) {
                    ipAddressList.add(ipaddress);
                }
            }
        } else {
            ipAddressList.addAll(Arrays.asList(ipAddressArray));
        }
        return ipAddressList;
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static String remoteIpAddress(HttpServletRequest request) {
        String ip = request.getHeader(UtilConstants.X_FORWARDED_FOR_HEADER);
        if (GeneralUtils.isEmpty(ip) || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = request.getHeader(UtilConstants.PROXY_CLIENT_IP_HEADER);
        }
        if (GeneralUtils.isEmpty(ip) || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = request.getHeader(UtilConstants.WL_PROXY_CLIENT_IP_HEADER);
        }
        if (GeneralUtils.isEmpty(ip) || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = request.getHeader(UtilConstants.HTTP_CLIENT_IP_HEADER);
        }
        if (GeneralUtils.isEmpty(ip) || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = request.getHeader(UtilConstants.HTTP_X_FORWARDED_FOR_HEADER);
        }
        if (GeneralUtils.isEmpty(ip) || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String userIpAddress(HttpServletRequest request) {
        String ip = request.getHeader(UtilConstants.X_REAL_IP_HEADER);
        if (ip == null || ip.length() == 0 || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = request.getHeader(UtilConstants.X_FORWARDED_FOR_HEADER);
        }
        if (ip == null || ip.length() == 0 || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (UtilConstants.LOCALHOST_IPV6.equals(ip)) {
                ip = UtilConstants.LOCALHOST_IPV4;
            }
        }
        if (UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = UtilConstants.LOCALHOST_IPV4;
            return ip;
        } else {
            int index = ip.indexOf(44);
            if (index >= 0) {
                ip = ip.substring(0, index);
            }
            return ip;
        }
    }

    @SuppressWarnings("Duplicates")
    public static String baseIpAddress(HttpServletRequest request) {
        String ip;
        String xIp = request.getHeader(UtilConstants.X_REAL_IP_HEADER);
        String xFor = request.getHeader(UtilConstants.X_FORWARDED_FOR_HEADER);
        if (GeneralUtils.isNotEmpty(xFor) && !UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(xFor)) {
            int index = xFor.indexOf(UtilConstants.IP_REGEX);
            if (index >= 0) {
                return xFor.substring(0, index);
            }
            return xFor;
        }
        ip = xIp;
        if (GeneralUtils.isEmpty(xIp) || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(xIp)) {
            ip = request.getHeader(UtilConstants.PROXY_CLIENT_IP_HEADER);
        }
        if (GeneralUtils.isEmpty(ip) || ip.length() == 0 || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = request.getHeader(UtilConstants.WL_PROXY_CLIENT_IP_HEADER);
        }
        if (GeneralUtils.isEmpty(ip) || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = request.getHeader(UtilConstants.HTTP_CLIENT_IP_HEADER);
        }
        if (GeneralUtils.isEmpty(ip) || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = request.getHeader(UtilConstants.HTTP_X_FORWARDED_FOR_HEADER);
        }
        if (GeneralUtils.isEmpty(ip) || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (UtilConstants.LOCALHOST_IPV6.equals(ip)) {
                ip = UtilConstants.LOCALHOST_IPV4;
            }
        }
        return ip;
    }

}
