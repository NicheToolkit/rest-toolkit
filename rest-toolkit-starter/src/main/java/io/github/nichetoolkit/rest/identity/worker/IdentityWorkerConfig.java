package io.github.nichetoolkit.rest.identity.worker;

import java.util.HashSet;
import java.util.Set;

/**
 * <code>IdentityWorkerConfig</code>
 * <p>The identity worker config class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
class IdentityWorkerConfig {
    /**
     * <code>OFFSET</code>
     * {@link java.lang.Long} <p>The <code>OFFSET</code> field.</p>
     * @see java.lang.Long
     */
    static final Long OFFSET = 1L;

    /**
     * <code>STEP</code>
     * {@link java.lang.Long} <p>The <code>STEP</code> field.</p>
     * @see java.lang.Long
     */
    static final Long STEP = 0L;
    /**
     * <code>SEQUENCE</code>
     * {@link java.lang.Long} <p>The <code>SEQUENCE</code> field.</p>
     * @see java.lang.Long
     */
    static final Long SEQUENCE = 0L;
    /**
     * <code>TIMESTAMP</code>
     * {@link java.lang.Long} <p>The <code>TIMESTAMP</code> field.</p>
     * @see java.lang.Long
     */
    static final Long TIMESTAMP = -1L;
    /**
     * <code>SEQUENCE_BIT</code>
     * {@link java.lang.Long} <p>The <code>SEQUENCE_BIT</code> field.</p>
     * @see java.lang.Long
     */
    static final Long SEQUENCE_BIT = 14L;
    /**
     * <code>WORKER_ID_BIT</code>
     * {@link java.lang.Long} <p>The <code>WORKER_ID_BIT</code> field.</p>
     * @see java.lang.Long
     */
    static final Long WORKER_ID_BIT = 4L;
    /**
     * <code>CENTER_ID_BIT</code>
     * {@link java.lang.Long} <p>The <code>CENTER_ID_BIT</code> field.</p>
     * @see java.lang.Long
     */
    static final Long CENTER_ID_BIT = 4L;

    /**
     * <code>THREAD_ID_BIT</code>
     * {@link java.lang.Long} <p>The <code>THREAD_ID_BIT</code> field.</p>
     * @see java.lang.Long
     */
    static final Long THREAD_ID_BIT = 9L;
    /**
     * <code>SEQUENCE_MASK</code>
     * {@link java.lang.Long} <p>The <code>SEQUENCE_MASK</code> field.</p>
     * @see java.lang.Long
     */
    static final Long SEQUENCE_MASK = ~(TIMESTAMP << THREAD_ID_BIT);
    /**
     * <code>THREAD_ID_MASK</code>
     * {@link java.lang.Long} <p>The <code>THREAD_ID_MASK</code> field.</p>
     * @see java.lang.Long
     */
    static final Long THREAD_ID_MASK = ~(TIMESTAMP << SEQUENCE_BIT);

    /**
     * <code>MIN_THREAD_ID</code>
     * {@link java.lang.Long} <p>The <code>MIN_THREAD_ID</code> field.</p>
     * @see java.lang.Long
     */
    static final Long MIN_THREAD_ID = SEQUENCE;
    /**
     * <code>MIN_WORKER_ID</code>
     * {@link java.lang.Long} <p>The <code>MIN_WORKER_ID</code> field.</p>
     * @see java.lang.Long
     */
    static final Long MIN_WORKER_ID = SEQUENCE;
    /**
     * <code>MIN_CENTER_ID</code>
     * {@link java.lang.Long} <p>The <code>MIN_CENTER_ID</code> field.</p>
     * @see java.lang.Long
     */
    static final Long MIN_CENTER_ID = SEQUENCE;

    /**
     * <code>MAX_WORKER_ID</code>
     * {@link java.lang.Long} <p>The <code>MAX_WORKER_ID</code> field.</p>
     * @see java.lang.Long
     */
    static final Long MAX_WORKER_ID = ~(TIMESTAMP << WORKER_ID_BIT);
    /**
     * <code>MAX_CENTER_ID</code>
     * {@link java.lang.Long} <p>The <code>MAX_CENTER_ID</code> field.</p>
     * @see java.lang.Long
     */
    static final Long MAX_CENTER_ID = ~(TIMESTAMP << CENTER_ID_BIT);
    /**
     * <code>MAX_THREAD_ID</code>
     * {@link java.lang.Long} <p>The <code>MAX_THREAD_ID</code> field.</p>
     * @see java.lang.Long
     */
    static final Long MAX_THREAD_ID = ~(TIMESTAMP << THREAD_ID_BIT);

    /**
     * <code>CENTER_ID_SHIFT</code>
     * {@link java.lang.Long} <p>The <code>CENTER_ID_SHIFT</code> field.</p>
     * @see java.lang.Long
     */
    static final Long CENTER_ID_SHIFT = SEQUENCE_BIT + WORKER_ID_BIT;
    /**
     * <code>WORKER_ID_SHIFT</code>
     * {@link java.lang.Long} <p>The <code>WORKER_ID_SHIFT</code> field.</p>
     * @see java.lang.Long
     */
    static final Long WORKER_ID_SHIFT = SEQUENCE_BIT;

    /**
     * <code>TIMESTAMP_SHIFT</code>
     * {@link java.lang.Long} <p>The <code>TIMESTAMP_SHIFT</code> field.</p>
     * @see java.lang.Long
     */
    static final Long TIMESTAMP_SHIFT = SEQUENCE_BIT + WORKER_ID_BIT + CENTER_ID_BIT;

    /**
     * <code>TIMESTAMP_BIT_SIZE</code>
     * {@link java.lang.Integer} <p>The <code>TIMESTAMP_BIT_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    static final Integer TIMESTAMP_BIT_SIZE = 30;
    /**
     * <code>THREAD_BIT_SIZE</code>
     * {@link java.lang.Integer} <p>The <code>THREAD_BIT_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    static final Integer THREAD_BIT_SIZE = 12;
    /**
     * <code>REGION_BIT_SIZE</code>
     * {@link java.lang.Integer} <p>The <code>REGION_BIT_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    static final Integer REGION_BIT_SIZE = 10;
    /**
     * <code>MIN_REGION_SIZE</code>
     * {@link java.lang.Integer} <p>The <code>MIN_REGION_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    static final Integer MIN_REGION_SIZE = 1;
    /**
     * <code>MAX_REGION_SIZE</code>
     * {@link java.lang.Integer} <p>The <code>MAX_REGION_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    static final Integer MAX_REGION_SIZE = MIN_REGION_SIZE << REGION_BIT_SIZE;
    /**
     * <code>SEQUENCE_BIT_SIZE</code>
     * {@link java.lang.Integer} <p>The <code>SEQUENCE_BIT_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    static final Integer SEQUENCE_BIT_SIZE = 12;
    /**
     * <code>ALL_BIT_SIZE</code>
     * {@link java.lang.Integer} <p>The <code>ALL_BIT_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    static final Integer ALL_BIT_SIZE = TIMESTAMP_BIT_SIZE + THREAD_BIT_SIZE + REGION_BIT_SIZE + SEQUENCE_BIT_SIZE;

    /**
     * <code>DEFAULT_STEP</code>
     * {@link java.lang.Long} <p>The <code>DEFAULT_STEP</code> field.</p>
     * @see java.lang.Long
     */
    static final Long DEFAULT_STEP = 1L;

    /**
     * <code>DEFAULT_TAG</code>
     * {@link java.lang.Long} <p>The <code>DEFAULT_TAG</code> field.</p>
     * @see java.lang.Long
     */
    static final Long DEFAULT_TAG = 1L;
    /**
     * <code>CACHE_SIZE</code>
     * {@link java.lang.Integer} <p>The <code>CACHE_SIZE</code> field.</p>
     * @see java.lang.Integer
     */
    static final Integer CACHE_SIZE = 99;

}
