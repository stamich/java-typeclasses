package io.codeswarm.typeclasses.properties;

import io.codeswarm.typeclasses.data.NonEmptyList;
import io.codeswarm.typeclasses.instances.IntegerInstances;
import io.codeswarm.typeclasses.instances.NonEmptyListInstances;
import io.codeswarm.typeclasses.laws.EqLaws;
import io.codeswarm.typeclasses.laws.SemigroupLaws;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Property-based laws for {@link NonEmptyList} typeclass instances. */
class NonEmptyListInstancesProperties {

    /** Verifies associativity of non-empty-list concatenation. */
    @Property
    void concatenationShouldBeAssociative(
            @ForAll final int first,
            @ForAll final int second,
            @ForAll final int third) {
        final var semigroup = NonEmptyListInstances.<Integer>concatenation();
        final var eq = NonEmptyListInstances.eq(IntegerInstances.EQ);
        assertTrue(SemigroupLaws.associative(
                NonEmptyList.one(first),
                NonEmptyList.one(second),
                NonEmptyList.one(third),
                semigroup,
                eq));
    }

    /** Verifies reflexivity of derived non-empty-list equality. */
    @Property
    void equalityShouldBeReflexive(@ForAll final int value) {
        final var list = NonEmptyList.of(value, value + 1);
        assertTrue(EqLaws.reflexive(list, NonEmptyListInstances.eq(IntegerInstances.EQ)));
    }
}
