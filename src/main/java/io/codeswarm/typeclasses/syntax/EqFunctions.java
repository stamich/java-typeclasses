package io.codeswarm.typeclasses.syntax;

import io.codeswarm.typeclasses.core.Eq;

import java.util.Objects;

/**
 * Generic operations that consume {@link Eq} instances explicitly.
 */
public final class EqFunctions {

    private EqFunctions() {
        throw new AssertionError("Utility class must not be instantiated");
    }

    /**
     * Compares two values using the supplied equality definition.
     *
     * @param left the left value
     * @param right the right value
     * @param instance the equality instance
     * @param <A> the compared value type
     * @return {@code true} when both values are equivalent
     * @throws NullPointerException if {@code instance} is {@code null}
     */
    public static <A> boolean equal(
            final A left,
            final A right,
            final Eq<? super A> instance) {
        Objects.requireNonNull(instance, "instance must not be null");
        return instance.eqv(left, right);
    }
}
