package com.qxcode.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static Connection conn = null;
    static {
        String url = "jdbc:sqlite:"; //URL DO Banco de Dados
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
