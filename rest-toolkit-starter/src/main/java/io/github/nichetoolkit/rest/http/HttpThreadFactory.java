/*
 *  Copyright (c) 2020. 衷于栖 All rights reserved.
 *
 *  版权所有 衷于栖 并保留所有权利 2020。
 *  ============================================================================
 *  这不是一个自由软件！您只能在不用于商业目的的前提下对程序代码进行修改和
 *  使用。不允许对程序代码以任何形式任何目的的再发布。如果项目发布携带作者
 *  认可的特殊 LICENSE 则按照 LICENSE 执行，废除上面内容。请保留原作者信息。
 *  ============================================================================
 *  作者：衷于栖（feedback@zhoyq.com）
 *  博客：https://www.zhoyq.com
 *  创建时间：2020
 *
 */

package io.github.nichetoolkit.rest.http;

import org.springframework.lang.NonNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <code>HttpThreadFactory</code>
 * <p>The http thread factory class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.concurrent.ThreadFactory
 * @since Jdk1.8
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
        SecurityManager securityManager = System.getSecurityManager();
        group = (securityManager != null) ? securityManager.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
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
