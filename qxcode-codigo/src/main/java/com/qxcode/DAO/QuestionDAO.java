package com.qxcode.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.qxcode.JDBC.JDBC;
import com.qxcode.Model.Question;

public class QuestionDAO implements IDAO{

    public Question resultSetToObject(ResultSet resultSet) throws SQLException {

        Question question = new Question(resultSet.getInt("id_questao"),
        resultSet.getString("descricao"),
        resultSet.getString("titulo"),
        resultSet.getInt("dificuldade"),
        resultSet.getString("exemplos"),
        resultSet.getInt("id_categoria"),
        resultSet.getInt("favorito")
        );

        return question;
    }

    public void addFavorite(int id) {
        String sql = "UPDATE questao SET favorito = ? WHERE id_questao = ?";

        try (   Connection conn = JDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, 1);
            stmt.setInt(2, id);


            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFavorite(int id) {
        String sql = "UPDATE questao SET favorito = ? WHERE id_questao = ?";

        try (   Connection conn = JDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, 0);
            stmt.setInt(2, id);


            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateQuestionFavorite(int id) {

        if (this.getById(id).getFavorite() != 1) {
            addFavorite(id);
        } else {
            removeFavorite(id);
        }
    }

    public Question getById(int idQuestion){
        String sql = "SELECT * FROM questao WHERE id_questao = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, idQuestion);
            ResultSet resultSet = preparedStatement.executeQuery();

            Question question = resultSetToObject(resultSet);

            resultSet.close();
            return question;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Question> getQuestionsByCategory(int categoryId){
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questao WHERE id_categoria = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
             preparedStatement.setInt(1, categoryId);
             ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Question question = resultSetToObject(resultSet);
                questions.add(question);

            }

            resultSet.close();
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

                Question question = resultSetToObject(resultSet);
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
    public void insertQuestion(String title, String description, int difficulty, String examples, int categoryId) {
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
                Question question = resultSetToObject(resultSet);
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

    public Question getByTitle(String titulo) {
        String sql = "SELECT * FROM questao WHERE titulo = ?";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            ResultSet resultSet = stmt.executeQuery();

            Question question = resultSetToObject(resultSet);

            resultSet.close();
            stmt.close();

            return question;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
