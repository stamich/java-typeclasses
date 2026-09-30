package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.algebra.Semigroup;
import io.codeswarm.typeclasses.core.Eq;
import io.codeswarm.typeclasses.core.Show;
import io.codeswarm.typeclasses.data.NonEmptyList;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Typeclass instances for {@link NonEmptyList} derived from element instances.
 */
public final class NonEmptyListInstances {

    private NonEmptyListInstances() {
        throw new AssertionError("Instances holder must not be instantiated");
    }

    /**
     * Derives equality for non-empty lists from equality for their elements.
     *
     * @param eqA element equality
     * @param <A> element type
     * @return list equality preserving order
     */
    public static <A> Eq<NonEmptyList<A>> eq(final Eq<? super A> eqA) {
        Objects.requireNonNull(eqA, "eqA");
        return (left, right) -> {
            if (left.size() != right.size()) {
                return false;
            }
            final Iterator<A> leftIterator = left.iterator();
            final Iterator<A> rightIterator = right.iterator();
            while (leftIterator.hasNext()) {
                if (!eqA.eqv(leftIterator.next(), rightIterator.next())) {
                    return false;
                }
            }
            return true;
        };
    }

    /**
     * Derives a textual representation for non-empty lists.
     *
     * @param showA element renderer
     * @param <A> element type
     * @return non-empty-list renderer
     */
    public static <A> Show<NonEmptyList<A>> show(final Show<? super A> showA) {
        Objects.requireNonNull(showA, "showA");
        return values -> values.toList().stream()
                .map(showA::show)
                .collect(Collectors.joining(", ", "NonEmptyList(", ")"));
    }

    /**
     * Returns the natural semigroup for non-empty lists: concatenation.
     *
     * <p>There is intentionally no corresponding monoid because a non-empty
     * list has no lawful empty identity value.</p>
     *
     * @param <A> element type
     * @return concatenation semigroup
     */
    public static <A> Semigroup<NonEmptyList<A>> concatenation() {
        return NonEmptyList::concat;
    }
}
