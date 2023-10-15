package qxcode_implements.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static Connection conn = null;
    static {
        String url = "jdbc:postgres://localhost:5432/postgres";
        String user = "postgres";
        String password = "1234";
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
