package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.actuator.BiConsumerActuator;
import io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;
import io.github.nichetoolkit.rest.actuator.SupplierActuator;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * <code>RestCollector</code>
 * <p>The rest collector interface.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <A> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <R> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestCollector<T, A, R> {

    /**
     * <code>supplier</code>
     * <p>The supplier method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The supplier return object is <code>SupplierActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     */
    SupplierActuator<A> supplier();

    /**
     * <code>accumulator</code>
     * <p>The accumulator method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The accumulator return object is <code>BiConsumerActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    BiConsumerActuator<A, T> accumulator();

    /**
     * <code>combiner</code>
     * <p>The combiner method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The combiner return object is <code>BinaryOperatorActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     */
    BinaryOperatorActuator<A> combiner();

    /**
     * <code>finisher</code>
     * <p>The finisher method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The finisher return object is <code>FunctionActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    FunctionActuator<A, R> finisher();

    /**
     * <code>characteristics</code>
     * <p>The characteristics method.</p>
     * @return {@link java.util.Set} <p>The characteristics return object is <code>Set</code> type.</p>
     * @see java.util.Set
     */
    Set<Characteristics> characteristics();

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <T>             {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <R>             {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param supplier        {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The supplier parameter is <code>SupplierActuator</code> type.</p>
     * @param accumulator     {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The accumulator parameter is <code>BiConsumerActuator</code> type.</p>
     * @param combiner        {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The combiner parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @param characteristics {@link io.github.nichetoolkit.rest.stream.RestCollector.Characteristics} <p>The characteristics parameter is <code>Characteristics</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The of return object is <code>RestCollector</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.stream.RestCollector.Characteristics
     */
    static <T, R> RestCollector<T, R, R> of(SupplierActuator<R> supplier,
                                            BiConsumerActuator<R, T> accumulator,
                                            BinaryOperatorActuator<R> combiner,
                                            Characteristics... characteristics) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        Objects.requireNonNull(characteristics);
        Set<Characteristics> cs = (characteristics.length == 0)
                ? RestCollectors.CH_ID
                : Collections.unmodifiableSet(EnumSet.of(RestCollector.Characteristics.IDENTITY_FINISH,
                characteristics));
        return new RestCollectors.CollectorImpl<>(supplier, accumulator, combiner, cs);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <T>             {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <A>             {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <R>             {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param supplier        {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The supplier parameter is <code>SupplierActuator</code> type.</p>
     * @param accumulator     {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The accumulator parameter is <code>BiConsumerActuator</code> type.</p>
     * @param combiner        {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The combiner parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @param finisher        {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The finisher parameter is <code>FunctionActuator</code> type.</p>
     * @param characteristics {@link io.github.nichetoolkit.rest.stream.RestCollector.Characteristics} <p>The characteristics parameter is <code>Characteristics</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The of return object is <code>RestCollector</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.stream.RestCollector.Characteristics
     */
    static <T, A, R> RestCollector<T, A, R> of(SupplierActuator<A> supplier,
                                               BiConsumerActuator<A, T> accumulator,
                                               BinaryOperatorActuator<A> combiner,
                                               FunctionActuator<A, R> finisher,
                                               Characteristics... characteristics) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        Objects.requireNonNull(finisher);
        Objects.requireNonNull(characteristics);
        Set<Characteristics> cs = RestCollectors.CH_NOID;
        if (characteristics.length > 0) {
            cs = EnumSet.noneOf(Characteristics.class);
            Collections.addAll(cs, characteristics);
            cs = Collections.unmodifiableSet(cs);
        }
        return new RestCollectors.CollectorImpl<>(supplier, accumulator, combiner, finisher, cs);
    }

    /**
     * <code>Characteristics</code>
     * <p>The characteristics enumeration.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    enum Characteristics {

        /**
         * <code>CONCURRENT</code>
         * <p>The concurrent characteristics field.</p>
         */
        CONCURRENT,

        /**
         * <code>UNORDERED</code>
         * <p>The unordered characteristics field.</p>
         */
        UNORDERED,

        /**
         * <code>IDENTITY_FINISH</code>
         * <p>The identity finish characteristics field.</p>
         */
        IDENTITY_FINISH
    }
}
