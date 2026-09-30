# Architecture

## Overview

Milestone 0.4 keeps the project deliberately small and explicit. The production code is divided by responsibility:

```text
core/       basic typeclass contracts
algebra/    algebraic typeclasses
syntax/     generic helper functions
instances/  concrete and compositional instances
data/       algebraic data types
examples/   executable educational examples
```

Reusable law definitions remain test infrastructure under `src/test/java/.../laws`.

## Core relationships

```text
Show<A>
Eq<A> <--- Ord<A>

Semigroup<A> <--- Monoid<A>
```

Domain and data types do not implement these interfaces. Typeclass instances are separate values.

## ADT layer

```text
Option<A>
├── Some<A>
└── None<A>

Either<L,R>
├── Left<L,R>
└── Right<L,R>

Validated<E,A>
├── Valid<E,A>
└── Invalid<E,A>
```

The sealed hierarchy makes variants exhaustive and explicit.

## Compositional instances

Milestone 0.4 introduces instance factories that depend on component instances:

```text
Eq<A> ----------------------> Eq<Option<A>>
Show<A> --------------------> Show<Option<A>>

Eq<L> + Eq<R> -------------> Eq<Either<L,R>>
Show<L> + Show<R> ---------> Show<Either<L,R>>

Eq<E> + Eq<A> -------------> Eq<Validated<E,A>>
Show<E> + Show<A> ---------> Show<Validated<E,A>>
```

This dependency is explicit through method parameters; there is no global registry or implicit resolution mechanism.

## SOLID / KISS / DRY / YAGNI

### SRP

- ADTs model data alternatives.
- typeclasses describe capabilities/algebra.
- instance holders provide concrete interpretations.
- syntax classes provide reusable generic operations.
- law helpers verify algebraic contracts.

### OCP

New instances can be added without modifying the represented ADT or domain type.

### DIP

Generic code depends on `Eq`, `Show`, `Semigroup`, `Monoid`, etc., not on particular concrete policies.

### KISS

ADT APIs expose only operations needed by this milestone.

### DRY

Equality and rendering for structured values are implemented once per ADT in instance factories. Law logic from 0.3 is reused unchanged.

### YAGNI

The milestone intentionally excludes:

- HKT encoding,
- `Functor`, `Applicative`, `Monad`,
- generic `flatMap` abstractions,
- effect types,
- Tagless Final,
- reflection-based instance resolution.

These concepts are sequenced later in the roadmap.

## Why Tagless Final is later

Tagless Final becomes substantially more meaningful once Java code can represent an effect constructor `F` using `Kind<F,A>`, define higher-kinded typeclasses and provide effect interpreters. Introducing it before those layers would either hard-code effects or obscure the concept.
