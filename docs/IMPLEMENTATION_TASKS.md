# Milestone 0.1.1 — implementation tasks

## Task 1 — Freeze the 0.1 baseline

1. Tag or branch the existing repository as milestone 0.1.
2. Record the current build command and source tree.
3. Keep the refactor behaviour-focused: remove legacy files only after confirming they are not referenced.

**Done when:** the original state can be recovered and compared with 0.1.1.

## Task 2 — Clean repository metadata and build configuration

1. Rename the Gradle root project from the generic `FunctionalJava` name to `java-typeclasses`.
2. Set `group` to `io.codeswarm` and version to `0.1.1`.
3. Replace the `java` plugin with `java-library`.
4. Configure the Java 21 toolchain.
5. Enable source and Javadoc JAR generation.
6. Upgrade the wrapper target to Gradle 9.8.0.
7. Replace the old JUnit BOM with JUnit 6.1.2.
8. Enable `-Xlint:all`, UTF-8, JaCoCo, and Javadoc verification.
9. Simplify `.gitignore` and remove obsolete generated/IDE files from version control.

**Done when:** `clean`, `compileJava`, `test`, `jacocoTestReport`, and `javadoc` are defined by the build.

## Task 3 — Establish package boundaries

1. Create `io.codeswarm.typeclasses.core` for reusable abstractions.
2. Create `io.codeswarm.typeclasses.instances` for reusable platform/JDK instances.
3. Create `io.codeswarm.typeclasses.examples` for educational models and demos.
4. Mirror the package structure in `src/test/java`.
5. Remove old default-package or legacy package source files once their behaviour is represented in the new structure.

**Done when:** no production code remains in the default package and dependency direction is core <- instances/examples.

## Task 4 — Implement the minimal typeclass contract

1. Add `Show<A>` as a `@FunctionalInterface`.
2. Document its role and its deliberately instance-specific null policy.
3. Do not add a marker `TypeClass` parent interface; it would provide no behaviour in this milestone.

**Done when:** `Show<A>` contains exactly one abstract operation and no dependency on examples.

## Task 5 — Implement explicit dictionary passing

1. Add `TypeClassFunctions` as a non-instantiable utility class.
2. Add `show(A, Show<? super A>)`.
3. Validate only the required dictionary dependency, not the represented value.
4. Do not add reflection, registries, `ServiceLoader`, DI, annotations, or hidden lookup.

**Done when:** generic rendering depends only on `Show` and a caller-supplied instance.

## Task 6 — Add standard instances

1. Add `StringInstances.SHOW`.
2. Add `IntegerInstances.SHOW`.
3. Add `LocalDateInstances.ISO_SHOW` to prove that third-party/JDK types can receive external behaviour.
4. Keep each holder focused on a single represented type.

**Done when:** standard instances are stateless immutable values and contain no unrelated algorithms.

## Task 7 — Add an immutable domain example

1. Implement `Person` as a record.
2. Validate `name` and `age` in the canonical constructor.
3. Keep `Person` independent from `Show`.
4. Add `PersonInstances.COMPACT_SHOW`.
5. Add `PersonInstances.VERBOSE_SHOW`.

**Done when:** two `Show<Person>` implementations coexist without changing `Person`.

## Task 8 — Add an executable demonstration

1. Add `ShowExample`.
2. Demonstrate compact and verbose person rendering.
3. Demonstrate integer rendering.
4. Demonstrate `LocalDate` rendering.
5. Keep the example deterministic and free of external I/O other than stdout.

**Done when:** the example produces the four documented output lines.

## Task 9 — Add unit tests

1. Test generic delegation in `TypeClassFunctions`.
2. Test null-dictionary rejection.
3. Test instance-specific null handling.
4. Test each standard instance.
5. Test `Person` invariants.
6. Test both `Person` instances and their coexistence.

**Done when:** all public milestone behaviour has focused tests and examples are not used as substitutes for assertions.

## Task 10 — Add documentation

1. Replace the GitLab template README.
2. Add `ARCHITECTURE.md` with package boundaries, dependency direction, principles, and null policy.
3. Add `TYPECLASSES.md` explaining the Java encoding and dictionary passing.
4. Add `ROADMAP.md` covering 0.1 through 1.0.
5. Add `IMPLEMENTATION_TASKS.md`.
6. Add Javadoc to every public type and public member, plus useful documentation on test classes/methods for educational clarity.

**Done when:** a new developer can understand the milestone without reading commit history.

## Task 11 — Add CI

1. Add a GitHub Actions workflow.
2. Test Java 21, 25, and 27.
3. Run `clean check javadoc`.
4. Keep workflow permissions read-only unless a future release job needs more.

**Done when:** pull requests verify compilation, tests, coverage report generation, and Javadoc.

## Task 12 — Record changes and remove obsolete files

1. Add Apache-2.0 `LICENSE`.
2. Add `CHANGELOG.md` entry for 0.1.1.
3. Remove the placeholder GitLab README.
4. Remove unreferenced scripts and generated files from 0.1.
5. Remove legacy sources superseded by the new package structure.
6. Do not remove Gradle wrapper scripts/JAR when applying the milestone to the actual repository; regenerate them consistently for 9.8.0.

**Done when:** repository root contains only build, source, documentation, CI, wrapper, and license files needed by the current project.

## Task 13 — Final verification

Run:

```bash
gradle clean check javadoc
```

Then verify:

- no compiler warnings introduced by project sources;
- all tests pass;
- JaCoCo HTML/XML reports exist;
- Javadoc is generated;
- no stale package/source files remain;
- README examples match actual APIs;
- version is exactly `0.1.1`.
