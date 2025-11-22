package com.lms.test;

import com.lms.model.User;
import com.lms.service.AuthService;
import com.lms.service.CourseService;
import com.lms.util.ReportGenerator;

public class LMSTest {
    public static void main(String[] args) {
        System.out.println("Running LMS Tests...");

        testAuth();
        testCourseCreation();
        testReportGeneration();

        System.out.println("All tests completed.");
    }

    private static void testAuth() {
        System.out.println("\n[Test] Authentication");
        AuthService authService = new AuthService();
        try {
            User user = authService.login("admin", "admin123");
            if (user != null && user.getRole() == User.Role.ADMIN) {
                System.out.println("PASS: Admin login successful.");
            } else {
                System.out.println("FAIL: Admin login failed.");
            }
        } catch (Exception e) {
            System.out.println("FAIL: Exception during login - " + e.getMessage());
        }
    }

    private static void testCourseCreation() {
        System.out.println("\n[Test] Course Creation");
        CourseService courseService = new CourseService();
        AuthService authService = new AuthService();
        try {
            User instructor = authService.login("instructor", "pass123");
            int initialCount = courseService.getCoursesByInstructor(instructor.getId()).size();

            courseService.createCourse("Test Course", "Test Description", instructor);

            int newCount = courseService.getCoursesByInstructor(instructor.getId()).size();
            if (newCount == initialCount + 1) {
                System.out.println("PASS: Course created successfully.");
            } else {
                System.out.println("FAIL: Course count mismatch.");
            }
        } catch (Exception e) {
            System.out.println("FAIL: Exception during course creation - " + e.getMessage());
        }
    }

    private static void testReportGeneration() {
        System.out.println("\n[Test] Report Generation (Thread)");
        Thread reportThread = new Thread(new ReportGenerator());
        reportThread.start();
        try {
            reportThread.join(); // Wait for it to finish for testing purposes
            System.out.println("PASS: Report thread executed.");
        } catch (InterruptedException e) {
            System.out.println("FAIL: Report thread interrupted.");
        }
    }
}
