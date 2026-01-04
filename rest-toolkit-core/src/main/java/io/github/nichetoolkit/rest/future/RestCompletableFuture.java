package io.github.nichetoolkit.rest.future;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.*;
import io.github.nichetoolkit.rest.error.natives.ThreadErrorException;
import io.github.nichetoolkit.rest.error.supply.ParamEmptyException;
import io.github.nichetoolkit.rest.error.thread.*;
import org.springframework.lang.NonNull;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <code>RestCompletableFuture</code>
 * <p>The rest completable future class.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.future.RestFuture
 * @see io.github.nichetoolkit.rest.future.RestCompletionStage
 * @since Jdk1.8
 */
public class RestCompletableFuture<T> implements RestFuture<T>, RestCompletionStage<T> {
    /**
     * <code>result</code>
     * {@link java.lang.Object} <p>The <code>result</code> field.</p>
     * @see java.lang.Object
     */
    volatile Object result;
    /**
     * <code>stack</code>
     * {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion} <p>The <code>stack</code> field.</p>
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion
     */
    volatile RestCompletableFuture.Completion stack;

    /**
     * <code>internalComplete</code>
     * <p>The internal complete method.</p>
     * @param r {@link java.lang.Object} <p>The r parameter is <code>Object</code> type.</p>
     * @return boolean <p>The internal complete return object is <code>boolean</code> type.</p>
     * @see java.lang.Object
     */
    final boolean internalComplete(Object r) { // CAS from null to r
        return UNSAFE.compareAndSwapObject(this, RESULT, null, r);
    }

    /**
     * <code>casStack</code>
     * <p>The cas stack method.</p>
     * @param cmp {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion} <p>The cmp parameter is <code>Completion</code> type.</p>
     * @param val {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion} <p>The val parameter is <code>Completion</code> type.</p>
     * @return boolean <p>The cas stack return object is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion
     */
    final boolean casStack(RestCompletableFuture.Completion cmp, RestCompletableFuture.Completion val) {
        return UNSAFE.compareAndSwapObject(this, STACK, cmp, val);
    }

    /**
     * <code>tryPushStack</code>
     * <p>The try push stack method.</p>
     * @param c {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion} <p>The c parameter is <code>Completion</code> type.</p>
     * @return boolean <p>The try push stack return object is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion
     */
    final boolean tryPushStack(RestCompletableFuture.Completion c) {
        RestCompletableFuture.Completion h = stack;
        lazySetNext(c, h);
        return UNSAFE.compareAndSwapObject(this, STACK, h, c);
    }

    /**
     * <code>pushStack</code>
     * <p>The push stack method.</p>
     * @param c {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion} <p>The c parameter is <code>Completion</code> type.</p>
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion
     */
    final void pushStack(RestCompletableFuture.Completion c) {
        do {
        } while (!tryPushStack(c));
    }

    /**
     * <code>AltResult</code>
     * <p>The alt result class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    static final class AltResult { // See above
        /**
         * <code>ex</code>
         * {@link java.lang.Throwable} <p>The <code>ex</code> field.</p>
         * @see java.lang.Throwable
         */
        final Throwable ex;

        /**
         * <code>AltResult</code>
         * <p>Instantiates a new alt result.</p>
         * @param x {@link java.lang.Throwable} <p>The x parameter is <code>Throwable</code> type.</p>
         * @see java.lang.Throwable
         */
        AltResult(Throwable x) {
            this.ex = x;
        }
    }

    /**
     * <code>NIL</code>
     * {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.AltResult} <p>The <code>NIL</code> field.</p>
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.AltResult
     */
    static final RestCompletableFuture.AltResult NIL = new RestCompletableFuture.AltResult(null);

    /**
     * <code>completeNull</code>
     * <p>The complete null method.</p>
     * @return boolean <p>The complete null return object is <code>boolean</code> type.</p>
     */
    final boolean completeNull() {
        return UNSAFE.compareAndSwapObject(this, RESULT, null, NIL);
    }

    /**
     * <code>encodeValue</code>
     * <p>The encode value method.</p>
     * @param t T <p>The t parameter is <code>T</code> type.</p>
     * @return {@link java.lang.Object} <p>The encode value return object is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    final Object encodeValue(T t) {
        return (t == null) ? NIL : t;
    }

    /**
     * <code>completeValue</code>
     * <p>The complete value method.</p>
     * @param t T <p>The t parameter is <code>T</code> type.</p>
     * @return boolean <p>The complete value return object is <code>boolean</code> type.</p>
     */
    final boolean completeValue(T t) {
        return UNSAFE.compareAndSwapObject(this, RESULT, null, (t == null) ? NIL : t);
    }

    /**
     * <code>encodeThrowable</code>
     * <p>The encode throwable method.</p>
     * @param x {@link java.lang.Throwable} <p>The x parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.AltResult} <p>The encode throwable return object is <code>AltResult</code> type.</p>
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.AltResult
     */
    static RestCompletableFuture.AltResult encodeThrowable(Throwable x) {
        return new RestCompletableFuture.AltResult((x instanceof RestException) ? x : new ThreadCompletionException(x));
    }

    /**
     * <code>completeThrowable</code>
     * <p>The complete throwable method.</p>
     * @param x {@link java.lang.Throwable} <p>The x parameter is <code>Throwable</code> type.</p>
     * @return boolean <p>The complete throwable return object is <code>boolean</code> type.</p>
     * @see java.lang.Throwable
     */
    final boolean completeThrowable(Throwable x) {
        return UNSAFE.compareAndSwapObject(this, RESULT, null, encodeThrowable(x));
    }

    /**
     * <code>encodeThrowable</code>
     * <p>The encode throwable method.</p>
     * @param x {@link java.lang.Throwable} <p>The x parameter is <code>Throwable</code> type.</p>
     * @param r {@link java.lang.Object} <p>The r parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Object} <p>The encode throwable return object is <code>Object</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    static Object encodeThrowable(Throwable x, Object r) {
        if (!(x instanceof RestException))
            x = new ThreadCompletionException(x);
        else if (r instanceof RestCompletableFuture.AltResult && x == ((RestCompletableFuture.AltResult) r).ex)
            return r;
        return new RestCompletableFuture.AltResult(x);
    }

    /**
     * <code>completeThrowable</code>
     * <p>The complete throwable method.</p>
     * @param x {@link java.lang.Throwable} <p>The x parameter is <code>Throwable</code> type.</p>
     * @param r {@link java.lang.Object} <p>The r parameter is <code>Object</code> type.</p>
     * @return boolean <p>The complete throwable return object is <code>boolean</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    final boolean completeThrowable(Throwable x, Object r) {
        return UNSAFE.compareAndSwapObject(this, RESULT, null, encodeThrowable(x, r));
    }

    /**
     * <code>encodeOutcome</code>
     * <p>The encode outcome method.</p>
     * @param t T <p>The t parameter is <code>T</code> type.</p>
     * @param x {@link java.lang.Throwable} <p>The x parameter is <code>Throwable</code> type.</p>
     * @return {@link java.lang.Object} <p>The encode outcome return object is <code>Object</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    Object encodeOutcome(T t, Throwable x) {
        return (x == null) ? (t == null) ? NIL : t : encodeThrowable(x);
    }

    /**
     * <code>encodeRelay</code>
     * <p>The encode relay method.</p>
     * @param r {@link java.lang.Object} <p>The r parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Object} <p>The encode relay return object is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    static Object encodeRelay(Object r) {
        Throwable x;
        return (((r instanceof RestCompletableFuture.AltResult) &&
                (x = ((RestCompletableFuture.AltResult) r).ex) != null && !(x instanceof RestException)) ?
                new RestCompletableFuture.AltResult(new ThreadCompletionException(x)) : r);
    }

    /**
     * <code>completeRelay</code>
     * <p>The complete relay method.</p>
     * @param r {@link java.lang.Object} <p>The r parameter is <code>Object</code> type.</p>
     * @return boolean <p>The complete relay return object is <code>boolean</code> type.</p>
     * @see java.lang.Object
     */
    final boolean completeRelay(Object r) {
        return UNSAFE.compareAndSwapObject(this, RESULT, null, encodeRelay(r));
    }

    /**
     * <code>reportGet</code>
     * <p>The report get method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param r   {@link java.lang.Object} <p>The r parameter is <code>Object</code> type.</p>
     * @return T <p>The report get return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    private static <T> T reportGet(Object r) throws RestException {
        if (r == null) // by convention below, null means interrupted
            throw new ThreadInterruptedException();
        if (r instanceof RestCompletableFuture.AltResult) {
            Throwable x, cause;
            if ((x = ((RestCompletableFuture.AltResult) r).ex) == null)
                return null;
            if (x instanceof CancellationException)
                throw new ThreadCancellationException(x);
            if ((x instanceof CompletionException) && (cause = x.getCause()) != null)
                throw new ThreadCompletionException(cause);
            if (x instanceof ThreadErrorException)
                throw (ThreadErrorException) x;
            if (x instanceof RestException)
                throw (RestException) x;
            throw new RestException(x);
        }
        @SuppressWarnings("unchecked") T t = (T) r;
        return t;
    }

    /**
     * <code>reportJoin</code>
     * <p>The report join method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param r   {@link java.lang.Object} <p>The r parameter is <code>Object</code> type.</p>
     * @return T <p>The report join return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    private static <T> T reportJoin(Object r) throws RestException {
        if (r instanceof RestCompletableFuture.AltResult) {
            Throwable x;
            if ((x = ((RestCompletableFuture.AltResult) r).ex) == null)
                return null;
            if (x instanceof CancellationException)
                throw new ThreadCancellationException(x);
            if (x instanceof CompletionException)
                throw new ThreadCompletionException(x);
            if (x instanceof ThreadErrorException)
                throw (ThreadErrorException) x;
            if (x instanceof RestException)
                throw (RestException) x;
            throw new RestException(x);
        }
        @SuppressWarnings("unchecked") T t = (T) r;
        return t;
    }

    /* ------------- Async task preliminaries -------------- */

    /**
     * <code>AsynchronousCompletionTask</code>
     * <p>The asynchronous completion task interface.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static interface AsynchronousCompletionTask {
    }

    /**
     * <code>useCommonPool</code>
     * <p>The constant <code>useCommonPool</code> field.</p>
     */
    private static final boolean useCommonPool = (ForkJoinPool.getCommonPoolParallelism() > 1);

    /**
     * <code>asyncPool</code>
     * {@link java.util.concurrent.Executor} <p>The constant <code>asyncPool</code> field.</p>
     * @see java.util.concurrent.Executor
     */
    private static final Executor asyncPool = useCommonPool ? ForkJoinPool.commonPool() : new RestCompletableFuture.ThreadPerTaskExecutor();

    /**
     * <code>ThreadPerTaskExecutor</code>
     * <p>The thread per task executor class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see java.util.concurrent.Executor
     * @since Jdk1.8
     */
    static final class ThreadPerTaskExecutor implements Executor {
        public void execute(@NonNull Runnable r) {
            new Thread(r).start();
        }
    }

    /**
     * <code>screenExecutor</code>
     * <p>The screen executor method.</p>
     * @param e {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @return {@link java.util.concurrent.Executor} <p>The screen executor return object is <code>Executor</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    static Executor screenExecutor(Executor e) throws RestException {
        if (!useCommonPool && e == ForkJoinPool.commonPool())
            return asyncPool;
        if (e == null) throw new ThreadPointerEmptyException();
        return e;
    }

    /**
     * <code>SYNC</code>
     * <p>The constant <code>SYNC</code> field.</p>
     */
