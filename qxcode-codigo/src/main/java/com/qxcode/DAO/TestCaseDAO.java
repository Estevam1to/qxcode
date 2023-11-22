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

    public String getOutputsStringByQuestionId(int id) {

        ArrayList<String> received = new ArrayList<>();
        StringBuilder outputs = new StringBuilder();
        received = this.getOutputByQuestionId(id);

        for (String x : received) {
            x = x.replace('/', '\n');
            outputs.append(x);
            outputs.append('\n');
        }

        return outputs.toString();

    }

    public String getInputsStringByQuestionId(int id) {

        ArrayList<String> received = new ArrayList<>();
        StringBuilder inputs = new StringBuilder();
        received = this.getInputByQuestionId(id);

        for (String x : received) {

            x = x.replace('/', '\n');
            inputs.append(x);
            inputs.append('\n');

        }

        return inputs.toString();

    }

}