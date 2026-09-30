package io.codeswarm.typeclasses.data;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a validation result that is either valid or contains one or more
 * validation errors.
 *
 * <p>This milestone intentionally models validation without introducing an
 * Applicative typeclass. Error accumulation across independent computations is
 * deferred until the project has a generic Applicative abstraction.</p>
 *
 * @param <E> validation error type
 * @param <A> successful value type
 */
public sealed interface Validated<E, A> permits Valid, Invalid {

    /**
     * Creates a successful validation result.
     *
     * @param value validated value
     * @param <E> error type
     * @param <A> value type
     * @return valid branch
     */
    static <E, A> Validated<E, A> valid(final A value) {
        return new Valid<>(Objects.requireNonNull(value, "value"));
    }

    /**
     * Creates a failed validation result from one or more errors.
     *
     * @param errors validation errors
     * @param <E> error type
     * @param <A> value type
     * @return invalid branch
     */
    static <E, A> Validated<E, A> invalid(final List<E> errors) {
        return new Invalid<>(errors);
    }

    /**
     * Transforms a valid value while preserving validation errors.
     *
     * @param mapper mapping function
     * @param <B> resulting value type
     * @return mapped validation
     */
    <B> Validated<E, B> map(Function<? super A, ? extends B> mapper);

    /**
     * Eliminates this ADT using one function for each branch.
     *
     * @param onInvalid invalid-branch function
     * @param onValid valid-branch function
     * @param <T> result type
     * @return branch result
     */
    <T> T fold(Function<? super List<E>, ? extends T> onInvalid, Function<? super A, ? extends T> onValid);

    /**
     * Returns whether validation succeeded.
     *
     * @return {@code true} for {@link Valid}
     */
    default boolean isValid() {
        return fold(ignored -> false, ignored -> true);
    }

    /**
     * Returns whether validation failed.
     *
     * @return {@code true} for {@link Invalid}
     */
    default boolean isInvalid() {
        return !isValid();
    }
}
