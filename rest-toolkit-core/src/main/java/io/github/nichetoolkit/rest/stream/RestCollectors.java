package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.actuator.*;
import org.springframework.lang.NonNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * <code>RestCollectors</code>
 * <p>The rest collectors class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public final class RestCollectors {

    /**
     * <code>CH_CONCURRENT_ID</code>
     * {@link java.util.Set} <p>The <code>CH_CONCURRENT_ID</code> field.</p>
     * @see java.util.Set
     */
    static final Set<RestCollector.Characteristics> CH_CONCURRENT_ID
            = Collections.unmodifiableSet(EnumSet.of(RestCollector.Characteristics.CONCURRENT,
            RestCollector.Characteristics.UNORDERED,
            RestCollector.Characteristics.IDENTITY_FINISH));
    /**
     * <code>CH_CONCURRENT_NOID</code>
     * {@link java.util.Set} <p>The <code>CH_CONCURRENT_NOID</code> field.</p>
     * @see java.util.Set
     */
    static final Set<RestCollector.Characteristics> CH_CONCURRENT_NOID
            = Collections.unmodifiableSet(EnumSet.of(RestCollector.Characteristics.CONCURRENT,
            RestCollector.Characteristics.UNORDERED));
    /**
     * <code>CH_ID</code>
     * {@link java.util.Set} <p>The <code>CH_ID</code> field.</p>
     * @see java.util.Set
     */
    static final Set<RestCollector.Characteristics> CH_ID
            = Collections.unmodifiableSet(EnumSet.of(RestCollector.Characteristics.IDENTITY_FINISH));
    /**
     * <code>CH_UNORDERED_ID</code>
     * {@link java.util.Set} <p>The <code>CH_UNORDERED_ID</code> field.</p>
     * @see java.util.Set
     */
    static final Set<RestCollector.Characteristics> CH_UNORDERED_ID
            = Collections.unmodifiableSet(EnumSet.of(RestCollector.Characteristics.UNORDERED,
            RestCollector.Characteristics.IDENTITY_FINISH));
    /**
     * <code>CH_NOID</code>
     * {@link java.util.Set} <p>The <code>CH_NOID</code> field.</p>
     * @see java.util.Set
     */
    static final Set<RestCollector.Characteristics> CH_NOID = Collections.emptySet();

    /**
     * <code>RestCollectors</code>
     * <p>Instantiates a new rest collectors.</p>
     */
    private RestCollectors() {
    }

    /**
     * <code>throwingMerger</code>
     * <p>The throwing merger method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The throwing merger return object is <code>BinaryOperatorActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     */
    private static <T> BinaryOperatorActuator<T> throwingMerger() {
        return (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        };
    }

    /**
     * <code>castingIdentity</code>
     * <p>The casting identity method.</p>
     * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <R> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The casting identity return object is <code>FunctionActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    private static <I, R> FunctionActuator<I, R> castingIdentity() {
        return i -> (R) i;
    }

    /**
     * <code>CollectorImpl</code>
     * <p>The collector class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <A> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <R> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    static class CollectorImpl<T, A, R> implements RestCollector<T, A, R> {
        /**
         * <code>supplier</code>
         * {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The <code>supplier</code> field.</p>
         * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
         */
        private final SupplierActuator<A> supplier;
        /**
         * <code>accumulator</code>
         * {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The <code>accumulator</code> field.</p>
         * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
         */
        private final BiConsumerActuator<A, T> accumulator;
        /**
         * <code>combiner</code>
         * {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The <code>combiner</code> field.</p>
         * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
         */
        private final BinaryOperatorActuator<A> combiner;
        /**
         * <code>finisher</code>
         * {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The <code>finisher</code> field.</p>
         * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
         */
        private final FunctionActuator<A, R> finisher;
        /**
         * <code>characteristics</code>
         * {@link java.util.Set} <p>The <code>characteristics</code> field.</p>
         * @see java.util.Set
         */
        private final Set<Characteristics> characteristics;

        /**
         * <code>CollectorImpl</code>
         * <p>Instantiates a new collector.</p>
         * @param supplier        {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The supplier parameter is <code>SupplierActuator</code> type.</p>
         * @param accumulator     {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The accumulator parameter is <code>BiConsumerActuator</code> type.</p>
         * @param combiner        {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The combiner parameter is <code>BinaryOperatorActuator</code> type.</p>
         * @param finisher        {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The finisher parameter is <code>FunctionActuator</code> type.</p>
         * @param characteristics {@link java.util.Set} <p>The characteristics parameter is <code>Set</code> type.</p>
         * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
         * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
         * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
         * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
         * @see java.util.Set
         */
        CollectorImpl(SupplierActuator<A> supplier,
                      BiConsumerActuator<A, T> accumulator,
                      BinaryOperatorActuator<A> combiner,
                      FunctionActuator<A, R> finisher,
                      Set<Characteristics> characteristics) {
            this.supplier = supplier;
            this.accumulator = accumulator;
            this.combiner = combiner;
            this.finisher = finisher;
            this.characteristics = characteristics;
        }

        /**
         * <code>CollectorImpl</code>
         * <p>Instantiates a new collector.</p>
         * @param supplier        {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The supplier parameter is <code>SupplierActuator</code> type.</p>
         * @param accumulator     {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The accumulator parameter is <code>BiConsumerActuator</code> type.</p>
         * @param combiner        {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The combiner parameter is <code>BinaryOperatorActuator</code> type.</p>
         * @param characteristics {@link java.util.Set} <p>The characteristics parameter is <code>Set</code> type.</p>
         * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
         * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
         * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
         * @see java.util.Set
         */
        CollectorImpl(SupplierActuator<A> supplier,
                      BiConsumerActuator<A, T> accumulator,
                      BinaryOperatorActuator<A> combiner,
                      Set<Characteristics> characteristics) {
            this(supplier, accumulator, combiner, castingIdentity(), characteristics);
        }

        @Override
        public BiConsumerActuator<A, T> accumulator() {
            return accumulator;
        }

        @Override
        public SupplierActuator<A> supplier() {
            return supplier;
        }

        @Override
        public BinaryOperatorActuator<A> combiner() {
            return combiner;
        }

        @Override
        public FunctionActuator<A, R> finisher() {
            return finisher;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return characteristics;
        }
    }

    /**
     * <code>toCollection</code>
     * <p>The to collection method.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <C>               {@link java.util.Collection} <p>The generic parameter is <code>Collection</code> type.</p>
     * @param collectionFactory {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The collection factory parameter is <code>SupplierActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The to collection return object is <code>RestCollector</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     */
    public static <T, C extends Collection<T>>
    RestCollector<T, ?, C> toCollection(SupplierActuator<C> collectionFactory) {
        return new CollectorImpl<>(collectionFactory, Collection::add,
                (r1, r2) -> {
                    r1.addAll(r2);
                    return r1;
                },
                CH_ID);
    }

    /**
     * <code>toList</code>
     * <p>The to list method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The to list return object is <code>RestCollector</code> type.</p>
     */
    public static <T>
    RestCollector<T, ?, List<T>> toList() {
        return new CollectorImpl<>((SupplierActuator<List<T>>) ArrayList::new, List::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                CH_ID);
    }

    /**
     * <code>toSet</code>
     * <p>The to set method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The to set return object is <code>RestCollector</code> type.</p>
     */
    public static <T>
    RestCollector<T, ?, Set<T>> toSet() {
        return new CollectorImpl<>((SupplierActuator<Set<T>>) HashSet::new, Set::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                CH_UNORDERED_ID);
    }

    /**
     * <code>joining</code>
     * <p>The joining method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The joining return object is <code>RestCollector</code> type.</p>
     */
    public static RestCollector<CharSequence, ?, String> joining() {
        return new CollectorImpl<>(
                StringBuilder::new, StringBuilder::append,
                (r1, r2) -> {
                    r1.append(r2);
                    return r1;
                },
                StringBuilder::toString, CH_NOID);
    }

    /**
     * <code>joining</code>
     * <p>The joining method.</p>
     * @param delimiter {@link java.lang.CharSequence} <p>The delimiter parameter is <code>CharSequence</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The joining return object is <code>RestCollector</code> type.</p>
     * @see java.lang.CharSequence
     */
    public static RestCollector<CharSequence, ?, String> joining(CharSequence delimiter) {
        return joining(delimiter, "", "");
    }

    /**
     * <code>joining</code>
     * <p>The joining method.</p>
     * @param delimiter {@link java.lang.CharSequence} <p>The delimiter parameter is <code>CharSequence</code> type.</p>
     * @param prefix    {@link java.lang.CharSequence} <p>The prefix parameter is <code>CharSequence</code> type.</p>
     * @param suffix    {@link java.lang.CharSequence} <p>The suffix parameter is <code>CharSequence</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The joining return object is <code>RestCollector</code> type.</p>
     * @see java.lang.CharSequence
     */
    public static RestCollector<CharSequence, ?, String> joining(CharSequence delimiter,
                                                                 CharSequence prefix,
                                                                 CharSequence suffix) {
        return new CollectorImpl<>(
                () -> new StringJoiner(delimiter, prefix, suffix),
                StringJoiner::add, StringJoiner::merge,
                StringJoiner::toString, CH_NOID);
    }

    /**
     * <code>mapMerger</code>
     * <p>The map merger method.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <M>           {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param mergeFunction {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The merge function parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The map merger return object is <code>BinaryOperatorActuator</code> type.</p>
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     */
    private static <K, V, M extends Map<K, V>>
    BinaryOperatorActuator<M> mapMerger(BinaryOperatorActuator<V> mergeFunction) {
        return (m1, m2) -> {
            for (Map.Entry<K, V> e : m2.entrySet())
                merge(m1, e.getKey(), e.getValue(), mergeFunction);
            return m1;
        };
    }

    /**
     * <code>merge</code>
     * <p>The merge method.</p>
     * @param <V>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param map               {@link java.util.Map} <p>The map parameter is <code>Map</code> type.</p>
     * @param key               K <p>The key parameter is <code>K</code> type.</p>
     * @param value             V <p>The value parameter is <code>V</code> type.</p>
     * @param remappingFunction {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The remapping function parameter is <code>BiFunctionActuator</code> type.</p>
     * @return V <p>The merge return object is <code>V</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <V, K> V merge(Map<K, V> map, K key, V value, BiFunctionActuator<? super V, ? super V, ? extends V> remappingFunction) throws RestException {
        /* copy from jdk Map<K,V>  */
        Objects.requireNonNull(remappingFunction);
        Objects.requireNonNull(value);
        V oldValue = map.get(key);
        V newValue = (oldValue == null) ? value :
                remappingFunction.actuate(oldValue, value);
        if (newValue == null) {
            map.remove(key);
        } else {
            map.put(key, newValue);
        }
        return newValue;
    }

    /**
     * <code>mapping</code>
     * <p>The mapping method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <A>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <R>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper     {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @param downstream {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The downstream parameter is <code>RestCollector</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The mapping return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, U, A, R>
    RestCollector<T, ?, R> mapping(FunctionActuator<? super T, ? extends U> mapper,
                                   RestCollector<? super U, A, R> downstream) throws RestException {
        BiConsumerActuator<A, ? super U> downstreamAccumulator = downstream.accumulator();
        return new CollectorImpl<>(downstream.supplier(),
                (r, t) -> downstreamAccumulator.actuate(r, mapper.actuate(t)),
                downstream.combiner(), downstream.finisher(),
                downstream.characteristics());
    }

    /**
     * <code>collectingAndThen</code>
     * <p>The collecting and then method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <A>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <R>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <RR>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param downstream {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The downstream parameter is <code>RestCollector</code> type.</p>
     * @param finisher   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The finisher parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The collecting and then return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, A, R, RR> RestCollector<T, A, RR> collectingAndThen(RestCollector<T, A, R> downstream,
                                                                          FunctionActuator<R, RR> finisher) throws RestException {
        Set<RestCollector.Characteristics> characteristics = downstream.characteristics();
        if (characteristics.contains(RestCollector.Characteristics.IDENTITY_FINISH)) {
            if (characteristics.size() == 1)
                characteristics = RestCollectors.CH_NOID;
            else {
                characteristics = EnumSet.copyOf(characteristics);
                characteristics.remove(RestCollector.Characteristics.IDENTITY_FINISH);
                characteristics = Collections.unmodifiableSet(characteristics);
            }
        }
        return new CollectorImpl<>(downstream.supplier(),
                downstream.accumulator(),
                downstream.combiner(),
                downstream.finisher().andThen(finisher),
                characteristics);
    }

    /**
     * <code>counting</code>
     * <p>The counting method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The counting return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestCollector<T, ?, Long>
    counting() throws RestException {
        return reducing(0L, e -> 1L, Long::sum);
    }

    /**
     * <code>minBy</code>
     * <p>The min by method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param comparator {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>The comparator parameter is <code>ComparatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The min by return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ComparatorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestCollector<T, ?, RestOptional<T>>
    minBy(ComparatorActuator<? super T> comparator) throws RestException {
        return reducing(BinaryOperatorActuator.minBy(comparator));
    }

    /**
     * <code>maxBy</code>
     * <p>The max by method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param comparator {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>The comparator parameter is <code>ComparatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The max by return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ComparatorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestCollector<T, ?, RestOptional<T>>
    maxBy(ComparatorActuator<? super T> comparator) throws RestException {
        return reducing(BinaryOperatorActuator.maxBy(comparator));
    }

    /**
     * <code>sumWithCompensation</code>
     * <p>The sum with compensation method.</p>
     * @param intermediateSum double <p>The intermediate sum parameter is <code>double</code> type.</p>
     * @param value           double <p>The value parameter is <code>double</code> type.</p>
     * @return double <p>The sum with compensation return object is <code>double</code> type.</p>
     */
    static double[] sumWithCompensation(double[] intermediateSum, double value) {
        double tmp = value - intermediateSum[1];
        double sum = intermediateSum[0];
        double level = sum + tmp; // Little wolf of rounding error
        intermediateSum[1] = (level - sum) - tmp;
        intermediateSum[0] = level;
        return intermediateSum;
    }

    /**
     * <code>computeFinalSum</code>
     * <p>The compute final sum method.</p>
     * @param summands double <p>The summands parameter is <code>double</code> type.</p>
     * @return double <p>The compute final sum return object is <code>double</code> type.</p>
     */
    static double computeFinalSum(double[] summands) {
        // Better error bounds to add both terms as the final sum
        double tmp = summands[0] + summands[1];
        double simpleSum = summands[summands.length - 1];
        if (Double.isNaN(tmp) && Double.isInfinite(simpleSum))
            return simpleSum;
        else
            return tmp;
    }

    /**
     * <code>reducing</code>
     * <p>The reducing method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param identity T <p>The identity parameter is <code>T</code> type.</p>
     * @param op       {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The op parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The reducing return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestCollector<T, ?, T>
    reducing(T identity, BinaryOperatorActuator<T> op) throws RestException {
        return new CollectorImpl<>(
                boxSupplier(identity),
                (a, t) -> a[0] = op.actuate(a[0], t),
                (a, b) -> {
                    a[0] = op.actuate(a[0], b[0]);
                    return a;
                },
                a -> a[0],
                CH_NOID);
    }

    /**
     * <code>boxSupplier</code>
     * <p>The box supplier method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param identity T <p>The identity parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The box supplier return object is <code>SupplierActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    private static <T> SupplierActuator<T[]> boxSupplier(T identity) {
        return () -> (T[]) new Object[]{identity};
    }

    /**
     * <code>logicalAnd</code>
     * <p>The logical and method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The logical and return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static RestCollector<Boolean, ?, Boolean> logicalAnd() throws RestException {
        return logical(Boolean::logicalAnd);
    }

    /**
     * <code>logicalOr</code>
     * <p>The logical or method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The logical or return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static RestCollector<Boolean, ?, Boolean> logicalOr() throws RestException {
        return logical(Boolean::logicalOr);
    }

    /**
     * <code>logicalXor</code>
     * <p>The logical xor method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The logical xor return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static RestCollector<Boolean, ?, Boolean> logicalXor() throws RestException {
        return logical(Boolean::logicalXor);
    }

    /**
     * <code>logical</code>
     * <p>The logical method.</p>
     * @param op {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The op parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The logical return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static RestCollector<Boolean, ?, Boolean> logical(BinaryOperatorActuator<Boolean> op) throws RestException {
        class BooleanBox implements ConsumerActuator<Boolean> {
            Boolean value = null;
            boolean present = false;

            @Override
            public void actuate(Boolean t) throws RestException {
                if (present) {
                    value = op.actuate(value, t);
                } else {
                    value = t;
                    present = true;
                }
            }
        }

        return new CollectorImpl<>(
                BooleanBox::new, BooleanBox::actuate,
                (a, b) -> {
                    if (b.present) a.actuate(b.value);
                    return a;
                },
                a -> a.value, CH_NOID);
    }

    /**
     * <code>reducing</code>
     * <p>The reducing method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param op  {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The op parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The reducing return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestCollector<T, ?, RestOptional<T>>
    reducing(BinaryOperatorActuator<T> op) throws RestException {
        class RestOptionalBox implements ConsumerActuator<T> {
            T value = null;
            boolean present = false;

            @Override
            public void actuate(T t) throws RestException {
                if (present) {
                    value = op.actuate(value, t);
                } else {
                    value = t;
                    present = true;
                }
            }
        }

        return new CollectorImpl<>(
                RestOptionalBox::new, RestOptionalBox::actuate,
                (a, b) -> {
                    if (b.present) a.actuate(b.value);
                    return a;
                },
                a -> RestOptional.ofNullable(a.value), CH_NOID);
    }

    /**
     * <code>reducing</code>
     * <p>The reducing method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param identity U <p>The identity parameter is <code>U</code> type.</p>
     * @param mapper   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @param op       {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The op parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The reducing return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, U>
    RestCollector<T, ?, U> reducing(U identity,
                                    FunctionActuator<? super T, ? extends U> mapper,
                                    BinaryOperatorActuator<U> op) throws RestException {
        return new CollectorImpl<>(
                boxSupplier(identity),
                (a, t) -> a[0] = op.actuate(a[0], mapper.actuate(t)),
                (a, b) -> {
                    a[0] = op.actuate(a[0], b[0]);
                    return a;
                },
                a -> a[0], CH_NOID);
    }

    /**
     * <code>groupingBy</code>
     * <p>The grouping by method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param classifier {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The classifier parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The grouping by return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, K> RestCollector<T, ?, Map<K, List<T>>>
    groupingBy(FunctionActuator<? super T, ? extends K> classifier) throws RestException {
        return groupingBy(classifier, toList());
    }

    /**
     * <code>groupingBy</code>
     * <p>The grouping by method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <A>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <D>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param classifier {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The classifier parameter is <code>FunctionActuator</code> type.</p>
     * @param downstream {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The downstream parameter is <code>RestCollector</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The grouping by return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, K, A, D>
    RestCollector<T, ?, Map<K, D>> groupingBy(FunctionActuator<? super T, ? extends K> classifier,
                                              RestCollector<? super T, A, D> downstream) throws RestException {
        return groupingBy(classifier, HashMap::new, downstream);
    }

    /**
     * <code>groupingBy</code>
     * <p>The grouping by method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <D>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <A>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <M>        {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param classifier {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The classifier parameter is <code>FunctionActuator</code> type.</p>
     * @param mapFactory {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The map factory parameter is <code>SupplierActuator</code> type.</p>
     * @param downstream {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The downstream parameter is <code>RestCollector</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The grouping by return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, K, D, A, M extends Map<K, D>>
    RestCollector<T, ?, M> groupingBy(FunctionActuator<? super T, ? extends K> classifier,
                                      SupplierActuator<M> mapFactory,
                                      RestCollector<? super T, A, D> downstream) throws RestException {
        SupplierActuator<A> downstreamSupplier = downstream.supplier();
        BiConsumerActuator<A, ? super T> downstreamAccumulator = downstream.accumulator();
        BiConsumerActuator<Map<K, A>, T> accumulator = (m, t) -> {
            K key = Objects.requireNonNull(classifier.actuate(t), "element cannot be mapped to a null key");
            A container = computeIfAbsent(m, key, k -> downstreamSupplier.actuate());
            downstreamAccumulator.actuate(container, t);
        };
        BinaryOperatorActuator<Map<K, A>> merger = RestCollectors.mapMerger(downstream.combiner());
        @SuppressWarnings("unchecked")
        SupplierActuator<Map<K, A>> mangledFactory = (SupplierActuator<Map<K, A>>) mapFactory;

        if (downstream.characteristics().contains(RestCollector.Characteristics.IDENTITY_FINISH)) {
            return new CollectorImpl<>(mangledFactory, accumulator, merger, CH_ID);
        } else {
            @SuppressWarnings("unchecked")
            FunctionActuator<A, A> downstreamFinisher = (FunctionActuator<A, A>) downstream.finisher();
            FunctionActuator<Map<K, A>, M> finisher = intermediate -> {
                replaceAll(intermediate, (k, v) -> downstreamFinisher.actuate(v));
                @SuppressWarnings("unchecked")
                M castResult = (M) intermediate;
                return castResult;
            };
            return new CollectorImpl<>(mangledFactory, accumulator, merger, finisher, CH_NOID);
        }
    }

    /**
     * <code>replaceAll</code>
     * <p>The replace all method.</p>
     * @param <K>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param map      {@link java.util.Map} <p>The map parameter is <code>Map</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The function parameter is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <K, V> void replaceAll(Map<K, V> map, BiFunctionActuator<? super K, ? super V, ? extends V> function) throws RestException {
        /* copy from jdk Map<K,V>  */
        Objects.requireNonNull(function);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch (IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }

            // ise thrown from function is not a cme.
            v = function.actuate(k, v);

            try {
                entry.setValue(v);
            } catch (IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
        }
    }


    /**
     * <code>computeIfAbsent</code>
     * <p>The compute if absent method.</p>
     * @param <K>             {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>             {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param map             {@link java.util.Map} <p>The map parameter is <code>Map</code> type.</p>
     * @param key             K <p>The key parameter is <code>K</code> type.</p>
     * @param mappingFunction {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapping function parameter is <code>FunctionActuator</code> type.</p>
     * @return V <p>The compute if absent return object is <code>V</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <K, V> V computeIfAbsent(Map<K, V> map, K key,
                                           FunctionActuator<? super K, ? extends V> mappingFunction) throws RestException {
        /* copy from jdk Map<K,V>  */
        Objects.requireNonNull(mappingFunction);
        V v;
        if ((v = map.get(key)) == null) {
            V newValue;
            if ((newValue = mappingFunction.actuate(key)) != null) {
                map.put(key, newValue);
                return newValue;
            }
        }

        return v;
    }

    /**
     * <code>groupingByConcurrent</code>
     * <p>The grouping by concurrent method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param classifier {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The classifier parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The grouping by concurrent return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, K>
    RestCollector<T, ?, ConcurrentMap<K, List<T>>>
    groupingByConcurrent(FunctionActuator<? super T, ? extends K> classifier) throws RestException {
        return groupingByConcurrent(classifier, ConcurrentHashMap::new, toList());
    }

    /**
     * <code>groupingByConcurrent</code>
     * <p>The grouping by concurrent method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <A>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <D>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param classifier {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The classifier parameter is <code>FunctionActuator</code> type.</p>
     * @param downstream {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The downstream parameter is <code>RestCollector</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The grouping by concurrent return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, K, A, D>
    RestCollector<T, ?, ConcurrentMap<K, D>> groupingByConcurrent(FunctionActuator<? super T, ? extends K> classifier,
                                                                  RestCollector<? super T, A, D> downstream) throws RestException {
        return groupingByConcurrent(classifier, ConcurrentHashMap::new, downstream);
    }

    /**
     * <code>groupingByConcurrent</code>
     * <p>The grouping by concurrent method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <A>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <D>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <M>        {@link java.util.concurrent.ConcurrentMap} <p>The generic parameter is <code>ConcurrentMap</code> type.</p>
     * @param classifier {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The classifier parameter is <code>FunctionActuator</code> type.</p>
     * @param mapFactory {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The map factory parameter is <code>SupplierActuator</code> type.</p>
     * @param downstream {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The downstream parameter is <code>RestCollector</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The grouping by concurrent return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.ConcurrentMap
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, K, A, D, M extends ConcurrentMap<K, D>>
    RestCollector<T, ?, M> groupingByConcurrent(FunctionActuator<? super T, ? extends K> classifier,
                                                SupplierActuator<M> mapFactory,
                                                RestCollector<? super T, A, D> downstream) throws RestException {
        SupplierActuator<A> downstreamSupplier = downstream.supplier();
        BiConsumerActuator<A, ? super T> downstreamAccumulator = downstream.accumulator();
        BinaryOperatorActuator<ConcurrentMap<K, A>> merger = RestCollectors.mapMerger(downstream.combiner());
        @SuppressWarnings("unchecked")
        SupplierActuator<ConcurrentMap<K, A>> mangledFactory = (SupplierActuator<ConcurrentMap<K, A>>) mapFactory;
        BiConsumerActuator<ConcurrentMap<K, A>, T> accumulator;
        if (downstream.characteristics().contains(RestCollector.Characteristics.CONCURRENT)) {
            accumulator = (m, t) -> {
                K key = Objects.requireNonNull(classifier.actuate(t), "element cannot be mapped to a null key");
                A resultContainer = computeIfAbsent(m, key, k -> downstreamSupplier.actuate());
                downstreamAccumulator.actuate(resultContainer, t);
            };
        } else {
            accumulator = (m, t) -> {
                K key = Objects.requireNonNull(classifier.actuate(t), "element cannot be mapped to a null key");
                A resultContainer = computeIfAbsent(m, key, k -> downstreamSupplier.actuate());
                synchronized (resultContainer) {
                    downstreamAccumulator.actuate(resultContainer, t);
                }
            };
        }

        if (downstream.characteristics().contains(RestCollector.Characteristics.IDENTITY_FINISH)) {
            return new CollectorImpl<>(mangledFactory, accumulator, merger, CH_CONCURRENT_ID);
        } else {
            @SuppressWarnings("unchecked")
            FunctionActuator<A, A> downstreamFinisher = (FunctionActuator<A, A>) downstream.finisher();
            FunctionActuator<ConcurrentMap<K, A>, M> finisher = intermediate -> {
                replaceAll(intermediate, (k, v) -> downstreamFinisher.actuate(v));
                @SuppressWarnings("unchecked")
                M castResult = (M) intermediate;
                return castResult;
            };
            return new CollectorImpl<>(mangledFactory, accumulator, merger, finisher, CH_CONCURRENT_NOID);
        }
    }

    /**
     * <code>partitioningBy</code>
     * <p>The partitioning by method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The partitioning by return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T>
    RestCollector<T, ?, Map<Boolean, List<T>>> partitioningBy(PredicateActuator<? super T> predicate) throws RestException {
        return partitioningBy(predicate, toList());
    }

    /**
     * <code>partitioningBy</code>
     * <p>The partitioning by method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <D>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <A>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param predicate  {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @param downstream {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The downstream parameter is <code>RestCollector</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The partitioning by return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, D, A>
    RestCollector<T, ?, Map<Boolean, D>> partitioningBy(PredicateActuator<? super T> predicate,
                                                        RestCollector<? super T, A, D> downstream) throws RestException {
        BiConsumerActuator<A, ? super T> downstreamAccumulator = downstream.accumulator();
        BiConsumerActuator<Partition<A>, T> accumulator = (result, t) ->
                downstreamAccumulator.actuate(predicate.actuate(t) ? result.forTrue : result.forFalse, t);
        BinaryOperatorActuator<A> op = downstream.combiner();
        BinaryOperatorActuator<Partition<A>> merger = (left, right) ->
                new Partition<>(op.actuate(left.forTrue, right.forTrue),
                        op.actuate(left.forFalse, right.forFalse));
        SupplierActuator<Partition<A>> supplier = () ->
                new Partition<>(downstream.supplier().actuate(),
                        downstream.supplier().actuate());
        if (downstream.characteristics().contains(RestCollector.Characteristics.IDENTITY_FINISH)) {
            return new CollectorImpl<>(supplier, accumulator, merger, CH_ID);
        } else {
            FunctionActuator<Partition<A>, Map<Boolean, D>> finisher = par ->
                    new Partition<>(downstream.finisher().actuate(par.forTrue),
                            downstream.finisher().actuate(par.forFalse));
            return new CollectorImpl<>(supplier, accumulator, merger, finisher, CH_NOID);
        }
    }

    /**
     * <code>toMap</code>
     * <p>The to map method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param keyMapper   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The key mapper parameter is <code>FunctionActuator</code> type.</p>
     * @param valueMapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The value mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The to map return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, K, U>
    RestCollector<T, ?, Map<K, U>> toMap(FunctionActuator<? super T, ? extends K> keyMapper,
                                         FunctionActuator<? super T, ? extends U> valueMapper) throws RestException {
        return toMap(keyMapper, valueMapper, throwingMerger(), HashMap::new);
    }

    /**
     * <code>toMap</code>
     * <p>The to map method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param keyMapper     {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The key mapper parameter is <code>FunctionActuator</code> type.</p>
     * @param valueMapper   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The value mapper parameter is <code>FunctionActuator</code> type.</p>
     * @param mergeFunction {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The merge function parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The to map return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, K, U>
    RestCollector<T, ?, Map<K, U>> toMap(FunctionActuator<? super T, ? extends K> keyMapper,
                                         FunctionActuator<? super T, ? extends U> valueMapper,
                                         BinaryOperatorActuator<U> mergeFunction) throws RestException {
        return toMap(keyMapper, valueMapper, mergeFunction, HashMap::new);
    }

    /**
     * <code>toMap</code>
     * <p>The to map method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <M>           {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param keyMapper     {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The key mapper parameter is <code>FunctionActuator</code> type.</p>
     * @param valueMapper   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The value mapper parameter is <code>FunctionActuator</code> type.</p>
     * @param mergeFunction {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The merge function parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @param mapSupplier   {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The map supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The to map return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, K, U, M extends Map<K, U>>
    RestCollector<T, ?, M> toMap(FunctionActuator<? super T, ? extends K> keyMapper,
                                 FunctionActuator<? super T, ? extends U> valueMapper,
                                 BinaryOperatorActuator<U> mergeFunction,
                                 SupplierActuator<M> mapSupplier) throws RestException {
        BiConsumerActuator<M, T> accumulator
                = (map, element) -> merge(map, keyMapper.actuate(element),
                valueMapper.actuate(element), mergeFunction);
        return new CollectorImpl<>(mapSupplier, accumulator, mapMerger(mergeFunction), CH_ID);
    }

    /**
     * <code>toConcurrentMap</code>
     * <p>The to concurrent map method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param keyMapper   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The key mapper parameter is <code>FunctionActuator</code> type.</p>
     * @param valueMapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The value mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The to concurrent map return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, K, U>
    RestCollector<T, ?, ConcurrentMap<K, U>> toConcurrentMap(FunctionActuator<? super T, ? extends K> keyMapper,
                                                             FunctionActuator<? super T, ? extends U> valueMapper) throws RestException {
        return toConcurrentMap(keyMapper, valueMapper, throwingMerger(), ConcurrentHashMap::new);
    }

    /**
     * <code>toConcurrentMap</code>
     * <p>The to concurrent map method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param keyMapper     {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The key mapper parameter is <code>FunctionActuator</code> type.</p>
     * @param valueMapper   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The value mapper parameter is <code>FunctionActuator</code> type.</p>
     * @param mergeFunction {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The merge function parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The to concurrent map return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, K, U>
    RestCollector<T, ?, ConcurrentMap<K, U>>
    toConcurrentMap(FunctionActuator<? super T, ? extends K> keyMapper,
                    FunctionActuator<? super T, ? extends U> valueMapper,
                    BinaryOperatorActuator<U> mergeFunction) throws RestException {
        return toConcurrentMap(keyMapper, valueMapper, mergeFunction, ConcurrentHashMap::new);
    }

    /**
     * <code>toConcurrentMap</code>
     * <p>The to concurrent map method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <M>           {@link java.util.concurrent.ConcurrentMap} <p>The generic parameter is <code>ConcurrentMap</code> type.</p>
     * @param keyMapper     {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The key mapper parameter is <code>FunctionActuator</code> type.</p>
     * @param valueMapper   {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The value mapper parameter is <code>FunctionActuator</code> type.</p>
     * @param mergeFunction {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The merge function parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @param mapSupplier   {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The map supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The to concurrent map return object is <code>RestCollector</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.ConcurrentMap
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T, K, U, M extends ConcurrentMap<K, U>>
    RestCollector<T, ?, M> toConcurrentMap(FunctionActuator<? super T, ? extends K> keyMapper,
                                           FunctionActuator<? super T, ? extends U> valueMapper,
                                           BinaryOperatorActuator<U> mergeFunction,
                                           SupplierActuator<M> mapSupplier) throws RestException {
        BiConsumerActuator<M, T> accumulator
                = (map, element) -> merge(map, keyMapper.actuate(element),
                valueMapper.actuate(element), mergeFunction);
        return new CollectorImpl<>(mapSupplier, accumulator, mapMerger(mergeFunction), CH_CONCURRENT_ID);
    }

    /**
     * <code>Partition</code>
     * <p>The partition class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see java.util.AbstractMap
     * @see java.util.Map
     * @since Jdk1.8
     */
    private static final class Partition<T>
            extends AbstractMap<Boolean, T>
            implements Map<Boolean, T> {
        /**
         * <code>forTrue</code>
         * <p>The <code>forTrue</code> field.</p>
         */
        final T forTrue;
        /**
         * <code>forFalse</code>
         * <p>The <code>forFalse</code> field.</p>
         */
        final T forFalse;

        /**
         * <code>Partition</code>
         * <p>Instantiates a new partition.</p>
         * @param forTrue  T <p>The for true parameter is <code>T</code> type.</p>
         * @param forFalse T <p>The for false parameter is <code>T</code> type.</p>
         */
        Partition(T forTrue, T forFalse) {
            this.forTrue = forTrue;
            this.forFalse = forFalse;
        }

        @NonNull
        @Override
        public Set<Entry<Boolean, T>> entrySet() {
            return new AbstractSet<Entry<Boolean, T>>() {
                @NonNull
                @Override
                public Iterator<Entry<Boolean, T>> iterator() {
                    Entry<Boolean, T> falseEntry = new SimpleImmutableEntry<>(false, forFalse);
                    Entry<Boolean, T> trueEntry = new SimpleImmutableEntry<>(true, forTrue);
                    return Arrays.asList(falseEntry, trueEntry).iterator();
                }

                @Override
                public int size() {
                    return 2;
                }
            };
        }
    }
}
