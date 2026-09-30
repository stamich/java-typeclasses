package io.codeswarm.typeclasses.syntax;

import io.codeswarm.typeclasses.algebra.Semigroup;

import java.util.Iterator;
import java.util.Objects;

/**
 * Generic algorithms based on {@link Semigroup}.
 */
public final class SemigroupFunctions {

    private SemigroupFunctions() {
        throw new AssertionError("Utility class must not be instantiated");
    }

    /**
     * Combines all values from a non-empty iterable from left to right.
     *
     * <p>A semigroup has no identity element, therefore there is no lawful
     * result for an empty iterable.</p>
     *
     * @param values the values to combine
     * @param instance the semigroup instance
     * @param <A> the value type
     * @return the combined value
     * @throws NullPointerException if {@code values} or {@code instance} is null
     * @throws IllegalArgumentException if {@code values} is empty
     */
    public static <A> A combineAll(
            final Iterable<? extends A> values,
            final Semigroup<A> instance) {
        Objects.requireNonNull(values, "values must not be null");
        Objects.requireNonNull(instance, "instance must not be null");

        final Iterator<? extends A> iterator = values.iterator();
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException(
                    "Semigroup cannot combine an empty iterable");
        }

        A result = iterator.next();
        while (iterator.hasNext()) {
            result = instance.combine(result, iterator.next());
        }
        return result;
    }
}
