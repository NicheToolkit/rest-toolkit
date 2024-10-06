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
     * <p>The method.</p>
     */
    public static void clear() {
        IdentityWorker.clear();
    }

    /**
     * <code>generateLong</code>
     * <p>The long method.</p>
     * @param workerType {@link io.github.nichetoolkit.rest.identity.worker.WorkerType} <p>The worker type parameter is <code>WorkerType</code> type.</p>
     * @return {@link java.lang.Long} <p>The long return object is <code>Long</code> type.</p>
     * @see io.github.nichetoolkit.rest.identity.worker.WorkerType
     * @see java.lang.Long
     */
    public static Long generateLong(WorkerType workerType) {
        return IdentityFactory.getInstance().get(workerType).generate();
    }

    /**
     * <code>generateString</code>
     * <p>The string method.</p>
     * @param workerType {@link io.github.nichetoolkit.rest.identity.worker.WorkerType} <p>The worker type parameter is <code>WorkerType</code> type.</p>
     * @return {@link java.lang.String} <p>The string return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.identity.worker.WorkerType
     * @see java.lang.String
     */
    public static String generateString(WorkerType workerType) {
        return String.valueOf(IdentityFactory.getInstance().get(workerType).generate());
    }

    /**
     * <code>baseLong</code>
     * <p>The long method.</p>
     * @return {@link java.lang.Long} <p>The long return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long baseLong() {
        return generateLong(WorkerType.BASE_WORKER);
    }

    /**
     * <code>commonLong</code>
     * <p>The long method.</p>
     * @return {@link java.lang.Long} <p>The long return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long commonLong() {
        return generateLong(WorkerType.COMMON_WORKER);
    }

    /**
     * <code>offsetLong</code>
     * <p>The long method.</p>
     * @return {@link java.lang.Long} <p>The long return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long offsetLong() {
        return generateLong(WorkerType.OFFSET_WORKER);
    }

    /**
     * <code>sequenceLong</code>
     * <p>The long method.</p>
     * @return {@link java.lang.Long} <p>The long return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long sequenceLong() {
        return generateLong(WorkerType.SEQUENCE_WORKER);
    }

    /**
     * <code>generateLong</code>
     * <p>The long method.</p>
     * @return {@link java.lang.Long} <p>The long return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long generateLong() {
        return generateLong(WorkerType.COMMON_WORKER);
    }

    /**
     * <code>baseString</code>
     * <p>The string method.</p>
     * @return {@link java.lang.String} <p>The string return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String baseString() {
        return generateString(WorkerType.BASE_WORKER);
    }

    /**
     * <code>commonString</code>
     * <p>The string method.</p>
     * @return {@link java.lang.String} <p>The string return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String commonString() {
        return generateString(WorkerType.COMMON_WORKER);
    }

    /**
     * <code>offsetString</code>
     * <p>The string method.</p>
     * @return {@link java.lang.String} <p>The string return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String offsetString() {
        return generateString(WorkerType.OFFSET_WORKER);
    }

    /**
     * <code>sequenceString</code>
     * <p>The string method.</p>
     * @return {@link java.lang.String} <p>The string return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String sequenceString() {
        return generateString(WorkerType.SEQUENCE_WORKER);
    }

    /**
     * <code>generateString</code>
     * <p>The string method.</p>
     * @return {@link java.lang.String} <p>The string return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String generateString() {
        return generateString(WorkerType.COMMON_WORKER);
    }


}
