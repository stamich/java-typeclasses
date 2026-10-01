# Java Typeclasses

An educational implementation of typeclass-oriented functional programming in modern Java.

Milestone **0.5.0** introduces a minimal higher-kinded type (HKT) encoding on top of the ADT and algebra layers completed in 0.4.1.

## Goals

The project demonstrates how concepts associated with Scala, Cats and Haskell can be represented explicitly in Java while keeping the implementation small and understandable.

The project currently covers:

- explicit typeclass instances and dictionary passing,
- `Eq`, `Ord`, `Show`, `Semigroup` and `Monoid`,
- executable algebraic laws and jqwik property tests,
- sealed ADTs: `Option`, `Either` and `Validated`,
- compositional `Eq` and `Show` instances,
- `NonEmptyList<A>` and lawful concatenation `Semigroup`,
- smart constructors, typed validation errors and domain invariants,
- a minimal HKT encoding through `Kind<F,A>`,
- witness types and partial type application,
- centralized widen/narrow helpers.

The project deliberately does **not** yet introduce `Functor`, `Applicative`, `Monad`, `Traverse`, effect types, Kleisli, Tagless Final, Free Monad or optics.

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
├── hkt/
│   ├── Kind.java
│   └── package-info.java
├── data/
│   ├── Option.java
│   ├── OptionK.java
│   ├── OptionKinds.java
│   ├── Some.java
│   ├── None.java
│   ├── Either.java
│   ├── EitherK.java
│   ├── EitherKinds.java
│   ├── Left.java
│   ├── Right.java
│   ├── Validated.java
│   ├── ValidatedK.java
│   ├── ValidatedKinds.java
│   ├── Valid.java
│   ├── Invalid.java
│   └── NonEmptyList.java
├── domain/
├── examples/
├── instances/
└── syntax/
```

Reusable law helpers remain in `src/test/java/io/codeswarm/typeclasses/laws/`.

## Higher-kinded type encoding

Java can abstract over values of type `A`, but cannot directly abstract over a type constructor like Scala's `F[_]`. Milestone 0.5 introduces:

```java
public interface Kind<F, A> {
}
```

The `F` parameter is a witness identifying the constructor.

### Option

`Option<A>` now implements:

```text
Kind<OptionK, A>
```

Example:

```java
Option<Integer> option = Option.some(42);
Kind<OptionK, Integer> widened = OptionKinds.widen(option);
Option<Integer> restored = OptionKinds.narrow(widened);
```

### Either and partial type application

`Either<L,R>` has two type parameters, but a future `Functor<F>` needs a unary constructor. The left type is therefore fixed in a witness:

```text
Either<String, Integer>
≈ Kind<EitherK<String>, Integer>
```

Likewise:

```text
Validated<String, Integer>
≈ Kind<ValidatedK<String>, Integer>
```

This models partial type application without adding wrapper objects around the ADTs.

## Widening and narrowing

`widen` requires no cast because each ADT directly implements the relevant `Kind` application.

`narrow` needs a controlled unchecked cast because Java type erasure cannot prove the witness-to-ADT relationship at runtime. Those casts are intentionally isolated in exactly three helpers:

- `OptionKinds.narrow`,
- `EitherKinds.narrow`,
- `ValidatedKinds.narrow`.

The rest of the codebase should not perform HKT-related unchecked casts.

See [docs/HKT.md](docs/HKT.md).

## Existing ADT and domain layer

Milestone 0.5 preserves the 0.4.1 APIs:

- `NonEmptyList<A>` models non-empty collections structurally,
- `Invalid<E,A>` stores `NonEmptyList<E>`,
- `UserId`, `PersonName` and `Age` use smart constructors,
- `ValidationError` is a sealed typed error hierarchy,
- `UserValidator` demonstrates domain-level error accumulation with `Validated`.

No monadic API is added to those types yet.

## Design principles

- **SRP** — HKT mechanics live in `hkt` and `*Kinds` helpers rather than inside unrelated typeclasses.
- **OCP** — new type constructors can participate by introducing their own witness and `Kind` implementation.
- **DIP** — future higher-order typeclasses can depend on `Kind<F,A>` instead of concrete ADTs.
- **KISS** — only one marker abstraction is introduced; there is no `Kind2`, registry or reflection layer.
- **DRY** — unavoidable casts are centralized instead of repeated across future typeclass instances.
- **YAGNI** — `Functor`, `Applicative` and `Monad` remain outside this milestone.

## Examples

`HktExample` demonstrates round-trip widening and narrowing for `Option`, `Either` and `Validated`.

Existing examples continue to demonstrate algebra, ADTs, smart constructors and validation.

## Documentation

- [Architecture](docs/ARCHITECTURE.md)
- [Typeclasses](docs/TYPECLASSES.md)
- [Algebra](docs/ALGEBRA.md)
- [Algebraic laws](docs/LAWS.md)
- [Algebraic data types](docs/ADT.md)
- [Smart constructors](docs/SMART_CONSTRUCTORS.md)
- [Higher-kinded type encoding](docs/HKT.md)
- [Implementation tasks](docs/IMPLEMENTATION_TASKS.md)
- [Roadmap](docs/ROADMAP.md)
- [Changelog](CHANGELOG.md)

## Roadmap

```text
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
