package io.codeswarm.typeclasses.domain;

/** Validation error indicating that a user identifier was blank. */
public record BlankUserId() implements ValidationError {
    /** {@inheritDoc} */
    @Override
    public String message() {
        return "UserId must not be blank";
    }
}
