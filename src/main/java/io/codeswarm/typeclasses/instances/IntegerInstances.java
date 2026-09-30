package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.core.Show;

/**
 * Standard typeclass instances for {@link Integer} values.
 */
public final class IntegerInstances {

    /**
     * Renders an integer using its standard decimal representation.
     */
    public static final Show<Integer> SHOW = String::valueOf;

    private IntegerInstances() {
        throw new AssertionError("Instances holder must not be instantiated");
    }
}
