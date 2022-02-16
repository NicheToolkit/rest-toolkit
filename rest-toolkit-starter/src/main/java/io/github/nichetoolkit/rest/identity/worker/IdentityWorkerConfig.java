package io.github.nichetoolkit.rest.identity.worker;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>IdentityWorkerConfig</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
class IdentityWorkerConfig {
    static final Long OFFSET = 1L;
    static final Long SEQUENCE = 0L;
    static final Long TIMESTAMP = -1L;
    static final Long SEQUENCE_BIT = 12L;
    static final Long WORKER_ID_BIT = 5L;
    static final Long CENTER_ID_BIT = 5L;
    static final Long SEQUENCE_MASK = ~(TIMESTAMP << SEQUENCE_BIT);
    static final Long MIN_WORKER_ID = SEQUENCE;
    static final Long MIN_CENTER_ID = SEQUENCE;
    static final Long MAX_WORKER_ID = ~(TIMESTAMP << WORKER_ID_BIT);
    static final Long MAX_CENTER_ID = ~(TIMESTAMP << CENTER_ID_BIT);
    static final Long WORKER_ID_SHIFT = SEQUENCE_BIT;
    static final Long CENTER_ID_SHIFT = SEQUENCE_BIT + WORKER_ID_BIT;
    static final Long TIMESTAMP_SHIFT = SEQUENCE_BIT + WORKER_ID_BIT + CENTER_ID_BIT;

    static final Integer TIMESTAMP_BIT_SIZE = 42;
    static final Integer REGION_BIT_SIZE = 10;
    static final Integer MIN_REGION_SIZE = 1;
    static final Integer MAX_REGION_SIZE = MIN_REGION_SIZE << REGION_BIT_SIZE;
    static final Integer SEQUENCE_BIT_SIZE = 12;
    static final Integer ALL_BIT_SIZE = TIMESTAMP_BIT_SIZE + REGION_BIT_SIZE + SEQUENCE_BIT_SIZE;

    static final Long DEFAULT_TAG = 1L;
    static final Integer CACHE_SIZE = 99;
    static final Set<Long> CACHE_SET = new HashSet<>();

}
