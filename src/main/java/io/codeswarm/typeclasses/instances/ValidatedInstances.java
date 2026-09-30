package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.core.Eq;
import io.codeswarm.typeclasses.core.Show;
import io.codeswarm.typeclasses.data.NonEmptyList;
import io.codeswarm.typeclasses.data.Validated;
import java.util.Iterator;
import java.util.Objects;

/**
 * Typeclass instances derived for {@link Validated} values.
 */
public final class ValidatedInstances {

    private ValidatedInstances() {
        throw new AssertionError("Instances holder must not be instantiated");
    }

    /**
     * Derives equality for validation values. Invalid branches are compared
     * element-by-element and preserve error ordering.
     *
     * @param eqE equality for validation errors
     * @param eqA equality for successful values
     * @param <E> error type
     * @param <A> success type
     * @return equality for validated values
     */
    public static <E, A> Eq<Validated<E, A>> eq(
            final Eq<? super E> eqE,
            final Eq<? super A> eqA) {
        Objects.requireNonNull(eqE, "eqE");
        Objects.requireNonNull(eqA, "eqA");
        return (left, right) -> left.fold(
                leftErrors -> right.fold(
                        rightErrors -> equalNonEmptyLists(leftErrors, rightErrors, eqE),
                        ignored -> false),
                leftValue -> right.fold(
                        ignored -> false,
                        rightValue -> eqA.eqv(leftValue, rightValue)));
    }

    /**
     * Derives a textual representation for validation values.
     *
     * @param showE renderer for errors
     * @param showA renderer for successful values
     * @param <E> error type
     * @param <A> success type
     * @return renderer for validated values
     */
    public static <E, A> Show<Validated<E, A>> show(
            final Show<? super E> showE,
            final Show<? super A> showA) {
        Objects.requireNonNull(showE, "showE");
        Objects.requireNonNull(showA, "showA");
        return validated -> validated.fold(
                errors -> "Invalid(" + errors.toList().stream().map(showE::show).toList() + ")",
                value -> "Valid(" + showA.show(value) + ")");
    }

    private static <E> boolean equalNonEmptyLists(
            final NonEmptyList<E> left,
            final NonEmptyList<E> right,
            final Eq<? super E> eqE) {
        if (left.size() != right.size()) {
            return false;
        }
        final Iterator<E> leftIterator = left.iterator();
        final Iterator<E> rightIterator = right.iterator();
        while (leftIterator.hasNext()) {
            if (!eqE.eqv(leftIterator.next(), rightIterator.next())) {
                return false;
            }
        }
        return true;
    }
}
