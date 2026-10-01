package io.codeswarm.typeclasses.hkt;

import io.codeswarm.typeclasses.data.Validated;
import io.codeswarm.typeclasses.data.ValidatedK;
import io.codeswarm.typeclasses.data.ValidatedKinds;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Verifies higher-kinded widening and narrowing for {@link Validated}.
 */
class ValidatedKindsTest {

    /**
     * Ensures widening and narrowing do not change the concrete validation
     * value.
     */
    @Test
    void shouldRoundTripValidated() {
        final Validated<String, Integer> original = Validated.valid(42);
        final Kind<ValidatedK<String>, Integer> widened = ValidatedKinds.widen(original);
        final Validated<String, Integer> narrowed = ValidatedKinds.narrow(widened);

        assertSame(original, widened);
        assertSame(original, narrowed);
        assertEquals(original, narrowed);
    }
}
