# Java Typeclasses

An educational implementation of **typeclass-oriented programming in modern Java**.

Milestone **0.2** introduces the first algebraic typeclasses on top of the hardened 0.1.1 foundation. The project deliberately keeps typeclass instances as explicit values and uses explicit dictionary passing so that the mechanism remains visible.

## Requirements

- Java 21+
- Gradle 9.8.0 (wrapper configuration is provided)

The CI matrix verifies Java 21, 25 and 27.

## What milestone 0.2 contains

```text
Show<A>

Eq<A>
  ▲
  │
Ord<A>

Semigroup<A>
      ▲
      │
  Monoid<A>
```

- `Show<A>` — rendering behavior introduced in 0.1.1.
- `Eq<A>` — selectable equality semantics.
- `Ord<A>` — total ordering that also provides equality.
- `Semigroup<A>` — associative binary combination.
- `Monoid<A>` — a semigroup with an identity element.

This milestone intentionally does **not** add HKT encoding, `Functor`, `Applicative`, `Monad`, reflection-based lookup, a global registry, annotations, or automatic derivation.

## Core idea

A domain type does not implement its typeclass:

```java
public record Person(String name, int age) {
}
```

Instead, behaviour exists independently:

```java
public static final Eq<Person> BY_NAME =
        (left, right) -> left.name().equalsIgnoreCase(right.name());

public static final Ord<Person> BY_AGE =
        (left, right) -> Integer.compare(left.age(), right.age());
```

The same domain value can therefore have several meaningful behaviors without modifying `Person`.

## Multiple instances for one type

`Integer` demonstrates why typeclass instances are values rather than behavior embedded in the data type:

```java
IntegerInstances.ADDITION
IntegerInstances.MULTIPLICATION
```

Both are lawful `Monoid<Integer>` instances but have different operations and identities:

```text
addition        combine = +    empty = 0
multiplication  combine = *    empty = 1
```

A generic algorithm can select behavior explicitly:

```java
var numbers = List.of(1, 2, 3, 4);

var sum = MonoidFunctions.combineAll(
        numbers,
        IntegerInstances.ADDITION);

var product = MonoidFunctions.combineAll(
        numbers,
        IntegerInstances.MULTIPLICATION);
```

Results:

```text
10
24
```

## Semigroup versus Monoid

A `Semigroup<A>` only knows how to combine two values. Consequently `SemigroupFunctions.combineAll` requires a non-empty iterable.

A `Monoid<A>` additionally defines `empty()`, so `MonoidFunctions.combineAll` also works for an empty iterable and returns the identity value.

## Package structure

```text
io.codeswarm.typeclasses
├── core
│   ├── Show
│   ├── Eq
│   └── Ord
├── algebra
│   ├── Semigroup
│   └── Monoid
├── syntax
│   ├── ShowFunctions
│   ├── EqFunctions
│   ├── OrdFunctions
│   ├── SemigroupFunctions
│   └── MonoidFunctions
├── instances
│   ├── IntegerInstances
│   ├── StringInstances
│   ├── LocalDateInstances
│   └── ListInstances
└── examples
    ├── Person
    ├── PersonInstances
    ├── ShowExample
    ├── EqExample
    ├── OrdExample
    ├── SemigroupExample
    └── MonoidExample
```

## Design principles

### SOLID

- **SRP:** typeclasses define capabilities, instances define concrete semantics, syntax classes contain generic algorithms, domain records contain domain state and validation.
- **OCP:** new instances can be added without modifying domain types or generic algorithms.
- **LSP:** `Ord<A>` is a valid `Eq<A>` and `Monoid<A>` is a valid `Semigroup<A>`.
- **ISP:** each typeclass exposes only the minimal operation necessary for its abstraction.
- **DIP:** generic algorithms depend on `Show`, `Eq`, `Ord`, `Semigroup`, or `Monoid`, not on concrete implementations.

### KISS / DRY / YAGNI

- explicit instance passing instead of a hidden runtime registry;
- no reflection or dependency injection;
- no HKT encoding before it is required;
- no `Functor`/`Monad` before ADTs and laws are established;
- each utility class covers one typeclass family;
- reusable generic folds avoid duplicating combination logic.

## Build

With a generated Gradle wrapper:

```bash
./gradlew clean check javadoc
```

If the binary wrapper JAR is not present after extracting the source archive, regenerate it once with a local Gradle installation:

```bash
gradle wrapper --gradle-version 9.8.0
```

## Examples

After compiling, run the example classes:

```text
ShowExample
EqExample
OrdExample
SemigroupExample
MonoidExample
```

## Documentation

- [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md) — architecture and design decisions.
- [`docs/TYPECLASSES.md`](docs/TYPECLASSES.md) — typeclass pattern explained.
- [`docs/ALGEBRA.md`](docs/ALGEBRA.md) — algebraic structures introduced in 0.2.
- [`docs/IMPLEMENTATION_TASKS.md`](docs/IMPLEMENTATION_TASKS.md) — implementation sequence for 0.2.
- [`docs/ROADMAP.md`](docs/ROADMAP.md) — planned evolution toward laws, ADTs, HKT and higher abstractions.
- [`CHANGELOG.md`](CHANGELOG.md) — version history.

## Next milestone

Milestone **0.3** will focus on typeclass laws and property-based testing:

- `Eq` laws;
- `Ord` laws;
- `Semigroup` associativity;
- `Monoid` associativity and identity;
- reusable law-test harness;
- jqwik property tests.

## License

Apache License 2.0. See [`LICENSE`](LICENSE).
