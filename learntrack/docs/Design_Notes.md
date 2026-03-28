# Design Notes

## Why ArrayList Instead of Array?

Java arrays have a fixed size declared at creation time. For example, `new Student\[50]` can only hold exactly 50 students — no more, no less. In a real management system, you don't know in advance how many students will be added, so a fixed-size array is impractical.

`ArrayList` solves this by growing dynamically as elements are added. It is backed by an array internally, but it handles resizing automatically. Other advantages:

* `add()`, `remove()`, and `size()` are built-in and intuitive.
* It stores objects directly, which fits our `Student`, `Course`, and `Enrollment` entities perfectly.
* It integrates cleanly with enhanced for-loops (`for (Student s : students)`).

For this project, all three services (`StudentService`, `CourseService`, `EnrollmentService`) use `ArrayList` as their in-memory data store.

\---

## Where Static Members Were Used and Why

Static members belong to the **class itself**, not to any particular instance. They are shared across all objects of that class.

### `IdGenerator` (utility class)

```java
private static int studentIdCounter = 1;
public static int getNextStudentId() { return studentIdCounter++; }
```

The ID counters must be shared across the entire application — not tied to any single object. Making them `static` means every call to `getNextStudentId()` increments the *same* counter, guaranteeing unique IDs for every student created during a run. If the counter were an instance variable, each `IdGenerator` object would start its own counter from 1, producing duplicate IDs.

The constructor of `IdGenerator` is `private` to prevent instantiation — there is no reason to ever create an `IdGenerator` object; all its functionality is static.

\---

## Where Inheritance Was Used and What Was Gained

### `Person` ← `Student`, `Trainer`

`Person` is the base class holding fields common to any person in the system: `id`, `firstName`, `lastName`, and `email`. `Student` and `Trainer` both extend `Person`.

**What we gained:**

1. **No duplication.** Without inheritance, `Student` and `Trainer` would each define their own `id`, `firstName`, `lastName`, `email` fields and all associated getters/setters — identical code in two places. Inheritance eliminates that entirely.
2. **`super()` constructor chaining.** `Student`'s constructor calls `super(id, firstName, lastName, email)` to delegate the shared field setup to `Person`. This keeps constructors clean and DRY.
3. **Polymorphism via method overriding.** `Person` defines `getDisplayName()` returning `"firstName lastName"`. `Student` overrides it to return `"\[Student] firstName lastName"` and `Trainer` returns `"\[Trainer] firstName lastName"`. This means any code that holds a `Person` reference can call `getDisplayName()` and get the right, specialized output regardless of whether the actual object is a `Student` or `Trainer` — that is polymorphism in action.

\---

## Separation of Concerns

The project follows a three-layer structure:

|Layer|Classes|Responsibility|
|-|-|-|
|Entity|`Person`, `Student`, `Course`, `Enrollment`|Hold data; no logic|
|Service|`StudentService`, `CourseService`, etc.|Business rules, validation, storage|
|UI|`Main.java`|Display menus, read input, call services|

`Main.java` never directly touches the `ArrayList` of students — it always goes through `StudentService`. This means the storage mechanism could be swapped (e.g., to a database) without touching the UI layer.

\---

## Exception Handling Strategy

Two custom exceptions were created:

* `EntityNotFoundException` — thrown when a lookup by ID fails (e.g., student or course doesn't exist).
* `InvalidInputException` — thrown when input fails validation (empty name, invalid name, invalid email, inactive student enrollment, etc.).

Both extend `Exception` (checked exceptions), which forces callers to handle them explicitly with `try-catch`. This makes error paths visible and intentional rather than hidden. In `Main.java`, every service call is wrapped in a `try-catch` that prints a user-friendly `❌ Error: ...` message instead of a raw stack trace.

