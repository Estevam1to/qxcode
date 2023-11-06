package com.qxcode.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    public static Connection conn = null;
    static {
        String url = "jdbc:sqlite:/home/teamate/IdeaProjects/qxcode/qxcode-codigo/DataBase/QXCODEDB.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
