package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final List<Student> students = new ArrayList<>();

    // Add student with full details
    public Student addStudent(String firstName, String lastName, String email, String batch)
            throws InvalidInputException {
        InputValidator.isValidName(firstName, "First name");
        InputValidator.isValidName(lastName, "Last name");
        InputValidator.validateNotEmpty(batch, "Batch-B19");
        InputValidator.validateEmail(email);

        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch);
        students.add(student);
        return student;
    }

    // Overloaded: add student without email
    public Student addStudent(String firstName, String lastName, String batch)
            throws InvalidInputException {
        InputValidator.isValidName(firstName, "First name");
        InputValidator.isValidName(lastName, "Last name");
        InputValidator.validateNotEmpty(batch, "Batch");

        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, batch);
        students.add(student);
        return student;
    }

    public Student findStudentById(int id) throws EntityNotFoundException {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student", id);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public List<Student> getActiveStudents() {
        List<Student> active = new ArrayList<>();
        for (Student s : students) {
            if (s.isActive()) active.add(s);
        }
        return active;
    }

    public void updateStudent(int id, String firstName, String lastName, String email, String batch)
            throws EntityNotFoundException, InvalidInputException {
        Student student = findStudentById(id);
        InputValidator.validateNotEmpty(firstName, "First name");
        InputValidator.validateNotEmpty(lastName, "Last name");
        InputValidator.validateNotEmpty(batch, "Batch");
        InputValidator.validateEmail(email);

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);
    }

    public void deactivateStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(false);
    }

    public void reactivateStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(true);
    }

    public boolean removeStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        return students.remove(student);
    }
}
