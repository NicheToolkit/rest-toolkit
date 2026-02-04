package io.github.nichetoolkit.rest.license;

import de.schlichtherle.license.*;
import de.schlichtherle.xml.GenericCertificate;
import io.github.nichetoolkit.rest.error.license.LicenseErrorException;
import io.github.nichetoolkit.rest.error.license.LicenseErrorStatus;
import io.github.nichetoolkit.rest.error.license.LicenseVerifyException;
import io.github.nichetoolkit.rest.helper.CloseableHelper;
import io.github.nichetoolkit.rest.util.DateUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * <code>LicenseWorkerManager</code>
 * <p>The license worker manager class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see de.schlichtherle.license.LicenseManager
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
class LicenseWorkerManager extends LicenseManager {
    /**
     * <code>XML_CHARSET</code>
     * {@link java.lang.String} <p>The constant <code>XML_CHARSET</code> field.</p>
     * @see java.lang.String
     */
    private static final String XML_CHARSET = "UTF-8";
    /**
     * <code>DEFAULT_BUFF_SIZE</code>
     * <p>The constant <code>DEFAULT_BUFF_SIZE</code> field.</p>
     */
    private static final int DEFAULT_BUFF_SIZE = 8 * 1024;

    /**
     * <code>LicenseWorkerManager</code>
     * <p>Instantiates a new license worker manager.</p>
     */
    private LicenseWorkerManager() {
    }

    /**
     * <code>LicenseWorkerManager</code>
     * <p>Instantiates a new license worker manager.</p>
     * @param licenseParam {@link de.schlichtherle.license.LicenseParam} <p>The license param parameter is <code>LicenseParam</code> type.</p>
     * @see de.schlichtherle.license.LicenseParam
     */
    private LicenseWorkerManager(LicenseParam licenseParam) {
        super(licenseParam);
    }

    /**
     * <code>createWorker</code>
     * <p>The create worker method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseWorkerManager} <p>The create worker return object is <code>LicenseWorkerManager</code> type.</p>
     */
    public static LicenseWorkerManager createWorker() {
        return new LicenseWorkerManager();
    }

    /**
     * <code>createWorker</code>
     * <p>The create worker method.</p>
     * @param licenseParam {@link de.schlichtherle.license.LicenseParam} <p>The license param parameter is <code>LicenseParam</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseWorkerManager} <p>The create worker return object is <code>LicenseWorkerManager</code> type.</p>
     * @see de.schlichtherle.license.LicenseParam
     */
    public static LicenseWorkerManager createWorker(LicenseParam licenseParam) {
        return new LicenseWorkerManager(licenseParam);
    }

    @Override
    protected synchronized byte[] create(LicenseContent content, LicenseNotary notary) throws Exception {
        initialize(content);
        /* 加入自己额外的许可内容信息认证 */
        this.validateLicense(content);
        final GenericCertificate certificate = notary.sign(content);
        return getPrivacyGuard().cert2key(certificate);
    }


    @Override
    protected synchronized LicenseContent install(final byte[] key, final LicenseNotary notary) throws Exception {
        final GenericCertificate certificate = getPrivacyGuard().key2cert(key);
        notary.verify(certificate);
        final LicenseContent licenseContent = this.readLicense(certificate.getEncoded());
        /* 增加额外的自己的license校验方法，校验ip、mac、cpu序列号等 */
        this.validate(licenseContent);
        setLicenseKey(key);
        setCertificate(certificate);
        return licenseContent;
    }

    @Override
    protected synchronized LicenseContent verify(final LicenseNotary notary) throws Exception {
        final byte[] key = getLicenseKey();
        if (GeneralUtils.isEmpty(key)) {
            throw new LicenseErrorException(LicenseErrorStatus.LICENSE_NOT_INSTALLED_ERROR, "verify license", getLicenseParam().getSubject());
        }
        GenericCertificate certificate = getPrivacyGuard().key2cert(key);
        notary.verify(certificate);
        final LicenseContent content = this.readLicense(certificate.getEncoded());
        /* 增加额外的自己的license校验方法，校验ip、mac、cpu序列号等 */
        this.validate(content);
        setCertificate(certificate);
        return content;
    }

