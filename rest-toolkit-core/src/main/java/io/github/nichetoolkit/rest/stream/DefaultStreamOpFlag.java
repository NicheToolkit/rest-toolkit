
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

import java.util.EnumMap;
import java.util.Map;

enum DefaultStreamOpFlag {

    DISTINCT(0,
             set(Type.SPLITERATOR).set(Type.STREAM).setAndClear(Type.OP)),

    SORTED(1,
           set(Type.SPLITERATOR).set(Type.STREAM).setAndClear(Type.OP)),

    ORDERED(2,
            set(Type.SPLITERATOR).set(Type.STREAM).setAndClear(Type.OP).clear(Type.TERMINAL_OP)
                    .clear(Type.UPSTREAM_TERMINAL_OP)),

    SIZED(3,
          set(Type.SPLITERATOR).set(Type.STREAM).clear(Type.OP)),

    SHORT_CIRCUIT(12,
                  set(Type.OP).set(Type.TERMINAL_OP));

    enum Type {
        SPLITERATOR,

        STREAM,

        OP,

        TERMINAL_OP,

        UPSTREAM_TERMINAL_OP
    }

    private static final int SET_BITS = 0b01;

    private static final int CLEAR_BITS = 0b10;

    private static final int PRESERVE_BITS = 0b11;

    private static MaskBuilder set(Type t) {
        return new MaskBuilder(new EnumMap<>(Type.class)).set(t);
    }

    private static class MaskBuilder {
        final Map<Type, Integer> map;

        MaskBuilder(Map<Type, Integer> map) {
            this.map = map;
        }

        MaskBuilder mask(Type t, Integer i) {
            map.put(t, i);
            return this;
        }

        MaskBuilder set(Type t) {
            return mask(t, SET_BITS);
        }

        MaskBuilder clear(Type t) {
            return mask(t, CLEAR_BITS);
        }

        MaskBuilder setAndClear(Type t) {
            return mask(t, PRESERVE_BITS);
        }

        Map<Type, Integer> build() {
            for (Type t : Type.values()) {
                map.putIfAbsent(t, 0b00);
            }
            return map;
        }
    }

    private final Map<Type, Integer> maskTable;

    private final int bitPosition;

    private final int set;

    private final int clear;

    private final int preserve;

    DefaultStreamOpFlag(int position, MaskBuilder maskBuilder) {
        this.maskTable = maskBuilder.build();
        // Two bits per flag
        position *= 2;
        this.bitPosition = position;
        this.set = SET_BITS << position;
        this.clear = CLEAR_BITS << position;
        this.preserve = PRESERVE_BITS << position;
    }

    int set() {
        return set;
    }

    int clear() {
        return clear;
    }

    boolean isStreamFlag() {
        return maskTable.get(Type.STREAM) > 0;
    }

    boolean isKnown(int flags) {
        return (flags & preserve) == set;
    }

    boolean isCleared(int flags) {
        return (flags & preserve) == clear;
    }

    boolean isPreserved(int flags) {
        return (flags & preserve) == preserve;
    }

    boolean canSet(Type t) {
        return (maskTable.get(t) & SET_BITS) > 0;
    }

    static final int SPLITERATOR_CHARACTERISTICS_MASK = createMask(Type.SPLITERATOR);

    static final int STREAM_MASK = createMask(Type.STREAM);

    static final int OP_MASK = createMask(Type.OP);

    static final int TERMINAL_OP_MASK = createMask(Type.TERMINAL_OP);

    static final int UPSTREAM_TERMINAL_OP_MASK = createMask(Type.UPSTREAM_TERMINAL_OP);

    private static int createMask(Type t) {
        int mask = 0;
        for (DefaultStreamOpFlag flag : DefaultStreamOpFlag.values()) {
            mask |= flag.maskTable.get(t) << flag.bitPosition;
        }
        return mask;
    }

    private static final int FLAG_MASK = createFlagMask();

    private static int createFlagMask() {
        int mask = 0;
        for (DefaultStreamOpFlag flag : DefaultStreamOpFlag.values()) {
            mask |= flag.preserve;
        }
        return mask;
    }

    private static final int FLAG_MASK_IS = STREAM_MASK;

    private static final int FLAG_MASK_NOT = STREAM_MASK << 1;

    static final int INITIAL_OPS_VALUE = FLAG_MASK_IS | FLAG_MASK_NOT;

    static final int IS_DISTINCT = DISTINCT.set;

    static final int NOT_DISTINCT = DISTINCT.clear;

    static final int IS_SORTED = SORTED.set;

    static final int NOT_SORTED = SORTED.clear;

    static final int IS_ORDERED = ORDERED.set;

    static final int NOT_ORDERED = ORDERED.clear;

    static final int IS_SIZED = SIZED.set;

    static final int NOT_SIZED = SIZED.clear;

    static final int IS_SHORT_CIRCUIT = SHORT_CIRCUIT.set;

    private static int getMask(int flags) {
        return (flags == 0)
               ? FLAG_MASK
               : ~(flags | ((FLAG_MASK_IS & flags) << 1) | ((FLAG_MASK_NOT & flags) >> 1));
    }

    static int combineOpFlags(int newStreamOrOpFlags, int prevCombOpFlags) {
        return (prevCombOpFlags & DefaultStreamOpFlag.getMask(newStreamOrOpFlags)) | newStreamOrOpFlags;
    }

    static int toStreamFlags(int combOpFlags) {
        return ((~combOpFlags) >> 1) & FLAG_MASK_IS & combOpFlags;
    }

    static int toCharacteristics(int streamFlags) {
        return streamFlags & SPLITERATOR_CHARACTERISTICS_MASK;
    }

    static int fromCharacteristics(DefaultSpliterator<?> spliterator) throws RestException {
        int characteristics = spliterator.characteristics();
        if ((characteristics & DefaultSpliterator.SORTED) != 0 && spliterator.getComparator() != null) {
            return characteristics & SPLITERATOR_CHARACTERISTICS_MASK & ~DefaultSpliterator.SORTED;
        }
        else {
            return characteristics & SPLITERATOR_CHARACTERISTICS_MASK;
        }
    }

    static int fromCharacteristics(int characteristics) {
        return characteristics & SPLITERATOR_CHARACTERISTICS_MASK;
    }
}
