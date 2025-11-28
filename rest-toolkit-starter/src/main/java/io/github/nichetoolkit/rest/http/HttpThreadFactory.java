package io.github.nichetoolkit.rest.http;

import org.jspecify.annotations.NonNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <code>HttpThreadFactory</code>
 * <p>The http thread factory class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.concurrent.ThreadFactory
 * @since Jdk17
 */
public class HttpThreadFactory implements ThreadFactory {
    /**
     * <code>poolNumber</code>
     * {@link java.util.concurrent.atomic.AtomicInteger} <p>The constant <code>poolNumber</code> field.</p>
     * @see java.util.concurrent.atomic.AtomicInteger
     */
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    /**
     * <code>group</code>
     * {@link java.lang.ThreadGroup} <p>The <code>group</code> field.</p>
     * @see java.lang.ThreadGroup
     */
    private final ThreadGroup group;
    /**
     * <code>threadNumber</code>
     * {@link java.util.concurrent.atomic.AtomicInteger} <p>The <code>threadNumber</code> field.</p>
     * @see java.util.concurrent.atomic.AtomicInteger
     */
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    /**
     * <code>namePrefix</code>
     * {@link java.lang.String} <p>The <code>namePrefix</code> field.</p>
     * @see java.lang.String
     */
    private final String namePrefix;

    /**
     * <code>HttpThreadFactory</code>
     * <p>Instantiates a new http thread factory.</p>
     * @param threadPrefix {@link java.lang.String} <p>The thread prefix parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public HttpThreadFactory(String threadPrefix) {
        group = Thread.currentThread().getThreadGroup();
        namePrefix = threadPrefix + "-" + poolNumber.getAndIncrement() + "-";
    }

    @Override
    @SuppressWarnings("ThreadPriorityCheck")
    public Thread newThread(@NonNull Runnable runnable) {
        Thread thread = new Thread(group, runnable,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        if (thread.isDaemon()){
            thread.setDaemon(false);
        }
        if (thread.getPriority() != Thread.NORM_PRIORITY){
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
