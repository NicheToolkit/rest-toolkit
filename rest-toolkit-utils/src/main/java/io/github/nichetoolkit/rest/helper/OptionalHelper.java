package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.actuator.SupplierActuator;
import io.github.nichetoolkit.rest.error.data.*;
import io.github.nichetoolkit.rest.error.often.FieldRepeatException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.BiFunctionActuator;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;
import io.github.nichetoolkit.rest.error.often.FieldNullException;
import io.github.nichetoolkit.rest.error.often.IdentityNullException;
import io.github.nichetoolkit.rest.error.often.NameRepeatException;

/**
 * <p>OptionalHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class OptionalHelper {

    public static <T> void nullable(T object, SupplierActuator<RestException> supplierActuator) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            RestException restException = supplierActuator.get();
            log.error(restException.getMessage());
            throw restException;
        }
    }

    public static <T> void nullable(T object, String message, FunctionActuator<String,RestException> functionActuator) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            RestException restException = functionActuator.actuate(message);
            log.error(restException.getMessage());
            throw restException;
        }
    }


    public static <T> void nullable(T object, String message,String field, BiFunctionActuator<String,String,RestException> biFunctionActuator) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            RestException restException = biFunctionActuator.actuate(field,message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    public static void trueable(Boolean object, SupplierActuator<RestException> supplierActuator) throws RestException {
        if (GeneralUtils.isNotEmpty(object) && object) {
            RestException restException = supplierActuator.get();
            log.error(restException.getMessage());
            throw restException;
        }
    }

    public static void trueable(Boolean object, String message, FunctionActuator<String,RestException> functionActuator) throws RestException {
        if (GeneralUtils.isNotEmpty(object) && object) {
            RestException restException = functionActuator.actuate(message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    public static void trueable(Boolean object, String message, String field, BiFunctionActuator<String,String,RestException> biFunctionActuator) throws RestException {
        if (GeneralUtils.isNotEmpty(object) && object) {
            RestException restException = biFunctionActuator.actuate(field,message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    public static void falseable(Boolean object, SupplierActuator<RestException> supplierActuator) throws RestException {
        if (GeneralUtils.isNotEmpty(object) && !object) {
            RestException restException = supplierActuator.get();
            log.error(restException.getMessage());
            throw restException;
        }
    }

    public static void falseable(Boolean object, String message, FunctionActuator<String,RestException> functionActuator) throws RestException {
        if (GeneralUtils.isNotEmpty(object) && !object) {
            RestException restException = functionActuator.actuate(message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    public static void falseable(Boolean object, String message,String field, BiFunctionActuator<String,String,RestException> biFunctionActuator) throws RestException {
        if (GeneralUtils.isNotEmpty(object) && !object) {
            RestException restException = biFunctionActuator.actuate(field,message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    public static void nullResult(Integer result, SupplierActuator<RestException> supplierActuator) throws RestException {
        if (null != result && result == 0) {
            RestException restException = supplierActuator.get();
            log.error(restException.getMessage());
            throw restException;
        }
    }

    public static void nullResult(Integer result, String message, FunctionActuator<String,RestException> functionActuator) throws RestException {
        if (null != result && result == 0) {
            RestException restException = functionActuator.actuate(message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    public static void nullResult(Integer result, String message, String resource, BiFunctionActuator<String, String, RestException> functionActuator) throws RestException {
        if (null != result && result == 0) {
            RestException restException = functionActuator.actuate(resource, message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    public static <T> void fieldable(T object) throws RestException {
        nullable(object,FieldNullException::new);
    }

    public static <T> void fieldable(T object, String message) throws RestException {
        nullable(object,message,FieldNullException::new);
    }

    public static <T> void fieldable(T object, String message, String field) throws RestException {
        nullable(object,message,field,FieldNullException::new);
    }

    public static <T> void idable(T id) throws RestException {
        nullable(id,IdentityNullException::new);
    }

    public static <T> void idable(T id, String message) throws RestException {
        nullable(id,message,IdentityNullException::new);
    }


    public static <T> void idable(T id, String message, String field) throws RestException {
        nullable(id,message,field,IdentityNullException::new);
    }

    public static void create(Integer result) throws RestException {
        nullResult(result, DataCreateException::new);
    }

    public static void create(Integer result, String message) throws RestException {
        nullResult(result, message, DataCreateException::new);
    }

    public static void create(Integer result, String message, String resource) throws RestException {
        nullResult(result, message, resource, DataCreateException::new);
    }

    public static void save(Integer result) throws RestException {
        nullResult(result, DataSaveException::new);
    }

    public static void save(Integer result, String message) throws RestException {
        nullResult(result, message, DataSaveException::new);
    }

    public static void save(Integer result, String message, String resource) throws RestException {
        nullResult(result, message, resource, DataSaveException::new);
    }

    public static void update(Integer result) throws RestException {
        nullResult(result, DataUpdateException::new);
    }

    public static void update(Integer result, String message) throws RestException {
        nullResult(result, message, DataUpdateException::new);
    }

    public static void update(Integer result, String message, String resource) throws RestException {
        nullResult(result, message, resource, DataUpdateException::new);
    }

    public static void insertAll(Integer result) throws RestException {
        nullResult(result, DataBatchInsertException::new);
    }

    public static void insertAll(Integer result, String message) throws RestException {
        nullResult(result, message, DataBatchInsertException::new);
    }

    public static void insertAll(Integer result, String message, String resource) throws RestException {
        nullResult(result, message, resource, DataBatchInsertException::new);
    }

    public static void insertAll(Boolean comparer) throws RestException {
        falseable(comparer, DataBatchInsertException::new);
    }

    public static void insertAll(Boolean comparer, String message, String resource) throws RestException {
        falseable(comparer, message, resource, DataBatchInsertException::new);
    }

    public static void insertAll(Boolean comparer, String message) throws RestException {
        falseable(comparer, message, DataBatchInsertException::new);
    }

    public static void updateAll(Integer result) throws RestException {
        nullResult(result, DataBatchUpdateException::new);
    }

    public static void updateAll(Integer result, String message, String resource) throws RestException {
        nullResult(result, message, resource, DataBatchUpdateException::new);
    }

    public static void updateAll(Integer result, String message) throws RestException {
        nullResult(result, message, DataBatchUpdateException::new);
    }

    public static void updateAll(Boolean comparer) throws RestException {
        falseable(comparer, DataBatchUpdateException::new);
    }

    public static void updateAll(Boolean comparer, String message, String resource) throws RestException {
        falseable(comparer, message, resource, DataBatchUpdateException::new);
    }

    public static void updateAll(Boolean comparer, String message) throws RestException {
        falseable(comparer, message, DataBatchUpdateException::new);
    }

    public static void saveAll(Boolean comparer) throws RestException {
        falseable(comparer, DataBatchSaveException::new);
    }

    public static void saveAll(Boolean comparer, String message, String resource) throws RestException {
        falseable(comparer, message, resource, DataBatchSaveException::new);
    }

    public static void saveAll(Boolean comparer, String message) throws RestException {
        falseable(comparer, message, DataBatchSaveException::new);
    }

    public static void nameRepeat(Boolean exist) throws RestException {
        trueable(exist, NameRepeatException::new);
    }

    public static void nameRepeat(Boolean exist, String message) throws RestException {
        trueable(exist,message,NameRepeatException::new);
    }

    public static void nameRepeat(Boolean exist, String message, String resource) throws RestException {
        trueable(exist,message,resource,(resource1,message1) -> new NameRepeatException(resource1, null,message1));
    }

    public static void fieldRepeat(Boolean exist) throws RestException {
        trueable(exist, FieldRepeatException::new);
    }

    public static void fieldRepeat(Boolean exist, String field) throws RestException {
        trueable(exist,field,FieldRepeatException::new);
    }

    public static void fieldRepeat(Boolean exist, String field, String message) throws RestException {
        trueable(exist,message,field,(field1,message1) -> new FieldRepeatException(field1,(Object)null,message1));
    }

}
