/*
 * Copyright (c) 2012, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package io.github.nichetoolkit.rest.stream;

import java.util.EnumMap;
import java.util.Map;
import java.util.Spliterator;

enum DefaultStreamOpFlag {

    /*
     * Each characteristic takes up 2 bits in a bit set to accommodate
     * preserving, clearing and setting/injecting information.
     *
     * This applies to stream flags, intermediate/terminal operation flags, and
     * combined stream and operation flags. Even though the former only requires
     * 1 bit of information per characteristic, is it more efficient when
     * combining flags to align set and inject bits.
     *
     * Characteristics belong to certain types, see the Type enum. Bit masks for
     * the types are constructed as per the following table:
     *
     *                        DISTINCT  SORTED  ORDERED  SIZED  SHORT_CIRCUIT
     *          SPLITERATOR      01       01       01      01        00
     *               STREAM      01       01       01      01        00
     *                   OP      11       11       11      10        01
     *          TERMINAL_OP      00       00       10      00        01
     * UPSTREAM_TERMINAL_OP      00       00       10      00        00
     *
     * 01 = set/inject
     * 10 = clear
     * 11 = preserve
     *
     * Construction of the columns is performed using a simple builder for
     * non-zero values.
     */


    // The following flags correspond to characteristics on Spliterator
    // and the values MUST be equal.
    //

    // 0, 0x00000001
    // Matches Spliterator.DISTINCT
    DISTINCT(0,
             set(Type.SPLITERATOR).set(Type.STREAM).setAndClear(Type.OP)),

    // 1, 0x00000004
    // Matches Spliterator.SORTED
    SORTED(1,
           set(Type.SPLITERATOR).set(Type.STREAM).setAndClear(Type.OP)),

    // 2, 0x00000010
    // Matches Spliterator.ORDERED
    ORDERED(2,
            set(Type.SPLITERATOR).set(Type.STREAM).setAndClear(Type.OP).clear(Type.TERMINAL_OP)
                    .clear(Type.UPSTREAM_TERMINAL_OP)),

    // 3, 0x00000040
    // Matches Spliterator.SIZED
    SIZED(3,
          set(Type.SPLITERATOR).set(Type.STREAM).clear(Type.OP)),

    // The following Spliterator characteristics are not currently used but a
    // gap in the bit set is deliberately retained to enable corresponding
    // stream flags if//when required without modification to other flag values.
    //
    // 4, 0x00000100 NONNULL(4, ...
    // 5, 0x00000400 IMMUTABLE(5, ...
    // 6, 0x00001000 CONCURRENT(6, ...
    // 7, 0x00004000 SUBSIZED(7, ...

    // The following 4 flags are currently undefined and a free for any further
    // spliterator characteristics.
    //
    //  8, 0x00010000
    //  9, 0x00040000
    // 10, 0x00100000
    // 11, 0x00400000

    // The following flags are specific to streams and operations
    //

    // 12, 0x01000000
    SHORT_CIRCUIT(12,
                  set(Type.OP).set(Type.TERMINAL_OP));

    // The following 2 flags are currently undefined and a free for any further
    // stream flags if/when required
    //
    // 13, 0x04000000
    // 14, 0x10000000
    // 15, 0x40000000

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

    private DefaultStreamOpFlag(int position, MaskBuilder maskBuilder) {
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
        // 0x01 or 0x10 nibbles are transformed to 0x11
        // 0x00 nibbles remain unchanged
        // Then all the bits are flipped
        // Then the result is logically or'ed with the operation flags.
        return (prevCombOpFlags & DefaultStreamOpFlag.getMask(newStreamOrOpFlags)) | newStreamOrOpFlags;
    }

    static int toStreamFlags(int combOpFlags) {
        // By flipping the nibbles 0x11 become 0x00 and 0x01 become 0x10
        // Shift left 1 to restore set flags and mask off anything other than the set flags
        return ((~combOpFlags) >> 1) & FLAG_MASK_IS & combOpFlags;
    }

    static int toCharacteristics(int streamFlags) {
        return streamFlags & SPLITERATOR_CHARACTERISTICS_MASK;
    }

    static int fromCharacteristics(Spliterator<?> spliterator) {
        int characteristics = spliterator.characteristics();
        if ((characteristics & Spliterator.SORTED) != 0 && spliterator.getComparator() != null) {
            // Do not propagate the SORTED characteristic if it does not correspond
            // to a natural sort order
            return characteristics & SPLITERATOR_CHARACTERISTICS_MASK & ~Spliterator.SORTED;
        }
        else {
            return characteristics & SPLITERATOR_CHARACTERISTICS_MASK;
        }
    }

    static int fromCharacteristics(int characteristics) {
        return characteristics & SPLITERATOR_CHARACTERISTICS_MASK;
    }
}
