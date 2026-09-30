package io.codeswarm.typeclasses.properties;

import io.codeswarm.typeclasses.algebra.Monoid;
import io.codeswarm.typeclasses.core.Eq;
import io.codeswarm.typeclasses.instances.ListInstances;
import io.codeswarm.typeclasses.laws.MonoidLaws;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Provide;
import net.jqwik.api.Property;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Property-based law verification for list concatenation.
 */
class ListInstancesProperties {

    private static final Monoid<List<Integer>> CONCATENATION = ListInstances.concatenation();
    private static final Eq<List<Integer>> LIST_EQ = List::equals;

    /**
     * Supplies small immutable integer lists to keep shrinking and diagnostics concise.
     *
     * @return arbitrary immutable lists
     */
    @Provide
    Arbitrary<List<Integer>> integerLists() {
        return Arbitraries.integers()
                .between(-1_000, 1_000)
                .list()
                .ofMaxSize(20)
                .map(List::copyOf);
    }

    /** Verifies list concatenation as a lawful monoid. */
    @Property
    void concatenationIsALawfulMonoid(
            @ForAll("integerLists") final List<Integer> first,
            @ForAll("integerLists") final List<Integer> second,
            @ForAll("integerLists") final List<Integer> third) {
        assertTrue(MonoidLaws.associative(first, second, third, CONCATENATION, LIST_EQ));
        assertTrue(MonoidLaws.leftIdentity(first, CONCATENATION, LIST_EQ));
        assertTrue(MonoidLaws.rightIdentity(first, CONCATENATION, LIST_EQ));
    }
}
