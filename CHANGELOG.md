# Changelog

All notable changes to this project are documented in this file.

The project follows an incremental milestone model. Version 0.1 is treated as the original proof of concept; 0.1.1 is its first hardened release.

## [0.1.1] - 2026-09-30

### Added

- Java 21 toolchain baseline.
- `Show<A>` as the minimal first-order typeclass contract.
- `TypeClassFunctions.show(...)` demonstrating explicit dictionary passing.
- `StringInstances`, `IntegerInstances`, and `LocalDateInstances`.
- `Person` example record with domain validation.
- compact and verbose `Show<Person>` instances.
- executable `ShowExample`.
- JUnit tests for the core API, standard instances, domain validation, and multiple-instance behaviour.
- JaCoCo report generation.
- sources JAR and Javadoc JAR generation.
- GitHub Actions build matrix for Java 21, 25, and 27.
- `docs/ARCHITECTURE.md`.
- `docs/TYPECLASSES.md`.
- `docs/ROADMAP.md`.
- Apache License 2.0.

### Changed

- Replaced the original GitLab template README with project-specific documentation.
- Renamed the Gradle root project to `java-typeclasses`.
- Changed the Gradle plugin from `java` to `java-library`.
- Set project version to `0.1.1`.
- Updated the Gradle wrapper configuration target from Gradle 8.2 to 9.8.0.
- Standardised source packages under `io.codeswarm.typeclasses`.
- Separated reusable core abstractions, platform instances, and educational examples.
- Enabled compiler linting and UTF-8 consistently.

### Removed

- Placeholder GitLab onboarding documentation.
- Legacy/unused source structure from the proof-of-concept milestone.
- Unused IDE/build artefacts and obsolete generated files from the milestone source tree.
- Premature abstractions not required to explain milestone 0.1.1.

### Design notes

- Typeclass instances remain independent from domain classes.
- Instance dependencies are passed explicitly.
- No reflection, service locator, registry, dependency-injection framework, HKT encoding, or automatic derivation is introduced in this release.
