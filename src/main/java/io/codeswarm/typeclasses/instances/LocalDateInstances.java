package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.core.Show;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Standard typeclass instances for {@link LocalDate} values.
 *
 * <p>This class also demonstrates an important advantage of the typeclass
 * pattern: behaviour can be added to a type from the JDK without modifying or
 * extending that type.</p>
 */
public final class LocalDateInstances {

    /**
     * Renders a date in ISO-8601 local-date format, for example
     * {@code 2026-09-30}.
     */
    public static final Show<LocalDate> ISO_SHOW =
            date -> DateTimeFormatter.ISO_LOCAL_DATE.format(date);

    private LocalDateInstances() {
        throw new AssertionError("Instances holder must not be instantiated");
    }
}
