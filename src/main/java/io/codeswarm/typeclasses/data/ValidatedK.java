package io.codeswarm.typeclasses.data;

/**
 * Witness type identifying {@code Validated<E, ?>} after fixing the error
 * type.
 *
 * @param <E> fixed validation error type
 */
public final class ValidatedK<E> {

    private ValidatedK() {
    }
}
