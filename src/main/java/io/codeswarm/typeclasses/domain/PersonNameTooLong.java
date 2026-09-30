package io.codeswarm.typeclasses.domain;

/**
 * Validation error indicating that a person's name exceeded the supported
 * maximum length.
 *
 * @param actualLength rejected name length
 * @param maximumLength maximum accepted length
 */
public record PersonNameTooLong(int actualLength, int maximumLength) implements ValidationError {
    /** {@inheritDoc} */
    @Override
    public String message() {
        return "Person name length " + actualLength + " exceeds maximum " + maximumLength;
    }
}
