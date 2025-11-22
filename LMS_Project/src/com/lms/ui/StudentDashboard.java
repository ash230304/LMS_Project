package com.lms.ui;

import com.lms.model.User;
import com.lms.service.AuthService;
import com.lms.service.CourseService;
import com.lms.service.GamificationService;
import com.lms.ui.component.ModernButton;
import com.lms.ui.theme.Theme;
import com.lms.util.FileUploadWorker;
import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends JPanel {
    private MainFrame mainFrame;
    private User user;
    private CourseService courseService;
    private GamificationService gamificationService;

    public StudentDashboard(MainFrame mainFrame, User user, AuthService authService) {
        this.mainFrame = mainFrame;
        this.user = user;
        this.courseService = new CourseService();
        this.gamificationService = new GamificationService();

        setBackground(Theme.BG_COLOR);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Theme.PANEL_COLOR);
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Theme.PANEL_COLOR);

        JLabel title = new JLabel(user.getDashboardLabel());
        title.setForeground(Theme.TEXT_COLOR);
        title.setFont(Theme.HEADER_FONT);
        titlePanel.add(title);

        JLabel pointsLabel = new JLabel(" | Points: " + user.getPoints() + " 🏆");
        pointsLabel.setForeground(Theme.ACCENT_COLOR);
        pointsLabel.setFont(Theme.SUBHEADER_FONT);
        titlePanel.add(pointsLabel);

        header.add(titlePanel, BorderLayout.WEST);

        ModernButton logoutBtn = new ModernButton("Logout");
        logoutBtn.addActionListener(e -> mainFrame.logout());
        header.add(logoutBtn, BorderLayout.EAST);

        // Quote Panel
        JPanel quotePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quotePanel.setBackground(Theme.PANEL_COLOR);
        JLabel quoteLabel = new JLabel("Loading daily inspiration...");
        quoteLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        quoteLabel.setForeground(Theme.ACCENT_COLOR);
        quotePanel.add(quoteLabel);
        header.add(quotePanel, BorderLayout.SOUTH);

        add(header, BorderLayout.NORTH);

        // Load Quote
        new com.lms.service.QuoteService().fetchRandomQuote().thenAccept(quote -> {
            SwingUtilities.invokeLater(() -> {
                if (quote != null) {
                    quoteLabel.setText(quote.toString());
                } else {
                    quoteLabel.setText("Education is the passport to the future.");
                }
            });
        });

        // Content
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Theme.BG_COLOR);
        tabbedPane.setForeground(Theme.TEXT_COLOR);

        tabbedPane.addTab("Available Courses", createCourseListPanel());
        tabbedPane.addTab("My Assignments", createAssignmentPanel());
        tabbedPane.addTab("Leaderboard", createLeaderboardPanel());
        tabbedPane.addTab("Profile", new ProfilePanel(user, authService));
        tabbedPane.addTab("Notifications", new NotificationPanel(user));

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createLeaderboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Theme.BG_COLOR);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        gamificationService.getLeaderboard()
                .forEach(u -> listModel.addElement(u.getName() + " - " + u.getPoints() + " pts"));

        JList<String> leaderboardList = new JList<>(listModel);
        leaderboardList.setBackground(Theme.PANEL_COLOR);
        leaderboardList.setForeground(Theme.TEXT_COLOR);
        leaderboardList.setFont(Theme.REGULAR_FONT);

        JScrollPane scrollPane = new JScrollPane(leaderboardList);
        scrollPane.getViewport().setBackground(Theme.BG_COLOR);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCourseListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Theme.BG_COLOR);

        DefaultListModel<com.lms.model.EnrolledCourse> listModel = new DefaultListModel<>();
        courseService.getStudentCourses(user.getId()).forEach(listModel::addElement);

        JList<com.lms.model.EnrolledCourse> courseList = new JList<>(listModel);
        courseList.setBackground(Theme.PANEL_COLOR);
        courseList.setForeground(Theme.TEXT_COLOR);
        courseList.setFont(Theme.REGULAR_FONT);

        // Custom renderer for progress bar
        courseList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JPanel p = new JPanel(new BorderLayout(5, 5));
                p.setBackground(isSelected ? Theme.ACCENT_COLOR : Theme.PANEL_COLOR);
                p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                com.lms.model.EnrolledCourse ec = (com.lms.model.EnrolledCourse) value;

                JLabel title = new JLabel(ec.getTitle());
                title.setForeground(Theme.TEXT_COLOR);
                title.setFont(Theme.BOLD_FONT);
                p.add(title, BorderLayout.NORTH);

                JProgressBar pb = new JProgressBar(0, 100);
                pb.setValue(ec.getProgress());
                pb.setStringPainted(true);
                pb.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                p.add(pb, BorderLayout.CENTER);

                return p;
            }
        });

        JScrollPane scrollPane = new JScrollPane(courseList);
        scrollPane.getViewport().setBackground(Theme.BG_COLOR);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Theme.BG_COLOR);
        ModernButton enrollBtn = new ModernButton("Enroll in New Course");
        enrollBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Enroll Feature Placeholder"));
        btnPanel.add(enrollBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createAssignmentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Theme.BG_COLOR);

        // Placeholder assignment list
        String[] assignments = { "Calculator Project (Due: 2025-12-01)", "Essay on Java (Due: 2025-12-05)" };
        JList<String> assignmentList = new JList<>(assignments);
        assignmentList.setBackground(Theme.PANEL_COLOR);
        assignmentList.setForeground(Theme.TEXT_COLOR);
        assignmentList.setFont(Theme.REGULAR_FONT);

        JScrollPane scrollPane = new JScrollPane(assignmentList);
        scrollPane.getViewport().setBackground(Theme.BG_COLOR);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel uploadPanel = new JPanel();
        uploadPanel.setBackground(Theme.BG_COLOR);

        ModernButton uploadBtn = new ModernButton("Upload Submission");
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        uploadBtn.addActionListener(e -> {
            uploadBtn.setEnabled(false);
            // Simulate file upload with background thread
            new FileUploadWorker(progressBar, uploadBtn).execute();
        });

        uploadPanel.add(uploadBtn);
        uploadPanel.add(progressBar);
        panel.add(uploadPanel, BorderLayout.SOUTH);

        return panel;
    }
}
