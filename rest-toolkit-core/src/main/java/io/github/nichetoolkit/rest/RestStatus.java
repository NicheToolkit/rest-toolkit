package io.github.nichetoolkit.rest;

/**
 * <code>RestStatus</code>
 * <p>The rest status interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk17
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

    /**
     * <code>buildResult</code>
     * <p>The build result method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The build result return object is <code>RestResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     */
    default RestResult<?> buildResult() {
        return RestResult.defaultBuilder().status(this.getStatus()).message(getMessage()).build();
    }

    /**
     * <code>buildResult</code>
     * <p>The build result method.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The build result return object is <code>RestResult</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestResult
     */
    default RestResult<?> buildResult(String message) {
        return RestResult.defaultBuilder().status(this.getStatus()).message(message).build();
    }
}
