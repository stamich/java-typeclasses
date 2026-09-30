package io.codeswarm.typeclasses.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a validation result that is either valid or contains one or more
 * validation errors.
 *
 * <p>The invalid branch stores a {@link NonEmptyList}, making an empty failure
 * state unrepresentable. Generic Applicative-based accumulation remains outside
 * this milestone and is planned after the higher-kinded abstractions exist.</p>
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
     * Creates a failed validation result from a non-empty error collection.
     *
     * @param errors non-empty validation errors
     * @param <E> error type
     * @param <A> value type
     * @return invalid branch
     */
    static <E, A> Validated<E, A> invalid(final NonEmptyList<E> errors) {
        return new Invalid<>(Objects.requireNonNull(errors, "errors"));
    }

    /**
     * Creates a failed validation result containing a single error.
     *
     * @param error validation error
     * @param <E> error type
     * @param <A> value type
     * @return invalid branch
     */
    static <E, A> Validated<E, A> invalid(final E error) {
        return invalid(NonEmptyList.one(error));
    }

    /**
     * Creates a failed validation result containing one required error and
     * optional additional errors.
     *
     * @param first first validation error
     * @param rest additional validation errors
     * @param <E> error type
     * @param <A> value type
     * @return invalid branch
     */
    @SafeVarargs
    static <E, A> Validated<E, A> invalid(final E first, final E... rest) {
        Objects.requireNonNull(rest, "rest");
        final List<E> tail = new ArrayList<>(rest.length);
        for (final E error : rest) {
            tail.add(Objects.requireNonNull(error, "rest error"));
        }
        return invalid(new NonEmptyList<>(first, tail));
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
    <T> T fold(
            Function<? super NonEmptyList<E>, ? extends T> onInvalid,
            Function<? super A, ? extends T> onValid);

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
