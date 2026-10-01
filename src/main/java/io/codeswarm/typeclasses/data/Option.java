package io.codeswarm.typeclasses.data;

import io.codeswarm.typeclasses.hkt.Kind;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represents an optional value without using {@code null} as part of the public API.
 *
 * <p>{@code Option} is a sealed algebraic data type with exactly two variants:
 * {@link Some}, containing a value, and {@link None}, representing absence.</p>
 *
 * @param <A> contained value type
 */
public sealed interface Option<A> extends Kind<OptionK, A> permits Some, None {

    /**
     * Creates an option containing a non-null value.
     *
     * @param value value to wrap
     * @param <A> value type
     * @return a {@link Some}
     * @throws NullPointerException when {@code value} is {@code null}
     */
    static <A> Option<A> some(final A value) {
        return new Some<>(Objects.requireNonNull(value, "value"));
    }

    /**
     * Creates an empty option.
     *
     * @param <A> value type
     * @return an empty {@link None}
     */
    static <A> Option<A> none() {
        return new None<>();
    }

    /**
     * Creates an option from a nullable reference.
     *
     * @param value nullable value
     * @param <A> value type
     * @return {@link None} for {@code null}; otherwise {@link Some}
     */
    static <A> Option<A> fromNullable(final A value) {
        return value == null ? none() : some(value);
    }

    /**
     * Creates a present option only when the condition is true.
     *
     * <p>The supplier is evaluated lazily and only for the true branch.</p>
     *
     * @param condition condition deciding whether a value is created
     * @param supplier value supplier used when the condition is true
     * @param <A> value type
     * @return present or empty option
     */
    static <A> Option<A> when(
            final boolean condition,
            final Supplier<? extends A> supplier) {
        Objects.requireNonNull(supplier, "supplier");
        return condition ? some(supplier.get()) : none();
    }

    /**
     * Transforms a present value while preserving absence.
     *
     * @param mapper transformation applied to a present value
     * @param <B> resulting value type
     * @return transformed option
     */
    <B> Option<B> map(Function<? super A, ? extends B> mapper);

    /**
     * Eliminates this ADT by supplying one function for each variant.
     *
     * @param onNone computation used for an empty option
     * @param onSome computation used for a present value
     * @param <B> resulting type
     * @return result produced by the matching branch
     */
    <B> B fold(Supplier<? extends B> onNone, Function<? super A, ? extends B> onSome);

    /**
     * Returns whether this option contains a value.
     *
     * @return {@code true} for {@link Some}, otherwise {@code false}
     */
    default boolean isDefined() {
        return fold(() -> false, ignored -> true);
    }

    /**
     * Returns whether this option is empty.
     *
     * @return {@code true} for {@link None}, otherwise {@code false}
     */
    default boolean isEmpty() {
        return !isDefined();
    }

    /**
     * Returns the contained value when present or computes a fallback.
     *
     * @param fallback fallback computation
     * @return contained value or fallback value
     */
    default A getOrElse(final Supplier<? extends A> fallback) {
        Objects.requireNonNull(fallback, "fallback");
        return fold(fallback, Function.identity());
    }
}
