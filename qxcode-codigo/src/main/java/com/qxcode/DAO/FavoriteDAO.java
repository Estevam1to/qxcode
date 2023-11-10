package com.qxcode.DAO;

import com.qxcode.JDBC.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FavoriteDAO {

    public void Insert(int id) {
        String sql = "INSERT INTO favorito (id_questao) VALUES (?)";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Delete (int id) {
        String sql = "DELETE FROM favorito WHERE id_questão = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getFavoritesID() {
        ArrayList<Integer> favoriteIDs = new ArrayList<>();
        String sql = "SELECT id_questao FROM favorito";

        try (Connection conn = JDBC.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);){

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idQuestao = rs.getInt("id_questao");
                favoriteIDs.add(idQuestao);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return favoriteIDs;
    }
}