package io.codeswarm.typeclasses.syntax;

import io.codeswarm.typeclasses.core.Ord;

import java.util.Objects;

/**
 * Generic operations that consume {@link Ord} instances explicitly.
 */
public final class OrdFunctions {

    private OrdFunctions() {
        throw new AssertionError("Utility class must not be instantiated");
    }

    /**
     * Returns the smaller of two values according to the supplied ordering.
     *
     * @param left the left value
     * @param right the right value
     * @param instance the ordering instance
     * @param <A> the ordered value type
     * @return the smaller value
     * @throws NullPointerException if {@code instance} is {@code null}
     */
    public static <A> A min(final A left, final A right, final Ord<A> instance) {
        Objects.requireNonNull(instance, "instance must not be null");
        return instance.min(left, right);
    }

    /**
     * Returns the larger of two values according to the supplied ordering.
     *
     * @param left the left value
     * @param right the right value
     * @param instance the ordering instance
     * @param <A> the ordered value type
     * @return the larger value
     * @throws NullPointerException if {@code instance} is {@code null}
     */
    public static <A> A max(final A left, final A right, final Ord<A> instance) {
        Objects.requireNonNull(instance, "instance must not be null");
        return instance.max(left, right);
    }
}
