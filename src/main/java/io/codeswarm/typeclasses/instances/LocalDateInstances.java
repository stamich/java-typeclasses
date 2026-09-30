package io.codeswarm.typeclasses.instances;

import io.codeswarm.typeclasses.core.Eq;
import io.codeswarm.typeclasses.core.Ord;
import io.codeswarm.typeclasses.core.Show;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Standard typeclass instances for {@link LocalDate} values.
 *
 * <p>This class demonstrates that typeclass behaviour can be supplied for a
 * JDK type without modifying or extending that type.</p>
 */
public final class LocalDateInstances {

    /** Renders dates in ISO-8601 local-date format. */
    public static final Show<LocalDate> ISO_SHOW =
            DateTimeFormatter.ISO_LOCAL_DATE::format;

    /** Compares dates using standard value equality. */
    public static final Eq<LocalDate> EQ = LocalDate::equals;

    /** Orders dates chronologically. */
    public static final Ord<LocalDate> ORD = LocalDate::compareTo;

    private LocalDateInstances() {
        throw new AssertionError("Instances holder must not be instantiated");
    }
}
