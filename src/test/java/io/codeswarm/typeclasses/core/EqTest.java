package io.codeswarm.typeclasses.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests for {@link Eq}. */
class EqTest {

    /** Verifies the primary equality operation and its negated convenience method. */
    @Test
    void shouldCompareValuesAndNegateEquality() {
        final Eq<Integer> eq = Integer::equals;
        assertTrue(eq.eqv(42, 42));
        assertFalse(eq.eqv(42, 43));
        assertTrue(eq.neqv(42, 43));
        assertFalse(eq.neqv(42, 42));
    }
}
