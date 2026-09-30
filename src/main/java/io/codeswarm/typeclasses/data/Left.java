package io.codeswarm.typeclasses.data;

import java.util.Objects;
import java.util.function.Function;

/**
 * Left branch of {@link Either}.
 *
 * @param value left value
 * @param <L> left type
 * @param <R> right type
 */
public record Left<L, R>(L value) implements Either<L, R> {

    /** Creates a left branch containing a non-null value. */
    public Left {
        Objects.requireNonNull(value, "value");
    }

    /** {@inheritDoc} */
    @Override
    public <B> Either<L, B> map(final Function<? super R, ? extends B> mapper) {
        Objects.requireNonNull(mapper, "mapper");
        return Either.left(value);
    }

    /** {@inheritDoc} */
    @Override
    public <M> Either<M, R> mapLeft(final Function<? super L, ? extends M> mapper) {
        Objects.requireNonNull(mapper, "mapper");
        return Either.left(mapper.apply(value));
    }

    /** {@inheritDoc} */
    @Override
    public <T> T fold(
            final Function<? super L, ? extends T> onLeft,
            final Function<? super R, ? extends T> onRight) {
        Objects.requireNonNull(onLeft, "onLeft");
        Objects.requireNonNull(onRight, "onRight");
        return onLeft.apply(value);
    }
}
