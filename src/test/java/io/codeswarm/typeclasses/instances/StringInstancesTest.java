package io.codeswarm.typeclasses.instances;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests for {@link StringInstances}. */
class StringInstancesTest {

    /** Verifies string rendering, equality, ordering, and concatenation. */
    @Test
    void shouldProvideStringInstances() {
        assertEquals("abc", StringInstances.SHOW.show("abc"));
        assertTrue(StringInstances.EQ.eqv("abc", "abc"));
        assertTrue(StringInstances.LEXICOGRAPHIC_ORD.lessThan("a", "b"));
        assertTrue(StringInstances.CASE_INSENSITIVE_ORD.eqv("ABC", "abc"));
        assertEquals("ab", StringInstances.CONCATENATION.combine("a", "b"));
        assertEquals("", StringInstances.CONCATENATION.empty());
    }
}
