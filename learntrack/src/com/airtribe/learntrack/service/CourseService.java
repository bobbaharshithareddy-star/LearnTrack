package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private final List<Course> courses = new ArrayList<>();

    public Course addCourse(String courseName, String description, int durationInWeeks)
            throws InvalidInputException {
        InputValidator.validateNotEmpty(courseName, "Course name");
        InputValidator.validateNotEmpty(description, "Description");
        InputValidator.validatePositive(durationInWeeks, "Duration");

        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks);
        courses.add(course);
        return course;
    }
    public Course findCourseByName(String courseName) throws EntityNotFoundException{
        for (Course course : courses) {
            if (course.getCourseName() == courseName) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course not found");
    }
    public Course findCourseById(int id) throws EntityNotFoundException {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course", id);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public List<Course> getActiveCourses() {
        List<Course> active = new ArrayList<>();
        for (Course c : courses) {
            if (c.isActive()) active.add(c);
        }
        return active;
    }

    public void updateCourse(int id, String courseName, String description, int durationInWeeks)
            throws EntityNotFoundException, InvalidInputException {
        Course course = findCourseById(id);
        InputValidator.validateNotEmpty(courseName, "Course name");
        InputValidator.validateNotEmpty(description, "Description");
        InputValidator.validatePositive(durationInWeeks, "Duration");

        course.setCourseName(courseName);
        course.setDescription(description);
        course.setDurationInWeeks(durationInWeeks);
    }

    public void activateCourse(int id) throws EntityNotFoundException {
        findCourseById(id).setActive(true);
    }

    public void deactivateCourse(int id) throws EntityNotFoundException {
        findCourseById(id).setActive(false);
    }
}
