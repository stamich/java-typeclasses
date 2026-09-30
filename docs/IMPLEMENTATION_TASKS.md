# Milestone 0.2 — Detailed Implementation Tasks

## Goal

Turn the hardened 0.1.1 foundation into the first useful algebraic typeclass release without introducing higher-kinded types or hidden instance resolution.

## Task 1 — Freeze and verify the 0.1.1 baseline

1. Preserve the 0.1.1 public concepts: `Show<A>`, external instances, explicit dictionary passing, `Person` as an independent domain record.
2. Remove generated build output before starting.
3. Use the 0.1.1 source tree as the only migration source; do not restore obsolete scripts or experimental files from pre-hardening revisions.

## Task 2 — Version and build metadata

1. Change project version from `0.1.1` to `0.2.0`.
2. Keep Java 21 as the minimum toolchain.
3. Keep JUnit, JaCoCo, source JAR and Javadoc JAR configuration.
4. Keep strict compiler warnings with `-Xlint:all`.
5. Keep CI verification on Java 21, 25 and 27.

## Task 3 — Introduce `Eq<A>`

1. Add `core/Eq.java`.
2. Keep the interface functional with only `eqv` as the abstract operation.
3. Add `neqv` as a derived default method.
4. Document that equality is selectable and independent from `Object.equals`.
5. Add focused unit tests.

## Task 4 — Introduce `Ord<A>`

1. Add `core/Ord.java` extending `Eq<A>`.
2. Add abstract `compare`.
3. Derive `eqv`, `lessThan`, `greaterThan`, `min`, and `max` from `compare`.
4. Avoid extending `Comparator` in this milestone to keep the typeclass model independent and minimal.
5. Add unit tests for all derived operations.

## Task 5 — Introduce the algebra package

1. Add `algebra/Semigroup.java` with only `combine`.
2. Document associativity as a law rather than trying to encode it in the Java type system.
3. Add `algebra/Monoid.java` extending `Semigroup` with `empty`.
4. Document left and right identity laws.
5. Add representative unit tests.

## Task 6 — Refactor generic helper functions

1. Remove `core/TypeClassFunctions.java`.
2. Add `syntax/ShowFunctions.java`.
3. Add `syntax/EqFunctions.java`.
4. Add `syntax/OrdFunctions.java`.
5. Add `syntax/SemigroupFunctions.java`.
6. Add `syntax/MonoidFunctions.java`.
7. Give each utility class exactly one typeclass-family responsibility.
8. Validate required strategy/instance arguments at API boundaries.
9. Make semigroup folding reject empty input.
10. Make monoid folding use `empty()` for empty input.

## Task 7 — Expand standard instances

### Integer

Add `SHOW`, `EQ`, `ORD`, `ADDITION`, and `MULTIPLICATION`.

### String

Add `SHOW`, `EQ`, `LEXICOGRAPHIC_ORD`, `CASE_INSENSITIVE_ORD`, and `CONCATENATION`.

### LocalDate

Keep `ISO_SHOW` and add `EQ` and chronological `ORD`.

### List

Add a generic `concatenation()` monoid factory. Do not mutate input lists; return an immutable copy.

## Task 8 — Expand the Person example

1. Keep `Person` free of typeclass interfaces.
2. Keep `COMPACT_SHOW` and `VERBOSE_SHOW`.
3. Add `BY_ALL_FIELDS` and case-insensitive `BY_NAME` equality instances.
4. Add `BY_AGE` and `BY_NAME_ORDER` orderings.
5. Test that different policies produce different valid results.

## Task 9 — Add executable examples

1. Migrate `ShowExample` to `ShowFunctions`.
2. Add `EqExample` showing two equality policies.
3. Add `OrdExample` showing age and name ordering.
4. Add `SemigroupExample` showing a non-empty maximum fold.
5. Add `MonoidExample` showing sum and product from the same `List<Integer>`.

## Task 10 — Unit-test the complete milestone API

1. Test `Eq` and `Ord` default methods.
2. Test representative `Semigroup` and `Monoid` behaviour.
3. Test all syntax helpers and exceptional cases.
4. Test every standard instance family.
5. Test `Person` validation and all domain instances.
6. Do not add fake example-based “law proofs”; reserve property law testing for 0.3.

## Task 11 — Javadoc

1. Document every public type.
2. Document every public method and public instance field where the intent is not self-evident.
3. Explain algebraic laws in the relevant interfaces.
4. Explain the design reason behind external instances and explicit dictionary passing.

## Task 12 — Documentation

1. Rewrite README for milestone 0.2.
2. Update `ARCHITECTURE.md` with type relationships and package boundaries.
3. Expand `TYPECLASSES.md` with equality, ordering and algebra.
4. Add `ALGEBRA.md`.
5. Update `ROADMAP.md` and mark 0.2 as current.
6. Add a complete 0.2.0 entry to `CHANGELOG.md`.

## Task 13 — Cleanup

1. Delete `TypeClassFunctions` and its old test.
2. Delete old aggregated standard-instance tests replaced by focused per-family tests.
3. Do not include obsolete shell scripts or generated `build`, `.gradle`, IDE, coverage or class files.
4. Keep only wrapper configuration that belongs to the source distribution.

## Task 14 — Verification

1. Compile production sources with Java 21 and `-Xlint:all`.
2. Compile and run all unit tests through Gradle when the wrapper is available.
3. Generate Javadoc with doclint.
4. Run all executable examples and compare output with README documentation.
5. Verify no stale references to version 0.1.1 remain except historical documentation and changelog.
6. Package the clean source tree as milestone 0.2.
