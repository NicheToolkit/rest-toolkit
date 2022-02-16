package io.github.nichetoolkit.rest.identity;

import io.github.nichetoolkit.rest.identity.worker.WorkerType;
import io.github.nichetoolkit.rest.identity.worker.IdentityWorker;

/**
 * <p>IdentityUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class IdentityUtils {

    public static void clear() {
        IdentityWorker.clear();
    }

    public static Long generateLong(WorkerType workerType) {
        return IdentityFactory.getInstance().get(workerType).generate();
    }

    public static String generateString(WorkerType workerType) {
        return String.valueOf(IdentityFactory.getInstance().get(workerType).generate());
    }

    public static Long baseLong() {
        return generateLong(WorkerType.BASE_WORKER);
    }

    public static Long commonLong() {
        return generateLong(WorkerType.COMMON_WORKER);
    }

    public static Long offsetLong() {
        return generateLong(WorkerType.OFFSET_WORKER);
    }

    public static Long sequenceLong() {
        return generateLong(WorkerType.SEQUENCE_WORKER);
    }

    public static Long generateLong() {
        return generateLong(WorkerType.COMMON_WORKER);
    }

    public static String baseString() {
        return generateString(WorkerType.BASE_WORKER);
    }

    public static String commonString() {
        return generateString(WorkerType.COMMON_WORKER);
    }

    public static String offsetString() {
        return generateString(WorkerType.OFFSET_WORKER);
    }

    public static String sequenceString() {
        return generateString(WorkerType.SEQUENCE_WORKER);
    }

    public static String generateString() {
        return generateString(WorkerType.COMMON_WORKER);
    }


}
