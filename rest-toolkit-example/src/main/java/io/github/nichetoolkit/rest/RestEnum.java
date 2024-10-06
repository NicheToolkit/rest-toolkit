package io.github.nichetoolkit.rest;


/**
 * <code>RestEnum</code>
 * <p>The type rest enum interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestEnum {

    /**
     * <code>resolve</code>
     * <p>The method.</p>
     * @param <E>          {@link java.lang.Enum} <p>The generic parameter is <code>Enum</code> type.</p>
     * @param clazz        {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param name         {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param paramClasses {@link java.lang.Class} <p>The param classes parameter is <code>Class</code> type.</p>
     * @param paramValues  {@link java.lang.Object} <p>The param values parameter is <code>Object</code> type.</p>
     * @return E <p>The return object is <code>E</code> type.</p>
     * @see java.lang.Enum
     * @see java.lang.Class
     * @see java.lang.String
     * @see java.lang.Object
     */
    static <E extends Enum<E>> E resolve(Class<E> clazz, String name, Class<?>[] paramClasses, Object[] paramValues) {
        return DefaultEnum.value(clazz, name, paramClasses, paramValues);
    }
}
