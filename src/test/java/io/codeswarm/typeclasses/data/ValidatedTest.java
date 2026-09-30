package io.codeswarm.typeclasses.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests for {@link Validated}. */
class ValidatedTest {

    /** Verifies mapping of a successful validation. */
    @Test
    void shouldMapValidValue() {
        assertEquals(Validated.valid(42), Validated.<String, Integer>valid(21).map(value -> value * 2));
    }

    /** Verifies that mapping preserves accumulated errors. */
    @Test
    void shouldPreserveInvalidErrors() {
        final Validated<String, Integer> invalid = Validated.invalid("first", "second");
        assertEquals(Validated.invalid("first", "second"), invalid.map(value -> value * 2));
        assertTrue(invalid.isInvalid());
    }

    /** Verifies single-error convenience construction. */
    @Test
    void shouldCreateSingleErrorInvalid() {
        final Invalid<String, Integer> invalid = (Invalid<String, Integer>) Validated.<String, Integer>invalid("error");
        assertEquals(NonEmptyList.one("error"), invalid.errors());
    }

    /** Verifies that an invalid branch always contains at least one error structurally. */
    @Test
    void shouldExposeNonEmptyErrors() {
        final Invalid<String, Integer> invalid = new Invalid<>(NonEmptyList.of("first", "second"));
        assertEquals(2, invalid.errors().size());
    }
}
