package io.codeswarm.typeclasses.domain;

import io.codeswarm.typeclasses.data.Either;

/**
 * Validated age constrained to the inclusive range 0..130.
 */
public final class Age {

    /** Largest accepted age. */
    public static final int MAX_VALUE = 130;

    private final int value;

    private Age(final int value) {
        this.value = value;
    }

    /**
     * Validates and creates an age.
     *
     * @param raw raw age
     * @return right containing a valid age or left containing the error
     */
    public static Either<ValidationError, Age> from(final int raw) {
        return Either.cond(
                raw >= 0 && raw <= MAX_VALUE,
                () -> new Age(raw),
                () -> new InvalidAge(raw));
    }

    /** Returns the validated age value. */
    public int value() {
        return value;
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof Age age && value == age.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return "Age(" + value + ")";
    }
}
