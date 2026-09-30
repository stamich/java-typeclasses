package io.codeswarm.typeclasses.algebra;

/**
 * Represents a {@link Semigroup} with an identity element.
 *
 * <p>In addition to associativity, a lawful monoid must satisfy left and right
 * identity:</p>
 * <pre>{@code
 * combine(empty(), a) == a
 * combine(a, empty()) == a
 * }</pre>
 *
 * @param <A> the value type
 */
public interface Monoid<A> extends Semigroup<A> {

    /**
     * Returns the identity element of this monoid.
     *
     * @return the identity value
     */
    A empty();
}
