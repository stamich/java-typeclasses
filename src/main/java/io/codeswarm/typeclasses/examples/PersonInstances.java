package io.codeswarm.typeclasses.examples;

import io.codeswarm.typeclasses.core.Show;

/**
 * Typeclass instances associated with the example {@link Person} domain type.
 *
 * <p>The instances are deliberately kept outside {@code Person}. This makes
 * the domain model independent from presentation concerns and permits several
 * valid representations to coexist.</p>
 */
public final class PersonInstances {

    /**
     * Compact representation containing only the person's name.
     */
    public static final Show<Person> COMPACT_SHOW = Person::name;

    /**
     * Verbose representation containing both the person's name and age.
     */
    public static final Show<Person> VERBOSE_SHOW =
            person -> "%s (%d)".formatted(person.name(), person.age());

    private PersonInstances() {
        throw new AssertionError("Instances holder must not be instantiated");
    }
}
