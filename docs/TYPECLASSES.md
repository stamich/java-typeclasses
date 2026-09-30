# Typeclasses in Java

## Concept

A typeclass describes behaviour for a type without requiring that type to inherit from or implement the behaviour interface.

In this project a typeclass is represented by a small generic interface and an instance is an ordinary Java value implementing that interface.

## Show

```java
@FunctionalInterface
public interface Show<A> {
    String show(A value);
}
```

`Person` does not implement `Show<Person>`. Instead, separate values provide compact and verbose representations.

## Eq

`Eq<A>` represents selectable equality semantics:

```java
Eq<Person> byName =
        (left, right) -> left.name().equalsIgnoreCase(right.name());
```

This differs from `Object.equals`: multiple valid equality policies can coexist and callers choose one explicitly.

## Ord

`Ord<A>` refines `Eq<A>` with a comparison operation. Equality follows naturally when `compare(left, right) == 0`.

A `Person` can therefore be ordered by age in one algorithm and by name in another without changing the record.

## Semigroup

A semigroup consists of a set of values and an associative `combine` operation.

Examples include maximum over integers and string concatenation.

Milestone 0.2 documents associativity but does not attempt to prove it with a few example-based unit tests. Systematic law verification belongs to 0.3.

## Monoid

A monoid is a semigroup with an identity element.

Examples:

| Type / operation | `combine` | identity |
| --- | --- | --- |
| Integer addition | `+` | `0` |
| Integer multiplication | `*` | `1` |
| String concatenation | concatenation | `""` |
| List concatenation | concatenation | `[]` |

## Explicit instance selection

```java
var sum = MonoidFunctions.combineAll(values, IntegerInstances.ADDITION);
var product = MonoidFunctions.combineAll(values, IntegerInstances.MULTIPLICATION);
```

The generic algorithm does not need to know anything about addition or multiplication. Behaviour is selected by supplying the appropriate dictionary value.

## Typeclasses versus inheritance

With inheritance, a domain type usually embeds one implementation of an interface. Typeclasses invert that relationship: external instances describe the type.

This is especially useful when:

- the domain type cannot be modified, such as `LocalDate`;
- several valid implementations exist for the same type;
- domain data should remain independent of presentation or algorithm policy.

## Typeclasses versus Strategy

The Java representation resembles the Strategy pattern because behaviour is passed as an object. The typeclass perspective adds a stronger focus on generic capability interfaces, reusable instances, algebraic laws and composition across types.

## Deliberately missing in 0.2

There is no global registry, implicit search, `summon`, reflection, annotations, automatic derivation, higher-kinded type encoding, `Functor`, `Applicative`, or `Monad` yet.
