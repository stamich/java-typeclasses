package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.core.Eq;
import io.codeswarm.typeclasses.core.Show;
import io.codeswarm.typeclasses.data.Either;
import java.util.Objects;

/**
 * Typeclass instances derived for {@link Either} values.
 */
public final class EitherInstances {

    private EitherInstances() {
        throw new AssertionError("Instances holder must not be instantiated");
    }

    /**
     * Derives equality for {@code Either<L,R>} from equality instances for both
     * branches.
     *
     * @param eqL equality for left values
     * @param eqR equality for right values
     * @param <L> left type
     * @param <R> right type
     * @return equality for either values
     */
    public static <L, R> Eq<Either<L, R>> eq(
            final Eq<? super L> eqL,
            final Eq<? super R> eqR) {
        Objects.requireNonNull(eqL, "eqL");
        Objects.requireNonNull(eqR, "eqR");
        return (left, right) -> left.fold(
                leftValue -> right.fold(
                        rightLeft -> eqL.eqv(leftValue, rightLeft),
                        ignored -> false),
                leftValue -> right.fold(
                        ignored -> false,
                        rightValue -> eqR.eqv(leftValue, rightValue)));
    }

    /**
     * Derives a textual representation for {@code Either<L,R>}.
     *
     * @param showL renderer for left values
     * @param showR renderer for right values
     * @param <L> left type
     * @param <R> right type
     * @return renderer for either values
     */
    public static <L, R> Show<Either<L, R>> show(
            final Show<? super L> showL,
            final Show<? super R> showR) {
        Objects.requireNonNull(showL, "showL");
        Objects.requireNonNull(showR, "showR");
        return either -> either.fold(
                value -> "Left(" + showL.show(value) + ")",
                value -> "Right(" + showR.show(value) + ")");
    }
}
