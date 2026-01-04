package io.github.nichetoolkit.rest.future;

import sun.misc.VM;

import java.io.ObjectStreamField;
import java.util.Random;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

/**
 * <code>RestThreadLocalRandom</code>
 * <p>The rest thread local random class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.Random
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("all")
public class RestThreadLocalRandom extends Random {

    /**
     * <code>probeGenerator</code>
     * {@link java.util.concurrent.atomic.AtomicInteger} <p>The constant <code>probeGenerator</code> field.</p>
     * @see java.util.concurrent.atomic.AtomicInteger
     */
    private static final AtomicInteger probeGenerator = new AtomicInteger();

    /**
     * <code>seeder</code>
     * {@link java.util.concurrent.atomic.AtomicLong} <p>The constant <code>seeder</code> field.</p>
     * @see java.util.concurrent.atomic.AtomicLong
     */
    private static final AtomicLong seeder = new AtomicLong(initialSeed());

    /**
     * <code>initialSeed</code>
     * <p>The initial seed method.</p>
     * @return long <p>The initial seed return object is <code>long</code> type.</p>
     */
    private static long initialSeed() {
        String sec = VM.getSavedProperty("java.util.secureRandomSeed");
        if (Boolean.parseBoolean(sec)) {
            byte[] seedBytes = java.security.SecureRandom.getSeed(8);
            long s = (long) (seedBytes[0]) & 0xffL;
            for (int i = 1; i < 8; ++i)
                s = (s << 8) | ((long) (seedBytes[i]) & 0xffL);
            return s;
        }
        return (mix64(System.currentTimeMillis()) ^
                mix64(System.nanoTime()));
    }

    /**
     * <code>GAMMA</code>
     * <p>The constant <code>GAMMA</code> field.</p>
     */
    private static final long GAMMA = 0x9e3779b97f4a7c15L;

    /**
     * <code>PROBE_INCREMENT</code>
     * <p>The constant <code>PROBE_INCREMENT</code> field.</p>
     */
    private static final int PROBE_INCREMENT = 0x9e3779b9;

    /**
     * <code>SEEDER_INCREMENT</code>
     * <p>The constant <code>SEEDER_INCREMENT</code> field.</p>
     */
    private static final long SEEDER_INCREMENT = 0xbb67ae8584caa73bL;

    /**
     * <code>DOUBLE_UNIT</code>
     * <p>The constant <code>DOUBLE_UNIT</code> field.</p>
     */
