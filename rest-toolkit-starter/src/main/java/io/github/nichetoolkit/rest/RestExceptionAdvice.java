package io.github.nichetoolkit.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <code>RestExceptionAdvice</code>
 * <p>The rest exception advice interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk17
 */
public interface RestExceptionAdvice {

    /**
     * <code>preExceptionHandle</code>
     * <p>The pre exception handle method.</p>
     * @param exception {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @param request   {@link jakarta.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.Exception
     * @see jakarta.servlet.http.HttpServletRequest
     * @see jakarta.servlet.http.HttpServletResponse
     */
    default void preExceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response)  {
    }

    /**
     * <code>doRestExceptionHandle</code>
     * <p>The do rest exception handle method.</p>
     * @param exception {@link io.github.nichetoolkit.rest.RestException} <p>The exception parameter is <code>RestException</code> type.</p>
     * @param request   {@link jakarta.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see jakarta.servlet.http.HttpServletRequest
     * @see jakarta.servlet.http.HttpServletResponse
     */
    default void doRestExceptionHandle(RestException exception, HttpServletRequest request, HttpServletResponse response)  {
    }

    /**
     * <code>doExceptionHandle</code>
     * <p>The do exception handle method.</p>
     * @param exception {@link java.lang.Exception} <p>The exception parameter is <code>Exception</code> type.</p>
     * @param request   {@link jakarta.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response  {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.Exception
     * @see jakarta.servlet.http.HttpServletRequest
     * @see jakarta.servlet.http.HttpServletResponse
     */
    default void doExceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response)  {
    }
}
