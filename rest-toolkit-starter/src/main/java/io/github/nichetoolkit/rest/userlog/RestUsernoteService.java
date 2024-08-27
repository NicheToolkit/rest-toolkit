package io.github.nichetoolkit.rest.userlog;

/**
 * <code>RestUsernoteService</code>
 * <p>The type rest usernote service class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public abstract class RestUsernoteService {

    /**
     * <code>usernote</code>
     * <p>the method.</p>
     * @param request  {@link io.github.nichetoolkit.rest.userlog.RestRequest} <p>the request parameter is <code>RestRequest</code> type.</p>
     * @param response {@link io.github.nichetoolkit.rest.userlog.RestResponse} <p>the response parameter is <code>RestResponse</code> type.</p>
     * @param usernote {@link io.github.nichetoolkit.rest.userlog.RestUsernote} <p>the usernote parameter is <code>RestUsernote</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.RestRequest
     * @see io.github.nichetoolkit.rest.userlog.RestResponse
     */
    abstract public void usernote(RestRequest request, RestResponse response, RestUsernote usernote);

}
