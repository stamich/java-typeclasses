package io.codeswarm.typeclasses.instances;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Unit tests for {@link ListInstances}. */
class ListInstancesTest {

    /** Verifies concatenation and that input lists are not mutated. */
    @Test
    void shouldConcatenateWithoutMutatingInputs() {
        final var left = new ArrayList<>(List.of(1, 2));
        final var right = new ArrayList<>(List.of(3, 4));
        final var result = ListInstances.<Integer>concatenation().combine(left, right);
        assertEquals(List.of(1, 2, 3, 4), result);
        assertEquals(List.of(1, 2), left);
        assertEquals(List.of(3, 4), right);
        assertThrows(UnsupportedOperationException.class, () -> result.add(5));
    }

    /** Verifies that the empty immutable list is the identity. */
    @Test
    void shouldProvideEmptyListIdentity() {
        assertEquals(List.of(), ListInstances.<Integer>concatenation().empty());
    }
}
