package com.qxcode.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static Connection conn = null;
    static {
        String url = "jdbc:sqlite:C:/Users/ofern/OneDrive/Documentos/UFC/qxcode/qxcode-codigo/DataBase/QXCODEDB.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                String url = "jdbc:sqlite:C:/Users/ofern/OneDrive/Documentos/UFC/qxcode/qxcode-codigo/DataBase/QXCODEDB.db";
                conn = DriverManager.getConnection(url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
