package com.lms.ui;

import com.lms.model.User;
import com.lms.service.AuthService;
import com.lms.service.CourseService;
import com.lms.ui.component.ModernButton;
import com.lms.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class InstructorDashboard extends JPanel {
    private MainFrame mainFrame;
    private User user;
    private CourseService courseService;

    public InstructorDashboard(MainFrame mainFrame, User user, AuthService authService) {
        this.mainFrame = mainFrame;
        this.user = user;
        this.courseService = new CourseService();

        setBackground(Theme.BG_COLOR);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Theme.PANEL_COLOR);
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel(" " + user.getDashboardLabel());
        title.setForeground(Theme.TEXT_COLOR);
        title.setFont(Theme.HEADER_FONT);
        header.add(title, BorderLayout.WEST);

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
                    quoteLabel.setText("Teaching is the highest form of understanding.");
                }
            });
        });

        // Content
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Theme.BG_COLOR);
        tabbedPane.setForeground(Theme.TEXT_COLOR);

        tabbedPane.addTab("My Courses", createMyCoursesPanel());
        tabbedPane.addTab("Gradebook", createPlaceholderPanel("Gradebook Placeholder"));
        tabbedPane.addTab("Profile", new ProfilePanel(user, authService));
        tabbedPane.addTab("Notifications", new NotificationPanel(user));

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createMyCoursesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Theme.BG_COLOR);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        courseService.getCoursesByInstructor(user.getId()).forEach(c -> listModel.addElement(c.toString()));
        JList<String> courseList = new JList<>(listModel);
        courseList.setBackground(Theme.PANEL_COLOR);
        courseList.setForeground(Theme.TEXT_COLOR);
        courseList.setFont(Theme.REGULAR_FONT);

        JScrollPane scrollPane = new JScrollPane(courseList);
        scrollPane.getViewport().setBackground(Theme.BG_COLOR);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Theme.BG_COLOR);

        ModernButton addLessonBtn = new ModernButton("Add Lesson");
        addLessonBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Add Lesson Feature Placeholder"));
        btnPanel.add(addLessonBtn);

        ModernButton addAssignmentBtn = new ModernButton("Add Assignment");
        addAssignmentBtn
                .addActionListener(e -> JOptionPane.showMessageDialog(this, "Add Assignment Feature Placeholder"));
        btnPanel.add(addAssignmentBtn);

        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createPlaceholderPanel(String text) {
        JPanel panel = new JPanel();
        panel.setBackground(Theme.BG_COLOR);
        JLabel label = new JLabel(text);
        label.setForeground(Theme.TEXT_COLOR);
        label.setFont(Theme.REGULAR_FONT);
        panel.add(label);
        return panel;
    }
}
