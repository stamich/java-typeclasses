# Higher-Kinded Type Encoding

## Why this milestone exists

Java can abstract over ordinary types, but it cannot directly abstract over a type constructor such as `Option<?>` in the same way Scala can abstract over `F[_]`.

Milestone 0.5 introduces a small encoding:

```java
public interface Kind<F, A> {
}
```

`F` is a witness identifying a type constructor and `A` is the contained value type.

## Option

`Option<A>` now implements:

```text
Kind<OptionK, A>
```

`OptionK` is a zero-state witness type. It is not instantiated at runtime.

```java
Option<Integer> option = Option.some(42);
Kind<OptionK, Integer> kind = OptionKinds.widen(option);
Option<Integer> restored = OptionKinds.narrow(kind);
```

## Partial type application

Binary ADTs need one parameter fixed before they can act as a unary constructor.

```text
Either<L, R>
        ↓ fix L
EitherK<L>
        ↓ apply R
Kind<EitherK<L>, R>
```

Therefore:

```text
Either<String, Integer>
≈ Kind<EitherK<String>, Integer>
```

The same technique is used for `Validated<E, A>`:

```text
Validated<String, Integer>
≈ Kind<ValidatedK<String>, Integer>
```

## Widening and narrowing

`widen` is safe and requires no cast because each ADT directly implements the corresponding `Kind` application.

`narrow` requires an unchecked cast because Java type erasure cannot prove the relationship between a witness and its concrete ADT. Those casts are intentionally isolated in:

- `OptionKinds`
- `EitherKinds`
- `ValidatedKinds`

No other production code should need HKT-related unchecked casts.

## Limitations

This is an encoding, not native higher-kinded type support. Compared with Scala or Haskell it has:

- explicit witness types,
- weaker inference,
- more boilerplate,
- controlled unchecked narrowing.

The benefit is that later milestones can express abstractions such as:

```java
interface Functor<F> {
    <A, B> Kind<F, B> map(Kind<F, A> value, Function<? super A, ? extends B> mapper);
}
```

without hard-coding `Option`, `Either`, or `Validated`.

## Deliberate scope boundary

Milestone 0.5 does **not** implement `Functor`, `Applicative`, `Monad`, `Traverse`, Kleisli, effect types, or Tagless Final. It establishes only the type-level infrastructure required by those abstractions.
