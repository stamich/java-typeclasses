# Roadmap

## 0.1 — Initial proof of concept

Early exploration of typeclass-like patterns in Java.

## 0.1.1 — Foundation hardening

- Java 21 baseline,
- explicit `Show<A>`,
- dictionary passing,
- separated instances,
- CI and documentation cleanup.

## 0.2 — Basic algebraic typeclasses

- `Eq<A>`,
- `Ord<A>`,
- `Semigroup<A>`,
- `Monoid<A>`,
- standard instances,
- dedicated syntax helpers.

## 0.3 — Typeclass laws

- executable `Eq`, `Ord`, `Semigroup` and `Monoid` laws,
- jqwik property-based testing,
- lawful and intentionally unlawful examples.

## 0.4 — Algebraic data types and compositional instances

**Current milestone.**

- `Option<A>` / `Some<A>` / `None<A>`,
- `Either<L,R>` / `Left<L,R>` / `Right<L,R>`,
- `Validated<E,A>` / `Valid<E,A>` / `Invalid<E,A>`,
- `Eq` and `Show` instance composition,
- property tests for derived equality instances,
- ADT documentation.

## 0.5 — Higher-kinded type encoding

Introduce an explicit encoding for Java's missing higher-kinded types:

```text
Kind<F,A>
```

Planned work:

- `Kind<F,A>` marker/encoding,
- witness types such as `OptionK`, `EitherK`,
- safe narrowing helpers isolated in one place,
- discussion of Java type erasure and limitations,
- laws/tests for the encoding boundary,
- adapters between existing concrete ADTs and their `Kind` representation.

The goal is infrastructure, not new behavior.

## 0.6 — Functor, Applicative and Monad

Build the first higher-kinded typeclass hierarchy:

```text
Functor<F>
    ^
Applicative<F>
    ^
Monad<F>
```

Planned instances:

- `Option`,
- right-biased `Either`,
- `Validated` for Applicative error accumulation where appropriate.

Add corresponding laws and property tests.

This milestone should also clarify why `Validated` is naturally Applicative but should not be treated as a fail-fast Monad when accumulating errors.

## 0.7 — Effects and natural transformations

Prepare the execution model needed by Tagless Final.

Planned topics:

- simple `Id<A>`,
- lazy `Eval<A>`,
- small educational `IO<A>` effect,
- `FunctionK<F,G>` / natural transformations,
- effect interpreters,
- stack-safety considerations where relevant,
- Monad instances and laws for supported effects.

The goal is not to compete with Cats Effect or ZIO; it is to make effect polymorphism understandable in Java.

## 0.8 — Tagless Final

Introduce Tagless Final only after HKT and effects exist.

Example direction:

```java
interface UserRepository<F> {
    Kind<F, User> find(UserId id);
}
```

and programs parameterized by an effect constructor:

```text
algebra/capability interfaces
          +
programs parameterized by F
          +
multiple interpreters
```

Planned interpreters may include:

- `Id` for pure deterministic tests,
- `Either` for explicit errors,
- `IO` for real side effects.

Topics:

- programs vs interpreters,
- dependency inversion without framework DI,
- testing without mocks,
- natural transformations between interpreters,
- comparison with Scala 3 / Cats Tagless Final.

## 0.9 — Derivation, resolution and API hardening

Experiments that are useful only after the core model is stable:

- derived `Eq` / `Show` for records,
- optional annotation-processing experiment,
- explicit instance registry / `summon` experiment if it remains educationally useful,
- naming and package stabilization,
- compatibility review,
- publication metadata.

Any implicit-resolution mechanism must remain optional; explicit instances are the conceptual baseline.

## 1.0 — Stable educational release

- stable public API,
- complete documentation,
- Java vs Scala/Cats comparison,
- examples spanning basic typeclasses through Tagless Final,
- law-checked standard instances,
- release/publishing workflow.
