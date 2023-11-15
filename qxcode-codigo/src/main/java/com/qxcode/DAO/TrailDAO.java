package com.qxcode.DAO;

import com.qxcode.JDBC.JDBC;
import com.qxcode.Model.Trail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrailDAO {

    public void Insert(Trail t) {
        String sql = "INSERT INTO trilhas VALUES (id_trilha, dificuldade, titulo, descricao)";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, t.getId());
            preparedStatement.setInt(2, t.getDifficulty());
            preparedStatement.setString(3, t.getTitle());
            preparedStatement.setString(4, t.getDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Delete(int id) {
        String sql = "DELETE FROM trilhas WHERE id_trilha = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getTrailQuestionsId(int id) {
        ArrayList<Integer> trail_questions_ids = new ArrayList<>();
        String sql = "SELECT id_questão FROM trilhas_questao WHERE id_trilha = ?";

        try(Connection conn = JDBC.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id_questao = resultSet.getInt("id_questao");

                trail_questions_ids.add(id_questao);
            }

            resultSet.close();
            preparedStatement.close();
            return trail_questions_ids;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

