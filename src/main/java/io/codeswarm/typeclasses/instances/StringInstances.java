package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.algebra.Monoid;
import io.codeswarm.typeclasses.core.Eq;
import io.codeswarm.typeclasses.core.Ord;
import io.codeswarm.typeclasses.core.Show;

/**
 * Standard typeclass instances for {@link String} values.
 */
public final class StringInstances {

    /** Renders a string without additional formatting. */
    public static final Show<String> SHOW = value -> value;

    /** Compares strings using standard case-sensitive equality. */
    public static final Eq<String> EQ = String::equals;

    /** Orders strings lexicographically using their natural ordering. */
    public static final Ord<String> LEXICOGRAPHIC_ORD = String::compareTo;

    /** Orders strings lexicographically while ignoring case. */
    public static final Ord<String> CASE_INSENSITIVE_ORD = String::compareToIgnoreCase;

    /** String monoid under concatenation. The identity is the empty string. */
    public static final Monoid<String> CONCATENATION = new Monoid<>() {
        /** {@inheritDoc} */
        @Override
        public String combine(final String left, final String right) {
            return left + right;
        }

        /** {@inheritDoc} */
        @Override
        public String empty() {
            return "";
        }
    };

    private StringInstances() {
        throw new AssertionError("Instances holder must not be instantiated");
    }
}
