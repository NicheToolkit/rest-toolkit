package io.github.nichetoolkit.rest.license;

import io.github.nichetoolkit.rest.configure.RestLicenseProperties;
import io.github.nichetoolkit.rest.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public LicenseCreatorWorker(RestLicenseProperties licenseProperties) {
        this.licenseProperties = licenseProperties;
        this.licenseCreate = licenseProperties.createParam();
    }

    /**
     * <code>licenseCreatorWorkerInit</code>
     * <p>The license creator worker init method.</p>
     * @see javax.annotation.PostConstruct
     */
    @PostConstruct
    public void licenseCreatorWorkerInit() {
        log.debug("The creator    properties: {}", JsonUtils.parseJson(this.licenseCreate));
        INSTANCE = this;
    }

    /**
     * <code>refreshLicense</code>
     * <p>The refresh license method.</p>
     * @param licensePath {@link java.lang.String} <p>The license path parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    private static void refreshLicense(String licensePath) {
        INSTANCE.licenseCreate.setLicensePath(licensePath);
    }

    /**
     * <code>licenseProperties</code>
     * <p>The license properties method.</p>
     * @return {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties} <p>The license properties return object is <code>RestLicenseProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties
     */
    public static RestLicenseProperties licenseProperties() {
        return INSTANCE.licenseProperties;
    }

    /**
     * <code>licenseCreate</code>
     * <p>The license create method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseCreateParam} <p>The license create return object is <code>LicenseCreateParam</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseCreateParam
     */
    public static LicenseCreateParam licenseCreate() {
        return INSTANCE.licenseCreate;
    }

    /**
     * <code>getInstance</code>
     * <p>The get instance getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseCreatorWorker} <p>The get instance return object is <code>LicenseCreatorWorker</code> type.</p>
     */
    public static LicenseCreatorWorker getInstance() {
        return INSTANCE;
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
        LicenseCreateParam generateParam = LicenseCreateParam.builder().build();
        BeanUtils.copyNonnullProperties(licenseCreate(), generateParam);
        if (GeneralUtils.isNotEmpty(createParam)) {
            BeanUtils.copyNonnullProperties(createParam, generateParam);
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
     * @param request     {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response    {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.String
     * @see javax.servlet.http.HttpServletRequest
     * @see javax.servlet.http.HttpServletResponse
     */
    public static void downloadLicense(String licensePath, HttpServletRequest request, HttpServletResponse response) {
        File file = new File(licensePath);
        if(!file.exists()){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
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
     * @param request  {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param fileName {@link java.lang.String} <p>The file name parameter is <code>String</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see javax.servlet.http.HttpServletResponse
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
