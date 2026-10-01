# Algebraic data types

Milestone 0.4 introduced Java 21 sealed ADTs. Milestone 0.4.1 strengthens them with `NonEmptyList` and domain invariants.

## Sum types

`Option`, `Either` and `Validated` are modeled as sealed sum types:

```text
Option<A>              Either<L,R>            Validated<E,A>
├── Some<A>            ├── Left<L,R>          ├── Valid<E,A>
└── None<A>            └── Right<L,R>         └── Invalid<E,A>
```

## Product types

Records are used where all components can safely be exposed through canonical construction, for example `User` and `NonEmptyList`.

## Non-empty structures

`NonEmptyList<A>` stores a mandatory `head` and an immutable `tail`.

```text
NonEmptyList<A>
  head: A
  tail: List<A>
```

This structurally rules out the empty state.

`Invalid<E,A>` therefore stores `NonEmptyList<E>` rather than `List<E>`.

## Minimal operations

The project intentionally keeps concrete ADT operations small:

- `Option`: `map`, `fold`, `getOrElse`, branch checks,
- `Either`: `map`, `mapLeft`, `fold`, branch checks,
- `Validated`: `map`, `fold`, branch checks,
- `NonEmptyList`: `map`, `concat`, `toList`.

`flatMap`, generic `traverse`, Applicative composition and effectful operations are deferred until their corresponding abstractions exist.

## Smart constructors and invariants

ADT results are also used to create domain values safely. See [SMART_CONSTRUCTORS.md](SMART_CONSTRUCTORS.md).

## Algebraic connection

`NonEmptyList` has a lawful concatenation `Semigroup` but no lawful empty identity. An ordinary `List` can have a concatenation `Monoid` because `List.of()` is an identity element.

## HKT participation from milestone 0.5

The ADTs keep their ordinary Java APIs while also implementing the higher-kinded encoding:

```text
Option<A>       -> Kind<OptionK,A>
Either<L,R>     -> Kind<EitherK<L>,R>
Validated<E,A>  -> Kind<ValidatedK<E>,A>
```

This does not add runtime wrappers and does not alter `map`, `fold`, smart constructors or validation behavior. It only makes the type constructors addressable by generic abstractions that will be introduced in later milestones.

`Either` and `Validated` demonstrate partial type application: their left/error parameter is fixed in the witness type so the remaining value parameter can occupy the `A` position of `Kind<F,A>`.
