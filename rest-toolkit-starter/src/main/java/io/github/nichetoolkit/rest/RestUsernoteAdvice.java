package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.userlog.RestRequestPack;
import io.github.nichetoolkit.rest.userlog.RestResponsePack;
import io.github.nichetoolkit.rest.userlog.RestUsernotePack;
import org.springframework.lang.NonNull;

/**
 * <code>RestUsernoteAdvice</code>
 * <p>The type rest usernote advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestUsernoteAdvice {

    /**
     * <code>doUsernoteHandle</code>
     * <p>the usernote handle method.</p>
     * @param requestPack  {@link io.github.nichetoolkit.rest.userlog.RestRequestPack} <p>the request pack parameter is <code>RestRequestPack</code> type.</p>
     * @param responsePack {@link io.github.nichetoolkit.rest.userlog.RestResponsePack} <p>the response pack parameter is <code>RestResponsePack</code> type.</p>
     * @param usernotePack {@link io.github.nichetoolkit.rest.userlog.RestUsernotePack} <p>the usernote pack parameter is <code>RestUsernotePack</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.RestRequestPack
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.userlog.RestResponsePack
     * @see io.github.nichetoolkit.rest.userlog.RestUsernotePack
     */
    void doUsernoteHandle(@NonNull RestRequestPack requestPack,@NonNull RestResponsePack responsePack,@NonNull RestUsernotePack usernotePack);

}
