package com.qxcode.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static final String DATABASE_URL = "jdbc:sqlite:qxcode-codigo/DataBase/QXCODEDB.db";
    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(DATABASE_URL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
