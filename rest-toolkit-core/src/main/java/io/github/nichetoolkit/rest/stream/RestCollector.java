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
 * <p>The type rest collector interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <A> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <R> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestCollector<T, A, R> {

    /**
     * <code>supplier</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the return object is <code>SupplierActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     */
    SupplierActuator<A> supplier();

    /**
     * <code>accumulator</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the return object is <code>BiConsumerActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     */
    BiConsumerActuator<A, T> accumulator();

    /**
     * <code>combiner</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>the return object is <code>BinaryOperatorActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     */
    BinaryOperatorActuator<A> combiner();

    /**
     * <code>finisher</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the return object is <code>FunctionActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    FunctionActuator<A, R> finisher();

    /**
     * <code>characteristics</code>
     * <p>the method.</p>
     * @return {@link java.util.Set} <p>the return object is <code>Set</code> type.</p>
     * @see java.util.Set
     */
    Set<Characteristics> characteristics();

    /**
     * <code>of</code>
     * <p>the method.</p>
     * @param <T>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <R>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param supplier        {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier parameter is <code>SupplierActuator</code> type.</p>
     * @param accumulator     {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the accumulator parameter is <code>BiConsumerActuator</code> type.</p>
     * @param combiner        {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>the combiner parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @param characteristics {@link io.github.nichetoolkit.rest.stream.RestCollector.Characteristics} <p>the characteristics parameter is <code>Characteristics</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>the return object is <code>RestCollector</code> type.</p>
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
     * <p>the method.</p>
     * @param <T>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <A>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <R>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param supplier        {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the supplier parameter is <code>SupplierActuator</code> type.</p>
     * @param accumulator     {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the accumulator parameter is <code>BiConsumerActuator</code> type.</p>
     * @param combiner        {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>the combiner parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @param finisher        {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the finisher parameter is <code>FunctionActuator</code> type.</p>
     * @param characteristics {@link io.github.nichetoolkit.rest.stream.RestCollector.Characteristics} <p>the characteristics parameter is <code>Characteristics</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>the return object is <code>RestCollector</code> type.</p>
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
     * <p>The type characteristics enumeration.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    enum Characteristics {

        /**
         * <code>CONCURRENT</code>
         * <p>the Concurrent characteristics field.</p>
         */
        CONCURRENT,

        /**
         * <code>UNORDERED</code>
         * <p>the Unordered characteristics field.</p>
         */
        UNORDERED,

        /**
         * <code>IDENTITY_FINISH</code>
         * <p>the Identity finish characteristics field.</p>
         */
        IDENTITY_FINISH
    }
}
