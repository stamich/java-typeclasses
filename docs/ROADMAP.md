# Roadmap

## 0.1 — Original prototype

Initial experiment proving that the typeclass pattern can be represented in Java.

## 0.1.1 — Foundation hardening

- modern build baseline;
- `Show<A>`;
- explicit dictionary passing;
- external instances;
- clean package boundaries;
- tests, CI and documentation.

## 0.2 — Basic algebraic typeclasses — current

- `Eq<A>`;
- `Ord<A>`;
- `Semigroup<A>`;
- `Monoid<A>`;
- multiple instances for the same type;
- immutable list concatenation;
- dedicated syntax helpers;
- algebra documentation.

## 0.3 — Typeclass laws

Planned:

- reusable `EqLaws`, `OrdLaws`, `SemigroupLaws`, `MonoidLaws`;
- jqwik property-based testing;
- associativity, identity, reflexivity, symmetry, transitivity and ordering properties;
- lawful-instance test harness.

## 0.4 — Algebraic data types

Planned:

- `Option`;
- `Either`;
- `Validated`;
- sealed interfaces and records;
- typeclass instances for the ADTs.

## 0.5 — Higher-kinded type encoding

Planned:

- `Kind<F, A>`;
- witness types;
- safe conversion conventions;
- documentation of Java's HKT limitation.

## 0.6 — Functor, Applicative and Monad

Planned after HKT encoding is established.

## 0.7 — Foldable and Traverse

Planned generic folding and traversal abstractions.

## 0.8 — Instance resolution experiments

Possible explicit registry / `summon` experiments, kept outside the fundamental model.

## 0.9 — Automatic derivation

Possible annotation-processing based derivation for selected typeclasses.

## 1.0 — Stable educational release

A documented, law-tested API with a deliberate stability promise.
