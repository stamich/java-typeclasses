package io.codeswarm.typeclasses.domain;

import io.codeswarm.typeclasses.data.Either;
import io.codeswarm.typeclasses.data.NonEmptyList;
import io.codeswarm.typeclasses.data.Validated;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain-specific validator demonstrating error accumulation before a generic
 * {@code Applicative<Validated>} exists.
 *
 * <p>This class is intentionally concrete and small. Generic validation
 * composition is deferred to the Applicative milestone.</p>
 */
public final class UserValidator {

    private UserValidator() {
        throw new AssertionError("Utility class must not be instantiated");
    }

    /**
     * Validates all user fields and accumulates every independent failure.
     *
     * @param rawId raw user identifier
     * @param rawName raw person name
     * @param rawAge raw age
     * @return valid user or non-empty accumulated validation errors
     */
    public static Validated<ValidationError, User> validate(
            final String rawId,
            final String rawName,
            final int rawAge) {
        final Either<ValidationError, UserId> id = UserId.from(rawId);
        final Either<ValidationError, PersonName> name = PersonName.from(rawName);
        final Either<ValidationError, Age> age = Age.from(rawAge);

        final List<ValidationError> errors = new ArrayList<>();
        id.fold(error -> { errors.add(error); return null; }, ignored -> null);
        name.fold(error -> { errors.add(error); return null; }, ignored -> null);
        age.fold(error -> { errors.add(error); return null; }, ignored -> null);

        if (!errors.isEmpty()) {
            return Validated.invalid(toNonEmptyList(errors));
        }

        final UserId validId = id.fold(UserValidator::unexpected, value -> value);
        final PersonName validName = name.fold(UserValidator::unexpected, value -> value);
        final Age validAge = age.fold(UserValidator::unexpected, value -> value);
        return Validated.valid(new User(validId, validName, validAge));
    }

    private static NonEmptyList<ValidationError> toNonEmptyList(final List<ValidationError> errors) {
        final ValidationError head = errors.getFirst();
        return new NonEmptyList<>(head, errors.subList(1, errors.size()));
    }

    private static <A> A unexpected(final ValidationError error) {
        throw new IllegalStateException("Validated branch unexpectedly failed: " + error.message());
    }
}
