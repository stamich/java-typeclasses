package io.codeswarm.typeclasses.data;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        final Validated<String, Integer> invalid = Validated.invalid(List.of("first", "second"));
        assertEquals(Validated.invalid(List.of("first", "second")), invalid.map(value -> value * 2));
        assertTrue(invalid.isInvalid());
    }

    /** Verifies the invariant that invalid values contain at least one error. */
    @Test
    void shouldRejectEmptyErrorList() {
        assertThrows(IllegalArgumentException.class, () -> Validated.invalid(List.of()));
    }

    /** Verifies that the invalid branch defensively copies its error list. */
    @Test
    void shouldKeepErrorsImmutable() {
        final List<String> errors = new ArrayList<>();
        errors.add("first");
        final Invalid<String, Integer> invalid = new Invalid<>(errors);
        errors.add("second");
        assertEquals(List.of("first"), invalid.errors());
    }
}
