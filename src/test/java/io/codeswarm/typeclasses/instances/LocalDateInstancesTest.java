package io.codeswarm.typeclasses.instances;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests for {@link LocalDateInstances}. */
class LocalDateInstancesTest {

    /** Verifies rendering, equality, and chronological ordering. */
    @Test
    void shouldProvideLocalDateInstances() {
        final var first = LocalDate.of(2026, 9, 30);
        final var second = LocalDate.of(2026, 10, 1);
        assertEquals("2026-09-30", LocalDateInstances.ISO_SHOW.show(first));
        assertTrue(LocalDateInstances.EQ.eqv(first, first));
        assertTrue(LocalDateInstances.ORD.lessThan(first, second));
    }
}
