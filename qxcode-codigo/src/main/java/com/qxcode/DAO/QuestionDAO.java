package com.qxcode.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qxcode.JDBC.JDBC;
import com.qxcode.Model.Question;

public class QuestionDAO {

    public void Insert (Question question) {
        String sql = "INSERT INTO questão (id_questão, descrição, titulo, dificuldade, exemplos) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, question.getId());
            preparedStatement.setString(2, question.getDescription());
            preparedStatement.setString(3, question.getTitle());
            preparedStatement.setInt(4, question.getDifficulty());
            preparedStatement.setString(5, question.getExamples());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Delete (int id) {
        String sql = "DELETE FROM questão WHERE id_questão = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Question getQuestionById(int id) {

        String sql = "SELECT id_questao, descricao, titulo, dificuldade, exemplos FROM questao WHERE id_questao = ?";

        try(Connection conn = JDBC.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);) {

                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int questionId = resultSet.getInt("id_questao");
                    String description = resultSet.getString("descricao");
                    String title = resultSet.getString("titulo");
                    int difficulty = resultSet.getInt("dificuldade");
                    String examples = resultSet.getString("exemplos");

                    return new Question(id, description, title, difficulty, examples);
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }

    public String getExamplesById(int id) {
        String exemplos = null;
        String sql = "SELECT exemplos FROM questao WHERE id_questao = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            exemplos = rs.getString("exemplos");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exemplos;
    }

    public String getTitleById(int id) {
        String title = null;
        String sql = "SELECT titulo FROM questao WHERE id_questao = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                title = rs.getString("titulo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }

}
