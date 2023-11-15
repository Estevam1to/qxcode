package com.qxcode.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.qxcode.JDBC.JDBC;
import com.qxcode.Model.TestCase;


public class TestCaseDAO {

    public void Insert(TestCase t) {

        String sql = "INSERT INTO caso_de_teste VALUES (?, ?, ?, ?)";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, t.getId());
            preparedStatement.setString(2, t.getInput());
            preparedStatement.setString(3, t.getOutput());
            preparedStatement.setInt(4, t.getQuestion_Id());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteByQuestionId (int id) {
        String sql = "DELETE FROM caso_de_teste WHERE id_questão = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteByTestCaseId (int id) {
        String sql = "DELETE FROM caso_de_teste WHERE id_caso_de_teste = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

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
        String outputs = "";
        received = this.getOutputByQuestionId(id);

        for (String x : received) {
            if (!outputs.isEmpty()) {
                outputs = outputs.concat("\n");
            }
            outputs = outputs.concat(x);
        }

        return outputs;
    }

    public String getInputsStringByQuestionId(int id) {

        ArrayList<String> received = new ArrayList<>();
        String inputs = "";
        received = this.getInputByQuestionId(id);

        for (String x : received) {
            if (!inputs.isEmpty()) {
                inputs = inputs.concat("\n");
            }
            inputs = inputs.concat(x);
        }

        return inputs;
    }

}