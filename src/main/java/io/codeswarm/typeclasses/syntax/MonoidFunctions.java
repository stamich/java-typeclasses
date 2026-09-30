package io.codeswarm.typeclasses.syntax;

import io.codeswarm.typeclasses.algebra.Monoid;

import java.util.Objects;

/**
 * Generic algorithms based on {@link Monoid}.
 */
public final class MonoidFunctions {

    private MonoidFunctions() {
        throw new AssertionError("Utility class must not be instantiated");
    }

    /**
     * Combines all values from left to right, starting with the monoid identity.
     *
     * <p>Unlike a semigroup fold, this operation is defined for an empty
     * iterable and returns {@link Monoid#empty()} in that case.</p>
     *
     * @param values the values to combine
     * @param instance the monoid instance
     * @param <A> the value type
     * @return the combined value, or the identity for an empty iterable
     * @throws NullPointerException if {@code values} or {@code instance} is null
     */
    public static <A> A combineAll(
            final Iterable<? extends A> values,
            final Monoid<A> instance) {
        Objects.requireNonNull(values, "values must not be null");
        Objects.requireNonNull(instance, "instance must not be null");

        A result = instance.empty();
        for (final A value : values) {
            result = instance.combine(result, value);
        }
        return result;
    }
}
