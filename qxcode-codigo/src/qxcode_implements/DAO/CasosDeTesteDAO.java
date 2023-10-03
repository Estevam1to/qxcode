package qxcode_implements.DAO;

import java.sql.Connection;
public class CasosDeTesteDAO {
    Connection cnx;

    public CasosDeTesteDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public void insertCasosDeTeste(CasosDeTeste casosDeTeste) {
        // ...
    }

    public void updateCasosDeTeste(CasosDeTeste casosDeTeste) {
        // ...
    }

    public void deleteCasosDeTeste(CasosDeTeste casosDeTeste) {
        // ...
    }

    public CasosDeTeste getCasosDeTeste(int id) {
        // ...
    }
}
