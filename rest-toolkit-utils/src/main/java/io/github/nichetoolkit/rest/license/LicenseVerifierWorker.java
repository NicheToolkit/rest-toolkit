package io.github.nichetoolkit.rest.license;

import io.github.nichetoolkit.rest.configure.RestLicenseProperties;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * <code>LicenseVerifierWorker</code>
 * <p>The license verifier worker class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class LicenseVerifierWorker {
    /**
     * <code>INSTANCE</code>
     * {@link io.github.nichetoolkit.rest.license.LicenseVerifierWorker} <p>The constant <code>INSTANCE</code> field.</p>
     */
    private static LicenseVerifierWorker INSTANCE = null;

    /**
     * <code>licenseVerify</code>
     * {@link io.github.nichetoolkit.rest.license.LicenseVerifyParam} <p>The <code>licenseVerify</code> field.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseVerifyParam
     */
    private final LicenseVerifyParam licenseVerify;

    /**
     * <code>LicenseVerifierWorker</code>
     * <p>Instantiates a new license verifier worker.</p>
     * @param licenseProperties {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties} <p>The license properties parameter is <code>RestLicenseProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public LicenseVerifierWorker(RestLicenseProperties licenseProperties) {
        this.licenseVerify = licenseProperties.verifyParam();
    }

    /**
     * <code>licenseVerifierWorkerInit</code>
     * <p>The license verifier worker init method.</p>
     * @see javax.annotation.PostConstruct
     */
    @PostConstruct
    public void licenseVerifierWorkerInit() {
        log.debug("The verifier      properties: {}", JsonUtils.parseJson(this.licenseVerify));
        INSTANCE = this;
    }

    /**
     * <code>getInstance</code>
     * <p>The get instance getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseVerifierWorker} <p>The get instance return object is <code>LicenseVerifierWorker</code> type.</p>
     */
    public static LicenseVerifierWorker getInstance() {
        return INSTANCE;
    }


    /**
     * <code>licenseVerify</code>
     * <p>The license verify method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseVerifyParam} <p>The license verify return object is <code>LicenseVerifyParam</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseVerifyParam
     */
    public static LicenseVerifyParam licenseVerify() {
        return INSTANCE.licenseVerify;
    }


    /**
     * <code>verifyLicense</code>
     * <p>The verify license method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseResult} <p>The verify license return object is <code>LicenseResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseResult
     */
    public static LicenseResult verifyLicense() {
        return verifyLicense(licenseVerify());
    }

    /**
     * <code>verifyLicense</code>
     * <p>The verify license method.</p>
     * @param licenseVerify {@link io.github.nichetoolkit.rest.license.LicenseVerifyParam} <p>The license verify parameter is <code>LicenseVerifyParam</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseResult} <p>The verify license return object is <code>LicenseResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseVerifyParam
     * @see io.github.nichetoolkit.rest.license.LicenseResult
     */
    public static synchronized LicenseResult verifyLicense(LicenseVerifyParam licenseVerify) {
        return LicenseWorker.verifyLicense(licenseVerify);
    }


}
