package io.codeswarm.typeclasses.data;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Failed branch of {@link Validated}, containing one or more validation errors.
 *
 * @param errors immutable non-empty list of validation errors
 * @param <E> validation error type
 * @param <A> successful value type
 */
public record Invalid<E, A>(List<E> errors) implements Validated<E, A> {

    /**
     * Creates an invalid validation result.
     *
     * @param errors non-empty error list
     * @throws NullPointerException when the list or an element is {@code null}
     * @throws IllegalArgumentException when the list is empty
     */
    public Invalid {
        Objects.requireNonNull(errors, "errors");
        if (errors.isEmpty()) {
            throw new IllegalArgumentException("errors must not be empty");
        }
        errors = List.copyOf(errors);
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
            final Function<? super List<E>, ? extends T> onInvalid,
            final Function<? super A, ? extends T> onValid) {
        Objects.requireNonNull(onInvalid, "onInvalid");
        Objects.requireNonNull(onValid, "onValid");
        return onInvalid.apply(errors);
    }
}
