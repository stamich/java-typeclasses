package io.codeswarm.typeclasses.examples;

import io.codeswarm.typeclasses.algebra.Semigroup;
import io.codeswarm.typeclasses.syntax.SemigroupFunctions;

import java.util.List;

/** Demonstrates a semigroup and its non-empty fold semantics. */
public final class SemigroupExample {

    private SemigroupExample() {
        throw new AssertionError("Example class must not be instantiated");
    }

    /**
     * Runs the semigroup example.
     *
     * @param args command-line arguments; ignored
     */
    public static void main(final String[] args) {
        final Semigroup<Integer> maximum = Integer::max;
        System.out.println(SemigroupFunctions.combineAll(List.of(4, 9, 2, 7), maximum));
    }
}
