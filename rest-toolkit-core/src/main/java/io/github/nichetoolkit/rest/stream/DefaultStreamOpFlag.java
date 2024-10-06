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
     * <p>The distinct default stream op flag field.</p>
     */
    DISTINCT(0,
             set(Type.SPLITERATOR).set(Type.STREAM).setAndClear()),

    /**
     * <code>SORTED</code>
     * <p>The sorted default stream op flag field.</p>
     */
    SORTED(1,
           set(Type.SPLITERATOR).set(Type.STREAM).setAndClear()),

    /**
     * <code>ORDERED</code>
     * <p>The ordered default stream op flag field.</p>
     */
    ORDERED(2,
            set(Type.SPLITERATOR).set(Type.STREAM).setAndClear().clear(Type.TERMINAL_OP)
                    .clear(Type.UPSTREAM_TERMINAL_OP)),

    /**
     * <code>SIZED</code>
     * <p>The sized default stream op flag field.</p>
     */
    SIZED(3,
          set(Type.SPLITERATOR).set(Type.STREAM).clear(Type.OP)),

    /**
     * <code>SHORT_CIRCUIT</code>
     * <p>The short circuit default stream op flag field.</p>
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
         * <p>The spliterator type field.</p>
         */
        SPLITERATOR,

        /**
         * <code>STREAM</code>
         * <p>The stream type field.</p>
         */
        STREAM,

        /**
         * <code>OP</code>
         * <p>The op type field.</p>
         */
        OP,

        /**
         * <code>TERMINAL_OP</code>
         * <p>The terminal op type field.</p>
         */
        TERMINAL_OP,

        /**
         * <code>UPSTREAM_TERMINAL_OP</code>
         * <p>The upstream terminal op type field.</p>
         */
        UPSTREAM_TERMINAL_OP
    }

    /**
     * <code>SET_BITS</code>
     * <p>The constant <code>SET_BITS</code> field.</p>
     */
    private static final int SET_BITS = 0b01;

    /**
     * <code>CLEAR_BITS</code>
     * <p>The constant <code>CLEAR_BITS</code> field.</p>
     */
    private static final int CLEAR_BITS = 0b10;

    /**
     * <code>PRESERVE_BITS</code>
     * <p>The constant <code>PRESERVE_BITS</code> field.</p>
     */
    private static final int PRESERVE_BITS = 0b11;

    /**
     * <code>set</code>
     * <p>The method.</p>
     * @param t {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type} <p>The t parameter is <code>Type</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.MaskBuilder} <p>The return object is <code>MaskBuilder</code> type.</p>
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
         * {@link java.util.Map} <p>The <code>map</code> field.</p>
         * @see java.util.Map
         */
        final Map<Type, Integer> map;

        /**
         * <code>MaskBuilder</code>
         * <p>Instantiates a new mask builder.</p>
         * @param map {@link java.util.Map} <p>The map parameter is <code>Map</code> type.</p>
         * @see java.util.Map
         */
        MaskBuilder(Map<Type, Integer> map) {
            this.map = map;
        }

        /**
         * <code>mask</code>
         * <p>The method.</p>
         * @param t {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type} <p>The t parameter is <code>Type</code> type.</p>
         * @param i {@link java.lang.Integer} <p>The parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.MaskBuilder} <p>The return object is <code>MaskBuilder</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type
         * @see java.lang.Integer
         */
        MaskBuilder mask(Type t, Integer i) {
            map.put(t, i);
            return this;
        }

        /**
         * <code>set</code>
         * <p>The method.</p>
         * @param t {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type} <p>The t parameter is <code>Type</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.MaskBuilder} <p>The return object is <code>MaskBuilder</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type
         */
        MaskBuilder set(Type t) {
            return mask(t, SET_BITS);
        }

        /**
         * <code>clear</code>
         * <p>The method.</p>
         * @param t {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type} <p>The t parameter is <code>Type</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.MaskBuilder} <p>The return object is <code>MaskBuilder</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type
         */
        MaskBuilder clear(Type t) {
            return mask(t, CLEAR_BITS);
        }

        /**
         * <code>setAndClear</code>
         * <p>The and clear setter method.</p>
         * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.MaskBuilder} <p>The and clear return object is <code>MaskBuilder</code> type.</p>
         */
        MaskBuilder setAndClear() {
            return mask(Type.OP, PRESERVE_BITS);
        }

        /**
         * <code>build</code>
         * <p>The method.</p>
         * @return {@link java.util.Map} <p>The return object is <code>Map</code> type.</p>
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
     * {@link java.util.Map} <p>The <code>maskTable</code> field.</p>
     * @see java.util.Map
     */
    private final Map<Type, Integer> maskTable;

    /**
     * <code>bitPosition</code>
     * <p>The <code>bitPosition</code> field.</p>
     */
    private final int bitPosition;

    /**
     * <code>set</code>
     * <p>The <code>set</code> field.</p>
     */
    private final int set;

    /**
     * <code>clear</code>
     * <p>The <code>clear</code> field.</p>
     */
    private final int clear;

    /**
     * <code>preserve</code>
     * <p>The <code>preserve</code> field.</p>
     */
    private final int preserve;

    /**
     * <code>DefaultStreamOpFlag</code>
     * <p>Instantiates a new default stream op flag.</p>
     * @param position    int <p>The position parameter is <code>int</code> type.</p>
     * @param maskBuilder {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.MaskBuilder} <p>The mask builder parameter is <code>MaskBuilder</code> type.</p>
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
     * <p>The method.</p>
     * @return int <p>The return object is <code>int</code> type.</p>
     */
    int set() {
        return set;
    }

    /**
     * <code>clear</code>
     * <p>The method.</p>
     * @return int <p>The return object is <code>int</code> type.</p>
     */
    int clear() {
        return clear;
    }

    /**
     * <code>isStreamFlag</code>
     * <p>The stream flag method.</p>
     * @return boolean <p>The stream flag return object is <code>boolean</code> type.</p>
     */
    boolean isStreamFlag() {
        return maskTable.get(Type.STREAM) > 0;
    }

    /**
     * <code>isKnown</code>
     * <p>The known method.</p>
     * @param flags int <p>The flags parameter is <code>int</code> type.</p>
     * @return boolean <p>The known return object is <code>boolean</code> type.</p>
     */
    boolean isKnown(int flags) {
        return (flags & preserve) == set;
    }

    /**
     * <code>isCleared</code>
     * <p>The cleared method.</p>
     * @param flags int <p>The flags parameter is <code>int</code> type.</p>
     * @return boolean <p>The cleared return object is <code>boolean</code> type.</p>
     */
    boolean isCleared(int flags) {
        return (flags & preserve) == clear;
    }

    /**
     * <code>isPreserved</code>
     * <p>The preserved method.</p>
     * @param flags int <p>The flags parameter is <code>int</code> type.</p>
     * @return boolean <p>The preserved return object is <code>boolean</code> type.</p>
     */
    boolean isPreserved(int flags) {
        return (flags & preserve) == preserve;
    }

    /**
     * <code>canSet</code>
     * <p>The set method.</p>
     * @param t {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type} <p>The t parameter is <code>Type</code> type.</p>
     * @return boolean <p>The set return object is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type
     */
    boolean canSet(Type t) {
        return (maskTable.get(t) & SET_BITS) > 0;
    }

    /**
     * <code>SPLITERATOR_CHARACTERISTICS_MASK</code>
     * <p>The <code>SPLITERATOR_CHARACTERISTICS_MASK</code> field.</p>
     */
    static final int SPLITERATOR_CHARACTERISTICS_MASK = createMask(Type.SPLITERATOR);

    /**
     * <code>STREAM_MASK</code>
     * <p>The <code>STREAM_MASK</code> field.</p>
     */
    static final int STREAM_MASK = createMask(Type.STREAM);

    /**
     * <code>OP_MASK</code>
     * <p>The <code>OP_MASK</code> field.</p>
     */
    static final int OP_MASK = createMask(Type.OP);

    /**
     * <code>TERMINAL_OP_MASK</code>
     * <p>The <code>TERMINAL_OP_MASK</code> field.</p>
     */
    static final int TERMINAL_OP_MASK = createMask(Type.TERMINAL_OP);

    /**
     * <code>UPSTREAM_TERMINAL_OP_MASK</code>
     * <p>The <code>UPSTREAM_TERMINAL_OP_MASK</code> field.</p>
     */
    static final int UPSTREAM_TERMINAL_OP_MASK = createMask(Type.UPSTREAM_TERMINAL_OP);

    /**
     * <code>createMask</code>
     * <p>The mask method.</p>
     * @param t {@link io.github.nichetoolkit.rest.stream.DefaultStreamOpFlag.Type} <p>The t parameter is <code>Type</code> type.</p>
     * @return int <p>The mask return object is <code>int</code> type.</p>
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
     * <p>The constant <code>FLAG_MASK</code> field.</p>
     */
    private static final int FLAG_MASK = createFlagMask();

    /**
     * <code>createFlagMask</code>
     * <p>The flag mask method.</p>
     * @return int <p>The flag mask return object is <code>int</code> type.</p>
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
     * <p>The constant <code>FLAG_MASK_IS</code> field.</p>
     */
    private static final int FLAG_MASK_IS = STREAM_MASK;

    /**
     * <code>FLAG_MASK_NOT</code>
     * <p>The constant <code>FLAG_MASK_NOT</code> field.</p>
     */
    private static final int FLAG_MASK_NOT = STREAM_MASK << 1;

    /**
     * <code>INITIAL_OPS_VALUE</code>
     * <p>The <code>INITIAL_OPS_VALUE</code> field.</p>
     */
    static final int INITIAL_OPS_VALUE = FLAG_MASK_IS | FLAG_MASK_NOT;

    /**
     * <code>IS_DISTINCT</code>
     * <p>The <code>IS_DISTINCT</code> field.</p>
     */
    static final int IS_DISTINCT = DISTINCT.set;

    /**
     * <code>NOT_DISTINCT</code>
     * <p>The <code>NOT_DISTINCT</code> field.</p>
     */
    static final int NOT_DISTINCT = DISTINCT.clear;

    /**
     * <code>IS_SORTED</code>
     * <p>The <code>IS_SORTED</code> field.</p>
     */
    static final int IS_SORTED = SORTED.set;

    /**
     * <code>NOT_SORTED</code>
     * <p>The <code>NOT_SORTED</code> field.</p>
     */
    static final int NOT_SORTED = SORTED.clear;

    /**
     * <code>IS_ORDERED</code>
     * <p>The <code>IS_ORDERED</code> field.</p>
     */
    static final int IS_ORDERED = ORDERED.set;

    /**
     * <code>NOT_ORDERED</code>
     * <p>The <code>NOT_ORDERED</code> field.</p>
     */
    static final int NOT_ORDERED = ORDERED.clear;

    /**
     * <code>IS_SIZED</code>
     * <p>The <code>IS_SIZED</code> field.</p>
     */
    static final int IS_SIZED = SIZED.set;

    /**
     * <code>NOT_SIZED</code>
     * <p>The <code>NOT_SIZED</code> field.</p>
     */
    static final int NOT_SIZED = SIZED.clear;

    /**
     * <code>IS_SHORT_CIRCUIT</code>
     * <p>The <code>IS_SHORT_CIRCUIT</code> field.</p>
     */
    static final int IS_SHORT_CIRCUIT = SHORT_CIRCUIT.set;

    /**
     * <code>getMask</code>
     * <p>The mask getter method.</p>
     * @param flags int <p>The flags parameter is <code>int</code> type.</p>
     * @return int <p>The mask return object is <code>int</code> type.</p>
     */
    private static int getMask(int flags) {
        return (flags == 0)
               ? FLAG_MASK
               : ~(flags | ((FLAG_MASK_IS & flags) << 1) | ((FLAG_MASK_NOT & flags) >> 1));
    }

    /**
     * <code>combineOpFlags</code>
     * <p>The op flags method.</p>
     * @param newStreamOrOpFlags int <p>The new stream or op flags parameter is <code>int</code> type.</p>
     * @param prevCombOpFlags    int <p>The prev comb op flags parameter is <code>int</code> type.</p>
     * @return int <p>The op flags return object is <code>int</code> type.</p>
     */
    static int combineOpFlags(int newStreamOrOpFlags, int prevCombOpFlags) {
        return (prevCombOpFlags & DefaultStreamOpFlag.getMask(newStreamOrOpFlags)) | newStreamOrOpFlags;
    }

    /**
     * <code>toStreamFlags</code>
     * <p>The stream flags method.</p>
     * @param combOpFlags int <p>The comb op flags parameter is <code>int</code> type.</p>
     * @return int <p>The stream flags return object is <code>int</code> type.</p>
     */
    static int toStreamFlags(int combOpFlags) {
        return ((~combOpFlags) >> 1) & FLAG_MASK_IS & combOpFlags;
    }

    /**
     * <code>toCharacteristics</code>
     * <p>The characteristics method.</p>
     * @param streamFlags int <p>The stream flags parameter is <code>int</code> type.</p>
     * @return int <p>The characteristics return object is <code>int</code> type.</p>
     */
    static int toCharacteristics(int streamFlags) {
        return streamFlags & SPLITERATOR_CHARACTERISTICS_MASK;
    }

    /**
     * <code>fromCharacteristics</code>
     * <p>The characteristics method.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @return int <p>The characteristics return object is <code>int</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <p>The characteristics method.</p>
     * @param characteristics int <p>The characteristics parameter is <code>int</code> type.</p>
     * @return int <p>The characteristics return object is <code>int</code> type.</p>
     */
    static int fromCharacteristics(int characteristics) {
        return characteristics & SPLITERATOR_CHARACTERISTICS_MASK;
    }
}