// Constants from SplittableRandom
    private static final double DOUBLE_UNIT = 0x1.0p-53;  // 1.0  / (1L << 53)
    /**
     * <code>FLOAT_UNIT</code>
     * <p>The constant <code>FLOAT_UNIT</code> field.</p>
     */
    private static final float FLOAT_UNIT = 0x1.0p-24f; // 1.0f / (1 << 24)

    /**
     * <code>nextLocalGaussian</code>
     * {@link java.lang.ThreadLocal} <p>The constant <code>nextLocalGaussian</code> field.</p>
     * @see java.lang.ThreadLocal
     */
    private static final ThreadLocal<Double> nextLocalGaussian = new ThreadLocal<>();

    /**
     * <code>mix64</code>
     * <p>The mix 64 method.</p>
     * @param z long <p>The z parameter is <code>long</code> type.</p>
     * @return long <p>The mix 64 return object is <code>long</code> type.</p>
     */
    private static long mix64(long z) {
        z = (z ^ (z >>> 33)) * 0xff51afd7ed558ccdL;
        z = (z ^ (z >>> 33)) * 0xc4ceb9fe1a85ec53L;
        return z ^ (z >>> 33);
    }

    /**
     * <code>mix32</code>
     * <p>The mix 32 method.</p>
     * @param z long <p>The z parameter is <code>long</code> type.</p>
     * @return int <p>The mix 32 return object is <code>int</code> type.</p>
     */
    private static int mix32(long z) {
        z = (z ^ (z >>> 33)) * 0xff51afd7ed558ccdL;
        return (int) (((z ^ (z >>> 33)) * 0xc4ceb9fe1a85ec53L) >>> 32);
    }

    /**
     * <code>initialized</code>
     * <p>The <code>initialized</code> field.</p>
     */
    boolean initialized;

    /**
     * <code>RestThreadLocalRandom</code>
     * <p>Instantiates a new rest thread local random.</p>
     */
    private RestThreadLocalRandom() {
        initialized = true; // false during super() call
    }

    /**
     * <code>instance</code>
     * {@link io.github.nichetoolkit.rest.future.RestThreadLocalRandom} <p>The <code>instance</code> field.</p>
     */
    static final RestThreadLocalRandom instance = new RestThreadLocalRandom();

    /**
     * <code>localInit</code>
     * <p>The local init method.</p>
     */
    static final void localInit() {
        int p = probeGenerator.addAndGet(PROBE_INCREMENT);
        int probe = (p == 0) ? 1 : p; // skip 0
        long seed = mix64(seeder.getAndAdd(SEEDER_INCREMENT));
        Thread t = Thread.currentThread();
        UNSAFE.putLong(t, SEED, seed);
        UNSAFE.putInt(t, PROBE, probe);
    }

    /**
     * <code>current</code>
     * <p>The current method.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestThreadLocalRandom} <p>The current return object is <code>RestThreadLocalRandom</code> type.</p>
     */
    public static RestThreadLocalRandom current() {
        if (UNSAFE.getInt(Thread.currentThread(), PROBE) == 0)
            localInit();
        return instance;
    }

    public void setSeed(long seed) {
        // only allow call from super() constructor
        if (initialized)
            throw new UnsupportedOperationException();
    }

    /**
     * <code>nextSeed</code>
     * <p>The next seed method.</p>
     * @return long <p>The next seed return object is <code>long</code> type.</p>
     */
    final long nextSeed() {
        Thread t;
        long r; // read and update per-thread seed
        UNSAFE.putLong(t = Thread.currentThread(), SEED,
                r = UNSAFE.getLong(t, SEED) + GAMMA);
        return r;
    }

    // We must define this, but never use it.
    protected int next(int bits) {
        return (int) (mix64(nextSeed()) >>> (64 - bits));
    }

    /**
     * <code>BadBound</code>
     * {@link java.lang.String} <p>The constant <code>BadBound</code> field.</p>
     * @see java.lang.String
     */
