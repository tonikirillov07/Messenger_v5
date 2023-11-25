package com.darkstudio.messenger_v5;

import java.sql.*;

public class AIDatabase {
    private Connection conn;

    public AIDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:messenger.db");
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addMessage(String user, String message) {
        try {
            Statement stmt = conn.createStatement();
            String sql = String.format("INSERT INTO messages (user, message) VALUES ('%s', '%s');", user, message);
            stmt.executeUpdate(sql);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addUser(String username) {
        try {
            Statement stmt = conn.createStatement();
            String sql = String.format("INSERT INTO users (username) VALUES ('%s');", username);
            stmt.executeUpdate(sql);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getUserList() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username FROM users;");
            StringBuilder sb = new StringBuilder();
            while(rs.next()) {
                sb.append(rs.getString("username"));
                sb.append(",");
            }
            return sb.toString();
        } catch(SQLException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public void close() {
        try {
            conn.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}