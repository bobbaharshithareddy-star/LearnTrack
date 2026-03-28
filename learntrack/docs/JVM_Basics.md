# JVM Basics

## JDK, JRE, and JVM — What's the Difference?

### JVM — Java Virtual Machine
The JVM is an abstract computing machine that executes Java bytecode. It is the engine that actually *runs* your program. When you run `java MyApp`, the JVM reads the compiled `.class` file and executes the instructions line by line. Crucially, the JVM is platform-specific — there is a JVM for Windows, one for macOS, one for Linux — but the bytecode it runs is identical on all of them.

### JRE — Java Runtime Environment
The JRE is a bundle that contains the JVM plus the standard Java class libraries (like `java.util`, `java.io`, etc.) that your program may depend on at runtime. If you only want to *run* a Java program (not develop one), the JRE is all you need.

### JDK — Java Development Kit
The JDK contains everything in the JRE plus development tools such as:
- `javac` — the Java compiler (turns `.java` source into `.class` bytecode)
- `javadoc` — documentation generator
- `jar` — packaging tool
- Debugging utilities

As a developer, you always need the JDK.

**Summary Table:**

| Tool | Contains          | Use case               |
|------|-------------------|------------------------|
| JVM  | Bytecode executor | Runs bytecode          |
| JRE  | JVM + libraries   | Run Java applications  |
| JDK  | JRE + dev tools   | Develop Java programs  |

---

## What is Bytecode?

When you compile a `.java` file with `javac`, the compiler does NOT produce machine code (i.e., CPU instructions for a specific processor). Instead, it produces **bytecode** — a set of compact, platform-neutral instructions stored in `.class` files.

Bytecode is an intermediate representation: it is lower-level than Java source code, but not tied to any specific hardware. The JVM reads this bytecode and either interprets it directly or compiles it further using a JIT (Just-In-Time) compiler for performance.

Think of it like a recipe written in a universal language — any JVM on any machine can read and "cook" the same recipe.

---

## "Write Once, Run Anywhere"

One of Java's famous slogans is **"Write Once, Run Anywhere"** (WORA). This is made possible by bytecode and the JVM.

When you write a Java program and compile it, you produce a `.class` file containing bytecode. That single `.class` file can be run on any device — Windows laptop, macOS machine, Linux server, Android phone — as long as a compatible JVM is installed on that device. You do not need to recompile your source code for each platform.

This was revolutionary compared to languages like C/C++, where code typically has to be recompiled separately for each target operating system and CPU architecture. Java abstracts away those differences by inserting the JVM as a universal interpreter between your program and the hardware.

In short: the JVM is platform-specific, but your bytecode is not — so you write once and the JVM handles the rest everywhere.
