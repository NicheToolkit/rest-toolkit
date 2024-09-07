
package io.github.nichetoolkit.rest.stream;

abstract class DefaultAbstractSpinedBuffer {
    public static final int MIN_CHUNK_POWER = 4;

    public static final int MIN_CHUNK_SIZE = 1 << MIN_CHUNK_POWER;

    public static final int MAX_CHUNK_POWER = 30;

    public static final int MIN_SPINE_SIZE = 8;


    protected final int initialChunkPower;

    protected int elementIndex;

    protected int spineIndex;

    protected long[] priorElementCount;

    protected DefaultAbstractSpinedBuffer() {
        this.initialChunkPower = MIN_CHUNK_POWER;
    }

    protected DefaultAbstractSpinedBuffer(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);

        this.initialChunkPower = Math.max(MIN_CHUNK_POWER,
                Integer.SIZE - Integer.numberOfLeadingZeros(initialCapacity - 1));
    }

    public boolean isEmpty() {
        return (spineIndex == 0) && (elementIndex == 0);
    }

    public long count() {
        return (spineIndex == 0)
                ? elementIndex
                : priorElementCount[spineIndex] + elementIndex;
    }

    protected int chunkSize(int n) {
        int power = (n == 0 || n == 1)
                ? initialChunkPower
                : Math.min(initialChunkPower + n - 1, DefaultAbstractSpinedBuffer.MAX_CHUNK_POWER);
        return 1 << power;
    }

    public abstract void clear();
}
