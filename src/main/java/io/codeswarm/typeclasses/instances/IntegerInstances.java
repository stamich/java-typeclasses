package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.algebra.Monoid;
import io.codeswarm.typeclasses.core.Eq;
import io.codeswarm.typeclasses.core.Ord;
import io.codeswarm.typeclasses.core.Show;

/**
 * Standard typeclass instances for {@link Integer} values.
 */
public final class IntegerInstances {

    /** Renders an integer using its standard decimal representation. */
    public static final Show<Integer> SHOW = String::valueOf;

    /** Compares integers using value equality. */
    public static final Eq<Integer> EQ = Integer::equals;

    /** Orders integers by their numeric value. */
    public static final Ord<Integer> ORD = Integer::compare;

    /**
     * Integer monoid under addition. The identity element is {@code 0}.
     */
    public static final Monoid<Integer> ADDITION = new Monoid<>() {
        /** {@inheritDoc} */
        @Override
        public Integer combine(final Integer left, final Integer right) {
            return left + right;
        }

        /** {@inheritDoc} */
        @Override
        public Integer empty() {
            return 0;
        }
    };

    /**
     * Integer monoid under multiplication. The identity element is {@code 1}.
     */
    public static final Monoid<Integer> MULTIPLICATION = new Monoid<>() {
        /** {@inheritDoc} */
        @Override
        public Integer combine(final Integer left, final Integer right) {
            return left * right;
        }

        /** {@inheritDoc} */
        @Override
        public Integer empty() {
            return 1;
        }
    };

    private IntegerInstances() {
        throw new AssertionError("Instances holder must not be instantiated");
    }
}
