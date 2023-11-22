package com.qxcode.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.qxcode.JDBC.JDBC;
import com.qxcode.Model.Category;
import com.qxcode.Model.Question;

public class QuestionDAO {

    public List<Question> getQuestionsByCategory(int categoryId){
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questao WHERE id_categoria = ?";


        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);){

            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_categoria");
                String title = resultSet.getString("titulo");
                String description = resultSet.getString("descricao");
                int difficulty = resultSet.getInt("dificuldade");
                String examples = resultSet.getString("exemplos");
                int categId = resultSet.getInt("id_categoria");
                int favorite = resultSet.getInt("favorito");


                Question question = new Question(id, title, description, difficulty, examples, categId, favorite);
                questions.add(question);
            }

            resultSet.close();
            preparedStatement.close();
            return questions;
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
                int id = resultSet.getInt("id_categoria");
                String title = resultSet.getString("titulo");
                String description = resultSet.getString("descricao");
                int difficulty = resultSet.getInt("dificuldade");
                String examples = resultSet.getString("exemplos");
                int categId = resultSet.getInt("id_categoria");
                int favorite = resultSet.getInt("favorito");


                Question question = new Question(id, title, description, difficulty, examples, categId, favorite);
                questions.add(question);
            }

            resultSet.close();
            preparedStatement.close();
            return questions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void insertQuestion(String title, String description, Integer difficulty, String examples, int categoryId) {
        String sql = "INSERT INTO questao (titulo, descricao, dificuldade, exemplos, id_categoria) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = JDBC.getConnection()) {
            conn.setAutoCommit(false); // Starts transaction.

            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, description);
                preparedStatement.setInt(3, difficulty);
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
                        Question novaQuestao = new Question(idGerado, title, description, difficulty, examples, categoryId, 0);

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

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questao";


        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();){

            while (resultSet.next()) {
                int id = resultSet.getInt("id_questao");
                String description = resultSet.getString("descricao");
                String title = resultSet.getString("titulo");
                int difficulty = resultSet.getInt("dificuldade");
                String examples = resultSet.getString("exemplos");
                int categoryId = resultSet.getInt("id_categoria");
                int favorite = resultSet.getInt("favorito");

                Question question = new Question(id, description, title, difficulty, examples, categoryId, favorite);
                questions.add(question);
            }

            resultSet.close();
            preparedStatement.close();
            return questions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Question getQuestionByTitle(String titulo) {
        Question question = null;
        String sql = "SELECT * FROM questao WHERE titulo = ?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();

                int id = rs.getInt("id_questao");
                String title = rs.getString("titulo");
                String description = rs.getString("descricao");
                int difficulty = rs.getInt("dificuldade");
                String examples = rs.getString("exemplos");
                int categoryId = rs.getInt("id_categoria");
                int favorite = rs.getInt("favorito");

                question = new Question(id, description, title, difficulty, examples,categoryId, favorite);


            return question;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
