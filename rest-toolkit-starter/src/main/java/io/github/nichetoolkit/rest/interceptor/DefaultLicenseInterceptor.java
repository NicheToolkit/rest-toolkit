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

public class DefaultLicenseInterceptor implements HandlerInterceptor {
    private final RestLicenseProperties licenseProperties;
    private final LicenseVerifyParam licenseVerify;

    public DefaultLicenseInterceptor(RestLicenseProperties licenseProperties) {
        this.licenseProperties = licenseProperties;
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