    /**
     * <code>validateLicense</code>
     * <p>The validate license method.</p>
     * @param content {@link de.schlichtherle.license.LicenseContent} <p>The content parameter is <code>LicenseContent</code> type.</p>
     * @throws LicenseErrorException {@link io.github.nichetoolkit.rest.error.license.LicenseErrorException} <p>The license error exception is <code>LicenseErrorException</code> type.</p>
     * @see de.schlichtherle.license.LicenseContent
     * @see io.github.nichetoolkit.rest.error.license.LicenseErrorException
     */
    protected synchronized void validateLicense(final LicenseContent content) throws LicenseErrorException {
        // 当前时间
        final Date now = new Date();
        // 生效时间
        final Date notBefore = content.getNotBefore();
        // 失效时间
        final Date notAfter = content.getNotAfter();
        if (GeneralUtils.isNotEmpty(notAfter) && now.after(notAfter)) {
            log.error("The license expiration time cannot be earlier than the current time, now time: {}, not after time: {}", DateUtils.formatTime(now), DateUtils.formatTime(notAfter));
            throw new LicenseErrorException(LicenseErrorStatus.LICENSE_NOT_AFTER_ERROR, "validate license", "notAfter");
        }
        if (GeneralUtils.isNotEmpty(notBefore) && GeneralUtils.isNotEmpty(notAfter) && notAfter.before(notBefore)) {
            log.error("The license effective time cannot be later than the expiration time, not before time: {}, not after time: {}", DateUtils.formatTime(notBefore), DateUtils.formatTime(notAfter));
            throw new LicenseErrorException(LicenseErrorStatus.LICENSE_NOT_BEFORE_ERROR, "validate license", "notBefore");
        }
        final String consumerType = content.getConsumerType();
        if (GeneralUtils.isEmpty(consumerType)) {
            log.error("The consumer type of license cannot be empty");
            throw new LicenseErrorException(LicenseErrorStatus.LICENSE_CONSUMER_TYPE_ERROR, "validate license", "consumerType");
        }
    }

    @Override
    protected synchronized void validate(final LicenseContent content) throws LicenseContentException {
        // 当前时间
        final Date now = new Date();
        final Date notAfter = content.getNotAfter();
        if (now.after(notAfter)) {
            log.error("The system license has expired, and the current time has exceeded the license validity date: {}", DateUtils.formatTime(content.getNotAfter()));
            throw new LicenseVerifyException(LicenseErrorStatus.LICENSE_EXPIRED_ERROR, "validate license", "notAfter");
        }
        // 首先调用父类的validate方法
        super.validate(content);
        // 然后校验自定义的License参数 License中可被允许的参数信息
        LicenseExtraParam extraParam = (LicenseExtraParam) content.getExtra();
        // extraParam 为空，未开启校验
        if (GeneralUtils.isEmpty(extraParam)) {
            return;
        }
        // 当前服务器真实的参数信息
        RestServerInfo extraInfo = LicenseServerInfo.extraInfo();
        if (GeneralUtils.isEmpty(extraInfo)) {
            log.error("It is failed to obtain server hardware information, license extra: {}, server info: {}", JacksonUtils.parseJson(extraParam), JacksonUtils.parseJson(extraInfo));
            throw new LicenseVerifyException(LicenseErrorStatus.LICENSE_SERVER_INFO_ERROR, "validate license", "serverInfo");
        }
        // 校验IP地址
        if (extraParam.isIpCheck() && ofIpAddress(extraParam.getIpAddress(), extraInfo.getIpAddress())) {
            log.error("The system license is invalid, as the current server's IP is not within the authorized scope, license ip address: {}, server ip address: {}", JacksonUtils.parseJson(extraParam.getIpAddress()), JacksonUtils.parseJson(extraInfo.getIpAddress()));
            throw new LicenseVerifyException(LicenseErrorStatus.LICENSE_IP_OVERSTEP_ERROR, "validate license", "ipAddress");
        }
        // 校验Mac地址
        if (extraParam.isMacCheck() && ofIpAddress(extraParam.getMacAddress(), extraInfo.getMacAddress())) {
            log.error("The system license is invalid, as the Mac address of the current server is not within the authorized scope, license mac address: {}, server mac address: {}", JacksonUtils.parseJson(extraParam.getMacAddress()), JacksonUtils.parseJson(extraInfo.getMacAddress()));
            throw new LicenseVerifyException(LicenseErrorStatus.LICENSE_MAC_OVERSTEP_ERROR, "validate license", "macAddress");
        }
        // 校验主板序列号
        if (extraParam.isBoardCheck() && ofSerial(extraParam.getBoardSerial(), extraInfo.getBoardSerial())) {
            log.error("The system license is invalid, as the current server's motherboard serial number is not within the authorized scope, license board address: {}, server board address: {}", JacksonUtils.parseJson(extraParam.getBoardSerial()), JacksonUtils.parseJson(extraInfo.getBoardSerial()));
            throw new LicenseVerifyException(LicenseErrorStatus.LICENSE_BOARD_SERIAL_OVERSTEP_ERROR, "validate license", "boardSerial");
        }
        // 校验CPU序列号
        if (extraParam.isCpuCheck() && ofSerial(extraParam.getCpuSerial(), extraInfo.getCpuSerial())) {
            log.error("The system license is invalid, as the CPU serial number of the current server is not within the authorized scope, license cpu address: {}, server cpu address: {}", JacksonUtils.parseJson(extraParam.getCpuSerial()), JacksonUtils.parseJson(extraInfo.getCpuSerial()));
            throw new LicenseVerifyException(LicenseErrorStatus.LICENSE_CPU_SERIAL_OVERSTEP_ERROR, "validate license", "cpuSerial");
        }
    }

