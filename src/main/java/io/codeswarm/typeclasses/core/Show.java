package io.codeswarm.typeclasses.core;

/**
 * Describes how a value of type {@code A} is converted to a human-readable
 * textual representation.
 *
 * <p>{@code Show} demonstrates the central idea of the typeclass pattern:
 * behavior is defined independently of the domain type. A domain class does
 * not have to implement this interface and more than one {@code Show} instance
 * may exist for the same type.</p>
 *
 * @param <A> the type of value that can be rendered
 */
@FunctionalInterface
public interface Show<A> {

    /**
     * Produces a textual representation of the supplied value.
     *
     * <p>The treatment of {@code null} is intentionally left to the concrete
     * instance. The typeclass itself does not impose a global null policy.</p>
     *
     * @param value the value to render
     * @return the textual representation
     */
    String show(A value);
}
