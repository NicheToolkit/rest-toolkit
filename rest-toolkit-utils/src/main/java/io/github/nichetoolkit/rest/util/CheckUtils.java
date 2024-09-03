package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.error.data.*;
import lombok.extern.slf4j.Slf4j;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.actuator.BiFunctionActuator;
import io.github.nichetoolkit.rest.error.often.FieldNullException;
import io.github.nichetoolkit.rest.error.often.IdentityNullException;
import io.github.nichetoolkit.rest.error.often.NameRepeatException;

/**
 * <code>CheckUtils</code>
 * <p>The type check utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class CheckUtils {

    /**
     * <code>checkNullObject</code>
     * <p>the null object method.</p>
     * @param <T>              {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object           T <p>the object parameter is <code>T</code> type.</p>
     * @param logMessage       {@link java.lang.String} <p>the log message parameter is <code>String</code> type.</p>
     * @param exceptionMessage {@link java.lang.String} <p>the exception message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void checkNullObject(T object, String logMessage, String exceptionMessage) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            LoggerUtils.error(logMessage);
            throw new FieldNullException(exceptionMessage);
        }
    }

    /**
     * <code>checkNullObject</code>
     * <p>the null object method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object  T <p>the object parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void checkNullObject(T object, String message) throws RestException {
        checkNullObject(object, message, message);
    }

    /**
     * <code>checkNullObject</code>
     * <p>the null object method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object T <p>the object parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void checkNullObject(T object) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            LoggerUtils.error(RestErrorStatus.FIELD_IS_NULL);
            throw new FieldNullException();
        }
    }

    /**
     * <code>checkNullId</code>
     * <p>the null id method.</p>
     * @param <T>              {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id               T <p>the id parameter is <code>T</code> type.</p>
     * @param logMessage       {@link java.lang.String} <p>the log message parameter is <code>String</code> type.</p>
     * @param exceptionMessage {@link java.lang.String} <p>the exception message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void checkNullId(T id, String logMessage, String exceptionMessage) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            LoggerUtils.error(logMessage);
            throw new IdentityNullException(exceptionMessage);
        }
    }

    /**
     * <code>checkNullId</code>
     * <p>the null id method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id      T <p>the id parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void checkNullId(T id, String message) throws RestException {
        checkNullId(id, message, message);
    }

    /**
     * <code>checkNullId</code>
     * <p>the null id method.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id  T <p>the id parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void checkNullId(T id) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            LoggerUtils.error(RestErrorStatus.IDENTITY_IS_NULL);
            throw new IdentityNullException();
        }
    }

    /**
     * <code>checkTrueComparer</code>
     * <p>the true comparer method.</p>
     * @param comparer         {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @param logMessage       {@link java.lang.String} <p>the log message parameter is <code>String</code> type.</p>
     * @param exceptionMessage {@link java.lang.String} <p>the exception message parameter is <code>String</code> type.</p>
     * @param resource         {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkTrueComparer(Boolean comparer, String logMessage, String exceptionMessage, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        if (null != comparer && comparer) {
            LoggerUtils.error(logMessage);
            throw functionActuator.actuate(resource, exceptionMessage);
        }
    }

    /**
     * <code>checkTrueComparer</code>
     * <p>the true comparer method.</p>
     * @param comparer         {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @param status           {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource         {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkTrueComparer(Boolean comparer, RestStatus status, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        if (null != comparer && comparer) {
            LoggerUtils.error(status.getMessage());
            throw functionActuator.actuate(resource, status.getMessage());
        }
    }


    /**
     * <code>checkTrueComparer</code>
     * <p>the true comparer method.</p>
     * @param comparer         {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @param message          {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource         {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkTrueComparer(Boolean comparer, String message, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkTrueComparer(comparer, message, message, resource, functionActuator);
    }

    /**
     * <code>checkFalseComparer</code>
     * <p>the false comparer method.</p>
     * @param comparer         {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @param logMessage       {@link java.lang.String} <p>the log message parameter is <code>String</code> type.</p>
     * @param exceptionMessage {@link java.lang.String} <p>the exception message parameter is <code>String</code> type.</p>
     * @param resource         {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkFalseComparer(Boolean comparer, String logMessage, String exceptionMessage, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        if (null != comparer && !comparer) {
            LoggerUtils.error(logMessage);
            throw functionActuator.actuate(resource, exceptionMessage);
        }
    }

    /**
     * <code>checkFalseComparer</code>
     * <p>the false comparer method.</p>
     * @param comparer         {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @param message          {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource         {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkFalseComparer(Boolean comparer, String message, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkFalseComparer(comparer, message, message, resource, functionActuator);
    }

    /**
     * <code>checkFalseComparer</code>
     * <p>the false comparer method.</p>
     * @param comparer         {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @param status           {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource         {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkFalseComparer(Boolean comparer, RestStatus status, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkFalseComparer(comparer, status.getMessage(), status.getMessage(), resource, functionActuator);
    }


    /**
     * <code>checkNullResult</code>
     * <p>the null result method.</p>
     * @param result           {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param logMessage       {@link java.lang.String} <p>the log message parameter is <code>String</code> type.</p>
     * @param exceptionMessage {@link java.lang.String} <p>the exception message parameter is <code>String</code> type.</p>
     * @param resource         {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkNullResult(Integer result, String logMessage, String exceptionMessage, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        if (null != result && result == 0) {
            LoggerUtils.error(logMessage);
            throw functionActuator.actuate(resource, exceptionMessage);
        }
    }

    /**
     * <code>checkNullResult</code>
     * <p>the null result method.</p>
     * @param result           {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param status           {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource         {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkNullResult(Integer result, RestStatus status, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkNullResult(result, status.getMessage(), status.getMessage(), resource, functionActuator);
    }

    /**
     * <code>checkNullResult</code>
     * <p>the null result method.</p>
     * @param result           {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message          {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource         {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkNullResult(Integer result, String message, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkNullResult(result, message, message, resource, functionActuator);
    }

    /**
     * <code>checkNonNullResult</code>
     * <p>the non null result method.</p>
     * @param result           {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param logMessage       {@link java.lang.String} <p>the log message parameter is <code>String</code> type.</p>
     * @param exceptionMessage {@link java.lang.String} <p>the exception message parameter is <code>String</code> type.</p>
     * @param resource         {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkNonNullResult(Integer result, String logMessage, String exceptionMessage, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        if (null != result && result > 0) {
            LoggerUtils.error(logMessage);
            throw functionActuator.actuate(resource, exceptionMessage);
        }
    }

    /**
     * <code>checkNonNullResult</code>
     * <p>the non null result method.</p>
     * @param result           {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message          {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource         {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkNonNullResult(Integer result, String message, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkNonNullResult(result, message, message, resource, functionActuator);
    }

    /**
     * <code>checkExistResource</code>
     * <p>the exist resource method.</p>
     * @param exist            {@link java.lang.Boolean} <p>the exist parameter is <code>Boolean</code> type.</p>
     * @param message          {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource         {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkExistResource(Boolean exist, String message, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkTrueComparer(exist, message, message, resource, functionActuator);
    }

    /**
     * <code>checkNonExistResource</code>
     * <p>the non exist resource method.</p>
     * @param exist            {@link java.lang.Boolean} <p>the exist parameter is <code>Boolean</code> type.</p>
     * @param message          {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource         {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkNonExistResource(Boolean exist, String message, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkFalseComparer(exist, message, message, resource, functionActuator);
    }

    /**
     * <code>checkCreate</code>
     * <p>the create method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkCreate(Integer result, String message, String resource) throws RestException {
        checkNullResult(result, message, message, resource, DataCreateException::new);
    }

    /**
     * <code>checkCreate</code>
     * <p>the create method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkCreate(Integer result, String resource) throws RestException {
        checkNullResult(result, RestErrorStatus.DATA_CREATE_FAILED, resource, DataCreateException::new);
    }

    /**
     * <code>checkBatchCreate</code>
     * <p>the batch create method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkBatchCreate(Integer result, String message, String resource) throws RestException {
        checkNullResult(result, message, message, resource, DataBatchInsertException::new);
    }

    /**
     * <code>checkBatchCreate</code>
     * <p>the batch create method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkBatchCreate(Integer result, String resource) throws RestException {
        checkNullResult(result, RestErrorStatus.DATA_INSERT_ALL_FAILED, resource, DataBatchInsertException::new);
    }

    /**
     * <code>checkUpdate</code>
     * <p>the update method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkUpdate(Integer result, String message, String resource) throws RestException {
        checkNullResult(result, message, message, resource, DataUpdateException::new);
    }

    /**
     * <code>checkUpdate</code>
     * <p>the update method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkUpdate(Integer result, String resource) throws RestException {
        checkNullResult(result, RestErrorStatus.DATA_UPDATE_FAILED, resource, DataUpdateException::new);
    }

    /**
     * <code>checkBatchUpdate</code>
     * <p>the batch update method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkBatchUpdate(Integer result, String message, String resource) throws RestException {
        checkNullResult(result, message, message, resource, DataBatchUpdateException::new);
    }

    /**
     * <code>checkBatchUpdate</code>
     * <p>the batch update method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkBatchUpdate(Integer result, String resource) throws RestException {
        checkNullResult(result, RestErrorStatus.DATA_UPDATE_ALL_FAILED, resource, DataBatchUpdateException::new);
    }

    /**
     * <code>checkBatchSave</code>
     * <p>the batch save method.</p>
     * @param result   {@link java.lang.Boolean} <p>the result parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkBatchSave(Boolean result, String message, String resource) throws RestException {
        checkFalseComparer(result, message, message, resource, DataBatchSaveException::new);
    }

    /**
     * <code>checkBatchSave</code>
     * <p>the batch save method.</p>
     * @param result   {@link java.lang.Boolean} <p>the result parameter is <code>Boolean</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkBatchSave(Boolean result, String resource) throws RestException {
        checkFalseComparer(result, RestErrorStatus.DATA_SAVE_ALL_FAILED, resource, DataBatchSaveException::new);
    }

    /**
     * <code>checkNameRepeat</code>
     * <p>the name repeat method.</p>
     * @param exist    {@link java.lang.Boolean} <p>the exist parameter is <code>Boolean</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkNameRepeat(Boolean exist, String resource, String value) throws RestException {
        if (null != exist && exist) {
            LoggerUtils.error(RestErrorStatus.NAME_REPEATED.getMessage());
            throw new NameRepeatException(resource,value);
        }
    }

    /**
     * <code>checkNameRepeat</code>
     * <p>the name repeat method.</p>
     * @param exist {@link java.lang.Boolean} <p>the exist parameter is <code>Boolean</code> type.</p>
     * @param value {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void checkNameRepeat(Boolean exist, String value) throws RestException {
        if (null != exist && exist) {
            LoggerUtils.error(RestErrorStatus.NAME_REPEATED.getMessage());
            throw new NameRepeatException(value);
        }
    }
}
