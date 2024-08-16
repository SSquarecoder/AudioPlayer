package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exp {

    private static final String DB_URL = "jdbc:sqlite:game_stats.db";

    public static void createTable() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS stats " +
                    "(player TEXT PRIMARY KEY, wins INTEGER, losses INTEGER, draws INTEGER)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStats(String player, String result) {
        String selectSql = "SELECT * FROM stats WHERE player = ?";
        String updateSql = "UPDATE stats SET wins = ?, losses = ?, draws = ? WHERE player = ?";
        String insertSql = "INSERT INTO stats (player, wins, losses, draws) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement selectStmt = conn.prepareStatement(selectSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            selectStmt.setString(1, player);
            ResultSet rs = selectStmt.executeQuery();

            int wins = 0, losses = 0, draws = 0;
            boolean exists = rs.next();
            if (exists) {
                wins = rs.getInt("wins");
                losses = rs.getInt("losses");
                draws = rs.getInt("draws");
            }

            switch (result) {
                case "win":
                    wins++;
                    break;
                case "loss":
                    losses++;
                    break;
                case "draw":
                    draws++;
                    break;
            }

            if (exists) {
                updateStmt.setInt(1, wins);
                updateStmt.setInt(2, losses);
                updateStmt.setInt(3, draws);
                updateStmt.setString(4, player);
                updateStmt.executeUpdate();
            } else {
                insertStmt.setString(1, player);
                insertStmt.setInt(2, wins);
                insertStmt.setInt(3, losses);
                insertStmt.setInt(4, draws);
                insertStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createTable();
        updateStats("Player1", "win");
    }
}
