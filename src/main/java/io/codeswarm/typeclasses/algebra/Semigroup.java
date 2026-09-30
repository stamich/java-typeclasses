package io.codeswarm.typeclasses.algebra;

/**
 * Represents an associative binary operation over values of type {@code A}.
 *
 * <p>A lawful semigroup must satisfy associativity:</p>
 * <pre>{@code
 * combine(combine(a, b), c) == combine(a, combine(b, c))
 * }</pre>
 *
 * <p>Milestone 0.2 documents this law but deliberately leaves systematic law
 * verification to milestone 0.3.</p>
 *
 * @param <A> the value type
 */
@FunctionalInterface
public interface Semigroup<A> {

    /**
     * Combines two values.
     *
     * @param left the left value
     * @param right the right value
     * @return the combined value
     */
    A combine(A left, A right);
}
