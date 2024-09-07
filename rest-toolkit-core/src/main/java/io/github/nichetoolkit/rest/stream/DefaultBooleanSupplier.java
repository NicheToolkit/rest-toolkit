package io.github.nichetoolkit.rest.stream;


import io.github.nichetoolkit.rest.RestException;

@FunctionalInterface
public interface DefaultBooleanSupplier {

    boolean actuate() throws RestException;
}
