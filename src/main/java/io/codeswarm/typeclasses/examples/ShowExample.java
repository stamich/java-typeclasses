package io.codeswarm.typeclasses.examples;

import io.codeswarm.typeclasses.core.TypeClassFunctions;
import io.codeswarm.typeclasses.instances.IntegerInstances;
import io.codeswarm.typeclasses.instances.LocalDateInstances;

import java.time.LocalDate;

/**
 * Small executable demonstration of explicit typeclass instance passing.
 */
public final class ShowExample {

    private ShowExample() {
        throw new AssertionError("Example class must not be instantiated");
    }

    /**
     * Runs the milestone 0.1.1 example.
     *
     * @param args command-line arguments; ignored
     */
    public static void main(final String[] args) {
        final var person = new Person("Alice", 30);

        System.out.println(TypeClassFunctions.show(person, PersonInstances.COMPACT_SHOW));
        System.out.println(TypeClassFunctions.show(person, PersonInstances.VERBOSE_SHOW));
        System.out.println(TypeClassFunctions.show(42, IntegerInstances.SHOW));
        System.out.println(TypeClassFunctions.show(
                LocalDate.of(2026, 9, 30),
                LocalDateInstances.ISO_SHOW));
    }
}
