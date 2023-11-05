package com.qxcode.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qxcode.JDBC.JDBC;
import com.qxcode.Model.Category;

public class CategoryDAO {
    Connection conn = JDBC.getConnection();
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        Connection conn = JDBC.getConnection();

        if (conn != null) {
            try {
                String sql = "SELECT * FROM categoria";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id_categoria");
                    String title = resultSet.getString("titulo");
                    String description = resultSet.getString("descricao");

                    Category category = new Category(id, title, description);
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

        return null;
    }

    public String getTitleById(int id) {
        String title = null;
        String sql = "SELECT titulo FROM categoria WHERE id_categoria = ?";
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

    public String getDescriptionById(int id) {
        String description = null;
        String sql = "SELECT descricao FROM categoria WHERE id_categoria = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                description = rs.getString("descricao");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return description;
    }

}
