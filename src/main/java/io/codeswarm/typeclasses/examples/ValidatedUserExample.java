package io.codeswarm.typeclasses.examples;

import io.codeswarm.typeclasses.domain.UserValidator;

/** Demonstrates domain validation with accumulated typed errors. */
public final class ValidatedUserExample {

    private ValidatedUserExample() {
        throw new AssertionError("Example class must not be instantiated");
    }

    /** Runs valid and invalid user-validation examples. */
    public static void main(final String[] args) {
        System.out.println(UserValidator.validate("user-1", "Alice", 42));
        System.out.println(UserValidator.validate("", "", -10));
    }
}
