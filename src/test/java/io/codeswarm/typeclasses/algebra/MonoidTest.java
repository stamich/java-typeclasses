package io.codeswarm.typeclasses.algebra;

import io.codeswarm.typeclasses.instances.IntegerInstances;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Unit tests for representative {@link Monoid} instances. */
class MonoidTest {

    /** Verifies that different monoids over Integer expose different identities. */
    @Test
    void shouldExposeOperationSpecificIdentity() {
        assertEquals(0, IntegerInstances.ADDITION.empty());
        assertEquals(1, IntegerInstances.MULTIPLICATION.empty());
    }
}
