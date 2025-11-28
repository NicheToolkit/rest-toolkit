package io.github.nichetoolkit.rest.license;

import io.github.nichetoolkit.rest.RestException;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>LicenseVerifyListener</code>
 * <p>The license verify listener class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk17
 */
public abstract class LicenseVerifyListener {

    /**
     * <code>LICENSE_VERIFY_LISTENERS</code>
     * {@link java.util.List} <p>The constant <code>LICENSE_VERIFY_LISTENERS</code> field.</p>
     * @see java.util.List
     */
    private static final List<LicenseVerifyListener> LICENSE_VERIFY_LISTENERS = new ArrayList<>(16);

    /**
     * <code>licenseVerifyListeners</code>
     * <p>The license verify listeners method.</p>
     * @return {@link java.util.List} <p>The license verify listeners return object is <code>List</code> type.</p>
     * @see java.util.List
     */
    public static List<LicenseVerifyListener> licenseVerifyListeners() {
        return LICENSE_VERIFY_LISTENERS;
    }

    /**
     * <code>addVerifyListener</code>
     * <p>The add verify listener method.</p>
     * @param verifyListener {@link io.github.nichetoolkit.rest.license.LicenseVerifyListener} <p>The verify listener parameter is <code>LicenseVerifyListener</code> type.</p>
     */
    public synchronized static void addVerifyListener(LicenseVerifyListener verifyListener) {
        LICENSE_VERIFY_LISTENERS.add(verifyListener);
    }

    /**
     * <code>LicenseVerifyListener</code>
     * <p>Instantiates a new license verify listener.</p>
     */
    public LicenseVerifyListener() {
        addVerifyListener(this);
    }

    /**
     * <code>verify</code>
     * <p>The verify method.</p>
     * @param licenseExtra {@link io.github.nichetoolkit.rest.license.LicenseExtraParam} <p>The license extra parameter is <code>LicenseExtraParam</code> type.</p>
     * @return boolean <p>The verify return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseExtraParam
     * @see io.github.nichetoolkit.rest.RestException
     */
    public abstract boolean verify(LicenseExtraParam licenseExtra) throws RestException;


}
