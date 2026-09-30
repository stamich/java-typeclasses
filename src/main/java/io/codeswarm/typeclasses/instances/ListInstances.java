package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.algebra.Monoid;

import java.util.ArrayList;
import java.util.List;

/**
 * Typeclass instance factories for immutable-style operations on {@link List}
 * values.
 */
public final class ListInstances {

    private ListInstances() {
        throw new AssertionError("Instances holder must not be instantiated");
    }

    /**
     * Creates a list monoid under concatenation.
     *
     * <p>The implementation never mutates either input list. Every non-trivial
     * combination returns an unmodifiable copy and the identity is
     * {@link List#of()}.</p>
     *
     * @param <A> the list element type
     * @return a monoid that concatenates lists
     */
    public static <A> Monoid<List<A>> concatenation() {
        return new Monoid<>() {
            /** {@inheritDoc} */
            @Override
            public List<A> combine(final List<A> left, final List<A> right) {
                final var result = new ArrayList<A>(left.size() + right.size());
                result.addAll(left);
                result.addAll(right);
                return List.copyOf(result);
            }

            /** {@inheritDoc} */
            @Override
            public List<A> empty() {
                return List.of();
            }
        };
    }
}
