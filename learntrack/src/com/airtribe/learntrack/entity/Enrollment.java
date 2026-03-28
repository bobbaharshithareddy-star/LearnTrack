package com.airtribe.learntrack.entity;

import java.time.LocalDate;

public class Enrollment {

    public enum Status {
        ACTIVE, COMPLETED, CANCELLED
    }

    private int id;
    private int studentId;
    private int courseId;
    private LocalDate enrollmentDate;
    private Status status;

    public Enrollment() {}

    public Enrollment(int id, int studentId, int courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = LocalDate.now();
        this.status = Status.ACTIVE;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
   // public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCourseId() { return courseId; }
    //public void setCourseId(int courseId) { this.courseId = courseId; }

    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return "Enrollment ID: " + id
                + " | Student ID: " + studentId
                + " | Course ID: " + courseId
                + " | Date: " + enrollmentDate
                + " | Status: " + status;
    }
}
