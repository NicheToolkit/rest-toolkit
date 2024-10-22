package io.github.nichetoolkit.rest.identity;

import io.github.nichetoolkit.rest.identity.worker.WorkerType;

/**
 * <code>IdentityUtils</code>
 * <p>The type identity utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class IdentityUtils {

    /**
     * <code>valueOfLong</code>
     * <p>The of long method.</p>
     * @param workerType {@link io.github.nichetoolkit.rest.identity.worker.WorkerType} <p>The worker type parameter is <code>WorkerType</code> type.</p>
     * @return {@link java.lang.Long} <p>The of long return object is <code>Long</code> type.</p>
     * @see io.github.nichetoolkit.rest.identity.worker.WorkerType
     * @see java.lang.Long
     */
    public static Long valueOfLong(WorkerType workerType) {
        return IdentityFactory.getInstance().get(workerType).generate();
    }

    /**
     * <code>valueOfString</code>
     * <p>The of string method.</p>
     * @param workerType {@link io.github.nichetoolkit.rest.identity.worker.WorkerType} <p>The worker type parameter is <code>WorkerType</code> type.</p>
     * @return {@link java.lang.String} <p>The of string return object is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.identity.worker.WorkerType
     * @see java.lang.String
     */
    public static String valueOfString(WorkerType workerType) {
        return String.valueOf(IdentityFactory.getInstance().get(workerType).generate());
    }

    /**
     * <code>longOfBase</code>
     * <p>The of base method.</p>
     * @return {@link java.lang.Long} <p>The of base return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long longOfBase() {
        return valueOfLong(WorkerType.BASE_WORKER);
    }

    /**
     * <code>longOfCommon</code>
     * <p>The of common method.</p>
     * @return {@link java.lang.Long} <p>The of common return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long longOfCommon() {
        return valueOfLong(WorkerType.COMMON_WORKER);
    }

    /**
     * <code>longOfOffset</code>
     * <p>The of offset method.</p>
     * @return {@link java.lang.Long} <p>The of offset return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long longOfOffset() {
        return valueOfLong(WorkerType.OFFSET_WORKER);
    }

    /**
     * <code>longOfSequence</code>
     * <p>The of sequence method.</p>
     * @return {@link java.lang.Long} <p>The of sequence return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long longOfSequence() {
        return valueOfLong(WorkerType.SEQUENCE_WORKER);
    }

    /**
     * <code>valueOfLong</code>
     * <p>The of long method.</p>
     * @return {@link java.lang.Long} <p>The of long return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long valueOfLong() {
        return valueOfLong(WorkerType.COMMON_WORKER);
    }

    /**
     * <code>stringOfBase</code>
     * <p>The of base method.</p>
     * @return {@link java.lang.String} <p>The of base return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String stringOfBase() {
        return valueOfString(WorkerType.BASE_WORKER);
    }

    /**
     * <code>stringOfCommon</code>
     * <p>The of common method.</p>
     * @return {@link java.lang.String} <p>The of common return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String stringOfCommon() {
        return valueOfString(WorkerType.COMMON_WORKER);
    }

    /**
     * <code>stringOfOffset</code>
     * <p>The of offset method.</p>
     * @return {@link java.lang.String} <p>The of offset return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String stringOfOffset() {
        return valueOfString(WorkerType.OFFSET_WORKER);
    }

    /**
     * <code>stringOfSequence</code>
     * <p>The of sequence method.</p>
     * @return {@link java.lang.String} <p>The of sequence return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String stringOfSequence() {
        return valueOfString(WorkerType.SEQUENCE_WORKER);
    }

    /**
     * <code>valueOfString</code>
     * <p>The of string method.</p>
     * @return {@link java.lang.String} <p>The of string return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String valueOfString() {
        return valueOfString(WorkerType.COMMON_WORKER);
    }


}
