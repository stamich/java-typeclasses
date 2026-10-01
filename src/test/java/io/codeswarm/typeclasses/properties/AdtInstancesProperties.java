package io.codeswarm.typeclasses.properties;

import io.codeswarm.typeclasses.data.Either;
import io.codeswarm.typeclasses.data.Option;
import io.codeswarm.typeclasses.data.Validated;
import io.codeswarm.typeclasses.instances.EitherInstances;
import io.codeswarm.typeclasses.instances.IntegerInstances;
import io.codeswarm.typeclasses.instances.OptionInstances;
import io.codeswarm.typeclasses.instances.StringInstances;
import io.codeswarm.typeclasses.instances.ValidatedInstances;
import io.codeswarm.typeclasses.laws.EqLaws;
import java.util.List;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Property-based verification of composed {@code Eq} instances for the ADTs. */
class AdtInstancesProperties {

    /** Verifies the equality laws for representative Option values. */
    @Property
    void optionEqualityIsLawful(@ForAll final int first, @ForAll final int second, @ForAll final int third) {
        final var eq = OptionInstances.eq(IntegerInstances.EQ);
        final Option<Integer> a = first % 3 == 0 ? Option.none() : Option.some(first);
        final Option<Integer> b = second % 3 == 0 ? Option.none() : Option.some(second);
        final Option<Integer> c = third % 3 == 0 ? Option.none() : Option.some(third);
        assertTrue(EqLaws.reflexive(a, eq));
        assertTrue(EqLaws.symmetric(a, b, eq));
        assertTrue(EqLaws.transitive(a, b, c, eq));
    }

    /** Verifies the equality laws for representative Either values. */
    @Property
    void eitherEqualityIsLawful(@ForAll final int first, @ForAll final int second, @ForAll final int third) {
        final var eq = EitherInstances.eq(StringInstances.EQ, IntegerInstances.EQ);
        final Either<String, Integer> a = either(first);
        final Either<String, Integer> b = either(second);
        final Either<String, Integer> c = either(third);
        assertTrue(EqLaws.reflexive(a, eq));
        assertTrue(EqLaws.symmetric(a, b, eq));
        assertTrue(EqLaws.transitive(a, b, c, eq));
    }

    /** Verifies the equality laws for representative Validated values. */
    @Property
    void validatedEqualityIsLawful(@ForAll final int first, @ForAll final int second, @ForAll final int third) {
        final var eq = ValidatedInstances.eq(StringInstances.EQ, IntegerInstances.EQ);
        final Validated<String, Integer> a = validated(first);
        final Validated<String, Integer> b = validated(second);
        final Validated<String, Integer> c = validated(third);
        assertTrue(EqLaws.reflexive(a, eq));
        assertTrue(EqLaws.symmetric(a, b, eq));
        assertTrue(EqLaws.transitive(a, b, c, eq));
    }

    private static Either<String, Integer> either(final int value) {
        return value % 2 == 0 ? Either.right(value) : Either.left(Integer.toString(value));
    }

    private static Validated<String, Integer> validated(final int value) {
        return value % 2 == 0
                ? Validated.valid(value)
                : Validated.invalid("error-" + value);
    }
}
