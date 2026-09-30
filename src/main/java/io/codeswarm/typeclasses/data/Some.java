package io.codeswarm.typeclasses.data;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Present-value branch of {@link Option}.
 *
 * @param value contained non-null value
 * @param <A> contained value type
 */
public record Some<A>(A value) implements Option<A> {

    /**
     * Creates a present optional value.
     *
     * @param value contained value
     */
    public Some {
        Objects.requireNonNull(value, "value");
    }

    /** {@inheritDoc} */
    @Override
    public <B> Option<B> map(final Function<? super A, ? extends B> mapper) {
        Objects.requireNonNull(mapper, "mapper");
        return Option.some(mapper.apply(value));
    }

    /** {@inheritDoc} */
    @Override
    public <B> B fold(
            final Supplier<? extends B> onNone,
            final Function<? super A, ? extends B> onSome) {
        Objects.requireNonNull(onNone, "onNone");
        Objects.requireNonNull(onSome, "onSome");
        return onSome.apply(value);
    }
}
