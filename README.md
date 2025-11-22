# Java GUI Learning Management System (LMS)

## Overview
A robust Java GUI-based Learning Management System supporting Admin, Instructor, and Student roles. Built using Java Swing, JDBC, and OOP best practices. This project demonstrates a full-stack desktop application with a relational database backend.

## Features
### Core Features
- **Role-Based Access Control**: Secure login for Admin, Instructor, and Student.
- **Course Management**: Create, edit, and view courses, modules, and lessons.
- **Assessments**: Create assignments and submit work.
- **Persistence**: Data stored in a relational database (SQLite).
- **Multithreading**: Background file uploads and report generation.

### Advanced Features
- **Gamification**:
    - **Points System**: Earn points for activities.
    - **Leaderboard**: View top-performing students.
    - **Badges**: Earn badges (e.g., "Scholar", "Master") displayed on the profile.
- **API Integration**: Fetches daily motivational quotes from `api.quotable.io`.
- **Modern UI**: Custom dark theme, rounded buttons, and responsive layouts.
- **Notifications**: Real-time system notifications.
- **Search**: Filter courses by title.

## Setup Instructions
1.  **Prerequisites**: Java Development Kit (JDK) 8 or higher.
2.  **Database**:
    - The application uses SQLite. The database file `lms.db` is automatically created and seeded on the first run.
3.  **Compiling**:
    ```bash
    javac -cp "lib/*:src" -d bin src/com/lms/main/Main.java src/com/lms/model/*.java src/com/lms/dao/*.java src/com/lms/service/*.java src/com/lms/ui/*.java src/com/lms/ui/component/*.java src/com/lms/ui/theme/*.java src/com/lms/util/*.java
    ```
4.  **Running**:
    ```bash
    java -cp "lib/*:bin" com.lms.main.Main
    ```

## Demo Credentials
| Role | Username | Password |
| :--- | :--- | :--- |
| **Student** | `student` | `learn123` |
| **Instructor** | `instructor` | `pass123` |
| **Admin** | `admin` | `admin123` |

## Rubric Compliance Mapping
| Requirement | Implementation Details | File Reference |
| :--- | :--- | :--- |
| **OOP Implementation** (10 marks) | **Polymorphism**: `User.getDashboardLabel()` overridden.<br>**Inheritance**: `Admin`, `Instructor`, `Student` extend `User`.<br>**Interfaces**: `GenericDAO<T>` implemented by DAOs.<br>**Exception Handling**: Custom `AuthException`, `try-catch` blocks. | `User.java`, `GenericDAO.java`, `UserDAO.java`, `AuthException.java` |
| **Collections & Generics** (6 marks) | **Generics**: `GenericDAO<T>` interface.<br>**Collections**: `List<User>`, `ArrayList` used for fetching records. | `GenericDAO.java`, `UserDAO.java` |
| **Multithreading** (4 marks) | **SwingWorker**: `FileUploadWorker` for background uploads.<br>**Runnable**: `ReportGenerator` for background stats generation. | `FileUploadWorker.java`, `ReportGenerator.java` |
| **Database Classes** (7 marks) | Separate DAO classes for each entity (`UserDAO`, `CourseDAO`, `NotificationDAO`). | `com.lms.dao.*` |
| **JDBC Connectivity** (6 marks) | **Connection**: Singleton `DBConnection` class.<br>**Implementation**: `PreparedStatement` used for all queries to prevent SQL injection. | `DBConnection.java`, `UserDAO.java` |
