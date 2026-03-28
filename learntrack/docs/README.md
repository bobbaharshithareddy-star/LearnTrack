# LearnTrack 📚
A console-based **Student & Course Management System** built in Core Java.

----gi

## Project Description
LearnTrack is a menu-driven Java application that allows admins to manage Students, Courses, and Enrollments entirely in memory. It is designed to practice fundamental Java and OOP concepts including encapsulation, inheritance, polymorphism, collections, and basic exception handling.

----

## How to Compile and Run

### Prerequisites
- Java JDK 17 or later installed
- Terminal / Command Prompt

### Compile
```bash
# From the project root
find src -name "*.java" > sources.txt
javac -d out @sources.txt
```

### Run
```bash
java -cp out com.airtribe.learntrack.ui.Main
```

---

## Package Structure

```
src/
└── com/airtribe/learntrack/
    ├── entity/          # Core data classes
    │   ├── Person.java
    │   ├── Student.java
    │   ├── Trainer.java
    │   ├── Course.java
    │   └── Enrollment.java
    ├── service/         # Business logic
    │   ├── StudentService.java
    │   ├── CourseService.java
    │   └── EnrollmentService.java
    ├── ui/              # Console UI
    │   └── Main.java
    ├── exception/       # Custom exceptions
    │   ├── EntityNotFoundException.java
    │   └── InvalidInputException.java
    └── util/            # Helper classes
        ├── IdGenerator.java
        └── InputValidator.java

docs/
├── JVM_Basics.md
├── Setup_Instructions.md
└── Design_Notes.md
```

---

## Features

### Student Management
- Add a new student (with or without email — demonstrates constructor overloading)
- View all students
- Search student by ID
- Update student details
- Deactivate / Reactivate a student

### Course Management
- Add a new course
- View all courses
- Update course details
- Activate / Deactivate a course

### Enrollment Management
- Enroll a student in a course (with active-status validation)
- View enrollments for a specific student
- View all enrollments
- Mark enrollment as Completed
- Cancel an enrollment

---

## Class Diagram

```
         ┌─────────────────────┐
         │       Person        │
         │─────────────────────│
         │ - id: int           │
         │ - firstName: String │
         │ - lastName: String  │
         │ - email: String     │
         │─────────────────────│
         │ + getDisplayName()  │
         └────────┬────────────┘
                  │  extends
        ┌─────────┴──────────┐
        │                    │
┌───────▼───────┐    ┌───────▼───────┐
│    Student    │    │    Trainer    │
│───────────────│    │───────────────│
│ - batch       │    │ -specializ.   │
│ - active      │    │───────────────│
│───────────────│    │+getDisplayN() │
│+getDisplayN() │    └───────────────┘
└───────────────┘

┌────────────────────────┐
│        Course          │
│────────────────────────│
│ - id: int              │
│ - courseName: String   │
│ - description: String  │
│ - durationInWeeks: int │
│ - active: boolean      │
└────────────────────────┘

┌────────────────────────┐
│      Enrollment        │
│────────────────────────│
│ - id: int              │
│ - studentId: int       │
│ - courseId: int        │
│ - enrollmentDate       │
│ - status: Status(enum) │
└────────────────────────┘

Services:
  StudentService   ──uses──▶ Student, IdGenerator, InputValidator
  CourseService    ──uses──▶ Course,  IdGenerator, InputValidator
  EnrollmentService──uses──▶ StudentService, CourseService, Enrollment

Utilities:
  IdGenerator      (static counters for auto-incrementing IDs)
  InputValidator   (static validation helpers)

Exceptions:
  EntityNotFoundException  extends Exception
  InvalidInputException    extends Exception
```

---

## Design Highlights
- **Separation of concerns**: entity classes hold data, service classes hold logic, `Main.java` handles I/O only.
- **Encapsulation**: all fields are `private` with public getters/setters.
- **Inheritance**: `Student` and `Trainer` extend `Person`, sharing common fields and overriding `getDisplayName()`.
- **Static members**: `IdGenerator` uses static counters to generate unique IDs across the app lifetime.
- **Exception handling**: custom exceptions propagate meaningful messages instead of crashing.
