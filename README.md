# Java Typeclasses

An educational implementation of typeclass-oriented functional programming in modern Java.

Milestone **0.4.1** hardens the ADT layer introduced in 0.4.0 with non-empty collections, smart constructors, typed validation errors and domain invariants.

## Goals

The project demonstrates how concepts associated with Scala, Cats and Haskell can be represented explicitly in Java while keeping the implementation small and understandable.

The project currently covers:

- explicit typeclass instances and dictionary passing,
- `Eq`, `Ord`, `Show`, `Semigroup` and `Monoid`,
- executable algebraic laws and jqwik property tests,
- sealed ADTs: `Option`, `Either` and `Validated`,
- compositional `Eq` and `Show` instances,
- `NonEmptyList<A>`,
- smart constructors and invariant-safe domain values,
- typed validation errors,
- domain-level error accumulation with `Validated`.

The project deliberately does **not** yet introduce HKT encoding, `Functor`, `Applicative`, `Monad`, `Traverse`, effects, Kleisli, Tagless Final, Free Monad or optics.

## Requirements

- Java 21 or newer,
- Gradle 9.8.0 through the wrapper configuration.

CI is intended to verify Java 21, 25 and 27.

## Build

```bash
./gradlew clean check
./gradlew javadoc
```

## Project structure

```text
src/main/java/io/codeswarm/typeclasses/
├── algebra/
│   ├── Monoid.java
│   └── Semigroup.java
├── core/
│   ├── Eq.java
│   ├── Ord.java
│   └── Show.java
├── data/
│   ├── Option.java
│   ├── Some.java
│   ├── None.java
│   ├── Either.java
│   ├── Left.java
│   ├── Right.java
│   ├── Validated.java
│   ├── Valid.java
│   ├── Invalid.java
│   └── NonEmptyList.java
├── domain/
│   ├── ValidationError.java
│   ├── BlankUserId.java
│   ├── BlankPersonName.java
│   ├── PersonNameTooLong.java
│   ├── InvalidAge.java
│   ├── UserId.java
│   ├── PersonName.java
│   ├── Age.java
│   ├── User.java
│   └── UserValidator.java
├── examples/
├── instances/
└── syntax/
```

Reusable law helpers remain in `src/test/java/io/codeswarm/typeclasses/laws/`.

## NonEmptyList

`NonEmptyList<A>` encodes non-emptiness structurally:

```java
NonEmptyList<Integer> values = NonEmptyList.of(1, 2, 3);
```

There is no public way to create an empty value. This lets `Invalid<E,A>` represent failed validation without a separate runtime `isEmpty()` guard.

```java
public record Invalid<E, A>(NonEmptyList<E> errors)
        implements Validated<E, A> {
}
```

`NonEmptyList<A>` has a natural concatenation `Semigroup` but intentionally no `Monoid`:

```text
List<A>          -> Monoid<List<A>>
NonEmptyList<A>  -> Semigroup<NonEmptyList<A>>
```

A monoid requires an identity element; an empty `NonEmptyList` cannot exist.

## Smart constructors

Domain values expose factories that validate raw input and return an ADT instead of throwing for expected validation failures.

```java
Either<ValidationError, UserId> id = UserId.from(" user-42 ");
Either<ValidationError, Age> age = Age.from(42);
```

Invalid input is explicit:

```java
Age.from(-1);       // Left(InvalidAge[-1])
UserId.from(" ");   // Left(BlankUserId[])
```

The constructors of `UserId`, `PersonName` and `Age` are private, so invalid states cannot be created through their public APIs.

See [docs/SMART_CONSTRUCTORS.md](docs/SMART_CONSTRUCTORS.md).

## Typed validation errors

Expected validation failures are modeled as a closed ADT:

```text
ValidationError
├── BlankUserId
├── BlankPersonName
├── PersonNameTooLong
└── InvalidAge
```

This is preferable to using arbitrary strings when callers need to inspect or pattern-match on error categories.

## Validated and error accumulation

`UserValidator` demonstrates accumulation of independent errors:

```java
Validated<ValidationError, User> result =
        UserValidator.validate("", "", -10);
```

The result contains three typed errors in a `NonEmptyList`.

This implementation is intentionally domain-specific. Generic validation composition is deferred until the project has `Applicative`.

## ADT helper factories

Milestone 0.4.1 adds small Java-interoperability helpers:

```java
Option.when(condition, supplier);
Either.cond(condition, rightSupplier, leftSupplier);
Either.fromNullable(value, leftSupplier);
Either.fromOptional(optional, leftSupplier);
```

No `flatMap` is added yet; monadic operations will be introduced together with the generic `Monad` milestone.

## Design principles

- **SRP** — ADTs, domain validation, typeclasses, instances, syntax and laws remain separate.
- **OCP** — new instances and domain validators do not require modifying typeclasses.
- **DIP** — generic functions continue to depend on typeclass abstractions.
- **KISS** — smart constructors use ordinary Java and explicit ADTs.
- **DRY** — law helpers and compositional instances are reused rather than duplicated.
- **YAGNI** — no HKT, generic Applicative validation or effect system is introduced early.

## Examples

`AdtExample` demonstrates `Option`, `Either`, `Validated` and composed `Show` instances.

`SmartConstructorExample` demonstrates validated domain values.

`ValidatedUserExample` demonstrates accumulated typed validation errors.

## Documentation

- [Architecture](docs/ARCHITECTURE.md)
- [Typeclasses](docs/TYPECLASSES.md)
- [Algebra](docs/ALGEBRA.md)
- [Algebraic laws](docs/LAWS.md)
- [Algebraic data types](docs/ADT.md)
- [Smart constructors](docs/SMART_CONSTRUCTORS.md)
- [Implementation tasks](docs/IMPLEMENTATION_TASKS.md)
- [Roadmap](docs/ROADMAP.md)
- [Changelog](CHANGELOG.md)

## Roadmap

```text
0.5   HKT encoding: Kind<F,A>
0.6   Functor / Applicative / Monad / MonadError
0.7   Foldable / Traverse / Bifunctor
0.8   Id / Eval / IO / FunctionK
0.9   Kleisli and effectful composition
0.10  Tagless Final
0.11  Free Monad
0.12  Lens / Prism / Optics
0.13+ advanced typeclasses, derivation and API hardening
1.0   stable educational release
```

## License

Apache License 2.0. See [LICENSE](LICENSE).
