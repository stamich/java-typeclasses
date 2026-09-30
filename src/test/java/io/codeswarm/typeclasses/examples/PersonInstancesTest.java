package io.codeswarm.typeclasses.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests for {@link PersonInstances}. */
class PersonInstancesTest {

    /** Verifies multiple renderings for the same domain value. */
    @Test
    void shouldSupportMultipleShowInstances() {
        final var person = new Person("Alice", 30);
        assertEquals("Alice", PersonInstances.COMPACT_SHOW.show(person));
        assertEquals("Alice (30)", PersonInstances.VERBOSE_SHOW.show(person));
    }

    /** Verifies that equality policy can be selected independently of Person. */
    @Test
    void shouldSupportMultipleEqualityDefinitions() {
        final var alice30 = new Person("Alice", 30);
        final var alice40 = new Person("ALICE", 40);
        assertFalse(PersonInstances.BY_ALL_FIELDS.eqv(alice30, alice40));
        assertTrue(PersonInstances.BY_NAME.eqv(alice30, alice40));
    }

    /** Verifies that ordering policy can be selected independently of Person. */
    @Test
    void shouldSupportMultipleOrderings() {
        final var alice = new Person("Alice", 35);
        final var bob = new Person("Bob", 27);
        assertEquals(bob, PersonInstances.BY_AGE.min(alice, bob));
        assertEquals(alice, PersonInstances.BY_NAME_ORDER.min(alice, bob));
    }
}
