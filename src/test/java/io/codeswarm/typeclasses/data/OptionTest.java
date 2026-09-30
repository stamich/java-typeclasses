package io.codeswarm.typeclasses.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests for {@link Option}. */
class OptionTest {

    /** Verifies mapping of a present value. */
    @Test
    void shouldMapSome() {
        assertEquals(Option.some(42), Option.some(21).map(value -> value * 2));
    }

    /** Verifies that mapping preserves absence. */
    @Test
    void shouldPreserveNoneWhenMapping() {
        assertEquals(Option.none(), Option.<Integer>none().map(value -> value * 2));
    }

    /** Verifies nullable conversion. */
    @Test
    void shouldCreateOptionFromNullableValue() {
        assertTrue(Option.fromNullable(null).isEmpty());
        assertEquals(Option.some("value"), Option.fromNullable("value"));
    }

    /** Verifies branch elimination and fallback handling. */
    @Test
    void shouldFoldAndUseFallback() {
        assertEquals("42", Option.some(42).fold(() -> "none", String::valueOf));
        assertEquals(7, Option.<Integer>none().getOrElse(() -> 7));
        assertFalse(Option.some(1).isEmpty());
    }
    /** Verifies conditional lazy construction. */
    @Test
    void shouldCreateOptionWhenConditionIsTrue() {
        assertEquals(Option.some(42), Option.when(true, () -> 42));
        assertEquals(Option.none(), Option.when(false, () -> 42));
    }

}
