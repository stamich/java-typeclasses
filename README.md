# Java Typeclasses

An educational implementation of typeclass-oriented functional programming in modern Java.

Milestone **0.4.0** extends the algebra and executable laws introduced in earlier releases with algebraic data types (ADTs) and compositional typeclass instances.

## Goals

The project demonstrates how concepts commonly associated with Scala, Cats and Haskell can be represented explicitly in Java while keeping the implementation small and understandable.

The project currently covers:

- explicit typeclass instances,
- dictionary passing,
- multiple instances for the same domain type,
- `Eq`, `Ord`, `Show`, `Semigroup` and `Monoid`,
- executable algebraic laws,
- property-based testing with jqwik,
- sealed algebraic data types,
- composition of typeclass instances.

The project deliberately does **not** yet introduce higher-kinded type encoding, `Functor`, `Applicative`, `Monad`, effect types or Tagless Final.

## Requirements

- Java 21 or newer,
- Gradle 9.8.0 through the project wrapper configuration.

CI is intended to verify the project on Java 21, 25 and 27.

## Build

```bash
./gradlew clean check
```

Generate Javadoc:

```bash
./gradlew javadoc
```

## Project structure

```text
src/main/java/io/codeswarm/typeclasses/
├── algebra/
│   ├── Monoid.java
│   └── Semigroup.java
├── core/
│   ├── Eq.java
│   ├── Ord.java
│   └── Show.java
├── data/
│   ├── Either.java
│   ├── Invalid.java
│   ├── Left.java
│   ├── None.java
│   ├── Option.java
│   ├── Right.java
│   ├── Some.java
│   ├── Valid.java
│   └── Validated.java
├── examples/
├── instances/
└── syntax/
```

Reusable law helpers remain test infrastructure:

```text
src/test/java/io/codeswarm/typeclasses/laws/
```

This keeps the production API focused while the project is still pre-1.0.

## Basic typeclass example

```java
Eq<Integer> integerEq = IntegerInstances.EQ;
boolean same = integerEq.eqv(42, 42);
```

Typeclass behavior remains separate from the represented type. A domain object does not have to implement the typeclass interface.

## Algebraic data types

### Option

`Option<A>` models presence or absence without exposing `null` as part of normal program flow.

```java
Option<Integer> result =
        Option.some(21)
                .map(value -> value * 2);
```

The ADT is sealed and has two variants:

```text
Option<A>
├── Some<A>
└── None<A>
```

### Either

`Either<L,R>` models two alternatives and is right-biased for `map`.

```java
Either<String, Integer> result =
        Either.<String, Integer>right(21)
                .map(value -> value * 2);
```

```text
Either<L,R>
├── Left<L,R>
└── Right<L,R>
```

### Validated

`Validated<E,A>` models a successful value or one or more validation errors.

```java
Validated<String, Integer> result =
        Validated.invalid(
                List.of("name is empty", "age is negative"));
```

```text
Validated<E,A>
├── Valid<E,A>
└── Invalid<E,A>
```

Milestone 0.4 stores multiple errors in `Invalid`, but intentionally does not yet define generic Applicative-based error accumulation. That belongs after `Applicative` exists in the typeclass hierarchy.

## Compositional instances

A major addition in 0.4 is the ability to derive a typeclass instance for a structured type from instances for its elements.

For example:

```java
Eq<Option<Integer>> optionEq =
        OptionInstances.eq(IntegerInstances.EQ);
```

Conceptually:

```text
Eq<A>
  │
  ▼
Eq<Option<A>>
```

The same idea is implemented for `Either` and `Validated`:

```java
Eq<Either<String, Integer>> eitherEq =
        EitherInstances.eq(
                StringInstances.EQ,
                IntegerInstances.EQ);
```

and:

```java
Show<Validated<String, Integer>> validatedShow =
        ValidatedInstances.show(
                StringInstances.SHOW,
                IntegerInstances.SHOW);
```

This is an important bridge between simple typeclasses and the higher-kinded abstractions planned for later milestones.

## Algebraic laws

A Java implementation matching an interface is not automatically a lawful instance of the algebraic abstraction it represents.

Milestone 0.3 introduced executable laws for:

- `Eq`,
- `Ord`,
- `Semigroup`,
- `Monoid`.

Milestone 0.4 extends property testing to composed ADT equality instances. For example, if `Eq<Integer>` is lawful, `OptionInstances.eq(IntegerInstances.EQ)` is verified against the `Eq` laws as well.

See [docs/LAWS.md](docs/LAWS.md).

## Design principles

The project applies the following principles deliberately:

- **SRP** — ADTs, typeclasses, instances, syntax helpers and laws have separate responsibilities.
- **OCP** — new instances can be added without modifying represented domain types.
- **DIP** — generic algorithms depend on typeclass interfaces, not concrete policies.
- **KISS** — each ADT exposes only operations needed by the current milestone.
- **DRY** — compositional instance logic is centralized in dedicated instance factories.
- **YAGNI** — no HKT encoding, Monad, effect runtime or Tagless Final is introduced before the required foundations exist.

## Example

Run `AdtExample` from the IDE or compile and execute it manually. It demonstrates `Option`, `Either`, `Validated` and derived `Show` instances.

Expected output:

```text
Some(42)
Right(42)
Invalid([name is empty, age is negative])
```

## Documentation

- [Architecture](docs/ARCHITECTURE.md)
- [Typeclasses](docs/TYPECLASSES.md)
- [Algebra](docs/ALGEBRA.md)
- [Algebraic laws](docs/LAWS.md)
- [Algebraic data types](docs/ADT.md)
- [Implementation tasks](docs/IMPLEMENTATION_TASKS.md)
- [Roadmap](docs/ROADMAP.md)
- [Changelog](CHANGELOG.md)

## Roadmap

The planned sequence after 0.4 is:

```text
0.5  Higher-kinded type encoding: Kind<F,A>
0.6  Functor / Applicative / Monad
0.7  Effect types and natural transformations
0.8  Tagless Final with an effect parameter F
0.9  Derivation / instance-resolution experiments and API hardening
1.0  Stable educational release
```

Tagless Final is intentionally delayed until the project can express an effect constructor and the typeclasses needed to program over it meaningfully.

## License

Apache License 2.0. See [LICENSE](LICENSE).
