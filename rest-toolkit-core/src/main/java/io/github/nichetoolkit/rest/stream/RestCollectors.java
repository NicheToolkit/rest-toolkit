package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.*;
import org.springframework.lang.NonNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public final class RestCollectors {

    static final Set<RestCollector.Characteristics> CH_CONCURRENT_ID
            = Collections.unmodifiableSet(EnumSet.of(RestCollector.Characteristics.CONCURRENT,
            RestCollector.Characteristics.UNORDERED,
            RestCollector.Characteristics.IDENTITY_FINISH));
    static final Set<RestCollector.Characteristics> CH_CONCURRENT_NOID
            = Collections.unmodifiableSet(EnumSet.of(RestCollector.Characteristics.CONCURRENT,
            RestCollector.Characteristics.UNORDERED));
    static final Set<RestCollector.Characteristics> CH_ID
            = Collections.unmodifiableSet(EnumSet.of(RestCollector.Characteristics.IDENTITY_FINISH));
    static final Set<RestCollector.Characteristics> CH_UNORDERED_ID
            = Collections.unmodifiableSet(EnumSet.of(RestCollector.Characteristics.UNORDERED,
            RestCollector.Characteristics.IDENTITY_FINISH));
    static final Set<RestCollector.Characteristics> CH_NOID = Collections.emptySet();

    private RestCollectors() {
    }

    private static <T> BinaryOperatorActuator<T> throwingMerger() {
        return (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        };
    }

    @SuppressWarnings("unchecked")
    private static <I, R> FunctionActuator<I, R> castingIdentity() {
        return i -> (R) i;
    }

    static class CollectorImpl<T, A, R> implements RestCollector<T, A, R> {
        private final SupplierActuator<A> supplier;
        private final BiConsumerActuator<A, T> accumulator;
        private final BinaryOperatorActuator<A> combiner;
        private final FunctionActuator<A, R> finisher;
        private final Set<Characteristics> characteristics;

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

    public static <T, C extends Collection<T>>
    RestCollector<T, ?, C> toCollection(SupplierActuator<C> collectionFactory) {
        return new CollectorImpl<>(collectionFactory, Collection::add,
                (r1, r2) -> {
                    r1.addAll(r2);
                    return r1;
                },
                CH_ID);
    }

    public static <T>
    RestCollector<T, ?, List<T>> toList() {
        return new CollectorImpl<>((SupplierActuator<List<T>>) ArrayList::new, List::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                CH_ID);
    }

    public static <T>
    RestCollector<T, ?, Set<T>> toSet() {
        return new CollectorImpl<>((SupplierActuator<Set<T>>) HashSet::new, Set::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                CH_UNORDERED_ID);
    }

    public static RestCollector<CharSequence, ?, String> joining() {
        return new CollectorImpl<>(
                StringBuilder::new, StringBuilder::append,
                (r1, r2) -> {
                    r1.append(r2);
                    return r1;
                },
                StringBuilder::toString, CH_NOID);
    }

    public static RestCollector<CharSequence, ?, String> joining(CharSequence delimiter) {
        return joining(delimiter, "", "");
    }

    public static RestCollector<CharSequence, ?, String> joining(CharSequence delimiter,
                                                                 CharSequence prefix,
                                                                 CharSequence suffix) {
        return new CollectorImpl<>(
                () -> new StringJoiner(delimiter, prefix, suffix),
                StringJoiner::add, StringJoiner::merge,
                StringJoiner::toString, CH_NOID);
    }

    private static <K, V, M extends Map<K, V>>
    BinaryOperatorActuator<M> mapMerger(BinaryOperatorActuator<V> mergeFunction) {
        return (m1, m2) -> {
            for (Map.Entry<K, V> e : m2.entrySet())
                merge(m1, e.getKey(), e.getValue(), mergeFunction);
            return m1;
        };
    }

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

    public static <T, U, A, R>
    RestCollector<T, ?, R> mapping(FunctionActuator<? super T, ? extends U> mapper,
                                   RestCollector<? super U, A, R> downstream) throws RestException {
        BiConsumerActuator<A, ? super U> downstreamAccumulator = downstream.accumulator();
        return new CollectorImpl<>(downstream.supplier(),
                (r, t) -> downstreamAccumulator.actuate(r, mapper.actuate(t)),
                downstream.combiner(), downstream.finisher(),
                downstream.characteristics());
    }

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

    public static <T> RestCollector<T, ?, Long>
    counting() throws RestException {
        return reducing(0L, e -> 1L, Long::sum);
    }

    public static <T> RestCollector<T, ?, Optional<T>>
    minBy(ComparatorActuator<? super T> comparator) throws RestException {
        return reducing(BinaryOperatorActuator.minBy(comparator));
    }

    public static <T> RestCollector<T, ?, Optional<T>>
    maxBy(ComparatorActuator<? super T> comparator) throws RestException {
        return reducing(BinaryOperatorActuator.maxBy(comparator));
    }

    static double[] sumWithCompensation(double[] intermediateSum, double value) {
        double tmp = value - intermediateSum[1];
        double sum = intermediateSum[0];
        double velvel = sum + tmp; // Little wolf of rounding error
        intermediateSum[1] = (velvel - sum) - tmp;
        intermediateSum[0] = velvel;
        return intermediateSum;
    }

    static double computeFinalSum(double[] summands) {
        // Better error bounds to add both terms as the final sum
        double tmp = summands[0] + summands[1];
        double simpleSum = summands[summands.length - 1];
        if (Double.isNaN(tmp) && Double.isInfinite(simpleSum))
            return simpleSum;
        else
            return tmp;
    }

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

    @SuppressWarnings("unchecked")
    private static <T> SupplierActuator<T[]> boxSupplier(T identity) {
        return () -> (T[]) new Object[]{identity};
    }

    public static <T> RestCollector<T, ?, Optional<T>>
    reducing(BinaryOperatorActuator<T> op) throws RestException {
        class OptionalBox implements ConsumerActuator<T> {
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
                OptionalBox::new, OptionalBox::actuate,
                (a, b) -> {
                    if (b.present) a.actuate(b.value);
                    return a;
                },
                a -> Optional.ofNullable(a.value), CH_NOID);
    }

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

    public static <T, K> RestCollector<T, ?, Map<K, List<T>>>
    groupingBy(FunctionActuator<? super T, ? extends K> classifier) throws RestException {
        return groupingBy(classifier, toList());
    }

    public static <T, K, A, D>
    RestCollector<T, ?, Map<K, D>> groupingBy(FunctionActuator<? super T, ? extends K> classifier,
                                              RestCollector<? super T, A, D> downstream) throws RestException {
        return groupingBy(classifier, HashMap::new, downstream);
    }

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

    public static <T, K>
    RestCollector<T, ?, ConcurrentMap<K, List<T>>>
    groupingByConcurrent(FunctionActuator<? super T, ? extends K> classifier) throws RestException {
        return groupingByConcurrent(classifier, ConcurrentHashMap::new, toList());
    }

    public static <T, K, A, D>
    RestCollector<T, ?, ConcurrentMap<K, D>> groupingByConcurrent(FunctionActuator<? super T, ? extends K> classifier,
                                                                  RestCollector<? super T, A, D> downstream) throws RestException {
        return groupingByConcurrent(classifier, ConcurrentHashMap::new, downstream);
    }

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

    public static <T>
    RestCollector<T, ?, Map<Boolean, List<T>>> partitioningBy(PredicateActuator<? super T> predicate) throws RestException {
        return partitioningBy(predicate, toList());
    }

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

    public static <T, K, U>
    RestCollector<T, ?, Map<K, U>> toMap(FunctionActuator<? super T, ? extends K> keyMapper,
                                         FunctionActuator<? super T, ? extends U> valueMapper) throws RestException {
        return toMap(keyMapper, valueMapper, throwingMerger(), HashMap::new);
    }

    public static <T, K, U>
    RestCollector<T, ?, Map<K, U>> toMap(FunctionActuator<? super T, ? extends K> keyMapper,
                                         FunctionActuator<? super T, ? extends U> valueMapper,
                                         BinaryOperatorActuator<U> mergeFunction) throws RestException {
        return toMap(keyMapper, valueMapper, mergeFunction, HashMap::new);
    }

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

    public static <T, K, U>
    RestCollector<T, ?, ConcurrentMap<K, U>> toConcurrentMap(FunctionActuator<? super T, ? extends K> keyMapper,
                                                             FunctionActuator<? super T, ? extends U> valueMapper) throws RestException {
        return toConcurrentMap(keyMapper, valueMapper, throwingMerger(), ConcurrentHashMap::new);
    }

    public static <T, K, U>
    RestCollector<T, ?, ConcurrentMap<K, U>>
    toConcurrentMap(FunctionActuator<? super T, ? extends K> keyMapper,
                    FunctionActuator<? super T, ? extends U> valueMapper,
                    BinaryOperatorActuator<U> mergeFunction) throws RestException {
        return toConcurrentMap(keyMapper, valueMapper, mergeFunction, ConcurrentHashMap::new);
    }

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

    private static final class Partition<T>
            extends AbstractMap<Boolean, T>
            implements Map<Boolean, T> {
        final T forTrue;
        final T forFalse;

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
