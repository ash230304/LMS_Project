-- Insert Users (Passwords are plain text for demo simplicity, in real app should be hashed)
-- Admin
INSERT INTO users (username, password, role, name, email) VALUES 
('admin', 'admin123', 'ADMIN', 'System Admin', 'admin@lms.com');

-- Instructors
INSERT INTO users (username, password, role, name, email) VALUES 
('instructor', 'pass123', 'INSTRUCTOR', 'John Doe', 'john@lms.com'),
('jane_prof', 'pass123', 'INSTRUCTOR', 'Jane Smith', 'jane@lms.com');

-- Students
INSERT INTO users (username, password, role, name, email) VALUES 
('student', 'learn123', 'STUDENT', 'Alice Wonderland', 'alice@lms.com'),
('bob_builder', 'learn123', 'STUDENT', 'Bob Builder', 'bob@lms.com');

-- Insert Courses
INSERT INTO courses (title, description, instructor_id) VALUES 
('Java Programming 101', 'Introduction to Java and OOP', 2),
('Advanced Database Systems', 'Deep dive into SQL and JDBC', 3);

-- Insert Modules
INSERT INTO modules (course_id, title) VALUES 
(1, 'Module 1: Basics'),
(1, 'Module 2: OOP Concepts'),
(2, 'Module 1: Normalization');

-- Insert Lessons
INSERT INTO lessons (module_id, title, content) VALUES 
(1, 'Setup JDK', 'Download and install JDK 17...'),
(1, 'Hello World', 'public class Main { ... }'),
(2, 'Classes and Objects', 'A class is a blueprint...');

-- Insert Assignments
INSERT INTO assignments (course_id, title, description, due_date) VALUES 
(1, 'Calculator Project', 'Build a console calculator', '2025-12-01');

-- Insert Enrollments
INSERT INTO enrollments (student_id, course_id, progress) VALUES 
(4, 1, 25),
(5, 1, 50),
(4, 2, 0);

-- Insert Notifications
INSERT INTO notifications (user_id, message) VALUES 
(4, 'Welcome to the LMS!'),
(4, 'Assignment "Calculator Project" is due soon.'),
(4, 'Assignment "Calculator Project" is due soon.'),
(5, 'New course available: Advanced Database Systems');

-- Insert Badges
INSERT INTO badges (name, description, icon) VALUES
('Newbie', 'First Login', '🌱'),
('Scholar', 'Completed 1 Course', '🎓'),
('Master', 'Completed 5 Courses', '👑');

-- Update Users with Points (Simulation)
UPDATE users SET points = 120 WHERE id = 4; -- Student
UPDATE users SET points = 50 WHERE id = 5; -- Student 2

-- Award Badges
INSERT INTO user_badges (user_id, badge_id) VALUES
(4, 1),
(4, 2),
(5, 1);
