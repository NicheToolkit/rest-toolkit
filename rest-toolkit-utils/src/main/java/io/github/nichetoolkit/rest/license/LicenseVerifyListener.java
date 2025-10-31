package io.github.nichetoolkit.rest.license;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.configure.RestLicenseProperties;
import io.github.nichetoolkit.rest.util.FileUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.IoStreamUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public abstract class LicenseVerifyListener {

    private static final List<LicenseVerifyListener> LICENSE_VERIFY_LISTENERS = new ArrayList<>(16);

    public static List<LicenseVerifyListener> licenseVerifyListeners() {
        return LICENSE_VERIFY_LISTENERS;
    }

    public synchronized static void addVerifyListener(LicenseVerifyListener verifyListener) {
        LICENSE_VERIFY_LISTENERS.add(verifyListener);
    }

    public LicenseVerifyListener() {
        addVerifyListener(this);
    }

    public abstract boolean verify(LicenseExtraParam licenseExtra) throws RestException;


}
