# Architecture — Milestone 0.2

## Purpose

Milestone 0.2 evolves the 0.1.1 foundation into a small algebraic typeclass library while deliberately retaining explicit, understandable mechanics.

## Layers

```text
Domain values
(Person, Integer, String, LocalDate, List)
        │
        │ described by external instances
        ▼
Typeclasses
┌──────────────────────────────┐
│ Show<A>                      │
│ Eq<A>  ◄── Ord<A>            │
│ Semigroup<A> ◄── Monoid<A>   │
└──────────────┬───────────────┘
               │ implemented by
               ▼
Instances
(IntegerInstances, StringInstances,
 LocalDateInstances, ListInstances,
 PersonInstances)
               │ consumed by
               ▼
Generic syntax/algorithms
(ShowFunctions, EqFunctions,
 OrdFunctions, SemigroupFunctions,
 MonoidFunctions)
```

## Package responsibilities

### `core`

Contains small capability interfaces that are not specifically combination algebras: `Show`, `Eq`, and `Ord`.

### `algebra`

Contains algebraic combination structures: `Semigroup` and `Monoid`.

### `syntax`

Contains reusable algorithms that consume typeclass instances explicitly. A separate class exists for each family to avoid a growing generic utility class.

### `instances`

Contains reusable instances for JDK types and generic instance factories such as list concatenation.

### `examples`

Contains the `Person` domain type, its domain-specific instances, and executable examples.

## Type relationships

`Ord<A>` extends `Eq<A>` because a total ordering determines equality through `compare(a, b) == 0`.

`Monoid<A>` extends `Semigroup<A>` because every monoid provides the associative `combine` operation and additionally supplies an identity value.

`Show<A>` is intentionally independent from those hierarchies.

## Explicit dictionary passing

A generic algorithm receives its behaviour as an argument:

```java
MonoidFunctions.combineAll(values, IntegerInstances.ADDITION);
```

No hidden global lookup occurs. This keeps dependencies local, testable, and visible.

## Multiple instances

A Java class must not be forced to choose one globally privileged behaviour. For example, both integer addition and multiplication form monoids, while `Person` can be ordered by age or by name.

This is why instances remain external values rather than interfaces implemented by domain classes.

## Immutability boundary

The list concatenation monoid does not mutate either input. It returns an immutable copy. This avoids surprising aliasing effects and makes the algebraic operation easier to reason about.

## SOLID

- **SRP:** interfaces, instances, algorithms, and domain state have separate responsibilities.
- **OCP:** add instances and generic consumers without changing domain classes.
- **LSP:** subtype relationships reflect actual capability refinement.
- **ISP:** each typeclass exposes the smallest meaningful interface.
- **DIP:** generic algorithms depend on abstractions supplied by callers.

## KISS, DRY, YAGNI

The milestone intentionally excludes automatic instance discovery, reflection, annotations, DI frameworks, HKT encoding, and advanced FP abstractions. Those features would obscure the core concepts or introduce requirements that do not yet exist.

## Compatibility decision from 0.1.1

`TypeClassFunctions` was removed rather than retained as a deprecated facade. The project is still pre-1.0 and has no stable compatibility promise. Keeping it would duplicate the new syntax API and violate the cleanup goal.

## Deferred design questions

Milestone 0.3 will address executable algebraic laws. HKT representation, ADTs, instance resolution and derivation are intentionally deferred to later roadmap stages.
