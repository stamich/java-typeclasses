# Architecture — milestone 0.1.1

## Purpose

Milestone 0.1.1 establishes the smallest architecture that can explain and safely evolve the typeclass pattern in Java.

## Dependency direction

```text
Domain data                Typeclass contract
   Person                  Show<A>
      │                        │
      │                        │
      └──────────┐    ┌────────┘
                 ▼    ▼
             Typeclass instance
             Show<Person>
                    │
                    ▼
             Generic algorithm
          TypeClassFunctions.show
```

The domain model does not depend on the typeclass contract or on a concrete instance.

## Packages

### `io.codeswarm.typeclasses.core`

Contains the minimal reusable API of the milestone:

- `Show<A>` — the typeclass contract;
- `TypeClassFunctions` — generic algorithms consuming typeclass instances.

### `io.codeswarm.typeclasses.instances`

Contains reusable instances for JDK/platform types. Instances are grouped by target type to avoid a global god-class.

### `io.codeswarm.typeclasses.examples`

Contains educational domain examples. Example-only code does not leak into the core package.

## Architectural rules

1. **Domain types do not implement typeclasses.**
   A typeclass instance is separate from the represented model.

2. **Dependencies are explicit.**
   Generic algorithms receive instances as method arguments.

3. **No hidden global resolution in 0.1.1.**
   There is no service locator, registry, reflection, annotation processing, or dependency-injection container.

4. **Instances are immutable values.**
   Stateless instances are exposed as `public static final` values.

5. **Core stays independent.**
   The core package has no dependency on examples and no external runtime dependency.

6. **Examples do not define architecture.**
   `Person` exists to demonstrate the pattern, not as part of the reusable public abstraction.

## SOLID / KISS / DRY / YAGNI

### Single Responsibility Principle

- `Show` defines one capability.
- instance holders define concrete behaviour.
- `Person` represents domain data.
- `TypeClassFunctions` hosts generic algorithms.

### Open/Closed Principle

New `Show<A>` instances can be added without changing `A`, `Show`, or existing algorithms.

### Liskov Substitution Principle

Every `Show<A>` implementation obeys the same minimal contract: it produces a string representation for an `A` according to its documented policy.

### Interface Segregation Principle

`Show` contains a single operation. Consumers are not forced to depend on unrelated capabilities.

### Dependency Inversion Principle

Generic algorithms depend on the `Show` abstraction and receive concrete dictionaries from callers.

### KISS

No automatic lookup mechanism is introduced. The central mechanism can be understood from one method call.

### DRY

Generic rendering logic is expressed once and reused with different instances.

### YAGNI

Abstractions planned for later milestones are not prematurely encoded in the core.

## Null policy

`Show<A>` does not impose a global null policy. A particular instance may reject, support, or specially format null values. Generic infrastructure validates only its own required dependency: the `Show` dictionary itself.

## Future evolution

The architecture is intentionally prepared for additional first-order typeclasses without requiring a redesign:

```text
Show<A>
Eq<A>
Ord<A>
Semigroup<A>
Monoid<A>
```

Higher-kinded encodings and automatic instance resolution are postponed until their dedicated milestones.
