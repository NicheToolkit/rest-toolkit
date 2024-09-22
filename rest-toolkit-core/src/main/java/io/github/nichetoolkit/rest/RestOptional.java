package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.actuator.*;
import io.github.nichetoolkit.rest.error.data.*;
import io.github.nichetoolkit.rest.error.often.FieldNullException;
import io.github.nichetoolkit.rest.error.often.FieldRepeatException;
import io.github.nichetoolkit.rest.error.often.IdentityNullException;
import io.github.nichetoolkit.rest.error.often.NameRepeatException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
public final class RestOptional<T> {
    private static final RestOptional<?> EMPTY = new RestOptional<>();

    private final T value;

    private RestOptional() {
        this.value = null;
    }

    public static <T> RestOptional<T> empty() {
        @SuppressWarnings("unchecked")
        RestOptional<T> t = (RestOptional<T>) EMPTY;
        return t;
    }

    private RestOptional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public static <T> RestOptional<T> of(T value) {
        return new RestOptional<>(value);
    }

    public static <T> RestOptional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    public static <T> RestOptional<T> ofEmptyable(T value) {
        return GeneralUtils.isEmpty(value) ? empty() : of(value);
    }

    public static <T> RestOptional<T> ofValidable(T value) {
        return GeneralUtils.isInvalid(value) ? empty() : of(value);
    }

    public T get() {
        return value;
    }

    public T nullGet() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    public T emptyGet() {
        if (GeneralUtils.isEmpty(value)) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    public T validGet() {
        if (GeneralUtils.isInvalid(value)) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    public boolean isNullPresent() {
        return value != null;
    }

    public boolean isEmptyPresent() {
        return GeneralUtils.isNotEmpty(value);
    }

    public boolean isValidPresent() {
        return GeneralUtils.isValid(value);
    }

    public void ifNullPresent(ConsumerActuator<? super T> consumer) throws RestException {
        if (value != null)
            consumer.actuate(value);
    }

    public void ifEmptyPresent(ConsumerActuator<? super T> consumer) throws RestException {
        if (GeneralUtils.isNotEmpty(value))
            consumer.actuate(value);
    }

    public void ifValidPresent(ConsumerActuator<? super T> consumer) throws RestException {
        if (GeneralUtils.isValid(value))
            consumer.actuate(value);
    }


    public RestOptional<T> nullFilter(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (!isNullPresent())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    public RestOptional<T> emptyFilter(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (!isEmptyPresent())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    public RestOptional<T> validFilter(PredicateActuator<? super T> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        if (!isValidPresent())
            return this;
        else
            return predicate.actuate(value) ? this : empty();
    }

    public <U> RestOptional<U> nullMap(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (!isNullPresent())
            return empty();
        else {
            return RestOptional.ofNullable(mapper.actuate(value));
        }
    }

    public <U> RestOptional<U> emptyMap(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (!isEmptyPresent())
            return empty();
        else {
            return RestOptional.ofNullable(mapper.actuate(value));
        }
    }

    public <U> RestOptional<U> validMap(FunctionActuator<? super T, ? extends U> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (!isValidPresent())
            return empty();
        else {
            return RestOptional.ofNullable(mapper.actuate(value));
        }
    }

    public <U> RestOptional<U> nullFlatMap(FunctionActuator<? super T, RestOptional<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (!isNullPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.actuate(value));
        }
    }

    public <U> RestOptional<U> emptyFlatMap(FunctionActuator<? super T, RestOptional<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (!isEmptyPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.actuate(value));
        }
    }

    public <U> RestOptional<U> validFlatMap(FunctionActuator<? super T, RestOptional<U>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        if (!isValidPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.actuate(value));
        }
    }

    public T nullElse(T other) {
        return value != null ? value : other;
    }

    public T emptyElse(T other) {
        return GeneralUtils.isNotEmpty(value) ? value : other;
    }

    public T validElse(T other) {
        return GeneralUtils.isValid(value) ? value : other;
    }

    public T nullElseGet(SupplierActuator<? extends T> other) throws RestException {
        return value != null ? value : other.actuate();
    }

    public T emptyElseGet(SupplierActuator<? extends T> other) throws RestException {
        return GeneralUtils.isNotEmpty(value) ? value : other.actuate();
    }

    public T validElseGet(SupplierActuator<? extends T> other) throws RestException {
        return GeneralUtils.isValid(value) ? value : other.actuate();
    }


    public <X extends Throwable> T nullElseThrow(SupplierActuator<? extends X> exceptionSupplier) throws X, RestException {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.actuate();
        }
    }

    public <X extends Throwable> T emptyElseThrow(SupplierActuator<? extends X> exceptionSupplier) throws X, RestException {
        if (GeneralUtils.isNotEmpty(value)) {
            return value;
        } else {
            throw exceptionSupplier.actuate();
        }
    }

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
