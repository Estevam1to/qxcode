package com.qxcode.DAO;

import java.sql.*;
import java.util.ArrayList;

import com.qxcode.JDBC.JDBC;
import com.qxcode.Model.Category;
import com.qxcode.Model.TestCase;


public class TestCaseDAO {

    public void insertTestCase(String inputStr, String outputStr,int idQuestion) {
        String sql = "INSERT INTO caso_de_teste (input, output, id_questão) VALUES (?, ?, ?)";
        try (Connection conn = JDBC.getConnection()) {
            conn.setAutoCommit(false); // Starts transaction.

            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, inputStr);
                preparedStatement.setString(2, outputStr);
                preparedStatement.setInt(3, idQuestion);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected == 0) {
                    throw new SQLException("Falha ao adicionar categoria, nenhuma linha afetada.");
                }

                try (Statement statement = conn.createStatement();
                     ResultSet generatedKeys = statement.executeQuery("SELECT last_insert_rowid()")) {
                    if (generatedKeys.next()) {
                        int idGerado = generatedKeys.getInt(1);

                        // Criar uma nova instância do modelo Category com o ID gerado
                        TestCase novoCasoDeTeste = new TestCase(idGerado, inputStr, outputStr, idQuestion);

                        conn.commit(); // Commits transaction.
                    } else {
                        throw new SQLException("Falha ao obter o ID da categoria, nenhum ID gerado.");
                    }
                }
            }
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