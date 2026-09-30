package io.codeswarm.typeclasses.properties;

import io.codeswarm.typeclasses.instances.StringInstances;
import io.codeswarm.typeclasses.laws.EqLaws;
import io.codeswarm.typeclasses.laws.MonoidLaws;
import io.codeswarm.typeclasses.laws.OrdLaws;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Property-based law verification for {@link StringInstances}.
 */
class StringInstancesProperties {

    /** Verifies the standard string equality laws. */
    @Property
    void equalityIsLawful(
            @ForAll final String first,
            @ForAll final String second,
            @ForAll final String third) {
        assertTrue(EqLaws.reflexive(first, StringInstances.EQ));
        assertTrue(EqLaws.symmetric(first, second, StringInstances.EQ));
        assertTrue(EqLaws.transitive(first, second, third, StringInstances.EQ));
    }

    /** Verifies the case-sensitive lexicographic ordering laws. */
    @Property
    void lexicographicOrderingIsLawful(
            @ForAll final String first,
            @ForAll final String second,
            @ForAll final String third) {
        assertTrue(OrdLaws.reflexive(first, StringInstances.LEXICOGRAPHIC_ORD));
        assertTrue(OrdLaws.signAntisymmetric(first, second, StringInstances.LEXICOGRAPHIC_ORD));
        assertTrue(OrdLaws.transitive(first, second, third, StringInstances.LEXICOGRAPHIC_ORD));
        assertTrue(OrdLaws.consistentWithEquality(first, second, StringInstances.LEXICOGRAPHIC_ORD));
    }

    /** Verifies the case-insensitive lexicographic ordering laws. */
    @Property
    void caseInsensitiveOrderingIsLawful(
            @ForAll final String first,
            @ForAll final String second,
            @ForAll final String third) {
        assertTrue(OrdLaws.reflexive(first, StringInstances.CASE_INSENSITIVE_ORD));
        assertTrue(OrdLaws.signAntisymmetric(first, second, StringInstances.CASE_INSENSITIVE_ORD));
        assertTrue(OrdLaws.transitive(first, second, third, StringInstances.CASE_INSENSITIVE_ORD));
        assertTrue(OrdLaws.consistentWithEquality(first, second, StringInstances.CASE_INSENSITIVE_ORD));
    }

    /** Verifies string concatenation as a lawful monoid. */
    @Property
    void concatenationIsALawfulMonoid(
            @ForAll final String first,
            @ForAll final String second,
            @ForAll final String third) {
        assertTrue(MonoidLaws.associative(
                first, second, third, StringInstances.CONCATENATION, StringInstances.EQ));
        assertTrue(MonoidLaws.leftIdentity(first, StringInstances.CONCATENATION, StringInstances.EQ));
        assertTrue(MonoidLaws.rightIdentity(first, StringInstances.CONCATENATION, StringInstances.EQ));
    }
}
