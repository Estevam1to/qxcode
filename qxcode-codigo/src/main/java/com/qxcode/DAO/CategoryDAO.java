package com.qxcode.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.qxcode.JDBC.JDBC;
import com.qxcode.Model.Category;

public class CategoryDAO implements IDAO{

    public Category resultSetToObject(ResultSet resultSet) throws SQLException {
        Category category = new Category(
        resultSet.getInt("id_categoria"),
        resultSet.getString("titulo"),
        resultSet.getString("descricao")
        );

        return category;

    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categoria";


            try (Connection conn = JDBC.getConnection();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery();){

                while (resultSet.next()) {

                    Category category = resultSetToObject(resultSet);
                    categories.add(category);

                }

                resultSet.close();
                preparedStatement.close();

                return categories;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

    public Category getById(int idC) {
        Category categoria = null;
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idC);
            ResultSet resultSet = stmt.executeQuery();

            categoria = resultSetToObject(resultSet);


            return categoria;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro no DAO, função getCategoryById");
            return null;
        }
    }
    public Category getByTitle(String titulo) {
        Category categoria = null;
        String sql = "SELECT * FROM categoria WHERE titulo = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {

                categoria = resultSetToObject(resultSet);
            }

            return categoria;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro no DAO, função getCategoryById");
            return null;
        }
    }

    public void insertCategory(String title, String description) {
        String sql = "INSERT INTO categoria (titulo, descricao) VALUES (?, ?)";

        try (Connection conn = JDBC.getConnection()) {
            conn.setAutoCommit(false); // Starts transaction.

            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, description);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected == 0) {
                    throw new SQLException("Falha ao adicionar categoria, nenhuma linha afetada.");
                }

                try (Statement statement = conn.createStatement();
                     ResultSet generatedKeys = statement.executeQuery("SELECT last_insert_rowid()")) {
                    if (generatedKeys.next()) {
                        int idGerado = generatedKeys.getInt(1);

                        // Criar uma nova instância do modelo Category com o ID gerado
                        Category novaCategoria = new Category(idGerado, title, description);

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

    public void deleteCategory(int id) {
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";

        try{
            Connection conn = JDBC.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCategory(int id, String titulo, String descricao) {
        String sql = "UPDATE categoria SET titulo = ?, descricao = ? WHERE id_categoria = ?";

        try{
            Connection conn = JDBC.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, titulo);
            stmt.setString(2, descricao);
            stmt.setInt(3, id);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
