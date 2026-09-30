package io.codeswarm.typeclasses.laws;

import io.codeswarm.typeclasses.core.Ord;

/**
 * Reusable executable laws for {@link Ord} instances.
 */
public final class OrdLaws {

    private OrdLaws() {
        throw new AssertionError("Law utility must not be instantiated");
    }

    /**
     * Checks reflexivity of ordering: comparing a value with itself yields zero.
     *
     * @param value tested value
     * @param ord ordering instance
     * @param <A> value type
     * @return {@code true} when reflexivity holds
     */
    public static <A> boolean reflexive(final A value, final Ord<? super A> ord) {
        return ord.compare(value, value) == 0;
    }

    /**
     * Checks sign antisymmetry of comparison results.
     *
     * @param left left value
     * @param right right value
     * @param ord ordering instance
     * @param <A> value type
     * @return {@code true} when reversing arguments reverses the comparison sign
     */
    public static <A> boolean signAntisymmetric(
            final A left,
            final A right,
            final Ord<? super A> ord) {
        return Integer.signum(ord.compare(left, right)) == -Integer.signum(ord.compare(right, left));
    }

    /**
     * Checks transitivity of the less-than-or-equal relation.
     *
     * @param first first value
     * @param second second value
     * @param third third value
     * @param ord ordering instance
     * @param <A> value type
     * @return {@code true} when the transitivity implication holds
     */
    public static <A> boolean transitive(
            final A first,
            final A second,
            final A third,
            final Ord<? super A> ord) {
        final boolean firstBeforeSecond = ord.compare(first, second) <= 0;
        final boolean secondBeforeThird = ord.compare(second, third) <= 0;
        return !(firstBeforeSecond && secondBeforeThird) || ord.compare(first, third) <= 0;
    }

    /**
     * Checks that {@link Ord#eqv(Object, Object)} agrees with zero comparison.
     *
     * @param left left value
     * @param right right value
     * @param ord ordering instance
     * @param <A> value type
     * @return {@code true} when equality and comparison agree
     */
    public static <A> boolean consistentWithEquality(
            final A left,
            final A right,
            final Ord<? super A> ord) {
        return ord.eqv(left, right) == (ord.compare(left, right) == 0);
    }
}
