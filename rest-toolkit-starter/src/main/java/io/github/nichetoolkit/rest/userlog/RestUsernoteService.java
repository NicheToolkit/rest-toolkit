package io.github.nichetoolkit.rest.userlog;

/**
 * <p>RestUsernoteService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RestUsernoteService {

    /**
     * 自定义日志处理方法 不能抛出异常，
     * 抛出异常又可能会导致正常 处理逻辑回滚
     * @param request 请求数据
     * @param response 返回响应数据
     * @param usernote 用户标注数据
     */
    abstract public void usernote(RestRequest request, RestResponse response, RestUsernote usernote);

}
