package io.codeswarm.typeclasses.syntax;

import io.codeswarm.typeclasses.instances.IntegerInstances;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Unit tests for {@link OrdFunctions}. */
class OrdFunctionsTest {

    /** Verifies generic minimum and maximum algorithms. */
    @Test
    void shouldSelectMinimumAndMaximum() {
        assertEquals(10, OrdFunctions.min(10, 20, IntegerInstances.ORD));
        assertEquals(20, OrdFunctions.max(10, 20, IntegerInstances.ORD));
    }

    /** Verifies that the required instance dependency is validated. */
    @Test
    void shouldRejectNullInstance() {
        assertThrows(NullPointerException.class, () -> OrdFunctions.min(1, 2, null));
    }
}
