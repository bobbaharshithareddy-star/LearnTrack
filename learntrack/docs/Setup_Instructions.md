# Setup Instructions

## JDK Version Used

This project was developed and tested with **Java JDK 17** (LTS).

You can verify your installed version by running:

```bash
java -version
javac -version
```

Expected output (example):

```
java version "17.0.x" ...
javac 17.0.x
```

\---

## Step 1 — Install JDK

### Windows

1. Download the JDK 17 installer from https://www.oracle.com/java/technologies/downloads/
2. Run the installer and follow the prompts.
3. Add `JAVA\_HOME` to your system environment variables pointing to the JDK install directory.
4. Add `%JAVA\_HOME%\\bin` to your `PATH`.

### macOS

```bash
brew install openjdk@17
```

Then follow the symlink instructions shown by Homebrew.

### Linux (Ubuntu/Debian)

```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

\---

## Step 2 — Verify Installation

Open a terminal and run:

```bash
java -version
javac -version
```

Both should report version 17.x.x.

\---

## Step 3 — Hello World Verification

Create a file called `Hello.java`:

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compile and run:

```bash
javac Hello.java
java Hello
```

Expected output:

```
Hello, World!
```

\---

## Step 4 — Compile and Run LearnTrack

From the project root directory:

```bash
# Collect all .java source files
find src -name "\*.java" > sources.txt

# Compile into the /out directory
mkdir -p out
javac -d out @sources.txt

# Run the application
java -cp out com.airtribe.learntrack.ui.Main
```

### Using an IDE (IntelliJ IDEA recommended)

1. Open IntelliJ IDEA → **File > Open** → select the `learntrack` folder.
2. Mark `src` as the Sources Root (right-click → Mark Directory As → Sources Root).
3. Run `Main.java` using the green ▶ button.

