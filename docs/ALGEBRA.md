# Basic Algebra — Milestone 0.2

Milestone 0.2 introduces two algebraic structures: `Semigroup<A>` and `Monoid<A>`.

## Semigroup

A semigroup is a type together with an associative binary operation:

```text
combine(combine(a, b), c)
==
combine(a, combine(b, c))
```

The interface is intentionally minimal:

```java
@FunctionalInterface
public interface Semigroup<A> {
    A combine(A left, A right);
}
```

Because a semigroup has no identity value, a generic fold cannot produce a lawful result for an empty collection. `SemigroupFunctions.combineAll` therefore rejects an empty iterable.

## Monoid

A monoid extends a semigroup with an identity element `empty()`.

In addition to associativity, it must satisfy:

```text
combine(empty(), a) == a
combine(a, empty()) == a
```

This makes an empty fold meaningful.

## Multiple monoids over the same type

There is no unique monoid for `Integer`.

### Addition

```text
combine(a, b) = a + b
empty()       = 0
```

### Multiplication

```text
combine(a, b) = a * b
empty()       = 1
```

This is a central reason for representing algebraic behaviour as external typeclass instances.

## String concatenation

Strings form a monoid under concatenation with `""` as the identity.

## List concatenation

Lists form a monoid under concatenation with the empty list as the identity. The milestone implementation avoids mutating source lists and returns an immutable result.

## Law testing

Unit tests in 0.2 verify API behaviour, not algebraic correctness over the entire input domain. Milestone 0.3 will add reusable law definitions and property-based tests for associativity, identities, equality laws and ordering laws.
