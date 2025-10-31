package io.github.nichetoolkit.rest.interceptor;

import de.schlichtherle.license.LicenseContent;
import io.github.nichetoolkit.rest.configure.RestLicenseProperties;
import io.github.nichetoolkit.rest.error.license.LicenseErrorException;
import io.github.nichetoolkit.rest.error.license.LicenseErrorStatus;
import io.github.nichetoolkit.rest.license.*;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * <code>DefaultLicenseInterceptor</code>
 * <p>The default license interceptor class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.servlet.HandlerInterceptor
 * @since Jdk1.8
 */
public class DefaultLicenseInterceptor implements HandlerInterceptor {
    /**
     * <code>licenseVerify</code>
     * {@link io.github.nichetoolkit.rest.license.LicenseVerifyParam} <p>The <code>licenseVerify</code> field.</p>
     * @see io.github.nichetoolkit.rest.license.LicenseVerifyParam
     */
    private final LicenseVerifyParam licenseVerify;

    /**
     * <code>DefaultLicenseInterceptor</code>
     * <p>Instantiates a new default license interceptor.</p>
     * @param licenseProperties {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties} <p>The license properties parameter is <code>RestLicenseProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties
     */
    public DefaultLicenseInterceptor(RestLicenseProperties licenseProperties) {
        this.licenseVerify = licenseProperties.verifyParam();
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        RestLicense restLicense = method.getAnnotation(RestLicense.class);
        if (GeneralUtils.isEmpty(restLicense)) {
            return true;
        }
        LicenseResult verifyResult = LicenseWorker.verifyLicense(licenseVerify);
        if (!verifyResult.getResult()) {
            throw new LicenseErrorException(LicenseErrorStatus.LICENSE_EXPIRED_ERROR, verifyResult.getException());
        }
        LicenseContent licenseContent = verifyResult.getContent();
        LicenseExtraParam licenseCheck = (LicenseExtraParam) licenseContent.getExtra();
        if (verifyResult.getResult()) {
            /* 增加业务系统监听，是否自定义验证 */
            List<LicenseVerifyListener> licenseVerifyListeners = LicenseVerifyListener.licenseVerifyListeners();
            boolean compare = true;
            for (LicenseVerifyListener listener : licenseVerifyListeners) {
                boolean verify = listener.verify(licenseCheck);
                compare = compare && verify;
            }
            return compare;
        }
        throw new LicenseErrorException(LicenseErrorStatus.LICENSE_EXPIRED_ERROR);
    }

}
