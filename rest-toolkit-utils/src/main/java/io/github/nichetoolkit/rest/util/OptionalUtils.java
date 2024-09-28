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
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.Optional;

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
     * <code>xOfNull</code>
     * <p>the of null method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @return X <p>the of null return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.springframework.lang.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    private static <T, X extends Throwable> X xOfNull(@Nullable T object, @NonNull SupplierActuator<X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isNull(object)) {
            cause = actuator.actuate();
        } else if (object instanceof Optional) {
            Optional<?> optional = (Optional<?>) object;
            if (optional.isPresent()) {
                cause = actuator.actuate();
            }
        } else if (object instanceof RestOptional) {
            RestOptional<?> optional = (RestOptional<?>) object;
            if (optional.isNullPresent()) {
                cause = actuator.actuate();
            }
        }
        return cause;
    }


    /**
     * <code>xOfNull</code>
     * <p>the of null method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @return X <p>the of null return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    private static <T, X extends Throwable> X xOfNull(@Nullable T object, String message, @NonNull FunctionActuator<String, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isNull(object)) {
            cause = actuator.actuate(message);
        } else if (object instanceof Optional) {
            Optional<?> optional = (Optional<?>) object;
            if (optional.isPresent()) {
                cause = actuator.actuate(message);
            }
        } else if (object instanceof RestOptional) {
            RestOptional<?> optional = (RestOptional<?>) object;
            if (optional.isNullPresent()) {
                cause = actuator.actuate(message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfNull</code>
     * <p>the of null method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @return X <p>the of null return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    private static <T, X extends Throwable> X xOfNull(@Nullable T object, String message, String resource, @NonNull BiFunctionActuator<String, String, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isNull(object)) {
            cause = actuator.actuate(resource, message);
        } else if (object instanceof Optional) {
            Optional<?> optional = (Optional<?>) object;
            if (optional.isPresent()) {
                cause = actuator.actuate(resource, message);
            }
        } else if (object instanceof RestOptional) {
            RestOptional<?> optional = (RestOptional<?>) object;
            if (optional.isNullPresent()) {
                cause = actuator.actuate(resource, message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfEmpty</code>
     * <p>the of empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @return X <p>the of empty return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.springframework.lang.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    private static <T, X extends Throwable> X xOfEmpty(@Nullable T object, @NonNull SupplierActuator<X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isEmpty(object)) {
            cause = actuator.actuate();
        } else if (object instanceof RestOptional) {
            RestOptional<?> optional = (RestOptional<?>) object;
            if (optional.isEmptyPresent()) {
                cause = actuator.actuate();
            }
        }
        return cause;
    }

    /**
     * <code>xOfEmpty</code>
     * <p>the of empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @return X <p>the of empty return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    private static <T, X extends Throwable> X xOfEmpty(@Nullable T object, String message, @NonNull FunctionActuator<String, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isEmpty(object)) {
            cause = actuator.actuate(message);
        } else if (object instanceof RestOptional) {
            RestOptional<?> optional = (RestOptional<?>) object;
            if (optional.isEmptyPresent()) {
                cause = actuator.actuate(message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfEmpty</code>
     * <p>the of empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @return X <p>the of empty return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    private static <T, X extends Throwable> X xOfEmpty(@Nullable T object, String message, String resource, @NonNull BiFunctionActuator<String, String, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isEmpty(object)) {
            cause = actuator.actuate(resource, message);
        } else if (object instanceof RestOptional) {
            RestOptional<?> optional = (RestOptional<?>) object;
            if (optional.isEmptyPresent()) {
                cause = actuator.actuate(resource, message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfInvalid</code>
     * <p>the of invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @return X <p>the of invalid return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.springframework.lang.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    private static <T, X extends Throwable> X xOfInvalid(@Nullable T object, @NonNull SupplierActuator<X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isInvalid(object)) {
            cause = actuator.actuate();
        } else if (object instanceof RestOptional) {
            RestOptional<?> optional = (RestOptional<?>) object;
            if (optional.isValidPresent()) {
                cause = actuator.actuate();
            }
        }
        return cause;
    }

    /**
     * <code>xOfInvalid</code>
     * <p>the of invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @return X <p>the of invalid return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    private static <T, X extends Throwable> X xOfInvalid(@Nullable T object, String message, @NonNull FunctionActuator<String, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isInvalid(object)) {
            cause = actuator.actuate(message);
        } else if (object instanceof RestOptional) {
            RestOptional<?> optional = (RestOptional<?>) object;
            if (optional.isValidPresent()) {
                cause = actuator.actuate(message);
            }
        }
        return cause;
    }

    /**
     * <code>xOfInvalid</code>
     * <p>the of invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @return X <p>the of invalid return object is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    private static <T, X extends Throwable> X xOfInvalid(@Nullable T object, String message, String resource, @NonNull BiFunctionActuator<String, String, X> actuator) throws RestException {
        Objects.requireNonNull(actuator);
        X cause = null;
        if (GeneralUtils.isInvalid(object)) {
            cause = actuator.actuate(resource, message);
        } else if (object instanceof RestOptional) {
            RestOptional<?> optional = (RestOptional<?>) object;
            if (optional.isValidPresent()) {
                cause = actuator.actuate(resource, message);
            }
        }
        return cause;
    }

    /**
     * <code>ofCauseThrow</code>
     * <p>the cause throw method.</p>
     * @param <X>   {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param cause X <p>the cause parameter is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @throws X             X <p>the x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.RestException
     */
    private static <X extends Throwable> void ofCauseThrow(X cause) throws RestException, X {
        if (GeneralUtils.isNotNull(cause)) {
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrow</code>
     * <p>the true throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @throws X             X <p>the x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     * @see X
     */
    private static <X extends Throwable> void ofTrueThrow(Boolean present, SupplierActuator<X> actuator) throws RestException, X {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = actuator.actuate();
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrow</code>
     * <p>the true throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @throws X             X <p>the x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     * @see X
     */
    private static <X extends Throwable> void ofTrueThrow(Boolean present, String message, FunctionActuator<String, X> actuator) throws RestException, X {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = actuator.actuate(message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofTrueThrow</code>
     * <p>the true throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @throws X             X <p>the x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     * @see X
     */
    private static <X extends Throwable> void ofTrueThrow(Boolean present, String message, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException, X {
        if (GeneralUtils.isNotNull(present) && present) {
            X cause = actuator.actuate(resource, message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrow</code>
     * <p>the false throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @throws X             X <p>the x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     * @see X
     */
    private static <X extends Throwable> void ofFalseThrow(Boolean present, SupplierActuator<X> actuator) throws RestException, X {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = actuator.actuate();
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrow</code>
     * <p>the false throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @throws X             X <p>the x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     * @see X
     */
    private static <X extends Throwable> void ofFalseThrow(Boolean present, String message, FunctionActuator<String, X> actuator) throws RestException, X {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = actuator.actuate(message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofFalseThrow</code>
     * <p>the false throw method.</p>
     * @param <X>      {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @throws X             X <p>the x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     * @see X
     */
    private static <X extends Throwable> void ofFalseThrow(Boolean present, String message, String resource, BiFunctionActuator<String, String, X> actuator) throws RestException, X {
        if (GeneralUtils.isNotNull(present) && !present) {
            X cause = actuator.actuate(resource, message);
            log.error(cause.getMessage());
            throw cause;
        }
    }

    /**
     * <code>ofNull</code>
     * <p>the null method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNull(@Nullable T object, @NonNull SupplierActuator<RestException> actuator) throws RestException {
        ofCauseThrow(xOfNull(object, actuator));
    }

    /**
     * <code>ofEmpty</code>
     * <p>the empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmpty(@Nullable T object, @NonNull SupplierActuator<RestException> actuator) throws RestException {
        ofCauseThrow(xOfEmpty(object, actuator));
    }

    /**
     * <code>ofInvalid</code>
     * <p>the invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalid(@Nullable T object, @NonNull SupplierActuator<RestException> actuator) throws RestException {
        ofCauseThrow(xOfInvalid(object, actuator));
    }

    /**
     * <code>ofNullError</code>
     * <p>the null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNullError(@Nullable T object, @NonNull SupplierActuator<RestError> actuator) throws RestException {
        ofCauseThrow(xOfNull(object, actuator));
    }

    /**
     * <code>ofEmptyError</code>
     * <p>the empty error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmptyError(@Nullable T object, @NonNull SupplierActuator<RestError> actuator) throws RestException {
        ofCauseThrow(xOfEmpty(object, actuator));
    }

    /**
     * <code>ofInvalidError</code>
     * <p>the invalid error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalidError(@Nullable T object, @NonNull SupplierActuator<RestError> actuator) throws RestException {
        ofCauseThrow(xOfInvalid(object, actuator));
    }

    /**
     * <code>ofNull</code>
     * <p>the null method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNull(@Nullable T object, String message, FunctionActuator<String, RestException> actuator) throws RestException {
        ofCauseThrow(xOfNull(object, message, actuator));
    }

    /**
     * <code>ofEmpty</code>
     * <p>the empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmpty(@Nullable T object, String message, FunctionActuator<String, RestException> actuator) throws RestException {
        ofCauseThrow(xOfEmpty(object, message, actuator));
    }

    /**
     * <code>ofInvalid</code>
     * <p>the invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalid(@Nullable T object, String message, FunctionActuator<String, RestException> actuator) throws RestException {
        ofCauseThrow(xOfInvalid(object, message, actuator));
    }

    /**
     * <code>ofNullError</code>
     * <p>the null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNullError(@Nullable T object, String message, FunctionActuator<String, RestError> actuator) throws RestException {
        ofCauseThrow(xOfNull(object, message, actuator));
    }

    /**
     * <code>ofEmptyError</code>
     * <p>the empty error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmptyError(@Nullable T object, String message, FunctionActuator<String, RestError> actuator) throws RestException {
        ofCauseThrow(xOfEmpty(object, message, actuator));
    }

    /**
     * <code>ofInvalidError</code>
     * <p>the invalid error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalidError(@Nullable T object, String message, FunctionActuator<String, RestError> actuator) throws RestException {
        ofCauseThrow(xOfInvalid(object, message, actuator));
    }

    /**
     * <code>ofNull</code>
     * <p>the null method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNull(@Nullable T object, String message, String resource, BiFunctionActuator<String, String, RestException> actuator) throws RestException {
        ofCauseThrow(xOfNull(object, message, resource, actuator));
    }

    /**
     * <code>ofEmpty</code>
     * <p>the empty method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmpty(@Nullable T object, String message, String resource, BiFunctionActuator<String, String, RestException> actuator) throws RestException {
        ofCauseThrow(xOfEmpty(object, message, resource, actuator));
    }

    /**
     * <code>ofInvalid</code>
     * <p>the invalid method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalid(@Nullable T object, String message, String resource, BiFunctionActuator<String, String, RestException> actuator) throws RestException {
        ofCauseThrow(xOfInvalid(object, message, resource, actuator));
    }

    /**
     * <code>ofNullError</code>
     * <p>the null error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofNullError(@Nullable T object, String message, String resource, BiFunctionActuator<String, String, RestError> actuator) throws RestException {
        ofCauseThrow(xOfNull(object, message, resource, actuator));
    }

    /**
     * <code>ofEmptyError</code>
     * <p>the empty error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofEmptyError(@Nullable T object, String message, String resource, BiFunctionActuator<String, String, RestError> actuator) throws RestException {
        ofCauseThrow(xOfEmpty(object, message, resource, actuator));
    }

    /**
     * <code>ofInvalidError</code>
     * <p>the invalid error method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param object   T <p>the object parameter is <code>T</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.Nullable
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> void ofInvalidError(@Nullable T object, String message, String resource, BiFunctionActuator<String, String, RestError> actuator) throws RestException {
        ofCauseThrow(xOfInvalid(object, message, resource, actuator));
    }

    /**
     * <code>ofTrue</code>
     * <p>the true method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofTrue(Boolean present, SupplierActuator<RestException> actuator) throws RestException {
        ofTrueThrow(present, actuator);
    }

    /**
     * <code>ofTrue</code>
     * <p>the true method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofTrue(Boolean present, String message, FunctionActuator<String, RestException> actuator) throws RestException {
        ofTrueThrow(present, message, actuator);
    }

    /**
     * <code>ofTrue</code>
     * <p>the true method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofTrue(Boolean present, String message, String resource, BiFunctionActuator<String, String, RestException> actuator) throws RestException {
        ofTrueThrow(present, message, resource, actuator);
    }

    /**
     * <code>ofTrueError</code>
     * <p>the true error method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofTrueError(Boolean present, SupplierActuator<RestError> actuator) throws RestException {
        ofTrueThrow(present, actuator);
    }

    /**
     * <code>ofTrueError</code>
     * <p>the true error method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofTrueError(Boolean present, String message, FunctionActuator<String, RestError> actuator) throws RestException {
        ofTrueThrow(present, message, actuator);
    }

    /**
     * <code>ofTrueError</code>
     * <p>the true error method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofTrueError(Boolean present, String message, String resource, BiFunctionActuator<String, String, RestError> actuator) throws RestException {
        ofTrueThrow(present, message, resource, actuator);
    }

    /**
     * <code>ofFalse</code>
     * <p>the false method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFalse(Boolean present, SupplierActuator<RestException> actuator) throws RestException {
        ofFalseThrow(present, actuator);
    }

    /**
     * <code>ofFalse</code>
     * <p>the false method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFalse(Boolean present, String message, FunctionActuator<String, RestException> actuator) throws RestException {
        ofFalseThrow(present, message, actuator);
    }

    /**
     * <code>ofFalse</code>
     * <p>the false method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFalse(Boolean present, String message, String resource, BiFunctionActuator<String, String, RestException> actuator) throws RestException {
        ofFalseThrow(present, message, resource, actuator);
    }

    /**
     * <code>ofFalseError</code>
     * <p>the false error method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the actuator parameter is <code>SupplierActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFalseError(Boolean present, SupplierActuator<RestError> actuator) throws RestException {
        ofFalseThrow(present, actuator);
    }

    /**
     * <code>ofFalseError</code>
     * <p>the false error method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the actuator parameter is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFalseError(Boolean present, String message, FunctionActuator<String, RestError> actuator) throws RestException {
        ofFalseThrow(present, message, actuator);
    }

    /**
     * <code>ofFalseError</code>
     * <p>the false error method.</p>
     * @param present  {@link java.lang.Boolean} <p>the present parameter is <code>Boolean</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param actuator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the actuator parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static void ofFalseError(Boolean present, String message, String resource, BiFunctionActuator<String, String, RestError> actuator) throws RestException {
        ofFalseThrow(present, message, resource, actuator);
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
