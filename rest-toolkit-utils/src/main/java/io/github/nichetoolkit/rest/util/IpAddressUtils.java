package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.constant.UtilConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <code>IpAddressUtils</code>
 * <p>The ip address utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class IpAddressUtils {

    /**
     * <code>getHttpServletRequest</code>
     * <p>The get http servlet request getter method.</p>
     * @return {@link javax.servlet.http.HttpServletRequest} <p>The get http servlet request return object is <code>HttpServletRequest</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     */
    private static HttpServletRequest getHttpServletRequest() {
        return BeanUtils.beanOfType(HttpServletRequest.class);
    }

    /**
     * <code>ipAddress</code>
     * <p>The ip address method.</p>
     * @return {@link java.lang.String} <p>The ip address return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String ipAddress() {
        return ipAddress(getHttpServletRequest());
    }

    /**
     * <code>ipAddress</code>
     * <p>The ip address method.</p>
     * @param httpServletRequest {@link javax.servlet.http.HttpServletRequest} <p>The http servlet request parameter is <code>HttpServletRequest</code> type.</p>
     * @return {@link java.lang.String} <p>The ip address return object is <code>String</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see java.lang.String
     */
    public static String ipAddress(HttpServletRequest httpServletRequest) {
        return ipAddress(httpServletRequest,Collections.emptyList());
    }

    /**
     * <code>ipAddress</code>
     * <p>The ip address method.</p>
     * @param httpServletRequest {@link javax.servlet.http.HttpServletRequest} <p>The http servlet request parameter is <code>HttpServletRequest</code> type.</p>
     * @param ignoredIpAddresses {@link java.util.List} <p>The ignored ip addresses parameter is <code>List</code> type.</p>
     * @return {@link java.lang.String} <p>The ip address return object is <code>String</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see java.util.List
     * @see java.lang.String
     */
    public static String ipAddress(HttpServletRequest httpServletRequest, List<String> ignoredIpAddresses) {
        if (GeneralUtils.isEmpty(httpServletRequest)) {
            return UtilConstants.LOCALHOST_IPV4;
        }
        String ip = httpServletRequest.getHeader(UtilConstants.X_FORWARDED_FOR_HEADER);
        if (GeneralUtils.isEmpty(ip)) {
            return httpServletRequest.getRemoteAddr();
        }
        List<String> ipAddressList = ipAddress(ip, ignoredIpAddresses);
        if (ipAddressList.isEmpty()) {
            return ip;
        }
        return ipAddressList.get(ipAddressList.size() - 1);
    }

    /**
     * <code>ipAddress</code>
     * <p>The ip address method.</p>
     * @param ip {@link java.lang.String} <p>The ip parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The ip address return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see java.util.List
     */
    public static List<String> ipAddress(String ip) {
        return ipAddress(ip,Collections.emptyList());
    }

    /**
     * <code>ipAddress</code>
     * <p>The ip address method.</p>
     * @param ip                 {@link java.lang.String} <p>The ip parameter is <code>String</code> type.</p>
     * @param ignoredIpAddresses {@link java.util.List} <p>The ignored ip addresses parameter is <code>List</code> type.</p>
     * @return {@link java.util.List} <p>The ip address return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see java.util.List
     */
    public static List<String> ipAddress(String ip, List<String> ignoredIpAddresses) {
        if (GeneralUtils.isEmpty(ip)) {
            return Collections.emptyList();
        }
        if (!ip.contains(UtilConstants.IP_REGEX)) {
            return Collections.singletonList(ip);
        }
        String[] ipAddressArray = ip.split(UtilConstants.IP_REGEX,-1);
        List<String> ipAddressList = new ArrayList<>();
        if (GeneralUtils.isNotEmpty(ignoredIpAddresses)) {
            for(String ipAddress : ipAddressArray) {
                if (!ignoredIpAddresses.contains(ipAddress)) {
                    ipAddressList.add(ipAddress);
                }
            }
        } else {
            ipAddressList.addAll(Arrays.asList(ipAddressArray));
        }
        return ipAddressList;
    }

    /**
     * <code>remoteIpAddress</code>
     * <p>The remote ip address method.</p>
     * @return {@link java.lang.String} <p>The remote ip address return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String remoteIpAddress() {
        return remoteIpAddress(getHttpServletRequest());
    }

    /**
     * <code>remoteIpAddress</code>
     * <p>The remote ip address method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @return {@link java.lang.String} <p>The remote ip address return object is <code>String</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings({"Duplicates", "unused"})
    public static String remoteIpAddress(HttpServletRequest request) {
        if (GeneralUtils.isEmpty(request)) {
            return UtilConstants.LOCALHOST_IPV4;
        }
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

    /**
     * <code>userIpAddress</code>
     * <p>The user ip address method.</p>
     * @return {@link java.lang.String} <p>The user ip address return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String userIpAddress() {
        return userIpAddress(getHttpServletRequest());
    }

    /**
     * <code>userIpAddress</code>
     * <p>The user ip address method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @return {@link java.lang.String} <p>The user ip address return object is <code>String</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see java.lang.String
     */
    public static String userIpAddress(HttpServletRequest request) {
        if (GeneralUtils.isEmpty(request)) {
            return UtilConstants.LOCALHOST_IPV4;
        }
        String ip = request.getHeader(UtilConstants.X_REAL_IP_HEADER);
        if (ip == null || ip.isEmpty() || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = request.getHeader(UtilConstants.X_FORWARDED_FOR_HEADER);
        }
        if (ip == null || ip.isEmpty() || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (UtilConstants.LOCALHOST_IPV6.equals(ip)) {
                ip = UtilConstants.LOCALHOST_IPV4;
            }
        }
        if (UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
            ip = UtilConstants.LOCALHOST_IPV4;
        } else {
            int index = ip.indexOf(44);
            if (index >= 0) {
                ip = ip.substring(0, index);
            }
        }
        return ip;
    }

    /**
     * <code>baseIpAddress</code>
     * <p>The base ip address method.</p>
     * @param request {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @return {@link java.lang.String} <p>The base ip address return object is <code>String</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
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
        if (GeneralUtils.isEmpty(ip) || ip.isEmpty() || UtilConstants.UNKNOWN_HEADER.equalsIgnoreCase(ip)) {
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
