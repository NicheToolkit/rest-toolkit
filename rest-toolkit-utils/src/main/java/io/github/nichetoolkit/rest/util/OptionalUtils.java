package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.actuator.*;
import io.github.nichetoolkit.rest.error.data.*;
import io.github.nichetoolkit.rest.error.often.FieldNullException;
import io.github.nichetoolkit.rest.error.often.FieldRepeatException;
import io.github.nichetoolkit.rest.error.often.IdentityNullException;
import io.github.nichetoolkit.rest.error.often.NameRepeatException;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

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
     * <code>ofNull</code>
     * <p>the null method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link java.util.Optional} <p>the optional parameter is <code>Optional</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Optional
     * @see java.util.function.Supplier
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("all")
    public static <T> void ofNull(Optional<T> optional, Supplier<RestException> supplier) throws RestException {
        optional.orElseThrow(() -> {
            RestException restException = supplier.get();
            log.error(restException.getMessage());
            return restException;
        });
    }

    /**
     * <code>ofNull</code>
     * <p>the null method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNull(RestOptional<T> optional, SupplierActuator<RestException> supplier) throws RestException {
        optional.nullElseThrow(() -> {
            RestException restException = supplier.actuate();
            log.error(restException.getMessage());
            return restException;
        });
    }

    /**
     * <code>ofEmpty</code>
     * <p>the empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmpty(RestOptional<T> optional, SupplierActuator<RestException> supplier) throws RestException {
        optional.emptyElseThrow(() -> {
            RestException restException = supplier.actuate();
            log.error(restException.getMessage());
            return restException;
        });
    }

    /**
     * <code>ofInvalid</code>
     * <p>the invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalid(RestOptional<T> optional, SupplierActuator<RestException> supplier) throws RestException {
        optional.validElseThrow(() -> {
            RestException restException = supplier.actuate();
            log.error(restException.getMessage());
            return restException;
        });
    }

    /**
     * <code>ofNull</code>
     * <p>the null method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNull(T object, SupplierActuator<RestException> supplier) throws RestException {
        if (GeneralUtils.isNull(object)) {
            RestException restException = supplier.actuate();
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofEmpty</code>
     * <p>the empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmpty(T object, SupplierActuator<RestException> supplier) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            RestException restException = supplier.actuate();
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofInvalid</code>
     * <p>the invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalid(T object, SupplierActuator<RestException> supplier) throws RestException {
        if (GeneralUtils.isInvalid(object)) {
            RestException restException = supplier.actuate();
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofNullError</code>
     * <p>the null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link java.util.Optional} <p>the optional parameter is <code>Optional</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.Optional
     * @see java.util.function.Supplier
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("all")
    public static <T> void ofNullError(Optional<T> optional, Supplier<RestError> supplier) {
        optional.orElseThrow(() -> {
            RestError restError = supplier.get();
            log.error(restError.getMessage());
            return restError;
        });
    }

    /**
     * <code>ofNullError</code>
     * <p>the null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNullError(RestOptional<T> optional, SupplierActuator<RestError> supplier) throws RestException {
        optional.nullElseThrow(() -> {
            RestError restError = supplier.actuate();
            log.error(restError.getMessage());
            return restError;
        });
    }

    /**
     * <code>ofEmptyError</code>
     * <p>the empty error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmptyError(RestOptional<T> optional, SupplierActuator<RestError> supplier) throws RestException {
        optional.emptyElseThrow(() -> {
            RestError restError = supplier.actuate();
            log.error(restError.getMessage());
            return restError;
        });
    }

    /**
     * <code>ofInvalidError</code>
     * <p>the invalid error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalidError(RestOptional<T> optional, SupplierActuator<RestError> supplier) throws RestException {
        optional.validElseThrow(() -> {
            RestError restError = supplier.actuate();
            log.error(restError.getMessage());
            return restError;
        });
    }

    /**
     * <code>ofNullError</code>
     * <p>the null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNullError(T object, SupplierActuator<RestError> supplier) throws RestException {
        if (GeneralUtils.isNull(object)) {
            RestError restError = supplier.actuate();
            log.error(restError.getMessage());
            throw restError;
        }
    }

    /**
     * <code>ofEmptyError</code>
     * <p>the empty error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmptyError(T object, SupplierActuator<RestError> supplier) throws RestException {
        if (GeneralUtils.isNotEmpty(object)) {
            RestError restError = supplier.actuate();
            log.error(restError.getMessage());
            throw restError;
        }
    }

    /**
     * <code>ofInvalidError</code>
     * <p>the invalid error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalidError(T object, SupplierActuator<RestError> supplier) throws RestException {
        if (GeneralUtils.isInvalid(object)) {
            RestError restError = supplier.actuate();
            log.error(restError.getMessage());
            throw restError;
        }
    }

    /**
     * <code>ofNull</code>
     * <p>the null method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link java.util.Optional} <p>the optional parameter is <code>Optional</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>the function parameter is <code>Function</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Optional
     * @see java.lang.String
     * @see java.util.function.Function
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("all")
    public static <T> void ofNull(Optional<T> optional, String message, Function<String, RestException> function) throws RestException {
        optional.orElseThrow(() -> {
            RestException restException = function.apply(message);
            log.error(restException.getMessage());
            return restException;
        });
    }

    /**
     * <code>ofNull</code>
     * <p>the null method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNull(RestOptional<T> optional, String message, FunctionActuator<String, RestException> function) throws RestException {
        optional.nullElseThrow(() -> {
            RestException restException = function.actuate(message);
            log.error(restException.getMessage());
            return restException;
        });
    }

    /**
     * <code>ofEmpty</code>
     * <p>the empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmpty(RestOptional<T> optional, String message, FunctionActuator<String, RestException> function) throws RestException {
        optional.emptyElseThrow(() -> {
            RestException restException = function.actuate(message);
            log.error(restException.getMessage());
            return restException;
        });
    }

    /**
     * <code>ofInvalid</code>
     * <p>the invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalid(RestOptional<T> optional, String message, FunctionActuator<String, RestException> function) throws RestException {
        optional.validElseThrow(() -> {
            RestException restException = function.actuate(message);
            log.error(restException.getMessage());
            return restException;
        });
    }

    /**
     * <code>ofNull</code>
     * <p>the null method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNull(T object, String message, FunctionActuator<String, RestException> function) throws RestException {
        if (GeneralUtils.isNull(object)) {
            RestException restException = function.actuate(message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofEmpty</code>
     * <p>the empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmpty(T object, String message, FunctionActuator<String, RestException> function) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            RestException restException = function.actuate(message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofInvalid</code>
     * <p>the invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalid(T object, String message, FunctionActuator<String, RestException> function) throws RestException {
        if (GeneralUtils.isInvalid(object)) {
            RestException restException = function.actuate(message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofNullError</code>
     * <p>the null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link java.util.Optional} <p>the optional parameter is <code>Optional</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>the function parameter is <code>Function</code> type.</p>
     * @see java.util.Optional
     * @see java.lang.String
     * @see java.util.function.Function
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("all")
    public static <T> void ofNullError(Optional<T> optional, String message, Function<String, RestError> function) {
        optional.orElseThrow(() -> {
            RestError restError = function.apply(message);
            log.error(restError.getMessage());
            return restError;
        });
    }

    /**
     * <code>ofNullError</code>
     * <p>the null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNullError(RestOptional<T> optional, String message, FunctionActuator<String, RestError> function) throws RestException {
        optional.nullElseThrow(() -> {
            RestError restError = function.actuate(message);
            log.error(restError.getMessage());
            return restError;
        });
    }

    /**
     * <code>ofEmptyError</code>
     * <p>the empty error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmptyError(RestOptional<T> optional, String message, FunctionActuator<String, RestError> function) throws RestException {
        optional.emptyElseThrow(() -> {
            RestError restError = function.actuate(message);
            log.error(restError.getMessage());
            return restError;
        });
    }

    /**
     * <code>ofInvalidError</code>
     * <p>the invalid error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalidError(RestOptional<T> optional, String message, FunctionActuator<String, RestError> function) throws RestException {
        optional.validElseThrow(() -> {
            RestError restError = function.actuate(message);
            log.error(restError.getMessage());
            return restError;
        });
    }

    /**
     * <code>ofNullError</code>
     * <p>the null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNullError(T object, String message, FunctionActuator<String, RestError> function) throws RestException {
        if (GeneralUtils.isNull(object)) {
            RestError restError = function.actuate(message);
            log.error(restError.getMessage());
            throw restError;
        }
    }

    /**
     * <code>ofEmptyError</code>
     * <p>the empty error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmptyError(T object, String message, FunctionActuator<String, RestError> function) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            RestError restError = function.actuate(message);
            log.error(restError.getMessage());
            throw restError;
        }
    }

    /**
     * <code>ofInvalidError</code>
     * <p>the invalid error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalidError(T object, String message, FunctionActuator<String, RestError> function) throws RestException {
        if (GeneralUtils.isInvalid(object)) {
            RestError restError = function.actuate(message);
            log.error(restError.getMessage());
            throw restError;
        }
    }


    /**
     * <code>ofNull</code>
     * <p>the null method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional   {@link java.util.Optional} <p>the optional parameter is <code>Optional</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunction {@link java.util.function.BiFunction} <p>the bi function parameter is <code>BiFunction</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Optional
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("all")
    public static <T> void ofNull(Optional<T> optional, String message, String resource, BiFunction<String, String, RestException> biFunction) throws RestException {
        optional.orElseThrow(() -> {
            RestException restException = biFunction.apply(resource, message);
            log.error(restException.getMessage());
            return restException;
        });
    }

    /**
     * <code>ofNull</code>
     * <p>the null method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional   {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunction {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNull(RestOptional<T> optional, String message, String resource, BiFunctionActuator<String, String, RestException> biFunction) throws RestException {
        optional.nullElseThrow(() -> {
            RestException restException = biFunction.actuate(resource, message);
            log.error(restException.getMessage());
            return restException;
        });
    }

    /**
     * <code>ofEmpty</code>
     * <p>the empty method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional   {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunction {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmpty(RestOptional<T> optional, String message, String resource, BiFunctionActuator<String, String, RestException> biFunction) throws RestException {
        optional.emptyElseThrow(() -> {
            RestException restException = biFunction.actuate(resource, message);
            log.error(restException.getMessage());
            return restException;
        });
    }

    /**
     * <code>ofInvalid</code>
     * <p>the invalid method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional   {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunction {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalid(RestOptional<T> optional, String message, String resource, BiFunctionActuator<String, String, RestException> biFunction) throws RestException {
        optional.validElseThrow(() -> {
            RestException restException = biFunction.actuate(resource, message);
            log.error(restException.getMessage());
            return restException;
        });
    }

    /**
     * <code>ofNull</code>
     * <p>the null method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object     T <p>the object parameter is <code>T</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunction {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNull(T object, String message, String resource, BiFunctionActuator<String, String, RestException> biFunction) throws RestException {
        if (GeneralUtils.isNull(object)) {
            RestException restException = biFunction.actuate(resource, message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofEmpty</code>
     * <p>the empty method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object     T <p>the object parameter is <code>T</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunction {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmpty(T object, String message, String resource, BiFunctionActuator<String, String, RestException> biFunction) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            RestException restException = biFunction.actuate(resource, message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofInvalid</code>
     * <p>the invalid method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object     T <p>the object parameter is <code>T</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunction {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalid(T object, String message, String resource, BiFunctionActuator<String, String, RestException> biFunction) throws RestException {
        if (GeneralUtils.isInvalid(object)) {
            RestException restException = biFunction.actuate(resource, message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofNullError</code>
     * <p>the null error method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional   {@link java.util.Optional} <p>the optional parameter is <code>Optional</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunction {@link java.util.function.BiFunction} <p>the bi function parameter is <code>BiFunction</code> type.</p>
     * @see java.util.Optional
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("all")
    public static <T> void ofNullError(Optional<T> optional, String message, String resource, BiFunction<String, String, RestError> biFunction) {
        optional.orElseThrow(() -> {
            RestError restError = biFunction.apply(resource, message);
            log.error(restError.getMessage());
            return restError;
        });
    }

    /**
     * <code>ofNullError</code>
     * <p>the null error method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional   {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunction {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNullError(RestOptional<T> optional, String message, String resource, BiFunctionActuator<String, String, RestError> biFunction) throws RestException {
        optional.nullElseThrow(() -> {
            RestError restError = biFunction.actuate(resource, message);
            log.error(restError.getMessage());
            return restError;
        });
    }

    /**
     * <code>ofEmptyError</code>
     * <p>the empty error method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional   {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunction {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmptyError(RestOptional<T> optional, String message, String resource, BiFunctionActuator<String, String, RestError> biFunction) throws RestException {
        optional.emptyElseThrow(() -> {
            RestError restError = biFunction.actuate(resource, message);
            log.error(restError.getMessage());
            return restError;
        });
    }

    /**
     * <code>ofInvalidError</code>
     * <p>the invalid error method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param optional   {@link io.github.nichetoolkit.rest.RestOptional} <p>the optional parameter is <code>RestOptional</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunction {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalidError(RestOptional<T> optional, String message, String resource, BiFunctionActuator<String, String, RestError> biFunction) throws RestException {
        optional.validElseThrow(() -> {
            RestError restError = biFunction.actuate(resource, message);
            log.error(restError.getMessage());
            return restError;
        });
    }

    /**
     * <code>ofNullError</code>
     * <p>the null error method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object     T <p>the object parameter is <code>T</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunction {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNullError(T object, String message, String resource, BiFunctionActuator<String, String, RestError> biFunction) throws RestException {
        if (GeneralUtils.isNull(object)) {
            RestError restError = biFunction.actuate(resource, message);
            log.error(restError.getMessage());
            throw restError;
        }
    }

    /**
     * <code>ofEmptyError</code>
     * <p>the empty error method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object     T <p>the object parameter is <code>T</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunction {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmptyError(T object, String message, String resource, BiFunctionActuator<String, String, RestError> biFunction) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            RestError restError = biFunction.actuate(resource, message);
            log.error(restError.getMessage());
            throw restError;
        }
    }

    /**
     * <code>ofInvalidError</code>
     * <p>the invalid error method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object     T <p>the object parameter is <code>T</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunction {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalidError(T object, String message, String resource, BiFunctionActuator<String, String, RestError> biFunction) throws RestException {
        if (GeneralUtils.isInvalid(object)) {
            RestError restError = biFunction.actuate(resource, message);
            log.error(restError.getMessage());
            throw restError;
        }
    }

    /**
     * <code>ofTrue</code>
     * <p>the true method.</p>
     * @param present          {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param supplierActuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofTrue(Boolean present, SupplierActuator<RestException> supplierActuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && present) {
            RestException restException = supplierActuator.actuate();
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofTrue</code>
     * <p>the true method.</p>
     * @param present          {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message          {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofTrue(Boolean present, String message, FunctionActuator<String, RestException> functionActuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && present) {
            RestException restException = functionActuator.actuate(message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofTrue</code>
     * <p>the true method.</p>
     * @param present            {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource           {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunctionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofTrue(Boolean present, String message, String resource, BiFunctionActuator<String, String, RestException> biFunctionActuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && present) {
            RestException restException = biFunctionActuator.actuate(resource, message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofTrueError</code>
     * <p>the true error method.</p>
     * @param present          {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param supplierActuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofTrueError(Boolean present, SupplierActuator<RestError> supplierActuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && present) {
            RestError restError = supplierActuator.actuate();
            log.error(restError.getMessage());
            throw restError;
        }
    }

    /**
     * <code>ofTrueError</code>
     * <p>the true error method.</p>
     * @param present          {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message          {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofTrueError(Boolean present, String message, FunctionActuator<String, RestError> functionActuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && present) {
            RestError restError = functionActuator.actuate(message);
            log.error(restError.getMessage());
            throw restError;
        }
    }

    /**
     * <code>ofTrueError</code>
     * <p>the true error method.</p>
     * @param present            {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource           {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunctionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofTrueError(Boolean present, String message, String resource, BiFunctionActuator<String, String, RestError> biFunctionActuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && present) {
            RestError restError = biFunctionActuator.actuate(resource, message);
            log.error(restError.getMessage());
            throw restError;
        }
    }


    /**
     * <code>ofFalse</code>
     * <p>the false method.</p>
     * @param present          {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param supplierActuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFalse(Boolean present, SupplierActuator<RestException> supplierActuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && !present) {
            RestException restException = supplierActuator.actuate();
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofFalse</code>
     * <p>the false method.</p>
     * @param present          {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message          {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFalse(Boolean present, String message, FunctionActuator<String, RestException> functionActuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && !present) {
            RestException restException = functionActuator.actuate(message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofFalse</code>
     * <p>the false method.</p>
     * @param present            {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource           {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunctionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFalse(Boolean present, String message, String resource, BiFunctionActuator<String, String, RestException> biFunctionActuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && !present) {
            RestException restException = biFunctionActuator.actuate(resource, message);
            log.error(restException.getMessage());
            throw restException;
        }
    }

    /**
     * <code>ofFalseError</code>
     * <p>the false error method.</p>
     * @param present          {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param supplierActuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFalseError(Boolean present, SupplierActuator<RestError> supplierActuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && !present) {
            RestError restError = supplierActuator.actuate();
            log.error(restError.getMessage());
            throw restError;
        }
    }

    /**
     * <code>ofFalseError</code>
     * <p>the false error method.</p>
     * @param present          {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message          {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param functionActuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFalseError(Boolean present, String message, FunctionActuator<String, RestError> functionActuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && !present) {
            RestError restError = functionActuator.actuate(message);
            log.error(restError.getMessage());
            throw restError;
        }
    }

    /**
     * <code>ofFalseError</code>
     * <p>the false error method.</p>
     * @param present            {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource           {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param biFunctionActuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the bi function actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFalseError(Boolean present, String message, String resource, BiFunctionActuator<String, String, RestError> biFunctionActuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && !present) {
            RestError restError = biFunctionActuator.actuate(resource, message);
            log.error(restError.getMessage());
            throw restError;
        }
    }

    /**
     * <code>ofFieldNull</code>
     * <p>the field null method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param field T <p>the field parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldNull(T field) throws RestException {
        ofNull(field, FieldNullException::new);
    }

    /**
     * <code>ofFieldNull</code>
     * <p>the field null method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param field   T <p>the field parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldNull(T field, String message) throws RestException {
        ofNull(field, message, FieldNullException::new);
    }

    /**
     * <code>ofFieldNull</code>
     * <p>the field null method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param field    T <p>the field parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldNull(T field, String message, String resource) throws RestException {
        ofNull(field, message, resource, FieldNullException::new);
    }

    /**
     * <code>ofFieldEmpty</code>
     * <p>the field empty method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param field T <p>the field parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldEmpty(T field) throws RestException {
        ofEmpty(field, FieldNullException::new);
    }

    /**
     * <code>ofFieldEmpty</code>
     * <p>the field empty method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param field   T <p>the field parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldEmpty(T field, String message) throws RestException {
        ofEmpty(field, message, FieldNullException::new);
    }

    /**
     * <code>ofFieldEmpty</code>
     * <p>the field empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param field    T <p>the field parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldEmpty(T field, String message, String resource) throws RestException {
        ofEmpty(field, message, resource, FieldNullException::new);
    }

    /**
     * <code>ofFieldInvalid</code>
     * <p>the field invalid method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param field T <p>the field parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldInvalid(T field) throws RestException {
        ofInvalid(field, FieldNullException::new);
    }

    /**
     * <code>ofFieldInvalid</code>
     * <p>the field invalid method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param field   T <p>the field parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldInvalid(T field, String message) throws RestException {
        ofInvalid(field, message, FieldNullException::new);
    }

    /**
     * <code>ofFieldInvalid</code>
     * <p>the field invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param field    T <p>the field parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldInvalid(T field, String message, String resource) throws RestException {
        ofInvalid(field, message, resource, FieldNullException::new);
    }

    /**
     * <code>ofIdNull</code>
     * <p>the id null method.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id  T <p>the id parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdNull(T id) throws RestException {
        ofNull(id, IdentityNullException::new);
    }

    /**
     * <code>ofIdNull</code>
     * <p>the id null method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id      T <p>the id parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdNull(T id, String message) throws RestException {
        ofNull(id, message, IdentityNullException::new);
    }

    /**
     * <code>ofIdNull</code>
     * <p>the id null method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id       T <p>the id parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdNull(T id, String message, String resource) throws RestException {
        ofNull(id, message, resource, IdentityNullException::new);
    }

    /**
     * <code>ofIdEmpty</code>
     * <p>the id empty method.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id  T <p>the id parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdEmpty(T id) throws RestException {
        ofEmpty(id, IdentityNullException::new);
    }

    /**
     * <code>ofIdEmpty</code>
     * <p>the id empty method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id      T <p>the id parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdEmpty(T id, String message) throws RestException {
        ofEmpty(id, message, IdentityNullException::new);
    }

    /**
     * <code>ofIdEmpty</code>
     * <p>the id empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id       T <p>the id parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdEmpty(T id, String message, String resource) throws RestException {
        ofEmpty(id, message, resource, IdentityNullException::new);
    }

    /**
     * <code>ofIdInvalid</code>
     * <p>the id invalid method.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id  T <p>the id parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdInvalid(T id) throws RestException {
        ofInvalid(id, IdentityNullException::new);
    }

    /**
     * <code>ofIdInvalid</code>
     * <p>the id invalid method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id      T <p>the id parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdInvalid(T id, String message) throws RestException {
        ofInvalid(id, message, IdentityNullException::new);
    }

    /**
     * <code>ofIdInvalid</code>
     * <p>the id invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param id       T <p>the id parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdInvalid(T id, String message, String resource) throws RestException {
        ofInvalid(id, message, resource, IdentityNullException::new);
    }

    /**
     * <code>ofCreate</code>
     * <p>the create method.</p>
     * @param result {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofCreate(Integer result) throws RestException {
        ofInvalid(result, DataCreateException::new);
    }

    /**
     * <code>ofCreate</code>
     * <p>the create method.</p>
     * @param result  {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofCreate(Integer result, String message) throws RestException {
        ofInvalid(result, message, DataCreateException::new);
    }

    /**
     * <code>ofCreate</code>
     * <p>the create method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofCreate(Integer result, String message, String resource) throws RestException {
        ofInvalid(result, message, resource, DataCreateException::new);
    }

    /**
     * <code>ofUpdate</code>
     * <p>the update method.</p>
     * @param result {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdate(Integer result) throws RestException {
        ofInvalid(result, DataUpdateException::new);
    }

    /**
     * <code>ofUpdate</code>
     * <p>the update method.</p>
     * @param result  {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdate(Integer result, String message) throws RestException {
        ofInvalid(result, message, DataUpdateException::new);
    }

    /**
     * <code>ofUpdate</code>
     * <p>the update method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdate(Integer result, String message, String resource) throws RestException {
        ofInvalid(result, message, resource, DataUpdateException::new);
    }

    /**
     * <code>ofSave</code>
     * <p>the save method.</p>
     * @param result {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSave(Integer result) throws RestException {
        ofInvalid(result, DataSaveException::new);
    }

    /**
     * <code>ofSave</code>
     * <p>the save method.</p>
     * @param result  {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSave(Integer result, String message) throws RestException {
        ofInvalid(result, message, DataSaveException::new);
    }

    /**
     * <code>ofSave</code>
     * <p>the save method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSave(Integer result, String message, String resource) throws RestException {
        ofInvalid(result, message, resource, DataSaveException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>the insert all method.</p>
     * @param result {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Integer result) throws RestException {
        ofInvalid(result, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>the insert all method.</p>
     * @param result  {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Integer result, String message) throws RestException {
        ofInvalid(result, message, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>the insert all method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Integer result, String message, String resource) throws RestException {
        ofInvalid(result, message, resource, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>the insert all method.</p>
     * @param present {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Boolean present) throws RestException {
        ofFalse(present, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>the insert all method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Boolean present, String message, String resource) throws RestException {
        ofFalse(present, message, resource, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>the insert all method.</p>
     * @param present {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Boolean present, String message) throws RestException {
        ofFalse(present, message, DataBatchInsertException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>the update all method.</p>
     * @param result {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Integer result) throws RestException {
        ofInvalid(result, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>the update all method.</p>
     * @param result   {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Integer result, String message, String resource) throws RestException {
        ofInvalid(result, message, resource, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>the update all method.</p>
     * @param result  {@link java.lang.Integer} <p>the result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Integer result, String message) throws RestException {
        ofInvalid(result, message, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>the update all method.</p>
     * @param present {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Boolean present) throws RestException {
        ofFalse(present, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>the update all method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Boolean present, String message, String resource) throws RestException {
        ofFalse(present, message, resource, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>the update all method.</p>
     * @param present {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Boolean present, String message) throws RestException {
        ofFalse(present, message, DataBatchUpdateException::new);
    }

    /**
     * <code>ofSaveAll</code>
     * <p>the save all method.</p>
     * @param present {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSaveAll(Boolean present) throws RestException {
        ofFalse(present, DataBatchSaveException::new);
    }

    /**
     * <code>ofSaveAll</code>
     * <p>the save all method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSaveAll(Boolean present, String message, String resource) throws RestException {
        ofFalse(present, message, resource, DataBatchSaveException::new);
    }

    /**
     * <code>ofSaveAll</code>
     * <p>the save all method.</p>
     * @param present {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSaveAll(Boolean present, String message) throws RestException {
        ofFalse(present, message, DataBatchSaveException::new);
    }

    /**
     * <code>ofNameRepeat</code>
     * <p>the name repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofNameRepeat(Boolean present) throws RestException {
        ofTrue(present, NameRepeatException::new);
    }

    /**
     * <code>ofNameRepeat</code>
     * <p>the name repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofNameRepeat(Boolean present, String message) throws RestException {
        ofTrue(present, message, NameRepeatException::new);
    }

    /**
     * <code>ofNameRepeat</code>
     * <p>the name repeat method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofNameRepeat(Boolean present, String message, String resource) throws RestException {
        ofTrue(present, message, resource, (resource1, message1) -> new NameRepeatException(resource1, null, message1));
    }

    /**
     * <code>ofFieldRepeat</code>
     * <p>the field repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFieldRepeat(Boolean present) throws RestException {
        ofTrue(present, FieldRepeatException::new);
    }

    /**
     * <code>ofFieldRepeat</code>
     * <p>the field repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFieldRepeat(Boolean present, String field) throws RestException {
        ofTrue(present, field, FieldRepeatException::new);
    }

    /**
     * <code>ofFieldRepeat</code>
     * <p>the field repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFieldRepeat(Boolean present, String field, String message) throws RestException {
        ofTrue(present, message, field, (fld, msg) -> new FieldRepeatException(fld, null, msg));
    }
}
