package qxcode_implements.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FavoriteDAO {

    Connection conn = JDBC.getConnection();

    public ArrayList<Integer> getFavoritesID() {
        ArrayList<Integer> favoriteIDs = new ArrayList<>();

        String sql = "SELECT id_questao FROM favorito";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idQuestao = rs.getInt("id_questao");
                favoriteIDs.add(idQuestao);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return favoriteIDs;
    }
}