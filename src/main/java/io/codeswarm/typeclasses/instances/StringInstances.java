package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.core.Show;

/**
 * Standard typeclass instances for {@link String} values.
 */
public final class StringInstances {

    /**
     * Renders a string without adding formatting or quotation marks.
     */
    public static final Show<String> SHOW = value -> value;

    private StringInstances() {
        throw new AssertionError("Instances holder must not be instantiated");
    }
}
