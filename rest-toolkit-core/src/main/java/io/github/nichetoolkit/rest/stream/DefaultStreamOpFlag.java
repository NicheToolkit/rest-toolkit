
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

import java.util.EnumMap;
import java.util.Map;

/**
 * <code>DefaultStreamOpFlag</code>
 * <p>The type default stream op flag enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
enum DefaultStreamOpFlag {

    /**
     * <code>DISTINCT</code>
     * <p>the Distinct default stream op flag field.</p>
     */
    DISTINCT(0,
             set(Type.SPLITERATOR).set(Type.STREAM).setAndClear()),

    /**
     * <code>SORTED</code>
     * <p>the Sorted default stream op flag field.</p>
     */
    SORTED(1,
           set(Type.SPLITERATOR).set(Type.STREAM).setAndClear()),

    /**
     * <code>ORDERED</code>
     * <p>the Ordered default stream op flag field.</p>
     */
    ORDERED(2,
            set(Type.SPLITERATOR).set(Type.STREAM).setAndClear().clear(Type.TERMINAL_OP)
                    .clear(Type.UPSTREAM_TERMINAL_OP)),

    /**
     * <code>SIZED</code>
     * <p>the Sized default stream op flag field.</p>
     */
    SIZED(3,
          set(Type.SPLITERATOR).set(Type.STREAM).clear(Type.OP)),

    /**
     * <code>SHORT_CIRCUIT</code>
     * <p>the Short circuit default stream op flag field.</p>
     */
    SHORT_CIRCUIT(12,
                  set(Type.OP).set(Type.TERMINAL_OP));

    /**
     * <code>Type</code>
     * <p>The type type enumeration.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    enum Type {
        /**
         * <code>SPLITERATOR</code>
         * <p>the Spliterator type field.</p>
         */
        SPLITERATOR,

        /**
         * <code>STREAM</code>
         * <p>the Stream type field.</p>
         */
        STREAM,

        /**
         * <code>OP</code>
         * <p>the Op type field.</p>
         */
        OP,

        /**
         * <code>TERMINAL_OP</code>
         * <p>the Terminal op type field.</p>
         */
        TERMINAL_OP,

        /**
         * <code>UPSTREAM_TERMINAL_OP</code>
         * <p>the Upstream terminal op type field.</p>
         */
        UPSTREAM_TERMINAL_OP
    }

    /**
     * <code>SET_BITS</code>
     * <p>the constant <code>SET_BITS</code> field.</p>
     */
    private static final int SET_BITS = 0b01;

    /**
     * <code>CLEAR_BITS</code>
     * <p>the constant <code>CLEAR_BITS</code> field.</p>
     */
    private static final int CLEAR_BITS = 0b10;

    /**
     * <code>PRESERVE_BITS</code>
     * <p>the constant <code>PRESERVE_BITS</code> field.</p>
     */
    private static final int PRESERVE_BITS = 0b11;

    /**
     * <code>set</code>
     * <p>the method.</p>
     * @param t {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type} <p>the t parameter is <code>Type</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.MaskBuilder} <p>the return object is <code>MaskBuilder</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type
     * @see io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.MaskBuilder
     */
    private static MaskBuilder set(Type t) {
        return new MaskBuilder(new EnumMap<>(Type.class)).set(t);
    }

    /**
     * <code>MaskBuilder</code>
     * <p>The type mask builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    private static class MaskBuilder {
        /**
         * <code>map</code>
         * {@link java.util.Map} <p>the <code>map</code> field.</p>
         * @see java.util.Map
         */
        final Map<Type, Integer> map;

        /**
         * <code>MaskBuilder</code>
         * Instantiates a new mask builder.
         * @param map {@link java.util.Map} <p>the map parameter is <code>Map</code> type.</p>
         * @see java.util.Map
         */
        MaskBuilder(Map<Type, Integer> map) {
            this.map = map;
        }

        /**
         * <code>mask</code>
         * <p>the method.</p>
         * @param t {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type} <p>the t parameter is <code>Type</code> type.</p>
         * @param i {@link java.lang.Integer} <p>the parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.MaskBuilder} <p>the return object is <code>MaskBuilder</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type
         * @see java.lang.Integer
         */
        MaskBuilder mask(Type t, Integer i) {
            map.put(t, i);
            return this;
        }

        /**
         * <code>set</code>
         * <p>the method.</p>
         * @param t {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type} <p>the t parameter is <code>Type</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.MaskBuilder} <p>the return object is <code>MaskBuilder</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type
         */
        MaskBuilder set(Type t) {
            return mask(t, SET_BITS);
        }

        /**
         * <code>clear</code>
         * <p>the method.</p>
         * @param t {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type} <p>the t parameter is <code>Type</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.MaskBuilder} <p>the return object is <code>MaskBuilder</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type
         */
        MaskBuilder clear(Type t) {
            return mask(t, CLEAR_BITS);
        }

        /**
         * <code>setAndClear</code>
         * <p>the and clear setter method.</p>
         * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.MaskBuilder} <p>the and clear return object is <code>MaskBuilder</code> type.</p>
         */
        MaskBuilder setAndClear() {
            return mask(Type.OP, PRESERVE_BITS);
        }

        /**
         * <code>build</code>
         * <p>the method.</p>
         * @return {@link java.util.Map} <p>the return object is <code>Map</code> type.</p>
         * @see java.util.Map
         */
        Map<Type, Integer> build() {
            for (Type t : Type.values()) {
                map.putIfAbsent(t, 0b00);
            }
            return map;
        }
    }

    /**
     * <code>maskTable</code>
     * {@link java.util.Map} <p>the <code>maskTable</code> field.</p>
     * @see java.util.Map
     */
    private final Map<Type, Integer> maskTable;

    /**
     * <code>bitPosition</code>
     * <p>the <code>bitPosition</code> field.</p>
     */
    private final int bitPosition;

    /**
     * <code>set</code>
     * <p>the <code>set</code> field.</p>
     */
    private final int set;

    /**
     * <code>clear</code>
     * <p>the <code>clear</code> field.</p>
     */
    private final int clear;

    /**
     * <code>preserve</code>
     * <p>the <code>preserve</code> field.</p>
     */
    private final int preserve;

    /**
     * <code>DefaultStreamOpFlag</code>
     * Instantiates a new default stream op flag.
     * @param position    int <p>the position parameter is <code>int</code> type.</p>
     * @param maskBuilder {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.MaskBuilder} <p>the mask builder parameter is <code>MaskBuilder</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.MaskBuilder
     */
    DefaultStreamOpFlag(int position, MaskBuilder maskBuilder) {
        this.maskTable = maskBuilder.build();
        // Two bits per flag
        position *= 2;
        this.bitPosition = position;
        this.set = SET_BITS << position;
        this.clear = CLEAR_BITS << position;
        this.preserve = PRESERVE_BITS << position;
    }

    /**
     * <code>set</code>
     * <p>the method.</p>
     * @return int <p>the return object is <code>int</code> type.</p>
     */
    int set() {
        return set;
    }

    /**
     * <code>clear</code>
     * <p>the method.</p>
     * @return int <p>the return object is <code>int</code> type.</p>
     */
    int clear() {
        return clear;
    }

    /**
     * <code>isStreamFlag</code>
     * <p>the stream flag method.</p>
     * @return boolean <p>the stream flag return object is <code>boolean</code> type.</p>
     */
    boolean isStreamFlag() {
        return maskTable.get(Type.STREAM) > 0;
    }

    /**
     * <code>isKnown</code>
     * <p>the known method.</p>
     * @param flags int <p>the flags parameter is <code>int</code> type.</p>
     * @return boolean <p>the known return object is <code>boolean</code> type.</p>
     */
    boolean isKnown(int flags) {
        return (flags & preserve) == set;
    }

    /**
     * <code>isCleared</code>
     * <p>the cleared method.</p>
     * @param flags int <p>the flags parameter is <code>int</code> type.</p>
     * @return boolean <p>the cleared return object is <code>boolean</code> type.</p>
     */
    boolean isCleared(int flags) {
        return (flags & preserve) == clear;
    }

    /**
     * <code>isPreserved</code>
     * <p>the preserved method.</p>
     * @param flags int <p>the flags parameter is <code>int</code> type.</p>
     * @return boolean <p>the preserved return object is <code>boolean</code> type.</p>
     */
    boolean isPreserved(int flags) {
        return (flags & preserve) == preserve;
    }

    /**
     * <code>canSet</code>
     * <p>the set method.</p>
     * @param t {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type} <p>the t parameter is <code>Type</code> type.</p>
     * @return boolean <p>the set return object is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type
     */
    boolean canSet(Type t) {
        return (maskTable.get(t) & SET_BITS) > 0;
    }

    /**
     * <code>SPLITERATOR_CHARACTERISTICS_MASK</code>
     * <p>the <code>SPLITERATOR_CHARACTERISTICS_MASK</code> field.</p>
     */
    static final int SPLITERATOR_CHARACTERISTICS_MASK = createMask(Type.SPLITERATOR);

    /**
     * <code>STREAM_MASK</code>
     * <p>the <code>STREAM_MASK</code> field.</p>
     */
    static final int STREAM_MASK = createMask(Type.STREAM);

    /**
     * <code>OP_MASK</code>
     * <p>the <code>OP_MASK</code> field.</p>
     */
    static final int OP_MASK = createMask(Type.OP);

    /**
     * <code>TERMINAL_OP_MASK</code>
     * <p>the <code>TERMINAL_OP_MASK</code> field.</p>
     */
    static final int TERMINAL_OP_MASK = createMask(Type.TERMINAL_OP);

    /**
     * <code>UPSTREAM_TERMINAL_OP_MASK</code>
     * <p>the <code>UPSTREAM_TERMINAL_OP_MASK</code> field.</p>
     */
    static final int UPSTREAM_TERMINAL_OP_MASK = createMask(Type.UPSTREAM_TERMINAL_OP);

    /**
     * <code>createMask</code>
     * <p>the mask method.</p>
     * @param t {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type} <p>the t parameter is <code>Type</code> type.</p>
     * @return int <p>the mask return object is <code>int</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type
     */
    private static int createMask(Type t) {
        int mask = 0;
        for (DefaultStreamOpFlag flag : DefaultStreamOpFlag.values()) {
            mask |= flag.maskTable.get(t) << flag.bitPosition;
        }
        return mask;
    }

    /**
     * <code>FLAG_MASK</code>
     * <p>the constant <code>FLAG_MASK</code> field.</p>
     */
    private static final int FLAG_MASK = createFlagMask();

    /**
     * <code>createFlagMask</code>
     * <p>the flag mask method.</p>
     * @return int <p>the flag mask return object is <code>int</code> type.</p>
     */
    private static int createFlagMask() {
        int mask = 0;
        for (DefaultStreamOpFlag flag : DefaultStreamOpFlag.values()) {
            mask |= flag.preserve;
        }
        return mask;
    }

    /**
     * <code>FLAG_MASK_IS</code>
     * <p>the constant <code>FLAG_MASK_IS</code> field.</p>
     */
    private static final int FLAG_MASK_IS = STREAM_MASK;

    /**
     * <code>FLAG_MASK_NOT</code>
     * <p>the constant <code>FLAG_MASK_NOT</code> field.</p>
     */
    private static final int FLAG_MASK_NOT = STREAM_MASK << 1;

    /**
     * <code>INITIAL_OPS_VALUE</code>
     * <p>the <code>INITIAL_OPS_VALUE</code> field.</p>
     */
    static final int INITIAL_OPS_VALUE = FLAG_MASK_IS | FLAG_MASK_NOT;

    /**
     * <code>IS_DISTINCT</code>
     * <p>the <code>IS_DISTINCT</code> field.</p>
     */
    static final int IS_DISTINCT = DISTINCT.set;

    /**
     * <code>NOT_DISTINCT</code>
     * <p>the <code>NOT_DISTINCT</code> field.</p>
     */
    static final int NOT_DISTINCT = DISTINCT.clear;

    /**
     * <code>IS_SORTED</code>
     * <p>the <code>IS_SORTED</code> field.</p>
     */
    static final int IS_SORTED = SORTED.set;

    /**
     * <code>NOT_SORTED</code>
     * <p>the <code>NOT_SORTED</code> field.</p>
     */
    static final int NOT_SORTED = SORTED.clear;

    /**
     * <code>IS_ORDERED</code>
     * <p>the <code>IS_ORDERED</code> field.</p>
     */
    static final int IS_ORDERED = ORDERED.set;

    /**
     * <code>NOT_ORDERED</code>
     * <p>the <code>NOT_ORDERED</code> field.</p>
     */
    static final int NOT_ORDERED = ORDERED.clear;

    /**
     * <code>IS_SIZED</code>
     * <p>the <code>IS_SIZED</code> field.</p>
     */
    static final int IS_SIZED = SIZED.set;

    /**
     * <code>NOT_SIZED</code>
     * <p>the <code>NOT_SIZED</code> field.</p>
     */
    static final int NOT_SIZED = SIZED.clear;

    /**
     * <code>IS_SHORT_CIRCUIT</code>
     * <p>the <code>IS_SHORT_CIRCUIT</code> field.</p>
     */
    static final int IS_SHORT_CIRCUIT = SHORT_CIRCUIT.set;

    /**
     * <code>getMask</code>
     * <p>the mask getter method.</p>
     * @param flags int <p>the flags parameter is <code>int</code> type.</p>
     * @return int <p>the mask return object is <code>int</code> type.</p>
     */
    private static int getMask(int flags) {
        return (flags == 0)
               ? FLAG_MASK
               : ~(flags | ((FLAG_MASK_IS & flags) << 1) | ((FLAG_MASK_NOT & flags) >> 1));
    }

    /**
     * <code>combineOpFlags</code>
     * <p>the op flags method.</p>
     * @param newStreamOrOpFlags int <p>the new stream or op flags parameter is <code>int</code> type.</p>
     * @param prevCombOpFlags    int <p>the prev comb op flags parameter is <code>int</code> type.</p>
     * @return int <p>the op flags return object is <code>int</code> type.</p>
     */
    static int combineOpFlags(int newStreamOrOpFlags, int prevCombOpFlags) {
        return (prevCombOpFlags & DefaultStreamOpFlag.getMask(newStreamOrOpFlags)) | newStreamOrOpFlags;
    }

    /**
     * <code>toStreamFlags</code>
     * <p>the stream flags method.</p>
     * @param combOpFlags int <p>the comb op flags parameter is <code>int</code> type.</p>
     * @return int <p>the stream flags return object is <code>int</code> type.</p>
     */
    static int toStreamFlags(int combOpFlags) {
        return ((~combOpFlags) >> 1) & FLAG_MASK_IS & combOpFlags;
    }

    /**
     * <code>toCharacteristics</code>
     * <p>the characteristics method.</p>
     * @param streamFlags int <p>the stream flags parameter is <code>int</code> type.</p>
     * @return int <p>the characteristics return object is <code>int</code> type.</p>
     */
    static int toCharacteristics(int streamFlags) {
        return streamFlags & SPLITERATOR_CHARACTERISTICS_MASK;
    }

    /**
     * <code>fromCharacteristics</code>
     * <p>the characteristics method.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @return int <p>the characteristics return object is <code>int</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    static int fromCharacteristics(DefaultSpliterator<?> spliterator) throws RestException {
        int characteristics = spliterator.characteristics();
        if ((characteristics & DefaultSpliterator.SORTED) != 0 && spliterator.getComparator() != null) {
            return characteristics & SPLITERATOR_CHARACTERISTICS_MASK & ~DefaultSpliterator.SORTED;
        }
        else {
            return characteristics & SPLITERATOR_CHARACTERISTICS_MASK;
        }
    }

    /**
     * <code>fromCharacteristics</code>
     * <p>the characteristics method.</p>
     * @param characteristics int <p>the characteristics parameter is <code>int</code> type.</p>
     * @return int <p>the characteristics return object is <code>int</code> type.</p>
     */
    static int fromCharacteristics(int characteristics) {
        return characteristics & SPLITERATOR_CHARACTERISTICS_MASK;
    }
}
