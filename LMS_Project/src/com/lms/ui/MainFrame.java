package com.lms.ui;

import com.lms.model.User;
import com.lms.service.AuthService;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private AuthService authService;

    public MainFrame() {
        authService = new AuthService();

        setTitle("Learning Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(com.lms.ui.theme.Theme.BG_COLOR);

        // Add Login Panel
        LoginPanel loginPanel = new LoginPanel(this, authService);
        mainPanel.add(loginPanel, "LOGIN");

        add(mainPanel);

        // Show Login initially
        cardLayout.show(mainPanel, "LOGIN");
    }

    public void showDashboard(User user) {
        // Create dashboard based on role
        JPanel dashboardPanel;
        switch (user.getRole()) {
            case ADMIN:
                dashboardPanel = new AdminDashboard(this, user, authService);
                break;
            case INSTRUCTOR:
                dashboardPanel = new InstructorDashboard(this, user, authService);
                break;
            case STUDENT:
                dashboardPanel = new StudentDashboard(this, user, authService);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Unknown Role", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        // Remove old dashboard if exists (simple approach for this demo)
        // In a real app, we might cache them or manage them better.
        mainPanel.add(dashboardPanel, "DASHBOARD");
        cardLayout.show(mainPanel, "DASHBOARD");
    }

    public void logout() {
        authService.logout();
        cardLayout.show(mainPanel, "LOGIN");
    }
}
