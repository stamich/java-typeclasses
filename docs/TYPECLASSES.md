# Typeclasses in Java

## 1. Motivation

A typeclass describes behaviour for a type without requiring that type to inherit from a particular interface.

In milestone 0.1.1 the pattern has three pieces:

1. a generic behavioural contract;
2. one or more instances of that contract for a concrete type;
3. generic algorithms that receive an instance explicitly.

## 2. Contract

```java
@FunctionalInterface
public interface Show<A> {
    String show(A value);
}
```

`Show<A>` says only that values of `A` can be represented as text. It says nothing about how `A` is implemented.

## 3. Instance

```java
Show<Integer> decimalShow = String::valueOf;
```

The instance is an ordinary Java value. It can be stored in a constant, passed as an argument, selected dynamically, or created locally.

## 4. Dictionary passing

A typeclass instance can be understood as a dictionary containing the operations required by a generic algorithm.

```java
static <A> String show(A value, Show<? super A> dictionary) {
    return dictionary.show(value);
}
```

The dependency is explicit and compile-time typed.

## 5. Multiple instances

Because behaviour is not attached to the model, several representations can coexist:

```java
Show<Person> compact = Person::name;
Show<Person> verbose = person ->
        "%s (%d)".formatted(person.name(), person.age());
```

The caller chooses the behaviour appropriate to its context.

## 6. Existing types

Typeclasses can describe types that cannot be modified. The project defines an ISO rendering instance for `java.time.LocalDate` without subclassing or wrapping `LocalDate`.

## 7. Typeclass vs inheritance

Inheritance is useful when a capability is an intrinsic part of a model's public contract. A typeclass-like approach is useful when behaviour should be:

- externally defined;
- independently evolvable;
- replaceable at the call site;
- available in several variants for one type.

The approaches are complementary rather than universally interchangeable.

## 8. Typeclass vs Strategy

In Java, the runtime mechanics are close to the Strategy pattern: behaviour is represented by an interface and passed to a consumer. The typeclass interpretation adds a type-oriented viewpoint: the interface represents an operation family parameterised by a target type, and generic algorithms are written in terms of that capability.

## 9. Java limitations

Java has no native syntax corresponding to Scala `given`/`using`, Haskell typeclass instance search, or higher-kinded types. Milestone 0.1.1 intentionally does not hide those limitations.

Later milestones may explore explicit encodings for higher-kinded types and instance resolution, but only after the basic first-order model is stable.
