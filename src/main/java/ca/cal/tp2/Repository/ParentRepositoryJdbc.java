package ca.cal.tp2.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ParentRepositoryJdbc {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:mem:tp1-h25-rilesCL;DB_CLOSE_DELAY=-1";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    static Connection conn = null;
    static Statement stmt = null;

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createDatabase() {
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();
            createTable();
            System.out.println("Created table in given database...");

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeResources();
        }
    }

    protected void createTable() throws SQLException {}

    protected void closeResources() {
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    protected void handleException(SQLException e) {
        System.out.println("Error Code: " + e.getErrorCode());
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("SQLException message: " + e.getMessage());
        e.printStackTrace();
    }
}