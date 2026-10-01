# Roadmap

## Completed

### 0.1 / 0.1.1 — Foundation

Initial proof of concept followed by repository, build, documentation, testing, and typeclass-foundation hardening.

### 0.2 — Basic algebraic typeclasses

`Eq`, `Ord`, `Semigroup`, `Monoid`, reusable syntax functions, and standard instances.

### 0.3 — Laws and property-based testing

Reusable algebraic law checks and jqwik property suites.

### 0.4 — Algebraic data types

`Option`, `Either`, `Validated`, sealed ADTs, compositional `Eq` and `Show` instances.

### 0.4.1 — ADT hardening

`NonEmptyList`, smart constructors, typed validation errors, domain invariants, and lawful `Semigroup<NonEmptyList<A>>`.

### 0.5 — Higher-Kinded Type Encoding

`Kind<F, A>`, witness types, partial type application, and centralized widen/narrow helpers.

## Planned

### 0.6 — Core higher-order typeclasses

- `Functor<F>`
- `Applicative<F>`
- `Monad<F>`
- `MonadError<F, E>` where the encoding remains practical
- laws for the new typeclasses
- instances for `OptionK`, `EitherK<E>`, and `ValidatedK<E>` as appropriate

### 0.7 — Structural typeclasses

- `Foldable<F>`
- `Traverse<F>`
- `Bifunctor<F>`
- `sequence` / `traverse`
- corresponding laws and examples

### 0.8 — Effect types and natural transformations

- `Id`
- `Eval`
- minimal educational `IO`
- `FunctionK<F, G>`
- natural-transformation examples

### 0.9 — Kleisli and effectful composition

- `Kleisli<F, A, B>`
- composition of effectful functions
- Reader-style examples

### 0.10 — Tagless Final

- algebras parameterized by effect witness `F`
- programs written against abstract capabilities
- multiple interpreters
- tests using pure interpreters

### 0.11 — Free Monad

- `Free<F, A>`
- algebra ASTs
- interpreters via natural transformations
- comparison with Tagless Final

### 0.12 — Optics

- `Lens`
- `Prism`
- `Optional`
- immutable nested updates and composition

### 0.13 — Advanced composition

- `SemigroupK`
- `MonoidK`
- `Alternative`
- richer error accumulation

### 0.14 — Derivation and ergonomics

- derived instances where useful
- optional instance resolution experiments
- summon-like ergonomics without hiding the underlying model

### 0.15 — Performance and API hardening

- JMH where measurements are meaningful
- allocation analysis
- API cleanup and compatibility review

### 1.0 — Stable educational release

Stable public API, complete documentation, examples, laws, and a coherent Java-to-functional-programming learning path.
