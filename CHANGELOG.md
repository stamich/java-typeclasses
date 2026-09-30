# Changelog

All notable changes to the project are documented in this file.

## 0.3.0 — Typeclass Laws and Property-Based Testing

### Added

- Reusable test-scope `EqLaws`, `OrdLaws`, `SemigroupLaws`, and `MonoidLaws`.
- jqwik 1.10.1 for property-based testing on the JUnit Platform.
- Property suites for Integer, String, LocalDate, List, and Person instances.
- Custom jqwik generators for dates, immutable lists, and `Person`.
- `UnlawfulInstancesTest`, demonstrating that integer subtraction implements the Java shape of a semigroup but violates associativity.
- `docs/LAWS.md` explaining executable algebraic contracts and property-based verification.
- Detailed milestone 0.3 implementation task list.

### Changed

- Project version advanced from `0.2.0` to `0.3.0`.
- `PersonInstances` names now include their typeclass family: `SHOW_COMPACT`, `SHOW_VERBOSE`, `EQ_ALL_FIELDS`, `EQ_NAME`, `ORD_AGE`, and `ORD_NAME`.
- Existing examples and unit tests were updated for the normalized instance names.
- README, architecture, algebra, typeclass documentation and roadmap now describe executable laws.
- `Semigroup` Javadoc no longer describes law verification as future work.

### Removed

- Ambiguous pre-0.3 `PersonInstances` constant names. No deprecated aliases were retained because the API is still pre-1.0.
- Stale milestone-0.2 documentation describing property-based law verification as future work.
- No generated build output, IDE state, obsolete scripts or unused compatibility shims are included.

### Design notes

- Law helpers remain under `src/test` and therefore do not expand the production API.
- Laws compare algebraic results using explicit `Eq` instances rather than `Objects.equals`.
- Focused unit tests are retained alongside property tests; the two test styles serve complementary purposes.
- jqwik is test-only and does not leak into production dependencies.
- ADTs, HKT, `Functor`, `Applicative`, `Monad`, registries and automatic derivation remain outside milestone 0.3.

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
- Algebraic laws were documented in 0.2 and are executable from milestone 0.3 onward.
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
