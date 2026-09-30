package io.codeswarm.typeclasses.laws;

import io.codeswarm.typeclasses.algebra.Semigroup;
import io.codeswarm.typeclasses.instances.IntegerInstances;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Demonstrates that implementing a typeclass interface does not automatically
 * make an instance lawful.
 */
class UnlawfulInstancesTest {

    /**
     * Shows that integer subtraction is not associative and therefore cannot be
     * a lawful {@link Semigroup} operation.
     */
    @Test
    void subtractionShouldFailAssociativity() {
        final Semigroup<Integer> subtraction = (left, right) -> left - right;

        assertFalse(SemigroupLaws.associative(
                10,
                3,
                2,
                subtraction,
                IntegerInstances.EQ));
    }
}
