package io.codeswarm.typeclasses.domain;

import io.codeswarm.typeclasses.data.Either;
import java.util.Objects;

/**
 * Validated user identifier that cannot be blank when created through the
 * public API.
 */
public final class UserId {

    private final String value;

    private UserId(final String value) {
        this.value = value;
    }

    /**
     * Validates and creates a user identifier.
     *
     * @param raw raw identifier
     * @return right containing a valid identifier or left containing the error
     */
    public static Either<ValidationError, UserId> from(final String raw) {
        if (raw == null || raw.isBlank()) {
            return Either.left(new BlankUserId());
        }
        return Either.right(new UserId(raw.trim()));
    }

    /**
     * Returns the validated identifier value.
     *
     * @return identifier string
     */
    public String value() {
        return value;
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof UserId userId && value.equals(userId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "UserId(" + value + ")";
    }
}
