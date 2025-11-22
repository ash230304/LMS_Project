package com.lms.ui;

import com.lms.model.User;
import com.lms.service.AuthService;
import com.lms.ui.component.ModernButton;
import com.lms.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private User user;
    private AuthService authService;
    private JTextField nameField;
    private JTextField emailField;

    public ProfilePanel(User user, AuthService authService) {
        this.user = user;
        this.authService = authService;

        setBackground(Theme.BG_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Edit Profile");
        titleLabel.setFont(Theme.HEADER_FONT);
        titleLabel.setForeground(Theme.TEXT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(Theme.REGULAR_FONT);
        nameLabel.setForeground(Theme.TEXT_COLOR);
        add(nameLabel, gbc);

        gbc.gridx = 1;
        nameField = new JTextField(user.getName(), 20);
        styleTextField(nameField);
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(Theme.REGULAR_FONT);
        emailLabel.setForeground(Theme.TEXT_COLOR);
        add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField(user.getEmail(), 20);
        styleTextField(emailField);
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        ModernButton updateBtn = new ModernButton("Update Profile");
        updateBtn.addActionListener(e -> {
            String newName = nameField.getText();
            String newEmail = emailField.getText();
            if (!newName.isEmpty() && !newEmail.isEmpty()) {
                authService.updateProfile(newName, newEmail);
                JOptionPane.showMessageDialog(this, "Profile Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(updateBtn, gbc);

        // Badges Section
        gbc.gridy = 4;
        gbc.insets = new Insets(20, 10, 10, 10);
        JLabel badgesLabel = new JLabel("Earned Badges");
        badgesLabel.setFont(Theme.SUBHEADER_FONT);
        badgesLabel.setForeground(Theme.TEXT_COLOR);
        add(badgesLabel, gbc);

        gbc.gridy = 5;
        JPanel badgesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        badgesPanel.setBackground(Theme.BG_COLOR);

        com.lms.service.GamificationService gamificationService = new com.lms.service.GamificationService();
        java.util.List<com.lms.model.Badge> badges = gamificationService.getUserBadges(user.getId());

        if (badges.isEmpty()) {
            JLabel noBadges = new JLabel("No badges earned yet. Keep learning!");
            noBadges.setForeground(Theme.TEXT_COLOR);
            noBadges.setFont(Theme.REGULAR_FONT);
            badgesPanel.add(noBadges);
        } else {
            for (com.lms.model.Badge badge : badges) {
                JPanel badgeItem = new JPanel(new BorderLayout());
                badgeItem.setBackground(Theme.PANEL_COLOR);
                badgeItem.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                JLabel iconLabel = new JLabel(badge.getIcon());
                iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
                badgeItem.add(iconLabel, BorderLayout.CENTER);

                JLabel badgeNameLabel = new JLabel(badge.getName());
                badgeNameLabel.setForeground(Theme.TEXT_COLOR);
                badgeNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
                badgeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                badgeItem.add(badgeNameLabel, BorderLayout.SOUTH);

                badgeItem.setToolTipText(badge.getDescription());
                badgesPanel.add(badgeItem);
            }
        }
        add(badgesPanel, gbc);
    }

    private void styleTextField(JTextField field) {
        field.setBackground(Theme.PANEL_COLOR);
        field.setForeground(Theme.TEXT_COLOR);
        field.setCaretColor(Theme.TEXT_COLOR);
        field.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        field.setFont(Theme.REGULAR_FONT);
    }
}
