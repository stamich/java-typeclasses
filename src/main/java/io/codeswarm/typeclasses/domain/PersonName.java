package io.codeswarm.typeclasses.domain;

import io.codeswarm.typeclasses.data.Either;
import java.util.Objects;

/**
 * Validated person name with a non-blank, bounded-length invariant.
 */
public final class PersonName {

    /** Maximum accepted person-name length. */
    public static final int MAX_LENGTH = 100;

    private final String value;

    private PersonName(final String value) {
        this.value = value;
    }

    /**
     * Validates and creates a person name.
     *
     * @param raw raw name
     * @return right containing a valid name or left containing the first error
     */
    public static Either<ValidationError, PersonName> from(final String raw) {
        if (raw == null || raw.isBlank()) {
            return Either.left(new BlankPersonName());
        }
        final String normalized = raw.trim();
        if (normalized.length() > MAX_LENGTH) {
            return Either.left(new PersonNameTooLong(normalized.length(), MAX_LENGTH));
        }
        return Either.right(new PersonName(normalized));
    }

    /** Returns the validated name. */
    public String value() {
        return value;
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof PersonName name && value.equals(name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "PersonName(" + value + ")";
    }
}
