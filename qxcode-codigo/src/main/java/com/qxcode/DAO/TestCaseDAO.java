package com.qxcode.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.qxcode.JDBC.JDBC;


public class TestCaseDAO {

    public ArrayList<String> getInputByQuestionId(int id) {
        ArrayList<String> inputs = new ArrayList<>();
        String sql = "SELECT input FROM caso_de_teste WHERE id_questao = ?";
        try (   Connection conn = JDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String input = rs.getString("input");
                inputs.add(input);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    public ArrayList<String> getOutputByQuestionId(int id) {
        ArrayList<String> outputs = new ArrayList<String>();
        String sql = "SELECT output FROM caso_de_teste WHERE id_questao = ?";
        try (   Connection conn = JDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String output = rs.getString("output");
                outputs.add(output);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return outputs;
    }
}