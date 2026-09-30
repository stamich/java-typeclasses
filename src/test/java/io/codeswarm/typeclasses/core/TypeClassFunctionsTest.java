package io.codeswarm.typeclasses.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for {@link TypeClassFunctions}.
 */
class TypeClassFunctionsTest {

    /**
     * Verifies that a generic algorithm delegates rendering to the supplied
     * dictionary rather than relying on the runtime type.
     */
    @Test
    void shouldUseProvidedShowInstance() {
        final Show<Integer> hexadecimal = Integer::toHexString;

        assertEquals("ff", TypeClassFunctions.show(255, hexadecimal));
    }

    /**
     * Verifies that a missing typeclass dictionary fails immediately with a
     * clear error rather than producing a later, less useful failure.
     */
    @Test
    void shouldRejectNullShowInstance() {
        assertThrows(
                NullPointerException.class,
                () -> TypeClassFunctions.show(1, null));
    }

    /**
     * Verifies that null handling remains the responsibility of an individual
     * typeclass instance.
     */
    @Test
    void shouldAllowInstanceSpecificNullPolicy() {
        final Show<String> nullAware = value -> value == null ? "<null>" : value;

        assertEquals("<null>", TypeClassFunctions.show(null, nullAware));
    }
}
