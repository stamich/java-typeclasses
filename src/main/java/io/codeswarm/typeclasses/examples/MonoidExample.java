package io.codeswarm.typeclasses.examples;

import io.codeswarm.typeclasses.instances.IntegerInstances;
import io.codeswarm.typeclasses.syntax.MonoidFunctions;

import java.util.List;

/** Demonstrates selecting different monoids for the same value type. */
public final class MonoidExample {

    private MonoidExample() {
        throw new AssertionError("Example class must not be instantiated");
    }

    /**
     * Runs the monoid example.
     *
     */
    static void main() {
        final var numbers = List.of(1, 2, 3, 4);
        System.out.println(MonoidFunctions.combineAll(numbers, IntegerInstances.ADDITION));
        System.out.println(MonoidFunctions.combineAll(numbers, IntegerInstances.MULTIPLICATION));
    }
}
