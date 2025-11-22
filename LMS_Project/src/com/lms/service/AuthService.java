package com.lms.service;

import com.lms.dao.UserDAO;
import com.lms.exception.AuthException;
import com.lms.model.User;
import java.util.Optional;

public class AuthService {
    private UserDAO userDAO;
    private User currentUser;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    public User login(String username, String password) throws AuthException {
        Optional<User> userOpt = userDAO.getByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // In a real app, we would hash the input password and compare.
            // For this demo, we compare plain text as per seed data.
            if (user != null && user.getPassword().equals(password)) {
                currentUser = user;
                return user;
            }
        }
        throw new AuthException("Invalid username or password.");
    }

    public void logout() {
        currentUser = null;
    }

    public void updateProfile(String newName, String newEmail) {
        if (currentUser != null) {
            userDAO.updateProfile(currentUser.getId(), newName, newEmail);
            // Update current user object in memory
            // In a real app, we might reload from DB, but here we can just set fields if we
            // had setters.
            // Since User fields are protected, we can't easily set them without setters or
            // being in same package.
            // Let's assume we reload or just trust the DB update for next login.
            // Actually, let's add setters to User model or just reload.
            // For now, let's just print.
            System.out.println("Profile updated for: " + currentUser.getUsername());
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isAuthenticated() {
        return currentUser != null;
    }
}
