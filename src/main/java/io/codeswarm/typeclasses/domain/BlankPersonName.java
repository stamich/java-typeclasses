package io.codeswarm.typeclasses.domain;

/** Validation error indicating that a person's name was blank. */
public record BlankPersonName() implements ValidationError {
    /** {@inheritDoc} */
    @Override
    public String message() {
        return "Person name must not be blank";
    }
}
