package com.lms.service;

import com.lms.dao.CourseDAO;
import com.lms.model.Course;
import com.lms.model.User;
import java.util.List;

public class CourseService {
    private CourseDAO courseDAO;

    public CourseService() {
        this.courseDAO = new CourseDAO();
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAll();
    }

    public List<Course> getCoursesByInstructor(int instructorId) {
        return courseDAO.getByInstructor(instructorId);
    }

    public List<Course> searchCourses(String query) {
        return courseDAO.searchCourses(query);
    }

    public List<com.lms.model.EnrolledCourse> getStudentCourses(int studentId) {
        return courseDAO.getStudentCourses(studentId);
    }

    public void createCourse(String title, String description, User instructor) {
        if (instructor.getRole() != User.Role.INSTRUCTOR && instructor.getRole() != User.Role.ADMIN) {
            throw new SecurityException("Only instructors or admins can create courses.");
        }
        Course course = new Course(0, title, description, instructor.getId());
        courseDAO.save(course);
    }

    // Additional methods for modules/lessons would go here, using their respective
    // DAOs.
    // For brevity in this demo, we might focus on the main course flow.
}
