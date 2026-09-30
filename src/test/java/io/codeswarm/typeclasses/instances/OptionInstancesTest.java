package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.data.Option;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests for {@link OptionInstances}. */
class OptionInstancesTest {

    /** Verifies derived equality for present and absent options. */
    @Test
    void shouldDeriveEquality() {
        final var eq = OptionInstances.eq(IntegerInstances.EQ);
        assertTrue(eq.eqv(Option.some(1), Option.some(1)));
        assertTrue(eq.eqv(Option.none(), Option.none()));
        assertFalse(eq.eqv(Option.some(1), Option.none()));
    }

    /** Verifies derived rendering. */
    @Test
    void shouldDeriveShow() {
        final var show = OptionInstances.show(IntegerInstances.SHOW);
        assertEquals("Some(42)", show.show(Option.some(42)));
        assertEquals("None", show.show(Option.none()));
    }
}
