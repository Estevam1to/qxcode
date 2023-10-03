package qxcode_implements.DAO;
import java.sql.Connection;
public class CategoriaDAO {
    Connection cnx;

    public CategoriaDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public void insertCategoria(Categoria categoria) {
        // ...
    }

    public void updateCategoria(Categoria categoria) {
        // ...
    }

    public void deleteCategoria(Categoria categoria) {
        // ...
    }

    public Categoria getCategoria(int id) {
        // ...
    }
}
