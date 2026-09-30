package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.core.Eq;
import io.codeswarm.typeclasses.core.Show;
import io.codeswarm.typeclasses.data.None;
import io.codeswarm.typeclasses.data.Option;
import java.util.Objects;

/**
 * Typeclass instances derived for {@link Option} values.
 */
public final class OptionInstances {

    private OptionInstances() {
        throw new AssertionError("Instances holder must not be instantiated");
    }

    /**
     * Derives an equality instance for {@code Option<A>} from an equality
     * instance for {@code A}.
     *
     * @param eqA equality for contained values
     * @param <A> contained value type
     * @return equality for options
     */
    public static <A> Eq<Option<A>> eq(final Eq<? super A> eqA) {
        Objects.requireNonNull(eqA, "eqA");
        return (left, right) -> left.fold(
                () -> right instanceof None<?>,
                leftValue -> right.fold(
                        () -> false,
                        rightValue -> eqA.eqv(leftValue, rightValue)));
    }

    /**
     * Derives a textual representation for {@code Option<A>} from a renderer
     * for {@code A}.
     *
     * @param showA renderer for contained values
     * @param <A> contained value type
     * @return renderer for options
     */
    public static <A> Show<Option<A>> show(final Show<? super A> showA) {
        Objects.requireNonNull(showA, "showA");
        return option -> option.fold(
                () -> "None",
                value -> "Some(" + showA.show(value) + ")");
    }
}
