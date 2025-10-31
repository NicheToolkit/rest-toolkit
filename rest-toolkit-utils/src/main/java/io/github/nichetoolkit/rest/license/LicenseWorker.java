package io.github.nichetoolkit.rest.license;

import de.schlichtherle.license.*;
import io.github.nichetoolkit.rest.error.license.LicenseErrorException;
import io.github.nichetoolkit.rest.error.license.LicenseErrorStatus;
import io.github.nichetoolkit.rest.util.DateUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import javax.security.auth.x500.X500Principal;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.prefs.Preferences;

/**
 * <code>LicenseWorker</code>
 * <p>The license worker class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class LicenseWorker {

    /**
     * <code>DEFAULT_HOLDER_AND_ISSUER</code>
     * {@link javax.security.auth.x500.X500Principal} <p>The constant <code>DEFAULT_HOLDER_AND_ISSUER</code> field.</p>
     * @see javax.security.auth.x500.X500Principal
     */
    private final static X500Principal DEFAULT_HOLDER_AND_ISSUER = new X500Principal("CN=a, OU=a, O=a, L=a, ST=a, C=a");


    /**
     * <code>createLicense</code>
     * <p>The create license method.</p>
     * @param creatorParam {@link io.github.nichetoolkit.rest.license.LicenseCreateParam} <p>The creator param parameter is <code>LicenseCreateParam</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseResult} <p>The create license return object is <code>LicenseResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseCreateParam
     * @see io.github.nichetoolkit.rest.license.LicenseResult
     */
    public static LicenseResult createLicense(LicenseCreateParam creatorParam) {
        try {
            LicenseParam licenseParam = LicenseWorker.createParam(creatorParam);
            LicenseContent licenseContent = LicenseWorker.createContent(creatorParam);
            LicenseManager licenseManager = LicenseWorkerManager.createWorker(licenseParam);
            File licenseFile = new File(creatorParam.getLicensePath());
            licenseManager.store(licenseContent, licenseFile);
            return new LicenseResult("The license created successful！", licenseContent);
        } catch (Exception exception) {
            log.error("The license created has error!, error: {}", exception.getMessage(), exception);
            GeneralUtils.printStackTrace(exception);
            return new LicenseResult("The license created has error!", exception);
        }
    }

    /**
     * <code>downloadLicense</code>
     * <p>The download license method.</p>
     * @param creatorParam {@link io.github.nichetoolkit.rest.license.LicenseCreateParam} <p>The creator param parameter is <code>LicenseCreateParam</code> type.</p>
     * @return {@link java.io.InputStream} <p>The download license return object is <code>InputStream</code> type.</p>
     * @throws LicenseErrorException {@link io.github.nichetoolkit.rest.error.license.LicenseErrorException} <p>The license error exception is <code>LicenseErrorException</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseCreateParam
     * @see java.io.InputStream
     * @see io.github.nichetoolkit.rest.error.license.LicenseErrorException
     */
    public static InputStream downloadLicense(LicenseCreateParam creatorParam) throws LicenseErrorException {
        try {
            LicenseParam licenseParam = LicenseWorker.createParam(creatorParam);
            LicenseContent licenseContent = LicenseWorker.createContent(creatorParam);
            LicenseManager licenseManager = LicenseWorkerManager.createWorker(licenseParam);
            File licenseFile = new File(creatorParam.getLicensePath());
            licenseManager.store(licenseContent, licenseFile);
            return Files.newInputStream(licenseFile.toPath());
        } catch (Exception exception) {
            log.error("The license created has error!, error: {}", exception.getMessage(), exception);
            GeneralUtils.printStackTrace(exception);
            throw new LicenseErrorException(LicenseErrorStatus.LICENSE_CREATE_ERROR, exception);
        }
    }

    /**
     * <code>createParam</code>
     * <p>The create param method.</p>
     * @param createParam {@link io.github.nichetoolkit.rest.license.LicenseCreateParam} <p>The create param parameter is <code>LicenseCreateParam</code> type.</p>
     * @return {@link de.schlichtherle.license.LicenseParam} <p>The create param return object is <code>LicenseParam</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseCreateParam
     * @see de.schlichtherle.license.LicenseParam
     */
    public static LicenseParam createParam(LicenseCreateParam createParam) {
        Preferences preferences = Preferences.userNodeForPackage(LicenseCreator.class);
        CipherParam cipherParam = new DefaultCipherParam(createParam.getStorePass());
        KeyStoreParam privateStoreParam = new DefaultKeyStoreParam(LicenseCreator.class
                , createParam.getPrivateKeysStorePath()
                , createParam.getPrivateAlias()
                , createParam.getStorePass()
                , createParam.getKeyPass());
        return new DefaultLicenseParam(createParam.getSubject(), preferences, privateStoreParam, cipherParam);
    }

    /**
     * <code>createParam</code>
     * <p>The create param method.</p>
     * @param verifyParam {@link io.github.nichetoolkit.rest.license.LicenseVerifyParam} <p>The verify param parameter is <code>LicenseVerifyParam</code> type.</p>
     * @return {@link de.schlichtherle.license.LicenseParam} <p>The create param return object is <code>LicenseParam</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseVerifyParam
     * @see de.schlichtherle.license.LicenseParam
     */
    public static LicenseParam createParam(LicenseVerifyParam verifyParam) {
        Preferences preferences = Preferences.userNodeForPackage(LicenseVerifier.class);
        CipherParam cipherParam = new DefaultCipherParam(verifyParam.getStorePass());
        KeyStoreParam publicStoreParam = new DefaultKeyStoreParam(LicenseVerifier.class
                /* 公钥库存储路径 */
                , verifyParam.getPublicKeysStorePath()
                /* 公匙别名 */
                , verifyParam.getPublicAlias()
                /* 公钥库访问密码 */
                , verifyParam.getStorePass()
                , null);
        return new DefaultLicenseParam(verifyParam.getSubject(), preferences, publicStoreParam, cipherParam);
    }

    /**
     * <code>createContent</code>
     * <p>The create content method.</p>
     * @param createParam {@link io.github.nichetoolkit.rest.license.LicenseCreateParam} <p>The create param parameter is <code>LicenseCreateParam</code> type.</p>
     * @return {@link de.schlichtherle.license.LicenseContent} <p>The create content return object is <code>LicenseContent</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseCreateParam
     * @see de.schlichtherle.license.LicenseContent
     */
    public static LicenseContent createContent(LicenseCreateParam createParam) {
        LicenseContent licenseContent = new LicenseContent();
        licenseContent.setHolder(DEFAULT_HOLDER_AND_ISSUER);
        licenseContent.setIssuer(DEFAULT_HOLDER_AND_ISSUER);
        /* 设置证书名称 */
        licenseContent.setSubject(createParam.getSubject());
        /* 设置证书有效期 */
        licenseContent.setIssued(createParam.getIssuedTime());
        /* 设置证书生效日期 */
        licenseContent.setNotBefore(createParam.getIssuedTime());
        /* 设置证书失效日期 */
        licenseContent.setNotAfter(createParam.getExpiryTime());
        /* 设置证书用户类型 */
        licenseContent.setConsumerType(createParam.getConsumerType());
        /* 设置证书用户数量 */
        licenseContent.setConsumerAmount(createParam.getConsumerSize());
        /* 设置证书描述信息 */
        licenseContent.setInfo(createParam.getDescription());
        /* 设置证书扩展信息（对象 -- 额外的ip、mac、cpu等信息） */
        licenseContent.setExtra(createParam.getExtraInfo());
        return licenseContent;
    }

    /**
     * <code>installLicense</code>
     * <p>The install license method.</p>
     * @param verifyParam {@link io.github.nichetoolkit.rest.license.LicenseVerifyParam} <p>The verify param parameter is <code>LicenseVerifyParam</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseResult} <p>The install license return object is <code>LicenseResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseVerifyParam
     * @see io.github.nichetoolkit.rest.license.LicenseResult
     */
    public static synchronized LicenseResult installLicense(LicenseVerifyParam verifyParam) {
        try {
            /* 1、初始化License证书参数 */
            LicenseParam licenseParam = LicenseWorker.createParam(verifyParam);
            /* 2、创建License证书管理机对象 */
            // 走自定义的Lic管理
            LicenseWorkerManager licenseManager = LicenseWorkerManager.createWorker(licenseParam);
            /* 3、获取要安装的证书文件 */
            File licenseFile = ResourceUtils.getFile(verifyParam.getLicensePath());
            /* 4、如果之前安装过证书，先卸载之前的证书 */
            licenseManager.uninstall();
            /* 5、开始安装 */
            LicenseContent licenseContent = licenseManager.install(licenseFile);
            log.info("The license installed has successfully!,  issued time: [{} - {}]", DateUtils.formatTime(licenseContent.getNotBefore()), DateUtils.formatTime(licenseContent.getNotAfter()));
            return new LicenseResult("The license installed has successfully!", licenseContent);
        } catch (Exception exception) {
            log.error("The license installed has error, error: {}", exception.getMessage(), exception);
            GeneralUtils.printStackTrace(exception);
            return new LicenseResult("The license installed has error!", exception);
        }
    }

    /**
     * <code>verifyLicense</code>
     * <p>The verify license method.</p>
     * @param verifyParam {@link io.github.nichetoolkit.rest.license.LicenseVerifyParam} <p>The verify param parameter is <code>LicenseVerifyParam</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseResult} <p>The verify license return object is <code>LicenseResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseVerifyParam
     * @see io.github.nichetoolkit.rest.license.LicenseResult
     */
    public static synchronized LicenseResult verifyLicense(LicenseVerifyParam verifyParam) {
        /* 1、初始化License证书参数 */
        LicenseParam licenseParam = LicenseWorker.createParam(verifyParam);
        /* 2、创建License证书管理机对象 */
        LicenseWorkerManager licenseManager = LicenseWorkerManager.createWorker(licenseParam);
        try {
            LicenseContent licenseContent = licenseManager.verify();
            log.info("The license verified has successfully!, issued time: [{} - {}]", DateUtils.formatTime(licenseContent.getNotBefore()), DateUtils.formatTime(licenseContent.getNotAfter()));
            return new LicenseResult("The license verified has successfully!", licenseContent);
        } catch (Exception exception) {
            log.error("The license verified has error, error: {}", exception.getMessage(), exception);
            GeneralUtils.printStackTrace(exception);
            return new LicenseResult("The license verified has error!", exception);
        }
    }

}
