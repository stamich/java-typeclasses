package io.codeswarm.typeclasses.domain;

/**
 * Validation error indicating that an age was outside the supported range.
 *
 * @param value rejected age
 */
public record InvalidAge(int value) implements ValidationError {
    /** {@inheritDoc} */
    @Override
    public String message() {
        return "Age must be between 0 and 130: " + value;
    }
}
