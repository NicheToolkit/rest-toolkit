package io.github.nichetoolkit.rest.license;

import io.github.nichetoolkit.rest.configure.RestLicenseProperties;
import io.github.nichetoolkit.rest.holder.ApplicationContextHolder;
import io.github.nichetoolkit.rest.util.FileUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.jspecify.annotations.NonNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * <code>LicenseInstallListener</code>
 * <p>The license install listener class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.context.ApplicationListener
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class LicenseInstallListener implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * <code>licenseVerify</code>
     * {@link io.github.nichetoolkit.rest.license.LicenseVerifyParam} <p>The <code>licenseVerify</code> field.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseVerifyParam
     */
    private final LicenseVerifyParam licenseVerify;
    /**
     * <code>serverStop</code>
     * <p>The <code>serverStop</code> field.</p>
     */
    private final boolean serverStop;
    /**
     * <code>licenseMd5</code>
     * {@link java.lang.String} <p>The constant <code>licenseMd5</code> field.</p>
     * @see java.lang.String
     */
    private volatile static String licenseMd5 = "";
    /**
     * <code>licenseRefresh</code>
     * <p>The constant <code>licenseRefresh</code> field.</p>
     */
    private static boolean licenseRefresh = false;

    /**
     * <code>LicenseInstallListener</code>
     * <p>Instantiates a new license install listener.</p>
     * @param licenseProperties {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties} <p>The license properties parameter is <code>RestLicenseProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties
     */
    public LicenseInstallListener(RestLicenseProperties licenseProperties) {
        super();
        this.licenseVerify = licenseProperties.verifyParam();
        this.serverStop = licenseProperties.getListener().getStop();
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
     * <code>installLicense</code>
     * <p>The install license method.</p>
     */
    private void installLicense() {
        log.info("================= Install License ================= ");
        LicenseResult licenseResult = LicenseWorker.installLicense(licenseVerify);
        if (licenseResult.getResult()) {
            log.info("================= Install Success ================= ");
        } else {
            log.info("================= Install Failure ================= ");
            if (serverStop || licenseResult.getStopServer()) {
                int exitCode = SpringApplication.exit(ApplicationContextHolder.getApplicationContext(), licenseResult::getErrorCode);
                System.exit(exitCode);
            }
        }
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        String licensePath = licenseVerify.getLicensePath();
        if (GeneralUtils.isNotEmpty(licensePath)) {
            installLicense();
            try {
                String licenseMd5 = licenseMd5(licensePath);
                if (GeneralUtils.isEmpty(LicenseInstallListener.licenseMd5)) {
                    LicenseInstallListener.licenseMd5 = licenseMd5;
                }
                licenseRefresh = true;
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * <code>installLicenseSchedule</code>
     * <p>The install license schedule method.</p>
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     * @see org.springframework.scheduling.annotation.Scheduled
     * @see java.lang.Exception
     */
    @Scheduled(cron = "0/10 * * * * ?")
    protected void installLicenseSchedule() throws Exception {
        if (licenseRefresh) {
            String licenseMd5 = licenseMd5(licenseVerify.getLicensePath());
            if (GeneralUtils.isNotEmpty(licenseMd5) && !licenseMd5.equals(LicenseInstallListener.licenseMd5)) {
                log.info("The license file has refreshed, it will be reinstall!");
                installLicense();
                LicenseInstallListener.licenseMd5 = licenseMd5;
            }
        }
    }

}
