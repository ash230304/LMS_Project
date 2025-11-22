package com.lms.dao;

import com.lms.model.Notification;
import com.lms.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    public List<Notification> getNotificationsForUser(int userId) {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notifications WHERE user_id = ? ORDER BY created_at DESC";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                notifications.add(new Notification(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("message"),
                        rs.getString("created_at"),
                        rs.getBoolean("is_read")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    public void markAsRead(int notificationId) {
        String sql = "UPDATE notifications SET is_read = 1 WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, notificationId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
