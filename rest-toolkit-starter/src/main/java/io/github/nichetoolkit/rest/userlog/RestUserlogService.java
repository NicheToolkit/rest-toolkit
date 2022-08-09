package io.github.nichetoolkit.rest.userlog;

import io.github.nichetoolkit.rest.RestRequest;
import io.github.nichetoolkit.rest.RestResponse;

/**
 * <p>RestNoteService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RestUserlogService {

    abstract public void handler(RestRequest request, RestResponse restResponse, RestUsernote restLog);

}
