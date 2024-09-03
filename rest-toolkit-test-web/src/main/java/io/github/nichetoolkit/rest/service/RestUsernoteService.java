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
 * <p>RestUsernoteService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Service
public class RestUsernoteService implements RestUsernoteAdvice {
    @Override
    public void doUsernoteHandle(@NonNull RestRequestPack requestPack,@NonNull RestResponsePack responsePack,@NonNull RestUsernotePack usernotePack) {
        log.info("the request pack: {}", JsonUtils.parseJson(requestPack));
        log.info("the response pack: {}", JsonUtils.parseJson(responsePack));
        log.info("the usernote pack: {}", JsonUtils.parseJson(usernotePack));
    }
}
