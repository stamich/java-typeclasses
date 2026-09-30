package io.codeswarm.typeclasses.syntax;

import io.codeswarm.typeclasses.instances.IntegerInstances;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests for {@link EqFunctions}. */
class EqFunctionsTest {

    /** Verifies explicit dictionary passing for Eq. */
    @Test
    void shouldCompareUsingProvidedInstance() {
        assertTrue(EqFunctions.equal(42, 42, IntegerInstances.EQ));
    }

    /** Verifies that the required instance dependency is validated. */
    @Test
    void shouldRejectNullInstance() {
        assertThrows(NullPointerException.class, () -> EqFunctions.equal(1, 1, null));
    }
}
