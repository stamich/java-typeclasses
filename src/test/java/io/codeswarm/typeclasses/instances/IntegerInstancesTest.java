package io.codeswarm.typeclasses.instances;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests for {@link IntegerInstances}. */
class IntegerInstancesTest {

    /** Verifies show, equality, ordering, addition, and multiplication instances. */
    @Test
    void shouldProvideIntegerInstances() {
        assertEquals("42", IntegerInstances.SHOW.show(42));
        assertTrue(IntegerInstances.EQ.eqv(42, 42));
        assertTrue(IntegerInstances.ORD.lessThan(1, 2));
        assertEquals(3, IntegerInstances.ADDITION.combine(1, 2));
        assertEquals(6, IntegerInstances.MULTIPLICATION.combine(2, 3));
    }
}
