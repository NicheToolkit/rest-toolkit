package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.actuator.*;
import io.github.nichetoolkit.rest.error.data.*;
import io.github.nichetoolkit.rest.error.often.FieldNullException;
import io.github.nichetoolkit.rest.error.often.FieldRepeatException;
import io.github.nichetoolkit.rest.error.often.IdentityNullException;
import io.github.nichetoolkit.rest.error.often.NameRepeatException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <code>OptionalUtils</code>
 * <p>The optional utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk17
 */
@Slf4j
public final class OptionalUtils {

    /**
     * <code>xOfNull</code>
     * <p>The x of null method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @return X <p>The x of null return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfNull(@Nullable T object, @NonNull Supplier<X> supplier) {
        Objects.requireNonNull(supplier);
        X cause = null;
        if (GeneralUtils.isNull(object)) {
            cause = supplier.get();
        } else if (object instanceof Optional<?> optional) {
            if (optional.isPresent()) {
                cause = supplier.get();
            }
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isNull()) {
                cause = supplier.get();
            }
        }
        return cause;
    }

    /**
     * <code>xOfNullActuator</code>
     * <p>The x of null actuator method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @return X <p>The x of null actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, X extends Throwable> X xOfNullActuator(@Nullable T object, @NonNull SupplierActuator<X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isNull(object)) {
            cause = actuator.actuate();
        } else if (object instanceof Optional<?> optional) {
            if (optional.isEmpty()) {
                cause = actuator.actuate();
            }
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isNull()) {
                cause = actuator.actuate();
            }
        }
        return cause;
    }


    /**
     * <code>xOfNull</code>
     * <p>The x of null method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @return X <p>The x of null return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.Function
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfNull(@Nullable T object, String message, @NonNull Function<String, X> function) {
        Objects.requireNonNull(function);
        X cause = null;
        if (GeneralUtils.isNull(object)) {
            cause = function.apply(message);
        } else if (object instanceof Optional<?> optional) {
            if (optional.isEmpty()) {
                cause = function.apply(message);
            }
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isNull()) {
                cause = function.apply(message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfNull</code>
     * <p>The x of null method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @return X <p>The x of null return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfNull(@Nullable T object, RestStatus restStatus, @NonNull Function<RestStatus, X> function) {
        Objects.requireNonNull(function);
        X cause = null;
        if (GeneralUtils.isNull(object)) {
            cause = function.apply(restStatus);
        } else if (object instanceof Optional<?> optional) {
            if (optional.isEmpty()) {
                cause = function.apply(restStatus);
            }
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isNull()) {
                cause = function.apply(restStatus);
            }
        }
        return cause;
    }


    /**
     * <code>xOfNullActuator</code>
     * <p>The x of null actuator method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @return X <p>The x of null actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, X extends Throwable> X xOfNullActuator(@Nullable T object, String message, @NonNull FunctionActuator<String, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isNull(object)) {
            cause = actuator.actuate(message);
        } else if (object instanceof Optional<?> optional) {
            if (optional.isEmpty()) {
                cause = actuator.actuate(message);
            }
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isNull()) {
                cause = actuator.actuate(message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfNullActuator</code>
     * <p>The x of null actuator method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @return X <p>The x of null actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, X extends Throwable> X xOfNullActuator(@Nullable T object, RestStatus restStatus, @NonNull FunctionActuator<RestStatus, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isNull(object)) {
            cause = actuator.actuate(restStatus);
        } else if (object instanceof Optional<?> optional) {
            if (optional.isEmpty()) {
                cause = actuator.actuate(restStatus);
            }
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isNull()) {
                cause = actuator.actuate(restStatus);
            }
        }
        return cause;
    }

    /**
     * <code>xOfNull</code>
     * <p>The x of null method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @return X <p>The x of null return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfNull(@Nullable T object, String message, String resource, @NonNull BiFunction<String, String, X> function) {
        Objects.requireNonNull(function);
        X cause = null;
        if (GeneralUtils.isNull(object)) {
            cause = function.apply(resource, message);
        } else if (object instanceof Optional<?> optional) {
            if (optional.isEmpty()) {
                cause = function.apply(resource, message);
            }
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isNull()) {
                cause = function.apply(resource, message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfNull</code>
     * <p>The x of null method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @return X <p>The x of null return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfNull(@Nullable T object, RestStatus restStatus, String resource, @NonNull BiFunction<String, String, X> function) {
        return xOfNull(object, restStatus.getMessage(), resource, function);
    }

    /**
     * <code>xOfNullActuator</code>
     * <p>The x of null actuator method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @return X <p>The x of null actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, X extends Throwable> X xOfNullActuator(@Nullable T object, String message, String resource, @NonNull BiFunctionActuator<String, String, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isNull(object)) {
            cause = actuator.actuate(resource, message);
        } else if (object instanceof Optional<?> optional) {
            if (optional.isEmpty()) {
                cause = actuator.actuate(resource, message);
            }
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isNull()) {
                cause = actuator.actuate(resource, message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfNullActuator</code>
     * <p>The x of null actuator method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @return X <p>The x of null actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, X extends Throwable> X xOfNullActuator(@Nullable T object, RestStatus restStatus, String resource, @NonNull BiFunctionActuator<String, String, X> actuator) throws RestException {
        return xOfNullActuator(object, restStatus.getMessage(), resource, actuator);
    }

    /**
     * <code>xOfEmpty</code>
     * <p>The x of empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @return X <p>The x of empty return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfEmpty(@Nullable T object, @NonNull Supplier<X> supplier) {
        Objects.requireNonNull(supplier);
        X cause = null;
        if (GeneralUtils.isEmpty(object)) {
            cause = supplier.get();
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isEmpty()) {
                cause = supplier.get();
            }
        }
        return cause;
    }

    /**
     * <code>xOfEmptyActuator</code>
     * <p>The x of empty actuator method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @return X <p>The x of empty actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, X extends Throwable> X xOfEmptyActuator(@Nullable T object, @NonNull SupplierActuator<X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isEmpty(object)) {
            cause = actuator.actuate();
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isEmpty()) {
                cause = actuator.actuate();
            }
        }
        return cause;
    }

    /**
     * <code>xOfEmpty</code>
     * <p>The x of empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @return X <p>The x of empty return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.Function
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfEmpty(@Nullable T object, String message, @NonNull Function<String, X> function) {
        Objects.requireNonNull(function);
        X cause = null;
        if (GeneralUtils.isEmpty(object)) {
            cause = function.apply(message);
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isEmpty()) {
                cause = function.apply(message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfEmpty</code>
     * <p>The x of empty method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @return X <p>The x of empty return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfEmpty(@Nullable T object, RestStatus restStatus, @NonNull Function<RestStatus, X> function) {
        Objects.requireNonNull(function);
        X cause = null;
        if (GeneralUtils.isEmpty(object)) {
            cause = function.apply(restStatus);
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isEmpty()) {
                cause = function.apply(restStatus);
            }
        }
        return cause;
    }

    /**
     * <code>xOfEmptyActuator</code>
     * <p>The x of empty actuator method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @return X <p>The x of empty actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, X extends Throwable> X xOfEmptyActuator(@Nullable T object, String message, @NonNull FunctionActuator<String, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isEmpty(object)) {
            cause = actuator.actuate(message);
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isEmpty()) {
                cause = actuator.actuate(message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfEmptyActuator</code>
     * <p>The x of empty actuator method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @return X <p>The x of empty actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, X extends Throwable> X xOfEmptyActuator(@Nullable T object, RestStatus restStatus, @NonNull FunctionActuator<RestStatus, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isEmpty(object)) {
            cause = actuator.actuate(restStatus);
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isEmpty()) {
                cause = actuator.actuate(restStatus);
            }
        }
        return cause;
    }

    /**
     * <code>xOfEmpty</code>
     * <p>The x of empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @return X <p>The x of empty return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfEmpty(@Nullable T object, String message, String resource, @NonNull BiFunction<String, String, X> function) {
        Objects.requireNonNull(function);
        X cause = null;
        if (GeneralUtils.isEmpty(object)) {
            cause = function.apply(resource, message);
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isEmpty()) {
                cause = function.apply(resource, message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfEmpty</code>
     * <p>The x of empty method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @return X <p>The x of empty return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfEmpty(@Nullable T object, RestStatus restStatus, String resource, @NonNull BiFunction<String, String, X> function) {
        return xOfEmpty(object, restStatus.getMessage(), resource, function);
    }

    /**
     * <code>xOfEmptyActuator</code>
     * <p>The x of empty actuator method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @return X <p>The x of empty actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, X extends Throwable> X xOfEmptyActuator(@Nullable T object, String message, String resource, @NonNull BiFunctionActuator<String, String, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isEmpty(object)) {
            cause = actuator.actuate(resource, message);
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isEmpty()) {
                cause = actuator.actuate(resource, message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfEmptyActuator</code>
     * <p>The x of empty actuator method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @return X <p>The x of empty actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, X extends Throwable> X xOfEmptyActuator(@Nullable T object, RestStatus restStatus, String resource, @NonNull BiFunctionActuator<String, String, X> actuator) throws RestException {
        return xOfEmptyActuator(object, restStatus.getMessage(), resource, actuator);
    }

    /**
     * <code>xOfInvalid</code>
     * <p>The x of invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @return X <p>The x of invalid return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfInvalid(@Nullable T object, @NonNull Supplier<X> supplier) {
        Objects.requireNonNull(supplier);
        X cause = null;
        if (GeneralUtils.isInvalid(object)) {
            cause = supplier.get();
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isInvalid()) {
                cause = supplier.get();
            }
        }
        return cause;
    }

    /**
     * <code>xOfInvalidActuator</code>
     * <p>The x of invalid actuator method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @return X <p>The x of invalid actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, X extends Throwable> X xOfInvalidActuator(@Nullable T object, @NonNull SupplierActuator<X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isInvalid(object)) {
            cause = actuator.actuate();
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isInvalid()) {
                cause = actuator.actuate();
            }
        }
        return cause;
    }

    /**
     * <code>xOfInvalid</code>
     * <p>The x of invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @return X <p>The x of invalid return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.Function
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfInvalid(@Nullable T object, String message, @NonNull Function<String, X> function) {
        Objects.requireNonNull(function);
        X cause = null;
        if (GeneralUtils.isInvalid(object)) {
            cause = function.apply(message);
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isInvalid()) {
                cause = function.apply(message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfInvalid</code>
     * <p>The x of invalid method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @return X <p>The x of invalid return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfInvalid(@Nullable T object, RestStatus restStatus, @NonNull Function<RestStatus, X> function) {
        Objects.requireNonNull(function);
        X cause = null;
        if (GeneralUtils.isInvalid(object)) {
            cause = function.apply(restStatus);
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isInvalid()) {
                cause = function.apply(restStatus);
            }
        }
        return cause;
    }

    /**
     * <code>xOfInvalidActuator</code>
     * <p>The x of invalid actuator method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @return X <p>The x of invalid actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, X extends Throwable> X xOfInvalidActuator(@Nullable T object, String message, @NonNull FunctionActuator<String, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isInvalid(object)) {
            cause = actuator.actuate(message);
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isInvalid()) {
                cause = actuator.actuate(message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfInvalidActuator</code>
     * <p>The x of invalid actuator method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @return X <p>The x of invalid actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, X extends Throwable> X xOfInvalidActuator(@Nullable T object, RestStatus restStatus, @NonNull FunctionActuator<RestStatus, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isInvalid(object)) {
            cause = actuator.actuate(restStatus);
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isInvalid()) {
                cause = actuator.actuate(restStatus);
            }
        }
        return cause;
    }

    /**
     * <code>xOfInvalid</code>
     * <p>The x of invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @return X <p>The x of invalid return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfInvalid(@Nullable T object, String message, String resource, @NonNull BiFunction<String, String, X> function) {
        Objects.requireNonNull(function);
        X cause = null;
        if (GeneralUtils.isInvalid(object)) {
            cause = function.apply(resource, message);
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isInvalid()) {
                cause = function.apply(resource, message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfInvalid</code>
     * <p>The x of invalid method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @return X <p>The x of invalid return object is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends Throwable> X xOfInvalid(@Nullable T object, RestStatus restStatus, String resource, @NonNull BiFunction<String, String, X> function) {
        return xOfInvalid(object, restStatus.getMessage(), resource, function);
    }

    /**
     * <code>xOfInvalidActuator</code>
     * <p>The x of invalid actuator method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @return X <p>The x of invalid actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends RestException> X xOfInvalidActuator(@Nullable T object, String message, String resource, @NonNull BiFunctionActuator<String, String, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isInvalid(object)) {
            cause = actuator.actuate(resource, message);
        } else if (object instanceof RestOptional<?> optional) {
            if (optional.isInvalid()) {
                cause = actuator.actuate(resource, message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfInvalidActuator</code>
     * <p>The x of invalid actuator method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @return X <p>The x of invalid actuator return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends RestException> X xOfInvalidActuator(@Nullable T object, RestStatus restStatus, String resource, @NonNull BiFunctionActuator<String, String, X> actuator) throws RestException {
        return xOfInvalidActuator(object, restStatus.getMessage(), resource, actuator);
    }

    /**
     * <code>ofCauseThrow</code>
     * <p>The of cause throw method.</p>
     * @param <X>   {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param cause X <p>The cause parameter is <code>X</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     */
    public static <X extends Throwable> void ofCauseThrow(X cause) throws X {
        if (GeneralUtils.isNotNull(cause)) {
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofCauseThrow</code>
     * <p>The of cause throw method.</p>
     * @param <X>    {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param logger {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param cause  X <p>The cause parameter is <code>X</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.slf4j.Logger
     */
    public static <X extends Throwable> void ofCauseThrow(Logger logger, X cause) throws X {
        if (GeneralUtils.isNotNull(cause)) {
            logger.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofCauseThrowError</code>
     * <p>The of cause throw error method.</p>
     * @param <X>   {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param cause X <p>The cause parameter is <code>X</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     */
    public static <X extends RestError> void ofCauseThrowError(X cause) {
        if (GeneralUtils.isNotNull(cause)) {
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofCauseThrowError</code>
     * <p>The of cause throw error method.</p>
     * @param <X>    {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param logger {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param cause  X <p>The cause parameter is <code>X</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.slf4j.Logger
     */
    public static <X extends RestError> void ofCauseThrowError(Logger logger, X cause) {
        if (GeneralUtils.isNotNull(cause)) {
            logger.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofCauseThrowException</code>
     * <p>The of cause throw exception method.</p>
     * @param <X>   {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param cause {@link io.github.nichetoolkit.rest.RestException} <p>The cause parameter is <code>RestException</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <X extends RestException> void ofCauseThrowException(RestException cause) throws RestException {
        if (GeneralUtils.isNotNull(cause)) {
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofCauseThrowException</code>
     * <p>The of cause throw exception method.</p>
     * @param <X>    {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param logger {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param cause  {@link io.github.nichetoolkit.rest.RestException} <p>The cause parameter is <code>RestException</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.slf4j.Logger
     */
    public static <X extends RestException> void ofCauseThrowException(Logger logger, RestException cause) throws RestException {
        if (GeneralUtils.isNotNull(cause)) {
            logger.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrow</code>
     * <p>The of true throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.util.function.Supplier
     * @see X
     */
    public static <X extends Throwable> void ofTrueThrow(Boolean present, Supplier<X> supplier) throws X {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = supplier.get();
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrow</code>
     * <p>The of true throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see java.util.function.Supplier
     * @see X
     */
    public static <X extends Throwable> void ofTrueThrow(Boolean present, Logger logger, Supplier<X> supplier) throws X {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = supplier.get();
            logger.error(cause.getMessage());
            throw cause;
        }
    }


    /**
     * <code>ofTrueThrowError</code>
     * <p>The of true throw error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.util.function.Supplier
     */
    public static <X extends RestError> void ofTrueThrowError(Boolean present, Supplier<X> supplier) {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = supplier.get();
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowError</code>
     * <p>The of true throw error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see java.util.function.Supplier
     */
    public static <X extends RestError> void ofTrueThrowError(Boolean present, Logger logger, Supplier<X> supplier) {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = supplier.get();
            logger.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowException</code>
     * <p>The of true throw exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     */
    public static <X extends RestException> void ofTrueThrowException(Boolean present, SupplierActuator<X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = actuator.actuate();
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowException</code>
     * <p>The of true throw exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     */
    public static <X extends RestException> void ofTrueThrowException(Boolean present, Logger logger, SupplierActuator<X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = actuator.actuate();
            logger.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrow</code>
     * <p>The of true throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofTrueThrow(Boolean present, String message, Function<String, X> function) throws X {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = function.apply(message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrow</code>
     * <p>The of true throw method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofTrueThrow(Boolean present, RestStatus restStatus, Function<RestStatus, X> function) throws X {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = function.apply(restStatus);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrow</code>
     * <p>The of true throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofTrueThrow(Boolean present, String message, Logger logger, Function<String, X> function) throws X {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = function.apply(message);
            logger.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrow</code>
     * <p>The of true throw method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofTrueThrow(Boolean present, RestStatus restStatus, Logger logger, Function<RestStatus, X> function) throws X {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = function.apply(restStatus);
            logger.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowError</code>
     * <p>The of true throw error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofTrueThrowError(Boolean present, String message, Function<String, X> function) {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = function.apply(message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowError</code>
     * <p>The of true throw error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofTrueThrowError(Boolean present, RestStatus restStatus, Function<RestStatus, X> function) {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = function.apply(restStatus);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowError</code>
     * <p>The of true throw error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofTrueThrowError(Boolean present, String message, Logger logger, Function<String, X> function) {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = function.apply(message);
            logger.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowError</code>
     * <p>The of true throw error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofTrueThrowError(Boolean present, RestStatus restStatus, Logger logger, Function<RestStatus, X> function) {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = function.apply(restStatus);
            logger.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowException</code>
     * <p>The of true throw exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofTrueThrowException(Boolean present, String message, FunctionActuator<String, X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = actuator.actuate(message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowException</code>
     * <p>The of true throw exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofTrueThrowException(Boolean present, RestStatus restStatus, FunctionActuator<RestStatus, X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = actuator.actuate(restStatus);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowException</code>
     * <p>The of true throw exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofTrueThrowException(Boolean present, String message, Logger logger, FunctionActuator<String, X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = actuator.actuate(message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowException</code>
     * <p>The of true throw exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofTrueThrowException(Boolean present, RestStatus restStatus, Logger logger, FunctionActuator<RestStatus, X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = actuator.actuate(restStatus);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrow</code>
     * <p>The of true throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofTrueThrow(Boolean present, String message, String resource, BiFunction<String, String, X> function) throws X {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = function.apply(resource, message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrow</code>
     * <p>The of true throw method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofTrueThrow(Boolean present, RestStatus restStatus, String resource, BiFunction<String, String, X> function) throws X {
        ofTrueThrow(present, restStatus.getMessage(), resource, function);
    }

    /**
     * <code>ofTrueThrow</code>
     * <p>The of true throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofTrueThrow(Boolean present, String message, String resource, Logger logger, BiFunction<String, String, X> function) throws X {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = function.apply(resource, message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrow</code>
     * <p>The of true throw method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofTrueThrow(Boolean present, RestStatus restStatus, String resource, Logger logger, BiFunction<String, String, X> function) throws X {
        ofTrueThrow(present, restStatus.getMessage(), resource, logger, function);
    }


    /**
     * <code>ofTrueThrowError</code>
     * <p>The of true throw error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofTrueThrowError(Boolean present, String message, String resource, BiFunction<String, String, X> function) {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = function.apply(resource, message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowError</code>
     * <p>The of true throw error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofTrueThrowError(Boolean present, RestStatus restStatus, String resource, BiFunction<String, String, X> function) {
        ofTrueThrowError(present, restStatus.getMessage(), resource, function);
    }

    /**
     * <code>ofTrueThrowError</code>
     * <p>The of true throw error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofTrueThrowError(Boolean present, String message, String resource, Logger logger, BiFunction<String, String, X> function) {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = function.apply(resource, message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowError</code>
     * <p>The of true throw error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofTrueThrowError(Boolean present, RestStatus restStatus, String resource, Logger logger, BiFunction<String, String, X> function) {
        ofTrueThrowError(present, restStatus.getMessage(), resource, logger, function);
    }

    /**
     * <code>ofTrueThrowException</code>
     * <p>The of true throw exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofTrueThrowException(Boolean present, String message, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = actuator.actuate(resource, message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowException</code>
     * <p>The of true throw exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofTrueThrowException(Boolean present, RestStatus restStatus, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofTrueThrowException(present, restStatus.getMessage(), resource, actuator);
    }

    /**
     * <code>ofTrueThrowException</code>
     * <p>The of true throw exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofTrueThrowException(Boolean present, String message, String resource, Logger logger, BiFunctionActuator<String, String, X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = actuator.actuate(resource, message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrowException</code>
     * <p>The of true throw exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofTrueThrowException(Boolean present, RestStatus restStatus, String resource, Logger logger, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofTrueThrowException(present, restStatus.getMessage(), resource, logger, actuator);
    }

    /**
     * <code>ofFalseThrow</code>
     * <p>The of false throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.util.function.Supplier
     * @see X
     */
    public static <X extends Throwable> void ofFalseThrow(Boolean present, Supplier<X> supplier) throws X {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = supplier.get();
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrow</code>
     * <p>The of false throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see java.util.function.Supplier
     * @see X
     */
    public static <X extends Throwable> void ofFalseThrow(Boolean present, Logger logger, Supplier<X> supplier) throws X {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = supplier.get();
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowError</code>
     * <p>The of false throw error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.util.function.Supplier
     */
    public static <X extends RestError> void ofFalseThrowError(Boolean present, Supplier<X> supplier) {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = supplier.get();
            log.error(cause.getMessage());
            throw cause;
        }
    }


    /**
     * <code>ofFalseThrowError</code>
     * <p>The of false throw error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see java.util.function.Supplier
     */
    public static <X extends RestError> void ofFalseThrowError(Boolean present, Logger logger, Supplier<X> supplier) {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = supplier.get();
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowException</code>
     * <p>The of false throw exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     */
    public static <X extends RestException> void ofFalseThrowException(Boolean present, SupplierActuator<X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = actuator.actuate();
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowException</code>
     * <p>The of false throw exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     */
    public static <X extends RestException> void ofFalseThrowException(Boolean present, Logger logger, SupplierActuator<X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = actuator.actuate();
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrow</code>
     * <p>The of false throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofFalseThrow(Boolean present, String message, Function<String, X> function) throws X {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = function.apply(message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrow</code>
     * <p>The of false throw method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofFalseThrow(Boolean present, RestStatus restStatus, Function<RestStatus, X> function) throws X {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = function.apply(restStatus);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrow</code>
     * <p>The of false throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofFalseThrow(Boolean present, String message, Logger logger, Function<String, X> function) throws X {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = function.apply(message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrow</code>
     * <p>The of false throw method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofFalseThrow(Boolean present, RestStatus restStatus, Logger logger, Function<RestStatus, X> function) throws X {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = function.apply(restStatus);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowError</code>
     * <p>The of false throw error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofFalseThrowError(Boolean present, String message, Function<String, X> function) {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = function.apply(message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowError</code>
     * <p>The of false throw error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofFalseThrowError(Boolean present, RestStatus restStatus, Function<RestStatus, X> function) {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = function.apply(restStatus);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowError</code>
     * <p>The of false throw error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofFalseThrowError(Boolean present, String message, Logger logger, Function<String, X> function) {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = function.apply(message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowError</code>
     * <p>The of false throw error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofFalseThrowError(Boolean present, RestStatus restStatus, Logger logger, Function<RestStatus, X> function) {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = function.apply(restStatus);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowException</code>
     * <p>The of false throw exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofFalseThrowException(Boolean present, String message, FunctionActuator<String, X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = actuator.actuate(message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowException</code>
     * <p>The of false throw exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofFalseThrowException(Boolean present, RestStatus restStatus, FunctionActuator<RestStatus, X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = actuator.actuate(restStatus);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowException</code>
     * <p>The of false throw exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofFalseThrowException(Boolean present, String message, Logger logger, FunctionActuator<String, X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = actuator.actuate(message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowException</code>
     * <p>The of false throw exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofFalseThrowException(Boolean present, RestStatus restStatus, Logger logger, FunctionActuator<RestStatus, X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = actuator.actuate(restStatus);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrow</code>
     * <p>The of false throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofFalseThrow(Boolean present, String message, String resource, BiFunction<String, String, X> function) throws X {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = function.apply(resource, message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrow</code>
     * <p>The of false throw method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofFalseThrow(Boolean present, RestStatus restStatus, String resource, BiFunction<String, String, X> function) throws X {
        ofFalseThrow(present, restStatus.getMessage(), resource, function);
    }

    /**
     * <code>ofFalseThrow</code>
     * <p>The of false throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofFalseThrow(Boolean present, String message, String resource, Logger logger, BiFunction<String, String, X> function) throws X {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = function.apply(resource, message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrow</code>
     * <p>The of false throw method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofFalseThrow(Boolean present, RestStatus restStatus, String resource, Logger logger, BiFunction<String, String, X> function) throws X {
        ofFalseThrow(present, restStatus.getMessage(), resource, logger, function);
    }

    /**
     * <code>ofFalseThrowError</code>
     * <p>The of false throw error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofFalseThrowError(Boolean present, String message, String resource, BiFunction<String, String, X> function) {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = function.apply(resource, message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowError</code>
     * <p>The of false throw error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofFalseThrowError(Boolean present, RestStatus restStatus, String resource, BiFunction<String, String, X> function) {
        ofFalseThrowError(present, restStatus.getMessage(), resource, function);
    }

    /**
     * <code>ofFalseThrowError</code>
     * <p>The of false throw error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofFalseThrowError(Boolean present, String message, String resource, Logger logger, BiFunction<String, String, X> function) {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = function.apply(resource, message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowError</code>
     * <p>The of false throw error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofFalseThrowError(Boolean present, RestStatus restStatus, String resource, Logger logger, BiFunction<String, String, X> function) {
        ofFalseThrowError(present, restStatus.getMessage(), resource, logger, function);
    }

    /**
     * <code>ofFalseThrowException</code>
     * <p>The of false throw exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofFalseThrowException(Boolean present, String message, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = actuator.actuate(resource, message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowException</code>
     * <p>The of false throw exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofFalseThrowException(Boolean present, RestStatus restStatus, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofFalseThrowException(present, restStatus.getMessage(), resource, actuator);
    }

    /**
     * <code>ofFalseThrowException</code>
     * <p>The of false throw exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofFalseThrowException(Boolean present, String message, String resource, Logger logger, BiFunctionActuator<String, String, X> actuator) throws RestException {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = actuator.actuate(resource, message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrowException</code>
     * <p>The of false throw exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofFalseThrowException(Boolean present, RestStatus restStatus, String resource, Logger logger, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofFalseThrowException(present, restStatus.getMessage(), resource, logger, actuator);
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     * @see X
     */
    public static <T, X extends Throwable> void ofNull(@Nullable T object, @NonNull Supplier<X> supplier) throws X {
        ofCauseThrow(xOfNull(object, supplier));
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see org.slf4j.Logger
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     * @see X
     */
    public static <T, X extends Throwable> void ofNull(@Nullable T object, Logger logger, @NonNull Supplier<X> supplier) throws X {
        ofCauseThrow(logger, xOfNull(object, supplier));
    }

    /**
     * <code>ofNullError</code>
     * <p>The of null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends RestError> void ofNullError(@Nullable T object, @NonNull Supplier<X> supplier) {
        ofCauseThrowError(xOfNull(object, supplier));
    }

    /**
     * <code>ofNullError</code>
     * <p>The of null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see org.slf4j.Logger
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends RestError> void ofNullError(@Nullable T object, Logger logger, @NonNull Supplier<X> supplier) {
        ofCauseThrowError(logger, xOfNull(object, supplier));
    }

    /**
     * <code>ofNullException</code>
     * <p>The of null exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends RestException> void ofNullException(@Nullable T object, @NonNull SupplierActuator<X> actuator) throws RestException {
        ofCauseThrowException(xOfNullActuator(object, actuator));
    }

    /**
     * <code>ofNullException</code>
     * <p>The of null exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends RestException> void ofNullException(@Nullable T object, Logger logger, @NonNull SupplierActuator<X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfNullActuator(object, actuator));
    }

    /**
     * <code>ofEmpty</code>
     * <p>The of empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     * @see X
     */
    public static <T, X extends Throwable> void ofEmpty(@Nullable T object, @NonNull Supplier<X> supplier) throws X {
        ofCauseThrow(xOfEmpty(object, supplier));
    }

    /**
     * <code>ofEmpty</code>
     * <p>The of empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see org.slf4j.Logger
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     * @see X
     */
    public static <T, X extends Throwable> void ofEmpty(@Nullable T object, Logger logger, @NonNull Supplier<X> supplier) throws X {
        ofCauseThrow(logger, xOfEmpty(object, supplier));
    }

    /**
     * <code>ofEmptyError</code>
     * <p>The of empty error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends RestError> void ofEmptyError(@Nullable T object, @NonNull Supplier<X> supplier) {
        ofCauseThrowError(xOfEmpty(object, supplier));
    }

    /**
     * <code>ofEmptyError</code>
     * <p>The of empty error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see org.slf4j.Logger
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends RestError> void ofEmptyError(@Nullable T object, Logger logger, @NonNull Supplier<X> supplier) {
        ofCauseThrowError(logger, xOfEmpty(object, supplier));
    }

    /**
     * <code>ofEmptyException</code>
     * <p>The of empty exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends RestException> void ofEmptyException(@Nullable T object, @NonNull SupplierActuator<X> actuator) throws RestException {
        ofCauseThrowException(xOfEmptyActuator(object, actuator));
    }

    /**
     * <code>ofEmptyException</code>
     * <p>The of empty exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends RestException> void ofEmptyException(@Nullable T object, Logger logger, @NonNull SupplierActuator<X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfEmptyActuator(object, actuator));
    }

    /**
     * <code>ofInvalid</code>
     * <p>The of invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     * @see X
     */
    public static <T, X extends Throwable> void ofInvalid(@Nullable T object, @NonNull Supplier<X> supplier) throws X {
        ofCauseThrow(xOfInvalid(object, supplier));
    }

    /**
     * <code>ofInvalid</code>
     * <p>The of invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see org.slf4j.Logger
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     * @see X
     */
    public static <T, X extends Throwable> void ofInvalid(@Nullable T object, Logger logger, @NonNull Supplier<X> supplier) throws X {
        ofCauseThrow(logger, xOfInvalid(object, supplier));
    }

    /**
     * <code>ofInvalidError</code>
     * <p>The of invalid error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends RestError> void ofInvalidError(@Nullable T object, @NonNull Supplier<X> supplier) {
        ofCauseThrowError(xOfInvalid(object, supplier));
    }

    /**
     * <code>ofInvalidError</code>
     * <p>The of invalid error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see org.slf4j.Logger
     * @see java.util.function.Supplier
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends RestError> void ofInvalidError(@Nullable T object, Logger logger, @NonNull Supplier<X> supplier) {
        ofCauseThrowError(logger, xOfInvalid(object, supplier));
    }

    /**
     * <code>ofInvalidException</code>
     * <p>The of invalid exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends RestException> void ofInvalidException(@Nullable T object, @NonNull SupplierActuator<X> actuator) throws RestException {
        ofCauseThrowException(xOfInvalidActuator(object, actuator));
    }

    /**
     * <code>ofInvalidException</code>
     * <p>The of invalid exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.jspecify.annotations.NonNull
     */
    public static <T, X extends RestException> void ofInvalidException(@Nullable T object, Logger logger, @NonNull SupplierActuator<X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfInvalidActuator(object, actuator));
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.Function
     * @see X
     */
    public static <T, X extends Throwable> void ofNull(@Nullable T object, String message, Function<String, X> function) throws X {
        ofCauseThrow(xOfNull(object, message, function));
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     * @see X
     */
    public static <T, X extends Throwable> void ofNull(@Nullable T object, RestStatus restStatus, Function<RestStatus, X> function) throws X {
        ofCauseThrow(xOfNull(object, restStatus, function));
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     * @see X
     */
    public static <T, X extends Throwable> void ofNull(@Nullable T object, String message, Logger logger, Function<String, X> function) throws X {
        ofCauseThrow(logger, xOfNull(object, message, function));
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     * @see X
     */
    public static <T, X extends Throwable> void ofNull(@Nullable T object, RestStatus restStatus, Logger logger, Function<RestStatus, X> function) throws X {
        ofCauseThrow(logger, xOfNull(object, restStatus, function));
    }

    /**
     * <code>ofNullError</code>
     * <p>The of null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.Function
     */
    public static <T, X extends RestError> void ofNullError(@Nullable T object, String message, Function<String, X> function) {
        ofCauseThrowError(xOfNull(object, message, function));
    }

    /**
     * <code>ofNullError</code>
     * <p>The of null error method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     */
    public static <T, X extends RestError> void ofNullError(@Nullable T object, RestStatus restStatus, Function<RestStatus, X> function) {
        ofCauseThrowError(xOfNull(object, restStatus, function));
    }

    /**
     * <code>ofNullError</code>
     * <p>The of null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     */
    public static <T, X extends RestError> void ofNullError(@Nullable T object, String message, Logger logger, Function<String, X> function) {
        ofCauseThrowError(logger, xOfNull(object, message, function));
    }

    /**
     * <code>ofNullError</code>
     * <p>The of null error method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     */
    public static <T, X extends RestError> void ofNullError(@Nullable T object, RestStatus restStatus, Logger logger, Function<RestStatus, X> function) {
        ofCauseThrowError(logger, xOfNull(object, restStatus, function));
    }

    /**
     * <code>ofNullException</code>
     * <p>The of null exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <T, X extends RestException> void ofNullException(@Nullable T object, String message, FunctionActuator<String, X> actuator) throws RestException {
        ofCauseThrowException(xOfNullActuator(object, message, actuator));
    }

    /**
     * <code>ofNullException</code>
     * <p>The of null exception method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <T, X extends RestException> void ofNullException(@Nullable T object, RestStatus restStatus, FunctionActuator<RestStatus, X> actuator) throws RestException {
        ofCauseThrowException(xOfNullActuator(object, restStatus, actuator));
    }

    /**
     * <code>ofNullException</code>
     * <p>The of null exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <T, X extends RestException> void ofNullException(@Nullable T object, String message, Logger logger, FunctionActuator<String, X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfNullActuator(object, message, actuator));
    }

    /**
     * <code>ofNullException</code>
     * <p>The of null exception method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <T, X extends RestException> void ofNullException(@Nullable T object, RestStatus restStatus, Logger logger, FunctionActuator<RestStatus, X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfNullActuator(object, restStatus, actuator));
    }

    /**
     * <code>ofEmpty</code>
     * <p>The of empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.Function
     * @see X
     */
    public static <T, X extends Throwable> void ofEmpty(@Nullable T object, String message, Function<String, X> function) throws X {
        ofCauseThrow(xOfEmpty(object, message, function));
    }

    /**
     * <code>ofEmpty</code>
     * <p>The of empty method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     * @see X
     */
    public static <T, X extends Throwable> void ofEmpty(@Nullable T object, RestStatus restStatus, Function<RestStatus, X> function) throws X {
        ofCauseThrow(xOfEmpty(object, restStatus, function));
    }

    /**
     * <code>ofEmpty</code>
     * <p>The of empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     * @see X
     */
    public static <T, X extends Throwable> void ofEmpty(@Nullable T object, String message, Logger logger, Function<String, X> function) throws X {
        ofCauseThrow(logger, xOfEmpty(object, message, function));
    }

    /**
     * <code>ofEmpty</code>
     * <p>The of empty method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     * @see X
     */
    public static <T, X extends Throwable> void ofEmpty(@Nullable T object, RestStatus restStatus, Logger logger, Function<RestStatus, X> function) throws X {
        ofCauseThrow(logger, xOfEmpty(object, restStatus, function));
    }

    /**
     * <code>ofEmptyError</code>
     * <p>The of empty error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.Function
     */
    public static <T, X extends RestError> void ofEmptyError(@Nullable T object, String message, Function<String, X> function) {
        ofCauseThrowError(xOfEmpty(object, message, function));
    }

    /**
     * <code>ofEmptyError</code>
     * <p>The of empty error method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     */
    public static <T, X extends RestError> void ofEmptyError(@Nullable T object, RestStatus restStatus, Function<RestStatus, X> function) {
        ofCauseThrowError(xOfEmpty(object, restStatus, function));
    }

    /**
     * <code>ofEmptyError</code>
     * <p>The of empty error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     */
    public static <T, X extends RestError> void ofEmptyError(@Nullable T object, String message, Logger logger, Function<String, X> function) {
        ofCauseThrowError(logger, xOfEmpty(object, message, function));
    }

    /**
     * <code>ofEmptyError</code>
     * <p>The of empty error method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     */
    public static <T, X extends RestError> void ofEmptyError(@Nullable T object, RestStatus restStatus, Logger logger, Function<RestStatus, X> function) {
        ofCauseThrowError(logger, xOfEmpty(object, restStatus, function));
    }

    /**
     * <code>ofEmptyException</code>
     * <p>The of empty exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <T, X extends RestException> void ofEmptyException(@Nullable T object, String message, FunctionActuator<String, X> actuator) throws RestException {
        ofCauseThrowException(xOfEmptyActuator(object, message, actuator));
    }

    /**
     * <code>ofEmptyException</code>
     * <p>The of empty exception method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <T, X extends RestException> void ofEmptyException(@Nullable T object, RestStatus restStatus, FunctionActuator<RestStatus, X> actuator) throws RestException {
        ofCauseThrowException(xOfEmptyActuator(object, restStatus, actuator));
    }

    /**
     * <code>ofEmptyException</code>
     * <p>The of empty exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <T, X extends RestException> void ofEmptyException(@Nullable T object, String message, Logger logger, FunctionActuator<String, X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfEmptyActuator(object, message, actuator));
    }

    /**
     * <code>ofEmptyException</code>
     * <p>The of empty exception method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <T, X extends RestException> void ofEmptyException(@Nullable T object, RestStatus restStatus, Logger logger, FunctionActuator<RestStatus, X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfEmptyActuator(object, restStatus, actuator));
    }

    /**
     * <code>ofInvalid</code>
     * <p>The of invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.Function
     * @see X
     */
    public static <T, X extends Throwable> void ofInvalid(@Nullable T object, String message, Function<String, X> function) throws X {
        ofCauseThrow(xOfInvalid(object, message, function));
    }

    /**
     * <code>ofInvalid</code>
     * <p>The of invalid method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     * @see X
     */
    public static <T, X extends Throwable> void ofInvalid(@Nullable T object, RestStatus restStatus, Function<RestStatus, X> function) throws X {
        ofCauseThrow(xOfInvalid(object, restStatus, function));
    }

    /**
     * <code>ofInvalid</code>
     * <p>The of invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     * @see X
     */
    public static <T, X extends Throwable> void ofInvalid(@Nullable T object, String message, Logger logger, Function<String, X> function) throws X {
        ofCauseThrow(logger, xOfInvalid(object, message, function));
    }

    /**
     * <code>ofInvalid</code>
     * <p>The of invalid method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     * @see X
     */
    public static <T, X extends Throwable> void ofInvalid(@Nullable T object, RestStatus restStatus, Logger logger, Function<RestStatus, X> function) throws X {
        ofCauseThrow(logger, xOfInvalid(object, restStatus, function));
    }

    /**
     * <code>ofInvalidError</code>
     * <p>The of invalid error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.Function
     */
    public static <T, X extends RestError> void ofInvalidError(@Nullable T object, String message, Function<String, X> function) {
        ofCauseThrowError(xOfInvalid(object, message, function));
    }

    /**
     * <code>ofInvalidError</code>
     * <p>The of invalid error method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     */
    public static <T, X extends RestError> void ofInvalidError(@Nullable T object, RestStatus restStatus, Function<RestStatus, X> function) {
        ofCauseThrowError(xOfInvalid(object, restStatus, function));
    }

    /**
     * <code>ofInvalidError</code>
     * <p>The of invalid error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     */
    public static <T, X extends RestError> void ofInvalidError(@Nullable T object, String message, Logger logger, Function<String, X> function) {
        ofCauseThrowError(logger, xOfInvalid(object, message, function));
    }

    /**
     * <code>ofInvalidError</code>
     * <p>The of invalid error method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     */
    public static <T, X extends RestError> void ofInvalidError(@Nullable T object, RestStatus restStatus, Logger logger, Function<RestStatus, X> function) {
        ofCauseThrowError(logger, xOfInvalid(object, restStatus, function));
    }

    /**
     * <code>ofInvalidException</code>
     * <p>The of invalid exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <T, X extends RestException> void ofInvalidException(@Nullable T object, String message, FunctionActuator<String, X> actuator) throws RestException {
        ofCauseThrowException(xOfInvalidActuator(object, message, actuator));
    }

    /**
     * <code>ofInvalidException</code>
     * <p>The of invalid exception method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <T, X extends RestException> void ofInvalidException(@Nullable T object, RestStatus restStatus, FunctionActuator<RestStatus, X> actuator) throws RestException {
        ofCauseThrowException(xOfInvalidActuator(object, restStatus, actuator));
    }

    /**
     * <code>ofInvalidException</code>
     * <p>The of invalid exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <T, X extends RestException> void ofInvalidException(@Nullable T object, String message, Logger logger, FunctionActuator<String, X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfInvalidActuator(object, message, actuator));
    }

    /**
     * <code>ofInvalidException</code>
     * <p>The of invalid exception method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <T, X extends RestException> void ofInvalidException(@Nullable T object, RestStatus restStatus, Logger logger, FunctionActuator<RestStatus, X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfInvalidActuator(object, restStatus, actuator));
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <T, X extends Throwable> void ofNull(@Nullable T object, String message, String resource, BiFunction<String, String, X> function) throws X {
        ofCauseThrow(xOfNull(object, message, resource, function));
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <T, X extends Throwable> void ofNull(@Nullable T object, RestStatus restStatus, String resource, BiFunction<String, String, X> function) throws X {
        ofCauseThrow(xOfNull(object, restStatus, resource, function));
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <T, X extends Throwable> void ofNull(@Nullable T object, String message, String resource, Logger logger, BiFunction<String, String, X> function) throws X {
        ofCauseThrow(logger, xOfNull(object, message, resource, function));
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <T, X extends Throwable> void ofNull(@Nullable T object, RestStatus restStatus, String resource, Logger logger, BiFunction<String, String, X> function) throws X {
        ofCauseThrow(logger, xOfNull(object, restStatus, resource, function));
    }

    /**
     * <code>ofNullError</code>
     * <p>The of null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.BiFunction
     */
    public static <T, X extends RestError> void ofNullError(@Nullable T object, String message, String resource, BiFunction<String, String, X> function) {
        ofCauseThrowError(xOfNull(object, message, resource, function));
    }

    /**
     * <code>ofNullError</code>
     * <p>The of null error method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     */
    public static <T, X extends RestError> void ofNullError(@Nullable T object, RestStatus restStatus, String resource, BiFunction<String, String, X> function) {
        ofCauseThrowError(xOfNull(object, restStatus, resource, function));
    }

    /**
     * <code>ofNullError</code>
     * <p>The of null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     */
    public static <T, X extends RestError> void ofNullError(@Nullable T object, String message, String resource, Logger logger, BiFunction<String, String, X> function) {
        ofCauseThrowError(logger, xOfNull(object, message, resource, function));
    }

    /**
     * <code>ofNullError</code>
     * <p>The of null error method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     */
    public static <T, X extends RestError> void ofNullError(@Nullable T object, RestStatus restStatus, String resource, Logger logger, BiFunction<String, String, X> function) {
        ofCauseThrowError(logger, xOfNull(object, restStatus, resource, function));
    }

    /**
     * <code>ofNullException</code>
     * <p>The of null exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <T, X extends RestException> void ofNullException(@Nullable T object, String message, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofCauseThrowException(xOfNullActuator(object, message, resource, actuator));
    }

    /**
     * <code>ofNullException</code>
     * <p>The of null exception method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <T, X extends RestException> void ofNullException(@Nullable T object, RestStatus restStatus, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofCauseThrowException(xOfNullActuator(object, restStatus, resource, actuator));
    }

    /**
     * <code>ofNullException</code>
     * <p>The of null exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <T, X extends RestException> void ofNullException(@Nullable T object, String message, String resource, Logger logger, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfNullActuator(object, message, resource, actuator));
    }

    /**
     * <code>ofNullException</code>
     * <p>The of null exception method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <T, X extends RestException> void ofNullException(@Nullable T object, RestStatus restStatus, String resource, Logger logger, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfNullActuator(object, restStatus, resource, actuator));
    }

    /**
     * <code>ofEmpty</code>
     * <p>The of empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <T, X extends Throwable> void ofEmpty(@Nullable T object, String message, String resource, BiFunction<String, String, X> function) throws X {
        ofCauseThrow(xOfEmpty(object, message, resource, function));
    }

    /**
     * <code>ofEmpty</code>
     * <p>The of empty method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <T, X extends Throwable> void ofEmpty(@Nullable T object, RestStatus restStatus, String resource, BiFunction<String, String, X> function) throws X {
        ofCauseThrow(xOfEmpty(object, restStatus, resource, function));
    }

    /**
     * <code>ofEmpty</code>
     * <p>The of empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <T, X extends Throwable> void ofEmpty(@Nullable T object, String message, String resource, Logger logger, BiFunction<String, String, X> function) throws X {
        ofCauseThrow(logger, xOfEmpty(object, message, resource, function));
    }

    /**
     * <code>ofEmpty</code>
     * <p>The of empty method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <T, X extends Throwable> void ofEmpty(@Nullable T object, RestStatus restStatus, String resource, Logger logger, BiFunction<String, String, X> function) throws X {
        ofCauseThrow(logger, xOfEmpty(object, restStatus, resource, function));
    }

    /**
     * <code>ofEmptyError</code>
     * <p>The of empty error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.BiFunction
     */
    public static <T, X extends RestError> void ofEmptyError(@Nullable T object, String message, String resource, BiFunction<String, String, X> function) {
        ofCauseThrowError(xOfEmpty(object, message, resource, function));
    }

    /**
     * <code>ofEmptyError</code>
     * <p>The of empty error method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     */
    public static <T, X extends RestError> void ofEmptyError(@Nullable T object, RestStatus restStatus, String resource, BiFunction<String, String, X> function) {
        ofCauseThrowError(xOfEmpty(object, restStatus, resource, function));
    }

    /**
     * <code>ofEmptyError</code>
     * <p>The of empty error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     */
    public static <T, X extends RestError> void ofEmptyError(@Nullable T object, String message, String resource, Logger logger, BiFunction<String, String, X> function) {
        ofCauseThrowError(logger, xOfEmpty(object, message, resource, function));
    }

    /**
     * <code>ofEmptyError</code>
     * <p>The of empty error method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     */
    public static <T, X extends RestError> void ofEmptyError(@Nullable T object, RestStatus restStatus, String resource, Logger logger, BiFunction<String, String, X> function) {
        ofCauseThrowError(logger, xOfEmpty(object, restStatus, resource, function));
    }

    /**
     * <code>ofEmptyException</code>
     * <p>The of empty exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <T, X extends RestException> void ofEmptyException(@Nullable T object, String message, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofCauseThrowException(xOfEmptyActuator(object, message, resource, actuator));
    }

    /**
     * <code>ofEmptyException</code>
     * <p>The of empty exception method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <T, X extends RestException> void ofEmptyException(@Nullable T object, RestStatus restStatus, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofCauseThrowException(xOfEmptyActuator(object, restStatus, resource, actuator));
    }

    /**
     * <code>ofEmptyException</code>
     * <p>The of empty exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <T, X extends RestException> void ofEmptyException(@Nullable T object, String message, String resource, Logger logger, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfEmptyActuator(object, message, resource, actuator));
    }

    /**
     * <code>ofEmptyException</code>
     * <p>The of empty exception method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <T, X extends RestException> void ofEmptyException(@Nullable T object, RestStatus restStatus, String resource, Logger logger, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfEmptyActuator(object, restStatus, resource, actuator));
    }

    /**
     * <code>ofInvalid</code>
     * <p>The of invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <T, X extends Throwable> void ofInvalid(@Nullable T object, String message, String resource, BiFunction<String, String, X> function) throws X {
        ofCauseThrow(xOfInvalid(object, message, resource, function));
    }

    /**
     * <code>ofInvalid</code>
     * <p>The of invalid method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <T, X extends Throwable> void ofInvalid(@Nullable T object, RestStatus restStatus, String resource, BiFunction<String, String, X> function) throws X {
        ofCauseThrow(xOfInvalid(object, restStatus, resource, function));
    }

    /**
     * <code>ofInvalid</code>
     * <p>The of invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <T, X extends Throwable> void ofInvalid(@Nullable T object, String message, String resource, Logger logger, BiFunction<String, String, X> function) throws X {
        ofCauseThrow(logger, xOfInvalid(object, message, resource, function));
    }

    /**
     * <code>ofInvalid</code>
     * <p>The of invalid method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <T, X extends Throwable> void ofInvalid(@Nullable T object, RestStatus restStatus, String resource, Logger logger, BiFunction<String, String, X> function) throws X {
        ofCauseThrow(logger, xOfInvalid(object, restStatus, resource, function));
    }

    /**
     * <code>ofInvalidError</code>
     * <p>The of invalid error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see java.util.function.BiFunction
     */
    public static <T, X extends RestError> void ofInvalidError(@Nullable T object, String message, String resource, BiFunction<String, String, X> function) {
        ofCauseThrowError(xOfInvalid(object, message, resource, function));
    }

    /**
     * <code>ofInvalidError</code>
     * <p>The of invalid error method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     */
    public static <T, X extends RestError> void ofInvalidError(@Nullable T object, RestStatus restStatus, String resource, BiFunction<String, String, X> function) {
        ofCauseThrowError(xOfInvalid(object, restStatus, resource, function));
    }

    /**
     * <code>ofInvalidError</code>
     * <p>The of invalid error method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     */
    public static <T, X extends RestError> void ofInvalidError(@Nullable T object, String message, String resource, Logger logger, BiFunction<String, String, X> function) {
        ofCauseThrowError(logger, xOfInvalid(object, message, resource, function));
    }

    /**
     * <code>ofInvalidError</code>
     * <p>The of invalid error method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     */
    public static <T, X extends RestError> void ofInvalidError(@Nullable T object, RestStatus restStatus, String resource, Logger logger, BiFunction<String, String, X> function) {
        ofCauseThrowError(logger, xOfInvalid(object, restStatus, resource, function));
    }

    /**
     * <code>ofInvalidException</code>
     * <p>The of invalid exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <T, X extends RestException> void ofInvalidException(@Nullable T object, String message, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofCauseThrowException(xOfInvalidActuator(object, message, resource, actuator));
    }

    /**
     * <code>ofInvalidException</code>
     * <p>The of invalid exception method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <T, X extends RestException> void ofInvalidException(@Nullable T object, RestStatus restStatus, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofCauseThrowException(xOfInvalidActuator(object, restStatus, resource, actuator));
    }

    /**
     * <code>ofInvalidException</code>
     * <p>The of invalid exception method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object   T <p>The object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <T, X extends RestException> void ofInvalidException(@Nullable T object, String message, String resource, Logger logger, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfInvalidActuator(object, message, resource, actuator));
    }

    /**
     * <code>ofInvalidException</code>
     * <p>The of invalid exception method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param object     T <p>The object parameter is <code>T</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see org.jspecify.annotations.Nullable
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <T, X extends RestException> void ofInvalidException(@Nullable T object, RestStatus restStatus, String resource, Logger logger, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofCauseThrowException(logger, xOfInvalidActuator(object, restStatus, resource, actuator));
    }

    /**
     * <code>ofTrue</code>
     * <p>The of true method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.util.function.Supplier
     * @see X
     */
    public static <X extends Throwable> void ofTrue(Boolean present, Supplier<X> supplier) throws X {
        ofTrueThrow(present, supplier);
    }

    /**
     * <code>ofTrue</code>
     * <p>The of true method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see java.util.function.Supplier
     * @see X
     */
    public static <X extends Throwable> void ofTrue(Boolean present, Logger logger, Supplier<X> supplier) throws X {
        ofTrueThrow(present, logger, supplier);
    }

    /**
     * <code>ofTrueError</code>
     * <p>The of true error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.util.function.Supplier
     */
    public static <X extends RestError> void ofTrueError(Boolean present, Supplier<X> supplier) {
        ofTrueThrowError(present, supplier);
    }

    /**
     * <code>ofTrueError</code>
     * <p>The of true error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see java.util.function.Supplier
     */
    public static <X extends RestError> void ofTrueError(Boolean present, Logger logger, Supplier<X> supplier) {
        ofTrueThrowError(present, logger, supplier);
    }

    /**
     * <code>ofTrueException</code>
     * <p>The of true exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     */
    public static <X extends RestException> void ofTrueException(Boolean present, SupplierActuator<X> actuator) throws RestException {
        ofTrueThrowException(present, actuator);
    }

    /**
     * <code>ofTrueException</code>
     * <p>The of true exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     */
    public static <X extends RestException> void ofTrueException(Boolean present, Logger logger, SupplierActuator<X> actuator) throws RestException {
        ofTrueThrowException(present, logger, actuator);
    }

    /**
     * <code>ofTrue</code>
     * <p>The of true method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofTrue(Boolean present, String message, Function<String, X> function) throws X {
        ofTrueThrow(present, message, function);
    }

    /**
     * <code>ofTrue</code>
     * <p>The of true method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofTrue(Boolean present, RestStatus restStatus, Function<RestStatus, X> function) throws X {
        ofTrueThrow(present, restStatus, function);
    }

    /**
     * <code>ofTrue</code>
     * <p>The of true method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofTrue(Boolean present, String message, Logger logger, Function<String, X> function) throws X {
        ofTrueThrow(present, message, logger, function);
    }

    /**
     * <code>ofTrue</code>
     * <p>The of true method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofTrue(Boolean present, RestStatus restStatus, Logger logger, Function<RestStatus, X> function) throws X {
        ofTrueThrow(present, restStatus, logger, function);
    }

    /**
     * <code>ofTrueError</code>
     * <p>The of true error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofTrueError(Boolean present, String message, Function<String, X> function) {
        ofTrueThrowError(present, message, function);
    }

    /**
     * <code>ofTrueError</code>
     * <p>The of true error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofTrueError(Boolean present, RestStatus restStatus, Function<RestStatus, X> function) {
        ofTrueThrowError(present, restStatus, function);
    }

    /**
     * <code>ofTrueError</code>
     * <p>The of true error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofTrueError(Boolean present, String message, Logger logger, Function<String, X> function) {
        ofTrueThrowError(present, message, logger, function);
    }

    /**
     * <code>ofTrueError</code>
     * <p>The of true error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofTrueError(Boolean present, RestStatus restStatus, Logger logger, Function<RestStatus, X> function) {
        ofTrueThrowError(present, restStatus, logger, function);
    }

    /**
     * <code>ofTrueException</code>
     * <p>The of true exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofTrueException(Boolean present, String message, FunctionActuator<String, X> actuator) throws RestException {
        ofTrueThrowException(present, message, actuator);
    }

    /**
     * <code>ofTrueException</code>
     * <p>The of true exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofTrueException(Boolean present, RestStatus restStatus, FunctionActuator<RestStatus, X> actuator) throws RestException {
        ofTrueThrowException(present, restStatus, actuator);
    }

    /**
     * <code>ofTrueException</code>
     * <p>The of true exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofTrueException(Boolean present, String message, Logger logger, FunctionActuator<String, X> actuator) throws RestException {
        ofTrueThrowException(present, message, logger, actuator);
    }

    /**
     * <code>ofTrueException</code>
     * <p>The of true exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofTrueException(Boolean present, RestStatus restStatus, Logger logger, FunctionActuator<RestStatus, X> actuator) throws RestException {
        ofTrueThrowException(present, restStatus, logger, actuator);
    }

    /**
     * <code>ofTrue</code>
     * <p>The of true method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofTrue(Boolean present, String message, String resource, BiFunction<String, String, X> function) throws X {
        ofTrueThrow(present, message, resource, function);
    }

    /**
     * <code>ofTrue</code>
     * <p>The of true method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofTrue(Boolean present, RestStatus restStatus, String resource, BiFunction<String, String, X> function) throws X {
        ofTrueThrow(present, restStatus, resource, function);
    }

    /**
     * <code>ofTrue</code>
     * <p>The of true method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofTrue(Boolean present, String message, String resource, Logger logger, BiFunction<String, String, X> function) throws X {
        ofTrueThrow(present, message, resource, logger, function);
    }

    /**
     * <code>ofTrue</code>
     * <p>The of true method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofTrue(Boolean present, RestStatus restStatus, String resource, Logger logger, BiFunction<String, String, X> function) throws X {
        ofTrueThrow(present, restStatus, resource, logger, function);
    }

    /**
     * <code>ofTrueError</code>
     * <p>The of true error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofTrueError(Boolean present, String message, String resource, BiFunction<String, String, X> function) {
        ofTrueThrowError(present, message, resource, function);
    }

    /**
     * <code>ofTrueError</code>
     * <p>The of true error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofTrueError(Boolean present, RestStatus restStatus, String resource, BiFunction<String, String, X> function) {
        ofTrueThrowError(present, restStatus, resource, function);
    }

    /**
     * <code>ofTrueError</code>
     * <p>The of true error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofTrueError(Boolean present, String message, String resource, Logger logger, BiFunction<String, String, X> function) {
        ofTrueThrowError(present, message, resource, logger, function);
    }

    /**
     * <code>ofTrueError</code>
     * <p>The of true error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofTrueError(Boolean present, RestStatus restStatus, String resource, Logger logger, BiFunction<String, String, X> function) {
        ofTrueThrowError(present, restStatus, resource, logger, function);
    }

    /**
     * <code>ofTrueException</code>
     * <p>The of true exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofTrueException(Boolean present, String message, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofTrueThrowException(present, message, resource, actuator);
    }

    /**
     * <code>ofTrueException</code>
     * <p>The of true exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofTrueException(Boolean present, RestStatus restStatus, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofTrueThrowException(present, restStatus, resource, actuator);
    }

    /**
     * <code>ofTrueException</code>
     * <p>The of true exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofTrueException(Boolean present, String message, String resource, Logger logger, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofTrueThrowException(present, message, resource, logger, actuator);
    }

    /**
     * <code>ofTrueException</code>
     * <p>The of true exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofTrueException(Boolean present, RestStatus restStatus, String resource, Logger logger, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofTrueThrowException(present, restStatus, resource, logger, actuator);
    }

    /**
     * <code>ofFalse</code>
     * <p>The of false method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.util.function.Supplier
     * @see X
     */
    public static <X extends Throwable> void ofFalse(Boolean present, Supplier<X> supplier) throws X {
        ofFalseThrow(present, supplier);
    }

    /**
     * <code>ofFalse</code>
     * <p>The of false method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see java.util.function.Supplier
     * @see X
     */
    public static <X extends Throwable> void ofFalse(Boolean present, Logger logger, Supplier<X> supplier) throws X {
        ofFalseThrow(present, logger, supplier);
    }

    /**
     * <code>ofFalseError</code>
     * <p>The of false error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.util.function.Supplier
     */
    public static <X extends RestError> void ofFalseError(Boolean present, Supplier<X> supplier) {
        ofFalseThrowError(present, supplier);
    }

    /**
     * <code>ofFalseError</code>
     * <p>The of false error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see java.util.function.Supplier
     */
    public static <X extends RestError> void ofFalseError(Boolean present, Logger logger, Supplier<X> supplier) {
        ofFalseThrowError(present, logger, supplier);
    }

    /**
     * <code>ofFalseException</code>
     * <p>The of false exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     */
    public static <X extends RestException> void ofFalseException(Boolean present, SupplierActuator<X> actuator) throws RestException {
        ofFalseThrowException(present, actuator);
    }

    /**
     * <code>ofFalseException</code>
     * <p>The of false exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     */
    public static <X extends RestException> void ofFalseException(Boolean present, Logger logger, SupplierActuator<X> actuator) throws RestException {
        ofFalseThrowException(present, logger, actuator);
    }

    /**
     * <code>ofFalse</code>
     * <p>The of false method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofFalse(Boolean present, String message, Function<String, X> function) throws X {
        ofFalseThrow(present, message, function);
    }

    /**
     * <code>ofFalse</code>
     * <p>The of false method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofFalse(Boolean present, RestStatus restStatus, Function<RestStatus, X> function) throws X {
        ofFalseThrow(present, restStatus, function);
    }

    /**
     * <code>ofFalse</code>
     * <p>The of false method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofFalse(Boolean present, String message, Logger logger, Function<String, X> function) throws X {
        ofFalseThrow(present, message, logger, function);
    }

    /**
     * <code>ofFalse</code>
     * <p>The of false method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     * @see X
     */
    public static <X extends Throwable> void ofFalse(Boolean present, RestStatus restStatus, Logger logger, Function<RestStatus, X> function) throws X {
        ofFalseThrow(present, restStatus, logger, function);
    }

    /**
     * <code>ofFalseError</code>
     * <p>The of false error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofFalseError(Boolean present, String message, Function<String, X> function) {
        ofFalseThrowError(present, message, function);
    }

    /**
     * <code>ofFalseError</code>
     * <p>The of false error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofFalseError(Boolean present, RestStatus restStatus, Function<RestStatus, X> function) {
        ofFalseThrowError(present, restStatus, function);
    }

    /**
     * <code>ofFalseError</code>
     * <p>The of false error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofFalseError(Boolean present, String message, Logger logger, Function<String, X> function) {
        ofFalseThrowError(present, message, logger, function);
    }

    /**
     * <code>ofFalseError</code>
     * <p>The of false error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see java.util.function.Function
     */
    public static <X extends RestError> void ofFalseError(Boolean present, RestStatus restStatus, Logger logger, Function<RestStatus, X> function) {
        ofFalseThrowError(present, restStatus, logger, function);
    }

    /**
     * <code>ofFalseException</code>
     * <p>The of false exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofFalseException(Boolean present, String message, FunctionActuator<String, X> actuator) throws RestException {
        ofFalseThrowException(present, message, actuator);
    }

    /**
     * <code>ofFalseException</code>
     * <p>The of false exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofFalseException(Boolean present, RestStatus restStatus, FunctionActuator<RestStatus, X> actuator) throws RestException {
        ofFalseThrowException(present, restStatus, actuator);
    }

    /**
     * <code>ofFalseException</code>
     * <p>The of false exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofFalseException(Boolean present, String message, Logger logger, FunctionActuator<String, X> actuator) throws RestException {
        ofFalseThrowException(present, message, logger, actuator);
    }

    /**
     * <code>ofFalseException</code>
     * <p>The of false exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    public static <X extends RestException> void ofFalseException(Boolean present, RestStatus restStatus, Logger logger, FunctionActuator<RestStatus, X> actuator) throws RestException {
        ofFalseThrowException(present, restStatus, logger, actuator);
    }

    /**
     * <code>ofFalse</code>
     * <p>The of false method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofFalse(Boolean present, String message, String resource, BiFunction<String, String, X> function) throws X {
        ofFalseThrow(present, message, resource, function);
    }

    /**
     * <code>ofFalse</code>
     * <p>The of false method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofFalse(Boolean present, RestStatus restStatus, String resource, BiFunction<String, String, X> function) throws X {
        ofFalseThrow(present, restStatus, resource, function);
    }

    /**
     * <code>ofFalse</code>
     * <p>The of false method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofFalse(Boolean present, String message, String resource, Logger logger, BiFunction<String, String, X> function) throws X {
        ofFalseThrow(present, message, resource, logger, function);
    }

    /**
     * <code>ofFalse</code>
     * <p>The of false method.</p>
     * @param <X>        {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     * @see X
     */
    public static <X extends Throwable> void ofFalse(Boolean present, RestStatus restStatus, String resource, Logger logger, BiFunction<String, String, X> function) throws X {
        ofFalseThrow(present, restStatus, resource, logger, function);
    }

    /**
     * <code>ofFalseError</code>
     * <p>The of false error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofFalseError(Boolean present, String message, String resource, BiFunction<String, String, X> function) {
        ofFalseThrowError(present, message, resource, function);
    }

    /**
     * <code>ofFalseError</code>
     * <p>The of false error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofFalseError(Boolean present, RestStatus restStatus, String resource, BiFunction<String, String, X> function) {
        ofFalseThrowError(present, restStatus, resource, function);
    }

    /**
     * <code>ofFalseError</code>
     * <p>The of false error method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofFalseError(Boolean present, String message, String resource, Logger logger, BiFunction<String, String, X> function) {
        ofFalseThrowError(present, message, resource, logger, function);
    }

    /**
     * <code>ofFalseError</code>
     * <p>The of false error method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestError} <p>The generic parameter is <code>RestError</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param function   {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see java.util.function.BiFunction
     */
    public static <X extends RestError> void ofFalseError(Boolean present, RestStatus restStatus, String resource, Logger logger, BiFunction<String, String, X> function) {
        ofFalseThrowError(present, restStatus, resource, logger, function);
    }

    /**
     * <code>ofFalseException</code>
     * <p>The of false exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofFalseException(Boolean present, String message, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofFalseThrowException(present, message, resource, actuator);
    }

    /**
     * <code>ofFalseException</code>
     * <p>The of false exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofFalseException(Boolean present, RestStatus restStatus, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofFalseThrowException(present, restStatus, resource, actuator);
    }

    /**
     * <code>ofFalseException</code>
     * <p>The of false exception method.</p>
     * @param <X>      {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofFalseException(Boolean present, String message, String resource, Logger logger, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofFalseThrowException(present, message, resource, logger, actuator);
    }

    /**
     * <code>ofFalseException</code>
     * <p>The of false exception method.</p>
     * @param <X>        {@link io.github.nichetoolkit.rest.RestException} <p>The generic parameter is <code>RestException</code> type.</p>
     * @param present    {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param resource   {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger     {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param actuator   {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     */
    public static <X extends RestException> void ofFalseException(Boolean present, RestStatus restStatus, String resource, Logger logger, BiFunctionActuator<String, String, X> actuator) throws RestException {
        ofFalseThrowException(present, restStatus, resource, logger, actuator);
    }

    /**
     * <code>ofFieldNull</code>
     * <p>The of field null method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field T <p>The field parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldNull(T field) throws RestException {
        ofNullException(field, FieldNullException::new);
    }

    /**
     * <code>ofFieldNull</code>
     * <p>The of field null method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field  T <p>The field parameter is <code>T</code> type.</p>
     * @param logger {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldNull(T field, Logger logger) throws RestException {
        ofNullException(field, logger, FieldNullException::new);
    }

    /**
     * <code>ofFieldNull</code>
     * <p>The of field null method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field   T <p>The field parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldNull(T field, String message) throws RestException {
        ofNullException(field, message, FieldNullException::new);
    }

    /**
     * <code>ofFieldNull</code>
     * <p>The of field null method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field   T <p>The field parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldNull(T field, String message, Logger logger) throws RestException {
        ofNullException(field, message, logger, FieldNullException::new);
    }

    /**
     * <code>ofFieldNull</code>
     * <p>The of field null method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field    T <p>The field parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldNull(T field, String message, String resource) throws RestException {
        ofNullException(field, message, resource, FieldNullException::new);
    }

    /**
     * <code>ofFieldNull</code>
     * <p>The of field null method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field    T <p>The field parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldNull(T field, String message, String resource, Logger logger) throws RestException {
        ofNullException(field, message, resource, logger, FieldNullException::new);
    }

    /**
     * <code>ofFieldEmpty</code>
     * <p>The of field empty method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field T <p>The field parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldEmpty(T field) throws RestException {
        ofEmptyException(field, FieldNullException::new);
    }

    /**
     * <code>ofFieldEmpty</code>
     * <p>The of field empty method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field  T <p>The field parameter is <code>T</code> type.</p>
     * @param logger {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldEmpty(T field, Logger logger) throws RestException {
        ofEmptyException(field, logger, FieldNullException::new);
    }

    /**
     * <code>ofFieldEmpty</code>
     * <p>The of field empty method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field   T <p>The field parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldEmpty(T field, String message) throws RestException {
        ofEmptyException(field, message, FieldNullException::new);
    }

    /**
     * <code>ofFieldEmpty</code>
     * <p>The of field empty method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field   T <p>The field parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldEmpty(T field, String message, Logger logger) throws RestException {
        ofEmptyException(field, message, logger, FieldNullException::new);
    }

    /**
     * <code>ofFieldEmpty</code>
     * <p>The of field empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field    T <p>The field parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldEmpty(T field, String message, String resource) throws RestException {
        ofEmptyException(field, message, resource, FieldNullException::new);
    }

    /**
     * <code>ofFieldEmpty</code>
     * <p>The of field empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field    T <p>The field parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldEmpty(T field, String message, String resource, Logger logger) throws RestException {
        ofEmptyException(field, message, resource, logger, FieldNullException::new);
    }

    /**
     * <code>ofFieldInvalid</code>
     * <p>The of field invalid method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field T <p>The field parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldInvalid(T field) throws RestException {
        ofInvalidException(field, FieldNullException::new);
    }

    /**
     * <code>ofFieldInvalid</code>
     * <p>The of field invalid method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field  T <p>The field parameter is <code>T</code> type.</p>
     * @param logger {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldInvalid(T field, Logger logger) throws RestException {
        ofInvalidException(field, logger, FieldNullException::new);
    }

    /**
     * <code>ofFieldInvalid</code>
     * <p>The of field invalid method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field   T <p>The field parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldInvalid(T field, String message) throws RestException {
        ofInvalidException(field, message, FieldNullException::new);
    }

    /**
     * <code>ofFieldInvalid</code>
     * <p>The of field invalid method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field   T <p>The field parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldInvalid(T field, String message, Logger logger) throws RestException {
        ofInvalidException(field, message, logger, FieldNullException::new);
    }

    /**
     * <code>ofFieldInvalid</code>
     * <p>The of field invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field    T <p>The field parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldInvalid(T field, String message, String resource) throws RestException {
        ofInvalidException(field, message, resource, FieldNullException::new);
    }

    /**
     * <code>ofFieldInvalid</code>
     * <p>The of field invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param field    T <p>The field parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofFieldInvalid(T field, String message, String resource, Logger logger) throws RestException {
        ofInvalidException(field, message, resource, logger, FieldNullException::new);
    }

    /**
     * <code>ofIdNull</code>
     * <p>The of id null method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id  T <p>The id parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdNull(T id) throws RestException {
        ofNullException(id, IdentityNullException::new);
    }

    /**
     * <code>ofIdNull</code>
     * <p>The of id null method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id     T <p>The id parameter is <code>T</code> type.</p>
     * @param logger {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdNull(T id, Logger logger) throws RestException {
        ofNullException(id, logger, IdentityNullException::new);
    }


    /**
     * <code>ofIdNull</code>
     * <p>The of id null method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id      T <p>The id parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdNull(T id, String message) throws RestException {
        ofNullException(id, message, IdentityNullException::new);
    }

    /**
     * <code>ofIdNull</code>
     * <p>The of id null method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id      T <p>The id parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdNull(T id, String message, Logger logger) throws RestException {
        ofNullException(id, message, logger, IdentityNullException::new);
    }

    /**
     * <code>ofIdNull</code>
     * <p>The of id null method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id       T <p>The id parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdNull(T id, String message, String resource) throws RestException {
        ofNullException(id, message, resource, IdentityNullException::new);
    }

    /**
     * <code>ofIdNull</code>
     * <p>The of id null method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id       T <p>The id parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdNull(T id, String message, String resource, Logger logger) throws RestException {
        ofNullException(id, message, resource, logger, IdentityNullException::new);
    }

    /**
     * <code>ofIdEmpty</code>
     * <p>The of id empty method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id  T <p>The id parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdEmpty(T id) throws RestException {
        ofEmptyException(id, IdentityNullException::new);
    }

    /**
     * <code>ofIdEmpty</code>
     * <p>The of id empty method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id     T <p>The id parameter is <code>T</code> type.</p>
     * @param logger {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdEmpty(T id, Logger logger) throws RestException {
        ofEmptyException(id, IdentityNullException::new);
    }

    /**
     * <code>ofIdEmpty</code>
     * <p>The of id empty method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id      T <p>The id parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdEmpty(T id, String message) throws RestException {
        ofEmptyException(id, message, IdentityNullException::new);
    }

    /**
     * <code>ofIdEmpty</code>
     * <p>The of id empty method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id      T <p>The id parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdEmpty(T id, String message, Logger logger) throws RestException {
        ofEmptyException(id, message, logger, IdentityNullException::new);
    }

    /**
     * <code>ofIdEmpty</code>
     * <p>The of id empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id       T <p>The id parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdEmpty(T id, String message, String resource) throws RestException {
        ofEmptyException(id, message, resource, IdentityNullException::new);
    }

    /**
     * <code>ofIdEmpty</code>
     * <p>The of id empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id       T <p>The id parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdEmpty(T id, String message, String resource, Logger logger) throws RestException {
        ofEmptyException(id, message, resource, logger, IdentityNullException::new);
    }

    /**
     * <code>ofIdInvalid</code>
     * <p>The of id invalid method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id  T <p>The id parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdInvalid(T id) throws RestException {
        ofInvalidException(id, IdentityNullException::new);
    }

    /**
     * <code>ofIdInvalid</code>
     * <p>The of id invalid method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id     T <p>The id parameter is <code>T</code> type.</p>
     * @param logger {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdInvalid(T id, Logger logger) throws RestException {
        ofInvalidException(id, logger, IdentityNullException::new);
    }

    /**
     * <code>ofIdInvalid</code>
     * <p>The of id invalid method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id      T <p>The id parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdInvalid(T id, String message) throws RestException {
        ofInvalidException(id, message, IdentityNullException::new);
    }

    /**
     * <code>ofIdInvalid</code>
     * <p>The of id invalid method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id      T <p>The id parameter is <code>T</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdInvalid(T id, String message, Logger logger) throws RestException {
        ofInvalidException(id, message, logger, IdentityNullException::new);
    }

    /**
     * <code>ofIdInvalid</code>
     * <p>The of id invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id       T <p>The id parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdInvalid(T id, String message, String resource) throws RestException {
        ofInvalidException(id, message, resource, IdentityNullException::new);
    }

    /**
     * <code>ofIdInvalid</code>
     * <p>The of id invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param id       T <p>The id parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofIdInvalid(T id, String message, String resource, Logger logger) throws RestException {
        ofInvalidException(id, message, resource, logger, IdentityNullException::new);
    }

    /**
     * <code>ofCreate</code>
     * <p>The of create method.</p>
     * @param result {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofCreate(Integer result) throws RestException {
        ofInvalidException(result, DataCreateException::new);
    }

    /**
     * <code>ofCreate</code>
     * <p>The of create method.</p>
     * @param result {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param logger {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofCreate(Integer result, Logger logger) throws RestException {
        ofInvalidException(result, logger, DataCreateException::new);
    }

    /**
     * <code>ofCreate</code>
     * <p>The of create method.</p>
     * @param result  {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofCreate(Integer result, String message) throws RestException {
        ofInvalidException(result, message, DataCreateException::new);
    }

    /**
     * <code>ofCreate</code>
     * <p>The of create method.</p>
     * @param result  {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofCreate(Integer result, String message, Logger logger) throws RestException {
        ofInvalidException(result, message, logger, DataCreateException::new);
    }

    /**
     * <code>ofCreate</code>
     * <p>The of create method.</p>
     * @param result   {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofCreate(Integer result, String message, String resource) throws RestException {
        ofInvalidException(result, message, resource, DataCreateException::new);
    }

    /**
     * <code>ofCreate</code>
     * <p>The of create method.</p>
     * @param result   {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofCreate(Integer result, String message, String resource, Logger logger) throws RestException {
        ofInvalidException(result, message, resource, logger, DataCreateException::new);
    }

    /**
     * <code>ofUpdate</code>
     * <p>The of update method.</p>
     * @param result {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdate(Integer result) throws RestException {
        ofInvalidException(result, DataUpdateException::new);
    }

    /**
     * <code>ofUpdate</code>
     * <p>The of update method.</p>
     * @param result {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param logger {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdate(Integer result, Logger logger) throws RestException {
        ofInvalidException(result, logger, DataUpdateException::new);
    }

    /**
     * <code>ofUpdate</code>
     * <p>The of update method.</p>
     * @param result  {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdate(Integer result, String message) throws RestException {
        ofInvalidException(result, message, DataUpdateException::new);
    }

    /**
     * <code>ofUpdate</code>
     * <p>The of update method.</p>
     * @param result  {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdate(Integer result, String message, Logger logger) throws RestException {
        ofInvalidException(result, message, logger, DataUpdateException::new);
    }

    /**
     * <code>ofUpdate</code>
     * <p>The of update method.</p>
     * @param result   {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdate(Integer result, String message, String resource) throws RestException {
        ofInvalidException(result, message, resource, DataUpdateException::new);
    }

    /**
     * <code>ofUpdate</code>
     * <p>The of update method.</p>
     * @param result   {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdate(Integer result, String message, String resource, Logger logger) throws RestException {
        ofInvalidException(result, message, resource, logger, DataUpdateException::new);
    }

    /**
     * <code>ofSave</code>
     * <p>The of save method.</p>
     * @param result {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSave(Integer result) throws RestException {
        ofInvalidException(result, DataSaveException::new);
    }

    /**
     * <code>ofSave</code>
     * <p>The of save method.</p>
     * @param result {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param logger {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSave(Integer result, Logger logger) throws RestException {
        ofInvalidException(result, logger, DataSaveException::new);
    }

    /**
     * <code>ofSave</code>
     * <p>The of save method.</p>
     * @param result  {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSave(Integer result, String message) throws RestException {
        ofInvalidException(result, message, DataSaveException::new);
    }

    /**
     * <code>ofSave</code>
     * <p>The of save method.</p>
     * @param result  {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSave(Integer result, String message, Logger logger) throws RestException {
        ofInvalidException(result, message, logger, DataSaveException::new);
    }

    /**
     * <code>ofSave</code>
     * <p>The of save method.</p>
     * @param result   {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSave(Integer result, String message, String resource) throws RestException {
        ofInvalidException(result, message, resource, DataSaveException::new);
    }

    /**
     * <code>ofSave</code>
     * <p>The of save method.</p>
     * @param result   {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSave(Integer result, String message, String resource, Logger logger) throws RestException {
        ofInvalidException(result, message, resource, logger, DataSaveException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>The of insert all method.</p>
     * @param result {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Integer result) throws RestException {
        ofInvalidException(result, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>The of insert all method.</p>
     * @param result {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param logger {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Integer result, Logger logger) throws RestException {
        ofInvalidException(result, logger, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>The of insert all method.</p>
     * @param result  {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Integer result, String message) throws RestException {
        ofInvalidException(result, message, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>The of insert all method.</p>
     * @param result  {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Integer result, String message, Logger logger) throws RestException {
        ofInvalidException(result, message, logger, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>The of insert all method.</p>
     * @param result   {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Integer result, String message, String resource) throws RestException {
        ofInvalidException(result, message, resource, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>The of insert all method.</p>
     * @param result   {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Integer result, String message, String resource, Logger logger) throws RestException {
        ofInvalidException(result, message, resource, logger, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>The of insert all method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Boolean present) throws RestException {
        ofFalseException(present, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>The of insert all method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Boolean present, Logger logger) throws RestException {
        ofFalseException(present, logger, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>The of insert all method.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Boolean present, String message, String resource) throws RestException {
        ofFalseException(present, message, resource, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>The of insert all method.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Boolean present, String message, String resource, Logger logger) throws RestException {
        ofFalseException(present, message, resource, logger, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>The of insert all method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Boolean present, String message) throws RestException {
        ofFalseException(present, message, DataBatchInsertException::new);
    }

    /**
     * <code>ofInsertAll</code>
     * <p>The of insert all method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofInsertAll(Boolean present, String message, Logger logger) throws RestException {
        ofFalseException(present, message, logger, DataBatchInsertException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>The of update all method.</p>
     * @param result {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Integer result) throws RestException {
        ofInvalidException(result, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>The of update all method.</p>
     * @param result {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param logger {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Integer result, Logger logger) throws RestException {
        ofInvalidException(result, logger, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>The of update all method.</p>
     * @param result   {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Integer result, String message, String resource) throws RestException {
        ofInvalidException(result, message, resource, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>The of update all method.</p>
     * @param result   {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Integer result, String message, String resource, Logger logger) throws RestException {
        ofInvalidException(result, message, resource, logger, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>The of update all method.</p>
     * @param result  {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Integer result, String message) throws RestException {
        ofInvalidException(result, message, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>The of update all method.</p>
     * @param result  {@link java.lang.Integer} <p>The result parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Integer result, String message, Logger logger) throws RestException {
        ofInvalidException(result, message, logger, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>The of update all method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Boolean present) throws RestException {
        ofFalseException(present, DataBatchUpdateException::new);
    }


    /**
     * <code>ofUpdateAll</code>
     * <p>The of update all method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Boolean present, Logger logger) throws RestException {
        ofFalseException(present, logger, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>The of update all method.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Boolean present, String message, String resource) throws RestException {
        ofFalseException(present, message, resource, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>The of update all method.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Boolean present, String message, String resource, Logger logger) throws RestException {
        ofFalseException(present, message, resource, logger, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>The of update all method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Boolean present, String message) throws RestException {
        ofFalseException(present, message, DataBatchUpdateException::new);
    }

    /**
     * <code>ofUpdateAll</code>
     * <p>The of update all method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofUpdateAll(Boolean present, String message, Logger logger) throws RestException {
        ofFalseException(present, message, logger, DataBatchUpdateException::new);
    }

    /**
     * <code>ofSaveAll</code>
     * <p>The of save all method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSaveAll(Boolean present) throws RestException {
        ofFalseException(present, DataBatchSaveException::new);
    }

    /**
     * <code>ofSaveAll</code>
     * <p>The of save all method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSaveAll(Boolean present, Logger logger) throws RestException {
        ofFalseException(present, logger, DataBatchSaveException::new);
    }

    /**
     * <code>ofSaveAll</code>
     * <p>The of save all method.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSaveAll(Boolean present, String message, String resource) throws RestException {
        ofFalseException(present, message, resource, DataBatchSaveException::new);
    }

    /**
     * <code>ofSaveAll</code>
     * <p>The of save all method.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSaveAll(Boolean present, String message, String resource, Logger logger) throws RestException {
        ofFalseException(present, message, resource, logger, DataBatchSaveException::new);
    }

    /**
     * <code>ofSaveAll</code>
     * <p>The of save all method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSaveAll(Boolean present, String message) throws RestException {
        ofFalseException(present, message, DataBatchSaveException::new);
    }

    /**
     * <code>ofSaveAll</code>
     * <p>The of save all method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofSaveAll(Boolean present, String message, Logger logger) throws RestException {
        ofFalseException(present, message, logger, DataBatchSaveException::new);
    }

    /**
     * <code>ofNameRepeat</code>
     * <p>The of name repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofNameRepeat(Boolean present) throws RestException {
        ofTrueException(present, NameRepeatException::new);
    }

    /**
     * <code>ofNameRepeat</code>
     * <p>The of name repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofNameRepeat(Boolean present, Logger logger) throws RestException {
        ofTrueException(present, logger, NameRepeatException::new);
    }

    /**
     * <code>ofNameRepeat</code>
     * <p>The of name repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofNameRepeat(Boolean present, String message) throws RestException {
        ofTrueException(present, message, NameRepeatException::new);
    }

    /**
     * <code>ofNameRepeat</code>
     * <p>The of name repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofNameRepeat(Boolean present, String message, Logger logger) throws RestException {
        ofTrueException(present, message, logger, NameRepeatException::new);
    }

    /**
     * <code>ofNameRepeat</code>
     * <p>The of name repeat method.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofNameRepeat(Boolean present, String message, String resource) throws RestException {
        ofTrueException(present, message, resource, (rse, msg) -> new NameRepeatException(rse, null, msg));
    }

    /**
     * <code>ofNameRepeat</code>
     * <p>The of name repeat method.</p>
     * @param present  {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param logger   {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofNameRepeat(Boolean present, String message, String resource, Logger logger) throws RestException {
        ofTrueException(present, message, resource, logger, (rse, msg) -> new NameRepeatException(rse, null, msg));
    }

    /**
     * <code>ofFieldRepeat</code>
     * <p>The of field repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFieldRepeat(Boolean present) throws RestException {
        ofTrueException(present, FieldRepeatException::new);
    }

    /**
     * <code>ofFieldRepeat</code>
     * <p>The of field repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFieldRepeat(Boolean present, Logger logger) throws RestException {
        ofTrueException(present, logger, FieldRepeatException::new);
    }

    /**
     * <code>ofFieldRepeat</code>
     * <p>The of field repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFieldRepeat(Boolean present, String field) throws RestException {
        ofTrueException(present, field, FieldRepeatException::new);
    }

    /**
     * <code>ofFieldRepeat</code>
     * <p>The of field repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFieldRepeat(Boolean present, String field, Logger logger) throws RestException {
        ofTrueException(present, field, logger, FieldRepeatException::new);
    }

    /**
     * <code>ofFieldRepeat</code>
     * <p>The of field repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFieldRepeat(Boolean present, String field, String message) throws RestException {
        ofTrueException(present, message, field, (fld, msg) -> new FieldRepeatException(fld, null, msg));
    }

    /**
     * <code>ofFieldRepeat</code>
     * <p>The of field repeat method.</p>
     * @param present {@link java.lang.Boolean} <p>The present parameter is <code>Boolean</code> type.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param logger  {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFieldRepeat(Boolean present, String field, String message, Logger logger) throws RestException {
        ofTrueException(present, message, field, logger, (fld, msg) -> new FieldRepeatException(fld, null, msg));
    }
}
