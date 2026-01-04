package io.github.nichetoolkit.rest.future;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.BiConsumerActuator;
import io.github.nichetoolkit.rest.actuator.BiFunctionActuator;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;
import org.springframework.lang.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <code>RestCompletionStage</code>
 * <p>The rest completion stage interface.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.concurrent.CompletionStage
 * @since Jdk1.8
 */
public interface RestCompletionStage<T> extends CompletionStage<T> {

    @Override
    @NonNull
    <U> RestCompletionStage<U> thenApply(@NonNull Function<? super T, ? extends U> function);

    /**
     * <code>ofThenApply</code>
     * <p>The of then apply method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then apply return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<U> ofThenApply(@NonNull FunctionActuator<? super T, ? extends U> function) throws RestException;

    @Override
    @NonNull
    <U> RestCompletionStage<U> thenApplyAsync(@NonNull Function<? super T, ? extends U> function);

    /**
     * <code>ofThenApplyAsync</code>
     * <p>The of then apply async method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then apply async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<U> ofThenApplyAsync(@NonNull FunctionActuator<? super T, ? extends U> function) throws RestException;

    @Override
    @NonNull
    <U> RestCompletionStage<U> thenApplyAsync(@NonNull Function<? super T, ? extends U> function, Executor executor);

    /**
     * <code>ofThenApplyAsync</code>
     * <p>The of then apply async method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then apply async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.springframework.lang.NonNull
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<U> ofThenApplyAsync(@NonNull FunctionActuator<? super T, ? extends U> function, Executor executor) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> thenAccept(@NonNull Consumer<? super T> action);

    /**
     * <code>ofThenAccept</code>
     * <p>The of then accept method.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The action parameter is <code>ConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then accept return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofThenAccept(@NonNull ConsumerActuator<? super T> action) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> thenAcceptAsync(@NonNull Consumer<? super T> action);

    /**
     * <code>ofThenAcceptAsync</code>
     * <p>The of then accept async method.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The action parameter is <code>ConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then accept async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofThenAcceptAsync(@NonNull ConsumerActuator<? super T> action) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> thenAcceptAsync(@NonNull Consumer<? super T> action, Executor executor);

    /**
     * <code>ofThenAcceptAsync</code>
     * <p>The of then accept async method.</p>
     * @param action   {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The action parameter is <code>ConsumerActuator</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then accept async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see org.springframework.lang.NonNull
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofThenAcceptAsync(@NonNull ConsumerActuator<? super T> action, Executor executor) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> thenRun(@NonNull Runnable action);

    /**
     * <code>ofThenRun</code>
     * <p>The of then run method.</p>
     * @param action {@link java.lang.Runnable} <p>The action parameter is <code>Runnable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then run return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Runnable
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofThenRun(@NonNull Runnable action) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> thenRunAsync(@NonNull Runnable action);

    /**
     * <code>ofThenRunAsync</code>
     * <p>The of then run async method.</p>
     * @param action {@link java.lang.Runnable} <p>The action parameter is <code>Runnable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then run async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Runnable
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofThenRunAsync(@NonNull Runnable action) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> thenRunAsync(@NonNull Runnable action, Executor executor);

    /**
     * <code>ofThenRunAsync</code>
     * <p>The of then run async method.</p>
     * @param action   {@link java.lang.Runnable} <p>The action parameter is <code>Runnable</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then run async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Runnable
     * @see org.springframework.lang.NonNull
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofThenRunAsync(@NonNull Runnable action, Executor executor) throws RestException;

    @Override
    @NonNull
    <U, V> RestCompletionStage<V> thenCombine(@NonNull CompletionStage<? extends U> other, @NonNull BiFunction<? super T, ? super U, ? extends V> function);

    /**
     * <code>ofThenCombine</code>
     * <p>The of then combine method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param other    {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The function parameter is <code>BiFunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then combine return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U, V> RestCompletionStage<V> ofThenCombine(@NonNull RestCompletionStage<? extends U> other, @NonNull BiFunctionActuator<? super T, ? super U, ? extends V> function) throws RestException;

    @Override
    @NonNull
    <U, V> RestCompletionStage<V> thenCombineAsync(@NonNull CompletionStage<? extends U> other, @NonNull BiFunction<? super T, ? super U, ? extends V> function);

    /**
     * <code>ofThenCombineAsync</code>
     * <p>The of then combine async method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param other    {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The function parameter is <code>BiFunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then combine async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U, V> RestCompletionStage<V> ofThenCombineAsync(@NonNull RestCompletionStage<? extends U> other, @NonNull BiFunctionActuator<? super T, ? super U, ? extends V> function) throws RestException;

    @Override
    @NonNull
    <U, V> RestCompletionStage<V> thenCombineAsync(@NonNull CompletionStage<? extends U> other, @NonNull BiFunction<? super T, ? super U, ? extends V> function, Executor executor);

    /**
     * <code>ofThenCombineAsync</code>
     * <p>The of then combine async method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param other    {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The function parameter is <code>BiFunctionActuator</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then combine async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U, V> RestCompletionStage<V> ofThenCombineAsync(@NonNull RestCompletionStage<? extends U> other, @NonNull BiFunctionActuator<? super T, ? super U, ? extends V> function, Executor executor) throws RestException;

    @Override
    @NonNull
    <U> RestCompletionStage<Void> thenAcceptBoth(@NonNull CompletionStage<? extends U> other, @NonNull BiConsumer<? super T, ? super U> action);

    /**
     * <code>ofThenAcceptBoth</code>
     * <p>The of then accept both method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param other  {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The action parameter is <code>BiConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then accept both return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<Void> ofThenAcceptBoth(@NonNull RestCompletionStage<? extends U> other, @NonNull BiConsumerActuator<? super T, ? super U> action) throws RestException;

    @Override
    @NonNull
    <U> RestCompletionStage<Void> thenAcceptBothAsync(@NonNull CompletionStage<? extends U> other, @NonNull BiConsumer<? super T, ? super U> action);


    /**
     * <code>ofThenAcceptBothAsync</code>
     * <p>The of then accept both async method.</p>
     * @param <U>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param other  {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The action parameter is <code>BiConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then accept both async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<Void> ofThenAcceptBothAsync(@NonNull RestCompletionStage<? extends U> other, @NonNull BiConsumerActuator<? super T, ? super U> action) throws RestException;

    @Override
    @NonNull
    <U> RestCompletionStage<Void> thenAcceptBothAsync(@NonNull CompletionStage<? extends U> other, @NonNull BiConsumer<? super T, ? super U> action, Executor executor);

    /**
     * <code>ofThenAcceptBothAsync</code>
     * <p>The of then accept both async method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param other    {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param action   {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The action parameter is <code>BiConsumerActuator</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then accept both async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<Void> ofThenAcceptBothAsync(@NonNull RestCompletionStage<? extends U> other, @NonNull BiConsumerActuator<? super T, ? super U> action, Executor executor) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> runAfterBoth(@NonNull CompletionStage<?> other, @NonNull Runnable action);

    /**
     * <code>ofRunAfterBoth</code>
     * <p>The of run after both method.</p>
     * @param other  {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param action {@link java.lang.Runnable} <p>The action parameter is <code>Runnable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of run after both return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofRunAfterBoth(@NonNull RestCompletionStage<?> other, @NonNull Runnable action) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> runAfterBothAsync(@NonNull CompletionStage<?> other, @NonNull Runnable action);

    /**
     * <code>ofRunAfterBothAsync</code>
     * <p>The of run after both async method.</p>
     * @param other  {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param action {@link java.lang.Runnable} <p>The action parameter is <code>Runnable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of run after both async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofRunAfterBothAsync(@NonNull RestCompletionStage<?> other, @NonNull Runnable action) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> runAfterBothAsync(@NonNull CompletionStage<?> other, @NonNull Runnable action, Executor executor);

    /**
     * <code>ofRunAfterBothAsync</code>
     * <p>The of run after both async method.</p>
     * @param other    {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param action   {@link java.lang.Runnable} <p>The action parameter is <code>Runnable</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of run after both async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see java.lang.Runnable
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofRunAfterBothAsync(@NonNull RestCompletionStage<?> other, @NonNull Runnable action, Executor executor) throws RestException;

    @Override
    @NonNull
    <U> RestCompletionStage<U> applyToEither(@NonNull CompletionStage<? extends T> other, @NonNull Function<? super T, U> function);

    /**
     * <code>ofApplyToEither</code>
     * <p>The of apply to either method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param other    {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of apply to either return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<U> ofApplyToEither(@NonNull RestCompletionStage<? extends T> other, @NonNull FunctionActuator<? super T, U> function) throws RestException;

    @Override
    @NonNull
    <U> RestCompletionStage<U> applyToEitherAsync(@NonNull CompletionStage<? extends T> other, @NonNull Function<? super T, U> function);

    /**
     * <code>ofApplyToEitherAsync</code>
     * <p>The of apply to either async method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param other    {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of apply to either async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<U> ofApplyToEitherAsync(@NonNull RestCompletionStage<? extends T> other, @NonNull FunctionActuator<? super T, U> function) throws RestException;

    @Override
    @NonNull
    <U> RestCompletionStage<U> applyToEitherAsync(@NonNull CompletionStage<? extends T> other, @NonNull Function<? super T, U> function, Executor executor);

    /**
     * <code>ofApplyToEitherAsync</code>
     * <p>The of apply to either async method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param other    {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of apply to either async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<U> ofApplyToEitherAsync(@NonNull RestCompletionStage<? extends T> other, @NonNull FunctionActuator<? super T, U> function, Executor executor) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> acceptEither(@NonNull CompletionStage<? extends T> other, @NonNull Consumer<? super T> action);

    /**
     * <code>ofAcceptEither</code>
     * <p>The of accept either method.</p>
     * @param other  {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The action parameter is <code>ConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of accept either return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofAcceptEither(@NonNull RestCompletionStage<? extends T> other, @NonNull ConsumerActuator<? super T> action) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> acceptEitherAsync(@NonNull CompletionStage<? extends T> other, @NonNull Consumer<? super T> action);

    /**
     * <code>ofAcceptEitherAsync</code>
     * <p>The of accept either async method.</p>
     * @param other  {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The action parameter is <code>ConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of accept either async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofAcceptEitherAsync(@NonNull RestCompletionStage<? extends T> other, @NonNull ConsumerActuator<? super T> action) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> acceptEitherAsync(@NonNull CompletionStage<? extends T> other, @NonNull Consumer<? super T> action, Executor executor);

    /**
     * <code>ofAcceptEitherAsync</code>
     * <p>The of accept either async method.</p>
     * @param other    {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param action   {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The action parameter is <code>ConsumerActuator</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of accept either async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofAcceptEitherAsync(@NonNull RestCompletionStage<? extends T> other, @NonNull ConsumerActuator<? super T> action, Executor executor) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> runAfterEither(@NonNull CompletionStage<?> other, @NonNull Runnable action);

    /**
     * <code>ofRunAfterEither</code>
     * <p>The of run after either method.</p>
     * @param other  {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param action {@link java.lang.Runnable} <p>The action parameter is <code>Runnable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of run after either return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofRunAfterEither(@NonNull RestCompletionStage<?> other, @NonNull Runnable action) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> runAfterEitherAsync(@NonNull CompletionStage<?> other, @NonNull Runnable action);

    /**
     * <code>ofRunAfterEitherAsync</code>
     * <p>The of run after either async method.</p>
     * @param other  {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param action {@link java.lang.Runnable} <p>The action parameter is <code>Runnable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of run after either async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofRunAfterEitherAsync(@NonNull RestCompletionStage<?> other, @NonNull Runnable action) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<Void> runAfterEitherAsync(@NonNull CompletionStage<?> other, @NonNull Runnable action, Executor executor);

    /**
     * <code>ofRunAfterEitherAsync</code>
     * <p>The of run after either async method.</p>
     * @param other    {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The other parameter is <code>RestCompletionStage</code> type.</p>
     * @param action   {@link java.lang.Runnable} <p>The action parameter is <code>Runnable</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of run after either async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see java.lang.Runnable
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<Void> ofRunAfterEitherAsync(@NonNull RestCompletionStage<?> other, @NonNull Runnable action, Executor executor) throws RestException;

    @Override
    @NonNull
    <U> RestCompletionStage<U> thenCompose(@NonNull Function<? super T, ? extends CompletionStage<U>> function);

    /**
     * <code>ofThenCompose</code>
     * <p>The of then compose method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then compose return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<U> ofThenCompose(@NonNull FunctionActuator<? super T, ? extends RestCompletionStage<U>> function) throws RestException;

    @Override
    @NonNull
    <U> RestCompletionStage<U> thenComposeAsync(@NonNull Function<? super T, ? extends CompletionStage<U>> function);

    /**
     * <code>ofThenComposeAsync</code>
     * <p>The of then compose async method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then compose async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<U> ofThenComposeAsync(@NonNull FunctionActuator<? super T, ? extends RestCompletionStage<U>> function) throws RestException;

    @Override
    @NonNull
    <U> RestCompletionStage<U> thenComposeAsync(@NonNull Function<? super T, ? extends CompletionStage<U>> function, Executor executor);

    /**
     * <code>ofThenComposeAsync</code>
     * <p>The of then compose async method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of then compose async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.springframework.lang.NonNull
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<U> ofThenComposeAsync(@NonNull FunctionActuator<? super T, ? extends RestCompletionStage<U>> function, Executor executor) throws RestException;

    @Override
    @NonNull
    <U> RestCompletionStage<U> handle(@NonNull BiFunction<? super T, Throwable, ? extends U> function);

    /**
     * <code>ofHandle</code>
     * <p>The of handle method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The function parameter is <code>BiFunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of handle return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<U> ofHandle(@NonNull BiFunctionActuator<? super T, Throwable, ? extends U> function) throws RestException;

    @Override
    @NonNull
    <U> RestCompletionStage<U> handleAsync(@NonNull BiFunction<? super T, Throwable, ? extends U> function);

    /**
     * <code>ofHandleAsync</code>
     * <p>The of handle async method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The function parameter is <code>BiFunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of handle async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<U> ofHandleAsync(@NonNull BiFunctionActuator<? super T, Throwable, ? extends U> function) throws RestException;

    @Override
    @NonNull
    <U> RestCompletionStage<U> handleAsync(@NonNull BiFunction<? super T, Throwable, ? extends U> function, Executor executor);

    /**
     * <code>ofHandleAsync</code>
     * <p>The of handle async method.</p>
     * @param <U>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The function parameter is <code>BiFunctionActuator</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of handle async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see org.springframework.lang.NonNull
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <U> RestCompletionStage<U> ofHandleAsync(@NonNull BiFunctionActuator<? super T, Throwable, ? extends U> function, Executor executor) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<T> whenComplete(@NonNull BiConsumer<? super T, ? super Throwable> action);

    /**
     * <code>ofWhenComplete</code>
     * <p>The of when complete method.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The action parameter is <code>BiConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of when complete return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<T> ofWhenComplete(@NonNull BiConsumerActuator<? super T, ? super Throwable> action) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<T> whenCompleteAsync(@NonNull BiConsumer<? super T, ? super Throwable> action);

    /**
     * <code>ofWhenCompleteAsync</code>
     * <p>The of when complete async method.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The action parameter is <code>BiConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of when complete async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<T> ofWhenCompleteAsync(@NonNull BiConsumerActuator<? super T, ? super Throwable> action) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<T> whenCompleteAsync(@NonNull BiConsumer<? super T, ? super Throwable> action, Executor executor);

    /**
     * <code>ofWhenCompleteAsync</code>
     * <p>The of when complete async method.</p>
     * @param action   {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The action parameter is <code>BiConsumerActuator</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of when complete async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see org.springframework.lang.NonNull
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<T> ofWhenCompleteAsync(@NonNull BiConsumerActuator<? super T, ? super Throwable> action, Executor executor) throws RestException;

    @Override
    @NonNull
    RestCompletionStage<T> exceptionally(@NonNull Function<Throwable, ? extends T> function);

    /**
     * <code>ofExceptionally</code>
     * <p>The of exceptionally method.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of exceptionally return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestCompletionStage<T> ofExceptionally(@NonNull FunctionActuator<Throwable, ? extends T> function) throws RestException;

    /**
     * <code>exceptionallyAsync</code>
     * <p>The exceptionally async method.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The exceptionally async return object is <code>RestCompletionStage</code> type.</p>
     * @see java.util.function.Function
     */
    default RestCompletionStage<T> exceptionallyAsync(Function<Throwable, ? extends T> function) {
        return handle((r, ex) -> (ex == null)
                ? this
                : this.<T>handleAsync((r1, ex1) -> function.apply(ex1)))
                .thenCompose(Function.identity());
    }

