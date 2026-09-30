package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.data.NonEmptyList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests for {@link NonEmptyListInstances}. */
class NonEmptyListInstancesTest {

    /** Verifies derived equality. */
    @Test
    void shouldCompareElementsWithProvidedEq() {
        final var eq = NonEmptyListInstances.eq(IntegerInstances.EQ);
        assertTrue(eq.eqv(NonEmptyList.of(1, 2), NonEmptyList.of(1, 2)));
        assertFalse(eq.eqv(NonEmptyList.of(1, 2), NonEmptyList.of(2, 1)));
    }

    /** Verifies derived rendering. */
    @Test
    void shouldRenderElementsWithProvidedShow() {
        final var show = NonEmptyListInstances.show(IntegerInstances.SHOW);
        assertEquals("NonEmptyList(1, 2, 3)", show.show(NonEmptyList.of(1, 2, 3)));
    }

    /** Verifies concatenation semigroup. */
    @Test
    void shouldConcatenateNonEmptyLists() {
        final var semigroup = NonEmptyListInstances.<Integer>concatenation();
        assertEquals(
                NonEmptyList.of(1, 2, 3),
                semigroup.combine(NonEmptyList.of(1, 2), NonEmptyList.one(3)));
    }
}
