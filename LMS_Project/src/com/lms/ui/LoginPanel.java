package com.lms.ui;

import com.lms.exception.AuthException;
import com.lms.model.User;
import com.lms.service.AuthService;
import com.lms.ui.component.ModernButton;
import com.lms.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
    private MainFrame mainFrame;
    private AuthService authService;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel(MainFrame mainFrame, AuthService authService) {
        this.mainFrame = mainFrame;
        this.authService = authService;

        setBackground(Theme.BG_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("LMS Login");
        titleLabel.setFont(Theme.HEADER_FONT);
        titleLabel.setForeground(Theme.TEXT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(Theme.REGULAR_FONT);
        userLabel.setForeground(Theme.TEXT_COLOR);
        add(userLabel, gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        styleTextField(usernameField);
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(Theme.REGULAR_FONT);
        passLabel.setForeground(Theme.TEXT_COLOR);
        add(passLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        styleTextField(passwordField);
        add(passwordField, gbc);

        ModernButton loginButton = new ModernButton("Login");
        loginButton.addActionListener(this::handleLogin);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(loginButton, gbc);

        // Hint
        JLabel hintLabel = new JLabel(
                "<html><center>Demo Credentials:<br>admin/admin123<br>instructor/pass123<br>student/learn123</center></html>");
        hintLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        hintLabel.setForeground(Color.GRAY);
        gbc.gridy = 4;
        add(hintLabel, gbc);
    }

    private void styleTextField(JTextField field) {
        field.setBackground(Theme.PANEL_COLOR);
        field.setForeground(Theme.TEXT_COLOR);
        field.setCaretColor(Theme.TEXT_COLOR);
        field.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        field.setFont(Theme.REGULAR_FONT);
    }

    private void handleLogin(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            User user = authService.login(username, password);
            mainFrame.showDashboard(user);
            // Clear fields
            usernameField.setText("");
            passwordField.setText("");
        } catch (AuthException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
