package io.github.nichetoolkit.rest.fitter;

import io.github.nichetoolkit.rest.RestFitter;

/**
 * <code>RestFulfilledFitter</code>
 * <p>The rest fulfilled fitter interface.</p>
 * @param <F> {@link io.github.nichetoolkit.rest.fitter.RestFulfilledFitter} <p>The generic parameter is <code>RestFulfilledFitter</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestFitter
 * @since Jdk1.8
 */
public interface RestFulfilledFitter<F extends RestFulfilledFitter<F>> extends RestFitter<F> {
}
