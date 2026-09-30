package io.codeswarm.typeclasses.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests for {@link Ord} default operations. */
class OrdTest {

    private static final Ord<Integer> ORD = Integer::compare;

    /** Verifies equality, comparison predicates, minimum, and maximum helpers. */
    @Test
    void shouldExposeOrderingOperations() {
        assertTrue(ORD.eqv(10, 10));
        assertTrue(ORD.lessThan(10, 20));
        assertTrue(ORD.greaterThan(20, 10));
        assertFalse(ORD.lessThan(20, 10));
        assertEquals(10, ORD.min(10, 20));
        assertEquals(20, ORD.max(10, 20));
    }
}
