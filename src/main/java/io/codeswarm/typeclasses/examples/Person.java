package io.codeswarm.typeclasses.examples;

import java.util.Objects;

/**
 * Immutable example domain value used to demonstrate that a model does not
 * need to implement its typeclasses.
 *
 * @param name the person's non-null name
 * @param age the person's age; must not be negative
 */
public record Person(String name, int age) {

    /**
     * Creates a validated person value.
     *
     * @throws NullPointerException if {@code name} is {@code null}
     * @throws IllegalArgumentException if {@code age} is negative
     */
    public Person {
        Objects.requireNonNull(name, "name must not be null");
        if (age < 0) {
            throw new IllegalArgumentException("age must not be negative");
        }
    }
}
