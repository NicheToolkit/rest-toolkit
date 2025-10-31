package io.github.nichetoolkit.rest.license;

import io.github.nichetoolkit.rest.configure.RestLicenseProperties;
import io.github.nichetoolkit.rest.util.FileUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * <code>LicenseAutoInstallListener</code>
 * <p>The license auto install listener class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.context.ApplicationListener
 * @since Jdk1.8
 */
public class LicenseAutoInstallListener implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * <code>licenseProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties} <p>The <code>licenseProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties
     */
    private final RestLicenseProperties licenseProperties;
    /**
     * <code>licenseVerify</code>
     * {@link io.github.nichetoolkit.rest.license.LicenseVerifyParam} <p>The <code>licenseVerify</code> field.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseVerifyParam
     */
    private final LicenseVerifyParam licenseVerify;

    /**
     * <code>licenseMd5</code>
     * {@link java.lang.String} <p>The constant <code>licenseMd5</code> field.</p>
     * @see java.lang.String
     */
    private static String licenseMd5 = "";
    /**
     * <code>licenseLoad</code>
     * <p>The constant <code>licenseLoad</code> field.</p>
     */
    private static boolean licenseLoad = false;

    /**
     * <code>LicenseAutoInstallListener</code>
     * <p>Instantiates a new license auto install listener.</p>
     * @param licenseProperties {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties} <p>The license properties parameter is <code>RestLicenseProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties
     */
    public LicenseAutoInstallListener(RestLicenseProperties licenseProperties) {
        super();
        this.licenseProperties = licenseProperties;
        this.licenseVerify = licenseProperties.verifyParam();
    }

    /**
     * <code>licenseMd5</code>
     * <p>The license md 5 method.</p>
     * @param licensePath {@link java.lang.String} <p>The license path parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The license md 5 return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    protected String licenseMd5(String licensePath) {
        String licenseMd5 = "";
        try {
            File licenseFile = ResourceUtils.getFile(licensePath);
            if (licenseFile.exists()) {
                byte[] licenseBytes = FileUtils.read(licenseFile);
                licenseMd5 = DigestUtils.md2Hex(licenseBytes);
            }
        } catch (FileNotFoundException ignored) {
        }
        return licenseMd5;
    }

    /**
     * <code>autoInstallLicense</code>
     * <p>The auto install license method.</p>
     */
    private void autoInstallLicense() {
        Boolean autoInstall = licenseProperties.getListener().getAutoInstall();
        if (autoInstall) {
            LicenseWorker.installLicense(licenseVerify);
        }
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        String licensePath = licenseVerify.getLicensePath();
        if (GeneralUtils.isNotEmpty(licensePath)) {
            autoInstallLicense();
            try {
                String licenseMd5 = licenseMd5(licensePath);
                licenseLoad = true;
                if (GeneralUtils.isEmpty(licenseMd5)) {
                    LicenseAutoInstallListener.licenseMd5 = licenseMd5;
                }
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * <code>timer</code>
     * <p>The timer method.</p>
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     * @see org.springframework.scheduling.annotation.Scheduled
     * @see java.lang.Exception
     */
    @Scheduled(cron = "0/10 * * * * ?")
    protected void timer() throws Exception {
        if (licenseLoad) {
            String licenseMd5 = licenseMd5(licenseVerify.getLicensePath());
            if (!licenseMd5.equals(LicenseAutoInstallListener.licenseMd5)) {
                autoInstallLicense();
                LicenseAutoInstallListener.licenseMd5 = licenseMd5;
            }
        }
    }

}
