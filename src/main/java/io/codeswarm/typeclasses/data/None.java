package io.codeswarm.typeclasses.data;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Empty branch of {@link Option}.
 *
 * @param <A> value type represented by the empty option
 */
public record None<A>() implements Option<A> {

    /** {@inheritDoc} */
    @Override
    public <B> Option<B> map(final Function<? super A, ? extends B> mapper) {
        Objects.requireNonNull(mapper, "mapper");
        return Option.none();
    }

    /** {@inheritDoc} */
    @Override
    public <B> B fold(
            final Supplier<? extends B> onNone,
            final Function<? super A, ? extends B> onSome) {
        Objects.requireNonNull(onNone, "onNone");
        Objects.requireNonNull(onSome, "onSome");
        return onNone.get();
    }
}
