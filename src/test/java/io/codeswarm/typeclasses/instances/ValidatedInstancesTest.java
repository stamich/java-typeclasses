package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.data.Validated;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests for {@link ValidatedInstances}. */
class ValidatedInstancesTest {

    /** Verifies equality for valid and invalid branches. */
    @Test
    void shouldDeriveEquality() {
        final var eq = ValidatedInstances.eq(StringInstances.EQ, IntegerInstances.EQ);
        assertTrue(eq.eqv(Validated.valid(42), Validated.valid(42)));
        assertTrue(eq.eqv(Validated.invalid(List.of("a", "b")), Validated.invalid(List.of("a", "b"))));
        assertFalse(eq.eqv(Validated.invalid(List.of("a", "b")), Validated.invalid(List.of("b", "a"))));
    }

    /** Verifies rendering for both validation branches. */
    @Test
    void shouldDeriveShow() {
        final var show = ValidatedInstances.show(StringInstances.SHOW, IntegerInstances.SHOW);
        assertEquals("Valid(42)", show.show(Validated.valid(42)));
        assertEquals("Invalid([bad, worse])", show.show(Validated.invalid(List.of("bad", "worse"))));
    }
}
