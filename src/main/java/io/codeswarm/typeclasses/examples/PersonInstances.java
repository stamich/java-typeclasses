package io.codeswarm.typeclasses.examples;

import io.codeswarm.typeclasses.core.Eq;
import io.codeswarm.typeclasses.core.Ord;
import io.codeswarm.typeclasses.core.Show;

/**
 * Typeclass instances associated with the example {@link Person} domain type.
 *
 * <p>The instances are deliberately kept outside {@code Person}. This keeps
 * the domain model independent of equality, ordering, and presentation policy
 * and permits several valid interpretations to coexist.</p>
 */
public final class PersonInstances {

    /** Compact representation containing only the person's name. */
    public static final Show<Person> SHOW_COMPACT = Person::name;

    /** Verbose representation containing both the person's name and age. */
    public static final Show<Person> SHOW_VERBOSE =
            person -> "%s (%d)".formatted(person.name(), person.age());

    /** Equality based on both record components. */
    public static final Eq<Person> EQ_ALL_FIELDS =
            (left, right) -> left.name().equals(right.name()) && left.age() == right.age();

    /** Case-insensitive equality based only on the person's name. */
    public static final Eq<Person> EQ_NAME =
            (left, right) -> left.name().equalsIgnoreCase(right.name());

    /** Orders people by age and therefore considers equal ages equivalent. */
    public static final Ord<Person> ORD_AGE =
            (left, right) -> Integer.compare(left.age(), right.age());

    /** Orders people case-insensitively by name. */
    public static final Ord<Person> ORD_NAME =
            (left, right) -> left.name().compareToIgnoreCase(right.name());

    private PersonInstances() {
        throw new AssertionError("Instances holder must not be instantiated");
    }
}
