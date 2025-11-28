package io.github.nichetoolkit.rest.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.schlichtherle.license.AbstractKeyStoreParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;


/**
 * <code>LicenseKeyStoreParam</code>
 * <p>The license key store param class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see de.schlichtherle.license.AbstractKeyStoreParam
 * @see lombok.Getter
 * @see lombok.Setter
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseKeyStoreParam extends AbstractKeyStoreParam {
    /**
     * <code>alias</code>
     * {@link java.lang.String} <p>The <code>alias</code> field.</p>
     * @see java.lang.String
     */
    private final String alias;
    /**
     * <code>storePwd</code>
     * {@link java.lang.String} <p>The <code>storePwd</code> field.</p>
     * @see java.lang.String
     */
    private final String storePwd;
    /**
     * <code>keyPwd</code>
     * {@link java.lang.String} <p>The <code>keyPwd</code> field.</p>
     * @see java.lang.String
     */
    private final String keyPwd;
    /**
     * <code>storePath</code>
     * {@link java.lang.String} <p>The <code>storePath</code> field.</p>
     * @see java.lang.String
     */
    private final String storePath;

    /**
     * <code>LicenseKeyStoreParam</code>
     * <p>Instantiates a new license key store param.</p>
     * @param clazz    {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param alias    {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @param storePwd {@link java.lang.String} <p>The store pwd parameter is <code>String</code> type.</p>
     * @param keyPwd   {@link java.lang.String} <p>The key pwd parameter is <code>String</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.String
     */
    public LicenseKeyStoreParam(Class<?> clazz, String resource, String alias, String storePwd, String keyPwd) {
        super(clazz, resource);
        this.storePath = resource;
        this.alias = alias;
        this.storePwd = storePwd;
        this.keyPwd = keyPwd;
    }


    @Override
    public InputStream getStream() throws IOException {
        File storeFile = ResourceUtils.getFile(this.storePath);
        return Files.newInputStream(storeFile.toPath());
    }
}
