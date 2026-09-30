package io.codeswarm.typeclasses.laws;

import io.codeswarm.typeclasses.algebra.Semigroup;
import io.codeswarm.typeclasses.core.Eq;

/**
 * Reusable executable laws for {@link Semigroup} instances.
 */
public final class SemigroupLaws {

    private SemigroupLaws() {
        throw new AssertionError("Law utility must not be instantiated");
    }

    /**
     * Checks associativity of a semigroup operation.
     *
     * @param first first value
     * @param second second value
     * @param third third value
     * @param semigroup semigroup instance
     * @param eq equality used to compare both association results
     * @param <A> value type
     * @return {@code true} when associativity holds for the supplied values
     */
    public static <A> boolean associative(
            final A first,
            final A second,
            final A third,
            final Semigroup<A> semigroup,
            final Eq<? super A> eq) {
        final A leftAssociated = semigroup.combine(semigroup.combine(first, second), third);
        final A rightAssociated = semigroup.combine(first, semigroup.combine(second, third));
        return eq.eqv(leftAssociated, rightAssociated);
    }
}
