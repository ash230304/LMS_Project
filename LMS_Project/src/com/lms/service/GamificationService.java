package com.lms.service;

import com.lms.dao.UserDAO;
import com.lms.model.Badge;
import com.lms.model.User;
import java.util.List;

public class GamificationService {
    private UserDAO userDAO;

    public GamificationService() {
        this.userDAO = new UserDAO();
    }

    public void addPoints(User user, int points) {
        int newPoints = user.getPoints() + points;
        user.setPoints(newPoints);
        userDAO.updatePoints(user.getId(), newPoints);
        checkBadges(user);
    }

    public List<Badge> getUserBadges(int userId) {
        return userDAO.getUserBadges(userId);
    }

    public List<User> getLeaderboard() {
        return userDAO.getTopStudents(5);
    }

    private void checkBadges(User user) {
        // Simple badge logic
        // 1. Newbie (First Login) - Handled elsewhere or assume done
        // 2. Scholar (1 Course) - Need to check course count, simplified here
        // 3. Master (5 Courses)

        // For demo, let's award a badge if points > 100
        if (user.getPoints() >= 100) {
            // Award "Scholar" (ID 2) if not already owned
            userDAO.awardBadge(user.getId(), 2);
        }
    }
}