    /**
     * <code>readLicense</code>
     * <p>The read license method.</p>
     * @param encoded {@link java.lang.String} <p>The encoded parameter is <code>String</code> type.</p>
     * @return {@link de.schlichtherle.license.LicenseContent} <p>The read license return object is <code>LicenseContent</code> type.</p>
     * @throws LicenseErrorException {@link io.github.nichetoolkit.rest.error.license.LicenseErrorException} <p>The license error exception is <code>LicenseErrorException</code> type.</p>
     * @see java.lang.String
     * @see de.schlichtherle.license.LicenseContent
     * @see io.github.nichetoolkit.rest.error.license.LicenseErrorException
     */
    private LicenseContent readLicense(String encoded) throws LicenseErrorException {
        BufferedInputStream inputStream = null;
        XMLDecoder decoder = null;
        try {
            inputStream = new BufferedInputStream(new ByteArrayInputStream(encoded.getBytes(XML_CHARSET)));
            decoder = new XMLDecoder(new BufferedInputStream(inputStream, DEFAULT_BUFF_SIZE), null, null);
            return (LicenseContent) decoder.readObject();
        } catch (UnsupportedEncodingException exception) {
            log.error("The encoding of license is unsupported, error: {}", exception.getMessage(), exception);
            throw new LicenseErrorException(LicenseErrorStatus.LICENSE_ENCODING_UNSUPPORTED, "read license", "encoded");
        } finally {
            CloseableHelper.close(decoder, inputStream);
        }
    }

    /**
     * <code>ofIpAddress</code>
     * <p>The of ip address method.</p>
     * @param expectedList {@link java.util.List} <p>The expected list parameter is <code>List</code> type.</p>
     * @param serverList   {@link java.util.List} <p>The server list parameter is <code>List</code> type.</p>
     * @return boolean <p>The of ip address return object is <code>boolean</code> type.</p>
     * @see java.util.List
     */
    private boolean ofIpAddress(List<String> expectedList, List<String> serverList) {
        /* 如果期望的IP列表空直接返回false，因为既然验证ip，这一项必须要有元素 */
        if (GeneralUtils.isEmpty(expectedList)) {
            return true;
        }
        /* 如果当前服务器的IP列表空直接返回false，因为服务器不可能获取不到ip，没有的话验证个锤子 */
        if (GeneralUtils.isEmpty(serverList)) {
            return true;
        }
        for (String expected : expectedList) {
            if (serverList.contains(expected.trim())) {
                return false;
            }
        }
        return true;

    }

    /**
     * <code>ofSerial</code>
     * <p>The of serial method.</p>
     * @param expectedSerial {@link java.lang.String} <p>The expected serial parameter is <code>String</code> type.</p>
     * @param serverSerial   {@link java.lang.String} <p>The server serial parameter is <code>String</code> type.</p>
     * @return boolean <p>The of serial return object is <code>boolean</code> type.</p>
     * @see java.lang.String
     */
    private boolean ofSerial(String expectedSerial, String serverSerial) {
        if (GeneralUtils.isNotEmpty(expectedSerial)) {
            if (GeneralUtils.isNotEmpty(serverSerial)) {
                return !expectedSerial.equals(serverSerial);
            }
            return true;
        } else {
            return false;
        }
    }
}
