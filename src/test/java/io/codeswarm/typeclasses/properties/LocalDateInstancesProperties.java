package io.codeswarm.typeclasses.properties;

import io.codeswarm.typeclasses.instances.LocalDateInstances;
import io.codeswarm.typeclasses.laws.EqLaws;
import io.codeswarm.typeclasses.laws.OrdLaws;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Provide;
import net.jqwik.api.Property;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Property-based law verification for {@link LocalDateInstances}.
 */
class LocalDateInstancesProperties {

    /**
     * Supplies dates from a deliberately broad but safe epoch-day range.
     *
     * @return arbitrary local dates
     */
    @Provide
    Arbitrary<LocalDate> dates() {
        return Arbitraries.longs()
                .between(-365_000L, 365_000L)
                .map(LocalDate::ofEpochDay);
    }

    /** Verifies equality laws for dates. */
    @Property
    void equalityIsLawful(
            @ForAll("dates") final LocalDate first,
            @ForAll("dates") final LocalDate second,
            @ForAll("dates") final LocalDate third) {
        assertTrue(EqLaws.reflexive(first, LocalDateInstances.EQ));
        assertTrue(EqLaws.symmetric(first, second, LocalDateInstances.EQ));
        assertTrue(EqLaws.transitive(first, second, third, LocalDateInstances.EQ));
    }

    /** Verifies chronological ordering laws for dates. */
    @Property
    void orderingIsLawful(
            @ForAll("dates") final LocalDate first,
            @ForAll("dates") final LocalDate second,
            @ForAll("dates") final LocalDate third) {
        assertTrue(OrdLaws.reflexive(first, LocalDateInstances.ORD));
        assertTrue(OrdLaws.signAntisymmetric(first, second, LocalDateInstances.ORD));
        assertTrue(OrdLaws.transitive(first, second, third, LocalDateInstances.ORD));
        assertTrue(OrdLaws.consistentWithEquality(first, second, LocalDateInstances.ORD));
    }
}
