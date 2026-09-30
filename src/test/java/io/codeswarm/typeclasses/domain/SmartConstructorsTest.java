package io.codeswarm.typeclasses.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tests domain smart constructors and invariant-safe value objects. */
class SmartConstructorsTest {

    /** Verifies UserId normalization and blank rejection. */
    @Test
    void shouldValidateUserId() {
        assertEquals("user-1", UserId.from("  user-1  ").fold(_ -> "bad", UserId::value));
        assertTrue(UserId.from(" ").isLeft());
    }

    /** Verifies person-name invariants. */
    @Test
    void shouldValidatePersonName() {
        assertEquals("Alice", PersonName.from(" Alice ").fold(_ -> "bad", PersonName::value));
        assertTrue(PersonName.from("").isLeft());
        assertTrue(PersonName.from("x".repeat(PersonName.MAX_LENGTH + 1)).isLeft());
    }

    /** Verifies age range validation. */
    @Test
    void shouldValidateAge() {
        assertEquals(42, Age.from(42).fold(_ -> -1, Age::value));
        assertTrue(Age.from(-1).isLeft());
        assertTrue(Age.from(Age.MAX_VALUE + 1).isLeft());
    }
}
