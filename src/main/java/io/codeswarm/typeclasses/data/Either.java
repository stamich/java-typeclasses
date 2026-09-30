package io.codeswarm.typeclasses.data;

import java.util.Objects;
import java.util.function.Function;

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
