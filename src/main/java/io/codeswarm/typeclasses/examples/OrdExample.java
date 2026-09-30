package io.codeswarm.typeclasses.examples;

import io.codeswarm.typeclasses.syntax.OrdFunctions;

/** Demonstrates selecting an ordering independently of a domain type. */
public final class OrdExample {

    private OrdExample() {
        throw new AssertionError("Example class must not be instantiated");
    }

    /**
     * Runs the ordering example.
     *
     * @param args command-line arguments; ignored
     */
    public static void main(final String[] args) {
        final var alice = new Person("Alice", 35);
        final var bob = new Person("Bob", 27);
        System.out.println(OrdFunctions.min(alice, bob, PersonInstances.ORD_AGE));
        System.out.println(OrdFunctions.min(alice, bob, PersonInstances.ORD_NAME));
    }
}
