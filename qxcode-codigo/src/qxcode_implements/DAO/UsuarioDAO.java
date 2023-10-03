package qxcode_implements.DAO;
import java.sql.Connection;
public class UsuarioDAO {
    Connection cnx;

    public UsuarioDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public void insertUsuario(Usuario usuario) {
        // ...
    }

    public void updateUsuario(Usuario usuario) {
        // ...
    }

    public void deleteUsuario(Usuario usuario) {
        // ...
    }

    public Usuario getUsuario(int id) {
        // ...
    }
}
