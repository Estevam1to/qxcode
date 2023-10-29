package qxcode_implements.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qxcode_implements.JDBC.JDBC;
import qxcode_implements.Model.Category;

public class CategoryDAO {
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        Connection conn = JDBC.getConnection();

        if (conn != null) {
            try {
                String sql = "SELECT id_categoria, titulo, descricao FROM categoria";
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return categories;
    }
}
