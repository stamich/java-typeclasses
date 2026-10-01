package io.codeswarm.typeclasses.hkt;

/**
 * Encodes an application of a higher-kinded type constructor {@code F} to a
 * value type {@code A}.
 *
 * <p>Java cannot abstract directly over type constructors such as
 * {@code Option<?>}. This marker interface provides a lightweight encoding
 * that allows later typeclasses to describe operations over a constructor
 * independently of the contained value type.</p>
 *
 * <p>The {@code F} parameter is a witness type. For example,
 * {@code Option<A>} implements {@code Kind<OptionK, A>}.</p>
 *
 * @param <F> witness type identifying the type constructor
 * @param <A> contained value type
 */
public interface Kind<F, A> {
}
