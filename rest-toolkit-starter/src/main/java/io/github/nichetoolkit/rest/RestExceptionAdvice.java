package io.github.nichetoolkit.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface RestExceptionAdvice {

    default void preExceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response)  {
    }

    default void doRestExceptionHandle(RestException exception, HttpServletRequest request, HttpServletResponse response)  {
    }

    default void doExceptionHandle(Exception exception, HttpServletRequest request, HttpServletResponse response)  {
    }
}
