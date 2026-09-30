package io.codeswarm.typeclasses.instances;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the standard typeclass instances shipped in milestone 0.1.1.
 */
class StandardInstancesTest {

    /**
     * Verifies standard decimal integer rendering.
     */
    @Test
    void shouldShowInteger() {
        assertEquals("42", IntegerInstances.SHOW.show(42));
    }

    /**
     * Verifies identity-like string rendering.
     */
    @Test
    void shouldShowString() {
        assertEquals("typeclasses", StringInstances.SHOW.show("typeclasses"));
    }

    /**
     * Verifies that behaviour can be supplied for an unmodified JDK type.
     */
    @Test
    void shouldShowLocalDateInIsoFormat() {
        assertEquals(
                "2026-09-30",
                LocalDateInstances.ISO_SHOW.show(LocalDate.of(2026, 9, 30)));
    }
}
