package io.codeswarm.typeclasses.data;

import java.util.Optional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Unit tests for {@link Either}. */
class EitherTest {

    /** Verifies right-biased mapping. */
    @Test
    void shouldMapRight() {
        final Either<String, Integer> result = Either.<String, Integer>right(21).map(value -> value * 2);
        assertEquals(Either.right(42), result);
        assertTrue(result.isRight());
    }

    /** Verifies that right mapping leaves the left branch unchanged. */
    @Test
    void shouldPreserveLeftWhenMappingRight() {
        final Either<String, Integer> result = Either.<String, Integer>left("boom").map(value -> value * 2);
        assertEquals(Either.left("boom"), result);
        assertTrue(result.isLeft());
    }

    /** Verifies explicit left mapping. */
    @Test
    void shouldMapLeft() {
        final Either<String, Integer> result = Either.<Integer, Integer>left(404).mapLeft(String::valueOf);
        assertEquals(Either.left("404"), result);
    }

    /** Verifies branch elimination. */
    @Test
    void shouldFold() {
        assertEquals("error: boom", Either.<String, Integer>left("boom")
                .fold(value -> "error: " + value, String::valueOf));
    }
    /** Verifies conditional construction. */
    @Test
    void shouldConstructConditionally() {
        assertEquals(Either.right(42), Either.cond(true, () -> 42, () -> "error"));
        assertEquals(Either.left("error"), Either.cond(false, () -> 42, () -> "error"));
    }

    /** Verifies nullable and Optional adapters. */
    @Test
    void shouldAdaptJavaOptionalValues() {
        assertEquals(Either.right(42), Either.fromNullable(42, () -> "missing"));
        assertEquals(Either.left("missing"), Either.fromNullable(null, () -> "missing"));
        assertEquals(Either.right(42), Either.fromOptional(Optional.of(42), () -> "missing"));
        assertEquals(Either.left("missing"), Either.fromOptional(Optional.empty(), () -> "missing"));
    }

}
