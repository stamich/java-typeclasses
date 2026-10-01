package io.codeswarm.typeclasses.data;

import io.codeswarm.typeclasses.hkt.Kind;
import java.util.Objects;

/**
 * Conversion helpers between {@link Validated} and its higher-kinded
 * representation with the error type fixed.
 */
public final class ValidatedKinds {

    private ValidatedKinds() {
    }

    /**
     * Widens a {@link Validated} value to {@code Kind<ValidatedK<E>, A>}.
     *
     * @param validated validation value to widen
     * @param <E> error type
     * @param <A> successful value type
     * @return the same value viewed through the HKT encoding
     */
    public static <E, A> Kind<ValidatedK<E>, A> widen(final Validated<E, A> validated) {
        return Objects.requireNonNull(validated, "validated");
    }

    /**
     * Narrows {@code Kind<ValidatedK<E>, A>} back to {@link Validated}.
     *
     * @param value higher-kinded validation value
     * @param <E> error type
     * @param <A> successful value type
     * @return corresponding validation value
     */
    @SuppressWarnings("unchecked")
    public static <E, A> Validated<E, A> narrow(final Kind<ValidatedK<E>, A> value) {
        Objects.requireNonNull(value, "value");
        return (Validated<E, A>) value;
    }
}
