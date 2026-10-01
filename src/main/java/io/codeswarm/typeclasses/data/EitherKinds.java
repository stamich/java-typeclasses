package io.codeswarm.typeclasses.data;

import io.codeswarm.typeclasses.hkt.Kind;
import java.util.Objects;

/**
 * Conversion helpers between {@link Either} and its higher-kinded
 * representation with the left type fixed.
 */
public final class EitherKinds {

    private EitherKinds() {
    }

    /**
     * Widens an {@link Either} to {@code Kind<EitherK<L>, R>}.
     *
     * @param either either value to widen
     * @param <L> left type
     * @param <R> right type
     * @return the same value viewed through the HKT encoding
     */
    public static <L, R> Kind<EitherK<L>, R> widen(final Either<L, R> either) {
        return Objects.requireNonNull(either, "either");
    }

    /**
     * Narrows {@code Kind<EitherK<L>, R>} back to {@link Either}.
     *
     * @param value higher-kinded either value
     * @param <L> left type
     * @param <R> right type
     * @return corresponding either
     */
    @SuppressWarnings("unchecked")
    public static <L, R> Either<L, R> narrow(final Kind<EitherK<L>, R> value) {
        Objects.requireNonNull(value, "value");
        return (Either<L, R>) value;
    }
}
