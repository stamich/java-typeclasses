# Roadmap

## 0.1 — Initial proof of concept

Early exploration of typeclass-like patterns in Java.

## 0.1.1 — Foundation hardening

Java 21 baseline, explicit `Show<A>`, dictionary passing, separated instances and documentation cleanup.

## 0.2 — Basic algebraic typeclasses

`Eq`, `Ord`, `Semigroup`, `Monoid`, standard instances and syntax helpers.

## 0.3 — Typeclass laws

Executable laws, jqwik property testing and lawful/unlawful examples.

## 0.4 — Algebraic data types

`Option`, `Either`, `Validated`, composed `Eq` / `Show` instances and ADT property tests.

## 0.4.1 — ADT hardening and smart constructors

**Current milestone.**

- `NonEmptyList<A>`,
- `Invalid<E,A>` backed by `NonEmptyList<E>`,
- `Semigroup<NonEmptyList<A>>`,
- smart constructors,
- typed validation errors,
- invariant-safe domain values,
- Java interoperability helpers for `Option` / `Either`,
- domain-level accumulated validation.

## 0.5 — Higher-kinded type encoding

- `Kind<F,A>`,
- witness types (`OptionK`, `EitherK`, `ValidatedK`),
- isolated narrowing/widening helpers,
- adapters from existing concrete ADTs,
- documentation of Java type-erasure limitations.

## 0.6 — Functor / Applicative / Monad

- `Functor<F>`,
- `Applicative<F>`,
- `Monad<F>`,
- `MonadError<F,E>` where justified,
- instances for `Option` and right-biased `Either`,
- Applicative validation accumulation for `Validated`,
- Functor / Applicative / Monad laws.

## 0.7 — Foldable / Traverse / Bifunctor

- `Foldable<F>`,
- `Traverse<F>`,
- `Bifunctor<F>`,
- `sequence` / `traverse`,
- examples such as `List<Option<A>> -> Option<List<A>>`,
- corresponding laws.

## 0.8 — Effects and natural transformations

- `Id<A>`,
- `Eval<A>`,
- educational `IO<A>`,
- `FunctionK<F,G>`,
- effect interpreters,
- stack-safety discussion.

## 0.9 — Kleisli and effectful composition

- `Kleisli<F,A,B>`,
- composition of effectful functions,
- Reader-style dependency passing where useful,
- workflow examples.

## 0.10 — Tagless Final

- algebras/capabilities parameterized by `F`,
- programs expressed only in terms of algebras and typeclasses,
- interpreters for `Id`, `Either` and `IO`,
- natural transformations between interpreters,
- comparison with Scala/Cats Tagless Final.

## 0.11 — Free Monad

- `Free<F,A>`,
- algebra ASTs,
- interpreters via natural transformations,
- explicit comparison: Tagless Final vs Free Monad.

## 0.12 — Optics

- `Lens<S,A>`,
- `Prism<S,A>`,
- optional focus / traversal concepts,
- composition for immutable nested updates.

## 0.13 — Advanced typeclasses

Candidates include `SemigroupK`, `MonoidK`, `Alternative`, richer error typeclasses and advanced validation composition.

## 0.14 — Derivation and resolution experiments

- generated `Eq` / `Show` for records,
- optional annotation processing,
- optional explicit registry / `summon` experiment,
- API naming and package stabilization.

## 0.15 — Performance and ergonomics

- JMH where meaningful,
- allocation analysis,
- API simplification,
- compatibility review.

## 1.0 — Stable educational release

Stable API, complete docs, Java-vs-Scala/Cats comparisons, examples from first-order typeclasses through Tagless Final / Free / optics, and law-checked standard instances.
