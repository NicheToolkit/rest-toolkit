package io.github.nichetoolkit.rest.service;

import io.github.nichetoolkit.rest.RestUsernoteAdvice;
import io.github.nichetoolkit.rest.userlog.RestRequestPack;
import io.github.nichetoolkit.rest.userlog.RestResponsePack;
import io.github.nichetoolkit.rest.userlog.RestUsernotePack;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * <code>RestUsernoteService</code>
 * <p>The rest usernote service class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestUsernoteAdvice
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Service
 * @since Jdk1.8
 */
@Slf4j
@Service
public class RestUsernoteService implements RestUsernoteAdvice {
    @Override
    public void doUsernoteHandle(@NonNull RestRequestPack requestPack,@NonNull RestResponsePack responsePack,@NonNull RestUsernotePack usernotePack) {
        log.info("the request pack: {}", JsonUtils.parseJson(requestPack));
        log.info("the response pack: {}", JsonUtils.parseJson(responsePack));
        System.out.println(JsonUtils.parseJson(usernotePack));
        log.info("the usernote pack: {}", JsonUtils.parseJson(usernotePack));
    }
}