// Modes for Completion.tryFire. Signedness matters.
    static final int SYNC = 0;
    /**
     * <code>ASYNC</code>
     * <p>The <code>ASYNC</code> field.</p>
     */
    static final int ASYNC = 1;
    /**
     * <code>NESTED</code>
     * <p>The <code>NESTED</code> field.</p>
     */
    static final int NESTED = -1;

    /**
     * <code>SPINS</code>
     * <p>The constant <code>SPINS</code> field.</p>
     */
    private static final int SPINS = (Runtime.getRuntime().availableProcessors() > 1 ?
            1 << 8 : 0);


    /**
     * <code>Completion</code>
     * <p>The completion class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see java.util.concurrent.ForkJoinTask
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.AsynchronousCompletionTask
     * @since Jdk1.8
     */
    abstract static class Completion extends ForkJoinTask<Void> implements Runnable, RestCompletableFuture.AsynchronousCompletionTask {
        /**
         * <code>next</code>
         * {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion} <p>The <code>next</code> field.</p>
         */
        volatile RestCompletableFuture.Completion next;

        /**
         * <code>tryFire</code>
         * <p>The try fire method.</p>
         * @param mode int <p>The mode parameter is <code>int</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The try fire return object is <code>RestCompletableFuture</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
        abstract RestCompletableFuture<?> tryFire(int mode) throws RestException;

        /**
         * <code>isLive</code>
         * <p>The is live method.</p>
         * @return boolean <p>The is live return object is <code>boolean</code> type.</p>
         */
        abstract boolean isLive();

        public final void run() {
            try {
                tryFire(ASYNC);
            } catch (RestException e) {
                throw new RestError(e);
            }
        }

        public final boolean exec() {
            try {
                tryFire(ASYNC);
            } catch (RestException e) {
                throw new RestError(e);
            }
            return true;
        }

        public final Void getRawResult() {
            return null;
        }

        public final void setRawResult(Void v) {
        }
    }

    /**
     * <code>lazySetNext</code>
     * <p>The lazy set next method.</p>
     * @param c    {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion} <p>The c parameter is <code>Completion</code> type.</p>
     * @param next {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion} <p>The next parameter is <code>Completion</code> type.</p>
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion
     */
    static void lazySetNext(RestCompletableFuture.Completion c, RestCompletableFuture.Completion next) {
        UNSAFE.putOrderedObject(c, NEXT, next);
    }

    /**
     * <code>postComplete</code>
     * <p>The post complete method.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    final void postComplete() throws RestException {
        /*
         * On each step, variable f holds current dependents to pop
         * and run.  It is extended along only one path at a time,
         * pushing others to avoid unbounded recursion.
         */
        RestCompletableFuture<?> f = this;
        RestCompletableFuture.Completion h;
        while ((h = f.stack) != null ||
                (f != this && (h = (f = this).stack) != null)) {
            RestCompletableFuture<?> d;
            RestCompletableFuture.Completion t;
            if (f.casStack(h, t = h.next)) {
                if (t != null) {
                    if (f != this) {
                        pushStack(h);
                        continue;
                    }
                    h.next = null;    // detach
                }
                f = (d = h.tryFire(NESTED)) == null ? this : d;
            }
        }
    }

    /**
     * <code>cleanStack</code>
     * <p>The clean stack method.</p>
     */
    final void cleanStack() {
        for (RestCompletableFuture.Completion p = null, q = stack; q != null; ) {
            RestCompletableFuture.Completion s = q.next;
            if (q.isLive()) {
                p = q;
                q = s;
            } else if (p == null) {
                casStack(q, s);
                q = stack;
            } else {
                p.next = s;
                if (p.isLive())
                    q = s;
                else {
                    p = null;  // restart
                    q = stack;
                }
            }
        }
    }

    /* ------------- One-input Completions -------------- */

    /**
     * <code>UniCompletion</code>
     * <p>The uni completion class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion
     * @since Jdk1.8
     */
    abstract static class UniCompletion<T, V> extends RestCompletableFuture.Completion {
        /**
         * <code>executor</code>
         * {@link java.util.concurrent.Executor} <p>The <code>executor</code> field.</p>
         * @see java.util.concurrent.Executor
         */
        Executor executor;
        /**
         * <code>dep</code>
         * <p>The dep field.</p>
         */
        RestCompletableFuture<V> dep;
        /**
         * <code>src</code>
         * <p>The src field.</p>
         */
        RestCompletableFuture<T> src;

        /**
         * <code>UniCompletion</code>
         * <p>Instantiates a new uni completion.</p>
         * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @see java.util.concurrent.Executor
         */
        UniCompletion(Executor executor, RestCompletableFuture<V> dep,
                      RestCompletableFuture<T> src) {
            this.executor = executor;
            this.dep = dep;
            this.src = src;
        }

        /**
         * <code>claim</code>
         * <p>The claim method.</p>
         * @return boolean <p>The claim return object is <code>boolean</code> type.</p>
         */
        final boolean claim() {
            Executor e = executor;
            if (compareAndSetForkJoinTaskTag((short) 0, (short) 1)) {
                if (e == null)
                    return true;
                executor = null; // disable
                e.execute(this);
            }
            return false;
        }

        final boolean isLive() {
            return dep != null;
        }
    }

    /**
     * <code>push</code>
     * <p>The push method.</p>
     * @param c {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.UniCompletion} <p>The c parameter is <code>UniCompletion</code> type.</p>
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniCompletion
     */
    final void push(RestCompletableFuture.UniCompletion<?, ?> c) {
        if (c != null) {
            while (result == null && !tryPushStack(c))
                lazySetNext(c, null); // clear on failure
        }
    }

    /**
     * <code>postFire</code>
     * <p>The post fire method.</p>
     * @param a    {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param mode int <p>The mode parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The post fire return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    final RestCompletableFuture<T> postFire(RestCompletableFuture<?> a, int mode) throws RestException {
        if (a != null && a.stack != null) {
            if (mode < 0 || a.result == null)
                a.cleanStack();
            else
                a.postComplete();
        }
        if (result != null && stack != null) {
            if (mode < 0)
                return this;
            else
                postComplete();
        }
        return null;
    }

    /**
     * <code>UniApply</code>
     * <p>The uni apply class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniCompletion
     * @since Jdk1.8
     */
    static final class UniApply<T, V> extends RestCompletableFuture.UniCompletion<T, V> {
        /**
         * <code>function</code>
         * {@link java.util.function.Function} <p>The <code>function</code> field.</p>
         * @see java.util.function.Function
         */
        Function<? super T, ? extends V> function;

        /**
         * <code>UniApply</code>
         * <p>Instantiates a new uni apply.</p>
         * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
         * @see java.util.concurrent.Executor
         * @see java.util.function.Function
         */
        UniApply(Executor executor, RestCompletableFuture<V> dep,
                 RestCompletableFuture<T> src,
                 Function<? super T, ? extends V> function) {
            super(executor, dep, src);
            this.function = function;
        }

        final RestCompletableFuture<V> tryFire(int mode) throws RestException {
            RestCompletableFuture<V> d;
            RestCompletableFuture<T> a;
            if ((d = dep) == null ||
                    !d.uniApply(a = src, function, mode > 0 ? null : this))
                return null;
            dep = null;
            src = null;
            function = null;
            return d.postFire(a, mode);
        }
    }

    /**
     * <code>uniApply</code>
     * <p>The uni apply method.</p>
     * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param a   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param f   {@link java.util.function.Function} <p>The f parameter is <code>Function</code> type.</p>
     * @param c   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.UniApply} <p>The c parameter is <code>UniApply</code> type.</p>
     * @return boolean <p>The uni apply return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniApply
     * @see io.github.nichetoolkit.rest.RestException
     */
    final <S> boolean uniApply(RestCompletableFuture<S> a, Function<? super S, ? extends T> f, RestCompletableFuture.UniApply<S, T> c) throws RestException {
        Object r;
        Throwable x;
        if (a == null || (r = a.result) == null || f == null)
            return false;
        tryComplete:
        if (result == null) {
            if (r instanceof RestCompletableFuture.AltResult) {
                if ((x = ((RestCompletableFuture.AltResult) r).ex) != null) {
                    if (x instanceof RestError) {
                        throw new RestException(x.getCause());
                    } else if (x instanceof RestException) {
                        throw (RestException) x;
                    }
                    completeThrowable(x, r);
                    break tryComplete;
                }
                r = null;
            }
            try {
                if (c != null && !c.claim())
                    return false;
                @SuppressWarnings("unchecked") S s = (S) r;
                if (f instanceof FunctionActuator) {
                    completeValue(((FunctionActuator<? super S, ? extends T>) f).actuate(s));
                } else {
                    completeValue(f.apply(s));
                }
            } catch (Throwable ex) {
                if (ex instanceof RestError) {
                    throw new RestException(ex.getCause());
                } else if (ex instanceof RestException) {
                    throw (RestException) ex;
                }
                completeThrowable(ex);
            }
        }
        return true;
    }

    /**
     * <code>uniApplyStage</code>
     * <p>The uni apply stage method.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param e   {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @param f   {@link java.util.function.Function} <p>The f parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The uni apply stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.Executor
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.RestException
     */
    private <V> RestCompletableFuture<V> uniApplyStage(Executor e, Function<? super T, ? extends V> f) throws RestException {
        if (f == null) throw new ParamEmptyException();
        RestCompletableFuture<V> d = new RestCompletableFuture<V>();
        if (e != null || !d.uniApply(this, f, null)) {
            RestCompletableFuture.UniApply<T, V> c = new RestCompletableFuture.UniApply<>(e, d, this, f);
            push(c);
            c.tryFire(SYNC);
        }
        return d;
    }

    /**
     * <code>UniAccept</code>
     * <p>The uni accept class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniCompletion
     * @since Jdk1.8
     */
    static final class UniAccept<T> extends RestCompletableFuture.UniCompletion<T, Void> {
        /**
         * <code>function</code>
         * {@link java.util.function.Consumer} <p>The <code>function</code> field.</p>
         * @see java.util.function.Consumer
         */
        Consumer<? super T> function;

        /**
         * <code>UniAccept</code>
         * <p>Instantiates a new uni accept.</p>
         * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link java.util.function.Consumer} <p>The function parameter is <code>Consumer</code> type.</p>
         * @see java.util.concurrent.Executor
         * @see java.util.function.Consumer
         */
        UniAccept(Executor executor, RestCompletableFuture<Void> dep, RestCompletableFuture<T> src, Consumer<? super T> function) {
            super(executor, dep, src);
            this.function = function;
        }

        final RestCompletableFuture<Void> tryFire(int mode) throws RestException {
            RestCompletableFuture<Void> d;
            RestCompletableFuture<T> a;
            if ((d = dep) == null ||
                    !d.uniAccept(a = src, function, mode > 0 ? null : this))
                return null;
            dep = null;
            src = null;
            function = null;
            return d.postFire(a, mode);
        }
    }

    /**
     * <code>uniAccept</code>
     * <p>The uni accept method.</p>
     * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param a   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param f   {@link java.util.function.Consumer} <p>The f parameter is <code>Consumer</code> type.</p>
     * @param c   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.UniAccept} <p>The c parameter is <code>UniAccept</code> type.</p>
     * @return boolean <p>The uni accept return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.Consumer
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniAccept
     * @see io.github.nichetoolkit.rest.RestException
     */
    final <S> boolean uniAccept(RestCompletableFuture<S> a, Consumer<? super S> f, RestCompletableFuture.UniAccept<S> c) throws RestException {
        Object r;
        Throwable x;
        if (a == null || (r = a.result) == null || f == null)
            return false;
        tryComplete:
        if (result == null) {
            if (r instanceof RestCompletableFuture.AltResult) {
                if ((x = ((RestCompletableFuture.AltResult) r).ex) != null) {
                    if (x instanceof RestError) {
                        throw new RestException(x.getCause());
                    } else if (x instanceof RestException) {
                        throw (RestException) x;
                    }
                    completeThrowable(x, r);
                    break tryComplete;
                }
                r = null;
            }
            try {
                if (c != null && !c.claim())
                    return false;
                @SuppressWarnings("unchecked") S s = (S) r;
                if (f instanceof ConsumerActuator) {
                    ((ConsumerActuator<? super S>) f).actuate(s);
                } else {
                    f.accept(s);
                }
                completeNull();
            } catch (Throwable ex) {
                if (ex instanceof RestError) {
                    throw new RestException(ex.getCause());
                } else if (ex instanceof RestException) {
                    throw (RestException) ex;
                }
                completeThrowable(ex);
            }
        }
        return true;
    }

    /**
     * <code>uniAcceptStage</code>
     * <p>The uni accept stage method.</p>
     * @param e {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @param f {@link java.util.function.Consumer} <p>The f parameter is <code>Consumer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The uni accept stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.Executor
     * @see java.util.function.Consumer
     * @see io.github.nichetoolkit.rest.RestException
     */
    private RestCompletableFuture<Void> uniAcceptStage(Executor e, Consumer<? super T> f) throws RestException {
        if (f == null) throw new ThreadPointerEmptyException();
        RestCompletableFuture<Void> d = new RestCompletableFuture<>();
        if (e != null || !d.uniAccept(this, f, null)) {
            RestCompletableFuture.UniAccept<T> c = new RestCompletableFuture.UniAccept<>(e, d, this, f);
            push(c);
            c.tryFire(SYNC);
        }
        return d;
    }

    /**
     * <code>UniRun</code>
     * <p>The uni run class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniCompletion
     * @since Jdk1.8
     */
    static final class UniRun<T> extends RestCompletableFuture.UniCompletion<T, Void> {
        /**
         * <code>function</code>
         * {@link java.lang.Runnable} <p>The <code>function</code> field.</p>
         * @see java.lang.Runnable
         */
        Runnable function;

        /**
         * <code>UniRun</code>
         * <p>Instantiates a new uni run.</p>
         * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link java.lang.Runnable} <p>The function parameter is <code>Runnable</code> type.</p>
         * @see java.util.concurrent.Executor
         * @see java.lang.Runnable
         */
        UniRun(Executor executor, RestCompletableFuture<Void> dep,
               RestCompletableFuture<T> src, Runnable function) {
            super(executor, dep, src);
            this.function = function;
        }

        final RestCompletableFuture<Void> tryFire(int mode) throws RestException {
            RestCompletableFuture<Void> d;
            RestCompletableFuture<T> a;
            if ((d = dep) == null ||
                    !d.uniRun(a = src, function, mode > 0 ? null : this))
                return null;
            dep = null;
            src = null;
            function = null;
            return d.postFire(a, mode);
        }
    }

    /**
     * <code>uniRun</code>
     * <p>The uni run method.</p>
     * @param a {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param f {@link java.lang.Runnable} <p>The f parameter is <code>Runnable</code> type.</p>
     * @param c {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.UniRun} <p>The c parameter is <code>UniRun</code> type.</p>
     * @return boolean <p>The uni run return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniRun
     * @see io.github.nichetoolkit.rest.RestException
     */
    final boolean uniRun(RestCompletableFuture<?> a, Runnable f, RestCompletableFuture.UniRun<?> c) throws RestException {
        Object r;
        Throwable x;
        if (a == null || (r = a.result) == null || f == null)
            return false;
        if (result == null) {
            if (r instanceof RestCompletableFuture.AltResult && (x = ((RestCompletableFuture.AltResult) r).ex) != null)
                if (x instanceof RestError) {
                    throw new RestException(x.getCause());
                } else if (x instanceof RestException) {
                    throw (RestException) x;
                } else {
                    completeThrowable(x, r);
                }
            else
                try {
                    if (c != null && !c.claim())
                        return false;
                    f.run();
                    completeNull();
                } catch (Throwable ex) {
                    if (ex instanceof RestError) {
                        throw new RestException(ex.getCause());
                    }
                    completeThrowable(ex);
                }
        }
        return true;
    }

    /**
     * <code>uniRunStage</code>
     * <p>The uni run stage method.</p>
     * @param e {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @param f {@link java.lang.Runnable} <p>The f parameter is <code>Runnable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The uni run stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.Executor
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.RestException
     */
    private RestCompletableFuture<Void> uniRunStage(Executor e, Runnable f) throws RestException {
        if (f == null) throw new ThreadPointerEmptyException();
        RestCompletableFuture<Void> d = new RestCompletableFuture<>();
        if (e != null || !d.uniRun(this, f, null)) {
            RestCompletableFuture.UniRun<T> c = new RestCompletableFuture.UniRun<T>(e, d, this, f);
            push(c);
            c.tryFire(SYNC);
        }
        return d;
    }

    /**
     * <code>UniWhenComplete</code>
     * <p>The uni when complete class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniCompletion
     * @since Jdk1.8
     */
    static final class UniWhenComplete<T> extends RestCompletableFuture.UniCompletion<T, T> {
        /**
         * <code>function</code>
         * {@link java.util.function.BiConsumer} <p>The <code>function</code> field.</p>
         * @see java.util.function.BiConsumer
         */
        BiConsumer<? super T, ? super Throwable> function;

        /**
         * <code>UniWhenComplete</code>
         * <p>Instantiates a new uni when complete.</p>
         * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link java.util.function.BiConsumer} <p>The function parameter is <code>BiConsumer</code> type.</p>
         * @see java.util.concurrent.Executor
         * @see java.util.function.BiConsumer
         */
        UniWhenComplete(Executor executor, RestCompletableFuture<T> dep,
                        RestCompletableFuture<T> src,
                        BiConsumer<? super T, ? super Throwable> function) {
            super(executor, dep, src);
            this.function = function;
        }

        final RestCompletableFuture<T> tryFire(int mode) throws RestException {
            RestCompletableFuture<T> d;
            RestCompletableFuture<T> a;
            if ((d = dep) == null ||
                    !d.uniWhenComplete(a = src, function, mode > 0 ? null : this))
                return null;
            dep = null;
            src = null;
            function = null;
            return d.postFire(a, mode);
        }
    }

    /**
     * <code>uniWhenComplete</code>
     * <p>The uni when complete method.</p>
     * @param a {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param f {@link java.util.function.BiConsumer} <p>The f parameter is <code>BiConsumer</code> type.</p>
     * @param c {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.UniWhenComplete} <p>The c parameter is <code>UniWhenComplete</code> type.</p>
     * @return boolean <p>The uni when complete return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.BiConsumer
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniWhenComplete
     * @see io.github.nichetoolkit.rest.RestException
     */
    final boolean uniWhenComplete(RestCompletableFuture<T> a, BiConsumer<? super T, ? super Throwable> f, RestCompletableFuture.UniWhenComplete<T> c) throws RestException {
        Object r;
        T t;
        Throwable x = null;
        if (a == null || (r = a.result) == null || f == null)
            return false;
        if (result == null) {
            try {
                if (c != null && !c.claim())
                    return false;
                if (r instanceof RestCompletableFuture.AltResult) {
                    x = ((RestCompletableFuture.AltResult) r).ex;
                    t = null;
                } else {
                    @SuppressWarnings("unchecked") T tr = (T) r;
                    t = tr;
                }
                if (f instanceof BiConsumerActuator) {
                    ((BiConsumerActuator<? super T, ? super Throwable>) f).actuate(t, x);
                } else {
                    f.accept(t, x);
                }
                if (x == null) {
                    internalComplete(r);
                    return true;
                }
            } catch (Throwable ex) {
                if (x == null)
                    x = ex;
            }
            if (x instanceof RestError) {
                throw new RestException(x.getCause());
            } else if (x instanceof RestException) {
                throw (RestException) x;
            }
            completeThrowable(x, r);
        }
        return true;
    }

    /**
     * <code>uniWhenCompleteStage</code>
     * <p>The uni when complete stage method.</p>
     * @param e {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @param f {@link java.util.function.BiConsumer} <p>The f parameter is <code>BiConsumer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The uni when complete stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.Executor
     * @see java.util.function.BiConsumer
     * @see io.github.nichetoolkit.rest.RestException
     */
    private RestCompletableFuture<T> uniWhenCompleteStage(
            Executor e, BiConsumer<? super T, ? super Throwable> f) throws RestException {
        if (f == null) throw new ThreadPointerEmptyException();
        RestCompletableFuture<T> d = new RestCompletableFuture<>();
        if (e != null || !d.uniWhenComplete(this, f, null)) {
            RestCompletableFuture.UniWhenComplete<T> c = new RestCompletableFuture.UniWhenComplete<>(e, d, this, f);
            push(c);
            c.tryFire(SYNC);
        }
        return d;
    }

    /**
     * <code>UniHandle</code>
     * <p>The uni handle class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniCompletion
     * @since Jdk1.8
     */
    static final class UniHandle<T, V> extends RestCompletableFuture.UniCompletion<T, V> {
        /**
         * <code>function</code>
         * {@link java.util.function.BiFunction} <p>The <code>function</code> field.</p>
         * @see java.util.function.BiFunction
         */
        BiFunction<? super T, Throwable, ? extends V> function;

        /**
         * <code>UniHandle</code>
         * <p>Instantiates a new uni handle.</p>
         * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
         * @see java.util.concurrent.Executor
         * @see java.util.function.BiFunction
         */
        UniHandle(Executor executor, RestCompletableFuture<V> dep,
                  RestCompletableFuture<T> src,
                  BiFunction<? super T, Throwable, ? extends V> function) {
            super(executor, dep, src);
            this.function = function;
        }

        final RestCompletableFuture<V> tryFire(int mode) throws RestException {
            RestCompletableFuture<V> d;
            RestCompletableFuture<T> a;
            if ((d = dep) == null ||
                    !d.uniHandle(a = src, function, mode > 0 ? null : this))
                return null;
            dep = null;
            src = null;
            function = null;
            return d.postFire(a, mode);
        }
    }

    /**
     * <code>uniHandle</code>
     * <p>The uni handle method.</p>
     * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param a   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param f   {@link java.util.function.BiFunction} <p>The f parameter is <code>BiFunction</code> type.</p>
     * @param c   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.UniHandle} <p>The c parameter is <code>UniHandle</code> type.</p>
     * @return boolean <p>The uni handle return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.BiFunction
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniHandle
     * @see io.github.nichetoolkit.rest.RestException
     */
    final <S> boolean uniHandle(RestCompletableFuture<S> a,
                                BiFunction<? super S, Throwable, ? extends T> f,
                                RestCompletableFuture.UniHandle<S, T> c) throws RestException {
        Object r;
        S s;
        Throwable x;
        if (a == null || (r = a.result) == null || f == null)
            return false;
        if (result == null) {
            try {
                if (c != null && !c.claim())
                    return false;
                if (r instanceof RestCompletableFuture.AltResult) {
                    x = ((RestCompletableFuture.AltResult) r).ex;
                    s = null;
                } else {
                    x = null;
                    @SuppressWarnings("unchecked") S ss = (S) r;
                    s = ss;
                }
                if (f instanceof BiFunctionActuator) {
                    completeValue(((BiFunctionActuator<? super S, Throwable, ? extends T>) f).actuate(s, x));
                } else {
                    completeValue(f.apply(s, x));
                }
            } catch (Throwable ex) {
                if (ex instanceof RestError) {
                    throw new RestException(ex.getCause());
                } else if (ex instanceof RestException) {
                    throw (RestException) ex;
                }
                completeThrowable(ex);
            }
        }
        return true;
    }

    /**
     * <code>uniHandleStage</code>
     * <p>The uni handle stage method.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param e   {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @param f   {@link java.util.function.BiFunction} <p>The f parameter is <code>BiFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The uni handle stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.Executor
     * @see java.util.function.BiFunction
     * @see io.github.nichetoolkit.rest.RestException
     */
    private <V> RestCompletableFuture<V> uniHandleStage(
            Executor e, BiFunction<? super T, Throwable, ? extends V> f) throws RestException {
        if (f == null) throw new ThreadPointerEmptyException();
        RestCompletableFuture<V> d = new RestCompletableFuture<>();
        if (e != null || !d.uniHandle(this, f, null)) {
            RestCompletableFuture.UniHandle<T, V> c = new RestCompletableFuture.UniHandle<>(e, d, this, f);
            push(c);
            c.tryFire(SYNC);
        }
        return d;
    }

    /**
     * <code>UniExceptionally</code>
     * <p>The uni exceptionally class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniCompletion
     * @since Jdk1.8
     */
    static final class UniExceptionally<T> extends RestCompletableFuture.UniCompletion<T, T> {
        /**
         * <code>function</code>
         * {@link java.util.function.Function} <p>The <code>function</code> field.</p>
         * @see java.util.function.Function
         */
        Function<? super Throwable, ? extends T> function;

        /**
         * <code>UniExceptionally</code>
         * <p>Instantiates a new uni exceptionally.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
         * @see java.util.function.Function
         */
        UniExceptionally(RestCompletableFuture<T> dep, RestCompletableFuture<T> src,
                         Function<? super Throwable, ? extends T> function) {
            super(null, dep, src);
            this.function = function;
        }

        final RestCompletableFuture<T> tryFire(int mode) throws RestException { // never ASYNC
            // assert mode != ASYNC;
            RestCompletableFuture<T> d;
            RestCompletableFuture<T> a;
            if ((d = dep) == null || !d.uniExceptionally(a = src, function, this))
                return null;
            dep = null;
            src = null;
            function = null;
            return d.postFire(a, mode);
        }
    }

    /**
     * <code>uniExceptionally</code>
     * <p>The uni exceptionally method.</p>
     * @param a {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param f {@link java.util.function.Function} <p>The f parameter is <code>Function</code> type.</p>
     * @param c {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.UniExceptionally} <p>The c parameter is <code>UniExceptionally</code> type.</p>
     * @return boolean <p>The uni exceptionally return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniExceptionally
     * @see io.github.nichetoolkit.rest.RestException
     */
    final boolean uniExceptionally(RestCompletableFuture<T> a, Function<? super Throwable, ? extends T> f,
                                   RestCompletableFuture.UniExceptionally<T> c) throws RestException {
        Object r;
        Throwable x;
        if (a == null || (r = a.result) == null || f == null)
            return false;
        if (result == null) {
            try {
                if (r instanceof RestCompletableFuture.AltResult && (x = ((RestCompletableFuture.AltResult) r).ex) != null) {
                    if (c != null && !c.claim())
                        return false;
                    if (x instanceof RestError) {
                        throw new RestException(x.getCause());
                    } else if (x instanceof RestException) {
                        throw (RestException) x;
                    }
                    if (f instanceof FunctionActuator) {
                        completeValue(((FunctionActuator<? super Throwable, ? extends T>) f).actuate(x));
                    } else {
                        completeValue(f.apply(x));
                    }
                } else
                    internalComplete(r);
            } catch (Throwable ex) {
                if (ex instanceof RestError) {
                    throw new RestException(ex.getCause());
                } else if (ex instanceof RestException) {
                    throw (RestException) ex;
                }
                completeThrowable(ex);
            }
        }
        return true;
    }

    /**
     * <code>uniExceptionallyStage</code>
     * <p>The uni exceptionally stage method.</p>
     * @param f {@link java.util.function.Function} <p>The f parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The uni exceptionally stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.RestException
     */
    private RestCompletableFuture<T> uniExceptionallyStage(Function<Throwable, ? extends T> f) throws RestException {
        if (f == null) throw new ThreadPointerEmptyException();
        RestCompletableFuture<T> d = new RestCompletableFuture<>();
        if (!d.uniExceptionally(this, f, null)) {
            RestCompletableFuture.UniExceptionally<T> c = new RestCompletableFuture.UniExceptionally<>(d, this, f);
            push(c);
            c.tryFire(SYNC);
        }
        return d;
    }

    /**
     * <code>UniRelay</code>
     * <p>The uni relay class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniCompletion
     * @since Jdk1.8
     */
    static final class UniRelay<T> extends RestCompletableFuture.UniCompletion<T, T> { // for Compose
        /**
         * <code>UniRelay</code>
         * <p>Instantiates a new uni relay.</p>
         * @param dep {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         */
        UniRelay(RestCompletableFuture<T> dep, RestCompletableFuture<T> src) {
            super(null, dep, src);
        }

        final RestCompletableFuture<T> tryFire(int mode) throws RestException {
            RestCompletableFuture<T> d;
            RestCompletableFuture<T> a;
            if ((d = dep) == null || !d.uniRelay(a = src))
                return null;
            src = null;
            dep = null;
            return d.postFire(a, mode);
        }
    }

    /**
     * <code>uniRelay</code>
     * <p>The uni relay method.</p>
     * @param a {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @return boolean <p>The uni relay return object is <code>boolean</code> type.</p>
     */
    final boolean uniRelay(RestCompletableFuture<T> a) {
        Object r;
        if (a == null || (r = a.result) == null)
            return false;
        if (result == null) // no need to claim
            completeRelay(r);
        return true;
    }

    /**
     * <code>UniCompose</code>
     * <p>The uni compose class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniCompletion
     * @since Jdk1.8
     */
    static final class UniCompose<T, V> extends RestCompletableFuture.UniCompletion<T, V> {
        /**
         * <code>function</code>
         * {@link java.util.function.Function} <p>The <code>function</code> field.</p>
         * @see java.util.function.Function
         */
        Function<? super T, ? extends CompletionStage<V>> function;

        /**
         * <code>UniCompose</code>
         * <p>Instantiates a new uni compose.</p>
         * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
         * @see java.util.concurrent.Executor
         * @see java.util.function.Function
         */
        UniCompose(Executor executor, RestCompletableFuture<V> dep,
                   RestCompletableFuture<T> src,
                   Function<? super T, ? extends CompletionStage<V>> function) {
            super(executor, dep, src);
            this.function = function;
        }

        final RestCompletableFuture<V> tryFire(int mode) throws RestException {
            RestCompletableFuture<V> d;
            RestCompletableFuture<T> a;
            if ((d = dep) == null ||
                    !d.uniCompose(a = src, function, mode > 0 ? null : this))
                return null;
            dep = null;
            src = null;
            function = null;
            return d.postFire(a, mode);
        }
    }

    /**
     * <code>uniCompose</code>
     * <p>The uni compose method.</p>
     * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param a   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param f   {@link java.util.function.Function} <p>The f parameter is <code>Function</code> type.</p>
     * @param c   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.UniCompose} <p>The c parameter is <code>UniCompose</code> type.</p>
     * @return boolean <p>The uni compose return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniCompose
     * @see io.github.nichetoolkit.rest.RestException
     */
    final <S> boolean uniCompose(RestCompletableFuture<S> a, Function<? super S, ? extends CompletionStage<T>> f, RestCompletableFuture.UniCompose<S, T> c) throws RestException {
        Object r;
        Throwable x;
        if (a == null || (r = a.result) == null || f == null)
            return false;
        tryComplete:
        if (result == null) {
            if (r instanceof RestCompletableFuture.AltResult) {
                if ((x = ((RestCompletableFuture.AltResult) r).ex) != null) {
                    if (x instanceof RestError) {
                        throw new RestException(x.getCause());
                    } else if (x instanceof RestException) {
                        throw (RestException) x;
                    }
                    completeThrowable(x, r);
                    break tryComplete;
                }
                r = null;
            }
            try {
                if (c != null && !c.claim())
                    return false;
                @SuppressWarnings("unchecked") S s = (S) r;
                CompletionStage<T> stage;
                if (f instanceof FunctionActuator) {
                    stage = ((FunctionActuator<? super S, ? extends CompletionStage<T>>) f).actuate(s);
                } else {
                    stage = f.apply(s);
                }
                RestCompletableFuture<T> g;
                if (stage instanceof RestCompletionStage) {
                    g = ((RestCompletionStage<T>) stage).ofCompletableFuture();
                } else {
                    g = of(stage.toCompletableFuture());
                }
                if (g.result == null || !uniRelay(g)) {
                    RestCompletableFuture.UniRelay<T> copy = new RestCompletableFuture.UniRelay<>(this, g);
                    g.push(copy);
                    copy.tryFire(SYNC);
                    if (result == null)
                        return false;
                }
            } catch (Throwable ex) {
                if (ex instanceof RestError) {
                    throw new RestException(ex.getCause());
                } else if (ex instanceof RestException) {
                    throw (RestException) ex;
                }
                completeThrowable(ex);
            }
        }
        return true;
    }

    /**
     * <code>uniComposeStage</code>
     * <p>The uni compose stage method.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param e   {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @param f   {@link java.util.function.Function} <p>The f parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The uni compose stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.Executor
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.RestException
     */
    private <V> RestCompletableFuture<V> uniComposeStage(
            Executor e, Function<? super T, ? extends CompletionStage<V>> f) throws RestException {
        if (f == null) throw new ThreadPointerEmptyException();
        Object r;
        Throwable x;
        if (e == null && (r = result) != null) {
            // try to return function result directly
            if (r instanceof RestCompletableFuture.AltResult) {
                if ((x = ((RestCompletableFuture.AltResult) r).ex) != null) {
                    if (x instanceof RestError) {
                        throw new RestException(x.getCause());
                    } else if (x instanceof RestException) {
                        throw (RestException) x;
                    }
                    return new RestCompletableFuture<>(encodeThrowable(x, r));
                }
                r = null;
            }
            try {
                @SuppressWarnings("unchecked") T t = (T) r;
                CompletionStage<V> stage;
                if (f instanceof FunctionActuator) {
                    stage = ((FunctionActuator<? super T, ? extends CompletionStage<V>>) f).actuate(t);
                } else {
                    stage = f.apply(t);
                }
                RestCompletableFuture<V> g;
                if (stage instanceof RestCompletionStage) {
                    g = ((RestCompletionStage<V>) stage).ofCompletableFuture();
                } else {
                    g = of(stage.toCompletableFuture());
                }
                Object s = g.result;
                if (s != null)
                    return new RestCompletableFuture<>(encodeRelay(s));
                RestCompletableFuture<V> d = new RestCompletableFuture<>();
                RestCompletableFuture.UniRelay<V> copy = new RestCompletableFuture.UniRelay<>(d, g);
                g.push(copy);
                copy.tryFire(SYNC);
                return d;
            } catch (Throwable ex) {
                if (ex instanceof RestError) {
                    throw new RestException(ex.getCause());
                } else if (ex instanceof RestException) {
                    throw (RestException) ex;
                }
                return new RestCompletableFuture<>(encodeThrowable(ex));
            }
        }
        RestCompletableFuture<V> d = new RestCompletableFuture<>();
        RestCompletableFuture.UniCompose<T, V> c = new RestCompletableFuture.UniCompose<>(e, d, this, f);
        push(c);
        c.tryFire(SYNC);
        return d;
    }

    /* ------------- Two-input Completions -------------- */

    /**
     * <code>BiCompletion</code>
     * <p>The bi completion class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.UniCompletion
     * @since Jdk1.8
     */
    abstract static class BiCompletion<T, U, V> extends RestCompletableFuture.UniCompletion<T, V> {
        /**
         * <code>snd</code>
         * {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The <code>snd</code> field.</p>
         */
        RestCompletableFuture<U> snd; // second source for action

        /**
         * <code>BiCompletion</code>
         * <p>Instantiates a new bi completion.</p>
         * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param snd      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The snd parameter is <code>RestCompletableFuture</code> type.</p>
         * @see java.util.concurrent.Executor
         */
        BiCompletion(Executor executor, RestCompletableFuture<V> dep,
                     RestCompletableFuture<T> src, RestCompletableFuture<U> snd) {
            super(executor, dep, src);
            this.snd = snd;
        }
    }

    /**
     * <code>CoCompletion</code>
     * <p>The co completion class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion
     * @since Jdk1.8
     */
    static final class CoCompletion extends RestCompletableFuture.Completion {
        /**
         * <code>base</code>
         * <p>The base field.</p>
         * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion
         */
        RestCompletableFuture.BiCompletion<?, ?, ?> base;

        /**
         * <code>CoCompletion</code>
         * <p>Instantiates a new co completion.</p>
         * @param base {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion} <p>The base parameter is <code>BiCompletion</code> type.</p>
         * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion
         */
        CoCompletion(RestCompletableFuture.BiCompletion<?, ?, ?> base) {
            this.base = base;
        }

        final RestCompletableFuture<?> tryFire(int mode) throws RestException {
            RestCompletableFuture.BiCompletion<?, ?, ?> c;
            RestCompletableFuture<?> d;
            if ((c = base) == null || (d = c.tryFire(mode)) == null)
                return null;
            base = null; // detach
            return d;
        }

        final boolean isLive() {
            RestCompletableFuture.BiCompletion<?, ?, ?> c;
            return (c = base) != null && c.dep != null;
        }
    }

    /**
     * <code>bipush</code>
     * <p>The bipush method.</p>
     * @param b {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The b parameter is <code>RestCompletableFuture</code> type.</p>
     * @param c {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion} <p>The c parameter is <code>BiCompletion</code> type.</p>
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion
     */
    final void bipush(RestCompletableFuture<?> b, RestCompletableFuture.BiCompletion<?, ?, ?> c) {
        if (c != null) {
            Object r;
            while ((r = result) == null && !tryPushStack(c))
                lazySetNext(c, null); // clear on failure
            if (b != null && b != this && b.result == null) {
                RestCompletableFuture.Completion q = (r != null) ? c : new RestCompletableFuture.CoCompletion(c);
                while (b.result == null && !b.tryPushStack(q))
                    lazySetNext(q, null); // clear on failure
            }
        }
    }

    /**
     * <code>postFire</code>
     * <p>The post fire method.</p>
     * @param a    {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param b    {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The b parameter is <code>RestCompletableFuture</code> type.</p>
     * @param mode int <p>The mode parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The post fire return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    final RestCompletableFuture<T> postFire(RestCompletableFuture<?> a,
                                            RestCompletableFuture<?> b, int mode) throws RestException {
        if (b != null && b.stack != null) { // clean second source
            if (mode < 0 || b.result == null)
                b.cleanStack();
            else
                b.postComplete();
        }
        return postFire(a, mode);
    }

    /**
     * <code>BiApply</code>
     * <p>The bi apply class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion
     * @since Jdk1.8
     */
    static final class BiApply<T, U, V> extends RestCompletableFuture.BiCompletion<T, U, V> {
        /**
         * <code>function</code>
         * {@link java.util.function.BiFunction} <p>The <code>function</code> field.</p>
         * @see java.util.function.BiFunction
         */
        BiFunction<? super T, ? super U, ? extends V> function;

        /**
         * <code>BiApply</code>
         * <p>Instantiates a new bi apply.</p>
         * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param snd      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The snd parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link java.util.function.BiFunction} <p>The function parameter is <code>BiFunction</code> type.</p>
         * @see java.util.concurrent.Executor
         * @see java.util.function.BiFunction
         */
        BiApply(Executor executor, RestCompletableFuture<V> dep,
                RestCompletableFuture<T> src, RestCompletableFuture<U> snd,
                BiFunction<? super T, ? super U, ? extends V> function) {
            super(executor, dep, src, snd);
            this.function = function;
        }

        final RestCompletableFuture<V> tryFire(int mode) throws RestException {
            RestCompletableFuture<V> d;
            RestCompletableFuture<T> a;
            RestCompletableFuture<U> b;
            if ((d = dep) == null ||
                    !d.biApply(a = src, b = snd, function, mode > 0 ? null : this))
                return null;
            dep = null;
            src = null;
            snd = null;
            function = null;
            return d.postFire(a, b, mode);
        }
    }

    /**
     * <code>biApply</code>
     * <p>The bi apply method.</p>
     * @param <R> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param a   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param b   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The b parameter is <code>RestCompletableFuture</code> type.</p>
     * @param f   {@link java.util.function.BiFunction} <p>The f parameter is <code>BiFunction</code> type.</p>
     * @param c   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.BiApply} <p>The c parameter is <code>BiApply</code> type.</p>
     * @return boolean <p>The bi apply return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.BiFunction
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiApply
     * @see io.github.nichetoolkit.rest.RestException
     */
    final <R, S> boolean biApply(RestCompletableFuture<R> a,
                                 RestCompletableFuture<S> b,
                                 BiFunction<? super R, ? super S, ? extends T> f,
                                 RestCompletableFuture.BiApply<R, S, T> c) throws RestException {
        Object r, s;
        Throwable x;
        if (a == null || (r = a.result) == null ||
                b == null || (s = b.result) == null || f == null)
            return false;
        tryComplete:
        if (result == null) {
            if (r instanceof RestCompletableFuture.AltResult) {
                if ((x = ((RestCompletableFuture.AltResult) r).ex) != null) {
                    if (x instanceof RestError) {
                        throw new RestException(x.getCause());
                    } else if (x instanceof RestException) {
                        throw (RestException) x;
                    }
                    completeThrowable(x, r);
                    break tryComplete;
                }
                r = null;
            }
            if (s instanceof RestCompletableFuture.AltResult) {
                if ((x = ((RestCompletableFuture.AltResult) s).ex) != null) {
                    if (x instanceof RestError) {
                        throw new RestException(x.getCause());
                    } else if (x instanceof RestException) {
                        throw (RestException) x;
                    }
                    completeThrowable(x, s);
                    break tryComplete;
                }
                s = null;
            }
            try {
                if (c != null && !c.claim())
                    return false;
                @SuppressWarnings("unchecked") R rr = (R) r;
                @SuppressWarnings("unchecked") S ss = (S) s;
                if (f instanceof BiFunctionActuator) {
                    completeValue(((BiFunctionActuator<? super R, ? super S, ? extends T>) f).actuate(rr, ss));
                } else {
                    completeValue(f.apply(rr, ss));
                }
            } catch (Throwable ex) {
                if (ex instanceof RestError) {
                    throw new RestException(ex.getCause());
                } else if (ex instanceof RestException) {
                    throw (RestException) ex;
                }
                completeThrowable(ex);
            }
        }
        return true;
    }

    /**
     * <code>biApplyStage</code>
     * <p>The bi apply stage method.</p>
     * @param <U> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param e   {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @param o   {@link java.util.concurrent.CompletionStage} <p>The o parameter is <code>CompletionStage</code> type.</p>
     * @param f   {@link java.util.function.BiFunction} <p>The f parameter is <code>BiFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The bi apply stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.Executor
     * @see java.util.concurrent.CompletionStage
     * @see java.util.function.BiFunction
     * @see io.github.nichetoolkit.rest.RestException
     */
    private <U, V> RestCompletableFuture<V> biApplyStage(
            Executor e, CompletionStage<U> o,
            BiFunction<? super T, ? super U, ? extends V> f) throws RestException {
        RestCompletableFuture<U> b;
        if (o instanceof RestCompletionStage) {
            b = ((RestCompletionStage<U>) o).ofCompletableFuture();
        } else {
            b = of(o.toCompletableFuture());
        }
        if (f == null || b == null)
            throw new ThreadPointerEmptyException();
        RestCompletableFuture<V> d = new RestCompletableFuture<V>();
        if (e != null || !d.biApply(this, b, f, null)) {
            RestCompletableFuture.BiApply<T, U, V> c = new RestCompletableFuture.BiApply<>(e, d, this, b, f);
            bipush(b, c);
            c.tryFire(SYNC);
        }
        return d;
    }

    /**
     * <code>BiAccept</code>
     * <p>The bi accept class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion
     * @since Jdk1.8
     */
    static final class BiAccept<T, U> extends RestCompletableFuture.BiCompletion<T, U, Void> {
        /**
         * <code>function</code>
         * {@link java.util.function.BiConsumer} <p>The <code>function</code> field.</p>
         * @see java.util.function.BiConsumer
         */
        BiConsumer<? super T, ? super U> function;

        /**
         * <code>BiAccept</code>
         * <p>Instantiates a new bi accept.</p>
         * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param snd      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The snd parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link java.util.function.BiConsumer} <p>The function parameter is <code>BiConsumer</code> type.</p>
         * @see java.util.concurrent.Executor
         * @see java.util.function.BiConsumer
         */
        BiAccept(Executor executor, RestCompletableFuture<Void> dep,
                 RestCompletableFuture<T> src, RestCompletableFuture<U> snd,
                 BiConsumer<? super T, ? super U> function) {
            super(executor, dep, src, snd);
            this.function = function;
        }

        final RestCompletableFuture<Void> tryFire(int mode) throws RestException {
            RestCompletableFuture<Void> d;
            RestCompletableFuture<T> a;
            RestCompletableFuture<U> b;
            if ((d = dep) == null ||
                    !d.biAccept(a = src, b = snd, function, mode > 0 ? null : this))
                return null;
            dep = null;
            src = null;
            snd = null;
            function = null;
            return d.postFire(a, b, mode);
        }
    }

    /**
     * <code>biAccept</code>
     * <p>The bi accept method.</p>
     * @param <R> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param a   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param b   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The b parameter is <code>RestCompletableFuture</code> type.</p>
     * @param f   {@link java.util.function.BiConsumer} <p>The f parameter is <code>BiConsumer</code> type.</p>
     * @param c   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.BiAccept} <p>The c parameter is <code>BiAccept</code> type.</p>
     * @return boolean <p>The bi accept return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.BiConsumer
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiAccept
     * @see io.github.nichetoolkit.rest.RestException
     */
    final <R, S> boolean biAccept(RestCompletableFuture<R> a,
                                  RestCompletableFuture<S> b,
                                  BiConsumer<? super R, ? super S> f,
                                  RestCompletableFuture.BiAccept<R, S> c) throws RestException {
        Object r, s;
        Throwable x;
        if (a == null || (r = a.result) == null ||
                b == null || (s = b.result) == null || f == null)
            return false;
        tryComplete:
        if (result == null) {
            if (r instanceof RestCompletableFuture.AltResult) {
                if ((x = ((RestCompletableFuture.AltResult) r).ex) != null) {
                    if (x instanceof RestError) {
                        throw new RestException(x.getCause());
                    } else if (x instanceof RestException) {
                        throw (RestException) x;
                    }
                    completeThrowable(x, r);
                    break tryComplete;
                }
                r = null;
            }
            if (s instanceof RestCompletableFuture.AltResult) {
                if ((x = ((RestCompletableFuture.AltResult) s).ex) != null) {
                    if (x instanceof RestError) {
                        throw new RestException(x.getCause());
                    } else if (x instanceof RestException) {
                        throw (RestException) x;
                    }
                    completeThrowable(x, s);
                    break tryComplete;
                }
                s = null;
            }
            try {
                if (c != null && !c.claim())
                    return false;
                @SuppressWarnings("unchecked") R rr = (R) r;
                @SuppressWarnings("unchecked") S ss = (S) s;
                if (f instanceof BiConsumerActuator) {
                    ((BiConsumerActuator<? super R, ? super S>) f).actuate(rr, ss);
                } else {
                    f.accept(rr, ss);
                }
                completeNull();
            } catch (Throwable ex) {
                if (ex instanceof RestError) {
                    throw new RestException(ex.getCause());
                } else if (ex instanceof RestException) {
                    throw (RestException) ex;
                }
                completeThrowable(ex);
            }
        }
        return true;
    }

    /**
     * <code>biAcceptStage</code>
     * <p>The bi accept stage method.</p>
     * @param <U> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param e   {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @param o   {@link java.util.concurrent.CompletionStage} <p>The o parameter is <code>CompletionStage</code> type.</p>
     * @param f   {@link java.util.function.BiConsumer} <p>The f parameter is <code>BiConsumer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The bi accept stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.Executor
     * @see java.util.concurrent.CompletionStage
     * @see java.util.function.BiConsumer
     * @see io.github.nichetoolkit.rest.RestException
     */
    private <U> RestCompletableFuture<Void> biAcceptStage(Executor e, CompletionStage<U> o, BiConsumer<? super T, ? super U> f) throws RestException {
        RestCompletableFuture<U> b;
        if (o instanceof RestCompletionStage) {
            b = ((RestCompletionStage<U>) o).ofCompletableFuture();
        } else {
            b = of(o.toCompletableFuture());
        }
        if (f == null || b == null)
            throw new ThreadPointerEmptyException();
        RestCompletableFuture<Void> d = new RestCompletableFuture<>();
        if (e != null || !d.biAccept(this, b, f, null)) {
            RestCompletableFuture.BiAccept<T, U> c = new RestCompletableFuture.BiAccept<>(e, d, this, b, f);
            bipush(b, c);
            c.tryFire(SYNC);
        }
        return d;
    }

    /**
     * <code>BiRun</code>
     * <p>The bi run class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion
     * @since Jdk1.8
     */
    static final class BiRun<T, U> extends RestCompletableFuture.BiCompletion<T, U, Void> {
        /**
         * <code>function</code>
         * {@link java.lang.Runnable} <p>The <code>function</code> field.</p>
         * @see java.lang.Runnable
         */
        Runnable function;

        /**
         * <code>BiRun</code>
         * <p>Instantiates a new bi run.</p>
         * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param snd      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The snd parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link java.lang.Runnable} <p>The function parameter is <code>Runnable</code> type.</p>
         * @see java.util.concurrent.Executor
         * @see java.lang.Runnable
         */
        BiRun(Executor executor, RestCompletableFuture<Void> dep,
              RestCompletableFuture<T> src,
              RestCompletableFuture<U> snd,
              Runnable function) {
            super(executor, dep, src, snd);
            this.function = function;
        }

        final RestCompletableFuture<Void> tryFire(int mode) throws RestException {
            RestCompletableFuture<Void> d;
            RestCompletableFuture<T> a;
            RestCompletableFuture<U> b;
            if ((d = dep) == null ||
                    !d.biRun(a = src, b = snd, function, mode > 0 ? null : this))
                return null;
            dep = null;
            src = null;
            snd = null;
            function = null;
            return d.postFire(a, b, mode);
        }
    }

    /**
     * <code>biRun</code>
     * <p>The bi run method.</p>
     * @param a {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param b {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The b parameter is <code>RestCompletableFuture</code> type.</p>
     * @param f {@link java.lang.Runnable} <p>The f parameter is <code>Runnable</code> type.</p>
     * @param c {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.BiRun} <p>The c parameter is <code>BiRun</code> type.</p>
     * @return boolean <p>The bi run return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiRun
     * @see io.github.nichetoolkit.rest.RestException
     */
    final boolean biRun(RestCompletableFuture<?> a, RestCompletableFuture<?> b,
                        Runnable f, RestCompletableFuture.BiRun<?, ?> c) throws RestException {
        Object r, s;
        Throwable x;
        if (a == null || (r = a.result) == null ||
                b == null || (s = b.result) == null || f == null)
            return false;
        if (result == null) {
            if (r instanceof RestCompletableFuture.AltResult && (x = ((RestCompletableFuture.AltResult) r).ex) != null)
                if (x instanceof RestError) {
                    throw new RestException(x.getCause());
                } else if (x instanceof RestException) {
                    throw (RestException) x;
                } else {
                    completeThrowable(x, r);
                }
            else if (s instanceof RestCompletableFuture.AltResult && (x = ((RestCompletableFuture.AltResult) s).ex) != null)
                if (x instanceof RestError) {
                    throw new RestException(x.getCause());
                } else if (x instanceof RestException) {
                    throw (RestException) x;
                } else {
                    completeThrowable(x, s);
                }
            else
                try {
                    if (c != null && !c.claim())
                        return false;
                    f.run();
                    completeNull();
                } catch (Throwable ex) {
                    if (ex instanceof RestError) {
                        throw new RestException(ex.getCause());
                    } else if (ex instanceof RestException) {
                        throw (RestException) ex;
                    }
                    completeThrowable(ex);
                }
        }
        return true;
    }

    /**
     * <code>biRunStage</code>
     * <p>The bi run stage method.</p>
     * @param e {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @param o {@link java.util.concurrent.CompletionStage} <p>The o parameter is <code>CompletionStage</code> type.</p>
     * @param f {@link java.lang.Runnable} <p>The f parameter is <code>Runnable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The bi run stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.Executor
     * @see java.util.concurrent.CompletionStage
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.RestException
     */
    private RestCompletableFuture<Void> biRunStage(Executor e, CompletionStage<?> o, Runnable f) throws RestException {
        RestCompletableFuture<?> b;
        if (o instanceof RestCompletionStage) {
            b = ((RestCompletionStage<?>) o).ofCompletableFuture();
        } else {
            b = of(o.toCompletableFuture());
        }
        if (f == null || b == null)
            throw new ThreadPointerEmptyException();
        RestCompletableFuture<Void> d = new RestCompletableFuture<>();
        if (e != null || !d.biRun(this, b, f, null)) {
            RestCompletableFuture.BiRun<T, ?> c = new RestCompletableFuture.BiRun<>(e, d, this, b, f);
            bipush(b, c);
            c.tryFire(SYNC);
        }
        return d;
    }

    /**
     * <code>BiRelay</code>
     * <p>The bi relay class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion
     * @since Jdk1.8
     */
    static final class BiRelay<T, U> extends RestCompletableFuture.BiCompletion<T, U, Void> { // for And
        /**
         * <code>BiRelay</code>
         * <p>Instantiates a new bi relay.</p>
         * @param dep {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param snd {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The snd parameter is <code>RestCompletableFuture</code> type.</p>
         */
        BiRelay(RestCompletableFuture<Void> dep,
                RestCompletableFuture<T> src,
                RestCompletableFuture<U> snd) {
            super(null, dep, src, snd);
        }

        final RestCompletableFuture<Void> tryFire(int mode) throws RestException {
            RestCompletableFuture<Void> d;
            RestCompletableFuture<T> a;
            RestCompletableFuture<U> b;
            if ((d = dep) == null || !d.biRelay(a = src, b = snd))
                return null;
            src = null;
            snd = null;
            dep = null;
            return d.postFire(a, b, mode);
        }
    }

    /**
     * <code>biRelay</code>
     * <p>The bi relay method.</p>
     * @param a {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param b {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The b parameter is <code>RestCompletableFuture</code> type.</p>
     * @return boolean <p>The bi relay return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    boolean biRelay(RestCompletableFuture<?> a, RestCompletableFuture<?> b) throws RestException {
        Object r, s;
        Throwable x;
        if (a == null || (r = a.result) == null ||
                b == null || (s = b.result) == null)
            return false;
        if (result == null) {
            if (r instanceof RestCompletableFuture.AltResult && (x = ((RestCompletableFuture.AltResult) r).ex) != null)
                if (x instanceof RestError) {
                    throw new RestException(x.getCause());
                } else if (x instanceof RestException) {
                    throw (RestException) x;
                } else {
                    completeThrowable(x, r);
                }
            else if (s instanceof RestCompletableFuture.AltResult && (x = ((RestCompletableFuture.AltResult) s).ex) != null)
                if (x instanceof RestError) {
                    throw new RestException(x.getCause());
                } else if (x instanceof RestException) {
                    throw (RestException) x;
                } else {
                    completeThrowable(x, s);
                }
            else
                completeNull();
        }
        return true;
    }

    /**
     * <code>biRelay</code>
     * <p>The bi relay method.</p>
     * @param a {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @return boolean <p>The bi relay return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    boolean biRelay(RestCompletableFuture<?> a) throws RestException {
        Object r;
        Throwable x;
        if (a == null || (r = a.result) == null)
            return false;
        if (result == null) {
            if (r instanceof RestCompletableFuture.AltResult && (x = ((RestCompletableFuture.AltResult) r).ex) != null)
                if (x instanceof RestError) {
                    throw new RestException(x.getCause());
                } else if (x instanceof RestException) {
                    throw (RestException) x;
                } else {
                    completeThrowable(x, r);
                }
            else
                completeNull();
        }
        return true;
    }

    /**
     * <code>biRelay</code>
     * <p>The bi relay method.</p>
     * @param a {@link java.util.concurrent.CompletableFuture} <p>The a parameter is <code>CompletableFuture</code> type.</p>
     * @param b {@link java.util.concurrent.CompletableFuture} <p>The b parameter is <code>CompletableFuture</code> type.</p>
     * @return boolean <p>The bi relay return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.CompletableFuture
     * @see io.github.nichetoolkit.rest.RestException
     */
    boolean biRelay(CompletableFuture<?> a, CompletableFuture<?> b) throws RestException {
        Object r, s;
        Throwable x;
        if (a == null || (r = a.getNow(null)) == null ||
                b == null || (s = b.getNow(null)) == null)
            return false;
        if (result == null) {
            if (r instanceof RestCompletableFuture.AltResult && (x = ((RestCompletableFuture.AltResult) r).ex) != null)
                if (x instanceof RestError) {
                    throw new RestException(x.getCause());
                } else if (x instanceof RestException) {
                    throw (RestException) x;
                } else {
                    completeThrowable(x, r);
                }
            else if (s instanceof RestCompletableFuture.AltResult && (x = ((RestCompletableFuture.AltResult) s).ex) != null)
                if (x instanceof RestError) {
                    throw new RestException(x.getCause());
                } else if (x instanceof RestException) {
                    throw (RestException) x;
                } else {
                    completeThrowable(x, s);
                }
            else
                completeNull();
        }
        return true;
    }

    /**
     * <code>biRelay</code>
     * <p>The bi relay method.</p>
     * @param a {@link java.util.concurrent.CompletableFuture} <p>The a parameter is <code>CompletableFuture</code> type.</p>
     * @return boolean <p>The bi relay return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.CompletableFuture
     * @see io.github.nichetoolkit.rest.RestException
     */
    boolean biRelay(CompletableFuture<?> a) throws RestException {
        Object r;
        Throwable x;
        if (a == null || (r = a.getNow(null)) == null)
            return false;
        if (result == null) {
            if (r instanceof RestCompletableFuture.AltResult && (x = ((RestCompletableFuture.AltResult) r).ex) != null)
                if (x instanceof RestError) {
                    throw new RestException(x.getCause());
                } else if (x instanceof RestException) {
                    throw (RestException) x;
                } else {
                    completeThrowable(x, r);
                }
            else
                completeNull();
        }
        return true;
    }

    /**
     * <code>andTree</code>
     * <p>The and tree method.</p>
     * @param cfs {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The cfs parameter is <code>RestCompletableFuture</code> type.</p>
     * @param lo  int <p>The lo parameter is <code>int</code> type.</p>
     * @param hi  int <p>The hi parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The and tree return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    static RestCompletableFuture<Void> andTree(RestCompletableFuture<?>[] cfs,
                                               int lo, int hi) throws RestException {
        RestCompletableFuture<Void> d = new RestCompletableFuture<>();
        if (lo > hi) // empty
            d.result = NIL;
        else {
            RestCompletableFuture<?> a, b;
            int mid = (lo + hi) >>> 1;
            if ((a = (lo == mid ? cfs[lo] :
                    andTree(cfs, lo, mid))) == null ||
                    (b = (lo == hi ? a : (hi == mid + 1) ? cfs[hi] :
                            andTree(cfs, mid + 1, hi))) == null)
                throw new ThreadPointerEmptyException();
            if (!d.biRelay(a, b)) {
                RestCompletableFuture.BiRelay<?, ?> c = new RestCompletableFuture.BiRelay<>(d, a, b);
                a.bipush(b, c);
                c.tryFire(SYNC);
            }
        }
        return d;
    }

    /**
     * <code>andThen</code>
     * <p>The and then method.</p>
     * @param a {@link java.util.concurrent.CompletableFuture} <p>The a parameter is <code>CompletableFuture</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The and then return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.CompletableFuture
     * @see io.github.nichetoolkit.rest.RestException
     */
    static RestCompletableFuture<Void> andThen(CompletableFuture<Void> a) throws RestException {
        RestCompletableFuture<Void> d = new RestCompletableFuture<>();
        if (a == null)
            throw new ThreadPointerEmptyException();
        if (!d.biRelay(a)) {
            andThen(CompletableFuture.allOf(a));
        } else {
            d.completeNull();
        }
        return d;
    }

    /* ------------- Projected (Ored) BiCompletions -------------- */

    /**
     * <code>orpush</code>
     * <p>The orpush method.</p>
     * @param b {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The b parameter is <code>RestCompletableFuture</code> type.</p>
     * @param c {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion} <p>The c parameter is <code>BiCompletion</code> type.</p>
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion
     */
    final void orpush(RestCompletableFuture<?> b, RestCompletableFuture.BiCompletion<?, ?, ?> c) {
        if (c != null) {
            while ((b == null || b.result == null) && result == null) {
                if (tryPushStack(c)) {
                    if (b != null && b != this && b.result == null) {
                        RestCompletableFuture.Completion q = new RestCompletableFuture.CoCompletion(c);
                        while (result == null && b.result == null &&
                                !b.tryPushStack(q))
                            lazySetNext(q, null); // clear on failure
                    }
                    break;
                }
                lazySetNext(c, null); // clear on failure
            }
        }
    }

    /**
     * <code>OrApply</code>
     * <p>The or apply class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U> T <p>The generic parameter is <code>T</code> type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see T
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion
     * @since Jdk1.8
     */
    static final class OrApply<T, U extends T, V> extends RestCompletableFuture.BiCompletion<T, U, V> {
        /**
         * <code>function</code>
         * {@link java.util.function.Function} <p>The <code>function</code> field.</p>
         * @see java.util.function.Function
         */
        Function<? super T, ? extends V> function;

        /**
         * <code>OrApply</code>
         * <p>Instantiates a new or apply.</p>
         * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param snd      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The snd parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
         * @see java.util.concurrent.Executor
         * @see java.util.function.Function
         */
        OrApply(Executor executor, RestCompletableFuture<V> dep,
                RestCompletableFuture<T> src,
                RestCompletableFuture<U> snd,
                Function<? super T, ? extends V> function) {
            super(executor, dep, src, snd);
            this.function = function;
        }

        RestCompletableFuture<V> tryFire(int mode) throws RestException {
            RestCompletableFuture<V> d;
            RestCompletableFuture<T> a;
            RestCompletableFuture<U> b;
            if ((d = dep) == null ||
                    !d.orApply(a = src, b = snd, function, mode > 0 ? null : this))
                return null;
            dep = null;
            src = null;
            snd = null;
            function = null;
            return d.postFire(a, b, mode);
        }
    }

    /**
     * <code>orApply</code>
     * <p>The or apply method.</p>
     * @param <R> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S> R <p>The generic parameter is <code>R</code> type.</p>
     * @param a   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param b   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The b parameter is <code>RestCompletableFuture</code> type.</p>
     * @param f   {@link java.util.function.Function} <p>The f parameter is <code>Function</code> type.</p>
     * @param c   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.OrApply} <p>The c parameter is <code>OrApply</code> type.</p>
     * @return boolean <p>The or apply return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.OrApply
     * @see io.github.nichetoolkit.rest.RestException
     */
    final <R, S extends R> boolean orApply(RestCompletableFuture<R> a,
                                           RestCompletableFuture<S> b,
                                           Function<? super R, ? extends T> f,
                                           RestCompletableFuture.OrApply<R, S, T> c) throws RestException {
        Object r;
        Throwable x;
        if (a == null || b == null ||
                ((r = a.result) == null && (r = b.result) == null) || f == null)
            return false;
        tryComplete:
        if (result == null) {
            try {
                if (c != null && !c.claim())
                    return false;
                if (r instanceof RestCompletableFuture.AltResult) {
                    if ((x = ((RestCompletableFuture.AltResult) r).ex) != null) {
                        if (x instanceof RestError) {
                            throw new RestException(x.getCause());
                        } else if (x instanceof RestException) {
                            throw (RestException) x;
                        }
                        completeThrowable(x, r);
                        break tryComplete;
                    }
                    r = null;
                }
                @SuppressWarnings("unchecked") R rr = (R) r;
                if (f instanceof FunctionActuator) {
                    completeValue(((FunctionActuator<? super R, ? extends T>) f).actuate(rr));
                } else {
                    completeValue(f.apply(rr));
                }
            } catch (Throwable ex) {
                if (ex instanceof RestError) {
                    throw new RestException(ex.getCause());
                } else if (ex instanceof RestException) {
                    throw (RestException) ex;
                }
                completeThrowable(ex);
            }
        }
        return true;
    }

    /**
     * <code>orApplyStage</code>
     * <p>The or apply stage method.</p>
     * @param <U> T <p>The generic parameter is <code>T</code> type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param e   {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @param o   {@link java.util.concurrent.CompletionStage} <p>The o parameter is <code>CompletionStage</code> type.</p>
     * @param f   {@link java.util.function.Function} <p>The f parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The or apply stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see T
     * @see java.util.concurrent.Executor
     * @see java.util.concurrent.CompletionStage
     * @see java.util.function.Function
     * @see io.github.nichetoolkit.rest.RestException
     */
    private <U extends T, V> RestCompletableFuture<V> orApplyStage(Executor e, CompletionStage<U> o, Function<? super T, ? extends V> f) throws RestException {
        RestCompletableFuture<U> b;
        if (o instanceof RestCompletionStage) {
            b = ((RestCompletionStage<U>) o).ofCompletableFuture();
        } else {
            b = of(o.toCompletableFuture());
        }
        if (f == null || b == null)
            throw new ThreadPointerEmptyException();
        RestCompletableFuture<V> d = new RestCompletableFuture<>();
        if (e != null || !d.orApply(this, b, f, null)) {
            RestCompletableFuture.OrApply<T, U, V> c = new RestCompletableFuture.OrApply<>(e, d, this, b, f);
            orpush(b, c);
            c.tryFire(SYNC);
        }
        return d;
    }

    /**
     * <code>OrAccept</code>
     * <p>The or accept class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U> T <p>The generic parameter is <code>T</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see T
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion
     * @since Jdk1.8
     */
    static final class OrAccept<T, U extends T> extends RestCompletableFuture.BiCompletion<T, U, Void> {
        /**
         * <code>function</code>
         * {@link java.util.function.Consumer} <p>The <code>function</code> field.</p>
         * @see java.util.function.Consumer
         */
        Consumer<? super T> function;

        /**
         * <code>OrAccept</code>
         * <p>Instantiates a new or accept.</p>
         * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param snd      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The snd parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link java.util.function.Consumer} <p>The function parameter is <code>Consumer</code> type.</p>
         * @see java.util.concurrent.Executor
         * @see java.util.function.Consumer
         */
        OrAccept(Executor executor, RestCompletableFuture<Void> dep,
                 RestCompletableFuture<T> src,
                 RestCompletableFuture<U> snd,
                 Consumer<? super T> function) {
            super(executor, dep, src, snd);
            this.function = function;
        }

        RestCompletableFuture<Void> tryFire(int mode) throws RestException {
            RestCompletableFuture<Void> d;
            RestCompletableFuture<T> a;
            RestCompletableFuture<U> b;
            if ((d = dep) == null ||
                    !d.orAccept(a = src, b = snd, function, mode > 0 ? null : this))
                return null;
            dep = null;
            src = null;
            snd = null;
            function = null;
            return d.postFire(a, b, mode);
        }
    }

    /**
     * <code>orAccept</code>
     * <p>The or accept method.</p>
     * @param <R> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S> R <p>The generic parameter is <code>R</code> type.</p>
     * @param a   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param b   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The b parameter is <code>RestCompletableFuture</code> type.</p>
     * @param f   {@link java.util.function.Consumer} <p>The f parameter is <code>Consumer</code> type.</p>
     * @param c   {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.OrAccept} <p>The c parameter is <code>OrAccept</code> type.</p>
     * @return boolean <p>The or accept return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.Consumer
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.OrAccept
     * @see io.github.nichetoolkit.rest.RestException
     */
    final <R, S extends R> boolean orAccept(RestCompletableFuture<R> a,
                                            RestCompletableFuture<S> b,
                                            Consumer<? super R> f,
                                            RestCompletableFuture.OrAccept<R, S> c) throws RestException {
        Object r;
        Throwable x;
        if (a == null || b == null ||
                ((r = a.result) == null && (r = b.result) == null) || f == null)
            return false;
        tryComplete:
        if (result == null) {
            try {
                if (c != null && !c.claim())
                    return false;
                if (r instanceof RestCompletableFuture.AltResult) {
                    if ((x = ((RestCompletableFuture.AltResult) r).ex) != null) {
                        if (x instanceof RestError) {
                            throw new RestException(x.getCause());
                        } else if (x instanceof RestException) {
                            throw (RestException) x;
                        }
                        completeThrowable(x, r);
                        break tryComplete;
                    }
                    r = null;
                }
                @SuppressWarnings("unchecked") R rr = (R) r;
                if (f instanceof ConsumerActuator) {
                    ((ConsumerActuator<? super R>) f).actuate(rr);
                } else {
                    f.accept(rr);
                }
                completeNull();
            } catch (Throwable ex) {
                if (ex instanceof RestError) {
                    throw new RestException(ex.getCause());
                } else if (ex instanceof RestException) {
                    throw (RestException) ex;
                }
                completeThrowable(ex);
            }
        }
        return true;
    }

    /**
     * <code>orAcceptStage</code>
     * <p>The or accept stage method.</p>
     * @param <U> T <p>The generic parameter is <code>T</code> type.</p>
     * @param e   {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @param o   {@link java.util.concurrent.CompletionStage} <p>The o parameter is <code>CompletionStage</code> type.</p>
     * @param f   {@link java.util.function.Consumer} <p>The f parameter is <code>Consumer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The or accept stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see T
     * @see java.util.concurrent.Executor
     * @see java.util.concurrent.CompletionStage
     * @see java.util.function.Consumer
     * @see io.github.nichetoolkit.rest.RestException
     */
    private <U extends T> RestCompletableFuture<Void> orAcceptStage(
            Executor e, CompletionStage<U> o, Consumer<? super T> f) throws RestException {
        RestCompletableFuture<U> b;
        if (o instanceof RestCompletionStage) {
            b = ((RestCompletionStage<U>) o).ofCompletableFuture();
        } else {
            b = of(o.toCompletableFuture());
        }
        if (f == null || b == null)
            throw new ThreadPointerEmptyException();
        RestCompletableFuture<Void> d = new RestCompletableFuture<>();
        if (e != null || !d.orAccept(this, b, f, null)) {
            RestCompletableFuture.OrAccept<T, U> c = new RestCompletableFuture.OrAccept<>(e, d, this, b, f);
            orpush(b, c);
            c.tryFire(SYNC);
        }
        return d;
    }

    /**
     * <code>OrRun</code>
     * <p>The or run class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion
     * @since Jdk1.8
     */
    static final class OrRun<T, U> extends RestCompletableFuture.BiCompletion<T, U, Void> {
        /**
         * <code>function</code>
         * {@link java.lang.Runnable} <p>The <code>function</code> field.</p>
         * @see java.lang.Runnable
         */
        Runnable function;

        /**
         * <code>OrRun</code>
         * <p>Instantiates a new or run.</p>
         * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param snd      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The snd parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link java.lang.Runnable} <p>The function parameter is <code>Runnable</code> type.</p>
         * @see java.util.concurrent.Executor
         * @see java.lang.Runnable
         */
        OrRun(Executor executor, RestCompletableFuture<Void> dep,
              RestCompletableFuture<T> src,
              RestCompletableFuture<U> snd,
              Runnable function) {
            super(executor, dep, src, snd);
            this.function = function;
        }

        RestCompletableFuture<Void> tryFire(int mode) throws RestException {
            RestCompletableFuture<Void> d;
            RestCompletableFuture<T> a;
            RestCompletableFuture<U> b;
            if ((d = dep) == null ||
                    !d.orRun(a = src, b = snd, function, mode > 0 ? null : this))
                return null;
            dep = null;
            src = null;
            snd = null;
            function = null;
            return d.postFire(a, b, mode);
        }
    }

    /**
     * <code>orRun</code>
     * <p>The or run method.</p>
     * @param a {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param b {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The b parameter is <code>RestCompletableFuture</code> type.</p>
     * @param f {@link java.lang.Runnable} <p>The f parameter is <code>Runnable</code> type.</p>
     * @param c {@link io.github.nichetoolkit.rest.future.RestCompletableFuture.OrRun} <p>The c parameter is <code>OrRun</code> type.</p>
     * @return boolean <p>The or run return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.OrRun
     * @see io.github.nichetoolkit.rest.RestException
     */
    final boolean orRun(RestCompletableFuture<?> a, RestCompletableFuture<?> b,
                        Runnable f, RestCompletableFuture.OrRun<?, ?> c) throws RestException {
        Object r;
        Throwable x;
        if (a == null || b == null ||
                ((r = a.result) == null && (r = b.result) == null) || f == null)
            return false;
        if (result == null) {
            try {
                if (c != null && !c.claim())
                    return false;
                if (r instanceof RestCompletableFuture.AltResult && (x = ((RestCompletableFuture.AltResult) r).ex) != null)
                    if (x instanceof RestError) {
                        throw new RestException(x.getCause());
                    } else if (x instanceof RestException) {
                        throw (RestException) x;
                    } else {
                        completeThrowable(x, r);
                    }
                else {
                    f.run();
                    completeNull();
                }
            } catch (Throwable ex) {
                if (ex instanceof RestError) {
                    throw new RestException(ex.getCause());
                } else if (ex instanceof RestException) {
                    throw (RestException) ex;
                }
                completeThrowable(ex);
            }
        }
        return true;
    }

    /**
     * <code>orRunStage</code>
     * <p>The or run stage method.</p>
     * @param e {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @param o {@link java.util.concurrent.CompletionStage} <p>The o parameter is <code>CompletionStage</code> type.</p>
     * @param f {@link java.lang.Runnable} <p>The f parameter is <code>Runnable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The or run stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.Executor
     * @see java.util.concurrent.CompletionStage
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.RestException
     */
    private RestCompletableFuture<Void> orRunStage(Executor e, CompletionStage<?> o, Runnable f) throws RestException {
        RestCompletableFuture<?> b;
        if (o instanceof RestCompletionStage) {
            b = ((RestCompletionStage<?>) o).ofCompletableFuture();
        } else {
            b = of(o.toCompletableFuture());
        }
        if (f == null || b == null)
            throw new ThreadPointerEmptyException();
        RestCompletableFuture<Void> d = new RestCompletableFuture<>();
        if (e != null || !d.orRun(this, b, f, null)) {
            RestCompletableFuture.OrRun<T, ?> c = new RestCompletableFuture.OrRun<>(e, d, this, b, f);
            orpush(b, c);
            c.tryFire(SYNC);
        }
        return d;
    }

    /**
     * <code>OrRelay</code>
     * <p>The or relay class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.BiCompletion
     * @since Jdk1.8
     */
    static final class OrRelay<T, U> extends RestCompletableFuture.BiCompletion<T, U, Object> { // for Or
        /**
         * <code>OrRelay</code>
         * <p>Instantiates a new or relay.</p>
         * @param dep {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param src {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The src parameter is <code>RestCompletableFuture</code> type.</p>
         * @param snd {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The snd parameter is <code>RestCompletableFuture</code> type.</p>
         */
        OrRelay(RestCompletableFuture<Object> dep, RestCompletableFuture<T> src,
                RestCompletableFuture<U> snd) {
            super(null, dep, src, snd);
        }

        RestCompletableFuture<Object> tryFire(int mode) throws RestException {
            RestCompletableFuture<Object> d;
            RestCompletableFuture<T> a;
            RestCompletableFuture<U> b;
            if ((d = dep) == null || !d.orRelay(a = src, b = snd))
                return null;
            src = null;
            snd = null;
            dep = null;
            return d.postFire(a, b, mode);
        }
    }

    /**
     * <code>orRelay</code>
     * <p>The or relay method.</p>
     * @param a {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @param b {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The b parameter is <code>RestCompletableFuture</code> type.</p>
     * @return boolean <p>The or relay return object is <code>boolean</code> type.</p>
     */
    final boolean orRelay(RestCompletableFuture<?> a, RestCompletableFuture<?> b) {
        Object r;
        if (a == null || b == null ||
                ((r = a.result) == null && (r = b.result) == null))
            return false;
        if (result == null)
            completeRelay(r);
        return true;
    }

    /**
     * <code>orRelay</code>
     * <p>The or relay method.</p>
     * @param a {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @return boolean <p>The or relay return object is <code>boolean</code> type.</p>
     */
    final boolean orRelay(RestCompletableFuture<?> a) {
        Object r;
        if (a == null || (r = a.result) == null)
            return false;
        if (result == null)
            completeRelay(r);
        return true;
    }

    /**
     * <code>orRelay</code>
     * <p>The or relay method.</p>
     * @param a {@link java.util.concurrent.CompletableFuture} <p>The a parameter is <code>CompletableFuture</code> type.</p>
     * @param b {@link java.util.concurrent.CompletableFuture} <p>The b parameter is <code>CompletableFuture</code> type.</p>
     * @return boolean <p>The or relay return object is <code>boolean</code> type.</p>
     * @see java.util.concurrent.CompletableFuture
     */
    final boolean orRelay(CompletableFuture<?> a, CompletableFuture<?> b) {
        Object r;
        if (a == null || b == null ||
                ((r = a.getNow(null)) == null && (r = b.getNow(null)) == null))
            return false;
        if (result == null)
            completeRelay(r);
        return true;
    }

    /**
     * <code>orRelay</code>
     * <p>The or relay method.</p>
     * @param a {@link java.util.concurrent.CompletableFuture} <p>The a parameter is <code>CompletableFuture</code> type.</p>
     * @return boolean <p>The or relay return object is <code>boolean</code> type.</p>
     * @see java.util.concurrent.CompletableFuture
     */
    final boolean orRelay(CompletableFuture<?> a) {
        Object r;
        if (a == null || (r = a.getNow(null)) == null)
            return false;
        if (result == null)
            completeRelay(r);
        return true;
    }

    /**
     * <code>orTree</code>
     * <p>The or tree method.</p>
     * @param cfs {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The cfs parameter is <code>RestCompletableFuture</code> type.</p>
     * @param lo  int <p>The lo parameter is <code>int</code> type.</p>
     * @param hi  int <p>The hi parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The or tree return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    static RestCompletableFuture<Object> orTree(RestCompletableFuture<?>[] cfs, int lo, int hi) throws RestException {
        RestCompletableFuture<Object> d = new RestCompletableFuture<>();
        if (lo <= hi) {
            RestCompletableFuture<?> a, b;
            int mid = (lo + hi) >>> 1;
            if ((a = (lo == mid ? cfs[lo] :
                    orTree(cfs, lo, mid))) == null ||
                    (b = (lo == hi ? a : (hi == mid + 1) ? cfs[hi] :
                            orTree(cfs, mid + 1, hi))) == null)
                throw new ThreadPointerEmptyException();
            if (!d.orRelay(a, b)) {
                RestCompletableFuture.OrRelay<?, ?> c = new RestCompletableFuture.OrRelay<>(d, a, b);
                a.orpush(b, c);
                c.tryFire(SYNC);
            }
        }
        return d;
    }

    /**
     * <code>orThen</code>
     * <p>The or then method.</p>
     * @param a {@link java.util.concurrent.CompletableFuture} <p>The a parameter is <code>CompletableFuture</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The or then return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.CompletableFuture
     * @see io.github.nichetoolkit.rest.RestException
     */
    static RestCompletableFuture<Object> orThen(CompletableFuture<?> a) throws RestException {
        RestCompletableFuture<Object> d = new RestCompletableFuture<>();
        if (a == null)
            throw new ThreadPointerEmptyException();
        if (!d.orRelay(a)) {
            a.thenAcceptAsync(d::completeValue);
        } else {
            d.completeValue(a.join());
        }
        return d;
    }

    /**
     * <code>toThen</code>
     * <p>The to then method.</p>
     * @param a {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The a parameter is <code>RestCompletableFuture</code> type.</p>
     * @return {@link java.util.concurrent.CompletableFuture} <p>The to then return object is <code>CompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.CompletableFuture
     * @see io.github.nichetoolkit.rest.RestException
     */
    static CompletableFuture<Object> toThen(RestCompletableFuture<?> a) throws RestException {
        RestCompletableFuture<Object> b = new RestCompletableFuture<>();
        CompletableFuture<Object> d = new CompletableFuture<>();
        if (a == null)
            throw new ThreadPointerEmptyException();
        if (!b.orRelay(a)) {
            a.thenAcceptAsync(d::complete);
        } else {
            d.complete(a.join());
        }
        return d;
    }


    /* ------------- Zero-input Async forms -------------- */

    /**
     * <code>AsyncSupply</code>
     * <p>The async supply class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see java.util.concurrent.ForkJoinTask
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.AsynchronousCompletionTask
     * @since Jdk1.8
     */
    static final class AsyncSupply<T> extends ForkJoinTask<Void>
            implements Runnable, RestCompletableFuture.AsynchronousCompletionTask {
        /**
         * <code>dep</code>
         * <p>The dep field.</p>
         */
        RestCompletableFuture<T> dep;
        /**
         * <code>function</code>
         * <p>The function field.</p>
         * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
         */
        SupplierActuator<T> function;

        /**
         * <code>AsyncSupply</code>
         * <p>Instantiates a new async supply.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The function parameter is <code>SupplierActuator</code> type.</p>
         * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
         */
        AsyncSupply(RestCompletableFuture<T> dep, SupplierActuator<T> function) {
            this.dep = dep;
            this.function = function;
        }

        public Void getRawResult() {
            return null;
        }

        public void setRawResult(Void v) {
        }

        public final boolean exec() {
            run();
            return true;
        }

        public void run() {
            RestCompletableFuture<T> d;
            SupplierActuator<T> f;
            if ((d = dep) != null && (f = function) != null) {
                dep = null;
                function = null;
                if (d.result == null) {
                    try {
                        d.completeValue(f.actuate());
                    } catch (Throwable ex) {
                        d.completeThrowable(ex);
                    }
                }
                try {
                    d.postComplete();
                } catch (RestException e) {
                    d.completeThrowable(e);
                }
            }
        }
    }

    /**
     * <code>asyncSupplyStage</code>
     * <p>The async supply stage method.</p>
     * @param <U> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param e   {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @param f   {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The f parameter is <code>SupplierActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The async supply stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    static <U> RestCompletableFuture<U> asyncSupplyStage(Executor e, SupplierActuator<U> f) throws RestException {
        if (f == null) throw new ThreadPointerEmptyException();
        RestCompletableFuture<U> d = new RestCompletableFuture<>();
        e.execute(new RestCompletableFuture.AsyncSupply<>(d, f));
        return d;
    }

    /**
     * <code>AsyncRun</code>
     * <p>The async run class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see java.util.concurrent.ForkJoinTask
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.AsynchronousCompletionTask
     * @since Jdk1.8
     */
    static final class AsyncRun extends ForkJoinTask<Void>
            implements Runnable, RestCompletableFuture.AsynchronousCompletionTask {
        /**
         * <code>dep</code>
         * <p>The dep field.</p>
         */
        RestCompletableFuture<Void> dep;
        /**
         * <code>function</code>
         * {@link java.lang.Runnable} <p>The <code>function</code> field.</p>
         * @see java.lang.Runnable
         */
        Runnable function;

        /**
         * <code>AsyncRun</code>
         * <p>Instantiates a new async run.</p>
         * @param dep      {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The dep parameter is <code>RestCompletableFuture</code> type.</p>
         * @param function {@link java.lang.Runnable} <p>The function parameter is <code>Runnable</code> type.</p>
         * @see java.lang.Runnable
         */
        AsyncRun(RestCompletableFuture<Void> dep, Runnable function) {
            this.dep = dep;
            this.function = function;
        }

        public Void getRawResult() {
            return null;
        }

        public final void setRawResult(Void v) {
        }

        public final boolean exec() {
            run();
            return true;
        }

        public void run() {
            RestCompletableFuture<Void> d;
            Runnable f;
            if ((d = dep) != null && (f = function) != null) {
                dep = null;
                function = null;
                if (d.result == null) {
                    try {
                        f.run();
                        d.completeNull();
                    } catch (Throwable ex) {
                        d.completeThrowable(ex);
                    }
                }
                try {
                    d.postComplete();
                } catch (RestException e) {
                    d.completeThrowable(e);
                }
            }
        }
    }

    /**
     * <code>asyncRunStage</code>
     * <p>The async run stage method.</p>
     * @param e {@link java.util.concurrent.Executor} <p>The e parameter is <code>Executor</code> type.</p>
     * @param f {@link java.lang.Runnable} <p>The f parameter is <code>Runnable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The async run stage return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.Executor
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.RestException
     */
    static RestCompletableFuture<Void> asyncRunStage(Executor e, Runnable f) throws RestException {
        if (f == null) throw new ThreadPointerEmptyException();
        RestCompletableFuture<Void> d = new RestCompletableFuture<>();
        e.execute(new RestCompletableFuture.AsyncRun(d, f));
        return d;
    }

    /* ------------- Signallers -------------- */

    /**
     * <code>Signaller</code>
     * <p>The signaller class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture.Completion
     * @see java.util.concurrent.ForkJoinPool.ManagedBlocker
     * @since Jdk1.8
     */
    static final class Signaller extends RestCompletableFuture.Completion
            implements ForkJoinPool.ManagedBlocker {
        /**
         * <code>nanos</code>
         * <p>The <code>nanos</code> field.</p>
         */
        long nanos;                    // wait time if timed
        /**
         * <code>deadline</code>
         * <p>The <code>deadline</code> field.</p>
         */
        final long deadline;           // non-zero if timed
        /**
         * <code>interruptControl</code>
         * <p>The <code>interruptControl</code> field.</p>
         */
        volatile int interruptControl; // > 0: interruptible, < 0: interrupted
        /**
         * <code>thread</code>
         * {@link java.lang.Thread} <p>The <code>thread</code> field.</p>
         * @see java.lang.Thread
         */
        volatile Thread thread;

        /**
         * <code>Signaller</code>
         * <p>Instantiates a new signaller.</p>
         * @param interruptible boolean <p>The interruptible parameter is <code>boolean</code> type.</p>
         * @param nanos         long <p>The nanos parameter is <code>long</code> type.</p>
         * @param deadline      long <p>The deadline parameter is <code>long</code> type.</p>
         */
        Signaller(boolean interruptible, long nanos, long deadline) {
            this.thread = Thread.currentThread();
            this.interruptControl = interruptible ? 1 : 0;
            this.nanos = nanos;
            this.deadline = deadline;
        }

        final RestCompletableFuture<?> tryFire(int ignore) {
            Thread w; // no need to atomically claim
            if ((w = thread) != null) {
                thread = null;
                LockSupport.unpark(w);
            }
            return null;
        }

        public boolean isReleasable() {
            if (thread == null)
                return true;
            if (Thread.interrupted()) {
                int i = interruptControl;
                interruptControl = -1;
                if (i > 0)
                    return true;
            }
            if (deadline != 0L &&
                    (nanos <= 0L || (nanos = deadline - System.nanoTime()) <= 0L)) {
                thread = null;
                return true;
            }
            return false;
        }

        public boolean block() {
            if (isReleasable())
                return true;
            else if (deadline == 0L)
                LockSupport.park(this);
            else if (nanos > 0L)
                LockSupport.parkNanos(this, nanos);
            return isReleasable();
        }

        final boolean isLive() {
            return thread != null;
        }
    }

    /**
     * <code>waitingGet</code>
     * <p>The waiting get method.</p>
     * @param interruptible boolean <p>The interruptible parameter is <code>boolean</code> type.</p>
     * @return {@link java.lang.Object} <p>The waiting get return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    private Object waitingGet(boolean interruptible) throws RestException {
        RestCompletableFuture.Signaller q = null;
        boolean queued = false;
        int spins = -1;
        Object r;
        while ((r = result) == null) {
            if (spins < 0)
                spins = SPINS;
            else if (spins > 0) {
                if (RestThreadLocalRandom.nextSecondarySeed() >= 0)
                    --spins;
            } else if (q == null)
                q = new RestCompletableFuture.Signaller(interruptible, 0L, 0L);
            else if (!queued)
                queued = tryPushStack(q);
            else if (interruptible && q.interruptControl < 0) {
                q.thread = null;
                cleanStack();
                return null;
            } else if (q.thread != null && result == null) {
                try {
                    ForkJoinPool.managedBlock(q);
                } catch (InterruptedException ie) {
                    q.interruptControl = -1;
                }
            }
        }
        if (q != null) {
            q.thread = null;
            if (q.interruptControl < 0) {
                if (interruptible)
                    r = null; // report interruption
                else
                    Thread.currentThread().interrupt();
            }
        }
        postComplete();
        return r;
    }

    /**
     * <code>timedGet</code>
     * <p>The timed get method.</p>
     * @param nanos long <p>The nanos parameter is <code>long</code> type.</p>
     * @return {@link java.lang.Object} <p>The timed get return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    private Object timedGet(long nanos) throws RestException {
        if (Thread.interrupted())
            return null;
        if (nanos <= 0L)
            throw new ThreadTimeoutException();
        long d = System.nanoTime() + nanos;
        RestCompletableFuture.Signaller q = new RestCompletableFuture.Signaller(true, nanos, d == 0L ? 1L : d); // avoid 0
        boolean queued = false;
        Object r;
        // We intentionally don't spin here (as waitingGet does) because
        // the call to nanoTime() above acts much like a spin.
        while ((r = result) == null) {
            if (!queued)
                queued = tryPushStack(q);
            else if (q.interruptControl < 0 || q.nanos <= 0L) {
                q.thread = null;
                cleanStack();
                if (q.interruptControl < 0)
                    return null;
                throw new ThreadTimeoutException();
            } else if (q.thread != null && result == null) {
                try {
                    ForkJoinPool.managedBlock(q);
                } catch (InterruptedException ie) {
                    q.interruptControl = -1;
                }
            }
        }
        if (q.interruptControl < 0)
            r = null;
        q.thread = null;
        postComplete();
        return r;
    }

    /* ------------- public methods -------------- */

    /**
     * <code>RestCompletableFuture</code>
     * <p>Instantiates a new rest completable future.</p>
     */
    public RestCompletableFuture() {
    }

    /**
     * <code>RestCompletableFuture</code>
     * <p>Instantiates a new rest completable future.</p>
     * @param r {@link java.lang.Object} <p>The r parameter is <code>Object</code> type.</p>
     * @see java.lang.Object
     */
    private RestCompletableFuture(Object r) {
        this.result = r;
    }

    /**
     * <code>supplyAsync</code>
     * <p>The supply async method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The supply async return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <U> RestCompletableFuture<U> supplyAsync(SupplierActuator<U> supplier) throws RestException {
        return asyncSupplyStage(asyncPool, supplier);
    }

    /**
     * <code>supplyAsync</code>
     * <p>The supply async method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The supplier parameter is <code>SupplierActuator</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The supply async return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <U> RestCompletableFuture<U> supplyAsync(SupplierActuator<U> supplier, Executor executor) throws RestException {
        return asyncSupplyStage(screenExecutor(executor), supplier);
    }

    /**
     * <code>runAsync</code>
     * <p>The run async method.</p>
     * @param runnable {@link java.lang.Runnable} <p>The runnable parameter is <code>Runnable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The run async return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static RestCompletableFuture<Void> runAsync(Runnable runnable) throws RestException {
        return asyncRunStage(asyncPool, runnable);
    }

    /**
     * <code>runAsync</code>
     * <p>The run async method.</p>
     * @param runnable {@link java.lang.Runnable} <p>The runnable parameter is <code>Runnable</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The run async return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Runnable
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static RestCompletableFuture<Void> runAsync(Runnable runnable, Executor executor) throws RestException {
        return asyncRunStage(screenExecutor(executor), runnable);
    }

    /**
     * <code>completedFuture</code>
     * <p>The completed future method.</p>
     * @param <U>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value U <p>The value parameter is <code>U</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The completed future return object is <code>RestCompletableFuture</code> type.</p>
     */
    public static <U> RestCompletableFuture<U> completedFuture(U value) {
        return new RestCompletableFuture<>((value == null) ? NIL : value);
    }

    public boolean isDone() {
        return result != null;
    }

    @Override
    public T ofGet() throws RestException {
        Object r;
        return reportGet((r = result) == null ? waitingGet(true) : r);
    }

    @Override
    public T ofGet(long timeout, TimeUnit unit) throws RestException {
        Object r;
        long nanos = unit.toNanos(timeout);
        return reportGet((r = result) == null ? timedGet(nanos) : r);
    }

    /**
     * <code>join</code>
     * <p>The join method.</p>
     * @return T <p>The join return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T join() throws RestException {
        Object r;
        return reportJoin((r = result) == null ? waitingGet(false) : r);
    }

    /**
     * <code>getNow</code>
     * <p>The get now getter method.</p>
     * @param valueIfAbsent T <p>The value if absent parameter is <code>T</code> type.</p>
     * @return T <p>The get now return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public T getNow(T valueIfAbsent) throws RestException {
        Object r;
        return ((r = result) == null) ? valueIfAbsent : reportJoin(r);
    }

    /**
     * <code>complete</code>
     * <p>The complete method.</p>
     * @param value T <p>The value parameter is <code>T</code> type.</p>
     * @return boolean <p>The complete return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public boolean complete(T value) throws RestException {
        boolean triggered = completeValue(value);
        postComplete();
        return triggered;
    }

    /**
     * <code>completeExceptionally</code>
     * <p>The complete exceptionally method.</p>
     * @param ex {@link java.lang.Throwable} <p>The ex parameter is <code>Throwable</code> type.</p>
     * @return boolean <p>The complete exceptionally return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.RestException
     */
    public boolean completeExceptionally(Throwable ex) throws RestException {
        if (ex == null) throw new ThreadPointerEmptyException();
        boolean triggered = internalComplete(new RestCompletableFuture.AltResult(ex));
        postComplete();
        return triggered;
    }


    @Override
    @NonNull
    public <U> RestCompletableFuture<U> thenApply(@NonNull Function<? super T, ? extends U> function) {
        try {
            return uniApplyStage(null, function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> ofThenApply(@NonNull FunctionActuator<? super T, ? extends U> function) throws RestException {
        return uniApplyStage(null, function);
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> thenApplyAsync(@NonNull Function<? super T, ? extends U> function) {
        try {
            return uniApplyStage(asyncPool, function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> ofThenApplyAsync(@NonNull FunctionActuator<? super T, ? extends U> function) throws RestException {
        return uniApplyStage(asyncPool, function);
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> thenApplyAsync(@NonNull Function<? super T, ? extends U> function, Executor executor) {
        try {
            return uniApplyStage(screenExecutor(executor), function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> ofThenApplyAsync(@NonNull FunctionActuator<? super T, ? extends U> function, Executor executor) throws RestException {
        return uniApplyStage(screenExecutor(executor), function);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> thenAccept(@NonNull Consumer<? super T> action) {
        try {
            return uniAcceptStage(null, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofThenAccept(@NonNull ConsumerActuator<? super T> action) throws RestException {
        return uniAcceptStage(null, action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> thenAcceptAsync(@NonNull Consumer<? super T> action) {
        try {
            return uniAcceptStage(asyncPool, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofThenAcceptAsync(@NonNull ConsumerActuator<? super T> action) throws RestException {
        return uniAcceptStage(asyncPool, action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> thenAcceptAsync(@NonNull Consumer<? super T> action, Executor executor) {
        try {
            return uniAcceptStage(screenExecutor(executor), action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofThenAcceptAsync(@NonNull ConsumerActuator<? super T> action, Executor executor) throws RestException {
        return uniAcceptStage(screenExecutor(executor), action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> thenRun(@NonNull Runnable action) {
        try {
            return uniRunStage(null, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofThenRun(@NonNull Runnable action) throws RestException {
        return uniRunStage(null, action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> thenRunAsync(@NonNull Runnable action) {
        try {
            return uniRunStage(asyncPool, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofThenRunAsync(@NonNull Runnable action) throws RestException {
        return uniRunStage(asyncPool, action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> thenRunAsync(@NonNull Runnable action, Executor executor) {
        try {
            return uniRunStage(screenExecutor(executor), action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofThenRunAsync(@NonNull Runnable action, Executor executor) throws RestException {
        return uniRunStage(screenExecutor(executor), action);
    }

    @Override
    @NonNull
    public <U, V> RestCompletableFuture<V> thenCombine(@NonNull CompletionStage<? extends U> other, @NonNull BiFunction<? super T, ? super U, ? extends V> function) {
        try {
            return biApplyStage(null, other, function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U, V> RestCompletableFuture<V> ofThenCombine(@NonNull RestCompletionStage<? extends U> other, @NonNull BiFunctionActuator<? super T, ? super U, ? extends V> function) throws RestException {
        return biApplyStage(null, other, function);
    }


    @Override
    @NonNull
    public <U, V> RestCompletableFuture<V> thenCombineAsync(@NonNull CompletionStage<? extends U> other, @NonNull BiFunction<? super T, ? super U, ? extends V> function) {
        try {
            return biApplyStage(asyncPool, other, function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U, V> RestCompletableFuture<V> ofThenCombineAsync(@NonNull RestCompletionStage<? extends U> other, @NonNull BiFunctionActuator<? super T, ? super U, ? extends V> function) throws RestException {
        return biApplyStage(asyncPool, other, function);
    }

    @Override
    @NonNull
    public <U, V> RestCompletableFuture<V> thenCombineAsync(@NonNull CompletionStage<? extends U> other, @NonNull BiFunction<? super T, ? super U, ? extends V> function, Executor executor) {
        try {
            return biApplyStage(screenExecutor(executor), other, function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U, V> RestCompletableFuture<V> ofThenCombineAsync(@NonNull RestCompletionStage<? extends U> other, @NonNull BiFunctionActuator<? super T, ? super U, ? extends V> function, Executor executor) throws RestException {
        return biApplyStage(screenExecutor(executor), other, function);
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<Void> thenAcceptBoth(@NonNull CompletionStage<? extends U> other, @NonNull BiConsumer<? super T, ? super U> action) {
        try {
            return biAcceptStage(null, other, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<Void> ofThenAcceptBoth(@NonNull RestCompletionStage<? extends U> other, @NonNull BiConsumerActuator<? super T, ? super U> action) throws RestException {
        return biAcceptStage(null, other, action);
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<Void> thenAcceptBothAsync(@NonNull CompletionStage<? extends U> other, @NonNull BiConsumer<? super T, ? super U> action) {
        try {
            return biAcceptStage(asyncPool, other, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<Void> ofThenAcceptBothAsync(@NonNull RestCompletionStage<? extends U> other, @NonNull BiConsumerActuator<? super T, ? super U> action) throws RestException {
        return biAcceptStage(asyncPool, other, action);
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<Void> thenAcceptBothAsync(@NonNull CompletionStage<? extends U> other, @NonNull BiConsumer<? super T, ? super U> action, Executor executor) {
        try {
            return biAcceptStage(screenExecutor(executor), other, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<Void> ofThenAcceptBothAsync(@NonNull RestCompletionStage<? extends U> other, @NonNull BiConsumerActuator<? super T, ? super U> action, Executor executor) throws RestException {
        return biAcceptStage(screenExecutor(executor), other, action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> runAfterBoth(@NonNull CompletionStage<?> other, @NonNull Runnable action) {
        try {
            return biRunStage(null, other, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofRunAfterBoth(@NonNull RestCompletionStage<?> other, @NonNull Runnable action) throws RestException {
        return biRunStage(null, other, action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> runAfterBothAsync(@NonNull CompletionStage<?> other, @NonNull Runnable action) {
        try {
            return biRunStage(asyncPool, other, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofRunAfterBothAsync(@NonNull RestCompletionStage<?> other, @NonNull Runnable action) throws RestException {
        return biRunStage(asyncPool, other, action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> runAfterBothAsync(@NonNull CompletionStage<?> other, @NonNull Runnable action, Executor executor) {
        try {
            return biRunStage(screenExecutor(executor), other, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofRunAfterBothAsync(@NonNull RestCompletionStage<?> other, @NonNull Runnable action, Executor executor) throws RestException {
        return biRunStage(screenExecutor(executor), other, action);
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> applyToEither(@NonNull CompletionStage<? extends T> other, @NonNull Function<? super T, U> function) {
        try {
            return orApplyStage(null, other, function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> ofApplyToEither(@NonNull RestCompletionStage<? extends T> other, @NonNull FunctionActuator<? super T, U> function) throws RestException {
        return orApplyStage(null, other, function);
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> applyToEitherAsync(@NonNull CompletionStage<? extends T> other, @NonNull Function<? super T, U> function) {
        try {
            return orApplyStage(asyncPool, other, function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> ofApplyToEitherAsync(@NonNull RestCompletionStage<? extends T> other, @NonNull FunctionActuator<? super T, U> function) throws RestException {
        return orApplyStage(asyncPool, other, function);
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> applyToEitherAsync(@NonNull CompletionStage<? extends T> other, @NonNull Function<? super T, U> function, Executor executor) {
        try {
            return orApplyStage(screenExecutor(executor), other, function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> ofApplyToEitherAsync(@NonNull RestCompletionStage<? extends T> other, @NonNull FunctionActuator<? super T, U> function, Executor executor) throws RestException {
        return orApplyStage(screenExecutor(executor), other, function);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> acceptEither(@NonNull CompletionStage<? extends T> other, @NonNull Consumer<? super T> action) {
        try {
            return orAcceptStage(null, other, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofAcceptEither(@NonNull RestCompletionStage<? extends T> other, @NonNull ConsumerActuator<? super T> action) throws RestException {
        return orAcceptStage(null, other, action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> acceptEitherAsync(@NonNull CompletionStage<? extends T> other, @NonNull Consumer<? super T> action) {
        try {
            return orAcceptStage(asyncPool, other, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofAcceptEitherAsync(@NonNull RestCompletionStage<? extends T> other, @NonNull ConsumerActuator<? super T> action) throws RestException {
        return orAcceptStage(asyncPool, other, action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> acceptEitherAsync(@NonNull CompletionStage<? extends T> other, @NonNull Consumer<? super T> action, Executor executor) {
        try {
            return orAcceptStage(screenExecutor(executor), other, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofAcceptEitherAsync(@NonNull RestCompletionStage<? extends T> other, @NonNull ConsumerActuator<? super T> action, Executor executor) throws RestException {
        return orAcceptStage(screenExecutor(executor), other, action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> runAfterEither(@NonNull CompletionStage<?> other, @NonNull Runnable action) {
        try {
            return orRunStage(null, other, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofRunAfterEither(@NonNull RestCompletionStage<?> other, @NonNull Runnable action) throws RestException {
        return orRunStage(null, other, action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> runAfterEitherAsync(@NonNull CompletionStage<?> other, @NonNull Runnable action) {
        try {
            return orRunStage(asyncPool, other, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofRunAfterEitherAsync(@NonNull RestCompletionStage<?> other, @NonNull Runnable action) throws RestException {
        return orRunStage(asyncPool, other, action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> runAfterEitherAsync(@NonNull CompletionStage<?> other, @NonNull Runnable action, Executor executor) {
        try {
            return orRunStage(screenExecutor(executor), other, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<Void> ofRunAfterEitherAsync(@NonNull RestCompletionStage<?> other, @NonNull Runnable action, Executor executor) throws RestException {
        return orRunStage(screenExecutor(executor), other, action);
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> thenCompose(@NonNull Function<? super T, ? extends CompletionStage<U>> function) {
        try {
            return uniComposeStage(null, function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> ofThenCompose(@NonNull FunctionActuator<? super T, ? extends RestCompletionStage<U>> function) throws RestException {
        return uniComposeStage(null, function);
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> thenComposeAsync(@NonNull Function<? super T, ? extends CompletionStage<U>> function) {
        try {
            return uniComposeStage(asyncPool, function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> ofThenComposeAsync(@NonNull FunctionActuator<? super T, ? extends RestCompletionStage<U>> function) throws RestException {
        return uniComposeStage(asyncPool, function);
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> thenComposeAsync(@NonNull Function<? super T, ? extends CompletionStage<U>> function, Executor executor) {
        try {
            return uniComposeStage(screenExecutor(executor), function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> ofThenComposeAsync(@NonNull FunctionActuator<? super T, ? extends RestCompletionStage<U>> function, Executor executor) throws RestException {
        return uniComposeStage(screenExecutor(executor), function);
    }

    @Override
    @NonNull
    public RestCompletableFuture<T> whenComplete(@NonNull BiConsumer<? super T, ? super Throwable> action) {
        try {
            return uniWhenCompleteStage(null, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<T> ofWhenComplete(@NonNull BiConsumerActuator<? super T, ? super Throwable> action) throws RestException {
        return uniWhenCompleteStage(null, action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<T> whenCompleteAsync(@NonNull BiConsumer<? super T, ? super Throwable> action) {
        try {
            return uniWhenCompleteStage(asyncPool, action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<T> ofWhenCompleteAsync(@NonNull BiConsumerActuator<? super T, ? super Throwable> action) throws RestException {
        return uniWhenCompleteStage(asyncPool, action);
    }

    @Override
    @NonNull
    public RestCompletableFuture<T> whenCompleteAsync(@NonNull BiConsumer<? super T, ? super Throwable> action, Executor executor) {
        try {
            return uniWhenCompleteStage(screenExecutor(executor), action);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<T> ofWhenCompleteAsync(@NonNull BiConsumerActuator<? super T, ? super Throwable> action, Executor executor) throws RestException {
        return uniWhenCompleteStage(screenExecutor(executor), action);
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> handle(@NonNull BiFunction<? super T, Throwable, ? extends U> function) {
        try {
            return uniHandleStage(null, function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> ofHandle(@NonNull BiFunctionActuator<? super T, Throwable, ? extends U> function) throws RestException {
        return uniHandleStage(null, function);
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> handleAsync(@NonNull BiFunction<? super T, Throwable, ? extends U> function) {
        try {
            return uniHandleStage(asyncPool, function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> ofHandleAsync(@NonNull BiFunctionActuator<? super T, Throwable, ? extends U> function) throws RestException {
        return uniHandleStage(asyncPool, function);
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> handleAsync(@NonNull BiFunction<? super T, Throwable, ? extends U> function, Executor executor) {
        try {
            return uniHandleStage(screenExecutor(executor), function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public <U> RestCompletableFuture<U> ofHandleAsync(@NonNull BiFunctionActuator<? super T, Throwable, ? extends U> function, Executor executor) throws RestException {
        return uniHandleStage(screenExecutor(executor), function);
    }

    @Override
    @NonNull
    public CompletableFuture<T> toCompletableFuture() {
        try {
            return to(this);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<T> ofCompletableFuture() {
        return this;
    }

    // not in interface CompletionStage

    @Override
    @NonNull
    public RestCompletableFuture<T> exceptionally(@NonNull Function<Throwable, ? extends T> function) {
        try {
            return uniExceptionallyStage(function);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    @NonNull
    public RestCompletableFuture<T> ofExceptionally(@NonNull FunctionActuator<Throwable, ? extends T> function) throws RestException {
        return uniExceptionallyStage(function);
    }

    /* ------------- Arbitrary-arity constructions -------------- */

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param cf  {@link java.util.concurrent.CompletableFuture} <p>The cf parameter is <code>CompletableFuture</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The of return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.CompletableFuture
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("unchecked")
    public static <T> RestCompletableFuture<T> of(CompletableFuture<T> cf) throws RestException {
        return (RestCompletableFuture<T>) orThen(CompletableFuture.allOf(cf));
    }

    /**
     * <code>to</code>
     * <p>The to method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param cf  {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The cf parameter is <code>RestCompletableFuture</code> type.</p>
     * @return {@link java.util.concurrent.CompletableFuture} <p>The to return object is <code>CompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.CompletableFuture
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("unchecked")
    public static <T> CompletableFuture<T> to(RestCompletableFuture<T> cf) throws RestException {
        return (CompletableFuture<T>) toThen(RestCompletableFuture.allOf(cf));
    }

    /**
     * <code>allOf</code>
     * <p>The all of method.</p>
     * @param cfs {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The cfs parameter is <code>RestCompletableFuture</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The all of return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static RestCompletableFuture<Void> allOf(RestCompletableFuture<?>... cfs) throws RestException {
        return andTree(cfs, 0, cfs.length - 1);
    }

    /**
     * <code>allOf</code>
     * <p>The all of method.</p>
     * @param cfs {@link java.util.concurrent.CompletableFuture} <p>The cfs parameter is <code>CompletableFuture</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The all of return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.CompletableFuture
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static RestCompletableFuture<Void> allOf(CompletableFuture<?>... cfs) throws RestException {
        return andThen(CompletableFuture.allOf(cfs));
    }

    /**
     * <code>anyOf</code>
     * <p>The any of method.</p>
     * @param cfs {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The cfs parameter is <code>RestCompletableFuture</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The any of return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static RestCompletableFuture<Object> anyOf(RestCompletableFuture<?>... cfs) throws RestException {
        return orTree(cfs, 0, cfs.length - 1);
    }

    /**
     * <code>anyOf</code>
     * <p>The any of method.</p>
     * @param cfs {@link java.util.concurrent.CompletableFuture} <p>The cfs parameter is <code>CompletableFuture</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The any of return object is <code>RestCompletableFuture</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.CompletableFuture
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static RestCompletableFuture<Object> anyOf(CompletableFuture<?>... cfs) throws RestException {
        return orThen(CompletableFuture.anyOf(cfs));
    }

    /* ------------- Control and status methods -------------- */

    @Override
    public boolean ofCancel(boolean mayInterruptIfRunning) throws RestException {
        boolean cancelled = (result == null) &&
                internalComplete(new RestCompletableFuture.AltResult(new CancellationException()));
        postComplete();
        return cancelled || isCancelled();
    }

    @Override
    public boolean isCancelled() {
        Object r;
        return ((r = result) instanceof RestCompletableFuture.AltResult) &&
                (((RestCompletableFuture.AltResult) r).ex instanceof CancellationException);
    }

    /**
     * <code>isCompletedExceptionally</code>
     * <p>The is completed exceptionally method.</p>
     * @return boolean <p>The is completed exceptionally return object is <code>boolean</code> type.</p>
     */
    public boolean isCompletedExceptionally() {
        Object r;
        return ((r = result) instanceof RestCompletableFuture.AltResult) && r != NIL;
    }

    /**
     * <code>obtrudeValue</code>
     * <p>The obtrude value method.</p>
     * @param value T <p>The value parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void obtrudeValue(T value) throws RestException {
        result = (value == null) ? NIL : value;
        postComplete();
    }

    /**
     * <code>obtrudeException</code>
     * <p>The obtrude exception method.</p>
     * @param ex {@link java.lang.Throwable} <p>The ex parameter is <code>Throwable</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Throwable
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void obtrudeException(Throwable ex) throws RestException {
        if (ex == null) throw new ThreadPointerEmptyException();
        result = new RestCompletableFuture.AltResult(ex);
        postComplete();
    }

    /**
     * <code>getNumberOfDependents</code>
     * <p>The get number of dependents getter method.</p>
     * @return int <p>The get number of dependents return object is <code>int</code> type.</p>
     */
    public int getNumberOfDependents() {
        int count = 0;
        for (RestCompletableFuture.Completion p = stack; p != null; p = p.next)
            ++count;
        return count;
    }

    public String toString() {
        Object r = result;
        int count;
        return super.toString() +
                ((r == null) ?
                        (((count = getNumberOfDependents()) == 0) ?
                                "[Not completed]" :
                                "[Not completed, " + count + " dependents]") :
                        (((r instanceof RestCompletableFuture.AltResult) && ((RestCompletableFuture.AltResult) r).ex != null) ?
                                "[Completed exceptionally]" :
                                "[Completed normally]"));
    }

    /**
     * <code>UNSAFE</code>
     * {@link sun.misc.Unsafe} <p>The constant <code>UNSAFE</code> field.</p>
     * @see sun.misc.Unsafe
     */
// Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;
    /**
     * <code>RESULT</code>
     * <p>The constant <code>RESULT</code> field.</p>
     */
    private static final long RESULT;
    /**
     * <code>STACK</code>
     * <p>The constant <code>STACK</code> field.</p>
     */
    private static final long STACK;
    /**
     * <code>NEXT</code>
     * <p>The constant <code>NEXT</code> field.</p>
     */
    private static final long NEXT;

    static {
        try {
            final sun.misc.Unsafe u;
            UNSAFE = u = sun.misc.Unsafe.getUnsafe();
            Class<?> k = CompletableFuture.class;
            RESULT = u.objectFieldOffset(k.getDeclaredField("result"));
            STACK = u.objectFieldOffset(k.getDeclaredField("stack"));
            NEXT = u.objectFieldOffset
                    (RestCompletableFuture.Completion.class.getDeclaredField("next"));
        } catch (Exception x) {
            throw new Error(x);
        }
    }

}
