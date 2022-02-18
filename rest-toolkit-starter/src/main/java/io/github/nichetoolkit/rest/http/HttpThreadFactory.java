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
 * <p>JT808ThreadFactory</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v.1.0.0
 */
public class HttpThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

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
