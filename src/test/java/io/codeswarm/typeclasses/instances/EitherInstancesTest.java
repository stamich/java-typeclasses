package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.data.Either;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests for {@link EitherInstances}. */
class EitherInstancesTest {

    /** Verifies branch-aware equality derivation. */
    @Test
    void shouldDeriveEquality() {
        final var eq = EitherInstances.eq(StringInstances.EQ, IntegerInstances.EQ);
        assertTrue(eq.eqv(Either.left("x"), Either.left("x")));
        assertTrue(eq.eqv(Either.right(42), Either.right(42)));
        assertFalse(eq.eqv(Either.left("42"), Either.right(42)));
    }

    /** Verifies branch-aware rendering. */
    @Test
    void shouldDeriveShow() {
        final var show = EitherInstances.show(StringInstances.SHOW, IntegerInstances.SHOW);
        assertEquals("Left(error)", show.show(Either.left("error")));
        assertEquals("Right(42)", show.show(Either.right(42)));
    }
}
