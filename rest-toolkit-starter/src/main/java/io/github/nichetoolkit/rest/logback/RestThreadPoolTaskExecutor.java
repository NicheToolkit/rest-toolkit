package io.github.nichetoolkit.rest.logback;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * <p>RestThreadPoolTaskExecutor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class RestThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    /*** 接口请求开启的异步线程会调用下述方法*/
    @Override
    public void execute(@NonNull Runnable runnable) {
        Map<String, String> context = MDC.getCopyOfContextMap();
        super.execute(() -> {
            try {
                if (GeneralUtils.isNotEmpty(context)) {
                    MDC.setContextMap(context);
                }
                runnable.run();
            } finally {
                if (GeneralUtils.isNotEmpty(context)) {
                    MDC.clear();
                }
            }
        });
    }

    /*** 定时任务会调用下述方法*/
    @NotNull
    @Override
    public <T> Future<T> submit(@NonNull Callable<T> callable) {
        Map<String, String> context = MDC.getCopyOfContextMap();
        return super.submit(() -> {
            try {
                if (GeneralUtils.isNotEmpty(context)) {
                    MDC.setContextMap(context);
                }
                return callable.call();
            } finally {
                MDC.clear();
            }
        });
    }
}