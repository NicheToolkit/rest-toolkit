package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import org.springframework.lang.NonNull;

import java.util.Iterator;

/**
 * <code>DefaultBaseStream</code>
 * <p>The default base stream interface.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <S> {@link io.github.nichetoolkit.rest.stream.DefaultBaseStream} <p>The generic parameter is <code>DefaultBaseStream</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.AutoCloseable
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("resource")
interface DefaultBaseStream<T, S extends DefaultBaseStream<T, S>> extends AutoCloseable {

    /**
     * <code>iterator</code>
     * <p>The iterator method.</p>
     * @return {@link java.util.Iterator} <p>The iterator return object is <code>Iterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Iterator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    Iterator<T> iterator() throws RestException;

    /**
     * <code>spliterator</code>
     * <p>The spliterator method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator return object is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    DefaultSpliterator<T> spliterator() throws RestException;

    /**
     * <code>isParallel</code>
     * <p>The is parallel method.</p>
     * @return boolean <p>The is parallel return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    boolean isParallel() throws RestException;

    /**
     * <code>sequential</code>
     * <p>The sequential method.</p>
     * @return S <p>The sequential return object is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    S sequential() throws RestException;

    /**
     * <code>parallel</code>
     * <p>The parallel method.</p>
     * @return S <p>The parallel return object is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    S parallel() throws RestException;

    /**
     * <code>unordered</code>
     * <p>The unordered method.</p>
     * @return S <p>The unordered return object is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    S unordered() throws RestException;

    /**
     * <code>onClose</code>
     * <p>The on close method.</p>
     * @param closeHandler {@link java.lang.Runnable} <p>The close handler parameter is <code>Runnable</code> type.</p>
     * @return S <p>The on close return object is <code>S</code> type.</p>
     * @see java.lang.Runnable
     * @see org.springframework.lang.NonNull
     */
    @NonNull
    S onClose(Runnable closeHandler);



    void close();

}