// IllegalArgumentException messages
    static final String BadBound = "bound must be positive";
    /**
     * <code>BadRange</code>
     * {@link java.lang.String} <p>The <code>BadRange</code> field.</p>
     * @see java.lang.String
     */
    static final String BadRange = "bound must be greater than origin";
    /**
     * <code>BadSize</code>
     * {@link java.lang.String} <p>The <code>BadSize</code> field.</p>
     * @see java.lang.String
     */
    static final String BadSize = "size must be non-negative";

    /**
     * <code>internalNextLong</code>
     * <p>The internal next long method.</p>
     * @param origin long <p>The origin parameter is <code>long</code> type.</p>
     * @param bound  long <p>The bound parameter is <code>long</code> type.</p>
     * @return long <p>The internal next long return object is <code>long</code> type.</p>
     */
    final long internalNextLong(long origin, long bound) {
        long r = mix64(nextSeed());
        if (origin < bound) {
            long n = bound - origin, m = n - 1;
            if ((n & m) == 0L)  // power of two
                r = (r & m) + origin;
            else if (n > 0L) {  // reject over-represented candidates
                for (long u = r >>> 1;            // ensure nonnegative
                     u + m - (r = u % n) < 0L;    // rejection check
                     u = mix64(nextSeed()) >>> 1) // retry
                    ;
                r += origin;
            } else {              // range not representable as long
                while (r < origin || r >= bound)
                    r = mix64(nextSeed());
            }
        }
        return r;
    }

    /**
     * <code>internalNextInt</code>
     * <p>The internal next int method.</p>
     * @param origin int <p>The origin parameter is <code>int</code> type.</p>
     * @param bound  int <p>The bound parameter is <code>int</code> type.</p>
     * @return int <p>The internal next int return object is <code>int</code> type.</p>
     */
    final int internalNextInt(int origin, int bound) {
        int r = mix32(nextSeed());
        if (origin < bound) {
            int n = bound - origin, m = n - 1;
            if ((n & m) == 0)
                r = (r & m) + origin;
            else if (n > 0) {
                for (int u = r >>> 1;
                     u + m - (r = u % n) < 0;
                     u = mix32(nextSeed()) >>> 1)
                    ;
                r += origin;
            } else {
                while (r < origin || r >= bound)
                    r = mix32(nextSeed());
            }
        }
        return r;
    }

    /**
     * <code>internalNextDouble</code>
     * <p>The internal next double method.</p>
     * @param origin double <p>The origin parameter is <code>double</code> type.</p>
     * @param bound  double <p>The bound parameter is <code>double</code> type.</p>
     * @return double <p>The internal next double return object is <code>double</code> type.</p>
     */
    final double internalNextDouble(double origin, double bound) {
        double r = (nextLong() >>> 11) * DOUBLE_UNIT;
        if (origin < bound) {
            r = r * (bound - origin) + origin;
            if (r >= bound) // correct for rounding
                r = Double.longBitsToDouble(Double.doubleToLongBits(bound) - 1);
        }
        return r;
    }

    public int nextInt() {
        return mix32(nextSeed());
    }

    public int nextInt(int bound) {
        if (bound <= 0)
            throw new IllegalArgumentException(BadBound);
        int r = mix32(nextSeed());
        int m = bound - 1;
        if ((bound & m) == 0) // power of two
            r &= m;
        else { // reject over-represented candidates
            for (int u = r >>> 1;
                 u + m - (r = u % bound) < 0;
                 u = mix32(nextSeed()) >>> 1)
                ;
        }
        return r;
    }

    /**
     * <code>nextInt</code>
     * <p>The next int method.</p>
     * @param origin int <p>The origin parameter is <code>int</code> type.</p>
     * @param bound  int <p>The bound parameter is <code>int</code> type.</p>
     * @return int <p>The next int return object is <code>int</code> type.</p>
     */
    public int nextInt(int origin, int bound) {
        if (origin >= bound)
            throw new IllegalArgumentException(BadRange);
        return internalNextInt(origin, bound);
    }

    public long nextLong() {
        return mix64(nextSeed());
    }

    /**
     * <code>nextLong</code>
     * <p>The next long method.</p>
     * @param bound long <p>The bound parameter is <code>long</code> type.</p>
     * @return long <p>The next long return object is <code>long</code> type.</p>
     */
    public long nextLong(long bound) {
        if (bound <= 0)
            throw new IllegalArgumentException(BadBound);
        long r = mix64(nextSeed());
        long m = bound - 1;
        if ((bound & m) == 0L) // power of two
            r &= m;
        else { // reject over-represented candidates
            for (long u = r >>> 1;
                 u + m - (r = u % bound) < 0L;
                 u = mix64(nextSeed()) >>> 1)
                ;
        }
        return r;
    }

    /**
     * <code>nextLong</code>
     * <p>The next long method.</p>
     * @param origin long <p>The origin parameter is <code>long</code> type.</p>
     * @param bound  long <p>The bound parameter is <code>long</code> type.</p>
     * @return long <p>The next long return object is <code>long</code> type.</p>
     */
    public long nextLong(long origin, long bound) {
        if (origin >= bound)
            throw new IllegalArgumentException(BadRange);
        return internalNextLong(origin, bound);
    }

    public double nextDouble() {
        return (mix64(nextSeed()) >>> 11) * DOUBLE_UNIT;
    }

    /**
     * <code>nextDouble</code>
     * <p>The next double method.</p>
     * @param bound double <p>The bound parameter is <code>double</code> type.</p>
     * @return double <p>The next double return object is <code>double</code> type.</p>
     */
    public double nextDouble(double bound) {
        if (!(bound > 0.0))
            throw new IllegalArgumentException(BadBound);
        double result = (mix64(nextSeed()) >>> 11) * DOUBLE_UNIT * bound;
        return (result < bound) ? result : // correct for rounding
                Double.longBitsToDouble(Double.doubleToLongBits(bound) - 1);
    }

    /**
     * <code>nextDouble</code>
     * <p>The next double method.</p>
     * @param origin double <p>The origin parameter is <code>double</code> type.</p>
     * @param bound  double <p>The bound parameter is <code>double</code> type.</p>
     * @return double <p>The next double return object is <code>double</code> type.</p>
     */
    public double nextDouble(double origin, double bound) {
        if (!(origin < bound))
            throw new IllegalArgumentException(BadRange);
        return internalNextDouble(origin, bound);
    }

    public boolean nextBoolean() {
        return mix32(nextSeed()) < 0;
    }

    public float nextFloat() {
        return (mix32(nextSeed()) >>> 8) * FLOAT_UNIT;
    }

    public double nextGaussian() {
        // Use nextLocalGaussian instead of nextGaussian field
        Double d = nextLocalGaussian.get();
        if (d != null) {
            nextLocalGaussian.remove();
            return d;
        }
        double v1, v2, s;
        do {
            v1 = 2 * nextDouble() - 1; // between -1 and 1
            v2 = 2 * nextDouble() - 1; // between -1 and 1
            s = v1 * v1 + v2 * v2;
        } while (s >= 1 || s == 0);
        double multiplier = StrictMath.sqrt(-2 * StrictMath.log(s) / s);
        nextLocalGaussian.set(v2 * multiplier);
        return v1 * multiplier;
    }

    // stream methods, coded in a way intended to better isolate for
    // maintenance purposes the small differences across forms.

    public IntStream ints(long streamSize) {
        if (streamSize < 0L)
            throw new IllegalArgumentException(BadSize);
        return StreamSupport.intStream
                (new RestThreadLocalRandom.RandomIntsSpliterator
                                (0L, streamSize, Integer.MAX_VALUE, 0),
                        false);
    }

    public IntStream ints() {
        return StreamSupport.intStream
                (new RestThreadLocalRandom.RandomIntsSpliterator
                                (0L, Long.MAX_VALUE, Integer.MAX_VALUE, 0),
                        false);
    }

    public IntStream ints(long streamSize, int randomNumberOrigin,
                          int randomNumberBound) {
        if (streamSize < 0L)
            throw new IllegalArgumentException(BadSize);
        if (randomNumberOrigin >= randomNumberBound)
            throw new IllegalArgumentException(BadRange);
        return StreamSupport.intStream
                (new RestThreadLocalRandom.RandomIntsSpliterator
                                (0L, streamSize, randomNumberOrigin, randomNumberBound),
                        false);
    }

    public IntStream ints(int randomNumberOrigin, int randomNumberBound) {
        if (randomNumberOrigin >= randomNumberBound)
            throw new IllegalArgumentException(BadRange);
        return StreamSupport.intStream
                (new RestThreadLocalRandom.RandomIntsSpliterator
                                (0L, Long.MAX_VALUE, randomNumberOrigin, randomNumberBound),
                        false);
    }

    public LongStream longs(long streamSize) {
        if (streamSize < 0L)
            throw new IllegalArgumentException(BadSize);
        return StreamSupport.longStream
                (new RestThreadLocalRandom.RandomLongsSpliterator
                                (0L, streamSize, Long.MAX_VALUE, 0L),
                        false);
    }

    public LongStream longs() {
        return StreamSupport.longStream
                (new RestThreadLocalRandom.RandomLongsSpliterator
                                (0L, Long.MAX_VALUE, Long.MAX_VALUE, 0L),
                        false);
    }

    public LongStream longs(long streamSize, long randomNumberOrigin,
                            long randomNumberBound) {
        if (streamSize < 0L)
            throw new IllegalArgumentException(BadSize);
        if (randomNumberOrigin >= randomNumberBound)
            throw new IllegalArgumentException(BadRange);
        return StreamSupport.longStream
                (new RestThreadLocalRandom.RandomLongsSpliterator
                                (0L, streamSize, randomNumberOrigin, randomNumberBound),
                        false);
    }

    public LongStream longs(long randomNumberOrigin, long randomNumberBound) {
        if (randomNumberOrigin >= randomNumberBound)
            throw new IllegalArgumentException(BadRange);
        return StreamSupport.longStream
                (new RestThreadLocalRandom.RandomLongsSpliterator
                                (0L, Long.MAX_VALUE, randomNumberOrigin, randomNumberBound),
                        false);
    }

    public DoubleStream doubles(long streamSize) {
        if (streamSize < 0L)
            throw new IllegalArgumentException(BadSize);
        return StreamSupport.doubleStream
                (new RestThreadLocalRandom.RandomDoublesSpliterator
                                (0L, streamSize, Double.MAX_VALUE, 0.0),
                        false);
    }

    public DoubleStream doubles() {
        return StreamSupport.doubleStream
                (new RestThreadLocalRandom.RandomDoublesSpliterator
                                (0L, Long.MAX_VALUE, Double.MAX_VALUE, 0.0),
                        false);
    }

    public DoubleStream doubles(long streamSize, double randomNumberOrigin,
                                double randomNumberBound) {
        if (streamSize < 0L)
            throw new IllegalArgumentException(BadSize);
        if (!(randomNumberOrigin < randomNumberBound))
            throw new IllegalArgumentException(BadRange);
        return StreamSupport.doubleStream
                (new RestThreadLocalRandom.RandomDoublesSpliterator
                                (0L, streamSize, randomNumberOrigin, randomNumberBound),
                        false);
    }

    public DoubleStream doubles(double randomNumberOrigin, double randomNumberBound) {
        if (!(randomNumberOrigin < randomNumberBound))
            throw new IllegalArgumentException(BadRange);
        return StreamSupport.doubleStream
                (new RestThreadLocalRandom.RandomDoublesSpliterator
                                (0L, Long.MAX_VALUE, randomNumberOrigin, randomNumberBound),
                        false);
    }

    /**
     * <code>RandomIntsSpliterator</code>
     * <p>The random ints spliterator class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see java.util.Spliterator.OfInt
     * @since Jdk1.8
     */
    static final class RandomIntsSpliterator implements Spliterator.OfInt {
        /**
         * <code>index</code>
         * <p>The <code>index</code> field.</p>
         */
        long index;
        /**
         * <code>fence</code>
         * <p>The <code>fence</code> field.</p>
         */
        final long fence;
        /**
         * <code>origin</code>
         * <p>The <code>origin</code> field.</p>
         */
        final int origin;
        /**
         * <code>bound</code>
         * <p>The <code>bound</code> field.</p>
         */
        final int bound;

        /**
         * <code>RandomIntsSpliterator</code>
         * <p>Instantiates a new random ints spliterator.</p>
         * @param index  long <p>The index parameter is <code>long</code> type.</p>
         * @param fence  long <p>The fence parameter is <code>long</code> type.</p>
         * @param origin int <p>The origin parameter is <code>int</code> type.</p>
         * @param bound  int <p>The bound parameter is <code>int</code> type.</p>
         */
        RandomIntsSpliterator(long index, long fence,
                              int origin, int bound) {
            this.index = index;
            this.fence = fence;
            this.origin = origin;
            this.bound = bound;
        }

        public RestThreadLocalRandom.RandomIntsSpliterator trySplit() {
            long i = index, m = (i + fence) >>> 1;
            return (m <= i) ? null :
                    new RestThreadLocalRandom.RandomIntsSpliterator(i, index = m, origin, bound);
        }

        public long estimateSize() {
            return fence - index;
        }

        public int characteristics() {
            return (Spliterator.SIZED | Spliterator.SUBSIZED |
                    Spliterator.NONNULL | Spliterator.IMMUTABLE);
        }

        public boolean tryAdvance(IntConsumer consumer) {
            if (consumer == null) throw new NullPointerException();
            long i = index, f = fence;
            if (i < f) {
                consumer.accept(RestThreadLocalRandom.current().internalNextInt(origin, bound));
                index = i + 1;
                return true;
            }
            return false;
        }

        public void forEachRemaining(IntConsumer consumer) {
            if (consumer == null) throw new NullPointerException();
            long i = index, f = fence;
            if (i < f) {
                index = f;
                int o = origin, b = bound;
                RestThreadLocalRandom rng = RestThreadLocalRandom.current();
                do {
                    consumer.accept(rng.internalNextInt(o, b));
                } while (++i < f);
            }
        }
    }

    /**
     * <code>RandomLongsSpliterator</code>
     * <p>The random longs spliterator class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see java.util.Spliterator.OfLong
     * @since Jdk1.8
     */
    static final class RandomLongsSpliterator implements Spliterator.OfLong {
        /**
         * <code>index</code>
         * <p>The <code>index</code> field.</p>
         */
        long index;
        /**
         * <code>fence</code>
         * <p>The <code>fence</code> field.</p>
         */
        final long fence;
        /**
         * <code>origin</code>
         * <p>The <code>origin</code> field.</p>
         */
        final long origin;
        /**
         * <code>bound</code>
         * <p>The <code>bound</code> field.</p>
         */
        final long bound;

        /**
         * <code>RandomLongsSpliterator</code>
         * <p>Instantiates a new random longs spliterator.</p>
         * @param index  long <p>The index parameter is <code>long</code> type.</p>
         * @param fence  long <p>The fence parameter is <code>long</code> type.</p>
         * @param origin long <p>The origin parameter is <code>long</code> type.</p>
         * @param bound  long <p>The bound parameter is <code>long</code> type.</p>
         */
        RandomLongsSpliterator(long index, long fence,
                               long origin, long bound) {
            this.index = index;
            this.fence = fence;
            this.origin = origin;
            this.bound = bound;
        }

        public RestThreadLocalRandom.RandomLongsSpliterator trySplit() {
            long i = index, m = (i + fence) >>> 1;
            return (m <= i) ? null :
                    new RestThreadLocalRandom.RandomLongsSpliterator(i, index = m, origin, bound);
        }

        public long estimateSize() {
            return fence - index;
        }

        public int characteristics() {
            return (Spliterator.SIZED | Spliterator.SUBSIZED |
                    Spliterator.NONNULL | Spliterator.IMMUTABLE);
        }

        public boolean tryAdvance(LongConsumer consumer) {
            if (consumer == null) throw new NullPointerException();
            long i = index, f = fence;
            if (i < f) {
                consumer.accept(RestThreadLocalRandom.current().internalNextLong(origin, bound));
                index = i + 1;
                return true;
            }
            return false;
        }

        public void forEachRemaining(LongConsumer consumer) {
            if (consumer == null) throw new NullPointerException();
            long i = index, f = fence;
            if (i < f) {
                index = f;
                long o = origin, b = bound;
                RestThreadLocalRandom rng = RestThreadLocalRandom.current();
                do {
                    consumer.accept(rng.internalNextLong(o, b));
                } while (++i < f);
            }
        }

    }

    /**
     * <code>RandomDoublesSpliterator</code>
     * <p>The random doubles spliterator class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see java.util.Spliterator.OfDouble
     * @since Jdk1.8
     */
    static final class RandomDoublesSpliterator implements Spliterator.OfDouble {
        /**
         * <code>index</code>
         * <p>The <code>index</code> field.</p>
         */
        long index;
        /**
         * <code>fence</code>
         * <p>The <code>fence</code> field.</p>
         */
        final long fence;
        /**
         * <code>origin</code>
         * <p>The <code>origin</code> field.</p>
         */
        final double origin;
        /**
         * <code>bound</code>
         * <p>The <code>bound</code> field.</p>
         */
        final double bound;

        /**
         * <code>RandomDoublesSpliterator</code>
         * <p>Instantiates a new random doubles spliterator.</p>
         * @param index  long <p>The index parameter is <code>long</code> type.</p>
         * @param fence  long <p>The fence parameter is <code>long</code> type.</p>
         * @param origin double <p>The origin parameter is <code>double</code> type.</p>
         * @param bound  double <p>The bound parameter is <code>double</code> type.</p>
         */
        RandomDoublesSpliterator(long index, long fence,
                                 double origin, double bound) {
            this.index = index;
            this.fence = fence;
            this.origin = origin;
            this.bound = bound;
        }

        public RestThreadLocalRandom.RandomDoublesSpliterator trySplit() {
            long i = index, m = (i + fence) >>> 1;
            return (m <= i) ? null :
                    new RestThreadLocalRandom.RandomDoublesSpliterator(i, index = m, origin, bound);
        }

        public long estimateSize() {
            return fence - index;
        }

        public int characteristics() {
            return (Spliterator.SIZED | Spliterator.SUBSIZED |
                    Spliterator.NONNULL | Spliterator.IMMUTABLE);
        }

        public boolean tryAdvance(DoubleConsumer consumer) {
            if (consumer == null) throw new NullPointerException();
            long i = index, f = fence;
            if (i < f) {
                consumer.accept(RestThreadLocalRandom.current().internalNextDouble(origin, bound));
                index = i + 1;
                return true;
            }
            return false;
        }

        public void forEachRemaining(DoubleConsumer consumer) {
            if (consumer == null) throw new NullPointerException();
            long i = index, f = fence;
            if (i < f) {
                index = f;
                double o = origin, b = bound;
                RestThreadLocalRandom rng = RestThreadLocalRandom.current();
                do {
                    consumer.accept(rng.internalNextDouble(o, b));
                } while (++i < f);
            }
        }
    }


    /**
     * <code>getProbe</code>
     * <p>The get probe getter method.</p>
     * @return int <p>The get probe return object is <code>int</code> type.</p>
     */
    static final int getProbe() {
        return UNSAFE.getInt(Thread.currentThread(), PROBE);
    }

    /**
     * <code>advanceProbe</code>
     * <p>The advance probe method.</p>
     * @param probe int <p>The probe parameter is <code>int</code> type.</p>
     * @return int <p>The advance probe return object is <code>int</code> type.</p>
     */
    static final int advanceProbe(int probe) {
        probe ^= probe << 13;   // xor shift
        probe ^= probe >>> 17;
        probe ^= probe << 5;
        UNSAFE.putInt(Thread.currentThread(), PROBE, probe);
        return probe;
    }

    /**
     * <code>nextSecondarySeed</code>
     * <p>The next secondary seed method.</p>
     * @return int <p>The next secondary seed return object is <code>int</code> type.</p>
     */
    static final int nextSecondarySeed() {
        int r;
        Thread t = Thread.currentThread();
        if ((r = UNSAFE.getInt(t, SECONDARY)) != 0) {
            r ^= r << 13;   // xorshift
            r ^= r >>> 17;
            r ^= r << 5;
        } else {
            localInit();
            if ((r = (int) UNSAFE.getLong(t, SEED)) == 0)
                r = 1; // avoid zero
        }
        UNSAFE.putInt(t, SECONDARY, r);
        return r;
    }

    // Serialization support

    /**
     * <code>serialVersionUID</code>
     * <p>The constant <code>serialVersionUID</code> field.</p>
     */
    private static final long serialVersionUID = -5851777807851030925L;

    /**
     * <code>serialPersistentFields</code>
     * {@link java.io.ObjectStreamField} <p>The constant <code>serialPersistentFields</code> field.</p>
     * @see java.io.ObjectStreamField
     */
    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("rnd", long.class),
            new ObjectStreamField("initialized", boolean.class),
    };

    /**
     * <code>writeObject</code>
     * <p>The write object method.</p>
     * @param s {@link java.io.ObjectOutputStream} <p>The s parameter is <code>ObjectOutputStream</code> type.</p>
     * @throws IOException {@link java.io.IOException} <p>The io exception is <code>IOException</code> type.</p>
     * @see java.io.ObjectOutputStream
     * @see java.io.IOException
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {

        java.io.ObjectOutputStream.PutField fields = s.putFields();
        fields.put("rnd", UNSAFE.getLong(Thread.currentThread(), SEED));
        fields.put("initialized", true);
        s.writeFields();
    }

    /**
     * <code>readResolve</code>
     * <p>The read resolve method.</p>
     * @return {@link java.lang.Object} <p>The read resolve return object is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    private Object readResolve() {
        return current();
    }

    /**
     * <code>UNSAFE</code>
     * {@link sun.misc.Unsafe} <p>The constant <code>UNSAFE</code> field.</p>
     * @see sun.misc.Unsafe
     */
// Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;
    /**
     * <code>SEED</code>
     * <p>The constant <code>SEED</code> field.</p>
     */
    private static final long SEED;
    /**
     * <code>PROBE</code>
     * <p>The constant <code>PROBE</code> field.</p>
     */
    private static final long PROBE;
    /**
     * <code>SECONDARY</code>
     * <p>The constant <code>SECONDARY</code> field.</p>
     */
    private static final long SECONDARY;

    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> tk = Thread.class;
            SEED = UNSAFE.objectFieldOffset
                    (tk.getDeclaredField("threadLocalRandomSeed"));
            PROBE = UNSAFE.objectFieldOffset
                    (tk.getDeclaredField("threadLocalRandomProbe"));
            SECONDARY = UNSAFE.objectFieldOffset
                    (tk.getDeclaredField("threadLocalRandomSecondarySeed"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
