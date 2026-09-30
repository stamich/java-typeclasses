package io.codeswarm.typeclasses.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Immutable list that is guaranteed to contain at least one value.
 *
 * <p>The non-empty invariant is encoded structurally by storing the first
 * element separately from the remaining tail. This avoids representing an
 * invalid empty state and makes the type useful for validation errors and
 * other algebraic structures that have no lawful empty value.</p>
 *
 * @param head first element
 * @param tail immutable remaining elements
 * @param <A> element type
 */
public record NonEmptyList<A>(A head, List<A> tail) implements Iterable<A> {

    /**
     * Creates a non-empty list from a head and tail.
     *
     * @param head first element
     * @param tail remaining elements
     * @throws NullPointerException when the head, tail or a tail element is null
     */
    public NonEmptyList {
        Objects.requireNonNull(head, "head");
        Objects.requireNonNull(tail, "tail");
        tail = List.copyOf(tail);
    }

    /**
     * Creates a single-element non-empty list.
     *
     * @param value only element
     * @param <A> element type
     * @return non-empty list containing {@code value}
     */
    public static <A> NonEmptyList<A> one(final A value) {
        return new NonEmptyList<>(Objects.requireNonNull(value, "value"), List.of());
    }

    /**
     * Creates a non-empty list from a mandatory first value and optional rest.
     *
     * @param head first element
     * @param rest remaining elements
     * @param <A> element type
     * @return non-empty list
     */
    @SafeVarargs
    public static <A> NonEmptyList<A> of(final A head, final A... rest) {
        Objects.requireNonNull(rest, "rest");
        final List<A> tail = new ArrayList<>(rest.length);
        for (final A value : rest) {
            tail.add(Objects.requireNonNull(value, "rest element"));
        }
        return new NonEmptyList<>(head, tail);
    }

    /**
     * Returns all values as an immutable ordinary list.
     *
     * @return immutable list preserving element order
     */
    public List<A> toList() {
        final List<A> values = new ArrayList<>(1 + tail.size());
        values.add(head);
        values.addAll(tail);
        return List.copyOf(values);
    }

    /**
     * Returns the number of elements.
     *
     * @return size, always at least one
     */
    public int size() {
        return 1 + tail.size();
    }

    /**
     * Transforms every element while preserving the non-empty invariant.
     *
     * @param mapper element mapping function
     * @param <B> resulting element type
     * @return mapped non-empty list
     */
    public <B> NonEmptyList<B> map(final Function<? super A, ? extends B> mapper) {
        Objects.requireNonNull(mapper, "mapper");
        final B mappedHead = Objects.requireNonNull(mapper.apply(head), "mapped head");
        final List<B> mappedTail = tail.stream()
                .map(mapper)
                .map(value -> Objects.requireNonNull(value, "mapped tail element"))
                .toList();
        return new NonEmptyList<>(mappedHead, mappedTail);
    }

    /**
     * Concatenates this list with another non-empty list.
     *
     * @param other list appended after this list
     * @return concatenated non-empty list
     */
    public NonEmptyList<A> concat(final NonEmptyList<? extends A> other) {
        Objects.requireNonNull(other, "other");
        final List<A> newTail = new ArrayList<>(tail.size() + other.size());
        newTail.addAll(tail);
        newTail.add(other.head());
        newTail.addAll(other.tail());
        return new NonEmptyList<>(head, newTail);
    }

    /**
     * Returns an iterator over the head followed by the tail.
     *
     * @return iterator preserving list order
     */
    @Override
    public Iterator<A> iterator() {
        return toList().iterator();
    }
}
