package io.github.nichetoolkit.rest;

/**
 * <code>RestStatus</code>
 * <p>The rest status interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public interface RestStatus extends RestValue<Integer,String> {

    @Override
    default Integer getKey() {
        return getStatus();
    }

    @Override
    default String getValue() {
        return getMessage();
    }

    /**
     * <code>getStatus</code>
     * <p>The get status getter method.</p>
     * @return {@link java.lang.Integer} <p>The get status return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    Integer getStatus();

    /**
     * <code>getMessage</code>
     * <p>The get message getter method.</p>
     * @return {@link java.lang.String} <p>The get message return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String getMessage();


}
