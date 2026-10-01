package io.codeswarm.typeclasses.data;

import io.codeswarm.typeclasses.hkt.Kind;
import java.util.Objects;

/**
 * Conversion helpers between {@link Option} and its higher-kinded
 * representation.
 *
 * <p>Unchecked narrowing is intentionally isolated in this class so the rest
 * of the codebase does not need to repeat casts required by Java type
 * erasure.</p>
 */
public final class OptionKinds {

    private OptionKinds() {
    }

    /**
     * Widens an {@link Option} to its generic {@link Kind} representation.
     *
     * @param option option to widen
     * @param <A> contained value type
     * @return the same value viewed as {@code Kind<OptionK, A>}
     */
    public static <A> Kind<OptionK, A> widen(final Option<A> option) {
        return Objects.requireNonNull(option, "option");
    }

    /**
     * Narrows an {@link Kind} carrying the {@link OptionK} witness back to an
     * {@link Option}.
     *
     * <p>The cast is safe for values created through this library because the
     * sealed {@code Option} hierarchy is the intended implementation of the
     * {@code OptionK} witness. Java cannot prove that relationship after type
     * erasure, therefore the cast is centralized here.</p>
     *
     * @param value higher-kinded option value
     * @param <A> contained value type
     * @return the corresponding option
     */
    @SuppressWarnings("unchecked")
    public static <A> Option<A> narrow(final Kind<OptionK, A> value) {
        Objects.requireNonNull(value, "value");
        return (Option<A>) value;
    }
}
