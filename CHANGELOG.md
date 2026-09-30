# Changelog

All notable changes to the project are documented in this file.

## 0.2.0 — Basic Algebraic Typeclasses

### Added

- `Eq<A>` with derived inequality support.
- `Ord<A>` extending `Eq<A>` with comparison, ordering predicates, minimum and maximum.
- `Semigroup<A>` representing associative combination.
- `Monoid<A>` extending `Semigroup<A>` with an identity element.
- Dedicated `ShowFunctions`, `EqFunctions`, `OrdFunctions`, `SemigroupFunctions`, and `MonoidFunctions` syntax helpers.
- Integer equality, ordering, addition monoid and multiplication monoid.
- String equality, case-sensitive and case-insensitive ordering, and concatenation monoid.
- `LocalDate` equality and chronological ordering.
- Immutable-style generic list concatenation monoid.
- Multiple `Eq<Person>` and `Ord<Person>` instances.
- Executable examples for equality, ordering, semigroup and monoid usage.
- Focused tests for all new typeclasses, syntax helpers and instance families.
- `docs/ALGEBRA.md`.
- Detailed milestone 0.2 implementation task list.

### Changed

- Project version advanced from `0.1.1` to `0.2.0`.
- Generic helper architecture changed from one growing `TypeClassFunctions` class to one small syntax class per typeclass family.
- `ShowExample` now uses `ShowFunctions`.
- README now documents the complete basic algebra model and multiple-instance examples.
- Architecture, typeclass documentation and roadmap were updated for milestone 0.2.
- CI verification now runs `clean check javadoc` for Java 21, 25 and 27.

### Removed

- `TypeClassFunctions`, superseded by focused syntax helpers.
- The old aggregated tests from 0.1.1 that were replaced by smaller type-specific test classes.
- No obsolete pre-hardening scripts, generated build output or IDE files are included in the source distribution.

### Design notes

- Domain types remain unaware of their typeclass instances.
- Instance selection remains explicit; no registry, reflection or automatic resolution was introduced.
- `Ord<A>` refines `Eq<A>` and `Monoid<A>` refines `Semigroup<A>`.
- Algebraic laws are documented but systematic property-based verification is intentionally deferred to milestone 0.3.
- HKT, ADTs, `Functor`, `Applicative` and `Monad` remain out of scope for YAGNI reasons.

## 0.1.1 — Foundation Hardening

### Added

- Java 21 toolchain baseline.
- `Show<A>` as the minimal typeclass example.
- Explicit dictionary-passing helper.
- Standard Show instances for Integer, String and LocalDate.
- Person example with compact and verbose rendering instances.
- Unit tests, JaCoCo reporting, Javadoc generation and GitHub Actions CI.
- Architecture, typeclass, roadmap and implementation documentation.
- Apache License 2.0.

### Changed

- Reorganized the original prototype into coherent `core`, `instances` and `examples` packages.
- Replaced the placeholder GitLab README with project-specific documentation.

## 0.1 — Original Prototype

Initial repository state, subsequently treated as milestone 0.1.
