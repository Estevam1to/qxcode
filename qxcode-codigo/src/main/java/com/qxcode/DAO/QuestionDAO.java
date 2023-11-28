package com.qxcode.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.qxcode.JDBC.JDBC;
import com.qxcode.Model.Category;
import com.qxcode.Model.Question;

public class QuestionDAO {

    private Question createQuestion(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id_questao");
        String title = resultSet.getString("titulo");
        String description = resultSet.getString("descricao");
        int difficulty = resultSet.getInt("dificuldade");
        String examples = resultSet.getString("exemplos");
        int categId = resultSet.getInt("id_categoria");
        int favorite = resultSet.getInt("favorito");

        Question question = new Question(id,description, title, difficulty, examples, categId, favorite);
        return question;
    }

    public List<Question> getQuestionsByCategory(int categoryId) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questao WHERE id_categoria = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
             preparedStatement.setInt(1, categoryId);
             ResultSet resultSet = preparedStatement.executeQuery();

             while (resultSet.next()) {
                 questions.add(createQuestion(resultSet));
             }

             resultSet.close();
             return questions;
        } catch (SQLException e) {
            return null;
        }
    }

    public Question getQuestionById(int idQuestion){
        String sql = "SELECT * FROM questao WHERE id_questao = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, idQuestion);
            ResultSet resultSet = preparedStatement.executeQuery();

            Question question = createQuestion(resultSet);

            resultSet.close();
            return question;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Question> getFavoriteQuestions(){
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questao WHERE favorito = 1";


        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();){


            while (resultSet.next()) {
                while (resultSet.next()) {
                    questions.add(createQuestion(resultSet));
                }
            }

            resultSet.close();
            preparedStatement.close();
            return questions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void insertQuestion(String title, String description, Integer dificulty, String examples, int categoryId) {
        String sql = "INSERT INTO questao (titulo, descricao, dificuldade, exemplos, id_categoria) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = JDBC.getConnection()) {
            conn.setAutoCommit(false); // Starts transaction.

            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, description);
                preparedStatement.setInt(3, dificulty);
                preparedStatement.setString(4, examples);
                preparedStatement.setInt(5, categoryId);


                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected == 0) {
                    throw new SQLException("Falha ao adicionar questao, nenhuma linha afetada.");
                }

                try (Statement statement = conn.createStatement();
                     ResultSet generatedKeys = statement.executeQuery("SELECT last_insert_rowid()")) {
                    if (generatedKeys.next()) {
                        int idGerado = generatedKeys.getInt(1);

                        // Criar uma nova instância do modelo Category com o ID gerado
                        Question novaQuestao = new Question(idGerado, title, description, dificulty, examples, categoryId, 0);

                        conn.commit(); // Commits transaction.
                    } else {
                        throw new SQLException("Falha ao obter o ID da questao, nenhum ID gerado.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
