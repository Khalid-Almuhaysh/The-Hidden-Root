package main;

import java.sql.*;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/2D_Game";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public DatabaseHandler() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean readFullscreenPreference() {
        String query = "SELECT value FROM settings WHERE key_name = 'fullscreen'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return Boolean.parseBoolean(rs.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Default to windowed mode if the value is not found
    }

    public void saveFullscreenPreference(boolean isFullscreen) {
        String query = "INSERT INTO settings (key_name, value) VALUES ('fullscreen', ?) " +
                       "ON DUPLICATE KEY UPDATE value = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            String value = String.valueOf(isFullscreen);
            stmt.setString(1, value);
            stmt.setString(2, value);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    // Save player's name and time to the database
    public void savePlayerScore(String name, double time) {
         // First, ensure that no duplicate player names are inserted by checking if the name exists
    String checkQuery = "SELECT COUNT(*) FROM leaderboard WHERE name = ?";
    
    try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
        checkStmt.setString(1, name);
        ResultSet rs = checkStmt.executeQuery();
        rs.next();
        
        // If the player is not already in the leaderboard, save their score
        if (rs.getInt(1) == 0) {
            String insertQuery = "INSERT INTO leaderboard (name, time) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
                stmt.setString(1, name);
                stmt.setDouble(2, time);
                stmt.executeUpdate();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void cleanupLeaderboard() {
        String deleteQuery = "DELETE FROM leaderboard WHERE id NOT IN (SELECT id FROM leaderboard ORDER BY time LIMIT 10)";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve the top 5 players from the database
    public void loadLeaderboard(GamePanle gp) {
        // Modify the query to get top 10 players based on time
    String query = "SELECT name, time FROM leaderboard ORDER BY time LIMIT 10";
    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        int i = 0;
        while (rs.next()) {
            String name = rs.getString("name");
            double time = rs.getDouble("time");
            // Storing the data in the GamePanle's playerNames and playerTimes arrays
            gp.playerNames[i] = name;
            gp.playerTimes[i] = time;
            i++;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    // Close the database connection
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


