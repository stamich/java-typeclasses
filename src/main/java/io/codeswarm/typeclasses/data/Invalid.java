package io.codeswarm.typeclasses.data;

import java.util.Objects;
import java.util.function.Function;

/**
 * Failed branch of {@link Validated}, containing one or more validation errors.
 *
 * <p>The non-empty invariant is represented by {@link NonEmptyList} rather than
 * checked dynamically in this record.</p>
 *
 * @param errors non-empty validation errors
 * @param <E> validation error type
 * @param <A> successful value type
 */
public record Invalid<E, A>(NonEmptyList<E> errors) implements Validated<E, A> {

    /**
     * Creates an invalid validation result.
     *
     * @param errors non-empty error collection
     * @throws NullPointerException when {@code errors} is null
     */
    public Invalid {
        Objects.requireNonNull(errors, "errors");
    }

    /** {@inheritDoc} */
    @Override
    public <B> Validated<E, B> map(final Function<? super A, ? extends B> mapper) {
        Objects.requireNonNull(mapper, "mapper");
        return Validated.invalid(errors);
    }

    /** {@inheritDoc} */
    @Override
    public <T> T fold(
            final Function<? super NonEmptyList<E>, ? extends T> onInvalid,
            final Function<? super A, ? extends T> onValid) {
        Objects.requireNonNull(onInvalid, "onInvalid");
        Objects.requireNonNull(onValid, "onValid");
        return onInvalid.apply(errors);
    }
}
