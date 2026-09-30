package io.codeswarm.typeclasses.data;

import java.util.Objects;
import java.util.function.Function;

/**
 * Successful branch of {@link Validated}.
 *
 * @param value successful value
 * @param <E> validation error type
 * @param <A> value type
 */
public record Valid<E, A>(A value) implements Validated<E, A> {

    /** Creates a successful validation branch. */
    public Valid {
        Objects.requireNonNull(value, "value");
    }

    /** {@inheritDoc} */
    @Override
    public <B> Validated<E, B> map(final Function<? super A, ? extends B> mapper) {
        Objects.requireNonNull(mapper, "mapper");
        return Validated.valid(mapper.apply(value));
    }

    /** {@inheritDoc} */
    @Override
    public <T> T fold(
            final Function<? super NonEmptyList<E>, ? extends T> onInvalid,
            final Function<? super A, ? extends T> onValid) {
        Objects.requireNonNull(onInvalid, "onInvalid");
        Objects.requireNonNull(onValid, "onValid");
        return onValid.apply(value);
    }
}
