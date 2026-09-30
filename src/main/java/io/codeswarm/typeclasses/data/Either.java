package io.codeswarm.typeclasses.data;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represents a value that is either a left value, commonly an error, or a right
 * value, commonly a successful result.
 *
 * <p>The type is right-biased for {@link #map(Function)}: mapping transforms only
 * the {@link Right} branch.</p>
 *
 * @param <L> left value type
 * @param <R> right value type
 */
public sealed interface Either<L, R> permits Left, Right {

    /**
     * Creates a left value.
     *
     * @param value left value
     * @param <L> left type
     * @param <R> right type
     * @return left branch
     */
    static <L, R> Either<L, R> left(final L value) {
        return new Left<>(Objects.requireNonNull(value, "value"));
    }

    /**
     * Creates a right value.
     *
     * @param value right value
     * @param <L> left type
     * @param <R> right type
     * @return right branch
     */
    static <L, R> Either<L, R> right(final R value) {
        return new Right<>(Objects.requireNonNull(value, "value"));
    }

    /**
     * Creates a right value when {@code condition} is true and a left value
     * otherwise. Suppliers are evaluated lazily.
     *
     * @param condition branch condition
     * @param right right-value supplier
     * @param left left-value supplier
     * @param <L> left type
     * @param <R> right type
     * @return selected branch
     */
    static <L, R> Either<L, R> cond(
            final boolean condition,
            final Supplier<? extends R> right,
            final Supplier<? extends L> left) {
        Objects.requireNonNull(right, "right");
        Objects.requireNonNull(left, "left");
        return condition ? Either.right(right.get()) : Either.left(left.get());
    }

    /**
     * Converts a nullable value into an {@code Either}.
     *
     * @param value nullable right value
     * @param ifNull left-value supplier used for {@code null}
     * @param <L> left type
     * @param <R> right type
     * @return right for a non-null value, otherwise left
     */
    static <L, R> Either<L, R> fromNullable(
            final R value,
            final Supplier<? extends L> ifNull) {
        Objects.requireNonNull(ifNull, "ifNull");
        return value == null ? Either.left(ifNull.get()) : Either.right(value);
    }

    /**
     * Converts a JDK {@link Optional} into an {@code Either}.
     *
     * @param optional optional right value
     * @param ifEmpty left-value supplier used for an empty optional
     * @param <L> left type
     * @param <R> right type
     * @return right for a present value, otherwise left
     */
    static <L, R> Either<L, R> fromOptional(
            final Optional<? extends R> optional,
            final Supplier<? extends L> ifEmpty) {
        Objects.requireNonNull(optional, "optional");
        Objects.requireNonNull(ifEmpty, "ifEmpty");
        return optional.<Either<L, R>>map(Either::right)
                .orElseGet(() -> Either.left(ifEmpty.get()));
    }

    /**
     * Transforms the right branch while preserving the left branch.
     *
     * @param mapper mapping function
     * @param <B> new right type
     * @return mapped either
     */
    <B> Either<L, B> map(Function<? super R, ? extends B> mapper);

    /**
     * Transforms the left branch while preserving the right branch.
     *
     * @param mapper mapping function
     * @param <M> new left type
     * @return mapped either
     */
    <M> Either<M, R> mapLeft(Function<? super L, ? extends M> mapper);

    /**
     * Eliminates this ADT using one function for each branch.
     *
     * @param onLeft left-branch function
     * @param onRight right-branch function
     * @param <T> result type
     * @return branch result
     */
    <T> T fold(Function<? super L, ? extends T> onLeft, Function<? super R, ? extends T> onRight);

    /**
     * Returns whether this value is a right branch.
     *
     * @return {@code true} for {@link Right}
     */
    default boolean isRight() {
        return fold(ignored -> false, ignored -> true);
    }

    /**
     * Returns whether this value is a left branch.
     *
     * @return {@code true} for {@link Left}
     */
    default boolean isLeft() {
        return !isRight();
    }
}
