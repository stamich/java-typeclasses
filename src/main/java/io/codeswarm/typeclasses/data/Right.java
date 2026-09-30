package io.codeswarm.typeclasses.data;

import java.util.Objects;
import java.util.function.Function;

/**
 * Right branch of {@link Either}.
 *
 * @param value right value
 * @param <L> left type
 * @param <R> right type
 */
public record Right<L, R>(R value) implements Either<L, R> {

    /** Creates a right branch containing a non-null value. */
    public Right {
        Objects.requireNonNull(value, "value");
    }

    /** {@inheritDoc} */
    @Override
    public <B> Either<L, B> map(final Function<? super R, ? extends B> mapper) {
        Objects.requireNonNull(mapper, "mapper");
        return Either.right(mapper.apply(value));
    }

    /** {@inheritDoc} */
    @Override
    public <M> Either<M, R> mapLeft(final Function<? super L, ? extends M> mapper) {
        Objects.requireNonNull(mapper, "mapper");
        return Either.right(value);
    }

    /** {@inheritDoc} */
    @Override
    public <T> T fold(
            final Function<? super L, ? extends T> onLeft,
            final Function<? super R, ? extends T> onRight) {
        Objects.requireNonNull(onLeft, "onLeft");
        Objects.requireNonNull(onRight, "onRight");
        return onRight.apply(value);
    }
}
