# Roadmap

## 0.1 — Original prototype

Initial experiment proving that the typeclass pattern can be represented in Java.

## 0.1.1 — Foundation hardening

Modern build baseline, `Show<A>`, explicit dictionary passing, external instances, tests, CI and documentation.

## 0.2 — Basic algebraic typeclasses

`Eq`, `Ord`, `Semigroup`, `Monoid`, multiple instances, immutable list concatenation and focused syntax helpers.

## 0.3 — Typeclass laws — current

- reusable `EqLaws`, `OrdLaws`, `SemigroupLaws`, `MonoidLaws` in test scope;
- jqwik property-based testing;
- properties for Integer, String, LocalDate, List and Person instances;
- explicit demonstration of an unlawful semigroup;
- normalized Person instance naming;
- law-focused documentation.

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

Possible explicit registry / `summon` experiments kept outside the fundamental model.

## 0.9 — Automatic derivation

Possible annotation-processing based derivation for selected typeclasses.

## 1.0 — Stable educational release

A documented, law-tested API with a deliberate stability promise.
