# Java Typeclasses

An educational implementation of the **typeclass pattern in modern Java**.

Milestone **0.1.1** is a foundation-hardening release. It keeps the code deliberately small and explicit while establishing the architecture, documentation, tests, and build conventions used by later milestones.

## Goals

The project demonstrates how behaviour can be defined independently from domain types and passed explicitly to generic algorithms.

Milestone 0.1.1 focuses on:

- explicit dictionary passing;
- separation of domain data from typeclass instances;
- multiple instances for the same domain type;
- instances for types that cannot be modified, such as JDK classes;
- a clean Java 21 baseline;
- tests, Javadoc, JaCoCo, and CI;
- a stable foundation for future algebraic typeclasses.

It intentionally does **not** yet implement `Eq`, `Ord`, `Semigroup`, `Monoid`, higher-kinded type encodings, `Functor`, `Applicative`, `Monad`, instance registries, reflection-based resolution, or automatic derivation. Those belong to later milestones.

## What is a typeclass here?

Java does not provide native typeclasses. This project models the pattern using ordinary generic interfaces whose implementations are values passed to generic algorithms.

```java
@FunctionalInterface
public interface Show<A> {
    String show(A value);
}
```

A domain model does not implement `Show`:

```java
public record Person(String name, int age) {
}
```

Instead, behaviour is defined separately:

```java
public static final Show<Person> COMPACT_SHOW = Person::name;

public static final Show<Person> VERBOSE_SHOW =
        person -> "%s (%d)".formatted(person.name(), person.age());
```

Both instances can coexist:

```java
var person = new Person("Alice", 30);

COMPACT_SHOW.show(person); // Alice
VERBOSE_SHOW.show(person); // Alice (30)
```

This separation is the key design decision of the project.

## Explicit dictionary passing

Generic code receives the behaviour it needs explicitly:

```java
var result = TypeClassFunctions.show(person, PersonInstances.VERBOSE_SHOW);
```

No reflection, global registry, dependency-injection framework, or hidden instance lookup is required.

## Why not inheritance?

With inheritance, behaviour is usually attached to the model itself. That makes it difficult or impossible to:

- define a typeclass for a class you cannot modify;
- define several equally valid instances for one type;
- keep domain data independent from unrelated behaviour.

For example, this project defines `Show<LocalDate>` even though `LocalDate` is a JDK class.

## Project structure

```text
src/main/java/io/codeswarm/typeclasses/
├── core/
│   ├── Show.java
│   └── TypeClassFunctions.java
├── instances/
│   ├── IntegerInstances.java
│   ├── LocalDateInstances.java
│   └── StringInstances.java
└── examples/
    ├── Person.java
    ├── PersonInstances.java
    └── ShowExample.java
```

Supporting documentation is stored in `docs/`.

## Requirements

- Java 21 or newer
- Gradle 9.8.0

The project uses Java 21 as its source/toolchain baseline. CI verifies the project on Java 21, 25, and 27.

## Build

```bash
gradle clean build
```

When this milestone is applied to the original repository, regenerate its existing Gradle Wrapper for 9.8.0 with `gradle wrapper --gradle-version 9.8.0`. The source archive includes the target wrapper properties, but not a regenerated binary wrapper JAR.

## Tests and quality checks

```bash
gradle clean check javadoc
```

JaCoCo reports are generated under:

```text
build/reports/jacoco/test/html/
```

Javadoc is generated under:

```text
build/docs/javadoc/
```

## Running the example

Compile the project and run:

```text
io.codeswarm.typeclasses.examples.ShowExample
```

Expected output:

```text
Alice
Alice (30)
42
2026-09-30
```

## Design principles

The milestone follows:

- **SOLID** — especially SRP and dependency inversion through explicit behavioural interfaces;
- **KISS** — no registry, reflection, DI framework, or HKT encoding yet;
- **DRY** — shared generic behaviour lives in small reusable abstractions;
- **YAGNI** — only abstractions required to explain the current milestone are implemented;
- **composition over inheritance** — behaviour is supplied as values rather than embedded into domain hierarchies.

See [Architecture](docs/ARCHITECTURE.md) for details.

## Roadmap

The planned evolution is documented in [ROADMAP.md](docs/ROADMAP.md).

The next milestone, **0.2**, is expected to introduce the first algebraic typeclasses such as `Eq`, `Ord`, `Semigroup`, and `Monoid` while keeping the explicit instance model established here.

## Changelog

See [CHANGELOG.md](CHANGELOG.md).

## License

Apache License 2.0. See [LICENSE](LICENSE).
