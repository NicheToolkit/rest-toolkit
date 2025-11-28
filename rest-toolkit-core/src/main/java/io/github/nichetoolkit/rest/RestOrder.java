package io.github.nichetoolkit.rest;

/**
 * <code>RestOrder</code>
 * <p>The rest order interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.Comparable
 * @since Jdk17
 */
public interface RestOrder extends Comparable<RestOrder> {

    /**
     * <code>getOrder</code>
     * <p>The get order getter method.</p>
     * @return int <p>The get order return object is <code>int</code> type.</p>
     */
    default int getOrder() {
        return 0;
    }

    @Override
    default int compareTo(RestOrder restOrder) {
        return Integer.compare(this.getOrder(), restOrder.getOrder());
    }

}
