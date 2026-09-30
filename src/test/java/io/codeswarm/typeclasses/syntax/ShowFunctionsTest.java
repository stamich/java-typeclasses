package io.codeswarm.typeclasses.syntax;

import io.codeswarm.typeclasses.instances.IntegerInstances;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Unit tests for {@link ShowFunctions}. */
class ShowFunctionsTest {

    /** Verifies explicit dictionary passing for Show. */
    @Test
    void shouldRenderUsingProvidedInstance() {
        assertEquals("42", ShowFunctions.show(42, IntegerInstances.SHOW));
    }

    /** Verifies that the required instance dependency is validated. */
    @Test
    void shouldRejectNullInstance() {
        assertThrows(NullPointerException.class, () -> ShowFunctions.show(42, null));
    }
}
