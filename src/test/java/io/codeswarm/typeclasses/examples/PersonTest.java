package io.codeswarm.typeclasses.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the example {@link Person} domain value.
 */
class PersonTest {

    /**
     * Verifies that valid data is retained by the record.
     */
    @Test
    void shouldCreateValidPerson() {
        final var person = new Person("Alice", 30);

        assertEquals("Alice", person.name());
        assertEquals(30, person.age());
    }

    /**
     * Verifies that invalid negative age values are rejected at the domain
     * boundary.
     */
    @Test
    void shouldRejectNegativeAge() {
        assertThrows(IllegalArgumentException.class, () -> new Person("Alice", -1));
    }

    /**
     * Verifies that a person always has a name.
     */
    @Test
    void shouldRejectNullName() {
        assertThrows(NullPointerException.class, () -> new Person(null, 30));
    }
}
