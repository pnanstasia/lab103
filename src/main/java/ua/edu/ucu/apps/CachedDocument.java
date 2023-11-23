package ua.edu.ucu.apps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CachedDocument {
    private static Connection connection;

    public CachedDocument(Connection connection) {
        this.connection = connection;
    }

    public CachedDocument() {
    }

    private Connection connect() {
        String url = "jdbc:sqlite:data.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public boolean recordExist(String gcsPath) {
       String sql = "SELECT count(*) FROM your table WHERE path = ?";
       try (Connection conn = this.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                preparedStatement.setString(1, gcsPath);
                ResultSet rs = preparedStatement.executeQuery();
                return rs.getInt(1) > 0;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
    }

    public void injectText(String text, String gcsPath) {
        String sql = "SELECT count(*) FROM your table WHERE path = ?";
        try (Connection con = this.connect();
            PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, gcsPath);
                pst.setString(2, text);
                pst.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    public static void main(String[] args) {
        CachedDocument app = new CachedDocument();
        String gcsPath = "path";
        
        if (app.recordExist(gcsPath)) {
            app.injectText("text, which i want find", gcsPath);
            System.out.println("Record updated");
        }
        else {
            System.out.println("Record not found");
        }
    }

}

