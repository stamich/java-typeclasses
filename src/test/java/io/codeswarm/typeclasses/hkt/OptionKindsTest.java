package io.codeswarm.typeclasses.hkt;

import io.codeswarm.typeclasses.data.Option;
import io.codeswarm.typeclasses.data.OptionK;
import io.codeswarm.typeclasses.data.OptionKinds;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Verifies higher-kinded widening and narrowing for {@link Option}.
 */
class OptionKindsTest {

    /**
     * Ensures a round-trip preserves the concrete option value and identity.
     */
    @Test
    void shouldRoundTripOption() {
        final Option<Integer> original = Option.some(42);
        final Kind<OptionK, Integer> widened = OptionKinds.widen(original);
        final Option<Integer> narrowed = OptionKinds.narrow(widened);

        assertSame(original, widened);
        assertSame(original, narrowed);
        assertEquals(original, narrowed);
    }
}
