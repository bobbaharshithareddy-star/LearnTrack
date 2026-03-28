# LearnTrack 📚

> A console-based Student \& Course Management System built in Core Java.

\---

## Project Description

LearnTrack is a menu-driven Java application that allows admins to manage **Students**, **Courses**, and **Enrollments** entirely in memory. It was built to practise core Java fundamentals: OOP, encapsulation, inheritance, polymorphism, ArrayList, and exception handling.

\---

## How to Compile and Run

### Prerequisites

* Java JDK 17+
* Terminal / Command Prompt (or IntelliJ IDEA)

### Compile

```bash
find src -name "\*.java" > sources.txt
mkdir -p out
javac -d out @sources.txt
```

### Run

```bash
java -cp out com.airtribe.learntrack.ui.Main
```

### IntelliJ IDEA

1. Open → select the `learntrack` folder
2. Right-click `src` → *Mark Directory As → Sources Root*
3. Run `Main.java` ▶

\---

## Features

|Module|Actions|
|-|-|
|Student Management|Add, View All, Search by ID, Update, Deactivate, Reactivate|
|Course Management|Add, View All, Update, Activate, Deactivate|
|Enrollment Mgmt|Enroll, View by Student, View All, Mark Completed, Cancel|

\---

## Package Structure

```
src/com/airtribe/learntrack/
├── entity/        Person, Student, Trainer, Course, Enrollment
├── service/       StudentService, CourseService, EnrollmentService
├── ui/            Main.java (console menu)
├── exception/     EntityNotFoundException, InvalidInputException
└── util/          IdGenerator, InputValidator

docs/
├── Setup\_Instructions.md
├── JVM\_Basics.md
└── Design\_Notes.md
```

\---

## Class Diagram

```
                    ┌──────────────────────────┐
                    │          Person          │
                    │──────────────────────────│
                    │ - id: int                │
                    │ - firstName: String      │
                    │ - lastName: String       │
                    │ - email: String          │
                    │──────────────────────────│
                    │ + getDisplayName()       │
                    │ + toString()             │
                    └────────────┬─────────────┘
                                 │  extends
               ┌─────────────────┴
               │                                   
  ┌────────────▼──────────────┐     
  │          Student          │     
  │───────────────────────────│     
  │ - batch: String           │   
  │ - active: boolean         │     
  │───────────────────────────│     
  │ + getDisplayName()\[overr.]│
  │ + toString()    \[overr.]  │
  └───────────────────────────┘

  ┌──────────────────────────────┐     ┌──────────────────────────────────┐
  │           Course             │     │           Enrollment             │
  │──────────────────────────────│     │──────────────────────────────────│
  │ - id: int                    │     │ - id: int                        │
  │ - courseName: String         │     │ - studentId: int                 │
  │ - description: String        │     │ - courseId: int                  │
  │ - durationInWeeks: int       │     │ - enrollmentDate: LocalDate      │
  │ - active: boolean            │     │ - status: Status {enum}          │
  │──────────────────────────────│     │   ACTIVE | COMPLETED | CANCELLED │
  │ + toString()                 │     └──────────────────────────────────┘
  └──────────────────────────────┘

  Services (hold ArrayList + business logic):
  ┌─────────────────────┐   ┌──────────────────────┐   ┌────────────────────────────────┐
  │   StudentService    │   │    CourseService      │   │      EnrollmentService         │
  │─────────────────────│   │──────────────────────│   │────────────────────────────────│
  │ addStudent(...)     │   │ addCourse(...)        │   │ enroll(studentId, courseId)     │
  │ findStudentById()   │   │ findCourseById()      │   │ findEnrollmentById()            │
  │ getAllStudents()     │   │ getAllCourses()        │   │ getEnrollmentsForStudent()      │
  │ updateStudent()     │   │ updateCourse()        │   │ markCompleted()                 │
  │ deactivateStudent() │   │ activateCourse()      │   │ cancelEnrollment()              │
  └─────────────────────┘   └──────────────────────┘   └────────────────────────────────┘

  Utilities:                              Exceptions:
  ┌──────────────────────────────┐        ┌───────────────────────┐
  │       IdGenerator            │        │ EntityNotFoundExc.    │  ← extends Exception
  │  static studentIdCounter     │        ├───────────────────────┤
  │  static courseIdCounter      │        │ InvalidInputExc.      │  ← extends Exception
  │  static enrollmentIdCounter  │        └───────────────────────┘
  └──────────────────────────────┘
  ┌──────────────────────────────┐
  │      InputValidator          │
  │  validateNotEmpty()          │
  │  validatePositive()          │
  │  validateEmail()             │
  └──────────────────────────────┘
```

\---

## Documentation

* [Setup Instructions](docs/Setup_Instructions.md)
* [JVM Basics](docs/JVM_Basics.md)
* [Design Notes](docs/Design_Notes.md)

