package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Setter;

import java.util.Objects;

/**
 * <code>RestState</code>
 * <p>The rest state interface.</p>
 * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestKey
 * @since Jdk17
 */
public interface RestState<S> extends RestKey<S> {

    /**
     * <code>getName</code>
     * <p>The get name getter method.</p>
     * @return {@link java.lang.String} <p>The get name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    default String getName() {
        return GeneralUtils.camelCase(this.getClass().getSimpleName());
    }

    /**
     * <code>getState</code>
     * <p>The get state getter method.</p>
     * @return S <p>The get state return object is <code>S</code> type.</p>
     */
    default S getState() {
        return this.getKey();
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <S>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param name    {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param restKey {@link io.github.nichetoolkit.rest.RestKey} <p>The rest key parameter is <code>RestKey</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestState} <p>The of return object is <code>RestState</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestKey
     */
    static <S> RestState<S> of(String name, RestKey<S> restKey) {
        return new OfRestState<>(name, restKey);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <S>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param value S <p>The value parameter is <code>S</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestState} <p>The of return object is <code>RestState</code> type.</p>
     * @see java.lang.String
     */
    static <S> RestState<S> of(String name, S value) {
        return new OfRestState<>(name, value);
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestState} <p>The of null return object is <code>RestState</code> type.</p>
     */
    static <S>  RestState<S> ofNull() {
        return new OfRestState<>();
    }

    /**
     * <code>OfRestState</code>
     * <p>The of rest state class.</p>
     * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.RestKey.OfRestKey
     * @see lombok.Setter
     * @since Jdk17
     */
    @Setter
    class OfRestState<S> extends OfRestKey<S> implements RestState<S> {
        /**
         * <code>name</code>
         * {@link java.lang.String} <p>The <code>name</code> field.</p>
         * @see java.lang.String
         */
        private String name;

        /**
         * <code>OfRestState</code>
         * <p>Instantiates a new of rest state.</p>
         */
        public OfRestState() {
        }

        /**
         * <code>OfRestState</code>
         * <p>Instantiates a new of rest state.</p>
         * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
         * @param state S <p>The state parameter is <code>S</code> type.</p>
         * @see java.lang.String
         */
        public OfRestState(String name, S state) {
            super(state);
            this.name = name;
        }

        /**
         * <code>OfRestState</code>
         * <p>Instantiates a new of rest state.</p>
         * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
         * @param state {@link io.github.nichetoolkit.rest.RestKey} <p>The state parameter is <code>RestKey</code> type.</p>
         * @see java.lang.String
         * @see io.github.nichetoolkit.rest.RestKey
         */
        public OfRestState(String name, RestKey<S> state) {
            super(state.getKey());
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            OfRestState<?> that = (OfRestState<?>) o;
            return Objects.equals(getName(), that.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), getName());
        }
    }

}
