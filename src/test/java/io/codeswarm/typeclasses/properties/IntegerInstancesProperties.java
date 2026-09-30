package io.codeswarm.typeclasses.properties;

import io.codeswarm.typeclasses.instances.IntegerInstances;
import io.codeswarm.typeclasses.laws.EqLaws;
import io.codeswarm.typeclasses.laws.MonoidLaws;
import io.codeswarm.typeclasses.laws.OrdLaws;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Property-based law verification for {@link IntegerInstances}.
 */
class IntegerInstancesProperties {

    /** Verifies the reflexivity law of integer equality. */
    @Property
    void equalityIsReflexive(@ForAll final int value) {
        assertTrue(EqLaws.reflexive(value, IntegerInstances.EQ));
    }

    /** Verifies the symmetry law of integer equality. */
    @Property
    void equalityIsSymmetric(@ForAll final int left, @ForAll final int right) {
        assertTrue(EqLaws.symmetric(left, right, IntegerInstances.EQ));
    }

    /** Verifies the transitivity law of integer equality. */
    @Property
    void equalityIsTransitive(
            @ForAll final int first,
            @ForAll final int second,
            @ForAll final int third) {
        assertTrue(EqLaws.transitive(first, second, third, IntegerInstances.EQ));
    }

    /** Verifies the integer ordering laws. */
    @Property
    void orderingIsLawful(
            @ForAll final int first,
            @ForAll final int second,
            @ForAll final int third) {
        assertTrue(OrdLaws.reflexive(first, IntegerInstances.ORD));
        assertTrue(OrdLaws.signAntisymmetric(first, second, IntegerInstances.ORD));
        assertTrue(OrdLaws.transitive(first, second, third, IntegerInstances.ORD));
        assertTrue(OrdLaws.consistentWithEquality(first, second, IntegerInstances.ORD));
    }

    /** Verifies associativity and identity for integer addition. */
    @Property
    void additionIsALawfulMonoid(
            @ForAll final int first,
            @ForAll final int second,
            @ForAll final int third) {
        assertTrue(MonoidLaws.associative(
                first, second, third, IntegerInstances.ADDITION, IntegerInstances.EQ));
        assertTrue(MonoidLaws.leftIdentity(first, IntegerInstances.ADDITION, IntegerInstances.EQ));
        assertTrue(MonoidLaws.rightIdentity(first, IntegerInstances.ADDITION, IntegerInstances.EQ));
    }

    /** Verifies associativity and identity for integer multiplication. */
    @Property
    void multiplicationIsALawfulMonoid(
            @ForAll final int first,
            @ForAll final int second,
            @ForAll final int third) {
        assertTrue(MonoidLaws.associative(
                first, second, third, IntegerInstances.MULTIPLICATION, IntegerInstances.EQ));
        assertTrue(MonoidLaws.leftIdentity(first, IntegerInstances.MULTIPLICATION, IntegerInstances.EQ));
        assertTrue(MonoidLaws.rightIdentity(first, IntegerInstances.MULTIPLICATION, IntegerInstances.EQ));
    }
}
