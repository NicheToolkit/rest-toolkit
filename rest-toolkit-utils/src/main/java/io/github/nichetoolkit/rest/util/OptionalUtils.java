package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.*;
import io.github.nichetoolkit.rest.error.data.*;
import io.github.nichetoolkit.rest.error.often.FieldNullException;
import io.github.nichetoolkit.rest.error.often.FieldRepeatException;
import io.github.nichetoolkit.rest.error.often.IdentityNullException;
import io.github.nichetoolkit.rest.error.often.NameRepeatException;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * <code>OptionalUtils</code>
 * <p>The type optional utils class.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public final class OptionalUtils<T> {
    /**
     * <code>EMPTY</code>
     * {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the constant <code>EMPTY</code> field.</p>
     */
    private static final OptionalUtils<?> EMPTY = new OptionalUtils<>();

    /**
     * <code>value</code>
     * <p>the <code>value</code> field.</p>
     */
    private final T value;

    /**
     * <code>OptionalUtils</code>
     * Instantiates a new optional utils.
     */
    private OptionalUtils() {
        this.value = null;
    }

    /**
     * <code>empty</code>
     * <p>the method.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the return object is <code>OptionalUtils</code> type.</p>
     */
    public static<T> OptionalUtils<T> empty() {
        @SuppressWarnings("unchecked")
        OptionalUtils<T> t = (OptionalUtils<T>) EMPTY;
        return t;
    }

    /**
     * <code>OptionalUtils</code>
     * Instantiates a new optional utils.
     * @param value T <p>the value parameter is <code>T</code> type.</p>
     */
    private OptionalUtils(T value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * <code>of</code>
     * <p>the method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param value T <p>the value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the return object is <code>OptionalUtils</code> type.</p>
     */
    public static <T> OptionalUtils<T> of(T value) {
        return new OptionalUtils<>(value);
    }

    /**
     * <code>ofNullable</code>
     * <p>the nullable method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param value T <p>the value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the nullable return object is <code>OptionalUtils</code> type.</p>
     */
    public static <T> OptionalUtils<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    /**
     * <code>ofEmptyable</code>
     * <p>the emptyable method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param value T <p>the value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the emptyable return object is <code>OptionalUtils</code> type.</p>
     */
    public static <T> OptionalUtils<T> ofEmptyable(T value) {
        return GeneralUtils.isEmpty(value) ? empty() : of(value);
    }

    /**
     * <code>ofValidable</code>
     * <p>the validable method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param value T <p>the value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the validable return object is <code>OptionalUtils</code> type.</p>
     */
    public static <T> OptionalUtils<T> ofValidable(T value) {
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
        return value != null;
    }

    /**
     * <code>isEmptyPresent</code>
     * <p>the empty present method.</p>
     * @return boolean <p>the empty present return object is <code>boolean</code> type.</p>
     */
    public boolean isEmptyPresent() {
        return GeneralUtils.isNotEmpty(value);
    }

    /**
     * <code>isValidPresent</code>
     * <p>the valid present method.</p>
     * @return boolean <p>the valid present return object is <code>boolean</code> type.</p>
     */
    public boolean isValidPresent() {
        return GeneralUtils.isValid(value);
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
     * @return {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the filter return object is <code>OptionalUtils</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public OptionalUtils<T> nullFilter(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (!isNullPresent())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    /**
     * <code>emptyFilter</code>
     * <p>the filter method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the filter return object is <code>OptionalUtils</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public OptionalUtils<T> emptyFilter(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (!isEmptyPresent())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    /**
     * <code>validFilter</code>
     * <p>the filter method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the filter return object is <code>OptionalUtils</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public OptionalUtils<T> validFilter(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (!isValidPresent())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    /**
     * <code>nullMap</code>
     * <p>the map method.</p>
     * @param <U>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the map return object is <code>OptionalUtils</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public<U> OptionalUtils<U> nullMap(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (!isNullPresent())
            return empty();
        else {
            return OptionalUtils.ofNullable(mapper.actuate(value));
        }
    }

    /**
     * <code>emptyMap</code>
     * <p>the map method.</p>
     * @param <U>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the map return object is <code>OptionalUtils</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public<U> OptionalUtils<U> emptyMap(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (!isEmptyPresent())
            return empty();
        else {
            return OptionalUtils.ofNullable(mapper.actuate(value));
        }
    }

    /**
     * <code>validMap</code>
     * <p>the map method.</p>
     * @param <U>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the map return object is <code>OptionalUtils</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public<U> OptionalUtils<U> validMap(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (!isValidPresent())
            return empty();
        else {
            return OptionalUtils.ofNullable(mapper.actuate(value));
        }
    }

    /**
     * <code>nullFlatMap</code>
     * <p>the flat map method.</p>
     * @param <U>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the flat map return object is <code>OptionalUtils</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public<U> OptionalUtils<U> nullFlatMap(FunctionActuator<? super T, OptionalUtils<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (!isNullPresent())
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
     * @return {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the flat map return object is <code>OptionalUtils</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public<U> OptionalUtils<U> emptyFlatMap(FunctionActuator<? super T, OptionalUtils<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (!isEmptyPresent())
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
     * @return {@link io.github.nichetoolkit.rest.util.OptionalUtils} <p>the flat map return object is <code>OptionalUtils</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public<U> OptionalUtils<U> validFlatMap(FunctionActuator<? super T, OptionalUtils<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (!isValidPresent())
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
    public static <T> void nullable(T object, String message, FunctionActuator<String,RestException> functionActuator) throws RestException {
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
    public static <T> void nullable(T object, String message,String field, BiFunctionActuator<String,String,RestException> biFunctionActuator) throws RestException {
        if (GeneralUtils.isEmpty(object)) {
            RestException restException = biFunctionActuator.actuate(field,message);
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
    public static void trueable(Boolean object, String message, FunctionActuator<String,RestException> functionActuator) throws RestException {
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
    public static void trueable(Boolean object, String message, String field, BiFunctionActuator<String,String,RestException> biFunctionActuator) throws RestException {
        if (GeneralUtils.isNotEmpty(object) && object) {
            RestException restException = biFunctionActuator.actuate(field,message);
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
    public static void falseable(Boolean object, String message, FunctionActuator<String,RestException> functionActuator) throws RestException {
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
    public static void falseable(Boolean object, String message,String field, BiFunctionActuator<String,String,RestException> biFunctionActuator) throws RestException {
        if (GeneralUtils.isNotEmpty(object) && !object) {
            RestException restException = biFunctionActuator.actuate(field,message);
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
    public static void nullResult(Integer result, String message, FunctionActuator<String,RestException> functionActuator) throws RestException {
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
        nullable(object,message,FieldNullException::new);
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
        nullable(object,message,field,FieldNullException::new);
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
        nullable(id,message,IdentityNullException::new);
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
        nullable(id,message,field,IdentityNullException::new);
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
        trueable(exist,message,NameRepeatException::new);
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
        trueable(exist,message,resource,(resource1,message1) -> new NameRepeatException(resource1, null,message1));
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
        trueable(exist,field,FieldRepeatException::new);
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
        trueable(exist,message,field,(field1,message1) -> new FieldRepeatException(field1, null,message1));
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof OptionalUtils)) {
            return false;
        }

        OptionalUtils<?> other = (OptionalUtils<?>) obj;
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
