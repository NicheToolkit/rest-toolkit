package io.github.nichetoolkit.rest.stream;

/**
 * <code>DefaultAbstractSpinedBuffer</code>
 * <p>The type default abstract spined buffer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
abstract class DefaultAbstractSpinedBuffer {
    /**
     * <code>MIN_CHUNK_POWER</code>
     * <p>the constant <code>MIN_CHUNK_POWER</code> field.</p>
     */
    public static final int MIN_CHUNK_POWER = 4;

    /**
     * <code>MIN_CHUNK_SIZE</code>
     * <p>the constant <code>MIN_CHUNK_SIZE</code> field.</p>
     */
    public static final int MIN_CHUNK_SIZE = 1 << MIN_CHUNK_POWER;

    /**
     * <code>MAX_CHUNK_POWER</code>
     * <p>the constant <code>MAX_CHUNK_POWER</code> field.</p>
     */
    public static final int MAX_CHUNK_POWER = 30;

    /**
     * <code>MIN_SPINE_SIZE</code>
     * <p>the constant <code>MIN_SPINE_SIZE</code> field.</p>
     */
    public static final int MIN_SPINE_SIZE = 8;


    /**
     * <code>initialChunkPower</code>
     * <p>the <code>initialChunkPower</code> field.</p>
     */
    protected final int initialChunkPower;

    /**
     * <code>elementIndex</code>
     * <p>the <code>elementIndex</code> field.</p>
     */
    protected int elementIndex;

    /**
     * <code>spineIndex</code>
     * <p>the <code>spineIndex</code> field.</p>
     */
    protected int spineIndex;

    /**
     * <code>priorElementCount</code>
     * <p>the <code>priorElementCount</code> field.</p>
     */
    protected long[] priorElementCount;

    /**
     * <code>DefaultAbstractSpinedBuffer</code>
     * Instantiates a new default abstract spined buffer.
     */
    protected DefaultAbstractSpinedBuffer() {
        this.initialChunkPower = MIN_CHUNK_POWER;
    }

    /**
     * <code>DefaultAbstractSpinedBuffer</code>
     * Instantiates a new default abstract spined buffer.
     * @param initialCapacity int <p>the initial capacity parameter is <code>int</code> type.</p>
     */
    protected DefaultAbstractSpinedBuffer(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);

        this.initialChunkPower = Math.max(MIN_CHUNK_POWER,
                Integer.SIZE - Integer.numberOfLeadingZeros(initialCapacity - 1));
    }

    /**
     * <code>isEmpty</code>
     * <p>the empty method.</p>
     * @return boolean <p>the empty return object is <code>boolean</code> type.</p>
     */
    public boolean isEmpty() {
        return (spineIndex == 0) && (elementIndex == 0);
    }

    /**
     * <code>count</code>
     * <p>the method.</p>
     * @return long <p>the return object is <code>long</code> type.</p>
     */
    public long count() {
        return (spineIndex == 0)
                ? elementIndex
                : priorElementCount[spineIndex] + elementIndex;
    }

    /**
     * <code>chunkSize</code>
     * <p>the size method.</p>
     * @param n int <p>the n parameter is <code>int</code> type.</p>
     * @return int <p>the size return object is <code>int</code> type.</p>
     */
    protected int chunkSize(int n) {
        int power = (n == 0 || n == 1)
                ? initialChunkPower
                : Math.min(initialChunkPower + n - 1, DefaultAbstractSpinedBuffer.MAX_CHUNK_POWER);
        return 1 << power;
    }

    /**
     * <code>clear</code>
     * <p>the method.</p>
     */
    public abstract void clear();
}
