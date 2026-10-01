package io.codeswarm.typeclasses.hkt;

import io.codeswarm.typeclasses.data.Either;
import io.codeswarm.typeclasses.data.EitherK;
import io.codeswarm.typeclasses.data.EitherKinds;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Verifies higher-kinded widening and narrowing for {@link Either}.
 */
class EitherKindsTest {

    /**
     * Ensures partial application keeps the left type fixed while the right
     * type occupies the value position of {@link Kind}.
     */
    @Test
    void shouldRoundTripEither() {
        final Either<String, Integer> original = Either.right(42);
        final Kind<EitherK<String>, Integer> widened = EitherKinds.widen(original);
        final Either<String, Integer> narrowed = EitherKinds.narrow(widened);

        assertSame(original, widened);
        assertSame(original, narrowed);
        assertEquals(original, narrowed);
    }
}
