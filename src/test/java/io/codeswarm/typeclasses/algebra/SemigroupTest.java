package io.codeswarm.typeclasses.algebra;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Unit tests for a representative {@link Semigroup} instance. */
class SemigroupTest {

    /** Verifies that a semigroup can model a standalone associative combination. */
    @Test
    void shouldCombineValues() {
        final Semigroup<Integer> maximum = Integer::max;
        assertEquals(20, maximum.combine(10, 20));
    }
}
