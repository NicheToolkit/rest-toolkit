package io.github.nichetoolkit.rest.license;

import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.configure.RestLicenseProperties;
import io.github.nichetoolkit.rest.error.license.LicenseLackError;
import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.util.*;
import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * <code>LicenseCreatorWorker</code>
 * <p>The license creator worker class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class LicenseCreatorWorker {

    /**
     * <code>INSTANCE</code>
     * {@link io.github.nichetoolkit.rest.license.LicenseCreatorWorker} <p>The constant <code>INSTANCE</code> field.</p>
     */
    private static LicenseCreatorWorker INSTANCE = null;

    /**
     * <code>licenseProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties} <p>The <code>licenseProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties
     */
    private final RestLicenseProperties licenseProperties;

    /**
     * <code>licenseCreate</code>
     * {@link io.github.nichetoolkit.rest.license.LicenseCreateParam} <p>The <code>licenseCreate</code> field.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseCreateParam
     */
    private final LicenseCreateParam licenseCreate;

    /**
     * <code>LicenseCreatorWorker</code>
     * <p>Instantiates a new license creator worker.</p>
     * @param licenseProperties {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties} <p>The license properties parameter is <code>RestLicenseProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties
     */
    public LicenseCreatorWorker(RestLicenseProperties licenseProperties) {
        this.licenseProperties = licenseProperties;
        this.licenseCreate = licenseProperties.createParam();
    }

    /**
     * <code>licenseCreatorWorkerInit</code>
     * <p>The license creator worker init method.</p>
     * @see jakarta.annotation.PostConstruct
     */
    @PostConstruct
    public void licenseCreatorWorkerInit() {
        log.debug("The creator    properties: {}", JsonUtils.parseJson(this.licenseCreate));
        INSTANCE = this;
    }

    /**
     * <code>instance</code>
     * <p>The instance method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseCreatorWorker} <p>The instance return object is <code>LicenseCreatorWorker</code> type.</p>
     */
    public static LicenseCreatorWorker instance() {
        return RestOptional.ofNullable(INSTANCE).orNullThrow(LicenseLackError::new);
    }

    /**
     * <code>refreshLicense</code>
     * <p>The refresh license method.</p>
     * @param licensePath {@link java.lang.String} <p>The license path parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    private static void refreshLicense(String licensePath) {
        instance().licenseCreate.setLicensePath(licensePath);
    }

    /**
     * <code>licenseProperties</code>
     * <p>The license properties method.</p>
     * @return {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties} <p>The license properties return object is <code>RestLicenseProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties
     */
    public static RestLicenseProperties licenseProperties() {
        return instance().licenseProperties;
    }

    /**
     * <code>licenseCreate</code>
     * <p>The license create method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseCreateParam} <p>The license create return object is <code>LicenseCreateParam</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseCreateParam
     */
    public static LicenseCreateParam licenseCreate() {
        return instance().licenseCreate;
    }

    /**
     * <code>createLicense</code>
     * <p>The create license method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseResult} <p>The create license return object is <code>LicenseResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseResult
     */
    public static LicenseResult createLicense() {
        return createLicense(licenseCreate());
    }

    /**
     * <code>createLicense</code>
     * <p>The create license method.</p>
     * @param createParam {@link io.github.nichetoolkit.rest.license.LicenseCreateParam} <p>The create param parameter is <code>LicenseCreateParam</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseResult} <p>The create license return object is <code>LicenseResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseCreateParam
     * @see io.github.nichetoolkit.rest.license.LicenseResult
     */
    public static synchronized LicenseResult createLicense(LicenseCreateParam createParam) {
        LicenseCreateParam generateParam = null;
        LicenseCreateParam licenseCreate = licenseCreate();
        if (GeneralUtils.isNotEmpty(licenseCreate)) {
            generateParam = LicenseCreateParam.builder().build();
            BeanUtils.copyNonnullProperties(licenseCreate, generateParam);
        }
        if (GeneralUtils.isNotEmpty(createParam)) {
            if (GeneralUtils.isEmpty(generateParam)) {
                generateParam = createParam;
            } else {
                BeanUtils.copyNonnullProperties(createParam, generateParam);
            }
        }
        if (GeneralUtils.isEmpty(generateParam)) {
            throw new LicenseLackError();
        }
        String licensePath = generateParam.getLicensePath();
        String parentPath;
        String fileName = "license.lic";
        if (GeneralUtils.isNotEmpty(licensePath)) {
            File licenseFile = new File(licensePath);
            parentPath = licenseFile.getParent();
            if (licenseFile.isFile()) {
                fileName = licenseFile.getName();
                if (licenseFile.exists()) {
                    licenseFile.delete();
                } else if (!licenseFile.getParentFile().exists()) {
                    FileUtils.createFile(parentPath);
                }
            } else if (!licenseFile.exists()) {
                FileUtils.createFile(licensePath);
            }

            if (!fileName.toLowerCase().endsWith(".lic")) {
                fileName += ".lic";
            }
        } else {
            RestLicenseProperties.Creator creator = licenseProperties().getCreator();
            String tempPath = creator.getTempPath();
            if (GeneralUtils.isEmpty(tempPath)) {
                tempPath = Objects.requireNonNull(FileUtils.createTempFile("license", GeneralUtils.uuid())).toString();
            }
            parentPath = tempPath;
        }
        String refreshLicensePath = parentPath + File.separator + fileName;
        refreshLicense(refreshLicensePath);
        generateParam.setLicensePath(refreshLicensePath);
        return LicenseWorker.createLicense(generateParam);
    }


    /**
     * <code>downloadLicense</code>
     * <p>The download license method.</p>
     * @param licensePath {@link java.lang.String} <p>The license path parameter is <code>String</code> type.</p>
     * @param request     {@link jakarta.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response    {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.lang.String
     * @see jakarta.servlet.http.HttpServletRequest
     * @see jakarta.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     */
    public static void downloadLicense(String licensePath, HttpServletRequest request, HttpServletResponse response) throws ResourceNotFoundException {
        File file = new File(licensePath);
        if(!file.exists()){
            throw new ResourceNotFoundException();
        }
        String fileName = file.getName();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        setAttachmentCoding(request, response, fileName);
        IoStreamUtils.write(response,file);
    }


    /**
     * <code>setAttachmentCoding</code>
     * <p>The set attachment coding setter method.</p>
     * @param request  {@link jakarta.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param fileName {@link java.lang.String} <p>The file name parameter is <code>String</code> type.</p>
     * @see jakarta.servlet.http.HttpServletRequest
     * @see jakarta.servlet.http.HttpServletResponse
     * @see java.lang.String
     */
    private static void setAttachmentCoding(HttpServletRequest request, HttpServletResponse response, String fileName) {
        String browser;
        try {
            browser = request.getHeader("User-Agent");
            if (browser.contains("MSIE 6.0") || browser.contains("MSIE 7.0")) {
                // IE6, IE7 浏览器
                response.addHeader("content-disposition", "attachment;filename="
                        + new String(fileName.getBytes(), "ISO8859-1"));
            } else if (browser.contains("MSIE 8.0")) {
                // IE8
                response.addHeader("content-disposition", "attachment;filename="
                        + URLEncoder.encode(fileName, "UTF-8"));
            } else if (browser.contains("MSIE 9.0")) {
                // IE9
                response.addHeader("content-disposition", "attachment;filename="
                        + URLEncoder.encode(fileName, "UTF-8"));
            } else if (browser.contains("Chrome")) {
                // 谷歌
                response.addHeader("content-disposition",
                        "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            } else if (browser.contains("Safari")) {
                // 苹果
                response.addHeader("content-disposition", "attachment;filename="
                        + new String(fileName.getBytes(), "ISO8859-1"));
            } else {
                // 火狐或者其他的浏览器
                response.addHeader("content-disposition",
                        "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            }
        } catch (Exception exception) {
            GeneralUtils.printStackTrace(exception);
        }
    }

}
