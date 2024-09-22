package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.*;
import io.github.nichetoolkit.rest.error.data.*;
import io.github.nichetoolkit.rest.error.often.FieldNullException;
import io.github.nichetoolkit.rest.error.often.FieldRepeatException;
import io.github.nichetoolkit.rest.error.often.IdentityNullException;
import io.github.nichetoolkit.rest.error.often.NameRepeatException;
import lombok.extern.slf4j.Slf4j;

/**
 * <code>OptionalUtils</code>
 * <p>The type optional utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public final class OptionalUtils {

    /**
     * <code>nullable</code>
     * <p>the method.</p>
     * @param <T>              {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object           T <p>the object parameter is <code>T</code> type.</p>
     * @param supplierActuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void nullable(T object, SupplierActuator<RestException> supplierActuator) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            RestException restException = supplierActuator.actuate();
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>nullable</code>
     * <p>the method.</p>
     * @param <T>              {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object           T <p>the object parameter is <code>T</code> type.</p>
     * @param message          {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void nullable(T object, String message, FunctionActuator<String, RestException> functionActuator) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            RestException restException = functionActuator.actuate(message);
            log.error(restException.getMessage());
            throw restException;
        }
    }


    /**
     * <code>nullable</code>
     * <p>the method.</p>
     * @param <T>                {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object             T <p>the object parameter is <code>T</code> type.</p>
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param field              {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param biFunctionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void nullable(T object, String message, String field, BiFunctionActuator<String, String, RestException> biFunctionActuator) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            RestException restException = biFunctionActuator.actuate(field, message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>trueable</code>
     * <p>the method.</p>
     * @param object           {@link java.lang.Boolean} <p>the object parameter is <code>Boolean</code> type.</p>
     * @param supplierActuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void trueable(Boolean object, SupplierActuator<RestException> supplierActuator) throws RestException {
        if (GeneralUtils.isNotEmpty(object) && object) {
            RestException restException = supplierActuator.actuate();
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>trueable</code>
     * <p>the method.</p>
     * @param object           {@link java.lang.Boolean} <p>the object parameter is <code>Boolean</code> type.</p>
     * @param message          {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void trueable(Boolean object, String message, FunctionActuator<String, RestException> functionActuator) throws RestException {
        if (GeneralUtils.isNotEmpty(object) && object) {
            RestException restException = functionActuator.actuate(message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>trueable</code>
     * <p>the method.</p>
     * @param object             {@link java.lang.Boolean} <p>the object parameter is <code>Boolean</code> type.</p>
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param field              {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param biFunctionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void trueable(Boolean object, String message, String field, BiFunctionActuator<String, String, RestException> biFunctionActuator) throws RestException {
        if (GeneralUtils.isNotEmpty(object) && object) {
            RestException restException = biFunctionActuator.actuate(field, message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>falseable</code>
     * <p>the method.</p>
     * @param object           {@link java.lang.Boolean} <p>the object parameter is <code>Boolean</code> type.</p>
     * @param supplierActuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void falseable(Boolean object, SupplierActuator<RestException> supplierActuator) throws RestException {
        if (GeneralUtils.isNotEmpty(object) && !object) {
            RestException restException = supplierActuator.actuate();
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>falseable</code>
     * <p>the method.</p>
     * @param object           {@link java.lang.Boolean} <p>the object parameter is <code>Boolean</code> type.</p>
     * @param message          {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void falseable(Boolean object, String message, FunctionActuator<String, RestException> functionActuator) throws RestException {
        if (GeneralUtils.isNotEmpty(object) && !object) {
            RestException restException = functionActuator.actuate(message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>falseable</code>
     * <p>the method.</p>
     * @param object             {@link java.lang.Boolean} <p>the object parameter is <code>Boolean</code> type.</p>
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param field              {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param biFunctionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void falseable(Boolean object, String message, String field, BiFunctionActuator<String, String, RestException> biFunctionActuator) throws RestException {
        if (GeneralUtils.isNotEmpty(object) && !object) {
            RestException restException = biFunctionActuator.actuate(field, message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>nullResult</code>
     * <p>the result method.</p>
     * @param result           {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param supplierActuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void nullResult(Integer result, SupplierActuator<RestException> supplierActuator) throws RestException {
        if (null != result && result == 0) {
            RestException restException = supplierActuator.actuate();
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>nullResult</code>
     * <p>the result method.</p>
     * @param result           {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message          {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void nullResult(Integer result, String message, FunctionActuator<String, RestException> functionActuator) throws RestException {
        if (null != result && result == 0) {
            RestException restException = functionActuator.actuate(message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>nullResult</code>
     * <p>the result method.</p>
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
    public static void nullResult(Integer result, String message, String resource, BiFunctionActuator<String, String, RestException> functionActuator) throws RestException {
        if (null != result && result == 0) {
            RestException restException = functionActuator.actuate(resource, message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>fieldable</code>
     * <p>the method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object T <p>the object parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void fieldable(T object) throws RestException {
        nullable(object, FieldNullException::new);
    }

    /**
     * <code>fieldable</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object  T <p>the object parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void fieldable(T object, String message) throws RestException {
        nullable(object, message, FieldNullException::new);
    }

    /**
     * <code>fieldable</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object  T <p>the object parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void fieldable(T object, String message, String field) throws RestException {
        nullable(object, message, field, FieldNullException::new);
    }

    /**
     * <code>idable</code>
     * <p>the method.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id  T <p>the id parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void idable(T id) throws RestException {
        nullable(id, IdentityNullException::new);
    }

    /**
     * <code>idable</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id      T <p>the id parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void idable(T id, String message) throws RestException {
        nullable(id, message, IdentityNullException::new);
    }


    /**
     * <code>idable</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id      T <p>the id parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void idable(T id, String message, String field) throws RestException {
        nullable(id, message, field, IdentityNullException::new);
    }

    /**
     * <code>create</code>
     * <p>the method.</p>
     * @param result {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void create(Integer result) throws RestException {
        nullResult(result, DataCreateException::new);
    }

    /**
     * <code>create</code>
     * <p>the method.</p>
     * @param result  {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void create(Integer result, String message) throws RestException {
        nullResult(result, message, DataCreateException::new);
    }

    /**
     * <code>create</code>
     * <p>the method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void create(Integer result, String message, String resource) throws RestException {
        nullResult(result, message, resource, DataCreateException::new);
    }

    /**
     * <code>save</code>
     * <p>the method.</p>
     * @param result {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void save(Integer result) throws RestException {
        nullResult(result, DataSaveException::new);
    }

    /**
     * <code>save</code>
     * <p>the method.</p>
     * @param result  {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void save(Integer result, String message) throws RestException {
        nullResult(result, message, DataSaveException::new);
    }

    /**
     * <code>save</code>
     * <p>the method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void save(Integer result, String message, String resource) throws RestException {
        nullResult(result, message, resource, DataSaveException::new);
    }

    /**
     * <code>update</code>
     * <p>the method.</p>
     * @param result {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void update(Integer result) throws RestException {
        nullResult(result, DataUpdateException::new);
    }

    /**
     * <code>update</code>
     * <p>the method.</p>
     * @param result  {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void update(Integer result, String message) throws RestException {
        nullResult(result, message, DataUpdateException::new);
    }

    /**
     * <code>update</code>
     * <p>the method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void update(Integer result, String message, String resource) throws RestException {
        nullResult(result, message, resource, DataUpdateException::new);
    }

    /**
     * <code>insertAll</code>
     * <p>the all method.</p>
     * @param result {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void insertAll(Integer result) throws RestException {
        nullResult(result, DataBatchInsertException::new);
    }

    /**
     * <code>insertAll</code>
     * <p>the all method.</p>
     * @param result  {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void insertAll(Integer result, String message) throws RestException {
        nullResult(result, message, DataBatchInsertException::new);
    }

    /**
     * <code>insertAll</code>
     * <p>the all method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void insertAll(Integer result, String message, String resource) throws RestException {
        nullResult(result, message, resource, DataBatchInsertException::new);
    }

    /**
     * <code>insertAll</code>
     * <p>the all method.</p>
     * @param comparer {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void insertAll(Boolean comparer) throws RestException {
        falseable(comparer, DataBatchInsertException::new);
    }

    /**
     * <code>insertAll</code>
     * <p>the all method.</p>
     * @param comparer {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void insertAll(Boolean comparer, String message, String resource) throws RestException {
        falseable(comparer, message, resource, DataBatchInsertException::new);
    }

    /**
     * <code>insertAll</code>
     * <p>the all method.</p>
     * @param comparer {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void insertAll(Boolean comparer, String message) throws RestException {
        falseable(comparer, message, DataBatchInsertException::new);
    }

    /**
     * <code>updateAll</code>
     * <p>the all method.</p>
     * @param result {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void updateAll(Integer result) throws RestException {
        nullResult(result, DataBatchUpdateException::new);
    }

    /**
     * <code>updateAll</code>
     * <p>the all method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void updateAll(Integer result, String message, String resource) throws RestException {
        nullResult(result, message, resource, DataBatchUpdateException::new);
    }

    /**
     * <code>updateAll</code>
     * <p>the all method.</p>
     * @param result  {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void updateAll(Integer result, String message) throws RestException {
        nullResult(result, message, DataBatchUpdateException::new);
    }

    /**
     * <code>updateAll</code>
     * <p>the all method.</p>
     * @param comparer {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void updateAll(Boolean comparer) throws RestException {
        falseable(comparer, DataBatchUpdateException::new);
    }

    /**
     * <code>updateAll</code>
     * <p>the all method.</p>
     * @param comparer {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void updateAll(Boolean comparer, String message, String resource) throws RestException {
        falseable(comparer, message, resource, DataBatchUpdateException::new);
    }

    /**
     * <code>updateAll</code>
     * <p>the all method.</p>
     * @param comparer {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void updateAll(Boolean comparer, String message) throws RestException {
        falseable(comparer, message, DataBatchUpdateException::new);
    }

    /**
     * <code>saveAll</code>
     * <p>the all method.</p>
     * @param comparer {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void saveAll(Boolean comparer) throws RestException {
        falseable(comparer, DataBatchSaveException::new);
    }

    /**
     * <code>saveAll</code>
     * <p>the all method.</p>
     * @param comparer {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void saveAll(Boolean comparer, String message, String resource) throws RestException {
        falseable(comparer, message, resource, DataBatchSaveException::new);
    }

    /**
     * <code>saveAll</code>
     * <p>the all method.</p>
     * @param comparer {@link java.lang.Boolean} <p>the comparer parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void saveAll(Boolean comparer, String message) throws RestException {
        falseable(comparer, message, DataBatchSaveException::new);
    }

    /**
     * <code>nameRepeat</code>
     * <p>the repeat method.</p>
     * @param exist {@link java.lang.Boolean} <p>the exist parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void nameRepeat(Boolean exist) throws RestException {
        trueable(exist, NameRepeatException::new);
    }

    /**
     * <code>nameRepeat</code>
     * <p>the repeat method.</p>
     * @param exist   {@link java.lang.Boolean} <p>the exist parameter is <code>Boolean</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void nameRepeat(Boolean exist, String message) throws RestException {
        trueable(exist, message, NameRepeatException::new);
    }

    /**
     * <code>nameRepeat</code>
     * <p>the repeat method.</p>
     * @param exist    {@link java.lang.Boolean} <p>the exist parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void nameRepeat(Boolean exist, String message, String resource) throws RestException {
        trueable(exist, message, resource, (resource1, message1) -> new NameRepeatException(resource1, null, message1));
    }

    /**
     * <code>fieldRepeat</code>
     * <p>the repeat method.</p>
     * @param exist {@link java.lang.Boolean} <p>the exist parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void fieldRepeat(Boolean exist) throws RestException {
        trueable(exist, FieldRepeatException::new);
    }

    /**
     * <code>fieldRepeat</code>
     * <p>the repeat method.</p>
     * @param exist {@link java.lang.Boolean} <p>the exist parameter is <code>Boolean</code> type.</p>
     * @param field {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void fieldRepeat(Boolean exist, String field) throws RestException {
        trueable(exist, field, FieldRepeatException::new);
    }

    /**
     * <code>fieldRepeat</code>
     * <p>the repeat method.</p>
     * @param exist   {@link java.lang.Boolean} <p>the exist parameter is <code>Boolean</code> type.</p>
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void fieldRepeat(Boolean exist, String field, String message) throws RestException {
        trueable(exist, message, field, (field1, message1) -> new FieldRepeatException(field1, null, message1));
    }
}
