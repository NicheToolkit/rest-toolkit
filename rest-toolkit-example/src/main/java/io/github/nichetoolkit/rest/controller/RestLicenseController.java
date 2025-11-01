package io.github.nichetoolkit.rest.controller;

import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.error.license.LicenseVerifyException;
import io.github.nichetoolkit.rest.license.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <code>RestLicenseController</code>
 * <p>The rest license controller class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.bind.annotation.RestController
 * @see org.springframework.web.bind.annotation.RequestMapping
 * @since Jdk1.8
 */
@RestController
@RequestMapping("/license")
public class RestLicenseController {

    /**
     * <code>testLicense</code>
     * <p>The test license method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The test license return object is <code>RestResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.license.RestLicense
     * @see org.springframework.web.bind.annotation.RequestMapping
     */
    @RestLicense
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public RestResult<?> testLicense() {
        return RestResult.success();
    }

    /**
     * <code>serverInfo</code>
     * <p>The server info method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The server info return object is <code>RestResult</code> type.</p>
     * @throws LicenseVerifyException {@link io.github.nichetoolkit.rest.error.license.LicenseVerifyException} <p>The license verify exception is <code>LicenseVerifyException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.RequestMapping
     * @see io.github.nichetoolkit.rest.error.license.LicenseVerifyException
     */
    @RequestMapping(value = "/server", method = RequestMethod.GET)
    public RestResult<LicenseServerInfo> serverInfo() throws LicenseVerifyException {
        LicenseServerInfo serverInfo = LicenseServerInfo.getServerInfo();
        return RestResult.success(serverInfo);
    }

    /**
     * <code>createLicense</code>
     * <p>The create license method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The create license return object is <code>RestResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.RequestMapping
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public RestResult<LicenseResult> createLicense() {
        LicenseResult licenseResult = LicenseCreatorWorker.createLicense();
        return RestResult.success(licenseResult);
    }

    /**
     * <code>verifyLicense</code>
     * <p>The verify license method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The verify license return object is <code>RestResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.RequestMapping
     */
    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public RestResult<LicenseResult> verifyLicense() {
        LicenseResult licenseResult = LicenseVerifierWorker.verifyLicense();
        return RestResult.success(licenseResult);
    }
}
