package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService =
            new EnrollmentService(studentService, courseService);

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║   Welcome to LearnTrack 📚   ║");
        System.out.println("╚══════════════════════════════╝");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> studentMenu();
                case 2 -> courseMenu();
                case 3 -> enrollmentMenu();
                case 0 -> {
                    System.out.println("Goodbye! 👋");
                    running = false;
                }
                default -> System.out.println("❌ Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // ─── Menus ───────────────────────────────────────────────────────────────

    private static void printMainMenu() {
        System.out.println("\n══════════ MAIN MENU ══════════");
        System.out.println("  1. Student Management");
        System.out.println("  2. Course Management");
        System.out.println("  3. Enrollment Management");
        System.out.println("  0. Exit");
        System.out.println("═══════════════════════════════");
    }

    private static void studentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n─── Student Management ───");
            System.out.println("  1. Add Student");
            System.out.println("  2. View All Students");
            System.out.println("  3. Search Student by ID");
            System.out.println("  4. Update Student");
            System.out.println("  5. Deactivate Student");
            System.out.println("  6. Reactivate Student");
            System.out.println("  0. Back");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> searchStudentById();
                case 4 -> updateStudent();
                case 5 -> deactivateStudent();
                case 6 -> reactivateStudent();
                case 0 -> back = true;
                default -> System.out.println("❌ Invalid option.");
            }
        }
    }

    private static void courseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n─── Course Management ───");
            System.out.println("  1. Add Course");
            System.out.println("  2. View All Courses");
            System.out.println("  3. Update Course");
            System.out.println("  4. Activate Course");
            System.out.println("  5. Deactivate Course");
            System.out.println("  0. Back");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addCourse();
                case 2 -> viewAllCourses();
                case 3 -> updateCourse();
                case 4 -> activateCourse();
                case 5 -> deactivateCourse();
                case 0 -> back = true;
                default -> System.out.println("❌ Invalid option.");
            }
        }
    }

    private static void enrollmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n─── Enrollment Management ───");
            System.out.println("  1. Enroll Student in Course");
            System.out.println("  2. View Enrollments for Student");
            System.out.println("  3. View All Enrollments");
            System.out.println("  4. Mark Enrollment as Completed");
            System.out.println("  5. Cancel Enrollment");
            System.out.println("  0. Back");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> enrollStudent();
                case 2 -> viewEnrollmentsForStudent();
                case 3 -> viewAllEnrollments();
                case 4 -> markEnrollmentCompleted();
                case 5 -> cancelEnrollment();
                case 0 -> back = true;
                default -> System.out.println("❌ Invalid option.");
            }
        }
    }

    // ─── Student Actions ─────────────────────────────────────────────────────

    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        String firstName = readString("First Name: ");
        String lastName  = readString("Last Name: ");
        String email     = readString("Email: ");
        String batch     = readString("Batch (e.g. Batch- B19): ");

        try {
            Student s = studentService.addStudent(firstName, lastName, email, batch);
            System.out.println("✅ Student added: " + s);
        } catch (InvalidInputException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void viewAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("ℹ️  No students found.");
            return;
        }
        System.out.println("\n--- All Students ---");
        for (Student s : students) {
            System.out.println("  " + s);
        }
    }

    private static void searchStudentById() {
        int id = readInt("Enter Student ID: ");
        try {
            Student s = studentService.findStudentById(id);
            System.out.println("Found: " + s);
        } catch (EntityNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void updateStudent() {
        int id = readInt("Enter Student ID to update: ");
        try {
            studentService.findStudentById(id); // validate exists first
            String firstName = readString("New First Name: ");
            String lastName  = readString("New Last Name: ");
            String email     = readString("New Email: ");
            String batch     = readString("New Batch: ");
            studentService.updateStudent(id, firstName, lastName, email, batch);
            System.out.println("✅ Student updated.");
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void deactivateStudent() {
        int id = readInt("Enter Student ID to deactivate: ");
        try {
            studentService.deactivateStudent(id);
            System.out.println("✅ Student deactivated.");
        } catch (EntityNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void reactivateStudent() {
        int id = readInt("Enter Student ID to reactivate: ");
        try {
            studentService.reactivateStudent(id);
            System.out.println("✅ Student reactivated.");
        } catch (EntityNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    // ─── Course Actions ───────────────────────────────────────────────────────

    private static void addCourse() {
        System.out.println("\n--- Add New Course ---");
        String name        = readString("Course Name: ");
        String description = readString("Description: ");
        int    duration    = readInt("Duration (weeks): ");

        try {
            Course c = courseService.addCourse(name, description, duration);
            System.out.println("✅ Course added: " + c);
        } catch (InvalidInputException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void viewAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("ℹ️  No courses found.");
            return;
        }
        System.out.println("\n--- All Courses ---");
        for (Course c : courses) {
            System.out.println("  " + c);
        }
    }

    private static void updateCourse() {
        int id = readInt("Enter Course ID to update: ");
        try {
            courseService.findCourseById(id);
            String name = readString("New Course Name: ");
            String desc = readString("New Description: ");
            int    dur  = readInt("New Duration (weeks): ");
            courseService.updateCourse(id, name, desc, dur);
            System.out.println("✅ Course updated.");
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void activateCourse() {
        int id = readInt("Enter Course ID to activate: ");
        try {
            courseService.activateCourse(id);
            System.out.println("✅ Course activated.");
        } catch (EntityNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void deactivateCourse() {
        int id = readInt("Enter Course ID to deactivate: ");
        try {
            courseService.deactivateCourse(id);
            System.out.println("✅ Course deactivated.");
        } catch (EntityNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    // ─── Enrollment Actions ───────────────────────────────────────────────────

    private static void enrollStudent() {
        int studentId = readInt("Enter Student ID: ");
        int courseId  = readInt("Enter Course ID: ");
        try {
            Enrollment e = enrollmentService.enroll(studentId, courseId);
            System.out.println("✅ Enrolled: " + e);
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void viewEnrollmentsForStudent() {
        int studentId = readInt("Enter Student ID: ");
        List<Enrollment> list = enrollmentService.getEnrollmentsForStudent(studentId);
        if (list.isEmpty()) {
            System.out.println("ℹ️  No enrollments found for Student ID " + studentId);
            return;
        }
        System.out.println("\n--- Enrollments for Student ID " + studentId + " ---");
        for (Enrollment e : list) {
            System.out.println("  " + e);
        }
    }

    private static void viewAllEnrollments() {
        List<Enrollment> list = enrollmentService.getAllEnrollments();
        if (list.isEmpty()) {
            System.out.println("ℹ️  No enrollments found.");
            return;
        }
        System.out.println("\n--- All Enrollments ---");
        for (Enrollment e : list) {
            System.out.println("  " + e);
        }
    }

    private static void markEnrollmentCompleted() {
        int id = readInt("Enter Enrollment ID: ");
        try {
            enrollmentService.markCompleted(id);
            System.out.println("✅ Enrollment marked as COMPLETED.");
        } catch (EntityNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void cancelEnrollment() {
        int id = readInt("Enter Enrollment ID: ");
        try {
            enrollmentService.cancelEnrollment(id);
            System.out.println("✅ Enrollment CANCELLED.");
        } catch (EntityNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    // ─── Input Helpers ────────────────────────────────────────────────────────

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
