package io.codeswarm.typeclasses.syntax;

import io.codeswarm.typeclasses.instances.IntegerInstances;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Unit tests for {@link MonoidFunctions}. */
class MonoidFunctionsTest {

    /** Verifies that changing only the monoid changes the generic fold semantics. */
    @Test
    void shouldUseSelectedMonoid() {
        final var values = List.of(1, 2, 3, 4);
        assertEquals(10, MonoidFunctions.combineAll(values, IntegerInstances.ADDITION));
        assertEquals(24, MonoidFunctions.combineAll(values, IntegerInstances.MULTIPLICATION));
    }

    /** Verifies that an empty iterable evaluates to the selected identity. */
    @Test
    void shouldReturnIdentityForEmptyIterable() {
        assertEquals(0, MonoidFunctions.combineAll(List.of(), IntegerInstances.ADDITION));
        assertEquals(1, MonoidFunctions.combineAll(List.of(), IntegerInstances.MULTIPLICATION));
    }
}
