package io.codeswarm.typeclasses.data;

/**
 * Witness type identifying {@code Either<L, ?>} after fixing the left type.
 *
 * <p>For example, {@code Either<String, Integer>} is represented as
 * {@code Kind<EitherK<String>, Integer>}.</p>
 *
 * @param <L> fixed left type
 */
public final class EitherK<L> {

    private EitherK() {
    }
}
