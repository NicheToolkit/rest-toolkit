package io.github.nichetoolkit.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>RestExceptionAdvice</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestExceptionAdvice {

    default void preExceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response)  {
    }

    default void doRestExceptionHandle(RestException exception, HttpServletRequest request, HttpServletResponse response)  {
    }

    default void doExceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response)  {
    }
}
