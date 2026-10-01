package io.codeswarm.typeclasses.examples;

import io.codeswarm.typeclasses.data.Either;
import io.codeswarm.typeclasses.data.Option;
import io.codeswarm.typeclasses.data.Validated;
import io.codeswarm.typeclasses.instances.EitherInstances;
import io.codeswarm.typeclasses.instances.IntegerInstances;
import io.codeswarm.typeclasses.instances.OptionInstances;
import io.codeswarm.typeclasses.instances.StringInstances;
import io.codeswarm.typeclasses.instances.ValidatedInstances;

/**
 * Demonstrates the algebraic data types introduced in milestone 0.4.x and their
 * compositional typeclass instances.
 */
public final class AdtExample {

    private AdtExample() {
        throw new AssertionError("Example class must not be instantiated");
    }

    /**
     * Runs a small demonstration of Option, Either and Validated.
     *
     * @param args ignored command-line arguments
     */
    public static void main(final String[] args) {
        final Option<Integer> option = Option.some(21).map(value -> value * 2);
        final Either<String, Integer> either = Either.<String, Integer>right(21).map(value -> value * 2);
        final Validated<String, Integer> validated = Validated.invalid("name is empty", "age is negative");

        System.out.println(OptionInstances.show(IntegerInstances.SHOW).show(option));
        System.out.println(EitherInstances.show(StringInstances.SHOW, IntegerInstances.SHOW).show(either));
        System.out.println(ValidatedInstances.show(StringInstances.SHOW, IntegerInstances.SHOW).show(validated));
    }
}
