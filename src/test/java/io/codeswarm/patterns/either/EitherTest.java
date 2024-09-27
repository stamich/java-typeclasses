package io.codeswarm.patterns.either;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class EitherTest {

    @Test
    void testLeft() {
        EitherType<String, Integer> either = new EitherType<>(Optional.empty(), Optional.empty());
        Either<String, Integer> left = either.left("LeftValue");

        assertInstanceOf(EitherType.class, left);
        assertEquals("LeftValue", ((EitherType<String, Integer>) left).map(l -> l, r -> null));
        assertFalse(((EitherType<String, Integer>) left).right.isPresent());
    }

    @Test
    void testRight() {
        EitherType<String, Integer> either = new EitherType<>(Optional.empty(), Optional.empty());
        Either<String, Integer> right = either.right(42);

        assertInstanceOf(EitherType.class, right);
        assertEquals(Optional.of(42), Optional.ofNullable(right.map(l -> null, r -> r)));
        assertFalse(((EitherType<String, Integer>) right).left.isPresent());
    }

    @Test
    void testMap() {
        EitherType<String, Integer> leftEither = new EitherType<>(Optional.of("LeftValue"), Optional.empty());
        EitherType<String, Integer> rightEither = new EitherType<>(Optional.empty(), Optional.of(42));

        // Test mapping left value
        String leftResult = leftEither.map(l -> "Left: " + l, r -> "Right: " + r);
        assertEquals("Left: LeftValue", leftResult);

        // Test mapping right value
        String rightResult = rightEither.map(l -> "Left: " + l, r -> "Right: " + r);
        assertEquals("Right: 42", rightResult);
    }

    @Test
    void testMapLeft() {
        EitherType<String, Integer> either = new EitherType<>(Optional.of("LeftValue"), Optional.of(42));

        Either<String, Integer> mappedLeft = either.mapLeft(l -> l + "Mapped");

        assertEquals("LeftValueMapped", mappedLeft.map(l -> l, r -> null));
    }

    @Test
    void testMapRight() {
        EitherType<String, Integer> either = new EitherType<>(Optional.of("LeftValue"), Optional.of(42));

        Either<String, Integer> mappedRight = either.mapRight(r -> r + 10);

        assertEquals(Optional.of(52), Optional.ofNullable(mappedRight.map(l -> null, r -> r)));
    }

    @Test
    void testApply() {
        EitherType<String, Integer> leftEither = new EitherType<>(Optional.of("LeftValue"), Optional.empty());
        EitherType<String, Integer> rightEither = new EitherType<>(Optional.empty(), Optional.of(42));

        // Test applying consumers to left value
        StringBuilder resultLeft = new StringBuilder();
        leftEither.apply(l -> resultLeft.append("Left: ").append(l), r -> resultLeft.append("Right: ").append(r));
        assertEquals("Left: LeftValue", resultLeft.toString());

        // Test applying consumers to right value
        StringBuilder resultRight = new StringBuilder();
        rightEither.apply(l -> resultRight.append("Left: ").append(l), r -> resultRight.append("Right: ").append(r));
        assertEquals("Right: 42", resultRight.toString());
    }
}
