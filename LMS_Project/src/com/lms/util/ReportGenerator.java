package com.lms.util;

import com.lms.service.CourseService;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReportGenerator implements Runnable {
    private CourseService courseService;

    public ReportGenerator() {
        this.courseService = new CourseService();
    }

    @Override
    public void run() {
        System.out.println("Starting report generation...");
        try (PrintWriter writer = new PrintWriter(new FileWriter("course_report.txt"))) {
            writer.println("LMS Course Report");
            writer.println("=================");
            writer.println("Generated at: " + java.time.LocalDateTime.now());
            writer.println();

            courseService.getAllCourses().forEach(course -> {
                writer.println("Course ID: " + course.getId());
                writer.println("Title: " + course.getTitle());
                writer.println("Description: " + course.getDescription());
                writer.println("-----------------------------");

                // Simulate heavy processing
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            System.out.println("Report generation completed: course_report.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
