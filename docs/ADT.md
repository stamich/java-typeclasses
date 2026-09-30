# Algebraic Data Types

Milestone 0.4 introduces three sealed algebraic data types implemented with Java 21 sealed interfaces and records.

## Why ADTs?

ADTs make the possible states of a value explicit in the type system. They avoid using `null`, magic sentinel values or exceptions for ordinary branching.

The milestone deliberately keeps each ADT small. Generic higher-kinded abstractions are deferred until later milestones.

## Option<A>

`Option<A>` represents an optional value:

```text
Option<A> = Some<A> | None<A>
```

Supported operations:

- `map`,
- `fold`,
- `isDefined`,
- `isEmpty`,
- `getOrElse`,
- factories `some`, `none`, `fromNullable`.

`Some` rejects `null`. `None` carries no value.

## Either<L,R>

`Either<L,R>` represents one of two possible values:

```text
Either<L,R> = Left<L,R> | Right<L,R>
```

By convention the right branch represents the successful path, so `map` is right-biased. `mapLeft` explicitly transforms the left branch.

Supported operations:

- `map`,
- `mapLeft`,
- `fold`,
- `isLeft`,
- `isRight`,
- factories `left` and `right`.

`flatMap` is intentionally absent. It will become more meaningful when the project introduces `Monad` and can explain the relationship between a concrete method and the generic typeclass.

## Validated<E,A>

`Validated<E,A>` represents successful validation or one or more validation errors:

```text
Validated<E,A> = Valid<E,A> | Invalid<E,A>
```

`Invalid` requires a non-empty list and defensively copies it.

Supported operations:

- `map`,
- `fold`,
- `isValid`,
- `isInvalid`,
- factories `valid` and `invalid`.

Although `Invalid` can store multiple errors, 0.4 does not yet implement generic error accumulation between independent validations. That behavior naturally belongs to an Applicative abstraction planned for milestone 0.6.

## Compositional typeclass instances

The `instances` package contains factories that derive instances for an ADT from instances for its components.

Examples:

```text
Eq<A>                      -> Eq<Option<A>>
Show<A>                    -> Show<Option<A>>
Eq<L> + Eq<R>              -> Eq<Either<L,R>>
Show<L> + Show<R>          -> Show<Either<L,R>>
Eq<E> + Eq<A>              -> Eq<Validated<E,A>>
Show<E> + Show<A>          -> Show<Validated<E,A>>
```

This is the first project milestone where typeclass instances are explicitly composed from other instances.

## Why no Tagless Final in 0.4?

ADTs solve a different problem from Tagless Final. ADTs model data and branching. Tagless Final abstracts programs over effect capabilities.

Java cannot directly express Scala's `F[_]`. Introducing a pseudo-Tagless-Final API before an HKT encoding would either hard-code result types or hide the central idea.

The planned progression is therefore:

```text
ADTs
  -> Kind<F,A>
  -> Functor / Applicative / Monad
  -> effect types
  -> Tagless Final
```
