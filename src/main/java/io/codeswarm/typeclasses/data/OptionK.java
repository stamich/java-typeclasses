package io.codeswarm.typeclasses.data;

/**
 * Witness type identifying the {@link Option} type constructor in the
 * higher-kinded encoding.
 *
 * <p>The class has no instances and carries no runtime state. It exists only
 * at the type level so that {@code Option<A>} can be represented as
 * {@code Kind<OptionK, A>}.</p>
 */
public final class OptionK {

    private OptionK() {
    }
}
