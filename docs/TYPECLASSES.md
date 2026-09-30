# Typeclasses in Java

## External polymorphism

A typeclass describes behavior for a type without forcing the domain type to inherit from that behavior.

```java
@FunctionalInterface
public interface Eq<A> {
    boolean eqv(A left, A right);
}
```

A `Person` record therefore remains a plain domain model while several `Eq<Person>` values may coexist.

## Explicit dictionary passing

Java does not provide Scala-style `given` / `using`. This project deliberately passes instances explicitly:

```java
EqFunctions.equal(left, right, PersonInstances.EQ_NAME);
```

The approach is transparent, testable and requires no reflection or global registry.

## Show

`Show<A>` represents presentation independently from the domain type. Multiple renderings can coexist.

## Eq

`Eq<A>` represents a chosen equivalence relation. A lawful equality is reflexive, symmetric and transitive.

## Ord

`Ord<A>` extends `Eq<A>` and derives equality from zero comparison. Different orderings may intentionally induce different equivalence classes, such as ordering `Person` only by age.

## Semigroup and Monoid

`Semigroup<A>` describes associative combination. `Monoid<A>` adds an identity element.

Milestone 0.3 no longer leaves these requirements as documentation only: property tests execute them through reusable law helpers.

See [`LAWS.md`](LAWS.md).

## Multiple instances

The same Java type may have multiple useful instances. `Integer` has both addition and multiplication monoids. `Person` has several equality and ordering interpretations.

This is a major difference from encoding behavior directly through inheritance on the data type.

## Current limitation

Java has no native higher-kinded types. Milestone 0.3 intentionally does not attempt to solve that problem yet. HKT encoding is planned only after the first-order algebra and laws are stable.
