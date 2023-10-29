package qxcode_implements.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qxcode_implements.JDBC.JDBC;
import qxcode_implements.Model.Question;

public class QuestionsDAO {
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        Connection conn = JDBC.getConnection();

        if (conn != null) {
            try {
                String sql = "SELECT id_questao, descricao, titulo, dificuldade, exemplos FROM questao";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id_questao");
                    String description = resultSet.getString("descricao");
                    String title = resultSet.getString("titulo");
                    int difficulty = resultSet.getInt("dificuldade");
                    String examples = resultSet.getString("exemplos");

                    Question question = new Question(id, description, title, difficulty, examples);
                    questions.add(question);
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return questions;
    }
}
