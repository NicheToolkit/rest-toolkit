package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.actuator.*;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <code>RestOptional</code>
 * <p>The type rest optional class.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public final class RestOptional<T> {
    /**
     * <code>EMPTY</code>
     * {@link io.github.nichetoolkit.rest.RestOptional} <p>the constant <code>EMPTY</code> field.</p>
     */
    private static final RestOptional<?> EMPTY = new RestOptional<>();

    /**
     * <code>value</code>
     * <p>the <code>value</code> field.</p>
     */
    private final T value;

    /**
     * <code>RestOptional</code>
     * Instantiates a new rest optional.
     */
    private RestOptional() {
        this.value = null;
    }

    /**
     * <code>empty</code>
     * <p>the method.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>the return object is <code>RestOptional</code> type.</p>
     */
    public static <T> RestOptional<T> empty() {
        @SuppressWarnings("unchecked")
        RestOptional<T> t = (RestOptional<T>) EMPTY;
        return t;
    }

    /**
     * <code>RestOptional</code>
     * Instantiates a new rest optional.
     * @param value T <p>the value parameter is <code>T</code> type.</p>
     */
    private RestOptional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * <code>of</code>
     * <p>the method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param value T <p>the value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>the return object is <code>RestOptional</code> type.</p>
     */
    public static <T> RestOptional<T> of(T value) {
        return new RestOptional<>(value);
    }

    /**
     * <code>ofNullable</code>
     * <p>the nullable method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param value T <p>the value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>the nullable return object is <code>RestOptional</code> type.</p>
     */
    public static <T> RestOptional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    /**
     * <code>ofEmptyable</code>
     * <p>the emptyable method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param value T <p>the value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>the emptyable return object is <code>RestOptional</code> type.</p>
     */
    public static <T> RestOptional<T> ofEmptyable(T value) {
        return GeneralUtils.isEmpty(value) ? empty() : of(value);
    }

    /**
     * <code>ofValidable</code>
     * <p>the validable method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param value T <p>the value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>the validable return object is <code>RestOptional</code> type.</p>
     */
    public static <T> RestOptional<T> ofValidable(T value) {
        return GeneralUtils.isInvalid(value) ? empty() : of(value);
    }

    /**
     * <code>get</code>
     * <p>the method.</p>
     * @return T <p>the return object is <code>T</code> type.</p>
     */
    public T get() {
        return value;
    }

    /**
     * <code>nullGet</code>
     * <p>the get method.</p>
     * @return T <p>the get return object is <code>T</code> type.</p>
     */
    public T nullGet() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * <code>emptyGet</code>
     * <p>the get method.</p>
     * @return T <p>the get return object is <code>T</code> type.</p>
     */
    public T emptyGet() {
        if (GeneralUtils.isEmpty(value)) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * <code>validGet</code>
     * <p>the get method.</p>
     * @return T <p>the get return object is <code>T</code> type.</p>
     */
    public T validGet() {
        if (GeneralUtils.isInvalid(value)) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * <code>isNullPresent</code>
     * <p>the null present method.</p>
     * @return boolean <p>the null present return object is <code>boolean</code> type.</p>
     */
    public boolean isNullPresent() {
        return value == null;
    }

    /**
     * <code>isEmptyPresent</code>
     * <p>the empty present method.</p>
     * @return boolean <p>the empty present return object is <code>boolean</code> type.</p>
     */
    public boolean isEmptyPresent() {
        return !GeneralUtils.isNotEmpty(value);
    }

    /**
     * <code>isValidPresent</code>
     * <p>the valid present method.</p>
     * @return boolean <p>the valid present return object is <code>boolean</code> type.</p>
     */
    public boolean isValidPresent() {
        return !GeneralUtils.isValid(value);
    }

    /**
     * <code>ifNullPresent</code>
     * <p>the null present method.</p>
     * @param consumer {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void ifNullPresent(ConsumerActuator<? super T> consumer) throws RestException {
        if (value != null)
            consumer.actuate(value);
    }

    /**
     * <code>ifEmptyPresent</code>
     * <p>the empty present method.</p>
     * @param consumer {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void ifEmptyPresent(ConsumerActuator<? super T> consumer) throws RestException {
        if (GeneralUtils.isNotEmpty(value))
            consumer.actuate(value);
    }

    /**
     * <code>ifValidPresent</code>
     * <p>the valid present method.</p>
     * @param consumer {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void ifValidPresent(ConsumerActuator<? super T> consumer) throws RestException {
        if (GeneralUtils.isValid(value))
            consumer.actuate(value);
    }


    /**
     * <code>nullFilter</code>
     * <p>the filter method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>the filter return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> nullFilter(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (isNullPresent())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    /**
     * <code>emptyFilter</code>
     * <p>the filter method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>the filter return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> emptyFilter(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (isEmptyPresent())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    /**
     * <code>validFilter</code>
     * <p>the filter method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>the filter return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> validFilter(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (isValidPresent())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    /**
     * <code>nullMap</code>
     * <p>the map method.</p>
     * @param <U>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>the map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> nullMap(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isNullPresent())
            return empty();
        else {
            return RestOptional.ofNullable(mapper.actuate(value));
        }
    }

    /**
     * <code>emptyMap</code>
     * <p>the map method.</p>
     * @param <U>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>the map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> emptyMap(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isEmptyPresent())
            return empty();
        else {
            return RestOptional.ofNullable(mapper.actuate(value));
        }
    }

    /**
     * <code>validMap</code>
     * <p>the map method.</p>
     * @param <U>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>the map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> validMap(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isValidPresent())
            return empty();
        else {
            return RestOptional.ofNullable(mapper.actuate(value));
        }
    }

    /**
     * <code>nullFlatMap</code>
     * <p>the flat map method.</p>
     * @param <U>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>the flat map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> nullFlatMap(FunctionActuator<? super T, RestOptional<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isNullPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.actuate(value));
        }
    }

    /**
     * <code>emptyFlatMap</code>
     * <p>the flat map method.</p>
     * @param <U>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>the flat map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> emptyFlatMap(FunctionActuator<? super T, RestOptional<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isEmptyPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.actuate(value));
        }
    }

    /**
     * <code>validFlatMap</code>
     * <p>the flat map method.</p>
     * @param <U>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>the flat map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> validFlatMap(FunctionActuator<? super T, RestOptional<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isValidPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.actuate(value));
        }
    }

    /**
     * <code>nullElse</code>
     * <p>the else method.</p>
     * @param other T <p>the other parameter is <code>T</code> type.</p>
     * @return T <p>the else return object is <code>T</code> type.</p>
     */
    public T nullElse(T other) {
        return value != null ? value : other;
    }

    /**
     * <code>emptyElse</code>
     * <p>the else method.</p>
     * @param other T <p>the other parameter is <code>T</code> type.</p>
     * @return T <p>the else return object is <code>T</code> type.</p>
     */
    public T emptyElse(T other) {
        return GeneralUtils.isNotEmpty(value) ? value : other;
    }

    /**
     * <code>validElse</code>
     * <p>the else method.</p>
     * @param other T <p>the other parameter is <code>T</code> type.</p>
     * @return T <p>the else return object is <code>T</code> type.</p>
     */
    public T validElse(T other) {
        return GeneralUtils.isValid(value) ? value : other;
    }

    /**
     * <code>nullElseGet</code>
     * <p>the else get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the other parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>the else get return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T nullElseGet(SupplierActuator<? extends T> other) throws RestException {
        return value != null ? value : other.actuate();
    }

    /**
     * <code>emptyElseGet</code>
     * <p>the else get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the other parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>the else get return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T emptyElseGet(SupplierActuator<? extends T> other) throws RestException {
        return GeneralUtils.isNotEmpty(value) ? value : other.actuate();
    }

    /**
     * <code>validElseGet</code>
     * <p>the else get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the other parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>the else get return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T validElseGet(SupplierActuator<? extends T> other) throws RestException {
        return GeneralUtils.isValid(value) ? value : other.actuate();
    }


    /**
     * <code>nullElseThrow</code>
     * <p>the else throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the exception supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>the else throw return object is <code>T</code> type.</p>
     * @throws X             X <p>the x is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see X
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <X extends Throwable> T nullElseThrow(SupplierActuator<? extends X> exceptionSupplier) throws X, RestException {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.actuate();
        }
    }

    /**
     * <code>emptyElseThrow</code>
     * <p>the else throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the exception supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>the else throw return object is <code>T</code> type.</p>
     * @throws X             X <p>the x is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see X
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <X extends Throwable> T emptyElseThrow(SupplierActuator<? extends X> exceptionSupplier) throws X, RestException {
        if (GeneralUtils.isNotEmpty(value)) {
            return value;
        } else {
            throw exceptionSupplier.actuate();
        }
    }

    /**
     * <code>validElseThrow</code>
     * <p>the else throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>the generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the exception supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>the else throw return object is <code>T</code> type.</p>
     * @throws X             X <p>the x is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see X
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <X extends Throwable> T validElseThrow(SupplierActuator<? extends X> exceptionSupplier) throws X, RestException {
        if (GeneralUtils.isValid(value)) {
            return value;
        } else {
            throw exceptionSupplier.actuate();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RestOptional)) {
            return false;
        }
        RestOptional<?> other = (RestOptional<?>) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value != null
                ? String.format("Optional[%s]", value)
                : "Optional.empty";
    }
}
