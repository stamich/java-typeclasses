package io.codeswarm.typeclasses.data;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Unit tests for {@link NonEmptyList}. */
class NonEmptyListTest {

    /** Verifies construction and immutable list conversion. */
    @Test
    void shouldCreateNonEmptyList() {
        final NonEmptyList<Integer> values = NonEmptyList.of(1, 2, 3);
        assertEquals(List.of(1, 2, 3), values.toList());
        assertEquals(3, values.size());
    }

    /** Verifies that the tail is defensively copied. */
    @Test
    void shouldCopyTail() {
        final List<Integer> tail = new ArrayList<>(List.of(2));
        final NonEmptyList<Integer> values = new NonEmptyList<>(1, tail);
        tail.add(3);
        assertEquals(List.of(1, 2), values.toList());
    }

    /** Verifies mapping while preserving the non-empty invariant. */
    @Test
    void shouldMapAllElements() {
        assertEquals(
                NonEmptyList.of(2, 4, 6),
                NonEmptyList.of(1, 2, 3).map(value -> value * 2));
    }

    /** Verifies ordered concatenation. */
    @Test
    void shouldConcatenate() {
        assertEquals(
                NonEmptyList.of(1, 2, 3, 4),
                NonEmptyList.of(1, 2).concat(NonEmptyList.of(3, 4)));
    }

    /** Verifies null rejection. */
    @Test
    void shouldRejectNullHead() {
        assertThrows(NullPointerException.class, () -> new NonEmptyList<>(null, List.of()));
    }
}
