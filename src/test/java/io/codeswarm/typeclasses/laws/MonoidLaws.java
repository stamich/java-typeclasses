package io.codeswarm.typeclasses.laws;

import io.codeswarm.typeclasses.algebra.Monoid;
import io.codeswarm.typeclasses.core.Eq;

/**
 * Reusable executable laws for {@link Monoid} instances.
 */
public final class MonoidLaws {

    private MonoidLaws() {
        throw new AssertionError("Law utility must not be instantiated");
    }

    /**
     * Checks the inherited semigroup associativity law.
     *
     * @param first first value
     * @param second second value
     * @param third third value
     * @param monoid monoid instance
     * @param eq equality used to compare results
     * @param <A> value type
     * @return {@code true} when associativity holds
     */
    public static <A> boolean associative(
            final A first,
            final A second,
            final A third,
            final Monoid<A> monoid,
            final Eq<? super A> eq) {
        return SemigroupLaws.associative(first, second, third, monoid, eq);
    }

    /**
     * Checks the left identity law: {@code empty <> a == a}.
     *
     * @param value tested value
     * @param monoid monoid instance
     * @param eq equality used to compare results
     * @param <A> value type
     * @return {@code true} when left identity holds
     */
    public static <A> boolean leftIdentity(
            final A value,
            final Monoid<A> monoid,
            final Eq<? super A> eq) {
        return eq.eqv(monoid.combine(monoid.empty(), value), value);
    }

    /**
     * Checks the right identity law: {@code a <> empty == a}.
     *
     * @param value tested value
     * @param monoid monoid instance
     * @param eq equality used to compare results
     * @param <A> value type
     * @return {@code true} when right identity holds
     */
    public static <A> boolean rightIdentity(
            final A value,
            final Monoid<A> monoid,
            final Eq<? super A> eq) {
        return eq.eqv(monoid.combine(value, monoid.empty()), value);
    }
}
