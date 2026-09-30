package io.codeswarm.typeclasses.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests demonstrating that several typeclass instances may coexist for one
 * domain type.
 */
class PersonInstancesTest {

    /**
     * Verifies the compact person representation.
     */
    @Test
    void shouldUseCompactRepresentation() {
        final var person = new Person("Alice", 30);

        assertEquals("Alice", PersonInstances.COMPACT_SHOW.show(person));
    }

    /**
     * Verifies the verbose person representation.
     */
    @Test
    void shouldUseVerboseRepresentation() {
        final var person = new Person("Alice", 30);

        assertEquals("Alice (30)", PersonInstances.VERBOSE_SHOW.show(person));
    }

    /**
     * Demonstrates that choosing behaviour is a call-site decision rather than
     * a responsibility permanently attached to the domain class.
     */
    @Test
    void shouldAllowMultipleInstancesForSameType() {
        final var person = new Person("Alice", 30);

        assertEquals("Alice", PersonInstances.COMPACT_SHOW.show(person));
        assertEquals("Alice (30)", PersonInstances.VERBOSE_SHOW.show(person));
    }
}
