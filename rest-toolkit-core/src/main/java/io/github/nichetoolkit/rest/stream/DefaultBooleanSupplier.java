package io.github.nichetoolkit.rest.stream;


import io.github.nichetoolkit.rest.RestException;

@FunctionalInterface
interface DefaultBooleanSupplier {

    boolean actuate() throws RestException;
}
