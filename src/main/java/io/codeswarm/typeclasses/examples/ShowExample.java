package io.codeswarm.typeclasses.examples;

import io.codeswarm.typeclasses.instances.IntegerInstances;
import io.codeswarm.typeclasses.instances.LocalDateInstances;
import io.codeswarm.typeclasses.syntax.ShowFunctions;

import java.time.LocalDate;

/** Demonstrates explicit use of {@code Show} instances. */
public final class ShowExample {

    private ShowExample() {
        throw new AssertionError("Example class must not be instantiated");
    }

    /**
     * Runs the show example.
     *
     * @param args command-line arguments; ignored
     */
    public static void main(final String[] args) {
        final var person = new Person("Alice", 30);
        System.out.println(ShowFunctions.show(person, PersonInstances.SHOW_COMPACT));
        System.out.println(ShowFunctions.show(person, PersonInstances.SHOW_VERBOSE));
        System.out.println(ShowFunctions.show(42, IntegerInstances.SHOW));
        System.out.println(ShowFunctions.show(
                LocalDate.of(2026, 9, 30), LocalDateInstances.ISO_SHOW));
    }
}
