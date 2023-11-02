package qxcode_implements.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import qxcode_implements.JDBC.JDBC;
import qxcode_implements.Model.Question;

public class QuestionDAO {

    Connection conn = JDBC.getConnection();

    public Question getQuestionById(int id) {
        Connection conn = JDBC.getConnection();

        if (conn != null) {
            try {
                String sql = "SELECT id_questao, descricao, titulo, dificuldade, exemplos FROM questao WHERE id_questao = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
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
        }

        return null;
    }

    public String getExamplesById(int id) {
        String exemplos = null;
        String sql = "SELECT exemplos FROM questao WHERE id_questao = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            exemplos = rs.getString("exemplos");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exemplos;
    }

    public String getTitleById(int id) {
        String title = null;
        String sql = "SELECT titulo FROM questao WHERE id_questao = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                title = rs.getString("titulo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }

}
