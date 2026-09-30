# Basic Algebra — Milestone 0.3

## Semigroup

A semigroup is a type plus an associative binary operation:

```text
combine(combine(a, b), c)
==
combine(a, combine(b, c))
```

The Java representation remains deliberately minimal:

```java
@FunctionalInterface
public interface Semigroup<A> {
    A combine(A left, A right);
}
```

Milestone 0.3 verifies associativity with generated values rather than relying only on documentation or a few examples.

## Monoid

A monoid is a semigroup with an identity element:

```text
combine(empty(), a) == a
combine(a, empty()) == a
```

Examples in the project:

- integer addition with identity `0`;
- integer multiplication with identity `1`;
- string concatenation with identity `""`;
- list concatenation with identity `[]`.

## Equality used by laws

Law checking does not hard-code `Object.equals`. The law receives an `Eq<A>` instance, allowing algebraic results to be compared according to the same explicit typeclass philosophy as the rest of the project.

## Laws as executable contracts

The interfaces remain tiny. Their semantic contracts are verified in test scope through `SemigroupLaws` and `MonoidLaws` plus jqwik property suites.

## NonEmptyList semigroup (0.4.1)

`NonEmptyList<A>` has a natural associative concatenation operation:

```text
NonEmptyList(1,2) <> NonEmptyList(3,4)
=
NonEmptyList(1,2,3,4)
```

Therefore it forms a `Semigroup` under concatenation.

It deliberately does **not** form a `Monoid` under the same representation because there is no empty `NonEmptyList` to serve as the identity value. This contrasts with ordinary `List<A>`, whose empty list is the concatenation identity.
