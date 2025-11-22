package com.lms.service;

import com.lms.dao.NotificationDAO;
import com.lms.model.Notification;
import java.util.List;

public class NotificationService {
    private NotificationDAO notificationDAO;

    public NotificationService() {
        this.notificationDAO = new NotificationDAO();
    }

    public List<Notification> getUserNotifications(int userId) {
        return notificationDAO.getNotificationsForUser(userId);
    }

    public void markAsRead(int notificationId) {
        notificationDAO.markAsRead(notificationId);
    }
}
