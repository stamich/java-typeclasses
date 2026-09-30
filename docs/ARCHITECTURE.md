# Architecture — Milestone 0.3

## Overview

Milestone 0.3 preserves the small production architecture from 0.2 and adds a separate verification layer in test scope.

```text
                     Domain types
                         │
                         │ described by
                         ▼
              ┌─────────────────────┐
              │     Typeclasses     │
              │                     │
              │ Show<A>             │
              │ Eq<A> <- Ord<A>     │
              │ Semigroup<A>        │
              │     ^               │
              │     │               │
              │ Monoid<A>           │
              └─────────┬───────────┘
                        │ implemented as explicit values
                        ▼
                    Instances
                        │
                        │ exercised by
              ┌─────────▼───────────┐
              │ Syntax / algorithms │
              └─────────────────────┘

Test verification layer:

Instances + generated values
            │
            ▼
      reusable Laws
            │
            ▼
    jqwik properties
```

## Production packages

### `core`

Defines non-algebraic/general typeclasses: `Show`, `Eq`, and `Ord`.

### `algebra`

Defines `Semigroup` and `Monoid`.

### `instances`

Contains reusable instances for JDK types.

### `examples`

Contains the `Person` record, its external instances and executable examples.

### `syntax`

Contains small generic algorithms grouped by typeclass family. This avoids a growing utility god class.

## Test packages

### `laws`

Contains reusable executable laws. They are deliberately test infrastructure rather than production API.

### `properties`

Contains jqwik property suites applying the laws to concrete instances.

### existing focused unit-test packages

Retain example-based tests for direct behavior, edge cases and readability.

## Dependency direction

Production code never depends on test laws or jqwik:

```text
core/algebra <- instances/examples/syntax

production code
     ▲
     │ tested by
laws + properties + JUnit tests
```

## SOLID / KISS / DRY / YAGNI

- **SRP:** each typeclass, syntax helper, law family and property suite has one purpose.
- **OCP:** new instances can be added without modifying domain models or generic algorithms.
- **DIP:** generic algorithms and laws depend on abstractions such as `Eq` and `Semigroup`.
- **KISS:** instance passing remains explicit; no resolver or reflection is introduced.
- **DRY:** algebraic laws are encoded once and reused by many property suites.
- **YAGNI:** law helpers remain test-only until there is a demonstrated need to publish them.

## Naming rule for instance constants

When one holder exposes several typeclass families, names include the family:

```text
SHOW_COMPACT
EQ_NAME
ORD_AGE
```

This scales better than ambiguous names such as `BY_NAME`.

## Null policy

The project still does not impose one global null policy. Instances are tested with their intended non-null domains. Null-handling semantics may be introduced later only if there is a concrete use case.