    /**
     * <code>ofExceptionallyAsync</code>
     * <p>The of exceptionally async method.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of exceptionally async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    default RestCompletionStage<T> ofExceptionallyAsync(FunctionActuator<Throwable, ? extends T> function) throws RestException {
        return ofHandle((r, ex) -> (ex == null)
                ? this
                : this.<T>ofHandleAsync((r1, ex1) -> function.actuate(ex1)))
                .ofThenCompose(FunctionActuator.identity());
    }

    /**
     * <code>exceptionallyAsync</code>
     * <p>The exceptionally async method.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The exceptionally async return object is <code>RestCompletionStage</code> type.</p>
     * @see java.util.function.Function
     * @see java.util.concurrent.Executor
     */
    default RestCompletionStage<T> exceptionallyAsync(Function<Throwable, ? extends T> function, Executor executor) {
        return handle((r, ex) -> (ex == null)
                ? this
                : this.<T>handleAsync((r1, ex1) -> function.apply(ex1), executor))
                .thenCompose(Function.identity());
    }

    /**
     * <code>ofExceptionallyAsync</code>
     * <p>The of exceptionally async method.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of exceptionally async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    default RestCompletionStage<T> ofExceptionallyAsync(FunctionActuator<Throwable, ? extends T> function, Executor executor) throws RestException {
        return ofHandle((r, ex) -> (ex == null)
                ? this
                : this.<T>ofHandleAsync((r1, ex1) -> function.actuate(ex1), executor))
                .ofThenCompose(FunctionActuator.identity());
    }

    /**
     * <code>exceptionallyCompose</code>
     * <p>The exceptionally compose method.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The exceptionally compose return object is <code>RestCompletionStage</code> type.</p>
     * @see java.util.function.Function
     */
    default RestCompletionStage<T> exceptionallyCompose(Function<Throwable, ? extends CompletionStage<T>> function) {
        return handle((r, ex) -> (ex == null)
                ? this
                : function.apply(ex))
                .thenCompose(Function.identity());
    }

