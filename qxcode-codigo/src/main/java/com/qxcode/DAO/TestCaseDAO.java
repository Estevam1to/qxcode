package com.qxcode.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.qxcode.JDBC.JDBC;


public class TestCaseDAO {

    public void insert (String inputs, String outputs, int questionId) {

        String sql = "INSERT INTO caso_de_teste (input, output, id_questao) VALUES (?, ?, ?)";

        try (   Connection conn = JDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, inputs);
            stmt.setString(2, outputs);
            stmt.setInt(3, questionId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

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

        if (received.isEmpty()) {
            return "";
        }

        for (String x : received) {
            x = x.replace('/', '\n');
            outputs.append(x);
            outputs.append('\n');
        }
        outputs.append('\n');
        return outputs.toString();

    }

    public String getInputsStringByQuestionId(int id) {

        ArrayList<String> received = new ArrayList<>();
        StringBuilder inputs = new StringBuilder();
        received = this.getInputByQuestionId(id);

        if (received.isEmpty()) {
            return "";
        }

        for (String x : received) {

            x = x.replace('/', '\n');
            inputs.append(x);
            inputs.append('\n');

        }

        return inputs.toString();

    }

    public String getExOutputsStringByQuestionId(int id) {

        ArrayList<String> received = new ArrayList<>();
        received = this.getOutputByQuestionId(id);

        if (received.isEmpty()) {
            return "";
        }

        String x = received.get(0);

        x = x.replace('/', '\n');

        return x;
    }

    public String getExInputsStringByQuestionId(int id) {

        ArrayList<String> received = new ArrayList<>();
        received = this.getInputByQuestionId(id);

        if (received.isEmpty()) {
            return "";
        }

        String x = received.get(0);

        x = x.replace('/', '\n');

        return x;
    }

}