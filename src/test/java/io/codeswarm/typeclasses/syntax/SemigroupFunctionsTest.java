package io.codeswarm.typeclasses.syntax;

import io.codeswarm.typeclasses.algebra.Semigroup;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Unit tests for {@link SemigroupFunctions}. */
class SemigroupFunctionsTest {

    private static final Semigroup<Integer> MAXIMUM = Integer::max;

    /** Verifies left-to-right combination of a non-empty iterable. */
    @Test
    void shouldCombineNonEmptyIterable() {
        assertEquals(9, SemigroupFunctions.combineAll(List.of(4, 9, 2), MAXIMUM));
    }

    /** Verifies that an empty iterable has no semigroup result. */
    @Test
    void shouldRejectEmptyIterable() {
        assertThrows(IllegalArgumentException.class,
                () -> SemigroupFunctions.combineAll(List.of(), MAXIMUM));
    }
}
