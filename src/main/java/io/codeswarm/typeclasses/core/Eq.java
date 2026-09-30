package io.codeswarm.typeclasses.core;

/**
 * Describes equality semantics for values of type {@code A}.
 *
 * <p>The equality strategy is independent of the domain type, so several
 * meaningful definitions of equality may coexist for the same type.</p>
 *
 * @param <A> the compared value type
 */
@FunctionalInterface
public interface Eq<A> {

    /**
     * Determines whether two values are equivalent according to this instance.
     *
     * @param left the left value
     * @param right the right value
     * @return {@code true} when the values are equivalent
     */
    boolean eqv(A left, A right);

    /**
     * Determines whether two values are not equivalent according to this
     * instance.
     *
     * @param left the left value
     * @param right the right value
     * @return {@code true} when the values are not equivalent
     */
    default boolean neqv(final A left, final A right) {
        return !eqv(left, right);
    }
}