    /**
     * <code>ofExceptionallyCompose</code>
     * <p>The of exceptionally compose method.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of exceptionally compose return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    default RestCompletionStage<T> ofExceptionallyCompose(FunctionActuator<Throwable, ? extends RestCompletionStage<T>> function) throws RestException {
        return ofHandle((r, ex) -> (ex == null)
                ? this
                : function.actuate(ex))
                .ofThenCompose(FunctionActuator.identity());
    }

    /**
     * <code>exceptionallyComposeAsync</code>
     * <p>The exceptionally compose async method.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The exceptionally compose async return object is <code>RestCompletionStage</code> type.</p>
     * @see java.util.function.Function
     */
    default RestCompletionStage<T> exceptionallyComposeAsync(Function<Throwable, ? extends CompletionStage<T>> function) {
        return handle((r, ex) -> (ex == null)
                ? this
                : this.handleAsync((r1, ex1) -> function.apply(ex1))
                .thenCompose(Function.identity()))
                .thenCompose(Function.identity());
    }

    /**
     * <code>ofExceptionallyComposeAsync</code>
     * <p>The of exceptionally compose async method.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of exceptionally compose async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    default RestCompletionStage<T> ofExceptionallyComposeAsync(FunctionActuator<Throwable, ? extends RestCompletionStage<T>> function) throws RestException {
        return ofHandle((r, ex) -> (ex == null)
                ? this
                : this.ofHandleAsync((r1, ex1) -> function.actuate(ex1))
                .ofThenCompose(FunctionActuator.identity()))
                .ofThenCompose(FunctionActuator.identity());
    }

    /**
     * <code>exceptionallyComposeAsync</code>
     * <p>The exceptionally compose async method.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The exceptionally compose async return object is <code>RestCompletionStage</code> type.</p>
     * @see java.util.function.Function
     * @see java.util.concurrent.Executor
     */
    default RestCompletionStage<T> exceptionallyComposeAsync(Function<Throwable, ? extends CompletionStage<T>> function, Executor executor) {
        return handle((r, ex) -> (ex == null)
                ? this
                : this.handleAsync((r1, ex1) -> function.apply(ex1), executor)
                .thenCompose(Function.identity()))
                .thenCompose(Function.identity());
    }

