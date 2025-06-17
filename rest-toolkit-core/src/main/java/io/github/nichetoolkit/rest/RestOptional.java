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
     * <code>getOfNull</code>
     * <p>The get of null getter method.</p>
     * @return T <p>The get of null return object is <code>T</code> type.</p>
     */
    public T getOfNull() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * <code>getOfEmpty</code>
     * <p>The get of empty getter method.</p>
     * @return T <p>The get of empty return object is <code>T</code> type.</p>
     */
    public T getOfEmpty() {
        if (GeneralUtils.isEmpty(value)) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * <code>getOfValid</code>
     * <p>The get of valid getter method.</p>
     * @return T <p>The get of valid return object is <code>T</code> type.</p>
     */
    public T getOfValid() {
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
        return isNotNull();
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
     * <code>isNotNull</code>
     * <p>The is not null method.</p>
     * @return boolean <p>The is not null return object is <code>boolean</code> type.</p>
     */
    public boolean isNotNull() {
        return GeneralUtils.isNotNull(value);
    }

    /**
     * <code>isNotEmpty</code>
     * <p>The is not empty method.</p>
     * @return boolean <p>The is not empty return object is <code>boolean</code> type.</p>
     */
    public boolean isNotEmpty() {
        return GeneralUtils.isNotEmpty(value);
    }

    /**
     * <code>isValid</code>
     * <p>The is valid method.</p>
     * @return boolean <p>The is valid return object is <code>boolean</code> type.</p>
     */
    public boolean isValid() {
        return GeneralUtils.isValid(value);
    }

    /**
     * <code>ifPresent</code>
     * <p>The if present method.</p>
     * @param consumer {@link java.util.function.Consumer} <p>The consumer parameter is <code>Consumer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The if present return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Consumer
     */
    public RestOptional<T> ifPresent(Consumer<? super T> consumer) {
        if (isPresent())
            consumer.accept(value);
        return this;
    }

    /**
     * <code>ifNotPresent</code>
     * <p>The if not present method.</p>
     * @param anchor {@link io.github.nichetoolkit.rest.actuator.AnchorFunction} <p>The anchor parameter is <code>AnchorFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The if not present return object is <code>RestOptional</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorFunction
     */
    public RestOptional<T> ifNotPresent(AnchorFunction anchor) {
        if (isNull())
            anchor.apply();
        return this;
    }

    /**
     * <code>isNotNull</code>
     * <p>The is not null method.</p>
     * @param consumer {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The is not null return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> isNotNull(ConsumerActuator<? super T> consumer) throws RestException {
        if (isNotNull())
            consumer.actuate(value);
        return this;
    }

    /**
     * <code>ifNotNull</code>
     * <p>The if not null method.</p>
     * @param consumer {@link java.util.function.Consumer} <p>The consumer parameter is <code>Consumer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The if not null return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Consumer
     */
    public RestOptional<T> ifNotNull(Consumer<? super T> consumer) {
        if (isNotNull())
            consumer.accept(value);
        return this;
    }

    /**
     * <code>isNull</code>
     * <p>The is null method.</p>
     * @param anchor {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>The anchor parameter is <code>AnchorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The is null return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> isNull(AnchorActuator anchor) throws RestException {
        if (isNull())
            anchor.actuate();
        return this;
    }

    /**
     * <code>ifNull</code>
     * <p>The if null method.</p>
     * @param anchor {@link io.github.nichetoolkit.rest.actuator.AnchorFunction} <p>The anchor parameter is <code>AnchorFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The if null return object is <code>RestOptional</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorFunction
     */
    public RestOptional<T> ifNull(AnchorFunction anchor) {
        if (isNull())
            anchor.apply();
        return this;
    }

    /**
     * <code>isNotEmpty</code>
     * <p>The is not empty method.</p>
     * @param consumer {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The is not empty return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> isNotEmpty(ConsumerActuator<? super T> consumer) throws RestException {
        if (isNotEmpty())
            consumer.actuate(value);
        return this;
    }

    /**
     * <code>ifNotEmpty</code>
     * <p>The if not empty method.</p>
     * @param consumer {@link java.util.function.Consumer} <p>The consumer parameter is <code>Consumer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The if not empty return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Consumer
     */
    public RestOptional<T> ifNotEmpty(Consumer<? super T> consumer) {
        if (isNotEmpty())
            consumer.accept(value);
        return this;
    }

    /**
     * <code>isEmpty</code>
     * <p>The is empty method.</p>
     * @param anchor {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>The anchor parameter is <code>AnchorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The is empty return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> isEmpty(AnchorActuator anchor) throws RestException {
        if (isEmpty())
            anchor.actuate();
        return this;
    }

    /**
     * <code>ifEmpty</code>
     * <p>The if empty method.</p>
     * @param anchor {@link io.github.nichetoolkit.rest.actuator.AnchorFunction} <p>The anchor parameter is <code>AnchorFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The if empty return object is <code>RestOptional</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorFunction
     */
    public RestOptional<T> ifEmpty(AnchorFunction anchor) {
        if (isEmpty())
            anchor.apply();
        return this;
    }

    /**
     * <code>isValid</code>
     * <p>The is valid method.</p>
     * @param consumer {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The is valid return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> isValid(ConsumerActuator<? super T> consumer) throws RestException {
        if (isValid())
            consumer.actuate(value);
        return this;
    }

    /**
     * <code>ifValid</code>
     * <p>The if valid method.</p>
     * @param consumer {@link java.util.function.Consumer} <p>The consumer parameter is <code>Consumer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The if valid return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Consumer
     */
    public RestOptional<T> ifValid(Consumer<? super T> consumer) {
        if (isValid())
            consumer.accept(value);
        return this;
    }

    /**
     * <code>isInvalid</code>
     * <p>The is invalid method.</p>
     * @param anchor {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>The anchor parameter is <code>AnchorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The is invalid return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> isInvalid(AnchorActuator anchor) throws RestException {
        if (isInvalid())
            anchor.actuate();
        return this;
    }

    /**
     * <code>ifInvalid</code>
     * <p>The if invalid method.</p>
     * @param anchor {@link io.github.nichetoolkit.rest.actuator.AnchorFunction} <p>The anchor parameter is <code>AnchorFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The if invalid return object is <code>RestOptional</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorFunction
     */
    public RestOptional<T> ifInvalid(AnchorFunction anchor) {
        if (isInvalid())
            anchor.apply();
        return this;
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
     * <code>filterOfNull</code>
     * <p>The filter of null method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The filter of null return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> filterOfNull(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (isNull())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    /**
     * <code>filterNull</code>
     * <p>The filter null method.</p>
     * @param predicate {@link java.util.function.Predicate} <p>The predicate parameter is <code>Predicate</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The filter null return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Predicate
     */
    public RestOptional<T> filterNull(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (isNull())
            return this;
        else
            return predicate.test(value) ? this : empty();
    }

    /**
     * <code>filterOfEmpty</code>
     * <p>The filter of empty method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The filter of empty return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> filterOfEmpty(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (isEmpty())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    /**
     * <code>filterEmpty</code>
     * <p>The filter empty method.</p>
     * @param predicate {@link java.util.function.Predicate} <p>The predicate parameter is <code>Predicate</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The filter empty return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Predicate
     */
    public RestOptional<T> filterEmpty(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (isEmpty())
            return this;
        else
            return predicate.test(value) ? this : empty();
    }

    /**
     * <code>filterOfValid</code>
     * <p>The filter of valid method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The filter of valid return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestOptional<T> filterOfValid(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (isInvalid())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    /**
     * <code>filterValid</code>
     * <p>The filter valid method.</p>
     * @param predicate {@link java.util.function.Predicate} <p>The predicate parameter is <code>Predicate</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The filter valid return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Predicate
     */
    public RestOptional<T> filterValid(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (isInvalid())
            return this;
        else
            return predicate.test(value) ? this : empty();
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
     * <code>mapOfNull</code>
     * <p>The map of null method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The map of null return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> mapOfNull(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isNull())
            return empty();
        else {
            return RestOptional.ofNullable(mapper.actuate(value));
        }
    }

    /**
     * <code>mapNull</code>
     * <p>The map null method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link java.util.function.Function} <p>The mapper parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The map null return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Function
     */
    public <U> RestOptional<U> mapNull(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (isNull())
            return empty();
        else {
            return RestOptional.ofNullable(mapper.apply(value));
        }
    }

    /**
     * <code>mapOfEmpty</code>
     * <p>The map of empty method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The map of empty return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> mapOfEmpty(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isEmpty())
            return empty();
        else {
            return RestOptional.ofEmptyable(mapper.actuate(value));
        }
    }

    /**
     * <code>mapEmpty</code>
     * <p>The map empty method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link java.util.function.Function} <p>The mapper parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The map empty return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Function
     */
    public <U> RestOptional<U> mapEmpty(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (isEmpty())
            return empty();
        else {
            return RestOptional.ofEmptyable(mapper.apply(value));
        }
    }

    /**
     * <code>mapOfValid</code>
     * <p>The map of valid method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The map of valid return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> mapOfValid(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isInvalid())
            return empty();
        else {
            return RestOptional.ofValidable(mapper.actuate(value));
        }
    }

    /**
     * <code>mapValid</code>
     * <p>The map valid method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link java.util.function.Function} <p>The mapper parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The map valid return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Function
     */
    public <U> RestOptional<U> mapValid(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (isInvalid())
            return empty();
        else {
            return RestOptional.ofValidable(mapper.apply(value));
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
     * <code>flatMapOfNull</code>
     * <p>The flat map of null method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The flat map of null return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> flatMapOfNull(FunctionActuator<? super T, RestOptional<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isNull())
            return empty();
        else {
            return Objects.requireNonNull(mapper.actuate(value));
        }
    }

    /**
     * <code>flatMapNull</code>
     * <p>The flat map null method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link java.util.function.Function} <p>The mapper parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The flat map null return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Function
     */
    public <U> RestOptional<U> flatMapNull(Function<? super T, RestOptional<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (isNull())
            return empty();
        else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }

    /**
     * <code>flatMapOfEmpty</code>
     * <p>The flat map of empty method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The flat map of empty return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> flatMapOfEmpty(FunctionActuator<? super T, RestOptional<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isEmpty())
            return empty();
        else {
            return Objects.requireNonNull(mapper.actuate(value));
        }
    }

    /**
     * <code>flatMapEmpty</code>
     * <p>The flat map empty method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link java.util.function.Function} <p>The mapper parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The flat map empty return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Function
     */
    public <U> RestOptional<U> flatMapEmpty(Function<? super T, RestOptional<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (isEmpty())
            return empty();
        else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }

    /**
     * <code>flatMapOfValid</code>
     * <p>The flat map of valid method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The flat map of valid return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <U> RestOptional<U> flatMapOfValid(FunctionActuator<? super T, RestOptional<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (isInvalid())
            return empty();
        else {
            return Objects.requireNonNull(mapper.actuate(value));
        }
    }

    /**
     * <code>flatMapValid</code>
     * <p>The flat map valid method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link java.util.function.Function} <p>The mapper parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The flat map valid return object is <code>RestOptional</code> type.</p>
     * @see java.util.function.Function
     */
    public <U> RestOptional<U> flatMapValid(Function<? super T, RestOptional<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (isInvalid())
            return empty();
        else {
            return Objects.requireNonNull(mapper.apply(value));
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
     * <code>orNull</code>
     * <p>The or null method.</p>
     * @param other T <p>The other parameter is <code>T</code> type.</p>
     * @return T <p>The or null return object is <code>T</code> type.</p>
     */
    public T orNull(T other) {
        return isNotNull() ? value : other;
    }

    /**
     * <code>orEmpty</code>
     * <p>The or empty method.</p>
     * @param other T <p>The other parameter is <code>T</code> type.</p>
     * @return T <p>The or empty return object is <code>T</code> type.</p>
     */
    public T orEmpty(T other) {
        return isNotEmpty() ? value : other;
    }

    /**
     * <code>orValid</code>
     * <p>The or valid method.</p>
     * @param other T <p>The other parameter is <code>T</code> type.</p>
     * @return T <p>The or valid return object is <code>T</code> type.</p>
     */
    public T orValid(T other) {
        return isValid() ? value : other;
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
     * <code>orElse</code>
     * <p>The or else method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The other parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The or else return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T orElse(SupplierActuator<? extends T> other) throws RestException {
        return isPresent() ? value : other.actuate();
    }

    /**
     * <code>ofElse</code>
     * <p>The of else method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.AnchorFunction} <p>The other parameter is <code>AnchorFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorFunction
     */
    public void ofElse(AnchorFunction other) {
        ifNull(other);
    }

    /**
     * <code>ofElseGet</code>
     * <p>The of else get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>The other parameter is <code>AnchorActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void ofElseGet(AnchorActuator other) throws RestException {
        isNull(other);
    }

    /**
     * <code>orNull</code>
     * <p>The or null method.</p>
     * @param other {@link java.util.function.Supplier} <p>The other parameter is <code>Supplier</code> type.</p>
     * @return T <p>The or null return object is <code>T</code> type.</p>
     * @see java.util.function.Supplier
     */
    public T orNull(Supplier<? extends T> other) {
        return isNotNull() ? value : other.get();
    }

    /**
     * <code>orNullGet</code>
     * <p>The or null get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The other parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The or null get return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T orNullGet(SupplierActuator<? extends T> other) throws RestException {
        return isNotNull() ? value : other.actuate();
    }

    /**
     * <code>ofNullGet</code>
     * <p>The of null get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>The other parameter is <code>AnchorActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void ofNullGet(AnchorActuator other) throws RestException {
        isNull(other);
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.AnchorFunction} <p>The other parameter is <code>AnchorFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorFunction
     */
    public void ofNull(AnchorFunction other) {
        ifNull(other);
    }

    /**
     * <code>orEmptyGet</code>
     * <p>The or empty get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The other parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The or empty get return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T orEmptyGet(SupplierActuator<? extends T> other) throws RestException {
        return isNotEmpty() ? value : other.actuate();
    }

    /**
     * <code>orEmpty</code>
     * <p>The or empty method.</p>
     * @param other {@link java.util.function.Supplier} <p>The other parameter is <code>Supplier</code> type.</p>
     * @return T <p>The or empty return object is <code>T</code> type.</p>
     * @see java.util.function.Supplier
     */
    public T orEmpty(Supplier<? extends T> other) {
        return isNotEmpty() ? value : other.get();
    }

    /**
     * <code>ofEmptyGet</code>
     * <p>The of empty get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>The other parameter is <code>AnchorActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void ofEmptyGet(AnchorActuator other) throws RestException {
        isEmpty(other);
    }

    /**
     * <code>ofEmpty</code>
     * <p>The of empty method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.AnchorFunction} <p>The other parameter is <code>AnchorFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorFunction
     */
    public void ofEmpty(AnchorFunction other) {
        ifEmpty(other);
    }

    /**
     * <code>orValidGet</code>
     * <p>The or valid get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The other parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The or valid get return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T orValidGet(SupplierActuator<? extends T> other) throws RestException {
        return isValid() ? value : other.actuate();
    }

    /**
     * <code>orValid</code>
     * <p>The or valid method.</p>
     * @param other {@link java.util.function.Supplier} <p>The other parameter is <code>Supplier</code> type.</p>
     * @return T <p>The or valid return object is <code>T</code> type.</p>
     * @see java.util.function.Supplier
     */
    public T orValid(Supplier<? extends T> other) {
        return isValid() ? value : other.get();
    }

    /**
     * <code>ofValidGet</code>
     * <p>The of valid get method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>The other parameter is <code>AnchorActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void ofValidGet(AnchorActuator other) throws RestException {
        isInvalid(other);
    }

    /**
     * <code>ofValid</code>
     * <p>The of valid method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.AnchorFunction} <p>The other parameter is <code>AnchorFunction</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorFunction
     */
    public void ofValid(AnchorFunction other) {
        ifInvalid(other);
    }

    /**
     * <code>elseThrow</code>
     * <p>The else throw method.</p>
     * @param <X>       {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exception {@link java.util.function.Supplier} <p>The exception parameter is <code>Supplier</code> type.</p>
     * @return T <p>The else throw return object is <code>T</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.util.function.Supplier
     * @see X
     */
    public <X extends Throwable> T elseThrow(Supplier<? extends X> exception) throws X {
        if (isPresent()) {
            return value;
        } else {
            throw exception.get();
        }
    }

    /**
     * <code>orElseThrow</code>
     * <p>The or else throw method.</p>
     * @param <X>       {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exception {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The exception parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The or else throw return object is <code>T</code> type.</p>
     * @throws X             X <p>The x is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see X
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <X extends Throwable> T orElseThrow(SupplierActuator<? extends X> exception) throws X, RestException {
        if (isPresent()) {
            return value;
        } else {
            throw exception.actuate();
        }
    }

    /**
     * <code>orNullThrow</code>
     * <p>The or null throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The exception supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The or null throw return object is <code>T</code> type.</p>
     * @throws X             X <p>The x is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see X
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <X extends Throwable> T orNullThrow(SupplierActuator<? extends X> exceptionSupplier) throws X, RestException {
        if (isNotNull()) {
            return value;
        } else {
            throw exceptionSupplier.actuate();
        }
    }

    /**
     * <code>nullThrow</code>
     * <p>The null throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link java.util.function.Supplier} <p>The exception supplier parameter is <code>Supplier</code> type.</p>
     * @return T <p>The null throw return object is <code>T</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.util.function.Supplier
     * @see X
     */
    public <X extends Throwable> T nullThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (isNotNull()) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    /**
     * <code>orEmptyThrow</code>
     * <p>The or empty throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The exception supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The or empty throw return object is <code>T</code> type.</p>
     * @throws X             X <p>The x is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see X
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <X extends Throwable> T orEmptyThrow(SupplierActuator<? extends X> exceptionSupplier) throws X, RestException {
        if (isNotEmpty()) {
            return value;
        } else {
            throw exceptionSupplier.actuate();
        }
    }

    /**
     * <code>emptyThrow</code>
     * <p>The empty throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link java.util.function.Supplier} <p>The exception supplier parameter is <code>Supplier</code> type.</p>
     * @return T <p>The empty throw return object is <code>T</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.util.function.Supplier
     * @see X
     */
    public <X extends Throwable> T emptyThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (isNotEmpty()) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    /**
     * <code>orValidThrow</code>
     * <p>The or valid throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The exception supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return T <p>The or valid throw return object is <code>T</code> type.</p>
     * @throws X             X <p>The x is <code>X</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see X
     * @see io.github.nichetoolkit.rest.RestException
     */
    public <X extends Throwable> T orValidThrow(SupplierActuator<? extends X> exceptionSupplier) throws X, RestException {
        if (isValid()) {
            return value;
        } else {
            throw exceptionSupplier.actuate();
        }
    }

    /**
     * <code>validThrow</code>
     * <p>The valid throw method.</p>
     * @param <X>               {@link java.lang.Throwable} <p>The generic parameter is <code>Throwable</code> type.</p>
     * @param exceptionSupplier {@link java.util.function.Supplier} <p>The exception supplier parameter is <code>Supplier</code> type.</p>
     * @return T <p>The valid throw return object is <code>T</code> type.</p>
     * @throws X X <p>The x is <code>X</code> type.</p>
     * @see java.lang.Throwable
     * @see java.util.function.Supplier
     * @see X
     */
    public <X extends Throwable> T validThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (isValid()) {
            return value;
        } else {
            throw exceptionSupplier.get();
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
