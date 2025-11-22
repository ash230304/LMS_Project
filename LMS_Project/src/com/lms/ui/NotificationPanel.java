package com.lms.ui;

import com.lms.model.Notification;
import com.lms.model.User;
import com.lms.service.NotificationService;
import com.lms.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NotificationPanel extends JPanel {
    private User user;
    private NotificationService notificationService;
    private JPanel listPanel;

    public NotificationPanel(User user) {
        this.user = user;
        this.notificationService = new NotificationService();

        setBackground(Theme.BG_COLOR);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Notifications");
        titleLabel.setFont(Theme.HEADER_FONT);
        titleLabel.setForeground(Theme.TEXT_COLOR);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Theme.BG_COLOR);

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.getViewport().setBackground(Theme.BG_COLOR);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        refreshNotifications();
    }

    private void refreshNotifications() {
        listPanel.removeAll();
        List<Notification> notifications = notificationService.getUserNotifications(user.getId());

        if (notifications.isEmpty()) {
            JLabel emptyLabel = new JLabel("No notifications.");
            emptyLabel.setForeground(Theme.TEXT_COLOR);
            emptyLabel.setFont(Theme.REGULAR_FONT);
            emptyLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            listPanel.add(emptyLabel);
        } else {
            for (Notification n : notifications) {
                JPanel itemPanel = new JPanel(new BorderLayout());
                itemPanel.setBackground(Theme.PANEL_COLOR);
                itemPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.BG_COLOR),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

                JLabel msgLabel = new JLabel(
                        "<html>" + n.getMessage() + " <small>(" + n.getCreatedAt() + ")</small></html>");
                msgLabel.setForeground(Theme.TEXT_COLOR);
                msgLabel.setFont(Theme.REGULAR_FONT);
                itemPanel.add(msgLabel, BorderLayout.CENTER);

                if (!n.isRead()) {
                    JLabel unreadLabel = new JLabel("●");
                    unreadLabel.setForeground(Theme.ACCENT_COLOR);
                    itemPanel.add(unreadLabel, BorderLayout.WEST);
                }

                listPanel.add(itemPanel);
            }
        }
        listPanel.revalidate();
        listPanel.repaint();
    }
}
