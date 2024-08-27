package io.github.nichetoolkit.rest.identity;

import io.github.nichetoolkit.rest.identity.worker.WorkerType;
import io.github.nichetoolkit.rest.identity.worker.IdentityWorker;

/**
 * <code>IdentityUtils</code>
 * <p>The type identity utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class IdentityUtils {

    /**
     * <code>clear</code>
     * <p>the method.</p>
     */
    public static void clear() {
        IdentityWorker.clear();
    }

    /**
     * <code>generateLong</code>
     * <p>the long method.</p>
     * @param workerType {@link io.github.nichetoolkit.rest.identity.worker.WorkerType} <p>the worker type parameter is <code>WorkerType</code> type.</p>
     * @return {@link java.lang.Long} <p>the long return object is <code>Long</code> type.</p>
     * @see io.github.nichetoolkit.rest.identity.worker.WorkerType
     * @see java.lang.Long
     */
    public static Long generateLong(WorkerType workerType) {
        return IdentityFactory.getInstance().get(workerType).generate();
    }

    /**
     * <code>generateString</code>
     * <p>the string method.</p>
     * @param workerType {@link io.github.nichetoolkit.rest.identity.worker.WorkerType} <p>the worker type parameter is <code>WorkerType</code> type.</p>
     * @return {@link java.lang.String} <p>the string return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.identity.worker.WorkerType
     * @see java.lang.String
     */
    public static String generateString(WorkerType workerType) {
        return String.valueOf(IdentityFactory.getInstance().get(workerType).generate());
    }

    /**
     * <code>baseLong</code>
     * <p>the long method.</p>
     * @return {@link java.lang.Long} <p>the long return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long baseLong() {
        return generateLong(WorkerType.BASE_WORKER);
    }

    /**
     * <code>commonLong</code>
     * <p>the long method.</p>
     * @return {@link java.lang.Long} <p>the long return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long commonLong() {
        return generateLong(WorkerType.COMMON_WORKER);
    }

    /**
     * <code>offsetLong</code>
     * <p>the long method.</p>
     * @return {@link java.lang.Long} <p>the long return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long offsetLong() {
        return generateLong(WorkerType.OFFSET_WORKER);
    }

    /**
     * <code>sequenceLong</code>
     * <p>the long method.</p>
     * @return {@link java.lang.Long} <p>the long return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long sequenceLong() {
        return generateLong(WorkerType.SEQUENCE_WORKER);
    }

    /**
     * <code>generateLong</code>
     * <p>the long method.</p>
     * @return {@link java.lang.Long} <p>the long return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long generateLong() {
        return generateLong(WorkerType.COMMON_WORKER);
    }

    /**
     * <code>baseString</code>
     * <p>the string method.</p>
     * @return {@link java.lang.String} <p>the string return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String baseString() {
        return generateString(WorkerType.BASE_WORKER);
    }

    /**
     * <code>commonString</code>
     * <p>the string method.</p>
     * @return {@link java.lang.String} <p>the string return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String commonString() {
        return generateString(WorkerType.COMMON_WORKER);
    }

    /**
     * <code>offsetString</code>
     * <p>the string method.</p>
     * @return {@link java.lang.String} <p>the string return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String offsetString() {
        return generateString(WorkerType.OFFSET_WORKER);
    }

    /**
     * <code>sequenceString</code>
     * <p>the string method.</p>
     * @return {@link java.lang.String} <p>the string return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String sequenceString() {
        return generateString(WorkerType.SEQUENCE_WORKER);
    }

    /**
     * <code>generateString</code>
     * <p>the string method.</p>
     * @return {@link java.lang.String} <p>the string return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String generateString() {
        return generateString(WorkerType.COMMON_WORKER);
    }


}
