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

public class LicenseAutoInstallListener implements ApplicationListener<ContextRefreshedEvent> {

    private final RestLicenseProperties licenseProperties;
    private final LicenseVerifyParam licenseVerify;

    private static String licenseMd5 = "";
    private static boolean licenseLoad = false;

    public LicenseAutoInstallListener(RestLicenseProperties licenseProperties) {
        super();
        this.licenseProperties = licenseProperties;
        this.licenseVerify = licenseProperties.verifyParam();
    }

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
