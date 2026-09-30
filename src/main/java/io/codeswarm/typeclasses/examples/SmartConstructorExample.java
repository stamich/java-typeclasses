package io.codeswarm.typeclasses.examples;

import io.codeswarm.typeclasses.domain.Age;
import io.codeswarm.typeclasses.domain.PersonName;
import io.codeswarm.typeclasses.domain.UserId;

/** Demonstrates domain smart constructors that return explicit validation ADTs. */
public final class SmartConstructorExample {

    private SmartConstructorExample() {
        throw new AssertionError("Example class must not be instantiated");
    }

    /** Runs the smart-constructor example. */
    static void main() {
        System.out.println(UserId.from("user-42"));
        System.out.println(UserId.from("   "));
        System.out.println(PersonName.from("Alice"));
        System.out.println(Age.from(42));
        System.out.println(Age.from(-1));
    }
}
