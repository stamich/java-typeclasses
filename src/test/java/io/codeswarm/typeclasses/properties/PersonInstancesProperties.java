package io.codeswarm.typeclasses.properties;

import io.codeswarm.typeclasses.examples.Person;
import io.codeswarm.typeclasses.examples.PersonInstances;
import io.codeswarm.typeclasses.laws.EqLaws;
import io.codeswarm.typeclasses.laws.OrdLaws;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Provide;
import net.jqwik.api.Property;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Property-based law verification for the example {@link Person} instances.
 */
class PersonInstancesProperties {

    /**
     * Supplies valid example people with compact names and realistic ages.
     *
     * @return arbitrary people
     */
    @Provide
    Arbitrary<Person> people() {
        final Arbitrary<String> names = Arbitraries.strings()
                .alpha()
                .ofMinLength(1)
                .ofMaxLength(20);
        final Arbitrary<Integer> ages = Arbitraries.integers().between(0, 130);
        return Combinators.combine(names, ages).as(Person::new);
    }

    /** Verifies equality based on all fields. */
    @Property
    void allFieldsEqualityIsLawful(
            @ForAll("people") final Person first,
            @ForAll("people") final Person second,
            @ForAll("people") final Person third) {
        assertTrue(EqLaws.reflexive(first, PersonInstances.EQ_ALL_FIELDS));
        assertTrue(EqLaws.symmetric(first, second, PersonInstances.EQ_ALL_FIELDS));
        assertTrue(EqLaws.transitive(first, second, third, PersonInstances.EQ_ALL_FIELDS));
    }

    /** Verifies case-insensitive equality based on name. */
    @Property
    void nameEqualityIsLawful(
            @ForAll("people") final Person first,
            @ForAll("people") final Person second,
            @ForAll("people") final Person third) {
        assertTrue(EqLaws.reflexive(first, PersonInstances.EQ_NAME));
        assertTrue(EqLaws.symmetric(first, second, PersonInstances.EQ_NAME));
        assertTrue(EqLaws.transitive(first, second, third, PersonInstances.EQ_NAME));
    }

    /** Verifies ordering by age. */
    @Property
    void ageOrderingIsLawful(
            @ForAll("people") final Person first,
            @ForAll("people") final Person second,
            @ForAll("people") final Person third) {
        assertTrue(OrdLaws.reflexive(first, PersonInstances.ORD_AGE));
        assertTrue(OrdLaws.signAntisymmetric(first, second, PersonInstances.ORD_AGE));
        assertTrue(OrdLaws.transitive(first, second, third, PersonInstances.ORD_AGE));
        assertTrue(OrdLaws.consistentWithEquality(first, second, PersonInstances.ORD_AGE));
    }

    /** Verifies case-insensitive ordering by name. */
    @Property
    void nameOrderingIsLawful(
            @ForAll("people") final Person first,
            @ForAll("people") final Person second,
            @ForAll("people") final Person third) {
        assertTrue(OrdLaws.reflexive(first, PersonInstances.ORD_NAME));
        assertTrue(OrdLaws.signAntisymmetric(first, second, PersonInstances.ORD_NAME));
        assertTrue(OrdLaws.transitive(first, second, third, PersonInstances.ORD_NAME));
        assertTrue(OrdLaws.consistentWithEquality(first, second, PersonInstances.ORD_NAME));
    }
}
