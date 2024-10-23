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
 * <p>The rest optional class.</p>
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
     * <p>The empty method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The empty return object is <code>RestOptional</code> type.</p>
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
     * <p>The of method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value T <p>The value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The of return object is <code>RestOptional</code> type.</p>
     */
    public static <T> RestOptional<T> of(T value) {
        return new RestOptional<>(value);
    }

    /**
     * <code>ofNullable</code>
     * <p>The of nullable method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value T <p>The value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The of nullable return object is <code>RestOptional</code> type.</p>
     */
    public static <T> RestOptional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    /**
     * <code>ofEmptyable</code>
     * <p>The of emptyable method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value T <p>The value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The of emptyable return object is <code>RestOptional</code> type.</p>
     */
    public static <T> RestOptional<T> ofEmptyable(T value) {
        return GeneralUtils.isEmpty(value) ? empty() : of(value);
    }

    /**
     * <code>ofValidable</code>
     * <p>The of validable method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value T <p>The value parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The of validable return object is <code>RestOptional</code> type.</p>
     */
    public static <T> RestOptional<T> ofValidable(T value) {
        return GeneralUtils.isInvalid(value) ? empty() : of(value);
    }

    /**
     * <code>get</code>
     * <p>The get method.</p>
     * @return T <p>The get return object is <code>T</code> type.</p>
     */
    public T get() {
        return value;
    }

    /**
     * <code>nullGet</code>
     * <p>The null get method.</p>
     * @return T <p>The null get return object is <code>T</code> type.</p>
     */
    public T nullGet() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * <code>emptyGet</code>
     * <p>The empty get method.</p>
     * @return T <p>The empty get return object is <code>T</code> type.</p>
     */
    public T emptyGet() {
        if (GeneralUtils.isEmpty(value)) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * <code>validGet</code>
     * <p>The valid get method.</p>
     * @return T <p>The valid get return object is <code>T</code> type.</p>
     */
    public T validGet() {
        if (GeneralUtils.isInvalid(value)) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * <code>isPresent</code>
     * <p>The is present method.</p>
     * @return boolean <p>The is present return object is <code>boolean</code> type.</p>
     */
    public boolean isPresent() {
        return isNullPresent();
    }

    /**
     * <code>isNull</code>
     * <p>The is null method.</p>
     * @return boolean <p>The is null return object is <code>boolean</code> type.</p>
     */
    public boolean isNull() {
        return GeneralUtils.isNull(value);
    }

    /**
     * <code>isEmpty</code>
     * <p>The is empty method.</p>
     * @return boolean <p>The is empty return object is <code>boolean</code> type.</p>
     */
    public boolean isEmpty() {
        return GeneralUtils.isEmpty(value);
    }

    /**
     * <code>isInvalid</code>
     * <p>The is invalid method.</p>
     * @return boolean <p>The is invalid return object is <code>boolean</code> type.</p>
     */
    public boolean isInvalid() {
        return GeneralUtils.isInvalid(value);
    }

    /**
     * <code>isNullPresent</code>
     * <p>The is null present method.</p>
     * @return boolean <p>The is null present return object is <code>boolean</code> type.</p>
     */
    public boolean isNullPresent() {
        return GeneralUtils.isNotNull(value);
    }

    /**
     * <code>isEmptyPresent</code>
     * <p>The is empty present method.</p>
     * @return boolean <p>The is empty present return object is <code>boolean</code> type.</p>
     */
    public boolean isEmptyPresent() {
        return GeneralUtils.isNotEmpty(value);
    }

    /**
     * <code>isValidPresent</code>
     * <p>The is valid present method.</p>
     * @return boolean <p>The is valid present return object is <code>boolean</code> type.</p>
     */
    public boolean isValidPresent() {
        return GeneralUtils.isValid(value);
    }

    /**
     * <code>ifPresent</code>
     * <p>The if present method.</p>
     * @param consumer {@link java.util.function.Consumer} <p>The consumer parameter is <code>Consumer</code> type.</p>
     * @see java.util.function.Consumer
     */
    public void ifPresent(Consumer<? super T> consumer) {
        if (isPresent())
            consumer.accept(value);
    }

    /**
     * <code>ifNullPresent</code>
     * <p>The if null present method.</p>
     * @param consumer {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void ifNullPresent(ConsumerActuator<? super T> consumer) throws RestException {
        if (isNullPresent())
            consumer.actuate(value);
    }

    /**
     * <code>ifEmptyPresent</code>
     * <p>The if empty present method.</p>
     * @param consumer {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void ifEmptyPresent(ConsumerActuator<? super T> consumer) throws RestException {
        if (isEmptyPresent())
            consumer.actuate(value);
    }

    /**
     * <code>ifValidPresent</code>
     * <p>The if valid present method.</p>
     * @param consumer {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void ifValidPresent(ConsumerActuator<? super T> consumer) throws RestException {
        if (isValidPresent())
            consumer.actuate(value);
    }

    /**
     * <code>filter</code>
     * <p>The filter method.</p>
     * @param predicate {@link java.util.function.Predicate} <p>The predicate parameter is <code>Predicate</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The filter return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Predicate
     */
    public RestOptional<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (isNull())
            return this;
        else
            return predicate.test(value) ? this : empty();
    }

    /**
     * <code>nullFilter</code>
     * <p>The null filter method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The null filter return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> nullFilter(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (isNull())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    /**
     * <code>emptyFilter</code>
     * <p>The empty filter method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The empty filter return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> emptyFilter(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (isEmpty())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    /**
     * <code>validFilter</code>
     * <p>The valid filter method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The valid filter return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> validFilter(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (isInvalid())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    /**
     * <code>map</code>
     * <p>The map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link java.util.function.Function} <p>The mapper parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The map return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Function
     */
    public <U> RestOptional<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (isNull())
            return empty();
        else {
            return RestOptional.ofNullable(mapper.apply(value));
        }
    }

    /**
     * <code>nullMap</code>
     * <p>The null map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The null map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> nullMap(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isNull())
            return empty();
        else {
            return RestOptional.ofNullable(mapper.actuate(value));
        }
    }

    /**
     * <code>emptyMap</code>
     * <p>The empty map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The empty map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> emptyMap(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isEmpty())
            return empty();
        else {
            return RestOptional.ofNullable(mapper.actuate(value));
        }
    }

    /**
     * <code>validMap</code>
     * <p>The valid map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The valid map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> validMap(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isInvalid())
            return empty();
        else {
            return RestOptional.ofNullable(mapper.actuate(value));
        }
    }

    /**
     * <code>flatMap</code>
     * <p>The flat map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link java.util.function.Function} <p>The mapper parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The flat map return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Function
     */
    public <U> RestOptional<U> flatMap(Function<? super T, RestOptional<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (isNull())
            return empty();
        else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }

    /**
     * <code>nullFlatMap</code>
     * <p>The null flat map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The null flat map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> nullFlatMap(FunctionActuator<? super T, RestOptional<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isNull())
            return empty();
        else {
            return Objects.requireNonNull(mapper.actuate(value));
        }
    }

    /**
     * <code>emptyFlatMap</code>
     * <p>The empty flat map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The empty flat map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> emptyFlatMap(FunctionActuator<? super T, RestOptional<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isEmpty())
            return empty();
        else {
            return Objects.requireNonNull(mapper.actuate(value));
        }
    }

    /**
     * <code>validFlatMap</code>
     * <p>The valid flat map method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The valid flat map return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> validFlatMap(FunctionActuator<? super T, RestOptional<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isInvalid())
            return empty();
        else {
            return Objects.requireNonNull(mapper.actuate(value));
        }
    }


    /**
     * <code>orElse</code>
     * <p>The or else method.</p>
     * @param other T <p>The other parameter is <code>T</code> type.</p>
     * @return T <p>The or else return object is <code>T</code> type.</p>
     */
    public T orElse(T other) {
        return isPresent() ? value : other;
    }

    /**
     * <code>nullElse</code>
     * <p>The null else method.</p>
     * @param other T <p>The other parameter is <code>T</code> type.</p>
     * @return T <p>The null else return object is <code>T</code> type.</p>
     */
    public T nullElse(T other) {
        return isNullPresent() ? value : other;
    }

    /**
     * <code>emptyElse</code>
     * <p>The empty else method.</p>
     * @param other T <p>The other parameter is <code>T</code> type.</p>
     * @return T <p>The empty else return object is <code>T</code> type.</p>
     */
    public T emptyElse(T other) {
        return isEmptyPresent() ? value : other;
    }

    /**
     * <code>validElse</code>
     * <p>The valid else method.</p>
     * @param other T <p>The other parameter is <code>T</code> type.</p>
     * @return T <p>The valid else return object is <code>T</code> type.</p>
     */
    public T validElse(T other) {
        return isValidPresent() ? value : other;
    }

    /**
     * <code>orElseGet</code>
     * <p>The or else get method.</p>
     * @param other {@link java.util.function.Supplier} <p>The other parameter is <code>Supplier</code> type.</p>
     * @return T <p>The or else get return object is <code>T</code> type.</p>
     * @see java.util.function.Supplier
     */
    public T orElseGet(Supplier<? extends T> other) {
        return isPresent() ? value : other.get();
    }

    /**
     * <code>nullElseGet</code>
     * <p>The null else get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The other parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The null else get return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T nullElseGet(SupplierActuator<? extends T> other) throws RestException {
        return isNullPresent() ? value : other.actuate();
    }

    /**
     * <code>emptyElseGet</code>
     * <p>The empty else get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The other parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The empty else get return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T emptyElseGet(SupplierActuator<? extends T> other) throws RestException {
        return isEmptyPresent() ? value : other.actuate();
    }

    /**
     * <code>validElseGet</code>
     * <p>The valid else get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The other parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The valid else get return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T validElseGet(SupplierActuator<? extends T> other) throws RestException {
        return isValidPresent() ? value : other.actuate();
    }

    /**
     * <code>orElseThrow</code>
     * <p>The or else throw method.</p>
     * @param <X>       {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exception {@link java.util.function.Supplier} <p>The exception parameter is <code>Supplier</code> type.</p>
     * @return T <p>The or else throw return object is <code>T</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.util.function.Supplier
     * @see X
     */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exception) throws X {
        if (isPresent()) {
            return value;
        } else {
            throw exception.get();
        }
    }

    /**
     * <code>nullElseThrow</code>
     * <p>The null else throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The exception supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The null else throw return object is <code>T</code> type.</p>
     * @throws X             X <p>The x is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see X
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <X extends Throwable> T nullElseThrow(SupplierActuator<? extends X> exceptionSupplier) throws X, RestException {
        if (isNullPresent()) {
            return value;
        } else {
            throw exceptionSupplier.actuate();
        }
    }

    /**
     * <code>emptyElseThrow</code>
     * <p>The empty else throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The exception supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The empty else throw return object is <code>T</code> type.</p>
     * @throws X             X <p>The x is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see X
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <X extends Throwable> T emptyElseThrow(SupplierActuator<? extends X> exceptionSupplier) throws X, RestException {
        if (isEmptyPresent()) {
            return value;
        } else {
            throw exceptionSupplier.actuate();
        }
    }

    /**
     * <code>validElseThrow</code>
     * <p>The valid else throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The exception supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The valid else throw return object is <code>T</code> type.</p>
     * @throws X             X <p>The x is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see X
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <X extends Throwable> T validElseThrow(SupplierActuator<? extends X> exceptionSupplier) throws X, RestException {
        if (isValidPresent()) {
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
