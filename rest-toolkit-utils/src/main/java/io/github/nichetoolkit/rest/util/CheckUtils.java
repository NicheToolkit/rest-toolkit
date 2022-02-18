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
 * <p>CheckUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class CheckUtils {

    public static <T> void checkNullObject(T object, String logMessage, String exceptionMessage) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            LoggerUtils.error(logMessage);
            throw new FieldNullException(exceptionMessage);
        }
    }

    public static <T> void checkNullObject(T object, String message) throws RestException {
        checkNullObject(object, message, message);
    }

    public static <T> void checkNullObject(T object) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            LoggerUtils.error(RestErrorStatus.FIELD_IS_NULL);
            throw new FieldNullException();
        }
    }

    public static <T> void checkNullId(T id, String logMessage, String exceptionMessage) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            LoggerUtils.error(logMessage);
            throw new IdentityNullException(exceptionMessage);
        }
    }

    public static <T> void checkNullId(T id, String message) throws RestException {
        checkNullId(id, message, message);
    }

    public static <T> void checkNullId(T id) throws RestException {
        if (GeneralUtils.isEmpty(id)) {
            LoggerUtils.error(RestErrorStatus.IDENTITY_IS_NULL);
            throw new IdentityNullException();
        }
    }

    public static void checkTrueComparer(Boolean comparer, String logMessage, String exceptionMessage, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        if (null != comparer && comparer) {
            LoggerUtils.error(logMessage);
            throw functionActuator.actuate(resource, exceptionMessage);
        }
    }

    public static void checkTrueComparer(Boolean comparer, RestStatus status, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        if (null != comparer && comparer) {
            LoggerUtils.error(status.getMessage());
            throw functionActuator.actuate(resource, status.getMessage());
        }
    }


    public static void checkTrueComparer(Boolean comparer, String message, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkTrueComparer(comparer, message, message, resource, functionActuator);
    }

    public static void checkFalseComparer(Boolean comparer, String logMessage, String exceptionMessage, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        if (null != comparer && !comparer) {
            LoggerUtils.error(logMessage);
            throw functionActuator.actuate(resource, exceptionMessage);
        }
    }

    public static void checkFalseComparer(Boolean comparer, String message, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkFalseComparer(comparer, message, message, resource, functionActuator);
    }

    public static void checkFalseComparer(Boolean comparer, RestStatus status, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkFalseComparer(comparer, status.getMessage(), status.getMessage(), resource, functionActuator);
    }


    public static void checkNullResult(Integer result, String logMessage, String exceptionMessage, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        if (null != result && result == 0) {
            LoggerUtils.error(logMessage);
            throw functionActuator.actuate(resource, exceptionMessage);
        }
    }

    public static void checkNullResult(Integer result, RestStatus status, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkNullResult(result, status.getMessage(), status.getMessage(), resource, functionActuator);
    }

    public static void checkNullResult(Integer result, String message, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkNullResult(result, message, message, resource, functionActuator);
    }

    public static void checkNonNullResult(Integer result, String logMessage, String exceptionMessage, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        if (null != result && result > 0) {
            LoggerUtils.error(logMessage);
            throw functionActuator.actuate(resource, exceptionMessage);
        }
    }

    public static void checkNonNullResult(Integer result, String message, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkNonNullResult(result, message, message, resource, functionActuator);
    }

    public static void checkExistResource(Boolean exist, String message, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkTrueComparer(exist, message, message, resource, functionActuator);
    }

    public static void checkNonExistResource(Boolean exist, String message, String resource, BiFunctionActuator<String, String, RestErrorException> functionActuator) throws RestException {
        checkFalseComparer(exist, message, message, resource, functionActuator);
    }

    public static void checkCreate(Integer result, String message, String resource) throws RestException {
        checkNullResult(result, message, message, resource, DataCreateException::new);
    }

    public static void checkCreate(Integer result, String resource) throws RestException {
        checkNullResult(result, RestErrorStatus.DATA_CREATE_FAILED, resource, DataCreateException::new);
    }

    public static void checkBatchCreate(Integer result, String message, String resource) throws RestException {
        checkNullResult(result, message, message, resource, DataBatchInsertException::new);
    }

    public static void checkBatchCreate(Integer result, String resource) throws RestException {
        checkNullResult(result, RestErrorStatus.DATA_INSERT_ALL_FAILED, resource, DataBatchInsertException::new);
    }

    public static void checkUpdate(Integer result, String message, String resource) throws RestException {
        checkNullResult(result, message, message, resource, DataUpdateException::new);
    }

    public static void checkUpdate(Integer result, String resource) throws RestException {
        checkNullResult(result, RestErrorStatus.DATA_UPDATE_FAILED, resource, DataUpdateException::new);
    }

    public static void checkBatchUpdate(Integer result, String message, String resource) throws RestException {
        checkNullResult(result, message, message, resource, DataBatchUpdateException::new);
    }

    public static void checkBatchUpdate(Integer result, String resource) throws RestException {
        checkNullResult(result, RestErrorStatus.DATA_UPDATE_ALL_FAILED, resource, DataBatchUpdateException::new);
    }

    public static void checkBatchSave(Boolean result, String message, String resource) throws RestException {
        checkFalseComparer(result, message, message, resource, DataBatchSaveException::new);
    }

    public static void checkBatchSave(Boolean result, String resource) throws RestException {
        checkFalseComparer(result, RestErrorStatus.DATA_SAVE_ALL_FAILED, resource, DataBatchSaveException::new);
    }

    public static void checkNameRepeat(Boolean exist, String resource, String value) throws RestException {
        if (null != exist && exist) {
            LoggerUtils.error(RestErrorStatus.NAME_REPEATED.getMessage());
            throw new NameRepeatException(resource,value);
        }
    }

    public static void checkNameRepeat(Boolean exist, String value) throws RestException {
        if (null != exist && exist) {
            LoggerUtils.error(RestErrorStatus.NAME_REPEATED.getMessage());
            throw new NameRepeatException(value);
        }
    }
}
