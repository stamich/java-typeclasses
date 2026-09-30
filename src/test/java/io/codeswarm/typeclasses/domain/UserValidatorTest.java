package io.codeswarm.typeclasses.domain;

import io.codeswarm.typeclasses.data.NonEmptyList;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tests accumulated domain validation. */
class UserValidatorTest {

    /** Verifies successful creation of a fully validated user. */
    @Test
    void shouldCreateValidUser() {
        final var result = UserValidator.validate("user-1", "Alice", 42);
        assertTrue(result.isValid());
        assertEquals("user-1", result.fold(errors -> "bad", user -> user.id().value()));
    }

    /** Verifies accumulation of independent validation failures. */
    @Test
    void shouldAccumulateAllErrors() {
        final var result = UserValidator.validate("", "", -1);
        assertTrue(result.isInvalid());
        assertEquals(3, Optional.ofNullable(result.fold(NonEmptyList::size, ignored -> 0)).get());
    }
}
