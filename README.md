# Java Typeclasses

Educational implementation of typeclass-oriented functional programming in modern Java.

Milestone **0.3.0** builds on the algebra introduced in 0.2 and adds **executable typeclass laws** verified with property-based tests.

## Goals

The project demonstrates how typeclass-style polymorphism can be expressed in Java while keeping domain models independent from behavior policies. It deliberately favors explicit mechanics over framework magic.

Core design principles:

- **SOLID** — small abstractions with focused responsibilities;
- **KISS** — explicit instance passing instead of hidden resolution;
- **DRY** — reusable law helpers remove duplicated test logic;
- **YAGNI** — no HKT encoding, registry, reflection, annotations, `Functor` or `Monad` yet;
- **immutability by default** — examples and collection instances do not mutate caller-owned data.

## Requirements

- Java 21 or newer;
- Gradle 9.8.0 when regenerating/running the wrapper;
- JUnit 6.1.2;
- jqwik 1.10.1 for property-based testing.

## Typeclasses implemented

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

`Show` is independent. `Ord` refines `Eq`; `Monoid` refines `Semigroup`.

## Why laws matter

Implementing an interface is not enough to create a valid algebraic instance. A `Semigroup<A>` must be associative, and a `Monoid<A>` must additionally have a left and right identity. Likewise, equality and ordering instances must satisfy their corresponding laws.

Milestone 0.3 verifies these properties systematically using reusable law functions and jqwik-generated values.

Examples:

```text
Eq:
  reflexivity
  symmetry
  transitivity

Ord:
  reflexivity
  sign antisymmetry
  transitivity
  equality consistency

Semigroup:
  associativity

Monoid:
  associativity
  left identity
  right identity
```

See [`docs/LAWS.md`](docs/LAWS.md) for details.

## Explicit instances

Domain types do not implement their typeclasses:

```java
public record Person(String name, int age) {
}
```

Instead, behavior is external:

```java
public static final Eq<Person> EQ_ALL_FIELDS =
        (left, right) -> left.name().equals(right.name())
                && left.age() == right.age();

public static final Eq<Person> EQ_NAME =
        (left, right) -> left.name().equalsIgnoreCase(right.name());
```

This allows several valid interpretations for the same domain type.

## Multiple lawful monoids for the same Java type

`Integer` has two useful monoids:

```java
IntegerInstances.ADDITION
IntegerInstances.MULTIPLICATION
```

The same algorithm can therefore produce different results solely through the supplied instance:

```java
var numbers = List.of(1, 2, 3, 4);

var sum = MonoidFunctions.combineAll(numbers, IntegerInstances.ADDITION);
var product = MonoidFunctions.combineAll(numbers, IntegerInstances.MULTIPLICATION);
```

Results:

```text
10
24
```

## Property-based law verification

A reusable law is independent from any specific instance:

```java
public static <A> boolean associative(
        A first,
        A second,
        A third,
        Semigroup<A> semigroup,
        Eq<? super A> eq) {

    var left = semigroup.combine(semigroup.combine(first, second), third);
    var right = semigroup.combine(first, semigroup.combine(second, third));

    return eq.eqv(left, right);
}
```

jqwik then generates many values:

```java
@Property
void additionIsALawfulMonoid(
        @ForAll int first,
        @ForAll int second,
        @ForAll int third) {

    assertTrue(MonoidLaws.associative(
            first,
            second,
            third,
            IntegerInstances.ADDITION,
            IntegerInstances.EQ));
}
```

The tests also contain an intentionally unlawful subtraction semigroup to demonstrate that merely satisfying the Java interface is not sufficient.

## Packages

```text
src/main/java/io/codeswarm/typeclasses/
├── algebra/
│   ├── Monoid.java
│   └── Semigroup.java
├── core/
│   ├── Eq.java
│   ├── Ord.java
│   └── Show.java
├── examples/
│   ├── Person.java
│   ├── PersonInstances.java
│   └── ...
├── instances/
│   ├── IntegerInstances.java
│   ├── ListInstances.java
│   ├── LocalDateInstances.java
│   └── StringInstances.java
└── syntax/
    ├── EqFunctions.java
    ├── MonoidFunctions.java
    ├── OrdFunctions.java
    ├── SemigroupFunctions.java
    └── ShowFunctions.java

src/test/java/io/codeswarm/typeclasses/
├── laws/
│   ├── EqLaws.java
│   ├── MonoidLaws.java
│   ├── OrdLaws.java
│   ├── SemigroupLaws.java
│   └── UnlawfulInstancesTest.java
├── properties/
│   ├── IntegerInstancesProperties.java
│   ├── ListInstancesProperties.java
│   ├── LocalDateInstancesProperties.java
│   ├── PersonInstancesProperties.java
│   └── StringInstancesProperties.java
└── ... existing focused unit tests
```

The law framework intentionally lives under `src/test`. It validates this project but is not yet committed as public production API.

## Build

```bash
./gradlew clean check javadoc
```

`check` runs both normal JUnit tests and jqwik properties through the JUnit Platform.

## CI

GitHub Actions validates Java 21, 25 and 27 with:

```bash
./gradlew clean check javadoc
```

## Documentation

- [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md)
- [`docs/TYPECLASSES.md`](docs/TYPECLASSES.md)
- [`docs/ALGEBRA.md`](docs/ALGEBRA.md)
- [`docs/LAWS.md`](docs/LAWS.md)
- [`docs/ROADMAP.md`](docs/ROADMAP.md)
- [`CHANGELOG.md`](CHANGELOG.md)

## Out of scope for 0.3

The following are deliberately deferred:

- `Option`, `Either`, `Validated`;
- higher-kinded type encoding;
- `Functor`, `Applicative`, `Monad`;
- instance registry / `summon`;
- reflection-based resolution;
- annotation processing and automatic derivation.

These belong to later milestones after the basic algebra has a trustworthy law-tested foundation.

## License

Apache License 2.0.
