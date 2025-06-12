package io.github.nichetoolkit.rest;

public interface RestOrder extends Comparable<RestOrder> {

    default int getOrder() {
        return 0;
    }

    @Override
    default int compareTo(RestOrder restOrder) {
        return Integer.compare(this.getOrder(), restOrder.getOrder());
    }

}
