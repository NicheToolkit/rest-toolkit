package io.github.nichetoolkit.rest.holder;

import io.github.nichetoolkit.rest.RestFitter;

/**
 * <code>RestUnfulfilledFitter</code>
 * <p>The rest unfulfilled fitter interface.</p>
 * @param <F> {@link io.github.nichetoolkit.rest.holder.RestUnfulfilledFitter} <p>The generic parameter is <code>RestUnfulfilledFitter</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestFitter
 * @since Jdk1.8
 */
public interface RestUnfulfilledFitter<F extends RestUnfulfilledFitter<F>> extends RestFitter<F> {
}
