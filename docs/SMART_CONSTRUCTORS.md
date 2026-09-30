# Smart constructors

## Purpose

A smart constructor validates raw data before a domain value becomes observable. The public API returns an ADT describing success or expected failure instead of exposing a permissive constructor.

```text
raw String
    |
    v
UserId.from(...)
    |
    +--> Left<ValidationError>
    |
    `--> Right<UserId>
```

The goal is to make invalid domain states difficult or impossible to construct through the public API.

## Why not throw?

Exceptions remain appropriate for programming errors and broken invariants. Expected validation failures are ordinary outcomes and are modeled explicitly with `Either` or `Validated`.

## Value objects

Milestone 0.4.1 includes:

- `UserId` — non-blank, normalized identifier,
- `PersonName` — non-blank and at most 100 characters,
- `Age` — range 0..130.

Their constructors are private. Construction goes through `from(...)`.

## Typed errors

Validation uses a sealed hierarchy rather than plain strings:

```text
ValidationError
├── BlankUserId
├── BlankPersonName
├── PersonNameTooLong
└── InvalidAge
```

Typed errors are inspectable, testable and composable.

## Either vs Validated

`Either<ValidationError,A>` is used for a single smart constructor because each value object has one local validation flow.

`Validated<ValidationError,User>` is used by `UserValidator` to demonstrate accumulation of independent field failures.

The current validator performs this accumulation explicitly. Generic `Applicative<Validated>` composition belongs to the later Applicative milestone.

## Records vs final classes

Records are ideal when their canonical constructor may be public and all constructor arguments already satisfy the invariant.

For `UserId`, `PersonName` and `Age`, a private constructor is important. Ordinary final classes therefore communicate the invariant more clearly than public records.

`User` is a record because all of its components are already validated value objects.
