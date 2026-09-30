# Changelog

All notable changes to this educational project are documented here.

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
