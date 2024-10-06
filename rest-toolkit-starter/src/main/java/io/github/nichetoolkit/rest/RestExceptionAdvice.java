package io.github.nichetoolkit.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <code>RestExceptionAdvice</code>
 * <p>The type rest exception advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestExceptionAdvice {

    /**
     * <code>preExceptionHandle</code>
     * <p>The exception handle method.</p>
     * @param exception {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @param request   {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.Exception
     * @see javax.servlet.http.HttpServletRequest
     * @see javax.servlet.http.HttpServletResponse
     */
    default void preExceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response)  {
    }

    /**
     * <code>doRestExceptionHandle</code>
     * <p>The rest exception handle method.</p>
     * @param exception {@link io.github.nichetoolkit.rest.RestException} <p>The exception parameter is <code>RestException</code> type.</p>
     * @param request   {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see javax.servlet.http.HttpServletRequest
     * @see javax.servlet.http.HttpServletResponse
     */
    default void doRestExceptionHandle(RestException exception, HttpServletRequest request, HttpServletResponse response)  {
    }

    /**
     * <code>doExceptionHandle</code>
     * <p>The exception handle method.</p>
     * @param exception {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @param request   {@link javax.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.Exception
     * @see javax.servlet.http.HttpServletRequest
     * @see javax.servlet.http.HttpServletResponse
     */
    default void doExceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response)  {
    }
}
