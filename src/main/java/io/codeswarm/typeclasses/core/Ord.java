package io.codeswarm.typeclasses.core;

/**
 * Defines a total ordering for values of type {@code A}.
 *
 * <p>An ordering is also an equality definition: values are equivalent when
 * {@link #compare(Object, Object)} returns zero.</p>
 *
 * @param <A> the ordered value type
 */
@FunctionalInterface
public interface Ord<A> extends Eq<A> {

    /**
     * Compares two values.
     *
     * @param left the left value
     * @param right the right value
     * @return a negative number when {@code left < right}, zero when they are
     *         equivalent, or a positive number when {@code left > right}
     */
    int compare(A left, A right);

    /**
     * Determines equality from the ordering relation.
     *
     * @param left the left value
     * @param right the right value
     * @return {@code true} when {@link #compare(Object, Object)} returns zero
     */
    @Override
    default boolean eqv(final A left, final A right) {
        return compare(left, right) == 0;
    }

    /**
     * Checks whether the left value precedes the right value.
     *
     * @param left the left value
     * @param right the right value
     * @return {@code true} when {@code left < right}
     */
    default boolean lessThan(final A left, final A right) {
        return compare(left, right) < 0;
    }

    /**
     * Checks whether the left value follows the right value.
     *
     * @param left the left value
     * @param right the right value
     * @return {@code true} when {@code left > right}
     */
    default boolean greaterThan(final A left, final A right) {
        return compare(left, right) > 0;
    }

    /**
     * Returns the smaller of two values according to this ordering.
     *
     * @param left the left value
     * @param right the right value
     * @return the smaller value; {@code left} is retained on equality
     */
    default A min(final A left, final A right) {
        return compare(left, right) <= 0 ? left : right;
    }

    /**
     * Returns the larger of two values according to this ordering.
     *
     * @param left the left value
     * @param right the right value
     * @return the larger value; {@code left} is retained on equality
     */
    default A max(final A left, final A right) {
        return compare(left, right) >= 0 ? left : right;
    }
}
