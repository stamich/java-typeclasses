package io.codeswarm.typeclasses.syntax;

import io.codeswarm.typeclasses.core.Show;

import java.util.Objects;

/**
 * Generic operations that consume {@link Show} instances explicitly.
 */
public final class ShowFunctions {

    private ShowFunctions() {
        throw new AssertionError("Utility class must not be instantiated");
    }

    /**
     * Renders a value with the supplied {@link Show} instance.
     *
     * @param value the value to render
     * @param instance the rendering instance
     * @param <A> the value type
     * @return the rendered value
     * @throws NullPointerException if {@code instance} is {@code null}
     */
    public static <A> String show(final A value, final Show<? super A> instance) {
        Objects.requireNonNull(instance, "instance must not be null");
        return instance.show(value);
    }
}
