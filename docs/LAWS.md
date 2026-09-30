# Typeclass Laws — Milestone 0.3

## Why laws are necessary

A Java interface describes available operations, but it cannot express the mathematical properties those operations should obey. Therefore a class or lambda may compile while still being an invalid typeclass instance.

Milestone 0.3 turns the documented algebraic contracts into executable test rules.

## Eq laws

For an `Eq<A>` instance:

### Reflexivity

```text
a ~ a
```

### Symmetry

```text
a ~ b  <=>  b ~ a
```

### Transitivity

```text
a ~ b && b ~ c  =>  a ~ c
```

## Ord laws

`Ord<A>` refines `Eq<A>` through `compare(a, b) == 0`.

The test suite verifies:

- self comparison yields zero;
- reversing arguments reverses the sign of comparison;
- the less-than-or-equal relation is transitive;
- `eqv(a, b)` agrees with zero comparison.

An `Ord` instance may intentionally consider different domain values equivalent. For example, ordering `Person` only by age means two people of equal age compare as zero.

## Semigroup law

A `Semigroup<A>` must be associative:

```text
(a <> b) <> c == a <> (b <> c)
```

The two results are compared using an explicit `Eq<A>` instance rather than `Objects.equals`. This preserves the project's typeclass semantics.

## Monoid laws

A `Monoid<A>` inherits semigroup associativity and adds an identity element `empty()`:

```text
empty <> a == a
a <> empty == a
```

## Why property-based testing

Example-based unit tests can demonstrate known cases but cannot provide meaningful confidence about universal algebraic rules. Property-based testing generates many inputs and shrinks failing cases to smaller counterexamples.

The project uses jqwik through the JUnit Platform.

## Law helpers

Reusable helpers are located in test scope:

```text
src/test/java/io/codeswarm/typeclasses/laws/
```

They return booleans and do not depend on an assertion library. This keeps them reusable by both property tests and focused example-based tests.

## Lawful and unlawful instances

Integer addition is associative and has identity `0`, so it is a lawful monoid.

Integer subtraction is not associative:

```text
(10 - 3) - 2 = 5
10 - (3 - 2) = 9
```

`UnlawfulInstancesTest` intentionally demonstrates this failure. The subtraction operation can implement `Semigroup<Integer>` syntactically, but it does not satisfy the semigroup law semantically.

## Why laws remain in test scope

Milestone 0.3 uses laws as internal verification infrastructure. Publishing them as production API would create a compatibility commitment before the project has enough experience with their shape.

A future milestone may extract them into a dedicated module if users need reusable law suites.

## ADT equality laws in milestone 0.4

The law helpers introduced in 0.3 are reused to verify composed equality instances for `Option`, `Either` and `Validated`.

The project checks the same `Eq` laws:

- reflexivity,
- symmetry,
- transitivity.

This demonstrates an important principle: composing an instance should preserve the algebraic contract when the component instances are lawful.
