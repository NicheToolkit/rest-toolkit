package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.actuator.*;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * <code>RestOptional</code>
 * <p>The type rest optional class.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public final class RestOptional<T> {
    /**
     * <code>EMPTY</code>
     * {@link io.github.nichetoolkit.rest.RestOptional} <p>The constant <code>EMPTY</code> field.</p>
     */
    private static final RestOptional<?> EMPTY = new RestOptional<>();

    /**
     * <code>value</code>
     * <p>The <code>value</code> field.</p>
     */
    private final T value;

    /**
     * <code>RestOptional</code>
     * <p>Instantiates a new rest optional.</p>
     */
    private RestOptional() {
        this.value = null;
    }

    /**
     * <code>empty</code>
     * <p>The method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The return object is <code>RestOptional</code> type.</p>
     */
    public static <T> RestOptional<T> empty() {
        @SuppressWarnings("unchecked")
        RestOptional<T> t = (RestOptional<T>) EMPTY;
        return t;
    }

    /**
     * <code>RestOptional</code>
     * <p>Instantiates a new rest optional.</p>
     * @param value T <p>The value parameter is <code>T</code> type.</p>
     */
    private RestOptional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * <code>of</code>
     * <p>The method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value T <p>The value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The return object is <code>RestOptional</code> type.</p>
     */
    public static <T> RestOptional<T> of(T value) {
        return new RestOptional<>(value);
    }

    /**
     * <code>ofNullable</code>
     * <p>The nullable method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value T <p>The value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The nullable return object is <code>RestOptional</code> type.</p>
     */
    public static <T> RestOptional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    /**
     * <code>ofEmptyable</code>
     * <p>The emptyable method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value T <p>The value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The emptyable return object is <code>RestOptional</code> type.</p>
     */
    public static <T> RestOptional<T> ofEmptyable(T value) {
        return GeneralUtils.isEmpty(value) ? empty() : of(value);
    }

    /**
     * <code>ofValidable</code>
     * <p>The validable method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value T <p>The value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The validable return object is <code>RestOptional</code> type.</p>
     */
    public static <T> RestOptional<T> ofValidable(T value) {
        return GeneralUtils.isInvalid(value) ? empty() : of(value);
    }

    /**
     * <code>get</code>
     * <p>The method.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     */
    public T get() {
        return value;
    }

    /**
     * <code>nullGet</code>
     * <p>The get method.</p>
     * @return T <p>The get return object is <code>T</code> type.</p>
     */
    public T nullGet() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * <code>emptyGet</code>
     * <p>The get method.</p>
     * @return T <p>The get return object is <code>T</code> type.</p>
     */
    public T emptyGet() {
        if (GeneralUtils.isEmpty(value)) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * <code>validGet</code>
     * <p>The get method.</p>
     * @return T <p>The get return object is <code>T</code> type.</p>
     */
    public T validGet() {
        if (GeneralUtils.isInvalid(value)) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * <code>isPresent</code>
     * <p>The present method.</p>
     * @return boolean <p>The present return object is <code>boolean</code> type.</p>
     */
    public boolean isPresent() {
        return isNullPresent();
    }

    /**
     * <code>isNullPresent</code>
     * <p>The null present method.</p>
     * @return boolean <p>The null present return object is <code>boolean</code> type.</p>
     */
    public boolean isNullPresent() {
        return GeneralUtils.isNull(value);
    }

    /**
     * <code>isEmptyPresent</code>
     * <p>The empty present method.</p>
     * @return boolean <p>The empty present return object is <code>boolean</code> type.</p>
     */
    public boolean isEmptyPresent() {
        return GeneralUtils.isEmpty(value);
    }

    /**
     * <code>isValidPresent</code>
     * <p>The valid present method.</p>
     * @return boolean <p>The valid present return object is <code>boolean</code> type.</p>
     */
    public boolean isValidPresent() {
        return GeneralUtils.isInvalid(value);
    }

    /**
     * <code>ifPresent</code>
     * <p>The present method.</p>
     * @param consumer {@link java.util.function.Consumer} <p>The consumer parameter is <code>Consumer</code> type.</p>
     * @see java.util.function.Consumer
     */
    public void ifPresent(Consumer<? super T> consumer) {
        if (GeneralUtils.isNotNull(value))
            consumer.accept(value);
    }

    /**
     * <code>ifNullPresent</code>
     * <p>The null present method.</p>
     * @param consumer {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void ifNullPresent(ConsumerActuator<? super T> consumer) throws RestException {
        if (GeneralUtils.isNotNull(value))
            consumer.actuate(value);
    }

    /**
     * <code>ifEmptyPresent</code>
     * <p>The empty present method.</p>
     * @param consumer {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void ifEmptyPresent(ConsumerActuator<? super T> consumer) throws RestException {
        if (GeneralUtils.isNotEmpty(value))
            consumer.actuate(value);
    }

    /**
     * <code>ifValidPresent</code>
     * <p>The valid present method.</p>
     * @param consumer {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void ifValidPresent(ConsumerActuator<? super T> consumer) throws RestException {
        if (GeneralUtils.isValid(value))
            consumer.actuate(value);
    }

    /**
     * <code>filter</code>
     * <p>The method.</p>
     * @param predicate {@link java.util.function.Predicate} <p>The predicate parameter is <code>Predicate</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Predicate
     */
    public RestOptional<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (isPresent())
            return this;
        else
            return predicate.test(value) ? this : empty();
    }

    /**
     * <code>nullFilter</code>
     * <p>The filter method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The filter return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <p>The filter method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The filter return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <p>The filter method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The filter return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <code>map</code>
     * <p>The method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link java.util.function.Function} <p>The mapper parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Function
     */
    public <U> RestOptional<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (isPresent())
            return empty();
        else {
            return RestOptional.ofNullable(mapper.apply(value));
        }
    }

    /**
     * <code>nullMap</code>
     * <p>The map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <p>The map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <p>The map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <code>flatMap</code>
     * <p>The map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link java.util.function.Function} <p>The mapper parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The map return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Function
     */
    public <U> RestOptional<U> flatMap(Function<? super T, RestOptional<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (isPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }

    /**
     * <code>nullFlatMap</code>
     * <p>The flat map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The flat map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <p>The flat map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The flat map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <p>The flat map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The flat map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <code>orElse</code>
     * <p>The else method.</p>
     * @param other T <p>The other parameter is <code>T</code> type.</p>
     * @return T <p>The else return object is <code>T</code> type.</p>
     */
    public T orElse(T other) {
        return value != null ? value : other;
    }

    /**
     * <code>nullElse</code>
     * <p>The else method.</p>
     * @param other T <p>The other parameter is <code>T</code> type.</p>
     * @return T <p>The else return object is <code>T</code> type.</p>
     */
    public T nullElse(T other) {
        return value != null ? value : other;
    }

    /**
     * <code>emptyElse</code>
     * <p>The else method.</p>
     * @param other T <p>The other parameter is <code>T</code> type.</p>
     * @return T <p>The else return object is <code>T</code> type.</p>
     */
    public T emptyElse(T other) {
        return GeneralUtils.isNotEmpty(value) ? value : other;
    }

    /**
     * <code>validElse</code>
     * <p>The else method.</p>
     * @param other T <p>The other parameter is <code>T</code> type.</p>
     * @return T <p>The else return object is <code>T</code> type.</p>
     */
    public T validElse(T other) {
        return GeneralUtils.isValid(value) ? value : other;
    }

    /**
     * <code>orElseGet</code>
     * <p>The else get method.</p>
     * @param other {@link java.util.function.Supplier} <p>The other parameter is <code>Supplier</code> type.</p>
     * @return T <p>The else get return object is <code>T</code> type.</p>
     * @see java.util.function.Supplier
     */
    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

    /**
     * <code>nullElseGet</code>
     * <p>The else get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The other parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The else get return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T nullElseGet(SupplierActuator<? extends T> other) throws RestException {
        return GeneralUtils.isNotNull(value) ? value : other.actuate();
    }

    /**
     * <code>emptyElseGet</code>
     * <p>The else get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The other parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The else get return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T emptyElseGet(SupplierActuator<? extends T> other) throws RestException {
        return GeneralUtils.isNotEmpty(value) ? value : other.actuate();
    }

    /**
     * <code>validElseGet</code>
     * <p>The else get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The other parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The else get return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T validElseGet(SupplierActuator<? extends T> other) throws RestException {
        return GeneralUtils.isValid(value) ? value : other.actuate();
    }

    /**
     * <code>orElseThrow</code>
     * <p>The else throw method.</p>
     * @param <X>       {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exception {@link java.util.function.Supplier} <p>The exception parameter is <code>Supplier</code> type.</p>
     * @return T <p>The else throw return object is <code>T</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.util.function.Supplier
     * @see X
     */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exception) throws X {
        if (value != null) {
            return value;
        } else {
            throw exception.get();
        }
    }

    /**
     * <code>nullElseThrow</code>
     * <p>The else throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The exception supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The else throw return object is <code>T</code> type.</p>
     * @throws X             X <p>The x is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <p>The else throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The exception supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The else throw return object is <code>T</code> type.</p>
     * @throws X             X <p>The x is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <p>The else throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The exception supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The else throw return object is <code>T</code> type.</p>
     * @throws X             X <p>The x is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
