package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.license.LicenseCreateParam;
import io.github.nichetoolkit.rest.license.LicenseExtraParam;
import io.github.nichetoolkit.rest.license.LicenseVerifyParam;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.DateUtils;
import io.github.nichetoolkit.rest.util.FileUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Date;
import java.util.List;

/**
 * <code>RestLicenseProperties</code>
 * <p>The rest license properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Getter
 * @see lombok.Setter
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "nichetoolkit.rest.license")
public class RestLicenseProperties {
    /**
     * <code>subject</code>
     * {@link java.lang.String} <p>The <code>subject</code> field.</p>
     * @see java.lang.String
     */
    private String subject;
    /**
     * <code>storePass</code>
     * {@link java.lang.String} <p>The <code>storePass</code> field.</p>
     * @see java.lang.String
     */
    private String storePass;
    /**
     * <code>licensePath</code>
     * {@link java.lang.String} <p>The <code>licensePath</code> field.</p>
     * @see java.lang.String
     */
    private String licensePath;

    /**
     * <code>creator</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties.Creator} <p>The <code>creator</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties.Creator
     * @see org.springframework.boot.context.properties.NestedConfigurationProperty
     */
    @NestedConfigurationProperty
    private Creator creator = new Creator();
    /**
     * <code>checker</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties.Checker} <p>The <code>checker</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties.Checker
     * @see org.springframework.boot.context.properties.NestedConfigurationProperty
     */
    @NestedConfigurationProperty
    private Checker checker = new Checker();
    /**
     * <code>verifier</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties.Verifier} <p>The <code>verifier</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties.Verifier
     * @see org.springframework.boot.context.properties.NestedConfigurationProperty
     */
    @NestedConfigurationProperty
    private Verifier verifier = new Verifier();

    /**
     * <code>listener</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties.Listener} <p>The <code>listener</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties.Listener
     * @see org.springframework.boot.context.properties.NestedConfigurationProperty
     */
    @NestedConfigurationProperty
    private Listener listener = new Listener();

    /**
     * <code>Listener</code>
     * <p>The listener class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Getter
     * @see lombok.Setter
     * @since Jdk1.8
     */
    @Getter
    @Setter
    public static class Listener {
        /**
         * <code>intercept</code>
         * {@link java.lang.Boolean} <p>The <code>intercept</code> field.</p>
         * @see java.lang.Boolean
         */
        private Boolean intercept = false;
        /**
         * <code>install</code>
         * {@link java.lang.Boolean} <p>The <code>install</code> field.</p>
         * @see java.lang.Boolean
         */
        private Boolean install = false;
    }

    /**
     * <code>Checker</code>
     * <p>The checker class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Getter
     * @see lombok.Setter
     * @since Jdk1.8
     */
    @Getter
    @Setter
    public static class Checker {
        /**
         * <code>enabled</code>
         * {@link java.lang.Boolean} <p>The <code>enabled</code> field.</p>
         * @see java.lang.Boolean
         */
        private Boolean enabled = false;
        /**
         * <code>ipCheck</code>
         * <p>The <code>ipCheck</code> field.</p>
         */
        private boolean ipCheck;
        /**
         * <code>ipAddress</code>
         * {@link java.util.List} <p>The <code>ipAddress</code> field.</p>
         * @see java.util.List
         */
        private List<String> ipAddress;
        /**
         * <code>macCheck</code>
         * <p>The <code>macCheck</code> field.</p>
         */
        private boolean macCheck;
        /**
         * <code>macAddress</code>
         * {@link java.util.List} <p>The <code>macAddress</code> field.</p>
         * @see java.util.List
         */
        private List<String> macAddress;
        /**
         * <code>cpuCheck</code>
         * <p>The <code>cpuCheck</code> field.</p>
         */
        private boolean cpuCheck;
        /**
         * <code>cpuSerial</code>
         * {@link java.lang.String} <p>The <code>cpuSerial</code> field.</p>
         * @see java.lang.String
         */
        private String cpuSerial;
        /**
         * <code>boardCheck</code>
         * <p>The <code>boardCheck</code> field.</p>
         */
        private boolean boardCheck;
        /**
         * <code>boardSerial</code>
         * {@link java.lang.String} <p>The <code>boardSerial</code> field.</p>
         * @see java.lang.String
         */
        private String boardSerial;
    }

    /**
     * <code>Creator</code>
     * <p>The creator class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Getter
     * @see lombok.Setter
     * @since Jdk1.8
     */
    @Getter
    @Setter
    public static class Creator {
        /**
         * <code>enabled</code>
         * {@link java.lang.Boolean} <p>The <code>enabled</code> field.</p>
         * @see java.lang.Boolean
         */
        private Boolean enabled = false;

        /**
         * <code>tempPath</code>
         * {@link java.lang.String} <p>The <code>tempPath</code> field.</p>
         * @see java.lang.String
         */
        private String tempPath;

        /**
         * <code>serverPrefix</code>
         * {@link java.lang.String} <p>The <code>serverPrefix</code> field.</p>
         * @see java.lang.String
         */
        private String serverPrefix;

        /**
         * <code>subject</code>
         * {@link java.lang.String} <p>The <code>subject</code> field.</p>
         * @see java.lang.String
         */
        private String subject;

        /**
         * <code>description</code>
         * {@link java.lang.String} <p>The <code>description</code> field.</p>
         * @see java.lang.String
         */
        private String description;

        /**
         * <code>storePass</code>
         * {@link java.lang.String} <p>The <code>storePass</code> field.</p>
         * @see java.lang.String
         */
        private String storePass;

        /**
         * <code>licensePath</code>
         * {@link java.lang.String} <p>The <code>licensePath</code> field.</p>
         * @see java.lang.String
         */
        private String licensePath;

        /**
         * <code>privateAlias</code>
         * {@link java.lang.String} <p>The <code>privateAlias</code> field.</p>
         * @see java.lang.String
         */
        private String privateAlias;

        /**
         * <code>keyPass</code>
         * {@link java.lang.String} <p>The <code>keyPass</code> field.</p>
         * @see java.lang.String
         */
        private String keyPass;

        /**
         * <code>privateKeysStorePath</code>
         * {@link java.lang.String} <p>The <code>privateKeysStorePath</code> field.</p>
         * @see java.lang.String
         */
        private String privateKeysStorePath;

        /**
         * <code>issuedTime</code>
         * {@link java.lang.String} <p>The <code>issuedTime</code> field.</p>
         * @see java.lang.String
         */
        private String issuedTime;

        /**
         * <code>expiryTime</code>
         * {@link java.lang.String} <p>The <code>expiryTime</code> field.</p>
         * @see java.lang.String
         */
        private String expiryTime;

        /**
         * <code>consumerType</code>
         * {@link java.lang.String} <p>The <code>consumerType</code> field.</p>
         * @see java.lang.String
         */
        private String consumerType = "user";

        /**
         * <code>consumerSize</code>
         * {@link java.lang.Integer} <p>The <code>consumerSize</code> field.</p>
         * @see java.lang.Integer
         */
        private Integer consumerSize = 1;

        /**
         * <code>setTempPath</code>
         * <p>The set temp path setter method.</p>
         * @param tempPath {@link java.lang.String} <p>The temp path parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public void setTempPath(String tempPath) {
            this.tempPath = FileUtils.resource(tempPath);
            if (GeneralUtils.isNotEmpty(tempPath)) {
                FileUtils.createFile(tempPath);
            }
        }

        /**
         * <code>setLicensePath</code>
         * <p>The set license path setter method.</p>
         * @param licensePath {@link java.lang.String} <p>The license path parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public void setLicensePath(String licensePath) {
            this.licensePath = FileUtils.resource(licensePath);
        }

        /**
         * <code>setPrivateKeysStorePath</code>
         * <p>The set private keys store path setter method.</p>
         * @param privateKeysStorePath {@link java.lang.String} <p>The private keys store path parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public void setPrivateKeysStorePath(String privateKeysStorePath) {
            this.privateKeysStorePath = FileUtils.resource(privateKeysStorePath);
        }

        /**
         * <code>getIssuedTime</code>
         * <p>The get issued time getter method.</p>
         * @return {@link java.util.Date} <p>The get issued time return object is <code>Date</code> type.</p>
         * @see java.util.Date
         */
        public Date getIssuedTime() {
            if (GeneralUtils.isNotEmpty(this.issuedTime)) {
                return DateUtils.parseTime(this.issuedTime);
            }
            return new Date();
        }

        /**
         * <code>getExpiryTime</code>
         * <p>The get expiry time getter method.</p>
         * @return {@link java.util.Date} <p>The get expiry time return object is <code>Date</code> type.</p>
         * @see java.util.Date
         */
        public Date getExpiryTime() {
            if (GeneralUtils.isNotEmpty(this.expiryTime)) {
                return DateUtils.parseTime(this.expiryTime);
            }
            return DateUtils.addMonths(new Date(), 3);
        }
    }

    /**
     * <code>Verifier</code>
     * <p>The verifier class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Getter
     * @see lombok.Setter
     * @since Jdk1.8
     */
    @Getter
    @Setter
    public static class Verifier {
        /**
         * <code>enabled</code>
         * {@link java.lang.Boolean} <p>The <code>enabled</code> field.</p>
         * @see java.lang.Boolean
         */
        private Boolean enabled = false;
        /**
         * <code>subject</code>
         * {@link java.lang.String} <p>The <code>subject</code> field.</p>
         * @see java.lang.String
         */
        private String subject;
        /**
         * <code>storePass</code>
         * {@link java.lang.String} <p>The <code>storePass</code> field.</p>
         * @see java.lang.String
         */
        private String storePass;
        /**
         * <code>licensePath</code>
         * {@link java.lang.String} <p>The <code>licensePath</code> field.</p>
         * @see java.lang.String
         */
        private String licensePath;
        /**
         * <code>publicAlias</code>
         * {@link java.lang.String} <p>The <code>publicAlias</code> field.</p>
         * @see java.lang.String
         */
        private String publicAlias;
        /**
         * <code>publicKeysStorePath</code>
         * {@link java.lang.String} <p>The <code>publicKeysStorePath</code> field.</p>
         * @see java.lang.String
         */
        private String publicKeysStorePath;

        /**
         * <code>setLicensePath</code>
         * <p>The set license path setter method.</p>
         * @param licensePath {@link java.lang.String} <p>The license path parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public void setLicensePath(String licensePath) {
            this.licensePath = FileUtils.resource(licensePath);
        }

        /**
         * <code>setPublicKeysStorePath</code>
         * <p>The set public keys store path setter method.</p>
         * @param publicKeysStorePath {@link java.lang.String} <p>The public keys store path parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public void setPublicKeysStorePath(String publicKeysStorePath) {
            this.publicKeysStorePath = FileUtils.resource(publicKeysStorePath);
        }
    }

    /**
     * <code>setSubject</code>
     * <p>The set subject setter method.</p>
     * @param subject {@link java.lang.String} <p>The subject parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setSubject(String subject) {
        this.subject = subject;
        RestOptional.ofEmptyable(this.creator.subject).ifEmpty(() -> {
            this.creator.setSubject(subject);
        });
        RestOptional.ofEmptyable(this.verifier.subject).ifEmpty(() -> {
            this.verifier.setSubject(subject);
        });
    }

    /**
     * <code>setStorePass</code>
     * <p>The set store pass setter method.</p>
     * @param storePass {@link java.lang.String} <p>The store pass parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setStorePass(String storePass) {
        this.storePass = storePass;
        RestOptional.ofEmptyable(this.creator.storePass).ifEmpty(() -> {
            this.creator.setStorePass(storePass);
        });
        RestOptional.ofEmptyable(this.verifier.storePass).ifEmpty(() -> {
            this.verifier.setStorePass(storePass);
        });
    }

    /**
     * <code>setLicensePath</code>
     * <p>The set license path setter method.</p>
     * @param licensePath {@link java.lang.String} <p>The license path parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setLicensePath(String licensePath) {
        this.licensePath = licensePath;
        RestOptional.ofEmptyable(this.creator.licensePath).ifEmpty(() -> {
            this.creator.setLicensePath(licensePath);
        });
        RestOptional.ofEmptyable(this.verifier.licensePath).ifEmpty(() -> {
            this.verifier.setLicensePath(licensePath);
        });
    }

    /**
     * <code>createParam</code>
     * <p>The create param method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseCreateParam} <p>The create param return object is <code>LicenseCreateParam</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseCreateParam
     */
    public LicenseCreateParam createParam() {
        LicenseCreateParam.LicenseCreateParamBuilder<?, ?> builder = LicenseCreateParam.builder()
                .subject(this.creator.getSubject())
                .description(this.creator.getDescription())
                .privateAlias(this.creator.getPrivateAlias())
                .keyPass(this.creator.getKeyPass())
                .storePass(this.creator.getStorePass())
                .licensePath(this.creator.getLicensePath())
                .privateAlias(this.creator.getPrivateAlias())
                .privateKeysStorePath(this.creator.getPrivateKeysStorePath())
                .issuedTime(this.creator.getIssuedTime())
                .expiryTime(this.creator.getExpiryTime())
                .consumerType(this.creator.getConsumerType())
                .consumerSize(this.creator.getConsumerSize());
        if (this.checker.enabled) {
            LicenseExtraParam extraParam = new LicenseExtraParam();
            BeanUtils.copyNonnullProperties(this.checker, extraParam);
            builder.extraInfo(extraParam);
        }
        return builder.build();
    }

    /**
     * <code>verifyParam</code>
     * <p>The verify param method.</p>
     * @return {@link io.github.nichetoolkit.rest.license.LicenseVerifyParam} <p>The verify param return object is <code>LicenseVerifyParam</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseVerifyParam
     */
    public LicenseVerifyParam verifyParam() {
        return LicenseVerifyParam.builder()
                .subject(this.verifier.getSubject())
                .publicAlias(this.verifier.getPublicAlias())
                .storePass(this.verifier.getStorePass())
                .licensePath(this.verifier.getLicensePath())
                .publicKeysStorePath(this.verifier.getPublicKeysStorePath())
                .build();
    }

}
