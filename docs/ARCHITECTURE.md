# Architecture

## Layers

```text
Domain values
  UserId / PersonName / Age / User
          |
          | smart constructors / validation
          v
Data / ADT layer
  Option / Either / Validated / NonEmptyList
          |
          | described by external instances
          v
Typeclasses
  Show / Eq / Ord / Semigroup / Monoid
          |
          v
Instances + syntax helpers
          |
          v
Laws and property tests (test-only)
```

## Separation of responsibilities

- `core` contains basic typeclass contracts.
- `algebra` contains algebraic contracts.
- `data` contains immutable ADTs.
- `domain` demonstrates invariant-safe domain modeling.
- `instances` derives behavior externally from data types.
- `syntax` contains generic helper functions.
- test-only `laws` contains reusable executable laws.

## NonEmptyList

```text
List<E> + runtime non-empty check       (0.4.0)
                 |
                 v
NonEmptyList<E> encoded invariant       (0.4.1)
```

This removes one invalid representable state from `Invalid<E,A>`.

## Domain validation

```text
raw fields
   |
   +--> UserId.from ------> Either<ValidationError,UserId>
   +--> PersonName.from --> Either<ValidationError,PersonName>
   `--> Age.from ---------> Either<ValidationError,Age>
                 |
                 v
          UserValidator
                 |
                 v
 Validated<ValidationError,User>
```

The accumulation in `UserValidator` is intentionally concrete. A generic solution will be introduced with `Applicative`.

## Dependency direction

Domain examples depend on ADTs. ADTs do not depend on domain classes. Typeclasses do not depend on either domain examples or specific instances.

This preserves DIP and keeps the educational abstractions reusable.

## Milestone 0.5 — HKT layer

Milestone 0.5 adds a small type-level layer without changing the runtime representation of the ADTs.

```text
                         Kind<F,A>
                            ▲
           ┌────────────────┼─────────────────┐
           │                │                 │
           │                │                 │
      Option<A>        Either<L,A>      Validated<E,A>
           │                │                 │
           │                │                 │
        OptionK          EitherK<L>       ValidatedK<E>
```

Conceptually:

```text
Option<A>       ≈ Kind<OptionK, A>
Either<L,A>     ≈ Kind<EitherK<L>, A>
Validated<E,A>  ≈ Kind<ValidatedK<E>, A>
```

### Boundary of unsafe narrowing

Java erasure means the compiler cannot reconstruct a concrete ADT from `Kind<F,A>` purely from the witness parameter. The unavoidable casts are therefore centralized in:

```text
OptionKinds.narrow
EitherKinds.narrow
ValidatedKinds.narrow
```

This is an explicit architectural boundary: future higher-order typeclass implementations may call those helpers, but should not introduce additional HKT casts.

### Dependency direction

```text
higher-order typeclasses (0.6+)
             │
             ▼
          Kind<F,A>
             ▲
             │
      concrete ADTs + witnesses
```

`Kind` depends on no ADT. Concrete ADTs opt into the encoding. Future generic abstractions can therefore depend on `Kind` rather than concrete data types.
