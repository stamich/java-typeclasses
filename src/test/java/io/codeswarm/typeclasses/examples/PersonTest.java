package io.codeswarm.typeclasses.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Unit tests for {@link Person}. */
class PersonTest {

    /** Verifies construction of a valid person. */
    @Test
    void shouldCreateValidPerson() {
        final var person = new Person("Alice", 30);
        assertEquals("Alice", person.name());
        assertEquals(30, person.age());
    }

    /** Verifies name validation. */
    @Test
    void shouldRejectNullName() {
        assertThrows(NullPointerException.class, () -> new Person(null, 30));
    }

    /** Verifies age validation. */
    @Test
    void shouldRejectNegativeAge() {
        assertThrows(IllegalArgumentException.class, () -> new Person("Alice", -1));
    }
}