    /**
     * <code>ofExceptionallyComposeAsync</code>
     * <p>The of exceptionally compose async method.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The function parameter is <code>FunctionActuator</code> type.</p>
     * @param executor {@link java.util.concurrent.Executor} <p>The executor parameter is <code>Executor</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletionStage} <p>The of exceptionally compose async return object is <code>RestCompletionStage</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.concurrent.Executor
     * @see io.github.nichetoolkit.rest.RestException
     */
    default RestCompletionStage<T> ofExceptionallyComposeAsync(FunctionActuator<Throwable, ? extends RestCompletionStage<T>> function, Executor executor) throws RestException {
        return ofHandle((r, ex) -> (ex == null)
                ? this
                : this.ofHandleAsync((r1, ex1) -> function.actuate(ex1), executor)
                .ofThenCompose(FunctionActuator.identity()))
                .ofThenCompose(FunctionActuator.identity());
    }

    @NonNull
    CompletableFuture<T> toCompletableFuture();

    /**
     * <code>ofCompletableFuture</code>
     * <p>The of completable future method.</p>
     * @return {@link io.github.nichetoolkit.rest.future.RestCompletableFuture} <p>The of completable future return object is <code>RestCompletableFuture</code> type.</p>
     * @see io.github.nichetoolkit.rest.future.RestCompletableFuture
     * @see org.springframework.lang.NonNull
     */
    @NonNull
    RestCompletableFuture<T> ofCompletableFuture();

}
