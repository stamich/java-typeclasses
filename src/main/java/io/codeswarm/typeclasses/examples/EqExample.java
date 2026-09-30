package io.codeswarm.typeclasses.examples;

import io.codeswarm.typeclasses.syntax.EqFunctions;

/** Demonstrates multiple equality definitions for the same domain type. */
public final class EqExample {

    private EqExample() {
        throw new AssertionError("Example class must not be instantiated");
    }

    /**
     * Runs the equality example.
     *
     */
    static void main() {
        final var alice30 = new Person("Alice", 30);
        final var alice40 = new Person("ALICE", 40);
        System.out.println(EqFunctions.equal(alice30, alice40, PersonInstances.EQ_ALL_FIELDS));
        System.out.println(EqFunctions.equal(alice30, alice40, PersonInstances.EQ_NAME));
    }
}
