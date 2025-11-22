package com.lms.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.io.File;

public class DBInitializer {
    public static void initialize() {
        File dbFile = new File("lms.db");
        if (dbFile.exists() && dbFile.length() > 0) {
            System.out.println("Database already exists. Skipping initialization.");
            return;
        }

        System.out.println("Initializing database...");
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement()) {

            // Run Schema
            runScript(stmt, "db/schema.sql");

            // Run Seed Data
            runScript(stmt, "db/seed_data.sql");

            System.out.println("Database initialized successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runScript(Statement stmt, String filePath) throws IOException, java.sql.SQLException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                // Remove comments and empty lines
                if (line.trim().startsWith("--") || line.trim().isEmpty())
                    continue;

                sb.append(line).append("\n");

                // SQLite statements end with ;
                if (line.trim().endsWith(";")) {
                    stmt.execute(sb.toString());
                    sb.setLength(0); // Reset buffer
                }
            }
        }
    }
}
