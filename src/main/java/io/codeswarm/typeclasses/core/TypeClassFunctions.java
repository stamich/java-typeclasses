package io.codeswarm.typeclasses.core;

import java.util.Objects;

/**
 * Contains generic algorithms that consume typeclass instances explicitly.
 *
 * <p>Milestone 0.1.1 intentionally uses explicit dictionary passing instead
 * of reflection, a registry, dependency injection, or automatic instance
 * resolution. This keeps the mechanism visible and makes dependencies easy to
 * reason about.</p>
 */
public final class TypeClassFunctions {

    private TypeClassFunctions() {
        throw new AssertionError("Utility class must not be instantiated");
    }

    /**
     * Renders a value using the supplied {@link Show} instance.
     *
     * @param value the value to render
     * @param show the typeclass instance that defines the rendering behaviour
     * @param <A> the value type
     * @return the representation produced by {@code show}
     * @throws NullPointerException if {@code show} is {@code null}
     */
    public static <A> String show(final A value, final Show<? super A> show) {
        Objects.requireNonNull(show, "show must not be null");
        return show.show(value);
    }
}
