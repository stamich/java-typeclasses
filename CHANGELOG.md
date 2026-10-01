# Changelog

All notable changes to this educational project are documented here.

## 0.5.0

### Added

- minimal higher-kinded type encoding `Kind<F,A>`,
- documented `hkt` package,
- `OptionK`, `EitherK<L>` and `ValidatedK<E>` witness types,
- `OptionKinds`, `EitherKinds` and `ValidatedKinds`,
- centralized `widen` / `narrow` conversions,
- HKT round-trip unit tests,
- `HktExample`,
- `docs/HKT.md`.

### Changed

- project version updated from `0.4.1` to `0.5.0`,
- `Option<A>` now implements `Kind<OptionK,A>`,
- `Either<L,R>` now implements `Kind<EitherK<L>,R>`,
- `Validated<E,A>` now implements `Kind<ValidatedK<E>,A>`,
- project description, README, ADT documentation, architecture and roadmap updated for the HKT layer.

### Removed / intentionally omitted

- no `Kind2` hierarchy is introduced because partial witness types are sufficient for the next milestones,
- no `Functor`, `Applicative`, `Monad`, `Traverse`, effect type, Kleisli or Tagless Final implementation is added yet,
- no reflection, annotation processing, registry or runtime witness objects are introduced,
- no obsolete scripts or generated build outputs are retained.

### Design notes

- witness types contain no runtime state and exist only to identify type constructors,
- binary ADTs use partial type application by fixing one parameter in the witness type,
- widening is cast-free because the ADTs implement `Kind` directly,
- unavoidable unchecked narrowing is isolated in exactly three helper methods,
- the milestone intentionally establishes infrastructure rather than higher-order behavior, following SOLID, KISS, DRY and YAGNI.

## 0.4.1

### Added

- immutable `NonEmptyList<A>` with `map`, `concat`, `toList` and size operations,
- derived `Eq<NonEmptyList<A>>` and `Show<NonEmptyList<A>>`,
- concatenation `Semigroup<NonEmptyList<A>>`,
- jqwik law checks for non-empty-list equality and associativity,
- `Option.when`,
- `Either.cond`, `Either.fromNullable` and `Either.fromOptional`,
- sealed typed `ValidationError` hierarchy,
- invariant-safe `UserId`, `PersonName` and `Age` smart constructors,
- validated `User` domain record,
- domain-level `UserValidator` accumulating errors into `Validated`,
- `SmartConstructorExample` and `ValidatedUserExample`,
- `docs/SMART_CONSTRUCTORS.md`.

### Changed

- project version updated from `0.4.0` to `0.4.1`,
- `Invalid<E,A>` now stores `NonEmptyList<E>` instead of `List<E>`,
- `Validated.invalid` now requires at least one error structurally,
- `Validated.fold` exposes `NonEmptyList<E>` on the invalid branch,
- `ValidatedInstances` updated for the stronger error representation,
- README, ADT, architecture, roadmap and implementation tasks updated.

### Removed / intentionally omitted

- removed the runtime empty-list validation from `Invalid`; the type now guarantees non-emptiness,
- no `Monoid<NonEmptyList<A>>` is provided because no lawful empty identity exists,
- no `flatMap`, HKT encoding, generic `Applicative` validation, effects or Tagless Final code is introduced yet,
- no obsolete scripts or generated build outputs are retained.

### Design notes

- public smart constructors model expected failures with ADTs instead of exceptions,
- invalid domain states cannot be created through the public APIs of `UserId`, `PersonName` and `Age`,
- typed validation errors replace stringly-typed domain errors,
- explicit domain-level validation accumulation is temporary and intentionally precedes the generic Applicative solution,
- the milestone continues to follow SOLID, KISS, DRY and YAGNI.

## 0.4.0

### Added

- sealed `Option<A>` ADT with `Some<A>` and `None<A>`,
- sealed `Either<L,R>` ADT with `Left<L,R>` and `Right<L,R>`,
- sealed `Validated<E,A>` ADT with `Valid<E,A>` and `Invalid<E,A>`,
- minimal ADT operations (`map`, `fold` and branch helpers),
- safe factories for all ADTs,
- non-empty immutable error invariant for `Invalid`,
- `OptionInstances` with derived `Eq` and `Show`,
- `EitherInstances` with derived `Eq` and `Show`,
- `ValidatedInstances` with derived `Eq` and `Show`,
- unit tests for all ADTs and compositional instance factories,
- jqwik properties validating `Eq` laws for composed ADT instances,
- `AdtExample`,
- `docs/ADT.md`,
- extended roadmap covering HKT, higher-kinded typeclasses, effects and Tagless Final.

### Changed

- project version updated from `0.3.0` to `0.4.0`,
- project description updated to include ADTs and compositional instances,
- README expanded with ADT examples and the post-0.4 roadmap,
- architecture documentation updated with the data layer and composed-instance flow,
- laws documentation extended to cover ADT equality properties,
- implementation task list rewritten for milestone 0.4.

### Removed / intentionally omitted

- generated build output and temporary compilation files are not part of the milestone artifact,
- no obsolete helper scripts are retained,
- no compatibility aliases are added for pre-1.0 APIs,
- no Tagless Final code is introduced yet,
- no HKT encoding is introduced yet,
- no `Functor`, `Applicative` or `Monad` is introduced yet,
- no generic validation accumulation API is introduced before `Applicative` exists.

### Design notes

- ADTs are modeled with Java 21 sealed interfaces and records,
- variants reject invalid null state where appropriate,
- `Invalid` enforces at least one validation error,
- typeclass instances remain external to represented data types,
- composed instances receive their dependencies explicitly,
- law helpers remain test-only infrastructure,
- the project continues to follow SOLID, KISS, DRY and YAGNI.

## 0.3.0

### Added

- executable laws for `Eq`, `Ord`, `Semigroup` and `Monoid`,
- jqwik property-based testing,
- lawful-instance property suites,
- intentionally unlawful semigroup example.

## 0.2.0

### Added

- `Eq<A>`, `Ord<A>`, `Semigroup<A>` and `Monoid<A>`,
- standard typeclass instances,
- dedicated syntax helpers.

## 0.1.1

### Changed

- hardened and documented the initial proof of concept,
- introduced the explicit `Show<A>` model and dictionary passing.

## 0.1

Initial proof of concept.
