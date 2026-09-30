# Roadmap

The roadmap is incremental. Each milestone should remain buildable, documented, and understandable on its own.

## 0.1 — Original proof of concept

Initial experiments with typeclass-oriented functional programming in Java.

## 0.1.1 — Foundation hardening

- Java 21 baseline;
- modern Gradle build;
- clear package structure;
- explicit `Show<A>` typeclass;
- explicit dictionary passing;
- standard and domain-specific instances;
- multiple instances for one domain type;
- JUnit tests;
- JaCoCo and Javadoc;
- GitHub Actions CI;
- architecture, typeclass, roadmap, and changelog documentation.

## 0.2 — Basic algebraic typeclasses

Planned:

- `Eq<A>`;
- `Ord<A>`;
- `Semigroup<A>`;
- `Monoid<A>`;
- standard instances for selected JDK types;
- reusable generic algorithms consuming those instances.

## 0.3 — Typeclass laws

Planned:

- reusable law definitions;
- property-based testing;
- associativity, identity, equality, and ordering laws;
- jqwik-based test support.

## 0.4 — Algebraic data types

Planned:

- `Option<A>`;
- `Either<L, R>`;
- `Validated<E, A>`;
- Java records, sealed interfaces, and pattern matching.

## 0.5 — Higher-kinded type encoding

Planned:

- `Kind<F, A>` or equivalent witness encoding;
- safe inject/project boundaries;
- documentation of Java type-system limitations.

## 0.6 — Functor / Applicative / Monad

Planned higher-order typeclasses based on the 0.5 encoding.

## 0.7 — Foldable / Traverse

Planned structural typeclasses and instances for supported ADTs.

## 0.8 — Instance resolution experiments

Planned investigation of explicit registries, type tokens, scoping, ambiguity detection, and a `summon`-style API. Explicit dictionary passing remains the reference semantics.

## 0.9 — Automatic derivation

Planned annotation-processing or source-generation experiments for selected typeclasses such as `Eq` and `Show`.

## 1.0 — Stable educational release

Planned stable public API, complete documentation, examples, compatibility policy, publishing metadata, and release automation.
