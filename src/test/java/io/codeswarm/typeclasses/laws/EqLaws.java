package io.codeswarm.typeclasses.laws;

import io.codeswarm.typeclasses.core.Eq;

/**
 * Reusable executable laws for {@link Eq} instances.
 *
 * <p>The helpers return booleans so they can be used from both example-based
 * JUnit tests and property-based jqwik tests without coupling the law model to
 * a specific assertion library.</p>
 */
public final class EqLaws {

    private EqLaws() {
        throw new AssertionError("Law utility must not be instantiated");
    }

    /**
     * Checks reflexivity: {@code a ~ a}.
     *
     * @param value tested value
     * @param eq equality instance
     * @param <A> value type
     * @return {@code true} when reflexivity holds for the supplied value
     */
    public static <A> boolean reflexive(final A value, final Eq<? super A> eq) {
        return eq.eqv(value, value);
    }

    /**
     * Checks symmetry: {@code a ~ b} iff {@code b ~ a}.
     *
     * @param left left value
     * @param right right value
     * @param eq equality instance
     * @param <A> value type
     * @return {@code true} when symmetry holds for the supplied pair
     */
    public static <A> boolean symmetric(final A left, final A right, final Eq<? super A> eq) {
        return eq.eqv(left, right) == eq.eqv(right, left);
    }

    /**
     * Checks transitivity: when {@code a ~ b} and {@code b ~ c}, then
     * {@code a ~ c} must also hold.
     *
     * @param first first value
     * @param second second value
     * @param third third value
     * @param eq equality instance
     * @param <A> value type
     * @return {@code true} when the transitivity implication holds
     */
    public static <A> boolean transitive(
            final A first,
            final A second,
            final A third,
            final Eq<? super A> eq) {
        return !(eq.eqv(first, second) && eq.eqv(second, third)) || eq.eqv(first, third);
    }
}
