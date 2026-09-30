package io.codeswarm.typeclasses.domain;

import java.util.Objects;

/**
 * Domain user composed exclusively from already validated value objects.
 *
 * @param id validated user identifier
 * @param name validated person name
 * @param age validated age
 */
public record User(UserId id, PersonName name, Age age) {

    /** Ensures that all domain components are present. */
    public User {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(age, "age");
    }
}
